package com.ted.lms.learning.activity.resource.internal.web;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.document.library.kernel.exception.DuplicateFileException;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.ted.lms.learning.activity.resource.internal.web.constants.ResourceInternalConstants;
import com.ted.lms.learning.activity.resource.internal.web.util.ResourceInternalPrefsPropsValues;
import com.ted.lms.model.BaseLearningActivityType;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityTry;
import com.ted.lms.service.LearningActivityResultLocalService;
import com.ted.lms.util.LearningActivityUtil;

import javax.portlet.ActionRequest;
import org.osgi.service.component.annotations.Reference;

public class ResourceInternalActivityType extends BaseLearningActivityType{
	
	private AssetEntryLocalService assetEntryLocalService;
	private DLAppLocalService dlAppLocalService;
	private RoleLocalService roleLocalService;
	private ResourcePermissionLocalService resourcePermissionLocalService;
	
	public ResourceInternalActivityType(LearningActivity activity, LearningActivityResultLocalService learningActivityResultLocalService, 
			AssetEntryLocalService assetEntryLocalService, DLAppLocalService dlAppLocalService, RoleLocalService roleLocalService,
			ResourcePermissionLocalService resourcePermissionLocalService) {
		super(activity, learningActivityResultLocalService);
		this.assetEntryLocalService = assetEntryLocalService;
		this.dlAppLocalService = dlAppLocalService;
		this.roleLocalService = roleLocalService;
		this.resourcePermissionLocalService = resourcePermissionLocalService;
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
		
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		boolean linkResources = ResourceInternalPrefsPropsValues.getDocumentLinked(themeDisplay.getCompanyId());	
		
		if(!linkResources){
			ServiceContext serviceContext = ServiceContextFactory.getInstance(LearningActivity.class.getName(), actionRequest);

			//Obtengo el assetEntry y compruebo si es un recurso de tipo Documento y Multimedia comprobando
			//si existe un elemento DLFileEntry con id=assetEntry.getClassPK
			AssetEntry docAsset = assetEntryLocalService.getAssetEntry(assetEntryId);
			
			FileEntry docfile = dlAppLocalService.getFileEntry(docAsset.getClassPK());
			
			if(docfile != null){
				//Creo un directorio propio de la actividad o lo recupero si ya existe.
				Folder dlFolder = learningActivityUtil.getFolderLearningActivity(activity.getGroupId(), activity.getActId());
				if(dlFolder == null) {
					dlFolder = learningActivityUtil.createFolderLearningActivity(themeDisplay.getUserId(), activity.getGroupId(), activity.getActId(), activity.getTitle(themeDisplay.getLocale()), serviceContext);
				}
				FileEntry newFile = null;
				boolean created = false;	
				int counter = 0;
				while(!created){
					try{
						if(counter==0){
							newFile = dlAppLocalService.addFileEntry(themeDisplay.getUserId(), themeDisplay.getScopeGroupId(), dlFolder.getFolderId(), 
									docfile.getTitle()+StringPool.PERIOD+docfile.getExtension(), docfile.getMimeType(), docfile.getTitle(), 
									docfile.getDescription(), "", docfile.getContentStream(), docfile.getSize(), serviceContext);
						}else{
							newFile = dlAppLocalService.addFileEntry(themeDisplay.getUserId(), themeDisplay.getScopeGroupId(), dlFolder.getFolderId(), 
									docfile.getTitle()+counter+StringPool.PERIOD+docfile.getExtension(), docfile.getMimeType(), docfile.getTitle()+counter, 
									docfile.getDescription(), "", docfile.getContentStream(), docfile.getSize(), serviceContext);
						}
						created = true;
					}catch (DuplicateFileException e){
						counter++;
					}
				}
								
				//Recupero el asset asociado al nuevo archivo local del curso.
				AssetEntry newAsset = assetEntryLocalService.getEntry(docAsset.getClassName(), newFile.getFileEntryId());
				
				//Actualizo el extraContentTmp para que apunte al recurso local y no al global
				assetEntryId = newAsset.getEntryId();

				//Actualizo los permisos del recurso.
				Role siteMemberRole = roleLocalService.getRole(themeDisplay.getCompanyId(), RoleConstants.SITE_MEMBER);
				try {

					resourcePermissionLocalService.addResourcePermission(siteMemberRole.getCompanyId(), DLFolder.class.getName(),
						ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(dlFolder.getFolderId()), siteMemberRole.getRoleId(), ActionKeys.VIEW);
					resourcePermissionLocalService.addResourcePermission(siteMemberRole.getCompanyId(), DLFileEntry.class.getName(),
							ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(newFile.getFileEntryId()), siteMemberRole.getRoleId(), ActionKeys.VIEW);
				} catch (Exception e) {
					e.printStackTrace();
				}

				//ImageProcessorUtil.generateImages(newFile.getLatestFileVersion());
			}
		}
		
		JSONObject extraContent = activity.getExtraContentJSON();
		
		JSONObject resourceInternal = extraContent.getJSONObject(ResourceInternalConstants.JSON_RESOURCE_INTERNAL);
		
		if(resourceInternal == null) {
			resourceInternal = JSONFactoryUtil.createJSONObject();
			extraContent.put(ResourceInternalConstants.JSON_RESOURCE_INTERNAL, resourceInternal);
		}

		resourceInternal.put(ResourceInternalConstants.JSON_RESOURCE_INTERNAL_ASSET_ENTRY, assetEntryId);
		
		activity.setExtraContent(extraContent.toJSONString());
	}
	
	@Override
	public void onDelete(ActionRequest actionRequest) throws PortalException {
		
		Folder folder = learningActivityUtil.getFolderLearningActivity(activity.getGroupId(), activity.getActId());
	
		if(folder != null) {
			dlAppLocalService.deleteFolder(folder.getFolderId());
		}
	}
	
	@Reference
	private LearningActivityUtil learningActivityUtil;

}
