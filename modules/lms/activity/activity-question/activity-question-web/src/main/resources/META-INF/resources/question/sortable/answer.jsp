<%@page import="com.liferay.petra.string.StringPool"%>
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
String namespace = ParamUtil.getString(request, "namespace", themeDisplay.getPortletDisplay().getNamespace());
%>

<aui:input  type="hidden" name='<%=namespace + iteratorQuestion + "_answerId_" + iterator%>' value="<%=answerId %>" useNamespace="false" />
<aui:input  type="hidden" name='<%=namespace + iteratorQuestion + "_iteratorAnswer" %>' id='<%=namespace + iteratorQuestion + "_iteratorAnswer" %>' value="<%=iterator%>" useNamespace="false"  />

<div class="col-md-10">		
	
	<liferay-editor:editor
		contents='<%=answer != null ? answer.getAnswer() : "" %>' 
		name='<%=iteratorQuestion + "_answer_" + iterator %>'
		placeholder="write-here-answer"
		required="<%= true %>"
		editorName="alloyeditor"
		showSource="true" 
		onChangeMethod='<%=iteratorQuestion + "changeAnswer" + iterator %>'
	>
		<aui:validator name="required" />
	</liferay-editor:editor>
	
	<aui:input type="hidden" name='<%=namespace + iteratorQuestion + "_answer_title_" + iterator %>' useNamespace="false" value="<%=answer != null ? answer.getAnswer() : "" %>"/>
</div>
<div class="col-md-2">
	<liferay-ui:icon-menu
		cssClass='entry-options inline'
		direction="left-side"
		icon="<%= StringPool.BLANK %>"
		markupView="lexicon"
		message="<%= StringPool.BLANK %>"
		showWhenSingleIcon="true"
	>
		<liferay-ui:icon-delete
			label="true"
			url='<%="javascript:" + namespace + "deleteAnswer(" + iteratorQuestion + "," + iterator + ")" %>'
			confirmation="question.delete-question.confirm"
		/>
	</liferay-ui:icon-menu>
</div>

<script>
	function <portlet:namespace /><%=iteratorQuestion%>changeAnswer<%=iterator%>(val){
		$('#<%=namespace + iteratorQuestion%>_answer_title_<%=iterator%>').val(val);
	}
</script>
	