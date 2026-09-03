package fi.ishtech.base.jpa.entity;

import java.io.Serial;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import fi.ishtech.base.entity.BaseStandardEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Test entity that declares {@code @DynamicInsert} itself. Mirrors springboot-oms entities and jwtauth's
 * {@code UserProfile}.
 *
 * @author Muneer Ahmed Syed
 */
@Entity
@Table(name = "t_declared_dynamic_insert")
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DeclaredDynamicInsertEntity extends BaseStandardEntity {

	@Serial
	private static final long serialVersionUID = 1L;

}
