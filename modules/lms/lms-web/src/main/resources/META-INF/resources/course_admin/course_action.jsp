<%@page import="com.ted.lms.constants.CourseConstants"%>
<%@page import="com.ted.lms.configuration.CourseServiceConfiguration"%>
<%@page import="com.liferay.trash.TrashHelper"%>
<%@page import="com.liferay.portal.kernel.dao.orm.QueryUtil"%>
<%@page import="com.liferay.portal.kernel.workflow.WorkflowConstants"%>
<%@page import="com.ted.lms.service.StudentLocalServiceUtil"%>
<%@page import="com.ted.lms.service.CourseResultLocalServiceUtil"%>
<%@page import="com.ted.lms.service.CourseLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.ted.lms.web.internal.configuration.CourseAdminPortletInstanceConfiguration"%>
<%@page import="com.ted.lms.web.internal.portlet.action.CourseAdminConfigurationAction"%>
<%@page import="com.ted.lms.web.internal.display.context.CourseDisplayContext"%>
<%@page import="com.ted.lms.constants.LMSActionKeys"%>
<%@page import="com.ted.lms.security.permission.resource.LMSPermission"%>
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

CourseDisplayContext courseDisplayContext = (CourseDisplayContext)request.getAttribute("courseDisplayContext");
CourseAdminPortletInstanceConfiguration configuration = courseDisplayContext.getCourseAdminConfiguration();
CourseServiceConfiguration courseServiceConfiguration = courseDisplayContext.getCourseServiceConfiguration();

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
	<c:if test="<%=CoursePermission.contains(permissionChecker, course, LMSActionKeys.ASSIGN_MEMBERS) && course.isApproved() && (course.getParentCourseId() != CourseConstants.DEFAULT_PARENT_COURSE_ID || courseServiceConfiguration.editionWithoutRestrictions() || course.getCountEditions() == 0)%>">
		<portlet:renderURL var="assignMembersURL">
			<portlet:param name="mvcRenderCommandName" value="/courses/view_members" />
			<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
			<portlet:param name="courseId" value="<%= String.valueOf(course.getCourseId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			message="assign-member"
			url="${assignMembersURL }"
		/>
	</c:if>
	<li aria-hidden="true" class="dropdown-divider" role="presentation"></li>
	<c:if test="<%=LMSPermission.contains(permissionChecker, themeDisplay.getScopeGroupId(), LMSActionKeys.ADD_COURSE) %>">
		<portlet:renderURL var="copyCourseURL">
			<portlet:param name="mvcRenderCommandName" value="/courses/copy_course" />
			<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
			<portlet:param name="courseId" value="<%= String.valueOf(course.getCourseId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			message='copy'
			url="${copyCourseURL }"
		/>
	</c:if>
	<c:if test="<%=configuration.courseActionClose() && CoursePermission.contains(permissionChecker, course, ActionKeys.UPDATE) %>">
		<c:choose>
			<c:when test="<%=course.isApproved() %>">
				<portlet:actionURL var="closeCourseURL" name="/courses/edit_course">
					<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
					<portlet:param name="courseId" value="<%= String.valueOf(course.getCourseId()) %>" />
					<portlet:param name="<%= Constants.CMD %>" value="<%=Constants.EXPIRE %>" />
				</portlet:actionURL>
				<liferay-ui:icon
					message='close'
					url="<%= 
						\"javascript:AUI().use(function(A){ \"+
						\"   msg = '\" + LanguageUtil.get(themeDisplay.getLocale(), \"course-admin.confirm.close\") + \"';\" +
						\"   if(confirm(msg)) { \" +
						\"       window.location.href = '\" + closeCourseURL.toString() + \"';\" +
						\"   } else { \" +
						\"       return false; \" +
						\"   } \" +
						\"}) && undefined\"%>"
				/>
			</c:when>
			<c:when test="<%=course.isExpired() %>">
				<portlet:actionURL var="openCourseURL" name="/courses/edit_course">
					<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
					<portlet:param name="courseId" value="<%= String.valueOf(course.getCourseId()) %>" />
					<portlet:param  name="<%= Constants.CMD %>" value="<%=Constants.APPROVE %>" />
				</portlet:actionURL>
				<liferay-ui:icon
					message='open'
					url="${openCourseURL}"
				/>
			</c:when>
		</c:choose>
	</c:if>
	<c:if test="<%=configuration.courseActionDelete() && CoursePermission.contains(permissionChecker, course, ActionKeys.DELETE) && 
			StudentLocalServiceUtil.countStudentsFinished(course.getCourseId(), course.getCompanyId(), null, WorkflowConstants.STATUS_ANY, null, 
					QueryUtil.ALL_POS, QueryUtil.ALL_POS) == 0%>">
		<portlet:actionURL name="/courses/edit_course" var="deleteURL">
			<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
			<portlet:param name="courseId" value="<%= String.valueOf(course.getCourseId()) %>" />
			<portlet:param name="<%=Constants.CMD %>" value="<%=courseDisplayContext.getTrashHelper().isTrashEnabled(themeDisplay.getScopeGroupId()) ? Constants.MOVE_TO_TRASH : Constants.DELETE %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			trash="<%= courseDisplayContext.getTrashHelper().isTrashEnabled(themeDisplay.getScopeGroupId()) %>"
			url="${deleteURL }"
		/>			
	</c:if>
</liferay-ui:icon-menu>