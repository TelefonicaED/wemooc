package com.ted.lms.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class ModuleEndDateException extends PortalException {

	public ModuleEndDateException() {
	}

	public ModuleEndDateException(String msg) {
		super(msg);
	}

	public ModuleEndDateException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ModuleEndDateException(Throwable cause) {
		super(cause);
	}

}
