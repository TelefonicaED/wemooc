package com.ted.lms.model;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.PortletBag;
import com.liferay.portal.kernel.portlet.PortletBagPool;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.Validator;
import com.ted.lms.service.LearningActivityLocalServiceUtil;
import com.ted.lms.util.LMSPrefsPropsValues;

import java.util.Locale;
import java.util.ResourceBundle;

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
	public long getClassNameId() {
		return PortalUtil.getClassNameId(getClassName());
	}

	@Override
	public String getIconCssClass() {
		return "activity";
	}
	
/*	@Override
	public boolean isCategorizable() {
		return false;
	}*/

	@Override
	public String getName(Locale locale) {
		String modelResourceNamePrefix =
			ResourceActionsUtil.getModelResourceNamePrefix();

		String key = modelResourceNamePrefix.concat(getClassName());

		String value = LanguageUtil.get(locale, key, null);

		if (value == null) {
			PortletBag portletBag = PortletBagPool.get(getPortletId());

			ResourceBundle resourceBundle = portletBag.getResourceBundle(
				locale);

			if (resourceBundle != null) {
				value = ResourceBundleUtil.getString(resourceBundle, key);
			}
		}

		if (value == null) {
			value = getClassName();
		}

		return value;
	}
	
	@Override
	public String getDescription(Locale locale) {
		return "";
	}

/*	@Override
	public PortletURL getURLView(
			LiferayPortletResponse liferayPortletResponse,
			WindowState windowState)
		throws PortalException {

		return null;
	}*/

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
		if (Validator.isNull(getPortletId())) {
			return true;
		}

		//Comprobamos que esté activa la actividad para esa companyId
		boolean active = LMSPrefsPropsValues.getLearningActivityType(companyId, getType());
		
		return active;
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
	public boolean canBeSeenResults() {
		return false;
	}
	
	@Override
	public String getSpecificResultsPage() {
		return null;
	}
	
	@Override
	public void specificValidations(ActionRequest actionRequest) throws PortalException {
		
	}
	
	public String getURLSpecificContent() {
		return null;
	}
}
