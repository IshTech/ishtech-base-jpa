package fi.ishtech.base.spec;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import fi.ishtech.base.entity.BaseEntity;
import fi.ishtech.base.payload.filter.BaseFilterParams;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class BaseSpec<T extends BaseEntity, P extends BaseFilterParams> implements Specification<T> {

	private static final long serialVersionUID = -3229114388128110817L;

	protected static final String PERCENT = "%";

	@Getter
	private final P params;

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates = toPredicateList(root, cb);
		query.distinct(true);

		Predicate[] predicatesArray = new Predicate[predicates.size()];
		return cb.and(predicates.toArray(predicatesArray));
	}

	protected List<Predicate> toPredicateList(Root<T> root, CriteriaBuilder cb) {
		return new ArrayList<>();
	}

	protected void addPredicateLike(List<Predicate> predicates, Root<T> root, CriteriaBuilder cb, String attribValue,
			String attribName) {
		if (StringUtils.hasText(attribValue)) {
			predicates.add(cb.like(root.get(attribName), padForSqlLike(attribValue)));
		}
	}

	protected void addPredicateLike(List<Predicate> predicates, Join<? extends BaseEntity, ? extends BaseEntity> join,
			CriteriaBuilder cb, String attribValue, String attribName) {
		if (StringUtils.hasText(attribValue)) {
			predicates.add(cb.like(join.get(attribName), padForSqlLike(attribValue)));
		}
	}

	protected void addPredicateLike(List<Predicate> predicates, Root<T> root, CriteriaBuilder cb, Number attribValue,
			String attribName) {
		if (attribValue != null) {
			predicates.add(cb.like(root.get(attribName).as(String.class), padForSqlLike(attribValue)));
		}
	}

	protected void addPredicateLike(List<Predicate> predicates, Join<? extends BaseEntity, ? extends BaseEntity> join,
			CriteriaBuilder cb, Number attribValue, String attribName) {
		if (attribValue != null) {
			predicates.add(cb.like(join.get(attribName).as(String.class), padForSqlLike(attribValue)));
		}
	}

	protected void addPredicateEq(List<Predicate> predicates, Root<T> root, CriteriaBuilder cb, String attribValue,
			String attribName) {
		if (StringUtils.hasText(attribValue)) {
			predicates.add(cb.equal(root.get(attribName), attribValue));
		}
	}

	protected void addPredicateEq(List<Predicate> predicates, Root<T> root, CriteriaBuilder cb, Number attribValue,
			String attribName) {
		if (attribValue != null) {
			predicates.add(cb.equal(root.get(attribName), attribValue));
		}
	}

	protected void addPredicateEq(List<Predicate> predicates, Root<T> root, CriteriaBuilder cb, Boolean attribValue,
			String attribName) {
		if (attribValue != null) {
			predicates.add(cb.equal(root.get(attribName), attribValue));
		}
	}

	protected void addPredicateEq(List<Predicate> predicates, Join<? extends BaseEntity, ? extends BaseEntity> join,
			CriteriaBuilder cb, String attribValue, String attribName) {
		if (attribValue != null) {
			predicates.add(cb.equal(join.get(attribName), attribValue));
		}
	}

	protected void addPredicateEq(List<Predicate> predicates, Join<? extends BaseEntity, ? extends BaseEntity> join,
			CriteriaBuilder cb, Number attribValue, String attribName) {
		if (attribValue != null) {
			predicates.add(cb.equal(join.get(attribName), attribValue));
		}
	}

	protected void addPredicateEq(List<Predicate> predicates, Join<? extends BaseEntity, ? extends BaseEntity> join,
			CriteriaBuilder cb, Boolean attribValue, String attribName) {
		if (attribValue != null) {
			predicates.add(cb.equal(join.get(attribName), attribValue));
		}
	}

	protected void addPredicateIn(List<Predicate> predicates, Root<T> root, CriteriaBuilder cb, Object[] attribValue,
			String attribName) {
		if (!ArrayUtils.isEmpty(attribValue)) {
			predicates.add(root.get(attribName).in(attribValue));
		}
	}

	protected void addPredicateIn(List<Predicate> predicates, Join<? extends BaseEntity, ? extends BaseEntity> join,
			CriteriaBuilder cb, Object[] attribValue, String attribName) {
		if (!ArrayUtils.isEmpty(attribValue)) {
			predicates.add(join.get(attribName).in(attribValue));
		}
	}

	protected void addPredicateGE(List<Predicate> predicates, Root<T> root, CriteriaBuilder cb, BigDecimal attribValue,
			String attribName) {
		if (attribValue != null) {
			predicates.add(cb.ge(root.get(attribName), attribValue));
		}
	}

	protected void addPredicateLE(List<Predicate> predicates, Root<T> root, CriteriaBuilder cb, BigDecimal attribValue,
			String attribName) {
		if (attribValue != null) {
			predicates.add(cb.le(root.get(attribName), attribValue));
		}
	}

	protected void addPredicateGE(List<Predicate> predicates, Root<T> root, CriteriaBuilder cb, Integer attribValue,
			String attribName) {
		if (attribValue != null) {
			predicates.add(cb.ge(root.get(attribName), attribValue));
		}
	}

	protected void addPredicateLE(List<Predicate> predicates, Root<T> root, CriteriaBuilder cb, Integer attribValue,
			String attribName) {
		if (attribValue != null) {
			predicates.add(cb.le(root.get(attribName), attribValue));
		}
	}

	protected void addPredicateGE(List<Predicate> predicates, Root<T> root, CriteriaBuilder cb, Long attribValue,
			String attribName) {
		if (attribValue != null) {
			predicates.add(cb.ge(root.get(attribName), attribValue));
		}
	}

	protected void addPredicateLE(List<Predicate> predicates, Root<T> root, CriteriaBuilder cb, Long attribValue,
			String attribName) {
		if (attribValue != null) {
			predicates.add(cb.le(root.get(attribName), attribValue));
		}
	}

	protected void addPredicateGE(List<Predicate> predicates, Root<T> root, CriteriaBuilder cb,
			LocalDateTime attribValue, String attribName) {
		if (attribValue != null) {
			predicates.add(cb.greaterThanOrEqualTo(root.get(attribName), attribValue));
		}
	}

	protected void addPredicateLE(List<Predicate> predicates, Root<T> root, CriteriaBuilder cb,
			LocalDateTime attribValue, String attribName) {
		if (attribValue != null) {
			predicates.add(cb.lessThanOrEqualTo(root.get(attribName), attribValue));
		}
	}

	@SuppressWarnings("hiding")
	protected <T, X> Join<T, X> getJoin(Join<T, X> join, Root<T> root, String joinAttrib) {
		if (join == null) {
			join = (Join<T, X>) root.<T, X>join(joinAttrib);
		}
		return join;
	}

	@SuppressWarnings("hiding")
	protected <T, X> Join<T, X> getJoin(Join<T, X> join, Root<T> root, String joinAttrib, JoinType joinType) {
		if (join == null) {
			join = (Join<T, X>) root.<T, X>join(joinAttrib, joinType);
		}
		return join;
	}

	protected <X, Y> Join<X, Y> getDeepJoin(Join<X, Y> join, Join<T, X> parentJoin, String joinAttrib) {
		if (join == null) {
			join = (Join<X, Y>) parentJoin.<X, Y>join(joinAttrib);
		}
		return join;
	}

	protected <X, Y> Join<X, Y> getDeepJoin(Join<X, Y> join, Join<T, X> parentJoin, String joinAttrib,
			JoinType joinType) {
		if (join == null) {
			join = (Join<X, Y>) parentJoin.<X, Y>join(joinAttrib, joinType);
		}
		return join;
	}

	/**
	 * TODO: move to some util class
	 *
	 * @param values
	 *
	 * @return {@code true} if {@code null} or empty else {@code false}
	 */
	@SuppressWarnings("unused")
	private boolean isArrayEmpty(Object[] values) {
		return values == null || values.length == 0;
	}

	/**
	 * TODO: move to some util class
	 *
	 * @param input
	 * @return input padded with % on either side
	 */
	protected String padForSqlLike(String input) {
		return PERCENT + input + PERCENT;
	}

	/**
	 * TODO: move to some util class
	 *
	 * @param input
	 * @return input padded with % on either side
	 */
	protected String padForSqlLike(Number input) {
		return PERCENT + input + PERCENT;
	}

	/**
	 * TODO: move to some util class
	 *
	 * @param input
	 * @return input padded with % on either side
	 */
	protected String padForSqlLike(Integer input) {
		return PERCENT + input + PERCENT;
	}

}