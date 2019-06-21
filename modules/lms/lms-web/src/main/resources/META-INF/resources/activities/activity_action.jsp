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
	<liferay-ui:icon
		image="edit"
		message="edit"
		url="<%=activity.getURLEdit(themeDisplay)%>"
	/>	
	
	<portlet:actionURL var="moveUpLearningActivityURL">
		<portlet:param name="mvcRenderCommandName" value="/activities/edit_activity" />
		<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.MOVE %>" />
		<portlet:param name="<%= Constants.ACTION %>" value="-1" />
		<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
		<portlet:param name="actId" value="<%= String.valueOf(activity.getActId()) %>" />
	</portlet:actionURL>

	<liferay-ui:icon
		image="top"
		message="move-up"
		url="${moveUpLearningActivityURL }"
	/>
	
	<portlet:actionURL var="moveDownLearningActivityURL">
		<portlet:param name="mvcRenderCommandName" value="/activities/edit_activity" />
		<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.MOVE %>" />
		<portlet:param name="<%= Constants.ACTION %>" value="1" />
		<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
		<portlet:param name="actId" value="<%= String.valueOf(activity.getActId()) %>" />
	</portlet:actionURL>

	<liferay-ui:icon
		image="bottom"
		message="move-down"
		url="${moveDownLearningActivityURL }"
	/>
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

	<portlet:actionURL var="changeVisibilityURL">
		<portlet:param name="mvcRenderCommandName" value="/activities/edit_activity" />
		<portlet:param name="<%= Constants.CMD %>" value="<%= LMSConstants.CHANGE_VISIBILITY %>" />
		<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
		<portlet:param name="actId" value="<%= String.valueOf(activity.getActId()) %>" />
	</portlet:actionURL>

	<%
		Role student = RoleLocalServiceUtil.getRole(themeDisplay.getCompanyId(), LMSRoleConstants.STUDENT);
		boolean visible = ResourcePermissionLocalServiceUtil.hasResourcePermission(student.getCompanyId(), LearningActivity.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(activity.getActId()),student.getRoleId(), ActionKeys.VIEW);
	%>
	
	<liferay-ui:icon message="<%=(visible)?\"learning-activity.visible\":\"learning-activity.invisible\" %>"  image="<%=(visible)?\"global\":\"close\" %>" 
		url="${changeVisibilityURL }"/>
</c:if>

<c:if test="<%=LearningActivityPermission.contains(permissionChecker, activity, ActionKeys.DELETE) %>">

	<portlet:actionURL name="/activities/edit_activity" var="deleteLearningActivityURL">
			<portlet:param name="<%= Constants.CMD %>" value="<%=Constants.DELETE %>" />
			<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
			<portlet:param name="actId" value="<%= String.valueOf(activity.getActId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			url="${deleteModuleURL }"
		/>
	
	<%
	long numLearningActivityResults = LearningActivityResultLocalServiceUtil.getLearningActivityCountByActId(activity.getActId());			
	if(numLearningActivityResults>0){%>
	
		<liferay-ui:icon image="delete" message="delete" url="#" onClick="javascript: alert(Liferay.Language.get('activity.delete-activity-with-result'));" />
	<%}else{ %>
		<liferay-ui:icon image="delete" message="delete" onClick="javascript:if(confirm('are-you-sure-you-want-to-delete-this')){location.href='${deleteLearningActivityURL}';}"/>
	<%} %> 					 				
</c:if>