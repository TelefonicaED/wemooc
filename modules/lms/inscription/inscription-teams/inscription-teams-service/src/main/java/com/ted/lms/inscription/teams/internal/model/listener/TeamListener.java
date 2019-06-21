package com.ted.lms.inscription.teams.internal.model.listener;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.Team;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.TeamLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.ted.lms.inscription.teams.model.Schedule;
import com.ted.lms.inscription.teams.service.ScheduleLocalService;
import com.ted.lms.model.Course;
import com.ted.lms.service.CourseLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    immediate = true,
    service = ModelListener.class
)
public class TeamListener extends BaseModelListener<Team>{

	@Override
	public void onAfterRemoveAssociation(Object classPK, String associationClassName, Object associationClassPK) throws ModelListenerException {
		if(User.class.getName().equals(associationClassName)) {
			//Compruebo si tiene Schedule
			long teamId = GetterUtil.getLong(classPK);
			Schedule schedule = scheduleLocalService.getScheduleByTeamId(teamId);
			if(schedule != null) {
				try {
					Team team = teamLocalService.getTeam(teamId);
					Course course = courseLocalService.getCourseByGroupCreatedId(team.getGroupId());
					long userId = GetterUtil.getLong(associationClassPK);
					scheduleLocalService.deleteScheduleUserDates(schedule, course.getCourseId(), userId);
				} catch (PortalException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public void onAfterAddAssociation(Object classPK, String associationClassName, Object associationClassPK) throws ModelListenerException {
		if(User.class.getName().equals(associationClassName)) {
			//Compruebo si tiene Schedule
			long teamId = GetterUtil.getLong(classPK);
			Schedule schedule = scheduleLocalService.getScheduleByTeamId(teamId);
			if(schedule != null) {
				try {
					Team team = teamLocalService.getTeam(teamId);
					Course course = courseLocalService.getCourseByGroupCreatedId(team.getGroupId());
					long userId = GetterUtil.getLong(associationClassPK);
					scheduleLocalService.updateScheduleUserDates(team.getUserId(), schedule, course.getCourseId(), userId);
				} catch (PortalException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}
	
	@Reference(unbind = "-")
	protected void setScheduleLocalService(ScheduleLocalService scheduleLocalService) {
		this.scheduleLocalService = scheduleLocalService;
	}

	private ScheduleLocalService scheduleLocalService;
	
	@Reference(unbind = "-")
	protected void setTeamLocalService(TeamLocalService teamLocalService) {
		this.teamLocalService = teamLocalService;
	}

	private TeamLocalService teamLocalService;
	
	@Reference(unbind = "-")
	protected void setCourseLocalService(CourseLocalService courseLocalService) {
		this.courseLocalService = courseLocalService;
	}

	private CourseLocalService courseLocalService;
}
