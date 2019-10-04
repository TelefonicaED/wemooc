package com.ted.lms.question.freetext;

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
	
	@Reference(unbind = "-")
	protected void setQuestionLocalService(QuestionLocalService questionLocalService) {
		this.questionLocalService = questionLocalService;
	}
	protected QuestionLocalService questionLocalService;

	@Override
	public String getName() {
		return "freetext";
	}
	
	@Override
	public String[] getXMLType() {
		return new String[] {"numerical","essay"};
	}
	
	@Override
	public Question importQuestionXML(long userId, long groupId, long actId, Element questionElement, ServiceContext serviceContext) throws PortalException {
		Element questiontext=questionElement.element("questiontext");
		String description=questiontext.elementText("text");
		
		Question question = questionLocalService.addQuestion(userId, groupId, actId, description, getType(), false, serviceContext);
		
		if(!"essay".equals(questionElement.attributeValue("type"))){//los essay en moodle nunca tienen respuesta
			for(Element answerElement:questionElement.elements("answer")){
				
				boolean correct=("100".equals(answerElement.attributeValue("fraction")))? true:false;
				String answer=answerElement.elementText("text");
				String feedback="", feedbackCorrect="", feedbackIncorrect="";
				
				if(answerElement.element("feedback")!=null && answerElement.element("feedback").element("text")!=null)
					feedback=answerElement.element("feedback").element("text").getText();	 
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
				
				answerLocalService.addAnswer(userId, groupId, question.getQuestionId(), actId, answer, feedbackCorrect, feedbackIncorrect, correct, serviceContext);
				break;//porque inicialmente solo aceptamos una respuesta
			}
		}
		
		return question;
	}

}
