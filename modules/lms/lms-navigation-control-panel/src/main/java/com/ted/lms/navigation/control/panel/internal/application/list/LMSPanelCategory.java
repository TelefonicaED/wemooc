package com.ted.lms.navigation.control.panel.internal.application.list;

import java.util.Locale;

import com.liferay.application.list.BasePanelCategory;
import com.liferay.application.list.PanelCategory;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.ted.lms.navigation.control.panel.internal.application.list.constants.LMSPanelCategoryKeys;

import org.osgi.service.component.annotations.Component;

/**
 * @author JE10436
 */
@Component(
	immediate = true,
	property = {
		"panel.category.key=" + PanelCategoryKeys.CONTROL_PANEL,
		"panel.category.order:Integer=500"
	},
	service = PanelCategory.class
)
public class LMSPanelCategory extends BasePanelCategory {

	@Override
	public String getKey() {
		return LMSPanelCategoryKeys.CONTROL_PANEL_LMS;
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, "category.control_panel.lms");
	}

}