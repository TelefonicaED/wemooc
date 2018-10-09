<%@page import="com.ted.lms.model.Module"%>
<%@page import="com.liferay.portal.kernel.workflow.WorkflowConstants"%>
<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@ include file="../init.jsp" %>

<portlet:actionURL name="/modules/edit_module" var="editModuleURL" />

<aui:model-context bean="${module }" model="<%= Module.class %>" />

<liferay-frontend:edit-form action="<%=editModuleURL%>" enctype="multipart/form-data" method="post" name="fm" >

	<aui:input name="<%= Constants.CMD %>" value="${cmd }" type="hidden" />
	<aui:input name="redirect" type="hidden" value="${redirect}" />
	<aui:input name="moduleId" type="hidden" value="${moduleId}" />
	<aui:input name="workflowAction" type="hidden" value="<%= WorkflowConstants.ACTION_PUBLISH %>" />

	<liferay-frontend:edit-form-body>

		<liferay-frontend:form-navigator
			formModelBean="${module}"
			id="${formNavigatorId }"
			showButtons="false" 
		/>
	</liferay-frontend:edit-form-body>

	<liferay-frontend:edit-form-footer>

		<aui:button name="publishButton" type="submit" value="submit" />

		<aui:button href="${redirect}" name="cancelButton" type="cancel" />
	</liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>