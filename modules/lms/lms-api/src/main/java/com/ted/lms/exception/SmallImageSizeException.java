package com.ted.lms.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class SmallImageSizeException extends PortalException {

	public SmallImageSizeException() {
	}

	public SmallImageSizeException(String msg) {
		super(msg);
	}

	public SmallImageSizeException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public SmallImageSizeException(Throwable cause) {
		super(cause);
	}

}
