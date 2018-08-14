package com.ted.lms.web.internal.util;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.Validator;
import com.ted.lms.model.LearningActivity;

import java.util.ResourceBundle;

/**
 * Esta clase contiene métodos útiles para trabajar con los cursos
 * @author Virginia Martín Agudo
 *
 */
public class LearningActivityUtil {
	public static String getDisplayTitle(ResourceBundle resourceBundle, LearningActivity learningActivity) {

		if (Validator.isNull(learningActivity.getTitle())) {
			return LanguageUtil.get(resourceBundle, "untitled-entry");
		}

		return learningActivity.getTitle(resourceBundle.getLocale());
	}
	
	public static String getDisplayDescription(ResourceBundle resourceBundle, LearningActivity learningActivity) {

		if (Validator.isNull(learningActivity.getDescription())) {
			return LanguageUtil.get(resourceBundle, "undescripted-entry");
		}

		return learningActivity.getDescription(resourceBundle.getLocale());
	}
}
