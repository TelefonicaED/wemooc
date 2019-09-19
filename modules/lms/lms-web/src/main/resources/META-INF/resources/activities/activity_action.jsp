<%@page import="com.ted.lms.constants.LMSRoleConstants"%>
<%@page import="com.liferay.portal.kernel.model.ResourceConstants"%>
<%@page import="com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.model.RoleConstants"%>
<%@page import="com.liferay.portal.kernel.service.RoleLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.model.Role"%>
<%@page import="com.ted.lms.constants.LMSConstants"%>
<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@page import="com.ted.lms.util.LMSPrefsPropsValues"%>
<%@page import="com.ted.lms.constants.LMSActionKeys"%>
<%@page import="com.ted.lms.model.LearningActivity"%>
<%@page import="com.ted.lms.service.LearningActivityResultLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.security.permission.ActionKeys"%>
<%@page import="com.ted.lms.security.permission.resource.LearningActivityPermission"%>

<c:if test="<%=LearningActivityPermission.contains(permissionChecker, activity, ActionKeys.UPDATE) %>">							
	
	<a href="<%=activity.getURLEdit(themeDisplay)%>"><span class="glyphicon glyphicon-pencil" title="<liferay-ui:message key='edit' />"></span></a>
	
	<portlet:actionURL var="moveUpLearningActivityURL" name="/activities/edit_activity">
		<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.MOVE %>" />
		<portlet:param name="<%= Constants.ACTION %>" value="-1" />
		<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
		<portlet:param name="actId" value="<%= String.valueOf(activity.getActId()) %>" />
	</portlet:actionURL>

	<a href="${moveUpLearningActivityURL }"><span class="glyphicon glyphicon-arrow-up" title="<liferay-ui:message key='move-up' />"></span></a>
	
	<portlet:actionURL var="moveDownLearningActivityURL" name="/activities/edit_activity">
		<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.MOVE %>" />
		<portlet:param name="<%= Constants.ACTION %>" value="1" />
		<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
		<portlet:param name="actId" value="<%= String.valueOf(activity.getActId()) %>" />
	</portlet:actionURL>
	
	<a href="${moveDownLearningActivityURL }"><span class="glyphicon glyphicon-arrow-down" title="<liferay-ui:message key='move-down' />"></span></a>
</c:if>
<c:if test="<%=LearningActivityPermission.contains(permissionChecker, activity, ActionKeys.PERMISSIONS) %>">
	<liferay-security:permissionsURL
		modelResource="<%= LearningActivity.class.getName() %>"
		modelResourceDescription="<%= HtmlUtil.escape(activity.getTitle(themeDisplay.getLocale())) %>"
		resourcePrimKey="<%= String.valueOf(activity.getActId()) %>"
		var="permissionsURL"
		windowState="<%= LiferayWindowState.POP_UP.toString() %>"
	/>

	<liferay-ui:icon
		image="permissions"
		message="set-permissions"
		method="get"
		url="<%=permissionsURL %>"
		useDialog="<%= true %>"
	/>
</c:if>

<c:if test="<%=LMSPrefsPropsValues.getLearningActivityChangeVisibility(themeDisplay.getCompanyId()) && LearningActivityPermission.contains(permissionChecker, activity, LMSActionKeys.CHANGE_VISIBILITY)%>">

	<portlet:actionURL var="changeVisibilityURL" name="/activities/edit_activity">
		<portlet:param name="<%= Constants.CMD %>" value="<%= LMSConstants.CHANGE_VISIBILITY %>" />
		<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
		<portlet:param name="actId" value="<%= String.valueOf(activity.getActId()) %>" />
	</portlet:actionURL>

	<%
		Role student = RoleLocalServiceUtil.getRole(themeDisplay.getCompanyId(), LMSRoleConstants.STUDENT);
		boolean visible = ResourcePermissionLocalServiceUtil.hasResourcePermission(student.getCompanyId(), LearningActivity.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(activity.getActId()),student.getRoleId(), ActionKeys.VIEW);
	%>
	
	<a href="${changeVisibilityURL }">
		<c:choose>
			<c:when test="<%=visible %>">
				<span class="glyphicon glyphicon-eye-open" title="<liferay-ui:message key='learning-activity.visible' />"></span>
			</c:when>
			<c:otherwise>
				<span class="glyphicon glyphicon-eye-close" title="<liferay-ui:message key='invisible' />"></span>
			</c:otherwise>
		</c:choose>
	</a>
</c:if>

<c:if test="<%=LearningActivityPermission.contains(permissionChecker, activity, ActionKeys.DELETE) %>">

	<portlet:actionURL name="/activities/edit_activity" var="deleteLearningActivityURL">
		<portlet:param name="<%= Constants.CMD %>" value="<%=Constants.DELETE %>" />
		<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
		<portlet:param name="actId" value="<%= String.valueOf(activity.getActId()) %>" />
	</portlet:actionURL>
	<%
	long numLearningActivityResults = LearningActivityResultLocalServiceUtil.getLearningActivityCountByActId(activity.getActId());			
	if(numLearningActivityResults>0){%>
		<a href="javascript: alert(Liferay.Language.get('activity.delete-activity-with-result'));"><span class="glyphicon glyphicon-trash" title="<liferay-ui:message key='delete' />"></span></a>
	<%}else{ %>
		<a href="javascript:if(confirm('are-you-sure-you-want-to-delete-this')){location.href='${deleteLearningActivityURL}';}"><span class="glyphicon glyphicon-trash" title="<liferay-ui:message key='delete' />"></span></a>
	<%} %> 					 				
</c:if>