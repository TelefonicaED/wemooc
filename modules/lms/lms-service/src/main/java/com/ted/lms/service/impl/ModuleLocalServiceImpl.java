/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.ted.lms.service.impl;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetLinkConstants;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.comment.CommentManager;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector;
import com.liferay.portal.kernel.social.SocialActivityManagerUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.liferay.social.kernel.model.SocialActivityConstants;
import com.liferay.trash.exception.TrashEntryException;
import com.liferay.trash.service.TrashEntryLocalService;
import com.ted.lms.constants.DLAppConstants;
import com.ted.lms.constants.LMSActivityKeys;
import com.ted.lms.constants.LMSConstants;
import com.ted.lms.exception.ModuleEndDateException;
import com.ted.lms.exception.ModuleStartDateException;
import com.ted.lms.exception.NoSuchNextModuleException;
import com.ted.lms.exception.NoSuchPreviousModuleException;
import com.ted.lms.model.Module;
import com.ted.lms.service.base.ModuleLocalServiceBaseImpl;
import com.ted.lms.service.util.SmallImageHelper;
import com.ted.lms.settings.ModulesGroupServiceSettings;
import com.ted.lms.util.LMSPrefsPropsValues;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * The implementation of the module local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ted.lms.service.ModuleLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModuleLocalServiceBaseImpl
 * @see com.ted.lms.service.ModuleLocalServiceUtil
 */
