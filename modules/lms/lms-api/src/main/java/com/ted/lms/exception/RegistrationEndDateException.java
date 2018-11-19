package com.ted.lms.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class RegistrationEndDateException extends PortalException {

	public RegistrationEndDateException() {
	}

	public RegistrationEndDateException(String msg) {
		super(msg);
	}

	public RegistrationEndDateException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public RegistrationEndDateException(Throwable cause) {
		super(cause);
	}

}
