package com.ted.lms.internal.exportimport.data.handler;

import com.liferay.exportimport.data.handler.base.BaseStagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.ExportImportPathUtil;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataException;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelModifiedDateComparator;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.xml.Element;
import com.ted.lms.internal.exportimport.creation.strategy.LearningActivityCreationStrategy;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityType;
import com.ted.lms.model.LearningActivityTypeFactory;
import com.ted.lms.model.Module;
import com.ted.lms.registry.LearningActivityTypeFactoryRegistryUtil;
import com.ted.lms.service.LearningActivityLocalService;
import com.ted.lms.service.ModuleLocalService;
import com.ted.prerequisite.model.Prerequisite;
import com.ted.prerequisite.service.PrerequisiteRelationLocalServiceUtil;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true, 
	service = StagedModelDataHandler.class
)
public class LearningActivityStagedModelDataHandler extends BaseStagedModelDataHandler<LearningActivity> {
	
	private static final Log log = LogFactoryUtil.getLog(LearningActivityStagedModelDataHandler.class);

	public static final String[] CLASS_NAMES = {LearningActivity.class.getName()};

	@Override
	public String[] getClassNames() {
	    return CLASS_NAMES;
	}
	
	@Override
	public void deleteStagedModel(LearningActivity activity)
		throws PortalException {

		activityLocalService.deleteLearningActivity(activity);
	}

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException {

		LearningActivity activity = fetchStagedModelByUuidAndGroupId(
			uuid, groupId);

		if (activity != null) {
			deleteStagedModel(activity);
		}
	}

	@Override
	public LearningActivity fetchStagedModelByUuidAndGroupId(
		String uuid, long groupId) {

		return activityLocalService.fetchLearningActivityByUuidAndGroupId(
			uuid, groupId);
	}

	@Override
	public List<LearningActivity> fetchStagedModelsByUuidAndCompanyId(
		String uuid, long companyId) {

		return activityLocalService.getLearningActivitiesByUuidAndCompanyId(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new StagedModelModifiedDateComparator<LearningActivity>());
	}
	
	/**
	 * Añadimos el objeto al portletDataContext
	 */
	@Override
	protected void doExportStagedModel(
	        PortletDataContext portletDataContext, LearningActivity activity)
	    throws Exception {
		
		if(activity.getModuleId() > 0) {
			Module module = moduleLocalService.fetchModule(activity.getModuleId());
			
			StagedModelDataHandlerUtil.exportReferenceStagedModel(portletDataContext, activity, module, PortletDataContext.REFERENCE_TYPE_PARENT);
		}
		
		Element activityElement = portletDataContext.getExportDataElement(activity);

		String activityPath = ExportImportPathUtil.getModelPath(activity);
		
		System.out.println("activityPath: " + activityPath);
		
		activityElement.addAttribute("path", activityPath);
		
		portletDataContext.addReferenceElement(
				activity, activityElement, activity,
				PortletDataContext.REFERENCE_TYPE_DEPENDENCY, false);
		
		//Exportamos el extracontent
		LearningActivityTypeFactory learningActivityTypeFactory = LearningActivityTypeFactoryRegistryUtil.getLearningActivityTypeFactoryByType(activity.getTypeId());
		LearningActivityType learningActivityType = learningActivityTypeFactory.getLearningActivityType(activity);
		
		learningActivityType.doExportStagedModel(portletDataContext, activityElement);
		
		//Exportamos los prerequisitos
		List<Prerequisite> listPrerequisites = PrerequisiteRelationLocalServiceUtil.getPrerequisites(PortalUtil.getClassNameId(LearningActivity.class), activity.getActId());
		listPrerequisites.forEach((prerequisite) -> prerequisite.doExportStagedModel(portletDataContext, activityElement));

		portletDataContext.addZipEntry(activityPath, activity);
	}
	
