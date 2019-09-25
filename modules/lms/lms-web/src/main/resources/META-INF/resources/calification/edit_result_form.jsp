<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ include file="../init.jsp" %>

<liferay-portlet:actionURL var="updateGradesURL" name="updateResult" windowState="<%= LiferayWindowState.POP_UP.toString() %>">  
</liferay-portlet:actionURL>

<%boolean saved = ParamUtil.getBoolean(renderRequest, "saved", false); %>

<c:if test="<%=saved %>">
	<script>
		Liferay.Util.getOpener().refreshPortlet();
		Liferay.Util.getWindow().hide();
	</script>
</c:if>

<aui:form action="${updateGradesURL}" name="editResult" method="post" role="form">

	<%@ include file="edit_result.jsp" %>
	
	<aui:button-row>
		<aui:button value="cancel" onClick="javascript:${renderResponse.namespace}closeResult();" />
		<aui:button type="submit" value="save"/>
	</aui:button-row>

</aui:form>

<aui:script>
	function <portlet:namespace/>closeResult(){
		Liferay.Util.getWindow().hide();
	}
	
</aui:script>