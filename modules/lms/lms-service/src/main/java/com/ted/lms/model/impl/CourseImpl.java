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

import com.liferay.friendly.url.model.FriendlyURLEntry;
import com.liferay.friendly.url.model.FriendlyURLEntryLocalization;
import com.liferay.friendly.url.service.FriendlyURLEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.MembershipRequestConstants;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.MembershipRequestLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.impl.GroupImpl;
import com.ted.lms.constants.CourseConstants;
import com.ted.lms.constants.LMSActionKeys;
import com.ted.lms.exception.InscriptionException;
import com.ted.lms.model.Course;
import com.ted.lms.model.CourseResult;
import com.ted.lms.security.permission.resource.CoursePermission;
import com.ted.lms.service.CourseResultLocalServiceUtil;
import com.ted.prerequisite.model.Prerequisite;
import com.ted.prerequisite.service.PrerequisiteRelationLocalServiceUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
	private Group _group;
	private JSONObject extraData = null;
	
	public String getFriendlyURL() {
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

		return _group.getFriendlyURL();
	}
	
	private static final Group NULL_GROUP = new GroupImpl();

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
	
	public CourseResult getResultUser(long userId) {
		return CourseResultLocalServiceUtil.getByCourseIdUserId(getCourseId(), userId);
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
	
	public boolean canUnsubscribe(long userId, PermissionChecker permissionChecker) throws PortalException {
		Date now = new Date();
			
		if (GroupLocalServiceUtil.hasUserGroup(userId, getGroupCreatedId()) && getRegistrationStartDate().before(now) && 
				getRegistrationEndDate().after(now) && CoursePermission.contains(permissionChecker, this, LMSActionKeys.REGISTER)) {
			CourseResult courseResult = CourseResultLocalServiceUtil.getByCourseIdUserId(getCourseId(), userId); 
			Group group = GroupLocalServiceUtil.getGroup(getGroupCreatedId());

			return courseResult == null || courseResult.getPassedDate() == null || (group.getType() == GroupConstants.TYPE_SITE_OPEN);
		}else {
			return false;
		}
	}
	
	public boolean canEnroll(long userId, Locale locale, PermissionChecker permissionChecker) throws PortalException, InscriptionException {
		//1.Comprobamos que no esté ya inscrito
		if(!GroupLocalServiceUtil.hasUserGroup(userId, getGroupCreatedId())) {
			Date now = new Date();
			
			/*Date startDate = course.getStartDate();
			Date endDate = course.getEndDate();
			
			if(teamId>0){
				Schedule sch = scheduleLocalService.getScheduleByTeamId(teamId);	
				if(sch!=null){
					startDate = sch.getStartDate();
					endDate = sch.getEndDate();
				}
			}*/
			if(CoursePermission.contains(permissionChecker, this, LMSActionKeys.REGISTER)){
				//2. Fecha actual dentro del periodo de inscripcion
				if((getRegistrationStartDate().before(now) && getRegistrationEndDate().after(now))){
	
					//3.Comprobamos que se cumplan todos los prerequisitos
					List<Prerequisite> listPrerequiste = PrerequisiteRelationLocalServiceUtil.getPrerequisites(PortalUtil.getClassNameId(Course.class.getName()), getCourseId());
					boolean isPassed = true;
					int i = 0;
					while(isPassed && listPrerequiste.size() > i) {
						isPassed = listPrerequiste.get(i).isPassed(userId);
						i++;
					}
					if(isPassed) {
						// 4. El mÃ¡ximo de inscripciones del curso no ha sido superado
						if(getMaxUsers()<=0 || UserLocalServiceUtil.getGroupUsersCount(getGroupCreatedId()) < getMaxUsers()){
							//5. Comprobamos el tipo de inscripción
							Group group = GroupLocalServiceUtil.getGroup(getGroupCreatedId());
							if(group.getType()==GroupConstants.TYPE_SITE_OPEN){
								return true;
							}else{
								if(group.getType()==GroupConstants.TYPE_SITE_RESTRICTED){
									if(!MembershipRequestLocalServiceUtil.hasMembershipRequest(userId, group.getGroupId(), MembershipRequestConstants.STATUS_PENDING)){
										return true;
									}
								}else{
									if(group.getType()==GroupConstants.TYPE_SITE_PRIVATE){
										//Debería lanzar una excepción indicando que es privado y que no se puede
										throw new InscriptionException("site-private", LanguageUtil.get(locale, "inscription.error.site-private"));
									}
								}
							}
						}else {
							throw new InscriptionException("max-users", LanguageUtil.get(locale, "inscription.error.max-users"));
						}
					}else {
						//Debería lanzar una excepción indicando que no se cumplen los prerequisitos
						throw new InscriptionException("prerequisites", LanguageUtil.get(locale, "inscription.error.prerequisites"));
					}
				}else {
					//Debería lanzar una excepción indicando que está en periodo de ejecución
					throw new InscriptionException("registration-dates", LanguageUtil.get(locale, "inscription.error.registration-dates"));
				}
			}else {
				throw new InscriptionException("permission-register", LanguageUtil.get(locale, "inscription.error.permission-register"));
			}
			return false;
		}else {
			return false;
		}
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
	
	@Override
	public String getDescriptionMapAsXML() {
		return LocalizationUtil.updateLocalization(
			getDescriptionMap(), StringPool.BLANK, "Description",
			getDefaultLanguageId());
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

		long classNameId = PortalUtil.getClassNameId(Group.class);

		List<FriendlyURLEntry> friendlyURLEntries = FriendlyURLEntryLocalServiceUtil.getFriendlyURLEntries(getGroupId(), classNameId, getGroupCreatedId());

		if (friendlyURLEntries.isEmpty()) {
			friendlyURLMap.put(LocaleUtil.fromLanguageId(getDefaultLanguageId()),getFriendlyURL());

			return friendlyURLMap;
		}

		FriendlyURLEntry friendlyURLEntry = FriendlyURLEntryLocalServiceUtil.getMainFriendlyURLEntry(classNameId, getGroupCreatedId());

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
}