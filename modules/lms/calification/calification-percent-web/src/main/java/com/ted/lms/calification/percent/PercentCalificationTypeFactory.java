package com.ted.lms.calification.percent;


import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.ted.lms.model.BaseCalificationTypeFactory;
import com.ted.lms.model.CalificationType;
import com.ted.lms.model.CalificationTypeFactory;
import com.ted.lms.model.Course;
import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Implementa la factoría del tipo de calificación por porcentaje
 * @author Virginia Martín Agudo
 *
 */
@Component(
    immediate = true,
    property = {},
    service = CalificationTypeFactory.class
)
public class PercentCalificationTypeFactory extends BaseCalificationTypeFactory{

	private static final long TYPE = 0;
	
	@Override
	public String getClassName() {
		return PercentCalificationType.class.getName();
	}

	@Override
	public long getType() {
		return TYPE;
	}
	
	@Override
	public CalificationType getCalificationType(Course course) {	
		return new PercentCalificationType();
	}
	
	@Override
	public String getTitle(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		return LanguageUtil.get(resourceBundle, "calification.percent.title");
	}
	
	@Override
	public String getDescription(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		return LanguageUtil.get(resourceBundle, "calification.percent.description");
	}
	
	@Reference(
		unbind = "-"
	)
	public void setResourceBundleLoader(ResourceBundleLoader resourceBundleLoader) {

		this.resourceBundleLoader = resourceBundleLoader;
	}

	protected ResourceBundleLoader resourceBundleLoader;

}
