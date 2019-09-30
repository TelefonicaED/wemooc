<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ include file="../init.jsp" %>

<c:if test="${deleteTries }">
	<liferay-portlet:actionURL name="/activities/delete_tries" var="deleteAllTriesURL" windowState="<%=LiferayWindowState.POP_UP.toString() %>">
		<portlet:param name="actId" value="${actId }" />
	</liferay-portlet:actionURL>
	
	<a href="${deleteAllTriesURL }">
		<span class="glyphicon glyphicon-remove-circle"/>
		<liferay-ui:message key="results.delete-all-tries"/>
	</a>
	
	<liferay-portlet:actionURL name="/activities/delete_tries" var="deleteAllTriesFailedURL" windowState="<%=LiferayWindowState.POP_UP.toString() %>">
		<portlet:param name="actId" value="${actId }" />
		<portlet:param name="deleteOnlyFailed" value="true" />
	</liferay-portlet:actionURL>
	
	<a href="${deleteAllTriesFailedURL }">
		<span class="glyphicon glyphicon-remove-circle"/>
		<liferay-ui:message key="results.delete-only-failed"/>
	</a>
</c:if>

<clay:management-toolbar
	clearResultsURL="${activityResultsDisplayContext.clearResultsURL }"
	componentId="activiyResultsManagementToolbar"
	disabled="${activityResultsDisplayContext.isDisabledManagementBar() }"
	filterDropdownItems="${activityResultsDisplayContext.filterDropdownItems }"
	itemsTotal="${activityResultsDisplayContext.totalItems }"
	searchActionURL="${activityResultsDisplayContext.searchActionURL }"
	searchContainerId="activityResults"
	searchFormName="searchFm"
	showSearch="${activityResultsDisplayContext.isShowSearch() }"
	sortingOrder="${activityResultsDisplayContext.orderByType }"
	sortingURL="${activityResultsDisplayContext.sortingURL }"
	selectable="false"
/>

<aui:form cssClass="container-fluid-1280 portlet-site-memberships-select-users" name="fm">
	<liferay-ui:search-container
		id="studentResults"
		searchContainer="${activityResultsDisplayContext.userSearchContainer }"
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
			
			<c:set var="activityResult" value="${learningActivityResultLocalService.getLearningActivityResult(actId, user2.userId) }" />
			<liferay-ui:search-container-column-text name="result"
				cssClass="table-cell-expand table-cell-minw-100 table-title">
				<c:choose>
					<c:when test="${not empty activityResult }">
						${activityResult.getActivityStatus(themeDisplay.locale) }
					</c:when>
					<c:otherwise>
						<liferay-ui:message key="results.status.not-started" />
					</c:otherwise>
				</c:choose>
			</liferay-ui:search-container-column-text>
			<liferay-ui:search-container-column-text name="status"
				cssClass="table-cell-expand table-cell-minw-100 table-title">
				<c:choose>
					<c:when test="${not empty activityResult && activityResult.finished }">
						${calificationType.translate(themeDisplay.locale, activityResult.result).concat(calificationType.suffix) }
					</c:when>
					<c:otherwise>
						<liferay-ui:message key="results.result-course.pending" />
					</c:otherwise>
				</c:choose>
			</liferay-ui:search-container-column-text>
			<liferay-ui:search-container-column-text name="end-date"
				cssClass="table-cell-expand table-cell-minw-100 table-title">
				<c:choose>
					<c:when test="${not empty activityResult && not empty activityResult.endDate}">
						${activityResult.endDate }
					</c:when>
					<c:otherwise>
						<liferay-ui:message key="results.result-course.pending" />
					</c:otherwise>
				</c:choose>
			</liferay-ui:search-container-column-text>
			<liferay-ui:search-container-column-text name="actions"
				cssClass="table-cell-expand table-cell-minw-100 table-title">
				<c:if test="${not empty activityResult && deleteTries }">
					<liferay-portlet:actionURL name="/activities/delete_tries" var="viewResultsURL" windowState="<%=LiferayWindowState.POP_UP.toString() %>">
						<portlet:param name="actId" value="${actId }" />
						<portlet:param name="studentId" value="${user2.userId }" />
					</liferay-portlet:actionURL>
				
					<a href="${viewResultsURL }">
						<span class="glyphicon glyphicon-remove-circle"/>
						<liferay-ui:message key="results.delete-all-tries"/>
					</a>
				</c:if>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>
		

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>

</aui:form>