package com.ted.lms.calification.zero.to.n.internal.upgrade.v1_0_0;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.service.ReleaseLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.ted.lms.calification.zero.to.n.ZeroToNCalificationType;
import com.ted.lms.calification.zero.to.n.ZeroToNCalificationTypeFactory;
import com.ted.lms.constants.CourseConstants;
import com.ted.lms.model.Course;
import com.ted.lms.service.CourseLocalService;

import java.util.List;

public class UpgradeCourseExtraData extends UpgradeProcess {

	public UpgradeCourseExtraData(CourseLocalService courseServiceUtil, ReleaseLocalService releaseLocalService) {
		this.courseLocalService = courseServiceUtil;
		this.releaseLocalService = releaseLocalService;
	}
	
	@Override
	protected void doUpgrade() throws Exception {
		//Comprobamos si se está migrando o no
		Release release = releaseLocalService.fetchRelease("liferaylms-portlet");
		if(release != null && release.getBuildNumber() > 0) {
			//Actualizamos del prefspropsutil al campo CourseExtraData
			List<Course> listCourses = courseLocalService.getCourses(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			long value = 0;
			ZeroToNCalificationType zeroToNCalificationType = null;
			JSONObject calification = null;
			for(Course course: listCourses) {
				if(course.getCalificationType() == ZeroToNCalificationTypeFactory.TYPE) {
					value = PrefsPropsUtil.getLong(course.getGroupCreatedId(), "ceroton-value",-1);
					if(value > 0) {
						zeroToNCalificationType = new ZeroToNCalificationType(course);
						zeroToNCalificationType.setMaxValue(value);
						calification = zeroToNCalificationType.createJSONCalification();
						course.addCourseExtraDataJSON(CourseConstants.JSON_CALIFICATION, calification);
						courseLocalService.updateCourse(course);
					}
				}
			}
		}	
	}

	private final CourseLocalService courseLocalService;
	private final ReleaseLocalService releaseLocalService;
}
