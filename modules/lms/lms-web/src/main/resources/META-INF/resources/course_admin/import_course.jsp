<%@page import="com.liferay.portal.kernel.model.User"%>
<%@page import="com.liferay.portal.kernel.service.ServiceContext"%>
<%@page import="com.liferay.portal.kernel.model.TicketConstants"%>
<%@page import="com.liferay.portal.kernel.service.TicketLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.model.Ticket"%>
<%@page import="com.liferay.portal.kernel.util.Time"%>
<%@page import="com.liferay.portal.util.PropsValues"%>
<%@page import="java.util.Date"%>
<%@page import="com.liferay.exportimport.kernel.lar.ExportImportHelper"%>
<%@page import="com.liferay.portal.kernel.util.HtmlUtil"%>
<%@page import="com.liferay.portal.kernel.upload.UploadServletRequestConfigurationHelperUtil"%>
<%@page import="com.liferay.portal.kernel.util.StringUtil"%>
<%@page import="com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil"%>
<%@page import="com.liferay.document.library.configuration.DLConfiguration"%>
<%@page import="java.text.DecimalFormatSymbols"%>
<%@page import="com.liferay.portal.kernel.model.Portlet"%>
<%@page import="com.liferay.portal.kernel.service.PortletLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.ted.lms.service.CourseLocalServiceUtil"%>
<%@page import="com.ted.lms.model.Course"%>
<%@page import="com.liferay.exportimport.kernel.lar.PortletDataHandler"%>
<%@page import="com.liferay.exportimport.kernel.lar.UserIdStrategy"%>
<%@page import="com.liferay.exportimport.kernel.lar.PortletDataHandlerKeys"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@ include file="../init.jsp" %>

<%String portletResource = ParamUtil.getString(request, "portletResource");

Portlet selPortlet = PortletLocalServiceUtil.getPortletById(themeDisplay.getCompanyId(), portletResource);
DecimalFormatSymbols decimalFormatSymbols = DecimalFormatSymbols.getInstance(locale);
DLConfiguration dlConfiguration = ConfigurationProviderUtil.getSystemConfiguration(DLConfiguration.class);
Date expirationDate = new Date(System.currentTimeMillis() + PropsValues.SESSION_TIMEOUT * Time.MINUTE);

Ticket ticket = TicketLocalServiceUtil.addTicket(user.getCompanyId(), User.class.getName(), user.getUserId(), TicketConstants.TYPE_IMPERSONATE, null, expirationDate, new ServiceContext());

Course course = CourseLocalServiceUtil.getCourse(ParamUtil.getLong(renderRequest, "courseId")); %>

<liferay-ui:header title="${course.getTitle(themeDisplay.locale) }" backLabel="back" backURL="${redirect }" />

