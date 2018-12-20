package com.ted.lms.web.internal.portlet.action;

import com.liferay.asset.kernel.exception.AssetCategoryException;
import com.liferay.asset.kernel.exception.AssetTagException;
import com.liferay.document.library.kernel.exception.FileSizeException;
import com.liferay.portal.kernel.editor.EditorConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.TrashedModel;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.sanitizer.SanitizerException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.TransactionConfig;
import com.liferay.portal.kernel.transaction.TransactionInvokerUtil;
import com.liferay.portal.kernel.upload.LiferayFileItemException;
import com.liferay.portal.kernel.upload.UploadException;
import com.liferay.portal.kernel.upload.UploadRequestSizeException;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.trash.service.TrashEntryService;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.exception.ModuleEndDateException;
import com.ted.lms.exception.ModuleStartDateException;
import com.ted.lms.exception.NoSuchModuleException;
import com.ted.lms.exception.SmallImageNameException;
import com.ted.lms.exception.SmallImageScaleException;
import com.ted.lms.model.Module;
import com.ted.lms.model.ModuleEval;
import com.ted.lms.model.ModuleEvalFactory;
import com.ted.lms.registry.ModuleEvalFactoryRegistryUtil;
import com.ted.lms.service.ModuleLocalService;
import com.ted.lms.service.ModuleService;
import com.ted.lms.util.LMSPrefsPropsValues;
import com.ted.lms.web.internal.util.ModuleImageSelectorHelper;
import com.ted.prerequisite.model.Prerequisite;
import com.ted.prerequisite.model.PrerequisiteFactory;
import com.ted.prerequisite.registry.PrerequisiteFactoryRegistryUtil;
import com.ted.prerequisite.service.PrerequisiteRelationLocalService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletRequest;
import javax.portlet.WindowState;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + LMSPortletKeys.MODULES_ADMIN,
		"javax.portlet.name=" + LMSPortletKeys.MODULES_ACTIVITIES,
		"javax.portlet.name=" + LMSPortletKeys.COURSE_CONTENT_VIEWER,
		"mvc.command.name=/modules/edit_module"
	},
	service = MVCActionCommand.class
)
public class EditModuleMVCActionCommand extends BaseMVCActionCommand {
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		log.debug("cmd: " + cmd);
		
