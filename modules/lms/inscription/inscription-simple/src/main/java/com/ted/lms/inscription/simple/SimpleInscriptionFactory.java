package com.ted.lms.inscription.simple;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.ted.lms.model.BaseInscriptionTypeFactory;
import com.ted.lms.model.Course;
import com.ted.lms.model.InscriptionTypeFactory;

import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    immediate = true,
    property = {},
    service = InscriptionTypeFactory.class
)
public class SimpleInscriptionFactory extends BaseInscriptionTypeFactory{
	
	public static final long TYPE = 0;

	@Override
	public String getClassName() {
		return SimpleInscriptionFactory.class.getName();
	}

	@Override
	public long getType() {
		return TYPE;
	}
	
	@Override
	public SimpleInscription getInscriptionType(Course course, ServiceContext serviceContext) throws PortalException {	
		return new SimpleInscription(course, serviceContext, courseLocalService);
	}
	
	@Override
	public String getTitle(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		return LanguageUtil.get(resourceBundle, "inscription.simple.title");
	}
	
	@Reference(
			target = "(bundle.symbolic.name=com.ted.lms.inscription.simple)", unbind = "-"
		)
		public void setResourceBundleLoader(ResourceBundleLoader resourceBundleLoader) {

			this.resourceBundleLoader = resourceBundleLoader;
		}

		protected ResourceBundleLoader resourceBundleLoader;

}
