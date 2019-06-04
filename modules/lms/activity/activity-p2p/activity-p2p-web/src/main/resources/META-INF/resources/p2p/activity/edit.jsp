<%@page import="com.ted.lms.learning.activity.p2p.util.P2PPrefsPropsValues"%>
<%@page import="com.ted.lms.registry.LearningActivityTypeFactoryRegistryUtil"%>
<%@page import="com.liferay.portal.kernel.util.DateUtil"%>
<%@page import="java.text.Format"%>
<%@page import="com.liferay.portal.kernel.util.FastDateFormatFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.util.CalendarFactoryUtil"%>
<%@page import="java.util.Calendar"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="java.util.Date"%>
<%@page import="com.liferay.portal.kernel.util.HtmlUtil"%>
<%@page import="com.liferay.portal.kernel.json.JSONFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.json.JSONArray"%>
<%@page import="com.ted.lms.learning.activity.p2p.web.activity.P2PActivityTypeFactory"%>
<%@page import="com.ted.lms.learning.activity.p2p.web.activity.P2PActivityType"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.ted.lms.model.LearningActivity"%>
<%@page import="com.ted.lms.service.LearningActivityLocalServiceUtil"%>
<%@page import="com.ted.lms.model.LearningActivityType"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.ted.lms.learning.activity.p2p.constants.P2PConstants" %>
<%@ include file="init.jsp" %>

<%long actId = ParamUtil.getLong(request, "actId", 0);
boolean canBeEdited = ParamUtil.getBoolean(request, "canBeEdited", true);

P2PActivityType p2pActivityType = null;
LearningActivity learningActivity = null;

if(actId > 0){
	P2PActivityTypeFactory p2pActivityTypeFactory = (P2PActivityTypeFactory)LearningActivityTypeFactoryRegistryUtil.getLearningActivityTypeFactoryByType(P2PConstants.TYPE);
	learningActivity = LearningActivityLocalServiceUtil.getLearningActivity(actId);
	p2pActivityType = p2pActivityTypeFactory.getP2PActivityType(learningActivity);
}

Calendar cal = CalendarFactoryUtil.getCalendar(timeZone, locale); 

if(p2pActivityType != null && p2pActivityType.getUploadDate() != null){
	Date date = p2pActivityType.getUploadDate();
	cal.setTime(date);
}else{
	cal.add(Calendar.YEAR, 1);
}

int month = ParamUtil.getInteger(request, "uploadDateMonth", cal.get(Calendar.MONTH));
int day = ParamUtil.getInteger(request, "uploadDateDay", cal.get(Calendar.DATE));
int year = ParamUtil.getInteger(request, "uploadDateYear", cal.get(Calendar.YEAR));
int firstDayOfWeek = cal.getFirstDayOfWeek() - 1;
int hour = ParamUtil.getInteger(request, "uploadDateHour", DateUtil.isFormatAmPm(locale) ? cal.get(Calendar.HOUR) : cal.get(Calendar.HOUR_OF_DAY));
int minute = ParamUtil.getInteger(request, "uploadDateMinute", cal.get(Calendar.MINUTE));
int amPm = ParamUtil.getInteger(request, "uploadDateAmPm", DateUtil.isFormatAmPm(locale) ? cal.get(Calendar.AM_PM) : Calendar.AM);
String timeFormat = DateUtil.isFormatAmPm(locale) ? "am-pm" : "24-hour";

