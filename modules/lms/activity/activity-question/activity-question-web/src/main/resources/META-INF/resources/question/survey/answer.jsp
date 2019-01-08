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

<liferay-ui:panel title='<%=iterator+")" %>' collapsible="true" extended="true" defaultState="open" persistState="false">
	<aui:model-context bean="<%=answer %>" model="<%= Answer.class %>" />
	
	<div class="col-md-10">
		<liferay-ui:input-localized
			cssClass="form-control"
			formName="fm"
			name='<%=namespace + iteratorQuestion + "_answer_" + iterator %>'
			placeholder="write-here-answer"
			type="editor"
			xml='<%=answer != null ? answer.getAnswerMapAsXML() : "" %>' 
			useNamespace="false"
		/>		
	</div>
	<div class="col-md-2">
		<span class="newitem2"><a href="#" class="newitem2" onclick="<portlet:namespace />deleteNode('testAnswer_new<%=iterator %>');"><liferay-ui:message key="delete"/></a></span>
	</div>
</liferay-ui:panel>