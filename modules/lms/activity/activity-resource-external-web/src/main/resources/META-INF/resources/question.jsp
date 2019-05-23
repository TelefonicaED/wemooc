<%@page import="com.ted.lms.learning.activity.QuestionsLearningActivityType"%>
<%@page import="com.ted.lms.learning.activity.question.model.Question"%>
<%@page import="java.util.List"%>
<%@ include file="init.jsp" %>

<div class="container-activity isFeedback">
	<%Question question = (Question)request.getAttribute("question"); 
%>	
	<liferay-util:include page="<%=question.getQuestionType().getURLQuestion() %>" portletId="<%=question.getQuestionType().getQuestionTypeFactory().getPortletId() %>" >
		<liferay-util:param name="questionId" value="<%=String.valueOf(question.getQuestionId()) %>"/>
		<liferay-util:param name="tryResultData" value="${tryResultData }" />
		<liferay-util:param name="showCorrectAnswer" value="true"/>
		<liferay-util:param name="feedback" value="true" />
	</liferay-util:include>
</div>