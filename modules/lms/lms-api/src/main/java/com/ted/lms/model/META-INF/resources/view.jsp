<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<%
String toolbarItem = ParamUtil.getString(request, "toolbarItem", "view-all-users");

String redirect = ParamUtil.getString(request, "redirect");
String viewUsersRedirect = ParamUtil.getString(request, "viewUsersRedirect");
String backURL = ParamUtil.getString(request, "backURL", redirect);

int status = ParamUtil.getInteger(request, "status", WorkflowConstants.STATUS_APPROVED);

String usersListView = ParamUtil.get(request, "usersListView", UserConstants.LIST_VIEW_FLAT_USERS);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("toolbarItem", toolbarItem);
portletURL.setParameter("usersListView", usersListView);

if (Validator.isNotNull(viewUsersRedirect)) {
	portletURL.setParameter("viewUsersRedirect", viewUsersRedirect);
}

String portletURLString = portletURL.toString();

request.setAttribute("view.jsp-portletURL", portletURL);

request.setAttribute("view.jsp-usersListView", usersListView);

long organizationId = ParamUtil.getLong(request, "organizationId", OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID);

Organization organization = null;

if (organizationId != 0) {
	organization = OrganizationServiceUtil.getOrganization(organizationId);
}

if (!usersListView.equals(UserConstants.LIST_VIEW_FLAT_USERS)) {
	portletDisplay.setShowExportImportIcon(true);
}
else {
	portletDisplay.setShowExportImportIcon(false);
}
%>

<liferay-ui:error exception="<%= CompanyMaxUsersException.class %>" message="unable-to-activate-user-because-that-would-exceed-the-maximum-number-of-users-allowed" />
<liferay-ui:error exception="<%= RequiredOrganizationException.class %>" message="you-cannot-delete-organizations-that-have-suborganizations-or-users" />
<liferay-ui:error exception="<%= RequiredUserException.class %>" message="you-cannot-delete-or-deactivate-yourself" />

<%@ include file="/toolbar.jspf" %>

<c:choose>
	<c:when test="<%= usersListView.equals(UserConstants.LIST_VIEW_TREE) %>">

		<%
		request.setAttribute("view.jsp-backURL", backURL);
		request.setAttribute("view.jsp-organization", organization);
		request.setAttribute("view.jsp-organizationId", organizationId);
		request.setAttribute("view.jsp-portletURL", portletURL);
		request.setAttribute("view.jsp-toolbarItem", toolbarItem);
		request.setAttribute("view.jsp-usersListView", usersListView);
		%>

		<liferay-util:include page="/view_tree.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:when test="<%= portletName.equals(UsersAdminPortletKeys.MY_ORGANIZATIONS) || usersListView.equals(UserConstants.LIST_VIEW_FLAT_ORGANIZATIONS) %>">
		<liferay-util:include page="/view_flat_organizations.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:when test="<%= usersListView.equals(UserConstants.LIST_VIEW_FLAT_USERS) %>">

		<%
		request.setAttribute("view.jsp-backURL", backURL);
		request.setAttribute("view.jsp-status", status);
		request.setAttribute("view.jsp-usersListView", usersListView);
		request.setAttribute("view.jsp-viewUsersRedirect", viewUsersRedirect);
		%>

		<liferay-util:include page="/view_flat_users.jsp" servletContext="<%= application %>" />
	</c:when>
</c:choose>

