package com.ted.lms.learning.activity.online;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.ted.lms.learning.activity.online.web.constants.OnlineConstants;
import com.ted.lms.model.BaseLearningActivityType;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.service.LearningActivityResultLocalService;

import javax.portlet.ActionRequest;

public class OnlineActivityType extends BaseLearningActivityType {

	public OnlineActivityType(LearningActivity activity) {
		super(activity);
	}

	@Override
	public String getClassName() {
		return OnlineActivityType.class.getName();
	}
	
	@Override
	public void setExtraContent(ActionRequest actionRequest) throws PortalException {

		boolean includeFile = ParamUtil.getBoolean(actionRequest, "includeFile", false);
		boolean richText = ParamUtil.getBoolean(actionRequest, "richText", false);
		
		JSONObject extraContent = activity.getExtraContentJSON();
		
		JSONObject online = extraContent.getJSONObject(OnlineConstants.JSON_ONLINE);
		
		if(Validator.isNull(online)) {
			online = JSONFactoryUtil.createJSONObject();
		}
		
		online.put(OnlineConstants.JSON_INCLUDE_FILE, includeFile);
		online.put(OnlineConstants.JSON_RICH_TEXT, richText);
		
		extraContent.put(OnlineConstants.JSON_ONLINE, online);

		activity.setExtraContent(extraContent.toJSONString());
	}
}
