package fi.ishtech.base.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import fi.ishtech.core.i18n.enums.LangEnum;

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
public class BaseMasterEntity extends BaseStandardEntity {

	private static final long serialVersionUID = 7077999027203483536L;

	@Column(name = "name", nullable = false, insertable = true, updatable = false)
	protected String name;

	@Column(name = "label", nullable = false, insertable = true, updatable = false)
	protected String label;

	@Column(name = "lang", length = 2, nullable = false, insertable = true, updatable = false)
	protected String lang;

	public LangEnum getLangEnum() {
		return LangEnum.fromNameIgnoreCase(this.lang);
	}

}