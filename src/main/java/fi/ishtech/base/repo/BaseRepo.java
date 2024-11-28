package fi.ishtech.base.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import fi.ishtech.base.entity.BaseEntity;

/**
 *
 * @author Muneer Ahmed Syed
 */
@NoRepositoryBean
public interface BaseRepo<T extends BaseEntity, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

}