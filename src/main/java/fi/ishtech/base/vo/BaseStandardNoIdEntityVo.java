package fi.ishtech.base.vo;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
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

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@JsonIgnore
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
	@JsonGetter
	public boolean getIsActive() {
		return this.isActive();
	}

}