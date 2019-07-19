package com.ted.lms.web.internal.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.trash.service.TrashEntryService;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.Course;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.service.CourseService;
import com.ted.prerequisite.model.Prerequisite;
import com.ted.prerequisite.model.PrerequisiteFactory;
import com.ted.prerequisite.model.PrerequisiteRelation;
import com.ted.prerequisite.registry.PrerequisiteFactoryRegistryUtil;
import com.ted.prerequisite.service.PrerequisiteRelationLocalServiceUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + LMSPortletKeys.COURSE,
		"mvc.command.name=/courses/update_prerequisite"
	},
	service = MVCActionCommand.class
)
public class UpdatePrerequisiteMVCActionCommand extends BaseMVCActionCommand {
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		
		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);
		String redirect = ParamUtil.getString(actionRequest, "redirect");
		long courseId = ParamUtil.getLong(actionRequest, "courseId");
		
		
		if (cmd.equals(Constants.ADD) ||  cmd.equals(Constants.UPDATE)) {

			String classNamePrerequisite = ParamUtil.getString(actionRequest, "classNamePrerequisite");
			
			PrerequisiteFactory prerequisiteFactory = PrerequisiteFactoryRegistryUtil.getPrerequisiteFactoryByClassName(classNamePrerequisite);
			PrerequisiteRelation prerequisiteRelation = PrerequisiteRelationLocalServiceUtil.addPrerequisiteRelation(PortalUtil.getClassNameId(classNamePrerequisite), 
					PortalUtil.getClassNameId(Course.class), courseId, "");
	
			Prerequisite prerequisite = prerequisiteFactory.getPrerequisite(prerequisiteRelation);
			prerequisite.setExtraContent(actionRequest);
			
			PrerequisiteRelationLocalServiceUtil.updatePrerequisiteRelation(prerequisiteRelation);
			
			SessionMessages.add(actionRequest, "prerequisiteAdded");
			
			actionResponse.setRenderParameter("prerequisiteRelationId", String.valueOf(prerequisiteRelation.getPrerequisiteRelationId()));
			actionResponse.setRenderParameter("mvcRenderCommandName", "/courses/assign_prerequisite");
		}else if(cmd.equals(Constants.DELETE)) {
			
			long[] removePrerequisiteRelationIds = ParamUtil.getLongValues(actionRequest, "rowIds");
			
			for(long prerequisiteRelationId: removePrerequisiteRelationIds) {
				PrerequisiteRelationLocalServiceUtil.deletePrerequisiteRelation(prerequisiteRelationId);
			}
			
			actionRequest.setAttribute(WebKeys.REDIRECT, redirect);
		}
	}
	
	
	@Reference
	private Portal _portal;
	
	@Reference
	private Http _http;
	
	@Reference(unbind = "-")
	protected void setCourseLocalService(CourseLocalService courseLocalService) {
		this.courseLocalService = courseLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}
	
	@Reference(unbind = "-")
	protected void setTrashEntryService(TrashEntryService trashEntryService) {
		this.trashEntryService = trashEntryService;
	}
	
	private CourseLocalService courseLocalService;
	private CourseService courseService;
	private TrashEntryService trashEntryService;

}
