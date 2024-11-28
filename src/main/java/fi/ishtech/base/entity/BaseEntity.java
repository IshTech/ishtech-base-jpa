package fi.ishtech.base.entity;

import java.io.Serializable;

import jakarta.persistence.MappedSuperclass;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Base class for entities
 *
 * @author Muneer Ahmed Syed
 */
@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = -8407271875822469618L;

}