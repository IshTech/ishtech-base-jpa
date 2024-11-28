package fi.ishtech.base.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import fi.ishtech.base.annotations.mapstruct.BriefMapping;
import fi.ishtech.base.entity.BaseLovEntity;
import fi.ishtech.base.vo.BaseLovEntityVo;

/**
 * Base mapper for entities to/from vo with lov columns
 *
 * @author Muneer Ahmed Syed
 */
@Mapper(componentModel = "spring")
public interface BaseLovMapper extends BaseStandardMapper {

	@BriefMapping
	@BeanMapping(ignoreByDefault = true)
	@InheritConfiguration(name = "toBaseStandardVo")
	@Mapping(source = "name", target = "name")
	@Mapping(source = "displayOrder", target = "displayOrder")
	BaseLovEntityVo toBaseLovBriefVo(BaseLovEntity entity);

	/**
	 * Maps non relation fields.
	 *
	 * @param entity of type {@link BaseLovEntity}
	 * @return Vo of type {@link BaseLovEntityVo}
	 */
	@BeanMapping(ignoreByDefault = true)
	@InheritConfiguration(name = "toBaseLovBriefVo")
	BaseLovEntityVo toBriefVo(BaseLovEntity entity);

}