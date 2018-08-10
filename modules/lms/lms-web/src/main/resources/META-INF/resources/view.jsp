<%@page import="com.ted.lms.learningactivity.LearningActivityFactoryRegistryUtil"%>
<%@page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@page import="com.ted.lms.model.LearningActivityFactory"%>
<%@page import="java.util.List"%>
<%@ include file="/init.jsp" %>

<p>
	<b><liferay-ui:message key="courseadmin.caption"/></b>
</p>

<%
List<LearningActivityFactory<?>> assetRendererFactories = ListUtil.sort(LearningActivityFactoryRegistryUtil.getLearningActivityFactories(20099), null);

for (LearningActivityFactory<?> assetRendererFactory : assetRendererFactories) {
%>
	activity: <%=assetRendererFactory.getClassName() %>
<%}%>

Total: <%=assetRendererFactories.size()%> 