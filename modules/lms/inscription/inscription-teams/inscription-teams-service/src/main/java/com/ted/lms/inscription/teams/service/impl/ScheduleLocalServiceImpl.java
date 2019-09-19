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

package com.ted.lms.inscription.teams.service.impl;

import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Team;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.Validator;
import com.ted.lms.inscription.teams.model.Schedule;
import com.ted.lms.inscription.teams.service.base.ScheduleLocalServiceBaseImpl;
import com.ted.lms.model.Course;
import com.ted.lms.model.CourseResult;
import com.ted.lms.service.CourseLocalServiceUtil;
import com.ted.lms.service.CourseResultLocalServiceUtil;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * The implementation of the schedule local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.ted.lms.inscription.teams.service.ScheduleLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ScheduleLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.ted.lms.inscription.teams.model.Schedule",
	service = AopService.class
)
public class ScheduleLocalServiceImpl extends ScheduleLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>com.ted.lms.inscription.teams.service.ScheduleLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.ted.lms.inscription.teams.service.ScheduleLocalServiceUtil</code>.
	 */
	
	public Schedule getScheduleByTeamId(long teamId) {
		return schedulePersistence.fetchByTeamId(teamId);
	}
	
	public Schedule addSchedule (long userId, long teamId, Date startDate, Date endDate, long courseId){
		Schedule scheduledTeam = schedulePersistence.create(counterLocalService.increment(Schedule.class.getName()));
		scheduledTeam.setTeamId(teamId);
		scheduledTeam.setEndDate(endDate);
		scheduledTeam.setStartDate(startDate);
		schedulePersistence.update(scheduledTeam);
		
		updateScheduleUsersDates(userId, scheduledTeam, courseId);
		
		return scheduledTeam;
	}
	
	public void updateScheduleUsersDates(long userId, Schedule schedule, long courseId) {
		//Ponemos las fechas a los usuarios que pertenecen a ese equipo
		List<User> listUserTeams = userLocalService.getTeamUsers(schedule.getTeamId());
		if(Validator.isNotNull(listUserTeams)) {
			for(User user: listUserTeams) {
				updateScheduleUserDates(userId, schedule, courseId, user.getUserId());
			}
		}
	}
	
	public void updateScheduleUserDates(long userId, Schedule schedule, long courseId, long studentId) {
		CourseResult courseResult = CourseResultLocalServiceUtil.fetchCourseResult(courseId, studentId);
		if(courseResult == null) {
			courseResult = CourseResultLocalServiceUtil.addCourseResult(userId, courseId, studentId);
		}
		courseResult.setAllowStartDate(schedule.getStartDate());
		courseResult.setAllowEndDate(schedule.getEndDate());
		CourseResultLocalServiceUtil.updateCourseResult(courseResult);
	}
	
	public void deleteScheduleUsersDates(Schedule schedule, long courseId) {
		List<User> listUserTeams = userLocalService.getTeamUsers(schedule.getTeamId());
		if(Validator.isNotNull(listUserTeams)) {
			for(User user: listUserTeams) {
				deleteScheduleUserDates(schedule, courseId, user.getUserId());
			}
		}
	}
	
	public void deleteScheduleUserDates(Schedule schedule, long courseId, long userId) {
		CourseResult courseResult = CourseResultLocalServiceUtil.fetchCourseResult(courseId, userId);
		if(courseResult != null) {
			courseResult.setAllowStartDate(null);
			courseResult.setAllowEndDate(null);
			CourseResultLocalServiceUtil.updateCourseResult(courseResult);
		}
	}
	
	@Override
	public Schedule updateSchedule(Schedule schedule) {
		schedule = super.updateSchedule(schedule);
		
		try {
			Team team = teamLocalService.getTeam(schedule.getTeamId());
			Course course = CourseLocalServiceUtil.getCourseByGroupCreatedId(team.getGroupId());

			updateScheduleUsersDates(team.getUserId(), schedule, course.getCourseId());
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return schedule;
	}
	
	@Override
	public Schedule addSchedule(Schedule schedule) {
		schedule = super.addSchedule(schedule);
		
		try {
			Team team = teamLocalService.getTeam(schedule.getTeamId());
			Course course = CourseLocalServiceUtil.getCourseByGroupCreatedId(team.getGroupId());
			
			updateScheduleUsersDates(team.getUserId(), schedule, course.getCourseId());
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return schedule;
	}
	
	@Override
	public Schedule deleteSchedule(Schedule schedule) {
		schedule = super.deleteSchedule(schedule);
		
		try {
			Team team = teamLocalService.getTeam(schedule.getTeamId());
			Course course = CourseLocalServiceUtil.getCourseByGroupCreatedId(team.getGroupId());
			
			deleteScheduleUsersDates(schedule, course.getCourseId());
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return schedule;
	}
	
	@Override
	public Schedule deleteSchedule(long scheduleId) {
		Schedule schedule = null;
		try {
			schedule = super.deleteSchedule(scheduleId);

			Team team = teamLocalService.getTeam(schedule.getTeamId());
			Course course = CourseLocalServiceUtil.getCourseByGroupCreatedId(team.getGroupId());
			
			deleteScheduleUsersDates(schedule, course.getCourseId());
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return schedule;
	}
}