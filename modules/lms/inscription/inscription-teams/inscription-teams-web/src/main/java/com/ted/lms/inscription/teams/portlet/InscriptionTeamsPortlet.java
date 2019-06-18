package com.ted.lms.inscription.teams.portlet;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.MembershipRequestConstants;
import com.liferay.portal.kernel.model.Team;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.MembershipRequestLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.TeamLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.ted.lms.inscription.teams.TeamsInscription;
import com.ted.lms.inscription.teams.TeamsInscriptionFactory;
import com.ted.lms.inscription.teams.constants.InscriptionTeamsPortletKeys;
import com.ted.lms.inscription.teams.model.Schedule;
import com.ted.lms.inscription.teams.service.ScheduleLocalService;
import com.ted.lms.model.Course;
import com.ted.lms.security.permission.resource.CoursePermission;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.service.StudentLocalService;
import com.ted.prerequisite.model.Prerequisite;
import com.ted.prerequisite.service.PrerequisiteRelationLocalService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Virginia Martín Agudo
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.wemooc",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/inscription_teams/view.jsp",
		"javax.portlet.name=" + InscriptionTeamsPortletKeys.INSCRIPTION_TEAMS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=guest,user"
	},
	service = Portlet.class
)
public class InscriptionTeamsPortlet extends MVCPortlet {
	private static final Log log = LogFactoryUtil.getLog(InscriptionTeamsPortlet.class);
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
		
