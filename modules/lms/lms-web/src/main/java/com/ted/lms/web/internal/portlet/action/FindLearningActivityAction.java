package com.ted.lms.web.internal.portlet.action;

import javax.portlet.PortletURL;
import javax.servlet.http.HttpServletRequest;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.portlet.PortletLayoutFinder;
import com.liferay.portal.kernel.struts.StrutsAction;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.struts.FindStrutsAction;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.service.LearningActivityLocalService;

@Component(
	immediate = true, property = "path=/activities/find_activity",
	service = StrutsAction.class
)
public class FindLearningActivityAction extends FindStrutsAction {
	
	@Override
	public long getGroupId(long primaryKey) throws Exception {
		LearningActivity activity = learningActivityLocalService.getLearningActivity(primaryKey);

		return activity.getGroupId();
	}
	
	@Override
	public String getPrimaryKeyParameterName() {
		return "actId";
	}
	
	@Override
	protected PortletLayoutFinder getPortletLayoutFinder() {
		return portletLayoutFinder;
	}
	
	@Reference
	private LearningActivityLocalService learningActivityLocalService;
	
	@Reference(target = "(model.class.name=com.ted.lms.model.LearningActivity)")
	private PortletLayoutFinder portletLayoutFinder;

	@Override
	protected void addRequiredParameters(HttpServletRequest request, String portletId, PortletURL portletURL) {

		String mvcRenderCommandName = null;
		
		String action = ParamUtil.getString(request, Constants.CMD); 
		
		//Aquí deberíamos saber si venimos para editor o para ver

		if (action.equals(Constants.EDIT)) {
			mvcRenderCommandName = "/activities/edit_activity";
		} else if (action.equals(Constants.VIEW)) {
			mvcRenderCommandName = "/activities/view_activity";
		}

		portletURL.setParameter("mvcRenderCommandName", mvcRenderCommandName);
	}
	
	@Override
	public void setPrimaryKeyParameter(PortletURL portletURL, long primaryKey)
		throws Exception {
		portletURL.setParameter(
				"actId", String.valueOf(primaryKey));
	}
}
