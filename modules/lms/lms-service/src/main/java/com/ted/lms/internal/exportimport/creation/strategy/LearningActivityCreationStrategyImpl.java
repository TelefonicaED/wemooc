package com.ted.lms.internal.exportimport.creation.strategy;

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.ted.lms.model.LearningActivity;

import org.osgi.service.component.annotations.Component;

/**
 * 
 * @author Virginia Martín Agudo
 *
 */
@Component(immediate = true, service = LearningActivityCreationStrategy.class)
public class LearningActivityCreationStrategyImpl implements LearningActivityCreationStrategy{
	@Override
	public boolean addGroupPermissions(
			PortletDataContext context, Object activityObj)
		throws Exception {

		return true;
	}

	@Override
	public boolean addGuestPermissions(
			PortletDataContext context, Object activityObj)
		throws Exception {

		return false;
	}

	@Override
	public long getAuthorUserId(PortletDataContext context, Object activityObj)
		throws Exception {

		return LearningActivityCreationStrategy.USE_DEFAULT_USER_ID_STRATEGY;
	}

	@Override
	public String getTransformedDescription(
			PortletDataContext context, LearningActivity newLearningActivity)
		throws Exception {

		return LearningActivityCreationStrategy.LEARNING_ACTIVITY_DESCRIPTION_UNCHANGED;
	}

}
