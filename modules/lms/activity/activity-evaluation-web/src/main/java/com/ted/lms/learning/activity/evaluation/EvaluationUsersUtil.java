package com.ted.lms.learning.activity.evaluation;


import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.ted.lms.learning.activity.evaluation.web.constants.EvaluationConstants;
import com.ted.lms.model.Course;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.registry.LearningActivityTypeFactoryRegistryUtil;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.service.LearningActivityLocalService;
import com.ted.lms.service.StudentLocalService;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import aQute.bnd.annotation.ProviderType;

@Component(
	immediate = true, service = EvaluationUsersUtil.class
)
@ProviderType
public class EvaluationUsersUtil {
	
	private static final Log log = LogFactoryUtil.getLog(EvaluationUsersUtil.class);
	
	public void evaluate() {
		List<LearningActivity> activitiesEvaluation = learningActivityLocalService.getLearningActivitiesByTypeId(EvaluationConstants.TYPE);
		EvaluationActivityType evaluationActivityType = null;
		List<User> users = null;
		Course course = null;
		if(activitiesEvaluation != null) log.debug("activitiesEvaluation: " + activitiesEvaluation.size());
		EvaluationActivityTypeFactory evaluationActivityTypeFactory = (EvaluationActivityTypeFactory)LearningActivityTypeFactoryRegistryUtil.getLearningActivityTypeFactoryByType(EvaluationConstants.TYPE);
		System.out.println("evaluationActivityTypeFactory: " + evaluationActivityTypeFactory);
		for(LearningActivity activity: activitiesEvaluation) {
			try {
				System.out.println("activityId: " + activity.getActId() + " - groupId: " + activity.getGroupId());
				evaluationActivityType = evaluationActivityTypeFactory.getEvaluationActivityType(activity);
				course = courseLocalService.getCourseByGroupCreatedId(activity.getGroupId());
				users = studentLocalService.getStudentsFromCourse(course.getCourseId(), activity.getCompanyId(), null, WorkflowConstants.STATUS_APPROVED, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
				for(User user: users) {
					evaluationActivityType.evaluateUser(user.getUserId(), null);
				}
			} catch (PortalException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Reference(unbind = "-")
	protected void setLearningActivityLocalService(LearningActivityLocalService learningActivityLocalService) {
		this.learningActivityLocalService = learningActivityLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setCourseLocalService(CourseLocalService courseLocalService) {
		this.courseLocalService = courseLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setStudentLocalService(StudentLocalService studentLocalService) {
		this.studentLocalService = studentLocalService;
	}
	
	private LearningActivityLocalService learningActivityLocalService;
	private CourseLocalService courseLocalService;
	private StudentLocalService studentLocalService;
}
