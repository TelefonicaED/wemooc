<%@page import="com.ted.lms.registry.LearningActivityTypeFactoryRegistryUtil"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.ted.lms.service.LearningActivityServiceUtil"%>
<%@page import="com.ted.lms.service.ModuleServiceUtil"%>
<%@page import="com.ted.lms.learning.activity.evaluation.web.constants.EvaluationConstants"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portal.kernel.servlet.SessionErrors"%>
<%@page import="com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.json.JSONFactoryUtil"%>
<%@page import="com.ted.lms.service.ModuleLocalServiceUtil"%>
<%@page import="com.ted.lms.model.Module"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="com.liferay.portal.kernel.json.JSONArray"%>
<%@page import="com.ted.lms.learning.activity.evaluation.EvaluationActivityTypeFactory"%>
<%@page import="com.ted.lms.learning.activity.evaluation.EvaluationActivityType"%>
<%@page import="com.liferay.portal.kernel.json.JSONObject"%>
<%@page import="com.ted.lms.service.LearningActivityLocalServiceUtil"%>
<%@page import="com.ted.lms.model.LearningActivity"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ include file="init.jsp" %>

<%long actId = ParamUtil.getLong(request, "actId", 0);
boolean canBeEdited = ParamUtil.getBoolean(request, "canBeEdited", true);
EvaluationActivityType evaluationActivityType = null;
LearningActivity learningActivity = null;
EvaluationActivityTypeFactory evaluationActivityTypeFactory = null;

JSONObject activities = null;
Date firedDate = null;

if(actId > 0){
	evaluationActivityTypeFactory = (EvaluationActivityTypeFactory)LearningActivityTypeFactoryRegistryUtil.getLearningActivityTypeFactoryByType(EvaluationConstants.TYPE);
	learningActivity = LearningActivityLocalServiceUtil.getLearningActivity(actId);
	evaluationActivityType = evaluationActivityTypeFactory.getEvaluationActivityType(learningActivity);
	activities = evaluationActivityType.getActivities();
	firedDate = evaluationActivityType.getFiredDate();
}else{
	activities = JSONFactoryUtil.createJSONObject();
}

List<Module> modules = ModuleServiceUtil.getGroupModules(themeDisplay.getScopeGroupId());

String tabsNames = "";
for(Module module:modules){
	tabsNames += Validator.isNotNull(tabsNames) ? "," + module.getTitle(locale) : module.getTitle(locale);
}
%>

<div class="col-md-12">
	<liferay-ui:tabs
			names="<%= tabsNames %>"
			refresh="<%= false %>"
			type="tabs nav-tabs-default"
		>
		<%for(Module module: modules){ %>
			<liferay-ui:section>
				<liferay-ui:search-container emptyResultsMessage="there-are-no-activities" >
				
				   	<liferay-ui:search-container-results>
						<%
							List<LearningActivity> notTeamActivities = LearningActivityServiceUtil.getActivitiesNotTypeId(module.getModuleId(), EvaluationConstants.TYPE);
							List<LearningActivity> lactivities = new ArrayList<LearningActivity>();
							
							for(LearningActivity activity: notTeamActivities){
								long teamId = activity.getTeamId();
								if(teamId == 0){
									lactivities.add(activity);
								}
							}
							pageContext.setAttribute("results",lactivities);		
						%>
					</liferay-ui:search-container-results>
					
					<liferay-ui:search-container-row className="com.ted.lms.model.LearningActivity" keyProperty="actId" modelVar="activityToEvaluate">
						<liferay-ui:search-container-column-text name="learning-activity" >
							<%=activityToEvaluate.getTitle(themeDisplay.getLocale()) %>
						</liferay-ui:search-container-column-text>
						<liferay-ui:search-container-column-text name="learning-activity.type" cssClass="col-type" >
							<span class="<%=activityToEvaluate.getLearningActivityTypeFactory().getIconCssClass()%>"></span>
						</liferay-ui:search-container-column-text>
						<liferay-ui:search-container-column-text name="learning-activity.evaluation.weight" >
							<%double weight = activities.getDouble(String.valueOf(activityToEvaluate.getActId()), 0); %>
							<aui:input type="text" label="" name='<%="weight_"+activityToEvaluate.getActId() %>'  size="3" disabled="<%=firedDate != null%>"
								value="<%=weight > 0 ? weight : "" %>">
								<aui:validator name="number"/>
								<aui:validator name="range">[0,100]</aui:validator>
							</aui:input>
						</liferay-ui:search-container-column-text>
					</liferay-ui:search-container-row>
				
				 	<liferay-ui:search-iterator paginate="false" />
				</liferay-ui:search-container>
			</liferay-ui:section>
		<%} %>
	</liferay-ui:tabs>
</div>