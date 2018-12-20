create index IX_E95CE3AB on qu_Answer (groupId);
create index IX_5BBED734 on qu_Answer (questionId);
create index IX_47D96413 on qu_Answer (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_1A81D355 on qu_Answer (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_F76FAB96 on qu_Question (actId);
create index IX_AD4DB143 on qu_Question (groupId);
create index IX_CD1A617B on qu_Question (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_7330ABD on qu_Question (uuid_[$COLUMN_LENGTH:75$], groupId);