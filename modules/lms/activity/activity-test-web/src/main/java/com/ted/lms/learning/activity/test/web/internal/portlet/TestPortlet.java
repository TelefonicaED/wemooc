package com.ted.lms.learning.activity.test.web.internal.portlet;

import com.ted.lms.constants.LMSActionKeys;
import com.ted.lms.learning.activity.question.model.Question;
import com.ted.lms.learning.activity.question.model.QuestionType;
import com.ted.lms.learning.activity.question.service.QuestionLocalServiceUtil;
import com.ted.lms.learning.activity.test.web.activity.TestActivityType;
import com.ted.lms.learning.activity.test.web.activity.TestActivityTypeFactory;
import com.ted.lms.learning.activity.test.web.constants.TestPortletKeys;
import com.ted.lms.model.CalificationType;
import com.ted.lms.model.CalificationTypeFactory;
import com.ted.lms.model.Course;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityResult;
import com.ted.lms.model.LearningActivityTry;
import com.ted.lms.registry.CalificationTypeFactoryRegistryUtil;
import com.ted.lms.security.permission.resource.LMSPermission;
import com.ted.lms.security.permission.resource.LearningActivityPermission;
import com.ted.lms.service.CourseLocalServiceUtil;
import com.ted.lms.service.LearningActivityLocalServiceUtil;
import com.ted.lms.service.LearningActivityResultLocalServiceUtil;
import com.ted.lms.service.LearningActivityTryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author JE10436
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.mvc-command-names-default-views=/activities/test/view_activity",
		"javax.portlet.name=" + TestPortletKeys.TEST,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=user",
		"javax.portlet.supported-public-render-parameter=actId",
		"com.liferay.portlet.add-default-resource=true"
	},
	service = Portlet.class
)
public class TestPortlet extends MVCPortlet {
	
	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {
		
		ParamUtil.print(renderRequest);
		long actId = ParamUtil.getLong(renderRequest, "actId", 0);

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		String mvcRenderCommandName = ParamUtil.getString(renderRequest, "mvcRenderCommandName", "/");
		
	
		if (actId == 0) {
			renderRequest.setAttribute(WebKeys.PORTLET_CONFIGURATOR_VISIBILITY, Boolean.FALSE);
		}else {
			
			LearningActivity activity = LearningActivityLocalServiceUtil.fetchLearningActivity(actId);
			Course course = CourseLocalServiceUtil.getCourseByGroupCreatedId(themeDisplay.getScopeGroupId());
			
			renderRequest.setAttribute("activity", activity);
			renderRequest.setAttribute("actId", actId);
			
			boolean improving = false;
			LearningActivityResult result = LearningActivityResultLocalServiceUtil.getLearningActivityResult(actId, themeDisplay.getUserId());
			renderRequest.setAttribute("learningActivityResult", result);
			
			boolean isLocked = true;
			try {
				isLocked = activity.isLocked(themeDisplay.getUser(), themeDisplay.getPermissionChecker());
			} catch (PortalException e1) {
				e1.printStackTrace();
			}
			
			if(result != null){
				int done =  LearningActivityTryLocalServiceUtil.getLearningActivityTriesCount(actId,themeDisplay.getUserId());
				
				if(result.getResult() < 100 && !isLocked && result.isPassed() && (done < activity.getTries() || activity.getTries() == 0)){
					improving = true;
				}
			}
			
			boolean isTeacher = LMSPermission.contains(themeDisplay.getPermissionChecker(), themeDisplay.getScopeGroupId(), LMSActionKeys.VIEW_RESULTS);
			boolean improve =ParamUtil.getBoolean(renderRequest, "improve",false);
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
			
			TestActivityTypeFactory testActivityTypeFactory = new TestActivityTypeFactory();
			TestActivityType testActivityType = testActivityTypeFactory.getTestActivityType(activity); 
			
			renderRequest.setAttribute("testActivityType", testActivityType);
			renderRequest.setAttribute("course", course);
			renderRequest.setAttribute("improve", improve);
			renderRequest.setAttribute("hasPermissionAccessCourseFinished", hasPermissionAccessCourseFinished);
			renderRequest.setAttribute("result", result);
			
			if(Validator.isNull(mvcRenderCommandName)) {
				
				//Si ya hemos terminado la actividad y no podemos subir nota ni acceder a las actividades después de terminar el curso muestro los 
				//resultados
				if(!improve && result != null && result.isPassed() && !hasPermissionAccessCourseFinished) {
					
					renderRequest.setAttribute("mvcRenderCommandName", "/activities/test/results");
					renderRequest.setAttribute("isLocked", isLocked);
				}else {
					
					boolean canUserDoNewTry = testActivityType.hasTries(themeDisplay.getUserId()) && !isLocked;
					
					//Si todavía tengo intentos
					if(canUserDoNewTry || LearningActivityPermission.contains(themeDisplay.getPermissionChecker(), activity, LMSActionKeys.UPDATE)
							|| LMSPermission.contains(themeDisplay.getPermissionChecker(), themeDisplay.getScopeGroupId(), LMSActionKeys.ACCESSLOCK)
							|| improving || hasPermissionAccessCourseFinished) {
						
						if((activity.getTries() > 0 && !canUserDoNewTry) || testActivityType.getPreview()) {
							renderRequest.setAttribute("canUserDoNewTry", canUserDoNewTry);
							renderRequest.setAttribute("mvcRenderCommandName", "/activities/test/view_activity");
						}else {
							String navigateParam = ParamUtil.getString(renderRequest, "navigate");
							String passwordParam = ParamUtil.getString(renderRequest, "password",StringPool.BLANK).trim();
							String password = testActivityType.getPassword();
							
							if(Validator.isNotNull(navigateParam) || Validator.isNull(password) || passwordParam.equals(password)){
								
								long activityTimestamp=0;
								long timestamp=0;			
								LearningActivityTry learningTry = LearningActivityTryLocalServiceUtil.getLearningActivityTryNotFinishedByActUser(actId,themeDisplay.getUserId());
								try {
									//Comprobar si tenemos un try sin fecha de fin, para continuar en ese try.
									if(learningTry == null && !hasPermissionAccessCourseFinished){
										ServiceContext serviceContext = ServiceContextFactory.getInstance(LearningActivityTry.class.getName(), renderRequest);
										learningTry =LearningActivityTryLocalServiceUtil.addLearningActivityTry(actId, themeDisplay.getUserId(), serviceContext);
										
									}else if(!hasPermissionAccessCourseFinished){
										activityTimestamp = testActivityType.getTimeStamp();
										timestamp = activityTimestamp*1000 - (new Date().getTime() - learningTry.getStartDate().getTime());
									}
									
									if(activityTimestamp!=0 && timestamp<0){
										if(learningTry.getEndDate()!=null) {
											timestamp=activityTimestamp*1000 - (learningTry.getEndDate().getTime() - learningTry.getStartDate().getTime());
										}
										
										try {
										
											if(timestamp<0) { 
												long random = testActivityType.getRandom();
												String tryResultData = StringPool.BLANK;
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
												LearningActivityTryLocalServiceUtil.finishLearningActivityTry(learningTry, 0, endDate, serviceContext);
											}
										} catch (IOException e) {
											e.printStackTrace();
										}
										
										renderRequest.setAttribute("mvcRenderCommandName", "/activities/test/results");
										renderRequest.setAttribute("hasPermissionAccessCourseFinished", hasPermissionAccessCourseFinished);
										renderRequest.setAttribute("isLocked", isLocked);
										renderRequest.setAttribute("larntry", learningTry);
									}else {
										renderRequest.setAttribute("timestamp", timestamp);
										renderRequest.setAttribute("activityTimestamp", activityTimestamp);
										renderRequest.setAttribute("larntry", learningTry);
										renderRequest.setAttribute("canUserDoNewTry", canUserDoNewTry);
										renderRequest.setAttribute("mvcRenderCommandName", "/activities/test/view_test");
									}
								} catch (PortalException e) {
									e.printStackTrace();
								}
								
							//Muestro introducir la contraseña
							}else {
								renderRequest.setAttribute("mvcRenderCommandName", "/activities/test/password");
							}
						}
					}else {
						renderRequest.setAttribute("mvcRenderCommandName", "/activities/test/finish_without_triesy");
					}
				}
			}
		}

		
		super.render(renderRequest, renderResponse);
	}
	
	
	public void correct	(ActionRequest actionRequest,ActionResponse actionResponse)	throws Exception {
		
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		long actId=ParamUtil.getLong(actionRequest, "actId");
		long latId=ParamUtil.getLong(actionRequest,"latId" );

		String navigate = ParamUtil.getString(actionRequest, "navigate");
		boolean isPartial = false;
		if (Validator.isNotNull(navigate)) {
			if ("backward".equals(navigate) || "forward".equals(navigate)) {
				isPartial = true;
			}
		}

		LearningActivityTry larntry=LearningActivityTryLocalServiceUtil.getLearningActivityTry(latId);

		//Comprobar que el usuario tenga intentos posibles.
		if (larntry.getEndDate() == null){

			long correctanswers=0,penalizedAnswers=0;
			Element resultadosXML=SAXReaderUtil.createElement("results");
			Document resultadosXMLDoc=SAXReaderUtil.createDocument(resultadosXML);

			long[] questionIds = ParamUtil.getLongValues(actionRequest, "question");

			for (long questionId : questionIds) {
				Question question = QuestionLocalServiceUtil.fetchQuestion(questionId);
				QuestionType qt = question.getQuestionType();
				resultadosXML.add(qt.getResults(actionRequest));								
			}

			LearningActivity activity = LearningActivityLocalServiceUtil.getLearningActivity(actId);
			TestActivityType lat = new TestActivityType(activity);
			
			if(log.isDebugEnabled())
				log.debug(String.format("\n\tisPartial: %s\n\tcorrectanswers: %s\n\tpenalizedAnswers: %s\n\tquestionIds.length: %s", isPartial, correctanswers, penalizedAnswers, questionIds.length));
			// penalizedAnswers tiene valor negativo, por eso se suma a correctanswers
			
			
			LearningActivityResult learningActivityResult = LearningActivityResultLocalServiceUtil.getLearningActivityResult(actId, themeDisplay.getUserId());
			double oldResult=-1;
			if(learningActivityResult!=null) oldResult=learningActivityResult.getResult();

			larntry.setTryResultData(resultadosXMLDoc.formattedString());
			
			double score=isPartial ? 0 : lat.calculateResult(larntry) ;
			if(log.isDebugEnabled())
				log.debug("Score: " + score);

			if(score < 0)score = 0;
			
			if (!isPartial) {
				larntry.setResult(score);
				larntry.setEndDate(new java.util.Date(System.currentTimeMillis()));
			}

			LearningActivityTryLocalServiceUtil.updateLearningActivityTry(larntry);

			actionResponse.setRenderParameters(actionRequest.getParameterMap());

			if (isPartial) {
				actionResponse.setRenderParameter("improve", ParamUtil.getString(actionRequest, "improve", Boolean.FALSE.toString()));
			} else {
				actionResponse.setRenderParameter("oldResult", Double.toString(oldResult));
				actionResponse.setRenderParameter("correction", Boolean.toString(true));
			}
		}else{
			actionResponse.setRenderParameters(actionRequest.getParameterMap());
		}	
		actionRequest.setAttribute("actId", actId);
		actionResponse.setRenderParameter("mvcRenderCommandName", "/activities/test/view_activity");
	}
	
