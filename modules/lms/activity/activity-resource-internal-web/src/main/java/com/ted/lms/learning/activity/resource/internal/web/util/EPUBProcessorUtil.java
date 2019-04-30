package com.ted.lms.learning.activity.resource.internal.web.util;

import com.liferay.portal.kernel.repository.model.FileVersion;

public class EPUBProcessorUtil {
	public static boolean isEPUB(FileVersion fileVersion) {

		return fileVersion.getMimeType()!=null && fileVersion.getMimeType().equals("application/epub+zip");
	}
}
