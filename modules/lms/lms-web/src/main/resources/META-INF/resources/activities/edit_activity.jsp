<%@page import="com.liferay.portal.kernel.service.ServiceContext"%>
<%@page import="com.liferay.portal.kernel.util.PropsKeys"%>
<%@page import="com.liferay.portal.kernel.model.User"%>
<%@page import="com.ted.lms.constants.LearningActivityConstants"%>
<%@page import="com.liferay.portal.kernel.model.TicketConstants"%>
<%@page import="com.liferay.portal.kernel.service.TicketLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.model.Ticket"%>
<%@page import="com.liferay.portal.kernel.util.Time"%>
<%@page import="com.liferay.portal.kernel.util.PropsUtil"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="java.util.Date"%>
<%@page import="com.liferay.portal.kernel.util.TextFormatter"%>
<%@page import="com.liferay.portal.kernel.util.HtmlUtil"%>
<%@page import="com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.liferay.portal.kernel.repository.model.FileEntry"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.document.library.kernel.model.DLFolderConstants"%>
<%@page import="com.ted.lms.util.LMSPrefsPropsValues"%>
<%@page import="com.liferay.portal.kernel.util.StringUtil"%>
<%@page import="com.liferay.portal.kernel.util.TempFileEntryUtil"%>
<%@page import="java.text.DecimalFormatSymbols"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="com.liferay.portal.kernel.workflow.WorkflowConstants"%>
<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@page import="com.ted.lms.model.LearningActivity"%>
<%@page import="com.liferay.document.library.kernel.model.DLFileEntryConstants" %>
<%@ include file="../init.jsp" %>

<portlet:actionURL name="/activities/edit_activity" var="editActivityURL" />

