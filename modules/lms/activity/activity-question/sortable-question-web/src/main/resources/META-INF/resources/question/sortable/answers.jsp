<%@page import="com.ted.lms.question.sortable.constants.SortableWebPortletKeys"%>
<%@page import="com.ted.lms.question.sortable.SortableQuestionTypeFactory"%>
<%@page import="com.ted.lms.learning.activity.question.service.AnswerLocalServiceUtil"%>
<%@page import="com.ted.lms.learning.activity.question.model.Answer"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ include file="../../init.jsp" %>

<% 
int iteratorQuestion = ParamUtil.getInteger(request, "iteratorQuestion", 0);
long questionId = ParamUtil.getLong(request, "questionId");
String editorName = ParamUtil.getString(request, "editorName");
String namespace = ParamUtil.getString(request, "namespaceAnswers");
System.out.println("namespace sortable: " + namespace);

if(questionId > 0){
	List<Answer> answers =  AnswerLocalServiceUtil.getAnswersByQuestionId(questionId);
	int i=0;
	for(Answer answer:answers){
		i++;%>
		<div class="row" id='<%=themeDisplay.getPortletDisplay().getNamespace() + iteratorQuestion + "_div_answer_" + i%>'>
			<liferay-util:include page="<%=SortableQuestionTypeFactory.URL_EDIT_ANSWER %>" portletId="<%=SortableWebPortletKeys.SORTABLE%>" servletContext="<%=this.getServletContext() %>">
				<liferay-util:param name="iterator" value="<%=String.valueOf(i) %>" />
				<liferay-util:param name="iteratorQuestion" value="<%=String.valueOf(iteratorQuestion) %>" />
				<liferay-util:param name="answerId" value="<%=String.valueOf(answer.getAnswerId())%>" />
				<liferay-util:param name="editorName" value="<%=editorName%>" />
				<liferay-util:param name="namespaceAnswer" value="<%=namespace%>" />
			</liferay-util:include>
		</div>
	<%}
}else{
	for(int i = 1; i <= 2; i++){%>
		<div class="row" id='<%=themeDisplay.getPortletDisplay().getNamespace() + iteratorQuestion + "_div_answer_" + i%>'>
			<liferay-util:include page="<%=SortableQuestionTypeFactory.URL_EDIT_ANSWER %>" portletId="<%=SortableWebPortletKeys.SORTABLE%>" servletContext="<%=this.getServletContext() %>">
				<liferay-util:param name="iterator" value="<%=String.valueOf(i) %>" />
				<liferay-util:param name="iteratorQuestion" value="<%=String.valueOf(iteratorQuestion) %>" />
				<liferay-util:param name="editorName" value="<%=editorName%>" />
				<liferay-util:param name="namespaceAnswer" value="<%=namespace%>" />
			</liferay-util:include>
		</div>
<%	}
}
%>		
