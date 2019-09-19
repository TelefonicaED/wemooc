package com.ted.postcondition.model;

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.xml.Element;

import javax.portlet.PortletRequest;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Interfaz para las postcondiciones que se ejecutan cuando un usuario ha finalizado un curso
 * @author Virginia Martín Agudo
 *
 */
@ProviderType
public interface Postcondition {
	
	public String getClassName();
	
	public void passed(long userId);
	public void failed(long userId);
	public void completed(long userId);
	
	public void setExtraContent(PortletRequest request) throws PortalException;
	
	public PostconditionFactory getPostconditionFactory();
	
	public String doImportStagedModel(PortletDataContext portletDataContext, Element element);
	public String doExportStagedModel(PortletDataContext portletDataContext, Element element);
	
	public void updatePostconditionCopied(long classNameId, Object ... params);
}
