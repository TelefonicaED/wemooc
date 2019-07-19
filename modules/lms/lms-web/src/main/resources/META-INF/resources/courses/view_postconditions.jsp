<clay:management-toolbar
	actionDropdownItems="${postconditionDisplayContext.actionDropdownItems }"
	componentId="postconditionsManagementToolbar"
	disabled="${postconditionDisplayContext.isDisabledManagementBar() }"
	itemsTotal="${postconditionDisplayContext.totalItems }"
	searchContainerId="postconditions"
	showCreationMenu="true"
	showSearch="false"
	creationMenu="${postconditionDisplayContext.getCreationMenu()}"
/>

<div class="sidenav-content">
	<portlet:actionURL name="/courses/update_postcondition" var="deletePostconditionsURL">
		<portlet:param name="<%=Constants.CMD %>" value="<%=Constants.DELETE %>"/>
		<portlet:param name="redirect" value="${postconditionDisplayContext.portletURL}" />
	</portlet:actionURL>
	
	<aui:form action="${deletePostconditionsURL}" method="post" name="fm">
		<aui:input name="addPostconditionRelationIds" type="hidden" />

		<liferay-ui:search-container
			id="postconditions"
			searchContainer="${postconditionDisplayContext.searchContainer}"
		>
			<liferay-ui:search-container-row
				className="com.ted.postcondition.model.PostconditionRelation"
				escapedModel="<%= true %>"
				keyProperty="postconditionRelationId"
				modelVar="postconditionRelation"
				rowIdProperty="postconditionRelationId"
			>
		
				<liferay-ui:search-container-column-text
					cssClass="important table-cell-content"
					name="name"
					value="${postconditionRelation.postcondition.getTitle(themeDisplay.locale) }"
				/>
				
				<liferay-ui:search-container-column-text
					cssClass="important table-cell-content"
					name="type"
					value="${postconditionRelation.postcondition.getPostconditionFactory().getTitle(themeDisplay.locale) }"
				/>
				
			</liferay-ui:search-container-row>
			
			<liferay-ui:search-iterator
				markupView="lexicon"
			/>
		
		</liferay-ui:search-container>
	</aui:form>
</div>

<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>" var="assignPostconditionsURL">
	<portlet:param name="courseId" value="${courseId}" />
	<portlet:param name="mvcRenderCommandName" value="/courses/assign_postcondition" />
</portlet:renderURL>

<aui:script use="liferay-item-selector-dialog">
	var form = $(document.<portlet:namespace />fm);
	
	var deleteSelectedPostconditions = function() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-this" />')) {
			submitForm(form);
		}
	};
	
	function <portlet:namespace />handleAddClick(event) {
		console.log("handleAddClick");
		event.preventDefault();
		
		var className = event.data.item.data.className;
		
		var uri = '${assignPostconditionsURL}';

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
						title: '<liferay-ui:message key="assign-postconditions-to-this-site" />',		
						uri: uri
					}
					).render();
			window.<portlet:namespace />popupImport.show();
		});
	};
	
	var ACTIONS = {
			'deleteSelectedPostconditions': deleteSelectedPostconditions
	};
	
	Liferay.componentReady('postconditionsManagementToolbar').then(
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
