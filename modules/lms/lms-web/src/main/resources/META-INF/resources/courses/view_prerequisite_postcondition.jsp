<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ include file="../init.jsp" %>

<liferay-ui:header title="${course.getTitle(themeDisplay.locale) }" backLabel="back" backURL="${redirect }" />

<h2><liferay-ui:message key="course-admin.prerequisite-and-postcondition"/></h2>

<clay:navigation-bar 
	navigationItems="${navigationItem }"
/>

<c:choose>
	<c:when test="${tabs == 'prerequisites' }">
		<%@ include file="/courses/view_prerequisites.jsp" %>
	</c:when>
	<c:otherwise>
		<%@ include file="/courses/view_postconditions.jsp" %>
	</c:otherwise>
</c:choose>

<script>
	function <portlet:namespace/>refreshPortlet(){
		
		var portletNodeId = '#p_p_id_<%= portletDisplay.getId() %>_';
		var A = AUI();

		var portletNode = A.one(portletNodeId);

		Liferay.Portlet.refresh(
			portletNodeId,
			A.merge(
				Liferay.Util.ns(
					'<portlet:namespace />',
					{
						courseId: '${courseId}',
						mvcRenderCommandName: '/courses/prerequisite_postcondition_course'
					}
				),
				portletNode.refreshURLData || {}
			)
		);
	}
</script>
