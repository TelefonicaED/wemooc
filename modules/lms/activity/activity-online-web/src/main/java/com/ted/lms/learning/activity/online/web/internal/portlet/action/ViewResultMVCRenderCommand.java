package com.ted.lms.learning.activity.online.web.internal.portlet.action;

import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.util.DLURLHelperUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.ted.lms.learning.activity.online.web.constants.OnlineConstants;
import com.ted.lms.learning.activity.online.web.constants.OnlinePortletKeys;
import com.ted.lms.model.LearningActivityResult;
import com.ted.lms.model.LearningActivityTry;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.service.LearningActivityLocalService;
import com.ted.lms.service.LearningActivityResultLocalService;
import com.ted.lms.service.LearningActivityTryLocalService;

import java.text.SimpleDateFormat;
import java.util.Iterator;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true, 
	property = { "javax.portlet.name=" + OnlinePortletKeys.ONLINE,
			"mvc.command.name=/activity/online/view_result" }, 
	service = MVCRenderCommand.class
)
public class ViewResultMVCRenderCommand implements MVCRenderCommand {
	
	private static final Log log = LogFactoryUtil.getLog(ViewResultMVCRenderCommand.class);

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		long actId = ParamUtil.getLong(renderRequest, "actId");
		long studentId = ParamUtil.getLong(renderRequest, "studentId", 0);
		long courseId = ParamUtil.getLong(renderRequest, "courseId");
		
		log.debug("actId: " + actId);
		log.debug("studentId: " + studentId);
		log.debug("courseId: " + courseId);
		
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
						urlFile = DLURLHelperUtil.getDownloadURL(dlfile, dlfile.getFileVersion(), themeDisplay, null);
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
		
		String dateFormated = (result.getStartDate()!=null)? " ( "+dateFormat.format(result.getStartDate())+" )":"";
		renderRequest.setAttribute("dateFormated", dateFormated);
		
		renderRequest.setAttribute("studentId", studentId);
		renderRequest.setAttribute("status", ParamUtil.getString(renderRequest, "status"));
		renderRequest.setAttribute("courseId", courseId);
		
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
