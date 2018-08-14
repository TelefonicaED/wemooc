package com.ted.lms.model;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.ted.lms.service.CourseLocalServiceUtil;

import java.util.Locale;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;

public abstract class BaseCalificationTypeFactory<T> implements CalificationTypeFactory<T> {

	@Override
	public CalificationType<T> getCalificationType(Course course) throws PortalException {	
		return null;
	}

	@Override
	public CalificationType<T> getCalificationType(Module module) throws PortalException {
		Course course = CourseLocalServiceUtil.getCourseByGroupCreatedId(module.getGroupId());
		return getCalificationType(course);
	}
	
	@Override
	public CalificationType<T> getCalificationType(LearningActivity learningActivity) throws PortalException {
		Course course = CourseLocalServiceUtil.getCourseByGroupCreatedId(learningActivity.getGroupId());
		return getCalificationType(course);
	}

	@Override
	public long getClassNameId() {
		return PortalUtil.getClassNameId(getClassName());
	}

	@Override
	public String getIconCssClass() {
		return "calification-type";
	}

	@Override
	public String getTitle(Locale locale) {
		String modelResourceNamePrefix =
			ResourceActionsUtil.getModelResourceNamePrefix();

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
	public PortletURL getURLSpecificContent(
			LiferayPortletResponse liferayPortletResponse,
			WindowState windowState)
		throws PortalException{
		return null;
	}
	
}
