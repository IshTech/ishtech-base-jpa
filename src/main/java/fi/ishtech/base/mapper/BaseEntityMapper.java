package fi.ishtech.base.mapper;

import org.mapstruct.Mapper;

import fi.ishtech.base.entity.BaseEntity;
import fi.ishtech.base.vo.BaseEntityVo;

/**
 *
 * @author Muneer Ahmed Syed
 */
@Mapper
public interface BaseEntityMapper extends BaseMapper {

	default <E extends BaseEntity, V extends BaseEntityVo> V toBriefVo(E entity) {
		if (entity == null) {
			return null;
		}

		@SuppressWarnings("unchecked")
		V vo = (V) new BaseEntityVo();

		return vo;
	}

	default <E extends BaseEntity, V extends BaseEntityVo> V toSemiDetailVo(E entity) {
		return toBriefVo(entity);
	}

	default  <E extends BaseEntity, V extends BaseEntityVo> V toDetailVo(E entity) {
		return toSemiDetailVo(entity);
	}

}