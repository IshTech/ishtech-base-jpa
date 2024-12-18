package fi.ishtech.base.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Base class for value objects (vo) of entities
 *
 * @author Muneer Ahmed Syed
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class BaseEntityVo implements BaseVo {

	private static final long serialVersionUID = -1227556520257089092L;

}