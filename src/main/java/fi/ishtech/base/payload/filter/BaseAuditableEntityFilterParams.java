package fi.ishtech.base.payload.filter;

import java.time.LocalDateTime;

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
public abstract class BaseAuditableEntityFilterParams extends BaseEntityFilterParams {

	private static final long serialVersionUID = 8218319420897955842L;

	private Long createdBy;
	private Long updatedBy;

	private LocalDateTime createdAtMin;
	private LocalDateTime createdAtMax;

	private LocalDateTime updatedAtMin;
	private LocalDateTime updatedAtMax;

}