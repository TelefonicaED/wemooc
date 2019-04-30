<%@page import="com.liferay.portal.kernel.search.Sort"%>
<%@page import="com.liferay.portal.kernel.search.BaseModelSearchResult"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.liferay.portal.kernel.util.FastDateFormatFactoryUtil"%>
<%@page import="java.text.Format"%>
<%@page import="com.ted.lms.model.LearningActivity"%>
<%@page import="com.ted.lms.service.LearningActivityLocalServiceUtil"%>
<%@page import="com.ted.lms.service.LearningActivityLocalService"%>
<%@page import="com.ted.lms.learning.activity.p2p.web.activity.P2PActivityType"%>
<%@page import="com.ted.lms.learning.activity.p2p.web.activity.P2PActivityTypeFactory"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.ted.lms.learning.activity.p2p.model.P2PActivity"%>
<%@page import="com.ted.lms.learning.activity.p2p.service.P2PActivityCorrectionsLocalServiceUtil"%>
<%@page import="com.ted.lms.learning.activity.p2p.service.P2PActivityLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portal.kernel.workflow.WorkflowConstants"%>
<%@page import="com.liferay.portal.kernel.service.UserLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.model.User"%>
<%@page import="com.liferay.portal.kernel.util.OrderByComparator"%>
<%@page import="com.liferay.portal.kernel.dao.orm.CustomSQLParam"%>
<%@page import="com.liferay.portal.kernel.exception.SystemException"%>
<%@page import="com.liferay.portal.kernel.service.TeamLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.model.Team"%>
<%@page import="java.util.List"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="javax.portlet.PortletURL"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@include file="init.jsp" %>

<%
long actId=ParamUtil.getLong(request,"actId",0);
String criteria = ParamUtil.getString(renderRequest,"criteria","");
PortletURL portletURL = renderResponse.createRenderURL();
portletURL.setParameter("mvcPath","/p2p/activity/revisions.jsp");
portletURL.setParameter("criteria", criteria);
portletURL.setParameter("delta", "10"); 

LearningActivity activity = LearningActivityLocalServiceUtil.getLearningActivity(actId);

P2PActivityTypeFactory p2pActivityTypeFactory = new P2PActivityTypeFactory();
P2PActivityType p2pActivityType = p2pActivityTypeFactory.getP2PActivityType(activity);
%>

<portlet:renderURL var="backURL">
	<portlet:param name="mvcRenderCommandName" value="/activities/p2p/view_activity" />
	<portlet:param name="actId" value="<%=String.valueOf(actId) %>" />
</portlet:renderURL>

<liferay-ui:header title="learning-activity.p2p.corrections" backURL="<%=backURL %>" localizeTitle="true"/>

