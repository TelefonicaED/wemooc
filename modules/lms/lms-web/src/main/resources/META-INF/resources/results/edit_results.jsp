<%@page import="com.ted.lms.constants.LMSPortletKeys"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@ include file="../init.jsp" %>

<clay:management-toolbar
	clearResultsURL="${editResultsDisplayContext.clearResultsURL }"
	componentId="editResultsManagementToolbar"
	disabled="${editResultsDisplayContext.isDisabledManagementBar() }"
	filterDropdownItems="${editResultsDisplayContext.filterDropdownItems }"
	itemsTotal="${editResultsDisplayContext.totalItems }"
	searchActionURL="${editResultsDisplayContext.searchActionURL }"
	searchContainerId="editResults"
	searchFormName="searchFm"
	showSearch="${editResultsDisplayContext.isShowSearch() }"
	sortingOrder="${editResultsDisplayContext.orderByType }"
	sortingURL="${editResultsDisplayContext.sortingURL }"
	selectable="false"
/>

<liferay-ui:panel-container id="modules">
	<c:set var="count" value="0"/>
	<c:forEach items="${modules }" var="module">
		<liferay-ui:panel title="${module.getTitle(themeDisplay.locale) }" id="panel_${module.moduleId }">
			<c:set var="activities" value="${learningActivityService.getActivities(module.moduleId) }" />
			<liferay-ui:search-container
				id="editResults_${module.moduleId }"
				searchContainer="${editResultsDisplayContext.getUserSearchContainer(count) }"
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
					
					<c:forEach items="${activities }" var="activity">
						<liferay-ui:search-container-column-text cssClass="table-cell-expand table-cell-minw-200 table-title"
							name="${activity.getTitle(themeDisplay.locale) }">
							<c:set var="activityResult" value="${learningActivityResultLocalService.getLearningActivityResult(activity.actId, user2.userId) }" />
							<c:choose>
								<c:when test="${not empty activityResult && activityResult.finished }">
									<c:if test="${activityResult.failed }">
										<span class="failed">
									</c:if>
									${calificationType.translate(themeDisplay.locale, activityResult.result).concat(calificationType.suffix) }
									<c:if test="${activityResult.failed }">
										</span>
									</c:if>
								</c:when>
								<c:otherwise>
									<liferay-ui:message key="results.result-course.pending" />
								</c:otherwise>
							</c:choose>
							<c:if test="${not empty activityResult.comments}">
								<span class="glyphicon glyphicon-comment" data-toggle="tooltip" data-html="true" title="${activityResult.comments }">
								</span>
							</c:if>
							<c:if test="${not empty activityResult && viewResults }">
								<liferay-ui:icon-menu
									direction="left-side"
									icon="<%= StringPool.BLANK %>"
									markupView="lexicon"
									message="<%= StringPool.BLANK %>"
									showWhenSingleIcon="true"
								>
									<c:if test="${activityResult.finished }">
										<liferay-portlet:renderURL var="editResultURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>"  portletName="<%=LMSPortletKeys.CALIFICATION %>">
											<portlet:param name="mvcPath" value="/calification/edit_result_form.jsp" />
											<portlet:param name="actId" value="${activity.actId }" />
											<portlet:param name="studentId" value="${user2.userId }" />
											<portlet:param name="courseId" value="${editResultsDisplayContext.courseId }" />
										</liferay-portlet:renderURL>
										
										<liferay-ui:icon
											message="calification.edit-result"
											method="get"
											url="${editResultURL }"
											useDialog="<%= true %>" 
										/>
									</c:if>
									<c:set var="learningActivityTypeFactory" value="${activity.learningActivityTypeFactory }" />
									<c:if test="${not empty learningActivityTypeFactory.specificResultsPage }">
										<liferay-portlet:renderURL var="viewActivityURL" portletName="${learningActivityTypeFactory.portletId }" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
											<liferay-portlet:param name="mvcRenderCommandName" value="${learningActivityTypeFactory.specificResultsPage }"/>
											<portlet:param name="actId" value="${activity.actId }" />
											<portlet:param name="studentId" value="${user2.userId }" />
											<portlet:param name="courseId" value="${editResultsDisplayContext.courseId }" />
										</liferay-portlet:renderURL>
										
										<liferay-ui:icon
											message="learning-activity.view-activity"
											method="get"
											url="${viewActivityURL }"
											useDialog="<%= true %>" 
										/>
									</c:if>
								</liferay-ui:icon-menu>
							</c:if>
						</liferay-ui:search-container-column-text>
					</c:forEach>
				</liferay-ui:search-container-row>
				<liferay-ui:search-iterator
					markupView="lexicon"
				/>
			</liferay-ui:search-container>
		</liferay-ui:panel>
		<c:set var="count" value="${count+1 }"/>
	</c:forEach>
</liferay-ui:panel-container>

<script>
	function refreshPortlet(){
		
		var portletNodeId = '#p_p_id_<%= portletDisplay.getId() %>_';
		var A = AUI();

		var portletNode = A.one(portletNodeId);

		Liferay.Portlet.refresh(
			portletNodeId,
			A.merge(
				Liferay.Util.ns(
					'<portlet:namespace />',
					{
					}
				),
				portletNode.refreshURLData || {}
			)
		);
	}
</script>