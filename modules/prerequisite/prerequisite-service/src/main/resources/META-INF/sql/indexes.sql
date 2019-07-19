create index IX_92554EA9 on pre_PrerequisiteRelation (classNameId, classPK);
create index IX_C0E93525 on pre_PrerequisiteRelation (classNamePrerequisiteId, classNameId, classPK);
create index IX_7ECCAFE0 on pre_PrerequisiteRelation (uuid_[$COLUMN_LENGTH:75$]);