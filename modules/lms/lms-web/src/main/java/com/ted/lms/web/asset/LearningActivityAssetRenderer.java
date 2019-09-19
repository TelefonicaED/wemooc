package com.ted.lms.web.asset;

import com.liferay.asset.kernel.model.BaseJSPAssetRenderer;
import com.liferay.asset.util.AssetHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityType;
import com.ted.lms.model.LearningActivityTypeFactory;
import com.ted.lms.registry.LearningActivityTypeFactoryRegistryUtil;
import com.ted.lms.web.internal.util.LearningActivityUtil;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletRequest;

public class LearningActivityAssetRenderer extends BaseJSPAssetRenderer<LearningActivity>{
	private final LearningActivity learningActivity;
	private final ResourceBundleLoader resourceBundleLoader;
	private LearningActivityType learningActivityType;
	private final LearningActivityTypeFactory learningActivityTypeFactory;
	
	public LearningActivityAssetRenderer(LearningActivity activity, ResourceBundleLoader resourceBundleLoader) {
		this.learningActivity = activity; 
		this.resourceBundleLoader = resourceBundleLoader;
		this.learningActivityTypeFactory = LearningActivityTypeFactoryRegistryUtil.getLearningActivityTypeFactoryByType(learningActivity.getTypeId());
		try {
			this.learningActivityType = this.learningActivityTypeFactory.getLearningActivityType(activity);
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override 
	public String getClassName() {
		return learningActivityTypeFactory.getClassName();
	}
	
	@Override
	public LearningActivity getAssetObject() {
		return learningActivity;
	}
	
	@Override
	public long getClassPK() {
		return learningActivity.getActId();
	}

	@Override
	public long getGroupId() {
		return learningActivity.getGroupId();
	}

	@Override
	public String getTitle(Locale locale){
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);

		return LearningActivityUtil.getDisplayTitle(resourceBundle, learningActivity);
	}

	@Override
	public long getUserId() {
		return learningActivity.getUserId();
	}
	
	@Override
	public String getUserName() {
		return learningActivity.getUserName();
	}

	@Override
	public String getUuid() {
		return learningActivity.getUuid();
	}

	@Override
	public String getSummary(PortletRequest portletRequest, PortletResponse portletResponse) {
		
		int abstractLength = AssetHelper.ASSET_ENTRY_ABSTRACT_LENGTH;

	    if (portletRequest != null) {
	        abstractLength = GetterUtil.getInteger(
	            portletRequest.getAttribute(
	                WebKeys.ASSET_ENTRY_ABSTRACT_LENGTH),
	            AssetHelper.ASSET_ENTRY_ABSTRACT_LENGTH);
	    }
		
		Locale locale = getLocale(portletRequest);
		
		String summary =  HtmlUtil.stripHtml(StringUtil.shorten(learningActivity.getDescription(locale), abstractLength));
		
		return summary;
	}

	@Override
	public String getJspPath(HttpServletRequest request, String template) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getStatus() {
	    return learningActivity.getStatus();
	}
	
}
