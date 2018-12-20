<%@page import="com.ted.lms.learning.activity.question.service.AnswerLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="java.util.List"%>
<%@page import="com.ted.lms.learning.activity.question.model.Answer"%>
<%@ include file="/init.jsp" %>

<% 
long questionId = ParamUtil.getLong(request, "questionId");
List<Answer> answers =  AnswerLocalServiceUtil.getAnswersByQuestionId(questionId);
%>

<span class="question">
	<aui:input type="radio" id="includeSolution" name="includeSolution" label="includeSolution.yes" 
		value="y" onClick="${renderResponse.namespace}showHideSolution();" 
		checked='<%=answers != null && answers.size() > 0 %>'/>
	<aui:input type="radio" id="notIncludeSolution" name="includeSolution" label="includeSolution.no" 
		value="n" onClick="${renderResponse.namespace}showHideSolution();" 
		checked='<%=answers == null || answers.size() == 0 %>'/>
</span> 

<%
int i=0;
for(Answer answer:answers){
	i++;
	%>
	<div id="answer_<%=answer.getAnswerId() %>" class="row">
		<div class="col-md-12">
			<aui:input  type="hidden" name="answerId" value="<%=answer.getAnswerId() %>"></aui:input>
			<aui:input  type="hidden" name="iterator" value="1"></aui:input>
			<aui:input  type="hidden" name="<%=\"correct_\"+answer.getAnswerId() %>" label="correct" value="true"/>
			<aui:field-wrapper label="">
				<div class="container-textarea">
					<%String name=renderResponse.getNamespace()+"answer_"+answer.getAnswerId(); %>
					<textarea rows="10" cols="88" name="<%=name%>"><%=answer.getAnswer()%></textarea>
				</div>
			</aui:field-wrapper>
			<aui:input  name="<%=\"feedbackCorrect_\"+answer.getAnswerId() %>" label="feedbackCorrect" value="<%=answer.getFeedbackCorrect() %>" size="60"/>
			<aui:input  name="<%=\"feedbackNoCorrect_\"+answer.getAnswerId() %>" label="feedbackNoCorrect" value="<%=answer.getFeedbacknocorrect() %>" size="60"/>
		</div>
	</div>
<%
}
%>

<script type="text/javascript">
function <portlet:namespace />showHideSolution(){
	var A = AUI();
	if(A.one('input[id=<portlet:namespace />includeSolution]').attr('checked')) {
		A.one('.solution').show();
		A.one('.noSolution').hide();
		A.all('.leftSideAnswer input[id^="<portlet:namespace />correct_"]').val('true').attr('value', 'true');
	}else if(A.one('input[id=<portlet:namespace />notIncludeSolution]').attr('checked')) {
		A.one('.solution').hide();
		A.one('.noSolution').show();
		A.all('.leftSideAnswer input[id^="<portlet:namespace />correct_"]').val('false').attr('value', 'false');
	}
}

AUI().ready('aui-base',
   	function() {
		<portlet:namespace />showHideSolution();
   	}
);

</script>