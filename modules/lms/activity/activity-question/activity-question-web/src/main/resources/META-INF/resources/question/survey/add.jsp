<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ include file="/init.jsp" %>
<%
Long iterator = ParamUtil.getLong(request, "iterator", -1);
%>
<div class="row">
	<div class="col-md-10">
		<aui:input  type="hidden" name="answerId" value="<%=\"new\"+iterator %>"></aui:input>
		<aui:input  type="hidden" name="iterator" value="<%=iterator%>"></aui:input>
		
		<aui:input type="radio" name="correct_new" label="correct" value="<%=iterator%>"></aui:input>	
		
		<liferay-ui:input-localized
				cssClass="form-control"
				editorName="alloyeditor"
				formName="fm"
				name='<%="answer_" + iterator %>'
				placeholder="description"
				type="editor"
				xml=''
			/>		
		
	    <div id="<portlet:namespace />feedBackError_new<%=iterator%>" class="aui-helper-hidden portlet-msg-error">
			<liferay-ui:message key="feedback-maxlength"/>
		</div>
		<aui:input name="<%=\"feedbackCorrect_new\"+iterator %>" label="feedback" value="" size="60"/>
	</div>
	<div class="col-md-2">
		<span class="newitem2"><a href="#" class="newitem2" onclick="<portlet:namespace />deleteNode('testAnswer_new<%=iterator %>');"><liferay-ui:message key="delete"/></a></span>
	</div>
</div>