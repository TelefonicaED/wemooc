create index IX_3FD5DFAB on QU_Answer (groupId);
create index IX_44D45B34 on QU_Answer (questionId);
create index IX_3BBE813 on QU_Answer (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_B5355755 on QU_Answer (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_4DE8A796 on QU_Question (actId);
create index IX_4977AD43 on QU_Question (groupId);
create index IX_1A6BE57B on QU_Question (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_C3158EBD on QU_Question (uuid_[$COLUMN_LENGTH:75$], groupId);