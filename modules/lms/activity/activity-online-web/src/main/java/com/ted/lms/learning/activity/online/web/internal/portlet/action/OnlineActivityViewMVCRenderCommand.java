package com.ted.lms.learning.activity.online.web.internal.portlet.action;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.CustomSQLParam;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.util.comparator.UserFirstNameComparator;
import com.liferay.portal.kernel.util.comparator.UserLastNameComparator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.ted.lms.constants.LMSActionKeys;
import com.ted.lms.learning.activity.online.OnlineActivityType;
import com.ted.lms.learning.activity.online.OnlineActivityTypeFactory;
import com.ted.lms.learning.activity.online.web.constants.OnlineConstants;
import com.ted.lms.learning.activity.online.web.constants.OnlinePortletKeys;
import com.ted.lms.learning.activity.online.web.internal.util.OnlineActivitySQL;
import com.ted.lms.model.CalificationType;
import com.ted.lms.model.CalificationTypeFactory;
import com.ted.lms.model.Course;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityResult;
import com.ted.lms.model.LearningActivityTry;
import com.ted.lms.registry.CalificationTypeFactoryRegistryUtil;
import com.ted.lms.registry.LearningActivityTypeFactoryRegistryUtil;
import com.ted.lms.security.permission.resource.LMSPermission;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.service.LearningActivityLocalService;
import com.ted.lms.service.LearningActivityResultLocalService;
import com.ted.lms.service.LearningActivityTryLocalService;
import com.ted.lms.service.StudentLocalService;
import com.ted.lms.util.LMSPrefsPropsValues;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;
import javax.portlet.WindowStateException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true, 
	property = { "javax.portlet.name=" + OnlinePortletKeys.ONLINE,
			"mvc.command.name=/",
			"mvc.command.name=/activities/online/view_activity" }, 
	service = MVCRenderCommand.class
)
public class OnlineActivityViewMVCRenderCommand implements MVCRenderCommand {
	
	private static final Log log = LogFactoryUtil.getLog(OnlineActivityViewMVCRenderCommand.class);

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		log.debug("render online");
	
		boolean isTeacher = LMSPermission.contains(themeDisplay.getPermissionChecker(), themeDisplay.getScopeGroupId(), LMSActionKeys.VIEW_RESULTS);
		
