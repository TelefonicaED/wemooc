package com.ted.lms.model;

import com.liferay.portal.kernel.upload.UploadRequest;
import java.util.Locale;

import javax.portlet.PortletResponse;

import aQute.bnd.annotation.ProviderType;

/**
 * Interfaz para los tipos de calificación
 * @author Virginia Martín Agudo
 *
 */
@ProviderType
public interface CalificationType {
	
	public String translate(Locale locale, double result);
	
	public double toBase100(String result);
	
	public String getClassName();
	
	public String setExtraContent(UploadRequest uploadRequest, PortletResponse portletResponse);
	
	public CalificationTypeFactory getCalificationTypeFactory();
	
	public double getMinValue();
	
	public double getMaxValue();
	
	public String getSuffix();

}