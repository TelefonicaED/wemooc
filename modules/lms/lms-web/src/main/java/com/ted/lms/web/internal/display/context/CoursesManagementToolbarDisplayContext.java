package com.ted.lms.web.internal.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenu;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.ViewTypeItemList;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.LayoutPrototype;
import com.liferay.portal.kernel.model.LayoutSetPrototype;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.trash.TrashHelper;
import com.ted.lms.configuration.CourseServiceConfiguration;
import com.ted.lms.constants.CourseConstants;
import com.ted.lms.constants.LMSActionKeys;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.CalificationTypeFactory;
import com.ted.lms.model.CourseEvalFactory;
import com.ted.lms.model.InscriptionTypeFactory;
import com.ted.lms.registry.CalificationTypeFactoryRegistryUtil;
import com.ted.lms.registry.CourseEvalFactoryRegistryUtil;
import com.ted.lms.registry.InscriptionTypeFactoryRegistryUtil;
import com.ted.lms.security.permission.resource.LMSPermission;
import com.ted.lms.service.CourseTypeLocalServiceUtil;
import com.ted.lms.web.internal.configuration.CourseAdminPortletInstanceConfiguration;
import com.ted.lms.web.internal.util.CourseUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.servlet.http.HttpServletRequest;

public class CoursesManagementToolbarDisplayContext {
	
	private static final Log log = LogFactoryUtil.getLog(CoursesManagementToolbarDisplayContext.class);
	
	private final PortletURL currentURLObj;
	private final LiferayPortletRequest liferayPortletRequest;
	private final LiferayPortletResponse liferayPortletResponse;
	private final PortalPreferences portalPreferences;
	private final HttpServletRequest request;
	private final TrashHelper trashHelper;
	private long parentCourseId;
	private String keywords;
	private CourseAdminPortletInstanceConfiguration portletConfiguration;
	private CourseServiceConfiguration serviceConfiguration;
	private ThemeDisplay themeDisplay;
	
	public CoursesManagementToolbarDisplayContext(LiferayPortletRequest liferayPortletRequest, LiferayPortletResponse liferayPortletResponse,
			HttpServletRequest request, PortletURL currentURLObj, TrashHelper trashHelper, CourseServiceConfiguration serviceConfiguration) {

			this.liferayPortletRequest = liferayPortletRequest;
			this.liferayPortletResponse = liferayPortletResponse;
			this.request = request;
			this.currentURLObj = currentURLObj;
			this.trashHelper = trashHelper;

			this.portalPreferences = PortletPreferencesFactoryUtil.getPortalPreferences(
				liferayPortletRequest);
			
			themeDisplay = (ThemeDisplay)liferayPortletRequest.getAttribute(WebKeys.THEME_DISPLAY);
			PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();
			
			try {
				portletConfiguration = portletDisplay.getPortletInstanceConfiguration(CourseAdminPortletInstanceConfiguration.class);
			} catch (ConfigurationException e) {
				e.printStackTrace();
			}
			
			this.serviceConfiguration = serviceConfiguration;
			
			this.parentCourseId = ParamUtil.getLong(request, "parentCourseId", CourseConstants.DEFAULT_PARENT_COURSE_ID);
			log.debug("parentCourseId: " + parentCourseId);
		}

		public List<DropdownItem> getActionDropdownItems() {
			return new DropdownItemList() {
				{
					ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
					add(
						dropdownItem -> {
							dropdownItem.putData("action", "deleteCourses");

							boolean trashEnabled = trashHelper.isTrashEnabled(themeDisplay.getScopeGroupId());

							dropdownItem.setIcon(trashEnabled ? "trash" : "times");

							String label = "delete";

							if (trashEnabled) {
								label = "move-to-recycle-bin";
							}

							dropdownItem.setLabel(LanguageUtil.get(request, label));
							dropdownItem.setQuickAction(true);
						});
				}
			};
		}
		
		public long getParentCourseId() {
			return parentCourseId;
		}
		
		public long getDefaultParentCourseId() {
			return CourseConstants.DEFAULT_PARENT_COURSE_ID;
		}
		
		/**
		 * Sólo se muestra el botón de creación de curso si tienes dado de alta al menos:
		 * - Plantilla de sitio web
		 * @return
		 */
		public boolean showCreationButton() {
			List<LayoutSetPrototype> listLayoutSetPrototype = CourseUtil.getTemplates(portletConfiguration, serviceConfiguration, themeDisplay.getCompanyId());
			if(listLayoutSetPrototype == null || listLayoutSetPrototype.size() == 0) {
				return false;
			}
			List<CourseEvalFactory> listCourseEvalFactory = CourseUtil.getCourseEvalFactories(serviceConfiguration,themeDisplay.getCompanyId());
			if(listCourseEvalFactory == null || listCourseEvalFactory.size() == 0) {
				return false;
			}
			List<CalificationTypeFactory> listCalificationTypeFactory = CourseUtil.getCalificationTypeFactories(serviceConfiguration,themeDisplay.getCompanyId());
			if(listCalificationTypeFactory == null || listCalificationTypeFactory.size() == 0) {
				return false;
			}
			List<InscriptionTypeFactory> listInscriptionTypeFactory = CourseUtil.getInscriptionTypeFactories(serviceConfiguration,themeDisplay.getCompanyId());
			if(listInscriptionTypeFactory == null || listInscriptionTypeFactory.size() == 0) {
				return false;
			}
			return true;
		}
		
