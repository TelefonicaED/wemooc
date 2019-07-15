<%@page import="com.ted.lms.model.CourseResult"%>
<%@page import="com.ted.lms.web.internal.util.MyCoursesUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="com.ted.lms.constants.LMSPropsValues"%>
<%@page import="com.liferay.portal.util.PropsValues"%>
<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@page import="com.liferay.portal.kernel.settings.ParameterMapSettingsLocator"%>
<%@page import="com.liferay.portal.kernel.settings.PortletInstanceSettingsLocator"%>
<%@page import="com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil"%>
<%@page import="com.ted.lms.web.internal.display.context.MyCoursesPortletInstanceSettingsHelper"%>
<%@page import="com.ted.lms.web.internal.configuration.MyCoursesPortletInstanceConfiguration"%>
<%@ include file="../init.jsp" %>

<%
MyCoursesPortletInstanceConfiguration myCoursesPortletInstanceConfiguration = ConfigurationProviderUtil.getConfiguration(MyCoursesPortletInstanceConfiguration.class, new ParameterMapSettingsLocator(request.getParameterMap(), new PortletInstanceSettingsLocator(themeDisplay.getLayout(), portletDisplay.getPortletResource())));
MyCoursesPortletInstanceSettingsHelper myCoursesPortletInstanceSettingsHelper = new MyCoursesPortletInstanceSettingsHelper(request, myCoursesPortletInstanceConfiguration);

%>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="configurationRenderURL" />

<liferay-frontend:edit-form
	action="${configurationActionURL}"
	method="post"
	name="fm"
>
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="${configurationRenderURL}" />

	<liferay-frontend:edit-form-body>
		<liferay-frontend:fieldset-group>
			<liferay-frontend:fieldset
				collapsible="<%= false %>"
				label="course"
			>
				<aui:input name="preferences--coursesInProgress--" type="checkbox" label="my-courses.courses.in-progress" value="<%=myCoursesPortletInstanceConfiguration.coursesInProgress() %>"/>
				<aui:input name="preferences--coursesCompleted--" type="checkbox" label="my-courses.courses.completed" value="<%=myCoursesPortletInstanceConfiguration.coursesCompleted() %>"/>
				<aui:input name="preferences--coursesExpired--" type="checkbox" label="my-courses.courses.expired" value="<%=myCoursesPortletInstanceConfiguration.coursesExpired() %>"/>
				
				<aui:select label="my-courses.config.show-courses" name="preferences--showCourses--">
					<aui:option label="my-courses.config.show-courses.separated" selected="<%=myCoursesPortletInstanceConfiguration.showCourses() == MyCoursesUtil.COURSE_SEPARATED %>" value="<%=MyCoursesUtil.COURSE_SEPARATED %>"/>
					<aui:option label="my-courses.config.show-courses.completed-and-expire-together" selected="<%=myCoursesPortletInstanceConfiguration.showCourses() == MyCoursesUtil.COURSE_COMPLETED_AND_EXPIRED %>" value="<%=MyCoursesUtil.COURSE_COMPLETED_AND_EXPIRED %>"/>				
				</aui:select>
			
			</liferay-frontend:fieldset>
			<liferay-frontend:fieldset
				collapsible="<%= false %>"
				label="filter"
			>
				<aui:input name="preferences--onlyGroup--" type="checkbox" checked="<%=myCoursesPortletInstanceConfiguration.onlyGroup()%>" label="my-courses.config.view-group-courses"/>
			</liferay-frontend:fieldset>
			<liferay-frontend:fieldset
				collapsible="<%= false %>"
				label="list-view"
			>
				<aui:select label="maximum-items-to-display" name="preferences--pageDelta--">

					<%
					for (int pageDeltaValue : PropsValues.SEARCH_CONTAINER_PAGE_DELTA_VALUES) {
					%>
						<aui:option label="<%= pageDeltaValue %>" selected="<%= GetterUtil.getInteger(myCoursesPortletInstanceConfiguration.pageDelta()) == pageDeltaValue %>" />
					<%
					}
					%>

				</aui:select>

				<div class="display-template">

					<%
					List<String> displayStyles = new ArrayList<String>();
					%>

					<liferay-ddm:template-selector
						className="<%= CourseResult.class.getName() %>"
						displayStyle="<%= myCoursesPortletInstanceConfiguration.displayStyle() %>"
						displayStyleGroupId="<%= myCoursesPortletInstanceSettingsHelper.getDisplayStyleGroupId() %>"
						displayStyles="<%= displayStyles %>"
						refreshURL="<%= configurationRenderURL %>"
					/>
				</div>
			</liferay-frontend:fieldset>
		</liferay-frontend:fieldset-group>
	</liferay-frontend:edit-form-body>

	<liferay-frontend:edit-form-footer>
		<aui:button type="submit" />

		<aui:button type="cancel" />
	</liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>