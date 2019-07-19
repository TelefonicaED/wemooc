create table post_PostconditionRelation (
	uuid_ VARCHAR(75) null,
	postconditionRelationId LONG not null primary key,
	classNamePostconditionId LONG,
	classNameId LONG,
	classPK LONG,
	extraData VARCHAR(75) null
);