package com.ted.lms.learning.activity.online;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.ted.lms.learning.activity.online.web.constants.OnlineConstants;
import com.ted.lms.model.BaseLearningActivityType;
import com.ted.lms.model.LearningActivity;
import javax.portlet.ActionRequest;

public class OnlineActivityType extends BaseLearningActivityType {
	
	private static final Log log = LogFactoryUtil.getLog(OnlineActivityType.class);
	private boolean includeFile;
	private boolean richText;

	public OnlineActivityType(LearningActivity activity) {
		super(activity);
		
		JSONObject extraContent = activity.getExtraContentJSON();
		
		if(extraContent != null) {
			JSONObject online = extraContent.getJSONObject(OnlineConstants.JSON_ONLINE);
			if(online != null) {
				includeFile = online.getBoolean(OnlineConstants.JSON_INCLUDE_FILE, OnlineConstants.DEFAULT_INCLUDE_FILE);
				richText = online.getBoolean(OnlineConstants.JSON_RICH_TEXT, OnlineConstants.DEFAULT_RICH_TEXT);
			}else {
				initializateActivity();
			}
		}else {
			initializateActivity();
		}
	}

	@Override
	public String getClassName() {
		return OnlineActivityType.class.getName();
	}
	
	@Override
	public void setExtraContent(ActionRequest actionRequest) throws PortalException {

		includeFile = ParamUtil.getBoolean(actionRequest, "includeFile", OnlineConstants.DEFAULT_INCLUDE_FILE);
		richText = ParamUtil.getBoolean(actionRequest, "richText", OnlineConstants.DEFAULT_RICH_TEXT);
		
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
	
	public boolean getIncludeFile() {
		return includeFile;
	}
	
	public boolean getRichText() {
		return richText;
	}
	
	private void initializateActivity() {
		includeFile = OnlineConstants.DEFAULT_INCLUDE_FILE;
		richText = OnlineConstants.DEFAULT_RICH_TEXT;
	}
}
