<%@page import="com.ted.lms.security.permission.resource.ModulePermission"%>
<%@page import="com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker"%>
<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@ include file="../init.jsp" %>

<clay:management-toolbar
	actionDropdownItems="${modulesManagementToolbarDisplayContext.actionDropdownItems}"
	clearResultsURL="${modulesManagementToolbarDisplayContext.searchActionURL}"
	componentId="modulesManagementToolbar"
	creationMenu="${modulesManagementToolbarDisplayContext.getCreationMenu()}"
	disabled="${searchContainer.total <= 0 }"
	filterDropdownItems="${modulesManagementToolbarDisplayContext.filterDropdownItems}"
	itemsTotal="${searchContainer.total}"
	searchActionURL="${modulesManagementToolbarDisplayContext.searchActionURL}"
	searchContainerId="modules"
	searchFormName="searchFm"
	showInfoButton="<%= false %>"
	sortingOrder="${modulesManagementToolbarDisplayContext.orderByType}"
	sortingURL="${modulesManagementToolbarDisplayContext.sortingURL}"
	viewTypeItems="${modulesManagementToolbarDisplayContext.viewTypes}"
/>

<portlet:actionURL name="/modules/edit_module" var="restoreTrashModulesURL">
	<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.RESTORE %>" />
</portlet:actionURL>

<liferay-trash:undo
	portletURL="${restoreTrashModulesURL }"
/>

<div class="container-fluid-1280 main-content-body">
	<aui:form action="${portletURL }" method="get" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" />
		<aui:input name="redirect" type="hidden" value="${portletURL }" />
		<aui:input name="deleteModuleIds" type="hidden" />

		<liferay-asset:categorization-filter
			assetType="modules"
			portletURL="${portletURL}"
		/>

		<liferay-ui:search-container
			id="modules"
			rowChecker="<%= new EmptyOnClickRowChecker(renderResponse) %>"
			searchContainer="${searchContainer }"
		>
			<liferay-ui:search-container-row
				className="com.ted.lms.model.Module"
				escapedModel="<%= true %>"
				keyProperty="moduleId"
				modelVar="module"
			>
				<liferay-portlet:renderURL varImpl="rowURL">
					<portlet:param name="mvcRenderCommandName" value="/modules/edit_entry" />
					<portlet:param name="redirect" value="${searchContainer.getIteratorURL()}" />
					<portlet:param name="moduleId" value="${moduleId }" />
				</liferay-portlet:renderURL>

				<liferay-ui:search-container-column-text
					cssClass="table-cell-content"
					name="title"
					orderable="<%= false %>"
					value="${module.getTitle(themeDisplay.locale) }"
				/>
				
				<liferay-ui:search-container-column-jsp
					path="/module_admin/module_action.jsp"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				displayStyle="${displayStyle}"
				markupView="lexicon"
			/>
		</liferay-ui:search-container>
	</aui:form>
</div>

<aui:script>
	var deleteModules = function() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-the-selected-modules" />')) {
			var form = document.getElementById('<portlet:namespace />fm');

			if (form) {
				form.setAttribute('method', 'post');

				var cmd = form.querySelector('#<portlet:namespace /><%= Constants.CMD %>');

				if (cmd) {
					cmd.setAttribute('value', '<%=Constants.DELETE %>');
				}

				var deleteEntryIds = form.querySelector('#<portlet:namespace />deleteModuleIds');

				if (deleteEntryIds) {
					deleteEntryIds.setAttribute('value', Liferay.Util.listCheckedExcept(form, '<portlet:namespace />allRowIds'));
				}

				submitForm(form, '<portlet:actionURL name="/modules/edit_module" />');
			}
		}
	};

	var ACTIONS = {
		'deleteModules': deleteModules
	};

	Liferay.componentReady('modulesManagementToolbar').then(
		function(managementToolbar) {
			managementToolbar.on(
				'actionItemClicked',
				function(event) {
					var itemData = event.data.item.data;

					if (itemData && itemData.action && ACTIONS[itemData.action]) {
						ACTIONS[itemData.action]();
					}
				}
			);
		}
	);
</aui:script>