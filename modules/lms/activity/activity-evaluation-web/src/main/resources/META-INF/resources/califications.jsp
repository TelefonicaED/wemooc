<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.ted.lms.service.LearningActivityResultLocalServiceUtil"%>
<%@page import="com.ted.lms.model.LearningActivityResult"%>
<%@page import="com.ted.lms.model.CalificationType"%>
<%@page import="com.ted.lms.constants.LMSActionKeys"%>
<%@page import="com.ted.lms.security.permission.resource.LMSPermission"%>
<%@ include file="init.jsp" %>

<%CalificationType calificationType = (CalificationType)request.getAttribute("calificationType"); 
SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
dateFormat.setTimeZone(themeDisplay.getTimeZone());
long actId = ParamUtil.getLong(request, "actId");%>

<c:if test="${empty evaluationActivityType.firedDate && not empty evaluationActivityType.activities}">

	<div id="<%= renderResponse.getNamespace() %>calculateContents" class="hide">
		<aui:form method="POST" action="${calculateResultURL}" name="calculateFm">
			<p><liferay-ui:message key="learning-activity.evaluation.calculate.confirm" /></p> 
			<aui:button-row>
				<aui:button type="button" onclick="${renderResponse.getNamespace()}closeCalculateContents()" class="button-cancel" value="cancel" />
				<aui:button type="submit" value="learning-activity.evaluation.calculate.accept" />
			</aui:button-row>
		</aui:form>
	</div>
	
	<aui:button value="learning-activity.evaluation.calculate" onClick="javascript:${renderResponse.namespace}confirmCalculate()"/>
	
	<aui:script>
		Liferay.provide(
	        window,
	        '<portlet:namespace />confirmCalculate',
	        function() {
				//Start opening popUp			
				var <portlet:namespace />calculateResultBody = $('#<portlet:namespace />calculateContents').html();
				
				if(window.<portlet:namespace />calculateContentsPopUp){
					window.<portlet:namespace />calculateContentsPopUp.show();
				}else{
					window.<portlet:namespace />calculateContentsPopUp = Liferay.Util.Window.getWindow(
							{
								dialog: {
									modal: true,
									resizable: false,
									width: "auto",
									heigth: "auto",
									centered: true,
									bodyContent: <portlet:namespace />calculateResultBody
								},
								id: '<portlet:namespace />show_calculate_result_confirmation',
								title: Liferay.Language.get('learning-activity.evaluation.calculate'),
								
							}
						).render();
				}
	        },
	        ['liferay-util-window']
	    );
		Liferay.provide(
	        window,
	        '<portlet:namespace />closeCalculateContents',
	        function() {
	        	window.<portlet:namespace />calculateContentsPopUp.hide();
	        }
	    );
	</aui:script>
</c:if>

<c:if test="${not empty evaluationActivityType.firedDate && not empty evaluationActivityType.activities && empty evaluationActivityType.publishDate }">
	<div id="<%= renderResponse.getNamespace() %>publishContents" class="hide">
		<aui:form method="POST" action="${publishResultURL}" name="publishFm">
			<p><liferay-ui:message key="learning-activity.evaluation.publish.confirm" /></p> 
			<aui:button-row>
				<aui:button type="button" onclick="${renderResponse.getNamespace()}closePublishContents()" class="button-cancel" value="cancel" />
				<aui:button type="submit" value="learning-activity.evaluation.publish.accept"  />
			</aui:button-row>
		</aui:form>
	</div>
	
	<aui:button value="learning-activity.evaluation.publish" onClick="javascript:${renderResponse.namespace}confirmPublish()"/>
	
	<aui:script>
		Liferay.provide(
	        window,
	        '<portlet:namespace />confirmPublish',
	        function() {
				//Start opening popUp			
				var <portlet:namespace />publishResultBody = $('#<portlet:namespace />publishContents').html();
				
				if(window.<portlet:namespace />publishContentsPopUp){
					window.<portlet:namespace />publishContentsPopUp.show();
				}else{
					window.<portlet:namespace />publishContentsPopUp = Liferay.Util.Window.getWindow(
							{
								dialog: {
									modal: true,
									resizable: false,
									width: "auto",
									heigth: "auto",
									centered: true,
									bodyContent: <portlet:namespace />publishResultBody
								},
								id: '<portlet:namespace />show_publish_result_confirmation',
								title: Liferay.Language.get('learning-activity.evaluation.publish'),
								
							}
						).render();
				}
	        },
	        ['liferay-util-window']
	    );
		Liferay.provide(
	        window,
	        '<portlet:namespace />closePublishContents',
	        function() {
	        	window.<portlet:namespace />publishContentsPopUp.hide();
	        }
	    );
	</aui:script>
