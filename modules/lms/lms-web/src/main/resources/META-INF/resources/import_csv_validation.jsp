<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@page import="com.liferay.portal.kernel.util.StringUtil"%>
<%@page import="com.liferay.portal.kernel.upload.UploadServletRequestConfigurationHelperUtil"%>
<%@page import="com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil"%>
<%@page import="com.liferay.document.library.configuration.DLConfiguration"%>
<%@page import="java.text.DecimalFormatSymbols"%>
<%@page import="com.liferay.portal.kernel.service.ServiceContext"%>
<%@page import="com.liferay.portal.kernel.model.TicketConstants"%>
<%@page import="com.liferay.portal.kernel.model.User"%>
<%@page import="com.liferay.portal.kernel.service.TicketLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.model.Ticket"%>
<%@page import="com.liferay.portal.kernel.util.Time"%>
<%@page import="com.liferay.portal.util.PropsValues"%>
<%@page import="java.util.Date"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.ted.lms.constants.LMSPortletKeys"%>
<%@page import="com.liferay.exportimport.kernel.lar.ExportImportHelperUtil"%>
<%@page import="com.liferay.portal.kernel.repository.model.FileEntry"%>
<%@page import="com.liferay.exportimport.kernel.lar.ExportImportHelper"%>
<%@page import="com.liferay.portal.kernel.service.LayoutServiceUtil"%>
<%@page import="com.liferay.portal.kernel.model.Portlet"%>
<%@page import="com.liferay.portal.kernel.service.PortletLocalServiceUtil"%>
<%@page import="com.liferay.exportimport.kernel.background.task.BackgroundTaskExecutorNames"%>
<%@page import="com.liferay.portal.kernel.util.CalendarFactoryUtil"%>
<%@page import="java.util.Calendar"%>
<%@page import="com.liferay.portal.kernel.util.HtmlUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ include file="init.jsp" %>

<div id="<portlet:namespace />exportImportOptions">

	<div class='${incompleteBackgroundTaskCount == 0 ? "hide" : "in-progress"}' id="<portlet:namespace />incompleteProcessMessage">
		<div class="alert alert-info">
			<c:choose>
				<c:when test="${incompleteBackgroundTaskCount == 1 }">
					<liferay-ui:message key="there-is-currently-1-process-in-progress" />
				</c:when>
				<c:when test="${incompleteBackgroundTaskCount > 1 }">
					<liferay-ui:message arguments="${incompleteBackgroundTaskCount - 1 }" key="there-is-currently-1-process-in-progress-and-x-pending" translateArguments="false" />
				</c:when>
				<c:otherwise>
					<liferay-ui:message key="there-are-no-processes-in-progress-anymore" />
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<aui:form action="${importPortletURL}" method="post" name="fm1">
		
		<liferay-frontend:info-bar>
			<span class="text-secondary">
				${messageInfo }
			</span>
		</liferay-frontend:info-bar>
	
		<div class='${empty fileEntry ? "hide-dialog-footer" : ""}'>
			<div class="container-fluid-1280">
				<div class="lfr-upload-container" id="<portlet:namespace />fileUpload"></div>
			</div>
		</div>
	
		<aui:button-row>
			<aui:button name="continueButton" type="submit" value="continue" />
		</aui:button-row>	
		<aui:script use="liferay-upload">
			var liferayUpload = new Liferay.Upload(
				{
					boundingBox: '#<portlet:namespace />fileUpload',
					decimalSeparator: '${decimalFormatSymbols.decimalSeparator}',
					deleteFile: '${deleteFileURL}&ticketKey=${ticket.getKey()}',
					fileDescription: '${fileDescription}',
					maxFileSize: '<%= UploadServletRequestConfigurationHelperUtil.getMaxSize() %> B',
					metadataContainer: '#<portlet:namespace />commonFileMetadataContainer',
					metadataExplanationContainer: '#<portlet:namespace />metadataExplanationContainer',
					multipleFiles: false,
					namespace: '<portlet:namespace />',
					'strings.dropFileText': '<liferay-ui:message key="drop-a-csv-file-here-to-import" />',
					'strings.fileCannotBeSavedText': '<liferay-ui:message key="the-file-x-cannot-be-imported" />',
					'strings.pendingFileText': '<liferay-ui:message key="this-file-was-previously-uploaded-but-not-actually-imported" />',
					'strings.uploadsCompleteText': '<liferay-ui:message key="the-file-is-ready-to-be-imported" />',
					tempFileURL: {
						method: Liferay.Service.bind('/layout/get-temp-file-names'),
						params: {
							folderName: '${folderName}',
							groupId: ${themeDisplay.getScopeGroupId()}
						}
					},
					uploadFile: '${importCSVURL}&ticketKey=${ticket.getKey()}'
				}
			);
	
			liferayUpload._uploader.on(
				'alluploadscomplete',
				function(event) {
					console.log(event);
					toggleContinueButton();
				}
			);
	
			Liferay.on(
				'tempFileRemoved',
				function(event) {
					toggleContinueButton();
				}
			);
	
			function toggleContinueButton() {
				var lfrDynamicUploader = liferayUpload.get('boundingBox').ancestor('.lfr-dynamic-uploader');
				var uploadedFiles = liferayUpload._fileListContent.all('.upload-file.upload-complete');
	
				if (uploadedFiles.size() == 1) {
					lfrDynamicUploader.removeClass('hide-dialog-footer');
					
				}
				else {
					lfrDynamicUploader.addClass('hide-dialog-footer');
				}
			}
		</aui:script>
	</aui:form>
	
	<aui:script sandbox="<%= true %>">
		$('#<portlet:namespace />continueButton').on(
			'click',
			function(event) {
				event.preventDefault();
				$('#<portlet:namespace />continueButton').prop("disabled", true);
				
				$('#<portlet:namespace />fm1').ajaxSubmit(
					{
						success: function(responseData) {
							console.log(responseData);
							$('#<portlet:namespace />exportImportOptions').html(responseData);
						}
					}
				);
			}
		);
	</aui:script>
</div>
