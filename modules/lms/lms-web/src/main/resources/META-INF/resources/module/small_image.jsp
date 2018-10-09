<%@page import="com.ted.lms.util.LMSPrefsPropsValues"%>
<%@page import="com.liferay.portal.kernel.util.StringUtil"%>
<%@page import="com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory"%>
<%@page import="com.ted.lms.web.internal.ModulesItemSelectorHelper"%>
<%@page import="com.ted.lms.web.internal.util.LMSWebKeys"%>
<%@page import="com.ted.lms.constants.LMSPropsValues"%>
<%@page import="com.ted.lms.model.Module"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.ted.lms.exception.SmallImageNameException"%>
<%@page import="com.ted.lms.exception.SmallImageSizeException"%>
<%@ include file="../init.jsp" %>

<liferay-ui:error-marker
	key="<%= WebKeys.ERROR_SECTION %>"
	value="small_image"
/>

<aui:model-context bean="${module }" model="<%= Module.class %>" />

<liferay-ui:error exception="<%=SmallImageNameException.class %>">
	<liferay-ui:message key="image-names-must-end-with-one-of-the-following-extensions" />.
</liferay-ui:error>

<liferay-ui:error exception="<%= SmallImageSizeException.class %>">

	<liferay-ui:message key="please-enter-a-small-image-with-a-valid-file-size-no-larger-than-x" />
</liferay-ui:error>

<liferay-frontend:fieldset>
	<portlet:actionURL name="/course/upload_image" var="uploadSmallImageURL" />

	<div class="lfr-module-small-image-selector">

		<%
		String smallImageSelectedItemEventName = liferayPortletResponse.getNamespace() + "smallImageSelectedItem";
		ModulesItemSelectorHelper modulesItemSelectorHelper = (ModulesItemSelectorHelper)request.getAttribute(LMSWebKeys.MODULES_ITEM_SELECTOR_HELPER);
		RequestBackedPortletURLFactory requestBackedPortletURLFactory = RequestBackedPortletURLFactoryUtil.create(liferayPortletRequest);
		String itemSelectorURL = modulesItemSelectorHelper.getItemSelectorURL(requestBackedPortletURLFactory, themeDisplay, smallImageSelectedItemEventName);
		System.out.println("itemSelectorURL: " + itemSelectorURL);
		%>

		<liferay-item-selector:image-selector
			draggableImage="vertical"
			fileEntryId="${module.smallImageId }"
			itemSelectorEventName="<%= smallImageSelectedItemEventName %>"
			itemSelectorURL="<%=itemSelectorURL %>"
			maxFileSize="<%=LMSPrefsPropsValues.getModuleImageMaxSize(themeDisplay.getCompanyId())%>"
			paramName="smallImageFileEntry"
			uploadURL="${uploadSmallImageURL}"
			validExtensions='<%=StringUtil.merge(LMSPrefsPropsValues.getModuleImageExtensions(themeDisplay.getCompanyId()), ", ") %>'
		/>
	</div>
</liferay-frontend:fieldset>

