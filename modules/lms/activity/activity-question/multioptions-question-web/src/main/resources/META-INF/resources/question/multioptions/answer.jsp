<%@page import="com.liferay.portal.kernel.util.JavaConstants"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.liferay.taglib.aui.AUIUtil"%>
<%@page import="javax.portlet.PortletResponse"%>
<%@page import="javax.portlet.PortletRequest"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.ted.lms.learning.activity.question.service.AnswerLocalServiceUtil"%>
<%@page import="com.ted.lms.learning.activity.question.model.Answer"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ include file="../../init.jsp" %>
<%
long iteratorQuestion = ParamUtil.getLong(request, "iteratorQuestion");
long iterator = ParamUtil.getLong(request, "iterator");
long answerId = ParamUtil.getLong(request, "answerId", 0);
String editorName = ParamUtil.getString(request, "editorName");
Answer answer = null;
if(answerId > 0){
	answer = AnswerLocalServiceUtil.getAnswer(answerId);
} 
String namespace = ParamUtil.getString(request, "namespaceAnswer", themeDisplay.getPortletDisplay().getNamespace());
System.out.println("namespace multioptions q: " + namespace);

PortletRequest portletRequest = (PortletRequest)request.getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);

PortletResponse portletResponse = (PortletResponse)request.getAttribute(JavaConstants.JAVAX_PORTLET_RESPONSE);

String namespaceEditor = AUIUtil.getNamespace(portletRequest, portletResponse);

if (Validator.isNull(namespaceEditor)) {
	namespaceEditor = AUIUtil.getNamespace(request);
}
%>
<aui:input  type="hidden" name='<%=namespace + iteratorQuestion + "_answerId_" + iterator%>' value="<%=answerId %>" useNamespace="false" />
<aui:input  type="hidden" name='<%=namespace + iteratorQuestion + "_iteratorAnswer" %>' id='<%=namespace + iteratorQuestion + "_iteratorAnswer" %>' value="<%=iterator%>" useNamespace="false"  />
<div class="col-md-10">
	
	<aui:input type="checkbox" name='<%=namespace + iteratorQuestion + "_correct"%>' label="correct" value="<%=iterator%>" checked="<%=answer != null ? answer.isCorrect() : false %>" 
				useNamespace="false" id='<%=namespace + iteratorQuestion + "_correct_" + iterator %>' />
	
	 <liferay-editor:editor
		contents='<%=answer != null ? answer.getAnswer() : "" %>' 
		name='<%=iteratorQuestion + "_answer_" + iterator %>'
		placeholder="write-here-answer"
		required="<%= true %>"
		editorName="<%=editorName %>"
		showSource="true" 
		onChangeMethod='<%=iteratorQuestion + "changeAnswer" + iterator %>'
	>
		<aui:validator name="required" />
	</liferay-editor:editor>
	<aui:input type="hidden" name='<%=namespace + iteratorQuestion + "_answer_title_" + iterator %>' useNamespace="false" value="<%=answer != null ? answer.getAnswer() : "" %>"/>
	
	
	<aui:input type="text" name="<%=namespace + iteratorQuestion + \"_feedbackCorrect_\"+iterator %>" label="feedback" value='<%=answer != null ? answer.getFeedbackCorrect(): "" %>' useNamespace="false" />	
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

<c:choose>
	<c:when test="<%=!namespaceEditor.equals(namespace) %>">
		<aui:script>
			document.getElementById("<%=namespaceEditor + iteratorQuestion + "_answer_" + iterator%>").addEventListener("mouseout",
				function(){
					$('#<%=namespace + iteratorQuestion%>_answer_title_<%=iterator%>').val(window.<%=namespaceEditor %><%=iteratorQuestion%>_answer_<%=iterator%>.getHTML());
				});
		</aui:script>
	</c:when>
	<c:otherwise>
		<script>
			function <portlet:namespace /><%=iteratorQuestion%>changeAnswer<%=iterator%>(val){
				$('#<%=namespace + iteratorQuestion%>_answer_title_<%=iterator%>').val(window.<portlet:namespace /><%=iteratorQuestion%>_answer_<%=iterator%>.getHTML());
			}
		</script>
	</c:otherwise>
</c:choose>