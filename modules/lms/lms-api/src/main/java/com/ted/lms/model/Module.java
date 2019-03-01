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

package com.ted.lms.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the Module service. Represents a row in the &quot;LMS_Module&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ModuleModel
 * @see com.ted.lms.model.impl.ModuleImpl
 * @see com.ted.lms.model.impl.ModuleModelImpl
 * @generated
 */
@ImplementationClassName("com.ted.lms.model.impl.ModuleImpl")
@ProviderType
public interface Module extends ModuleModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.ted.lms.model.impl.ModuleImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Module, Long> MODULE_ID_ACCESSOR = new Accessor<Module, Long>() {
			@Override
			public Long get(Module module) {
				return module.getModuleId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Module> getTypeClass() {
				return Module.class;
			}
		};

	public java.util.List<LearningActivity> getRequiredLearningActivities();

	public String getSmallImageType()
		throws com.liferay.portal.kernel.exception.PortalException;

	public void setSmallImageType(String smallImageType);

	public java.util.List<com.liferay.portal.kernel.repository.model.FileEntry> getImagesFileEntries()
		throws com.liferay.portal.kernel.exception.PortalException;

	public java.util.List<com.liferay.portal.kernel.repository.model.FileEntry> getImagesFileEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException;

	public java.util.List<com.liferay.portal.kernel.repository.model.FileEntry> getImagesFileEntries(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException;

	public long getImagesFolderId();

	public String getDescriptionMapAsXML();

	public java.util.Calendar getStartDateCalendar();

	public java.util.Calendar getEndDateCalendar();

	public void setImagesFolderId(long imagesFolderId);

	public boolean isLocked(long userId,
		com.liferay.portal.kernel.security.permission.PermissionChecker permissionChecker);

	public String getURLView(
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay);

	public String getURLEdit(
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay);

	public String getURLNewActivity(
		com.liferay.portal.kernel.portlet.PortletLayoutFinder activityPortletLayoutFinder,
		javax.portlet.RenderRequest renderRequest,
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay);

	public String getStartDateFormat(java.util.Locale locale,
		java.util.TimeZone timeZone);

	public String getEndDateFormat(java.util.Locale locale,
		java.util.TimeZone timeZone);
}