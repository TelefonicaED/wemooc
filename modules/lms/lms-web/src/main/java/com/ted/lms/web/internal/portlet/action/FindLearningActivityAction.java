package com.ted.lms.web.internal.portlet.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.struts.BaseStrutsAction;
import com.liferay.portal.kernel.struts.StrutsAction;
import com.liferay.portal.struts.FindActionHelper;

@Component(
	immediate = true, property = "path=/activities/find_activity",
	service = StrutsAction.class
)
public class FindLearningActivityAction extends BaseStrutsAction {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)throws Exception {
		findActionHelper.execute(request, response);
		return null;
	}

	@Reference(
		target = "(model.class.name=com.ted.lms.model.LearningActivity)",
		unbind = "-"
	)
	protected void setFindActionHelper(FindActionHelper findActionHelper) {
		this.findActionHelper = findActionHelper;
	}

	private FindActionHelper findActionHelper;
}
