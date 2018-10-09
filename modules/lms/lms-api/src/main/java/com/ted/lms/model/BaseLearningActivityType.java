package com.ted.lms.model;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.upload.UploadRequest;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.xml.Element;
import com.ted.lms.registry.LearningActivityTypeFactoryRegistryUtil;
import com.ted.lms.service.LearningActivityResultLocalService;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletResponse;

/**
 * Base para los tipos de actividades
 * @author Virginia Martín Agudo
 *
 */
public abstract class BaseLearningActivityType implements LearningActivityType {
	
	protected LearningActivity activity;
	protected LearningActivityResultLocalService learningActivityResultLocalService;
	
	public BaseLearningActivityType(LearningActivity activity, LearningActivityResultLocalService learningActivityResultLocalService) {
		this.activity = activity;
		this.learningActivityResultLocalService = learningActivityResultLocalService;
	}

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
		return "";
	}

	@Override
	public AssetRenderer<LearningActivity> getAssetRenderer() {
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
	public boolean setManualCalification(long userId, long score) {
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
	public void setExtraContent(ActionRequest actionRequest) throws PortalException {
	}

	@Override
	public boolean specificValidations(UploadRequest uploadRequest, PortletResponse portletResponse) {
		return true;
	}

	@Override
	public void afterInsertOrUpdate(UploadRequest uploadRequest, PortletResponse portletResponse) {
		
	}

	@Override
	public boolean onDelete(ActionRequest actionRequest, ActionResponse actionResponse) {
		return true;
	}

	@Override
	public boolean isAutoCorrect() {
		return false;
	}

	@Override
	public String doImportStagedModel(PortletDataContext portletDataContext, Element activityElement) {
		return null;
	}

	@Override
	public String doExportStagedModel(PortletDataContext portletDataContext, Element activityElement) {
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
	
	@Override
	public LearningActivityTypeFactory getLearningActivityTypeFactory() {
		if (learningActivityTypeFactory != null) {
			return learningActivityTypeFactory;
		}

		learningActivityTypeFactory =
			(LearningActivityTypeFactory)
			LearningActivityTypeFactoryRegistryUtil.
					getLearningActivityTypeFactoryByClassName(getClassName());

		return learningActivityTypeFactory;
	}
	
	private LearningActivityTypeFactory learningActivityTypeFactory;

	@Override
	public int getStatus() {
		return WorkflowConstants.STATUS_APPROVED;
	}
	
	@Override
	public boolean hasEditPermission(PermissionChecker permissionChecker)
		throws PortalException {

		return false;
	}

	@Override
	public boolean hasViewPermission(PermissionChecker permissionChecker) throws PortalException {

		return true;
	}
	
	@Override
	public boolean isDone(long userId) {
		LearningActivityResult lar=learningActivityResultLocalService.getLearningActivityResult(activity.getActId(), userId);
		if(lar==null){
			return false;
		} else {
			return lar.getEndDate()!=null;
		}
	}
	
	@Override 
	public long getTypeId() {
		return getLearningActivityTypeFactory().getType();
	}
	
	@Override
	public String getPortletId() {
		return getLearningActivityTypeFactory().getPortletId();
	}
	
	@Override
	public boolean deleteLearningActivityTry(LearningActivityTry learningActiityTry) {
		return false;
	}
}
