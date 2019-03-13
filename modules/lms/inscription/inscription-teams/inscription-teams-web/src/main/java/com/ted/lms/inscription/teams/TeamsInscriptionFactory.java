package com.ted.lms.inscription.teams;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.TeamLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.ted.lms.inscription.teams.constants.InscriptionTeamsPortletKeys;
import com.ted.lms.model.BaseInscriptionTypeFactory;
import com.ted.lms.model.Course;
import com.ted.lms.model.InscriptionTypeFactory;
import com.ted.lms.service.CourseLocalService;

import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	    immediate = true,
	    property = {},
	    service = InscriptionTypeFactory.class
	)
public class TeamsInscriptionFactory extends BaseInscriptionTypeFactory{
	public static final long TYPE = 1;

	@Override
	public String getClassName() {
		return TeamsInscriptionFactory.class.getName();
	}

	@Override
	public long getType() {
		return TYPE;
	}
	
	@Override
	public TeamsInscription getInscriptionType(Course course, ServiceContext serviceContext) throws PortalException {	
		return new TeamsInscription(course, serviceContext, courseLocalService, userLocalService, teamLocalService);
	}
	
	@Override
	public String getTitle(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		return LanguageUtil.get(resourceBundle, "inscription.teams.title");
	}
	
	@Override
	public String getPortletId() {
		return InscriptionTeamsPortletKeys.INSCRIPTION_TEAMS;
	}
	
	@Reference(
		unbind = "-"
	)
	public void setResourceBundleLoader(ResourceBundleLoader resourceBundleLoader) {

		this.resourceBundleLoader = resourceBundleLoader;
	}

	protected ResourceBundleLoader resourceBundleLoader;
	
	@Reference(unbind = "-")
	protected void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}
	
	protected UserLocalService userLocalService;
	
	@Reference(unbind = "-")
	protected void setTeamLocalService(TeamLocalService teamLocalService) {
		this.teamLocalService = teamLocalService;
	}
	
	protected TeamLocalService teamLocalService;
	
	@Reference(unbind = "-")
	protected void setCourseLocalService(CourseLocalService courseLocalService) {
		this.courseLocalService = courseLocalService;
	}
	
	protected CourseLocalService courseLocalService;

}
