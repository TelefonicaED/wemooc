create index IX_3FA322B3 on post_PostconditionRelation (classNameId, classPK);
create index IX_E691F87A on post_PostconditionRelation (classNamePostconditionId, classNameId, classPK);
create index IX_6C368916 on post_PostconditionRelation (uuid_[$COLUMN_LENGTH:75$]);