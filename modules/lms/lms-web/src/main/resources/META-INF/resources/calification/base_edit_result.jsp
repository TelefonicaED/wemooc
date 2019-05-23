<%@page import="com.ted.lms.model.CalificationType"%>
<%@page import="com.ted.lms.service.LearningActivityResultLocalServiceUtil"%>
<%@page import="com.ted.lms.model.LearningActivityResult"%>
<%@page import="com.ted.lms.service.LearningActivityLocalServiceUtil"%>
<%@page import="com.ted.lms.model.LearningActivity"%>
<%@page import="com.ted.lms.registry.CalificationTypeFactoryRegistryUtil"%>
<%@page import="com.ted.lms.model.CalificationTypeFactory"%>
<%@page import="com.ted.lms.model.Course"%>
<%@page import="com.ted.lms.service.CourseLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@ include file="../init.jsp" %>

<%long actId = ParamUtil.getLong(request, "actId", 0);
long studentId = ParamUtil.getLong(request, "studentId", 0);
long courseId = ParamUtil.getLong(request, "courseId");
Course course = CourseLocalServiceUtil.getCourse(courseId);
CalificationTypeFactory calificationTypeFactory = CalificationTypeFactoryRegistryUtil.getCalificationTypeFactoryByType(course.getCalificationType());
CalificationType calificationType = calificationTypeFactory.getCalificationType(course);
LearningActivity activity = LearningActivityLocalServiceUtil.getLearningActivity(actId);
LearningActivityResult result = LearningActivityResultLocalServiceUtil.getLearningActivityResult(actId, studentId); %>

<aui:input type="text" name="result" label="calification.result" helpMessage="<%=LanguageUtil.format(themeDisplay.getLocale(), \"calification.edit-result.message\", 
						new Object[]{calificationType.translate(themeDisplay.getLocale(), activity.getPassPuntuation())})%>" 
		value='<%=result!=null?calificationType.translate(themeDisplay.getLocale(), result.getResult()):"" %>' >
   	<aui:validator name="number"></aui:validator>
   	<aui:validator  name="custom"  errorMessage="<%=LanguageUtil.format(themeDisplay.getLocale(), \"calification.edit-result.must-be-between\", new Object[]{calificationType.getMinValue(),calificationType.getMaxValue()})%>"  >
		function (val, fieldNode, ruleValue) {
			var result = false;
			if (val >= <%=calificationType.getMinValue() %> && val <= <%= calificationType.getMaxValue() %>) {
				result = true;
			}
			return result;					
		}
	</aui:validator>
</aui:input>