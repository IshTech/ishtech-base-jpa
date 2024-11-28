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
@ToString(callSuper = false)
public abstract class BaseEntityFilterParams extends BaseFilterParams {

	private static final long serialVersionUID = -8569707593782893584L;

}