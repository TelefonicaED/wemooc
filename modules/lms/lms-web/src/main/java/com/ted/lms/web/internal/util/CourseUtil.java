package com.ted.lms.web.internal.util;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.Validator;
import com.ted.lms.model.Course;

import java.util.ResourceBundle;

/**
 * Esta clase contiene métodos útiles para trabajar con los cursos
 * @author Virginia Martín Agudo
 *
 */
public class CourseUtil {
	public static String getDisplayTitle(ResourceBundle resourceBundle, Course course) {

		if (Validator.isNull(course.getTitle())) {
			return LanguageUtil.get(resourceBundle, "untitled-entry");
		}

		return course.getTitle(resourceBundle.getLocale());
	}
	
	public static String getDisplayDescription(ResourceBundle resourceBundle, Course course) {

		if (Validator.isNull(course.getDescription())) {
			return LanguageUtil.get(resourceBundle, "undescripted-entry");
		}

		return course.getDescription(resourceBundle.getLocale());
	}
}
