create table Aud_AuditEntry (
	auditId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	classNameId LONG,
	classPK LONG,
	associationClassPK LONG,
	userId LONG,
	userName VARCHAR(75) null,
	startDate DATE null,
	endDate DATE null,
	actionId INTEGER,
	extradata VARCHAR(75) null
);