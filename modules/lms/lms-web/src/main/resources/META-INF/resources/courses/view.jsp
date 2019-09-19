<%@ include file="../init.jsp" %>

<c:if test="${not empty urlFirstActivity }">
	<a href="${urlFirstActivity}"><liferay-ui:message key="learning-activity.start" /></a>
</c:if>

<c:if test="${not empty urlLastActivity}">
	<a href="${urlLastActivity}"><liferay-ui:message key="learning-activity.previous" /></a>
</c:if>
<c:if test="${not empty urlNextActivity}">
	<a href="${urlNextActivity}"><liferay-ui:message key="learning-activity.next" /></a>
</c:if>