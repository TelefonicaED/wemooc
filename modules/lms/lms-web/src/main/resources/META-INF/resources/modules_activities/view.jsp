<%@page import="com.ted.lms.service.LearningActivityService"%>
<%@page import="com.ted.lms.model.LearningActivity"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.ted.lms.constants.LMSActionKeys"%>
<%@page import="com.ted.lms.security.permission.resource.LMSPermission"%>
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
String newModuleURL = (String)request.getAttribute("newModuleURL");
PortletURL addActivityPortletURL  = (PortletURL)request.getAttribute("addActivityPortletURL");
long moduleId = (long)request.getAttribute("moduleId");
LearningActivityService learningActivityService = (LearningActivityService)request.getAttribute("learningActivityService");
%>

<div class="container modules">
	<c:choose>
		<c:when test="<%=listModules != null && listModules.size() > 0%>">
			<%for(Module module: listModules){ 
				boolean moduleIsLocked  = module.isLocked(user.getUserId(), permissionChecker); %>
				<div class='row module-row <%=moduleId == module.getModuleId() ? "module-selected" : "" %>'>
					<div class="col-md-11" id="${renderResponse.getNamespace()}<%=module.getModuleId() %>">
						<span class="module-title">
							<c:choose>
								<c:when test="<%=accessLock || (!courseIsLocked && !moduleIsLocked) %>">
									<a href="<%=module.getURLView(themeDisplay) %>"><%=module.getTitle(themeDisplay.getLocale()) %></a>
								</c:when>
								<c:otherwise>
									<%=module.getTitle(themeDisplay.getLocale()) %>
								</c:otherwise>
							</c:choose>
						</span>
					</div>
					<c:if test="${showActions }">
						<div class="col-md-1 lms-content-editable">
							<%@ include file="/modules/module_action.jsp" %>
						</div>
					</c:if>
				</div>
				<%List<LearningActivity> activities = learningActivityService.getActivities(module.getModuleId());
				for(LearningActivity activity: activities){ %>
					<div class="row activity-row">
						<c:choose>
							<c:when test="<%=accessLock || (!courseIsLocked && !moduleIsLocked && !activity.isLocked(themeDisplay.getUser(), permissionChecker)) %>">
								<a href="<%=activity.getURLView(themeDisplay) %>"><%=activity.getTitle(themeDisplay.getLocale()) %></a>
								<c:if test="${showActions }">
									<div class="lms-content-editable">
										<%@ include file="/activities/activity_action.jsp" %>
									</div>
								</c:if>
							</c:when>
							<c:otherwise>
								<%=activity.getTitle(themeDisplay.getLocale()) %>
								<span class="glyphicon glyphicon-lock"></span>
							</c:otherwise>
						</c:choose>
					</div>
			<%	}
			} %>
		</c:when>
		<c:otherwise>
			<div class="taglib-empty-result-message-header"></div>
		</c:otherwise>
	</c:choose>
</div>
<c:if test="<%=Validator.isNotNull(newModuleURL) && LMSPermission.contains(permissionChecker, themeDisplay.getScopeGroupId(), LMSActionKeys.ADD_MODULE) %>">
	<aui:button value="new-module" onClick="${newModuleURL}"/>
</c:if>