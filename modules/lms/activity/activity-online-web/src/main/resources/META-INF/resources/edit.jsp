<%@page import="com.ted.lms.learning.activity.online.web.constants.OnlineConstants"%>
<%@page import="com.ted.lms.learning.activity.online.OnlineActivityTypeFactory"%>
<%@page import="com.ted.lms.learning.activity.online.OnlineActivityType"%>
<%@page import="com.liferay.portal.kernel.json.JSONObject"%>
<%@page import="com.ted.lms.service.LearningActivityLocalServiceUtil"%>
<%@page import="com.ted.lms.model.LearningActivity"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ include file="init.jsp" %>

<%long actId = ParamUtil.getLong(request, "actId", 0);
boolean canBeEdited = ParamUtil.getBoolean(request, "canBeEdited", true);
OnlineActivityType onlineActivityType = null;
LearningActivity learningActivity = null;
OnlineActivityTypeFactory onlineActivityTypeFactory = null;

if(actId > 0){
	onlineActivityTypeFactory = new OnlineActivityTypeFactory();
	learningActivity = LearningActivityLocalServiceUtil.getLearningActivity(actId);
	onlineActivityType = onlineActivityTypeFactory.getOnlineActivityType(learningActivity);
}
%>

<div class="col-md-6">
	<div class="row">
		<div class="col-md-6">
			<aui:input name="includeFile" label="learning-activity.online.include-file" type="toggle-switch" 
				value="<%=onlineActivityType != null ? onlineActivityType.getIncludeFile() : OnlineConstants.DEFAULT_INCLUDE_FILE%>" ignoreRequestValue="true" />
		</div>
		<div class="col-md-6">
			<aui:input name="richText" label="learning-activity.online.rich-text" type="toggle-switch" 
				value="<%=onlineActivityType != null ? onlineActivityType.getRichText() : OnlineConstants.DEFAULT_RICH_TEXT%>" ignoreRequestValue="true" />
		</div>
	</div>
</div>