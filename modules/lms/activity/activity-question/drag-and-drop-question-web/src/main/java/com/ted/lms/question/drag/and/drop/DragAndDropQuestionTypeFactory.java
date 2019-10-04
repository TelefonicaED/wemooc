package com.ted.lms.question.drag.and.drop;

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
import com.ted.lms.question.drag.and.drop.constants.DragAndDropConstants;
import com.ted.lms.question.drag.and.drop.constants.DragAndDropWebPortletKeys;

import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    immediate = true,
    property = {},
    service = QuestionTypeFactory.class
)
public class DragAndDropQuestionTypeFactory extends BaseQuestionTypeFactory{
	
	public static final long TYPE = 4;
	
	public static final String URL_EDIT_ANSWER = "/question/draganddrop/answer.jsp";
	
	@Override
	public QuestionType getQuestionType(Question question) throws PortalException {
		return new DragAndDropQuestionType(question, answerLocalService);
	}
	
	public DragAndDropQuestionType getDragAndDropQuestionType(Question question) throws PortalException {
		return new DragAndDropQuestionType(question, answerLocalService);
	}


	@Override
	public String getClassName() {
		return DragAndDropQuestionTypeFactory.class.getName();
	}

	@Override
	public long getType() {
		return TYPE;
	}
	
	@Override
	public String getTitle(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		return LanguageUtil.get(resourceBundle, "question.drag-and-drop.title");
	}
	
	@Override
	public String getDescription(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		return LanguageUtil.get(resourceBundle, "question.drag-and-drop.description");
	}
	
	@Override
	public String getURLEditAnswers() {
		return "/question/draganddrop/answers.jsp";
	}
	
	@Override
	public String getURLEditAnswer() {
		return URL_EDIT_ANSWER;
	}
	
	@Override
	public String getPortletId() {
		return DragAndDropWebPortletKeys.DRAG_AND_DROP_PORTLET;
	}
	
	@Override
	public String[] getJavascriptImport(String cdnHost) {
		String[] javascript = new String[4];
		
		javascript[0] = "//ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js";
		javascript[1] = cdnHost + DragAndDropConstants.PATH_JAVA_SCRIPT + "/js/mouse.js";
		javascript[2] = cdnHost +DragAndDropConstants.PATH_JAVA_SCRIPT + "/js/jquery.ui.touch-punch.min.js";
		javascript[3] = cdnHost + DragAndDropConstants.PATH_JAVA_SCRIPT + "/js/drag_and_drop.js";
		
		return javascript;
	}
	
	public String getIconCssClass() {
		return "drag-and-drop";
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
		return "draganddrop";
	}
	
	@Override
	public String[] getXMLType() {
		return new String[] {"draganddrop"};
	}
	
	public Question importQuestionXML(long userId, long groupId, long actId, Element questionElement, ServiceContext serviceContext) throws PortalException {
		Element questiontext = questionElement.element("questiontext");
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

}
