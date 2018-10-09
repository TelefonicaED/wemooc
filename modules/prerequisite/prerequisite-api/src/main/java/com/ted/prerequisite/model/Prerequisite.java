package com.ted.prerequisite.model;

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.upload.UploadRequest;
import com.liferay.portal.kernel.xml.Element;

import javax.portlet.PortletRequest;
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
	
	public void setExtraContent(PortletRequest request) throws PortalException;
	
	public PrerequisiteFactory getPrerequisiteFactory();
	
	public String doImportStagedModel(PortletDataContext portletDataContext, Element element);
	public String doExportStagedModel(PortletDataContext portletDataContext, Element element);

}
