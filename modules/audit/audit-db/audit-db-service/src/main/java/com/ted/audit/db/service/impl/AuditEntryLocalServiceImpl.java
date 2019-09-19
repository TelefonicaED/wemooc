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

package com.ted.audit.db.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.util.PortalUtil;
import com.ted.audit.db.model.AuditEntry;
import com.ted.audit.db.service.base.AuditEntryLocalServiceBaseImpl;

import java.util.Date;

import org.osgi.service.component.annotations.Component;

/**
 * The implementation of the audit entry local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.ted.audit.db.service.AuditEntryLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AuditEntryLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.ted.audit.db.model.AuditEntry",
	service = AopService.class
)
public class AuditEntryLocalServiceImpl extends AuditEntryLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>com.ted.audit.db.service.AuditEntryLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.ted.audit.db.service.AuditEntryLocalServiceUtil</code>.
	 */
	
	public void addAuditEntry(long companyId, long groupId, int actionId, String className, long classPK, long userId,
			String userName, String extraData) {
		addAuditEntry(companyId, groupId, actionId, className, classPK, 0, userId, userName, extraData);
	}
	
	public void addAuditEntry(long companyId, long groupId, int actionId, String className, long classPK, long associationClassPK, long userId, 
			String userName, String extraData) {
		AuditEntry auditEntry = auditEntryPersistence.create(counterLocalService.increment(AuditEntry.class.getName()));
		auditEntry.setStartDate(new Date(System.currentTimeMillis()));
		auditEntry.setCompanyId(companyId);
		auditEntry.setGroupId(groupId);
		auditEntry.setActionId(actionId);
		auditEntry.setClassNameId(PortalUtil.getClassNameId(className));
		auditEntry.setUserId(userId);
		auditEntry.setUserName(userName);
		auditEntry.setExtradata(extraData);
		auditEntry.setClassPK(classPK);
		auditEntry.setAssociationClassPK(associationClassPK);
		
		auditEntryPersistence.update(auditEntry);
	}
}