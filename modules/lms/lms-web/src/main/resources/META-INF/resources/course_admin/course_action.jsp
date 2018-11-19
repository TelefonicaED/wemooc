<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@page import="com.liferay.portal.kernel.security.permission.ActionKeys"%>
<%@page import="com.ted.lms.security.permission.resource.CoursePermission"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.ted.lms.model.Course"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="javax.portlet.PortletURL"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@ include file="../init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Course course = (Course)row.getObject();

PortletURL portletURL = renderResponse.createRenderURL();
portletURL.setParameter("mvcRenderCommandName", "/courses/view");
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="true"
>

	<c:if test="<%= CoursePermission.contains(permissionChecker, course, ActionKeys.UPDATE) %>">
		<portlet:renderURL var="editCourseURL">
			<portlet:param name="mvcRenderCommandName" value="/courses/edit_course" />
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.UPDATE %>" />
			<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
			<portlet:param name="courseId" value="<%= String.valueOf(course.getCourseId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			message="edit"
			url="${editCourseURL }"
		/>
	</c:if>

</liferay-ui:icon-menu>