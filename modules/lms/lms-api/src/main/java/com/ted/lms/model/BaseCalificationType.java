package com.ted.lms.model;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.upload.UploadRequest;
import com.ted.lms.registry.CalificationTypeFactoryRegistryUtil;

import java.util.Locale;

import javax.portlet.PortletResponse;

public abstract class BaseCalificationType<T> implements CalificationType<T> {

	@Override
	public String getSuffix() {
		return "";
	}
	
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
	public double getMinValue() {
		return 0;
	}
	
	@Override
	public String setExtraContent(UploadRequest uploadRequest, PortletResponse portletResponse) {
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public CalificationTypeFactory<T> getCalificationTypeFactory(){
		if (calificationTypeFactory != null) {
			return calificationTypeFactory;
		}

		calificationTypeFactory =
			(CalificationTypeFactory<T>)
			CalificationTypeFactoryRegistryUtil.
					getCalificationTypeFactoryByClassName(getClassName());

		return calificationTypeFactory;
	}
	
	private CalificationTypeFactory<T> calificationTypeFactory;

	@Override
	public boolean hasViewPermission(PermissionChecker permissionChecker)
		throws PortalException{
		return true;
	}
}
