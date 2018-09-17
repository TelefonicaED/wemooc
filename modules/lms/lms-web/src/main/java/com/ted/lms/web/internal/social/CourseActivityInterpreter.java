package com.ted.lms.web.internal.social;

import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferay.social.kernel.model.BaseSocialActivityInterpreter;
import com.liferay.social.kernel.model.SocialActivity;
import com.liferay.social.kernel.model.SocialActivityInterpreter;
import com.ted.lms.constants.LMSConstants;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.Course;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * Declara los cursos para que se usen como actividad social
 * @author Virginia Martín Agudo
 *
 */
@Component(
		property = "javax.portlet.name=" + LMSPortletKeys.COURSE,
		service = SocialActivityInterpreter.class
	)
public class CourseActivityInterpreter extends BaseSocialActivityInterpreter {
	
	private static final String[] CLASS_NAMES = {Course.class.getName()};
	
	@Reference(
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	private volatile ResourceBundleLoader resourceBundleLoader;
	
	
	@Reference(target = "(model.class.name=" + LMSConstants.COURSE_MODEL_CLASS_NAME + ")")
	private ModelResourcePermission<Course>
		courseModelResourcePermission;

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	protected ResourceBundleLoader getResourceBundleLoader() {
		return resourceBundleLoader;
	}

	@Override
	protected boolean hasPermissions(
			PermissionChecker permissionChecker, SocialActivity activity,
			String actionId, ServiceContext serviceContext)
		throws Exception {

		return courseModelResourcePermission.contains(
			permissionChecker, activity.getClassPK(), actionId);
	}

}
