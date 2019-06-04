package com.ted.lms.learning.activity.question;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.ted.lms.learning.activity.question.constants.QuestionsWebPortletKeys;
import com.ted.lms.learning.activity.question.model.BaseQuestionTypeFactory;
import com.ted.lms.learning.activity.question.model.Question;
import com.ted.lms.learning.activity.question.model.QuestionType;
import com.ted.lms.learning.activity.question.model.QuestionTypeFactory;
import com.ted.lms.learning.activity.question.service.AnswerLocalService;

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
		return QuestionsWebPortletKeys.EDIT_QUESTIONS;
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

}
