<%@page import="com.ted.lms.learning.activity.question.constants.QuestionsWebPortletKeys"%>
<%@page import="com.ted.lms.question.fillblank.FillblankQuestionTypeFactory"%>
<%@page import="com.ted.lms.learning.activity.question.model.Answer"%>
<%@page import="java.util.List"%>
<%@page import="com.ted.lms.learning.activity.question.service.AnswerLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@ include file="../../init.jsp" %>

<%
int iteratorQuestion = ParamUtil.getInteger(request, "iteratorQuestion", 0);
long questionId = ParamUtil.getLong(request, "questionId");
String editorName = ParamUtil.getString(request, "editorName");
String namespace = ParamUtil.getString(request, "namespaceAnswers");
System.out.println("namespace fill: " + namespace);

if(questionId > 0){
	List<Answer> answers =  AnswerLocalServiceUtil.getAnswersByQuestionId(questionId);
	int i=0;
	if(Validator.isNotNull(answers)){
		for(Answer answer:answers){
		i++;%>
		<div class="row" id='<%=themeDisplay.getPortletDisplay().getNamespace() + iteratorQuestion + "_div_answer_" + i%>'>
			<liferay-util:include page="<%=FillblankQuestionTypeFactory.URL_EDIT_ANSWER %>" portletId="<%=QuestionsWebPortletKeys.EDIT_QUESTIONS%>" servletContext="<%=this.getServletContext() %>">
				<liferay-util:param name="iterator" value="<%=String.valueOf(i) %>" />
				<liferay-util:param name="iteratorQuestion" value="<%=String.valueOf(iteratorQuestion) %>" />
				<liferay-util:param name="answerId" value="<%=String.valueOf(answer.getAnswerId())%>" />
				<liferay-util:param name="editorName" value="<%=editorName%>" />
				<liferay-util:param name="namespaceAnswer" value="<%=namespace%>" />
			</liferay-util:include>
		</div>
	<%
		}
	}
}else{%>
	<div class="row" id='<%=themeDisplay.getPortletDisplay().getNamespace() + iteratorQuestion + "_div_answer_1"%>'>
		<liferay-util:include page="<%=FillblankQuestionTypeFactory.URL_EDIT_ANSWER %>" portletId="<%=QuestionsWebPortletKeys.EDIT_QUESTIONS%>" servletContext="<%=this.getServletContext() %>">
			<liferay-util:param name="iterator" value="<%=String.valueOf(1) %>" />
			<liferay-util:param name="iteratorQuestion" value="<%=String.valueOf(iteratorQuestion) %>" />
			<liferay-util:param name="editorName" value="<%=editorName%>" />
			<liferay-util:param name="namespaceAnswer" value="<%=namespace%>" />
		</liferay-util:include>
	</div>
<%}
%>