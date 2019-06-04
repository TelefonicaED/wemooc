package com.ted.lms.learning.activity.survey.web.internal.portlet.action;

import com.liferay.petra.string.StringPool;
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
import com.ted.lms.learning.activity.question.service.QuestionLocalService;
import com.ted.lms.learning.activity.survey.service.SurveyResultLocalService;
import com.ted.lms.learning.activity.survey.web.constants.SurveyConstants;
import com.ted.lms.learning.activity.survey.web.constants.SurveyPortletKeys;
import com.ted.lms.model.LearningActivityTry;
import com.ted.lms.service.LearningActivityTryLocalService;

import java.util.Iterator;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + SurveyPortletKeys.SURVEY,
		"mvc.command.name=/activities/survey/save_survey"
	},
	service = MVCActionCommand.class
)
public class SaveSurveyMVCActionCommand extends BaseMVCActionCommand {
	
	private static final Log log = LogFactoryUtil.getLog(SaveSurveyMVCActionCommand.class);
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		long actId=ParamUtil.getLong(actionRequest,"actId",0 );
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		LearningActivityTry learningActivityTry = learningActivityTryLocalService.getLastLearningActivityTry(actId, themeDisplay.getUserId());

		//Comprobar si el usuario se dejo alguna encuesta abierta
		if (learningActivityTry.getEndDate() == null ) {
			
			Element resultadosXML=SAXReaderUtil.createElement("results");
			Document resultadosXMLDoc=SAXReaderUtil.createDocument(resultadosXML);

			long[] questionIds = ParamUtil.getLongValues(actionRequest, "question");
			
			for (long questionId : questionIds) {
				Question question = questionLocalService.fetchQuestion(questionId);
				QuestionType qt = question.getQuestionType();
				
				resultadosXML.add(qt.getResults(actionRequest));								
			}
			
			Iterator<Element> nodeItr = resultadosXMLDoc.getRootElement().elementIterator();
			long questionId = 0;
			long answerId = 0;
			String textAnswer = StringPool.BLANK;
			while(nodeItr.hasNext()) {
				Element element = nodeItr.next();
		         if("question".equals(element.getName())){
		        	 questionId = Long.valueOf(element.attributeValue("id"));
		        	 
		        	 Iterator<Element> elementItr = element.elementIterator();
		        	 while(elementItr.hasNext()) {
		        		 Element elementElement = elementItr.next();
		        		 if("answer".equals(elementElement.getName())) {
		        			 if(Validator.isNotNull(elementElement.attributeValue("id"))) {
		        				 answerId = Long.valueOf(elementElement.attributeValue("id"));
		        			 }else {
		        				 answerId = 0;
		        			 }
		        			 if(Validator.isNotNull(elementElement.getTextTrim())) {
		        				 textAnswer = elementElement.getTextTrim();
		        			 }else {
		        				 textAnswer = StringPool.BLANK;
		        			 }
		        			 
		        			 //Añadimos las repuestas a la tabla de encuesta
		        			 surveyResultLocalService.addSurveyResult(actId, learningActivityTry.getLatId(), themeDisplay.getUserId(), questionId, answerId, textAnswer);
		        		 }
		        	 }
		         }
		    }

			ServiceContext serviceContext = ServiceContextFactory.getInstance(LearningActivityTry.class.getName(), actionRequest);
			
			//Guardar los resultados
			learningActivityTry.setTryResultData(resultadosXMLDoc.formattedString());
			learningActivityTryLocalService.finishLearningActivityTry(learningActivityTry, SurveyConstants.DEFAULT_SCORE_FINISH, serviceContext);
		}
	}
	
	@Reference
	private SurveyResultLocalService surveyResultLocalService;
	@Reference
	private QuestionLocalService questionLocalService;
	@Reference
	private LearningActivityTryLocalService learningActivityTryLocalService;

}
