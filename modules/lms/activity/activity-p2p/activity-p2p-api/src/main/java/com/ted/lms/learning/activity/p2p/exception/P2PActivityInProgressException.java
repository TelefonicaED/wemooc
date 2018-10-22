package com.ted.lms.learning.activity.p2p.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class P2PActivityInProgressException extends PortalException {

	public P2PActivityInProgressException() {
	}

	public P2PActivityInProgressException(String msg) {
		super(msg);
	}

	public P2PActivityInProgressException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public P2PActivityInProgressException(Throwable cause) {
		super(cause);
	}

}
