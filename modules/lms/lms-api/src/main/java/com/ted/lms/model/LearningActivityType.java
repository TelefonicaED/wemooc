package com.ted.lms.model;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.xml.Element;

import java.util.Map;

import javax.portlet.ActionRequest;
import aQute.bnd.annotation.ProviderType;

/**
 * Interfaz para las actividades del LMS
 * @author Virginia Martín Agudo
 *
 */
@ProviderType
public interface LearningActivityType {

	public long getTypeId();
	
	public AssetRenderer<LearningActivity> getAssetRenderer();
	public boolean setManualCalification(long userId, long score);
	
	public boolean isDone(long userId);
	
	public void setExtraContent(ActionRequest actionRequest) throws PortalException;
	
	public void afterInsertOrUpdate(ActionRequest actionRequest) throws PortalException;

	public void onDelete(ActionRequest actionRequest) throws PortalException;
	
	public String doImportStagedModel(PortletDataContext portletDataContext, Element activityElement);
	public String doExportStagedModel(PortletDataContext portletDataContext, Element activityElement);
	
	public double calculateResult(LearningActivityTry learningActivityTry);
	public boolean isPassed(LearningActivityTry learningActivityTry);
	public String getClassName();
	public boolean deleteLearningActivityTry(LearningActivityTry learningActivityTry);
	
	public LearningActivityTypeFactory getLearningActivityTypeFactory();

	public boolean hasEditPermission(PermissionChecker permissionChecker) throws PortalException;

	public boolean hasViewPermission(PermissionChecker permissionChecker) throws PortalException;

	public LearningActivity getLearningActivity();
	
	public void copyActivity(LearningActivity oldActivity, ServiceContext serviceContext) throws Exception;
	public void updateActivityCopied(Map<Long, Long> activitiesRelation);

}