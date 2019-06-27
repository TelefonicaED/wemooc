<%@page import="com.liferay.portal.kernel.util.LocaleUtil"%>
<%@page import="com.ted.lms.model.Course"%>
<%@page import="com.liferay.portal.kernel.workflow.WorkflowConstants"%>
<%@page import="com.ted.lms.web.constants.LMSPortletConstants"%>
<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@ include file="init.jsp" %>

<aui:model-context bean="${course }" model="<%= Course.class %>" />

<aui:form name="fm" enctype="multipart/form-data" method="post" action="${addEditionURL}" >
	<aui:input name="<%= Constants.CMD %>" value="${cmd}" type="hidden" />
	<aui:input name="redirect" type="hidden" value="${redirect}" />

	<div class="container">
		<div class="row">
			<div class="col-md-8">
				<aui:input autoFocus="true" label="title" localized="true" name="titleMapAsXML" type="text" wrapperCssClass="course-title" 
						value="" placeholder="write-title-here">
					<aui:validator name="required" />
				</aui:input>
			</div>
			<div class="col-md-4">
				<div class="form-group">
					<label for="<portlet:namespace />friendlyURL"><liferay-ui:message key="url" /></label>
				
					<liferay-ui:input-localized
						defaultLanguageId="<%= LocaleUtil.toLanguageId(themeDisplay.getSiteDefaultLocale()) %>"
						inputAddon="${courseDisplayContext.friendlyURLBase }"
						maxLength='${friendlyURLMaxLength }'
						name="friendlyURLMapAsXML"
						xml=""
					/>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4">
				<aui:field-wrapper label="registration-date" >
					<div class="row">
						<div class="col-md-6">
							<aui:input formName="fm" name="registrationStartDate" label="start"/>
						</div>
						<div class="col-md-6">
							<aui:input formName="fm" label="end" name="registrationEndDate" />
						</div>
					</div>
				</aui:field-wrapper>
			</div>
			<div class="col-md-4">
				<aui:field-wrapper label="execution-date">
					<div class="row">
						<div class="col-md-6">
							<aui:input formName="fm" label="start" name="executionStartDate" />
						</div>
						<div class="col-md-6">
							<aui:input formName="fm" label="end" name="executionEndDate"/>
						</div>
					</div>
				</aui:field-wrapper>
			</div>
			<div class="col-md-4">
				<c:choose>
					<c:when test="${not empty listLayoutSetPrototype && listLayoutSetPrototype.size() > 0 }">
						<aui:select name="layoutSetPrototypeId" label="template" helpMessage="template.help-message" required="true" >
							<c:forEach items="${listLayoutSetPrototype }" var="layoutSetPrototype">
								<aui:option label="${layoutSetPrototype.getName(themeDisplay.locale) }" value="${layoutSetPrototype.layoutSetPrototypeId }" selected="${layoutSetPrototype.layoutSetPrototypeId == course.layoutSetPrototypeId }"/>
							</c:forEach>
						</aui:select>
					</c:when>
				</c:choose>
			</div>
		</div>
	</div>
	
	<aui:button-row>
		<aui:button value="cancel" onClick="${redirect}"/>
		<aui:button name="saveButton" type="submit" value="course-admin.create-edition" />
	</aui:button-row>
</aui:form>