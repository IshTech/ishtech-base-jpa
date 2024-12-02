package fi.ishtech.base.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import fi.ishtech.base.entity.BaseMasterEntity;
import fi.ishtech.base.vo.BaseMasterEntityVo;

/**
 * Base mapper for entities to/from vo for master data columns
 *
 * @author Muneer Ahmed Syed
 */
@Mapper(componentModel = "spring")
public interface BaseMasterMapper extends BaseStandardMapper {

	/**
	 * Maps base fields.
	 *
	 * @param entity of type {@link BaseMasterEntity}
	 * @return Vo of type {@link BaseMasterEntityVo}
	 */
	@BeanMapping(ignoreByDefault = true)
	@InheritConfiguration(name = "toBaseStandardVo")
	@Mapping(source = "name", target = "name")
	@Mapping(source = "label", target = "label")
	@Mapping(source = "lang", target = "lang")
	BaseMasterEntityVo toBaseMasterVo(BaseMasterEntity entity);

	/**
	 * Maps non relation fields.
	 *
	 * @param entity of type {@link BaseMasterEntity}
	 * @return Vo of type {@link BaseMasterEntityVo}
	 */
	@SuppressWarnings("unchecked")
	default <E extends BaseMasterEntity, V extends BaseMasterEntityVo> V toBriefVo(E entity) {
		return (V) toBaseMasterVo(entity);
	}

}