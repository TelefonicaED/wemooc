package com.ted.lms.learning.activity.resource.internal.web.activity;

import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.bookmarks.service.BookmarksEntryLocalService;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
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

import javax.portlet.PortletMode;
import javax.portlet.PortletModeException;
import javax.portlet.PortletURL;
import javax.portlet.WindowStateException;
import javax.servlet.http.HttpServletRequest;

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
		return new ResourceInternalActivityType(activity, learningActivityResultLocalService, assetEntryLocalService,
				bookmarksEntryLocalService, dlFileEntryLocalService);
	}
	
	public ResourceInternalActivityType getResourceInternalType(LearningActivity activity) {
		return new ResourceInternalActivityType(activity, learningActivityResultLocalService, assetEntryLocalService,
				bookmarksEntryLocalService, dlFileEntryLocalService);
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
		return "/edit.jsp";
	}
	
	@Override
	public boolean canDeleteTries() {
		return true;
	}
	
	@Override
	public boolean isManualCalificationAllowed() {
		return false;
	}
	
	public PortletURL getURLSearchAssetEntry(HttpServletRequest request, long groupId) throws PortalException, PortletModeException, WindowStateException {
		PortletURL selectAssetEntryURL = 
				PortletProviderUtil.getPortletURL(
						request, DLFileEntry.class.getName(),
						PortletProvider.Action.BROWSE);
		
		selectAssetEntryURL.setParameter("typeSelection", DLFileEntry.class.getName());
		selectAssetEntryURL.setParameter("eventName", "selectAsset");
		selectAssetEntryURL.setPortletMode(PortletMode.VIEW);
		selectAssetEntryURL.setWindowState(LiferayWindowState.POP_UP);
		
		return selectAssetEntryURL;
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
	protected void setAssetEntryLocalService(AssetEntryLocalService assetEntryLocalService) {
		this.assetEntryLocalService = assetEntryLocalService;
	}
	protected AssetEntryLocalService assetEntryLocalService;
	
	@Reference(unbind = "-")
	protected void setBookmarksEntryLocalService(BookmarksEntryLocalService bookmarksEntryLocalService) {
		this.bookmarksEntryLocalService = bookmarksEntryLocalService;
	}
	protected BookmarksEntryLocalService bookmarksEntryLocalService;
	
	@Reference(unbind = "-")
	protected void setDLFileEntryLocalService(DLFileEntryLocalService dlFileEntryLocalService) {
		this.dlFileEntryLocalService = dlFileEntryLocalService;
	}
	protected DLFileEntryLocalService dlFileEntryLocalService;
	
}
