package com.ted.lms.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class SmallImageNameException extends PortalException {

	public SmallImageNameException() {
	}

	public SmallImageNameException(String msg) {
		super(msg);
	}

	public SmallImageNameException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public SmallImageNameException(Throwable cause) {
		super(cause);
	}

}
