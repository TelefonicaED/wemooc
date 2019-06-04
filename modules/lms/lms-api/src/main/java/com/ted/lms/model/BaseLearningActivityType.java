package com.ted.lms.model;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.xml.Element;
import com.ted.lms.registry.LearningActivityTypeFactoryRegistryUtil;
import com.ted.lms.service.LearningActivityResultLocalService;

import javax.portlet.ActionRequest;

/**
 * Base para los tipos de actividades
 * @author Virginia Martín Agudo
 *
 */
public abstract class BaseLearningActivityType implements LearningActivityType {
	
	protected LearningActivity activity;
	protected final LearningActivityResultLocalService learningActivityResultLocalService;
	
	public BaseLearningActivityType(LearningActivity activity, LearningActivityResultLocalService learningActivityResultLocalService) {
		this.activity = activity;
		this.learningActivityResultLocalService = learningActivityResultLocalService;
	}

	@Override
	public AssetRenderer<LearningActivity> getAssetRenderer() {
		return null;
	}

	@Override
	public boolean setManualCalification(long userId, long score) {
		return true;
	}

	@Override
	public void onDelete(ActionRequest actionRequest) throws PortalException {
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
	public boolean deleteLearningActivityTry(LearningActivityTry learningActiityTry) {
		return false;
	}

	@Override
	public void afterInsertOrUpdate(ActionRequest actionRequest) throws PortalException {
		
	}
	
	@Override
	public void setExtraContent(ActionRequest actionRequest) throws PortalException {
	}
	
	@Override
	public boolean isPassed(LearningActivityTry learningActivityTry) {
		return learningActivityTry.getResult()>=activity.getPassPuntuation();
	}
	
	@Override
	public double calculateResult(LearningActivityTry learningActivityTry) {
		return learningActivityTry.getResult();
	}
	
	@Override
	public LearningActivity getLearningActivity() {
		return activity;
	}
}
