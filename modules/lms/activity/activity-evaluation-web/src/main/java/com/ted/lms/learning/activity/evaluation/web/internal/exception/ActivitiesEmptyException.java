package com.ted.lms.learning.activity.evaluation.web.internal.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class ActivitiesEmptyException extends PortalException {

	public ActivitiesEmptyException() {
	}

	public ActivitiesEmptyException(String msg) {
		super(msg);
	}

	public ActivitiesEmptyException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ActivitiesEmptyException(Throwable cause) {
		super(cause);
	}

}
