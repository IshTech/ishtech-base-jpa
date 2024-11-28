package fi.ishtech.base.vo;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Base class for value objects (vo) of entities having standard columns
 *
 * @author Muneer Ahmed Syed
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BaseStandardNoIdEntityVo extends BaseAuditableEntityVo {

	private static final long serialVersionUID = -7333245854918653037L;

	protected boolean isActive;

	@JsonProperty
	protected String description;

	/**
	 * Setter for isActive.
	 *
	 * @param isActive
	 *
	 * @see #setIsActive(boolean)
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * Getter for isActive.
	 *
	 * @see #getIsActive()
	 *
	 * @return boolean
	 */
	@JsonIgnore
	public boolean isActive() {
		return this.isActive;
	}

	/**
	 * To support noIsPrefix in dependent classes.
	 *
	 * @param isActive
	 *
	 * @see #setActive(boolean)
	 */
	public void setIsActive(boolean isActive) {
		this.setActive(isActive);
	}

	/**
	 * To support noIsPrefix in dependent classes.
	 *
	 * @see #isActive()
	 *
	 * @return boolean
	 */
	@JsonGetter
	public boolean getIsActive() {
		return this.isActive();
	}

}