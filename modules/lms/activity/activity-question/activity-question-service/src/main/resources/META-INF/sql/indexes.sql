create index IX_C362A4F9 on LMS_Answer (groupId);
create index IX_DF78EB26 on LMS_Answer (questionId);
create index IX_B01D3A85 on LMS_Answer (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_4A4C9E47 on LMS_Answer (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_D1756CE4 on LMS_Question (actId);
create index IX_1CE85711 on LMS_Question (groupId);
create index IX_33C2636D on LMS_Question (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_6F76E12F on LMS_Question (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_E95CE3AB on qu_Answer (groupId);
create index IX_5BBED734 on qu_Answer (questionId);
create index IX_47D96413 on qu_Answer (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_1A81D355 on qu_Answer (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_F76FAB96 on qu_Question (actId);
create index IX_AD4DB143 on qu_Question (groupId);
create index IX_CD1A617B on qu_Question (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_7330ABD on qu_Question (uuid_[$COLUMN_LENGTH:75$], groupId);