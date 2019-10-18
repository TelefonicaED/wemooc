<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.liferay.taglib.aui.AUIUtil"%>
<%@page import="javax.portlet.PortletResponse"%>
<%@page import="com.liferay.portal.kernel.util.JavaConstants"%>
<%@page import="javax.portlet.PortletRequest"%>
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
System.out.println("namespace fillblank q: " + namespace);

PortletRequest portletRequest = (PortletRequest)request.getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);

PortletResponse portletResponse = (PortletResponse)request.getAttribute(JavaConstants.JAVAX_PORTLET_RESPONSE);

String namespaceEditor = AUIUtil.getNamespace(portletRequest, portletResponse);

if (Validator.isNull(namespaceEditor)) {
	namespaceEditor = AUIUtil.getNamespace(request);
}
%>
<aui:input  type="hidden" name='<%=namespace + iteratorQuestion + "_answerId_" + iterator%>' value="<%=answerId %>" useNamespace="false" />
<aui:input  type="hidden" name='<%=namespace + iteratorQuestion + "_iteratorAnswer" %>' id='<%=namespace + iteratorQuestion + "_iteratorAnswer" %>' value="<%=iterator%>" useNamespace="false"  />

<div class="col-md-12">
	
	<liferay-editor:editor
		name='<%=iteratorQuestion + "_answer_" + iterator %>'
		placeholder="write-here-answer"
		contents='<%=answer != null ? answer.getAnswer() : "" %>' 
		required="<%= true %>"
		editorName="<%=editorName %>"
		showSource="true" 
		onChangeMethod='<%=iteratorQuestion + "changeAnswer" + iterator %>' 
		/>	
		
	<aui:input type="hidden" name='<%=namespace + iteratorQuestion + "_answer_title_" + iterator %>' useNamespace="false" value="<%=answer != null ? answer.getAnswer() : "" %>"/>
		
	<aui:input name="<%=namespace + iteratorQuestion + \"_feedbackCorrect_\"+iterator %>" label="question.feedback-correct" value='<%=answer != null ? answer.getFeedbackCorrect(): "" %>' useNamespace="false" />
	<aui:input name="<%=namespace + iteratorQuestion + \"_feedbackIncorrect_\"+iterator %>" label="question.feedback-incorrect" value='<%=answer != null ? answer.getFeedbackIncorrect(): "" %>' useNamespace="false" />

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