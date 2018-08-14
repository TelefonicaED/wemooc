package com.ted.lms.model;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletBag;
import com.liferay.portal.kernel.portlet.PortletBagPool;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.Validator;
import com.ted.lms.constants.LMSPropsKeys;
import com.ted.lms.service.LearningActivityLocalServiceUtil;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.portlet.PortletURL;
import javax.portlet.WindowState;

public abstract class BaseLearningActivityTypeFactory<T> implements LearningActivityTypeFactory<T> {
	@Override
	public LearningActivity getLearningActivity(long actId) throws PortalException {
		return LearningActivityLocalServiceUtil.getLearningActivity(actId);
	}

	@Override
	public LearningActivityType<T> getLearningActivityType(long actId)
		throws PortalException {
		LearningActivity activity = getLearningActivity(actId);
		
		return getLearningActivityType(activity, activity.getTypeId());
	}

	@Override
	public LearningActivityType<T> getLearningActivityType(LearningActivity activity, int type) throws PortalException {

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

	@Override
	public PortletURL getURLView(
			LiferayPortletResponse liferayPortletResponse,
			WindowState windowState)
		throws PortalException {

		return null;
	}

	@Override
	public boolean hasAddPermission(
			PermissionChecker permissionChecker, long groupId, long classTypeId)
		throws Exception {

		return false;
	}

	@Override
	public boolean hasPermission(
			PermissionChecker permissionChecker, long classPK, String actionId)
		throws Exception {

		return _PERMISSION;
	}

	@Override
	public boolean isActive(long companyId) {
		if (Validator.isNull(getPortletId())) {
			return true;
		}

		//Comprobamos que esté activa la actividad para esa companyId
		boolean active = PrefsPropsUtil.getBoolean(companyId, LMSPropsKeys.LEARNING_ACTIVITY_TYPE + "." + getType(), false);
		
		return active;
	}
	
	@Override
	public boolean isActive(long companyId, long groupId) {
		return isActive(companyId);
	}

	private static final boolean _PERMISSION = true;
	
}