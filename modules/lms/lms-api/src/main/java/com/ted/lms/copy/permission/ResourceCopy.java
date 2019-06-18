package com.ted.lms.copy.permission;

import com.liferay.portal.kernel.exception.PortalException;

public interface ResourceCopy {
	
	public void copyModelResourcePermissions(long companyId, String name, long oldPrimKey, long newPrimKey) 
			throws PortalException;
}
