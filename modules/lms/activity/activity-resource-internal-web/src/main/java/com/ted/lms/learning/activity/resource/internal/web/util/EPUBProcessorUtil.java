package com.ted.lms.learning.activity.resource.internal.web.util;

import com.liferay.portal.kernel.repository.model.FileVersion;

public class EPUBProcessorUtil {
	public static boolean isEPUB(FileVersion fileVersion) {
		
		System.out.println("mimeType: " + fileVersion.getMimeType());

		return fileVersion.getMimeType()!=null && fileVersion.getMimeType().equals("application/epub+zip");
	}
}
