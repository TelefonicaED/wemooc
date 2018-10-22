package com.ted.lms.learning.activity.question.model;

import com.liferay.portal.kernel.exception.PortalException;

import java.util.Locale;

public interface QuestionTypeFactory {
	
	public QuestionType getQuestionType(Question question) throws PortalException;

	public String getClassName();

	public long getClassNameId();
	
	public long getType();
	
	public String getTitle(Locale locale);
	
	public String getDescription(Locale locale);
	
	public String getIconCssClass();
}
