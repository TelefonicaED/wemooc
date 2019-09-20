package com.ted.lms.internal.exportimport.data.handler;

import com.liferay.document.library.kernel.exception.DuplicateFileEntryException;
import com.liferay.document.library.kernel.exception.NoSuchFileException;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.exportimport.content.processor.ExportImportContentProcessor;
import com.liferay.exportimport.data.handler.base.BaseStagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.ExportImportPathUtil;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataException;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelModifiedDateComparator;
import com.liferay.exportimport.kernel.xstream.XStreamAliasRegistryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepository;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.trash.TrashHandler;
import com.liferay.portal.kernel.trash.TrashHandlerRegistryUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portlet.documentlibrary.lar.FileEntryUtil;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.internal.exportimport.creation.strategy.LearningActivityCreationStrategy;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityType;
import com.ted.lms.model.LearningActivityTypeFactory;
import com.ted.lms.model.Module;
import com.ted.lms.model.impl.LearningActivityImpl;
import com.ted.lms.registry.LearningActivityTypeFactoryRegistryUtil;
import com.ted.lms.service.LearningActivityLocalService;
import com.ted.lms.service.ModuleLocalService;
import com.ted.lms.service.util.LearningActivityAttachmentsUtil;
import com.ted.prerequisite.model.Prerequisite;
import com.ted.prerequisite.service.PrerequisiteRelationLocalServiceUtil;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

@Component(
	immediate = true, 
	service = StagedModelDataHandler.class
)
public class LearningActivityStagedModelDataHandler extends BaseStagedModelDataHandler<LearningActivity> {
	
	private static final Log log = LogFactoryUtil.getLog(LearningActivityStagedModelDataHandler.class);

	public static final String[] CLASS_NAMES = {LearningActivity.class.getName()};

	@Activate
	protected void activate() {
		XStreamAliasRegistryUtil.register(LearningActivityImpl.class, "LearningActivity");
	}
	
	@Override
	public String[] getClassNames() {
	    return CLASS_NAMES;
	}
	
