package com.ted.lms.model;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import java.util.Locale;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;

import aQute.bnd.annotation.ProviderType;

/**
 * Interfaz para la factoría de actividades del LMS
 * @author Virginia Martín Agudo
 *
 */
@ProviderType
public interface LearningActivityTypeFactory {

	public LearningActivity getLearningActivity(long actId) throws PortalException;

	public LearningActivityType getLearningActivityType(long actId) throws PortalException;

	public LearningActivityType getLearningActivityType(LearningActivity activity, long type) throws PortalException;

	public String getClassName();

	public long getClassNameId();

	public String getPortletId();

	public long getType();

	public String getName(Locale locale);
	
	public String getDescription(Locale locale);
	
	public String getIconCssClass();

	public PortletURL getURLView(
			LiferayPortletResponse liferayPortletResponse,
			WindowState windowState)
		throws PortalException;

	public boolean hasAddPermission(
			PermissionChecker permissionChecker, long groupId, long classTypeId)
		throws Exception;

	public boolean hasPermission(
			PermissionChecker permissionChecker, long actId,
			String actionId)
		throws Exception;

	public boolean isActive(long companyId);
	
	public boolean isActive(long companyId, long groupId);

	public boolean isCategorizable();

	public void setClassName(String className);

	public void setPortletId(String portletId);
}
