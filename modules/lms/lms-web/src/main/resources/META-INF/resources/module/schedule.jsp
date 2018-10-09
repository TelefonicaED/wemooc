<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.ted.lms.exception.ModuleEndDateException"%>
<%@page import="com.ted.lms.exception.ModuleStartDateException"%>
<%@page import="com.ted.lms.model.Module"%>
<%@ include file="../init.jsp" %>

<liferay-ui:error-marker
	key="<%= WebKeys.ERROR_SECTION %>"
	value="schedule"
/>

<aui:model-context bean="${module }" model="<%= Module.class %>" />

<liferay-ui:error exception="<%= ModuleStartDateException.class %>" message="please-enter-a-valid-start-date" />
<liferay-ui:error exception="<%= ModuleEndDateException.class %>" message="please-enter-a-valid-end-date" />

<aui:input dateTogglerCheckboxLabel="module.edit.user-start-execution-date-course" disabled="${not empty module && not empty module.startDate }" formName="fm" name="startDate" value="${empty module || empty module.startDate ? course.executionStartDateCalendar : module.startDateCalendar }"/>

<aui:input dateTogglerCheckboxLabel="module.edit.user-end-execution-date-course" disabled="${not empty module && not empty module.endDate }" formName="fm" name="endDate" value="${empty module || empty module.endDate ? course.executionEndDateCalendar : module.endDateCalendar }"/>
