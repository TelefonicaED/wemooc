<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="com.liferay.portal.kernel.util.UnicodeFormatter"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.util.Constants" %>

<liferay-ui:error key="campos-necesarios-vacios" message="learning-activity.p2p.steps.upload-activity.empty-fields" />
<liferay-ui:error key="error-subir-p2p" message="learning-activity.p2p.steps.upload-activity.error" />
<liferay-ui:error key="p2ptaskactivity-error-file-type" message="learning-activity.p2p.error.file.type" />
<liferay-ui:error key="p2ptaskactivity-error-file-name" message="learning-activity.p2p.error.file.name" />
<liferay-ui:error key="p2ptaskactivity-error-file" message="learning-activity.p2p.error.file" />
<%
String cssLinkTabletClassP2PUpload="";
if(isTablet){
	cssLinkTabletClassP2PUpload="tablet-link";
}
String textCorrection = "";
boolean uploadCorrect = ParamUtil.getBoolean(request, "uploadCorrect", false);

Date now = new Date();
boolean isDateExpired = now.after(p2pActivityType.getUploadDate()); 
%>

<!-- Start PopUp confirmation -->
<div id="<%= renderResponse.getNamespace() %>p2pconfirmation" class="hide">
	<div class="contDesc">
		<p><label for="${renderResponse.getNamespace()}contentFile"><liferay-ui:message key="learning-activity.p2p.steps.upload-activity.file-name" />:</label></p> 
		<p><span id="${renderResponse.getNamespace()}contentFile"></span></p>
	</div>
	<div class="contDesc">
		<p><label for="${renderResponse.getNamespace()}contentDescription"><liferay-ui:message key="learning-activity.p2p.steps.upload-activity.description" />:</label></p> 
		<p><span id="${renderResponse.getNamespace()}contentDescription"></span></p>
	</div>
	<aui:button-row>
		<aui:button type="button" onclick="${renderResponse.getNamespace()}closeConfirmation()" class="button-cancel" value="cancel" />
		<aui:button type="button" name="buttonSendP2P" cssClass="btn-primary" value="learning-activity.p2p.send" onclick="${renderResponse.getNamespace()}commitForm()" />
	</aui:button-row>
</div>
<!-- End PopUp confrimation -->

<div id="<%= renderResponse.getNamespace() %>p2puploaded" class="hide">
	<liferay-ui:message key="learning-activity.p2p.steps.upload-activity.uploaded" />
</div>


<script type="text/javascript">
<!--
	Liferay.provide(
        window,
        '<portlet:namespace />checkDataform',
        function() {
			
			var descrip = window.<portlet:namespace />descriptionContent.getHTML();
			
			if(descrip == "" || descrip == "<%=StringEscapeUtils.unescapeHtml(textCorrection)%>"){
				alert(Liferay.Language.get("learning-activity.p2p.description.empty"));
				return false;
			}
			if($('#<portlet:namespace />fileName').val() == "" && <%=!p2pActivityType.getFileOptional()%> ){
				alert(Liferay.Language.get("learning-activity.p2p.error.file.empty"));
				return false;
			}
			<portlet:namespace />openPopUp();
        }
    );
	
	Liferay.provide(
	        window,
	        '<portlet:namespace />openPopUp',
	        function() {
				
				var fileName = $("#<portlet:namespace />fileName").val();
				
				var pos = fileName.lastIndexOf("\\");
				if (pos > 0) {
					fileName = fileName.substring(pos+1);
				}
			
				//Start opening popUp			
				var <portlet:namespace />p2pconfirmationBody = $('#<portlet:namespace />p2pconfirmation').html();
				
				if(window.<portlet:namespace />p2pconfirmationpopup){
					window.<portlet:namespace />p2pconfirmationpopup.show();
				}else{
					window.<portlet:namespace />p2pconfirmationpopup = Liferay.Util.Window.getWindow(
							{
								dialog: {
									modal: true,
									resizable: false,
									width: "auto",
									heigth: "auto",
									centered: true,
									bodyContent: <portlet:namespace />p2pconfirmationBody
								},
								id: '<portlet:namespace />showp2pconfirmation',
								title: Liferay.Language.get('learning-activity.p2p.steps.upload-activity.confirmation.title'),
								
							}
						).render();
				}
				
				
				$("#<portlet:namespace />contentFile").html(fileName);
				$("#<portlet:namespace />contentDescription").html(window.<portlet:namespace />descriptionContent.getHTML());
	        },
	        ['liferay-util-window']
	    );
	Liferay.provide(
        window,
        '<portlet:namespace />commitForm',
        function() {
			$("#<portlet:namespace />buttonSendP2P").attr("disabled", "disabled");
			$("#<portlet:namespace />fm").submit();
        }
    );
    
	
	Liferay.provide(
	        window,
	        '<portlet:namespace />openConfirmation',
	        function() {
				
				var <portlet:namespace />p2puploadedBody = $('#<portlet:namespace />p2puploaded').html();

				if(window.<portlet:namespace />p2puploadedpopup){
					window.<portlet:namespace />p2puploadedpopup.show();
				}else{
					window.<portlet:namespace />p2puploadedpopup = Liferay.Util.Window.getWindow(
							{
								dialog: {
									modal: true,
									resizable: false,
									width: "auto",
									heigth: "auto",
									centered: true,
									bodyContent: <portlet:namespace />p2puploadedBody
								},
								id: '<portlet:namespace />showp2puploaded',
								title: Liferay.Language.get('learning-activity.p2p.steps.upload-activity.uploaded.title'),
								
							}
						).render();
				}
				
				
	        },
	        ['aui-dialog']
	    );
	
	Liferay.provide(
	        window,
	        '<portlet:namespace />closeConfirmation',
	        function() {
	        	window.<portlet:namespace />p2pconfirmationpopup.hide();
	        }
	    );
	
	Liferay.provide(
	        window,
	        '<portlet:namespace />closeForm',
	        function() {
				window.<portlet:namespace />p2puploadedpopup.hide();
	        }
	    );
	-->
