package com.ted.audit.db.model.impl;

import com.liferay.portal.kernel.util.PortalUtil;
import com.ted.audit.api.Audit;
import com.ted.audit.db.service.AuditEntryLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    immediate = true,
    property = {},
    service = Audit.class
)
public class AuditDB implements Audit{

	@Override
	public long getClassNameId() {
		return PortalUtil.getClassNameId(AuditDB.class);
	}

	@Override
	public String getClassName() {
		return AuditDB.class.getName();
	}

	@Override
	public int audit(long companyId, long groupId, int actionId, String className, long classPK, long userId,
			String userName, String extraData) {
		auditEntryLocalService.addAuditEntry(companyId, groupId, actionId, className, classPK, 0, userId, userName, extraData);
		return 0;
	}

	@Override
	public int audit(long companyId, long groupId, int actionId, String className, long classPK, long associationClassPK, long userId, 
			String userName, String extraData) {
		auditEntryLocalService.addAuditEntry(companyId, groupId, actionId, className, classPK, associationClassPK, userId, userName, extraData);
		return 0;
	}
	
	@Reference(unbind = "-")
	protected void setAuditEntryLocalService(AuditEntryLocalService auditEntryLocalService) {
		this.auditEntryLocalService = auditEntryLocalService;
	}
	
	protected AuditEntryLocalService auditEntryLocalService;

}
