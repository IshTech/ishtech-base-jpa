package fi.ishtech.base.payload.filter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *
 * @author Muneer Ahmed Syed
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class BaseLovEntityWithoutLovLangFilterParams extends BaseStandardEntityFilterParams {

	private static final long serialVersionUID = 3257510478983906709L;

	protected String name;

}