<aui:form action="${editActivityURL}" enctype="multipart/form-data" method="post" name="fm" >

	<aui:model-context bean="${activity }" model="<%= LearningActivity.class %>" />

	<aui:input name="<%= Constants.CMD %>" value="${cmd }" type="hidden" />
	<aui:input name="moduleId" type="hidden" value="${moduleId}" />
	<aui:input name="actId" type="hidden" value="${actId}" />
	<aui:input name="type" type="hidden" value="${learningActivityTypeFactory.type}" />
	<aui:input name="workflowAction" type="hidden" value="<%= WorkflowConstants.ACTION_PUBLISH %>" />

	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<aui:input autoFocus="true" label="title" localized="true" name="titleMapAsXML" type="text" wrapperCssClass="activity-title" 
					value="${activity.title }">
					<aui:validator name="required" />
				</aui:input>
			</div>
		</div>
		<div class="row">	
			<div class="col-md-12">
				<div class="module-description">
					<label for="<portlet:namespace />descriptionMapAsXML"><liferay-ui:message key="description" /></label>
					<liferay-ui:input-localized
						cssClass="form-control"
						editorName="alloyeditor"
						formName="fm"
						name="descriptionMapAsXML"
						placeholder="description"
						type="editor"
						xml="${not empty activity ? activity.descriptionMapAsXML : '' }"
					/>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4">
				<aui:input name="required" label="activity-required" type="toggle-switch" value="${activity.required }"/>
			</div>
			<c:if test="${learningActivityTypeFactory.isTriesConfigurable() }">
					<div class="col-md-4">
						<div class="row">
							<div class="col-md-8">
								<aui:input name="triesToggle" label="num-max-tries" type="toggle-switch" value="${activity.tries > 0 }"/>
							</div>
							<div class="col-md-4">
								<aui:input name="tries" label="" type="text" disabled="${empty activity || activity.tries == 0 }" 
									value="${not empty activity && activity.tries > 0 ? activity.tries : '' }">
									<aui:validator name="required">
										function(){
											return AUI.$('#<portlet:namespace />triesToggle').prop("checked");
										}
									</aui:validator>
									<aui:validator name="number"/>
								</aui:input>
							</div>
						</div>
						<script>
							$('#<portlet:namespace />triesToggle').on(
								'change',
								function() {
									if(!$('#<portlet:namespace />triesToggle').prop('checked')){
										$('#<portlet:namespace />tries').val('');
									}else{
										$('#<portlet:namespace />tries').val('${learningActivityTypeFactory.defaultTries}');
									}
									$('#<portlet:namespace />tries').prop("disabled", !$('#<portlet:namespace />tries').prop("disabled"));
								}
							);
						</script>
					</div>
				</c:if>
				<c:if test="${learningActivityTypeFactory.isScoreConfigurable() }">
						<div class="col-md-6">
							<aui:input name="passPuntuation" label="${learningActivityTypeFactory.scoreConfigurableProperty }" type="text" suffix="%" 
								helpMessage="${learningActivityTypeFactory.scoreConfigurableHelpMessageProperty }" 
								value="${not empty activity ? activity.passPuntuation : learningActivityTypeFactory.defaultScore }"/>
						</div>
				</c:if>
				<c:if test="${not empty learningActivityTypeFactory.getURLSpecificContent() }">
					<liferay-util:include page="${learningActivityTypeFactory.getURLSpecificContent()}" portletId="${learningActivityTypeFactory.getPortletId()}" >
						<liferay-util:param name="actId" value="${activity.actId }" />
						<liferay-util:param name="canBeEdited" value="${canBeEdited }" />
						<liferay-util:param name="groupId" value="${themeDisplay.scopeGroupId }" />
					</liferay-util:include>
				</c:if>	
				<c:if test="${learningActivityTypeFactory.hasDocuments() }">
					<div class="activity-attachmetns col-md-6">
						<aui:input name="removeFileEntryIds" type="hidden" />
						
						<div class="lfr-dynamic-uploader">
							<div class="lfr-upload-container" id="<portlet:namespace />fileUpload"></div>
						</div>
						
						<span id="<portlet:namespace />selectedFileNameContainer"></span>
						
						<div class="hide" id="<portlet:namespace />metadataExplanationContainer"></div>
						
						<div class="hide selected" id="<portlet:namespace />selectedFileNameMetadataContainer"></div>
						
						<%
						Date expirationDate = new Date(System.currentTimeMillis() + GetterUtil.getInteger(PropsUtil.get(PropsKeys.SESSION_TIMEOUT)) * Time.MINUTE);
						
						Ticket ticket = TicketLocalServiceUtil.addTicket(user.getCompanyId(), User.class.getName(), user.getUserId(), TicketConstants.TYPE_IMPERSONATE, null, expirationDate, new ServiceContext());
						%>
						
						<aui:script use="liferay-upload">
							new Liferay.Upload(
								{
									boundingBox: '#<portlet:namespace />fileUpload',
					
									<%
									DecimalFormatSymbols decimalFormatSymbols = DecimalFormatSymbols.getInstance(locale);
									long folderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;
									%>
					
									decimalSeparator: '<%= decimalFormatSymbols.getDecimalSeparator() %>',
					
									deleteFile: '<liferay-portlet:actionURL doAsUserId="<%= user.getUserId() %>" name="/activities/upload_multiple_documents"><portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE_TEMP %>" /><portlet:param name="folderId" value="<%= String.valueOf(folderId) %>" /></liferay-portlet:actionURL>&ticketKey=<%= ticket.getKey() %><liferay-ui:input-permissions-params modelName="<%= DLFileEntryConstants.getClassName() %>" />',
									fileDescription: '<%= StringUtil.merge(LMSPrefsPropsValues.getActivityDocumentsExtensions(themeDisplay.getCompanyId())) %>',
									maxFileSize: '<%=LMSPrefsPropsValues.getActivityDocumentsMaxSize(themeDisplay.getCompanyId())  %> B',
									metadataContainer: '#<portlet:namespace />selectedFileNameMetadataContainer',
									metadataExplanationContainer: '#<portlet:namespace />metadataExplanationContainer',
									namespace: '<portlet:namespace />',
									tempFileURL: {
										method: Liferay.Service.bind('/lms.learningactivity/get-temp-file-names'),
										params: {
											groupId: <%= scopeGroupId %>
										}
									},
									uploadFile: '<liferay-portlet:actionURL doAsUserId="<%= user.getUserId() %>" name="/activities/upload_multiple_documents"><portlet:param name="<%= Constants.CMD %>" value="<%= Constants.ADD_TEMP %>" /><portlet:param name="folderId" value="<%= String.valueOf(folderId) %>" /></liferay-portlet:actionURL>&ticketKey=<%= ticket.getKey() %><liferay-ui:input-permissions-params modelName="<%= DLFileEntryConstants.getClassName() %>" />'
								}
							);
						</aui:script>
						<%List<FileEntry> attachmentsFileEntries = (List<FileEntry>)request.getAttribute("attachmentsFileEntries");%>
						<c:if test="<%= !attachmentsFileEntries.isEmpty() %>">
							<h4><liferay-ui:message key="learning-activity.saved-attachments" /></h4>
	
							<div id="<portlet:namespace />existingAttachmentsContainer">
	
								<%
								for (FileEntry fileEntry : attachmentsFileEntries) {
								%>
	
									<div id="<portlet:namespace />fileEntryIdWrapper<%= fileEntry.getFileEntryId() %>">
	
										<%
										String rowURL = PortletFileRepositoryUtil.getDownloadPortletFileEntryURL(themeDisplay, fileEntry, "status=" + WorkflowConstants.STATUS_APPROVED);
										%>
	
										<liferay-ui:icon
											iconCssClass="icon-paper-clip"
											label="<%= true %>"
											message='<%= HtmlUtil.escape(fileEntry.getTitle()) + " (" + TextFormatter.formatStorageSize(fileEntry.getSize(), locale) + ")" %>'
											method="get"
											url="<%= rowURL %>"
										/>
	
										<%
										String taglibURL = "javascript:" + renderResponse.getNamespace() + "deleteFileEntry('" + fileEntry.getFileEntryId() + "');";
										%>
	
										<liferay-ui:icon-delete
											label="<%= true %>"
											url="<%= taglibURL %>"
										/>
									</div>
	
								<%
								}
								%>
	
							</div>
							<aui:script>
								Liferay.provide(
									window,
									'<portlet:namespace />deleteFileEntry',
									function(fileEntryId) {
										var A = AUI();
							
										var removeFileEntryIdsInput = A.one('#<portlet:namespace />removeFileEntryIds');
							
										var fileEntries = removeFileEntryIdsInput.val();
							
										if (fileEntries.length) {
											fileEntries += ',';
										}
							
										fileEntries += fileEntryId;
							
										removeFileEntryIdsInput.val(fileEntries);
							
										var fileEntryIdWrapper = A.one('#<portlet:namespace />fileEntryIdWrapper' + fileEntryId);
							
										if (fileEntryIdWrapper) {
											fileEntryIdWrapper.hide();
										}
									},
									['aui-base']
								);
							</aui:script>
						</c:if>
					</div>
				</c:if>
		</div>
		<c:if test="${learningActivityTypeFactory.isFeedbackCorrectConfigurable() || learningActivityTypeFactory.isFeedbackNoCorrectConfigurable() }">
			<div class="row">
				<c:if test="${learningActivityTypeFactory.isFeedbackCorrectConfigurable() }">
					<div class="col-md-6">
						<aui:input name="feedbackCorrectToggle" label="learning-activity.feedback-correct" 
									type="toggle-switch" value="${not empty activity.feedbackCorrect}" />
						<div id="${renderResponse.getNamespace()}feedback_correct_wrapper" class="${empty activity || empty activity.feedbackCorrect ? 'hide' : ''}">
							<aui:input label="" localized="true" name="feedbackCorrectMapAsXML" type="textarea" value="${activity.feedbackCorrect }"
								required="${not empty activity.feedbackCorrect }"/>
						</div>
					</div>
					<script>
						$('#<portlet:namespace />feedbackCorrectToggle').on(
							'change',
							function() {
								$('#<portlet:namespace />feedback_correct_wrapper').toggleClass("hide");
								$('#<portlet:namespace />feedbackCorrectMapAsXML').prop("required", !$('#<portlet:namespace />feedbackCorrectMapAsXML').prop("required"));
							}
						);
					</script>
				</c:if>
				<c:if test="${learningActivityTypeFactory.isFeedbackNoCorrectConfigurable() }">
					<div class="col-md-6">
						<aui:input name="feedbackNoCorrectToggle" label="learning-activity.feedback-no-correct" 
									type="toggle-switch" value="${not empty activity.feedbackNoCorrect}" />
						<div id="${renderResponse.getNamespace()}feedback_no_correct_wrapper" class="${empty activity || empty activity.feedbackNoCorrect ? 'hide' : ''}">
							<aui:input label="" localized="true" name="feedbackNoCorrectMapAsXML" type="textarea" value="${activity.feedbackNoCorrect }"
								required="${not empty activity.feedbackNoCorrect }"/>
						</div>
					</div>
					<script>
						$('#<portlet:namespace />feedbackNoCorrectToggle').on(
							'change',
							function() {
								$('#<portlet:namespace />feedback_no_correct_wrapper').toggleClass("hide");
								$('#<portlet:namespace />feedbackNoCorrectMapAsXML').prop("required", !$('#<portlet:namespace />feedbackNoCorrectMapAsXML').prop("required"));
							}
						);
					</script>
				</c:if>
			</div>
		</c:if>
		<div class="sheet-section">
			<h3 class="sheet-subtitle"><liferay-ui:message key="restrictions" /></h3>
			<div class="row">
				<div class="date-course col-md-6" >
					<c:choose>
						<c:when test="${not empty module.startDate }">
							<liferay-ui:message key="start-date-module" />: ${module.getStartDateFormat(themeDisplay.locale, themeDisplay.timeZone) }
							<aui:input dateTogglerCheckboxLabel="use-start-date-module" 
								disabled="${empty activity || empty activity.startDate }" formName="fm" name="startDate" 
								value="${empty activity || empty activity.startDate ? module.startDateCalendar : activity.startDateCalendar }"/>
						</c:when>
						<c:otherwise>
							<liferay-ui:message key="start-date-course" />: ${course.getExecutionStartDateFormat(themeDisplay.locale, themeDisplay.timeZone) }
							<aui:input dateTogglerCheckboxLabel="use-start-execution-date-course" 
								disabled="${empty activity || empty activity.startDate }" formName="fm" name="startDate" 
								value="${empty activity || empty activity.startDate ? course.executionStartDateCalendar : activity.startDateCalendar }"/>
						</c:otherwise>
					</c:choose>
				</div>
	
				<div class="date-course col-md-6">
					<c:choose>
						<c:when test="${not empty module.endDate }">
							<liferay-ui:message key="end-date-module" />: ${module.getEndDateFormat(themeDisplay.locale, themeDisplay.timeZone) }
							<aui:input dateTogglerCheckboxLabel="use-end-date-module" 
									disabled="${empty activity || empty activity.endDate }" formName="fm" name="endDate" 
									value="${empty activity || empty activity.endDate ? module.endDateCalendar : activity.endDateCalendar }"/>
						</c:when>
						<c:otherwise>
							<liferay-ui:message key="end-date-course" />: ${course.getExecutionEndDateFormat(themeDisplay.locale, themeDisplay.timeZone) }
							<aui:input dateTogglerCheckboxLabel="use-end-execution-date-course" 
									disabled="${empty activity || empty activity.endDate }" formName="fm" name="endDate" 
									value="${empty activity || empty activity.endDate ? course.executionEndDateCalendar : activity.endDateCalendar }"/>
						</c:otherwise>
					</c:choose>
				</div>
				<c:forEach items="${listPrerequisiteFactory }" var="prerequisiteFactory">
					<div class="col-md-6">
						<liferay-util:include page="${prerequisiteFactory.getURLSpecificContent()}" portletId="${prerequisiteFactory.getPortletId()}" >
							<liferay-util:param name="classNameId" value="<%=String.valueOf(PortalUtil.getClassNameId(LearningActivity.class)) %>" />
							<liferay-util:param name="classPK" value="${activity.actId }" />
							<liferay-util:param name="prerequisiteRelationId" value="${activity.getPrerequisiteRelation(prerequisiteFactory.getClassNameId()).prerequisiteRelationId }" />
							<liferay-util:param name="groupId" value="${themeDisplay.scopeGroupId }" />
						</liferay-util:include>
					</div>
				</c:forEach>
			</div>
		</div>
		<div class="sheet-section">
			<h3 class="sheet-subtitle"><liferay-ui:message key="categorization" /></h3>
			<div class="row">
				<div class="col-md-6">
					<liferay-asset:asset-tags-selector
						className="<%= LearningActivity.class.getName() %>"
						classPK="${activity.actId }"
					/>
				</div>
				<div class="col-md-6">
					<liferay-asset:asset-categories-selector
						className="<%= LearningActivity.class.getName() %>"
						classPK="${activity.actId }"
						
					/>
				</div>
			</div>
		</div>
	</div>
	<aui:button-row>
		<aui:button name="saveButton" type="submit" value="save" />
		<script>
			$('#<portlet:namespace />saveButton').on(
				'click',
				function() {
					$('#<portlet:namespace />workflowAction').val('<%= WorkflowConstants.ACTION_SAVE_DRAFT %>');
				}
			);
		</script>
		<aui:button name="saveButtonAndView" type="submit-and-view" value="save-and-view" />
		<script>
			$('#<portlet:namespace />saveButtonAndView').on(
				'click',
				function() {
					$('#<portlet:namespace />workflowAction').val('<%= WorkflowConstants.ACTION_PUBLISH %>');
				}
			);
		</script>
	</aui:button-row>
</aui:form>

<aui:script use="aui-base,event-input">
	var form = A.one('#<portlet:namespace />fm');

	form.on(
		'submit',
		function() {

			updateMultipleLearningActivityAttachments();
		}
	);

	var updateMultipleLearningActivityAttachments = function() {
		var Lang = A.Lang;

		var selectedFileNameContainer = A.one('#<portlet:namespace />selectedFileNameContainer');

		var TPL_INPUT = '<input id="<portlet:namespace />selectedFileName{id}" name="<portlet:namespace />selectedFileName" type="hidden" value="{value}" />';

		var values = A.all('input[name=<portlet:namespace />selectUploadedFile]:checked').val();

		var buffer = [];

		for (var i = 0; i < values.length; i++) {
			buffer[i] = Lang.sub(
				TPL_INPUT,
				{
					id: i,
					value: values[i]
				}
			);
		}

		selectedFileNameContainer.html(buffer.join(''));
	};
</aui:script>