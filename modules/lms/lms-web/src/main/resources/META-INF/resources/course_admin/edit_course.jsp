<%@page import="com.liferay.portal.kernel.security.permission.ResourceActionsUtil"%>
<%@page import="com.liferay.portal.kernel.service.LayoutLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.model.Layout"%>
<%@page import="com.liferay.portal.kernel.service.GroupLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.model.Group"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.liferay.portal.kernel.workflow.WorkflowConstants"%>
<%@page import="com.ted.lms.web.constants.LMSPortletConstants"%>
<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portal.kernel.exception.GroupFriendlyURLException"%>
<%@ include file="init.jsp" %>

<%String navigation = ParamUtil.getString(renderRequest, "navigation", LMSPortletConstants.EDIT_COURSE_DEFAULT_NAVIGATION);%>

<portlet:actionURL name="/courses/edit_course" var="editCourseURL" />

<liferay-util:include page="/courses/edit_course_navigation.jsp" servletContext="<%= application %>" />

<c:set var="workflowDraft" value="<%=WorkflowConstants.STATUS_DRAFT %>" />

<c:if test="${not empty course }">
<liferay-frontend:info-bar>
	<aui:workflow-status id="${course.courseId }" markupView="lexicon" showHelpMessage="false" showIcon="false" showLabel="false" status="${course.status }" />
</liferay-frontend:info-bar>
</c:if>

<liferay-ui:error exception="<%=GroupFriendlyURLException.class %>" >
	<%
	GroupFriendlyURLException gfurle = (GroupFriendlyURLException)errorException;
	%>

	<c:if test="<%= gfurle.getType() == GroupFriendlyURLException.ADJACENT_SLASHES %>">
		<liferay-ui:message key="please-enter-a-friendly-url-that-does-not-have-adjacent-slashes" />
	</c:if>

	<c:if test="<%= gfurle.getType() == GroupFriendlyURLException.DOES_NOT_START_WITH_SLASH %>">
		<liferay-ui:message key="please-enter-a-friendly-url-that-begins-with-a-slash" />
	</c:if>

	<c:if test="<%= gfurle.getType() == GroupFriendlyURLException.DUPLICATE %>">

		<%
		long duplicateClassPK = gfurle.getDuplicateClassPK();
		String duplicateClassName = gfurle.getDuplicateClassName();

		String name = StringPool.BLANK;

		if (duplicateClassName.equals(Group.class.getName())) {
			Group duplicateGroup = GroupLocalServiceUtil.getGroup(duplicateClassPK);

			name = duplicateGroup.getDescriptiveName(locale);
		}
		else if (duplicateClassName.equals(Layout.class.getName())) {
			Layout duplicateLayout = LayoutLocalServiceUtil.getLayout(duplicateClassPK);

			name = duplicateLayout.getName(locale);
		}
		%>

		<liferay-ui:message arguments="<%= new Object[] {ResourceActionsUtil.getModelResource(locale, duplicateClassName), name} %>" key="please-enter-a-unique-friendly-url" translateArguments="<%= false %>" />
	</c:if>

	<c:if test="<%= gfurle.getType() == GroupFriendlyURLException.ENDS_WITH_DASH %>">
		<liferay-ui:message key="please-enter-a-friendly-url-that-does-not-end-with-a-dash" />
	</c:if>

	<c:if test="<%= gfurle.getType() == GroupFriendlyURLException.ENDS_WITH_SLASH %>">
		<liferay-ui:message key="please-enter-a-friendly-url-that-does-not-end-with-a-slash" />
	</c:if>

	<c:if test="<%= gfurle.getType() == GroupFriendlyURLException.INVALID_CHARACTERS %>">
		<liferay-ui:message key="please-enter-a-friendly-url-with-valid-characters" />
	</c:if>

	<c:if test="<%= gfurle.getType() == GroupFriendlyURLException.KEYWORD_CONFLICT %>">
		<liferay-ui:message arguments="<%= gfurle.getKeywordConflict() %>" key="please-enter-a-friendly-url-that-does-not-conflict-with-the-keyword-x" translateArguments="<%= false %>" />
	</c:if>

	<c:if test="<%= gfurle.getType() == GroupFriendlyURLException.POSSIBLE_DUPLICATE %>">
		<liferay-ui:message key="the-friendly-url-may-conflict-with-another-page" />
	</c:if>

	<c:if test="<%= gfurle.getType() == GroupFriendlyURLException.TOO_DEEP %>">
		<liferay-ui:message key="the-friendly-url-has-too-many-slashes" />
	</c:if>

	<c:if test="<%= gfurle.getType() == GroupFriendlyURLException.TOO_SHORT %>">
		<liferay-ui:message key="please-enter-a-friendly-url-that-is-at-least-two-characters-long" />
	</c:if>
