<%@page import="com.ted.lms.service.LearningActivityResultLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.liferay.portal.kernel.service.ServiceContext"%>
<%@page import="com.ted.lms.model.CourseEval"%>
<%@page import="com.liferay.portal.kernel.service.ServiceContextFactory"%>
<%@page import="com.ted.lms.registry.CourseEvalFactoryRegistryUtil"%>
<%@page import="com.ted.lms.model.CourseEvalFactory"%>
<%@page import="com.ted.lms.service.CourseLocalServiceUtil"%>
<%@page import="com.ted.lms.model.CalificationType"%>
<%@page import="com.ted.lms.model.Course"%>
<%@page import="com.ted.lms.registry.CalificationTypeFactoryRegistryUtil"%>
<%@page import="com.ted.lms.model.CalificationTypeFactory"%>
<%@page import="com.ted.lms.model.LearningActivityTypeFactory"%>
<%@page import="com.ted.lms.registry.LearningActivityTypeFactoryRegistryUtil"%>
<%@page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@page import="java.util.List"%>

<%@ include file="../init.jsp" %>

<p>
*****************M�todos calificaci�n***************************
<%

LearningActivityResultLocalServiceUtil.getRequiredLearningActivityResults(themeDisplay.getScopeGroupId(), themeDisplay.getUserId());
List<CalificationTypeFactory> calificationFactories = CalificationTypeFactoryRegistryUtil.getCalificationFactories(themeDisplay.getCompanyId());
List<Course> courses = CourseLocalServiceUtil.getCourses(-1, -1);

for (CalificationTypeFactory calificationFactory : calificationFactories) {
%>
<p>
	calificaci�n: <%=calificationFactory.getTitle(themeDisplay.getLocale()) %>
	<br/>
	<%=calificationFactory.getDescription(themeDisplay.getLocale()) %>
	<%
	if(Validator.isNotNull(courses)){
		Course course = courses.get(0);
		
		CalificationType calification = calificationFactory.getCalificationType(course); %>
		<%=calificationFactory.getType()%>
		<%=calification.getMaxValue() %>
	<%} %>
	<liferay-util:include page="<%=calificationFactory.getURLSpecificContent() %>" portletId="<%=calificationFactory.getPortletId() %>">
	
	</liferay-util:include>
</p>
<%
}%>



*****************************************************************
</p>
<p>
*****************M�todos evaluaci�n***************************
<%
List<CourseEvalFactory> evaluationsFactories = CourseEvalFactoryRegistryUtil.getCourseEvalFactories(themeDisplay.getCompanyId());

for (CourseEvalFactory calificationFactory : evaluationsFactories) {
%>
<p>
	calificaci�n: <%=calificationFactory.getTitle(themeDisplay.getLocale()) %>
	<br/>
	<%=calificationFactory.getDescription(themeDisplay.getLocale()) %>
	<%
	if(Validator.isNotNull(courses)){
		Course course = courses.get(0);
	
		ServiceContext serviceContext = ServiceContextFactory.getInstance(request);
		CourseEval calification = calificationFactory.getCourseEval(course, serviceContext); %>
		<%=calificationFactory.getType()%>
	<%} %>
	<liferay-util:include page="<%=calificationFactory.getURLSpecificContent() %>" portletId="<%=calificationFactory.getPortletId() %>">
	
	</liferay-util:include>
</p>
<%
}%>



*****************************************************************
</p>
<p>
*****************************************************************
<%
List<LearningActivityTypeFactory> assetRendererFactories = LearningActivityTypeFactoryRegistryUtil.getLearningActivityFactories(themeDisplay.getCompanyId());

for (LearningActivityTypeFactory assetRendererFactory : assetRendererFactories) {
%>
	activity: <%=assetRendererFactory.getClassName() %>
<%}%>

Total: <%=assetRendererFactories.size()%>
*****************************************************************
</p>
<liferay-portlet:renderURL var="addCourseURL">
	<portlet:param name="mvcRenderCommandName" value="/lms/edit_course" />
</liferay-portlet:renderURL>

	<liferay-frontend:add-menu
      inline="true"
    >
      <liferay-frontend:add-menu-item
        title='new-course'
        url="${addCourseURL }"
      />
    </liferay-frontend:add-menu>

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
					<portlet:param name="mvcRenderCommandName" value="/lms/edit_course" />
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