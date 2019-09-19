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

package com.ted.lms.item.selector.web.internal;

import com.liferay.document.library.display.context.DLMimeTypeDisplayContext;
import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.ItemSelectorReturnTypeResolverHandler;
import com.liferay.item.selector.ItemSelectorView;
import com.liferay.item.selector.criteria.FileEntryItemSelectorReturnType;
import com.liferay.item.selector.criteria.URLItemSelectorReturnType;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.ted.lms.item.selector.constants.LMSItemSelectorViewConstants;
import com.ted.lms.item.selector.criterion.CourseItemSelectorCriterion;
import com.ted.lms.item.selector.web.internal.constants.LMSItemSelectorWebKeys;
import com.ted.lms.item.selector.web.internal.display.context.CourseItemSelectorViewDisplayContext;
import com.ted.lms.service.CourseLocalService;

import java.io.IOException;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.portlet.PortletURL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Roberto Díaz
 */
@Component(
	property = "item.selector.view.key=" + LMSItemSelectorViewConstants.COURSE_ITEM_SELECTOR_VIEW_KEY
)
public class CourseItemSelectorView implements ItemSelectorView<CourseItemSelectorCriterion> {

	@Override
	public Class<CourseItemSelectorCriterion> getItemSelectorCriterionClass() {
		return CourseItemSelectorCriterion.class;
	}

	public ServletContext getServletContext() {
		return this.servletContext;
	}

	@Override
	public List<ItemSelectorReturnType> getSupportedItemSelectorReturnTypes() {
		return supportedItemSelectorReturnTypes;
	}

	@Override
	public String getTitle(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		return LanguageUtil.get(resourceBundle, "course-images");
	}

	@Override
	public boolean isVisible(ThemeDisplay themeDisplay) {
		return true;
	}

	@Override
	public void renderHTML(ServletRequest request, ServletResponse response, CourseItemSelectorCriterion lmsItemSelectorCriterion,
			PortletURL portletURL, String itemSelectedEventName, boolean search) throws IOException, ServletException {
		
		CourseItemSelectorViewDisplayContext lmsItemSelectorViewDisplayContext = new CourseItemSelectorViewDisplayContext(
					lmsItemSelectorCriterion, this, this.itemSelectorReturnTypeResolverHandler, itemSelectedEventName, search, portletURL,
					this.courseLocalService);

		request.setAttribute(LMSItemSelectorWebKeys.COURSE_ITEM_SELECTOR_VIEW_DISPLAY_CONTEXT, lmsItemSelectorViewDisplayContext);

		request.setAttribute(LMSItemSelectorWebKeys.DL_MIME_TYPE_DISPLAY_CONTEXT, this.dlMimeTypeDisplayContext);

		ServletContext servletContext = getServletContext();

		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/course_attachments.jsp");

		requestDispatcher.include(request, response);
	}

	@Reference(unbind = "-")
	public void setItemSelectorReturnTypeResolverHandler(ItemSelectorReturnTypeResolverHandler itemSelectorReturnTypeResolverHandler) {
		this.itemSelectorReturnTypeResolverHandler = itemSelectorReturnTypeResolverHandler;
	}

	@Reference(
		target = "(osgi.web.symbolicname=com.ted.lms.item.selector.web)",
		unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	private static final List<ItemSelectorReturnType> supportedItemSelectorReturnTypes = Collections.unmodifiableList(
			ListUtil.fromArray(new ItemSelectorReturnType[] {new FileEntryItemSelectorReturnType(), new URLItemSelectorReturnType()}));

	@Reference
	private CourseLocalService courseLocalService;

	@Reference(
		cardinality = ReferenceCardinality.OPTIONAL,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	private volatile DLMimeTypeDisplayContext dlMimeTypeDisplayContext;

	private ItemSelectorReturnTypeResolverHandler itemSelectorReturnTypeResolverHandler;
	private ServletContext servletContext;
	
	@Reference(
		unbind = "-"
	)
	public void setResourceBundleLoader(ResourceBundleLoader resourceBundleLoader) {

		this.resourceBundleLoader = resourceBundleLoader;
	}

	protected ResourceBundleLoader resourceBundleLoader;

}