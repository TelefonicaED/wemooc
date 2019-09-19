package com.ted.lms.web.internal.portlet;

import com.ted.lms.constants.CourseConstants;
import com.ted.lms.constants.CourseParams;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.Course;
import com.ted.lms.model.InscriptionType;
import com.ted.lms.model.InscriptionTypeFactory;
import com.ted.lms.registry.InscriptionTypeFactoryRegistryUtil;
import com.ted.lms.security.permission.resource.CoursePermission;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.service.CourseService;
import com.ted.prerequisite.model.Prerequisite;
import com.ted.prerequisite.service.PrerequisiteRelationLocalService;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.MembershipRequestConstants;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.MembershipRequestLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Virginia Martín Agudo
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.wemooc",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/inscription/view.jsp",
		"javax.portlet.name=" + LMSPortletKeys.INSCRIPTION,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=guest,user"
	},
	service = Portlet.class
)
public class InscriptionPortlet extends MVCPortlet {
	
	private static final Log log = LogFactoryUtil.getLog(InscriptionPortlet.class);
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
		
		ThemeDisplay themeDisplay  =(ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		Course course=courseLocalService.getCourseByGroupCreatedId(themeDisplay.getScopeGroupId());
		
		try {
			log.debug("permission VIEW: " + CoursePermission.contains(themeDisplay.getPermissionChecker(), course, ActionKeys.VIEW));
			
			if (course==null || !CoursePermission.contains(themeDisplay.getPermissionChecker(), course, ActionKeys.VIEW)) {
				renderRequest.setAttribute(WebKeys.PORTLET_CONFIGURATOR_VISIBILITY, Boolean.FALSE);
			}else {
				InscriptionTypeFactory inscriptionTypeFactory = InscriptionTypeFactoryRegistryUtil.getInscriptionTypeFactoryByType(course.getInscriptionType());
				log.debug("inscriptionTypeFactory: " + inscriptionTypeFactory.getType() + " - " + inscriptionTypeFactory.getPortletId());
				log.debug("LMSPortletKeys.INSCRIPTION : " + LMSPortletKeys.INSCRIPTION);
				if(LMSPortletKeys.INSCRIPTION.equals(inscriptionTypeFactory.getPortletId())) {
					if(themeDisplay.isSignedIn()) {
						
						HttpServletRequest renderServletRequest = PortalUtil.getHttpServletRequest(renderRequest);
						HttpServletRequest servletRequest = PortalUtil.getOriginalServletRequest(renderServletRequest);
						
						String inscriptionParam = servletRequest.getParameter("inscriptionOk");
						if(Validator.isNotNull(inscriptionParam)){
							try{
								if(Boolean.parseBoolean(inscriptionParam)) {
									SessionMessages.add(renderRequest, "inscription-ok");
								}
							}catch(Exception e){
								if(log.isDebugEnabled())e.printStackTrace();
							}
						}
						
						PortletURL unsubscribeURL = renderResponse.createActionURL();
						unsubscribeURL.setParameter("javax.portlet.action", "unsubscribe");
						renderRequest.setAttribute("unsubscribeURL", unsubscribeURL);
						
						//Comprobamos si estoy inscrita en el curso o en alguna convocatoria
						if(userLocalService.hasGroupUser(course.getGroupCreatedId(), themeDisplay.getUserId())) {
							//Ya estoy inscrito, mando el curso y que estoy inscrito
							renderRequest.setAttribute("registredUser", true);
							renderRequest.setAttribute("course", course);
						}else {
							//Comprobamos si tengo la inscripción pendiente
							boolean inscriptionPending = membershipRequestLocalService.getMembershipRequests(themeDisplay.getUserId(), course.getGroupCreatedId(), MembershipRequestConstants.STATUS_PENDING).size() > 0;
							if(!inscriptionPending) {
								
								boolean inscriptionDenied = membershipRequestLocalService.getMembershipRequests(themeDisplay.getUserId(), course.getGroupCreatedId(), MembershipRequestConstants.STATUS_DENIED).size() > 0;
								renderRequest.setAttribute("inscriptionDenied", inscriptionDenied);
								
								if(!inscriptionDenied) {
									//Comprobamos si estoy inscrita en alguna de las hijas
									long parentCourseId = course.getCourseId();
									if(course.getParentCourseId() != CourseConstants.DEFAULT_PARENT_COURSE_ID) {
										parentCourseId = course.getParentCourseId();
									}
									List<Course> listChildCourses = courseLocalService.getChildsRegistredUser(parentCourseId, themeDisplay.getUserId());
									if(listChildCourses != null && listChildCourses.size() > 0) {
										//Estoy inscrita en alguna convocatoria de las hijas, mando qeu estoy inscrita y las convocatorias en las que estoy
										renderRequest.setAttribute("registredUser", true);
										renderRequest.setAttribute("listChildCourses", listChildCourses);
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
										
										//Si es una convocatoria sabemos que no estoy inscrito en ninguna más así que paso el curso
										if(course.getParentCourseId() != CourseConstants.DEFAULT_PARENT_COURSE_ID) {
											renderRequest.setAttribute("course", course);
										}else {
											//Si es un curso padre y no tengo convocatorias hijas mando el curso, si no mando las hijas
											//Buscamos los cursos hijos abiertos o restringidos
											LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
											int[] types = {GroupConstants.TYPE_SITE_OPEN, GroupConstants.TYPE_SITE_RESTRICTED};
											params.put(CourseParams.PARAM_TYPE, types);
											
											listChildCourses = courseService.searchCourses(themeDisplay.getCompanyId(), null, null, themeDisplay.getLanguageId(), 
													new int[]{WorkflowConstants.STATUS_APPROVED}, course.getCourseId(), 0, params, false, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
											
											if(course.getParentCourseId() == CourseConstants.DEFAULT_PARENT_COURSE_ID && (listChildCourses == null || listChildCourses.size() == 0)) {
												//Mando sólo el curso hijo
												renderRequest.setAttribute("course", course);
											}else {
												//Compruebo si tengo inscripción pendiente en algún hijo
												int i = 0;
												while(!inscriptionPending && i < listChildCourses.size()) {
													inscriptionPending = membershipRequestLocalService.getMembershipRequests(themeDisplay.getUserId(), listChildCourses.get(i++).getGroupCreatedId(), MembershipRequestConstants.STATUS_PENDING).size() > 0;
												}
												renderRequest.setAttribute("listChildCourses", listChildCourses);
												//Paso también el curso padre
												renderRequest.setAttribute("course", course);
											}
										}
									}
								}
							}
							renderRequest.setAttribute("inscriptionPending", inscriptionPending);
						}
					}
					
				}else {
					renderRequest.setAttribute("inscriptionPortletName", inscriptionTypeFactory.getPortletId());
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
		
		long courseId = ParamUtil.getLong(actionRequest, "courseId");
		
		Course course = courseLocalService.getCourse(courseId);
		
		ServiceContext serviceContext = ServiceContextFactory.getInstance(Course.class.getName(), actionRequest);
		try {
			InscriptionTypeFactory inscriptionTypeFactory = InscriptionTypeFactoryRegistryUtil.getInscriptionTypeFactoryByType(course.getInscriptionType());
			InscriptionType inscriptionType = inscriptionTypeFactory.getInscriptionType(course, serviceContext);
			
			inscriptionType.enrollUser(themeDisplay.getUserId(), themeDisplay.getPermissionChecker());
			
			if(course.getGroupCreatedId() != themeDisplay.getScopeGroupId()) {
				//Redirijo
				String url = themeDisplay.getPortalURL() + "/" + themeDisplay.getLocale().getLanguage() + course.getFriendlyURL()+"?inscriptionOk=true";
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
		
		long courseId = ParamUtil.getLong(actionRequest, "courseId");
		log.debug("InscriptionPortlet::unsubscribe::courseId::" + courseId);
		
		Course course = courseLocalService.getCourse(courseId);
		
		ServiceContext serviceContext = ServiceContextFactory.getInstance(Course.class.getName(), actionRequest);
		
		InscriptionTypeFactory inscriptionTypeFactory = InscriptionTypeFactoryRegistryUtil.getInscriptionTypeFactoryByType(course.getInscriptionType());
		InscriptionType inscriptionType = inscriptionTypeFactory.getInscriptionType(course, serviceContext);
		
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
	protected void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}
	
	private CourseService courseService;
	
	@Reference(unbind = "-")
	protected void setMembershipRequestLocalService(MembershipRequestLocalService membershipRequestLocalService) {
		this.membershipRequestLocalService = membershipRequestLocalService;
	}
	
	private MembershipRequestLocalService membershipRequestLocalService;
	
	@Reference
	private PrerequisiteRelationLocalService prerequisiteRelationLocalService;
	
	@Reference(unbind = "-")
	protected void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}
	
	private UserLocalService userLocalService;

}