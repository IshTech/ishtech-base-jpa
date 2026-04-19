package fi.ishtech.base.repo;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;

import fi.ishtech.base.entity.BaseStandardNoIdEntity;

/**
 * Base Repository
 *
 * @param <T>  extends {@link BaseStandardNoIdEntity}
 * @param <ID> id
 *
 * @author Muneer Ahmed Syed
 */
@NoRepositoryBean
public interface BaseStandardNoIdRepo<T extends BaseStandardNoIdEntity, ID> extends BaseRepo<T, ID> {

	/**
	 * Find by input isActive
	 *
	 * @param isActive {@code boolean}
	 * @return {@link List}&lt;T&gt;
	 */
	List<T> findAllByIsActive(boolean isActive);

	/**
	 * Find active
	 *
	 * @return {@link List}&lt;T&gt;
	 */
	List<T> findAllByIsActiveTrue();

}