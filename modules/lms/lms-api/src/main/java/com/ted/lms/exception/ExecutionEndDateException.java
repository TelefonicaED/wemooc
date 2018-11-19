package com.ted.lms.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class ExecutionEndDateException extends PortalException {

	public ExecutionEndDateException() {
	}

	public ExecutionEndDateException(String msg) {
		super(msg);
	}

	public ExecutionEndDateException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ExecutionEndDateException(Throwable cause) {
		super(cause);
	}

}