<div class="student_search"> 

	<aui:form name="studentsearch" action="${searchURL }" role="search" method="GET">	
		<aui:input label="" name="criteria" size="20" value="<%=criteria %>" />	

		<aui:button value="search" type="submit" />

	</aui:form>

	<liferay-ui:search-container iteratorURL="<%=portletURL%>" emptyResultsMessage="there-are-no-results" delta="5">

	   	<liferay-ui:search-container-results>
			<%
				LinkedHashMap<String, Object> userParams = new LinkedHashMap<>();
	
				userParams.put("inherit", Boolean.TRUE);
				userParams.put("usersGroups", Long.valueOf(themeDisplay.getScopeGroupId()));
				
				//Si el tutor pertenece a uno o más equipos, sólo se muestran los usuarios de los equipos
				
				List<Team> userTeams = null;
				try {
					userTeams=TeamLocalServiceUtil.getUserTeams(themeDisplay.getUserId(), themeDisplay.getScopeGroupId());
				} catch (SystemException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				if(userTeams != null && userTeams.size() > 0){
					String teamIds = "";
					for(int i = 0; i < userTeams.size();i++){
						teamIds += userTeams.get(i).getTeamId() + ",";
					}
					if(teamIds.length() > 0){
						teamIds = teamIds.substring(0, teamIds.length()-1);
					}
					
					userParams.put("userTeamIds", new CustomSQLParam("INNER JOIN users_teams ON user_.userId = users_teams.userId "
							+ "WHERE users_teams.teamId IN (" + teamIds + ")", null));
				}
				
				OrderByComparator obc = null;
				List<User> userListPage = UserLocalServiceUtil.search(themeDisplay.getCompanyId(), criteria, WorkflowConstants.STATUS_APPROVED, userParams, searchContainer.getStart(), searchContainer.getEnd(), obc);
				int userCount = UserLocalServiceUtil.searchCount(themeDisplay.getCompanyId(), criteria, WorkflowConstants.STATUS_APPROVED, userParams);
						
				pageContext.setAttribute("results", userListPage);
			    pageContext.setAttribute("total", userCount);
			    pageContext.setAttribute("delta", 10);
			%>
		</liferay-ui:search-container-results>
		
		<liferay-ui:search-container-row className="com.liferay.portal.kernel.model.User" keyProperty="userId" modelVar="user">
		
		<portlet:renderURL var="verDetalle">
			<portlet:param name="mvcPath" value="/p2p/activity/detalleAct.jsp" />
			<portlet:param name="actId" value="<%=String.valueOf(actId) %>" />
			<portlet:param name="userId" value="<%=String.valueOf(user.getUserId()) %>" />
		</portlet:renderURL>
		
		<%
		String nameTit = LanguageUtil.get(themeDisplay.getLocale(), "name");
		%>
		
		<liferay-ui:search-container-column-text value="<%=user.getFullName()%>" name="<%=nameTit %>"  />
		<%
		boolean existP2p = P2PActivityLocalServiceUtil.hasP2PActivity(actId, user.getUserId());
		boolean correctionCompleted = P2PActivityCorrectionsLocalServiceUtil.areAllCorrectionsDoneByUserInP2PActivity(actId, user.getUserId(), p2pActivityType.getNumValidations());
		
		String textTaks = "";
		
		String textTaksTit = LanguageUtil.get(themeDisplay.getLocale(), "state");
		
		//Si se ha entregado la tarea
		if(existP2p && !correctionCompleted ){
			textTaks = LanguageUtil.get(themeDisplay.getLocale(), "learning-activity.p2p.status.incomplete");
		} else if(existP2p && correctionCompleted){
			textTaks = LanguageUtil.get(themeDisplay.getLocale(), "learning-activity.p2p.status.passed");
		}else{
			textTaks = LanguageUtil.get(themeDisplay.getLocale(), "learning-activity.p2p.status.failed");
		}
		%>
		<liferay-ui:search-container-column-text value="<%=textTaks %>" name="<%=textTaksTit %>" />
		<%
		P2PActivity myP2PActivity = P2PActivityLocalServiceUtil.getP2PActivity(actId, user.getUserId());

		String dateTit = LanguageUtil.get(themeDisplay.getLocale(), "date");
		
		Date dateDel;
		Format dateFormatDate = FastDateFormatFactoryUtil.getDateTime(DateFormat.SHORT, DateFormat.SHORT, locale, timeZone);
		String dateDelS = "";
		if(myP2PActivity != null){
			dateDel = myP2PActivity.getDate();
			dateDelS = dateFormatDate.format(dateDel);
		}
		%>
		<liferay-ui:search-container-column-text value="<%=dateDelS %>" name="<%=dateTit %>" />
		<%
		if(existP2p){
			String urlResume = "self.location = '"+verDetalle+"';";
			String nameButton =LanguageUtil.get(themeDisplay.getLocale(), "learning-activity.p2p.see-task"); 
		%>
			<liferay-ui:search-container-column-button href="<%=urlResume %>"  name="<%=nameButton %>" />
		<%} else{%>
			<liferay-ui:search-container-column-text value="" />
		<%} %>
		</liferay-ui:search-container-row>
	 	<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</div>
