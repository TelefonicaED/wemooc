package com.ted.lms.model;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.upload.UploadRequest;
import com.liferay.portal.kernel.xml.Element;

import java.util.HashMap;
import java.util.Locale;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletResponse;

import aQute.bnd.annotation.ProviderType;

/**
 * Interfaz para las actividades del LMS
 * @author Virginia Martín Agudo
 *
 */
@ProviderType
public interface LearningActivityTypeFactory<T> {

	public long getClassNameId();
	public boolean isActive(long companyId);
	public boolean isSelectable();
	public String getClassName();
	public String getType();
}
