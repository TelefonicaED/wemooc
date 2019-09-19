package com.ted.prerequisite.model;

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.xml.Element;
import com.ted.prerequisite.service.PrerequisiteRelationLocalService;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Interfaz para las postcondiciones que se ejecutan cuando un usuario ha finalizado un curso
 * @author Virginia Martín Agudo
 *
 */
@ProviderType
public abstract class BasePrerequisite implements Prerequisite{
	
	protected PrerequisiteRelation prerequisiteRelation;
	protected long classNameId;
	protected long classPK;
	protected PrerequisiteFactory prerequisiteFactory;
	protected PrerequisiteRelationLocalService prerequisiteRelationLocalService;
	
	public BasePrerequisite(PrerequisiteRelation prerequisiteRelation, PrerequisiteRelationLocalService prerequisiteRelationLocalService) {
		this.prerequisiteRelation = prerequisiteRelation;
		this.prerequisiteRelationLocalService = prerequisiteRelationLocalService;
		this.classNameId = prerequisiteRelation.getClassNameId();
		this.classPK = prerequisiteRelation.getClassPK();
	}
	
	@Override
	public PrerequisiteRelation getPrerequisiteRelation() {
		return prerequisiteRelation;
	}
	
	@Override
	public String doExportStagedModel(PortletDataContext portletDataContext, Element element) {
		return null;
	}
	
	
}
