package com.ted.lms.learning.activity.resource.internal.web.activity;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.blogs.kernel.model.BlogsEntry;
import com.liferay.bookmarks.model.BookmarksEntry;
import com.liferay.bookmarks.service.BookmarksEntryLocalService;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppHelperLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.ted.lms.learning.activity.resource.internal.web.constants.ResourceInternalConstants;
import com.ted.lms.model.BaseLearningActivityType;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityTry;
import com.ted.lms.service.LearningActivityResultLocalService;

import javax.portlet.ActionRequest;

public class ResourceInternalActivityType extends BaseLearningActivityType{
	
	private long assetEntryId;
	protected final AssetEntryLocalService assetEntryLocalService;
	protected final BookmarksEntryLocalService bookmarksEntryLocalService;
	protected final DLFileEntryLocalService dlFileEntryLocalService;
	
	public ResourceInternalActivityType(LearningActivity activity, LearningActivityResultLocalService learningActivityResultLocalService,
			AssetEntryLocalService assetEntryLocalService, BookmarksEntryLocalService bookmarksEntryLocalService,
			DLFileEntryLocalService dlFileEntryLocalService) {
		super(activity, learningActivityResultLocalService);
		this.assetEntryLocalService = assetEntryLocalService;
		this.bookmarksEntryLocalService = bookmarksEntryLocalService;
		this.dlFileEntryLocalService = dlFileEntryLocalService;
		
		JSONObject extraContent = activity.getExtraContentJSON();
		
		if(Validator.isNotNull(extraContent)) {
			JSONObject resourceInternal = extraContent.getJSONObject(ResourceInternalConstants.JSON_RESOURCE_INTERNAL);
			if(Validator.isNotNull(resourceInternal)) {
				assetEntryId = resourceInternal.getLong(ResourceInternalConstants.JSON_RESOURCE_INTERNAL_ASSET_ENTRY, 0);
			}else {
				assetEntryId = 0;
			}
		}else {
			assetEntryId = 0;
		}
	}

	@Override
	public double calculateResult(LearningActivityTry learningActivityTry) {
		return 100;
	}

	@Override
	public boolean isPassed(LearningActivityTry learningActivityTry) {
		return true;
	}

	@Override
	public String getClassName() {
		return ResourceInternalActivityType.class.getName();
	}
	
	@Override
	public void setExtraContent(ActionRequest actionRequest) throws PortalException {
		
		long assetEntryId = ParamUtil.getLong(actionRequest,"assetEntryId",0);
		
		JSONObject extraContent = activity.getExtraContentJSON();
		
		JSONObject resourceInternal = extraContent.getJSONObject(ResourceInternalConstants.JSON_RESOURCE_INTERNAL);
		
		if(resourceInternal == null) {
			resourceInternal = JSONFactoryUtil.createJSONObject();
			extraContent.put(ResourceInternalConstants.JSON_RESOURCE_INTERNAL, resourceInternal);
		}

		resourceInternal.put(ResourceInternalConstants.JSON_RESOURCE_INTERNAL_ASSET_ENTRY, assetEntryId);
		this.assetEntryId = assetEntryId;
		
		activity.setExtraContent(extraContent.toJSONString());
	}
	
	public long getAssetEntryId() {
		return assetEntryId;
	}
	
	@Override
	public void copyActivity(LearningActivity oldActivity, ServiceContext serviceContext) throws Exception {
		super.copyActivity(oldActivity, serviceContext);
		
		//Actualizamos el assetEntryId
		JSONObject extraContent = activity.getExtraContentJSON();
		
		JSONObject resourceInternal = extraContent.getJSONObject(ResourceInternalConstants.JSON_RESOURCE_INTERNAL);
		
		long oldAssetEntryId = resourceInternal.getLong(ResourceInternalConstants.JSON_RESOURCE_INTERNAL_ASSET_ENTRY);
		assetEntryId = oldAssetEntryId;
		
		System.out.println("oldAssetEntryId: " + assetEntryId);
		
		AssetEntry oldAssetEntry = assetEntryLocalService.getAssetEntry(oldAssetEntryId);
		
		System.out.println("groupId de recurso: " + oldAssetEntry.getGroupId());
		System.out.println("groupId de la actividad: " + oldActivity.getGroupId());
		
		if(oldAssetEntry.getGroupId() == oldActivity.getGroupId()) {
			if(oldAssetEntry.getClassNameId() == PortalUtil.getClassNameId(DLFileEntry.class)) {
				//Copiamos el dlfileentry
				DLFileEntry newFileEntry = dlFileEntryLocalService.copyFileEntry(activity.getUserId(), activity.getGroupId(), activity.getGroupId(), 
						oldAssetEntry.getClassPK(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, serviceContext);
				System.out.println("newFileEntry: " + newFileEntry.getFileEntryId());
				FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(newFileEntry.getFileEntryId());
				System.out.println("fileEntry: " + fileEntry.getFileEntryId());
				DLAppHelperLocalServiceUtil.checkAssetEntry(serviceContext.getUserId(), fileEntry, fileEntry.getFileVersion());
				assetEntryId = assetEntryLocalService.getEntry(DLFileEntry.class.getName(), newFileEntry.getFileEntryId()).getEntryId();
				System.out.println("assetEntryId: " + assetEntryId);
			}else if(oldAssetEntry.getClassNameId() == PortalUtil.getClassNameId(BookmarksEntry.class)) {
				//Copiamos el enlace
				BookmarksEntry oldBookmarks = bookmarksEntryLocalService.getBookmarksEntry(oldAssetEntry.getClassPK());
				BookmarksEntry newBookmarks = bookmarksEntryLocalService.addEntry(activity.getUserId(), activity.getGroupId(), 0, oldBookmarks.getName(), oldBookmarks.getUrl(),
						oldBookmarks.getDescription(), serviceContext);
				
				assetEntryId = assetEntryLocalService.getEntry(BookmarksEntry.class.getName(), newBookmarks.getEntryId()).getEntryId();
			}else if(oldAssetEntry.getClassNameId() == PortalUtil.getClassNameId(BlogsEntry.class)) {
				//La entrada de blog si es del propio curso no la copiamos
				assetEntryId = 0;
			}
		}
		
		resourceInternal.put(ResourceInternalConstants.JSON_RESOURCE_INTERNAL_ASSET_ENTRY, assetEntryId);
		activity.setExtraContent(extraContent.toJSONString());
	}

}
