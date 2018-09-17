<%@page import="com.ted.lms.model.ModuleEval"%>
<%@page import="com.ted.lms.registry.ModuleEvalFactoryRegistryUtil"%>
<%@page import="com.ted.lms.model.ModuleEvalFactory"%>
<%@page import="com.ted.audit.api.registry.AuditRegistryUtil"%>
<%@page import="com.ted.audit.api.Audit"%>
<%@page import="com.ted.lms.model.InscriptionType"%>
<%@page import="com.ted.lms.registry.InscriptionTypeFactoryRegistryUtil"%>
<%@page import="com.ted.lms.model.InscriptionTypeFactory"%>
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
*****************Métodos inscripción***************************
<%

List<InscriptionTypeFactory> inscriptionFactories = InscriptionTypeFactoryRegistryUtil.getInscriptionFactories(themeDisplay.getCompanyId());
List<Course> courses = CourseLocalServiceUtil.getCourses(-1, -1);

for (InscriptionTypeFactory inscriptionFactory : inscriptionFactories) {
%>
<p>
	calificación: <%=inscriptionFactory.getTitle(themeDisplay.getLocale()) %>
	<br/>
	<%=inscriptionFactory.getDescription(themeDisplay.getLocale()) %>
	<%
	if(Validator.isNotNull(courses)){
		Course course = courses.get(0);
		ServiceContext serviceContext = ServiceContextFactory.getInstance(request);
		InscriptionType inscription = inscriptionFactory.getInscriptionType(course, serviceContext); %>
		<%=inscriptionFactory.getType()%>
	<%} %>
	<liferay-util:include page="<%=inscriptionFactory.getURLSpecificContent() %>" portletId="<%=inscriptionFactory.getPortletId() %>">
	
	</liferay-util:include>
</p>
<%
}%>
*****************************************************************
</p>

<p>
*****************Auditoría***************************
<%

List<Audit> audits = AuditRegistryUtil.getAudits(themeDisplay.getCompanyId());

for (Audit audit : audits) {
%>
<p>
	audit: <%=audit.getClassName() %>
	<%audit.audit(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(), 1, Course.class.getName(), 1, 2, themeDisplay.getUserId(), themeDisplay.getUser().getFullName(), ""); %>
</p>
<%
}%>



*****************************************************************

<p>
*****************Métodos evaluación de módulo***************************
<%

List<ModuleEvalFactory> moduleEvalFactories = ModuleEvalFactoryRegistryUtil.getModuleEvalFactories(themeDisplay.getCompanyId());

for (ModuleEvalFactory moduleEvalFactory : moduleEvalFactories) {
%>
<p>
	moduleEvalFactory: <%=moduleEvalFactory.getTitle(themeDisplay.getLocale()) %>
	<br/>
	<%=moduleEvalFactory.getDescription(themeDisplay.getLocale()) %>
	<%=moduleEvalFactory.getType()%>
</p>
<%
}%>
*****************************************************************
</p>

<p>
*****************Métodos calificación***************************
<%

List<CalificationTypeFactory> calificationFactories = CalificationTypeFactoryRegistryUtil.getCalificationFactories(themeDisplay.getCompanyId());

for (CalificationTypeFactory calificationFactory : calificationFactories) {
%>
<p>
	calificación: <%=calificationFactory.getTitle(themeDisplay.getLocale()) %>
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
*****************Métodos evaluación***************************
<%
List<CourseEvalFactory> evaluationsFactories = CourseEvalFactoryRegistryUtil.getCourseEvalFactories(themeDisplay.getCompanyId());

for (CourseEvalFactory calificationFactory : evaluationsFactories) {
%>
<p>
	calificación: <%=calificationFactory.getTitle(themeDisplay.getLocale()) %>
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
**************************Actividades***************************************
<%
List<LearningActivityTypeFactory> assetRendererFactories = LearningActivityTypeFactoryRegistryUtil.getLearningActivityFactories(themeDisplay.getCompanyId());

for (LearningActivityTypeFactory assetRendererFactory : assetRendererFactories) {
%>
<p>
	activity: <%=assetRendererFactory.getClassName() %>
</p>
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