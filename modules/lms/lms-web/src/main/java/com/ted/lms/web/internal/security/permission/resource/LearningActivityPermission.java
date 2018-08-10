package com.ted.lms.web.internal.security.permission.resource;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.ted.lms.constants.LMSConstants;
import com.ted.lms.model.LearningActivity;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Helper para permisos sobre actividades
 * @author Virginia Martín Agudo
 *
 */
@Component(immediate = true)
public class LearningActivityPermission {
	public static boolean contains(
            PermissionChecker permissionChecker, LearningActivity learningActivity,
            String actionId)
        throws PortalException {

        return learningActivityModelResourcePermission.contains(
            permissionChecker, learningActivity, actionId);
    }

    public static boolean contains(
            PermissionChecker permissionChecker, long actId, String actionId)
        throws PortalException {

        return learningActivityModelResourcePermission.contains(
            permissionChecker, actId, actionId);
    }

    @Reference(
        target = "(model.class.name=" + LMSConstants.LEARNING_ACTIVITY_MODEL_CLASS_NAME + ")",
        unbind = "-"
    )
    protected void setCourseModelPermission(
        ModelResourcePermission<LearningActivity> modelResourcePermission) {

    	learningActivityModelResourcePermission = modelResourcePermission;
    }

    private static ModelResourcePermission<LearningActivity>
    learningActivityModelResourcePermission;
}
