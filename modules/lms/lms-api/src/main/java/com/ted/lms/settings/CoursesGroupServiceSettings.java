package com.ted.lms.settings;

import com.ted.lms.constants.LMSConstants;

import java.util.Map;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.settings.FallbackKeys;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.settings.ParameterMapSettings;
import com.liferay.portal.kernel.settings.Settings;
import com.liferay.portal.kernel.settings.SettingsFactoryUtil;
import com.liferay.portal.kernel.settings.TypedSettings;

/**
 * 
 * @author Virginia Martín Agudo
 *
 */
@Settings.Config(settingsIds = LMSConstants.SERVICE_NAME)
public class CoursesGroupServiceSettings {
	
	public static CoursesGroupServiceSettings getInstance(long groupId)throws PortalException {

		Settings settings = SettingsFactoryUtil.getSettings(
			new GroupServiceSettingsLocator(
				groupId, LMSConstants.SERVICE_NAME));

		return new CoursesGroupServiceSettings(settings);
	}

	public static CoursesGroupServiceSettings getInstance(
			long groupId, Map<String, String[]> parameterMap)
		throws PortalException {

		Settings settings = SettingsFactoryUtil.getSettings(
			new GroupServiceSettingsLocator(
				groupId, LMSConstants.SERVICE_NAME));

		return new CoursesGroupServiceSettings(
			new ParameterMapSettings(parameterMap, settings));
	}

	public static void registerSettingsMetadata() {
		SettingsFactoryUtil.registerSettingsMetadata(CoursesGroupServiceSettings.class, null, _getFallbackKeys());
	}

	public CoursesGroupServiceSettings(Settings settings) {
		_typedSettings = new TypedSettings(settings);
	}

	public int getSmallImageWidth() {
		return _typedSettings.getIntegerValue("smallImageWidth");
	}

	private static FallbackKeys _getFallbackKeys() {
		FallbackKeys fallbackKeys = new FallbackKeys();

		return fallbackKeys;
	}

	static {
		SettingsFactoryUtil.registerSettingsMetadata(
			CoursesGroupServiceSettings.class, null, _getFallbackKeys());
	}

	private final TypedSettings _typedSettings;
}
