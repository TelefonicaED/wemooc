package com.ted.lms.internal.category;

import com.liferay.configuration.admin.category.ConfigurationCategory;

import org.osgi.service.component.annotations.Component;

@Component(service = ConfigurationCategory.class)
public class CourseConfigurationCategory implements ConfigurationCategory {

	@Override
	public String getCategoryKey() {
		return CATEGORY_KEY;
	}

	@Override
	public String getCategorySection() {
		return CATEGORY_SECTION;
	}
	
	/**
	 * choose one of
	 * @see https://clayui.com/docs/components/icons.html 
	 */
	@Override
	public String getCategoryIcon() {

		return "pencil";
	}

	private static final String CATEGORY_KEY = "lms";

	private static final String CATEGORY_SECTION = "content-and-data";
}
