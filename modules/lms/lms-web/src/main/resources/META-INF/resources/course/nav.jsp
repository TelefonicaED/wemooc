<%@ include file="../init.jsp" %>

<aui:input id="${renderResponse.getNamespace()}inputFiltersShowOptions" name="inputFiltersShowOptions" type="hidden" value="${inputFiltersShowOptions}" />

<clay:management-toolbar
	clearResultsURL="${coursesManagementToolbarDisplayContext.searchActionURL}"
	componentId="coursesManagementToolbar"
	creationMenu="${coursesManagementToolbarDisplayContext.getCreationMenu()}"
	disabled="${searchContainer.total <= 0 }"
	itemsTotal="${searchContainer.total}"
	searchActionURL="${coursesManagementToolbarDisplayContext.searchActionURL}"
	searchContainerId="courses"
	searchFormName="searchFm"
	showInfoButton="true"
	infoPanelId=""
	viewTypeItems="${coursesManagementToolbarDisplayContext.viewTypes}"
/>

<span class="${inputFiltersShowOptions ? 'hide' : ''}" id="${renderResponse.getNamespace()}inputFiltersShowOptionsLink">
	<a href="javascript:${renderResponse.getNamespace()}inputFiltersShowOptions();">
		<liferay-ui:message key="more-options" />
	</a>
	<liferay-ui:icon-help message="input-filters-more-options-help" />
</span>

<a ${inputFiltersShowOptions ? '' : 'hide'} href="javascript:${renderResponse.getNamespace()}inputFiltersHideOptions();" 
		id="${renderResponse.getNamespace()}inputFiltersHideOptionsLink">
	<liferay-ui:message key="hide-options" />
</a>

<div class="course-advanced-options ${inputFiltersShowOptions ? '' :'hide'}" id="${renderResponse.getNamespace()}inputFiltersOptions">
	
</div>

<aui:script>
	function <portlet:namespace />inputFiltersHideOptions() {
		<portlet:namespace />toggleFiltersOptions(false);
	}
	
	function <portlet:namespace />inputFiltersShowOptions() {
		<portlet:namespace />toggleFiltersOptions(true);
	}
	
	function <portlet:namespace />toggleFiltersOptions(force) {

		$('#<portlet:namespace />inputFiltersHideOptionsLink').toggleClass('hide', !force);
		$('#<portlet:namespace />inputFiltersOptions').toggleClass('hide', !force);

		$('#<portlet:namespace />inputFiltersShowOptionsLink').toggleClass('hide', force);
		$('#<portlet:namespace />inputFiltersShowOptions').val(force);
	}
			
</aui:script>