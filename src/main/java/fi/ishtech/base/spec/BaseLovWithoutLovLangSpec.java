package fi.ishtech.base.spec;

import java.util.List;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import fi.ishtech.base.entity.BaseLovEntity;
import fi.ishtech.base.entity.BaseLovEntity_;
import fi.ishtech.base.payload.filter.BaseLovEntityWithoutLovLangFilterParams;

/**
 *
 * @param &lt;T extends {@link BaseLovEntity}&gt;
 * @param &lt;P extends {@link BaseLovEntityWithoutLovLangFilterParams}&gt;
 *
 * @author Muneer Ahmed Syed
 */
public abstract class BaseLovWithoutLovLangSpec<T extends BaseLovEntity, P extends BaseLovEntityWithoutLovLangFilterParams>
		extends BaseStandardSpec<T, P> {

	private static final long serialVersionUID = 250453381737732289L;

	public BaseLovWithoutLovLangSpec(P params) {
		super(params);
	}

	@Override
	protected List<Predicate> toPredicateList(Root<T> root, CriteriaBuilder cb) {
		var predicates = super.toPredicateList(root, cb);

		addPredicateLike(predicates, root, cb, getParams().getName(), BaseLovEntity_.NAME);

		return predicates;
	}

}