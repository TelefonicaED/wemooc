package com.ted.lms.question.freetext;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.ted.lms.learning.activity.question.model.BaseQuestionTypeFactory;
import com.ted.lms.learning.activity.question.model.Question;
import com.ted.lms.learning.activity.question.model.QuestionType;
import com.ted.lms.learning.activity.question.model.QuestionTypeFactory;
import com.ted.lms.learning.activity.question.service.AnswerLocalService;
import com.ted.lms.question.freetext.constants.FreetextWebPortletKeys;

import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    immediate = true,
    property = {},
    service = QuestionTypeFactory.class
)
public class FreetextQuestionTypeFactory extends BaseQuestionTypeFactory{
	
	public static final long TYPE = 2;
	
	@Override
	public QuestionType getQuestionType(Question question) throws PortalException {
		return new FreetextQuestionType(question, answerLocalService);
	}
	
	public FreetextQuestionType getFreetextQuestionType(Question question) throws PortalException {
		return new FreetextQuestionType(question, answerLocalService);
	}

	@Override
	public String getClassName() {
		return FreetextQuestionTypeFactory.class.getName();
	}

	@Override
	public long getType() {
		return TYPE;
	}
	
	@Override
	public boolean isManualCorrection() {
		return true;
	}
	
	@Override
	public String getTitle(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		return LanguageUtil.get(resourceBundle, "question.freetext.title");
	}
	
	@Override
	public String getDescription(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		return LanguageUtil.get(resourceBundle, "question.freetext.description");
	}
	
	@Override
	public String getURLEditAnswers() {
		return "/question/freetext/answers.jsp";
	}
	
	@Override
	public String getPortletId() {
		return FreetextWebPortletKeys.FREETEXT;
	}
	
	public String getIconCssClass() {
		return "freetext";
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
