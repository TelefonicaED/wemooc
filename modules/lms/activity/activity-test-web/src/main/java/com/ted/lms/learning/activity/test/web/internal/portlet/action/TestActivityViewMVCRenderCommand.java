package com.ted.lms.learning.activity.test.web.internal.portlet.action;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
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
import com.ted.lms.learning.activity.question.service.QuestionLocalService;
import com.ted.lms.learning.activity.test.web.activity.TestActivityType;
import com.ted.lms.learning.activity.test.web.activity.TestActivityTypeFactory;
import com.ted.lms.learning.activity.test.web.constants.TestConstants;
import com.ted.lms.learning.activity.test.web.constants.TestPortletKeys;
import com.ted.lms.model.CalificationType;
import com.ted.lms.model.CalificationTypeFactory;
import com.ted.lms.model.Course;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityResult;
import com.ted.lms.model.LearningActivityTry;
import com.ted.lms.registry.CalificationTypeFactoryRegistryUtil;
import com.ted.lms.registry.LearningActivityTypeFactoryRegistryUtil;
import com.ted.lms.security.permission.resource.LMSPermission;
import com.ted.lms.security.permission.resource.LearningActivityPermission;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.service.LearningActivityLocalService;
import com.ted.lms.service.LearningActivityResultLocalService;
import com.ted.lms.service.LearningActivityTryLocalService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true, 
	property = { "javax.portlet.name=" + TestPortletKeys.TEST,
			"mvc.command.name=/",
			"mvc.command.name=/activities/test/view_activity" }, 
	service = MVCRenderCommand.class
)
public class TestActivityViewMVCRenderCommand implements MVCRenderCommand {
	
