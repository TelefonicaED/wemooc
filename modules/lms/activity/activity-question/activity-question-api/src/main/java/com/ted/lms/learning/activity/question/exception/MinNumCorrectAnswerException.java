/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
package com.ted.lms.learning.activity.question.exception;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.exception.NoSuchModelException;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class MinNumCorrectAnswerException extends PortalException {

	public MinNumCorrectAnswerException() {
	}

	public MinNumCorrectAnswerException(String msg) {
		super(msg);
	}

	public MinNumCorrectAnswerException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public MinNumCorrectAnswerException(Throwable cause) {
		super(cause);
	}

}