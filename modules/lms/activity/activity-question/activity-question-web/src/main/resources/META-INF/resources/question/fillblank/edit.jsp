<%@page import="com.ted.lms.learning.activity.question.service.AnswerLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.ted.lms.learning.activity.question.model.Answer"%>
<%@page import="java.util.List"%>
<%@ include file="/init.jsp" %>

<% 
long questionId = ParamUtil.getLong(request, "questionId");
List<Answer> answers =  AnswerLocalServiceUtil.getAnswersByQuestionId(questionId);
%>

<%
int i=0;
for(Answer answer:answers){
	i++;
	%>
	<div id="answer_<%=answer.getAnswerId() %>" class="row">
	
		<div class="col-md-10">
			<aui:input  type="hidden" name="answerId" value="<%=answers.get(i).getAnswerId() %>"></aui:input>
			<aui:input  type="hidden" name="iterator" value="<%=i%>"></aui:input>
			<aui:input  type="hidden" name="<%=\"correct_\"+answers.get(i).getAnswerId() %>" label="correct" value="true"/>
				
			<liferay-ui:input-localized
				cssClass="form-control"
				editorName="alloyeditor"
				formName="fm"
				name='<%="answer_" + answers.get(i).getAnswerId() %>'
				placeholder="write-here-answer"
				type="editor"
				xml=''
			/>	
			
			<aui:input  name="<%=\"feedbackCorrect_\"+answers.get(i).getAnswerId() %>" label="feedbackCorrect" 
				value="<%=answers.get(i).getFeedbackCorrect() %>" size="60"/>
			<aui:input  name="<%=\"feedbackNoCorrect_\"+answers.get(i).getAnswerId() %>" label="feedbackNoCorrect" 
				value="<%=answers.get(i).getFeedbacknocorrect() %>" size="60"/>
		</div>
		<div class="col-md-2">
			<span class="newitem2">
				<a href="#" class="newitem2" onclick="<portlet:namespace />deleteNode('answer_<%=answers.get(i).getAnswerId() %>');">
					<liferay-ui:message key="delete"/>
				</a>
			</span>
		</div>
	</div>
<%}
%>