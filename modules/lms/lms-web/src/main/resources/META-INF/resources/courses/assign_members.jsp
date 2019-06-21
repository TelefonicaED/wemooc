<%@page import="com.liferay.portal.kernel.util.HtmlUtil"%>
<%@ include file="/init.jsp" %>

<clay:management-toolbar
	clearResultsURL="${selectMembersDisplayContext.clearResultsURL }"
	componentId="usersManagementToolbar"
	disabled="${selectMembersDisplayContext.isDisabledManagementBar() }"
	filterDropdownItems="${selectMembersDisplayContext.filterDropdownItems }"
	itemsTotal="${selectMembersDisplayContext.totalItems }"
	searchActionURL="${selectMembersDisplayContext.searchActionURL }"
	searchContainerId="users"
	searchFormName="searchFm"
	showSearch="${selectMembersDisplayContext.isShowSearch() }"
	sortingOrder="${selectMembersDisplayContext.orderByType }"
	sortingURL="${selectMembersDisplayContext.sortingURL }"
	viewTypeItems="${selectMembersDisplayContext.viewTypeItems }"
/>

<aui:form cssClass="container-fluid-1280 portlet-site-memberships-select-users" name="fm">
	<liferay-ui:membership-policy-error />

	<liferay-ui:search-container
		id="users"
		searchContainer="${selectMembersDisplayContext.userSearchContainer }"
	>
		<liferay-ui:search-container-row
			className="com.liferay.portal.kernel.model.User"
			escapedModel="<%= true %>"
			keyProperty="userId"
			modelVar="user2"
			rowIdProperty="screenName"
		>

			<c:set var="selectUsers" value="true" />

			<%@ include file="/courses/user_columns.jspf" %>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			displayStyle="${selectMembersDisplayContext.displayStyle }"
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>

<aui:script use="liferay-search-container">
	var searchContainer = Liferay.SearchContainer.get('<portlet:namespace />users');

	searchContainer.on(
		'rowToggled',
		function(event) {
			var result = {};

			var data = event.elements.allSelectedElements.getDOMNodes();

			if (data.length) {
				result = {
					data: data
				};
			}

			Liferay.Util.getOpener().Liferay.fire(
				'<portlet:namespace />selectUsers',
				result);
		}
	);
</aui:script>