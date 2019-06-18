package com.ted.lms.web.internal.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.upload.UploadHandler;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.web.internal.upload.ImageCourseUploadFileEntryHandler;
import com.ted.lms.web.internal.upload.ImageCourseUploadResponseHandler;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * 
 * @author Virginia Martín Agudo
 *
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + LMSPortletKeys.COURSE,
		"javax.portlet.name=" + LMSPortletKeys.MODULES_ADMIN,
		"javax.portlet.name=" + LMSPortletKeys.COURSE_TYPE,
		"mvc.command.name=/course/upload_image"
	},
	service = MVCActionCommand.class
)
public class UploadImageMVCActionCommand extends BaseMVCActionCommand {
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		this.uploadHandler.upload(this.imageCourseUploadFileEntryHandler, this.imageCourseUploadResponseHandler,
			actionRequest, actionResponse);
	}

	@Reference
	private ImageCourseUploadFileEntryHandler imageCourseUploadFileEntryHandler;

	@Reference
	private ImageCourseUploadResponseHandler imageCourseUploadResponseHandler;

	@Reference
	private UploadHandler uploadHandler;
}
