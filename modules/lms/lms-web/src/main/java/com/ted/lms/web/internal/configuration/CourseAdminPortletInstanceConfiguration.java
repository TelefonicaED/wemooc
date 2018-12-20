package com.ted.lms.web.internal.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.ProviderType;
import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(
	category = "course",
	scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE
)
@Meta.OCD(
	id = "com.ted.lms.web.internal.configuration.CourseAdminPortletInstanceConfiguration",
	localization = "content/Language",
	name = "course-admin-instance-configuration-name"
)
@ProviderType
public interface CourseAdminPortletInstanceConfiguration {
	@Meta.AD(
		name = "search-tags", required = false
	)
	public boolean searchTags();
	
	@Meta.AD(
		name = "search-categories", required = false
	)
	public boolean searchCategories();
	
	@Meta.AD(
		name = "search-groups", required = false
	)
	public boolean searchGroups();
	
	@Meta.AD(
		name = "search-expando-values", required = false
	)
	public String searchExpandoValues();
	
	@Meta.AD(
		name = "search-templates-selected", required = false
	)
	public boolean searchTemplatesSelected();
	
	@Meta.AD(
		name = "search-edition-expando-values", required = false
	)
	public String searchEditionExpandoValues();
	
	@Meta.AD(
		name = "course-type-registration", required = false
	)
	public boolean courseTypeRegistration();
	
	@Meta.AD(
		name = "course-num-users", required = false
	)
	public boolean courseNumUsers();
	
	@Meta.AD(
		name = "course-indexer", required = false
	)
	public boolean courseIndexer();
	
	@Meta.AD(
		name = "course-asset-links", required = false, deflt="false"
	)
	public boolean courseAssetLinks();
	
	@Meta.AD(
		name = "course-welcome-message", required = false
	)
	public boolean courseWelcomeMessage();
	
	@Meta.AD(
		name = "course-goodye-message", required = false
	)
	public boolean courseGoodbyeMessage();
	
	@Meta.AD(
		name = "course-expando-values", required = false
	)
	public String courseExpandoValues();
	
	@Meta.AD(
		name = "course-template-values", required = false
	)
	public String courseTemplateValues();
	
	@Meta.AD(
		name = "course-action-permissions", required = false
	)
	public boolean courseActionPermissions();
	
	@Meta.AD(
		name = "course-action-close", required = false
	)
	public boolean courseActionClose();

	@Meta.AD(
		name = "course-action-delete", required = false
	)
	public boolean courseActionDelete();
	
	@Meta.AD(
		name = "course-action-members", required = false
	)
	public boolean courseActionMembers();
	
	@Meta.AD(
		name = "course-action-members-calendar", required = false
	)
	public boolean courseActionMembersCalendar();
	
	@Meta.AD(
		name = "course-action-export", required = false
	)
	public boolean courseActionExport();
	
	@Meta.AD(
		name = "course-action-import", required = false
	)
	public boolean courseActionImport();
	
	@Meta.AD(
		name = "course-action-duplicate", required = false
	)
	public boolean courseActionDuplicate();
	
	@Meta.AD(
		name = "course-column-creation-date", required = false
	)
	public boolean courseColumnCreationDate();
	
	@Meta.AD(
		name = "course-column-execution-dates", required = false
	)
	public boolean courseColumnExecutionDates();
	
	@Meta.AD(
		name = "edition-indexer", required = false
	)
	public boolean editionIndexer();
	
	@Meta.AD(
		name = "edition-all-options", required = false
	)
	public boolean editionAllOptions();
	
	@Meta.AD(
		name = "edition-column-inscription-dates", required = false
	)
	public boolean editionColumnInscriptionDates();
	
	@Meta.AD(
		name = "edition-column-execution-dates", required = false
	)
	public boolean editionColumnExecutionDates();
}