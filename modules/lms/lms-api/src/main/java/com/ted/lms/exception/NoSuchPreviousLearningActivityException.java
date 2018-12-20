package com.ted.lms.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class NoSuchPreviousLearningActivityException extends PortalException {

	public NoSuchPreviousLearningActivityException() {
	}

	public NoSuchPreviousLearningActivityException(String msg) {
		super(msg);
	}

	public NoSuchPreviousLearningActivityException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchPreviousLearningActivityException(Throwable cause) {
		super(cause);
	}

}
