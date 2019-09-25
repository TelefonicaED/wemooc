<%@page import="com.ted.lms.question.multioptions.constants.MultioptionsWebPortletKeys"%>
<%@page import="com.ted.lms.question.multioptions.MultioptionsQuestionTypeFactory"%>
<%@page import="com.ted.lms.question.options.OptionsQuestionTypeFactory"%>
<%@page import="javax.portlet.RenderResponse"%>
<%@page import="com.ted.lms.learning.activity.question.service.AnswerLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.ted.lms.learning.activity.question.model.Answer"%>
<%@page import="java.util.List"%>
<%@ include file="../../init.jsp" %>


<% 
int iteratorQuestion = ParamUtil.getInteger(request, "iteratorQuestion", 0);
long questionId = ParamUtil.getLong(request, "questionId");
String editorName = ParamUtil.getString(request, "editorName");

if(questionId > 0){
	List<Answer> answers =  AnswerLocalServiceUtil.getAnswersByQuestionId(questionId);
	int i=0;
	for(Answer answer:answers){
		i++;%>
		<div class="row" id='<%=themeDisplay.getPortletDisplay().getNamespace() + iteratorQuestion + "_div_answer_" + i%>'>
			<liferay-util:include page="<%=MultioptionsQuestionTypeFactory.URL_EDIT_ANSWER %>" portletId="<%=MultioptionsWebPortletKeys.MULTIOPTIONS%>" servletContext="<%=this.getServletContext() %>">
				<liferay-util:param name="iterator" value="<%=String.valueOf(i) %>" />
				<liferay-util:param name="iteratorQuestion" value="<%=String.valueOf(iteratorQuestion) %>" />
				<liferay-util:param name="answerId" value="<%=String.valueOf(answer.getAnswerId())%>" />
				<liferay-util:param name="editorName" value="<%=editorName%>" />
			</liferay-util:include>
		</div>
	<%}
}else{
	for(int i = 1; i <= 2; i++){%>
		<div class="row" id='<%=themeDisplay.getPortletDisplay().getNamespace() + iteratorQuestion + "_div_answer_" + i%>'>
			<liferay-util:include page="<%=MultioptionsQuestionTypeFactory.URL_EDIT_ANSWER %>" portletId="<%=MultioptionsWebPortletKeys.MULTIOPTIONS%>" servletContext="<%=this.getServletContext() %>">
				<liferay-util:param name="iterator" value="<%=String.valueOf(i) %>" />
				<liferay-util:param name="iteratorQuestion" value="<%=String.valueOf(iteratorQuestion) %>" />
				<liferay-util:param name="editorName" value="<%=editorName%>" />
			</liferay-util:include>
		</div>
<%	}
}
%>