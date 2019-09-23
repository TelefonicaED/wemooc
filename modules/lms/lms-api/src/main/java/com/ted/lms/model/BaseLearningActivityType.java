package com.ted.lms.model;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.xml.Element;
import com.ted.lms.constants.LearningActivityConstants;
import com.ted.lms.registry.LearningActivityTypeFactoryRegistryUtil;
import com.ted.lms.service.LearningActivityResultLocalService;

import java.util.Map;

import javax.portlet.ActionRequest;

/**
 * Base para los tipos de actividades
 * @author Virginia Martín Agudo
 *
 */
public abstract class BaseLearningActivityType implements LearningActivityType {
	
	private static final Log log = LogFactoryUtil.getLog(BaseLearningActivityType.class);
	
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
	public void onDelete() throws PortalException {
	}

	@Override
	public void doExportStagedModel(PortletDataContext portletDataContext, Element activityElement) throws PortalException {
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
	
	@Override
	public void copyActivity(LearningActivity oldActivity, ServiceContext serviceContext) throws Exception {
		activity.setExtraContent(oldActivity.getExtraContent());
	}
	
	@Override
	public void updateActivityCopied(Map<Long, Long> activitiesRelation) {
		JSONObject extraContent = activity.getExtraContentJSON();
		log.debug("BaseLearning: " + extraContent);
		updateActivityIds(extraContent, activitiesRelation);
		
		activity.setExtraContent(extraContent.toJSONString());
		log.debug("extraContent final: " + extraContent.toJSONString());
	}
	
	@Override
	public void doImportStagedModel(PortletDataContext portletDataContext, Element activityElement) throws PortalException{
		Map<Long, Long> activityIds = (Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(LearningActivity.class);
		
		JSONObject extraContent = activity.getExtraContentJSON();
		if(extraContent != null) {
			updateActivityIds(extraContent, activityIds);
			activity.setExtraContent(extraContent.toJSONString());
			log.debug("extraContent final: " + extraContent.toJSONString());
		}
		
	}
	
	
	protected void updateActivityIds(JSONObject jsonObject, Map<Long, Long> activitiesRelation) {
		if(jsonObject != null) {
			jsonObject.keys().forEachRemaining(
				key -> 
				{
					if(key.equals(LearningActivityConstants.JSON_ACT_ID)) {
						jsonObject.put(LearningActivityConstants.JSON_ACT_ID, activitiesRelation.get(jsonObject.getLong(LearningActivityConstants.JSON_ACT_ID)));
					}else if(key.equals(LearningActivityConstants.JSON_ACTIVITIES)) {
						JSONObject newActivities = JSONFactoryUtil.createJSONObject();
						jsonObject.getJSONObject(key).keys().forEachRemaining(
								keyActivityId ->
								{
									newActivities.put(String.valueOf(activitiesRelation.get(Long.parseLong(keyActivityId))), jsonObject.getJSONObject(key).get(keyActivityId));
									
								});
						jsonObject.put(LearningActivityConstants.JSON_ACTIVITIES, newActivities);
					}else if(jsonObject.getJSONObject(key) != null) {
						updateActivityIds(jsonObject.getJSONObject(key), activitiesRelation);
					}
				}
			);
		}
	}
}
