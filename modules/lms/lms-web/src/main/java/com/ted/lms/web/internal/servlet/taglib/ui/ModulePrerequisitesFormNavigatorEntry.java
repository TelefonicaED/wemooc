/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.ted.lms.web.internal.servlet.taglib.ui;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.servlet.taglib.ui.FormNavigatorEntry;
import com.liferay.portal.kernel.util.Validator;
import com.ted.lms.model.Module;
import com.ted.lms.util.LMSPrefsPropsValues;

import javax.servlet.ServletContext;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(
	property = "form.navigator.entry.order:Integer=10",
	service = FormNavigatorEntry.class
)
public class ModulePrerequisitesFormNavigatorEntry extends BaseModuleFormNavigatorEntry {

	@Override
	public String getKey() {
		return "prerequisites";
	}

	@Override
	@Reference(
		target = "(osgi.web.symbolicname=com.ted.lms.web)", unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);
	}

	@Override
	protected String getJspPath() {
		return "/module/prerequisites.jsp";
	}
	
	@Override
	public boolean isVisible(User user, Module module) {
		String[] prerequisitesOfModule = LMSPrefsPropsValues.getPrerequisitesOfModule(user.getCompanyId());
		return prerequisitesOfModule != null && prerequisitesOfModule.length > 0;
	}

}