	@Override
	public void deleteStagedModel(LearningActivity activity)
		throws PortalException {
		log.debug("deleteStagedModel: " + activity.getActId());
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
		
		log.debug("activityPath: " + activityPath);
		
		activityElement.addAttribute("path", activityPath);
		
		String description = activityExportImportContentProcessor.replaceExportContentReferences(portletDataContext, activity, activity.getDescription(),
				true,false);

		activity.setDescription(description);
		activity.setExtraContentJSON(null);
		
		exportLearningActivityAttachments(portletDataContext, activityElement, activity);
		
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
		
		long groupId = portletDataContext.getScopeGroupId();
		
		log.debug("doImportStagedModel: " + userId + " - " + authorId + " - " + groupId);
		log.debug("antigua descripción: " + activity.getDescription());
		
		activity.setDescription(activityExportImportContentProcessor.replaceImportContentReferences(portletDataContext, activity, activity.getDescription()));
		
		String newDescription = learningActivityCreationStrategy.getTransformedDescription(portletDataContext, activity);

		if (newDescription != LearningActivityCreationStrategy.LEARNING_ACTIVITY_DESCRIPTION_UNCHANGED) {
			activity.setDescription(newDescription);
		}
		
		log.debug("new descripción: " + activity.getDescription());

		Map<Long, Long> moduleIds =
				(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(Module.class);
		
		log.debug("oldModuleId: " + activity.getModuleId());
		long moduleId = MapUtil.getLong(moduleIds, activity.getModuleId(), activity.getModuleId());	
		log.debug("new moduleId: " + moduleId);
		
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
		
   	 	serviceContext.setUserId(userId);
   	 	serviceContext.setScopeGroupId(portletDataContext.getScopeGroupId());

	    LearningActivity importedLearningActivity = null;
	    
	    Element activityElement = portletDataContext.getImportDataStagedModelElement(activity);

	    if (portletDataContext.isDataStrategyMirror()) {
	    	
	    	log.debug("data strategry mirror");
	    	
	    	serviceContext.setUuid(activity.getUuid());

	        LearningActivity existingLearningActivity =
	            activityLocalService. fetchLearningActivityByUuidAndGroupId(
	                activity.getUuid(), portletDataContext.getScopeGroupId());

	        if (existingLearningActivity == null) {
	        	log.debug("no existía la actividad, lo creamos");
	            serviceContext.setUuid(activity.getUuid());
	            serviceContext.setUserId(userId);
	            serviceContext.setScopeGroupId(portletDataContext.getScopeGroupId());
	            
	            importedLearningActivity = activityLocalService.addLearningActivity(userId, groupId, moduleId, 
	            		activity.getTitleMap(), activity.getDescriptionMap(), activity.getTypeId(), activity.getStartDate(), activity.getEndDate(), 
	            		activity.getTries(), activity.getPassPuntuation(), activity.getPriority(), activity.getExtraContent(), activity.getFeedbackCorrectMap(), 
	            		activity.getFeedbackNoCorrectMap(), activity.getRequired(), activity.getCommentsActivated(), 
	            		null, serviceContext);
	        }
	        else {
	        	log.debug("la actividad ya existe: " + activity.getActId());
	            importedLearningActivity = activityLocalService.updateLearningActivity(userId, activity.getActId(), moduleId, 
	            		activity.getTitleMap(), activity.getDescriptionMap(), activity.getTypeId(), activity.getStartDate(), activity.getEndDate(), 
	            		activity.getTries(), activity.getPassPuntuation(), activity.getPriority(), activity.getExtraContent(), activity.getFeedbackCorrectMap(), 
	            		activity.getFeedbackNoCorrectMap(), activity.getRequired(), activity.getCommentsActivated(), null, null, serviceContext);
	        }
	    }
	    else {   
	    	log.debug("la actividad ya existe: " + activity.getActId());
	        importedLearningActivity = activityLocalService.addLearningActivity(userId, groupId, moduleId, 
            		activity.getTitleMap(), activity.getDescriptionMap(), activity.getTypeId(), activity.getStartDate(), activity.getEndDate(), 
            		activity.getTries(), activity.getPassPuntuation(), activity.getPriority(), activity.getExtraContent(), activity.getFeedbackCorrectMap(), 
            		activity.getFeedbackNoCorrectMap(), activity.getRequired(), activity.getCommentsActivated(), null, serviceContext);
	    }
	    
	    log.debug("importamos los attachments");
	    importLearningActivityAttachments(portletDataContext, activity, importedLearningActivity);
	    log.debug("fin importación los attachments");
	    
	    LearningActivityTypeFactory learningActivityTypeFactory = LearningActivityTypeFactoryRegistryUtil.getLearningActivityTypeFactoryByType(activity.getTypeId());
		LearningActivityType learningActivityType = learningActivityTypeFactory.getLearningActivityType(importedLearningActivity);
		
		log.debug("importamos la parte específica de la actividad");
		learningActivityType.doImportStagedModel(portletDataContext, activityElement);
		log.debug("fin de la importación la parte específica de la actividad");
		
		importedLearningActivity = activityLocalService.updateLearningActivity(userId, importedLearningActivity);
	    
	    Map<Long, Long> activityIds = (Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(LearningActivity.class);

		activityIds.put(activity.getPrimaryKey(), importedLearningActivity.getPrimaryKey());
	    
	    Map<String, String> activityUuids = (Map<String, String>)portletDataContext.getNewPrimaryKeysMap(LearningActivity.class + ".uuid");
	    
	    activityUuids.put(activity.getUuid(), importedLearningActivity.getUuid());
	}
	
	@Override
	public void restoreStagedModel(
			PortletDataContext portletDataContext, LearningActivity stagedModel)
		throws PortletDataException {

		try {
			doRestoreStagedModel(portletDataContext, stagedModel);
		}
		catch (PortletDataException pde) {
			throw pde;
		}
		catch (Exception e) {
			throw new PortletDataException(e);
		}
	}
	
	@Override
	protected void doRestoreStagedModel(
			PortletDataContext portletDataContext, LearningActivity activity) throws Exception {

		LearningActivity existingActivity = fetchStagedModelByUuidAndGroupId(
				activity.getUuid(), portletDataContext.getScopeGroupId());

		if ((existingActivity == null) || !existingActivity.isInTrash()) {
			return;
		}

		TrashHandler trashHandler = TrashHandlerRegistryUtil.getTrashHandler(Module.class.getName());

		if (trashHandler.isRestorable(existingActivity.getActId())) {
			long userId = portletDataContext.getUserId(activity.getUserUuid());

			trashHandler.restoreTrashEntry(userId, existingActivity.getActId());
		}
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
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(LearningActivity.class);

		activityIds.put(activityId, existingLearningActivity.getActId());
	}
	
	protected void exportLearningActivityAttachments(
			PortletDataContext portletDataContext, Element activityElement,
			LearningActivity activity)
		throws Exception {

		List<FileEntry> attachmentsFileEntries = activity.getAttachmentsFileEntries();

		for (FileEntry fileEntry : attachmentsFileEntries) {
			StagedModelDataHandlerUtil.exportReferenceStagedModel(
				portletDataContext, activity, fileEntry,
				PortletDataContext.REFERENCE_TYPE_WEAK);
		}
	}
	
	protected void importLearningActivityAttachments(PortletDataContext portletDataContext, 
			LearningActivity activity,
			LearningActivity importedLearningActivity)
		throws Exception {

		List<Element> dlFileEntryElements =portletDataContext.getReferenceDataElements(activity, DLFileEntry.class);

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(portletDataContext.getCompanyId());
		serviceContext.setScopeGroupId(portletDataContext.getScopeGroupId());

		for (Element dlFileEntryElement : dlFileEntryElements) {
			String path = dlFileEntryElement.attributeValue("path");

			FileEntry fileEntry = (FileEntry)portletDataContext.getZipEntryAsObject(path);

			String binPath = dlFileEntryElement.attributeValue("bin-path");

			try (InputStream inputStream = getLearningActivityAttachmentInputStream(binPath, portletDataContext, fileEntry)) {

				if (inputStream == null) {
					if (log.isWarnEnabled()) {
						log.warn("Unable to import attachment for file entry " +fileEntry.getFileEntryId());
					}

					continue;
				}
				
				long folderId = LearningActivityAttachmentsUtil.getFolderId(importedLearningActivity.getGroupId(), importedLearningActivity.getUserId(), 
						importedLearningActivity.getActId());
				
				log.debug("folderId: " + folderId);

				_portletFileRepository.addPortletFileEntry(
					importedLearningActivity.getGroupId(),
					portletDataContext.getUserId(importedLearningActivity.getUserUuid()),
					LearningActivity.class.getName(), importedLearningActivity.getActId(),
					LMSPortletKeys.MODULES_ACTIVITIES,
					folderId, inputStream,
					fileEntry.getFileName(), fileEntry.getMimeType(), true);
			} catch (DuplicateFileEntryException dfee) {
				if (log.isDebugEnabled()) {
					log.debug(dfee, dfee);
				}
			}
		}
	}
	
	private InputStream getLearningActivityAttachmentInputStream(String binPath, PortletDataContext portletDataContext,
			FileEntry fileEntry) throws Exception {

		if (Validator.isNull(binPath) &&
			portletDataContext.isPerformDirectBinaryImport()) {

			try {
				return FileEntryUtil.getContentStream(fileEntry);
			}
			catch (NoSuchFileException nsfe) {

				// LPS-52675

				if (log.isDebugEnabled()) {
					log.debug(nsfe, nsfe);
				}

				return null;
			}
		}

		return portletDataContext.getZipEntryAsInputStream(binPath);
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
	
	@Reference(
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY,
		target = "(model.class.name=com.ted.lms.model.LearningActivity)"
	)
	private volatile ExportImportContentProcessor<String> activityExportImportContentProcessor;
	
	@Reference(unbind = "-")
	protected void setPortletFileRepository(PortletFileRepository portletFileRepository) {

		_portletFileRepository = portletFileRepository;
	}
	
	private LearningActivityCreationStrategy learningActivityCreationStrategy;

	private LearningActivityLocalService activityLocalService;
	private ModuleLocalService moduleLocalService;
	private PortletFileRepository _portletFileRepository;
}
