# Known Issues

Confirmed issues that are not fixed yet.

## 1. Default VO mapping methods of the base services return base VOs instead of the service's VO type
**Status:** open
**Impact:** high for any service that doesn't override these methods: get-by-id fails with `500 Internal Server Error` (`ClassCastException`), and list APIs return `200 OK` with only the base fields, so the entity's own fields are silently missing
**Affects:**
- `BaseStandardService.findOneByIdAndMapToVoOrElseThrow(Long)` and `BaseStandardService.findAllAndMapToVo(spec, pageable)`
- `BaseStandardNoIdService.findOneByIdAndMapToVoOrElseThrow(ID)` and `BaseStandardNoIdService.findAllAndMapToVo(spec, pageable)`
- `BaseLovService.findOneByNameAndMapToVo(String)` and `BaseMasterService.findOneByNameAndMapToVo(String)`, which follow the same pattern (not tested)
- Confirmed in `springboot-oms`: `GET /api/v1/products/{productId}` and `GET /api/v1/customer-discounts/{customerDiscountId}` return `500`, and `GET /api/v1/products` returns items without `name` and `unitPrice`. `GET /api/v1/sales-orders/{salesOrderId}` and the customer discount and sales order list APIs use the same methods (not tested).
- Not affected: `ishtech-springboot-jwtauth`, `springboot-books-app` and `springboot-multi-port`, whose services override these methods.

### Description
The default methods map the entity with the base mapper method, `getMapper().toBaseStandardVo(...)` (or `toBaseStandardNoIdVo(...)`, `toBaseLovBriefVo(...)`, `toBaseMasterVo(...)`). That method creates an instance of the base VO class, for example `BaseStandardEntityVo`, not of the service's VO type `V`. The result is then cast unchecked to `V` or `Page<V>`:

```java
default V findOneByIdAndMapToVoOrElseThrow(Long id) {
	return (V) getMapper().toBaseStandardVo(this.findOneByIdOrElseThrow(id));
}

default Page<V> findAllAndMapToVo(BaseStandardSpec<T, ? extends BaseStandardEntityFilterParams> spec, Pageable pageable) {
	return (Page<V>) this.findAll(spec, pageable).map(getMapper()::toBaseStandardVo);
}
```

Because of type erasure, the cast inside the method does nothing:
- For a single object, the caller's implicit cast to the concrete VO type fails with `ClassCastException`, for example `class fi.ishtech.base.vo.BaseStandardEntityVo cannot be cast to class fi.ishtech.practice.oms.payload.ProductVo`.
- For a page, no element is cast before Jackson serializes it, so the response is `200 OK` with only the base VO's fields (`id`, `isActive`, `description`).

The behavior dates from commit `d94df71` (`remove default toBriefVo methods from base classes to avoid conflicts in implementations in child services`, 2025-11-12), which replaced `getMapper().toBriefVo(...)` with the base mapper methods in these default methods. `toBriefVo` was declared in the base mappers and implemented by each child mapper (for example `ProductMapper.toBriefVo` in `springboot-oms`), so it returned the concrete VO type; the base mapper methods don't.

### Steps to reproduce
Using `springboot-oms` (its `DB-SETUP.md` and `CURL-INFO.md`), with a user whose role is `ADMIN`:
1. Create a product: `POST /api/v1/products` with `{"name": "Sofa", "unitPrice": 150}`. Expected and actual: `201`, with the new product `id` in the body.
2. Get it: `GET /api/v1/products/{id}`.
   - Expected: `200` with `id`, `name` and `unitPrice`.
   - Actual: `500`; the application log shows the `ClassCastException` above.
3. List products: `GET /api/v1/products`.
   - Expected: each item has `name` and `unitPrice`.
   - Actual: `200` with items like `{"description":null,"id":5,"isActive":true}`.

### Likely cause
The base services have no way to map to the concrete VO type `V`: the base mappers only declare methods that return the base VO classes, and the unchecked casts hide the mismatch at compile time.

### Suggested fix
- Declare abstract mapping methods in the base services, for example `V mapToVo(T entity)`, and use them in the default methods instead of the base mapper methods. Each child service then implements the mapping with its own mapper, for example `productMapper.toBriefVo(entity)`, and the compiler enforces it. This is a breaking change for consumers of the library.
- Alternatively, remove these default methods, so that every child service must implement them.
- Restoring `toBriefVo` in the base mappers would also fix it, but brings back the conflicts in child mappers that commit `d94df71` removed it for.
- Remove the unchecked casts and `@SuppressWarnings("unchecked")`.
- Add tests with a concrete entity, VO and service that check the returned object's class and fields for get-by-id and for a page.

## 2. New entities are saved with `is_active = false` unless the request sets `isActive`
**Status:** open. A fix is in progress on branch `feature/is-active-boxed-boolean` (not merged).
**Impact:** high: every new record created without an explicit `isActive` is stored as inactive, although the table declares `is_active ... NOT NULL DEFAULT true`
**Affects:**
- `BaseStandardNoIdEntity.isActive` and `BaseStandardNoIdEntityVo.isActive`, both `protected boolean`, and therefore every entity and VO that extends them: `BaseStandardEntity`, `BaseMasterEntity`, `BaseLovEntity`, `BaseLovLangEntity` and their VOs
- Confirmed in `springboot-books-app`: `POST /api/v1/books` returns `"isActive":false`, and the new row in `t_book` has `is_active = false`.

### Description
`isActive` is a primitive `boolean`, so it is `false` in a new entity, and also in a VO deserialized from a request that doesn't contain `isActive`. The mapper copies that `false` to the entity. The column is mapped with `insertable = true`, and Hibernate lists every mapped column in the insert, so the insert always writes `false` and the table's default `true` is never used:

```sql
insert into booksapp_dev_schema.t_book (author,description,is_active,price,title,year) values (?,?,?,?,?,?)
```

A primitive also can't represent "not given", so neither the mapper nor the entity can tell a missing `isActive` from an explicit `false`. A request with `"isActive": true` is stored correctly.

### Steps to reproduce
Using `springboot-books-app` (its `DB-SETUP.md` and `CURL-INFO.md`), signed in as any user:
1. Create a book without `isActive`: `POST /api/v1/books` with `{"title": "Dune II", "author": "Frank Herbert", "year": 1970, "price": 29.99}`.
   - Expected: `201` with `"isActive": true`.
   - Actual: `201` with `"isActive": false`; `GET /api/v1/books/{id}` also returns `"isActive": false`.
2. Check the row: `SELECT is_active FROM booksapp_dev_schema.t_book WHERE id = <id>;`.
   - Expected: `true`.
   - Actual: `false`.
3. Create another book with `"isActive": true` in the request body. Expected and actual: `201` with `"isActive": true`.

### Likely cause
The primitive `boolean` field defaults to `false`, and because Hibernate inserts every mapped column, that value overrides the column default `true`.

### Suggested fix
- Change `isActive` in `BaseStandardNoIdEntity` and `BaseStandardNoIdEntityVo` to `Boolean`, so that "not given" is `null`.
- Make a new entity active by default, for example by initializing the field to `Boolean.TRUE` or by setting it in `@PrePersist` when it is `null`, and keep an explicit `false` from the request. The tests on branch `feature/is-active-boxed-boolean` show that `@DynamicInsert` alone doesn't load the database default into the saved entity, so the response would still not show `true`.
- Changing the public accessors from `boolean` to `Boolean` is a breaking change for consumers of the library, so it needs a major version bump.
- Add tests that create an entity without `isActive` and check that it is stored and returned as `true`, and with `isActive = false` and check that it is stored as `false`.
