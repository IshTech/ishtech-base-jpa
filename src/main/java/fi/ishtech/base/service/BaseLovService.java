package fi.ishtech.base.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;

import fi.ishtech.base.entity.BaseLovEntity;
import fi.ishtech.base.mapper.BaseLovMapper;
import fi.ishtech.base.repo.BaseLovRepo;
import fi.ishtech.base.vo.BaseLovEntityVo;

/**
 * Base interface for operations on lov entity classes
 *
 * @param <T> extends {@link BaseLovEntity}
 * @param <V> extends {@link BaseLovEntityVo}
 *
 * @author Muneer Ahmed Syed
 */
public interface BaseLovService<T extends BaseLovEntity, V extends BaseLovEntityVo> extends BaseStandardService<T, V> {

	@Override
	BaseLovRepo<T> getRepo();

	@Override
	BaseLovMapper getMapper();

	/**
	 * Finds by name
	 *
	 * @param name
	 * @return T
	 */
	public default Optional<T> findOneByName(String name) {
		return getRepo().findOneByName(name);
	}

	/**
	 * Finds by name and if not present returns null
	 *
	 * @param name
	 * @return T
	 */
	public default T findOneByNameOrElseNull(String name) {
		return this.findOneByName(name).orElse(null);
	}

	/**
	 * Finds by name and if not present throws {@link NoSuchElementException}.
	 *
	 * @param name
	 * @return T
	 */
	public default T findOneByNameOrElseThrow(String name) {
		return this.findOneByName(name).orElseThrow(() -> new NoSuchElementException("Not found for lov:" + name));
	}

	/**
	 * Finds by name and if not present throws exception of type {@link RuntimeException}.
	 *
	 * @param name
	 * @return T
	 */
	public default T findOneByNameOrElseThrow(String name, Supplier<? extends RuntimeException> exceptionSupplier) {
		return this.findOneByName(name).orElseThrow(exceptionSupplier);
	}

	/**
	 * Finds by name and maps to Vo, if not present throws {@link NoSuchElementException}
	 *
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public default V findOneByNameAndMapToVo(String name) {
		return (V) getMapper().toBaseLovBriefVo(this.findOneByNameOrElseThrow(name));
	}

	/**
	 *
	 * @param name
	 * @return {@link Long} id
	 */
	public default Long findIdByName(String name) {
		return getRepo().findIdByName(name);
	}

	public default List<V> findAllActiveWithLangsAndMapToVo() {
		throw new UnsupportedOperationException();
	}

}