	private static final Log log = LogFactoryUtil.getLog(TestActivityViewMVCRenderCommand.class);

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {
		
		long actId = ParamUtil.getLong(renderRequest, "actId", 0);

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
	
		if (actId == 0) {
			renderRequest.setAttribute(WebKeys.PORTLET_CONFIGURATOR_VISIBILITY, Boolean.FALSE);
			return null;
		}else {
			
			LearningActivity activity = learningActivityLocalService.fetchLearningActivity(actId);
			Course course = courseLocalService.getCourseByGroupCreatedId(themeDisplay.getScopeGroupId());
			
			renderRequest.setAttribute("activity", activity);
			renderRequest.setAttribute("actId", actId);
			
			boolean improving = false;
			LearningActivityResult result = learningActivityResultLocalService.getLearningActivityResult(actId, themeDisplay.getUserId());
			renderRequest.setAttribute("learningActivityResult", result);
			
			boolean isLocked = true;
			try {
				isLocked = activity.isLocked(themeDisplay.getUser(), themeDisplay.getPermissionChecker());
			} catch (PortalException e1) {
				e1.printStackTrace();
			}
			
			if(result != null){
				int done =  learningActivityTryLocalService.getLearningActivityTriesCount(actId,themeDisplay.getUserId());
				
				if(result.getResult() < 100 && !isLocked && result.isPassed() && (done < activity.getTries() || activity.getTries() == 0)){
					improving = true;
				}
			}
			
			boolean isTeacher = LMSPermission.contains(themeDisplay.getPermissionChecker(), themeDisplay.getScopeGroupId(), LMSActionKeys.VIEW_RESULTS);
			boolean improve =ParamUtil.getBoolean(renderRequest, "improve",false);
			boolean started = ParamUtil.getBoolean(renderRequest, "started");
			boolean hasPermissionAccessCourseFinished = false;
			try {
				hasPermissionAccessCourseFinished = course.hasPermissionAccessCourseFinished(themeDisplay.getUserId());
			} catch (PortalException e) {
				e.printStackTrace();
			}
			
			if(isTeacher) {
				PortletURL goToCorrectionURL = renderResponse.createRenderURL();
				goToCorrectionURL.setParameter("mvcRenderCommandName", "/activities/test/correction");
				goToCorrectionURL.setParameter("actId", String.valueOf(actId));
				renderRequest.setAttribute("goToCorrectionURL", goToCorrectionURL);
			}
			
			renderRequest.setAttribute("isTeacher", isTeacher);
			
			TestActivityTypeFactory testActivityTypeFactory = (TestActivityTypeFactory)LearningActivityTypeFactoryRegistryUtil.getLearningActivityTypeFactoryByType(TestConstants.TYPE);
			TestActivityType testActivityType = testActivityTypeFactory.getTestActivityType(activity); 
			
			
			String mvcRender = ParamUtil.getString(renderRequest, "mvcRender");
			
			//Si ya hemos terminado la actividad y no podemos subir nota ni acceder a las actividades después de terminar el curso muestro los 
			//resultados
			if(Validator.isNotNull(mvcRender) && mvcRender.equals("results") || (!improve && result != null && result.isPassed() && !hasPermissionAccessCourseFinished)) {
				
				LearningActivityTry learningActivityTry = learningActivityTryLocalService.getLastLearningActivityTryFinished(actId, themeDisplay.getUserId());
				return doRenderResults(renderRequest, renderResponse, hasPermissionAccessCourseFinished, learningActivityTry, testActivityType, course, isLocked, null);
				
			}else {
				
				boolean canUserDoNewTry = testActivityType.hasTries(themeDisplay.getUserId()) && !isLocked;
				
				//Si todavía tengo intentos
				if(canUserDoNewTry || LearningActivityPermission.contains(themeDisplay.getPermissionChecker(), activity, LMSActionKeys.UPDATE)
						|| LMSPermission.contains(themeDisplay.getPermissionChecker(), themeDisplay.getScopeGroupId(), LMSActionKeys.ACCESSLOCK)
						|| improving || hasPermissionAccessCourseFinished) {
					
					LearningActivityTry learningTry = learningActivityTryLocalService.getLearningActivityTryNotFinishedByActUser(actId,themeDisplay.getUserId());
					
					if(!improve && !started && learningTry == null && ((activity.getTries() > 0 && !canUserDoNewTry) || testActivityType.getPreview())) {
						
						return doRenderPreview(renderRequest, renderResponse, activity, canUserDoNewTry);
					}else {
						String navigateParam = ParamUtil.getString(renderRequest, "navigate");
						String passwordParam = ParamUtil.getString(renderRequest, "password",null);
						String password = testActivityType.getPassword();
						
						if(Validator.isNotNull(navigateParam) || Validator.isNull(password) || (passwordParam != null && passwordParam.equals(password))){
							
							long activityTimestamp=0;
							long timestamp=0;			
							
							try {
								//Comprobar si tenemos un try sin fecha de fin, para continuar en ese try.
								if(learningTry == null && !hasPermissionAccessCourseFinished){
									ServiceContext serviceContext = ServiceContextFactory.getInstance(LearningActivityTry.class.getName(), renderRequest);
									learningTry =learningActivityTryLocalService.addLearningActivityTry(actId, themeDisplay.getUserId(), serviceContext);
									
								}
								if(!hasPermissionAccessCourseFinished){
									activityTimestamp = testActivityType.getTimeStamp();
									timestamp = activityTimestamp*1000 - (new Date().getTime() - learningTry.getStartDate().getTime());
								}
								
								if(activityTimestamp!=0 && timestamp<0){
									if(learningTry.getEndDate()!=null) {
										timestamp=activityTimestamp*1000 - (learningTry.getEndDate().getTime() - learningTry.getStartDate().getTime());
									}
									
									String tryResultData = StringPool.BLANK;
									
									try {
									
										if(timestamp<0) { 
											long random = testActivityType.getRandom();
											
											if(random != 0) {
												
												List<Question> questions = new ArrayList<Question>(testActivityType.getQuestions());
												Collections.shuffle(questions);	
												
												if(random>questions.size()) {
													random = questions.size();
												}
												
												Element resultadosXML=SAXReaderUtil.createElement("results");
												Document resultadosXMLDoc=SAXReaderUtil.createDocument(resultadosXML);
												
												Question question = null;
												Element questionXML = null;
												for(int index=0; index<random; index++) {
													question = questions.get(index);
													questionXML=SAXReaderUtil.createElement("question");
													questionXML.addAttribute("id", Long.toString(question.getQuestionId()));
													resultadosXML.add(questionXML);	
												}
												
												tryResultData = resultadosXMLDoc.formattedString();
											}
											
											Date endDate = new Date(learningTry.getStartDate().getTime()+activityTimestamp);
											ServiceContext serviceContext = ServiceContextFactory.getInstance(LearningActivityTry.class.getName(), renderRequest);
											learningActivityTryLocalService.finishLearningActivityTry(learningTry, 0, endDate, serviceContext);
										}
									} catch (IOException e) {
										e.printStackTrace();
									}
									
									return doRenderResults(renderRequest, renderResponse, hasPermissionAccessCourseFinished, learningTry, testActivityType, course, isLocked, tryResultData);
									
								}else {
									return doRenderViewTest(renderRequest, renderResponse, hasPermissionAccessCourseFinished, testActivityType, learningTry, 
											activity, result, improve, activityTimestamp, timestamp, canUserDoNewTry);
								}
							} catch (PortalException e) {
								e.printStackTrace();
								return "/error.jsp";
							}
							
						//Muestro introducir la contraseña
						}else {
							if(passwordParam != null &&  !passwordParam.trim().equals(password)) {
								SessionErrors.add(renderRequest, "password-incorrect");
							}
							PortletURL passwordURL = renderResponse.createRenderURL();
							passwordURL.setParameter("mvcRenderCommandName", "/activities/test/view_activity");
							if(improve) {
								passwordURL.setParameter("improve", "true");		
							}
							renderRequest.setAttribute("passwordURL", passwordURL);
							
							return "/password.jsp";
						}
					}
				}else {
					boolean hasFreeQuestion = testActivityType.hasFreeQuestions();
					
					if(!hasFreeQuestion) {
						CalificationTypeFactory calificationTypeFactory = CalificationTypeFactoryRegistryUtil.getCalificationTypeFactoryByType(course.getCalificationType());
						CalificationType calificationType = calificationTypeFactory.getCalificationType(course);
						renderRequest.setAttribute("calificationType", calificationType);
					}
					
					renderRequest.setAttribute("hasFreeQuestion", hasFreeQuestion);
					
					return "/finish_without_tries.jsp";
				}
			}
		}
	}
	
