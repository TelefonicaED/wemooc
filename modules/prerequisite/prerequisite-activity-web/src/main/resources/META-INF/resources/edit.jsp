<%@page import="com.ted.prerequisite.activity.internal.constants.ActivityPrerequisitePortletKeys"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="com.ted.lms.service.LearningActivityLocalServiceUtil"%>
<%@page import="com.ted.lms.model.LearningActivity"%>
<%@page import="com.ted.lms.service.ModuleLocalServiceUtil"%>
<%@page import="com.ted.lms.model.Module"%>
<%@page import="java.util.List"%>
<%@page import="com.ted.prerequisite.activity.internal.constants.ActivityPrerequisiteConstants"%>
<%@page import="com.ted.prerequisite.activity.ActivityPrerequisiteFactory"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="com.ted.prerequisite.service.PrerequisiteRelationLocalServiceUtil"%>
<%@page import="com.ted.prerequisite.model.PrerequisiteRelation"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ include file="/init.jsp" %>

<script src="/o/lms-web/lms/activities.js"></script>

<%
long classNameId = ParamUtil.getLong(request, "classNameId", 0);
long classPK = ParamUtil.getLong(request, "classPK", 0);
long groupId = ParamUtil.getLong(request, "groupId", 0);
System.out.println("classPK:" + classPK);%>

<script>activityExcludedId = <%=classPK%>;</script>

<%PrerequisiteRelation prerequisiteRelation = PrerequisiteRelationLocalServiceUtil.getPrerequisiteRelation(
												PortalUtil.getClassNameId(ActivityPrerequisiteFactory.class), 
												classNameId, classPK);

System.out.println("prerequisiteRelation: " + (prerequisiteRelation != null ? prerequisiteRelation.getPrerequisiteRelationId() : ""));

long activityPrerequisiteId = prerequisiteRelation != null ? prerequisiteRelation.getExtraDataJSON().getLong(ActivityPrerequisiteConstants.JSON_ACT_ID) : 0;

long moduleId = 0;

if(activityPrerequisiteId > 0){
	LearningActivity activity = LearningActivityLocalServiceUtil.getLearningActivity(activityPrerequisiteId);
	moduleId = activity != null ? activity.getModuleId() : 0;
}

%>

<aui:select label="prerequisite-activity.module" name='prerequisiteActivityModuleId' />

<aui:select label="prerequisite-activity.activity" name='prerequisiteActId' />


<aui:script use="lms-activities,liferay-dynamic-select">
	new Liferay.DynamicSelect(
		[
			{
				select: '<portlet:namespace />prerequisiteActivityModuleId',
				selectData: Liferay.Activities.getModules,
				selectDesc: 'titleCurrentValue',
				selectId: 'moduleId',
				selectSort: '<%= true %>',
				selectVal: '<%= moduleId %>'
			},
			{
				select: '<portlet:namespace />prerequisiteActId',
				selectData: Liferay.Activities.getActivities,
				selectDesc: 'titleCurrentValue',
				selectId: 'actId',
				selectVal: '<%= activityPrerequisiteId %>'
			}
		]
	);
</aui:script>