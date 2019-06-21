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

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.friendly.url.model.FriendlyURLEntry;
import com.liferay.friendly.url.model.FriendlyURLEntryLocalization;
import com.liferay.friendly.url.service.FriendlyURLEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.impl.GroupImpl;
import com.liferay.portal.model.impl.LayoutSetImpl;
import com.liferay.portlet.asset.model.impl.AssetEntryImpl;
import com.ted.lms.constants.CourseConstants;
import com.ted.lms.exception.InscriptionException;
import com.ted.lms.exception.NoSuchCourseResultException;
import com.ted.lms.model.Course;
import com.ted.lms.model.CourseResult;
import com.ted.lms.model.Module;
import com.ted.lms.service.CourseLocalServiceUtil;
import com.ted.lms.service.CourseResultLocalServiceUtil;
import com.ted.lms.service.ModuleLocalServiceUtil;
import com.ted.lms.service.StudentLocalServiceUtil;
import com.ted.lms.service.util.DateUtil;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import aQute.bnd.annotation.ProviderType;

/**
 * The extended model implementation for the Course service. Represents a row in the &quot;LMS_Course&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ted.lms.model.Course} interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class CourseImpl extends CourseBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a course model instance should use the {@link com.ted.lms.model.Course} interface instead.
	 */
	
	private static Log log = LogFactoryUtil.getLog(CourseImpl.class);
	
	private Group _group;
	private JSONObject extraData = null;
	private LayoutSet layoutSet;
	private AssetEntry assetEntry;
	
	public String getFriendlyURL() {
		return "/web" + getGroup().getFriendlyURL();
	}
	
	private static final Group NULL_GROUP = new GroupImpl();
	private static final LayoutSet NULL_LAYOUT_SET = new LayoutSetImpl();
	private static final AssetEntry NULL_ASSET_ENTRY = new AssetEntryImpl();
	
	public int getTypeSite() {
		return getGroup().getType();
	}

	@Override
	public JSONObject getCourseExtraDataJSON() {
		if(extraData == null) {
			try {
				extraData = JSONFactoryUtil.createJSONObject(getCourseExtraData());
			} catch (JSONException e) {
				e.printStackTrace();
				extraData = JSONFactoryUtil.createJSONObject();
			}
		}
		return extraData;
	}
	
	@Override
	public void setCourseExtraData(String courseExtraData) {
		try {
			extraData = JSONFactoryUtil.createJSONObject(courseExtraData);
		} catch (JSONException e) {
			extraData = JSONFactoryUtil.createJSONObject();
		}
		
		super.setCourseExtraData(courseExtraData);
	}
	
	@Override
	public void addCourseExtraDataJSON(String key, Object value) {
		JSONObject extraData = getCourseExtraDataJSON();
		extraData.put(key, value);
		this.extraData = extraData;
	}
	
	public JSONObject getCourseEvalJSON() {
		return getCourseExtraDataJSON().getJSONObject(CourseConstants.JSON_COURSE_EVAL);
	}
	
	public boolean isRegistredUser(long userId) {
		return GroupLocalServiceUtil.hasUserGroup(userId,getGroupCreatedId());
	}
	
	public CourseResult getResultUser(long userId) throws NoSuchCourseResultException {
		return CourseResultLocalServiceUtil.getCourseResult(getCourseId(), userId);
	}
	
	public Group getGroup() {
		if (_group == NULL_GROUP) {
			return null;
		}

		if (_group == null) {
			Group group = GroupLocalServiceUtil.fetchGroup(getGroupCreatedId());

			if (group == null) {
				_group = NULL_GROUP;
			}
			else {
				_group = group;
			}
		}

		return _group;
	}
	
	public AssetEntry getAssetEntry() {
		if (assetEntry == NULL_ASSET_ENTRY) {
			return null;
		}

		if (assetEntry == null) {
			AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry(PortalUtil.getClassNameId(Course.class), getCourseId());

			if (assetEntry == null) {
				this.assetEntry = NULL_ASSET_ENTRY;
			}
			else {
				this.assetEntry = assetEntry;
			}
		}

		return assetEntry;
	}
	
	public LayoutSet getLayoutSet() {
		if (layoutSet == NULL_LAYOUT_SET) {
			return null;
		}

		if (layoutSet == null) {
			LayoutSet layoutSet = LayoutSetLocalServiceUtil.fetchLayoutSet(getGroupCreatedId(), false);

			if (layoutSet == null) {
				this.layoutSet = NULL_LAYOUT_SET;
			}
			else {
				this.layoutSet = layoutSet;
			}
		}

		return layoutSet;
	}
	
	public boolean canUnsubscribe(long userId, PermissionChecker permissionChecker) throws PortalException {
		return StudentLocalServiceUtil.canUnsubscribe(this, userId, permissionChecker);
	}
	
	public boolean canEnroll(long userId, Locale locale, PermissionChecker permissionChecker) throws PortalException, InscriptionException {
		return StudentLocalServiceUtil.canEnroll(this, userId, locale, permissionChecker);
	}
	
	public Calendar getExecutionStartDateCalendar() {
		Calendar startDate = Calendar.getInstance();
		startDate.setTime(getExecutionStartDate());
		return startDate;
	}
	
	public Calendar getExecutionEndDateCalendar() {
		Calendar endDate = Calendar.getInstance();
		endDate.setTime(getExecutionEndDate());
		return endDate;
	}
	
	public String getExecutionStartDateFormat(Locale locale, TimeZone timeZone) {
		return DateUtil.getSimpleDateFormatPattern(locale, timeZone).format(getExecutionStartDate());
	}
	
	public String getExecutionEndDateFormat(Locale locale, TimeZone timeZone) {
		return DateUtil.getSimpleDateFormatPattern(locale, timeZone).format(getExecutionEndDate());
	}
	
	@Override
	public String getDescriptionMapAsXML() {
		return LocalizationUtil.updateLocalization(
			getDescriptionMap(), StringPool.BLANK, "Description",
			getDefaultLanguageId());
	}
	
	@Override
	public String getWelcomeMsgMapAsXML() {
		return LocalizationUtil.updateLocalization(
			getWelcomeMsgMap(), StringPool.BLANK, "WelcomeMsg",
			getDefaultLanguageId());
	}
	
	@Override
	public String getGoodbyeMsgMapAsXML() {
		return LocalizationUtil.updateLocalization(
			getGoodbyeMsgMap(), StringPool.BLANK, "GoodbyeMsg",
			getDefaultLanguageId());
	}
	
	@Override 
	public String getDeniedInscriptionMsgMapAsXML() {
		return LocalizationUtil.updateLocalization(getDeniedInscriptionMsgMap(), StringPool.BLANK, "DeniedInscriptionMsg", getDefaultLanguageId());
	}
	
	@Override
	public String getFriendlyURLsXML() throws PortalException {
		Map<Locale, String> friendlyURLMap = getFriendlyURLMap();

		return LocalizationUtil.updateLocalization(
			friendlyURLMap, StringPool.BLANK, "FriendlyURL",
			LocaleUtil.toLanguageId(LocaleUtil.getSiteDefault()));
	}
	
	@Override
	public Map<Locale, String> getFriendlyURLMap() throws PortalException {
		Map<Locale, String> friendlyURLMap = new HashMap<>();

		long classNameId = PortalUtil.getClassNameId(Course.class);

		List<FriendlyURLEntry> friendlyURLEntries = FriendlyURLEntryLocalServiceUtil.getFriendlyURLEntries(getGroupId(), classNameId, getCourseId());

		if (friendlyURLEntries.isEmpty()) {
			
			friendlyURLMap.put(LocaleUtil.fromLanguageId(getDefaultLanguageId()),getFriendlyURL().substring(getFriendlyURL().lastIndexOf("/")));

			return friendlyURLMap;
		}

		FriendlyURLEntry friendlyURLEntry = FriendlyURLEntryLocalServiceUtil.getMainFriendlyURLEntry(classNameId, getCourseId());

		List<FriendlyURLEntryLocalization> friendlyURLEntryLocalizations = FriendlyURLEntryLocalServiceUtil.getFriendlyURLEntryLocalizations(friendlyURLEntry.getFriendlyURLEntryId());

		for (FriendlyURLEntryLocalization friendlyURLEntryLocalization : friendlyURLEntryLocalizations) {

			Locale locale = LocaleUtil.fromLanguageId(friendlyURLEntryLocalization.getLanguageId());
			friendlyURLMap.put(locale, friendlyURLEntryLocalization.getUrlTitle());
		}

		Locale defaultSiteLocale = LocaleUtil.getSiteDefault();

		if (Validator.isNull(friendlyURLMap.get(defaultSiteLocale))) {
			Locale defaultLocale = LocaleUtil.fromLanguageId(getDefaultLanguageId());

			friendlyURLMap.put(defaultSiteLocale, friendlyURLMap.get(defaultLocale));
		}

		return friendlyURLMap;
	}
	
	@Override
	public boolean isTypeSiteOpen() {
		return getGroup().getType() == GroupConstants.TYPE_SITE_OPEN;
	}
	
	@Override
	public boolean isTypeSiteRestricted() {
		return getGroup().getType() == GroupConstants.TYPE_SITE_RESTRICTED;
	}
	
	@Override
	public boolean isTypeSitePrivate() {
		return getGroup().getType() == GroupConstants.TYPE_SITE_PRIVATE; 
	}
	
	@Override 
	public long getLayoutSetPrototypeId() {
		long layoutSetPrototypeId = 0;
		LayoutSet layoutSet = getLayoutSet();
		
		if(layoutSet != null && layoutSet != NULL_LAYOUT_SET) {
			try {
				layoutSetPrototypeId = layoutSet.getLayoutSetPrototypeId();
			} catch (PortalException e) {
				e.printStackTrace();
			}
		}
		return layoutSetPrototypeId;
	}
	
	public boolean isLocked(User user, PermissionChecker permissionChecker){
		
		log.debug("CourseImpl::isLocked::isApproved:" + isApproved());
		
		//Si el curso est� cerrado
		if(!isApproved()){
			return true;
		}
		
		//Si perteneces a la comunidad
	
			if(!UserLocalServiceUtil.hasGroupUser(this.getGroupCreatedId(), user.getUserId())){
				log.debug("CourseImpl::isLocked::hasGroupUser:" + false);
				return true;
			}

		log.debug("CourseImpl::isLocked::hasGroupUser:" + true);
		
		//Si tienes permiso para acceder al curso
		if(!permissionChecker.hasPermission(this.getGroupCreatedId(), Course.class.getName(), this.getCourseId() , ActionKeys.ACCESS)){
			log.debug("CourseImpl::isLocked::hasPermissionAccess:" + false);
			return true;
		}
		log.debug("CourseImpl::isLocked::hasPermissionAccess:" + true);

		Date now = new Date();
		
		if(getExecutionStartDate().after(now) || getExecutionEndDate().before(now)){
			return true;
		}
		
		//Comprobamos si tiene fechas para realizar el curso
		CourseResult courseResult = CourseResultLocalServiceUtil.fetchCourseResult(getCourseId(), user.getUserId());
		
        if(courseResult!=null && ((courseResult.getAllowEndDate()!=null && courseResult.getAllowEndDate().before(now)) 
        							||(courseResult.getAllowStartDate()!=null && courseResult.getAllowStartDate().after(now)))){
        	log.debug("CourseImpl::isLocked::allowdates::startDate:" + courseResult.getAllowStartDate());
        	log.debug("CourseImpl::isLocked::allowdates::endDate:" + courseResult.getAllowEndDate());
			return true;
		}
        
        log.debug("CourseImpl::isLocked::allowdates:" + true);
		
		return false;
	}
	
	public boolean hasPermissionAccessCourseFinished(long userId) throws PortalException {
		log.debug(":::hasPermissionAccessCourseFinished:::companyId: " + this.getCompanyId() + " - courseId: "+ this.getCourseId() + " - userId: " + userId);
		
		if(!CourseLocalServiceUtil.getAllowAccessToCompletedCourses(getCompanyId())){
			return false;
		}
		
		log.debug(":::hasPermissionAccessCourseFinished:::lmsPrefs allowAccessToCompletedCourses ");
		
		Date now = new Date();
		
		if(log.isDebugEnabled()){
			log.debug(":::hasPermissionAccessCourseFinished:::executionEndDate: " + this.getExecutionEndDate());
		}
		
		if(this.getExecutionEndDate() != null && now.after(this.getExecutionEndDate())){
			return true;
		}
		
		Date lastModuleDate = null;
		
		//Ahora comprobamos si se cumple alguna de las otras tres condiciones
		for(Module module:ModuleLocalServiceUtil.findAllInGroup(this.getGroupCreatedId())){
			if(lastModuleDate==null){
				lastModuleDate=module.getEndDate();
			} else if(module.getEndDate()!=null && lastModuleDate.before(module.getEndDate())){
				lastModuleDate=module.getEndDate();
			}
		}	
		
		log.debug(":::hasPermissionAccessCourseFinished:::lastModuleDate: " + lastModuleDate);
		
		if(lastModuleDate != null && lastModuleDate.before(now)){
			log.debug(":::hasPermissionAccessCourseFinished:::lastModuleDateBefore: " + lastModuleDate != null && lastModuleDate.before(now));
			return true;
		}
		
		//Ahora comprobamos la condici�n de allowFinishDate
		CourseResult courseResult = CourseResultLocalServiceUtil.fetchCourseResult(this.getCourseId(), userId);
		
		if(courseResult != null){
			log.debug(":::hasPermissionAccessCourseFinished:::courseResult allowFinishDate: " + courseResult.getAllowEndDate());
		}
		
		if(courseResult != null && courseResult.getAllowEndDate() != null && courseResult.getAllowEndDate().before(now)){
			log.debug(":::hasPermissionAccessCourseFinished:::courseResult allowFinishDate pasada ");
			return true;
		}
		
		//Ahora comprobamos que lo haya finalizado y que no tenga intentos
		if(courseResult == null || courseResult.getPassedDate() == null){
			return false;
		}
		
		log.debug(":::hasPermissionAccessCourseFinished:::courseResult passedDate: " + courseResult.getPassedDate());
		
		return !CourseResultLocalServiceUtil.hasUserTries(this.getCourseId(), userId);
	}

	
	public long getCourseTypeId() {
		AssetEntry assetEntry = getAssetEntry();
		return assetEntry.getClassTypeId();
	}
	
	public int getCountEditions() {
		return CourseLocalServiceUtil.countCourses(getCompanyId(), null, null, null, getCourseId(), 0, null);
	}

}