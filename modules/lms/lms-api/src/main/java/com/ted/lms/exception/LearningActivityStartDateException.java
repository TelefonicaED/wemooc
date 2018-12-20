package com.ted.lms.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class LearningActivityStartDateException extends PortalException {

	public LearningActivityStartDateException() {
	}

	public LearningActivityStartDateException(String msg) {
		super(msg);
	}

	public LearningActivityStartDateException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public LearningActivityStartDateException(Throwable cause) {
		super(cause);
	}

}
