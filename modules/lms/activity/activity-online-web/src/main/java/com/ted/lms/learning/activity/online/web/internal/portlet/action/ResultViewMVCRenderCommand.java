package com.ted.lms.learning.activity.online.web.internal.portlet.action;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.ted.lms.constants.LMSActionKeys;
import com.ted.lms.learning.activity.online.OnlineActivityType;
import com.ted.lms.learning.activity.online.OnlineActivityTypeFactory;
import com.ted.lms.learning.activity.online.web.constants.OnlineConstants;
import com.ted.lms.learning.activity.online.web.constants.OnlinePortletKeys;
import com.ted.lms.model.CalificationType;
import com.ted.lms.model.CalificationTypeFactory;
import com.ted.lms.model.Course;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityResult;
import com.ted.lms.model.LearningActivityTry;
import com.ted.lms.registry.CalificationTypeFactoryRegistryUtil;
import com.ted.lms.security.permission.resource.LMSPermission;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.service.LearningActivityLocalService;
import com.ted.lms.service.LearningActivityResultLocalService;
import com.ted.lms.service.LearningActivityTryLocalService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowStateException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true, 
	property = { "javax.portlet.name=" + OnlinePortletKeys.ONLINE,
			"mvc.command.name=/activity/online/view_result" }, 
	service = MVCRenderCommand.class
)
public class ResultViewMVCRenderCommand implements MVCRenderCommand {
	
	private static final Log log = LogFactoryUtil.getLog(ResultViewMVCRenderCommand.class);

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		long actId = ParamUtil.getLong(renderRequest, "actId");
		long studentId = ParamUtil.getLong(renderRequest, "studentId", 0);
		
		log.debug("actId: " + actId);
		log.debug("studentId: " + studentId);
		
		boolean correctActivity = studentId > 0;
		studentId = studentId > 0 ? studentId : themeDisplay.getUserId();
		
		LearningActivityTry learningActivityTry = learningActivityTryLocalService.getLastLearningActivityTry(actId, studentId);
		LearningActivityResult result = learningActivityResultLocalService.getLearningActivityResult(actId, studentId);
		
		String urlFile=null;
		String titleFile=null;
		long sizeKbFile=0;

		String text=null;
		String richtext=null;

		if(learningActivityTry!=null){
			try {
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
						richtext = element.getStringValue();
					if(OnlineConstants.TRY_TEXT_XML.equals(element.getName()))
						text = element.getText(); 
				}
			} catch(DocumentException | PortalException e){
				e.printStackTrace();
			}
		}
		
		renderRequest.setAttribute("urlFile", urlFile);
		renderRequest.setAttribute("titleFile", titleFile);
		renderRequest.setAttribute("sizeKbFile", sizeKbFile);
		renderRequest.setAttribute("text", text);
		renderRequest.setAttribute("richtext", richtext);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		dateFormat.setTimeZone(themeDisplay.getTimeZone());
		
		String dateFormated = (result.getEndDate()!=null)? " ( "+dateFormat.format(result.getEndDate())+" )":"";
		renderRequest.setAttribute("dateFormated", dateFormated);
		
		if(correctActivity) {
			try {
				User student = userLocalService.getUser(studentId);
				renderRequest.setAttribute("studentName", student.getFullName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		Course course = courseLocalService.getCourseByGroupCreatedId(themeDisplay.getScopeGroupId());
		CalificationTypeFactory calificationTypeFactory = CalificationTypeFactoryRegistryUtil.getCalificationTypeFactoryByType(course.getCalificationType());
		CalificationType calificationType = calificationTypeFactory.getCalificationType(course);
		
		renderRequest.setAttribute("correctActivity", correctActivity);
		renderRequest.setAttribute("studentId", studentId);
		renderRequest.setAttribute("status", ParamUtil.getString(renderRequest, "status"));
		
		return "/result.jsp";
		
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
	protected void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}
	
	private LearningActivityLocalService learningActivityLocalService;
	private LearningActivityResultLocalService learningActivityResultLocalService;
	private LearningActivityTryLocalService learningActivityTryLocalService;
	private DLAppLocalService dlAppLocalService; 
	private CourseLocalService courseLocalService;
	private UserLocalService userLocalService;
}
