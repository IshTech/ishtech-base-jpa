package fi.ishtech.base.service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;

import fi.ishtech.base.entity.BaseStandardEntity;
import fi.ishtech.base.mapper.BaseStandardMapper;
import fi.ishtech.base.repo.BaseStandardRepo;
import fi.ishtech.base.vo.BaseStandardEntityVo;

/**
 * Base interface for operations on standard (with auto id) entity classes
 *
 * @param <T> extends {@link BaseStandardEntity}
 * @param <V> extends {@link BaseStandardEntityVo}
 *
 * @author Muneer Ahmed Syed
 */
public interface BaseStandardService<T extends BaseStandardEntity, V extends BaseStandardEntityVo>
		extends BaseStandardNoIdService<T, V, Long> {

	@Override
	BaseStandardRepo<T> getRepo();

	@Override
	BaseStandardMapper getMapper();

	public default boolean existsById(Long id) {
		return getRepo().existsById(id);
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	public default Optional<T> findOneById(Long id) {
		return getRepo().findById(id);
	}

	/**
	 *
	 * @param id
	 * @return T
	 */
	public default T findOneByIdOrElseNull(Long id) {
		return this.findOneById(id).orElse(null);
	}

	/**
	 * Finds by id and if not present, throws {@link NoSuchElementException}.
	 *
	 * @param id
	 * @return T
	 */
	public default T findOneByIdOrElseThrow(Long id) {
		return this.findOneById(id).orElseThrow();
	}

	/**
	 * Finds by id and if not present, throws {@link RuntimeException}.
	 *
	 * @param id
	 * @return T
	 */
	public default T findOneByIdOrElseThrow(Long id, Supplier<? extends RuntimeException> exceptionSupplier) {
		return this.findOneById(id).orElseThrow(exceptionSupplier);
	}

	@SuppressWarnings("unchecked")
	public default V findOneByIdAndMapToVoOrElseThrow(Long id) {
		return (V) getMapper().toBriefVo(this.findOneByIdOrElseThrow(id));
	}

}