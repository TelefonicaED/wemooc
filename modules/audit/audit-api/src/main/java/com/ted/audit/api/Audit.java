package com.ted.audit.api;

/**
 * @author JE10436
 */
public interface Audit {
	
	public long getClassNameId();
	
	public String getClassName();
	
	public int audit(long companyId, long groupId, int actionId, String className, long classPK, long userId, String userName, String extraData);
	
	public int audit(long companyId, long groupId, int actionId, String className, long classPK, long associationClassPK, long userId, String userName, String extraData);
}