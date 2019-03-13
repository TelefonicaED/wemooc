package com.ted.lms.model;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.upload.UploadRequest;
import com.liferay.portal.kernel.util.PortalUtil;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.service.CourseResultLocalService;
import com.ted.lms.service.LearningActivityLocalService;
import com.ted.lms.service.LearningActivityResultLocalService;
import com.ted.lms.service.LearningActivityTryLocalService;
import com.ted.lms.service.ModuleLocalService;
import com.ted.lms.service.ModuleResultLocalService;

import java.util.Locale;

import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;
import org.osgi.service.component.annotations.Reference;

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
