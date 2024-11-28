package fi.ishtech.base.service;

import java.util.List;

import jakarta.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fi.ishtech.base.entity.BaseEntity;
import fi.ishtech.base.mapper.BaseEntityMapper;
import fi.ishtech.base.payload.filter.BaseFilterParams;
import fi.ishtech.base.repo.BaseRepo;
import fi.ishtech.base.spec.BaseSpec;
import fi.ishtech.base.vo.BaseEntityVo;

/**
 *
 * @author Muneer Ahmed Syed
 */
public interface BaseEntityService<T extends BaseEntity, V extends BaseEntityVo, ID> extends BaseService {

	BaseRepo<T, ID> getRepo();

	BaseEntityMapper getMapper();

	public default EntityManager getEntityManager() {
		return null;
	}

	/**
	 * Refreshes entity from DB with latest values
	 *
	 * @param entity
	 */
	public default void refresh(T entity) {
		getEntityManager().refresh(entity);
	}

	/**
	 *
	 * @return {@link List} of {@link BaseEntity}
	 */
	public default List<T> findAll() {
		return getRepo().findAll();
	}

	/**
	 * @param spec     {@link BaseSpec}
	 * @param pageable {@link Pageable}
	 *
	 * @see {@link BaseFilterParams}
	 *
	 * @return {@link Page} of {@link BaseEntity}
	 */
	public default Page<T> findAll(BaseSpec<T, ? extends BaseFilterParams> spec, Pageable pageable) {
		return getRepo().findAll(spec, pageable);
	}

	/**
	 *
	 * @param spec     {@link BaseSpec}
	 * @param pageable {@link Pageable}
	 *
	 * @see {@link BaseFilterParams}
	 *
	 * @return {@link Page} of {@link BaseEntity}
	 */
	public default Page<V> findAllAndMapToVo(BaseSpec<T, ? extends BaseFilterParams> spec, Pageable pageable) {
		return this.findAll(spec, pageable).map(getMapper()::toBriefVo);
	}

}