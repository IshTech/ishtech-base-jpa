package fi.ishtech.base.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import fi.ishtech.base.entity.BaseMasterEntity;
import fi.ishtech.base.vo.BaseMasterEntityVo;

/**
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
	@BeanMapping(ignoreByDefault = true)
	@InheritConfiguration(name = "toBaseMasterVo")
	BaseMasterEntityVo toBriefVo(BaseMasterEntity entity);

}