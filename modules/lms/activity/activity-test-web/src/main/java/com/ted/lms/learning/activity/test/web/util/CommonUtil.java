package com.ted.lms.learning.activity.test.web.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import javax.portlet.ActionRequest;
import javax.portlet.PortletSession;

public class CommonUtil {

	public static boolean isFormRepeat(ActionRequest actionRequest){
		
		PortletSession session = actionRequest.getPortletSession(true);
		long formDate = GetterUtil.getLong(actionRequest.getParameter("formDate"));
		long lastformDate = GetterUtil.getLong(session.getAttribute("formDate"));
		if( Validator.equals(formDate, lastformDate) ){
			return true;
		}else{
			session.setAttribute("formDate", String.valueOf(formDate));
			return false;
		}
	}
}
