package fi.ishtech.base.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import fi.ishtech.base.entity.BaseLovEntity;

/**
 *
 * @author Muneer Ahmed Syed
 */
@NoRepositoryBean
public interface BaseLovRepo<T extends BaseLovEntity> extends BaseStandardRepo<T> {

	public Optional<T> findOneByName(String name);

	@Query("SELECT u.id FROM #{#entityName} u WHERE u.name = :name")
	Long findIdByName(@Param("name") String name);

	List<T> findAllByIsActiveTrueOrderByDisplayOrder();

}