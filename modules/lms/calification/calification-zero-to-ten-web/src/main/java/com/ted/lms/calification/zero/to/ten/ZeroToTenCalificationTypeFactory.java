package com.ted.lms.calification.zero.to.ten;


import com.liferay.portal.kernel.exception.PortalException;
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
 * Implementa la factoría del método de calificación de 0 a 10
 * @author Virginia Martín Agudo
 *
 */
@Component(
    immediate = true,
    property = {},
    service = CalificationTypeFactory.class
)
public class ZeroToTenCalificationTypeFactory extends BaseCalificationTypeFactory{

	private static final long TYPE = 1;
	
	@Override
	public String getClassName() {
		return ZeroToTenCalificationType.class.getName();
	}

	@Override
	public long getType() {
		return TYPE;
	}
	
	@Override
	public CalificationType getCalificationType(Course course) throws PortalException {	
		return new ZeroToTenCalificationType();
	}
	
	@Override
	public String getTitle(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		return LanguageUtil.get(resourceBundle, "calification.zero-to-ten.title");
	}
	
	@Override
	public String getDescription(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		return LanguageUtil.get(resourceBundle, "calification.zero-to-ten.description");
	}
	
	@Reference(
		unbind = "-"
	)
	public void setResourceBundleLoader(ResourceBundleLoader resourceBundleLoader) {

		this.resourceBundleLoader = resourceBundleLoader;
	}

	protected ResourceBundleLoader resourceBundleLoader;

}
