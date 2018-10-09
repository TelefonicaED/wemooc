package com.ted.lms.internal.exportimport.staged.model.repository;

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataException;
import com.liferay.exportimport.kernel.lar.StagedModelModifiedDateComparator;
import com.liferay.exportimport.staged.model.repository.StagedModelRepository;
import com.liferay.exportimport.staged.model.repository.StagedModelRepositoryHelper;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.trash.TrashHandler;
import com.liferay.portal.kernel.trash.TrashHandlerRegistryUtil;
import com.ted.lms.model.Module;
import com.ted.lms.service.ModuleLocalService;

import java.util.List;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true, 
	property = "model.class.name=com.ted.lms.model.Module",
	service = StagedModelRepository.class
)
public class ModuleStagedModelRepository
	implements StagedModelRepository<Module> {

	@Override
	public Module addStagedModel(
			PortletDataContext portletDataContext,
			Module module)
		throws PortalException {

		long userId = portletDataContext.getUserId(
			module.getUserUuid());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			module);

		if (portletDataContext.isDataStrategyMirror()) {
			serviceContext.setUuid(module.getUuid());
		}

		Module newModule = moduleLocalService.addModule(userId, module.getTitleMap(),module.getDescriptionMap(), module.getStartDate(), module.getEndDate(), 
				module.getAllowedTime(), null, module.getModuleEvalId(), module.getModuleExtraData(), serviceContext);
		newModule = moduleLocalService.updateOrder(newModule, module.getOrder());	
		
		return newModule;
	}

	@Override
	public void deleteStagedModel(Module module)
		throws PortalException {

		moduleLocalService.deleteModule(module);
	}

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException {

		Module module = fetchStagedModelByUuidAndGroupId(
			uuid, groupId);

		if (module != null) {
			deleteStagedModel(module);
		}
	}

	@Override
	public void deleteStagedModels(PortletDataContext portletDataContext)
		throws PortalException {

		moduleLocalService.deleteModules(
			portletDataContext.getScopeGroupId());
	}

	@Override
	public Module fetchMissingReference(String uuid, long groupId) {
		return stagedModelRepositoryHelper.fetchMissingReference(
			uuid, groupId, this);
	}

	@Override
	public Module fetchStagedModelByUuidAndGroupId(
		String uuid, long groupId) {

		return moduleLocalService.fetchModuleByUuidAndGroupId(
			uuid, groupId);
	}

	@Override
	public List<Module> fetchStagedModelsByUuidAndCompanyId(
		String uuid, long companyId) {

		return moduleLocalService.
			getModulesByUuidAndCompanyId(
				uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new StagedModelModifiedDateComparator<>());
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext) {

		return moduleLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public Module getStagedModel(long entryId) throws PortalException {
		return moduleLocalService.getModule(entryId);
	}

	@Override
	public void restoreStagedModel(
			PortletDataContext portletDataContext,
			Module module)
		throws PortletDataException {

		long userId = portletDataContext.getUserId(
			module.getUserUuid());

		Module existingModule =
			fetchStagedModelByUuidAndGroupId(
				module.getUuid(), portletDataContext.getScopeGroupId());

		if ((existingModule == null) ||
			!stagedModelRepositoryHelper.isStagedModelInTrash(
				existingModule)) {

			return;
		}

		TrashHandler trashHandler = TrashHandlerRegistryUtil.getTrashHandler(
			Module.class.getName());

		try {
			if (trashHandler.isRestorable(
					existingModule.getModuleId())) {

				trashHandler.restoreTrashEntry(
					userId, existingModule.getModuleId());
			}
		}
		catch (PortalException pe) {
			throw new PortletDataException(pe);
		}
	}

	@Override
	public Module saveStagedModel(Module module) {
		return moduleLocalService.updateModule(module);
	}

	@Override
	public Module updateStagedModel(
			PortletDataContext portletDataContext,
			Module module)
		throws PortalException {

		long userId = portletDataContext.getUserId(
			module.getUserUuid());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			module);

		Module updateModule = moduleLocalService.updateModule(userId, module.getModuleId(), module.getTitleMap(),module.getDescriptionMap(), 
				module.getStartDate(), module.getEndDate(), module.getAllowedTime(), null, module.getModuleEvalId(), module.getModuleExtraData(), 
				serviceContext);
		
		return moduleLocalService.updateOrder(updateModule, module.getOrder());
	}

	@Reference
	private ModuleLocalService moduleLocalService;

	@Reference
	private StagedModelRepositoryHelper stagedModelRepositoryHelper;
}

