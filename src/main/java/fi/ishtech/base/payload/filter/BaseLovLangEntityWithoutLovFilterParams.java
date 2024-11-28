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
public abstract class BaseLovLangEntityWithoutLovFilterParams extends BaseStandardEntityFilterParams {

	private static final long serialVersionUID = 7610668123991731277L;

	protected Long lovId;

	protected String label;

	protected String lang;

	protected String[] langArr;

}