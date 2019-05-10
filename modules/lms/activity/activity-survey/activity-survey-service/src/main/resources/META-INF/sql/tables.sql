create table Survey_SurveyResult (
	uuid_ VARCHAR(75) null,
	surveyResultId LONG not null primary key,
	actId LONG,
	latId LONG,
	questionId LONG,
	answerId LONG,
	userId LONG,
	freeAnswer TEXT null
);