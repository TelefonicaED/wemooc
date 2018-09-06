package com.ted.lms.model;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.upload.UploadRequest;
import com.liferay.portal.kernel.xml.Element;

import java.util.HashMap;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletResponse;

import aQute.bnd.annotation.ProviderType;

/**
 * Interfaz para las actividades del LMS
 * @author Virginia Martín Agudo
 *
 */
@ProviderType
public interface LearningActivityType {

	public long getTypeId();
	public boolean isScoreConfigurable();
	public long getDefaultScore();
	public boolean isTriesConfigurable();
	public long getDefaultTries();
	public boolean isFeedbackCorrectConfigurable();
	public String getDefaultFeedbackCorrect();
	public boolean isFeedbackNoCorrectConfigurable();
	public String getDefaultFeedbackNoCorrect();
	public AssetRenderer<LearningActivity> getAssetRenderer(LearningActivity learningActivity);
	public String getURLIcon();
	public boolean isManualCalificationAllowed();
	public boolean setManualCalification(long actId, long userId, long score);
	public boolean hasDeleteTries();
	public boolean isDone(LearningActivity learningActivity, long userId);
	public String getSpecificContentPage();
	public String setExtraContent (UploadRequest uploadRequest,PortletResponse portletResponse,LearningActivity learningActivity);
	public boolean specificValidations(UploadRequest uploadRequest, PortletResponse portletResponse);
	public void afterInsertOrUpdate(UploadRequest uploadRequest, PortletResponse portletResponse, LearningActivity learningActivity);
	public String getPortletId();
	public boolean onDelete(ActionRequest actionRequest,ActionResponse actionResponse, LearningActivity learningActivity);
	public boolean isAutoCorrect();
	public String importLearningActivity(LearningActivity learningActivity, Element actElement, Element rootElement, PortletDataContext portletDataContext, ServiceContext serviceContext);
	public String onAfterImportLearningActivity(LearningActivity learningActivity, HashMap<Long,Long> relationActivities);
	public String exportLearningActivity(LearningActivity learningActivity, Element actElement, PortletDataContext portletDataContext);
	public boolean canBeSeenResults();
	public String getSpecificResultsPage();
	public long calculateResult(LearningActivity learningActivity, LearningActivityTry learningActivityTry);
	public boolean isPassed(LearningActivity learningActivity, LearningActivityTry learningActivityTry);
	public String getClassName();
	public boolean deleteLearningActivityTry(LearningActivityTry learningActiityTry);
	
	public LearningActivityTypeFactory getLearningActivityTypeFactory();

	public int getStatus();
	public boolean hasEditPermission(PermissionChecker permissionChecker)
		throws PortalException;

	public boolean hasViewPermission(PermissionChecker permissionChecker)
		throws PortalException;

}