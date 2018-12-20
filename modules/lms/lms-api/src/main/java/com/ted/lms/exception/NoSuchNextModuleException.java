package com.ted.lms.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class NoSuchNextModuleException extends PortalException {

	public NoSuchNextModuleException() {
	}

	public NoSuchNextModuleException(String msg) {
		super(msg);
	}

	public NoSuchNextModuleException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchNextModuleException(Throwable cause) {
		super(cause);
	}

}
