package com.ted.lms.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class LearningActivityEndDateException extends PortalException {

	public LearningActivityEndDateException() {
	}

	public LearningActivityEndDateException(String msg) {
		super(msg);
	}

	public LearningActivityEndDateException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public LearningActivityEndDateException(Throwable cause) {
		super(cause);
	}

}
