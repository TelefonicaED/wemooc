package com.ted.lms.question.survey;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.ted.lms.learning.activity.question.model.Question;
import com.ted.lms.learning.activity.question.model.QuestionType;
import com.ted.lms.learning.activity.question.model.QuestionTypeFactory;
import com.ted.lms.learning.activity.question.service.AnswerLocalService;
import com.ted.lms.question.options.OptionsQuestionTypeFactory;
import com.ted.lms.question.survey.constants.SurveyWebPortletKeys;

import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    immediate = true,
    property = {},
    service = QuestionTypeFactory.class
)
public class SurveyQuestionTypeFactory extends OptionsQuestionTypeFactory {
	
	public static final long TYPE = 6;
	
	public static final String URL_EDIT_ANSWER = "/question/survey/answer.jsp";
	
	@Override
	public QuestionType getQuestionType(Question question) throws PortalException {
		return new SurveyQuestionType(question, answerLocalService);
	}
	
	public SurveyQuestionType getSurveyQuestionType(Question question) throws PortalException {
		return new SurveyQuestionType(question, answerLocalService);
	}

	@Override
	public String getClassName() {
		return SurveyQuestionTypeFactory.class.getName();
	}

	@Override
	public long getType() {
		return TYPE;
	}
	
	@Override
	public String getTitle(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		return LanguageUtil.get(resourceBundle, "question.survey.title");
	}
	
	@Override
	public String getDescription(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		return LanguageUtil.get(resourceBundle, "question.survey.description");
	}
	
	@Override
	public String getURLEditAnswers() {
		return "/question/survey/answers.jsp";
	}
	
	@Override
	public String getURLEditAnswer() {
		return URL_EDIT_ANSWER;
	}
	
	@Override
	public int getMinNumAnswers() {
		return 2;
	}
	
	@Override
	public String getPortletId() {
		return SurveyWebPortletKeys.SURVEY;
	}
	
	public boolean getPenalize(){
		return false;
	}
	
	public String getIconCssClass() {
		return "survey";
	}
	
	public String getName() {
		return "surveyoptions";
	}
	
	@Reference(
		unbind = "-"
	)
	public void setResourceBundleLoader(ResourceBundleLoader resourceBundleLoader) {

		this.resourceBundleLoader = resourceBundleLoader;
	}

	protected ResourceBundleLoader resourceBundleLoader;
	
	@Reference
	private AnswerLocalService answerLocalService;

}
