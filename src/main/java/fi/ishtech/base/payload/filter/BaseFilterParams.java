package fi.ishtech.base.payload.filter;

import fi.ishtech.base.vo.BaseVo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Base class for filter parameters in request
 * 
 * @author Muneer Ahmed Syed
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public abstract class BaseFilterParams implements BaseVo {

	private static final long serialVersionUID = 7672866470370773784L;

}