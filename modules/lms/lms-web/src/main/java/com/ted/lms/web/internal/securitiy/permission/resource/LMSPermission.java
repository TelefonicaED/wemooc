package com.ted.lms.web.internal.securitiy.permission.resource;

import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.ted.lms.constants.LMSConstants;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true)
public class LMSPermission {

	public static boolean contains(
        PermissionChecker permissionChecker, long groupId, String actionId) {

        return portletResourcePermission.contains(
            permissionChecker, groupId, actionId);
    }

    @Reference(
        target = "(resource.name=" + LMSConstants.RESOURCE_NAME + ")",
        unbind = "-"
    )
    protected void setPortletResourcePermission(
        PortletResourcePermission portletResourcePermission) {

        this.portletResourcePermission = portletResourcePermission;
    }

    private static PortletResourcePermission portletResourcePermission;

}
