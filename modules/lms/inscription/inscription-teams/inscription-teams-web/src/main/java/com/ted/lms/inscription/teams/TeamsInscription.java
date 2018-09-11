package com.ted.lms.inscription.teams;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Team;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.TeamLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.ted.lms.exception.InscriptionException;
import com.ted.lms.model.BaseInscriptionType;
import com.ted.lms.model.Course;
import com.ted.lms.model.CourseResult;
import com.ted.lms.service.CourseLocalService;

import java.util.List;

public class TeamsInscription extends BaseInscriptionType{

	public TeamsInscription(Course course, ServiceContext serviceContext,
			CourseLocalService courseLocalService, UserLocalService userLocalService,
			TeamLocalService teamLocalService) {
		super(course, serviceContext, courseLocalService);
	}
	
	@Override
	public String getClassName() {
		return TeamsInscription.class.getName();
	}
	
	public CourseResult enrollUser(long userId, long teamId, PermissionChecker permissionChecker) throws PortalException, InscriptionException {
		CourseResult courseResult = super.enrollUser(userId, permissionChecker);
		if(courseResult != null) {
			if(teamId>0){
    			long[] userIds = new long[1];
    			userIds[0] = userId;	
    			if(!userLocalService.hasTeamUser(teamId, userId)){
    				userLocalService.addTeamUsers(teamId, userIds);	
    			}			
    		}
		}
		return courseResult;
	}
	
	@Override
	public boolean unsubscribeUser(long userId, PermissionChecker permissionChecker) throws PortalException {
		boolean result =  super.unsubscribeUser(userId, permissionChecker);
		if(result) {
			try{
				List<Team> teams = teamLocalService.getUserTeams(userId, course.getGroupCreatedId());
				if(teams!=null && teams.size()>0){
					long[] userIds = new long[1];
					userIds[0] = userId;
					for(Team team : teams){
						if(userLocalService.hasTeamUser(team.getTeamId(), userId)){
							userLocalService.unsetTeamUsers(team.getTeamId(), userIds);
						}
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return result;
	}

	private UserLocalService userLocalService;
	private TeamLocalService teamLocalService;
}

