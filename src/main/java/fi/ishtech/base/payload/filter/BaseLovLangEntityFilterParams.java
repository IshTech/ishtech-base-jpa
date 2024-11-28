package fi.ishtech.base.payload.filter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Base class for filter parameters for BaseLovLangEntity in request
 *
 * @author Muneer Ahmed Syed
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class BaseLovLangEntityFilterParams extends BaseLovLangEntityWithoutLovFilterParams {

	private static final long serialVersionUID = 6820041813253319711L;

	protected BaseLovEntityWithoutLovLangFilterParams lov;

}