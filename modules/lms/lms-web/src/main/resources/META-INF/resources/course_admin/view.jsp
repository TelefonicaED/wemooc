<%@ include file="init.jsp" %>

<liferay-util:include page="/course_admin/nav.jsp" servletContext="<%= application %>" />

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
			href="${course.isApproved() ? course.friendlyURL : '' }"
			name="name"
			value="${course.getTitle(themeDisplay.locale) }"
		/>
		
		<liferay-ui:search-container-column-jsp
			path="/course_admin/course_action.jsp"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator
		displayStyle="${displayStyle}"
		markupView="lexicon"
	/>
</liferay-ui:search-container>