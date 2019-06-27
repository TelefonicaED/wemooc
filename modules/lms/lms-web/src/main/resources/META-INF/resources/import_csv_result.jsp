<%@ include file="init.jsp" %>

<liferay-portlet:resourceURL copyCurrentRenderParameters="false" id="/courses/import_members" var="importPortletURL">
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
						results = "<div class='alert alert-info'>" + Liferay.Language.get("import.error") + "</div>";
					}
					
					results += "<div class='results'>";
					if(data.results != ''){
						JSON.parse(data.results, function(key, value) {
							if(key != ""){
								results+="<div class='line'>" + key + "</div>";
								results+="<div class='value'>" + value + "</div>";
							}
						});
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
			$('#<portlet:namespace />generating_report').addClass("hide");
		}
	});		
}	

<portlet:namespace />readThreadState();
</script>