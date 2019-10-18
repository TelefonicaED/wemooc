<%@page import="java.util.List"%>
<%@page import="com.ted.lms.learning.activity.question.service.AnswerLocalServiceUtil"%>
<%@page import="com.ted.lms.learning.activity.question.model.Answer"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ include file="../../init.jsp" %>

<% 
int iteratorQuestion = ParamUtil.getInteger(request, "iteratorQuestion", 0);
long questionId = ParamUtil.getLong(request, "questionId");
String namespace = ParamUtil.getString(request, "namespaceAnswers");
System.out.println("namespace freetext: " + namespace);

List<Answer> answers = null;
if(questionId > 0){
	answers =  AnswerLocalServiceUtil.getAnswersByQuestionId(questionId);
}%>

<span class="question">
	<aui:input type="radio" id='<%=namespace + iteratorQuestion + "_include_solution"%>' 
			name='<%=namespace + iteratorQuestion + "includeSolution"%>' label="questions.free-text.include-solution.yes"  
			checked='<%=Validator.isNotNull(answers) && answers.size() > 0 %>'
			useNamespace="false"/>
			
	<aui:input type="radio" id='<%=namespace + iteratorQuestion + "_not_include_solution" %>' 
			name='<%=namespace + iteratorQuestion + "includeSolution"%>' label="questions.free-text.include-solution.no"
			checked='<%=Validator.isNull(answers) || answers.size() == 0  %>'
			useNamespace="false"/>
</span> 

<div id='<%=namespace + iteratorQuestion + "_solution"%>' class='<%=Validator.isNull(answers) ||  answers.size() == 0 ? "hide" : ""%>'>
	<%Answer answer = Validator.isNotNull(answers) && answers.size() > 0 ? answers.get(0) : null; %>
	<aui:input  type="hidden" name='<%=namespace + iteratorQuestion + "_answerId_0"%>' value="<%=answer != null ? answer.getAnswerId() : 0 %>" useNamespace="false" />
	<aui:input  type="hidden" name='<%=namespace + iteratorQuestion + "_iteratorAnswer" %>' id='<%=namespace + iteratorQuestion + "_iteratorAnswer" %>' value="0" useNamespace="false"  />
	
	<liferay-ui:panel title="answer" collapsible="true" extended="true" defaultState="open" persistState="false">
		<aui:input type="hidden" name='<%=namespace + iteratorQuestion + "_correct"%>' value="<%=answer != null %>"/>
		<aui:field-wrapper label="">
			<div class="container-textarea">
				<textarea name='<%=namespace + iteratorQuestion + "_answer_title_0"%>'>
					<%=answer != null ? answer.getAnswer() : "" %>
				</textarea>
			</div>
		</aui:field-wrapper>
		<aui:input name='<%=namespace + iteratorQuestion + "_feedbackCorrect" %>' label="question.feedback-correct" 
			value="" size="60" useNamespace="false" localized="true"/>
		<aui:input name='<%=namespace + iteratorQuestion + "_feedbackIncorrect" %>' label="question.feedback-incorrect" 
			value="" size="60" useNamespace="false" localized="true"/>
		
	</liferay-ui:panel>
</div>

<script >
	document.getElementById("<%=namespace + iteratorQuestion%>_include_solution").addEventListener("click",
			function(){
				if($('#<%=namespace + iteratorQuestion%>_solution').hasClass('hide')){
					$('#<%=namespace + iteratorQuestion%>_solution').removeClass("hide");
				}else{
					$('#<%=namespace + iteratorQuestion%>_solution').addClass("hide");
				}
	});

</script>