	private String doRenderViewTest(RenderRequest renderRequest, RenderResponse renderResponse, boolean hasPermissionAccessCourseFinished,
			TestActivityType testActivityType, LearningActivityTry learningTry, LearningActivity activity, LearningActivityResult result,
			boolean improve, long activityTimestamp, long timestamp, boolean canUserDoNewTry) {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		List<Question> questions = questionLocalService.getQuestionsOrder(activity.getActId());
		
		long random = 0;
		if(!hasPermissionAccessCourseFinished){
			random = testActivityType.getRandom();
		}
		
		long currentQuestionId = 0;
		if (learningTry != null && Validator.isXml(learningTry.getTryResultData())) {
			try {
				String tryResultData = learningTry.getTryResultData();
				Document docQuestions = SAXReaderUtil.read(tryResultData);
				List<Element> xmlQuestions = docQuestions.getRootElement().elements("question");
				
				Map<Long, Question> questionMap = new HashMap<Long, Question>();
				for (Question question : questions) {
					questionMap.put(question.getQuestionId(), question);
				}
				questions = new ArrayList<Question>();
				for (Element xmlQuestion : xmlQuestions) {
					String questionIdString = xmlQuestion.attributeValue("id");
					if (Validator.isNotNull(questionIdString) && Validator.isNumber(questionIdString)) {
						Long questionId = Long.valueOf(questionIdString);
						Question testQuestion = questionMap.get(questionId);
						if (testQuestion != null) {
							questions.add(testQuestion);
							String currentString = xmlQuestion.attributeValue("current");
							if ("true".equals(currentString)) {
								currentQuestionId = questionId;
							}
						}
					}
				}
				random = questions.size();
			}catch (DocumentException e) {
				e.printStackTrace();
				questions = null;
			}
		} else {
			if (random != 0){
				questions = new ArrayList<Question>(questions);
				Collections.shuffle(questions);	
				if (random > questions.size()){
					random=questions.size();
				}
			}else{
				random=questions.size();
			}
		}
		
		long limitChunk = testActivityType.getQuestionsPerPage() == 0 ? random : testActivityType.getQuestionsPerPage();
		if (limitChunk == 0) {
			limitChunk = 1;
		}
		
		long totalPages = (random / limitChunk) + (random % limitChunk != 0 ? 1 : 0);
		long width_frame = 10000 / totalPages;
		
		String tryResultData = "";
		if (!hasPermissionAccessCourseFinished && Validator.isXml(learningTry.getTryResultData())) {
			tryResultData = learningTry.getTryResultData();
		}
		renderRequest.setAttribute("tryResultData", tryResultData);
		
		renderRequest.setAttribute("isPassed", result != null && result.isPassed());
		renderRequest.setAttribute("hasPermissionAccessCourseFinished", hasPermissionAccessCourseFinished);
		
		PortletURL correctURL = renderResponse.createActionURL();
		correctURL.setParameter("javax.portlet.action", "/activities/test/correct_test");
		correctURL.setParameter("actId", String.valueOf(activity.getActId()));
		correctURL.setParameter("improve", String.valueOf(improve));
		if(!hasPermissionAccessCourseFinished) {
			correctURL.setParameter("latId", String.valueOf(learningTry.getLatId()));
		}
		renderRequest.setAttribute("correctURL", correctURL);
		
		PortletURL correctAccessFinishedURL = renderResponse.createActionURL();
		correctAccessFinishedURL.setParameter("javax.portlet.action", "correctAccessFinished");
		correctAccessFinishedURL.setParameter("actId", String.valueOf(activity.getActId()));
		correctAccessFinishedURL.setParameter("improve", String.valueOf(improve));
		renderRequest.setAttribute("correctAccessFinishedURL", correctAccessFinishedURL);
		
		log.debug("****activityTimestamp: " + activityTimestamp);
		log.debug("****timestamp: " + timestamp);
		
		
		renderRequest.setAttribute("activityTimestamp", activityTimestamp);
		renderRequest.setAttribute("timestamp", timestamp);
		renderRequest.setAttribute("questions", questions);
		renderRequest.setAttribute("currentQuestionId", currentQuestionId);
		renderRequest.setAttribute("totalPages", totalPages);
		renderRequest.setAttribute("width_frame", width_frame);
		renderRequest.setAttribute("random", random);
		renderRequest.setAttribute("limitChunk", limitChunk);
		renderRequest.setAttribute("testActivityType", testActivityType);
		renderRequest.setAttribute("canUserDoNewTry", canUserDoNewTry);
		
		return "/test.jsp";
	}
	
