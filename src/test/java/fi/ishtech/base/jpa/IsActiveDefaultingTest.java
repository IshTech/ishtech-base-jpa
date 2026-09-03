package fi.ishtech.base.jpa;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import fi.ishtech.base.jpa.entity.DeclaredDynamicInsertEntity;
import fi.ishtech.base.jpa.entity.InheritedDynamicInsertEntity;

/**
 * Verifies that a newly persisted entity ends up active, and that an explicitly set {@code false} is respected.
 *
 * @author Muneer Ahmed Syed
 */
@SpringBootTest
@Transactional
@TestPropertySource(properties = { "spring.jpa.hibernate.ddl-auto=none", "spring.sql.init.mode=always",
		"spring.sql.init.schema-locations=classpath:schema-is-active-test.sql" })
class IsActiveDefaultingTest {

	@PersistenceContext
	private EntityManager em;

	@Test
	@DisplayName("entity declaring @DynamicInsert is active by default")
	void declaredDynamicInsertIsActiveByDefault() {
		DeclaredDynamicInsertEntity entity = new DeclaredDynamicInsertEntity();
		assertThat(entity.getIsActive()).as("freshly constructed entity").isNull();

		em.persist(entity);
		em.flush();
		Long id = entity.getId();
		em.clear();

		assertThat(em.find(DeclaredDynamicInsertEntity.class, id).getIsActive()).isTrue();
	}

	@Test
	@DisplayName("entity inheriting @DynamicInsert from the mapped superclass is active by default")
	void inheritedDynamicInsertIsActiveByDefault() {
		InheritedDynamicInsertEntity entity = new InheritedDynamicInsertEntity();
		assertThat(entity.getIsActive()).as("freshly constructed entity").isNull();

		em.persist(entity);
		em.flush();
		Long id = entity.getId();
		em.clear();

		assertThat(em.find(InheritedDynamicInsertEntity.class, id).getIsActive()).isTrue();
	}

	@Test
	@DisplayName("explicitly set false is respected on insert")
	void explicitFalseIsRespected() {
		DeclaredDynamicInsertEntity declared = new DeclaredDynamicInsertEntity();
		declared.setActive(false);
		em.persist(declared);

		InheritedDynamicInsertEntity inherited = new InheritedDynamicInsertEntity();
		inherited.setActive(false);
		em.persist(inherited);

		em.flush();
		Long declaredId = declared.getId();
		Long inheritedId = inherited.getId();
		em.clear();

		assertThat(em.find(DeclaredDynamicInsertEntity.class, declaredId).getIsActive()).isFalse();
		assertThat(em.find(InheritedDynamicInsertEntity.class, inheritedId).getIsActive()).isFalse();
	}

	@Test
	@DisplayName("explicitly set true is respected on insert")
	void explicitTrueIsRespected() {
		DeclaredDynamicInsertEntity entity = new DeclaredDynamicInsertEntity();
		entity.setActive(true);
		em.persist(entity);
		em.flush();
		Long id = entity.getId();
		em.clear();

		assertThat(em.find(DeclaredDynamicInsertEntity.class, id).getIsActive()).isTrue();
	}

	@Test
	@DisplayName("value stays null in memory until the entity is reloaded")
	void inMemoryValueIsStaleUntilReload() {
		DeclaredDynamicInsertEntity entity = new DeclaredDynamicInsertEntity();
		em.persist(entity);
		em.flush();

		assertThat(entity.getIsActive()).as("the database default is not read back into the managed instance").isNull();
	}

	@Test
	@DisplayName("an explicit false survives an update")
	void deactivationIsPersistedOnUpdate() {
		DeclaredDynamicInsertEntity entity = new DeclaredDynamicInsertEntity();
		em.persist(entity);
		em.flush();
		Long id = entity.getId();
		em.clear();

		DeclaredDynamicInsertEntity loaded = em.find(DeclaredDynamicInsertEntity.class, id);
		assertThat(loaded.getIsActive()).isTrue();
		loaded.setActive(false);
		em.flush();
		em.clear();

		assertThat(em.find(DeclaredDynamicInsertEntity.class, id).getIsActive()).isFalse();
	}

}
