package com.ted.lms.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class NoSuchNextLearningActivityException extends PortalException {

	public NoSuchNextLearningActivityException() {
	}

	public NoSuchNextLearningActivityException(String msg) {
		super(msg);
	}

	public NoSuchNextLearningActivityException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchNextLearningActivityException(Throwable cause) {
		super(cause);
	}

}
