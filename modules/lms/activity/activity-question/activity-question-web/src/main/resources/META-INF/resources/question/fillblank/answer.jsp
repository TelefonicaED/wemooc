<%@page import="com.ted.lms.learning.activity.question.service.AnswerLocalServiceUtil"%>
<%@page import="com.ted.lms.learning.activity.question.model.Answer"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ include file="../../init.jsp" %>
<%
long iteratorQuestion = ParamUtil.getLong(request, "iteratorQuestion");
long iterator = ParamUtil.getLong(request, "iterator");
long answerId = ParamUtil.getLong(request, "answerId", 0);
Answer answer = null;
if(answerId > 0){
	answer = AnswerLocalServiceUtil.getAnswer(answerId);
}
%>
<aui:input  type="hidden" name='<%=themeDisplay.getPortletDisplay().getNamespace() + iteratorQuestion + "_answerId_" + iterator%>' value="<%=answerId %>" useNamespace="false" />
<aui:input  type="hidden" name='<%=themeDisplay.getPortletDisplay().getNamespace() + iteratorQuestion + "_iteratorAnswer" %>' id='<%=themeDisplay.getPortletDisplay().getNamespace() + iteratorQuestion + "_iteratorAnswer" %>' value="<%=iterator%>" useNamespace="false"  />

<div class="col-md-12">
	
	<liferay-editor:editor
		name='<%=iteratorQuestion + "_answer_" + iterator %>'
		placeholder="write-here-answer"
		contents='<%=answer != null ? answer.getAnswer() : "" %>' 
		required="<%= true %>"
		editorName="alloyeditor"
		showSource="true" 
		onChangeMethod='<%=iteratorQuestion + "changeAnswer" + iterator %>'
		/>	
		
	<aui:input type="hidden" name='<%=themeDisplay.getPortletDisplay().getNamespace() + iteratorQuestion + "_answer_title_" + iterator %>' useNamespace="false" value="<%=answer != null ? answer.getAnswer() : "" %>"/>
		
	<aui:input name="<%=themeDisplay.getPortletDisplay().getNamespace() + iteratorQuestion + \"_feedbackCorrect_\"+iterator %>" label="question.feedback-correct" value='<%=answer != null ? answer.getFeedbackCorrect(): "" %>' useNamespace="false" />
	<aui:input name="<%=themeDisplay.getPortletDisplay().getNamespace() + iteratorQuestion + \"feedbackIncorrect_\"+iterator %>" label="question.feedback-incorrect" value='<%=answer != null ? answer.getFeedbackIncorrect(): "" %>' useNamespace="false" />

</div>	

<script>
	function <portlet:namespace /><%=iteratorQuestion%>changeAnswer<%=iterator%>(val){
		$('#<portlet:namespace /><%=iteratorQuestion%>_answer_title_<%=iterator%>').val(val);
	}
</script>