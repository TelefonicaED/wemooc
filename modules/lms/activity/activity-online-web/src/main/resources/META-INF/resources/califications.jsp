<%@page import="java.text.SimpleDateFormat"%>
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
<%@page import="com.ted.lms.service.LearningActivityTryLocalServiceUtil"%>
<%@page import="com.ted.lms.model.LearningActivityTry"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@include file="init.jsp" %>

<%long actId = (long)request.getAttribute("actId");  
SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
dateFormat.setTimeZone(themeDisplay.getTimeZone());%>

<liferay-ui:error key="grades.bad-updating" message="learning-activity.online.corrections.bad-updating" />
<liferay-ui:success key="grades.updating" message="learning-activity.online.corrections.correct-saved" />

<script type="text/javascript">

function <portlet:namespace />showPopupActivity(studentId) {
	
	AUI().use('liferay-portlet-url,liferay-util-window', function(A){
		
		var uri = '${showPopupActivityURL}';
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
					id: '<portlet:namespace />showPopupActivity',
					title: Liferay.Language.get('learning-activity.online.corrections.calification'),		
					uri: uri
				}
				).render();
		window.<portlet:namespace />popupActivity.show();
  
	});
}

</script>

<aui:form name="searchFm" action="${searchURL }" method="POST">
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
		String status="";
		if(laResult != null){
			status="status.not-calification";
			
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
		
		<liferay-ui:search-container-column-text name="learning-activity.online.corrections.date-upload">
			<%=dateFormat.format(laResult.getStartDate()) %>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text name="learning-activity.online.corrections.calification">
			<span id="${renderResponse.namespace }status_<%=student.getUserId()%>"><liferay-ui:message key="<%=status %>"/></span>
		</liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-text>
		
			<liferay-ui:icon-menu
				direction="left-side"
				icon="<%= StringPool.BLANK %>"
				markupView="lexicon"
				message="<%= StringPool.BLANK %>"
			>
		
			<%
			if(status.equals("status.not-calification")){%>
				<liferay-ui:icon url='<%="javascript:"+renderResponse.getNamespace() + "showPopupActivity("+student.getUserId()+");" %>' message="learning-activity.online.corrections.new-calification"/>
			<%}else{%>
				<liferay-ui:icon url='<%="javascript:"+renderResponse.getNamespace() + "showPopupActivity("+student.getUserId()+");" %>' message="learning-activity.online.corrections.edit-calification"/>
			<%}
			%>
 			</liferay-ui:icon-menu>
		</liferay-ui:search-container-column-text>

	</liferay-ui:search-container-row>
	
	<liferay-ui:search-iterator
		markupView="lexicon"
	/>

</liferay-ui:search-container>

<aui:script use="aui-base">
	window['<portlet:namespace />changeResult'] = function(studentId, status) {
		console.log("studentId: " + studentId);
		$('#<portlet:namespace />status_' + studentId).text(Liferay.Language.get(status));
	}
</aui:script>
