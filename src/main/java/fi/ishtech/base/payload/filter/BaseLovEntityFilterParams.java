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
@ToString(callSuper = true)
public abstract class BaseLovEntityFilterParams extends BaseLovEntityWithoutLovLangFilterParams {

	private static final long serialVersionUID = -3507526864128524052L;

	protected BaseLovLangEntityWithoutLovFilterParams lovLang;

}