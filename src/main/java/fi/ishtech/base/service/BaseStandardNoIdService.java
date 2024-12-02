package fi.ishtech.base.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;

import fi.ishtech.base.entity.BaseStandardNoIdEntity;
import fi.ishtech.base.mapper.BaseStandardNoIdMapper;
import fi.ishtech.base.repo.BaseStandardNoIdRepo;
import fi.ishtech.base.vo.BaseStandardNoIdEntityVo;

/**
 * Base interface for operations on standard (with no auto id) entity classes
 *
 * @param <T>  extends {@link BaseStandardNoIdEntity}
 * @param <V>  extends {@link BaseStandardNoIdEntityVo}
 * @param <ID> id
 *
 * @author Muneer Ahmed Syed
 */
public interface BaseStandardNoIdService<T extends BaseStandardNoIdEntity, V extends BaseStandardNoIdEntityVo, ID>
		extends BaseEntityService<T, V, ID> {

	@Override
	BaseStandardNoIdRepo<T, ID> getRepo();

	@Override
	BaseStandardNoIdMapper getMapper();

	/**
	 *
	 * @return {@link List} of {@link BaseStandardNoIdEntity}
	 */
	public default List<T> findAllActive() {
		return getRepo().findAllByIsActiveTrue();
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	public default Optional<T> findOneById(ID id) {
		return getRepo().findById(id);
	}

	/**
	 *
	 * @param id
	 * @return T
	 */
	public default T findOneByIdOrElseNull(ID id) {
		return this.findOneById(id).orElse(null);
	}

	/**
	 * Finds by id and if not present, throws {@link NoSuchElementException}.
	 *
	 * @param id
	 * @return T
	 */
	public default T findOneByIdOrElseThrow(ID id) {
		return this.findOneById(id).orElseThrow();
	}

	/**
	 * Finds by id and if not present, throws {@link RuntimeException}.
	 *
	 * @param id
	 * @return T
	 */
	public default T findOneByIdOrElseThrow(ID id, Supplier<? extends RuntimeException> exceptionSupplier) {
		return this.findOneById(id).orElseThrow(exceptionSupplier);
	}

}