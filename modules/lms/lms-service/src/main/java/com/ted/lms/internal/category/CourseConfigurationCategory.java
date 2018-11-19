package com.ted.lms.internal.category;

import com.liferay.configuration.admin.category.ConfigurationCategory;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = "model.class.name=com.ted.lms.internal.category.CourseConfigurationCategory",
	service = ConfigurationCategory.class
)
public class CourseConfigurationCategory implements ConfigurationCategory {

	@Override
	public String getCategoryKey() {
		return CATEGORY_KEY;
	}

	@Override
	public String getCategorySection() {
		return CATEGORY_SECTION;
	}

	private static final String CATEGORY_KEY = "courses";

	private static final String CATEGORY_SECTION = "content";
}
