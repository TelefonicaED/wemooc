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

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.document.library.kernel.util.comparator.RepositoryModelTitleComparator;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.json.JSONObjectImpl;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portlet.asset.model.impl.AssetEntryImpl;
import com.ted.lms.constants.LMSActionKeys;
import com.ted.lms.constants.LearningActivityConstants;
import com.ted.lms.model.Course;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityTypeFactory;
import com.ted.lms.model.Module;
import com.ted.lms.registry.LearningActivityTypeFactoryRegistryUtil;
import com.ted.lms.security.permission.resource.LMSPermission;
import com.ted.lms.security.permission.resource.LearningActivityPermission;
import com.ted.lms.service.ModuleLocalServiceUtil;
import com.ted.lms.service.util.LearningActivityAttachmentsUtil;
import com.ted.prerequisite.model.Prerequisite;
import com.ted.prerequisite.model.PrerequisiteRelation;
import com.ted.prerequisite.service.PrerequisiteRelationLocalServiceUtil;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model implementation for the LearningActivity service. Represents a row in the &quot;LMS_LearningActivity&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.ted.lms.model.LearningActivity<code> interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class LearningActivityImpl extends LearningActivityBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a learning activity model instance should use the {@link com.ted.lms.model.LearningActivity} interface instead.
	 */
	private static final Log log = LogFactoryUtil.getLog(LearningActivityImpl.class);
	
	private static final AssetEntry NULL_ASSET_ENTRY = new AssetEntryImpl();
	private static final JSONObject NULL_JSON_OBJECT = new JSONObjectImpl();
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a learning activity model instance should use the {@link com.ted.lms.model.LearningActivity} interface instead.
	 */
	//Se omite en la exportación

	private AssetEntry assetEntry;
	private JSONObject extraContentJSON;
	private long attachmentsFolderId;
	private long teamId;
	private LearningActivityTypeFactory activityTypeFactory;
	{
		extraContentJSON = NULL_JSON_OBJECT;
		teamId = -1;
		activityTypeFactory = null;
		attachmentsFolderId = -1;
	}
	
	public LearningActivityImpl() {
	}
	
	public JSONObject getExtraContentJSON() {
		if(extraContentJSON == NULL_JSON_OBJECT) {
			try {
				extraContentJSON = JSONFactoryUtil.createJSONObject(getExtraContent());
			} catch (JSONException e) {
				e.printStackTrace();
				extraContentJSON = JSONFactoryUtil.createJSONObject();
			}
		}
		return extraContentJSON;
	}
	
	public void setExtraContentJSON(JSONObject extraContent) {
		this.extraContentJSON = extraContent;
	}
	
	@Override
	public void setExtraContent(String activityExtraContent) {
		if(Validator.isNotNull(activityExtraContent)) {
			try {
				extraContentJSON = JSONFactoryUtil.createJSONObject(activityExtraContent);
			} catch (JSONException e) {
				extraContentJSON = JSONFactoryUtil.createJSONObject();
			}
		}else {
			extraContentJSON = JSONFactoryUtil.createJSONObject();
		}
		
		super.setExtraContent(activityExtraContent);
	}
	
	public AssetEntry getAssetEntry() {
		if (assetEntry == NULL_ASSET_ENTRY) {
			return null;
		}

		if (assetEntry == null) {
			AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry(PortalUtil.getClassNameId(Module.class), getModuleId());

			if (assetEntry == null) {
				this.assetEntry = NULL_ASSET_ENTRY;
			}
			else {
				this.assetEntry = assetEntry;
			}
		}

		return assetEntry;
	}
	
	@Override
	public void addExtraContentJSON(String key, Object value) {
		JSONObject extraContent = getExtraContentJSON();
		extraContent.put(key, value);
		this.extraContentJSON = extraContent;
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
	
	public long getTeamId() {
		if(teamId == -1) {
			JSONObject extraContentJSON = getExtraContentJSON();
			if(extraContentJSON != null) {
				teamId = extraContentJSON.getLong(LearningActivityConstants.JSON_TEAM);
			}else {
				teamId = 0;
			}
		}
		
		return teamId;
	}
	
	public LearningActivityTypeFactory getLearningActivityTypeFactory() {
		if(activityTypeFactory == null) {
			activityTypeFactory = LearningActivityTypeFactoryRegistryUtil.getLearningActivityTypeFactoryByType(getTypeId());
		}
		
		return activityTypeFactory;
	}
	
	@Override
	public String getURLView(ThemeDisplay themeDisplay) {

		StringBundler sb = new StringBundler(11);

		sb.append(themeDisplay.getPortalURL());
		sb.append(themeDisplay.getPathMain());
		sb.append("/activities/find_activity");
		sb.append("?p_l_id=");
		sb.append(themeDisplay.getPlid());
		sb.append(StringPool.AMPERSAND);
		sb.append("actId");
		sb.append(StringPool.EQUAL);
		sb.append(String.valueOf(getActId()));
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
		sb.append("/activities/find_activity");
		sb.append("?p_l_id=");
		sb.append(themeDisplay.getPlid());
		sb.append(StringPool.AMPERSAND);
		sb.append("actId");
		sb.append(StringPool.EQUAL);
		sb.append(String.valueOf(getActId()));
		sb.append(StringPool.AMPERSAND);
		sb.append(Constants.CMD);
		sb.append(StringPool.EQUAL);
		sb.append(Constants.EDIT);

		return PortalUtil.addPreservedParameters(themeDisplay, sb.toString());
	}
	
	@Override
	public String getDescriptionMapAsXML() {
		return LocalizationUtil.updateLocalization(
			getDescriptionMap(), StringPool.BLANK, "Description",
			getDefaultLanguageId());
	}
	
	/**
	 * Comprueba si se puede accceder a una actividad aunque esté bloqueada
	 * @param viewActivityFinish Si la actividad deja acceder coon el modo observador
	 * @param user Usuario que accede a la actividad
	 * @param permissionChecker permisos del usuario
	 * @return true si puede acceder
	 * @throws PortalException 
	 */
	
	public boolean canAccess(boolean viewActivityFinish, User user, PermissionChecker permissionChecker, 
			Course course) throws PortalException{
		
		if(LMSPermission.contains(permissionChecker, this.getGroupId(), LMSActionKeys.ACCESSLOCK)){
			log.debug("tienes acceso a los cursos bloqueados");
			return true;
		}
		
		log.debug("viewActivityFinish: " + viewActivityFinish);
		log.debug("course.hasPermissionAccessCourseFinished(user.getUserId()): " + course.hasPermissionAccessCourseFinished(user.getUserId()));
		
		if(viewActivityFinish && course.hasPermissionAccessCourseFinished(user.getUserId())){
			return true;
		}

		//Primero comprobamos bloqueo de curso
		log.debug("course.isLocked(user, permissionChecker): " + course.isLocked(user, permissionChecker));
		if(!course.isLocked(user, permissionChecker)){
			
			//Ahora comprobamos que no tengas bloqueado el m�dulo y si no lo tengo bloqueado ya comprobamos los bloqueos de la actividad
			Module module = ModuleLocalServiceUtil.getModule(this.getModuleId());
			
			log.debug("module.isLocked(user.getUserId(), permissionChecker): " + module.isLocked(user.getUserId(), permissionChecker));
			log.debug("isLocked(user, permissionChecker): " + isLocked(user, permissionChecker));
			
			log.debug("LearningActivityPermission.contains(permissionChecker, this, ActionKeys.UPDATE): " + LearningActivityPermission.contains(permissionChecker, this, ActionKeys.UPDATE));
			
			if((!module.isLocked(user.getUserId(), permissionChecker) && !isLocked(user, permissionChecker))
					|| LearningActivityPermission.contains(permissionChecker, this, ActionKeys.UPDATE)){
				return true;
			}
		}
		return false;
		
	}

	/**
	 * Comprueba que la actividad esté bloqueda para el usuario para realizarla
	 */
	public boolean isLocked(User user, PermissionChecker permissionChecker) throws PortalException{
		if(!LearningActivityPermission.contains(permissionChecker, this, ActionKeys.VIEW)){
			return true;
		}
		
		Date now = new Date();
		
		if((getEndDate()!=null && getEndDate().before(now)) || (getStartDate()!=null && getStartDate().after(now))){
			return true;
		}
		
		List<Prerequisite> listPrerequiste = PrerequisiteRelationLocalServiceUtil.getPrerequisites(
				PortalUtil.getClassNameId(LearningActivity.class.getName()), getActId());
		int i = 0;
		while(listPrerequiste.size() > i) {
			if(!listPrerequiste.get(i).isPassed(user.getUserId())) {
				return true;
			}
			i++;
		}
		
		return false;			
	}
	
	public PrerequisiteRelation getPrerequisiteRelation(long classNameFactoryId) {
		List<PrerequisiteRelation> prerequisiteRelations = PrerequisiteRelationLocalServiceUtil.getPrerequisiteRelation(classNameFactoryId, PortalUtil.getClassNameId(LearningActivity.class), getActId());
		PrerequisiteRelation prerequisiteRelation = null;
		if(prerequisiteRelations != null && prerequisiteRelations.size() > 0) {
			prerequisiteRelation = prerequisiteRelations.get(0);
		}
		
		return prerequisiteRelation;
	}
	
	@Override
	public List<FileEntry> getAttachmentsFileEntries() throws PortalException {
		return PortletFileRepositoryUtil.getPortletFileEntries(
			getGroupId(), getAttachmentsFolderId(),
			WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, new RepositoryModelTitleComparator<>(true));
	}
	
	@Override
	public long getAttachmentsFolderId() throws PortalException {
		if (attachmentsFolderId > 0) {
			return attachmentsFolderId;
		}

		attachmentsFolderId = LearningActivityAttachmentsUtil.getFolderId(getGroupId(), getUserId(), getActId());

		return attachmentsFolderId;
	}

}