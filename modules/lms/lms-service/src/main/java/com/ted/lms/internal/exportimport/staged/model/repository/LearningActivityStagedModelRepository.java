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
import com.ted.lms.model.LearningActivity;
import com.ted.lms.service.LearningActivityLocalService;

import java.util.List;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true, 
	property = "model.class.name=com.ted.lms.model.LearningActivity",
	service = StagedModelRepository.class
)
public class LearningActivityStagedModelRepository
	implements StagedModelRepository<LearningActivity> {

	@Override
	public LearningActivity addStagedModel(
			PortletDataContext portletDataContext,
			LearningActivity learningActivity)
		throws PortalException {

		long userId = portletDataContext.getUserId(
				learningActivity.getUserUuid());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
				learningActivity);

		if (portletDataContext.isDataStrategyMirror()) {
			serviceContext.setUuid(learningActivity.getUuid());
		}
		
   	 	serviceContext.setUserId(userId);
   	 	serviceContext.setScopeGroupId(portletDataContext.getScopeGroupId());
		
		LearningActivity addedLearningActivity = learningActivityLocalService.addLearningActivity(userId, learningActivity.getGroupId(), learningActivity.getModuleId(), 
				learningActivity.getTitleMap(), learningActivity.getDescriptionMap(), learningActivity.getTypeId(), learningActivity.getStartDate(), 
				learningActivity.getEndDate(), learningActivity.getTries(), learningActivity.getPassPuntuation(), learningActivity.getPriority(), 
				learningActivity.getExtraContent(), learningActivity.getFeedbackCorrectMap(), learningActivity.getFeedbackNoCorrectMap(), 
				learningActivity.getRequired(), learningActivity.getCommentsActivated(), null, serviceContext);

		//TODO Añadir los attachments
		return addedLearningActivity;
	}

	@Override
	public void deleteStagedModel(LearningActivity learningActivity)
		throws PortalException {

		learningActivityLocalService.deleteLearningActivity(learningActivity);
	}

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException {

		LearningActivity learningActivity = fetchStagedModelByUuidAndGroupId(
			uuid, groupId);

		if (learningActivity != null) {
			deleteStagedModel(learningActivity);
		}
	}

	@Override
	public void deleteStagedModels(PortletDataContext portletDataContext)
		throws PortalException {

		learningActivityLocalService.deleteLearningActivities(
			portletDataContext.getScopeGroupId());
	}

	@Override
	public LearningActivity fetchMissingReference(String uuid, long groupId) {
		return stagedModelRepositoryHelper.fetchMissingReference(
			uuid, groupId, this);
	}

	@Override
	public LearningActivity fetchStagedModelByUuidAndGroupId(
		String uuid, long groupId) {

		return learningActivityLocalService.fetchLearningActivityByUuidAndGroupId(
			uuid, groupId);
	}

	@Override
	public List<LearningActivity> fetchStagedModelsByUuidAndCompanyId(
		String uuid, long companyId) {

		return learningActivityLocalService.
			getLearningActivitiesByUuidAndCompanyId(
				uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new StagedModelModifiedDateComparator<>());
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext) {

		return learningActivityLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public LearningActivity getStagedModel(long entryId) throws PortalException {
		return learningActivityLocalService.getLearningActivity(entryId);
	}

	@Override
	public void restoreStagedModel(
			PortletDataContext portletDataContext,
			LearningActivity learningActivity)
		throws PortletDataException {

		long userId = portletDataContext.getUserId(
			learningActivity.getUserUuid());

		LearningActivity existingLearningActivity =
			fetchStagedModelByUuidAndGroupId(
				learningActivity.getUuid(), portletDataContext.getScopeGroupId());

		if ((existingLearningActivity == null) ||
			!stagedModelRepositoryHelper.isStagedModelInTrash(
				existingLearningActivity)) {

			return;
		}

		TrashHandler trashHandler = TrashHandlerRegistryUtil.getTrashHandler(
			LearningActivity.class.getName());

		try {
			if (trashHandler.isRestorable(
					existingLearningActivity.getActId())) {

				trashHandler.restoreTrashEntry(
					userId, existingLearningActivity.getActId());
			}
		}
		catch (PortalException pe) {
			throw new PortletDataException(pe);
		}
	}

	@Override
	public LearningActivity saveStagedModel(LearningActivity learningActivity) {
		return learningActivityLocalService.updateLearningActivity(learningActivity);
	}

	@Override
	public LearningActivity updateStagedModel(
			PortletDataContext portletDataContext,
			LearningActivity learningActivity)
		throws PortalException {

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			learningActivity);

		LearningActivity updatedLearningActivity = learningActivityLocalService.updateLearningActivity(serviceContext.getUserId(), learningActivity.getActId(), 
				learningActivity.getModuleId(), learningActivity.getTitleMap(), learningActivity.getDescriptionMap(), learningActivity.getTypeId(), 
				learningActivity.getStartDate(), learningActivity.getEndDate(), learningActivity.getTries(), learningActivity.getPassPuntuation(), 
				learningActivity.getPriority(), learningActivity.getExtraContent(), learningActivity.getFeedbackCorrectMap(), 
				learningActivity.getFeedbackNoCorrectMap(), learningActivity.getRequired(), learningActivity.getCommentsActivated(), null, null, serviceContext);
		
		//TODO añadir actualización de attachments
		
		return updatedLearningActivity;
	}

	@Reference
	private LearningActivityLocalService learningActivityLocalService;

	@Reference
	private StagedModelRepositoryHelper stagedModelRepositoryHelper;
}

