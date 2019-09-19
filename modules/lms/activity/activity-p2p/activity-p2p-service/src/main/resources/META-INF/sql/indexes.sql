create unique index IX_53EF51C7 on PTP_P2PActivity (actId, userId);
create index IX_1AEB52D3 on PTP_P2PActivity (asignationsCompleted);
create index IX_29BCD86A on PTP_P2PActivity (userId);
create index IX_91FB8624 on PTP_P2PActivity (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_FC3745A6 on PTP_P2PActivity (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_9DEE2746 on PTP_P2PActivityCorrections (actId, userId);
create unique index IX_B788CD51 on PTP_P2PActivityCorrections (p2pActivityId, userId);
create index IX_15A5A3CB on PTP_P2PActivityCorrections (userId);
create index IX_804A4C45 on PTP_P2PActivityCorrections (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_F2132007 on PTP_P2PActivityCorrections (uuid_[$COLUMN_LENGTH:75$], groupId);