		ThemeDisplay themeDisplay  =(ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		Course course=courseLocalService.getCourseByGroupCreatedId(themeDisplay.getScopeGroupId());
		
		try {
			log.debug("permission VIEW: " + CoursePermission.contains(themeDisplay.getPermissionChecker(), course, ActionKeys.VIEW));
			
			if (course==null || !CoursePermission.contains(themeDisplay.getPermissionChecker(), course, ActionKeys.VIEW) || course.getInscriptionType() != TeamsInscriptionFactory.TYPE) {
				renderRequest.setAttribute(WebKeys.PORTLET_CONFIGURATOR_VISIBILITY, Boolean.FALSE);
			}else {
				if(themeDisplay.isSignedIn()) {
					
					PortletURL unsubscribeURL = renderResponse.createActionURL();
					unsubscribeURL.setParameter("javax.portlet.action", "unsubscribe");
					renderRequest.setAttribute("unsubscribeURL", unsubscribeURL);
					
					//Comprobamos si estoy inscrita en el curso o en alguna convocatoria
					if(userLocalService.hasGroupUser(course.getGroupCreatedId(), themeDisplay.getUserId())) {
						//Ya estoy inscrito, mando el curso y que estoy inscrito
						renderRequest.setAttribute("registredUser", true);
						renderRequest.setAttribute("course", course);
					}else {

						//Comprobamos los prerequisitos del curso padre (tanto si tiene convocatorias como si no)
						List<Prerequisite> listPrerequisites = prerequisiteRelationLocalService.getPrerequisites(PortalUtil.getClassNameId(Course.class.getName()), 
								course.getParentCourseId() == 0 ? course.getCourseId() : course.getParentCourseId());
						renderRequest.setAttribute("listPrerequisites", listPrerequisites);
						
						renderRequest.setAttribute("TYPE_SITE_OPEN", GroupConstants.TYPE_SITE_OPEN);
						renderRequest.setAttribute("TYPE_SITE_RESTRICTED", GroupConstants.TYPE_SITE_RESTRICTED);
						
						renderRequest.setAttribute("membershipRequestLocalService", membershipRequestLocalService);
						renderRequest.setAttribute("STATUS_PENDING", MembershipRequestConstants.STATUS_PENDING);
						renderRequest.setAttribute("STATUS_DENIED", MembershipRequestConstants.STATUS_DENIED);
						
						PortletURL enrollURL = renderResponse.createActionURL();
						enrollURL.setParameter("javax.portlet.action", "enroll");
						renderRequest.setAttribute("enrollURL", enrollURL);
						
						renderRequest.setAttribute("course", course);
						
						List<Team> teams = teamLocalService.getGroupTeams(course.getGroupCreatedId());
						List<Schedule> schedules = new ArrayList<Schedule>();
						Schedule schedule = null;
						Date now = new Date();
						for(Team team : teams){
							schedule = scheduleLocalService.getScheduleByTeamId(team.getTeamId());	
							if(schedule!=null){
								renderRequest.setAttribute("hasTeams", true);
								if(schedule.getStartDate().before(now)&&schedule.getEndDate().after(now)){
									schedules.add(schedule);
								}	
							}
						}
						renderRequest.setAttribute("schedules", schedules);
					}
				}
				
				super.doView(renderRequest, renderResponse);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			renderRequest.setAttribute(WebKeys.PORTLET_CONFIGURATOR_VISIBILITY, Boolean.FALSE);
		}
	}
	
	@ProcessAction(name = "enroll") 
	public void enroll(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException{
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		if (!themeDisplay.isSignedIn()) {return;}
		
		long teamId = ParamUtil.getLong(actionRequest, "teamId");
		
		Course course = courseLocalService.getCourseByGroupCreatedId(themeDisplay.getScopeGroupId());
		
		ServiceContext serviceContext = ServiceContextFactory.getInstance(Course.class.getName(), actionRequest);
		try {
			TeamsInscription inscriptionType = new TeamsInscription(course, serviceContext, studentLocalService, userLocalService, teamLocalService);
			inscriptionType.enrollUser(themeDisplay.getUserId(), teamId, themeDisplay.getPermissionChecker());
			if(course.getGroupCreatedId() != themeDisplay.getScopeGroupId()) {
				//Redirijo
				String url = themeDisplay.getPortalURL() + "/" + themeDisplay.getLocale().getLanguage() + "/web" + course.getFriendlyURL()+"?inscriptionOk=true";
	    		log.debug("Redirect to: "+url);
	    		actionResponse.sendRedirect(url);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			SessionErrors.add(actionRequest, "error-enroll-user", e.getMessage());
		}
	}
	
	@ProcessAction(name="unsubscribe")
	public void unsubscribe(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException{
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		if (!themeDisplay.isSignedIn()) {return;}
		
		Course course = courseLocalService.getCourseByGroupCreatedId(themeDisplay.getScopeGroupId());
		
		log.debug("InscriptionPortlet::unsubscribe::courseId::" + course.getCourseId());
		
		ServiceContext serviceContext = ServiceContextFactory.getInstance(Course.class.getName(), actionRequest);
		TeamsInscription inscriptionType = new TeamsInscription(course, serviceContext, studentLocalService, userLocalService, teamLocalService);
		
		boolean result = inscriptionType.unsubscribeUser(themeDisplay.getUserId(), themeDisplay.getPermissionChecker());
		log.debug("InscriptionPortlet::unsubscribe::result::" + result);
		if(result) {
			SessionMessages.add(actionRequest, "unsusbscribe");
		}else{
			SessionErrors.add(actionRequest, "unsusbscribe");
		}
	}
	
	@Reference(unbind = "-")
	protected void setCourseLocalService(CourseLocalService courseLocalService) {
		this.courseLocalService = courseLocalService;
	}
	
	private CourseLocalService courseLocalService;
	
	@Reference(unbind = "-")
	protected void setStudentLocalService(StudentLocalService studentLocalService) {
		this.studentLocalService = studentLocalService;
	}
	
	private StudentLocalService studentLocalService;
	
	@Reference(unbind = "-")
	protected void setMembershipRequestLocalService(MembershipRequestLocalService membershipRequestLocalService) {
		this.membershipRequestLocalService = membershipRequestLocalService;
	}
	
	private MembershipRequestLocalService membershipRequestLocalService;

	@Reference(unbind = "-")
	protected void setPrerequisiteRelationLocalService(PrerequisiteRelationLocalService prerequisiteRelationLocalService) {
		this.prerequisiteRelationLocalService = prerequisiteRelationLocalService;
	}
	
	private PrerequisiteRelationLocalService prerequisiteRelationLocalService;
	
	@Reference(unbind = "-")
	protected void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}
	
	private UserLocalService userLocalService;
	
	@Reference(unbind = "-")
	protected void setTeamLocalService(TeamLocalService teamLocalService) {
		this.teamLocalService = teamLocalService;
	}
	
	private TeamLocalService teamLocalService;
	
	@Reference(unbind = "-")
	protected void setScheduleLocalService(ScheduleLocalService scheduleLocalService) {
		this.scheduleLocalService = scheduleLocalService;
	}
	
	private ScheduleLocalService scheduleLocalService;
}