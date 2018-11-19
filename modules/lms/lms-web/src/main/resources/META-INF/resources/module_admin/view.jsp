<%@page import="com.ted.lms.security.permission.resource.ModulePermission"%>
<%@page import="com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker"%>
<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@ include file="../init.jsp" %>

<div class="container">

	<liferay-ui:search-container
		id="modules"
		searchContainer="${searchContainer }"
	>
		<liferay-ui:search-container-row
			className="com.ted.lms.model.Module"
			escapedModel="<%= true %>"
			keyProperty="moduleId"
			modelVar="module"
		>
			<liferay-portlet:renderURL varImpl="rowURL">
				<portlet:param name="mvcRenderCommandName" value="/modules/edit_entry" />
				<portlet:param name="redirect" value="${searchContainer.getIteratorURL()}" />
				<portlet:param name="moduleId" value="${moduleId }" />
			</liferay-portlet:renderURL>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-content"
				name="title"
				orderable="<%= false %>"
				value="${module.getTitle(themeDisplay.locale) }"
			/>
			
			<liferay-ui:search-container-column-jsp
				path="/module_admin/module_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			displayStyle="descriptive"
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>