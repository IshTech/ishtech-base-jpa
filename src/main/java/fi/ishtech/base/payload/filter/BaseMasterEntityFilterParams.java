package fi.ishtech.base.payload.filter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Base class for filter parameters for BaseMasterEntity in request
 *
 * @author Muneer Ahmed Syed
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class BaseMasterEntityFilterParams extends BaseStandardEntityFilterParams {

	private static final long serialVersionUID = 3956375276712347491L;

	protected String name;

	protected String label;

	protected String lang;

	protected String[] langArr;

}