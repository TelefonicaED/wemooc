package com.ted.lms.learning.activity.question;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferay.portal.kernel.util.WebKeys;
import com.ted.lms.learning.activity.question.constants.QuestionsWebPortletKeys;
import com.ted.lms.learning.activity.question.model.BaseQuestionTypeFactory;
import com.ted.lms.learning.activity.question.model.Question;
import com.ted.lms.learning.activity.question.model.QuestionType;
import com.ted.lms.learning.activity.question.model.QuestionTypeFactory;
import com.ted.lms.learning.activity.question.service.AnswerLocalService;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.portlet.PortletRequest;
import javax.portlet.WindowStateException;

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
	public String getURLEditAnswers() {
		return "/question/options/answers.jsp";
	}
	
	@Override
	public String getURLEditAnswer() {
		return URL_EDIT_ANSWER;
	}
	
	@Override
	public String getPortletId() {
		return QuestionsWebPortletKeys.EDIT_QUESTIONS;
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