package com.ted.lms.learning.activity.test.web.internal.portlet.action;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.ted.lms.learning.activity.question.model.Question;
import com.ted.lms.learning.activity.question.model.QuestionType;
import com.ted.lms.learning.activity.question.service.QuestionLocalServiceUtil;
import com.ted.lms.learning.activity.test.web.activity.TestActivityType;
import com.ted.lms.learning.activity.test.web.constants.TestPortletKeys;
import com.ted.lms.learning.activity.test.web.util.CommonUtil;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityResult;
import com.ted.lms.model.LearningActivityTry;
import com.ted.lms.service.LearningActivityLocalServiceUtil;
import com.ted.lms.service.LearningActivityResultLocalServiceUtil;
import com.ted.lms.service.LearningActivityTryLocalServiceUtil;

import java.util.ServiceConfigurationError;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + TestPortletKeys.TEST,
		"mvc.command.name=/activities/test/correct_test"
	},
	service = MVCActionCommand.class
)
public class CorrectTestMVCActionCommand extends BaseMVCActionCommand {
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		if(!CommonUtil.isFormRepeat(actionRequest)) {
			
			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
	
			long actId=ParamUtil.getLong(actionRequest, "actId");
			long latId=ParamUtil.getLong(actionRequest,"latId" );
			
			log.debug("latId: " + latId);
	
			String navigate = ParamUtil.getString(actionRequest, "navigate");
			boolean isPartial = false;
			if (Validator.isNotNull(navigate)) {
				if ("backward".equals(navigate) || "forward".equals(navigate)) {
					isPartial = true;
				}
			}
			
			log.debug("isPartial: " + isPartial);
	
			LearningActivityTry larntry=LearningActivityTryLocalServiceUtil.getLearningActivityTry(latId);
	
			//Comprobar que el usuario tenga intentos posibles.
			if (larntry.getEndDate() == null){
	
				int correctanswers=0;
				int penalizedAnswers=0;
				
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
				if(learningActivityResult != null) {
					oldResult = learningActivityResult.getResult();
				}
	
				larntry.setTryResultData(resultadosXMLDoc.formattedString());
				
				double score = isPartial ? 0 : lat.calculateResult(larntry) ;
				if(log.isDebugEnabled()) {
					log.debug("Score: " + score);
				}
	
				if(score < 0)score = 0;
				
				ServiceContext serviceContext = ServiceContextFactory.getInstance(actionRequest);
				
				if (!isPartial) {
					LearningActivityTryLocalServiceUtil.finishLearningActivityTry(larntry, score, serviceContext);
				}else {
					LearningActivityTryLocalServiceUtil.updateLearningActivityTry(larntry, 0, serviceContext);
				}

				actionRequest.setAttribute("larntry", larntry);
				actionResponse.setRenderParameters(actionRequest.getParameterMap());
	
				if (isPartial) {
					actionResponse.setRenderParameter("improve", ParamUtil.getString(actionRequest, "improve", Boolean.FALSE.toString()));
				} else {
					actionResponse.setRenderParameter("oldResult", Double.toString(oldResult));
					actionResponse.setRenderParameter("correction", Boolean.toString(true));
					actionResponse.setRenderParameter("mvcRender", "results");
				}
			}else{
				actionResponse.setRenderParameters(actionRequest.getParameterMap());
			}	
			actionRequest.setAttribute("actId", actId);
			actionResponse.setRenderParameter("mvcRenderCommandName", "/activities/test/view_activity");
		}
		
	}
	
	private static final Log log = LogFactoryUtil.getLog(CorrectTestMVCActionCommand.class);
	
}
