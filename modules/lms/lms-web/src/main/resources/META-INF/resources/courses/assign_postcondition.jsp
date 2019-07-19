<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@page import="com.liferay.portal.kernel.servlet.SessionMessages"%>
<%@ include file="../init.jsp" %>

<c:choose>
	<c:when test='<%= SessionMessages.contains(renderRequest, "postconditionAdded") %>'>
		<aui:script>
			Liferay.Util.getOpener().<portlet:namespace/>refreshPortlet();
		
			Liferay.Util.getWindow().hide();
		</aui:script>
	</c:when>
	<c:otherwise>
		<portlet:actionURL name="/courses/update_postcondition" var="addPostconditionsURL" >
			<portlet:param name="<%=Constants.CMD %>" value="<%=Constants.ADD %>"/>
			<portlet:param name="courseId" value="${courseId }"/>
			<portlet:param name="classNamePostcondition" value="${postconditionFactory.className }" />
		</portlet:actionURL>
		
		<aui:form action="${addPostconditionsURL }" method="post" name="addPostconditionsFm">
		
			<liferay-util:include page="${postconditionFactory.getURLSpecificContent()}" portletId="${postconditionFactory.getPortletId()}" >
				<liferay-util:param name="classNameId" value="${classNameId }" />
				<liferay-util:param name="classPK" value="${courseId }" />
				<liferay-util:param name="groupId" value="${course.groupCreatedId }" />
				<liferay-util:param name="required" value="true" />
			</liferay-util:include>
			
			<aui:button-row>
				<aui:button type="submit" value="add" />
			</aui:button-row>
		</aui:form>
	</c:otherwise>
</c:choose>