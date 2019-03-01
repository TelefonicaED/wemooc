package com.ted.lms.learning.activity.p2p.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class P2PFileEntrySizeException extends PortalException {

	public P2PFileEntrySizeException() {
	}

	public P2PFileEntrySizeException(String msg) {
		super(msg);
	}

	public P2PFileEntrySizeException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public P2PFileEntrySizeException(Throwable cause) {
		super(cause);
	}

}
