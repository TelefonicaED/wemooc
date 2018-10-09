package com.ted.lms.internal.exportimport.creation.strategy;

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.ted.lms.model.Module;

import org.osgi.service.component.annotations.Component;

/**
 * 
 * @author Virginia Martín Agudo
 *
 */
@Component(immediate = true, service = ModuleCreationStrategy.class)
public class ModuleCreationStrategyImpl implements ModuleCreationStrategy{
	@Override
	public boolean addGroupPermissions(
			PortletDataContext context, Object moduleObj)
		throws Exception {

		return true;
	}

	@Override
	public boolean addGuestPermissions(
			PortletDataContext context, Object moduleObj)
		throws Exception {

		return false;
	}

	@Override
	public long getAuthorUserId(PortletDataContext context, Object moduleObj)
		throws Exception {

		return ModuleCreationStrategy.USE_DEFAULT_USER_ID_STRATEGY;
	}

	@Override
	public String getTransformedDescription(
			PortletDataContext context, Module newModule)
		throws Exception {

		return ModuleCreationStrategy.MODULE_DESCRIPTION_UNCHANGED;
	}

}