		try {
			Module module = null;

			UploadException uploadException = (UploadException)actionRequest.getAttribute(WebKeys.UPLOAD_EXCEPTION);

			if (uploadException != null) {
				Throwable cause = uploadException.getCause();

				if (uploadException.isExceededFileSizeLimit()) {
					throw new FileSizeException(cause);
				}

				if (uploadException.isExceededLiferayFileItemSizeLimit()) {
					throw new LiferayFileItemException(cause);
				}

				if (uploadException.isExceededUploadRequestSizeLimit()) {
					throw new UploadRequestSizeException(cause);
				}

				throw new PortalException(cause);
			} else if (cmd.equals(Constants.ADD) ||  cmd.equals(Constants.UPDATE)) {
				ParamUtil.print(actionRequest);
				Callable<Module> updateModuleCallable = new UpdateModuleCallable(actionRequest);

				module = TransactionInvokerUtil.invoke(_transactionConfig, updateModuleCallable);
			} else if(cmd.equals(Constants.MOVE)) {
				moveModule(actionRequest);
			} else if (cmd.equals(Constants.DELETE)) {
				deleteModules(actionRequest, false);
			} else if (cmd.equals(Constants.MOVE_TO_TRASH)) {
				deleteModules(actionRequest, true);
			} else if (cmd.equals(Constants.RESTORE)) {
				restoreTrashModules(actionRequest);
			}

			String redirect = ParamUtil.getString(actionRequest, "redirect");

			String portletId = _http.getParameter(redirect, "p_p_id", false);

			int workflowAction = ParamUtil.getInteger(actionRequest, "workflowAction", WorkflowConstants.ACTION_SAVE_DRAFT);

			boolean ajax = ParamUtil.getBoolean(actionRequest, "ajax");

			if (ajax) {
				JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

				jsonObject.put("moduleId", module.getModuleId());
				jsonObject.put("redirect", redirect);

				JSONPortletResponseUtil.writeJSON(actionRequest, actionResponse, jsonObject);

				return;
			}

			if ((module != null) && (workflowAction == WorkflowConstants.ACTION_SAVE_DRAFT)) {

				redirect = getSaveAndContinueRedirect(actionRequest, module, redirect);

				sendRedirect(actionRequest, actionResponse, redirect);
			} else {
				WindowState windowState = actionRequest.getWindowState();

				if (Validator.isNotNull(redirect) && cmd.equals(Constants.UPDATE)) {

					String namespace = actionResponse.getNamespace();
					redirect = _http.setParameter(redirect, namespace + "redirectToLastFriendlyURL",false);
				}

				if (!windowState.equals(LiferayWindowState.POP_UP)) {
					sendRedirect(actionRequest, actionResponse, redirect);
				} else {
					redirect = _portal.escapeRedirect(redirect);

					if (Validator.isNotNull(redirect)) {
						if (cmd.equals(Constants.ADD) && (module != null)) {
							String namespace = _portal.getPortletNamespace(portletId);

							redirect = _http.addParameter(redirect, namespace + "className", Module.class.getName());
							redirect = _http.addParameter(redirect, namespace + "classPK",module.getModuleId());
						}

						actionRequest.setAttribute(WebKeys.REDIRECT, redirect);
					}
				}
			}
		} catch (AssetCategoryException | AssetTagException e) {
			SessionErrors.add(actionRequest, e.getClass(), e);

			actionResponse.setRenderParameter("mvcRenderCommandName", "/modules/edit_module");

			hideDefaultSuccessMessage(actionRequest);
		} catch (ModuleStartDateException | ModuleEndDateException | SmallImageNameException | SmallImageScaleException | 
			   FileSizeException | LiferayFileItemException | SanitizerException | UploadRequestSizeException e) {
			e.printStackTrace();
			SessionErrors.add(actionRequest, e.getClass());

			actionResponse.setRenderParameter("mvcRenderCommandName", "/modules/edit_module");

			hideDefaultSuccessMessage(actionRequest);
		} catch (NoSuchModuleException | PrincipalException e) {
			SessionErrors.add(actionRequest, e.getClass());

			actionResponse.setRenderParameter("mvcPath", "/modules/error.jsp");

			hideDefaultSuccessMessage(actionRequest);
		} catch (Throwable t) {
			log.error(t, t);

			actionResponse.setRenderParameter("mvcPath", "/modules/error.jsp");

			hideDefaultSuccessMessage(actionRequest);
		}
	}
	
	protected void moveModule(ActionRequest actionRequest) throws Exception {
		long moduleId = ParamUtil.getLong(actionRequest, "moduleId");
		
		int moved = ParamUtil.getInteger(actionRequest, Constants.ACTION);
		
		ServiceContext serviceContext = ServiceContextFactory.getInstance(Module.class.getName(), actionRequest);
		if(moved == -1) {
			moduleService.moveUpModule(moduleId, serviceContext);
		}else if(moved == 1) {
			moduleService.moveDownModule(moduleId, serviceContext);
		}
	}
	
	protected void deleteModules(ActionRequest actionRequest, boolean moveToTrash) throws Exception {

		long[] deleteModuleIds = null;

		long moduleId = ParamUtil.getLong(actionRequest, "moduleId");

		if (moduleId > 0) {
			deleteModuleIds = new long[] {moduleId};
		} else {
			deleteModuleIds = StringUtil.split(ParamUtil.getString(actionRequest, "deleteModuleIds"), 0L);
		}

		List<TrashedModel> trashedModels = new ArrayList<>();

		for (long deleteModuleId : deleteModuleIds) {
			if (moveToTrash) {
				Module module = moduleService.moveModuleToTrash(deleteModuleId);

				trashedModels.add(module);
			} else {
				moduleService.deleteModule(deleteModuleId);
			}
		}

		if (moveToTrash && !trashedModels.isEmpty()) {
			Map<String, Object> data = new HashMap<>();

			data.put("trashedModels", trashedModels);

			addDeleteSuccessData(actionRequest, data);
		}
	}
	
	protected void restoreTrashModules(ActionRequest actionRequest) throws Exception {

		long[] restoreTrashModuleIds = StringUtil.split(ParamUtil.getString(actionRequest, "restoreTrashModuleIds"), 0L);

		for (long restoreTrashModuleId : restoreTrashModuleIds) {
			trashEntryService.restoreEntry(restoreTrashModuleId);
		}
	}
	
	protected String getSaveAndContinueRedirect(ActionRequest actionRequest, Module module, String redirect) throws Exception {

		PortletConfig portletConfig = (PortletConfig)actionRequest.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);

		LiferayPortletURL portletURL = PortletURLFactoryUtil.create(actionRequest, portletConfig.getPortletName(),PortletRequest.RENDER_PHASE);

		portletURL.setParameter("mvcRenderCommandName", "/modules/edit_module");

		portletURL.setParameter(Constants.CMD, Constants.UPDATE, false);
		portletURL.setParameter("redirect", redirect, false);
		portletURL.setParameter("groupId", String.valueOf(module.getGroupId()), false);
		portletURL.setParameter("moduleId", String.valueOf(module.getModuleId()), false);
		portletURL.setWindowState(actionRequest.getWindowState());

		return portletURL.toString();
	}
	
	protected Module updateModule(ActionRequest actionRequest) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		long moduleId = ParamUtil.getLong(actionRequest, "moduleId");

		Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(actionRequest, "titleMapAsXML");
		Map<Locale, String> descriptionMap = LocalizationUtil.getLocalizationMap(actionRequest, "descriptionMapAsXML");
		log.debug("titleMap: " + titleMap);
		log.debug("descriptionMap: " + descriptionMap);
		
		boolean useStartExecutionDateCourse = ParamUtil.getBoolean(actionRequest, "useStartExecutionDateCourse");
		log.debug("useStartExecutionDateCourse: " + useStartExecutionDateCourse);

		int startDateMonth = ParamUtil.getInteger(actionRequest, "startDateMonth");
		int startDateDay = ParamUtil.getInteger(actionRequest, "startDateDay");
		int startDateYear = ParamUtil.getInteger(actionRequest, "startDateYear");
		int startDateHour = ParamUtil.getInteger(actionRequest, "startDateHour");
		int startDateMinute = ParamUtil.getInteger(actionRequest, "startDateMinute");
		int startDateAmPm = ParamUtil.getInteger(actionRequest, "startDateAmPm");
		log.debug("startDateMonth: " + startDateMonth);
		log.debug("startDateDay: " + startDateDay);
		log.debug("startDateYear: " + startDateYear);
		log.debug("startDateHour: " + startDateHour);
		log.debug("startDateMinute: " + startDateMinute);
		

		if (startDateAmPm == Calendar.PM) {
			startDateHour += 12;
		}
		
		boolean useEndExecutionDateCourse = ParamUtil.getBoolean(actionRequest, "useEndExecutionDateCourse");
		log.debug("useEndExecutionDateCourse: " + useEndExecutionDateCourse);
		
		int endDateMonth = ParamUtil.getInteger(actionRequest, "endDateMonth");
		int endDateDay = ParamUtil.getInteger(actionRequest, "endDateDay");
		int endDateYear = ParamUtil.getInteger(actionRequest, "endDateYear");
		int endDateHour = ParamUtil.getInteger(actionRequest, "endDateHour");
		int endDateMinute = ParamUtil.getInteger(actionRequest, "endDateMinute");
		int endDateAmPm = ParamUtil.getInteger(actionRequest, "endDateAmPm");

		if (endDateAmPm == Calendar.PM) {
			endDateHour += 12;
		}
		
		int allowedHours = ParamUtil.getInteger(actionRequest, "allowedHours");
		int allowedMinutes = ParamUtil.getInteger(actionRequest, "allowedMinutes");

		long oldSmallImageId = 0;
		
		System.out.println("moduleId: " + moduleId);
		
		if (moduleId != 0) {
			Module module = moduleLocalService.getModule(moduleId);

			oldSmallImageId = module.getSmallImageId();
		}
		
		long smallImageFileEntryId = ParamUtil.getLong(actionRequest, "smallImageFileEntryId");

		ModuleImageSelectorHelper moduleSmallImageSelectorHelper = new ModuleImageSelectorHelper(oldSmallImageId, smallImageFileEntryId);

		ImageSelector smallImageImageSelector = moduleSmallImageSelectorHelper.getImageSelector();
		
		long moduleEvalId = ParamUtil.getLong(actionRequest, "moduleEvalId");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(Module.class.getName(), actionRequest);

		Module module = null;

		if (moduleId <= 0) {

			// Añadir módulo
			module = moduleService.addModule(titleMap, descriptionMap, useStartExecutionDateCourse, startDateMonth, startDateDay, startDateYear,
					startDateHour, startDateMinute, useEndExecutionDateCourse, endDateMonth, endDateDay, endDateYear, endDateHour, endDateMinute,
					allowedHours, allowedMinutes, smallImageImageSelector, moduleEvalId, serviceContext);
		} else {
			// Actualizamos el módulo
			module = moduleService.updateModule(
				moduleId, titleMap, descriptionMap, useStartExecutionDateCourse, startDateMonth, startDateDay, startDateYear,
				startDateHour, startDateMinute, useEndExecutionDateCourse, endDateMonth, endDateDay, endDateYear, endDateHour, endDateMinute,
				allowedHours, allowedMinutes, smallImageImageSelector, moduleEvalId,  serviceContext);
		} 
		
		//Guardamos los campos personalizados del método de evaluación
		ModuleEvalFactory moduleEvalFactory = ModuleEvalFactoryRegistryUtil.getModuleEvalFactoryByType(moduleEvalId);
		ModuleEval moduleEval = moduleEvalFactory.getModuleEval(module, serviceContext);
		moduleEval.setExtraContent(actionRequest);
		
		moduleService.updateModule(module);
		
		//Guardamos los prerequisitos
		String[] classNamePrerequisites = LMSPrefsPropsValues.getPrerequisitesOfModule(themeDisplay.getCompanyId());
		PrerequisiteFactory prerequisiteFactory = null;
		Prerequisite prerequisite = null;
		long moduleClassNameId = PortalUtil.getClassNameId(Module.class);
		
		for(String classNamePrerequisite: classNamePrerequisites){
			prerequisiteFactory = PrerequisiteFactoryRegistryUtil.getPrerequisiteFactoryByClassName(classNamePrerequisite);
			prerequisite = prerequisiteFactory.getPrerequisite(moduleClassNameId, module.getModuleId());
			prerequisite.setExtraContent(actionRequest);
		}
		
		if (moduleSmallImageSelectorHelper.isFileEntryTempFile()) {
			moduleLocalService.addOriginalImageFileEntry(themeDisplay.getUserId(), module.getGroupId(), module.getModuleId(), smallImageImageSelector);

			PortletFileRepositoryUtil.deletePortletFileEntry(smallImageFileEntryId);
		}

		return module;
	}
	
	private static final Log log = LogFactoryUtil.getLog(EditModuleMVCActionCommand.class);
	
	private static final TransactionConfig _transactionConfig =
			TransactionConfig.Factory.create(
				Propagation.REQUIRED, new Class<?>[] {Exception.class});
	
	@Reference
	private Portal _portal;
	
	@Reference
	private Http _http;
	
	@Reference(unbind = "-")
	protected void setModuleLocalService(ModuleLocalService moduleLocalService) {
		this.moduleLocalService = moduleLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}
	
	@Reference(unbind = "-")
	protected void setPrerequisiteRelationLocalService(PrerequisiteRelationLocalService prerequisiteRelationLocalService) {
		this.prerequisiteRelationLocalService = prerequisiteRelationLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setTrashEntryService(TrashEntryService trashEntryService) {
		this.trashEntryService = trashEntryService;
	}
	
	private ModuleLocalService moduleLocalService;
	private ModuleService moduleService;
	private PrerequisiteRelationLocalService prerequisiteRelationLocalService;
	private TrashEntryService trashEntryService;

	
	private class UpdateModuleCallable implements Callable<Module> {

		@Override
		public Module call() throws Exception {
			return updateModule(actionRequest);
		}

		private UpdateModuleCallable(ActionRequest actionRequest) {
			this.actionRequest = actionRequest;
		}

		private final ActionRequest actionRequest;

	}
}
