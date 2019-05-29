package com.ted.lms.learning.activity.resource.external.web.internal.portlet;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.ted.lms.constants.LMSActionKeys;
import com.ted.lms.learning.activity.question.model.Question;
import com.ted.lms.learning.activity.question.model.QuestionType;
import com.ted.lms.learning.activity.question.model.QuestionTypeFactory;
import com.ted.lms.learning.activity.question.service.QuestionLocalServiceUtil;
import com.ted.lms.learning.activity.resource.external.ResourceExternalActivityType;
import com.ted.lms.learning.activity.resource.external.ResourceExternalActivityTypeFactory;
import com.ted.lms.learning.activity.resource.external.web.constants.ResourceExternalConstants;
import com.ted.lms.learning.activity.resource.external.web.constants.ResourceExternalPortletKeys;
import com.ted.lms.model.CalificationType;
import com.ted.lms.model.CalificationTypeFactory;
import com.ted.lms.model.Course;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityResult;
import com.ted.lms.model.LearningActivityTry;
import com.ted.lms.registry.CalificationTypeFactoryRegistryUtil;
import com.ted.lms.security.permission.resource.LMSPermission;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.service.CourseLocalServiceUtil;
import com.ted.lms.service.LearningActivityLocalServiceUtil;
import com.ted.lms.service.LearningActivityResultLocalService;
import com.ted.lms.service.LearningActivityResultLocalServiceUtil;
import com.ted.lms.service.LearningActivityTryLocalService;
import com.ted.lms.service.LearningActivityTryLocalServiceUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.ResourceURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author JE10436
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + ResourceExternalPortletKeys.RESOURCE_EXTERNAL,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.supported-public-render-parameter=actId",
		"com.liferay.portlet.use-default-template=true"
	},
	service = Portlet.class
)
public class ResourceExternalPortlet extends MVCPortlet {
	
	private static final Log log = LogFactoryUtil.getLog(ResourceExternalPortlet.class);
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
		
		System.out.println("doView");
		
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		long actId = ParamUtil.getLong(renderRequest, "actId", 0);
		
