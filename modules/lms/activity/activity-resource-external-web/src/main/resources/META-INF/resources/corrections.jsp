<%@page import="com.ted.lms.learning.activity.resource.external.ResourceExternalActivityType"%>
<%@page import="com.liferay.portal.kernel.workflow.WorkflowConstants"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.ted.lms.constants.LMSActionKeys"%>
<%@page import="com.ted.lms.security.permission.resource.LMSPermission"%>
<%@page import="com.ted.lms.model.CalificationType"%>
<%@page import="com.ted.lms.service.LearningActivityResultLocalServiceUtil"%>
<%@page import="com.ted.lms.model.LearningActivityResult"%>
<%@page import="com.liferay.portal.kernel.xml.SAXReaderUtil"%>
<%@page import="com.liferay.portal.kernel.xml.Element"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ted.lms.learning.activity.question.service.QuestionLocalServiceUtil"%>
<%@page import="com.ted.lms.service.LearningActivityTryLocalServiceUtil"%>
<%@page import="com.ted.lms.model.LearningActivityTry"%>
<%@page import="com.ted.lms.learning.activity.question.model.Question"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@include file="init.jsp" %>

<liferay-ui:header
	backURL="${backURL }"
	title="learning-activity.resource-external.correct-activity"
/>

<%long actId = (long)request.getAttribute("actId");  
ResourceExternalActivityType resourceExternalActivityType = (ResourceExternalActivityType)request.getAttribute("resourceExternalActivityType");
CalificationType calificationType = (CalificationType)request.getAttribute("calificationType");%>

<liferay-ui:error key="grades.bad-updating" message="learning-activity.resource-external.corrections.bad-updating" />
<liferay-ui:success key="grades.updating" message="learning-activity.resource-external.corrections.correct-saved" />

<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>" var="showPopupActivityURL">
	<portlet:param name="mvcPath" value="${resourceExternalActivityTypeFactory.specificResultsPage}" />
</portlet:renderURL>


<script type="text/javascript">

function <portlet:namespace />showPopupActivity(studentId) {
	
	AUI().use('liferay-portlet-url,liferay-util-window', function(A){
		
		var uri = '${showPopupActivityURL}';
		uri = Liferay.Util.addParams('<portlet:namespace />actId=<%=actId%>', uri) || uri;
		uri = Liferay.Util.addParams('<portlet:namespace />studentId=' + studentId, uri) || uri;
		uri = Liferay.Util.addParams('<portlet:namespace />courseId=${courseId}', uri) || uri;
		
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
					id: '<portlet:namespace />showPopupActivity',
					title: Liferay.Language.get('learning-activity.resource-external.corrections.view-test'),		
					uri: uri
				}
				).render();
		window.<portlet:namespace />popupActivity.show();
  
	});
}

function <portlet:namespace />showPopupGrades(studentId) {
	
	AUI().use('liferay-portlet-url,liferay-util-window', function(A){
		var uri = '${showPopupGradesURL}';
		uri = Liferay.Util.addParams('<portlet:namespace />studentId=' + studentId, uri) || uri;
		
		window.<portlet:namespace />popupGrades = Liferay.Util.Window.getWindow({
			dialog: {
				centered: true,
	            modal: true,
	            width: "auto",
				heigth: "auto",
	            destroyOnHide: true
			},
			id:'<portlet:namespace />showPopupGrades',
            title: Liferay.Language.get("learning-activity.resource-external.corrections.edit-result"),
            uri: uri
        }).render();
		window.<portlet:namespace />popupGrades.show();   
	});
}

</script>

	<aui:form name="searchFm" action="${searchURL }" method="POST">
		<div class="taglib-search-toggle-advanced row">
			<div class="col-md-3"><aui:input type="text" name="firstName" label="first-name" value="${firstName}" /></div>
			<div class="col-md-3"><aui:input type="text" name="lastName" label="last-name" value="${lastName}" /></div>
			<div class="col-md-3"><aui:input type="text" name="screenName" label="screen-name" value="${screenName}" /></div>
			<div class="col-md-3"><aui:input type="text" name="emailAddress" label="email-address" value="${lastName}" /></div>
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
			LearningActivityTry larntry = LearningActivityTryLocalServiceUtil.getLastLearningActivityTry(actId, student.getUserId());
			List<Question> questionList=QuestionLocalServiceUtil.getQuestionsOrder(actId);
			
			List<Question> questions = resourceExternalActivityType.getQuestions();
			
			boolean hasQuestions =  questions!= null && questions.size() > 0;
			
			LearningActivityResult laResult =  LearningActivityResultLocalServiceUtil.getLearningActivityResult(actId, student.getUserId());
			String result= "-";
			String status="status.not-attempted";
			if(laResult != null){
				status="status.incomplete";
				result = calificationType.translate(themeDisplay.getLocale(), laResult.getResult());
				
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

		<liferay-ui:search-container-column-text name="learning-activity.resource-external.corrections.notes">
			<span id="${renderResponse.namespace }result_<%=student.getUserId()%>"><%=result%></span>
		</liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-text name="status">
			<span id="${renderResponse.namespace }status_<%=student.getUserId()%>"><liferay-ui:message key="<%=status %>"/></span>
		</liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-text>
		
			<liferay-ui:icon-menu
				direction="left-side"
				icon="<%= StringPool.BLANK %>"
				markupView="lexicon"
				message="<%= StringPool.BLANK %>"
				showWhenSingleIcon="true"
			>
		
			<%
			if(!status.equals("status.not-attempted")){
 				if(LMSPermission.contains(themeDisplay.getPermissionChecker(), themeDisplay.getScopeGroupId(), LMSActionKeys.VIEW_RESULTS)){%>
 					
 					<liferay-ui:icon url='<%="javascript:"+renderResponse.getNamespace() + "showPopupActivity("+student.getUserId()+");" %>' message="learning-activity.resource-external.corrections.view-questions"/>
 					
 					<%if(status.equals("passed") || status.equals("not-passed") || hasQuestions){
 					%>
		 				<liferay-ui:icon url='<%="javascript:"+renderResponse.getNamespace() + "showPopupGrades("+student.getUserId()+");" %>' message="learning-activity.resource-external.corrections.edit-result"/>
			 		<% }
			 	}
 			} %>
 			</liferay-ui:icon-menu>
		</liferay-ui:search-container-column-text>

	</liferay-ui:search-container-row>
	
	<liferay-ui:search-iterator
		markupView="lexicon"
	/>

</liferay-ui:search-container>

<aui:script use="aui-base">
	window['<portlet:namespace />changeResult'] = function(studentId, result, status) {
		console.log("studentId: " + studentId);
		$('#<portlet:namespace />result_' + studentId).text(result);
		$('#<portlet:namespace />status_' + studentId).text(Liferay.Language.get(status));
	}
</aui:script>
