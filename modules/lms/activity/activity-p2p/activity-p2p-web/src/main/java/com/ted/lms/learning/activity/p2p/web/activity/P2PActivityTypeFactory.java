package com.ted.lms.learning.activity.p2p.web.activity;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferay.portal.kernel.util.Validator;
import com.ted.lms.learning.activity.p2p.constants.P2PConstants;
import com.ted.lms.learning.activity.p2p.service.P2PActivityCorrectionsLocalService;
import com.ted.lms.learning.activity.p2p.service.P2PActivityLocalService;
import com.ted.lms.learning.activity.p2p.web.constants.P2PWebPortletKeys;
import com.ted.lms.model.BaseLearningActivityTypeFactory;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityType;
import com.ted.lms.model.LearningActivityTypeFactory;
import com.ted.lms.service.LearningActivityResultLocalService;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.portlet.ActionRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    immediate = true,
    property = {},
    service = LearningActivityTypeFactory.class
)
public class P2PActivityTypeFactory extends BaseLearningActivityTypeFactory {
	
	@Override
	public String getClassName() {
		return P2PActivityTypeFactory.class.getName();
	}

	@Override
	public long getType() {
		return P2PConstants.TYPE;
	}
	
	@Override
	public LearningActivityType getLearningActivityType(LearningActivity activity) throws PortalException {
		return new P2PActivityType(activity, learningActivityResultLocalService, p2pActivityLocalService, p2pActivityCorrectionsLocalService);
	}
	
	public P2PActivityType getP2PActivityType(LearningActivity activity) {
		return new P2PActivityType(activity, learningActivityResultLocalService, p2pActivityLocalService, p2pActivityCorrectionsLocalService);
	}
	
	@Override
	public String getPortletId() {
		return P2PWebPortletKeys.P2P;
	}
	
	@Override
	public String getName(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		return LanguageUtil.get(resourceBundle, "learning-activity.p2p");
	}
	
	@Override
	public String getDescription(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		return LanguageUtil.get(resourceBundle, "learning-activity.p2p.help-message");
	}
	
	@Override
	public String getURLSpecificContent() {
		return "/p2p/activity/edit.jsp";
	}
	
	@Override
	public String getIconCssClass() {
		return "activity-p2p";
	}
	
	@Override
	public double getDefaultScore() {		
		return P2PConstants.DEFAULT_SCORE;
	}
	
	@Override
	public int getDefaultTries() {
		return P2PConstants.DEFAULT_TRIES;
	}
	
	@Override
	public boolean isAutoCorrect() {
		return false;
	}
	
	@Override
	public String getSpecificResultsPage(){
		return "/activities/p2p/view_result";
	}
	
	@Override
	public void specificValidations(ActionRequest actionRequest) throws PortalException {
		String numValidations = ParamUtil.getString(actionRequest, "numValidations", null);
		if(Validator.isNotNull(numValidations) && !Validator.isNumber(numValidations)) {
			throw new PortalException("learning-activity.p2p.num-validations.error.number");
		}
	}
	
	@Reference(unbind = "-")
	public void setResourceBundleLoader(ResourceBundleLoader resourceBundleLoader) {

		this.resourceBundleLoader = resourceBundleLoader;
	}

	protected ResourceBundleLoader resourceBundleLoader;
	
	@Reference(unbind = "-")
	protected void setP2PActivityLocalService(P2PActivityLocalService p2pActivityLocalService) {
		this.p2pActivityLocalService = p2pActivityLocalService;
	}
	protected P2PActivityLocalService p2pActivityLocalService;
	
	@Reference(unbind = "-")
	protected void setP2PActivityCorrectionsLocalService(P2PActivityCorrectionsLocalService p2pActivityCorrectionsLocalService) {
		this.p2pActivityCorrectionsLocalService = p2pActivityCorrectionsLocalService;
	}
	protected P2PActivityCorrectionsLocalService p2pActivityCorrectionsLocalService;
	
	@Reference(unbind = "-")
	protected void setLearningActivityResultLocalService(LearningActivityResultLocalService learningActivityResultLocalService) {
		this.learningActivityResultLocalService = learningActivityResultLocalService;
	}
	protected LearningActivityResultLocalService learningActivityResultLocalService;
	
}
