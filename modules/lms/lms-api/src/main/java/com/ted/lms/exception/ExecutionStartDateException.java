package com.ted.lms.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class ExecutionStartDateException extends PortalException {

	public ExecutionStartDateException() {
	}

	public ExecutionStartDateException(String msg) {
		super(msg);
	}

	public ExecutionStartDateException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ExecutionStartDateException(Throwable cause) {
		super(cause);
	}

}
