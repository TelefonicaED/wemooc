package com.ted.lms.model;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.upload.UploadRequest;
import java.util.Locale;

import javax.portlet.PortletResponse;

import aQute.bnd.annotation.ProviderType;

/**
 * Interfaz para las actividades del LMS
 * @author Virginia Martín Agudo
 *
 */
@ProviderType
public interface CalificationType<T> {

	public int getTypeId();

	public String getSuffix();
	
	public String translate(Locale locale, double result);
	
	public double toBase100(String result);
	
	public double getMinValue();
	
	public double getMaxValue();
	
	public String getClassName();
	
	public String setExtraContent(UploadRequest uploadRequest, PortletResponse portletResponse);
	
	public CalificationTypeFactory<T> getCalificationTypeFactory();

	public boolean hasViewPermission(PermissionChecker permissionChecker)
		throws PortalException;

}