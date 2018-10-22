package com.ted.lms.learning.activity.resource.internal.web.constants;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;

public class ResourceInternalPropsValues {
	public static final boolean DOCUMENT_LINKED = GetterUtil.getBoolean(PropsUtil.get(ResourceInternalPropsKeys.DOCUMENT_LINKED));
}