</liferay-ui:error>

<aui:form name="fm" enctype="multipart/form-data" method="post" action="${editCourseURL}" >
	<aui:input name="<%= Constants.CMD %>" value="${cmd}" type="hidden" />
	<aui:input name="navigationItem" value="" type="hidden" />
	<aui:input name="navigation" value="${navigation }" type="hidden" />
	<aui:input name="nextStep" value="" type="hidden" />
	<aui:input name="redirect" type="hidden" value="${redirect}" />
	<aui:input name="workflowAction" type="hidden" value="${empty course ? workflowDraft : course.status }" />
	<aui:input name="courseId" type="hidden" value="${course.courseId }" />

	<c:choose>
		<c:when test='<%=navigation.equals(LMSPortletConstants.EDIT_COURSE_DESCRIPTION) %>'>
			<liferay-util:include page="/courses/description.jsp" servletContext="<%= application %>" />
		</c:when>
		<c:when test='<%=navigation.equals(LMSPortletConstants.EDIT_COURSE_CONFIGURATION)%>'>
			<liferay-util:include page="/courses/configuration.jsp" servletContext="<%= application %>" />
		</c:when>
		<c:when test='<%=navigation.equals(LMSPortletConstants.EDIT_COURSE_ASSET_LINKS)%>'>
			<liferay-util:include page="/courses/asset_links.jsp" servletContext="<%= application %>" />
		</c:when>
		<c:when test='<%=navigation.equals(LMSPortletConstants.EDIT_COURSE_MESSAGES)%>'>
			<liferay-util:include page="/courses/messages.jsp" servletContext="<%= application %>" />
		</c:when>
	</c:choose>
	<aui:button-row>
		<aui:button value="cancel" onClick="${redirect}"/>
		<c:if test="<%=courseDisplayContext.showButtonSaveAsDraft() %>">
			<aui:button name="saveAsDraftButton" type="submit" value="save-as-draft" />
			<script>
				$('#<portlet:namespace />saveAsDraftButton').on(
					'click',
					function() {
						$('#<portlet:namespace />workflowAction').val('<%= WorkflowConstants.STATUS_DRAFT %>');
						$('#<portlet:namespace />nextStep').val(false);
					}
				);
			</script>
		</c:if>
		<c:if test="<%=courseDisplayContext.showButtonSave() %>">
			<aui:button name="saveButton" type="submit" value="save" />
			<script>
				$('#<portlet:namespace />saveButton').on(
					'click',
					function() {
						$('#<portlet:namespace />workflowAction').val('<%= WorkflowConstants.STATUS_APPROVED %>');
						$('#<portlet:namespace />nextStep').val(false);
					}
				);
			</script>
		</c:if>
		
		<c:if test="<%=courseDisplayContext.showButtonPublish() %>">
			<aui:button name="publishButton" type="submit" value="publish" />
			<script>
				$('#<portlet:namespace />publishButton').on(
					'click',
					function() {
						$('#<portlet:namespace />workflowAction').val('<%= WorkflowConstants.STATUS_APPROVED %>');
						$('#<portlet:namespace />nextStep').val(false);
					}
				);
			</script>
		</c:if>
		<c:if test="<%=courseDisplayContext.showButtonSaveAndContinue() %>">
			<aui:button name="saveAndContinueButton" type="submit" value="save-and-continue" />
			<script>
				$('#<portlet:namespace />saveAndContinueButton').on(
					'click',
					function() {
						$('#<portlet:namespace />nextStep').val(true);
					}
				);
			</script>
		</c:if>
	</aui:button-row>
</aui:form>