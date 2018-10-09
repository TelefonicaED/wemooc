<%@page import="com.ted.lms.service.ModuleLocalServiceUtil"%>
<%@page import="com.ted.lms.model.Module"%>
<%@page import="java.util.List"%>
<%@page import="com.ted.prerequisite.module.internal.constants.ModulePrerequisiteConstants"%>
<%@page import="com.ted.prerequisite.module.ModulePrerequisiteFactory"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="com.ted.prerequisite.service.PrerequisiteRelationLocalServiceUtil"%>
<%@page import="com.ted.prerequisite.model.PrerequisiteRelation"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ include file="../../init.jsp" %>

<%
long classNameId = ParamUtil.getLong(request, "classNameId", 0);
long classPK = ParamUtil.getLong(request, "classPK", 0);
long groupId = ParamUtil.getLong(request, "groupId", 0);

PrerequisiteRelation prerequisiteRelation = PrerequisiteRelationLocalServiceUtil.getPrerequisiteRelation(
																PortalUtil.getClassNameId(ModulePrerequisiteFactory.class), 
																classNameId, classPK);
long modulePrerequisiteId = prerequisiteRelation != null ? prerequisiteRelation.getExtraDataJSON().getLong(ModulePrerequisiteConstants.JSON_MODULE_ID) : 0;

List<Module> listModules = ModuleLocalServiceUtil.getModules(groupId, -1, -1);

%>

<aui:select name="prerequisiteModuleId" label="prerequisite-module.module">
	<aui:option label="" value="0" />
	<%for(Module modulePrerequisite: listModules){ %>
		<aui:option label="<%=modulePrerequisite.getTitle(themeDisplay.getLocale()) %>" value="<%=modulePrerequisite.getModuleId()%>" 
			selected="<%=modulePrerequisite.getModuleId() == modulePrerequisiteId %>"/>
	<%} %>
</aui:select>