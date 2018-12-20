package com.ted.lms.learning.activity.resource.internal.web;

import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.ted.lms.learning.activity.resource.internal.web.constants.ResourceInternalConstants;
import com.ted.lms.learning.activity.resource.internal.web.constants.ResourceInternalPortletKeys;
import com.ted.lms.model.BaseLearningActivityTypeFactory;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityType;
import com.ted.lms.model.LearningActivityTypeFactory;
import com.ted.lms.service.LearningActivityResultLocalService;

import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    immediate = true,
    property = {},
    service = LearningActivityTypeFactory.class
)
public class ResourceInternalActivityTypeFactory extends BaseLearningActivityTypeFactory{

	@Override
	public String getClassName() {
		return ResourceInternalActivityTypeFactory.class.getName();
	}

	@Override
	public String getPortletId() {
		return ResourceInternalPortletKeys.RESOURCE_INTERNAL;
	}

	@Override
	public long getType() {
		return ResourceInternalConstants.TYPE;
	}

	@Override
	public LearningActivityType getLearningActivityType(LearningActivity activity) throws PortalException {
		return new ResourceInternalActivityType(activity, learningActivityResultLocalService, assetEntryLocalService, dlAppLocalService, roleLocalService, resourcePermissionLocalService);
	}
	
	@Override
	public String getIconCssClass() {
		return "activity-resource-internal";
	}
	
	@Override
	public String getName(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		return LanguageUtil.get(resourceBundle, "learning-activity.internal");
	}
	
	@Override
	public String getDescription(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		return LanguageUtil.get(resourceBundle, "learning-activity.internal.help-message");
	}
	
	@Override
	public String getURLSpecificContent() {
		return "/resource_internal/activity/edit.jsp";
	}
	
	@Override
	public boolean canDeleteTries() {
		return true;
	}
	
	@Override
	public boolean isManualCalificationAllowed() {
		return false;
	}
	

	@Reference(unbind = "-")
	public void setResourceBundleLoader(ResourceBundleLoader resourceBundleLoader) {
		this.resourceBundleLoader = resourceBundleLoader;
	}

	protected ResourceBundleLoader resourceBundleLoader;
	
	@Reference(unbind = "-")
	protected void setLearningActivityResultLocalService(LearningActivityResultLocalService learningActivityResultLocalService) {
		this.learningActivityResultLocalService = learningActivityResultLocalService;
	}
	
	protected LearningActivityResultLocalService learningActivityResultLocalService;
	
	@Reference(unbind = "-")
	protected void setResourcePermissionLocalService(
		ResourcePermissionLocalService resourcePermissionLocalService) {

		this.resourcePermissionLocalService = resourcePermissionLocalService;
	}
	
	private ResourcePermissionLocalService resourcePermissionLocalService;
	
	@Reference(unbind = "-")
	protected void setDLAppLocalService(DLAppLocalService dlAppLocalService) {

		this.dlAppLocalService = dlAppLocalService;
	}
	
	private DLAppLocalService dlAppLocalService;
	
	@Reference(unbind = "-")
	protected void setRoleLocalService(RoleLocalService roleLocalService) {

		this.roleLocalService = roleLocalService;
	}
	
	private RoleLocalService roleLocalService;
	
	@Reference(unbind = "-")
	protected void setAssetEntryLocalService(AssetEntryLocalService assetEntryLocalService) {

		this.assetEntryLocalService = assetEntryLocalService;
	}
	
	private AssetEntryLocalService assetEntryLocalService;
	
}
