package fi.ishtech.base.spec;

import java.util.List;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import fi.ishtech.base.entity.BaseLovLangEntity;
import fi.ishtech.base.entity.BaseLovLangEntity_;
import fi.ishtech.base.payload.filter.BaseLovLangEntityWithoutLovFilterParams;

/**
 *
 * @param &lt;T extends {@link BaseLovLangEntity}&gt;
 * @param &lt;P extends {@link BaseLovLangEntityWithoutLovFilterParams}&gt;
 *
 * @author Muneer Ahmed Syed
 */
public abstract class BaseLovLangWithoutLovSpec<T extends BaseLovLangEntity<?>, P extends BaseLovLangEntityWithoutLovFilterParams>
		extends BaseStandardSpec<T, P> {

	private static final long serialVersionUID = -5563368345031577148L;

	public BaseLovLangWithoutLovSpec(P params) {
		super(params);
	}

	@Override
	protected List<Predicate> toPredicateList(Root<T> root, CriteriaBuilder cb) {
		var predicates = super.toPredicateList(root, cb);

		addPredicateLike(predicates, root, cb, getParams().getLovId(), BaseLovLangEntity_.LOV_ID);
		addPredicateLike(predicates, root, cb, getParams().getLabel(), BaseLovLangEntity_.LABEL);
		addPredicateEq(predicates, root, cb, getParams().getLang(), BaseLovLangEntity_.LANG);
		addPredicateIn(predicates, root, cb, getParams().getLangArr(), BaseLovLangEntity_.LANG);

		return predicates;
	}

}