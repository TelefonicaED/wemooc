package com.ted.lms.model;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.upload.UploadRequest;
import com.liferay.portal.kernel.util.PortalUtil;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.service.CourseResultLocalService;

import java.util.Locale;

import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Reference;

/**
 * Base para la factoría de los tipos de inscripción a cursos
 * @author Virginia Martín Agudo
 *
 */
public abstract class BaseInscriptionTypeFactory implements InscriptionTypeFactory {

	@Override
	public InscriptionType getInscriptionType(Course course, ServiceContext serviceContext) throws PortalException {	
		return null;
	}

	@Override
	public long getClassNameId() {
		return PortalUtil.getClassNameId(getClassName());
	}

	@Override
	public String getIconCssClass() {
		return "inscription-type";
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
	public String getPortletId() {
		return LMSPortletKeys.INSCRIPTION;
	}
	
	@Override
	public boolean specificValidations(UploadRequest uploadRequest,PortletResponse portletResponse) {
		return true;
	}
	
}
