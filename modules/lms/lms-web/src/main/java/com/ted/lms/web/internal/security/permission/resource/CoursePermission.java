package com.ted.lms.web.internal.security.permission.resource;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.ted.lms.constants.LMSConstants;
import com.ted.lms.model.Course;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Helper para permisos sobre cursos
 * @author Virginia Martín Agudo
 *
 */
@Component(immediate = true)
public class CoursePermission {
	public static boolean contains(
            PermissionChecker permissionChecker, Course course,
            String actionId)
        throws PortalException {

        return courseModelResourcePermission.contains(
            permissionChecker, course, actionId);
    }

    public static boolean contains(
            PermissionChecker permissionChecker, long courseId, String actionId)
        throws PortalException {

        return courseModelResourcePermission.contains(
            permissionChecker, courseId, actionId);
    }

    @Reference(
        target = "(model.class.name=" + LMSConstants.COURSE_MODEL_CLASS_NAME + ")",
        unbind = "-"
    )
    protected void setCourseModelPermission(
        ModelResourcePermission<Course> modelResourcePermission) {

    	courseModelResourcePermission = modelResourcePermission;
    }

    private static ModelResourcePermission<Course>
    	courseModelResourcePermission;
}
