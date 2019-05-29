create index IX_46E9FB7A on LMS_Course (companyId);
create unique index IX_53067AC6 on LMS_Course (groupCreatedId);
create index IX_37CF187C on LMS_Course (groupId);
create index IX_344EA6C2 on LMS_Course (parentCourseId);
create index IX_74970A62 on LMS_Course (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_93C2C164 on LMS_Course (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_664FE819 on LMS_CourseResult (courseId, passed);
create unique index IX_DE7486F on LMS_CourseResult (courseId, userId);

create index IX_FEF374C8 on LMS_LearningActivity (companyId);
create index IX_5238B462 on LMS_LearningActivity (groupId, priority);
create index IX_20DCD39D on LMS_LearningActivity (groupId, required);
create index IX_52D8FB13 on LMS_LearningActivity (moduleId, priority);
create index IX_217D1A4E on LMS_LearningActivity (moduleId, required);
create index IX_5FD18E64 on LMS_LearningActivity (moduleId, typeId);
create index IX_F69CFF89 on LMS_LearningActivity (typeId);
create index IX_653F1D54 on LMS_LearningActivity (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_78D6E8D6 on LMS_LearningActivity (uuid_[$COLUMN_LENGTH:75$], groupId);

create unique index IX_AD9F417A on LMS_LearningActivityResult (actId, userId);
create index IX_92E29591 on LMS_LearningActivityResult (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_D8854C53 on LMS_LearningActivityResult (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_A111CDC9 on LMS_LearningActivityTry (actId, userId, endDate);
create index IX_2445C2FB on LMS_LearningActivityTry (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_6CC8CC3D on LMS_LearningActivityTry (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_23FFC0C9 on LMS_Module (companyId);
create index IX_2F8DC090 on LMS_Module (groupId, order_);
create index IX_34956971 on LMS_Module (groupId, status);
create index IX_FBE67E33 on LMS_Module (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_4D4EF575 on LMS_Module (uuid_[$COLUMN_LENGTH:75$], groupId);

create unique index IX_CE0FB051 on LMS_ModuleResult (moduleId, userId);