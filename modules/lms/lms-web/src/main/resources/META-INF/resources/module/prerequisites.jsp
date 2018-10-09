<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="com.ted.prerequisite.registry.PrerequisiteFactoryRegistryUtil"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.ted.prerequisite.model.PrerequisiteFactory"%>
<%@page import="com.ted.lms.util.LMSPrefsPropsValues"%>
<%@page import="com.ted.lms.model.ModuleEvalFactory"%>
<%@page import="com.ted.lms.model.Module"%>
<%@ include file="../init.jsp" %>

<liferay-ui:error-marker
	key="<%= WebKeys.ERROR_SECTION %>"
	value="prerequisites"
/>

<aui:model-context bean="${module }" model="<%= Module.class %>" />

<%//Pedimos los prerequisitos del los módulos que están disponibles
String[] classNamePrerequisites = LMSPrefsPropsValues.getPrerequisitesOfModule(themeDisplay.getCompanyId());

PrerequisiteFactory prerequisiteFactory = null;
long classNameId = PortalUtil.getClassNameId(Module.class);

for(String classNamePrerequisite: classNamePrerequisites){
	prerequisiteFactory = PrerequisiteFactoryRegistryUtil.getPrerequisiteFactoryByClassName(classNamePrerequisite);
	if(prerequisiteFactory != null){%>
		<liferay-util:include page="<%=prerequisiteFactory.getURLSpecificContent() %>" portletId="<%=prerequisiteFactory.getPortletId() %>" >
			<liferay-util:param name="classNameId" value="<%=String.valueOf(classNameId) %>" />
			<liferay-util:param name="classPK" value="${moduleId }" />
			<liferay-util:param name="groupId" value="${themeDisplay.scopeGroupId }" />
		</liferay-util:include>
<%	}
}
%>