%>
<div class="col-md-4 form-group input-Date-wrapper">
	<label class="control-label" for="${renderResponse.namespace }uploadDate"><liferay-ui:message key="learning-activity.p2p.upload-date" /></label>
	<div class="clearfix">
		<liferay-ui:input-date
			dayParam='uploadDateDay'
			dayValue="<%= day %>"
			disabled="<%= !canBeEdited %>"
			firstDayOfWeek="<%= firstDayOfWeek %>"
			formName="fm"
			monthParam='uploadDateMonth'
			monthValue="<%= month %>"
			name="uploadDate"
			yearParam='uploadDateYear'
			yearValue="<%= year %>"
		/>

		<liferay-ui:input-time
			amPmParam='uploadDateAmPm'
			amPmValue="<%= amPm %>"
			disabled="<%=!canBeEdited %>"
			hourParam='uploadDateHour'
			hourValue="<%= hour %>"
			minuteParam='uploadDateMinute'
			minuteValue="<%= minute %>"
			name='uploadDateTime'
			timeFormat="<%= timeFormat %>"
		/>
	</div>
</div>
<div class="col-md-4">
	<aui:input type="text" size="3" name="numValidations" label="learning-activity.p2p.num-validations" 
			value="<%=p2pActivityType != null ? p2pActivityType.getNumValidations() : P2PConstants.DEFAULT_VALIDATION_NUMBER%>" disabled="<%=!canBeEdited %>" >
		<aui:validator name="required"/>
		<aui:validator name="number"/>
		<aui:validator name="range">[1,100]</aui:validator>
	</aui:input>
</div>
<div class="row">
	<div class="col-md-6">
		<liferay-frontend:fieldset>
			<div class="row">
				<div class="col-md-6"><h4><liferay-ui:message key="learning-activity.p2p.evaluation-criteria" /></h4></div>
				<%if(canBeEdited){ %>
					<div class="col-md-6">
						<aui:button name="addEvaluationCriteriaButton" value="learning-activity.p2p.add-evaluation-criteria" onClick="${renderResponse.getNamespace()}addEvaluationCriteria()" />
					</div>
				<%} %>
			</div>
			<div id="<portlet:namespace />texts">
				<% 
					String value = null;
					JSONArray evaluationCriteria = p2pActivityType != null ? p2pActivityType.getEvaluationCriteria() : JSONFactoryUtil.createJSONArray();
					value = evaluationCriteria.length() > 0 ? evaluationCriteria.getString(0) : LanguageUtil.get(locale, "feedback");
					
					%>  
						<span id="<portlet:namespace />texts0" class="row">
							<div class="col-md-12">
								<input class="field form-control" type="text" value="<%=HtmlUtil.escape(value) %>" name="${renderResponse.getNamespace()}text0" <%=!canBeEdited ? "disabled" : "" %>>
							</div>
						</span>
					<%
			
					if(evaluationCriteria.length() > 1){
						for(int i=1;i<evaluationCriteria.length();i++){
								value = (String)evaluationCriteria.get(i);
								if(value==null||value.equals(StringPool.BLANK))
									break;
							%>
							<div id="<portlet:namespace />texts<%=i %>" class="row">
								<div class='<%=canBeEdited ? "col-md-9" : "col-md-12" %>'>
									<input class="field form-control" type="text" value="<%=HtmlUtil.escape(value) %>" name="${renderResponse.getNamespace()}text<%=i %>" <%=!canBeEdited ? "disabled" : "" %>>
								</div>
								<%if(canBeEdited){ %>
									<a onClick="this.parentNode.parentNode.removeChild(this.parentNode);$('#<portlet:namespace />addEvaluationCriteriaButton').prop("disabled", false);" class="col-md-3" href="#">
										<liferay-ui:message key="delete" />
									</a>			
								<%} %>
							</div>
							<%
						}
					}
				%>
			</div>
		</liferay-frontend:fieldset>
	</div>
	<%
		if(P2PPrefsPropsValues.getP2PTeamAssignations(themeDisplay.getCompanyId())){
	%>
		<div class="col-md-6">
			<aui:field-wrapper label="learning-activity.p2p.select-assignation-type" >
				<aui:input inlineLabel="right" name="assignationType" type="radio" value="course" label="learning-activity.p2p.course-assignation" 
					checked='<%=p2pActivityType == null || "course".equals(p2pActivityType.getAssignationType())%>' 
					helpMessage="learning-activity.p2p.course-assignation.help-message" disabled="<%=!canBeEdited%>"/>
				<aui:input inlineLabel="right"  name="assignationType" type="radio" value="team" label="learning-activity.p2p.team-assignation"  
					checked='<%=p2pActivityType != null && "team".equals(p2pActivityType.getAssignationType())%>' 
					helpMessage="learning-activity.p2p.team-assignation.help-message" disabled="<%=!canBeEdited%>"/>
			</aui:field-wrapper>
		</div>
	<%
		}
	%>
