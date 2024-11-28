package fi.ishtech.base.spec;

import java.util.List;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import fi.ishtech.base.entity.BaseLovEntity;
import fi.ishtech.base.entity.BaseLovEntity_;
import fi.ishtech.base.entity.BaseLovLangEntity;
import fi.ishtech.base.payload.filter.BaseLovLangEntityFilterParams;

/**
 * {@link Specification} base class to build criteria
 *
 * @param <T> extends {@link BaseLovLangEntity}
 * @param <P> extends {@link BaseLovLangEntityFilterParams}
 *
 * @author Muneer Ahmed Syed
 */
public abstract class BaseLovLangSpec<T extends BaseLovLangEntity<?>, P extends BaseLovLangEntityFilterParams>
		extends BaseLovLangWithoutLovSpec<T, P> {

	private static final long serialVersionUID = 8033949711131377404L;

	public BaseLovLangSpec(P params) {
		super(params);
	}

	@Override
	protected List<Predicate> toPredicateList(Root<T> root, CriteriaBuilder cb) {
		var predicates = super.toPredicateList(root, cb);

		return predicates;
	}

	protected void addLovPredicates(List<Predicate> predicates,
			Join<? extends BaseLovLangEntity<?>, ? extends BaseLovEntity> joinLov, CriteriaBuilder cb) {
		if (getParams().getLov() != null) {
			addPredicateLike(predicates, joinLov, cb, getParams().getLov().getId(), BaseLovEntity_.ID);
			addPredicateLike(predicates, joinLov, cb, getParams().getLov().getName(), BaseLovEntity_.NAME);
			addPredicateEq(predicates, joinLov, cb, getParams().getLov().getIsActive(), BaseLovEntity_.IS_ACTIVE);
		}
	}

}