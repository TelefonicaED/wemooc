<%@page import="com.ted.lms.constants.LMSPortletKeys"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="com.liferay.portal.kernel.servlet.SessionMessages"%>
<%@ include file="init.jsp" %>

<c:choose>
	<c:when test="${correctActivity }">
		<span>${studentName }</span>
		<span><liferay-ui:message key="learning-activity.online.answer-student"/></span>
	</c:when>
	<c:otherwise>
		<span><liferay-ui:message key="date"/>: ${dateFormated} </span>
	</c:otherwise>
</c:choose>

<div>
	<c:choose>
		<c:when test="${not empty richtext }">
			<liferay-ui:panel-container >
				<liferay-ui:panel id="panelId" title="" collapsible="false" extended="false" >
					${richtext}
				</liferay-ui:panel>
			</liferay-ui:panel-container >
		</c:when>
		<c:otherwise>
			${text }
		</c:otherwise>
	</c:choose>
</div>

<c:if test="${not empty urlFile }">
	<div class="doc_descarga">
		<span>${titleFile} (${sizeKbFile}Kb)</span>
		<a href="${urlFile}" target="_blank"><liferay-ui:message key="download"/></a>
	</div>
</c:if>

<c:if test="${correctActivity }">
	<c:choose>
		<c:when test='<%= SessionMessages.contains(renderRequest, "resultEdited") %>'>
			<aui:script>
				Liferay.Util.getOpener().<portlet:namespace/>changeResult('${studentId}', '${status}');
			
				Liferay.Util.getWindow().hide();
			</aui:script>
		</c:when>
		<c:otherwise>
	
			<portlet:actionURL var="updateResultURL" name="updateResult" windowState="<%= LiferayWindowState.POP_UP.toString() %>">  
				<portlet:param name="actId" value="${actId }" /> 
				<portlet:param name="studentId" value="${studentId }" /> 
			</portlet:actionURL>
			
			<aui:form action="${updateResultURL}" name="editResult" method="post" role="form">
				
				<liferay-util:include page="/calification/edit_result.jsp" portletId="<%=LMSPortletKeys.CALIFICATION %>" >
					<liferay-util:param name="actId" value="${actId }" />
					<liferay-util:param name="studentId" value="${studentId }" />
					<liferay-util:param name="courseId" value="${courseId }" />
				</liferay-util:include>
				
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
		</c:otherwise>
	</c:choose>
</c:if>