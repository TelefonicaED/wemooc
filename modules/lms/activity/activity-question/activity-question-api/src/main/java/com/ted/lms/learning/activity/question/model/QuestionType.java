package com.ted.lms.learning.activity.question.model;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.xml.Element;

public interface QuestionType {
	public String getClassName();
	public long correct(Element element) throws PortalException;
}
