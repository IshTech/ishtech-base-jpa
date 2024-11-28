package fi.ishtech.base.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Base class for value objects (vo) of entities of list of values
 *
 * @author Muneer Ahmed Syed
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BaseLovEntityVo extends BaseStandardEntityVo {

	private static final long serialVersionUID = -5957447460795887460L;

	protected String name;

	protected Long displayOrder;

}