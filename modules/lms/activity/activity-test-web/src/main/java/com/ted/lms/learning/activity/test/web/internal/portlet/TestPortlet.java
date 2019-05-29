package com.ted.lms.learning.activity.test.web.internal.portlet;

import com.ted.lms.learning.activity.question.model.Question;
import com.ted.lms.learning.activity.question.model.QuestionType;
import com.ted.lms.learning.activity.question.service.QuestionLocalServiceUtil;
import com.ted.lms.learning.activity.test.web.constants.TestPortletKeys;
import com.ted.lms.model.CalificationType;
import com.ted.lms.model.CalificationTypeFactory;
import com.ted.lms.model.Course;
import com.ted.lms.model.LearningActivityResult;
import com.ted.lms.model.LearningActivityTry;
import com.ted.lms.registry.CalificationTypeFactoryRegistryUtil;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.service.LearningActivityResultLocalService;
import com.ted.lms.service.LearningActivityTryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import java.io.IOException;
import java.util.List;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
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
		"javax.portlet.init-param.template-path=/META-INF/resources/",
		"javax.portlet.init-param.mvc-command-names-default-views=/activities/test/view_activity",
		"javax.portlet.name=" + TestPortletKeys.TEST,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=user",
		"javax.portlet.supported-public-render-parameter=actId",
		"com.liferay.portlet.use-default-template=true"
	},
	service = Portlet.class
)
public class TestPortlet extends MVCPortlet {
	
	/**
	 * Correcci�n para cuando estamos en modo observador ya que no se tiene que guardar nada en learningactivitytry
	 * @param actionRequest
	 * @param actionResponse
	 * @throws PortalException 
	 * @throws SystemException 
	 * @throws Exception
	 */
	
	public void correctAccessFinished(ActionRequest actionRequest,ActionResponse actionResponse) throws PortalException {

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
	
	private static final Log log = LogFactoryUtil.getLog(TestPortlet.class);
	
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