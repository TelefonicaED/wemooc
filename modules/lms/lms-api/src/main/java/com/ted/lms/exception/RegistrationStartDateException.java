package com.ted.lms.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class RegistrationStartDateException extends PortalException {

	public RegistrationStartDateException() {
	}

	public RegistrationStartDateException(String msg) {
		super(msg);
	}

	public RegistrationStartDateException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public RegistrationStartDateException(Throwable cause) {
		super(cause);
	}

}
