package fi.ishtech.base.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.NoRepositoryBean;

import fi.ishtech.base.entity.BaseLovEntity;
import fi.ishtech.base.entity.BaseLovLangEntity;

/**
 *
 * @author Muneer Ahmed Syed
 */
@NoRepositoryBean
public interface BaseLovLangRepo<T extends BaseLovLangEntity<? extends BaseLovEntity>> extends BaseStandardRepo<T> {

	public Optional<T> findOneByLovIdAndLang(Long lovId, String lang);

	public Optional<T> findOneByLovNameAndLang(String lovName, String lang);

	public List<T> findAllByLang(String lang);

	public List<T> findAllByLangAndIsActiveTrue(String lang);

	public List<T> findAllByLovId(Long lovId);

	public List<T> findAllByLovIdAndIsActiveTrue(Long lovId);

	public List<T> findAllByLovNameAndIsActiveTrue(String lovName);

}