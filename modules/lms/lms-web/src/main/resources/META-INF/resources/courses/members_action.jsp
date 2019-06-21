<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.kernel.model.User"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

User user2 = (User)row.getObject();
%>
<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
					
	<portlet:actionURL name="deleteGroupUsers" var="deleteGroupUsersURL">
		<portlet:param name="redirect" value="${currentURL}" />
		<portlet:param name="courseId" value="${courseId }" />
		<portlet:param name="tabs" value="${tabs }" />
		<portlet:param name="removeUserId" value="<%= String.valueOf(user2.getUserId()) %>" />
	</portlet:actionURL>
	
	<liferay-ui:icon-delete
		message="remove"
		url="${deleteGroupUsersURL}"
	/>
</liferay-ui:icon-menu>