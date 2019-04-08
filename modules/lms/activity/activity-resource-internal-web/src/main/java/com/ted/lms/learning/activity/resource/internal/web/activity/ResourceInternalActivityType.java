package com.ted.lms.learning.activity.resource.internal.web.activity;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.ted.lms.learning.activity.resource.internal.web.constants.ResourceInternalConstants;
import com.ted.lms.model.BaseLearningActivityType;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityTry;
import com.ted.lms.service.LearningActivityResultLocalService;
import com.ted.lms.util.LearningActivityUtil;

import javax.portlet.ActionRequest;
import org.osgi.service.component.annotations.Reference;

public class ResourceInternalActivityType extends BaseLearningActivityType{
	
	private long assetEntryId;
	
	public ResourceInternalActivityType(LearningActivity activity) {
		super(activity);
		
		JSONObject extraContent = activity.getExtraContentJSON();
		
		if(Validator.isNotNull(extraContent)) {
			JSONObject resourceInternal = extraContent.getJSONObject(ResourceInternalConstants.JSON_RESOURCE_INTERNAL);
			if(Validator.isNotNull(resourceInternal)) {
				assetEntryId = resourceInternal.getLong(ResourceInternalConstants.JSON_RESOURCE_INTERNAL_ASSET_ENTRY, 0);
			}else {
				assetEntryId = 0;
			}
		}else {
			assetEntryId = 0;
		}
	}

	@Override
	public double calculateResult(LearningActivityTry learningActivityTry) {
		return 100;
	}

	@Override
	public boolean isPassed(LearningActivityTry learningActivityTry) {
		return true;
	}

	@Override
	public String getClassName() {
		return ResourceInternalActivityType.class.getName();
	}
	
	@Override
	public void setExtraContent(ActionRequest actionRequest) throws PortalException {
		
		long assetEntryId = ParamUtil.getLong(actionRequest,"assetEntryId",0);
		
		JSONObject extraContent = activity.getExtraContentJSON();
		
		JSONObject resourceInternal = extraContent.getJSONObject(ResourceInternalConstants.JSON_RESOURCE_INTERNAL);
		
		if(resourceInternal == null) {
			resourceInternal = JSONFactoryUtil.createJSONObject();
			extraContent.put(ResourceInternalConstants.JSON_RESOURCE_INTERNAL, resourceInternal);
		}

		resourceInternal.put(ResourceInternalConstants.JSON_RESOURCE_INTERNAL_ASSET_ENTRY, assetEntryId);
		this.assetEntryId = assetEntryId;
		
		activity.setExtraContent(extraContent.toJSONString());
	}
	
	public long getAssetEntryId() {
		return assetEntryId;
	}

}
