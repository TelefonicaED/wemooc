<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@ include file="../init.jsp" %>

<div class="container activitiy-types">
	<div class="row">
		<c:forEach items="${listLearningActivityTypeFactory }" var="learningActivityTypeFactory">
			<div class="col-md-4 activity-type">
				<liferay-ui:icon-help message="${learningActivityTypeFactory.getDescription(themeDisplay.locale) }" />
				<portlet:renderURL  var="addActivityURL">
					<portlet:param name="mvcRenderCommandName" value="/activities/edit_activity" />
					<portlet:param name="<%= Constants.CMD %>" value="<%=Constants.ADD %>" />
					<portlet:param name="moduleId" value="${moduleId }" />
					<portlet:param name="type" value="${learningActivityTypeFactory.type }" />
				</portlet:renderURL>
				<a href="${addActivityURL}">
					<div class="${learningActivityTypeFactory.getIconCssClass() }"></div>
					<span>${learningActivityTypeFactory.getName(themeDisplay.locale) }</span>
				</a>
			</div>
		</c:forEach>
	</div>
</div> 