<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@ include file="../init.jsp" %>

<aui:field-wrapper cssClass="course-types" helpMessage='course.add-course.course-type.help-message' label="course.add-course.course-type" name="courseTypes">
	<div class="row">
		<div class="col-md-4 course-type">
			<liferay-ui:icon-help message="course-type.empty-course.help-message" />
			<portlet:renderURL  var="addCourseURL">
				<portlet:param name="mvcRenderCommandName" value="/courses/edit_course" />
				<portlet:param name="<%= Constants.CMD %>" value="<%=Constants.ADD %>" />
				<portlet:param name="courseTypeId" value="0" />
				<portlet:param name="redirect" value="${redirect }" />
			</portlet:renderURL>
			<a href="${addCourseURL}">
				<div class="aspect-ratio aspect-ratio-bg-cover course-type-icon course-type-basic" ></div>
				<span><liferay-ui:message key="course-type.empty-course"/></span>
			</a>
		</div>
		<c:forEach items="${courseTypes }" var="courseType">
			<div class="col-md-4 course-type">
				<liferay-ui:icon-help message="${courseType.getDescription(themeDisplay.locale) }" />
				<portlet:renderURL  var="addCourseURL">
					<portlet:param name="mvcRenderCommandName" value="/courses/edit_course" />
					<portlet:param name="<%= Constants.CMD %>" value="<%=Constants.ADD %>" />
					<portlet:param name="courseTypeId" value="${courseType.courseTypeId }" />
					<portlet:param name="redirect" value="${redirect }" />
				</portlet:renderURL>
				<a href="${addCourseURL}">
					<div class="aspect-ratio aspect-ratio-bg-cover course-type-icon" style="background-image: url(${courseType.getIconURL(themeDisplay)})"></div>
					<span>${courseType.getName(themeDisplay.locale) }</span>
				</a>
			</div>
		</c:forEach>
	</div>
</aui:field-wrapper>