public class ModuleLocalServiceImpl extends ModuleLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.ted.lms.service.ModuleLocalServiceUtil} to access the module local service.
	 */
	
	private static final Log log = LogFactoryUtil.getLog(ModuleLocalServiceImpl.class);
	
	@Override
	public Module addModule(long userId, Map<Locale,String> titleMap, Map<Locale,String> descriptionMap, boolean useStartExecutionDateCourse, int startDateMonth, 
			int startDateDay, int startDateYear, int startDateHour, int startDateMinute, boolean useEndExecutionDateCourse, int endDateMonth, int endDateDay,
			int endDateYear, int endDateHour, int endDateMinute, int allowedHours, int allowedMinutes, ImageSelector smallImageImageSelector, 
			long moduleEvalId, ServiceContext serviceContext) throws PortalException {
		
		User user = userLocalService.getUser(userId);
		
		Date startDate = null;
		
		if(!useStartExecutionDateCourse) {	
			startDate = PortalUtil.getDate(
					startDateMonth, startDateDay, startDateYear, startDateHour,
					startDateMinute, user.getTimeZone(),
					ModuleStartDateException.class);
		}
		
		Date endDate = null;
		
		if(!useEndExecutionDateCourse) {
			endDate = PortalUtil.getDate(
					endDateMonth, endDateDay, endDateYear, endDateHour,
					endDateMinute, user.getTimeZone(),
					ModuleEndDateException.class);
		}
		
		long allowedTime = TimeUnit.HOURS.toMillis(allowedHours) + TimeUnit.MINUTES.toMillis(allowedMinutes);
		
		return moduleLocalService.addModule(userId, titleMap, descriptionMap, startDate, endDate, allowedTime,
				smallImageImageSelector, moduleEvalId, StringPool.BLANK, serviceContext);
	}
	
	@Override
	@Indexable(type = IndexableType.REINDEX)
	public Module addModule(long userId, Map<Locale,String> titleMap, Map<Locale,String> descriptionMap, Date startDate, 
			Date endDate, long allowedTime, ImageSelector smallImageImageSelector, 
			long moduleEvalId, String moduleExtraData, ServiceContext serviceContext) throws PortalException {
		User user = userLocalService.getUser(userId);
		
		Module module = modulePersistence.create(counterLocalService.increment(Module.class.getName()));
		
		module.setUuid(serviceContext.getUuid());
		module.setGroupId(serviceContext.getScopeGroupId());
		module.setCompanyId(user.getCompanyId());
		module.setUserId(user.getUserId());
		module.setUserName(user.getFullName());
		module.setTitleMap(titleMap);
		module.setDescriptionMap(descriptionMap);
		module.setOrder(module.getModuleId());
		module.setStartDate(startDate);
		module.setEndDate(endDate);
		module.setAllowedTime(allowedTime);
		module.setModuleEvalId(moduleEvalId);
		module.setModuleExtraData(moduleExtraData);
		
		Date now = new Date();
		module.setCreateDate(serviceContext.getModifiedDate(now));
		module.setModifiedDate(serviceContext.getModifiedDate(now));
		
		module.setStatus(WorkflowConstants.STATUS_APPROVED);
		module.setStatusByUserId(userId);
		module.setStatusDate(serviceContext.getModifiedDate(now));
		module.setExpandoBridgeAttributes(serviceContext);
		
		long smallImageId = 0;

		if (smallImageImageSelector != null) {

			if (smallImageImageSelector.getImageBytes() != null) {
				ModulesGroupServiceSettings modulesGroupServiceSettings = ModulesGroupServiceSettings.getInstance(serviceContext.getScopeGroupId());
				
				smallImageId = SmallImageHelper.addSmallImageFileEntry(userId, serviceContext.getScopeGroupId(), Module.class.getName(),
						module.getModuleId(), smallImageImageSelector, modulesGroupServiceSettings.getSmallImageWidth(), 
						UNIQUE_FILE_NAME_TRIES, SMALL_IMAGE_FOLDER_NAME);
			}
		}

		SmallImageHelper.validate(smallImageId, module.getCompanyId());

		module.setSmallImageId(smallImageId);
		
		module = modulePersistence.update(module);
		
		resourceLocalService.addResources(module.getCompanyId(), module.getGroupId(), module.getUserId(),  Module.class.getName(), 
				module.getModuleId(), false, true, false);
		
		updateAsset(userId, module, serviceContext.getAssetCategoryIds(), serviceContext.getAssetTagNames(), serviceContext.getAssetLinkEntryIds(), 
				serviceContext.getAssetPriority());
		
		return module;
	}
	
	public void updateAsset(long userId, Module module, long[] assetCategoryIds,String[] assetTagNames, long[] assetLinkEntryIds, 
			Double priority) throws PortalException {

		boolean visible = module.isApproved();

		String summary = HtmlUtil.extractText(StringUtil.shorten(module.getDescriptionCurrentValue(), 500));

		AssetEntry assetEntry = assetEntryLocalService.updateEntry(userId, module.getGroupId(), module.getCreateDate(),
			module.getModifiedDate(), Module.class.getName(), module.getModuleId(), module.getUuid(), 0, assetCategoryIds,
			assetTagNames, true, visible, null, null, null, null,
			ContentTypes.TEXT_HTML, module.getTitle(), module.getDescription(),
			summary, null, null, 0, 0, priority);

		assetLinkLocalService.updateLinks(userId, assetEntry.getEntryId(), assetLinkEntryIds,AssetLinkConstants.TYPE_RELATED);

	}
	
	@Override
	public Module updateModule(long userId, long moduleId, Map<Locale,String> titleMap, Map<Locale,String> descriptionMap, boolean useStartExecutionDateCourse, int startDateMonth, 
			int startDateDay, int startDateYear, int startDateHour, int startDateMinute, boolean useEndExecutionDateCourse, int endDateMonth, int endDateDay,
			int endDateYear, int endDateHour, int endDateMinute, int allowedHours, int allowedMinutes, ImageSelector smallImageImageSelector, 
			long moduleEvalId, String moduleExtraData, ServiceContext serviceContext) throws PortalException {
		
		User user = userLocalService.getUser(userId);
		
		Date startDate = null;
		
		if(!useStartExecutionDateCourse) {	
			startDate = PortalUtil.getDate(
					startDateMonth, startDateDay, startDateYear, startDateHour,
					startDateMinute, user.getTimeZone(),
					ModuleStartDateException.class);
		}
		
		Date endDate = null;
		
		if(!useEndExecutionDateCourse) {
			endDate = PortalUtil.getDate(
					endDateMonth, endDateDay, endDateYear, endDateHour,
					endDateMinute, user.getTimeZone(),
					ModuleEndDateException.class);
		}
		
		long allowedTime = TimeUnit.HOURS.toMillis(allowedHours) + TimeUnit.MINUTES.toMillis(allowedMinutes);
		
		return moduleLocalService.updateModule(userId, moduleId, titleMap, descriptionMap, startDate, endDate, allowedTime, 
				smallImageImageSelector, moduleEvalId, moduleExtraData, serviceContext);
	}
	
	@Override
	public Module updateOrder(Module module, long order) {
		module.setOrder(order);
		return modulePersistence.update(module);
	}
	
	@Override
	@Indexable(type = IndexableType.REINDEX)
	public Module updateModule(long userId, long moduleId, Map<Locale,String> titleMap, Map<Locale,String> descriptionMap, Date startDate, 
			Date endDate, long allowedTime, ImageSelector smallImageImageSelector, 
			long moduleEvalId, String moduleExtraData, ServiceContext serviceContext) throws PortalException {
		
		Module module = modulePersistence.fetchByPrimaryKey(moduleId);
		
		module.setTitleMap(titleMap);
		module.setDescriptionMap(descriptionMap);
		module.setStartDate(startDate);
		module.setEndDate(endDate);
		module.setAllowedTime(allowedTime);
		module.setModuleEvalId(moduleEvalId);
		if(moduleExtraData != null)
			module.setModuleExtraData(moduleExtraData);
		
		module.setExpandoBridgeAttributes(serviceContext);
		
		Date now = new Date();
		module.setModifiedDate(serviceContext.getModifiedDate(now));
		module.setExpandoBridgeAttributes(serviceContext);
		
		// Small image
		long smallImageId = module.getSmallImageId();

		long deletePreviousSmallImageId = 0;

		if (smallImageImageSelector != null) {
			if (smallImageImageSelector.getImageBytes() != null) {
				ModulesGroupServiceSettings modulesGroupServiceSettings = ModulesGroupServiceSettings.getInstance(serviceContext.getScopeGroupId());
				
				smallImageId = SmallImageHelper.addSmallImageFileEntry(userId, module.getGroupId(), Module.class.getName(), 
						moduleId,smallImageImageSelector, modulesGroupServiceSettings.getSmallImageWidth(), UNIQUE_FILE_NAME_TRIES, 
						SMALL_IMAGE_FOLDER_NAME);
			} else {
				smallImageId = 0;
			}

			deletePreviousSmallImageId = module.getSmallImageId();
		}

		SmallImageHelper.validate(smallImageId, module.getCompanyId());

		module.setSmallImageId(smallImageId);
		
		module = modulePersistence.update(module);
		
		updateAsset(userId, module, serviceContext.getAssetCategoryIds(), serviceContext.getAssetTagNames(), serviceContext.getAssetLinkEntryIds(), 
				serviceContext.getAssetPriority());
		
		if (deletePreviousSmallImageId != 0) {
			PortletFileRepositoryUtil.deletePortletFileEntry(deletePreviousSmallImageId);
		}
		
		return module;
	}
	
	@Override
	public long addOriginalImageFileEntry(long userId, long groupId, long entryId, ImageSelector imageSelector) throws PortalException {

		byte[] imageBytes = imageSelector.getImageBytes();

		if (imageBytes == null) {
			return 0;
		}

		Folder folder = SmallImageHelper.addSmallImageFolder(userId, groupId, SMALL_IMAGE_FOLDER_NAME);

		FileEntry originalFileEntry = PortletFileRepositoryUtil.addPortletFileEntry(
				groupId, userId, null, 0, LMSConstants.SERVICE_NAME, folder.getFolderId(), imageBytes,
				SmallImageHelper.getUniqueFileName(groupId, imageSelector.getImageTitle(), folder.getFolderId(), UNIQUE_FILE_NAME_TRIES),
				imageSelector.getImageMimeType(), true);

		return originalFileEntry.getFileEntryId();
	}
	
	public List<Module> findAllInGroup(long groupId) throws SystemException {
		return modulePersistence.findByGroupId(groupId);
	}
	
	public void deleteModules(long groupId) {
		List<Module> listModules = modulePersistence.findByGroupId(groupId);
		for(Module module: listModules) {
			modulePersistence.remove(module);
		}
	}
	
	@Override
	@Indexable(type = IndexableType.DELETE)
	public Module deleteModule(Module module) {
		//Primero eliminamos las actividades
		learningActivityLocalService.deleteLearningActivities(module.getModuleId());
		return super.deleteModule(module);
	}
	
	@Override
	@Indexable(type = IndexableType.DELETE)
	public Module deleteModule(long moduleId) throws PortalException {
		//Primero eliminamos las actividades
		learningActivityLocalService.deleteLearningActivities(moduleId);
		return super.deleteModule(moduleId);
	}
	
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Module moveModuleToTrash(long userId, Module module) throws PortalException {

		// Módulo

		if (module.isInTrash()) {
			throw new TrashEntryException();
		}

		int oldStatus = module.getStatus();

		if (oldStatus == WorkflowConstants.STATUS_PENDING) {
			module.setStatus(WorkflowConstants.STATUS_DRAFT);

			modulePersistence.update(module);
		}

		module = updateStatus(userId, module.getModuleId(), WorkflowConstants.STATUS_IN_TRASH, new ServiceContext(), new HashMap<>());

		// Social
		JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject();

		extraDataJSONObject.put("title", module.getTitleCurrentValue());

		SocialActivityManagerUtil.addActivity(userId, module, SocialActivityConstants.TYPE_MOVE_TO_TRASH, extraDataJSONObject.toString(), 0);

		// Workflow
		if (oldStatus == WorkflowConstants.STATUS_PENDING) {
			workflowInstanceLinkLocalService.deleteWorkflowInstanceLink(module.getCompanyId(), module.getGroupId(), Module.class.getName(), module.getModuleId());
		}

		return module;
	}
	
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Module updateStatus(long userId, long moduleId, int status, ServiceContext serviceContext, Map<String, Serializable> workflowContext) throws PortalException {

		// MÃ³dulo

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		Module module = modulePersistence.findByPrimaryKey(moduleId);

		int oldStatus = module.getStatus();

		module.setStatus(status);
		module.setStatusByUserId(user.getUserId());
		module.setStatusByUserName(user.getFullName());
		module.setStatusDate(serviceContext.getModifiedDate(now));

		modulePersistence.update(module);

		JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject();

		extraDataJSONObject.put("title", module.getTitleCurrentValue());

		if (status == WorkflowConstants.STATUS_APPROVED) {
			// Social

			if (oldStatus != WorkflowConstants.STATUS_IN_TRASH) {

				if (serviceContext.isCommandUpdate()) {
					SocialActivityManagerUtil.addActivity( user.getUserId(), module, LMSActivityKeys.UPDATE_MODULE, extraDataJSONObject.toString(), 0);
				} else {
					SocialActivityManagerUtil.addUniqueActivity(user.getUserId(), module, LMSActivityKeys.ADD_MODULE, extraDataJSONObject.toString(), 0);
				}
			}

			// Trash
			if (oldStatus == WorkflowConstants.STATUS_IN_TRASH) {
				if (LMSPrefsPropsValues.getModuleCommentsEnabled(module.getCompanyId())) {
					commentManager.restoreDiscussionFromTrash(Module.class.getName(), moduleId);
				}
				trashEntryLocalService.deleteEntry(Module.class.getName(), moduleId);
			}
		} else {
			// Social

			if (oldStatus != WorkflowConstants.STATUS_IN_TRASH) {
				if (serviceContext.isCommandUpdate()) {
					SocialActivityManagerUtil.addActivity(user.getUserId(), module, LMSActivityKeys.UPDATE_MODULE, extraDataJSONObject.toString(), 0);
				} else {
					SocialActivityManagerUtil.addUniqueActivity(user.getUserId(), module, LMSActivityKeys.ADD_MODULE, extraDataJSONObject.toString(), 0);
				}
			}

			// Trash
			if (status == WorkflowConstants.STATUS_IN_TRASH) {
				if (LMSPrefsPropsValues.getModuleCommentsEnabled(module.getCompanyId())) {
					commentManager.moveDiscussionToTrash(Module.class.getName(), moduleId);
				}

				trashEntryLocalService.addTrashEntry( userId, module.getGroupId(), Module.class.getName(),
					module.getModuleId(), module.getUuid(), null, oldStatus, null, null);
			} else if (oldStatus == WorkflowConstants.STATUS_IN_TRASH) {
				if (LMSPrefsPropsValues.getModuleCommentsEnabled(module.getCompanyId())) {
					commentManager.restoreDiscussionFromTrash(Module.class.getName(), moduleId);
				}

				trashEntryLocalService.deleteEntry(Module.class.getName(), moduleId);
			}
		}

		return module;
	}
	
	@Override
	public Module moveModuleToTrash(long userId, long moduleId) throws PortalException {

		Module module = modulePersistence.findByPrimaryKey(moduleId);

		return moduleLocalService.moveModuleToTrash(userId, module);
	}
	
	@Override
	public Module moveDownModule(long moduleId , ServiceContext serviceContext) throws PortalException{
		
		Module module = modulePersistence.fetchByPrimaryKey(moduleId);
		
		Module nextModule = getNextModule(module);
		
		if(nextModule!=null) {
			Date now = new Date();
			//Se actualiza el orden
			long order = module.getOrder();
			module.setOrder(nextModule.getOrder());
			nextModule.setOrder(order);
			
			module.setModifiedDate(serviceContext.getModifiedDate(now));
			nextModule.setModifiedDate(serviceContext.getModifiedDate(now));
			
			modulePersistence.update(module);			
			modulePersistence.update(nextModule);
		}
		
		return module;
	}
	
	@Override
	public Module moveUpModule(long moduleId , ServiceContext serviceContext) throws PortalException{
		
		Module module = modulePersistence.fetchByPrimaryKey(moduleId);
		
		Module previousModule = getPreviousModule(module);
		
		if(previousModule!=null) {
			Date now = new Date();
			long order=module.getOrder();
			module.setOrder(previousModule.getOrder());
			previousModule.setOrder(order);
			
			module.setModifiedDate(serviceContext.getModifiedDate(now));
			previousModule.setModifiedDate(serviceContext.getModifiedDate(now));
			
			modulePersistence.update(module);			
			modulePersistence.update(previousModule);
		}
		
		return module;
	}
	
	@Override
	public Module getNextModule(Module module) throws PortalException {
		List<Module> listNextModules = modulePersistence.findByGroupIdNextModules(module.getGroupId(), module.getOrder());
		Module nextModule = null;
		if(listNextModules != null && listNextModules.size() > 0) {
			nextModule = listNextModules.get(0);
		}else {
			throw new NoSuchNextModuleException();
		}
		return nextModule;
	}
	
	@Override
	public Module getPreviousModule(Module module) throws PortalException {
		List<Module> listPreviousModules = modulePersistence.findByGroupIdPreviousModules(module.getGroupId(), module.getOrder());
		Module previousModule = null;
		if(listPreviousModules != null && listPreviousModules.size() > 0) {
			previousModule = listPreviousModules.get(0);
		}else {
			throw new NoSuchPreviousModuleException();
		}
		return previousModule;
	}
	
	@Override
	public Folder addModuleFolder(long userId, long repositoryId) {
		
		Folder moduleFolder = null; 
        Folder dlFolderMain = null;
        
        ServiceContext serviceContext = new ServiceContext();

        //Damos permisos a los usuarios de la comunidad
		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(false);

        try {
        	//Si exite, obtenemos la carpeta principal
        	dlFolderMain = DLAppLocalServiceUtil.getFolder(repositoryId,DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,DLAppConstants.DOCUMENTLIBRARY_MAINFOLDER);
        } catch (PortalException e){
        	//Si no existe la carpeta principal la creo
        	try {
				dlFolderMain = DLAppLocalServiceUtil.addFolder(userId, repositoryId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, 
						DLAppConstants.DOCUMENTLIBRARY_MAINFOLDER, DLAppConstants.DOCUMENTLIBRARY_MAINFOLDER_DESCRIPTION, serviceContext);
			} catch (PortalException e1) {
				e1.printStackTrace();
			}
        }
        
        //Ahora creamos la carpeta para los módulos
        if(Validator.isNotNull(dlFolderMain)){
        	try {
				moduleFolder = DLAppLocalServiceUtil.getFolder(repositoryId,dlFolderMain.getFolderId(),DLAppConstants.DOCUMENTLIBRARY_PORTLETFOLDER);
			} catch (PortalException e) {
				//Si no existe la creamos
				try {
					moduleFolder = DLAppLocalServiceUtil.addFolder(userId, repositoryId, dlFolderMain.getFolderId(), DLAppConstants.DOCUMENTLIBRARY_PORTLETFOLDER, 
							DLAppConstants.DOCUMENTLIBRARY_PORTLETFOLDER_DESCRIPTION, serviceContext);
				} catch (PortalException e1) {
					e1.printStackTrace();
				}
			}
        }
        
        return moduleFolder;
	}
	
	@Override
	public List<Module> getModules(long groupId, int start, int end){
		return modulePersistence.findByGroupId(groupId, start, end);
	}
	
	@Override
	public int getModulesCount(long groupId) {
		return modulePersistence.countByGroupId(groupId);
	}
	
	private static final String SMALL_IMAGE_FOLDER_NAME = "Small Image";
	
	private static final int UNIQUE_FILE_NAME_TRIES = 50;
	
	@ServiceReference(type = CommentManager.class)
	protected CommentManager commentManager;
	
	@ServiceReference(type = TrashEntryLocalService.class)
	protected TrashEntryLocalService trashEntryLocalService;
	
}