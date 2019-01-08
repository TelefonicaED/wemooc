<%@page import="com.ted.lms.learning.activity.question.constants.QuestionsWebPortletKeys"%>
<%@page import="com.ted.lms.learning.activity.question.SurveyQuestionTypeFactory"%>
<%@page import="com.ted.lms.learning.activity.question.service.AnswerLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.ted.lms.learning.activity.question.model.Answer"%>
<%@page import="java.util.List"%>
<%@ include file="../../init.jsp" %>

<% 
int iteratorQuestion = ParamUtil.getInteger(request, "iteratorQuestion", 0);
long questionId = ParamUtil.getLong(request, "questionId");
String namespace = ParamUtil.getString(request, "namespace", renderResponse.getNamespace());

if(questionId > 0){
	List<Answer> answers =  AnswerLocalServiceUtil.getAnswersByQuestionId(questionId);
	int i=0;
	for(Answer answer:answers){
		i++;%>
		<div class="row" id='<%=namespace + iteratorQuestion + "_answer_" + i%>'>
			<liferay-util:include page="<%=SurveyQuestionTypeFactory.URL_EDIT_ANSWER %>" portletId="<%=QuestionsWebPortletKeys.EDIT_QUESTIONS%>" servletContext="<%=this.getServletContext() %>">
				<liferay-util:param name="iterator" value="<%=String.valueOf(i) %>" />
				<liferay-util:param name="iteratorQuestion" value="<%=String.valueOf(iteratorQuestion) %>" />
				<liferay-util:param name="namespace" value="<%=namespace%>" />
				<liferay-util:param name="answerId" value="<%=String.valueOf(answer.getAnswerId())%>" />
			</liferay-util:include>
		</div>
	<%}
}else{
	for(int i = 1; i <= 2; i++){%>
		<div class="row" id='<%=namespace + iteratorQuestion + "_answer_" + i%>'>
			<liferay-util:include page="<%=SurveyQuestionTypeFactory.URL_EDIT_ANSWER %>" portletId="<%=QuestionsWebPortletKeys.EDIT_QUESTIONS%>" servletContext="<%=this.getServletContext() %>">
				<liferay-util:param name="iterator" value="<%=String.valueOf(i) %>" />
				<liferay-util:param name="iteratorQuestion" value="<%=String.valueOf(iteratorQuestion) %>" />
				<liferay-util:param name="namespace" value="<%=namespace%>" />
			</liferay-util:include>
		</div>
<%	}
}
%>