package com.ted.lms.model;

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.ted.lms.service.LearningActivityLocalServiceUtil;
import java.util.Locale;
import javax.portlet.ActionRequest;

/**
 * Base para la factoría de los tipos de actividad
 * @author Virginia Martín Agudo
 *
 */
public abstract class BaseLearningActivityTypeFactory implements LearningActivityTypeFactory {
	
	public static final int DEFAULT_TRIES = 0;
	public static final double DEFAULT_SCORE = 0;
	
	@Override
	public LearningActivity getLearningActivity(long actId) throws PortalException {
		return LearningActivityLocalServiceUtil.getLearningActivity(actId);
	}

	@Override
	public LearningActivityType getLearningActivityType(long actId)
		throws PortalException {
		LearningActivity activity = getLearningActivity(actId);
		
		return getLearningActivityType(activity);
	}

	@Override
	public LearningActivityType getLearningActivityType(LearningActivity activity) throws PortalException {

		return null;
	}
	
	@Override
	public void doImportStagedModel(PortletDataContext portletDataContext) throws PortalException{
		
	}
	
	@Override
	public void doExportStagedModel(PortletDataContext portletDataContext) throws PortalException{
		
	}

	@Override
	public long getClassNameId() {
		return PortalUtil.getClassNameId(getClassName());
	}

	@Override
	public String getIconCssClass() {
		return "activity";
	}
	
	@Override
	public String getName(Locale locale) {
		String modelResourceNamePrefix = ResourceActionsUtil.getModelResourceNamePrefix();

		String key = modelResourceNamePrefix.concat(getClassName());

		String value = LanguageUtil.get(locale, key, null);

		if (value == null) {
			value = getClassName();
		}

		return value;
	}
	
	@Override
	public String getDescription(Locale locale) {
		return "";
	}

	@Override
	public boolean hasAddPermission(
			PermissionChecker permissionChecker, long groupId, long classTypeId)
		throws Exception {

		return true;
	}

	@Override
	public boolean hasPermission(PermissionChecker permissionChecker, long classPK, String actionId) throws Exception {
		return true;
	}
	
	@Override
	public boolean isActive(long companyId, long groupId) {
		return true;

	}
	
	@Override
	public boolean isScoreConfigurable() {
		return false;
	}
	
	@Override
	public double getDefaultScore() {
		return DEFAULT_SCORE;
	}
	
	@Override
	public String getScoreConfigurableProperty() {
		return "puntuation-passed";
	}
	
	public String getScoreConfigurableHelpMessageProperty() {
		return "puntuation-passed.help-message";
	}
	
	@Override
	public boolean isTriesConfigurable() {
		return false;
	}
	
	@Override
	public int getDefaultTries() {
		return DEFAULT_TRIES;
	}
	
	@Override
	public boolean isFeedbackCorrectConfigurable() {
		return false;
	}
	
	@Override
	public String getDefaultFeedbackCorrect() {
		return "";
	}
	
	@Override
	public boolean isFeedbackNoCorrectConfigurable() {
		return false;
	}
	
	@Override
	public String getDefaultFeedbackNoCorrect() {
		return "";
	}
	
	@Override
	public boolean isManualCalificationAllowed() {
		return true;
	}
	
	@Override
	public boolean canDeleteTries() {
		return false;
	}
	
	@Override
	public boolean isAutoCorrect() {
		return true;
	}
	
	@Override
	public String getSpecificResultsPage() {
		return null;
	}
	
	@Override
	public void specificValidations(ActionRequest actionRequest) throws PortalException {
		
	}
	
	@Override
	public String getURLSpecificContent() {
		return null;
	}
	
	@Override
	public boolean canAccessFinished() {
		return false;
	}
	
	@Override
	public boolean hasDocuments() {
		return false;
	}
}
