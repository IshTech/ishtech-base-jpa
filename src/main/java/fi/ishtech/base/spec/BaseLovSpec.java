package fi.ishtech.base.spec;

import java.util.List;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import fi.ishtech.base.entity.BaseLovEntity;
import fi.ishtech.base.entity.BaseLovLangEntity;
import fi.ishtech.base.entity.BaseLovLangEntity_;
import fi.ishtech.base.payload.filter.BaseAuditableEntityFilterParams;
import fi.ishtech.base.payload.filter.BaseLovEntityFilterParams;

/**
 *
 * @param &lt;T extends {@link BaseLovEntity}&gt;
 * @param &lt;P extends {@link BaseAuditableEntityFilterParams}&gt;
 *
 * @author Muneer Ahmed Syed
 */
public abstract class BaseLovSpec<T extends BaseLovEntity, P extends BaseLovEntityFilterParams>
		extends BaseLovWithoutLovLangSpec<T, P> {

	private static final long serialVersionUID = 924267496795483499L;

	public BaseLovSpec(P params) {
		super(params);
	}

	@Override
	protected List<Predicate> toPredicateList(Root<T> root, CriteriaBuilder cb) {
		var predicates = super.toPredicateList(root, cb);

		return predicates;
	}

	protected void addLovLangPredicates(List<Predicate> predicates,
			Join<? extends BaseLovEntity, ? extends BaseLovLangEntity<?>> joinLovLangs, CriteriaBuilder cb) {

		if (getParams().getLovLang() != null) {
			addPredicateLike(predicates, joinLovLangs, cb, getParams().getLovLang().getLovId(), BaseLovLangEntity_.LOV_ID);
			addPredicateLike(predicates, joinLovLangs, cb, getParams().getLovLang().getLabel(), BaseLovLangEntity_.LABEL);
			addPredicateEq(predicates, joinLovLangs, cb, getParams().getLovLang().getLang(), BaseLovLangEntity_.LANG);
			addPredicateIn(predicates, joinLovLangs, cb, getParams().getLovLang().getLangArr(), BaseLovLangEntity_.LANG);
		}
	}

}