	/**
	 * Correcci�n para cuando estamos en modo observador ya que no se tiene que guardar nada en learningactivitytry
	 * @param actionRequest
	 * @param actionResponse
	 * @throws PortalException 
	 * @throws SystemException 
	 * @throws Exception
	 */
	
	public void correctAccessFinished	(ActionRequest actionRequest,ActionResponse actionResponse) throws PortalException {

		long actId=ParamUtil.getLong(actionRequest, "actId");

		boolean isTablet = ParamUtil.getBoolean(actionRequest,"isTablet" );

		long correctanswers=0,penalizedAnswers=0;
		Element resultadosXML=SAXReaderUtil.createElement("results");
		Document resultadosXMLDoc=SAXReaderUtil.createDocument(resultadosXML);

		long[] questionIds = ParamUtil.getLongValues(actionRequest, "question");


		for (long questionId : questionIds) {
			Question question = QuestionLocalServiceUtil.fetchQuestion(questionId);
			QuestionType qt = question.getQuestionType();
			if(qt.isCorrect(actionRequest)>0) {
				correctanswers++;
			}else if(question.isPenalize()){
				penalizedAnswers++;
			}
			resultadosXML.add(qt.getResults(actionRequest));								
		}

		List<Question> questions=QuestionLocalServiceUtil.getQuestions(actId);
		long score = (correctanswers-penalizedAnswers)*100/questions.size();
		if(score < 0)score = 0;
		
		
		actionResponse.setRenderParameters(actionRequest.getParameterMap());

		actionResponse.setRenderParameter("correction", Boolean.toString(true));
		if(isTablet)actionResponse.setRenderParameter("isTablet", Boolean.toString(true));
		try {
			//actionResponse.setRenderParameter("tryResultData", resultadosXMLDoc.formattedString());
			actionResponse.setRenderParameter("tryResultData", resultadosXMLDoc.formattedString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		actionResponse.setRenderParameter("score", String.valueOf(score));
		actionRequest.setAttribute("actId", actId);
		actionResponse.setRenderParameter("mvcRenderCommandName", "/activities/test/view_activity");

	}
	
	private static final Log log = LogFactoryUtil.getLog(TestPortlet.class);
}