package fi.ishtech.base.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.NoRepositoryBean;

import fi.ishtech.base.entity.BaseLovEntity;
import fi.ishtech.base.entity.BaseLovLangEntity;

/**
 * Base Repository
 *
 * @param <T> extends {@link BaseLovLangEntity}
 *
 * @author Muneer Ahmed Syed
 */
@NoRepositoryBean
public interface BaseLovLangRepo<T extends BaseLovLangEntity<? extends BaseLovEntity>> extends BaseStandardRepo<T> {

	Optional<T> findOneByLovIdAndLang(Long lovId, String lang);

	Optional<T> findOneByLovNameAndLang(String lovName, String lang);

	List<T> findAllByLang(String lang);

	List<T> findAllByLangAndIsActiveTrue(String lang);

	List<T> findAllByLovId(Long lovId);

	List<T> findAllByLovIdAndIsActiveTrue(Long lovId);

	List<T> findAllByLovNameAndIsActiveTrue(String lovName);

}