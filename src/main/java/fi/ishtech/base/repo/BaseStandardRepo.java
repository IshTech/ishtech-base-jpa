package fi.ishtech.base.repo;

import org.springframework.data.repository.NoRepositoryBean;

import fi.ishtech.base.entity.BaseStandardEntity;

/**
 *
 * @author Muneer Ahmed Syed
 */
@NoRepositoryBean
public interface BaseStandardRepo<T extends BaseStandardEntity> extends BaseStandardNoIdRepo<T, Long> {

}