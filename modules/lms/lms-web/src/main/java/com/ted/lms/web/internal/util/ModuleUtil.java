package com.ted.lms.web.internal.util;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.Validator;
import com.ted.lms.model.Module;

import java.util.ResourceBundle;

/**
 * Esta clase contiene métodos útiles para trabajar con los cursos
 * @author Virginia Martín Agudo
 *
 */
public class ModuleUtil {
	public static String getDisplayTitle(ResourceBundle resourceBundle, Module module) {

		if (Validator.isNull(module.getTitle())) {
			return LanguageUtil.get(resourceBundle, "untitled-entry");
		}

		return module.getTitle(resourceBundle.getLocale());
	}
	
	public static String getDisplayDescription(ResourceBundle resourceBundle, Module module) {

		if (Validator.isNull(module.getDescription())) {
			return LanguageUtil.get(resourceBundle, "undescripted-entry");
		}

		return module.getDescription(resourceBundle.getLocale());
	}
}
