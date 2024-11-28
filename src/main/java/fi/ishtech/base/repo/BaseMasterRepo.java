package fi.ishtech.base.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import fi.ishtech.base.entity.BaseMasterEntity;

/**
 * Base Repository
 *
 * @param <T> extends {@link BaseMasterEntity}
 *
 * @author Muneer Ahmed Syed
 */
@NoRepositoryBean
public interface BaseMasterRepo<T extends BaseMasterEntity> extends BaseStandardRepo<T> {

	public Optional<T> findOneByName(String name);

	@Query("SELECT u.id FROM #{#entityName} u WHERE u.name = :name")
	Long findIdByName(@Param("name") String name);

}