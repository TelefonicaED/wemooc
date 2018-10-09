package com.ted.lms.settings;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.settings.FallbackKeys;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.settings.ParameterMapSettings;
import com.liferay.portal.kernel.settings.Settings;
import com.liferay.portal.kernel.settings.SettingsFactoryUtil;
import com.liferay.portal.kernel.settings.TypedSettings;
import com.ted.lms.constants.LMSConstants;

import java.util.Map;

@Settings.Config(settingsIds = LMSConstants.SERVICE_NAME)
public class ModulesGroupServiceSettings {
	
	public static ModulesGroupServiceSettings getInstance(long groupId) throws PortalException {

		Settings settings = SettingsFactoryUtil.getSettings(new GroupServiceSettingsLocator(groupId, LMSConstants.SERVICE_NAME));

		return new ModulesGroupServiceSettings(settings);
	}

	public static ModulesGroupServiceSettings getInstance(long groupId, Map<String, String[]> parameterMap) throws PortalException {

		Settings settings = SettingsFactoryUtil.getSettings(new GroupServiceSettingsLocator(groupId, LMSConstants.SERVICE_NAME));

		return new ModulesGroupServiceSettings(new ParameterMapSettings(parameterMap, settings));
	}

	public static void registerSettingsMetadata() {
		SettingsFactoryUtil.registerSettingsMetadata(ModulesGroupServiceSettings.class, null, getFallbackKeys());
	}

	public ModulesGroupServiceSettings(Settings settings) {
		this.typedSettings = new TypedSettings(settings);
	}

	public int getSmallImageWidth() {
		return this.typedSettings.getIntegerValue("smallImageWidth");
	}

	private static FallbackKeys getFallbackKeys() {
		FallbackKeys fallbackKeys = new FallbackKeys();

		return fallbackKeys;
	}

	static {
		SettingsFactoryUtil.registerSettingsMetadata(ModulesGroupServiceSettings.class, null, getFallbackKeys());
	}

	private final TypedSettings typedSettings;
}
