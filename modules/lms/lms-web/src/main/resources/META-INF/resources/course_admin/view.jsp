<%@ include file="init.jsp" %>

<liferay-trash:undo
	portletURL="${restoreTrashEntriesURL}"
/>

<liferay-util:include page="/course/nav.jsp" servletContext="<%= application %>" />

<liferay-ui:search-container
	id="courses"
	searchContainer="${searchContainer}"
	var="courseSearchContainer"
>
	<liferay-ui:search-container-row
		className="com.ted.lms.model.Course"
		modelVar="course"
	>

		<liferay-ui:search-container-column-text
			cssClass="important table-cell-content"
			href="/web${course.friendlyURL }"
			name="name"
			value="${course.getTitle(themeDisplay.locale) }"
		/>

		<liferay-ui:search-container-column-text>
			<liferay-ui:icon-menu
				direction="left-side"
				icon=""
				markupView="lexicon"
				message=""
				showWhenSingleIcon="true"
			>
			
				<portlet:renderURL var="editCourseURL">
					<portlet:param name="mvcRenderCommandName" value="/courses/edit_course" />
					<portlet:param name="courseId" value="${course.courseId }" />
				</portlet:renderURL>
			
				<liferay-ui:icon
					message="edit"
					url="${editCourseURL}"
				/>
			</liferay-ui:icon-menu>
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator
		displayStyle="${displayStyle}"
		markupView="lexicon"
	/>
</liferay-ui:search-container>