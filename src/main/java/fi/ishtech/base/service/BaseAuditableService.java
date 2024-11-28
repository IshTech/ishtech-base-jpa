package fi.ishtech.base.service;

import fi.ishtech.base.entity.BaseAuditableEntity;
import fi.ishtech.base.repo.BaseAuditableRepo;
import fi.ishtech.base.vo.BaseAuditableEntityVo;

/**
 *
 * @author Muneer Ahmed Syed
 */
public interface BaseAuditableService<T extends BaseAuditableEntity, V extends BaseAuditableEntityVo, ID>
		extends BaseEntityService<T, V, ID> {

	@Override
	BaseAuditableRepo<T, ID> getRepo();

}