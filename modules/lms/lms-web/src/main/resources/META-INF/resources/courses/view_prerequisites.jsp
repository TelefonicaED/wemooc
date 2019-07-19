<clay:management-toolbar
	actionDropdownItems="${prerequisteDisplayContext.actionDropdownItems }"
	componentId="prerequisitesManagementToolbar"
	disabled="${prerequisteDisplayContext.isDisabledManagementBar() }"
	itemsTotal="${prerequisteDisplayContext.totalItems }"
	searchContainerId="prerequisites"
	showCreationMenu="true"
	showSearch="false"
	creationMenu="${prerequisteDisplayContext.getCreationMenu()}"
/>

<div class="sidenav-content">
	<portlet:actionURL name="/courses/update_prerequisite" var="deletePrerequisitesURL">
		<portlet:param name="<%=Constants.CMD %>" value="<%=Constants.DELETE %>"/>
		<portlet:param name="redirect" value="${prerequisteDisplayContext.portletURL}" />
	</portlet:actionURL>
	
	<aui:form action="${deletePrerequisitesURL}" method="post" name="fm">
		<aui:input name="addPrerequisiteRelationIds" type="hidden" />

		<liferay-ui:search-container
			id="prerequisites"
			searchContainer="${prerequisteDisplayContext.searchContainer}"
		>
			<liferay-ui:search-container-row
				className="com.ted.prerequisite.model.PrerequisiteRelation"
				escapedModel="<%= true %>"
				keyProperty="prerequisiteRelationId"
				modelVar="prerequisiteRelation"
				rowIdProperty="prerequisiteRelationId"
			>
		
				<liferay-ui:search-container-column-text
					cssClass="important table-cell-content"
					name="name"
					value="${prerequisiteRelation.prerequisite.getTitle(themeDisplay.locale) }"
				/>
				
				<liferay-ui:search-container-column-text
					cssClass="important table-cell-content"
					name="type"
					value="${prerequisiteRelation.prerequisite.getPrerequisiteFactory().getTitle(themeDisplay.locale) }"
				/>
				
			</liferay-ui:search-container-row>
			
			<liferay-ui:search-iterator
				markupView="lexicon"
			/>
		
		</liferay-ui:search-container>
	</aui:form>
</div>

<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>" var="assignPrerequisitesURL">
	<portlet:param name="courseId" value="${courseId}" />
	<portlet:param name="mvcRenderCommandName" value="/courses/assign_prerequisite" />
</portlet:renderURL>

<aui:script use="liferay-item-selector-dialog">
	var form = $(document.<portlet:namespace />fm);
	
	var deleteSelectedPrerequisites = function() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-this" />')) {
			submitForm(form);
		}
	};
	
	function <portlet:namespace />handleAddClick(event) {
		console.log("handleAddClick");
		event.preventDefault();
		
		var className = event.data.item.data.className;
		
		var uri = '${assignPrerequisitesURL}';

		uri = Liferay.Util.addParams('<portlet:namespace />className=' + className, uri);
			
		AUI().use('liferay-portlet-url,liferay-util-window', function(A){
			
			window.<portlet:namespace />popupImport = Liferay.Util.Window.getWindow(
					{
						dialog: {
							modal: true,
							resizable: false,
							width: "auto",
							heigth: "auto",
							centered: true,
							destroyOnHide: true
						},
						title: '<liferay-ui:message key="assign-prerequisites-to-this-site" />',		
						uri: uri
					}
					).render();
			window.<portlet:namespace />popupImport.show();
		});
	};
	
	var ACTIONS = {
			'deleteSelectedPrerequisites': deleteSelectedPrerequisites
	};
	
	Liferay.componentReady('prerequisitesManagementToolbar').then(
		function(managementToolbar) {
			managementToolbar.on('creationMenuItemClicked', <portlet:namespace />handleAddClick);
			
			managementToolbar.on(
				['actionItemClicked'],
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
