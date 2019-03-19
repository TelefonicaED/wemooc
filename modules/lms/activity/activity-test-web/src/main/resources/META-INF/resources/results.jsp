<%@page import="com.ted.lms.learning.activity.test.web.activity.TestActivityType"%>
<%@page import="com.ted.lms.learning.activity.QuestionsLearningActivityType"%>
<%@page import="com.ted.lms.learning.activity.test.web.activity.TestActivityTypeFactory"%>
<%@page import="com.ted.lms.learning.activity.question.model.Question"%>
<%@page import="java.util.List"%>
<%@ include file="init.jsp" %>

<div class="container-activity isFeedback">
	<div class="description">${activity.getDescription(themeDisplay.locale)}</div>
	
	<c:if test="${showPopupResult }">
		<%@ include file="/popup_result.jsp" %>
	</c:if>
	
	<p><liferay-ui:message key="test-done" /></p>
	
	<liferay-util:include page="/html/execactivity/test/timeout.jsp" servletContext="<%=this.getServletContext() %>">
		<liferay-util:param value="${activity.actId }" name="actId"/>
	</liferay-util:include> 
	
	<c:if test="${!hasFreeQuestions }">
		<p><liferay-ui:message key="your-result" arguments="${calificationType.translate(locale, scoreTry) }" /></p>
	</c:if>
	<c:choose>
		<c:when test="${userPassed }">
			<p class="color_tercero negrita"><liferay-ui:message key="your-result-pass" /></p>
		</c:when>
		<c:when test="${!hasFreeQuestions }">
			<p class="color_tercero negrita"><liferay-ui:message key="your-result-dont-pass"  arguments="${calificationType.translate(locale, activity.passPuntuation) }" /></p>
		</c:when>
	</c:choose>
	<c:if test="${tries && userTries >=tries }">
		<p class="color_tercero"><liferay-ui:message key="your-result-no-more-tries" /></p>
	</c:if>
	<c:if test="${showFeedback }">
		<p class="negrita"><liferay-ui:message key="your-answers" /></p>
		<%List<Question> questions = (List<Question>)request.getAttribute("questions"); 
		TestActivityType testActivityType = (TestActivityType)request.getAttribute("testActivityType");
		QuestionsLearningActivityType questionActivityType = testActivityType;
		request.setAttribute("questionActivityType", questionActivityType);
		
		for(Question question: questions){
			request.setAttribute("question", question);%>
			<liferay-util:include page="<%=question.getQuestionType().getURLQuestion() %>" portletId="<%=question.getQuestionType().getQuestionTypeFactory().getPortletId() %>" servletContext="<%=application %>">
				<liferay-util:param name="feedback" value="true" />
			</liferay-util:include>
		<%} %>
	</c:if>
	
	<c:if test="${not empty tryTestURL }">
		<aui:button value="execativity.test.try.again" href="${tryTestURL }" />
	</c:if>
	<c:if test="${not empty improveURL }">
		<aui:button value="execativity.test.try.again.improve" href="${improveURL }"/>
	</c:if>
</div>