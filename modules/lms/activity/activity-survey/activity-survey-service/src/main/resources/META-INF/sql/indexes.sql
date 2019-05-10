create index IX_933494DB on Survey_SurveyResult (actId);
create index IX_CED1D0A4 on Survey_SurveyResult (answerId, questionId);
create index IX_E8C5C66 on Survey_SurveyResult (questionId, actId);
create index IX_27F6E3DC on Survey_SurveyResult (userId);
create index IX_D359A1D2 on Survey_SurveyResult (uuid_[$COLUMN_LENGTH:75$]);