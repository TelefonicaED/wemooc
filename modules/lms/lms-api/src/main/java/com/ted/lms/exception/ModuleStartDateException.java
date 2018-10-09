package com.ted.lms.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class ModuleStartDateException extends PortalException {

	public ModuleStartDateException() {
	}

	public ModuleStartDateException(String msg) {
		super(msg);
	}

	public ModuleStartDateException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ModuleStartDateException(Throwable cause) {
		super(cause);
	}

}
