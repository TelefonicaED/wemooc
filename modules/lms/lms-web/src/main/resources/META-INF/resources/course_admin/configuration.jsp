<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.liferay.portal.kernel.service.LayoutSetPrototypeLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.model.LayoutSetPrototype"%>
<%@page import="com.liferay.portal.kernel.util.ArrayUtil"%>
<%@page import="com.liferay.expando.kernel.model.ExpandoTableConstants"%>
<%@page import="com.ted.lms.model.Course"%>
<%@page import="com.liferay.expando.kernel.service.ExpandoColumnLocalServiceUtil"%>
<%@page import="com.liferay.expando.kernel.model.ExpandoColumn"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.settings.ParameterMapSettingsLocator"%>
<%@page import="com.liferay.portal.kernel.settings.PortletInstanceSettingsLocator"%>
<%@page import="com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil"%>
<%@page import="com.ted.lms.web.internal.configuration.CourseAdminPortletInstanceConfiguration"%>
<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@ include file="../init.jsp" %>

<%
CourseAdminPortletInstanceConfiguration courseAdminPortletInstanceConfiguration = ConfigurationProviderUtil.getConfiguration(CourseAdminPortletInstanceConfiguration.class, 
		new ParameterMapSettingsLocator(request.getParameterMap(), new PortletInstanceSettingsLocator(themeDisplay.getLayout(), portletDisplay.getPortletResource())));;
List<ExpandoColumn> listExpandos = ExpandoColumnLocalServiceUtil.getColumns(themeDisplay.getCompanyId(), Course.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME);
List<LayoutSetPrototype> listTemplates = LayoutSetPrototypeLocalServiceUtil.getLayoutSetPrototypes(themeDisplay.getCompanyId());
String[] searchExpandoValues = Validator.isNotNull(courseAdminPortletInstanceConfiguration.searchExpandoValues()) ? courseAdminPortletInstanceConfiguration.searchExpandoValues().split(",") : null;
String[] searchEditionExpandoValues = Validator.isNotNull(courseAdminPortletInstanceConfiguration.searchEditionExpandoValues()) ? courseAdminPortletInstanceConfiguration.searchEditionExpandoValues().split(",") : null;
String[] courseExpandoValues = Validator.isNotNull(courseAdminPortletInstanceConfiguration.courseExpandoValues()) ? courseAdminPortletInstanceConfiguration.courseExpandoValues().split(",") : null;
String[] courseTemplateValues = Validator.isNotNull(courseAdminPortletInstanceConfiguration.courseTemplateValues()) ? courseAdminPortletInstanceConfiguration.courseTemplateValues().split(",") : null;
 %>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="true" var="configurationRenderURL" />

