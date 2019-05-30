<%@page import="com.liferay.portal.kernel.util.HtmlUtil"%>
<%@page import="com.liferay.portal.kernel.servlet.SessionMessages"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.ted.lms.constants.LMSPortletKeys"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ include file="init.jsp" %>

<%long actId = ParamUtil.getLong(request, "actId");
long studentId = ParamUtil.getLong(request, "studentId");
long courseId = ParamUtil.getLong(request, "courseId");
String result = ParamUtil.getString(request, "result");
String status = ParamUtil.getString(request, "status");%>

<c:choose>
	<c:when test='<%= SessionMessages.contains(renderRequest, "resultEdited") %>'>
		<aui:script>
			Liferay.Util.getOpener().<portlet:namespace/>changeResult('<%= studentId %>', '<%= result %>', '<%=status%>');
		
			Liferay.Util.getWindow().hide();
		</aui:script>
	</c:when>
	<c:otherwise>

		<portlet:actionURL var="updateGradesURL" name="updateResult" windowState="<%= LiferayWindowState.POP_UP.toString() %>">  
			<portlet:param name="actId" value="<%=String.valueOf(actId)%>" /> 
			<portlet:param name="studentId" value="<%=String.valueOf(studentId)%>" /> 
		</portlet:actionURL>
		
		<aui:form action="${updateGradesURL}" name="editResult" method="post" role="form">
			
			<liferay-util:include page="/calification/edit_result.jsp" portletId="<%=LMSPortletKeys.CALIFICATION %>" >
				<liferay-util:param name="actId" value="<%=String.valueOf(actId) %>" />
				<liferay-util:param name="studentId" value="<%=String.valueOf(studentId) %>" />
				<liferay-util:param name="courseId" value="<%=String.valueOf(courseId) %>" />
			</liferay-util:include>
			
			<aui:button-row>
				<aui:button type="submit" value="save"/>
			</aui:button-row>
		</aui:form>
	</c:otherwise>

</c:choose>