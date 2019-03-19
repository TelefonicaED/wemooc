<%@page import="com.ted.lms.learning.activity.resource.internal.web.activity.ResourceInternalActivityType"%>
<%@page import="com.ted.lms.learning.activity.resource.internal.web.activity.ResourceInternalActivityTypeFactory"%>
<%@page import="com.ted.lms.learning.activity.resource.internal.web.internal.display.context.InputResourceInternalDisplayContext"%>
<%@page import="com.liferay.portal.kernel.util.HtmlUtil"%>
<%@page import="com.liferay.portal.kernel.util.UnicodeFormatter"%>
<%@page import="com.liferay.document.library.kernel.model.DLFileEntry"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="javax.portlet.PortletURL"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil"%>
<%@page import="com.liferay.asset.kernel.model.AssetEntry"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayPortletURL"%>
<%@page import="com.ted.lms.service.LearningActivityLocalServiceUtil"%>
<%@page import="com.ted.lms.model.LearningActivity"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ include file="init.jsp" %>

<%long actId = ParamUtil.getLong(request, "actId", 0);
boolean canBeEdited = ParamUtil.getBoolean(request, "canBeEdited", true);
long assetEntryId = ParamUtil.getLong(request, "assetEntryId", 0);

ResourceInternalActivityTypeFactory resourceInternalActivityTypeFactory = new ResourceInternalActivityTypeFactory();

InputResourceInternalDisplayContext inputAssetLinksDisplayContext = new InputResourceInternalDisplayContext(request);

ResourceInternalActivityType resourceInternalType = null;
LearningActivity learningActivity = null;

String assetEntryTitle = StringPool.BLANK;

if(actId > 0){
	learningActivity = LearningActivityLocalServiceUtil.getLearningActivity(actId);
	resourceInternalType = resourceInternalActivityTypeFactory.getResourceInternalType(learningActivity);
	if(assetEntryId == 0){
		assetEntryId = resourceInternalType.getAssetEntryId();
	}
}

if(assetEntryId > 0){
	AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchAssetEntry(assetEntryId);
	if(assetEntry != null)
		assetEntryTitle= assetEntry.getTitle(renderRequest.getLocale());
}

%>

<div class="col-md-8">
	<div class="form-group">
		<aui:input type="hidden" name="assetEntryId" ignoreRequestValue="true" value="<%=Long.toString(assetEntryId) %>"/>
		<aui:input name="assetEntryTitle" label="resource" type="resource" value="<%= assetEntryTitle %>" />
		
		<liferay-ui:icon-menu
			cssClass="select-existing-selector"
			direction="right"
			id='<%= inputAssetLinksDisplayContext.getRandomNamespace() + "inputAssetLinks" %>'
			message="select"
			showArrow="<%= false %>"
			showWhenSingleIcon="<%= true %>"
			disabled="<%= !canBeEdited %>"
		>
		
			<%
			for (Map<String, Object> selectorEntry : inputAssetLinksDisplayContext.getSelectorEntries()) {
			%>
		
				<liferay-ui:icon
					cssClass="asset-selector"
					data='<%= (Map<String, Object>)selectorEntry.get("data") %>'
					id='<%= (String)selectorEntry.get("id") %>'
					message='<%= HtmlUtil.escape((String)selectorEntry.get("message")) %>'
					url="javascript:;"
				/>
		
			<%
			}
			%>
		
		</liferay-ui:icon-menu>
		
		<aui:script use="aui-base,escape">
			var assetSelectorHandle = A.getBody().delegate(
				'click',
				function(event) {
					event.preventDefault();
		
					Liferay.Util.selectEntity(
						{
							dialog: {
								constrain: true,
								modal: true
							},
							eventName: '<%= inputAssetLinksDisplayContext.getEventName() %>',
							id: '<%= inputAssetLinksDisplayContext.getEventName() %>' + event.currentTarget.attr('id'),
							title: event.currentTarget.attr('data-title'),
							uri: event.currentTarget.attr('data-href')
						},
						function(event) {
							console.log("recibimos: " + event);
							var entityId = event.entityid;
							var assetEntryTitle = event.assettitle;
		
							$('#<portlet:namespace />assetEntryTitle').val(assetEntryTitle);
							$('#<portlet:namespace />assetEntryId').val(entityId);
						}
					);
				},
				'.asset-selector a'
			);
		
			var clearAssetSelectorHandle = function(event) {
				if (event.portletId === '<%= portletDisplay.getId() %>') {
					assetSelectorHandle.detach();
		
					Liferay.detach('destroyPortlet', clearAssetSelectorHandle);
				}
			};
		
			Liferay.on('destroyPortlet', clearAssetSelectorHandle);
		</aui:script>

	</div>
</div>
