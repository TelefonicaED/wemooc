package com.ted.lms.security.permission.resource;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.ted.lms.constants.LMSConstants;
import com.ted.lms.model.Module;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Helper para permisos sobre los módulos
 * @author Virginia Martín Agudo
 *
 */
@Component(immediate = true, service = {})
public class ModulePermission {
	public static boolean contains(
            PermissionChecker permissionChecker, Module module,
            String actionId)
        throws PortalException {

        return moduleModelResourcePermission.contains(
            permissionChecker, module, actionId);
    }

    public static boolean contains(
            PermissionChecker permissionChecker, long moduleId, String actionId)
        throws PortalException {

        return moduleModelResourcePermission.contains(
            permissionChecker, moduleId, actionId);
    }

    @Reference(
        target = "(model.class.name=" + LMSConstants.MODULE_MODEL_CLASS_NAME + ")",
        unbind = "-"
    )
    protected void setModuleModelPermission(
        ModelResourcePermission<Module> modelResourcePermission) {

    	moduleModelResourcePermission = modelResourcePermission;
    }

    private static ModelResourcePermission<Module>
    	moduleModelResourcePermission;
}
