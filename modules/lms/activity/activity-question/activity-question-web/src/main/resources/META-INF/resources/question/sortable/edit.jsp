<%@page import="com.liferay.portal.kernel.util.HtmlUtil"%>
<%@page import="com.ted.lms.learning.activity.question.service.AnswerLocalServiceUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ted.lms.learning.activity.question.model.Answer"%>
<%@page import="java.util.List"%>
<%@page import="com.ted.lms.learning.activity.question.service.QuestionLocalServiceUtil"%>
<%@page import="com.ted.lms.learning.activity.question.model.Question"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ include file="/init.jsp" %>


<% 
long questionId = ParamUtil.getLong(request,"questionId", 0);

Question question = null;
List<Answer> answers = null;
if(questionId > 0){
	question = QuestionLocalServiceUtil.getQuestion(questionId);
	answers = AnswerLocalServiceUtil.getAnswersByQuestionId(questionId);
}else{
	answers = new ArrayList<Answer>();
}%>
<span class="solution" id="${renderResponse.namespace }span_question_<%=questionId%>">

<%int index = 0;
int i=0;
for(Answer answer:answers){
	i++;
%>
	<div id="answer_<%=answer.getAnswerId() %>" class="row">			
		<div class="col-md-10">
			<aui:input  type="hidden" name="answerId" value="<%=answer.getAnswerId() %>"></aui:input>
			<aui:input  type="hidden" name="iterator" value="<%=i%>"></aui:input>
			
			<%index = index +1;%>
			<aui:input type="radio" name="correct_new" label="correct" value="<%=index%>" checked="<%=answer.isCorrect() %>"></aui:input>
			
			<liferay-ui:input-localized
				cssClass="form-control"
				editorName="alloyeditor"
				formName="fm"
				name='<%="answer_" + answer.getAnswerId() %>'
				placeholder="description"
				type="editor"
				xml=''
			/>
			
		    <div id="<portlet:namespace />feedBackError_<%=answer.getAnswerId()%>" class="aui-helper-hidden portlet-msg-error">
				<liferay-ui:message key="feedback-maxlength"/>
			</div>
			<aui:input name="<%=\"feedbackCorrect_\"+answer.getAnswerId() %>" label="feedback" value="<%=answer.getFeedbackCorrect() %>" size="60"/>
		</div>
		<div class="col-md-2">
			<span class="newitem2"><a href="#" class="newitem2" onclick="<portlet:namespace />deleteNode('testAnswer_<%=answer.getAnswerId() %>');"><liferay-ui:message key="delete"/></a></span>
		</div>
	</div>
<%}
%>
Questionssss
</span>