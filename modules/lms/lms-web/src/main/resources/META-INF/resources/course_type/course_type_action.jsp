<%@page import="com.ted.lms.model.CourseType"%>
<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@page import="com.liferay.portal.kernel.security.permission.ActionKeys"%>
<%@page import="com.ted.lms.security.permission.resource.CoursePermission"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="javax.portlet.PortletURL"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@ include file="../init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

CourseType courseType = (CourseType)row.getObject();

%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="true"
>
	<portlet:renderURL var="editCourseTypeURL">
		<portlet:param name="mvcRenderCommandName" value="/course_type/edit_course_type" />
		<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.UPDATE %>" />
		<portlet:param name="courseTypeId" value="<%= String.valueOf(courseType.getCourseTypeId()) %>" />
	</portlet:renderURL>

	<liferay-ui:icon
		message="edit"
		url="${editCourseTypeURL }"
	/>

</liferay-ui:icon-menu>