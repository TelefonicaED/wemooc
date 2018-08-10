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
import com.ted.lms.model.LearningActivity;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * Declara las actividades para que se usen como actividad social
 * @author Virginia Martín Agudo
 *
 */
@Component(
		property = "javax.portlet.name=" + LMSPortletKeys.LEARNING_ACTIVITY,
		service = SocialActivityInterpreter.class
	)
public class LearningActivityActivityInterpreter extends BaseSocialActivityInterpreter {
	
	private static final String[] CLASS_NAMES = {LearningActivity.class.getName()};
	
	@Reference(
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY,
		target = "(bundle.symbolic.name=com.ted.lms.web)"
	)
	private volatile ResourceBundleLoader resourceBundleLoader;
	
	
	@Reference(target = "(model.class.name=" + LMSConstants.LEARNING_ACTIVITY_MODEL_CLASS_NAME + ")")
	private ModelResourcePermission<LearningActivity>
		learningActivityModelResourcePermission;

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

		return learningActivityModelResourcePermission.contains(
			permissionChecker, activity.getClassPK(), actionId);
	}

}
