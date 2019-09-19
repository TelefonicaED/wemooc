package com.ted.audit.api;

import com.ted.audit.api.registry.AuditRegistryUtil;

import java.util.List;

public class AuditFactory {
	public static void audit(long companyId, long groupId, int actionId, String className, long classPK, long userId, String userName, String extraData) {
		List<Audit> audits = AuditRegistryUtil.getAudits(companyId, true);
		for(Audit audit: audits) {
			audit.audit(companyId, groupId, actionId, className, classPK, userId, userName, extraData);
		}
	}
	
	public static void audit(long companyId, long groupId, int actionId, String className, long classPK, long associationClassPK, long userId, String userName, String extraData) {
		List<Audit> audits = AuditRegistryUtil.getAudits(companyId, true);
		for(Audit audit: audits) {
			audit.audit(companyId, groupId, actionId, className, classPK, associationClassPK, userId, userName, extraData);
		}
	}
}
