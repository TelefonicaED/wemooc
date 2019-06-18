<%@page import="com.ted.lms.model.Course"%>
<%@page import="com.ted.lms.service.CourseLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.service.GroupLocalServiceUtil"%>
<%@page import="com.ted.lms.learning.activity.p2p.constants.P2PConstants"%>
<%@page import="com.ted.lms.registry.LearningActivityTypeFactoryRegistryUtil"%>
<%@page import="java.util.Date"%>
<%@page import="com.ted.lms.learning.activity.p2p.web.constants.P2PPortletConstants"%>
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
<%@page import="com.ted.lms.learning.activity.p2p.web.activity.P2PActivityType"%>
<%@page import="com.ted.lms.learning.activity.p2p.web.activity.P2PActivityTypeFactory"%>
<%@page import="com.liferay.portal.kernel.util.FastDateFormatFactoryUtil" %>
<%@page import="java.text.Format" %>
<%@page import="java.text.DateFormat"%>
<%@page import="com.liferay.document.library.kernel.util.DLUtil"%>
<%@page import="com.liferay.portal.kernel.repository.model.FileVersion"%>
<%@page import="com.liferay.portal.kernel.repository.model.FileEntry"%>
<%@page import="com.liferay.document.library.kernel.service.DLAppLocalServiceUtil"%>
<%@ include file="init.jsp" %>

<%Format dateFormatDate = FastDateFormatFactoryUtil.getDateTime(DateFormat.SHORT, DateFormat.SHORT, locale, timeZone); 

long actId=ParamUtil.getLong(request,"actId",0);
LearningActivity activity=LearningActivityLocalServiceUtil.getLearningActivity(actId);	


boolean isTablet = ParamUtil.getBoolean(renderRequest, "isTablet", false);
String cssLinkTabletClassP2PCorrect="";
if(isTablet){
	cssLinkTabletClassP2PCorrect="tablet-link";
}

P2PActivityTypeFactory p2pActivityTypeFactory = (P2PActivityTypeFactory)LearningActivityTypeFactoryRegistryUtil.getLearningActivityTypeFactoryByType(P2PConstants.TYPE);
P2PActivityType p2pActivityType = p2pActivityTypeFactory.getP2PActivityType(activity);

Course course = CourseLocalServiceUtil.getCourseByGroupCreatedId(themeDisplay.getScopeGroupId());

%>

<div class="description"><%=activity.getDescription(themeDisplay.getLocale()) %></div>

<div class="date-destacado"><liferay-ui:message key="learning-activity.p2p.date-expire" arguments="<%=dateFormatDate.format(p2pActivityType.getUploadDate())%>" /></div>

<%if(LearningActivityPermission.contains(permissionChecker, activity, LMSActionKeys.CORRECT) && isTablet){%>
	<liferay-portlet:renderURL var="correctionPages">
		<liferay-portlet:param name="actId" value="<%=Long.toString(actId) %>" />
		<liferay-portlet:param name="mvcRenderCommandName" value="/activities/p2p/revisions" />
	</liferay-portlet:renderURL>
	
	<%
	String urlCorrections = "self.location = '"+correctionPages.toString()+"';";
	%>
	<div class="container-buttons lms-valoraciones">
		<aui:button value="p2ptask-see-corrections" onClick="<%=urlCorrections.toString() %>" type="button" />
	</div>
<%
}else{
%>
	<%
	P2PActivity myP2PActivity = P2PActivityLocalServiceUtil.getP2PActivity(actId, themeDisplay.getUserId());
	
	LearningActivityResult learnResult = LearningActivityResultLocalServiceUtil.getLearningActivityResult(actId,themeDisplay.getUserId());
	
	long showRevision = ParamUtil.getLong(request, "showRevision",0);
	
	String navigation = P2PPortletConstants.P2P_DEFAULT_NAVIGATION;
	if(myP2PActivity!=null && !learnResult.getPassed() && showRevision == 0){
		navigation = P2PPortletConstants.CORRECT_ACTIVITIES;
	}else if(myP2PActivity!=null ){
		navigation = P2PPortletConstants.MY_CORRECTIONS;
	}
	%>
	
	<liferay-util:include page="/p2p/activity/p2p_navigation.jsp" servletContext="<%= application %>" >
		<liferay-util:param name="p2pActivity" value="<%=String.valueOf(myP2PActivity != null) %>"/>
		<liferay-util:param name="navigation" value="<%=String.valueOf(navigation) %>"/>
	</liferay-util:include>
	
	<%if(myP2PActivity==null){%>
		<div class="preg_content" id="${renderResponse.namespace}capa1">
			<%@ include file="/p2p/activity/steps/uploadActivity.jsp" %>
		</div>
	<%} else {
		String passed="";
		if(learnResult.getPassed()){
			passed="done";
		}
		
		%>
		<div class='preg_content <%=!navigation.equals(P2PPortletConstants.UPLOAD_ACTIVITY) ? "hide" : "" %>' id="${renderResponse.namespace}capa1" >
			<%@ include file="/p2p/activity/steps/uploadActivity.jsp" %>
		</div>
		<div class='preg_content <%=!navigation.equals(P2PPortletConstants.CORRECT_ACTIVITIES) ? "hide" : "" %>' id="${renderResponse.namespace}capa2" >
			<%@ include file="/p2p/activity/steps/correctActivities.jsp" %>
		</div>
		<div class='preg_content <%=!navigation.equals(P2PPortletConstants.MY_CORRECTIONS) ? "hide" : "" %>' id="${renderResponse.namespace}capa3" >
			<%@ include file="/p2p/activity/steps/myCorrections.jsp" %>
		</div>
		<%
	}
	%>
	
	<liferay-portlet:renderURL var="correctionPage">
		<liferay-portlet:param name="actId" value="<%=Long.toString(actId) %>" />
		<liferay-portlet:param name="mvcRenderCommandName" value="/activities/p2p/revisions" />
	</liferay-portlet:renderURL>
	
	<%
	if(LearningActivityPermission.contains(permissionChecker, activity, LMSActionKeys.CORRECT)){
		%>
		<div class="container-buttons lms-valoraciones">
			<aui:button value="learning-activity.p2p.see-corrections" onClick="<%=correctionPage %>" type="button" />
		</div>
		<%
	}
}
%>