<liferay-frontend:edit-form action="<%=configurationActionURL%>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%=configurationRenderURL%>" />

	<liferay-frontend:edit-form-body>
		<liferay-frontend:fieldset-group>
			<liferay-frontend:fieldset
				collapsible="false"
				label="course-search"
			>		
				<aui:input type="checkbox" name="preferences--searchTags--" value="<%= courseAdminPortletInstanceConfiguration.searchTags()%>"/>
				<aui:input type="checkbox" name="preferences--searchCategories--" value="<%= courseAdminPortletInstanceConfiguration.searchCategories()%>"/>
				<aui:input type="checkbox" name="preferences--searchGroups--" value="<%= courseAdminPortletInstanceConfiguration.searchGroups()%>"/>
				<c:if test="<%=listExpandos != null && listExpandos.size() > 0 %>">
					<aui:field-wrapper label="custom-field">
						<%for(ExpandoColumn expandoColumn: listExpandos){ %>
							<aui:input type="checkbox" multiple="true" label="<%=expandoColumn.getDisplayName(themeDisplay.getLocale())%>" name="preferences--searchExpandoValues--"
								value="<%=expandoColumn.getColumnId()%>" checked="<%=searchExpandoValues != null ? ArrayUtil.contains(searchExpandoValues, String.valueOf(expandoColumn.getColumnId())) : false%>"/>
						<%} %>
					</aui:field-wrapper>
				</c:if>
				<aui:input type="checkbox" name="preferences--searchTemplatesSelected--" value="<%= courseAdminPortletInstanceConfiguration.searchTemplatesSelected()%>"/>
			</liferay-frontend:fieldset>
			<c:if test="<%=listExpandos != null && listExpandos.size() > 0 %>">
				<liferay-frontend:fieldset
					collapsible="false"
					label="edition-search"
				>	
					<aui:field-wrapper label="custom-field">
						<%for(ExpandoColumn expandoColumn: listExpandos){ %>
							<aui:input type="checkbox" multiple="true" label="<%=expandoColumn.getDisplayName(themeDisplay.getLocale())%>" name="preferences--searchEditionExpandoValues--"
								value="<%=expandoColumn.getColumnId()%>" checked="<%=searchEditionExpandoValues != null ? ArrayUtil.contains(searchEditionExpandoValues, String.valueOf(expandoColumn.getColumnId())) : false%>"/>
						<%} %>
					</aui:field-wrapper>
				</liferay-frontend:fieldset>
			</c:if>
		</liferay-frontend:fieldset-group>
		<liferay-frontend:fieldset-group>
			<liferay-frontend:fieldset
				collapsible="false"
				label="edit-course"
			>
				<aui:input type="checkbox" name="preferences--courseTypeRegistration--" value="<%= courseAdminPortletInstanceConfiguration.courseTypeRegistration()%>"/>
				<aui:input type="checkbox" name="preferences--courseNumUsers--" value="<%= courseAdminPortletInstanceConfiguration.courseNumUsers()%>"/>
				<aui:input type="checkbox" name="preferences--courseIndexer--" value="<%= courseAdminPortletInstanceConfiguration.courseIndexer()%>"/>
				<aui:input type="checkbox" name="preferences--courseAssetLinks--" value="<%= courseAdminPortletInstanceConfiguration.courseAssetLinks()%>"/>
				<aui:input type="checkbox" name="preferences--courseWelcomeMessage--" value="<%= courseAdminPortletInstanceConfiguration.courseWelcomeMessage()%>"/>
				<aui:input type="checkbox" name="preferences--courseGoodbyeMessage--" value="<%= courseAdminPortletInstanceConfiguration.courseGoodbyeMessage()%>"/>
				<c:if test="<%=listExpandos != null && listExpandos.size() > 0 %>">
					<aui:field-wrapper label="custom-field">
						<%for(ExpandoColumn expandoColumn: listExpandos){ %>
							<aui:input type="checkbox" multiple="true" label="<%=expandoColumn.getDisplayName(themeDisplay.getLocale())%>" name="preferences--courseExpandoValues--" id="courseExpandoValues<%=expandoColumn.getColumnId() %>"
								value="<%=expandoColumn.getColumnId()%>" checked="<%=courseExpandoValues != null ? ArrayUtil.contains(courseExpandoValues, String.valueOf(expandoColumn.getColumnId())) : false%>"/>
						<%} %>
					</aui:field-wrapper>
				</c:if>
				<c:if test="<%=listTemplates != null && listTemplates.size() > 0%>">
					<aui:field-wrapper label="templates">
						<%for(LayoutSetPrototype layoutSetPrototype: listTemplates){ %>
							<aui:input type="checkbox" multiple="true" label="<%=layoutSetPrototype.getName(themeDisplay.getLocale())%>" name="preferences--courseTemplateValues--" id="courseTemplateValues<%=layoutSetPrototype.getLayoutSetPrototypeId() %>"
								value="<%=layoutSetPrototype.getLayoutSetPrototypeId() %>" checked="<%=courseTemplateValues != null ? ArrayUtil.contains(courseTemplateValues, String.valueOf(layoutSetPrototype.getLayoutSetPrototypeId())) : false%>"/>
						<%} %>
					</aui:field-wrapper>
				</c:if>
			</liferay-frontend:fieldset>
			<liferay-frontend:fieldset
				collapsible="false"
				label="edit-edition"
			>
				<aui:input type="checkbox" name="preferences--editionIndexer--" value="<%= courseAdminPortletInstanceConfiguration.editionIndexer()%>"/>
				<aui:input type="checkbox" name="preferences--editionAllOptions--" value="<%= courseAdminPortletInstanceConfiguration.editionAllOptions()%>"/>
			</liferay-frontend:fieldset>
		</liferay-frontend:fieldset-group>
		<liferay-frontend:fieldset-group >
			<liferay-frontend:fieldset
				collapsible="false"
				label="actions"
			>
				<aui:input type="checkbox" name="preferences--courseActionPermissions--" value="<%= courseAdminPortletInstanceConfiguration.courseActionPermissions()%>"/>
				<aui:input type="checkbox" name="preferences--courseActionClose--" value="<%= courseAdminPortletInstanceConfiguration.courseActionClose()%>"/>
				<aui:input type="checkbox" name="preferences--courseActionDelete--" value="<%= courseAdminPortletInstanceConfiguration.courseActionDelete()%>"/>
				<aui:input type="checkbox" name="preferences--courseActionMembers--" value="<%= courseAdminPortletInstanceConfiguration.courseActionMembers()%>"/>
				<aui:input type="checkbox" name="preferences--courseActionMembersCalendar--" value="<%= courseAdminPortletInstanceConfiguration.courseActionMembersCalendar()%>"/>
				<aui:input type="checkbox" name="preferences--courseActionExport--" value="<%= courseAdminPortletInstanceConfiguration.courseActionExport()%>"/>
				<aui:input type="checkbox" name="preferences--courseActionImport--" value="<%= courseAdminPortletInstanceConfiguration.courseActionImport()%>"/>
				<aui:input type="checkbox" name="preferences--courseActionDuplicate--" value="<%= courseAdminPortletInstanceConfiguration.courseActionDuplicate()%>"/>
			</liferay-frontend:fieldset>
		</liferay-frontend:fieldset-group>
		<liferay-frontend:fieldset-group>
			<liferay-frontend:fieldset
				collapsible="false"
				label="course-columns"
			>
				<aui:input type="checkbox" name="preferences--courseColumnCreationDate--" value="<%= courseAdminPortletInstanceConfiguration.courseColumnCreationDate()%>"/>
				<aui:input type="checkbox" name="preferences--courseColumnExecutionDates--" value="<%= courseAdminPortletInstanceConfiguration.courseColumnExecutionDates()%>"/>
			</liferay-frontend:fieldset>
			<liferay-frontend:fieldset
				collapsible="false"
				label="edition-columns"
			>
				<aui:input type="checkbox" name="preferences--editionColumnInscriptionDates--" value="<%= courseAdminPortletInstanceConfiguration.editionColumnInscriptionDates()%>"/>
				<aui:input type="checkbox" name="preferences--editionColumnExecutionDates--" value="<%= courseAdminPortletInstanceConfiguration.editionColumnExecutionDates()%>"/>
			</liferay-frontend:fieldset>
		</liferay-frontend:fieldset-group>
	</liferay-frontend:edit-form-body>
	<liferay-frontend:edit-form-footer>
		<aui:button type="submit" />

		<aui:button type="cancel" />
	</liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>