		if(isTeacher) {
			return doRenderViewTutor(renderRequest, renderResponse);
		}else {
			return doRenderViewStudent(renderRequest, renderResponse);
		}
		
	}
	
	private String doRenderViewStudent(RenderRequest renderRequest, RenderResponse renderResponse) {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		long actId = ParamUtil.getLong(renderRequest, "actId", 0);
		
		try {
		
			LearningActivityResult result = learningActivityResultLocalService.getLearningActivityResult(actId, themeDisplay.getUserId());
			LearningActivity activity = learningActivityLocalService.getLearningActivity(actId);
			
			if(result == null || result.getEndDate() == null) {
				PortletURL saveActivityURL = renderResponse.createActionURL();
				saveActivityURL.setParameter("javax.portlet.action", "saveActivity");
				saveActivityURL.setParameter("actId", String.valueOf(actId));
				renderRequest.setAttribute("saveActivityURL", saveActivityURL);
				
				OnlineActivityTypeFactory onlineActivityTypeFactory = (OnlineActivityTypeFactory)LearningActivityTypeFactoryRegistryUtil.getLearningActivityTypeFactoryByType(OnlineConstants.TYPE);
				OnlineActivityType onlineActivityType = onlineActivityTypeFactory.getOnlineActivityType(activity);
				
				renderRequest.setAttribute("onlineActivityType", onlineActivityType);
				
				LearningActivityTry learningActivityTry = learningActivityTryLocalService.getLastLearningActivityTry(actId, themeDisplay.getUserId());
				
				if(learningActivityTry != null) {
					try {
						String text = null;
						String urlFile = null;
						String titleFile = null;
						long sizeKbFile=0;
						Iterator<Node> nodeItr = SAXReaderUtil.read(learningActivityTry.getTryResultData()).getRootElement().nodeIterator();		
						while(nodeItr.hasNext()) {
							Node element = nodeItr.next();
							if(OnlineConstants.TRY_FILE_XML.equals(element.getName())) {
									FileEntry dlfile = dlAppLocalService.getFileEntry(Long.parseLong(((Element)element).attributeValue("id")));
									urlFile = DLUtil.getDownloadURL(dlfile, dlfile.getFileVersion(), themeDisplay, null);
									titleFile = dlfile.getTitle();
									sizeKbFile = dlfile.getSize()/1024;
							}
							if(OnlineConstants.TRY_RICH_TEXT_XML.equals(element.getName()))
								text = element.getStringValue();
							if(OnlineConstants.TRY_TEXT_XML.equals(element.getName()))
								text = element.getText(); 
						}
						
						renderRequest.setAttribute("text", text);
						renderRequest.setAttribute("urlFile", urlFile);
						renderRequest.setAttribute("titleFile", titleFile);
						renderRequest.setAttribute("sizeKbFile", sizeKbFile);
					}catch (Exception e) {
						e.printStackTrace();
					}
		 		}
			}else {
				Course course = courseLocalService.getCourseByGroupCreatedId(themeDisplay.getScopeGroupId());
				CalificationTypeFactory calificationTypeFactory = CalificationTypeFactoryRegistryUtil.getCalificationTypeFactoryByType(course.getCalificationType());
				CalificationType calificationType = calificationTypeFactory.getCalificationType(course);
				
				String userResult = calificationType.translate(themeDisplay.getLocale(), result.getResult()) + calificationType.getSuffix();
				renderRequest.setAttribute("userResult", userResult);
				
				LiferayPortletURL showPopupGradesURL = renderResponse.createRenderURL();
				try {
					showPopupGradesURL.setWindowState(LiferayWindowState.POP_UP);
			    }catch (WindowStateException wse) {
			    	wse.printStackTrace();
			    }
				showPopupGradesURL.setParameter(WebKeys.PORTLET_CONFIGURATOR_VISIBILITY, StringPool.TRUE);
				showPopupGradesURL.setParameter("mvcRenderCommandName", "/activity/online/edit_result");
				showPopupGradesURL.setParameter("actId", String.valueOf(actId));
				
				renderRequest.setAttribute("showPopupGradesURL", showPopupGradesURL);
			}
			
			//Documentos anexos al recurso externo
			List<FileEntry> listDocuments = null;
			try {
				listDocuments = activity.getAttachmentsFileEntries();
			} catch (PortalException e) {
				e.printStackTrace();
				listDocuments = new ArrayList<FileEntry>();
			}
			renderRequest.setAttribute("documents", listDocuments);
			
			renderRequest.setAttribute("activity", activity);
			renderRequest.setAttribute("result", result);
			
			return "/view.jsp";
		
		} catch (PortalException e) {
			e.printStackTrace();
			return "/error.jsp";
		}
	}
	
	private String doRenderViewTutor(RenderRequest renderRequest, RenderResponse renderResponse) {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		String keywords = ParamUtil.getString(renderRequest, "keywords");
		String status = ParamUtil.getString(renderRequest, "status");
		long actId = ParamUtil.getLong(renderRequest, "actId", 0);
		
		log.debug("keywords: " + keywords);
		log.debug("status: " + status);
		
		LinkedHashMap<String,Object> params = new LinkedHashMap<String,Object>();
		params.put("onlineActivity",new CustomSQLParam(OnlineActivitySQL.ACTIVITY_TRY_SQL,actId));
		
		if(Validator.isNotNull(status)) {
			switch(status) {
				case "nocalification":
					params.put("nocalification",new CustomSQLParam(OnlineActivitySQL.ACTIVITY_RESULT_NO_CALIFICATION_SQL,actId));
					break;
				case "passed":
					params.put("passed",new CustomSQLParam(OnlineActivitySQL.ACTIVITY_RESULT_PASSED_SQL,actId));
					break;
				case "failed":
					params.put("failed",new CustomSQLParam(OnlineActivitySQL.ACTIVITY_RESULT_FAIL_SQL,actId));
					break;
			}
		}
		
		Course course = courseLocalService.getCourseByGroupCreatedId(themeDisplay.getScopeGroupId());
		
		PortletURL portletURL = renderResponse.createRenderURL();
		portletURL.setParameter("actId",Long.toString(actId));
		portletURL.setParameter("keywords",keywords);
		portletURL.setParameter("status",String.valueOf(status));
		portletURL.setParameter("mvcRenderCommandName", "/activities/online/view_activity");
		
		PortletURL searchURL = renderResponse.createRenderURL();
		searchURL.setParameter("actId",Long.toString(actId));
		searchURL.setParameter("mvcRenderCommandName", "/activities/online/view_activity");
		renderRequest.setAttribute("searchURL", searchURL);

		SearchContainer<User> searchContainer = new SearchContainer<User>(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, 
				ParamUtil.getInteger(renderRequest, SearchContainer.DEFAULT_DELTA_PARAM,SearchContainer.DEFAULT_DELTA), portletURL, 
				null,  "no-results");
		
		OrderByComparator obc = null;
		if(LMSPrefsPropsValues.getUsersFirstLastName(themeDisplay.getCompanyId())){
			obc = new UserLastNameComparator(true);
		}else{
			obc = new UserFirstNameComparator(true);
		}
		
		List<User> users = null;
		try {
			users = studentLocalService.getStudentsFromCourse(course.getCourseId(), themeDisplay.getCompanyId(), keywords, WorkflowConstants.STATUS_APPROVED, 
					params, searchContainer.getStart(), searchContainer.getEnd(), obc);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			users = new ArrayList<User>();
		}
		
		int total = studentLocalService.countStudentsFromCourse(course.getCourseId(), themeDisplay.getCompanyId(), keywords, WorkflowConstants.STATUS_APPROVED, params);
		
		searchContainer.setResults(users);
		searchContainer.setTotal(total);
		
		renderRequest.setAttribute("searchContainer", searchContainer);
		
		try {
			LearningActivity activity = learningActivityLocalService.getLearningActivity(actId);
			OnlineActivityTypeFactory onlineActivityTypeFactory = (OnlineActivityTypeFactory)LearningActivityTypeFactoryRegistryUtil.getLearningActivityTypeFactoryByType(OnlineConstants.TYPE);
			OnlineActivityType onlineActivityType = onlineActivityTypeFactory.getOnlineActivityType(activity);
			
			PortletURL showPopupActivityURL = renderResponse.createRenderURL();
			showPopupActivityURL.setWindowState(LiferayWindowState.POP_UP);
			showPopupActivityURL.setParameter("mvcRenderCommandName", "/activity/online/edit_result");
			showPopupActivityURL.setParameter("actId", String.valueOf(actId));
			showPopupActivityURL.setParameter("courseId", String.valueOf(course.getCourseId()));
			renderRequest.setAttribute("showPopupActivityURL", showPopupActivityURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		renderRequest.setAttribute("keywords", keywords);
		renderRequest.setAttribute("status", status);
		renderRequest.setAttribute("actId", actId);
		

		log.debug("users: " + users.size());
		log.debug("total: " + total);
		
		return "/califications.jsp";
	}
	
	@Reference(unbind = "-")
	protected void setLearningActivityLocalService(LearningActivityLocalService learningActivityLocalService) {
		this.learningActivityLocalService = learningActivityLocalService;
	}

	@Reference(unbind = "-")
	protected void setLearningActivityResultLocalService(LearningActivityResultLocalService learningActivityResultLocalService) {
		this.learningActivityResultLocalService = learningActivityResultLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setLearningActivityTryLocalService(LearningActivityTryLocalService learningActivityTryLocalService) {
		this.learningActivityTryLocalService = learningActivityTryLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setDLAppLocalService(DLAppLocalService dlAppLocalService) {
		this.dlAppLocalService = dlAppLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setCourseLocalService(CourseLocalService courseLocalService) {
		this.courseLocalService = courseLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setStudentLocalService(StudentLocalService studentLocalService) {
		this.studentLocalService = studentLocalService;
	}
	
	private LearningActivityLocalService learningActivityLocalService;
	private LearningActivityResultLocalService learningActivityResultLocalService;
	private LearningActivityTryLocalService learningActivityTryLocalService;
	private DLAppLocalService dlAppLocalService; 
	private CourseLocalService courseLocalService;
	private StudentLocalService studentLocalService;
}
