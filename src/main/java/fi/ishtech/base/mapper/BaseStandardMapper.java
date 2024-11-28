package fi.ishtech.base.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import fi.ishtech.base.entity.BaseStandardEntity;
import fi.ishtech.base.vo.BaseStandardEntityVo;

/**
 * Base mapper for entities to/from vo with standard columns and id
 *
 * @author Muneer Ahmed Syed
 */
@Mapper(componentModel = "spring")
public interface BaseStandardMapper extends BaseStandardNoIdMapper {

	/**
	 * Maps base fields.
	 *
	 * @param entity of type {@link BaseStandardEntity}
	 * @return Vo of type {@link BaseStandardEntityVo}
	 */
	@BeanMapping(ignoreByDefault = true)
	@InheritConfiguration(name = "toBaseStandardNoIdVo")
	@Mapping(source = "id", target = "id")
	BaseStandardEntityVo toBaseStandardVo(BaseStandardEntity entity);

	/**
	 * Maps non relation fields.
	 *
	 * @param entity of type {@link BaseStandardEntity}
	 * @return Vo of type {@link BaseStandardEntityVo}
	 */
	@BeanMapping(ignoreByDefault = true)
	@InheritConfiguration(name = "toBaseStandardVo")
	BaseStandardEntityVo toBriefVo(BaseStandardEntity entity);

}