		try {
			
			ResourceURL saveQuestionURL = renderResponse.createResourceURL();
			saveQuestionURL.setResourceID("saveQuestion");
			saveQuestionURL.setParameter("actId", String.valueOf(actId));
			renderRequest.setAttribute("saveQuestionURL", saveQuestionURL);
			
			LearningActivity activity = LearningActivityLocalServiceUtil.getLearningActivity(actId);
			
			Course course = CourseLocalServiceUtil.getCourseByGroupCreatedId(themeDisplay.getScopeGroupId());
			
			boolean hasPermissionAccessCourseFinished = course.hasPermissionAccessCourseFinished(themeDisplay.getUserId());
			boolean isTeacher = LMSPermission.contains(themeDisplay.getPermissionChecker(), themeDisplay.getScopeGroupId(), LMSActionKeys.VIEW_RESULTS);
			
			if(isTeacher) {
				PortletURL goToCorrectionURL = renderResponse.createRenderURL();
				goToCorrectionURL.setParameter("mvcRenderCommandName", "/activities/resource_external/correction");
				goToCorrectionURL.setParameter("actId", String.valueOf(actId));
				renderRequest.setAttribute("goToCorrectionURL", goToCorrectionURL);
			}
			
			renderRequest.setAttribute("activity", activity);
			renderRequest.setAttribute("hasPermissionAccessCourseFinished", hasPermissionAccessCourseFinished);
			renderRequest.setAttribute("isTeacher", isTeacher);
			
			ResourceExternalActivityTypeFactory resourceExternalActivityTypeFactory = new ResourceExternalActivityTypeFactory();
			ResourceExternalActivityType resourceExternalActivityType = resourceExternalActivityTypeFactory.getResourceExternalActivityType(activity);
			
			//Comprobamos si es necesario ver un porcetanje del video para aprobar
			boolean isDefaultScore = (activity.getPassPuntuation() == 0);
			renderRequest.setAttribute("isDefaultScore", isDefaultScore);
			
			//Si hemos hecho un intento anteriormente y necesitamos ver un porcentaje especifico para aprobar posicionamos el video donde se quedó
			double videoPosition=0;
			int oldScore=0;
			int plays=0;
	
			LearningActivityTry lastLearningActivityTry = null;
			
			if (!isDefaultScore){
				
				if(!hasPermissionAccessCourseFinished){
					lastLearningActivityTry =LearningActivityTryLocalServiceUtil.getLastLearningActivityTry(actId,themeDisplay.getUserId());
				}
				if (lastLearningActivityTry != null){
					//Poner posición del video.
					String xml = lastLearningActivityTry.getTryResultData();
					
					if(!xml.equals("")){
						
						renderRequest.setAttribute("tryResultData", xml);
	
						try {
							Document documentTry = SAXReaderUtil.read(xml);
							
							Element rootElement = documentTry.getRootElement();
							Element positionElement = rootElement.element("position");						
							videoPosition =  Double.parseDouble(positionElement.getText());
							
							Element playsElement = rootElement.element("plays");	
							plays =  Integer.parseInt(playsElement.getText());
		
							Element scoreElement = rootElement.element("score");	
							oldScore =  Integer.parseInt(scoreElement.getText());	
						} catch (DocumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}		
				}
			}
			
			renderRequest.setAttribute("videoPosition", videoPosition);
			renderRequest.setAttribute("oldScore", oldScore);
			renderRequest.setAttribute("plays", plays);
			
			//Comprobamos si el usuario ha pasado el curso y lo enviamos para recargar los portlets al terminar
			boolean userPassed = LearningActivityResultLocalServiceUtil.hasUserPassed(actId,themeDisplay.getUserId());
			renderRequest.setAttribute("userPassed", userPassed);
			
			//Tratamos el video si tiene
			String video = resourceExternalActivityType.getVideo();
			if(Validator.isNotNull(video)){
				boolean isVimeoIframe = false;
				boolean isDLFileEntry = false;
				
				String videoIframeCode= video;
				isVimeoIframe = ((videoIframeCode.indexOf("iframe")>-1) &&  (videoIframeCode.indexOf("vimeo")>-1));
				log.debug("isVimeoIframe: " + isVimeoIframe);
				
				boolean videoControlDisabled = !resourceExternalActivityType.getShowControls();
				
				renderRequest.setAttribute("controls", videoControlDisabled && !userPassed ? "": "controls");
				
				int seekTo = 0;
				if (videoPosition > 0 && oldScore<100){
					DecimalFormat df = new DecimalFormat("#####");
					seekTo = Integer.parseInt(df.format(videoPosition));
				}
				renderRequest.setAttribute("currentTime", seekTo);
				
				String videoCode= video;
				log.debug("videoCode: " + videoCode);
				
				if(videoCode.indexOf("src=") > 0){
					try{
						Matcher matcher = Pattern.compile("src=\"([^\"]+)\"").matcher(videoCode);
						matcher.find();
						videoCode = matcher.group(1);
					}catch (IllegalStateException e){
						Matcher matcher = Pattern.compile("src=\'([^\']+)\'").matcher(videoCode);
						matcher.find();
						videoCode = matcher.group(1);
					}
				}
				
				log.debug("videoCode: " + videoCode);
				if(isVimeoIframe && videoCode.indexOf("?") >= 0){
					videoCode = videoCode.substring(0, videoCode.indexOf("?"));
				}
				if(isVimeoIframe){
					
					String parametros = "";
					if(videoControlDisabled && !userPassed){
						parametros += "?background=1&loop=0&mute=0";
					}
					videoCode += parametros;
					log.debug("videoCode: " + videoCode);
				}
				
				String mimeType = "";
				if(videoCode.contains("vimeo.com")){
					mimeType += "vimeo";
				}else if(videoCode.contains("youtu")){
					mimeType += "youtube";
				}else if(videoCode.contains(".mp4")){
					mimeType += "mp4";
				}else if(videoCode.contains(".wmv")){
					mimeType += "wmv";
				}else if(videoCode.contains(".ogv")){
					mimeType += "ogg";
				}else if(videoCode.contains(".webm")){
					mimeType += "webm";
				}else if(videoCode.contains(".flv")){
					mimeType += "flv";
				}else if(videoCode.contains(".mp4")){
					mimeType += "mp4";
				}
				
				renderRequest.setAttribute("mimeType", "video/" + mimeType);
				renderRequest.setAttribute("video", videoCode);
				
				List<Question> listQuestions = QuestionLocalServiceUtil.getQuestions(actId);
				renderRequest.setAttribute("listQuestions", listQuestions);
				
				JSONObject questionPositions = resourceExternalActivityType.getQuestionPositions();
				
				//Ahora pasamos los tiempos de las preguntas
				Hashtable<Long, Integer> timeQuestions = new Hashtable<Long, Integer>();
				Element element = null;
				for(Question question: listQuestions){
					if(questionPositions.has("second_" + question.getQuestionId())) {
						timeQuestions.put(question.getQuestionId(), questionPositions.getInt("second_" + question.getQuestionId()));
					}
				}
				
				renderRequest.setAttribute("timeQuestions", timeQuestions);
					
				
				renderRequest.setAttribute("isYoutubeIframe", true);
				renderRequest.setAttribute("isVimeoIframe", isVimeoIframe);
				renderRequest.setAttribute("isDLFileEntry", isDLFileEntry);
			}
			
			//Creamos el nuevo intento al usuario
			ServiceContext serviceContext = ServiceContextFactory.getInstance(LearningActivityTry.class.getName(), renderRequest);
			
			ResourceURL finishTryURL = renderResponse.createResourceURL();
			finishTryURL.setResourceID("finishTry");
			finishTryURL.setParameter("actId", String.valueOf(actId));
			
			if(!hasPermissionAccessCourseFinished) {
	
				LearningActivityTry learningTry = LearningActivityTryLocalServiceUtil.addLearningActivityTry(actId, themeDisplay.getUserId(), serviceContext);
				if (lastLearningActivityTry != null){
					learningTry.setTryResultData(lastLearningActivityTry.getTryResultData());
					LearningActivityTryLocalServiceUtil.updateLearningActivityTry(learningTry);	
				}
				finishTryURL.setParameter("latId", String.valueOf(learningTry.getLatId()));
				renderRequest.setAttribute("latId", learningTry.getLatId());
				//Si no hace falta nota para aprobar ya lo aprobamos
				if(isDefaultScore){
					LearningActivityTryLocalServiceUtil.finishLearningActivityTry(learningTry, 100, serviceContext);
				}
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

			renderRequest.setAttribute("finishTryURL", finishTryURL);
			
			super.doView(renderRequest, renderResponse);
			
		} catch (PortalException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		if(resourceRequest.getResourceID() != null && resourceRequest.getResourceID().equals("finishTry")){
			
			resourceResponse.setContentType("application/json");
			JSONObject oreturned = JSONFactoryUtil.createJSONObject();
			
			long score = ParamUtil.getLong(resourceRequest, "score");
			double position = ParamUtil.getDouble(resourceRequest, "position");
			int plays = ParamUtil.getInteger(resourceRequest, "plays");
			long actId = ParamUtil.getLong(resourceRequest, "actId");
			
			try {
			
				LearningActivity activity = LearningActivityLocalServiceUtil.getLearningActivity(actId);
				ResourceExternalActivityTypeFactory resourceExternalActivityTypeFactory = new ResourceExternalActivityTypeFactory();
				ResourceExternalActivityType resourceExternalActivityType = resourceExternalActivityTypeFactory.getResourceExternalActivityType(activity);
				
				LearningActivityTry activityTry = LearningActivityTryLocalServiceUtil.getLastLearningActivityTry(actId, themeDisplay.getUserId());
				
				log.debug("***updateCorrect*** " + activityTry + " - " + score + " - " + position + " - " + plays);
				
				String xml = activityTry.getTryResultData();
				
				if(!xml.equals("")){

					try {
						Document documentTry = SAXReaderUtil.read(xml);
						Element rootElement = documentTry.getRootElement();
						Element positionElement = rootElement.element("position");
						if(positionElement == null){
							positionElement = SAXReaderUtil.createElement("position");
							rootElement.add(positionElement);
						}
						positionElement.setText(String.valueOf(position));
						
						Element scoreElement = rootElement.element("score");
						if(scoreElement == null){
							scoreElement = SAXReaderUtil.createElement("score");
							rootElement.add(scoreElement);
						}
						scoreElement.setText(String.valueOf(score));
						
						Element playsElement = rootElement.element("plays");
						if(playsElement == null){
							playsElement = SAXReaderUtil.createElement("plays");
							rootElement.add(playsElement);
						}
						playsElement.setText(String.valueOf(plays));	
						
						activityTry.setTryResultData(documentTry.formattedString());
						
					} catch (DocumentException e) {
						e.printStackTrace();
					}

				}else{
					Element rootElement = SAXReaderUtil.createElement("result");
					Document resultadoXMLDoc=SAXReaderUtil.createDocument(rootElement);
					
					Element positionXML=SAXReaderUtil.createElement("position");
					positionXML.setText(String.valueOf(position));		
					rootElement.add(positionXML);
					
					Element scoreXML=SAXReaderUtil.createElement("score");
					scoreXML.setText(String.valueOf(score));		
					rootElement.add(scoreXML);
					
					Element playsXML=SAXReaderUtil.createElement("plays");
					playsXML.setText(String.valueOf(plays));		
					rootElement.add(playsXML);		
					
					activityTry.setTryResultData(resultadoXMLDoc.formattedString());
				}

			
				double result = resourceExternalActivityType.calculateResult(activityTry);
				log.debug("result: " + result);
				
				ServiceContext serviceContext = ServiceContextFactory.getInstance(resourceRequest);
				
				activityTry = LearningActivityTryLocalServiceUtil.finishLearningActivityTry(activityTry, result, serviceContext);
				
				int correctMode = resourceExternalActivityType.getCorrectMode();
				log.debug("CORRECT MODE "+correctMode);
				
				if(correctMode == ResourceExternalConstants.CORRECT_QUESTIONS){
					log.debug("--correctQUESTIONS!" );
					String feedback = "<span class=\"result-activity\">"+ LanguageUtil.get(themeDisplay.getLocale(), "evaluationtaskactivity.result.youresult") +" <strong>"+result+"</strong></span>";
					List<Question> questions = QuestionLocalServiceUtil.getQuestions(activity.getActId());

					QuestionTypeFactory questionTypeFactory = null;
					QuestionType questionType = null;
					
					oreturned.put("questionCorrection",true);
					oreturned.put("finalFeedback", resourceExternalActivityType.getFinalFeedback());
					oreturned.put("feedback", feedback);
				}else{
					oreturned.put("questionCorrection",false);
				}
				
			} catch (PortalException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				PrintWriter out = resourceResponse.getWriter();
				out.print(oreturned.toString());
				out.flush();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(resourceRequest.getResourceID() != null && resourceRequest.getResourceID().equals("saveQuestion")){
			
			resourceResponse.setContentType("application/json");
			JSONObject oreturned = JSONFactoryUtil.createJSONObject();
			
			long questionId = ParamUtil.getLong(resourceRequest, "questionId", 0);
			long actId = ParamUtil.getLong(resourceRequest, "actId");
			
			try {
				LearningActivityTry lat = LearningActivityTryLocalServiceUtil.getLastLearningActivityTry(actId, themeDisplay.getUserId());
				
				//Añadimos la respuesta al data del try
				String tryResultData = lat.getTryResultData();
				log.debug("tryResultData: " + tryResultData);
				Document resultXMLDoc = null;
				Element resultXML = null;
				try {
					resultXMLDoc = SAXReaderUtil.read(tryResultData);
					resultXML = resultXMLDoc.getRootElement();
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					log.debug("no hemos guardado ninguna pregunta todavia");
				}
				if(resultXMLDoc == null || resultXML == null){
					log.debug("creamos result porque no existe");
					resultXML=SAXReaderUtil.createElement("result");
					resultXMLDoc=SAXReaderUtil.createDocument(resultXML);
				}
				
				Question question = QuestionLocalServiceUtil.fetchQuestion(questionId);
				QuestionType qt = question.getQuestionType();
				Element resultsElement = qt.getResults(resourceRequest);
				//Borramos la repuesta anterior si existe
				Iterator<Element> nodeItr = resultXML.elementIterator();
				boolean finded = false;
				Element element = null;
				while(nodeItr.hasNext() && !finded) {
					element = nodeItr.next();
					if("question".equals(element.getName()) && questionId == Long.valueOf(element.attributeValue("id"))){
						resultXML.remove(element);
						finded = true;
					}
				}
				
				resultXML.add(resultsElement);
				
				lat.setTryResultData(resultXMLDoc.formattedString());
				
				ServiceContext serviceContext = ServiceContextFactory.getInstance(resourceRequest);
				LearningActivityTryLocalServiceUtil.updateLearningActivityTry(lat, lat.getResult(), serviceContext);
				
				LearningActivity activity = LearningActivityLocalServiceUtil.getLearningActivity(ParamUtil.getLong(resourceRequest, "actId"));
				ResourceExternalActivityTypeFactory resourceExternalActivityTypeFactory = new ResourceExternalActivityTypeFactory();
				ResourceExternalActivityType resourceExternalActivityType = resourceExternalActivityTypeFactory.getResourceExternalActivityType(activity);
				
				oreturned.put("questionFeedback", resourceExternalActivityType.getQuestionFeedback());
				oreturned.put("correct", true);
				
			} catch (PortalException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				PrintWriter out = resourceResponse.getWriter();
				out.print(oreturned.toString());
				out.flush();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			super.serveResource(resourceRequest, resourceResponse);
		}
	}
	
	public void updateResult(ActionRequest actionRequest,ActionResponse actionResponse) {
		long actId=ParamUtil.getLong(actionRequest, "actId");
		long studentId = ParamUtil.getLong(actionRequest,"studentId");	
		String comments = ParamUtil.getString(actionRequest,"comments");
		
		log.debug("actId: " + actId);
		log.debug("studentId: " + studentId);
		log.debug("comments: " + comments);
		
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		//Obtenemos el método de calificación para obtener el valor
		Course course = courseLocalService.getCourseByGroupCreatedId(themeDisplay.getScopeGroupId());
		CalificationTypeFactory calificationTypeFactory = CalificationTypeFactoryRegistryUtil.getCalificationTypeFactoryByType(course.getCalificationType());
		CalificationType calificationType = calificationTypeFactory.getCalificationType(course);
		
		try {
		
			double result = calificationType.getResultBase100(actionRequest);
			
			log.debug("result base 100: " + result);
			
			LearningActivityTry  learningActivityTry =  learningActivityTryLocalService.getLastLearningActivityTry(actId, studentId);
			ServiceContext serviceContext = ServiceContextFactory.getInstance(LearningActivityTry.class.getName(), actionRequest);
			if(learningActivityTry==null){
				learningActivityTry = learningActivityTryLocalService.addLearningActivityTry(actId, studentId, serviceContext);
				log.debug("creamos learningActivityTryId: " + learningActivityTry.getLatId());
			}else {
				log.debug("learningActivityTryId: " + learningActivityTry.getLatId());
			}
			
			learningActivityTry.setComments(comments);
			learningActivityTryLocalService.finishLearningActivityTry(learningActivityTry, result, serviceContext);
			
			LearningActivityResult learningActivityResult = learningActivityResultLocalService.getLearningActivityResult(actId, studentId);
			String status="status.not-attempted";
			if(learningActivityResult != null){
				status="status.incomplete";
				
				if(learningActivityResult.getEndDate()!=null){
					status="status.failed"	;
				}
				if(learningActivityResult.isPassed()){
					status="status.passed"	;
				}
			}	
			
			actionResponse.setRenderParameter("result", calificationType.translate(themeDisplay.getLocale(), learningActivityResult.getResult()));
			actionResponse.setRenderParameter("status", status);
			SessionMessages.add(actionRequest, "resultEdited");
		}catch(PortalException e) {
			SessionErrors.add(actionRequest, "grades.bad-updating");
		}

		actionResponse.setRenderParameter("mvcPath", "/edit_result.jsp");
	}
	
	@Reference(unbind = "-")
	protected void setCourseLocalService(CourseLocalService courseLocalService) {
		this.courseLocalService = courseLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setLearningActivityTryLocalService(LearningActivityTryLocalService learningActivityTryLocalService) {
		this.learningActivityTryLocalService = learningActivityTryLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setLearningActivityResultLocalService(LearningActivityResultLocalService learningActivityResultLocalService) {
		this.learningActivityResultLocalService = learningActivityResultLocalService;
	}
	
	private CourseLocalService courseLocalService;
	private LearningActivityTryLocalService learningActivityTryLocalService;
	private LearningActivityResultLocalService learningActivityResultLocalService;
	
}