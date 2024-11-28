package fi.ishtech.base.spec;

import static fi.ishtech.base.entity.BaseStandardNoIdEntity_.DESCRIPTION;
import static fi.ishtech.base.entity.BaseStandardNoIdEntity_.IS_ACTIVE;

import java.util.List;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import fi.ishtech.base.entity.BaseStandardNoIdEntity;
import fi.ishtech.base.payload.filter.BaseStandardNoIdEntityFilterParams;

/**
 *
 * @param &lt;T extends {@link BaseStandardNoIdEntity}&gt;
 * @param &lt;P extends {@link BaseStandardNoIdEntityFilterParams}&gt;
 *
 * @author Muneer Ahmed Syed
 */
public abstract class BaseStandardNoIdSpec<T extends BaseStandardNoIdEntity, P extends BaseStandardNoIdEntityFilterParams>
		extends BaseSpec<T, P> {

	private static final long serialVersionUID = -5813791296499595189L;

	public BaseStandardNoIdSpec(P params) {
		super(params);
	}

	@Override
	protected List<Predicate> toPredicateList(Root<T> root, CriteriaBuilder cb) {
		List<Predicate> predicates = super.toPredicateList(root, cb);

		addPredicateEq(predicates, root, cb, getParams().getIsActive(), IS_ACTIVE);
		addPredicateLike(predicates, root, cb, getParams().getDescription(), DESCRIPTION);

		return predicates;
	}

}