<%@ include file="../init.jsp" %>

<clay:management-toolbar
	clearResultsURL="${studentResultsDisplayContext.clearResultsURL }"
	componentId="studentResultsManagementToolbar"
	disabled="${studentResultsDisplayContext.isDisabledManagementBar() }"
	filterDropdownItems="${studentResultsDisplayContext.filterDropdownItems }"
	itemsTotal="${studentResultsDisplayContext.totalItems }"
	searchActionURL="${studentResultsDisplayContext.searchActionURL }"
	searchContainerId="studentResults"
	searchFormName="searchFm"
	showSearch="${studentResultsDisplayContext.isShowSearch() }"
	sortingOrder="${studentResultsDisplayContext.orderByType }"
	sortingURL="${studentResultsDisplayContext.sortingURL }"
	selectable="false"
/>

<aui:form cssClass="container-fluid-1280 portlet-site-memberships-select-users" name="fm">

	<liferay-ui:search-container
		id="studentResults"
		searchContainer="${studentResultsDisplayContext.userSearchContainer }"
	>
		<liferay-ui:search-container-row
			className="com.liferay.portal.kernel.model.User"
			escapedModel="<%= true %>"
			keyProperty="userId"
			modelVar="user2"
		>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-expand table-cell-minw-200 table-title"
				name="name"
				value="${user2.getFullName()}"
			/>
			<c:set var="courseResult" value="${courseResultLocalService.fetchCourseResult(courseId, user2.userId) }" />
			<liferay-ui:search-container-column-text name="result">
				<c:choose>
					<c:when test="${courseResult.finished }">
						${calificationType.translate(themeDisplay.locale, courseResult.result).concat(calificationType.suffix) }
					</c:when>
					<c:otherwise>
						<liferay-ui:message key="results.result-course.pending"/>
					</c:otherwise>
				</c:choose>
			</liferay-ui:search-container-column-text>
			<liferay-ui:search-container-column-text name="status">
				<c:choose>
					<c:when test="${not empty courseResult }">
						${courseResult.getCourseStatus(themeDisplay.locale) }
					</c:when>
					<c:otherwise>
						<liferay-ui:message key="results.status.not-started"/>
					</c:otherwise>
				</c:choose>
			</liferay-ui:search-container-column-text>
			<liferay-ui:search-container-column-text name="">
				<portlet:renderURL var="viewResultsURL">
					<portlet:param name="mvcRenderCommandName" value="/results/view" />
					<portlet:param name="redirect" value="${portletURL }" />
					<portlet:param name="studentId" value="${user2.userId }" />
				</portlet:renderURL>
			
				<a href="${viewResultsURL }"><i class="material-icons">content_paste</i><liferay-ui:message key="results.view-results"/></a>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>
		

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>