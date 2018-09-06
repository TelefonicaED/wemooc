create table pre_PrerequisiteRelation (
	uuid_ VARCHAR(75) null,
	prerequisiteRelationId LONG not null primary key,
	classNamePrerequisiteId LONG,
	classNameId LONG,
	classPK LONG,
	extraData VARCHAR(75) null
);