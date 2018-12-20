<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ include file="/init.jsp" %>
<%
Long iterator = ParamUtil.getLong(request, "iterator", -1);
%>
<div class="row">
	<div class="col-md-10">
		<aui:input  type="hidden" name="answerId" value="<%=\"new\"+iterator %>"></aui:input>
		<aui:input  type="hidden" name="iterator" value="<%=iterator%>"></aui:input>
		<aui:input  type="hidden" name="<%=\"correct_new\"+iterator %>" label="correct" value="true"/>	
				
		<liferay-ui:input-localized
			cssClass="form-control"
			editorName="alloyeditor"
			formName="fm"
			name='<%="answer_" + iterator %>'
			placeholder="write-here-answer"
			type="editor"
			xml=''
		/>	
			
		<aui:input name="<%=\"feedbackCorrect_new\"+iterator %>" label="feedbackCorrect" value="" size="60"/>
		<aui:input name="<%=\"feedbackNoCorrect_new\"+iterator %>" label="feedbackNoCorrect" value="" size="60"/>
	</div>
	<div class="col-md-2">
		<span class="newitem2"><a href="#" class="newitem2" onclick="<portlet:namespace />deleteNode('testAnswer_new<%=iterator %>');"><liferay-ui:message key="delete"/></a></span>
	</div>
</div>
	