package com.ted.lms.item.selector.web.internal.display.context;

import com.liferay.item.selector.ItemSelectorReturnTypeResolver;
import com.liferay.item.selector.ItemSelectorReturnTypeResolverHandler;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.item.selector.criterion.CourseItemSelectorCriterion;
import com.ted.lms.item.selector.web.internal.CourseItemSelectorView;
import com.ted.lms.service.CourseLocalService;

import java.util.Locale;

import javax.portlet.ActionRequest;
import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.servlet.http.HttpServletRequest;

public class CourseItemSelectorViewDisplayContext {
	
	public CourseItemSelectorViewDisplayContext(CourseItemSelectorCriterion lmsItemSelectorCriterion, CourseItemSelectorView lmsItemSelectorView,
			ItemSelectorReturnTypeResolverHandler itemSelectorReturnTypeResolverHandler, String itemSelectedEventName, 
			boolean search, PortletURL portletURL, CourseLocalService courseLocalService) {

			this.courseItemSelectorCriterion = lmsItemSelectorCriterion;
			this.courseItemSelectorView = lmsItemSelectorView;
			this.itemSelectorReturnTypeResolverHandler = itemSelectorReturnTypeResolverHandler;
			this.itemSelectedEventName = itemSelectedEventName;
			this.search = search;
			this.portletURL = portletURL;
			this.courseLocalService = courseLocalService;
		}

		public Folder fetchAttachmentsFolder(long userId, long groupId) {
			return courseLocalService.fetchAttachmentsFolder(userId, groupId);
		}

		public CourseItemSelectorCriterion getCourseItemSelectorCriterion() {
			return this.courseItemSelectorCriterion;
		}

		public String getItemSelectedEventName() {
			return this.itemSelectedEventName;
		}

		public ItemSelectorReturnTypeResolver getItemSelectorReturnTypeResolver() {
			return this.itemSelectorReturnTypeResolverHandler.getItemSelectorReturnTypeResolver(
					courseItemSelectorCriterion, courseItemSelectorView, FileEntry.class);
		}

		public PortletURL getPortletURL(HttpServletRequest request, LiferayPortletResponse liferayPortletResponse) throws PortletException {

			PortletURL portletURL = PortletURLUtil.clone(this.portletURL, liferayPortletResponse);

			portletURL.setParameter("selectedTab", String.valueOf(getTitle(request.getLocale())));

			return portletURL;
		}

		public String getTitle(Locale locale) {
			return courseItemSelectorView.getTitle(locale);
		}

		public PortletURL getUploadURL(LiferayPortletResponse liferayPortletResponse) {

			PortletURL portletURL = liferayPortletResponse.createActionURL(LMSPortletKeys.COURSE);

			portletURL.setParameter(ActionRequest.ACTION_NAME, "/course/upload_image");

			return portletURL;
		}

		public boolean isSearch() {
			return this.search;
		}

		private final CourseItemSelectorCriterion courseItemSelectorCriterion;
		private final CourseItemSelectorView courseItemSelectorView;
		private final String itemSelectedEventName;
		private final ItemSelectorReturnTypeResolverHandler itemSelectorReturnTypeResolverHandler;
		private final PortletURL portletURL;
		private final boolean search;
		private final CourseLocalService courseLocalService;
}
