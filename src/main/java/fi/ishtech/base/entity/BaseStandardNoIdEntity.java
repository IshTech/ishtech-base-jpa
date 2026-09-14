package fi.ishtech.base.entity;

import java.io.Serial;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import org.hibernate.annotations.ColumnDefault;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Base class for entities having standard columns
 *
 * @author Muneer Ahmed Syed
 */
@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class BaseStandardNoIdEntity extends BaseEntity {

	@Serial
	private static final long serialVersionUID = -174279538819426199L;

	@Column(name = "description", nullable = true, insertable = true, updatable = true)
	protected String description;

	/**
	 * Left {@code null} on a newly constructed entity so that {@code @DynamicInsert} omits the column from the INSERT
	 * and the {@code DEFAULT TRUE} of the database column applies. An explicitly set value is always written.
	 */
	@Column(name = "is_active", nullable = true, insertable = true, updatable = true)
	@ColumnDefault("true")
	protected Boolean isActive;

	/**
	 * Setter for isActive.
	 *
	 * @param isActive
	 *
	 * @see #setIsActive(Boolean)
	 */
	public void setActive(Boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * Getter for isActive.
	 * <p>
	 * TODO: isActive() is removed, so need to test its impact and fix.
	 *
	 * @see #getIsActive()
	 *
	 * @return {@link Boolean}, {@code null} when not yet defaulted by the database
	 */
	public Boolean getActive() {
		return this.isActive;
	}

	/**
	 * To support noIsPrefix in dependent classes.
	 *
	 * @param isActive
	 *
	 * @see #setActive(Boolean)
	 */
	public void setIsActive(Boolean isActive) {
		this.setActive(isActive);
	}

	/**
	 * To support noIsPrefix in dependent classes.
	 *
	 * @see #getActive()
	 *
	 * @return {@link Boolean}, {@code null} when not yet defaulted by the database
	 */
	public Boolean getIsActive() {
		return this.getActive();
	}

}