package com.ted.lms.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class NoSuchPreviousModuleException extends PortalException {

	public NoSuchPreviousModuleException() {
	}

	public NoSuchPreviousModuleException(String msg) {
		super(msg);
	}

	public NoSuchPreviousModuleException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchPreviousModuleException(Throwable cause) {
		super(cause);
	}

}