</script>



<%if(myP2PActivity != null){
	
	Date dateDel = myP2PActivity.getDate();
	textCorrection = myP2PActivity.getDescription();
	%>
	
	<label class="aui-field-label" for="${renderResponse.getNamespace()}textCorrection"> <liferay-ui:message key="learning-activity.p2p.steps.upload-activity.description" /> </label>
	<div class="container-textarea" id="${renderResponse.getNamespace()}textCorrection">
		<%=textCorrection %>
	</div>
	
	<%
		if(myP2PActivity.getFileEntryId() != 0){
			
			FileEntry dlfile = null;
			FileVersion fileVersion = null;
			String urlFile = "";
			int size = 0;
			int sizeKb = 0;
			
			try{
				dlfile = DLAppLocalServiceUtil.getFileEntry(myP2PActivity.getFileEntryId());
				fileVersion = dlfile.getFileVersion();
				urlFile = DLUtil.getDownloadURL(dlfile, fileVersion, themeDisplay, "");
				size = Integer.parseInt(String.valueOf(dlfile.getSize()));
				sizeKb = size/1024; //Lo paso a Kilobytes%>
				
				<div class="doc_descarga">
					<span><%=dlfile.getTitle()%>&nbsp;(<%= sizeKb%> Kb)&nbsp;</span>
					<a href="<%=urlFile%>" class="<%=cssLinkTabletClassP2PUpload %>" target="_blank"><liferay-ui:message key="download" /></a>
				</div>
			<%}catch(Exception e){
				e.printStackTrace();
			}
		}
}
//Si ha pasado la fecha de entrega.
else if(isDateExpired){
%>
	<div class="alert alert-info">
		<liferay-ui:message key="learning-activity.p2p.date-expire.alert" arguments="<%=activity.getTitle(themeDisplay.getLocale()) %>"/>
	</div>

<%} else {%>
	<aui:form name="fm" action="${addP2PActivityURL}" method="POST" enctype="multipart/form-data" >

		<div class="p2p-comments">
			<label for="<portlet:namespace />descriptionContent"><liferay-ui:message key="learning-activity.p2p.write-your-comments" /></label>
			<liferay-editor:editor
				contents='' 
				editorName='alloyeditor'
				name='descriptionContent'
				required="<%= true %>" 
				onChangeMethod="OnChangeEditor"
			/>
			<aui:input name="description" type="hidden" value="" />
		</div>
		
		<liferay-ui:error key="p2ptaskactivity-error-file-size" message="learning-activity.p2p.steps.upload-activity.error.file.size" />
		
		<div class="container-file">
			<label for="<portlet:namespace />fileName">
				<c:if test="<%=p2pActivityType.getFileOptional() %>">
					<liferay-ui:message key="learning-activity.p2p.file.optional" />
				</c:if>
				<c:if test="<%=!p2pActivityType.getFileOptional() %>">
					<liferay-ui:message key="learning-activity.p2p.file.required" />
				</c:if>
			</label>
			<aui:input name="fileName" label="" type="file" />
		</div>
		<aui:button-row>
			<aui:button type="button" value="learning-activity.p2p.send-task" name="checkDataForm" onclick="${renderResponse.getNamespace()}checkDataform()" />
		</aui:button-row>
	</aui:form>
<%	}%>
<%
if(uploadCorrect){
	uploadCorrect=false;
	request.setAttribute("uploadCorrect", uploadCorrect);
	renderResponse.setProperty("clear-request-parameters", Boolean.TRUE.toString());%>
		<script>
			$( document ).ready(function() {
				<portlet:namespace />openConfirmation();
			});	
		</script>
<%}%>

<aui:script>
	function <portlet:namespace />OnChangeEditor(html) {
		$('#<portlet:namespace />description').val(html);
	}
</aui:script>
