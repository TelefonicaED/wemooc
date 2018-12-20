package com.ted.lms.web.internal.custom.attributes;

import com.liferay.expando.kernel.model.BaseCustomAttributesDisplay;
import com.liferay.expando.kernel.model.CustomAttributesDisplay;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.Module;

import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
		property = "javax.portlet.name=" + LMSPortletKeys.MODULES_ADMIN,
		service = CustomAttributesDisplay.class
	)
public class ModuleCustomAttributesDisplay extends BaseCustomAttributesDisplay {
	@Override
	public String getClassName() {
		return Module.class.getName(); 
	}
}
