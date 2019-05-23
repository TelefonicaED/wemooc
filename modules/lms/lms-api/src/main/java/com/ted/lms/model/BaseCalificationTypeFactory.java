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
 * Base para la factoría de los tipos de calificaciones
 * @author Virginia Martín Agudo
 *
 */
public abstract class BaseCalificationTypeFactory implements CalificationTypeFactory {

	@Override
	public CalificationType getCalificationType(Course course) {	
		return null;
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
	public String getURLSpecificContent() {
		return null;
	}
	
	@Override
	public String getURLEditResult() {
		return "/calification/base_edit_result.jsp";
	}
	
	@Override
	public boolean specificValidations(UploadRequest uploadRequest,PortletResponse portletResponse) {
		return true;
	}
	
	@Override
	public String getPortletId() {
		return LMSPortletKeys.CALIFICATION;
	}
}
