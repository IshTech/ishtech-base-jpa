package fi.ishtech.base.spec;

import static fi.ishtech.base.entity.BaseAuditableEntity_.CREATED_AT;
import static fi.ishtech.base.entity.BaseAuditableEntity_.CREATED_BY;
import static fi.ishtech.base.entity.BaseAuditableEntity_.UPDATED_AT;
import static fi.ishtech.base.entity.BaseAuditableEntity_.UPDATED_BY;

import java.util.List;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import fi.ishtech.base.entity.BaseAuditableEntity;
import fi.ishtech.base.payload.filter.BaseAuditableEntityFilterParams;

/**
 *
 * @param &lt;T extends {@link BaseAuditableEntity}&gt;
 * @param &lt;P extends {@link BaseAuditableEntityFilterParams}&gt;
 *
 * @author Muneer Ahmed Syed
 */
public abstract class BaseAuditableSpec<T extends BaseAuditableEntity, P extends BaseAuditableEntityFilterParams>
		extends BaseSpec<T, P> {

	private static final long serialVersionUID = 8663456839776921171L;

	public BaseAuditableSpec(P params) {
		super(params);
	}

	@Override
	protected List<Predicate> toPredicateList(Root<T> root, CriteriaBuilder cb) {
		List<Predicate> predicates = super.toPredicateList(root, cb);

		addPredicateLike(predicates, root, cb, getParams().getCreatedBy(), CREATED_BY);
		addPredicateLike(predicates, root, cb, getParams().getUpdatedBy(), UPDATED_BY);

		addPredicateGE(predicates, root, cb, getParams().getCreatedAtMin(), CREATED_AT);
		addPredicateLE(predicates, root, cb, getParams().getCreatedAtMax(), CREATED_AT);

		addPredicateGE(predicates, root, cb, getParams().getUpdatedAtMin(), UPDATED_AT);
		addPredicateLE(predicates, root, cb, getParams().getUpdatedAtMax(), UPDATED_AT);

		return predicates;
	}

}