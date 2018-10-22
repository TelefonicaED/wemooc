package com.ted.lms.learning.activity.resource.internal.web.util;

import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.ted.lms.learning.activity.resource.internal.web.constants.ResourceInternalPropsKeys;
import com.ted.lms.learning.activity.resource.internal.web.constants.ResourceInternalPropsValues;

public class ResourceInternalPrefsPropsValues {
	
	public static boolean getDocumentLinked(long companyId) {
		return PrefsPropsUtil.getBoolean(companyId, ResourceInternalPropsKeys.DOCUMENT_LINKED, ResourceInternalPropsValues.DOCUMENT_LINKED);
	}
}
