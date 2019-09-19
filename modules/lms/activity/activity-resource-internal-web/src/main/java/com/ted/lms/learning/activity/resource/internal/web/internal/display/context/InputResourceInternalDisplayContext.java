package com.ted.lms.learning.activity.resource.internal.web.internal.display.context;

import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.ClassType;
import com.liferay.asset.kernel.model.ClassTypeReader;
import com.liferay.asset.util.comparator.AssetRendererFactoryTypeNameComparator;
import com.liferay.petra.string.StringPool;
import com.liferay.petra.string.StringUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.ted.lms.learning.activity.resource.internal.web.constants.ResourceInternalPortletKeys;
import com.ted.lms.learning.activity.resource.internal.web.util.ResourceInternalPrefsPropsValues;
import com.ted.lms.model.Course;
import com.ted.lms.service.CourseLocalServiceUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.portlet.PortletMode;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;


public class InputResourceInternalDisplayContext {

	public InputResourceInternalDisplayContext(HttpServletRequest request) {

		this.request = request;
		themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
	}

	public String getEventName() {
		if (eventName != null) {
			return eventName;
		}

		eventName = randomNamespace + "selectAsset";

		return eventName;
	}

	public String getRandomNamespace() {
		if (randomNamespace != null) {
			return randomNamespace;
		}

		String randomKey = PortalUtil.generateRandomKey(request, ResourceInternalPortletKeys.RESOURCE_INTERNAL);

		randomNamespace = randomKey + StringPool.UNDERLINE;

		return randomNamespace;
	}

	public List<Map<String, Object>> getSelectorEntries() throws Exception {
		List<Map<String, Object>> selectorEntries = new ArrayList<>();

		for (AssetRendererFactory<?> assetRendererFactory : getAssetRendererFactories()) {
			Map<String, Object> selectorEntry = new HashMap<>();

			selectorEntry.put("data", geSelectorEntryData(assetRendererFactory));
			selectorEntry.put("iconCssClass",getSelectorEntryIconCssClass(assetRendererFactory));
			selectorEntry.put("id", getSelectorEntryId(assetRendererFactory));
			selectorEntry.put("message", getSelectorEntryMessage(assetRendererFactory));

			selectorEntries.add(selectorEntry);
		}

		return selectorEntries;
	}
	
	public List<AssetRendererFactory<?>> getAssetRendererFactories() {
		List<AssetRendererFactory<?>> assetRendererFactories =
			AssetRendererFactoryRegistryUtil.getAssetRendererFactories(
				themeDisplay.getCompanyId());
		
		String[] assetTypes = ResourceInternalPrefsPropsValues.getAssetTypes(themeDisplay.getCompanyId());

		assetRendererFactories = ListUtil.filter(
			assetRendererFactories,
			assetRendererFactory -> {
				if (assetRendererFactory.isLinkable() &&
					assetRendererFactory.isSelectable() &&
					ArrayUtil.contains(assetTypes, assetRendererFactory.getClassName())) {

					return true;
				}

				return false;
			});

		return ListUtil.sort(
			assetRendererFactories,
			new AssetRendererFactoryTypeNameComparator(
				themeDisplay.getLocale()));
	}

	private Map<String, Object> geSelectorEntryData(AssetRendererFactory<?> assetRendererFactory) throws Exception {

		Map<String, Object> selectorEntryData = new HashMap<>();

		PortletURL assetBrowserPortletURL = getAssetBrowserPortletURL(assetRendererFactory);

		if (assetBrowserPortletURL != null) {
			selectorEntryData.put("href", assetBrowserPortletURL.toString());
		}

		String typeName = assetRendererFactory.getTypeName(themeDisplay.getLocale());

		selectorEntryData.put("title",LanguageUtil.format(themeDisplay.getLocale(), "select-x", typeName, false));
		selectorEntryData.put("type", assetRendererFactory.getClassName());

		return selectorEntryData;
	}

	private long[] getAssetBrowserGroupId(AssetRendererFactory<?> assetRendererFactory) {

		Group scopeGroup = themeDisplay.getScopeGroup();
		
		Course course = CourseLocalServiceUtil.getCourseByGroupCreatedId(themeDisplay.getScopeGroupId());
		
		long groupId = scopeGroup.getGroupId();
		
		if(course != null) {
			groupId = course.getGroupId();
		}

		return new long[] {groupId,themeDisplay.getScopeGroupId()};
	}