<aui:script>
	function <portlet:namespace />deleteOrganization(organizationId) {
		<portlet:namespace />doDeleteOrganization('<%= Organization.class.getName() %>', organizationId);
	}

	function <portlet:namespace />deleteOrganizations() {
		<portlet:namespace />doDeleteOrganization(
			'<%= Organization.class.getName() %>',
			Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, '<portlet:namespace />allRowIds', '<portlet:namespace />rowIdsOrganization')
		);
	}

	function <portlet:namespace />deleteUsers(cmd) {
		if (((cmd == '<%= Constants.DEACTIVATE %>') && confirm('<%= UnicodeLanguageUtil.get(request, "are-you-sure-you-want-to-deactivate-the-selected-users") %>')) || ((cmd == '<%= Constants.DELETE %>') && confirm('<%= UnicodeLanguageUtil.get(request, "are-you-sure-you-want-to-permanently-delete-the-selected-users") %>')) || (cmd == '<%= Constants.RESTORE %>')) {
			var form = AUI.$(document.<portlet:namespace />fm);

			form.attr('method', 'post');
			form.fm('<%= Constants.CMD %>').val(cmd);
			form.fm('redirect').val(form.fm('usersRedirect').val());
			form.fm('deleteUserIds').val(Liferay.Util.listCheckedExcept(form, '<portlet:namespace />allRowIds', '<portlet:namespace />rowIdsUser'));

			submitForm(form, '<portlet:actionURL name="/users_admin/edit_user" />');
		}
	}

	function <portlet:namespace />doDeleteOrganization(className, ids) {
		var status = <%= WorkflowConstants.STATUS_INACTIVE %>;

		<portlet:namespace />getUsersCount(
			className,
			ids,
			status,
			function(responseData) {
				var count = parseInt(responseData, 10);

				if (count > 0) {
					status = <%= WorkflowConstants.STATUS_APPROVED %>;

					<portlet:namespace />getUsersCount(
						className,
						ids,
						status,
						function(responseData) {
							count = parseInt(responseData, 10);

							if (count > 0) {
								if (confirm('<%= UnicodeLanguageUtil.get(request, "are-you-sure-you-want-to-delete-this") %>')) {
									<portlet:namespace />doDeleteOrganizations(ids);
								}
							}
							else {
								var message;

								if (ids && (ids.toString().split(',').length > 1)) {
									message = '<%= UnicodeLanguageUtil.get(request, "one-or-more-organizations-are-associated-with-deactivated-users.-do-you-want-to-proceed-with-deleting-the-selected-organizations-by-automatically-unassociating-the-deactivated-users") %>';
								}
								else {
									message = '<%= UnicodeLanguageUtil.get(request, "the-selected-organization-is-associated-with-deactivated-users.-do-you-want-to-proceed-with-deleting-the-selected-organization-by-automatically-unassociating-the-deactivated-users") %>';
								}

								if (confirm(message)) {
									<portlet:namespace />doDeleteOrganizations(ids);
								}
							}
						}
					);
				}
				else if (confirm('<%= UnicodeLanguageUtil.get(request, "are-you-sure-you-want-to-delete-this") %>')) {
					<portlet:namespace />doDeleteOrganizations(ids);
				}
			}
		);
	}

	function <portlet:namespace />doDeleteOrganizations(organizationIds) {
		var form = AUI.$(document.<portlet:namespace />fm);

		form.attr('method', 'post');
		form.fm('<%= Constants.CMD %>').val('<%= Constants.DELETE %>');
		form.fm('redirect').val(form.fm('organizationsRedirect').val());
		form.fm('deleteOrganizationIds').val(organizationIds);

		submitForm(form, '<portlet:actionURL name="/users_admin/edit_organization" />');
	}

	function <portlet:namespace />getUsersCount(className, ids, status, callback) {
		AUI.$.ajax(
			'<liferay-portlet:resourceURL id="/users_admin/get_users_count" />',
			{
				data: {
					className: className,
					ids: ids,
					status: status
				},
				success: callback
			}
		);
	}

	function <portlet:namespace />search() {
		var form = AUI.$(document.<portlet:namespace />fm);

		form.attr('method', 'post');
		form.fm('<%= Constants.CMD %>').val('');

		submitForm(form, '<%= portletURLString %>');
	}

	function <portlet:namespace />showUsers(status) {

		<%
		PortletURL showUsersURL = renderResponse.createRenderURL();

		showUsersURL.setParameter("mvcRenderCommandName", "/users_admin/view");
		showUsersURL.setParameter("toolbarItem", toolbarItem);
		showUsersURL.setParameter("usersListView", usersListView);

		organizationId = ParamUtil.getLong(request, "organizationId", OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID);

		if (organizationId != OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID) {
			showUsersURL.setParameter("organizationId", String.valueOf(organizationId));
		}

		if (Validator.isNotNull(viewUsersRedirect)) {
			showUsersURL.setParameter("viewUsersRedirect", viewUsersRedirect);
		}
		%>

		location.href = Liferay.Util.addParams('<portlet:namespace />status=' + status.value, '<%= HtmlUtil.escapeJS(showUsersURL.toString()) %>');
	}

	Liferay.provide(
		window,
		'<portlet:namespace />openSelectUsersDialog',
		function(organizationId) {
			var A = AUI();

			var form = AUI.$(document.<portlet:namespace />fm);

			<portlet:renderURL var="selectUsersURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="mvcPath" value="/select_organization_users.jsp" />
			</portlet:renderURL>

			var selectUsersURL = Liferay.PortletURL.createURL('<%= selectUsersURL.toString() %>');

			selectUsersURL.setParameter('organizationId', organizationId);

			var itemSelectorDialog = new A.LiferayItemSelectorDialog(
				{
					eventName: '<portlet:namespace />selectUsers',
					on: {
						selectedItemChange: function(event) {
							var data = event.newVal;

							if (data) {
								var editAssignmentURL = Liferay.PortletURL.createURL('<portlet:actionURL name="/users_admin/edit_organization_assignments" />');

								editAssignmentURL.setParameter('addUserIds', data.value);
								editAssignmentURL.setParameter('organizationId', organizationId);

								var assignmentsRedirectURL = Liferay.PortletURL.createURL('<portlet:renderURL><portlet:param name="mvcRenderCommandName" value="/users_admin/view" /><portlet:param name="toolbarItem" value="view-all-organizations" /><portlet:param name="usersListView" value="<%= UserConstants.LIST_VIEW_TREE %>" /></portlet:renderURL>');

								assignmentsRedirectURL.setParameter('organizationId', organizationId);

								editAssignmentURL.setParameter('assignmentsRedirect', assignmentsRedirectURL.toString());

								submitForm(form, editAssignmentURL.toString());
							}
						}
					},
					strings: {
						add: '<liferay-ui:message key="done" />',
						cancel: '<liferay-ui:message key="cancel" />'
					},
					title: '<liferay-ui:message key="assign-users" />',
					url: selectUsersURL.toString()
				}
			);

			itemSelectorDialog.open();
		},
		['liferay-item-selector-dialog', 'liferay-portlet-url']
	);
</aui:script>