package com.ted.lms.internal.trash;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.trash.BaseTrashHandler;
import com.liferay.portal.kernel.trash.TrashHandler;
import com.liferay.portal.kernel.util.Portal;
import com.ted.lms.model.Course;
import com.ted.lms.service.CourseLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = "model.class.name=com.ted.lms.model.Course",
	service = TrashHandler.class
)
public class CourseTrashHandler extends BaseTrashHandler {

	@Override
	public void deleteTrashEntry(long classPK) throws PortalException {
		courseLocalService.deleteCourse(classPK);
	}

	@Override
	public String getClassName() {
		return Course.class.getName();
	}

	@Override
	public void restoreTrashEntry(long userId, long classPK) throws PortalException {
		courseLocalService.restoreEntryFromTrash(userId, classPK);
	}

	@Override
	protected boolean hasPermission(PermissionChecker permissionChecker, long classPK, String actionId)
			throws PortalException {
		return courseModelResourcePermission.contains(permissionChecker, classPK, actionId);
	}

	@Reference(unbind = "-")
	protected void setCourseLocalService(CourseLocalService courseLocalService) {

		this.courseLocalService = courseLocalService;
	}

	private CourseLocalService courseLocalService;

	@Reference(target = "(model.class.name=com.ted.lms.model.Course)")
	private ModelResourcePermission<Course> courseModelResourcePermission;

	@Reference
	private Portal _portal;
}
