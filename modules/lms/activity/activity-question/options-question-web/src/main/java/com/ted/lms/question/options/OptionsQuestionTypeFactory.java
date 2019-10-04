package com.ted.lms.question.options;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferay.portal.kernel.xml.Element;
import com.ted.lms.learning.activity.question.model.BaseQuestionTypeFactory;
import com.ted.lms.learning.activity.question.model.Question;
import com.ted.lms.learning.activity.question.model.QuestionType;
import com.ted.lms.learning.activity.question.model.QuestionTypeFactory;
import com.ted.lms.learning.activity.question.service.AnswerLocalService;
import com.ted.lms.learning.activity.question.service.QuestionLocalService;
import com.ted.lms.question.options.constants.OptionsWebPortletKeys;

import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    immediate = true,
    property = {},
    service = QuestionTypeFactory.class
)
public class OptionsQuestionTypeFactory extends BaseQuestionTypeFactory{
	
	public static final long TYPE = 0;
	public static final String URL_EDIT_ANSWER = "/question/options/answer.jsp";

	@Override
	public QuestionType getQuestionType(Question question) throws PortalException {
		return new OptionsQuestionType(question, answerLocalService);
	}
	
	public OptionsQuestionType getOptionsQuestionType(Question question) throws PortalException {
		return new OptionsQuestionType(question, answerLocalService);
	}

	@Override
	public String getClassName() {
		return OptionsQuestionTypeFactory.class.getName();
	}

	@Override
	public long getType() {
		return TYPE;
	}
	
	@Override
	public String getTitle(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		return LanguageUtil.get(resourceBundle, "question.options.title");
	}
	
	@Override
	public String getDescription(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		return LanguageUtil.get(resourceBundle, "question.options.description");
	}
	
	@Override
	public String getURLQuestionExtraData() {
		return "/question/options/extra_data.jsp";
	}
	
	@Override
	public String getURLEditAnswers() {
		return "/question/options/answers.jsp";
	}
	
	@Override
	public String getURLEditAnswer() {
		return URL_EDIT_ANSWER;
	}
	
	@Override
	public String getPortletId() {
		return OptionsWebPortletKeys.OPTIONS_PORTLET;
	}
	
	@Override
	public int getMinNumAnswers() {
		return 2;
	}
	
	@Override
	public int getMinNumCorrectAnswers() {
		return 1;
	}
	
	public String getIconCssClass() {
		return "options";
	}
	
	@Override
	public String getName() {
		return "options";
	}
	
	@Override
	public String[] getXMLType() {
		return new String[] {"multichoice"};
	}
	
	@Override
	public Question importQuestionXML(long userId, long groupId, long actId, Element questionElement, ServiceContext serviceContext) throws PortalException {
		Element questiontext=questionElement.element("questiontext");
		String description=questiontext.elementText("text");
		
		Question question = questionLocalService.addQuestion(userId, groupId, actId, description, getType(), false, serviceContext);
		
		for(Element answerElement:questionElement.elements("answer")){
			boolean correct=(!"0".equals(answerElement.attributeValue("fraction")))? true:false;
			String answer=answerElement.elementText("text");
			String feedback="";
			if(answerElement.element("feedback")!=null && answerElement.element("feedback").element("text")!=null)
				feedback=answerElement.element("feedback").element("text").getText();
			
			answerLocalService.addAnswer(userId, groupId, question.getQuestionId(), actId, answer, feedback, feedback, correct, serviceContext);
		}
		
		return question;
	}
	
	@Reference(
		unbind = "-"
	)
	public void setResourceBundleLoader(ResourceBundleLoader resourceBundleLoader) {

		this.resourceBundleLoader = resourceBundleLoader;
	}

	protected ResourceBundleLoader resourceBundleLoader;
	
	@Reference(unbind = "-")
	protected void setAnswerLocalService(AnswerLocalService answerLocalService) {
		this.answerLocalService = answerLocalService;
	}
	protected AnswerLocalService answerLocalService;
	
	@Reference(unbind = "-")
	protected void setQuestionLocalService(QuestionLocalService questionLocalService) {
		this.questionLocalService = questionLocalService;
	}
	protected QuestionLocalService questionLocalService;
}
