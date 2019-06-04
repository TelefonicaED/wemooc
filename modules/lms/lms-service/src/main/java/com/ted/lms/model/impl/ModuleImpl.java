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

package com.ted.lms.model.impl;

import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Image;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.Repository;
import com.liferay.portal.kernel.model.impl.VirtualLayout;
import com.liferay.portal.kernel.portlet.PortletLayoutFinder;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletLayoutFinder.Result;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ImageLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.sites.kernel.util.SitesUtil;
import com.ted.lms.constants.LMSConstants;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.Module;
import com.ted.lms.service.LearningActivityLocalServiceUtil;
import com.ted.lms.service.util.DateUtil;
import com.ted.prerequisite.model.Prerequisite;
import com.ted.prerequisite.service.PrerequisiteRelationLocalServiceUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.portlet.PortletMode;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.WindowState;

import aQute.bnd.annotation.ProviderType;

/**
 * The extended model implementation for the Module service. Represents a row in the &quot;LMS_Module&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ted.lms.model.Module} interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class ModuleImpl extends ModuleBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a module model instance should use the {@link com.ted.lms.model.Module} interface instead.
	 */
	public ModuleImpl() {
	}
	
	
	public List<LearningActivity> getRequiredLearningActivities(){
		return LearningActivityLocalServiceUtil.getRequiredLearningActivitiesOfModule(getModuleId());
	}
	
	@Override
	public String getSmallImageType() throws PortalException {
		if ((smallImageType == null) && Validator.isNotNull(getSmallImageId())) {
			Image smallImage = ImageLocalServiceUtil.getImage(
				getSmallImageId());

			smallImageType = smallImage.getType();
		}

		return smallImageType;
	}
	
	@Override
	public void setSmallImageType(String smallImageType) {
		this.smallImageType = smallImageType;
	}
	
	private String smallImageType;
	
	
	@Override
	public List<FileEntry> getImagesFileEntries() throws PortalException {
		return getImagesFileEntries(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	@Override
	public List<FileEntry> getImagesFileEntries(int start, int end)
		throws PortalException {

		return getImagesFileEntries(start, end, null);
	}

	@Override
	public List<FileEntry> getImagesFileEntries(
			int start, int end, OrderByComparator obc)
		throws PortalException {

		long imagesFolderId = getImagesFolderId();

		if (imagesFolderId == DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			return new ArrayList<>();
		}

		return PortletFileRepositoryUtil.getPortletFileEntries(
			getGroupId(), imagesFolderId, WorkflowConstants.STATUS_APPROVED,
			start, end, obc);
	}
	
	@Override
	public long getImagesFolderId() {
		if (_imagesFolderId != DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			return _imagesFolderId;
		}

		Repository repository =
			PortletFileRepositoryUtil.fetchPortletRepository(
				getGroupId(), LMSConstants.SERVICE_NAME);

		if (repository == null) {
			return DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;
		}

		try {
			Folder folder = PortletFileRepositoryUtil.getPortletFolder(
				repository.getRepositoryId(),
				DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
				String.valueOf(getModuleId()));

			_imagesFolderId = folder.getFolderId();
		}
		catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("Unable to get folder for " + getModuleId());
			}
		}

		return _imagesFolderId;
	}
	
	@Override
	public String getDescriptionMapAsXML() {
		return LocalizationUtil.updateLocalization(
			getDescriptionMap(), StringPool.BLANK, "Description",
			getDefaultLanguageId());
	}
	
	public Calendar getStartDateCalendar() {
		Calendar startDate = Calendar.getInstance();
		startDate.setTime(getStartDate());
		return startDate;
	}
	
	public Calendar getEndDateCalendar() {
		Calendar endDate = Calendar.getInstance();
		endDate.setTime(getEndDate());
		return endDate;
	}
	
	@Override
	public void setImagesFolderId(long imagesFolderId) {
		_imagesFolderId = imagesFolderId;
	}
	
	@Override
	public boolean isLocked(long userId, PermissionChecker permissionChecker){
	
		if(!permissionChecker.hasPermission(this.getGroupId(), Module.class.getName(), getModuleId(), "ACCESS")){
			return true;
		}
		
		Date now = new Date();
		
		if((getEndDate()!=null && getEndDate().before(now)) || (getStartDate()!=null && getStartDate().after(now))){
			return true;
		}
		
		List<Prerequisite> listPrerequiste = PrerequisiteRelationLocalServiceUtil.getPrerequisites(PortalUtil.getClassNameId(Module.class.getName()), getModuleId());
		boolean isPassed = true;
		int i = 0;
		while(isPassed && listPrerequiste.size() > i) {
			isPassed = listPrerequiste.get(i).isPassed(userId);
			i++;
		}
		
		return !isPassed;
	}
	
	@Override
	public String getURLView(ThemeDisplay themeDisplay) {

		StringBundler sb = new StringBundler(11);

		sb.append(themeDisplay.getPortalURL());
		sb.append(themeDisplay.getPathMain());
		sb.append("/modules/find_module");
		sb.append("?p_l_id=");
		sb.append(themeDisplay.getPlid());
		sb.append(StringPool.AMPERSAND);
		sb.append("moduleId");
		sb.append(StringPool.EQUAL);
		sb.append(String.valueOf(getModuleId()));
		sb.append(StringPool.AMPERSAND);
		sb.append(Constants.CMD);
		sb.append(StringPool.EQUAL);
		sb.append(Constants.VIEW);

		return PortalUtil.addPreservedParameters(themeDisplay, sb.toString());
	}
	
	@Override
	public String getURLEdit(ThemeDisplay themeDisplay) {

		StringBundler sb = new StringBundler(11);

		sb.append(themeDisplay.getPortalURL());
		sb.append(themeDisplay.getPathMain());
		sb.append("/modules/find_module");
		sb.append("?p_l_id=");
		sb.append(themeDisplay.getPlid());
		sb.append(StringPool.AMPERSAND);
		sb.append("moduleId");
		sb.append(StringPool.EQUAL);
		sb.append(String.valueOf(getModuleId()));
		sb.append(StringPool.AMPERSAND);
		sb.append(Constants.CMD);
		sb.append(StringPool.EQUAL);
		sb.append(Constants.EDIT);

		return PortalUtil.addPreservedParameters(themeDisplay, sb.toString());
	}
	
	public String getURLNewActivity(PortletLayoutFinder activityPortletLayoutFinder, RenderRequest renderRequest, ThemeDisplay themeDisplay) {
		
		String urlNewActivity = "";
		
		try {
			Result result = activityPortletLayoutFinder.find(themeDisplay, themeDisplay.getScopeGroupId());
			
			Layout layout = setTargetLayout(renderRequest, themeDisplay.getScopeGroupId(), result.getPlid());
			
			PortletURL portletURL = PortletURLFactoryUtil.create(renderRequest, result.getPortletId(), layout, PortletRequest.RENDER_PHASE);
			portletURL.setParameter("mvcRenderCommandName", "/activities/add_activity");
			portletURL.setParameter("moduleId", String.valueOf(getModuleId()));
			portletURL.setPortletMode(PortletMode.VIEW);
			portletURL.setWindowState(WindowState.NORMAL);
			
			urlNewActivity = portletURL.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return urlNewActivity;
	}
	
	protected static Layout setTargetLayout(RenderRequest request, long groupId, long plid)throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);

		PermissionChecker permissionChecker = themeDisplay.getPermissionChecker();

		Group group = GroupLocalServiceUtil.getGroup(groupId);
		Layout layout = LayoutLocalServiceUtil.getLayout(plid);

		if ((groupId == layout.getGroupId()) || (group.getParentGroupId() == layout.getGroupId()) ||
			(layout.isPrivateLayout() && !SitesUtil.isUserGroupLayoutSetViewable(permissionChecker, layout.getGroup()))) {

			return layout;
		}

		layout = new VirtualLayout(layout, group);

		return layout;
	}
	
	public String getStartDateFormat(Locale locale, TimeZone timeZone) {
		return DateUtil.getSimpleDateFormatPattern(locale, timeZone).format(getStartDate());
	}
	
	public String getEndDateFormat(Locale locale, TimeZone timeZone) {
		return DateUtil.getSimpleDateFormatPattern(locale, timeZone).format(getEndDate());
	}
	
	private long _imagesFolderId;
	
	private static final Log log = LogFactoryUtil.getLog(ModuleImpl.class);
}