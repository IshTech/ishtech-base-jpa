package fi.ishtech.base.jpa.entity;

import java.io.Serial;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import fi.ishtech.base.entity.BaseStandardEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Test entity that does NOT declare {@code @DynamicInsert} itself, relying on it being inherited from
 * {@link BaseStandardEntity}. Mirrors springboot-books-app's {@code Book}.
 *
 * @author Muneer Ahmed Syed
 */
@Entity
@Table(name = "t_inherited_dynamic_insert")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InheritedDynamicInsertEntity extends BaseStandardEntity {

	@Serial
	private static final long serialVersionUID = 1L;

}
