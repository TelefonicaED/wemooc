package com.ted.lms.web.internal.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.Course;
import com.ted.postcondition.model.Postcondition;
import com.ted.postcondition.model.PostconditionFactory;
import com.ted.postcondition.model.PostconditionRelation;
import com.ted.postcondition.registry.PostconditionFactoryRegistryUtil;
import com.ted.postcondition.service.PostconditionRelationLocalServiceUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + LMSPortletKeys.COURSE,
		"mvc.command.name=/courses/update_postcondition"
	},
	service = MVCActionCommand.class
)
public class UpdatePostconditionMVCActionCommand extends BaseMVCActionCommand {
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		
		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);
		String redirect = ParamUtil.getString(actionRequest, "redirect");
		long courseId = ParamUtil.getLong(actionRequest, "courseId");
		
		
		if (cmd.equals(Constants.ADD) ||  cmd.equals(Constants.UPDATE)) {

			String classNamePostcondition = ParamUtil.getString(actionRequest, "classNamePostcondition");
			
			PostconditionFactory postconditionFactory = PostconditionFactoryRegistryUtil.getPostconditionFactoryByClassName(classNamePostcondition);
			PostconditionRelation postconditionRelation = PostconditionRelationLocalServiceUtil.addPostconditionRelation(PortalUtil.getClassNameId(classNamePostcondition), 
					PortalUtil.getClassNameId(Course.class), courseId, "");
	
			Postcondition postcondition = postconditionFactory.getPostcondition(postconditionRelation);
			postcondition.setExtraContent(actionRequest);
			
			PostconditionRelationLocalServiceUtil.updatePostconditionRelation(postconditionRelation);
			
			SessionMessages.add(actionRequest, "postconditionAdded");
			
			actionResponse.setRenderParameter("postconditionRelationId", String.valueOf(postconditionRelation.getPostconditionRelationId()));
			actionResponse.setRenderParameter("mvcRenderCommandName", "/courses/assign_postcondition");
		}else if(cmd.equals(Constants.DELETE)) {
			
			long[] removePostconditionRelationIds = ParamUtil.getLongValues(actionRequest, "rowIds");
			
			for(long postconditionRelationId: removePostconditionRelationIds) {
				PostconditionRelationLocalServiceUtil.deletePostconditionRelation(postconditionRelationId);
			}
			
			actionRequest.setAttribute(WebKeys.REDIRECT, redirect);
		}
	}

}
