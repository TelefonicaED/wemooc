package com.ted.lms.item.selector.web.internal;

import com.liferay.item.selector.BaseItemSelectorCriterionHandler;
import com.liferay.item.selector.ItemSelectorCriterionHandler;
import com.ted.lms.item.selector.criterion.CourseItemSelectorCriterion;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Virginia Martín Agudo
 */
@Component(service = ItemSelectorCriterionHandler.class)
public class CourseItemSelectorCriterionHandler extends BaseItemSelectorCriterionHandler<CourseItemSelectorCriterion> {

	@Override
	public Class<CourseItemSelectorCriterion> getItemSelectorCriterionClass() {
		return CourseItemSelectorCriterion.class;
	}

	@Activate
	@Override
	protected void activate(BundleContext bundleContext) {
		super.activate(bundleContext);
	}

	@Deactivate
	@Override
	protected void deactivate() {
		super.deactivate();
	}

}
