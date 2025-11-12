package fi.ishtech.base.mapper;

import org.mapstruct.Mapper;

import fi.ishtech.base.entity.BaseEntity;
import fi.ishtech.base.vo.BaseEntityVo;

/**
 * Base mapper for entities to/from vo
 *
 * @see BaseEntity
 * @see BaseEntityVo
 *
 * @author Muneer Ahmed Syed
 */
@Mapper
public interface BaseEntityMapper extends BaseMapper {

	/**
	 * Maps direct attributes from entity to vo
	 *
	 * @param entity E
	 * @return V
	 */
	default <E extends BaseEntity, V extends BaseEntityVo> V toBriefVo(E entity) {
		if (entity == null) {
			return null;
		}

		@SuppressWarnings("unchecked")
		V vo = (V) new BaseEntityVo();

		return vo;
	}

	/**
	 * Maps direct attributes and selective relational fields from entity to vo
	 *
	 * @param entity E
	 * @return V
	 */
	default <E extends BaseEntity, V extends BaseEntityVo> V toSemiDetailVo(E entity) {
		return toBriefVo(entity);
	}

	/**
	 * Maps direct attributes and relational fields from entity to vo
	 *
	 * @param entity E
	 * @return V
	 */
	default <E extends BaseEntity, V extends BaseEntityVo> V toDetailVo(E entity) {
		return toSemiDetailVo(entity);
	}

}