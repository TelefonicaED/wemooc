<%@ include file="/init.jsp" %>

<aui:button value="new-course-type" onClick="${addCourseTypeURL}"/>

<liferay-ui:search-container
	id="courseTypes"
	searchContainer="${searchContainer}"
	var="courseTypeSearchContainer"
>
	<liferay-ui:search-container-row
		className="com.ted.lms.model.CourseType"
		modelVar="courseType"
	>

		<liferay-ui:search-container-column-text
			name="name"
			value="${courseType.getName(themeDisplay.locale) }"
		/>
		
		<liferay-ui:search-container-column-jsp
			path="/course_type/course_type_action.jsp"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator
		displayStyle="${displayStyle}"
		markupView="lexicon"
	/>
</liferay-ui:search-container>