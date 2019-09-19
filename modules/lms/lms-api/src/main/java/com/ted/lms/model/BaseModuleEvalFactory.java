package com.ted.lms.model;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.upload.UploadRequest;
import com.liferay.portal.kernel.util.PortalUtil;
import com.ted.lms.constants.LMSPortletKeys;
import java.util.Locale;

import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;

/**
 * Base para la factoría de los métodos de evaluación de un módulo
 * @author Virginia Martín Agudo
 *
 */
public abstract class BaseModuleEvalFactory implements ModuleEvalFactory {

	@Override
	public long getClassNameId() {
		return PortalUtil.getClassNameId(getClassName());
	}

	@Override
	public String getIconCssClass() {
		return "module-eval";
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
	public PortletURL getURLSpecificContent(){
		return null;
	}

	@Override
	public String getPortletId(){
		return LMSPortletKeys.MODULES_ADMIN;
	}
	
	public boolean specificValidations(UploadRequest uploadRequest,PortletResponse portletResponse) {
		return true;
	}
}
