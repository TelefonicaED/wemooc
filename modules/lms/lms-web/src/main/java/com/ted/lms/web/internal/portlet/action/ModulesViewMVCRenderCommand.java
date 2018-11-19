package com.ted.lms.web.internal.portlet.action;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.trash.TrashHelper;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.Course;
import com.ted.lms.model.Module;
import com.ted.lms.service.CourseLocalServiceUtil;
import com.ted.lms.service.ModuleLocalService;
import com.ted.lms.service.ModuleLocalServiceUtil;
import com.ted.lms.web.internal.display.context.ModulesManagementToolbarDisplayContext;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
		immediate = true,
		property = {
			"javax.portlet.name=" + LMSPortletKeys.MODULE,
			"mvc.command.name=/", "mvc.command.name=/modules/view"
		},
		service = MVCRenderCommand.class
	)
public class ModulesViewMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		

		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter("mvcRenderCommandName", "/modules/view");
		
		LiferayPortletResponse liferayPortletResponse = PortalUtil.getLiferayPortletResponse(renderResponse);

		SearchContainer<Module> searchContainer = new SearchContainer<Module>(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA, 
				PortletURLUtil.clone(portletURL, liferayPortletResponse), null, "no-modules");

	/*	ModulesDisplayContext modulesDisplayContext = new ModulesDisplayContext(liferayPortletRequest);

		blogEntriesDisplayContext.populateResults(entriesSearchContainer);*/
		
		searchContainer.setResults(moduleLocalService.getModules(searchContainer.getStart(), searchContainer.getEnd()));
		searchContainer.setTotal(moduleLocalService.getModulesCount());
		
		renderRequest.setAttribute("searchContainer", searchContainer);
		renderRequest.setAttribute("portletURL", portletURL);
		renderRequest.setAttribute("trashHelper", trashHelper);
		
		return "/module_admin/view.jsp";
	}
	
	@Reference(unbind = "-")
	protected void setModuleLocalService(ModuleLocalService moduleLocalService) {
		this.moduleLocalService = moduleLocalService;
	}
	
	private ModuleLocalService moduleLocalService;
	
	@Reference
	private TrashHelper trashHelper;

}