		public String getCreationURL() {
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
			
			//Comprobamos que el usuario tiene permisos para crear cursos
			if (!LMSPermission.contains(themeDisplay.getPermissionChecker(), themeDisplay.getScopeGroupId(), LMSActionKeys.ADD_COURSE)) {
				return null;
			}
			
			//Dependiendo si hay tipos de curso o no, mandamos a una ventana u otra
			int countCourseTypes = CourseTypeLocalServiceUtil.countCourseTypes(themeDisplay.getCompanyId());
			
			String mvcRenderCommand = parentCourseId == CourseConstants.DEFAULT_PARENT_COURSE_ID ? (countCourseTypes > 0 ? "/courses/add_course" : "/courses/edit_course") : "/courses/edit_edition";

			
			PortletURL creationCourseURL = liferayPortletResponse.createRenderURL();
			creationCourseURL.setParameter("mvcRenderCommandName", mvcRenderCommand);
			creationCourseURL.setParameter("redirect", currentURLObj.toString());
			creationCourseURL.setParameter("parentCourseId", String.valueOf(parentCourseId));
			
			return creationCourseURL.toString();
		}

		public String getDisplayStyle() {
			String displayStyle = ParamUtil.getString(request, "displayStyle");

			if (Validator.isNull(displayStyle)) {
				displayStyle = this.portalPreferences.getValue(LMSPortletKeys.COURSE, "courses-display-style", "icon");
			} else {
				portalPreferences.setValue(LMSPortletKeys.COURSE, "courses-display-style",displayStyle);
				request.setAttribute(WebKeys.SINGLE_PAGE_APPLICATION_CLEAR_CACHE, Boolean.TRUE);
			}

			return displayStyle;
		}

		public List<DropdownItem> getFilterDropdownItems() {
			
			return new DropdownItemList() {
				{
					addGroup(
						dropdownGroupItem -> {
							dropdownGroupItem.setHref("javascript:alert('hola')");
							dropdownGroupItem.setLabel(LanguageUtil.get(request, "filter-search"));
						});
				}
			};
		}

		public String getOrderByCol() {
			return ParamUtil.getString(request, "orderByCol", "title");
		}

		public String getOrderByType() {
			return ParamUtil.getString(request, "orderByType", "desc");
		}

		public String getSearchActionURL() {
			PortletURL searchURL = liferayPortletResponse.createRenderURL();

			searchURL.setParameter("mvcRenderCommandName", "/courses/view");
			searchURL.setParameter("parentCourseId", String.valueOf(parentCourseId));

			String navigation = ParamUtil.getString(request, "navigation", "entries");

			searchURL.setParameter("navigation", navigation);

			searchURL.setParameter("orderByCol", getOrderByCol());
			searchURL.setParameter("orderByType", getOrderByType());

			return searchURL.toString();
		}

		public PortletURL getSortingURL() throws PortletException {
			PortletURL sortingURL = getCurrentSortingURL();
			sortingURL.setParameter("orderByType",Objects.equals(getOrderByType(), "asc") ? "desc" : "asc");

			return sortingURL;
		}

		public ViewTypeItemList getViewTypes() {
			PortletURL portletURL = liferayPortletResponse.createRenderURL();

			portletURL.setParameter("mvcRenderCommandName", "/courses/view");
			portletURL.setParameter("parentCourseId", String.valueOf(parentCourseId));

			int delta = ParamUtil.getInteger(request, SearchContainer.DEFAULT_DELTA_PARAM);

			if (delta > 0) {
				portletURL.setParameter("delta", String.valueOf(delta));
			}

			String orderByCol = ParamUtil.getString(request, "orderByCol", "title");
			String orderByType = ParamUtil.getString(request, "orderByType", "asc");

			portletURL.setParameter("orderBycol", orderByCol);
			portletURL.setParameter("orderByType", orderByType);

			String coursesNavigation = ParamUtil.getString(request, "coursesNavigation", "all");
			portletURL.setParameter("coursesNavigation", coursesNavigation);

			int cur = ParamUtil.getInteger(request, SearchContainer.DEFAULT_CUR_PARAM);

			if (cur > 0) {
				portletURL.setParameter("cur", String.valueOf(cur));
			}

			return new ViewTypeItemList(portletURL, getDisplayStyle()) {
				{
					addCardViewTypeItem();
					addListViewTypeItem();
					addTableViewTypeItem();
				}
			};
		}

		private PortletURL getCurrentSortingURL() throws PortletException {
			PortletURL sortingURL = PortletURLUtil.clone(currentURLObj, this.liferayPortletResponse);

			sortingURL.setParameter("mvcRenderCommandName", "/courses/view");
			sortingURL.setParameter(SearchContainer.DEFAULT_CUR_PARAM, "0");
			sortingURL.setParameter("parentCourseId", String.valueOf(parentCourseId));

			String keywords = ParamUtil.getString(request, "keywords");

			if (Validator.isNotNull(keywords)) {
				sortingURL.setParameter("keywords", keywords);
			}

			return sortingURL;
		}
		
		public boolean isShowAdvancedSearch() {
			return true;
		}

		
}
