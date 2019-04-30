package com.ted.lms.learning.activity.test.web.internal.portlet;

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
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
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
	 * Corrección para cuando estamos en modo observador ya que no se tiene que guardar nada en learningactivitytry
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