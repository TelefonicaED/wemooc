package com.ted.lms.model;

import com.liferay.portal.kernel.upload.UploadRequest;
import com.ted.lms.registry.CalificationTypeFactoryRegistryUtil;

import java.util.Locale;

import javax.portlet.PortletResponse;

/**
 * Base para los tipos de calificaciones
 * @author Virginia Martín Agudo
 *
 */
public abstract class BaseCalificationType implements CalificationType{
	
	@Override
	public String translate(Locale locale, double result) {
		return String.valueOf(result);
	}
	
	@Override
	public double toBase100(String result) {
		try {
			return Double.parseDouble(result);
		}catch (NumberFormatException e) {
			return 0;
		}
	}
	
	@Override
	public String setExtraContent(UploadRequest uploadRequest, PortletResponse portletResponse) {
		return null;
	}
	
	@Override
	public String getSuffix() {
		return "";
	}
	
	@Override
	public double getMinValue() {
		return 0;
	}
	
	@Override
	public CalificationTypeFactory getCalificationTypeFactory(){
		if (calificationTypeFactory != null) {
			return calificationTypeFactory;
		}

		calificationTypeFactory =
			(CalificationTypeFactory)
			CalificationTypeFactoryRegistryUtil.
					getCalificationTypeFactoryByClassName(getClassName());

		return calificationTypeFactory;
	}
	
	private CalificationTypeFactory calificationTypeFactory;
}
