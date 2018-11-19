<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="com.liferay.portal.kernel.util.HtmlUtil"%>
<%@page import="com.ted.lms.model.Module"%>
<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@page import="com.liferay.portal.kernel.security.permission.ActionKeys"%>
<%@page import="com.ted.lms.security.permission.resource.ModulePermission"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="javax.portlet.PortletURL"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@ include file="../init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Module module = (Module)row.getObject();

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcRenderCommandName", "/modules/view");
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<c:if test="<%= ModulePermission.contains(permissionChecker, module, ActionKeys.UPDATE) %>">
		<portlet:renderURL var="editModuleURL">
			<portlet:param name="mvcRenderCommandName" value="/modules/edit_module" />
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.UPDATE %>" />
			<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
			<portlet:param name="moduleId" value="<%= String.valueOf(module.getModuleId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			message="edit"
			url="${editModuleURL }"
		/>
	</c:if>
	<c:if test="<%= ModulePermission.contains(permissionChecker, module, ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= Module.class.getName() %>"
			modelResourceDescription="<%= HtmlUtil.escape(module.getTitle(themeDisplay.getLocale())) %>"
			resourcePrimKey="<%= String.valueOf(module.getModuleId()) %>"
			var="permissionsURL"
			windowState="<%= LiferayWindowState.POP_UP.toString() %>"
		/>

		<liferay-ui:icon
			message="permissions"
			method="get"
			url="<%=permissionsURL %>"
			useDialog="<%= true %>"
		/>
	</c:if>
	<c:if test="<%= ModulePermission.contains(permissionChecker, module, ActionKeys.DELETE) %>">
		<portlet:actionURL name="/modules/edit_module" var="deleteModuleURL">
			<portlet:param name="<%= Constants.CMD %>" value="<%=Constants.DELETE %>" />
			<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
			<portlet:param name="moduleId" value="<%= String.valueOf(module.getModuleId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			url="${deleteModuleURL }"
		/>
	</c:if>

	<liferay-export-import-changeset:publish-entity-menu-item
		className="<%= Module.class.getName() %>"
		groupId="<%= module.getGroupId() %>"
		uuid="<%= module.getUuid() %>"
	/>
</liferay-ui:icon-menu>