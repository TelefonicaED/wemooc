package com.ted.lms.web.internal.portlet.action;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.service.LearningActivityService;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + LMSPortletKeys.MODULES_ACTIVITIES,
		"mvc.command.name=/activities/delete_tries"
	},
	service = MVCActionCommand.class
)
public class DeleteTriesMVCActionCommand extends BaseMVCActionCommand {
	
	private static final Log log = LogFactoryUtil.getLog(DeleteTriesMVCActionCommand.class);
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		
		long actId = ParamUtil.getLong(actionRequest, "actId", 0);
		long studentId = ParamUtil.getLong(actionRequest, "studentId", 0);
		boolean deleteOnlyFailed = ParamUtil.getBoolean(actionRequest, "deleteOnlyFailed", false);
		
		LearningActivity activity = learningActivityService.getLearningActivity(actId);
	
		log.debug("actId: " + actId);
		log.debug("studentId: " + studentId);
		log.debug("deleteOnlyFailed: " + deleteOnlyFailed);
		
		ServiceContext serviceContext = ServiceContextFactory.getInstance(LearningActivity.class.getName(), actionRequest);
		
		learningActivityService.executeDeleteTries(activity.getGroupId(), actId, studentId, deleteOnlyFailed, serviceContext);
		
		actionResponse.setRenderParameter("mvcRenderCommandName", "/activities/view_results");
		actionResponse.setRenderParameter("actId", String.valueOf(actId));
	}
	
	@Reference
	private LearningActivityService learningActivityService;

}
