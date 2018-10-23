<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.frontend.taglib.clay.servlet.taglib.util.JSPNavigationItemList"%>
<%@ include file="init.jsp" %>

<%String navigation = ParamUtil.getString(renderRequest, "navigation", "description");
long courseId = ParamUtil.getLong(renderRequest, "courseId", 0);%>

<clay:navigation-bar
	navigationItems="<%= new JSPNavigationItemList(pageContext) {
			{
				add(
				navigationItem -> {
					navigationItem.setActive(navigation.equals("description"));
					navigationItem.setHref(renderResponse.createRenderURL(),"mvcRenderCommandName","/courses/edit_course","courseId",courseId);
					navigationItem.setLabel(LanguageUtil.get(request, "description"));
				});

				add(
				navigationItem -> {
					navigationItem.setActive(navigation.equals("configuration"));
					navigationItem.setHref(renderResponse.createRenderURL(), "mvcRenderCommandName","/courses/edit_course","courseId",courseId, 
							"navigation", "configuration");
					navigationItem.setLabel(LanguageUtil.get(request, "configuration"));
				});
				
				add(
				navigationItem -> {
					navigationItem.setActive(navigation.equals("messages"));
					navigationItem.setHref(renderResponse.createRenderURL(), "mvcRenderCommandName","/courses/edit_course","courseId",courseId, 
							"navigation", "messages");
					navigationItem.setLabel(LanguageUtil.get(request, "messages"));
				});
			}
		} %>"
/>

<portlet:actionURL name="/courses/edit_course" var="editCourseURL" />

<aui:form name="fm" enctype="multipart/form-data" method="post" action="${editCourseURL}" >

	<aui:input name="<%= Constants.CMD %>" value="${cmd }" type="hidden" />
	<aui:input name="redirect" type="hidden" value="${redirect}" />

	<c:choose>
		<c:when test='<%= navigation.equals("description") %>'>
			<liferay-util:include page="/course_admin/description.jsp" servletContext="<%= application %>" />
		</c:when>
		<c:otherwise>
			<liferay-util:include page="/course_admin/configuration.jsp" servletContext="<%= application %>" />
		</c:otherwise>
	</c:choose>
	<aui:button-row>
		<aui:button value="cancel" onClick="${redirect}"/>
		
		<aui:button name="saveButton" type="submit" value="save" />
		
		<aui:button name="saveAndContinueButton" type="submit" value="save-and-continue" />
	</aui:button-row>
</aui:form>