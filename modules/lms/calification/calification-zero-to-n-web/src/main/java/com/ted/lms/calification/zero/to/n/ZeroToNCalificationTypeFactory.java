package com.ted.lms.calification.zero.to.n;


import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.ted.lms.calification.zero.to.n.constants.ZeroToNPortletKeys;
import com.ted.lms.model.BaseCalificationTypeFactory;
import com.ted.lms.model.CalificationType;
import com.ted.lms.model.CalificationTypeFactory;
import com.ted.lms.model.Course;
import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Implementa la factoría del tipo de calificación de 0 a n
 * @author Virginia Martín Agudo
 *
 */
@Component(
    immediate = true,
    property = {},
    service = CalificationTypeFactory.class
)
public class ZeroToNCalificationTypeFactory extends BaseCalificationTypeFactory{

	public static final long TYPE = 3;
	
	@Override
	public String getClassName() {
		return ZeroToNCalificationType.class.getName();
	}

	@Override
	public long getType() {
		return TYPE;
	}
	
	@Override
	public CalificationType getCalificationType(Course course) {	
		return new ZeroToNCalificationType(course);
	}
	
	@Override
	public String getTitle(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		return LanguageUtil.get(resourceBundle, "calification.zero-to-n.title");
	}
	
	@Override
	public String getDescription(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		return LanguageUtil.get(resourceBundle, "calification.zero-to-n.description");
	}
	
	@Override
	public String getURLSpecificContent() {
		return "/zero_to_n/calification/edit.jsp";
	}
	
	@Override
	public String getPortletId() {
		return ZeroToNPortletKeys.ZERO_TO_N;
	}
	
	@Reference(
		unbind = "-"
	)
	public void setResourceBundleLoader(ResourceBundleLoader resourceBundleLoader) {

		this.resourceBundleLoader = resourceBundleLoader;
	}

	protected ResourceBundleLoader resourceBundleLoader;

}
