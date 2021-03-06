package com.ted.lms.question.sortable;

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
import com.ted.lms.question.sortable.constants.SortableConstants;
import com.ted.lms.question.sortable.constants.SortableWebPortletKeys;

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
		return new SortableQuestionType(question, answerLocalService);
	}
	
	public SortableQuestionType getSortableQuestionType(Question question) throws PortalException {
		return new SortableQuestionType(question, answerLocalService);
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
		return SortableWebPortletKeys.SORTABLE;
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
	public String[] getJavascriptImport(String cdnHost) {
		String[] javascript = new String[1];
		
		javascript[0] = cdnHost + SortableConstants.PATH_JAVA_SCRIPT + "/js/sortable.js";
		
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
		return "sortable";
	}
	
	@Override
	public String[] getXMLType() {
		return new String[] {"sort"};
	}
	
	@Override
	public Question importQuestionXML(long userId, long groupId, long actId, Element questionElement, ServiceContext serviceContext) throws PortalException {
		Element questiontext = questionElement.element("questiontext");
		String description = questiontext.elementText("text");
		
		Question question = questionLocalService.addQuestion(userId, groupId, actId, description, getType(), false, serviceContext);
		
		for(Element answerElement:questionElement.elements("answer")){
			boolean correct=true;
			String answer=answerElement.elementText("text");
			answerLocalService.addAnswer(userId, groupId, question.getQuestionId(), actId, answer, null, null, correct, serviceContext);
		}
		
		return question;
	}

}
