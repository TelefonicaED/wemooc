<%@page import="com.ted.lms.learning.activity.test.web.activity.TestActivityType"%>
<%@page import="com.ted.lms.learning.activity.QuestionsLearningActivityType"%>
<%@page import="com.ted.lms.learning.activity.test.web.activity.TestActivityTypeFactory"%>
<%@page import="com.ted.lms.learning.activity.question.model.Question"%>
<%@page import="java.util.List"%>
<%@ include file="init.jsp" %>

<div class="container-activity isFeedback">
	<div class="description">${activity.getDescription(themeDisplay.locale)}</div>
	
	<p><liferay-ui:message key="learning-activity.test.test-done" /></p>
	
	<liferay-util:include page="/html/execactivity/test/timeout.jsp" servletContext="<%=this.getServletContext() %>">
		<liferay-util:param value="${activity.actId }" name="actId"/>
	</liferay-util:include> 
	
	<c:if test="${!hasFreeQuestions }">
		<p><liferay-ui:message key="learning-activity.test.your-result" arguments="${calificationType.translate(locale, scoreTry) }" /></p>
	</c:if>
	<c:choose>
		<c:when test="${userPassed }">
			<p><liferay-ui:message key="learning-activity.test.your-result-pass" /></p>
		</c:when>
		<c:when test="${!hasFreeQuestions }">
			<p><liferay-ui:message key="learning-activity.test.your-result-dont-pass"  arguments="${calificationType.translate(locale, activity.passPuntuation) }" /></p>
		</c:when>
	</c:choose>
	<c:if test="${tries > 0 && userTries >=tries }">
		<p><liferay-ui:message key="learning-activity.test.your-result-no-more-tries" /></p>
	</c:if>
	<c:if test="${showFeedback }">
		<p><liferay-ui:message key="learning-activity.test.your-answers" /></p>
		<%List<Question> questions = (List<Question>)request.getAttribute("questions"); 
		
		for(Question question: questions){
			request.setAttribute("question", question);%>
			
			<liferay-util:include page="<%=question.getQuestionType().getURLQuestion() %>" portletId="<%=question.getQuestionType().getQuestionTypeFactory().getPortletId() %>" >
				<liferay-util:param name="questionId" value="<%=String.valueOf(question.getQuestionId()) %>"/>
				<liferay-util:param name="tryResultData" value="${tryResultData }" />
				<liferay-util:param name="canUserDoNewTry" value="${canUserDoNewTry }" />
				<liferay-util:param name="showCorrectAnswer" value="${testActivityType.showCorrectAnswer }"/>
				<liferay-util:param name="showCorrectAnswerOnlyOnFinalTry" value="${testActivityType.showCorrectAnswerOnlyOnFinalTry}"/>
				<liferay-util:param name="feedback" value="true" />
			</liferay-util:include>
		<%} %>
	</c:if>
	
	<c:if test="${not empty tryTestURL }">
		<aui:button value="learning-activity.test.try-again" href="${tryTestURL }" />
	</c:if>
	<c:if test="${not empty improveURL }">
		<aui:button value="learning-activity.test.try-again.improve" href="${improveURL }"/>
	</c:if>
</div>

<c:if test="${showPopupResult }">
	<%@ include file="/popup_result.jsp" %>
</c:if>