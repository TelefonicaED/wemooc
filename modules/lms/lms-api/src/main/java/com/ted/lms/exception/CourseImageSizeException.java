package com.ted.lms.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class CourseImageSizeException extends PortalException {

	public CourseImageSizeException() {
	}

	public CourseImageSizeException(String msg) {
		super(msg);
	}

	public CourseImageSizeException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public CourseImageSizeException(Throwable cause) {
		super(cause);
	}

}
