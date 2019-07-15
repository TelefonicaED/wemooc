package com.ted.lms.web.internal.template;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.comment.CommentManager;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portletdisplaytemplate.BasePortletDisplayTemplateHandler;
import com.liferay.portal.kernel.portletdisplaytemplate.PortletDisplayTemplateManager;
import com.liferay.portal.kernel.template.TemplateHandler;
import com.liferay.portal.kernel.template.TemplateVariableGroup;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.taglib.security.PermissionsURLTag;
import com.ted.lms.configuration.CourseServiceConfiguration;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.CourseResult;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.service.CourseResultLocalService;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

@Component(
	configurationPid = "com.ted.lms.configuration.CourseServiceConfiguration",
	immediate = true, property = "javax.portlet.name=" + LMSPortletKeys.MY_COURSES,
	service = TemplateHandler.class
)
public class MyCoursesPortletDisplayTemplateHandler extends BasePortletDisplayTemplateHandler {
	@Override
	public String getClassName() {
		return CourseResult.class.getName();
	}

	@Override
	public Map<String, Object> getCustomContextObjects() {
		Map<String, Object> contextObjects = new HashMap<>();

		contextObjects.put("commentManager", commentManager);
		contextObjects.put("permissionsURLTag", new PermissionsURLTag());

		return contextObjects;
	}

	@Override
	public String getName(Locale locale) {
		String portletTitle = portal.getPortletTitle(
			LMSPortletKeys.MY_COURSES, locale);

		return LanguageUtil.format(locale, "x-template", portletTitle, false);
	}

	@Override
	public String getResourceName() {
		return LMSPortletKeys.MY_COURSES;
	}

	@Override
	public Map<String, TemplateVariableGroup> getTemplateVariableGroups(
			long classPK, String language, Locale locale)
		throws Exception {

		Map<String, TemplateVariableGroup> templateVariableGroups =
			super.getTemplateVariableGroups(classPK, language, locale);

		String[] restrictedVariables = getRestrictedVariables(language);

		TemplateVariableGroup courseServicesTemplateVariableGroup =
			new TemplateVariableGroup("course-result-services", restrictedVariables);

		courseServicesTemplateVariableGroup.setAutocompleteEnabled(false);

		courseServicesTemplateVariableGroup.addServiceLocatorVariables(
			CourseResultLocalService.class, CourseLocalService.class);

		templateVariableGroups.put(
			courseServicesTemplateVariableGroup.getLabel(),
			courseServicesTemplateVariableGroup);

		TemplateVariableGroup fieldsTemplateVariableGroup =
			templateVariableGroups.get("fields");

		fieldsTemplateVariableGroup.empty();

		fieldsTemplateVariableGroup.addCollectionVariable(
			"courseResults", List.class, PortletDisplayTemplateManager.ENTRIES,
			"courseResult", CourseResult.class, "curCourseResult", "passed");

		return templateVariableGroups;
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		courseServiceConfiguration = ConfigurableUtil.createConfigurable(
				CourseServiceConfiguration.class, properties);
	}

	@Override
	protected String getTemplatesConfigPath() {
		return courseServiceConfiguration.displayTemplatesMyCoursesConfig();
	}

	private volatile CourseServiceConfiguration courseServiceConfiguration;

	@Reference
	private CommentManager commentManager;
	
	@Reference
	private Portal portal;
}