	@Override
	protected void doImportStagedModel(
	        PortletDataContext portletDataContext, LearningActivity activity)
	    throws Exception {
		
		long userId = portletDataContext.getUserId(activity.getUserUuid());
		
		long authorId = learningActivityCreationStrategy.getAuthorUserId(
				portletDataContext, activity);

		if (authorId != LearningActivityCreationStrategy.USE_DEFAULT_USER_ID_STRATEGY) {
			userId = authorId;
		}
		
		String newDescription = learningActivityCreationStrategy.getTransformedDescription(portletDataContext, activity);

		if (newDescription != LearningActivityCreationStrategy.LEARNING_ACTIVITY_DESCRIPTION_UNCHANGED) {
			activity.setDescription(newDescription);
		}

		Map<Long, Long> moduleIds =
				(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(Module.class);
		
		long moduleId = MapUtil.getLong(moduleIds, activity.getModuleId(), activity.getModuleId());	
		
    	//Element activityElement = portletDataContext.getImportDataStagedModelElement(activity);
		
		boolean addGroupPermissions =
				learningActivityCreationStrategy.addGroupPermissions(
					portletDataContext, activity);
		
		boolean addGuestPermissions =
				learningActivityCreationStrategy.addGuestPermissions(
					portletDataContext, activity);
		
		ServiceContext serviceContext =
		        portletDataContext.createServiceContext(activity);
		
		serviceContext.setAddGroupPermissions(addGroupPermissions);
		serviceContext.setAddGuestPermissions(addGuestPermissions);	

	    LearningActivity importedLearningActivity = null;
	    
	    Element activityElement = portletDataContext.getImportDataStagedModelElement(activity);

	    if (portletDataContext.isDataStrategyMirror()) {
	    	
	    	serviceContext.setUuid(activity.getUuid());

	        LearningActivity existingLearningActivity =
	            activityLocalService. fetchLearningActivityByUuidAndGroupId(
	                activity.getUuid(), portletDataContext.getScopeGroupId());

	        if (existingLearningActivity == null) {

	            serviceContext.setUuid(activity.getUuid());
	            importedLearningActivity = activityLocalService.addLearningActivity(portletDataContext.getScopeGroupId(), userId, moduleId, 
	            		activity.getTitleMap(), activity.getDescriptionMap(), activity.getTypeId(), activity.getStartDate(), activity.getEndDate(), 
	            		activity.getTries(), activity.getPassPuntuation(), activity.getPriority(), activity.getExtraContent(), activity.getFeedbackCorrect(), 
	            		activity.getFeedbackNoCorrect(), activity.getRequired(), activity.getCommentsActivated(), serviceContext);
	        }
	        else {
	            importedLearningActivity = activityLocalService.updateLearningActivity(activity.getActId(), moduleId, 
	            		activity.getTitleMap(), activity.getDescriptionMap(), activity.getTypeId(), activity.getStartDate(), activity.getEndDate(), 
	            		activity.getTries(), activity.getPassPuntuation(), activity.getPriority(), activity.getExtraContent(), activity.getFeedbackCorrect(), 
	            		activity.getFeedbackNoCorrect(), activity.getRequired(), activity.getCommentsActivated(), serviceContext);
	        }
	    }
	    else {
	        importedLearningActivity = activityLocalService.addLearningActivity(portletDataContext.getScopeGroupId(), userId, moduleId, 
            		activity.getTitleMap(), activity.getDescriptionMap(), activity.getTypeId(), activity.getStartDate(), activity.getEndDate(), 
            		activity.getTries(), activity.getPassPuntuation(), activity.getPriority(), activity.getExtraContent(), activity.getFeedbackCorrect(), 
            		activity.getFeedbackNoCorrect(), activity.getRequired(), activity.getCommentsActivated(), serviceContext);
	    }
	    
	    LearningActivityTypeFactory learningActivityTypeFactory = LearningActivityTypeFactoryRegistryUtil.getLearningActivityTypeFactoryByType(activity.getTypeId());
		LearningActivityType learningActivityType = learningActivityTypeFactory.getLearningActivityType(activity);
		
		learningActivityType.doImportStagedModel(portletDataContext, activityElement);
	    
	    Map<Long, Long> activityIds = (Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(LearningActivity.class);

		activityIds.put(activity.getPrimaryKey(), importedLearningActivity.getPrimaryKey());
	    
	    Map<String, String> activityUuids = (Map<String, String>)portletDataContext.getNewPrimaryKeysMap(LearningActivity.class + ".uuid");
	    
	    activityUuids.put(activity.getUuid(), importedLearningActivity.getUuid());
	}
	
	@Override
	protected void doImportMissingReference(PortletDataContext portletDataContext, String uuid, long groupId,
			long activityId)
		throws PortletDataException {

		LearningActivity existingLearningActivity = fetchMissingReference(uuid, groupId);

		if (existingLearningActivity == null) {
			return;
		}

		Map<Long, Long> activityIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				LearningActivity.class);

		activityIds.put(activityId, existingLearningActivity.getActId());
	}
	
	@Override
	public String getDisplayName(LearningActivity activity) {
	    return activity.getTitleCurrentValue();
	}
	
	protected LearningActivityLocalService getLearningActivityLocalService() {
	    return this.activityLocalService;
	}

	@Reference(unbind = "-")
	protected void setLearningActivityLocalService(
			LearningActivityLocalService activityLocalService) {

	    this.activityLocalService = activityLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setModuleLocalService(
			ModuleLocalService moduleLocalService) {

	    this.moduleLocalService = moduleLocalService;
	}

	@Reference(unbind = "-")
	protected void setLearningActivityCreationStrategy(
			LearningActivityCreationStrategy learningActivityCreationStrategy) {

		this.learningActivityCreationStrategy = learningActivityCreationStrategy;
	}
	
	private LearningActivityCreationStrategy learningActivityCreationStrategy;

	private LearningActivityLocalService activityLocalService;
	private ModuleLocalService moduleLocalService;
}