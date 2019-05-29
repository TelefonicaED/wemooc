<%@page import="com.ted.lms.learning.activity.online.web.internal.util.OnlinePrefsPropsValues"%>
<%@page import="com.liferay.portal.kernel.util.StringUtil"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.ted.lms.learning.activity.online.web.internal.exception.OnlineFileEntryExtensionException"%>
<%@page import="com.ted.lms.learning.activity.online.web.internal.exception.OnlineFileEntrySizeException"%>
<%@page import="com.liferay.portal.kernel.util.TextFormatter"%>
<%@page import="com.liferay.portal.kernel.util.HtmlUtil"%>
<%@page import="com.liferay.portal.kernel.workflow.WorkflowConstants"%>
<%@page import="com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil"%>
<%@page import="com.liferay.portal.kernel.repository.model.FileEntry"%>
<%@page import="java.util.List"%>
<%@ include file="init.jsp" %>

<%List<FileEntry> documents = (List<FileEntry>)request.getAttribute("documents"); %>

<c:if test="<%=documents != null && documents.size() > 0%>">
	<div class="container_files">
		<%for(FileEntry document: documents){ %>
			<div class="row_file">
				<%
				String rowURL = PortletFileRepositoryUtil.getDownloadPortletFileEntryURL(themeDisplay, document, "status=" + WorkflowConstants.STATUS_APPROVED);
				%>

				<liferay-ui:icon
					iconCssClass="icon-paper-clip"
					label="<%= true %>"
					message='<%= HtmlUtil.escape(document.getTitle()) + " (" + TextFormatter.formatStorageSize(document.getSize(), themeDisplay.getLocale()) + ")" %>'
					method="get"
					url="<%= rowURL %>"
				/>
			</div>
		<%} %>
	</div>
</c:if>

<c:choose>
	<c:when test="${empty result || empty result.endDate }">
		
		<aui:form name="fm" action="${saveActivityURL }"  method="post" enctype="multipart/form-data" >
		
			<liferay-ui:error exception="<%= OnlineFileEntryExtensionException.class %>">
				<liferay-ui:message key="learning-activity.online.file.document-extensions" /> <%= StringUtil.merge(OnlinePrefsPropsValues.getOnlineFileExtensions(themeDisplay.getCompanyId()), StringPool.COMMA_AND_SPACE) %>.
			</liferay-ui:error>

			<liferay-ui:error exception="<%= OnlineFileEntrySizeException.class %>">
				<liferay-ui:message arguments="<%= TextFormatter.formatStorageSize(OnlinePrefsPropsValues.getOnlineFileMaxSize(themeDisplay.getCompanyId()), themeDisplay.getLocale()) %>" key="please-enter-a-file-with-a-valid-file-size-no-larger-than-x" translateArguments="<%= false %>" />
			</liferay-ui:error>
		
			<c:choose>
				<c:when test="${onlineActivityType.richText }">
					<div class="online-description">
						<label for="<portlet:namespace />descriptionContent"><liferay-ui:message key="learning-activity.online.text" /></label>
						<liferay-editor:editor
							contents='${text }' 
							editorName='alloyeditor'
							name='descriptionContent'
							required="<%= true %>" 
							onChangeMethod="OnChangeEditor"
						/>
						<aui:input name="description" type="hidden" value="${text }" />
					</div>
					<aui:script>
						function <portlet:namespace />OnChangeEditor(html) {
							$('#<portlet:namespace />description').val(html);
						}
					</aui:script>
				</c:when>
				<c:otherwise>
					<aui:input type="textarea" cols="100" rows="5" name="description" label="learning-activity.online.text" value='${text }'/>
				</c:otherwise>
			</c:choose>
			
			<c:if test="${onlineActivityType.includeFile }">
			
				<div class="container-file">
					<label for="<portlet:namespace />fileName">
						<liferay-ui:message key="learning-activity.online.file" />
					</label>
					<aui:input name="fileName" label="" type="file" />
				</div>
			
				 <c:if test="${not empty urlFile }">	
					<aui:a href="${urlFile }" >${titleFile}</aui:a>
				</c:if>
			</c:if>
			<aui:button-row>
				<aui:button type="submit" value="save"/>
			</aui:button-row>
		</aui:form>
		<c:if test="${not empty result }">
			<span><liferay-ui:message key="learning-activity.online.not-qualificated-activity" /></span>
		</c:if>
	</c:when>
	<c:otherwise>
		<div> 
			<p>
				<a href="javascript:<portlet:namespace />showPopupGradesStudent();">
					<liferay-ui:message key="learning-activity.online.view-last" />
				</a>
			</p>
			<p><liferay-ui:message key="learning-activity.your-result" />${userResult}</p>
			
			<c:choose>
				<c:when test="${result.passed }">
					<p><liferay-ui:message key="learning-activity.your-result.pass" /> </p>
				</c:when>
				<c:otherwise>
					<p><liferay-ui:message key="learning-activity.your-result.failed" /> </p>
				</c:otherwise>
			</c:choose>
			<c:if test="${not empty result.comments }">
				<span><liferay-ui:message key="learning-activity.comment-teacher" /></span>
				<span>${result.comments}</span>
			</c:if>
		</div>
		<script type="text/javascript">
		    function <portlet:namespace />showPopupGradesStudent(){
		
		    	AUI().use('liferay-portlet-url,liferay-util-window', function(A){
		    		var uri = '${showPopupGradesURL}';
		    		
		    		window.<portlet:namespace />popupGrades = Liferay.Util.Window.getWindow({
		    			dialog: {
		    				centered: true,
		    	            modal: true,
		    	            width: "auto",
		    				heigth: "auto",
		    	            destroyOnHide: true
		    			},
		    			id:'<portlet:namespace />showPopupGrades',
		                title: Liferay.Language.get("learning-activity.online.view-last"),
		                uri: uri
		            }).render();
		    		window.<portlet:namespace />popupGrades.show(); 
				});
		    }
		</script>
	</c:otherwise>
</c:choose>