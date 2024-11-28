package fi.ishtech.base.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *
 * @author Muneer Ahmed Syed
 */
@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class BaseStandardNoIdEntity extends BaseEntity {

	private static final long serialVersionUID = -174279538819426199L;

	@Column(name = "description", nullable = true, insertable = true, updatable = true)
	protected String description;

	@Column(name = "is_active", nullable = true, insertable = true, updatable = true)
	protected boolean isActive;

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isActive() {
		return this.isActive;
	}

	/**
	 * To support noIsPrefix in dependent classes.
	 *
	 * @see #setActive(boolean)
	 *
	 * @param isActive
	 */
	public void setIsActive(boolean isActive) {
		this.setActive(isActive);
	}

	/**
	 * To support noIsPrefix in dependent classes.
	 *
	 * @see #isActive()
	 *
	 * @param isActive
	 */
	public boolean getIsActive() {
		return this.isActive();
	}

}