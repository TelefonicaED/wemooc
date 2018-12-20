create table qu_Answer (
	uuid_ VARCHAR(75) null,
	answerId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	questionId LONG,
	actId LONG,
	answer STRING null,
	correct BOOLEAN,
	feedbackCorrect STRING null,
	feedbackIncorrect STRING null
);

create table qu_Question (
	uuid_ VARCHAR(75) null,
	questionId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	actId LONG,
	text_ STRING null,
	questionType LONG,
	active_ BOOLEAN,
	weight LONG,
	penalize BOOLEAN,
	extraContent VARCHAR(75) null
);