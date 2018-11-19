<%@page import="com.liferay.portal.kernel.workflow.WorkflowConstants"%>
<%@page import="com.ted.lms.model.Course"%>
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
	selectable="false"
/>

<span class="${inputFiltersShowOptions ? 'hide' : ''}" id="${renderResponse.getNamespace()}inputFiltersShowOptionsLink">
	<a href="javascript:${renderResponse.getNamespace()}inputFiltersShowOptions();">
		<liferay-ui:message key="more-options" />
	</a>
	<liferay-ui:icon-help message="input-filters-more-options-help" />
</span>

<a class="${inputFiltersShowOptions ? '' : 'hide'}" href="javascript:${renderResponse.getNamespace()}inputFiltersHideOptions();" 
		id="${renderResponse.getNamespace()}inputFiltersHideOptionsLink">
	<liferay-ui:message key="hide-options" />
</a>

<div class="course-advanced-options ${inputFiltersShowOptions ? '' :'hide'}" id="${renderResponse.getNamespace()}inputFiltersOptions">
	<aui:form action="${coursesManagementToolbarDisplayContext.searchActionURL}" method="post" name="searchAdvancedFm">
		<div class="row">
			<c:if test="${courseDisplayContext.courseAdminConfiguration.searchGroups() && (empty courseDisplayContext.parentCourseId || courseDisplayContext.parentCourseId == 0) && not empty courseDisplayContext.searchListGroups}">
				<div class="col-md-3">
					<aui:select name="groupId" label="site" >
						<aui:option label="" value="0"/>
						<c:forEach items="${courseDisplayContext.searchListGroups}" var="courseGroup">
							<aui:option label="${courseGroup.getDescriptiveName(themeDisplay.locale) }" value="${courseGroup.groupId }" selected="${courseGroup.groupId == courseDisplayContext.groupId}"/>
						</c:forEach>
					</aui:select>
				</div>
			</c:if>
			<c:if test="${courseDisplayContext.courseAdminConfiguration.searchTags() && not empty courseDisplayContext.searchListAssetTags}">
				<div class="col-md-3">
					<aui:select name="${listAssetTags }" label="tags">
						<aui:option label="" value="0"/>
						<c:forEach items="${courseDisplayContext.searchListAssetTags }" var="assetTag">
							<aui:option label="${assetTag.name }" value="${assetTag.tagId }" selected="${courseDisplayContext.containsTagId(assetTag.tagId) }"/>
						</c:forEach>
					</aui:select> 
				</div>
			</c:if>
			<c:if test="${courseDisplayContext.courseAdminConfiguration.searchCategories() }">
				<c:forEach items="${courseDisplayContext.searchListAssetVocabularies }" var="vocabulary">
					<c:if test="${not empty vocabulary.categories }">
						<div class="col-md-3">
							<aui:select name="vocabulary_${vocabulary.vocabularyId }" label="${vocabulary.titleCurrentValue }">
								<aui:option label="" value="0"/>
								<c:forEach items="${vocabulary.categories }" var="category">
									<aui:option label="${category.titleCurrentValue }" value="${category.categoryId }" selected="${courseDisplayContext.containsCategoryId(category.categoryId) }"/>
								</c:forEach>
							</aui:select>
						</div>
					</c:if>
				</c:forEach>
			</c:if>
			<div class="col-md-3">
				<aui:select name="status" label="status">
					<aui:option label="active" value="<%=WorkflowConstants.STATUS_APPROVED %>" selected="${courseDisplayContext.statusApproved }"/>
					<aui:option label="inactive" value="<%=WorkflowConstants.STATUS_INACTIVE %>" selected="${courseDisplayContext.statusInactive }"/>
					<aui:option label="any-status" value="<%=WorkflowConstants.STATUS_ANY %>" selected="${courseDisplayContext.statusAny }"/>
				</aui:select>
			</div>
			<div class="col-md-3">
				<liferay-ui:input-date name="executionStartDate" dayParam="executionStartDateDay" monthParam="executionStartDateMonth" yearParam="executionStartDateYear" nullable="true"
					dayValue="${courseDisplayContext.executionStartDateDay }" monthValue="${courseDisplayContext.executionStartDateMonth }" yearValue="${courseDisplayContext.executionStartDateYear }"/>
			</div>
			<div class="col-md-3">
				<liferay-ui:input-date name="executionEndDate" dayParam="executionEndDateDay" monthParam="executionEndDateMonth" yearParam="executionEndDateYear" nullable="true"
					dayValue="${courseDisplayContext.executionEndDateDay }" monthValue="${courseDisplayContext.executionEndDateMonth }" yearValue="${courseDisplayContext.executionEndDateYear }" />
			</div>
		</div>
		<div class="row">
			<aui:button type="submit" value="filter"/>
		</div>
	</aui:form>
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