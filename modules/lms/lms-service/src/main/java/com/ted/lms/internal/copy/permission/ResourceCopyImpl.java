package com.ted.lms.internal.copy.permission;

import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.ted.lms.copy.permission.ResourceCopy;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	service = ResourceCopy.class
)
public class ResourceCopyImpl implements ResourceCopy{
	public void copyModelResourcePermissions(long companyId, String name, long oldPrimKey, long newPrimKey) throws PortalException{
		List<ResourcePermission> oldResourcePermissions = resourcePermissionLocalService.getResourcePermissions(
				companyId, name, ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(oldPrimKey));

		if (oldResourcePermissions.isEmpty()) {
			return;
		}

		long batchCounter = counterLocalService.increment(
			ResourcePermission.class.getName(), oldResourcePermissions.size());

		batchCounter -= oldResourcePermissions.size();

		ResourcePermission resourcePermission = null;
		
		for (ResourcePermission oldResourcePermission :
				oldResourcePermissions) {
			
			resourcePermission = resourcePermissionLocalService.fetchResourcePermission(companyId, name, ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(newPrimKey), oldResourcePermission.getRoleId());
			
			if(resourcePermission == null) {
			
				resourcePermission =
						resourcePermissionLocalService.createResourcePermission(++batchCounter);
	
				resourcePermission.setCompanyId(companyId);
				resourcePermission.setName(name);
				resourcePermission.setScope(oldResourcePermission.getScope());
				resourcePermission.setPrimKey(String.valueOf(newPrimKey));
				resourcePermission.setPrimKeyId(newPrimKey);
				resourcePermission.setRoleId(oldResourcePermission.getRoleId());
				resourcePermission.setOwnerId(oldResourcePermission.getOwnerId());
				resourcePermission.setActionIds(
					oldResourcePermission.getActionIds());
				resourcePermission.setViewActionId(
					oldResourcePermission.isViewActionId());
	
				resourcePermissionLocalService.addResourcePermission(resourcePermission);
			}else {
				//Lo actualizamos
				resourcePermission.setActionIds(oldResourcePermission.getActionIds());
				resourcePermission.setViewActionId(
						oldResourcePermission.isViewActionId());
				
				resourcePermissionLocalService.updateResourcePermission(resourcePermission);
			}
		}
	}
	
	@Reference
	private ResourcePermissionLocalService resourcePermissionLocalService;
	
	@Reference
	private CounterLocalService counterLocalService;
}
