package com.ted.lms.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class SmallImageScaleException extends PortalException {

	public SmallImageScaleException() {
	}

	public SmallImageScaleException(String msg) {
		super(msg);
	}

	public SmallImageScaleException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public SmallImageScaleException(Throwable cause) {
		super(cause);
	}

}