<c:choose>
	<c:when test="${empty backgroundTaskId || backgroundTaskId == 0 }">

		<portlet:actionURL name="/courses/import_course" var="importCourseActionURL"/>
		
		<portlet:renderURL var="importCourseRenderURL">
			<portlet:param name="mvcRenderCommandName" value="/courses/import_course" />
			<portlet:param name="redirect" value="${redirect }" />
			<portlet:param name="courseId" value="${course.courseId }" />
		</portlet:renderURL>
		
		<aui:form action="${importCourseActionURL }" cssClass="lfr-export-dialog" method="post" name="fm">
			<aui:input name="redirect" type="hidden" value="${importCourseRenderURL }" />
			<aui:input name="courseId" type="hidden" value="${course.courseId }" />
			<aui:input name="<%=Constants.CMD %>" type="hidden" value="<%=Constants.IMPORT %>" />
			
			<div class="lfr-dynamic-uploader hide-dialog-footer">
				<div class="container-fluid-1280">
					<div class="lfr-upload-container" id="<portlet:namespace />fileUpload"></div>
				</div>
			</div>
			
			<div class="export-dialog-tree">
				<div class="container-fluid-1280">
					<aui:fieldset-group markupView="lexicon">
		
						<liferay-staging:deletions
							cmd="<%= Constants.IMPORT %>"
						/>
		
						<liferay-staging:permissions
							action="<%= Constants.IMPORT %>"
							descriptionCSSClass="permissions-description"
							global="false"
							labelCSSClass="permissions-label"
						/>
		
						<aui:fieldset collapsed="<%= false %>" collapsible="<%= true %>" cssClass="options-group" label="update-data">
		
							<%
							String taglibMirrorLabel = LanguageUtil.get(request, "mirror") + ": <span style='font-weight: normal'>" + LanguageUtil.get(request, "import-data-strategy-mirror-help") + "</span>";
							%>
		
							<aui:input checked="<%= true %>" data-name='<%= LanguageUtil.get(request, "mirror") %>' id="mirror" label="<%= taglibMirrorLabel %>" name="<%= PortletDataHandlerKeys.DATA_STRATEGY %>" type="radio" value="<%= PortletDataHandlerKeys.DATA_STRATEGY_MIRROR %>" />
		
							<%
							String taglibMirrorWithOverwritingLabel = LanguageUtil.get(request, "mirror-with-overwriting") + ": <span style='font-weight: normal'>" + LanguageUtil.get(request, "import-data-strategy-mirror-with-overwriting-help" ) + "</span>";
							%>
		
							<aui:input data-name='<%= LanguageUtil.get(request, "mirror-with-overwriting") %>' id="mirrorWithOverwriting" label="<%= taglibMirrorWithOverwritingLabel %>" name="<%= PortletDataHandlerKeys.DATA_STRATEGY %>" type="radio" value="<%= PortletDataHandlerKeys.DATA_STRATEGY_MIRROR_OVERWRITE %>" />
		
							<%
							String taglibCopyAsNewLabel = LanguageUtil.get(request, "copy-as-new") + ": <span style='font-weight: normal'>" + LanguageUtil.get(request, "import-data-strategy-copy-as-new-help") + "</span>";
							%>
		
							<aui:input data-name='<%= LanguageUtil.get(request, "copy-as-new") %>' id="copyAsNew" label="<%= taglibCopyAsNewLabel %>" name="<%= PortletDataHandlerKeys.DATA_STRATEGY %>" type="radio" value="<%= PortletDataHandlerKeys.DATA_STRATEGY_COPY_AS_NEW %>" />
						</aui:fieldset>
		
						<aui:fieldset collapsed="<%= false %>" collapsible="<%= true %>" cssClass="options-group" label="authorship-of-the-content">
		
							<%
							String taglibUseTheOriginalAuthorLabel = LanguageUtil.get(request, "use-the-original-author") + ": <span style='font-weight: normal'>" + LanguageUtil.get(request, "use-the-original-author-help") + "</span>";
							%>
		
							<aui:input checked="<%= true %>" data-name='<%= LanguageUtil.get(request, "use-the-original-author") %>' id="currentUserId" label="<%= taglibUseTheOriginalAuthorLabel %>" name="<%= PortletDataHandlerKeys.USER_ID_STRATEGY %>" type="radio" value="<%= UserIdStrategy.CURRENT_USER_ID %>" />
		
							<%
							String taglibUseTheCurrentUserAsAuthorLabel = LanguageUtil.get(request, "use-the-current-user-as-author") + ": <span style='font-weight: normal'>" + LanguageUtil.get(request, "use-the-current-user-as-author-help") + "</span>";
							%>
		
							<aui:input data-name='<%= LanguageUtil.get(request, "always-use-my-user-id") %>' id="alwaysCurrentUserId" label="<%= taglibUseTheCurrentUserAsAuthorLabel %>" name="<%= PortletDataHandlerKeys.USER_ID_STRATEGY %>" type="radio" value="<%= UserIdStrategy.ALWAYS_CURRENT_USER_ID %>" />
						</aui:fieldset>
					</aui:fieldset-group>
				</div>
			</div>
			<aui:script use="liferay-upload">
				var liferayUpload = new Liferay.Upload(
					{
						boundingBox: '#<portlet:namespace />fileUpload',
						decimalSeparator: '<%= decimalFormatSymbols.getDecimalSeparator() %>',
						deleteFile: '<liferay-portlet:actionURL doAsUserId="<%= user.getUserId() %>" name="/courses/import_course"><portlet:param name="courseId" value="${course.courseId}" /><portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE_TEMP %>" /><portlet:param name="redirect" value="${redirect}" /><portlet:param name="portletResource" value="<%= portletResource %>" /></liferay-portlet:actionURL>&ticketKey=<%= ticket.getKey() %>',
						fileDescription: '<%= StringUtil.merge(dlConfiguration.fileExtensions()) %>',
						maxFileSize: '<%= UploadServletRequestConfigurationHelperUtil.getMaxSize() %> B',
						metadataContainer: '#<portlet:namespace />commonFileMetadataContainer',
						metadataExplanationContainer: '#<portlet:namespace />metadataExplanationContainer',
						multipleFiles: false,
						namespace: '<portlet:namespace />',
						'strings.dropFileText': '<liferay-ui:message key="drop-a-lar-file-here-to-import" />',
						'strings.fileCannotBeSavedText': '<liferay-ui:message key="the-file-x-cannot-be-imported" />',
						'strings.pendingFileText': '<liferay-ui:message key="this-file-was-previously-uploaded-but-not-actually-imported" />',
						'strings.uploadsCompleteText': '<liferay-ui:message key="the-file-is-ready-to-be-imported" />',
						tempFileURL: {
							method: Liferay.Service.bind('/layout/get-temp-file-names'),
							params: {
								folderName: '<%= HtmlUtil.escapeJS(ExportImportHelper.TEMP_FOLDER_NAME + selPortlet.getPortletId()) %>',
								groupId: <%= themeDisplay.getScopeGroupId() %>
							}
						},
						uploadFile: '<liferay-portlet:actionURL doAsUserId="<%= user.getUserId() %>" name="/courses/import_course"><portlet:param name="courseId" value="${course.courseId}" /><portlet:param name="<%= Constants.CMD %>" value="<%= Constants.ADD_TEMP %>" /><portlet:param name="redirect" value="${redirect}" /><portlet:param name="portletResource" value="<%= portletResource %>" /></liferay-portlet:actionURL>&ticketKey=<%= ticket.getKey() %>'
					}
				);
		
			</aui:script>
		
			<aui:button-row>
				<aui:button type="submit" value="import" />
			</aui:button-row>
		</aui:form>
	</c:when>
	<c:otherwise>
		<liferay-portlet:resourceURL copyCurrentRenderParameters="false" id="/courses/import_course" var="importPortletURL">
			<portlet:param name="backgroundTaskId" value="${backgroundTaskId }" />
		</liferay-portlet:resourceURL>
		
		<div id="<portlet:namespace />generating_report" >
			<div class="message_generating_report"><liferay-ui:message key="generatingreport"/></div>
		</div>
		<div id="<portlet:namespace />results" class="hide">
		
		</div>
		<script>
			function <portlet:namespace />readThreadState(){
				$.ajax({
					dataType: 'json',
					url: '${importPortletURL}',
				    cache:false,
					success: function(data){
						if(data){						
					    	if(!data.finished){		
					    		setTimeout(<portlet:namespace />readThreadState,2000);
					    	}else{	
					    		console.log("hemos terminado, los resultados son: " + data.results);
								$('#<portlet:namespace />generating_report').addClass("hide");
								var results = "";
								if(data.SUCCESSFUL){
									results = "<div class='alert alert-success'>" + Liferay.Language.get("import.success") + "</div>";
								}else{
									results += "<div class='alert alert-error'>";
									results += data.statusMessage;
									results += "</div>";
								}
								
								$('#<portlet:namespace />results').append(results);
								$('#<portlet:namespace />results').removeClass("hide");
					    	}
						}else{
							alert("Error en el readThreadState");
						}
					},
					error: function(){
						alert("error");
						$('#<portlet:namespace />generating_report').addClass("hide");
					}
				});		
			}	
			
			<portlet:namespace />readThreadState();
		</script>
	</c:otherwise>
</c:choose>