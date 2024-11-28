package fi.ishtech.base.payload.filter;

import jakarta.validation.constraints.Positive;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Base class for filter parameters for BaseStandardEntity in request
 *
 * @author Muneer Ahmed Syed
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class BaseStandardEntityFilterParams extends BaseStandardNoIdEntityFilterParams {

	private static final long serialVersionUID = 6966264259842692602L;

	@Positive
	protected Long id;

	protected boolean idLikeSearch = false;

}