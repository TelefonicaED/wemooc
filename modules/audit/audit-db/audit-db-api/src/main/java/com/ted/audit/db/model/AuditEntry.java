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

package com.ted.audit.db.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the AuditEntry service. Represents a row in the &quot;Aud_AuditEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see AuditEntryModel
 * @see com.ted.audit.db.model.impl.AuditEntryImpl
 * @see com.ted.audit.db.model.impl.AuditEntryModelImpl
 * @generated
 */
@ImplementationClassName("com.ted.audit.db.model.impl.AuditEntryImpl")
@ProviderType
public interface AuditEntry extends AuditEntryModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.ted.audit.db.model.impl.AuditEntryImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<AuditEntry, Long> AUDIT_ID_ACCESSOR = new Accessor<AuditEntry, Long>() {
			@Override
			public Long get(AuditEntry auditEntry) {
				return auditEntry.getAuditId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<AuditEntry> getTypeClass() {
				return AuditEntry.class;
			}
		};
}