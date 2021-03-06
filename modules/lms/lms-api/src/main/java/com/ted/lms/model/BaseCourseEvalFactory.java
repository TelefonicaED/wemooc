package com.ted.lms.model;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.upload.UploadRequest;
import com.liferay.portal.kernel.util.PortalUtil;
import com.ted.lms.constants.LMSPortletKeys;
import java.util.Locale;

import javax.portlet.PortletResponse;

/**
 * Base para la factoría de los métodos de evaluación del curso
 * @author Virginia Martín Agudo
 *
 */
public abstract class BaseCourseEvalFactory implements CourseEvalFactory {

	@Override
	public CourseEval getCourseEval(Course course) throws PortalException {	
		return null;
	}

	@Override
	public long getClassNameId() {
		return PortalUtil.getClassNameId(getClassName());
	}

	@Override
	public String getIconCssClass() {
		return "course-eval";
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
	public String getURLSpecificContent() {
		return null;
	}

	@Override
	public String getPortletId(){
		return LMSPortletKeys.COURSE;
	}
	
	public boolean specificValidations(UploadRequest uploadRequest,PortletResponse portletResponse) {
		return true;
	}
	

	@Override
	public boolean getNeedPassPuntuation() {
		return false;
	}
}
