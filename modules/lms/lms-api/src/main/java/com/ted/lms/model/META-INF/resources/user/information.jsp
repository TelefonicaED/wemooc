<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<%
User selUser = (User)request.getAttribute(UsersAdminWebKeys.SELECTED_USER);
%>

<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= (selUser == null) ? Constants.ADD : Constants.UPDATE %>" />

<div class="sheet-section">
	<h3 class="sheet-subtitle"><liferay-ui:message key="personal-information" /></h3>

	<liferay-util:include page="/user/details.jsp" servletContext="<%= application %>" />

	<liferay-util:include page="/user/comments.jsp" servletContext="<%= application %>" />
</div>

<div class="sheet-section">
	<h3 class="sheet-subtitle"><liferay-ui:message key="more-information" /></h3>

	<liferay-util:include page="/user/categorization.jsp" servletContext="<%= application %>" />
</div>

<div class="sheet-section">
	<h4 class="sheet-tertiary-title"><liferay-ui:message key="custom-fields" /></h4>

	<liferay-util:include page="/user/custom_fields.jsp" servletContext="<%= application %>" />
</div>