<%@ include file="init.jsp" %>

<p class="description">${activity.getDescription(themeDisplay.locale) }</p>

<c:if test="${empty evaluationActivityType.publishDate || empty resultUser}">
	<div class="alert alert-info"><liferay-ui:message key="learning-activity.evaluation.not-qualificated" /></div>
</c:if>

<h4><liferay-ui:message key="calification.result" /></h4>

<div>
	<span><liferay-ui:message key="calification.user-result" />: </span><span>${result }</span>
</div>
<div>
	<span><liferay-ui:message key="status" />: </span><span><liferay-ui:message key="${status }" /></span>
</div>
