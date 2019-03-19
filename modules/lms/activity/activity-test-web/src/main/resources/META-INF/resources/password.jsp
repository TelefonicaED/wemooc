<%@ include file="init.jsp" %>

<c:if test="${isTeacher }">
	<aui:button type="button" value="learning-activity.correct" href="${goToCorrectionURL }"/>
</c:if>

<liferay-ui:error message="learning-activity.test.error.password-incorrect" key="password-incorrect" />

<aui:form action="${passwordURL }" method="post" role="form">

	<aui:input type="text" name="password" label="learning-activity.test.enter-password" />
	
	<aui:button value="send" type="submit"/>
	
</aui:form>