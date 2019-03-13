package com.ted.lms.learning.activity.resource.internal.web.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.ted.lms.learning.activity.resource.internal.web.constants.ResourceInternalConstants;
import com.ted.lms.learning.activity.resource.internal.web.constants.ResourceInternalPropsKeys;
import com.ted.lms.learning.activity.resource.internal.web.constants.ResourceInternalPropsValues;

public class ResourceInternalPrefsPropsValues {
	
	public static String[] getAssetTypes(long companyId) {
		String[] defaultValues = ResourceInternalPropsValues.ASSET_TYPES;
		if(Validator.isNull(defaultValues) || defaultValues.length == 0) {
			defaultValues = StringUtil.split(ResourceInternalConstants.DEFAULT_ASSET_TYPES, StringPool.COMMA);
		}
		return PrefsPropsUtil.getStringArray(companyId, ResourceInternalPropsKeys.ASSET_TYPES, StringPool.COMMA, defaultValues);
	}
}
