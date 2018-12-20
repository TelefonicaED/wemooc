package com.ted.lms.web.internal.portlet.action;

import com.liferay.asset.kernel.exception.AssetCategoryException;
import com.liferay.asset.kernel.exception.AssetTagException;
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
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.TransactionConfig;
import com.liferay.portal.kernel.transaction.TransactionInvokerUtil;
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
import com.ted.lms.exception.LearningActivityEndDateException;
import com.ted.lms.exception.LearningActivityStartDateException;
import com.ted.lms.exception.NoSuchLearningActivityException;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityType;
import com.ted.lms.model.LearningActivityTypeFactory;
import com.ted.lms.model.Module;
import com.ted.lms.registry.LearningActivityTypeFactoryRegistryUtil;
import com.ted.lms.service.LearningActivityService;
import com.ted.lms.service.ModuleLocalService;
import com.ted.lms.util.LMSPrefsPropsValues;
import com.ted.prerequisite.model.Prerequisite;
import com.ted.prerequisite.model.PrerequisiteFactory;
import com.ted.prerequisite.registry.PrerequisiteFactoryRegistryUtil;

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
		"javax.portlet.name=" + LMSPortletKeys.COURSE_CONTENT_VIEWER,
		"mvc.command.name=/activities/edit_activity"
	},
	service = MVCActionCommand.class
)
public class EditActivityMVCActionCommand extends BaseMVCActionCommand {
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		log.debug("cmd: " + cmd);
		