	private String doRenderPreview(RenderRequest renderRequest, RenderResponse renderResponse, LearningActivity activity, boolean canUserDoNewTry) {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		int tries =  learningActivityTryLocalService.getLearningActivityTriesCount(activity.getActId(),themeDisplay.getUserId());
		
		Object[] argumentsTries =  new Object[]{tries,activity.getTries()};
		Object[] argumentsPassPuntuation =  new Object[]{activity.getPassPuntuation()};
		
		boolean hasQuestions = questionLocalService.getQuestionsCount(activity.getActId()) > 0;
		
		PortletURL correctURL = renderResponse.createRenderURL();
		correctURL.setParameter("actId", String.valueOf(activity.getActId()));
		correctURL.setParameter("improve", StringPool.TRUE);
		correctURL.setParameter("started", StringPool.TRUE);
		
		renderRequest.setAttribute("argumentsTries", argumentsTries);
		renderRequest.setAttribute("argumentsPassPuntuation", argumentsPassPuntuation);
		renderRequest.setAttribute("hasQuestions", hasQuestions);
		renderRequest.setAttribute("hasTries", canUserDoNewTry);
		renderRequest.setAttribute("correctURL", correctURL);
		
		return "/view.jsp";
	}
	
	private String doRenderResults(RenderRequest renderRequest, RenderResponse renderResponse, boolean hasPermissionAccessCourseFinished, 
			LearningActivityTry learningActivityTry, TestActivityType testActivityType, Course course, boolean isLocked, String tryResultData) {
		
		List<Question> questions = testActivityType.getQuestions();
		boolean hasFreeQuestions = testActivityType.hasFreeQuestions();
		
		long tries = 0;
		long userTries = 0;
		double score = 0;
		double scoreTry = 0;
		LearningActivity activity = testActivityType.getLearningActivity();
		
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		if(!hasPermissionAccessCourseFinished){
			tries = testActivityType.getLearningActivity().getTries();
			userTries = learningActivityTryLocalService.getLearningActivityTriesCount(activity.getActId(),themeDisplay.getUserId());
		
			LearningActivityResult result = learningActivityResultLocalService.getLearningActivityResult(activity.getActId(),themeDisplay.getUserId());
			score = result.getResult();
			scoreTry = learningActivityTry.getResult();
			tryResultData = learningActivityTry.getTryResultData();
		}else{
			score = ParamUtil.getLong(renderRequest, "score", 0);
			scoreTry = score;
			tryResultData = ParamUtil.getString(renderRequest, "tryResultData", "");
		}
		boolean userPassed=false;
		boolean showFeedback = testActivityType.getShowFeedback();
		
		boolean comesFromCorrection = ParamUtil.get(renderRequest, "correction", false);
		CalificationTypeFactory calificationTypeFactory = CalificationTypeFactoryRegistryUtil.getCalificationTypeFactoryByType(course.getCalificationType());
		CalificationType calificationType = calificationTypeFactory.getCalificationType(course);
		
		if(!comesFromCorrection) {
			userPassed=learningActivityResultLocalService.hasUserPassed(activity.getActId(),themeDisplay.getUserId());
		}else if(!hasPermissionAccessCourseFinished){
			userPassed=activity.getPassPuntuation()<=scoreTry;
			if(showFeedback){
				renderRequest.setAttribute("showPopupResult", true);
				renderRequest.setAttribute("hasFreeQuestion", testActivityType.hasFreeQuestions());
			}
		}
		
		renderRequest.setAttribute("userPassed", userPassed);
		
		if(showFeedback) {
			if(testActivityType.getRandom() > 0 && !hasPermissionAccessCourseFinished) {
				questions= new ArrayList<Question>();
				if(Validator.isNotNull(tryResultData)) {
					try {
						Iterator<Element> nodeItr = SAXReaderUtil.read(tryResultData).getRootElement().elementIterator();
						Question question=null;
						while(nodeItr.hasNext()) {
							Element element = nodeItr.next();				
							 if("question".equals(element.getName())) {
								 question=questionLocalService.fetchQuestion(Long.valueOf(element.attributeValue("id")));
								 if(question != null){
									 questions.add(question); 
								 }		        	 
							 }
						}
					} catch (DocumentException e) {
						e.printStackTrace();
					}
				}
				
				boolean canUserDoNewTry = testActivityType.hasTries(themeDisplay.getUserId()) && !isLocked;
				renderRequest.setAttribute("canUserDoNewTry",canUserDoNewTry);
					
			}
			if(Validator.isNotNull(tryResultData)) {
				try {
					Document documentTryResultData = SAXReaderUtil.read(tryResultData);
					renderRequest.setAttribute("documentTryResultData", documentTryResultData);
				} catch (DocumentException e) {
					e.printStackTrace();
				}
			}
		}
		
		if(tries==0 || userTries < tries || LearningActivityPermission.contains(themeDisplay.getPermissionChecker(), activity, LMSActionKeys.UPDATE)) {
			if(!learningActivityResultLocalService.hasUserPassed(activity.getActId(),themeDisplay.getUserId())){
				PortletURL tryTestURL = renderResponse.createRenderURL();
				tryTestURL.setParameter("actId", String.valueOf(activity.getActId()));
				renderRequest.setAttribute("tryTestURL", tryTestURL);
			}else if(score < 100 && testActivityType.getImprove()){
				PortletURL improveURL = renderResponse.createRenderURL();
				improveURL.setParameter("actId", String.valueOf(activity.getActId()));
				improveURL.setParameter("improve", StringPool.TRUE);
				renderRequest.setAttribute("improveURL", improveURL);
			}
		}
		
		boolean canUserDoNewTry = testActivityType.hasTries(themeDisplay.getUserId()) && !isLocked;
		
		renderRequest.setAttribute("hasFreeQuestions", hasFreeQuestions);
		renderRequest.setAttribute("calificationType", calificationType); 
		renderRequest.setAttribute("score", score);
		renderRequest.setAttribute("scoreTry", scoreTry);
		renderRequest.setAttribute("questions", questions);
		renderRequest.setAttribute("tries", tries);
		renderRequest.setAttribute("showFeedback", showFeedback);
		renderRequest.setAttribute("tryResultData", tryResultData);
		renderRequest.setAttribute("canUserDoNewTry", canUserDoNewTry);
		renderRequest.setAttribute("testActivityType", testActivityType);
		
		
		return "/results.jsp";
	}
	
	@Reference
	private CourseLocalService courseLocalService;
	@Reference
	private LearningActivityResultLocalService learningActivityResultLocalService;
	@Reference
	private LearningActivityTryLocalService learningActivityTryLocalService;
	@Reference
	private LearningActivityLocalService learningActivityLocalService;
	@Reference
	private QuestionLocalService questionLocalService;
}
