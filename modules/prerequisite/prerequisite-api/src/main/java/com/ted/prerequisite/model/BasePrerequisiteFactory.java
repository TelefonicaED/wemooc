package com.ted.prerequisite.model;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.ted.prerequisite.service.PrerequisiteRelationLocalServiceUtil;

import java.util.List;
import java.util.Locale;

/**
 * Base para la factoría de los métodos de evaluación del curso
 * @author Virginia Martín Agudo
 *
 */
public abstract class BasePrerequisiteFactory implements PrerequisiteFactory {
	
	

	@Override
	public long getClassNameId() {
		return PortalUtil.getClassNameId(getClassName());
	}

	@Override
	public String getIconCssClass() {
		return "prerequisite";
	}

	@Override
	public String getTitle(Locale locale) {
		String modelResourceNamePrefix =
			ResourceActionsUtil.getModelResourceNamePrefix();

		String key = modelResourceNamePrefix.concat(getClassName());

		String value = LanguageUtil.get(locale, key, null);

		if (value == null) {
			value = getClassName();
		}

		return value;
	}
	
	@Override
	public String getDescription(Locale locale) {
		return "";
	}

	@Override
	public String getURLSpecificContent() {
		return null;
	}

	@Override
	public String getPortletId(){
		return null;
	}
	
	@Override
	public void copyPrerequisite(long classNameId, long oldClassPK, long newClassPK, Object...params) {
		
		List<PrerequisiteRelation> prerequisiteRelations = PrerequisiteRelationLocalServiceUtil.getPrerequisiteRelations(classNameId, oldClassPK);
		
		for(PrerequisiteRelation prerequisiteRelation: prerequisiteRelations) {
			PrerequisiteRelationLocalServiceUtil.addPrerequisiteRelation(prerequisiteRelation.getClassNamePrerequisiteId(), classNameId, newClassPK, prerequisiteRelation.getExtraData());
		}
	}

}
