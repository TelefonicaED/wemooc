<%@page import="com.liferay.portal.kernel.portlet.PortletLayoutFinder"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.ted.lms.constants.LMSActionKeys"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="com.liferay.portal.kernel.util.HtmlUtil"%>
<%@page import="com.ted.lms.model.Module"%>
<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@page import="com.liferay.portal.kernel.security.permission.ActionKeys"%>
<%@page import="com.ted.lms.security.permission.resource.ModulePermission"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>


<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<c:if test="<%= ModulePermission.contains(permissionChecker, module, ActionKeys.UPDATE) %>">
		<liferay-ui:icon
			message="edit"
			url="<%=module.getURLEdit(liferayPortletRequest)%>"
		/>
		
		<portlet:actionURL var="moveUpModuleURL">
			<portlet:param name="mvcRenderCommandName" value="/modules/edit_module" />
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.MOVE %>" />
			<portlet:param name="<%= Constants.ACTION %>" value="-1" />
			<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
			<portlet:param name="moduleId" value="<%= String.valueOf(module.getModuleId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon
			message="move-up"
			url="${moveUpModuleURL }"
		/>
		
		<portlet:actionURL var="moveDownModuleURL">
			<portlet:param name="mvcRenderCommandName" value="/modules/edit_module" />
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.MOVE %>" />
			<portlet:param name="<%= Constants.ACTION %>" value="1" />
			<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
			<portlet:param name="moduleId" value="<%= String.valueOf(module.getModuleId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon
			message="move-down"
			url="${moveUpModuleURL }"
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
			message="set-permissions"
			method="get"
			url="<%=permissionsURL %>"
			useDialog="<%= true %>"
		/>
	</c:if>
	<c:if test="<%=ModulePermission.contains(permissionChecker, module, LMSActionKeys.ADD_ACT) %>">
		<%PortletLayoutFinder activityPortletLayoutFinder = (PortletLayoutFinder)request.getAttribute("activityPortletLayoutFinder"); %>
		<liferay-ui:icon
			message="add-activity"
			url="<%=module.getURLNewActivity(activityPortletLayoutFinder, renderRequest, themeDisplay)%>"
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
</liferay-ui:icon-menu>