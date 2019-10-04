package com.ted.lms.question.multioptions;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.ted.lms.learning.activity.question.model.Question;
import com.ted.lms.learning.activity.question.model.QuestionType;
import com.ted.lms.learning.activity.question.model.QuestionTypeFactory;
import com.ted.lms.learning.activity.question.service.AnswerLocalService;
import com.ted.lms.question.multioptions.constants.MultioptionsWebPortletKeys;
import com.ted.lms.question.options.OptionsQuestionTypeFactory;

import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    immediate = true,
    property = {},
    service = QuestionTypeFactory.class
)
public class MultioptionsQuestionTypeFactory extends OptionsQuestionTypeFactory{
	
	public static final long TYPE = 1;
	
	public static final String URL_EDIT_ANSWER = "/question/multioptions/answer.jsp";
	
	@Override
	public QuestionType getQuestionType(Question question) throws PortalException {
		return new MultioptionsQuestionType(question, answerLocalService);
	}
	
	public MultioptionsQuestionType getMultioptionsQuestionType(Question question) throws PortalException {
		return new MultioptionsQuestionType(question, answerLocalService);
	}

	@Override
	public String getClassName() {
		return MultioptionsQuestionTypeFactory.class.getName();
	}

	@Override
	public long getType() {
		return TYPE;
	}
	
	@Override
	public String getTitle(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		return LanguageUtil.get(resourceBundle, "question.multioptions.title");
	}
	
	@Override
	public String getDescription(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		return LanguageUtil.get(resourceBundle, "question.multioptions.description");
	}
	
	@Override
	public String getURLEditAnswer() {
		return URL_EDIT_ANSWER;
	}
	
	@Override
	public String getURLEditAnswers() {
		return "/question/multioptions/answers.jsp";
	}
	
	@Override
	public String getPortletId() {
		return MultioptionsWebPortletKeys.MULTIOPTIONS;
	}
	
	public String getIconCssClass() {
		return "multioptions";
	}
	
	public String getName() {
		return "multioptions";
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