</div>
<div class="row">
	<div class="col-md-4">
		<aui:input type="checkbox" name="anonimous" label="learning-activity.p2p.anonimous" value="<%=p2pActivityType != null && p2pActivityType.getAnonimous()%>" 
			helpMessage="learning-activity.p2p.anonimous.help-message" disabled="<%=!canBeEdited%>"/>
	</div>
	<div class="col-md-4">
		<aui:input type="checkbox" name="result" label="learning-activity.p2p.result" value="<%=p2pActivityType != null && p2pActivityType.getResult()%>" 
			helpMessage="learning-activity.p2p.result.help-message" disabled="<%=!canBeEdited%>"/>
	</div>
	<div class="col-md-4">	
		<aui:input type="checkbox" name="fileOptional" label="learning-activity.p2p.file-optional" value="<%=p2pActivityType != null && p2pActivityType.getFileOptional()%>" 
			helpMessage="learning-activity.p2p.file-optional.help-message" disabled="<%=!canBeEdited%>" />
	</div>
	<div class="col-md-4">	
		<aui:input type="checkbox" name="emailAnonimous" label="learning-activity.p2p.email-anonimous" value="<%=p2pActivityType != null && p2pActivityType.getEmailAnonimous()%>" 
			helpMessage="learning-activity.p2p.email-anonimous.help-message" disabled="<%=!canBeEdited%>"/>
	</div>
	<div class="col-md-4">
		<aui:input type="checkbox" name="askForP2PActivities" label="learning-activity.p2p.ask-for-p2p-activities" value="<%=p2pActivityType != null && p2pActivityType.getAskForP2PActivities()%>" 
			helpMessage="learning-activity.p2p.ask-for-p2p-activities.help-message" disabled="<%=!canBeEdited%>"/>
	</div>
</div>
<script>
	function <portlet:namespace />addEvaluationCriteria(){
		var container = document.getElementById("<portlet:namespace />texts");
		var numberQuestion = parseInt('<%=P2PPrefsPropsValues.getNumEvaluationCriteria(themeDisplay.getCompanyId())%>');
		
		if(container){
			var number=-1;
			for(var i=1;i < numberQuestion;i++){
				if(document.getElementById("<portlet:namespace />texts"+i)==null){
					number=i;
					break;
				}
			}
			console.log("number: " + number);
			if($('div[id^="<portlet:namespace />texts"]').length >= numberQuestion){
				$('#<portlet:namespace />addEvaluationCriteriaButton').prop("disabled",true);
			}
	
			var fDiv = document.createElement("div");
			fDiv.className = "row";
			fDiv.id = "<portlet:namespace />texts"+number;
			var div = document.createElement("div");
			div.className = "col-md-9";
			var input = document.createElement("input");
			input.type = "text";
			input.name = "<portlet:namespace />text"+number;
			input.className = "field form-control";
			div.appendChild(input);
			var link = document.createElement("a");
			link.className = "col-md-3";
			link.text = Liferay.Language.get("delete");
			link.style.cursor='pointer';
			link.onclick=function() {
				//Se modifica para internet explorer, ya que lo anterior no funciona
				this.parentNode.parentNode.removeChild(this.parentNode);
				$('#<portlet:namespace />addEvaluationCriteriaButton').prop("disabled", false);
			}
			fDiv.appendChild(div);
			fDiv.appendChild(link);
			container.appendChild(fDiv);
		}
	}
</script>