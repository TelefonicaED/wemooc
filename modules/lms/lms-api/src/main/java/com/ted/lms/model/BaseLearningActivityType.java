package com.ted.lms.model;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.upload.UploadRequest;
import com.liferay.portal.kernel.xml.Element;
import com.ted.lms.learningactivity.LearningActivityTypeFactoryRegistryUtil;

import java.util.HashMap;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletResponse;

public abstract class BaseLearningActivityType<T> implements LearningActivityType<T> {

	@Override
	public boolean isScoreConfigurable() {
		return false;
	}

	@Override
	public long getDefaultScore() {
		return 0;
	}

	@Override
	public boolean isTriesConfigurable() {
		return false;
	}

	@Override
	public long getDefaultTries() {
		return 0;
	}

	@Override
	public boolean isFeedbackCorrectConfigurable() {
		return false;
	}

	@Override
	public String getDefaultFeedbackCorrect() {
		return "";
	}

	@Override
	public boolean isFeedbackNoCorrectConfigurable() {
		return false;
	}

	@Override
	public String getDefaultFeedbackNoCorrect() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public AssetRenderer<LearningActivity> getAssetRenderer(LearningActivity learningActivity) {
		return null;
	}

	@Override
	public String getURLIcon() {
		return null;
	}

	@Override
	public boolean isManualCalificationAllowed() {
		return false;
	}

	@Override
	public boolean setManualCalification(long actId, long userId, long score) {
		return true;
	}

	@Override
	public boolean hasDeleteTries() {
		return false;
	}

	@Override
	public String getSpecificContentPage() {
		return null;
	}

	@Override
	public String setExtraContent(UploadRequest uploadRequest, PortletResponse portletResponse,
			LearningActivity learningActivity) {
		return null;
	}

	@Override
	public boolean specificValidations(UploadRequest uploadRequest, PortletResponse portletResponse) {
		return true;
	}

	@Override
	public void afterInsertOrUpdate(UploadRequest uploadRequest, PortletResponse portletResponse,
			LearningActivity learningActivity) {
		
	}

	@Override
	public void deleteResources(ActionRequest actionRequest, ActionResponse actionResponse,
			LearningActivity learningActivity) {
		
	}

	@Override
	public boolean isAutoCorrect() {
		return false;
	}

	@Override
	public String importLearningActivity(LearningActivity learningActivity, Element actElement, Element rootElement,
			PortletDataContext portletDataContext, ServiceContext serviceContext) {
		return null;
	}

	@Override
	public String onAfterImportLearningActivity(LearningActivity learningActivity,
			HashMap<Long, Long> relationActivities) {
		return null;
	}

	@Override
	public String exportLearningActivity(LearningActivity learningActivity, Element actElement,
			PortletDataContext portletDataContext) {
		return null;
	}

	@Override
	public boolean canBeSeenResults() {
		return false;
	}

	@Override
	public String getSpecificResultsPage() {
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public LearningActivityTypeFactory<T> getLearningActivityTypeFactory() {
		if (learningActivityTypeFactory != null) {
			return learningActivityTypeFactory;
		}

		learningActivityTypeFactory =
			(LearningActivityTypeFactory<T>)
			LearningActivityTypeFactoryRegistryUtil.
					getLearningActivityTypeFactoryByClassName(getClassName());

		return learningActivityTypeFactory;
	}
	
	private LearningActivityTypeFactory<T> learningActivityTypeFactory;

}
