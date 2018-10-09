<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.ted.lms.model.ModuleEvalFactory"%>
<%@page import="com.ted.lms.model.Module"%>
<%@ include file="../init.jsp" %>

<liferay-ui:error-marker
	key="<%= WebKeys.ERROR_SECTION %>"
	value="evaluation"
/>

<aui:model-context bean="${module }" model="<%= Module.class %>" />

<c:if test="${not empty listModuleEvalFactories }" >
	<aui:select name="moduleEvalId" onChange="javascript:${renderResponse.getNamespace()}changeModuleEval(this);" label="module.module-evaluation">
		<c:forEach items="${listModuleEvalFactories}" var="moduleEvalFactory">
			<aui:option selected="${moduleEvalFactory.type == module.moduleEvalId}" label="${moduleEvalFactory.getTitle(themeDisplay.locale)}" 
				value="${moduleEvalFactory.type}"/>
		</c:forEach>
	</aui:select>
	<c:forEach items="${listModuleEvalFactories}" var="moduleEvalFactory">
		<div class="${moduleEvalFactory.type!= module.moduleEvalId ? 'aui-helper-hidden' : '' }" id="${renderResponse.getNamespace()}module_eval_${moduleEvalFactory.type}">
			<c:if test="${not empty moduleEvalFactory.getURLSpecificContent()}">
				<liferay-util:include page="${moduleEvalFactory.getURLSpecificContent()}" portletId="${moduleEvalFactory.getPortletId()}" >
					<liferay-util:param name="moduleId" value="${moduleId }" />
					<liferay-util:param name="groupId" value="${themeDisplay.scopeGroupId }" />
				</liferay-util:include>
			</c:if>
		</div>
	</c:forEach>
	<aui:script>
		function <portlet:namespace />changeModuleEval(moduleEval){
			$('*[id^="<portlet:namespace />module_eval_"]').addClass("aui-helper-hidden");
			$('#<portlet:namespace />module_eval_' + moduleEval.value).removeClass("aui-helper-hidden");
		}
	</aui:script>
</c:if>

<aui:field-wrapper label="allowed-time">
	<div class="row">
		<div class="col-md-6 col-sm-6 col-xs-6">
			<aui:select name="allowedHours" label="hours">
				<c:forEach var = "i" begin = "0" end = "23">
					<aui:option label="${i }" value="${i }" selected="${i == allowedHours }"/>
				</c:forEach>
			</aui:select>
		</div>
		<div class="col-md-6  col-sm-6 col-xs-6">
			<aui:select name="allowedMinutes" label="minutes">
				<c:forEach var = "i" begin = "0" end = "59">
					<aui:option label="${i }" value="${i }" selected="${i == allowedMinutes }"/>
				</c:forEach>
			</aui:select>
		</div>
	</div>
</aui:field-wrapper>