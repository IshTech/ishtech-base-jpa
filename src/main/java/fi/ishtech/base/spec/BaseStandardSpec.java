package fi.ishtech.base.spec;

import static fi.ishtech.base.entity.BaseStandardEntity_.ID;

import java.util.List;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import fi.ishtech.base.entity.BaseStandardEntity;
import fi.ishtech.base.payload.filter.BaseStandardEntityFilterParams;

/**
 *
 * @param &lt;T extends {@link BaseStandardEntity}&gt;
 * @param &lt;P extends {@link BaseStandardEntityFilterParams}&gt;
 *
 * @author Muneer Ahmed Syed
 */
public abstract class BaseStandardSpec<T extends BaseStandardEntity, P extends BaseStandardEntityFilterParams>
		extends BaseStandardNoIdSpec<T, P> {

	private static final long serialVersionUID = -8725936771922443033L;

	public BaseStandardSpec(P params) {
		super(params);
	}

	@Override
	protected List<Predicate> toPredicateList(Root<T> root, CriteriaBuilder cb) {
		List<Predicate> predicates = super.toPredicateList(root, cb);

		if (getParams().isIdLikeSearch()) {
			addPredicateLike(predicates, root, cb, getParams().getId(), ID);
		} else {
			addPredicateEq(predicates, root, cb, getParams().getId(), ID);
		}

		return predicates;
	}

}