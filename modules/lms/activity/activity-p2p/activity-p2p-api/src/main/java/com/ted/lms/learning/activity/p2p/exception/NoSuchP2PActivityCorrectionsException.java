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
package com.ted.lms.learning.activity.p2p.exception;

import org.osgi.annotation.versioning.ProviderType;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class NoSuchP2PActivityCorrectionsException extends NoSuchModelException {

	public NoSuchP2PActivityCorrectionsException() {
	}

	public NoSuchP2PActivityCorrectionsException(String msg) {
		super(msg);
	}

	public NoSuchP2PActivityCorrectionsException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchP2PActivityCorrectionsException(Throwable cause) {
		super(cause);
	}

}