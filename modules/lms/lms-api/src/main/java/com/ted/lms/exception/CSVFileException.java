package com.ted.lms.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class CSVFileException extends PortalException {

	public static final int TYPE_DEFAULT = 0;

	public static final int TYPE_INVALID_HEADERS = 1;

	public CSVFileException() {
	}

	public CSVFileException(int type) {
		this.type = type;
	}

	public CSVFileException(int type, String msg) {
		this(msg);

		this.type = type;
	}

	public CSVFileException(int type, String msg, Throwable cause) {
		this(msg, cause);

		this.type = type;
	}

	public CSVFileException(int type, Throwable cause) {
		this(cause);

		this.type = type;
	}

	public CSVFileException(String msg) {
		super(msg);
	}

	public CSVFileException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public CSVFileException(Throwable cause) {
		super(cause);
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	private int type = TYPE_DEFAULT;
}
