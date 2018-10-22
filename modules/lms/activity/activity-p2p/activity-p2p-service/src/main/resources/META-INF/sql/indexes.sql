create unique index IX_A2D6DB61 on LMS_P2PActivity (actId, userId);
create index IX_889CA7F9 on LMS_P2PActivity (asignationsCompleted);
create index IX_13858810 on LMS_P2PActivity (userId);
create index IX_C4415E4A on LMS_P2PActivity (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_8A40EF4C on LMS_P2PActivity (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_7B04796C on LMS_P2PActivityCorrections (actId, userId);
create index IX_FDAB3FE0 on LMS_P2PActivityCorrections (p2pActivityId, actId);
create index IX_C559977 on LMS_P2PActivityCorrections (p2pActivityId, userId);
create index IX_2C1AD065 on LMS_P2PActivityCorrections (userId);
create index IX_A8CC005F on LMS_P2PActivityCorrections (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_B7C712A1 on LMS_P2PActivityCorrections (uuid_[$COLUMN_LENGTH:75$], groupId);