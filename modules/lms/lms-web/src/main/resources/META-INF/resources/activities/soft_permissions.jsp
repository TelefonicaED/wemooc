<%@page import="com.liferay.portal.util.ResourcePermissionUtil"%>
<%@page import="com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil"%>
<%@page import="com.liferay.portlet.portletconfiguration.action.ActionUtil"%>
<%@page import="com.liferay.portal.kernel.util.HtmlUtil"%>
<%@page import="com.liferay.portal.kernel.security.permission.ResourceActionsUtil"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.liferay.portal.kernel.security.permission.ActionKeys"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portal.kernel.model.RoleConstants"%>
<%@page import="com.liferay.portal.kernel.dao.search.SearchContainer"%>
<%@page import="com.liferay.portal.kernel.model.Resource"%>
<%@page import="com.ted.lms.web.internal.display.context.PortletConfigurationPermissionsDisplayContext"%>
<%@ include file="../init.jsp" %>

<%
PortletConfigurationPermissionsDisplayContext portletConfigurationPermissionsDisplayContext = new PortletConfigurationPermissionsDisplayContext(request, renderRequest);
Resource resource = portletConfigurationPermissionsDisplayContext.getResource();
SearchContainer roleSearchContainer = portletConfigurationPermissionsDisplayContext.getRoleSearchContainer();
%>

<div class="edit-permissions portlet-configuration-edit-permissions">
	<div class="portlet-configuration-body-content">
		<aui:form action="<%=portletConfigurationPermissionsDisplayContext.getUpdateRolePermissionsURL() %>" cssClass="container-fluid-1280" method="post" name="fm">
			<aui:input name="resourceId" type="hidden" value="<%= resource.getResourceId() %>" />

			<liferay-ui:search-container
				searchContainer="<%= roleSearchContainer %>"
			>
				<liferay-ui:search-container-row
					className="com.liferay.portal.kernel.model.Role"
					cssClass="table-title"
					escapedModel="<%= true %>"
					keyProperty="roleId"
					modelVar="role"
				>

					<%
					String name = role.getName();
					%>

					<liferay-ui:search-container-column-text
						name="role"
					>

						<%
						String icon = "user";
						String message = "regular-role";

						int roleType = role.getType();

						if (roleType == RoleConstants.TYPE_SITE) {
							icon = "sites";
							message = "site-role";
						}
						else if (roleType == RoleConstants.TYPE_ORGANIZATION) {
							icon = "organizations";
							message = "organization-role";
						}
						%>

						<liferay-ui:icon
							icon="<%= icon %>"
							label="<%= false %>"
							markupView="lexicon"
							message="<%= LanguageUtil.get(request, message) %>"
						/>

						<%= role.getTitle(locale) %>

						<c:if test="<%= layout.isPrivateLayout() && name.equals(RoleConstants.GUEST) %>">
							<liferay-ui:icon-help message="under-the-current-configuration-all-users-automatically-inherit-permissions-from-the-guest-role" />
						</c:if>
					</liferay-ui:search-container-column-text>

					<%

					// Actions

					List<String> currentIndividualActions = new ArrayList<String>();
					List<String> currentGroupActions = new ArrayList<String>();
					List<String> currentGroupTemplateActions = new ArrayList<String>();
					List<String> currentCompanyActions = new ArrayList<String>();

					ResourcePermissionUtil.populateResourcePermissionActionIds(portletConfigurationPermissionsDisplayContext.getGroupId(), role, resource, portletConfigurationPermissionsDisplayContext.getActions(), currentIndividualActions, currentGroupActions, currentGroupTemplateActions, currentCompanyActions);

					for (String action : portletConfigurationPermissionsDisplayContext.getActions()) {
						if (action.equals(ActionKeys.ACCESS_IN_CONTROL_PANEL)) {
							continue;
						}

						boolean checked = false;

						if (currentIndividualActions.contains(action) || currentGroupActions.contains(action) || currentGroupTemplateActions.contains(action) || currentCompanyActions.contains(action)) {
							checked = true;
						}

						String preselectedMsg = StringPool.BLANK;

						if (currentGroupActions.contains(action) || currentGroupTemplateActions.contains(action)) {
							preselectedMsg = "x-is-allowed-to-do-action-x-in-all-items-of-type-x-in-x";
						}
						else if (currentCompanyActions.contains(action)) {
							preselectedMsg = "x-is-allowed-to-do-action-x-in-all-items-of-type-x-in-this-portal-instance";
						}

						String dataMessage = StringPool.BLANK;

						if (Validator.isNotNull(preselectedMsg)) {
							String type = portletConfigurationPermissionsDisplayContext.getSelResourceDescription();

							if (Validator.isNull(type)) {
								type = ResourceActionsUtil.getModelResource(locale, resource.getName());
							}

							dataMessage = HtmlUtil.escapeAttribute(LanguageUtil.format(request, preselectedMsg, new Object[] {role.getTitle(locale), ResourceActionsUtil.getAction(request, action), type, HtmlUtil.escape(portletConfigurationPermissionsDisplayContext.getGroupDescriptiveName())}, false));
						}

						String actionSeparator = Validator.isNotNull(preselectedMsg) ? ActionUtil.PRESELECTED : ActionUtil.ACTION;
					%>

						<liferay-ui:search-container-column-text
							cssClass="table-column-text-center"
							name="<%= ResourceActionsUtil.getAction(request, action) %>"
						>
							<c:if test="<%= checked %>">
								<input name="<%= renderResponse.getNamespace() + role.getRoleId() + actionSeparator + action %>" type="hidden" value="<%= true %>" />
							</c:if>

							<input <%= checked ? "checked" : StringPool.BLANK %> class="<%= Validator.isNotNull(preselectedMsg) ? "lfr-checkbox-preselected" : StringPool.BLANK %>" data-message="<%= dataMessage %>" id="<%= FriendlyURLNormalizerUtil.normalize(role.getName()) + actionSeparator + action %>" name="<%= renderResponse.getNamespace() + role.getRoleId() + actionSeparator + action %>" onclick="<%= Validator.isNotNull(preselectedMsg) ? "return false;" : StringPool.BLANK %>" type="checkbox" />
						</liferay-ui:search-container-column-text>

					<%
					}
					%>

				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator
					fixedHeader="<%= true %>"
					markupView="lexicon"
				/>
			</liferay-ui:search-container>
		</aui:form>
	</div>

	<aui:button-row>
		<aui:button name="saveButton" type="submit" />

		<aui:button type="cancel" />
	</aui:button-row>
</div>

<aui:script require="metal-dom/src/all/dom as dom">
	var form = document.getElementById('<portlet:namespace />fm');

	var preSelectedHandler = dom.delegate(
		form,
		'mouseover',
		'.lfr-checkbox-preselected',
		function(event) {
			var target = event.target;

			Liferay.Portal.ToolTip.show(target, target.getAttribute('data-message'));
		}
	);
</aui:script>

<aui:script>
	var <portlet:namespace />saveButton = document.getElementById('<portlet:namespace />saveButton');

	if (<portlet:namespace />saveButton) {
		<portlet:namespace />saveButton.addEventListener(
			'click',
			function(event) {
				event.preventDefault();

				if (<%= portletConfigurationPermissionsDisplayContext.getRoleSearchContainer().getTotal() != 0 %>) {
					var form = document.getElementById('<portlet:namespace />fm');

					if (form) {
						submitForm(form);
					}
				}
			}
		);
	}
</aui:script>