</c:if>

<script>
function <portlet:namespace />showPopupGrades(studentId) {
	
	AUI().use('liferay-portlet-url,liferay-util-window', function(A){
		
		var uri = '${showPopupResultURL}';
		uri = Liferay.Util.addParams('<portlet:namespace />studentId=' + studentId, uri) || uri;
		
		window.<portlet:namespace />popupActivity = Liferay.Util.Window.getWindow(
				{
					dialog: {
						modal: true,
						resizable: false,
						width: "auto",
						heigth: "auto",
						centered: true,
						destroyOnHide: true
					},
					id: '<portlet:namespace />showPopupGrades',
					title: Liferay.Language.get('calification.edit-result'),		
					uri: uri
				}
				).render();
		window.<portlet:namespace />popupActivity.show();
  
	});
}


function <portlet:namespace />showPopupRecalculate(studentId) {
	
	AUI().use('liferay-portlet-url,liferay-util-window', function(A){
		//Start opening popUp
		document.<portlet:namespace />recalculateFm.<portlet:namespace />studentId.value = studentId;
		
		
		if(window.<portlet:namespace />recalculateContentsPopUp){
			window.<portlet:namespace />recalculateContentsPopUp.show();
		}else{
			
			window.<portlet:namespace />recalculateContentsPopUp = Liferay.Util.Window.getWindow(
					{
						dialog: {
							modal: true,
							bodyContent: $('#<portlet:namespace />recalculateContents').html()
						},
						title: Liferay.Language.get('learning-activity.evaluation.recalculate'),
						
					}
				).render();
			window.<portlet:namespace />recalculateContentsPopUp.show();
		}
	});
}
    
Liferay.provide(
    window,
    '<portlet:namespace />closeRecalculateContents',
    function() {
    	window.<portlet:namespace />recalculateContentsPopUp.hide();
    }
);
</script>

<aui:form name="searchFm" action="${searchURL }" method="POST" role="form">
	<div class="row">
		<div class="col-md-6"><aui:input type="text" name="keywords" label="student.search.keywords" value="${keywords}" /></div>
		<div class="col-md-3">
			<aui:select label="status" name="status" >
				<aui:option selected='${empty status}' value=""><liferay-ui:message key="status.all" /></aui:option>
				<aui:option selected='${status == "nocalification"}' value="nocalification"><liferay-ui:message key="status.not-calification" /></aui:option>
				<aui:option selected='${status == "passed" }' value="passed"><liferay-ui:message key="status.passed" /></aui:option>
				<aui:option selected='${status == "failed" }' value="failed"><liferay-ui:message key="status.failed" /></aui:option>
			</aui:select>
		</div>
	</div>
	<aui:button-row><aui:button type="submit" value="search"/></aui:button-row>
</aui:form>

<liferay-ui:search-container
	id="users"
	searchContainer="${searchContainer}"
	var="userSearchContainer"
