<%@ include file="../init.jsp" %>

<liferay-ui:icon-menu
	direction="left-side"
	icon=""
	markupView="lexicon"
	message=""
	showWhenSingleIcon="true"
>

	<portlet:renderURL var="editCourseURL">
		<portlet:param name="mvcRenderCommandName" value="/lms/edit_course" />
		<portlet:param name="courseId" value="${course.courseId }" />
	</portlet:renderURL>

	<liferay-ui:icon
		message="edit"
		url="${editCourseURL}"
	/>
</liferay-ui:icon-menu>