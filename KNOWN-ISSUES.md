# Known Issues

Confirmed issues that are not fixed yet.

## 1. Release CI run fails although the release is published
**Status:** open
**Impact:** the CI run for a GitHub release is red even when the release reaches Maven Central
**Affects:** CI release runs (`.github/workflows/cicd.yml`, step "Publish to Maven Central Sonatype")

The same issue, with its description, steps to reproduce, likely cause and suggested fix, is recorded in [ishtech-validations-java, KNOWN-ISSUES.md, issue 1](https://github.com/IshTech/ishtech-validations-java/blob/dev/KNOWN-ISSUES.md#1-release-ci-run-fails-although-the-release-is-published). This repo uses the same plugin configuration (`central-publishing-maven-plugin` with `waitUntil=published` in `pom.xml`).

## 2. Default VO mapping methods of the base services return base VOs instead of the service's VO type
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
