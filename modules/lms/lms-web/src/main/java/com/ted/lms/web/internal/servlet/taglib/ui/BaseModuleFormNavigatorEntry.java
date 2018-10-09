package com.ted.lms.web.internal.servlet.taglib.ui;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.servlet.taglib.ui.BaseJSPFormNavigatorEntry;
import com.ted.lms.model.Module;

import java.util.Locale;

public abstract class BaseModuleFormNavigatorEntry extends BaseJSPFormNavigatorEntry<Module> {

	@Override
	public String getCategoryKey() {
		return StringPool.BLANK;
	}

	@Override
	public String getFormNavigatorId() {
		return FormNavigatorConstants.FORM_NAVIGATOR_ID_MODULE;
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, getKey());
	}
}
