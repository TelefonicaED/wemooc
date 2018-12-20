<%@page import="javax.portlet.PortletURL"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.ted.lms.model.Module"%>
<%@page import="java.util.List"%>
<%@page import="com.ted.lms.security.permission.resource.ModulePermission"%>
<%@page import="com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker"%>
<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@ include file="../init.jsp" %>

<%
List<Module> listModules = (List<Module>)request.getAttribute("listModules");
boolean accessLock = (Boolean)request.getAttribute("accessLock");
boolean courseIsLocked = (Boolean)request.getAttribute("courseIsLocked");
PortletURL portletURL = renderResponse.createRenderURL();
PortletURL addActivityPortletURL = null;
%>

<portlet:renderURL var="newModuleURL">
	<portlet:param name="mvcRenderCommandName" value="/modules/edit_module" />
	<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.ADD %>" />
	<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
</portlet:renderURL> 

<div class="container modules">
	<c:choose>
		<c:when test="<%=listModules != null && listModules.size() > 0%>">
			<%for(Module module: listModules){%>
				<div class="row module-row ">
					<div class="col-md-11" id="${renderResponse.getNamespace()}<%=module.getModuleId() %>">
						<span class="module-title">
							<c:choose>
								<c:when test="<%=accessLock || (!courseIsLocked && !module.isLocked(user.getUserId(), permissionChecker)) %>">
									<a href="<%=module.getURLView(liferayPortletRequest) %>"><%=module.getTitle(themeDisplay.getLocale()) %></a>
								</c:when>
								<c:otherwise>
									<%=module.getTitle(themeDisplay.getLocale()) %>
								</c:otherwise>
							</c:choose>
						</span>
					</div>
					<div class="col-md-1">
						<%@ include file="/modules/module_action.jsp" %>
					</div>
				</div>
			<%} %>
		</c:when>
		<c:otherwise>
			<div class="taglib-empty-result-message-header"></div>
		</c:otherwise>
	</c:choose>
</div>