>
	
	<liferay-ui:search-container-row className="com.liferay.portal.kernel.model.User" keyProperty="userId" modelVar="student">
		<%
		LearningActivityResult laResult =  LearningActivityResultLocalServiceUtil.getLearningActivityResult(actId, student.getUserId());
		
		System.out.println("actId: " + actId + " - userId: " + student.getUserId());
		String result= "";
		String status="status.not-calification";
		if(laResult != null){
			result = calificationType.translate(themeDisplay.getLocale(), laResult.getResult()) + calificationType.getSuffix();
			
			if(laResult.getEndDate()!=null){
				status="status.failed"	;
			}
			if(laResult.isPassed()){
				status="status.passed"	;
			}
		}			
		%>
	
		<liferay-ui:search-container-column-text name="full-name" property="fullName"/>
		
		<liferay-ui:search-container-column-text name="screen-name" property="screenName"/>
		
		<liferay-ui:search-container-column-text name="email-address" property="emailAddress"/>

		<liferay-ui:search-container-column-text name="learning-activity.online.corrections.calification">
			<c:if test='<%=!status.equals("status.not-calification") %>' >
				<span id="${renderResponse.namespace }result_<%=student.getUserId()%>"><%=result %></span> - 
			</c:if>
			<span id="${renderResponse.namespace }status_<%=student.getUserId()%>"><liferay-ui:message key="<%=status %>"/></span>
		</liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-text>
			<c:choose>
				<c:when test="${empty evaluationActivityType.publishDate }">
		 			<liferay-ui:icon-menu
						direction="left-side"
						icon="<%= StringPool.BLANK %>"
						markupView="lexicon"
						message="<%= StringPool.BLANK %>"
						showWhenSingleIcon="true"
					>
					<%
					if(!status.equals("status.not-calification") && LMSPermission.contains(themeDisplay.getPermissionChecker(), themeDisplay.getScopeGroupId(), LMSActionKeys.VIEW_RESULTS)){%>	
		 				<liferay-ui:icon url='<%="javascript:"+renderResponse.getNamespace() + "showPopupRecalculate("+student.getUserId()+");" %>' message="learning-activity.evaluation.recalculate"/>
				 		<liferay-ui:icon url='<%="javascript:"+renderResponse.getNamespace() + "showPopupGrades("+student.getUserId()+");" %>' message="calification.edit-result"/>
					<%} %>
		 			</liferay-ui:icon-menu>
	 			</c:when>
	 			<c:when test="<%=laResult != null && Validator.isNotNull(laResult.getComments()) %>">
	 				<liferay-ui:icon-menu
						direction="left-side"
						icon="<%= StringPool.BLANK %>"
						markupView="lexicon"
						message="<%= StringPool.BLANK %>"
						showWhenSingleIcon="true"
					>
						<%String onClick = "AUI().use('liferay-portlet-url,liferay-util-window', function(A){Liferay.Util.Window.getWindow({dialog: {modal: true,destroyOnHide: true,bodyContent: '"+ laResult.getComments() + "'},title: Liferay.Language.get('comments'),}).render().show();});"; %>
						<liferay-ui:icon message="calification.view-comments" onClick="<%=onClick %>" url="javascript:;"/>
					</liferay-ui:icon-menu>
	 			</c:when>
 			</c:choose>
		</liferay-ui:search-container-column-text>

	</liferay-ui:search-container-row>
	
	<liferay-ui:search-iterator
		markupView="lexicon"
	/>

</liferay-ui:search-container>

<div id="<%= renderResponse.getNamespace() %>recalculateContents" class="hide">
	<aui:form name="recalculateFm" action="${recalculateResultURL}">
		<aui:input name="studentId" type="hidden" value=""/>
		<p><liferay-ui:message key="learning-activity.evaluation.recalculate.confirm" /></p> 
		<aui:button-row>
			<aui:button type="button" onclick="${renderResponse.getNamespace()}closeRecalculateContents()" class="button-cancel" value="cancel" />
			<aui:button type="submit" cssClass="btn-primary" value="learning-activity.evaluation.recalculate.accept" />
		</aui:button-row>
	</aui:form>
</div>

<aui:script use="aui-base liferay-util-window">
	window['<portlet:namespace />changeResult'] = function(studentId, result, status) {
		console.log("studentId: " + studentId);
		$('#<portlet:namespace />result_' + studentId).text(Liferay.Language.get(result));
		$('#<portlet:namespace />status_' + studentId).text(Liferay.Language.get(status));
	}
	

</aui:script>

