package fi.ishtech.base.vo;


import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Base class for value objects (vo) of entities with auto generated id
 *
 * @author Muneer Ahmed Syed
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BaseStandardEntityVo extends BaseStandardNoIdEntityVo {

	private static final long serialVersionUID = 9217741044047249388L;

	@JsonProperty
	@Positive
	private Long id;

}