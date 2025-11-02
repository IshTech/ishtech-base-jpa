package fi.ishtech.base.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import fi.ishtech.base.entity.BaseLovEntity;
import fi.ishtech.base.entity.BaseLovLangEntity;
import fi.ishtech.base.mapper.BaseLovLangMapper;
import fi.ishtech.base.repo.BaseLovLangRepo;
import fi.ishtech.base.vo.BaseLovEntityVo;
import fi.ishtech.base.vo.BaseLovLangEntityVo;
import fi.ishtech.core.i18n.enums.LangEnum;

/**
 * Base interface for operations on lov lang entity classes
 *
 * @param <T> extends {@link BaseLovLangEntity}
 * @param <V> extends {@link BaseLovLangEntityVo}
 *
 * @author Muneer Ahmed Syed
 */
public interface BaseLovLangService<T extends BaseLovLangEntity<? extends BaseLovEntity>, V extends BaseLovLangEntityVo<? extends BaseLovEntityVo>>
		extends BaseStandardService<T, V> {

	@Override
	BaseLovLangRepo<T> getRepo();

	@Override
	BaseLovLangMapper getMapper();

	/**
	 * Finds by name and lang
	 *
	 * @param lovName
	 * @param lang
	 * @return {@link Optional}&gt;T&lt;
	 */
	default Optional<T> findOneByLovNameAndLang(String lovName, LangEnum lang) {
		return getRepo().findOneByLovNameAndLang(lovName, lang.name());
	}

	/**
	 * Finds by lovId, lang
	 *
	 * @param lovId
	 * @param lang
	 * @return T
	 */
	default Optional<T> findOneByLovIdAndLang(Long lovId, LangEnum lang) {
		return getRepo().findOneByLovIdAndLang(lovId, lang.name());
	}

	/**
	 * Finds by lovId, lang and if not present returns null
	 *
	 * @param lovId
	 * @param lang
	 * @return T
	 */
	default T findOneByLovIdAndLangOrElseNull(Long lovId, LangEnum lang) {
		return this.findOneByLovIdAndLang(lovId, lang).orElse(null);
	}

	/**
	 * Finds by name, lang and if not present throws {@link NoSuchElementException}.
	 *
	 * @param lovName
	 * @param lang
	 * @return T
	 */
	default T findOneByLovNameAndLangOrElseNull(String lovName, LangEnum lang) {
		return this.findOneByLovNameAndLang(lovName, lang).orElse(null);
	}

	/**
	 * Finds by lovId, lang and if not present throws {@link NoSuchElementException}.
	 *
	 * @param lovId
	 * @param lang
	 * @return T
	 */
	default T findOneByLovIdAndLangOrElseThrow(Long lovId, LangEnum lang) {
		return this.findOneByLovIdAndLang(lovId, lang)
				.orElseThrow(() -> new NoSuchElementException("Not found for lov:" + lovId + ", lang:" + lang));
	}

	/**
	 * Finds by lovName, lang and if not present throws {@link NoSuchElementException}.
	 *
	 * @param lovName
	 * @param lang
	 * @return T
	 */
	default T findOneByLovNameAndLangOrElseThrow(String lovName, LangEnum lang) {
		return this.findOneByLovNameAndLang(lovName, lang)
				.orElseThrow(() -> new NoSuchElementException("Not found for lov:" + lovName + ", lang:" + lang));
	}

	/**
	 *
	 * @param lang
	 * @return {@link List}&gt;T&lt;
	 */
	default List<T> findAllByLang(LangEnum lang) {
		return getRepo().findAllByLang(lang.name());
	}

	/**
	 *
	 * @param lang
	 * @return {@link List}&gt;T&lt;
	 */
	default List<T> findAllActiveByLang(LangEnum lang) {
		return getRepo().findAllByLangAndIsActiveTrue(lang.name());
	}

	/**
	 * Finds all by lovId
	 *
	 * @param lovId
	 * @return {@link List}&gt;T&lt;
	 */
	default List<T> findAllByLovId(Long lovId) {
		return getRepo().findAllByLovId(lovId);
	}

	/**
	 * Finds all active by lovId
	 *
	 * @param lovId
	 * @return {@link List}&gt;T&lt;
	 */
	default List<T> findAllActiveByLovId(Long lovId) {
		return getRepo().findAllByLovIdAndIsActiveTrue(lovId);
	}

	/**
	 * Finds all active by lovName
	 *
	 * @param lovName
	 * @return {@link List}&gt;T&lt;
	 */
	default List<T> findAllActiveByLovName(String lovName) {
		return getRepo().findAllByLovNameAndIsActiveTrue(lovName);
	}

}