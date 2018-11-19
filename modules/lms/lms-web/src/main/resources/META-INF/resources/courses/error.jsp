<%@page import="com.ted.lms.exception.NoSuchCourseException"%>
<%@ include file="/courses/init.jsp" %>

<liferay-ui:error-header />

<liferay-ui:error exception="<%= NoSuchCourseException.class %>" message="the-course-could-not-be-found" />

<liferay-ui:error-principal />