package fi.ishtech.base.payload.filter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Base class for filter parameters for BaseStandardNoIdEntity in request
 *
 * @author Muneer Ahmed Syed
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class BaseStandardNoIdEntityFilterParams extends BaseAuditableEntityFilterParams {

	private static final long serialVersionUID = 5094236893124339566L;

	protected Boolean isActive;

	protected String description;

}