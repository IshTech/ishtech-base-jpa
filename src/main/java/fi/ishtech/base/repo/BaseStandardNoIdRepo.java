package fi.ishtech.base.repo;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;

import fi.ishtech.base.entity.BaseStandardNoIdEntity;

/**
 *
 * @author Muneer Ahmed Syed
 */
@NoRepositoryBean
public interface BaseStandardNoIdRepo<T extends BaseStandardNoIdEntity, ID> extends BaseRepo<T, ID> {

	List<T> findAllByIsActive(boolean isActive);

	List<T> findAllByIsActiveTrue();

}