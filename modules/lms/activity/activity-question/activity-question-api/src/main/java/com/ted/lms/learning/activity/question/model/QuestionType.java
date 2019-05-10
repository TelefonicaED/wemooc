package com.ted.lms.learning.activity.question.model;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.xml.Element;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;

public interface QuestionType {
	
	public long getType();
	
	public String getClassName();
	public long correct(Element element) throws PortalException;
	public long isCorrect(PortletRequest portletRequest) throws PortalException;
	public Element getResults(PortletRequest portletRequest);
	
	public QuestionTypeFactory getQuestionTypeFactory();
	
	public void setExtraContent(ActionRequest actionRequest, String iteratorQuestion) throws PortalException;
	public void saveAnswers(ActionRequest actionRequest, String iteratorQuestion) throws PortalException;
	
	public boolean isCorrectRequest(ActionRequest actionRequest, String iteratorQuestion, int counter);
	
	public String getURLQuestion();
}
