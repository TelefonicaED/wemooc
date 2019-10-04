<%@page import="com.ted.lms.learning.activity.survey.web.util.SurveyPrefsPropsValues"%>
<%@page import="com.ted.lms.learning.activity.question.constants.QuestionsWebPortletKeys"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.ted.lms.learning.activity.survey.web.activity.SurveyActivityTypeFactory"%>
<%@ include file="init.jsp" %>

<%long actId = ParamUtil.getLong(request, "actId", 0);
boolean canBeEdited = ParamUtil.getBoolean(request, "canBeEdited", true);
SurveyActivityTypeFactory surveyActivityTypeFactory = (SurveyActivityTypeFactory)LearningActivityTypeFactoryRegistryUtil.getLearningActivityTypeFactoryByType(SurveyConstants.TYPE);
%>

<div class="col-md-12 activity-test-questions" >
	<liferay-util:include page="<%=QuestionsWebPortletKeys.EDIT_QUESTIONS_JSP %>" portletId="<%=QuestionsWebPortletKeys.EDIT_QUESTIONS%>" >
		<liferay-util:param name="actId" value="<%=String.valueOf(actId)%>" />
		<liferay-util:param name="canBeEdited" value="<%=String.valueOf(canBeEdited)%>" />
		<liferay-util:param name="questionIdsAllowed" value="<%=surveyActivityTypeFactory.getQuestionsAllowed(themeDisplay.getCompanyId())%>" />
	</liferay-util:include>
</div>