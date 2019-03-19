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
%>

<aui:input  type="hidden" name='<%=namespace + iteratorQuestion + "_answerId" %>' value="<%=answerId %>" useNamespace="false" />
<aui:input  type="hidden" name='<%=namespace + iteratorQuestion + "_iterator" %>' value="<%=iterator%>" useNamespace="false"  />

<liferay-ui:panel title='<%="(" + iterator+")"%>' collapsible="true" extended="true" defaultState="open" persistState="false">
	<div class="col-md-10">		
		<aui:model-context bean="<%=answer %>" model="<%= Answer.class %>" />
		
		<liferay-editor:editor
			contents='<%=answer != null ? answer.getAnswer() : "" %>' 
			name='<%=namespace + iteratorQuestion + "_answer_" + iterator %>'
			placeholder="write-here-answer"
			required="<%= true %>"
		>
			<aui:validator name="required" />
		</liferay-editor:editor>
	</div>
	<div class="col-md-2">
		<span class="newitem2"><a href="#" class="newitem2" onclick="<portlet:namespace />deleteNode('testAnswer_new<%=iterator %>');"><liferay-ui:message key="delete"/></a></span>
	</div>
</liferay-ui:panel>
	