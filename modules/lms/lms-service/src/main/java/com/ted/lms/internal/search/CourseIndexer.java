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
import com.ted.lms.model.Course;
import com.ted.lms.security.permission.resource.CoursePermission;
import com.ted.lms.service.CourseLocalService;

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

@Component(immediate = true, service = Indexer.class)
public class CourseIndexer extends BaseIndexer<Course> {
	
	public static final String CLASS_NAME = Course.class.getName();
	
	public CourseIndexer() {
		setDefaultSelectedFieldNames(
			Field.ASSET_TAG_NAMES, Field.COMPANY_ID, Field.GROUP_ID, 
			Field.ENTRY_CLASS_NAME, Field.ENTRY_CLASS_PK, Field.GROUP_ID,
			Field.MODIFIED_DATE, Field.SCOPE_GROUP_ID, Field.UID);
		setDefaultSelectedLocalizedFieldNames(Field.DESCRIPTION, Field.TITLE);
		setFilterSearch(true);
		setPermissionAware(true);
		setSelectAllLocales(true);
		log.debug("CourseIndexer");
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
		log.debug("CourseIndexer::hasPermission::"+entryClassName+"::"+entryClassPK+"::" + actionId);
		log.debug("CourseIndexer::hasPermission::" + CoursePermission.contains(permissionChecker, entryClassPK, ActionKeys.VIEW));
		return CoursePermission.contains(permissionChecker, entryClassPK,actionId);
	}
	
	@Override
	public boolean isVisible(long classPK, int status) throws Exception {
		Course course = courseLocalService.getCourse(classPK);
		log.debug("CourseIndexer::isVisible::"+classPK+"::"+status);
		AssetEntry assetEntry = assetEntryLocalService.getEntry(Course.class.getName(), course.getCourseId());
		return isVisible(course.getStatus(), status) && assetEntry.isVisible();
	}
	
	@Override
	public void postProcessSearchQuery(
			BooleanQuery searchQuery, BooleanFilter fullQueryBooleanFilter,
			SearchContext searchContext)
		throws Exception {
		log.debug("CourseIndexer::postProcessSearchQuery::"+searchQuery.toString()+"::"+fullQueryBooleanFilter.toString()+"::"+searchContext.toString());
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
	protected void doDelete(Course course) throws Exception {
		log.debug("CourseIndexer::doDelete::"+course.getCourseId());
		deleteDocument(course.getCompanyId(), course.getCourseId());
	}
	
	@Override
	protected Document doGetDocument(Course course) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, course);

		log.debug("doGetDocument::"+course.getCourseId());
		
		Locale siteDefaultLocale = _portal.getSiteDefaultLocale(
				course.getGroupId());
		
		addLocalizedField(document, Field.TITLE, siteDefaultLocale,
				course.getTitleMap());
		addLocalizedField(document, Field.DESCRIPTION, siteDefaultLocale,
				course.getDescriptionMap());
		document.addDate("registrationStartDate", course.getRegistrationStartDate());
		document.addDate("registrationEndDate", course.getRegistrationEndDate());
		document.addDate("executionStartDate", course.getExecutionStartDate());
		document.addDate("executionEndDate", course.getExecutionEndDate());
		document.addKeyword(Field.COMPANY_ID, course.getCompanyId());
		document.addKeyword(Field.SCOPE_GROUP_ID, course.getGroupId());
		
		AssetEntry assetEntry = assetEntryLocalService.getEntry(Course.class.getName(), course.getCourseId());

		List<AssetCategory> assetCategories =
				assetEntry.getCategories();
		
		log.debug("assetCategories: " + assetCategories.size());

		long[] assetCategoryIds = ListUtil.toLongArray(
			assetCategories, AssetCategory.CATEGORY_ID_ACCESSOR);

		document.addKeyword(Field.ASSET_CATEGORY_IDS, assetCategoryIds);
		
		addAssetCategoryTitles(
				document, Field.ASSET_CATEGORY_TITLES, assetCategories);
		
		ExpandoBridgeIndexerUtil.addAttributes(document, course.getExpandoBridge());
		
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
		log.debug("CourseIndexer::doGetSummary::"+document.getField(Field.TITLE));
		Summary summary = createSummary(document);

		summary.setMaxContentLength(200);

		return summary;
	}

	@Override
	protected void doReindex(Course course) throws Exception {
		Document document = getDocument(course);
		log.debug("CourseIndexer::doReindex::"+course.getCourseId());
		indexWriterHelper.updateDocument(
			getSearchEngineId(), course.getCompanyId(), document,
			isCommitImmediately());
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		Course course = courseLocalService.getCourse(classPK);
		log.debug("CourseIndexer::doReindex::"+classPK);
		doReindex(course);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		reindexEntries(companyId);
	}
	
	protected void reindexEntries(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			courseLocalService.getIndexableActionableDynamicQuery();
		
		log.debug("CourseIndexer::reindexEntries::"+companyId);
		
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
			new ActionableDynamicQuery.PerformActionMethod<Course>() {

				@Override
				public void performAction(Course course) {
					try {
						AssetEntry assetEntry = assetEntryLocalService.getEntry(Course.class.getName(), course.getCourseId());
						
						if(assetEntry.isVisible()) {
							Document document = getDocument(course);
	
							log.debug("añado el documento: " + course.getCourseId());
							indexableActionableDynamicQuery.addDocuments(document);
						}
					}
					catch (PortalException pe) {
						if (log.isWarnEnabled()) {
							log.warn(
								"Unable to index blogs entry " +
									course.getCourseId(),
								pe);
						}
					}
				}

			});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}
	
	@Reference(unbind = "-")
	protected void setCourseLocalService(CourseLocalService courseLocalService) {
		this.courseLocalService = courseLocalService;
	}
	
	private CourseLocalService courseLocalService;
	
	@Reference(unbind = "-")
	protected void setAssetEntryLocalService(AssetEntryLocalService assetEntryLocalService) {
		this.assetEntryLocalService = assetEntryLocalService;
	}
	
	private AssetEntryLocalService assetEntryLocalService;
	
	@Reference
	protected IndexWriterHelper indexWriterHelper;
	
	private static final Log log = LogFactoryUtil.getLog(
			CourseIndexer.class);
	
	@Reference
	private Portal _portal;
}
