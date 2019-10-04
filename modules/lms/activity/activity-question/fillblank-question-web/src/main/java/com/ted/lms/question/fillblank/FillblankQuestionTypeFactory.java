package com.ted.lms.question.fillblank;

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
import com.ted.lms.question.fillblank.constants.FillblankWebPortletKeys;

import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    immediate = true,
    property = {},
    service = QuestionTypeFactory.class
)
public class FillblankQuestionTypeFactory extends BaseQuestionTypeFactory{
	
	public static final long TYPE = 3;
	
	public static final String URL_EDIT_ANSWER = "/question/fillblank/answer.jsp";
	
	@Override
	public QuestionType getQuestionType(Question question) throws PortalException {
		return new FillblankQuestionType(question, answerLocalService);
	}
	
	public FillblankQuestionType getFillblankQuestionType(Question question) throws PortalException {
		return new FillblankQuestionType(question, answerLocalService);
	}


	@Override
	public String getClassName() {
		return FillblankQuestionTypeFactory.class.getName();
	}

	@Override
	public long getType() {
		return TYPE;
	}
	
	@Override
	public String getTitle(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		return LanguageUtil.get(resourceBundle, "question.fillblank.title");
	}
	
	@Override
	public String getDescription(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		return LanguageUtil.get(resourceBundle, "question.fillblank.description");
	}
	
	@Override
	public String getURLEditAnswers() {
		return "/question/fillblank/answers.jsp";
	}
	
	@Override
	public String getURLEditAnswer() {
		return URL_EDIT_ANSWER;
	}
	
	@Override
	public String getPortletId() {
		return FillblankWebPortletKeys.FILLBLANK;
	}
	
	public String getIconCssClass() {
		return "fillblank";
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

	@Override
	public String getName() {
		return "fillblank";
	}

	@Override
	public String[] getXMLType() {
		return new String[] {"cloze"};
	}
	
	@Override
	public Question importQuestionXML(long userId, long groupId, long actId, Element questionElement, ServiceContext serviceContext) throws PortalException {
		Element name = questionElement.element("name");
		String description = (name!=null) ? name.elementText("text") : "";
		
		Question question = questionLocalService.addQuestion(userId, groupId, actId, description, getType(), false, serviceContext);
		
		Element questiontext = questionElement.element("questiontext");
		String answer = questiontext.elementText("text");
		Element generalFeedback = questionElement.element("generalfeedback");
		
		String feedback = generalFeedback.elementText("text");
		String feedbackCorrect = "", feedbackIncorrect="";
		
		if(feedback.contains("//")){
			String[] split = feedback.split("//");
			if(split.length == 2){
				feedbackCorrect = split[0];
				feedbackIncorrect = split[1];
			}else{
				feedbackCorrect = feedback;
				feedbackIncorrect = feedback;
			}
		}else{
			feedbackCorrect = feedback;
			feedbackIncorrect = feedback;
		}
		
		answerLocalService.addAnswer(userId, groupId, question.getQuestionId(), actId, answer, feedbackCorrect, feedbackIncorrect, true, serviceContext);

		return question;
	}
}
