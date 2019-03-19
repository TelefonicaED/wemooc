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
String namespace = ParamUtil.getString(request, "namespace", renderResponse.getNamespace()); 

List<Answer> answers = null;
if(questionId > 0){
	answers =  AnswerLocalServiceUtil.getAnswersByQuestionId(questionId);
}%>

<span class="question">
	<aui:input type="radio" id='<%=namespace + iteratorQuestion + "_include_solution"%>' 
			name='<%=namespace + iteratorQuestion + "includeSolution"%>' label="questions.free-text.include-solution.yes" value="y" 
			onClick='<%= renderResponse.getNamespace() + "showHideSolution();" %>' checked='<%=Validator.isNotNull(answers) && answers.size() > 0 %>'/>
			
	<aui:input type="radio" id='<%=namespace + iteratorQuestion + "_not_include_solution"%>' 
			name='<%=namespace + iteratorQuestion + "includeSolution"%>' label="questions.free-text.include-solution.no" value="n" 
			onClick='<%= renderResponse.getNamespace() + "showHideSolution();" %>' checked='<%=Validator.isNull(answers) || answers.size() == 0  %>'/>
</span> 

<div id='<%=namespace + iteratorQuestion + "_solution"%>'>
	<%Answer answer = Validator.isNotNull(answers) && answers.size() > 0 ? answers.get(0) : null; %>
	<liferay-ui:panel title="answer" collapsible="true" extended="true" defaultState="open" persistState="false">
		<aui:input type="hidden" name='<%=namespace + iteratorQuestion + "_correct"%>' value="<%=answer != null %>"/>
		<aui:field-wrapper label="">
			<div class="container-textarea">
				<textarea rows="10" cols="88" name='<%=namespace + iteratorQuestion + "_answer"%>'>
					<%=answer != null ? answer.getAnswer() : "" %>
				</textarea>
			</div>
		</aui:field-wrapper>
		<aui:input name='<%=namespace + iteratorQuestion + "_feedbackCorrect" %>' label="question.feedback-correct" 
			value="" size="60" useNamespace="false" localized="true"/>
		<aui:input name='<%=namespace + iteratorQuestion + "feedbackIncorrect" %>' label="question.feedback-incorrect" 
			value="" size="60" useNamespace="false" localized="true"/>
		
	</liferay-ui:panel>
</div>

<script type="text/javascript">
function <portlet:namespace />showHideSolution(){
	var A = AUI();
	if($('#<%=namespace + iteratorQuestion%>_include_solution').attr('checked')) {
		$('#<%=namespace + iteratorQuestion%>_solution').show();
		A.all('input[id="<%=namespace + iteratorQuestion%>_correct"]').val('true').attr('value', 'true');
	}else if($('#<%=namespace + iteratorQuestion%>not_include_solution').attr('checked')) {
		$('#<%=namespace + iteratorQuestion%>_solution').hide();
		A.all('input[id="<%=namespace + iteratorQuestion%>_correct"]').val('false').attr('value', 'false');
	}
}

AUI().ready('aui-base',
   	function() {
		<portlet:namespace />showHideSolution();
   	}
);

</script>