		try {
			LearningActivity activity = null;

			if (cmd.equals(Constants.ADD) ||  cmd.equals(Constants.UPDATE)) {
				ParamUtil.print(actionRequest);
				Callable<LearningActivity> updateLearningActivityCallable = new UpdateLearningActivityCallable(actionRequest);

				activity = TransactionInvokerUtil.invoke(_transactionConfig, updateLearningActivityCallable);
			} else if(cmd.equals(Constants.MOVE)) {
				moveLearningActivity(actionRequest);
			} else if (cmd.equals(Constants.DELETE)) {
				deleteLearningActivities(actionRequest, false);
			} else if (cmd.equals(Constants.MOVE_TO_TRASH)) {
				deleteLearningActivities(actionRequest, true);
			} else if (cmd.equals(Constants.RESTORE)) {
				restoreTrashLearningActivities(actionRequest);
			}

			String redirect = ParamUtil.getString(actionRequest, "redirect");
			
			String portletId = _http.getParameter(redirect, "p_p_id", false);

			int workflowAction = ParamUtil.getInteger(actionRequest, "workflowAction", WorkflowConstants.ACTION_PUBLISH);
			
			boolean ajax = ParamUtil.getBoolean(actionRequest, "ajax");

			if (ajax) {
				JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

				jsonObject.put("actId", activity.getActId());
				jsonObject.put("redirect", redirect);

				JSONPortletResponseUtil.writeJSON(actionRequest, actionResponse, jsonObject);

				return;
			}
			
			if ((activity != null) && (workflowAction == WorkflowConstants.ACTION_SAVE_DRAFT)) {

				redirect = getSaveAndContinueRedirect(actionRequest, activity, redirect);

				sendRedirect(actionRequest, actionResponse, redirect);
			} if((activity != null) && workflowAction == WorkflowConstants.ACTION_PUBLISH) {
				redirect = getSaveAndViewActivityRedirect(actionRequest, activity, redirect);
				
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
						if (cmd.equals(Constants.ADD) && (activity != null)) {
							String namespace = _portal.getPortletNamespace(portletId);

							redirect = _http.addParameter(redirect, namespace + "className", LearningActivity.class.getName());
							redirect = _http.addParameter(redirect, namespace + "classPK",activity.getActId());
						}

						actionRequest.setAttribute(WebKeys.REDIRECT, redirect);
					}
				}
			}
		} catch (AssetCategoryException | AssetTagException e) {
			SessionErrors.add(actionRequest, e.getClass(), e);

			actionResponse.setRenderParameter("mvcRenderCommandName", "/activities/edit_activity");

			hideDefaultSuccessMessage(actionRequest);
		} catch (LearningActivityStartDateException | LearningActivityEndDateException e) {
			e.printStackTrace();
			SessionErrors.add(actionRequest, e.getClass());

			actionResponse.setRenderParameter("mvcRenderCommandName", "/activities/edit_activity");

			hideDefaultSuccessMessage(actionRequest);
		} catch (NoSuchLearningActivityException | PrincipalException e) {
			SessionErrors.add(actionRequest, e.getClass());

			actionResponse.setRenderParameter("mvcPath", "/activities/error.jsp");

			hideDefaultSuccessMessage(actionRequest);
		} catch (PortalException e) {
			SessionErrors.add(actionRequest, e.getClass());
			
			actionResponse.setRenderParameter("mvcRenderCommandName", "/activities/edit_activity");
			hideDefaultSuccessMessage(actionRequest);
		} catch (Throwable t) {
			System.out.println("exception throwable: " + t.getLocalizedMessage());
			log.error(t, t);

			actionResponse.setRenderParameter("mvcPath", "/activities/error.jsp");

			hideDefaultSuccessMessage(actionRequest);
		}
	}
	
	protected String getSaveAndContinueRedirect(ActionRequest actionRequest, LearningActivity activity, String redirect) throws Exception {

		PortletConfig portletConfig = (PortletConfig)actionRequest.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);

		LiferayPortletURL portletURL = PortletURLFactoryUtil.create(actionRequest, portletConfig.getPortletName(),PortletRequest.RENDER_PHASE);

		portletURL.setParameter("mvcRenderCommandName", "/activities/edit_activity");

		portletURL.setParameter(Constants.CMD, Constants.UPDATE, false);
		portletURL.setParameter("redirect", redirect, false);
		portletURL.setParameter("actId", String.valueOf(activity.getActId()), false);
		portletURL.setWindowState(actionRequest.getWindowState());

		return portletURL.toString();
	}
	
	protected String getSaveAndViewActivityRedirect(ActionRequest actionRequest, LearningActivity activity, String redirect) throws Exception {

		PortletConfig portletConfig = (PortletConfig)actionRequest.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);

		LiferayPortletURL portletURL = PortletURLFactoryUtil.create(actionRequest, portletConfig.getPortletName(),PortletRequest.RENDER_PHASE);

		portletURL.setParameter("mvcRenderCommandName", "/activities/view_activity");

		portletURL.setParameter(Constants.CMD, Constants.UPDATE, false);
		portletURL.setParameter("redirect", redirect, false);
		portletURL.setParameter("actId", String.valueOf(activity.getActId()), false);
		portletURL.setWindowState(actionRequest.getWindowState());

		return portletURL.toString();
	}
	
	protected void moveLearningActivity(ActionRequest actionRequest) throws Exception {
		long actId = ParamUtil.getLong(actionRequest, "actId");
		
		int moved = ParamUtil.getInteger(actionRequest, Constants.ACTION);
		
		ServiceContext serviceContext = ServiceContextFactory.getInstance(LearningActivity.class.getName(), actionRequest);
		if(moved == -1) {
			learningActivityService.moveUpLearningActivity(actId, serviceContext);
		}else if(moved == 1) {
			learningActivityService.moveDownLearningActivity(actId, serviceContext);
		}
	}
	
	protected void deleteLearningActivities(ActionRequest actionRequest, boolean moveToTrash) throws Exception {

		long[] deleteLearningActivityIds = null;

		long actId = ParamUtil.getLong(actionRequest, "actId");

		if (actId > 0) {
			deleteLearningActivityIds = new long[] {actId};
		} else {
			deleteLearningActivityIds = StringUtil.split(ParamUtil.getString(actionRequest, "deleteLearningActivityIds"), 0L);
		}

		List<TrashedModel> trashedModels = new ArrayList<>();

		for (long deleteLearningActivityId : deleteLearningActivityIds) {
			if (moveToTrash) {
				LearningActivity activity = learningActivityService.moveLearningActivityToTrash(deleteLearningActivityId);

				trashedModels.add(activity);
			} else {
				learningActivityService.deleteLearningActivity(deleteLearningActivityId);
			}
		}

		if (moveToTrash && !trashedModels.isEmpty()) {
			Map<String, Object> data = new HashMap<>();

			data.put("trashedModels", trashedModels);

			addDeleteSuccessData(actionRequest, data);
		}
	}
	
	protected void restoreTrashLearningActivities(ActionRequest actionRequest) throws Exception {

		long[] restoreTrashLearningActivityIds = StringUtil.split(ParamUtil.getString(actionRequest, "restoreTrashLearningActivityIds"), 0L);

		for (long restoreTrashLearningActivityId : restoreTrashLearningActivityIds) {
			trashEntryService.restoreEntry(restoreTrashLearningActivityId);
		}
	}
	
	protected LearningActivity updateLearningActivity(ActionRequest actionRequest) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		long actId = ParamUtil.getLong(actionRequest, "actId");
		long type = ParamUtil.getLong(actionRequest, "type");
		long moduleId = ParamUtil.getLong(actionRequest, "moduleId");
		
		LearningActivityTypeFactory learningActivityTypeFactory = LearningActivityTypeFactoryRegistryUtil.getLearningActivityTypeFactoryByType(type);
		
		Module module = moduleLocalService.getModule(moduleId);

		Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(actionRequest, "titleMapAsXML");
		Map<Locale, String> descriptionMap = LocalizationUtil.getLocalizationMap(actionRequest, "descriptionMapAsXML");
		log.debug("titleMap: " + titleMap);
		log.debug("descriptionMap: " + descriptionMap);
		
		String paramStartDate = module.getStartDate() != null ? "useStartDateModule" : "useStartExecutionDateCourse";
		String paramEndDate = module.getEndDate() != null ? "useEndDateModule" : "useEndExecutionDateCourse";
		
		boolean useStartExecutionDateCourse = ParamUtil.getBoolean(actionRequest, paramStartDate);
		log.debug(paramStartDate + ": " + useStartExecutionDateCourse);

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
		
		boolean useEndExecutionDateCourse = ParamUtil.getBoolean(actionRequest, paramEndDate);
		log.debug(paramEndDate + ": " + useEndExecutionDateCourse);
		
		int endDateMonth = ParamUtil.getInteger(actionRequest, "endDateMonth");
		int endDateDay = ParamUtil.getInteger(actionRequest, "endDateDay");
		int endDateYear = ParamUtil.getInteger(actionRequest, "endDateYear");
		int endDateHour = ParamUtil.getInteger(actionRequest, "endDateHour");
		int endDateMinute = ParamUtil.getInteger(actionRequest, "endDateMinute");
		int endDateAmPm = ParamUtil.getInteger(actionRequest, "endDateAmPm");

		if (endDateAmPm == Calendar.PM) {
			endDateHour += 12;
		}
		
		boolean required = ParamUtil.getBoolean(actionRequest, "required", false);
		int tries = ParamUtil.getInteger(actionRequest, "tries", learningActivityTypeFactory.getDefaultTries());
		double score = ParamUtil.getDouble(actionRequest, "passPuntuation", learningActivityTypeFactory.getDefaultScore());
		
		Map<Locale, String> feedbackCorrectMap = LocalizationUtil.getLocalizationMap(actionRequest, "feedbackCorrectMapAsXML");
		Map<Locale, String> feedbackNoCorrectMap = LocalizationUtil.getLocalizationMap(actionRequest, "feedbackNoCorrectMapAsXML");
		boolean commentsActivated = ParamUtil.getBoolean(actionRequest, "commentsActivated", false);
		
		System.out.println("actId: " + actId);

		ServiceContext serviceContext = ServiceContextFactory.getInstance(LearningActivity.class.getName(), actionRequest);

		LearningActivity activity = null;

		if (actId <= 0) {

			// Añadir módulo
			activity = learningActivityService.addLearningActivity(moduleId, type, titleMap, descriptionMap, useStartExecutionDateCourse, startDateMonth, 
					startDateDay, startDateYear, startDateHour, startDateMinute, useEndExecutionDateCourse, endDateMonth, endDateDay, endDateYear, 
					endDateHour, endDateMinute, required, tries, score, feedbackCorrectMap, feedbackNoCorrectMap, commentsActivated, serviceContext);
		} else {
			// Actualizamos el módulo
			activity = learningActivityService.updateLearningActivity(
				actId, titleMap, descriptionMap, useStartExecutionDateCourse, startDateMonth, 
				startDateDay, startDateYear, startDateHour, startDateMinute, useEndExecutionDateCourse, endDateMonth, endDateDay, endDateYear, 
				endDateHour, endDateMinute, required, tries, score, feedbackCorrectMap, feedbackNoCorrectMap, commentsActivated, serviceContext);
		} 
		
		//Guardamos los campos personalizados del método de evaluación
		LearningActivityType activityType = learningActivityTypeFactory.getLearningActivityType(activity);
		activityType.setExtraContent(actionRequest);
		
		learningActivityService.updateLearningActivity(activity);
		
		//Guardamos los prerequisitos
		String[] classNamePrerequisites = LMSPrefsPropsValues.getPrerequisitesOfLearningActivity(themeDisplay.getCompanyId());
		PrerequisiteFactory prerequisiteFactory = null;
		Prerequisite prerequisite = null;
		long activityClassNameId = PortalUtil.getClassNameId(LearningActivity.class);
		
		for(String classNamePrerequisite: classNamePrerequisites){
			prerequisiteFactory = PrerequisiteFactoryRegistryUtil.getPrerequisiteFactoryByClassName(classNamePrerequisite);
			prerequisite = prerequisiteFactory.getPrerequisite(activityClassNameId, activity.getActId());
			prerequisite.setExtraContent(actionRequest);
		}

		return activity;
	}
	
	private static final Log log = LogFactoryUtil.getLog(EditActivityMVCActionCommand.class);
	
	private static final TransactionConfig _transactionConfig = TransactionConfig.Factory.create(Propagation.REQUIRED, new Class<?>[] {Exception.class});
	
	@Reference
	private Portal _portal;
	
	@Reference
	private Http _http;
	
	@Reference(unbind = "-")
	protected void setLearningActivityService(LearningActivityService learningActivityService) {
		this.learningActivityService = learningActivityService;
	}
	
	@Reference(unbind = "-")
	protected void setModuleLocalService(ModuleLocalService moduleLocalService) {
		this.moduleLocalService = moduleLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setTrashEntryService(TrashEntryService trashEntryService) {
		this.trashEntryService = trashEntryService;
	}
	
	private LearningActivityService learningActivityService;
	private ModuleLocalService moduleLocalService;
	private TrashEntryService trashEntryService;

	
	private class UpdateLearningActivityCallable implements Callable<LearningActivity> {

		@Override
		public LearningActivity call() throws Exception {
			return updateLearningActivity(actionRequest);
		}

		private UpdateLearningActivityCallable(ActionRequest actionRequest) {
			this.actionRequest = actionRequest;
		}

		private final ActionRequest actionRequest;

	}
}
