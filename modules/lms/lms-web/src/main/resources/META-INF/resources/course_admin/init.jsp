<%@page import="com.ted.lms.web.internal.display.context.CourseDisplayContext"%>
<%@ include file="../init.jsp" %>

<%
CourseDisplayContext courseDisplayContext = new CourseDisplayContext(request, liferayPortletRequest, liferayPortletResponse, portletPreferences, trashHelper);

pageContext.setAttribute("courseDisplayContext", courseDisplayContext);
%> 