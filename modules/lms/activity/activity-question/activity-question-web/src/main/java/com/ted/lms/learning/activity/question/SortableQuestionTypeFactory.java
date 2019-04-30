package com.ted.lms.learning.activity.question;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.ted.lms.learning.activity.question.constants.QuestionsWebPortletKeys;
import com.ted.lms.learning.activity.question.model.BaseQuestionTypeFactory;
import com.ted.lms.learning.activity.question.model.Question;
import com.ted.lms.learning.activity.question.model.QuestionType;
import com.ted.lms.learning.activity.question.model.QuestionTypeFactory;
import com.ted.lms.learning.activity.question.web.internal.constants.QuestionConstants;

import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    immediate = true,
    property = {},
    service = QuestionTypeFactory.class
)
public class SortableQuestionTypeFactory extends BaseQuestionTypeFactory{
	
	public static final long TYPE = 5;
	public static final String URL_EDIT_ANSWER = "/question/sortable/answer.jsp";
	
	@Override
	public QuestionType getQuestionType(Question question) throws PortalException {
		return new SortableQuestionType(question);
	}

	@Override
	public String getClassName() {
		return SortableQuestionTypeFactory.class.getName();
	}

	@Override
	public long getType() {
		return TYPE;
	}
	
	@Override
	public String getTitle(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		return LanguageUtil.get(resourceBundle, "question.sortable.title");
	}
	
	@Override
	public String getDescription(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		return LanguageUtil.get(resourceBundle, "question.sortable.description");
	}
	
	@Override
	public String getURLEditAnswers() {
		return "/question/sortable/answers.jsp";
	}
	
	@Override
	public String getPortletId() {
		return QuestionsWebPortletKeys.EDIT_QUESTIONS;
	}
	
	@Override
	public int getMinNumAnswers() {
		return 2;
	}
	
	@Override
	public String[] getJavascriptImport(String cdnHost) {
		String[] javascript = new String[1];
		
		javascript[0] = cdnHost + QuestionConstants.PATH_JAVA_SCRIPT + "/js/sortable.js";
		
		return javascript;
	}
	
	public String getIconCssClass() {
		return "sortable";
	}
	
	@Reference(
		unbind = "-"
	)
	public void setResourceBundleLoader(ResourceBundleLoader resourceBundleLoader) {

		this.resourceBundleLoader = resourceBundleLoader;
	}

	protected ResourceBundleLoader resourceBundleLoader;

}
