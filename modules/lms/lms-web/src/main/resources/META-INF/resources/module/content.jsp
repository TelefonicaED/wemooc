<%@page import="com.ted.lms.model.Module"%>
<%@ include file="../init.jsp" %>

<%@page import="com.liferay.portal.kernel.util.WebKeys"%>

<liferay-ui:error-marker
	key="<%= WebKeys.ERROR_SECTION %>"
	value="content"
/>

<aui:model-context bean="${module }" model="<%= Module.class %>" />

<liferay-frontend:fieldset>
	<aui:input autoFocus="true" label="title" localized="true" name="titleMapAsXML" type="text" wrapperCssClass="module-title" value="${module.title }">
		<aui:validator name="required" />
	</aui:input>

	<div class="module-description">
		<label for="<portlet:namespace />descriptionMapAsXML"><liferay-ui:message key="description" /></label>

		<liferay-ui:input-localized
			cssClass="form-control"
			editorName="alloyeditor"
			formName="fm"
			name="descriptionMapAsXML"
			placeholder="description"
			type="editor"
			xml="${not empty module ? module.descriptionMapAsXML : '' }"
		/>
	</div>
	
	
</liferay-frontend:fieldset>