	private PortletURL getAssetBrowserPortletURL(AssetRendererFactory<?> assetRendererFactory)
		throws Exception {

		PortletURL portletURL = PortletProviderUtil.getPortletURL(request, assetRendererFactory.getClassName(),PortletProvider.Action.BROWSE);

		if (portletURL == null) {
			return portletURL;
		}

		long[] groupIds = getAssetBrowserGroupId(assetRendererFactory);

		portletURL.setParameter("groupId", String.valueOf(themeDisplay.getScopeGroupId()));
		portletURL.setParameter("selectedGroupIds", StringUtil.merge(groupIds, StringPool.COMMA));
		portletURL.setParameter("typeSelection", assetRendererFactory.getClassName());
		portletURL.setParameter("eventName", getEventName());
		portletURL.setPortletMode(PortletMode.VIEW);
		portletURL.setWindowState(LiferayWindowState.POP_UP);

		return portletURL;
	}

	private List<Map<String, Object>> getSelectorEntries(AssetRendererFactory<?> assetRendererFactory) throws Exception {

		long[] groupIds = getAssetBrowserGroupId(assetRendererFactory);

		ClassTypeReader classTypeReader = assetRendererFactory.getClassTypeReader();

		List<ClassType> classTypes = classTypeReader.getAvailableClassTypes(PortalUtil.getCurrentAndAncestorSiteGroupIds(groupIds),themeDisplay.getLocale());

		if (classTypes.isEmpty()) {
			return Collections.emptyList();
		}

		List<Map<String, Object>> selectorEntries = new ArrayList<>();

		for (ClassType classType : classTypes) {
			Map<String, Object> selectorEntry = new HashMap<>();

			selectorEntry.put("data", getSelectorEntryData(assetRendererFactory, classType));
			selectorEntry.put("iconCssClass",getSelectorEntryIconCssClass(assetRendererFactory));
			selectorEntry.put("id", getSelectorEntryId(assetRendererFactory, classType));
			selectorEntry.put("message", getSelectorEntryMessage(classType));

			selectorEntries.add(selectorEntry);
		}

		return selectorEntries;
	}

	private Map<String, Object> getSelectorEntryData(AssetRendererFactory<?> assetRendererFactory, ClassType classType) throws Exception {

		Map<String, Object> selectorEntryData = new HashMap<>();

		PortletURL portletURL = getAssetBrowserPortletURL(assetRendererFactory);

		if (portletURL != null) {
			portletURL.setParameter("subtypeSelectionId",String.valueOf(classType.getClassTypeId()));
			selectorEntryData.put("href", portletURL.toString());
		}

		selectorEntryData.put("title",LanguageUtil.format(themeDisplay.getLocale(), "select-x", classType.getName(), false));
		selectorEntryData.put("type", classType.getName());

		return selectorEntryData;
	}

	private String getSelectorEntryIconCssClass(AssetRendererFactory<?> assetRendererFactory)throws Exception {
		return assetRendererFactory.getIconCssClass();
	}

	private String getSelectorEntryId(AssetRendererFactory<?> assetRendererFactory) {

		return FriendlyURLNormalizerUtil.normalize(assetRendererFactory.getTypeName(themeDisplay.getLocale()));
	}

	private String getSelectorEntryId(AssetRendererFactory<?> assetRendererFactory, ClassType classType) {

		String selectorEntryId = String.valueOf(getAssetBrowserGroupId(assetRendererFactory));

		selectorEntryId += FriendlyURLNormalizerUtil.normalize(classType.getName());

		return selectorEntryId;
	}

	private String getSelectorEntryMessage(AssetRendererFactory<?> assetRendererFactory) {

		return assetRendererFactory.getTypeName(themeDisplay.getLocale());
	}

	private String getSelectorEntryMessage(ClassType classType) {
		return classType.getName();
	}

	private static final Log log = LogFactoryUtil.getLog(InputResourceInternalDisplayContext.class);

	private String eventName;
	private String randomNamespace;
	private final HttpServletRequest request;
	private final ThemeDisplay themeDisplay;
}
