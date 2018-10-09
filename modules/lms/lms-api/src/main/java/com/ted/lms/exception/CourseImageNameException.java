package com.ted.lms.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class CourseImageNameException extends PortalException {

	public CourseImageNameException() {
	}

	public CourseImageNameException(String msg) {
		super(msg);
	}

	public CourseImageNameException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public CourseImageNameException(Throwable cause) {
		super(cause);
	}

}
