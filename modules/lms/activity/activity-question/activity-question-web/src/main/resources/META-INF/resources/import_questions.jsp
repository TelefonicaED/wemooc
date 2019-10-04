<%@page import="com.liferay.portal.kernel.upload.UploadServletRequestConfigurationHelperUtil"%>
<%@ include file="init.jsp" %>

<c:choose>
	<c:when test="${empty result }">
		<aui:form method="post" name="fm" action="${importQuestionsURL}">
		
			<liferay-ui:message key="questions.import-questions.title" arguments="${importType }"/>
			
			<div class="container-fluid-1280">
				<div class="lfr-upload-container" id="<portlet:namespace />fileUpload"></div>
			</div>
			
			<aui:button-row>
				<aui:button name="importButton" type="submit" value="import" />
			</aui:button-row>	
			
			<aui:script use="liferay-upload">
				var liferayUpload = new Liferay.Upload(
					{
						boundingBox: '#<portlet:namespace />fileUpload',
						decimalSeparator: '${decimalFormatSymbols.decimalSeparator}',
						deleteFile: '${deleteFileURL}&ticketKey=${ticket.getKey()}',
						fileDescription: '${importType}',
						maxFileSize: '<%= UploadServletRequestConfigurationHelperUtil.getMaxSize() %> B',
						metadataContainer: '#<portlet:namespace />commonFileMetadataContainer',
						metadataExplanationContainer: '#<portlet:namespace />metadataExplanationContainer',
						multipleFiles: false,
						namespace: '<portlet:namespace />',
						'strings.dropFileText': '<liferay-ui:message key="drop-a-x-file-here-to-import" arguments="${importType }"/>',
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
			
					if(lfrDynamicUploader !== null){
						if (uploadedFiles.size() == 1) {
							lfrDynamicUploader.removeClass('hide-dialog-footer');
							
						}
						else {
							lfrDynamicUploader.addClass('hide-dialog-footer');
						}
					}
				}
			</aui:script>
		
		</aui:form>
	</c:when>
	<c:otherwise>
		<liferay-ui:success message="questions.import.success" key="importQuestions" />
		<liferay-ui:error message="questions.import.error" key="importQuestions" />
		<div class="hide" id="${renderResponse.namespace }error_questions">
		
		</div>
		<script>
			var result = JSON.parse('${result}');
			if(result.result != 1){
				//Mostramos los errores
				var errorQuestions = $('#<portlet:namespace />}error_questions');
				for(error in result.errors){
					$.each( error, function( key, value ) {
						errorQuestions.append("<div class='row'><div class='col-md-2'>" + key + "</div><div class='col-md-10'>" + value + "</div></div>");
					});
					
				}
			}else{
				Liferay.Util.getWindow().hide();
			}
			Liferay.Util.getOpener().refreshQuestions(result.questionIds);
		</script>
		
	</c:otherwise>
</c:choose>