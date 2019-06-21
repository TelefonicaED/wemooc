<%@page import="com.ted.lms.web.internal.display.context.CourseMembersDisplayContext"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.ted.lms.model.Course"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ include file="init.jsp" %>

<liferay-ui:header title="${course.getTitle(themeDisplay.locale) }" backLabel="back" backURL="${redirect }" />

<aui:nav-bar cssClass="navbar-collapse-absolute" markupView="lexicon">

	<h2><liferay-ui:message key="course-admin.assing-users"/></h2>
	<liferay-ui:icon-menu
		direction="left-side"
		icon="<%= StringPool.BLANK %>"
		markupView="lexicon"
		message="<%= StringPool.BLANK %>"
		showWhenSingleIcon="true"
	>
		<liferay-ui:icon
			message="export"
			url="${exportURL }"
		/>
		<liferay-ui:icon
			message="import"
			url="${importURL }"
		/>
	</liferay-ui:icon-menu>
</aui:nav-bar>

<clay:navigation-bar 
	navigationItems="${navigationItem }"
/>

<clay:management-toolbar
	actionDropdownItems="${courseMembersDisplayContext.actionDropdownItems }"
	clearResultsURL="${courseMembersDisplayContext.clearResultsURL }"
	componentId="usersManagementToolbar"
	disabled="${courseMembersDisplayContext.isDisabledManagementBar() }"
	filterDropdownItems="${courseMembersDisplayContext.filterDropdownItems }"
	itemsTotal="${courseMembersDisplayContext.totalItems }"
	searchActionURL="${courseMembersDisplayContext.searchActionURL }"
	searchContainerId="users"
	searchFormName="searchFm"
	showCreationMenu="true"
	showSearch="${courseMembersDisplayContext.isShowSearch() }"
	sortingOrder="${courseMembersDisplayContext.orderByType }"
	sortingURL="${courseMembersDisplayContext.sortingURL }"
	viewTypeItems="${courseMembersDisplayContext.viewTypeItems }"
/>

<div class="sidenav-content">
	<portlet:actionURL name="deleteCourseUsers" var="deleteCourseUsersURL">
		<portlet:param name="redirect" value="${courseMembersDisplayContext.portletURL}" />
	</portlet:actionURL>
	
	<aui:form action="${deleteCourseUsersURL}" method="post" name="fm">
		<aui:input name="tabs" type="hidden" value="${courseMembersDisplayContext.tab }" />
		<aui:input name="addUserIds" type="hidden" />

		<liferay-ui:search-container
			id="users"
			searchContainer="${courseMembersDisplayContext.userSearchContainer }"
		>
			<liferay-ui:search-container-row
				className="com.liferay.portal.kernel.model.User"
				escapedModel="<%= true %>"
				keyProperty="userId"
				modelVar="user2"
				rowIdProperty="screenName"
			>

				<liferay-ui:search-container-column-text
					cssClass="table-cell-expand table-cell-minw-200 table-title"
					name="name"
					value="<%= user2.getFullName() %>"
				/>
		
				<liferay-ui:search-container-column-text
					cssClass="table-cell-expand table-cell-minw-200"
					name="screen-name"
					orderable="<%= true %>"
					property="screenName"
				/>
				
				<liferay-ui:search-container-column-text
					cssClass="table-cell-expand table-cell-minw-200"
					name="email-address"
					orderable="<%= true %>"
					property="emailAddress"
				/>
				<liferay-ui:search-container-column-text>
					
				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				markupView="lexicon"
			/>
		</liferay-ui:search-container>
	</aui:form>
</div>

<portlet:actionURL name="addGroupUsers" var="addGroupUsersURL" >
	<portlet:param name="courseId" value="${courseId }"/>
	<portlet:param name="roleId" value="${courseMembersDisplayContext.roleId }" />
</portlet:actionURL>

<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>" var="assignMembersURL">
	<portlet:param name="courseId" value="${courseId}" />
	<portlet:param name="mvcRenderCommandName" value="/courses/assign_members" />
	<portlet:param name="roleId" value="${courseMembersDisplayContext.roleId }" />
</portlet:renderURL>


<aui:form action="${addGroupUsersURL }" cssClass="hide" method="post" name="addGroupUsersFm">
	<aui:input name="tabs" type="hidden" value="${courseMembersDisplayContext.tab }" />
</aui:form>

<aui:script use="liferay-item-selector-dialog">
	var form = $(document.<portlet:namespace />fm);
	
	var deleteSelectedUsers = function() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-this" />')) {
			submitForm(form);
		}
	};

	function <portlet:namespace />handleAddClick(event) {
		event.preventDefault();
	
		var itemSelectorDialog = new A.LiferayItemSelectorDialog(
			{
				eventName: '<portlet:namespace />selectUsers',
				on: {
					selectedItemChange: function(event) {
						var selectedItem = event.newVal;
						if (selectedItem) {
							var addGroupUsersFm = $(document.<portlet:namespace />addGroupUsersFm);
							
							addGroupUsersFm.append(selectedItem);
	
							submitForm(addGroupUsersFm);
						}
					}
				},
				'strings.add': '<liferay-ui:message key="done" />',
				title: '<liferay-ui:message key="assign-users-to-this-site" />',
				url: '${assignMembersURL}'
			}
		);
	
		itemSelectorDialog.open();
	};
	
	var ACTIONS = {
			'deleteSelectedUsers': deleteSelectedUsers
	};
	
	Liferay.componentReady('usersManagementToolbar').then(
		function(managementToolbar) {
			console.log("cargado");
			managementToolbar.on('creationButtonClicked', <portlet:namespace />handleAddClick);
			
			managementToolbar.on(
				['actionItemClicked', 'filterItemClicked'],
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
