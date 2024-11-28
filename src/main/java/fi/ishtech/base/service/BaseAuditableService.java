package fi.ishtech.base.service;

import fi.ishtech.base.entity.BaseAuditableEntity;
import fi.ishtech.base.repo.BaseAuditableRepo;
import fi.ishtech.base.vo.BaseAuditableEntityVo;

/**
 * Base interface for operations on auditable entity classes
 *
 * @param <T>  extends {@link BaseAuditableEntity}
 * @param <V>  extends {@link BaseAuditableEntityVo}
 * @param <ID> id
 *
 * @author Muneer Ahmed Syed
 */
public interface BaseAuditableService<T extends BaseAuditableEntity, V extends BaseAuditableEntityVo, ID>
		extends BaseEntityService<T, V, ID> {

	@Override
	BaseAuditableRepo<T, ID> getRepo();

}