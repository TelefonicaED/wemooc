create table LMS_Answer (
	uuid_ VARCHAR(75) null,
	answerId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	questionId LONG,
	precedence LONG,
	answer VARCHAR(75) null,
	correct BOOLEAN,
	points BOOLEAN,
	feedbackCorrect VARCHAR(75) null,
	feedbackIncorrect VARCHAR(75) null
);

create table LMS_Question (
	uuid_ VARCHAR(75) null,
	questionId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	actId LONG,
	title VARCHAR(75) null,
	text_ VARCHAR(75) null,
	questionType LONG,
	active_ BOOLEAN,
	weight LONG,
	penalize BOOLEAN,
	orderedAnswers BOOLEAN,
	extraContent VARCHAR(75) null
);