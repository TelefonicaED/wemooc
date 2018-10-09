package com.ted.lms.web.internal.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenu;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.SafeConsumer;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.ViewTypeItemList;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.trash.TrashHelper;
import com.ted.lms.constants.LMSActionKeys;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.security.permission.resource.CoursePermission;
import com.ted.lms.security.permission.resource.LMSPermission;
import com.ted.lms.security.permission.resource.ModulePermission;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.service.CourseLocalServiceUtil;

import java.util.List;
import java.util.Objects;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.servlet.http.HttpServletRequest;

public class ModulesManagementToolbarDisplayContext {
	public ModulesManagementToolbarDisplayContext(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse,
			HttpServletRequest request, PortletURL currentURLObj,
			TrashHelper trashHelper) {

			_liferayPortletRequest = liferayPortletRequest;
			_liferayPortletResponse = liferayPortletResponse;
			_request = request;
			_currentURLObj = currentURLObj;
			_trashHelper = trashHelper;

			_portalPreferences = PortletPreferencesFactoryUtil.getPortalPreferences(
				liferayPortletRequest);
		}

		public List<DropdownItem> getActionDropdownItems() {
			return new DropdownItemList() {
				{
					ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
						WebKeys.THEME_DISPLAY);

					add(
						SafeConsumer.ignore(
							dropdownItem -> {
								dropdownItem.putData("action", "deleteModules");

								boolean trashEnabled = _trashHelper.isTrashEnabled(
									themeDisplay.getScopeGroupId());

								dropdownItem.setIcon(
									trashEnabled ? "trash" : "times");

								String label = "delete";

								if (trashEnabled) {
									label = "move-to-recycle-bin";
								}

								dropdownItem.setLabel(
									LanguageUtil.get(_request, label));

								dropdownItem.setQuickAction(true);
							}));
				}
			};
		}

		public CreationMenu getCreationMenu() {
			ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
				WebKeys.THEME_DISPLAY);

			try {
				if (!CoursePermission.contains(themeDisplay.getPermissionChecker(), 
								CourseLocalServiceUtil.getCourseByGroupCreatedId(themeDisplay.getScopeGroupId()), LMSActionKeys.ADD_MODULE)) {

					return null;
				}
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			CreationMenu creationMenu = new CreationMenu();

			creationMenu.addDropdownItem(
				dropdownItem -> {
					dropdownItem.setHref(
						_liferayPortletResponse.createRenderURL(),
						"mvcRenderCommandName", "/modules/edit_module", "redirect",
						_currentURLObj.toString());
					dropdownItem.setLabel(
						LanguageUtil.get(_request, "add-module"));
				});

			return creationMenu;
		}

		public String getDisplayStyle() {
			String displayStyle = ParamUtil.getString(_request, "displayStyle");

			if (Validator.isNull(displayStyle)) {
				displayStyle = _portalPreferences.getValue(
					LMSPortletKeys.MODULE, "modules-display-style", "icon");
			}
			else {
				_portalPreferences.setValue(
						LMSPortletKeys.MODULE, "modules-display-style",
					displayStyle);

				_request.setAttribute(
					WebKeys.SINGLE_PAGE_APPLICATION_CLEAR_CACHE, Boolean.TRUE);
			}

			return displayStyle;
		}

		public List<DropdownItem> getFilterDropdownItems() {
			return new DropdownItemList() {
				{
					addGroup(
						SafeConsumer.ignore(
							dropdownGroupItem -> {
								dropdownGroupItem.setDropdownItems(
									_getFilterNavigationDropdownItems());
								dropdownGroupItem.setLabel(
									LanguageUtil.get(
										_request, "filter-by-navigation"));
							}));
					addGroup(
						dropdownGroupItem -> {
							dropdownGroupItem.setDropdownItems(
								_getOrderByDropdownItems());
							dropdownGroupItem.setLabel(
								LanguageUtil.get(_request, "order-by"));
						});
				}
			};
		}

		public String getOrderByCol() {
			return ParamUtil.getString(_request, "orderByCol", "title");
		}

		public String getOrderByType() {
			return ParamUtil.getString(_request, "orderByType", "desc");
		}

		public String getSearchActionURL() {
			PortletURL searchURL = _liferayPortletResponse.createRenderURL();

			searchURL.setParameter("mvcRenderCommandName", "/modules/view");

			String navigation = ParamUtil.getString(
				_request, "navigation", "entries");

			searchURL.setParameter("navigation", navigation);

			searchURL.setParameter("orderByCol", getOrderByCol());
			searchURL.setParameter("orderByType", getOrderByType());

			return searchURL.toString();
		}

		public PortletURL getSortingURL() throws PortletException {
			PortletURL sortingURL = _getCurrentSortingURL();

			sortingURL.setParameter(
				"orderByType",
				Objects.equals(getOrderByType(), "asc") ? "desc" : "asc");

			return sortingURL;
		}

		public ViewTypeItemList getViewTypes() {
			PortletURL portletURL = _liferayPortletResponse.createRenderURL();

			portletURL.setParameter("mvcRenderCommandName", "/modules/view");

			int delta = ParamUtil.getInteger(
				_request, SearchContainer.DEFAULT_DELTA_PARAM);

			if (delta > 0) {
				portletURL.setParameter("delta", String.valueOf(delta));
			}

			String orderByCol = ParamUtil.getString(
				_request, "orderByCol", "title");
			String orderByType = ParamUtil.getString(
				_request, "orderByType", "asc");

			portletURL.setParameter("orderBycol", orderByCol);
			portletURL.setParameter("orderByType", orderByType);

			String modulesNavigation = ParamUtil.getString(
				_request, "modulesNavigation", "all");

			portletURL.setParameter("modulesNavigation", modulesNavigation);

			int cur = ParamUtil.getInteger(
				_request, SearchContainer.DEFAULT_CUR_PARAM);

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

		private PortletURL _getCurrentSortingURL() throws PortletException {
			PortletURL sortingURL = PortletURLUtil.clone(
				_currentURLObj, _liferayPortletResponse);

			sortingURL.setParameter("mvcRenderCommandName", "/modules/view");

			sortingURL.setParameter(SearchContainer.DEFAULT_CUR_PARAM, "0");

			String keywords = ParamUtil.getString(_request, "keywords");

			if (Validator.isNotNull(keywords)) {
				sortingURL.setParameter("keywords", keywords);
			}

			return sortingURL;
		}

		private List<DropdownItem> _getFilterNavigationDropdownItems() {
			final String modulesNavigation = ParamUtil.getString(
				_request, "modulesNavigation", "all");

			return new DropdownItemList() {
				{
					add(
						SafeConsumer.ignore(
							dropdownItem -> {
								dropdownItem.setActive(
									modulesNavigation.equals("all"));

								PortletURL navigationPortletURL =
									PortletURLUtil.clone(
										_currentURLObj, _liferayPortletResponse);

								dropdownItem.setHref(
									navigationPortletURL, "modulesNavigation",
									"all");

								dropdownItem.setLabel(
									LanguageUtil.get(_request, "all"));
							}));
					add(
						SafeConsumer.ignore(
							dropdownItem -> {
								dropdownItem.setActive(
									modulesNavigation.equals("mine"));

								PortletURL navigationPortletURL =
									PortletURLUtil.clone(
										_currentURLObj, _liferayPortletResponse);

								dropdownItem.setHref(
									navigationPortletURL, "modulesNavigation",
									"mine");

								dropdownItem.setLabel(
									LanguageUtil.get(_request, "mine"));
							}));
				}
			};
		}

		private List<DropdownItem> _getOrderByDropdownItems() {
			return new DropdownItemList() {
				{
					add(
						SafeConsumer.ignore(
							dropdownItem -> {
								dropdownItem.setActive(
									"title".equals(getOrderByCol()));
								dropdownItem.setHref(
									_getCurrentSortingURL(), "orderByCol", "title");
								dropdownItem.setLabel(
									LanguageUtil.get(_request, "title"));
							}));

					add(
						SafeConsumer.ignore(
							dropdownItem -> {
								dropdownItem.setActive(
									"display-date".equals(getOrderByCol()));
								dropdownItem.setHref(
									_getCurrentSortingURL(), "orderByCol",
									"display-date");
								dropdownItem.setLabel(
									LanguageUtil.get(_request, "display-date"));
							}));
				}
			};
		}

		private final PortletURL _currentURLObj;
		private final LiferayPortletRequest _liferayPortletRequest;
		private final LiferayPortletResponse _liferayPortletResponse;
		private final PortalPreferences _portalPreferences;
		private final HttpServletRequest _request;
		private final TrashHelper _trashHelper;
}
