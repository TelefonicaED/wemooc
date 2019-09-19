<%@ include file="init.jsp" %>

<c:if test="${isTeacher }">
	<aui:button type="button" value="learning-activity.test.correct" href="${goToCorrectionURL }"/>
</c:if>

<p><liferay-ui:message key="learning-activity.test.done" /></p>

<c:choose>
	<c:when test="${hasFreeQuestion }">
		<p><liferay-ui:message key="learning-activity.test.wait-teacher-correction" /></p>
	</c:when>
	<c:otherwise>
		<p><liferay-ui:message key="learning-activity.test.your-result" arguments="${calificationType.translate(themeDisplay.locale, learningActivityResult.result).concat(calificationType.suffix) }" /></p>
		<p class="bold"><liferay-ui:message key="learning-activity.test.your-result-dont-pass"  arguments="${calificationType.translate(themeDisplay.locale, activity.passPuntuation).concat(calificationType.suffix) }" /></p>
		<p><liferay-ui:message key="learning-activity.test.your-result-no-more-tries" /></p>
	</c:otherwise>
</c:choose>