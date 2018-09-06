package com.ted.prerequisite.model;

import com.liferay.portal.kernel.upload.UploadRequest;

import javax.portlet.PortletResponse;

import aQute.bnd.annotation.ProviderType;

/**
 * Interfaz para las postcondiciones que se ejecutan cuando un usuario ha finalizado un curso
 * @author Virginia Martín Agudo
 *
 */
@ProviderType
public interface Prerequisite {
	
	public String getClassName();
	
	public boolean isPassed(long userId);
	
	public void setExtraContent(UploadRequest uploadRequest, PortletResponse portletResponse);
	
	public PrerequisiteFactory getPrerequisiteFactory();

}
