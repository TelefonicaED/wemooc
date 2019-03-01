<%@page import="com.ted.lms.service.LearningActivityResultLocalServiceUtil"%>
<%@page import="com.ted.lms.model.LearningActivityResult"%>
<%@page import="com.ted.lms.learning.activity.p2p.service.P2PActivityLocalServiceUtil"%>
<%@page import="com.ted.lms.learning.activity.p2p.model.P2PActivity"%>
<%@page import="com.ted.lms.constants.LMSActionKeys"%>
<%@page import="com.ted.lms.security.permission.resource.LearningActivityPermission"%>
<%@page import="com.ted.lms.model.LearningActivityTry"%>
<%@page import="java.util.List"%>
<%@page import="com.ted.lms.service.LearningActivityTryLocalServiceUtil"%>
<%@page import="com.ted.lms.service.LearningActivityLocalServiceUtil"%>
<%@page import="com.ted.lms.model.LearningActivity"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ include file="../../init.jsp" %>

<liferay-ui:success key="p2p-activity-assign-correct" message="p2p-activity-assign-correct" />
<liferay-ui:error key="no-p2p-activity-assign" message="no-p2p-activity-assign" />

<%
long actId=ParamUtil.getLong(request,"actId",0);
System.out.println("jsp actId: " + actId);

LearningActivity activity=LearningActivityLocalServiceUtil.getLearningActivity(actId);	

boolean isTablet = ParamUtil.getBoolean(renderRequest, "isTablet", false);

boolean uploadCorrect = ParamUtil.getBoolean(request,"uploadCorrect", false);
String uploadCorrectString = request.getParameter("uploadCorrect");
String correctionsCompletedString = request.getParameter("correctionsCompleted");

long latId = ParamUtil.getLong(request,"latId",0);

if(latId==0){
	if(LearningActivityTryLocalServiceUtil.getLearningActivityTriesCount(actId, themeDisplay.getUserId())>0){
		List<LearningActivityTry> latList = LearningActivityTryLocalServiceUtil.getLearningActivityTries(actId, themeDisplay.getUserId());
		if(!latList.isEmpty()){
			for(LearningActivityTry lat :latList){
				latId = lat.getLatId();
			}
		}
	}
}%>

<div class="description"><%=activity.getDescription(themeDisplay.getLocale()) %></div>

<%if(LearningActivityPermission.contains(permissionChecker, activity, LMSActionKeys.CORRECT) && isTablet){%>
	
	
	<liferay-portlet:renderURL var="correctionPages">
		<liferay-portlet:param name="actId" value="<%=Long.toString(actId) %>" />
		<liferay-portlet:param name="jspPage" value="/html/p2ptaskactivity/revisions.jsp" />
	</liferay-portlet:renderURL>
	
	<%
			String urlCorrections = "self.location = '"+correctionPages.toString()+"';";
		%>
		<div class="container-buttons lms-valoraciones">
			<aui:button style="margin-top:10px" value="p2ptask-see-corrections" onClick="<%=urlCorrections.toString() %>" type="button" />
		</div>
		<%
	

}else{
%>
	<%
	Long userId = themeDisplay.getUserId();
	P2PActivity myp2pActivity = P2PActivityLocalServiceUtil.getP2PActivity(actId, userId);
	
	request.setAttribute("actId", actId);
	request.setAttribute("latId", latId);
	
	LearningActivityResult learnResult = LearningActivityResultLocalServiceUtil.getLearningActivityResult(actId,themeDisplay.getUserId());
	if(myp2pActivity==null){%>
		<div class="steps">
			<span id="span1" class="selected"><liferay-ui:message key="p2ptask-step1" />&nbsp;>&nbsp;</span>
			<span id="span2"><liferay-ui:message key="p2ptask-step2" />&nbsp;>&nbsp;</span>
			<span id="span3"><liferay-ui:message key="p2ptask-step3" /></span>
		</div>
		<div class="preg_content" id="capa1">
			<jsp:include page="steps/uploadActivity.jsp" >
				<jsp:param value="<%=uploadCorrectString %>" name="uploadCorrect"/>
			</jsp:include>
		</div>
		<div class="preg_content" id="capa2" style="display:none"></div>
		<div class="preg_content" id="capa3" style="display:none"></div>
	<%} else {
		String classCSS2="";
		String classCSS3="";
		String passed="";
		String javascript="";
		Long showRevision = ParamUtil.getLong(request, "showRevision",0);
		if(!learnResult.getPassed()){
			classCSS2="selected";
			if(showRevision==1)
				javascript=renderResponse.getNamespace()+"changeDiv(3);";
			else
				javascript=renderResponse.getNamespace()+"changeDiv(2);";
		}
		else{
			classCSS3="selected";
			javascript=renderResponse.getNamespace()+"changeDiv(3);";
			passed="done";
		}
		
		request.setAttribute("actId", actId);
		request.setAttribute("latId", latId);
		
		%>
		<div class="steps">
			<span id="span1" onclick="<portlet:namespace />changeDiv(1)" class="clicable done"><liferay-ui:message key="p2ptask-step1" />&nbsp;>&nbsp;</span>
			<span id="span2" class="<%=classCSS2 %> clicable <%=passed%>" onclick="<portlet:namespace />changeDiv(2)"><liferay-ui:message key="p2ptask-step2" />&nbsp;>&nbsp;</span>
			<span id="span3" class="<%=classCSS3 %> clicable" onclick="<portlet:namespace />changeDiv(3)"><liferay-ui:message key="p2ptask-step3" /></span>
		</div>
		<div class="preg_content" id="capa1" style="display:none">
			<jsp:include page="steps/uploadActivity.jsp" />
		</div>
		<div class="preg_content" id="capa2" style="display:none">
			<jsp:include page="steps/correctActivities.jsp" >
				<jsp:param value="<%=correctionsCompletedString %>" name="correctionsCompleted"/>
			</jsp:include>
		</div>
		<div class="preg_content" id="capa3" style="display:none">
			<jsp:include page="steps/myCorrections.jsp" />
		</div>
		<script type="text/javascript">
		<%=javascript%>
		</script>
		<%
	}
	%>
	
	<liferay-portlet:renderURL var="correctionPage">
		<liferay-portlet:param name="actId" value="<%=Long.toString(actId) %>" />
		<liferay-portlet:param name="mvcPath" value="/p2p/activity/revisions.jsp" />
	</liferay-portlet:renderURL>
	
	<%
	if(permissionChecker.hasPermission(themeDisplay.getScopeGroupId(),LearningActivity.class.getName() , actId, "CORRECT")){
		String urlCorrection = "self.location = '"+correctionPage.toString()+"';";
		%>
		<div class="container-buttons lms-valoraciones">
			<aui:button style="margin-top:10px" value="p2ptask-see-corrections" onClick="<%=urlCorrection.toString() %>" type="button" />
		</div>
		<%
	}
}
%>