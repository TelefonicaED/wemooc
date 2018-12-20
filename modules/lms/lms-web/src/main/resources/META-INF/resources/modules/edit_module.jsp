<%@page import="com.ted.lms.exception.ModuleEndDateException"%>
<%@page import="com.ted.lms.exception.ModuleStartDateException"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="com.liferay.portal.kernel.util.StringUtil"%>
<%@page import="com.ted.lms.util.LMSPrefsPropsValues"%>
<%@page import="com.ted.lms.web.internal.util.LMSWebKeys"%>
<%@page import="com.ted.lms.model.Module"%>
<%@page import="com.liferay.portal.kernel.workflow.WorkflowConstants"%>
<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@page import="com.ted.lms.exception.SmallImageNameException"%>
<%@page import="com.ted.lms.exception.SmallImageSizeException"%>
<%@ include file="../init.jsp" %>

<portlet:actionURL name="/modules/edit_module" var="editModuleURL" />

<aui:model-context bean="${module }" model="<%= Module.class %>" />

<liferay-ui:error exception="<%=SmallImageNameException.class %>">
	<liferay-ui:message key="image-names-must-end-with-one-of-the-following-extensions" />.
</liferay-ui:error>

<liferay-ui:error exception="<%= SmallImageSizeException.class %>">
	<liferay-ui:message key="please-enter-a-small-image-with-a-valid-file-size-no-larger-than-x" />
</liferay-ui:error>
<liferay-ui:error exception="<%= ModuleStartDateException.class %>" message="please-enter-a-valid-start-date" />
<liferay-ui:error exception="<%= ModuleEndDateException.class %>" message="please-enter-a-valid-end-date" />

<aui:form action="<%=editModuleURL%>" enctype="multipart/form-data" method="post" name="fm" >

	<aui:input name="<%= Constants.CMD %>" value="${cmd }" type="hidden" />
	<aui:input name="moduleId" type="hidden" value="${moduleId}" />
	<aui:input name="workflowAction" type="hidden" value="<%= WorkflowConstants.ACTION_PUBLISH %>" />

	<div class="container">
		<div class="row">
			<div class="col-md-9">
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
				<div class="row">
					<div class="col-md-4">
						<div class="date-course" id="${renderResponse.getNamespace()}courseStartDate">
							<liferay-ui:message key="start-date-course" />: ${course.getExecutionStartDateFormat(themeDisplay.locale, themeDisplay.timeZone) }
						</div>
						<aui:input dateTogglerCheckboxLabel="use-start-execution-date-course" 
								disabled="${empty module || empty module.startDate }" formName="fm" name="startDate" 
								value="${empty module || empty module.startDate ? course.executionStartDateCalendar : module.startDateCalendar }"/>
						<div class="date-course" id="${renderResponse.getNamespace()}courseEndDate">
							<liferay-ui:message key="end-date-course" />: ${course.getExecutionEndDateFormat(themeDisplay.locale, themeDisplay.timeZone) }
						</div>
						<aui:input dateTogglerCheckboxLabel="use-end-execution-date-course" 
								disabled="${empty module || empty module.endDate }" formName="fm" name="endDate" 
								value="${empty module || empty module.endDate ? course.executionEndDateCalendar : module.endDateCalendar }"/>
					</div>
					<c:if test="${not empty listModuleEvalFactories }" >
						<div class="col-md-5">
							<aui:select name="moduleEvalId" onChange="javascript:${renderResponse.getNamespace()}changeModuleEval(this);" label="module.module-evaluation">
								<c:forEach items="${listModuleEvalFactories}" var="moduleEvalFactory">
									<aui:option selected="${moduleEvalFactory.type == module.moduleEvalId}" label="${moduleEvalFactory.getTitle(themeDisplay.locale)}" 
										value="${moduleEvalFactory.type}"/>
								</c:forEach>
							</aui:select>
							<c:forEach items="${listModuleEvalFactories}" var="moduleEvalFactory">
								<div class="${moduleEvalFactory.type!= module.moduleEvalId ? 'aui-helper-hidden' : '' }" id="${renderResponse.getNamespace()}module_eval_${moduleEvalFactory.type}">
									<c:if test="${not empty moduleEvalFactory.getURLSpecificContent()}">
										<liferay-util:include page="${moduleEvalFactory.getURLSpecificContent()}" portletId="${moduleEvalFactory.getPortletId()}" >
											<liferay-util:param name="moduleId" value="${moduleId }" />
											<liferay-util:param name="groupId" value="${themeDisplay.scopeGroupId }" />
										</liferay-util:include>
									</c:if>
								</div>
							</c:forEach>
							<aui:script>
								function <portlet:namespace />changeModuleEval(moduleEval){
									$('*[id^="<portlet:namespace />module_eval_"]').addClass("aui-helper-hidden");
									$('#<portlet:namespace />module_eval_' + moduleEval.value).removeClass("aui-helper-hidden");
								}
							</aui:script>
						</div>
					</c:if>
					<div class="col-md-3">
						<aui:field-wrapper label="allowed-time" helpMessage="allowed-time.help-message">
							<div class="row">
								<div class="col-md-6">
									<aui:select name="allowedHours" label="" suffix="h">
										<c:forEach var = "i" begin = "0" end = "23">
											<aui:option label="${i }" value="${i }" selected="${i == allowedHours }"/>
										</c:forEach>
									</aui:select>
								</div>
								<div class="col-md-6">
									<aui:select name="allowedMinutes" label="" suffix="min">
										<c:forEach var = "i" begin = "0" end = "59">
											<aui:option label="${i }" value="${i }" selected="${i == allowedMinutes }"/>
										</c:forEach>
									</aui:select>
								</div>
							</div>
						</aui:field-wrapper>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<portlet:actionURL name="/course/upload_image" var="uploadSmallImageURL" />
			
				<div class="lfr-module-small-image-selector">
			
					<liferay-item-selector:image-selector
						draggableImage="vertical"
						fileEntryId="${module.smallImageId }"
						itemSelectorEventName="${smallImageSelectedItemEventName }"
						itemSelectorURL="${itemSelectorURL }"
						maxFileSize="<%=LMSPrefsPropsValues.getModuleImageMaxSize(themeDisplay.getCompanyId())%>"
						paramName="smallImageFileEntry"
						uploadURL="${uploadSmallImageURL}"
						validExtensions='<%=StringUtil.merge(LMSPrefsPropsValues.getModuleImageExtensions(themeDisplay.getCompanyId()), ", ") %>'
					/>
				</div>	
				
				<c:forEach items="${listPrerequisiteFactory }" var="prerequisiteFactory">
					<liferay-util:include page="${prerequisiteFactory.getURLSpecificContent()}" portletId="${prerequisiteFactory.getPortletId()}" >
						<liferay-util:param name="classNameId" value="<%=String.valueOf(PortalUtil.getClassNameId(Module.class)) %>" />
						<liferay-util:param name="classPK" value="${moduleId }" />
						<liferay-util:param name="groupId" value="${themeDisplay.scopeGroupId }" />
					</liferay-util:include>
				</c:forEach>
								
			</div>
		</div>
	</div>
	<aui:button-row>
		<aui:button name="saveButton" type="submit" value="save" />
	</aui:button-row>
</aui:form>