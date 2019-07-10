<%@page import="com.liferay.portal.kernel.backgroundtask.BackgroundTaskConstants"%>
<%@page import="com.liferay.exportimport.kernel.lar.PortletDataHandlerKeys"%>
<%@ include file="../init.jsp" %>
<portlet:resourceURL id="/courses/export_course" var="exportURL">
	<portlet:param name="courseId" value="${course.courseId }" />
</portlet:resourceURL>

<portlet:renderURL var="exportCourseURL">
	<portlet:param name="mvcRenderCommandName" value="/courses/export_course" />
	<portlet:param name="redirect" value="${redirect }" />
	<portlet:param name="courseId" value="${course.courseId }" />
</portlet:renderURL>

<liferay-ui:header title="${course.getTitle(themeDisplay.locale) }" backLabel="back" backURL="${redirect }" />

<aui:nav-bar cssClass="navbar-collapse-absolute" markupView="lexicon">
	<h3><liferay-ui:message key="course-admin.export-course"/></h3>
</aui:nav-bar>

<div class="alert alert-info hide" id="${renderResponse.namespace}export_course_in_progress">
	<liferay-ui:message key="course-admin.export-course.in-progress"/>
</div>
<div class="alert alert-error hide" id="${renderResponse.namespace}export_course_error">
</div>
<div id="${renderResponse.namespace}export_course_success" class="hide">
	<div class="msg-success" >
		<liferay-ui:message key="course-admin.export-course.success"/>
	</div>
	<aui:button-row>
		<aui:button value="course-admin.export-course.return-export" onClick="${exportCourseURL}" />
	</aui:button-row>
</div>
<div class="container" id="${renderResponse.namespace}formExportCourse">
	<div class="row">
		<div class="col-md-8">
			<aui:input autoFocus="true" label="course-admin.export-course.file-name" name="fileName" type="text" value="${fileName }" 
					helpMessage="course-admin.export-course.file-name.help-message" suffix=".lar">
				<aui:validator name="required" />
			</aui:input>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<aui:input name="<%=PortletDataHandlerKeys.PERMISSIONS %>" label="course-admin.export-course.permissions" helpMessage="course-admin.export-course.permissions.help-message" type="checkbox"/>
		</div>
	</div>
	
	<aui:button-row>
		<aui:button value="course-admin.export-course" onClick="${renderResponse.namespace}exportCourse()" name="exportCourseButton"/>
	</aui:button-row>

</div>

<script>
function <portlet:namespace/>exportCourse(){

	$('#<portlet:namespace/>exportCourseButton').prop("disabled", true);
	$('#<portlet:namespace/>export_course_in_progress').removeClass("hide");
	$('#<portlet:namespace/>formExportCourse').addClass("hide");
	
	var data = Liferay.Util.ns(
			'<portlet:namespace />',
			{
				fileName: $('#<portlet:namespace/>fileName').val(),
				PERMISSIONS: $('#<portlet:namespace/><%=PortletDataHandlerKeys.PERMISSIONS %>').val()
			}
		);

	$.ajax({
		dataType: 'json',
		url:'${exportURL}',
	    cache:false,
	    data: data,
		success: function(data){
			if(data && data.backgroundTaskId > 0){
				console.log("backgroundTaskId: " + data.backgroundTaskId);
				<portlet:namespace />readThreadState(data.backgroundTaskId);
			}else{
				alert("Error generando el archivo");
				$('#<portlet:namespace />exportCourseButton').prop("disabled", false);
				$('#<portlet:namespace/>export_course_in_progress').addClass("hide");
				$('#<portlet:namespace/>formExportCourse').removeClass("hide");
			}
		},
		error: function(){
			alert("Error al generar el archivo");
			$('#<portlet:namespace />exportCourseButton').prop("disabled", false);
			$('#<portlet:namespace/>export_course_in_progress').addClass("hide");
		}
	});
}

function <portlet:namespace />readThreadState(backgroundTaskId){
	console.log("backgroundTaskId thread: " + backgroundTaskId);
	if(backgroundTaskId > 0){
		
		var data = Liferay.Util.ns(
			'<portlet:namespace />',
			{
				backgroundTaskId: backgroundTaskId
			}
		);
		
		$.ajax({
			dataType: 'json',
			url: '${exportURL}',
		    cache:false,
			data: data,
			success: function(data){
				if(data){						
			    	if(!data.finished){		
			    		setTimeout(<portlet:namespace />readThreadState,2000, backgroundTaskId);
			    	}else{	
			    		console.log("hemos terminado, el fichero es: " + data.fileURL);
						$('#<portlet:namespace />export_course_in_progress').addClass("hide");
						if(data.status != <%=BackgroundTaskConstants.STATUS_SUCCESSFUL%>){
							$('#<portlet:namespace />export_course_error').empty();
							$('#<portlet:namespace />export_course_error').append(data.message);
							$('#<portlet:namespace />export_course_error').removeClass("hide");
						}else{
							$('#<portlet:namespace />export_course_success').removeClass("hide");
							window.location.href = data.fileURL;
						}
			    	}
				}else{
					alert("Error en el readThreadState");
				}
			},
			error: function(){		
			}
		});		
	}
}	

</script>