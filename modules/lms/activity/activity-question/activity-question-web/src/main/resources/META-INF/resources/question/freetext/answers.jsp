<%@page import="com.ted.lms.learning.activity.question.constants.QuestionsWebPortletKeys"%>
<%@page import="java.util.List"%>
<%@page import="com.ted.lms.learning.activity.question.service.AnswerLocalServiceUtil"%>
<%@page import="com.ted.lms.learning.activity.question.model.Answer"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ include file="../../init.jsp" %>

<% 
int iteratorQuestion = ParamUtil.getInteger(request, "iteratorQuestion", 0);
long questionId = ParamUtil.getLong(request, "questionId");

List<Answer> answers = null;
if(questionId > 0){
	answers =  AnswerLocalServiceUtil.getAnswersByQuestionId(questionId);
}%>

<span class="question">
	<aui:input type="radio" id='<%=themeDisplay.getPortletDisplay().getNamespace() + iteratorQuestion + "_include_solution"%>' 
			name='<%=themeDisplay.getPortletDisplay().getNamespace() + iteratorQuestion + "includeSolution"%>' label="questions.free-text.include-solution.yes"  
			onClick='<%= themeDisplay.getPortletDisplay().getNamespace() + "showHideSolution" + iteratorQuestion + "();" %>' checked='<%=Validator.isNotNull(answers) && answers.size() > 0 %>'
			useNamespace="false"/>
			
	<aui:input type="radio" id='<%=themeDisplay.getPortletDisplay().getNamespace() + iteratorQuestion + "_not_include_solution" %>' 
			name='<%=themeDisplay.getPortletDisplay().getNamespace() + iteratorQuestion + "includeSolution"%>' label="questions.free-text.include-solution.no"
			onClick='<%= themeDisplay.getPortletDisplay().getNamespace() + "showHideSolution" + iteratorQuestion + "();" %>' checked='<%=Validator.isNull(answers) || answers.size() == 0  %>'
			useNamespace="false"/>
</span> 

<div id='<%=themeDisplay.getPortletDisplay().getNamespace() + iteratorQuestion + "_solution"%>' class='<%=Validator.isNull(answers) ||  answers.size() == 0 ? "hide" : ""%>'>
	<%Answer answer = Validator.isNotNull(answers) && answers.size() > 0 ? answers.get(0) : null; %>
	<liferay-ui:panel title="answer" collapsible="true" extended="true" defaultState="open" persistState="false">
		<aui:input type="hidden" name='<%=themeDisplay.getPortletDisplay().getNamespace() + iteratorQuestion + "_correct"%>' value="<%=answer != null %>"/>
		<aui:field-wrapper label="">
			<div class="container-textarea">
				<textarea name='<%=themeDisplay.getPortletDisplay().getNamespace() + iteratorQuestion + "_answer"%>'>
					<%=answer != null ? answer.getAnswer() : "" %>
				</textarea>
			</div>
		</aui:field-wrapper>
		<aui:input name='<%=themeDisplay.getPortletDisplay().getNamespace() + iteratorQuestion + "_feedbackCorrect" %>' label="question.feedback-correct" 
			value="" size="60" useNamespace="false" localized="true"/>
		<aui:input name='<%=themeDisplay.getPortletDisplay().getNamespace() + iteratorQuestion + "_feedbackIncorrect" %>' label="question.feedback-incorrect" 
			value="" size="60" useNamespace="false" localized="true"/>
		
	</liferay-ui:panel>
</div>

<script type="text/javascript">
function <portlet:namespace />showHideSolution<%=iteratorQuestion%>(){
	if($('#<portlet:namespace /><%=iteratorQuestion%>_solution').hasClass('hide')){
		$('#<portlet:namespace /><%=iteratorQuestion%>_solution').removeClass("hide");
	}else{
		$('#<portlet:namespace /><%=iteratorQuestion%>_solution').addClass("hide");
	}
}

</script>
