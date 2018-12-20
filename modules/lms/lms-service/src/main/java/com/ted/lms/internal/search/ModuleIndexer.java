package com.ted.lms.internal.search;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.expando.kernel.util.ExpandoBridgeIndexerUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriterHelper;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.ted.lms.model.Module;
import com.ted.lms.security.permission.resource.ModulePermission;
import com.ted.lms.service.ModuleLocalService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true, 
	service = Indexer.class
)
public class ModuleIndexer extends BaseIndexer<Module> {
	
	public static final String CLASS_NAME = Module.class.getName();
	
	public ModuleIndexer() {
		setDefaultSelectedFieldNames(
			Field.ASSET_TAG_NAMES, Field.COMPANY_ID, Field.GROUP_ID, 
			Field.ENTRY_CLASS_NAME, Field.ENTRY_CLASS_PK, Field.GROUP_ID,
			Field.MODIFIED_DATE, Field.SCOPE_GROUP_ID, Field.UID);
		setDefaultSelectedLocalizedFieldNames(Field.DESCRIPTION, Field.TITLE);
		setFilterSearch(true);
		setPermissionAware(true);
		setSelectAllLocales(true);
		log.debug("ModuleIndexer");
	}

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}
	
	@Override
	public boolean hasPermission(
			PermissionChecker permissionChecker, String entryClassName,
			long entryClassPK, String actionId)
		throws Exception {
		log.debug("ModuleIndexer::hasPermission::"+entryClassName+"::"+entryClassPK+"::" + actionId);
		log.debug("ModuleIndexer::hasPermission::" + ModulePermission.contains(permissionChecker, entryClassPK, ActionKeys.VIEW));
		return ModulePermission.contains(permissionChecker, entryClassPK,actionId);
	}
	
	@Override
	public boolean isVisible(long classPK, int status) throws Exception {
		Module module = moduleLocalService.getModule(classPK);
		log.debug("ModuleIndexer::isVisible::"+classPK+"::"+status);
		AssetEntry assetEntry = assetEntryLocalService.getEntry(Module.class.getName(), module.getModuleId());
		return isVisible(module.getStatus(), status) && assetEntry.isVisible();
	}
	
	@Override
	public void postProcessSearchQuery(
			BooleanQuery searchQuery, BooleanFilter fullQueryBooleanFilter,
			SearchContext searchContext)
		throws Exception {
		log.debug("ModuleIndexer::postProcessSearchQuery::"+searchQuery.toString()+"::"+fullQueryBooleanFilter.toString()+"::"+searchContext.toString());
		addSearchLocalizedTerm(
			searchQuery, searchContext, Field.DESCRIPTION, false);
		addSearchLocalizedTerm(searchQuery, searchContext, Field.TITLE, false);
		
		LinkedHashMap<String, Object> params =
			(LinkedHashMap<String, Object>)searchContext.getAttribute("params");

		if (params != null) {
			String expandoAttributes = (String)params.get("expandoAttributes");

			if (Validator.isNotNull(expandoAttributes)) {
				addSearchExpando(searchQuery, searchContext, expandoAttributes);
			}
		}
	}

	@Override
	protected void doDelete(Module module) throws Exception {
		log.debug("ModuleIndexer::doDelete::"+module.getModuleId());
		deleteDocument(module.getCompanyId(), module.getModuleId());
	}
	
	@Override
	protected Document doGetDocument(Module module) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, module);

		log.debug("doGetDocument::"+module.getModuleId());
		
		Locale siteDefaultLocale = _portal.getSiteDefaultLocale(
				module.getGroupId());
		
		addLocalizedField(document, Field.TITLE, siteDefaultLocale,
				module.getTitleMap());
		addLocalizedField(document, Field.DESCRIPTION, siteDefaultLocale,
				module.getDescriptionMap());
		document.addDate("startDate", module.getStartDate());
		document.addDate("endDate", module.getEndDate());
		document.addKeyword(Field.COMPANY_ID, module.getCompanyId());
		document.addKeyword(Field.SCOPE_GROUP_ID, module.getGroupId());
		
		AssetEntry assetEntry = assetEntryLocalService.getEntry(Module.class.getName(), module.getModuleId());

		List<AssetCategory> assetCategories =
				assetEntry.getCategories();
		
		log.debug("assetCategories: " + assetCategories.size());

		long[] assetCategoryIds = ListUtil.toLongArray(
			assetCategories, AssetCategory.CATEGORY_ID_ACCESSOR);

		document.addKeyword(Field.ASSET_CATEGORY_IDS, assetCategoryIds);
		
		addAssetCategoryTitles(
				document, Field.ASSET_CATEGORY_TITLES, assetCategories);
		
		ExpandoBridgeIndexerUtil.addAttributes(document, module.getExpandoBridge());
		
		return document;
	}
	
	protected void addAssetCategoryTitles(
			Document document, String field, List<AssetCategory> assetCategories) {

			Map<Locale, List<String>> assetCategoryTitles = new HashMap<>();

			Locale defaultLocale = LocaleUtil.getDefault();

			for (AssetCategory assetCategory : assetCategories) {
				Map<Locale, String> titleMap = assetCategory.getTitleMap();

				for (Map.Entry<Locale, String> entry : titleMap.entrySet()) {
					Locale locale = entry.getKey();
					String title = entry.getValue();

					if (Validator.isNull(title)) {
						continue;
					}

					List<String> titles = assetCategoryTitles.computeIfAbsent(
						locale, k -> new ArrayList<>());

					titles.add(StringUtil.toLowerCase(title));
					log.debug("titulo: " + title);
				}
			}

			for (Map.Entry<Locale, List<String>> entry :
					assetCategoryTitles.entrySet()) {

				Locale locale = entry.getKey();
				List<String> titles = entry.getValue();

				String[] titlesArray = titles.toArray(new String[titles.size()]);

				if (locale.equals(defaultLocale)) {
					document.addText(field, titlesArray);
				}
				log.debug("añadiendo: " + field.concat(StringPool.UNDERLINE).concat(locale.toString()) + " - " + titlesArray);
				document.addText(
					field.concat(StringPool.UNDERLINE).concat(locale.toString()),
					titlesArray);
			}
		}


	@Override
	protected Summary doGetSummary(
		Document document, Locale locale, String snippet,
		PortletRequest portletRequest, PortletResponse portletResponse) {
		log.debug("ModuleIndexer::doGetSummary::"+document.getField(Field.TITLE));
		Summary summary = createSummary(document);

		summary.setMaxContentLength(200);

		return summary;
	}

	@Override
	protected void doReindex(Module module) throws Exception {
		Document document = getDocument(module);
		log.debug("ModuleIndexer::doReindex::"+module.getModuleId());
		indexWriterHelper.updateDocument(
			getSearchEngineId(), module.getCompanyId(), document,
			isCommitImmediately());
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		Module module = moduleLocalService.getModule(classPK);
		log.debug("ModuleIndexer::doReindex::"+classPK);
		doReindex(module);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		reindexEntries(companyId);
	}
	
	protected void reindexEntries(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			moduleLocalService.getIndexableActionableDynamicQuery();
		
		log.debug("ModuleIndexer::reindexEntries::"+companyId);
		
		indexableActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {

					Property statusProperty = PropertyFactoryUtil.forName(
						"status");

					Integer[] statuses = {
						WorkflowConstants.STATUS_APPROVED,
						WorkflowConstants.STATUS_IN_TRASH
					};

					dynamicQuery.add(statusProperty.in(statuses));
				}

			});
		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<Module>() {

				@Override
				public void performAction(Module module) {
					try {
						AssetEntry assetEntry = assetEntryLocalService.getEntry(Module.class.getName(), module.getModuleId());
						
						if(assetEntry.isVisible()) {
							Document document = getDocument(module);
	
							log.debug("añado el documento: " + module.getModuleId());
							indexableActionableDynamicQuery.addDocuments(document);
						}
					}
					catch (PortalException pe) {
						if (log.isWarnEnabled()) {
							log.warn(
								"Unable to index blogs entry " +
									module.getModuleId(),
								pe);
						}
					}
				}

			});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}
	
	@Reference(unbind = "-")
	protected void setModuleLocalService(ModuleLocalService moduleLocalService) {
		this.moduleLocalService = moduleLocalService;
	}
	
	private ModuleLocalService moduleLocalService;
	
	@Reference(unbind = "-")
	protected void setAssetEntryLocalService(AssetEntryLocalService assetEntryLocalService) {
		this.assetEntryLocalService = assetEntryLocalService;
	}
	
	private AssetEntryLocalService assetEntryLocalService;
	
	@Reference
	protected IndexWriterHelper indexWriterHelper;
	
	private static final Log log = LogFactoryUtil.getLog(
			ModuleIndexer.class);
	
	@Reference
	private Portal _portal;
}
