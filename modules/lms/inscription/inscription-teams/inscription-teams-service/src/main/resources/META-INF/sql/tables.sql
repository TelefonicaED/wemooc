create table iteam_Schedule (
	scheduleId LONG not null primary key,
	teamId LONG,
	startDate DATE null,
	endDate DATE null
);