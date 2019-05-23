package com.ted.lms.model;

import com.liferay.portal.kernel.exception.PortalException;

import java.util.Locale;

import javax.portlet.ActionRequest;
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
	
	public void setExtraContent(ActionRequest actionRequest) throws PortalException;
	
	public CalificationTypeFactory getCalificationTypeFactory();
	
	public double getMinValue();
	
	public double getMaxValue();
	
	public String getSuffix();
	
	public double getResultBase100(ActionRequest actionRequest) throws PortalException;

}