<%@page import="com.ted.lms.learning.activity.question.service.AnswerLocalServiceUtil"%>
<%@page import="com.ted.lms.learning.activity.question.model.Answer"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ include file="../../init.jsp" %>
<%
long iteratorQuestion = ParamUtil.getLong(request, "iteratorQuestion");
long iterator = ParamUtil.getLong(request, "iterator");
String namespace = ParamUtil.getString(request, "namespace", renderResponse.getNamespace());
long answerId = ParamUtil.getLong(request, "answerId", 0);
Answer answer = null;
if(answerId > 0){
	answer = AnswerLocalServiceUtil.getAnswer(answerId);
}

System.out.println("options:answer.jps::iteratorQuestion: " + iteratorQuestion);
System.out.println("options:answer.jps::iterator: " + iterator);
System.out.println("options:answer.jps::namespace: " + namespace);
%>
<aui:input  type="hidden" name='<%=namespace + iteratorQuestion + "_answerId" %>' value="<%=answerId %>" useNamespace="false" />
<aui:input  type="hidden" name='<%=namespace + iteratorQuestion + "_iterator" %>' value="<%=iterator%>" useNamespace="false"  />
<div class="col-md-10">

	<aui:model-context bean="<%=answer %>" model="<%= Answer.class %>" />
	
	<aui:input type="radio" name='<%=namespace + iteratorQuestion + "_correct_new"%>' label="correct" value="<%=iterator%>" checked="<%=answer != null ? answer.isCorrect() : false %>" 
				useNamespace="false" id='<%=namespace + iteratorQuestion + "_correct_new_" + iterator %>'/>
	
	<liferay-ui:input-localized
			cssClass="form-control"
			formName="fm"
			name='<%=namespace + iteratorQuestion + "_answer_" + iterator %>'
			placeholder="write-here-answer"
			type="editor"
			xml='<%=answer != null ? answer.getAnswerMapAsXML() : "" %>' 
			useNamespace="false"
		/>		
	
	<aui:input name="<%=namespace + iteratorQuestion + \"feedbackCorrect_new\"+iterator %>" label="feedback" value="" size="60" useNamespace="false" localized="true"/>
</div>
<div class="col-md-2">
	<span class="newitem2"><a href="#" class="newitem2" onclick="<portlet:namespace />deleteNode('testAnswer_new<%=iterator %>');"><liferay-ui:message key="delete"/></a></span>
</div>