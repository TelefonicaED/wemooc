create table PTP_P2PActivity (
	uuid_ VARCHAR(75) null,
	p2pActivityId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userCreateId LONG,
	userCreateName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	actId LONG,
	userId LONG,
	fileEntryId LONG,
	countCorrections LONG,
	description VARCHAR(75) null,
	date_ DATE null,
	asignationsCompleted BOOLEAN
);

create table PTP_P2PActivityCorrections (
	uuid_ VARCHAR(75) null,
	p2pActivityCorrectionsId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userCreateId LONG,
	userCreateName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	p2pActivityId LONG,
	userId LONG,
	actId LONG,
	description VARCHAR(75) null,
	date_ DATE null,
	fileEntryId LONG,
	result LONG
);