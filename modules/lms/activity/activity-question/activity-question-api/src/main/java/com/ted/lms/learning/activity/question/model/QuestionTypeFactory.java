package com.ted.lms.learning.activity.question.model;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import java.util.Locale;

public interface QuestionTypeFactory {
	
	public QuestionType getQuestionType(Question question) throws PortalException;

	public String getClassName();

	public long getClassNameId();
	
	public long getType();
	
	public String getTitle(Locale locale);
	
	public String getDescription(Locale locale);
	
	public String getURLEditAnswers();
	public String getURLEditAnswer();
	
	public String getPortletId();
	
	public boolean getPenalize();
	
	public boolean isPartialCorrectAvailable();
	public boolean isManualCorrection();
	
	public int getMinNumAnswers();
	public int getMinNumCorrectAnswers();
	
	public String getURLAddQuestion(LiferayPortletResponse liferayPortletResponse);
	public String getURLAddAnswer(LiferayPortletResponse liferayPortletResponse);
	
}
