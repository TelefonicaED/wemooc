package com.ted.lms.web.internal.portlet.action;

import com.liferay.asset.kernel.exception.AssetCategoryException;
import com.liferay.asset.kernel.exception.AssetTagException;
import com.liferay.portal.kernel.exception.GroupFriendlyURLException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.TrashedModel;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector;
import com.liferay.portal.kernel.theme.PortletDisplay;
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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.trash.service.TrashEntryService;
import com.ted.lms.constants.CourseConstants;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.exception.NoSuchCourseException;
import com.ted.lms.model.CalificationType;
import com.ted.lms.model.CalificationTypeFactory;
import com.ted.lms.model.Course;
import com.ted.lms.model.CourseEval;
import com.ted.lms.model.CourseEvalFactory;
import com.ted.lms.model.InscriptionType;
import com.ted.lms.model.InscriptionTypeFactory;
import com.ted.lms.registry.CalificationTypeFactoryRegistryUtil;
import com.ted.lms.registry.CourseEvalFactoryRegistryUtil;
import com.ted.lms.registry.InscriptionTypeFactoryRegistryUtil;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.service.CourseService;
import com.ted.lms.web.constants.LMSPortletConstants;
import com.ted.lms.web.internal.configuration.CourseAdminPortletInstanceConfiguration;
import com.ted.lms.web.internal.util.CourseImageSelectorHelper;

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
		"javax.portlet.name=" + LMSPortletKeys.COURSE,
		"mvc.command.name=/courses/edit_course"
	},
	service = MVCActionCommand.class
)
public class EditCourseMVCActionCommand extends BaseMVCActionCommand {
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		log.debug("cmd: " + cmd);
		
		try {
			Course course = null;

			if (cmd.equals(Constants.ADD) ||  cmd.equals(Constants.UPDATE)) {
				Callable<Course> updateCourseCallable = new UpdateCourseCallable(actionRequest);

				course = TransactionInvokerUtil.invoke(_transactionConfig, updateCourseCallable);
			}else if(cmd.equals(Constants.EXPIRE)) {
				closeCourse(actionRequest);
			}else if(cmd.equals(Constants.APPROVE)) {
				openCourse(actionRequest);
			}else if (cmd.equals(Constants.DELETE)) {
				deleteCourse(actionRequest, false);
			}
			else if (cmd.equals(Constants.MOVE_TO_TRASH)) {
				deleteCourse(actionRequest, true);
			}else if (cmd.equals(Constants.PUBLISH)) {
				changeStatusCourse(actionRequest, WorkflowConstants.STATUS_APPROVED);
			}

			String redirect = ParamUtil.getString(actionRequest, "redirect");

			String portletId = _http.getParameter(redirect, "p_p_id", false);

			boolean nextStep = ParamUtil.getBoolean(actionRequest, "nextStep", false);
			log.debug("nextStep: " + nextStep);

			//Si estamos guardando y continuando, pasamos a la siguiente pesta�a
			if (course != null && nextStep) {
				log.debug("hemos guardado y continuamos");
				redirect = getSaveAndContinueRedirect(actionRequest, course, redirect);
				
				log.debug("redirect: " + redirect);

				sendRedirect(actionRequest, actionResponse, redirect);
			} //Si estamos guardando, volvemos a la pantalla anterior (hay que tener en cuenta que no s�lo es el portlet de admin cursos,
			//puede llamarse desde cualquier otro sitio)
			else {
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
						if (cmd.equals(Constants.ADD) && (course != null)) {
							String namespace = _portal.getPortletNamespace(portletId);

							redirect = _http.addParameter(redirect, namespace + "className", Course.class.getName());
							redirect = _http.addParameter(redirect, namespace + "classPK",course.getCourseId());
						}

						actionRequest.setAttribute(WebKeys.REDIRECT, redirect);
					}
				}
			}
		} catch(GroupFriendlyURLException e) {
			e.printStackTrace();
			SessionErrors.add(actionRequest, e.getClass(), e);
			
			actionResponse.setRenderParameter("mvcRenderCommandName", "/courses/edit_course");

			hideDefaultSuccessMessage(actionRequest);
		} catch (AssetCategoryException | AssetTagException e) {
			e.printStackTrace();
			SessionErrors.add(actionRequest, e.getClass(), e);

			actionResponse.setRenderParameter("mvcRenderCommandName", "/courses/edit_course");

			hideDefaultSuccessMessage(actionRequest);
		} catch (NoSuchCourseException | PrincipalException e) {
			SessionErrors.add(actionRequest, e.getClass());

			actionResponse.setRenderParameter("mvcPath", "/courses/error.jsp");

			hideDefaultSuccessMessage(actionRequest);
		} catch (Throwable t) {
			log.error(t, t);

			actionResponse.setRenderParameter("mvcPath", "/courses/error.jsp");

			hideDefaultSuccessMessage(actionRequest);
		}
	}
	
	protected void changeStatusCourse(ActionRequest actionRequest, int status) throws PrincipalException, PortalException {
		long courseId = ParamUtil.getLong(actionRequest, "courseId");
		ServiceContext serviceContext = ServiceContextFactory.getInstance(Course.class.getName(), actionRequest);
		
		courseService.updateCourse(courseId, status, serviceContext);
	}
	
	protected void deleteCourse(ActionRequest actionRequest, boolean moveToTrash) throws Exception {

		long courseId = ParamUtil.getLong(actionRequest, "courseId");
		
		List<TrashedModel> trashedModels = new ArrayList<>();

		if (moveToTrash) {
			Course course = courseService.moveEntryToTrash(courseId);

			trashedModels.add(course);
		} else {
			courseService.deleteCourse(courseId);
		}

		if (moveToTrash && !trashedModels.isEmpty()) {
			Map<String, Object> data = new HashMap<>();

			data.put("trashedModels", trashedModels);

			addDeleteSuccessData(actionRequest, data);
		}
	}
	
	protected void closeCourse(ActionRequest actionRequest) throws PortalException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long courseId = ParamUtil.getLong(actionRequest, "courseId");
		
		ServiceContext serviceContext = ServiceContextFactory.getInstance(Course.class.getName(), actionRequest);
		
		courseLocalService.updateStatus(themeDisplay.getUserId(), courseId, WorkflowConstants.STATUS_INACTIVE, serviceContext);
	}
	
	protected void openCourse(ActionRequest actionRequest) throws PortalException {
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long courseId = ParamUtil.getLong(actionRequest, "courseId");
		
		ServiceContext serviceContext = ServiceContextFactory.getInstance(Course.class.getName(), actionRequest);
		
		courseLocalService.updateStatus(themeDisplay.getUserId(), courseId, WorkflowConstants.STATUS_APPROVED, serviceContext);
	}
	
	protected String getSaveAndContinueRedirect(ActionRequest actionRequest, Course course, String redirect) throws Exception {

		PortletConfig portletConfig = (PortletConfig)actionRequest.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);

		LiferayPortletURL portletURL = PortletURLFactoryUtil.create(actionRequest, portletConfig.getPortletName(),PortletRequest.RENDER_PHASE);

		portletURL.setParameter("mvcRenderCommandName", "/courses/edit_course");
		
		String navigationItem = ParamUtil.getString(actionRequest, "navigationItem");
		String navigation = ParamUtil.getString(actionRequest, "navigation");
		log.debug("navigationItem: " + navigationItem);
		log.debug("navigation: " + navigation);
		
		if(Validator.isNull(navigationItem)) {
			ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
			CourseAdminPortletInstanceConfiguration configuration = null;
			PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();
			try {
				configuration = portletDisplay.getPortletInstanceConfiguration(CourseAdminPortletInstanceConfiguration.class);
			} catch (ConfigurationException e) {
				e.printStackTrace();
			}
			if(LMSPortletConstants.EDIT_COURSE_DESCRIPTION.equals(navigation)) {
				navigation = LMSPortletConstants.EDIT_COURSE_CONFIGURATION;
			}else if(LMSPortletConstants.EDIT_COURSE_CONFIGURATION.equals(navigation) && configuration != null && configuration.courseAssetLinks()) {
				navigation = LMSPortletConstants.EDIT_COURSE_ASSET_LINKS;
			}else if(LMSPortletConstants.EDIT_COURSE_CONFIGURATION.equals(navigation) && configuration != null && 
						(configuration.courseWelcomeMessage() || configuration.courseGoodbyeMessage())) {
				navigation = LMSPortletConstants.EDIT_COURSE_MESSAGES;
			}else if(LMSPortletConstants.EDIT_COURSE_ASSET_LINKS.equals(navigation) && configuration != null && 
					(configuration.courseWelcomeMessage() || configuration.courseGoodbyeMessage())) {
				navigation = LMSPortletConstants.EDIT_COURSE_MESSAGES;
			}
		}else {
			navigation = navigationItem;
		}
		
		portletURL.setParameter(Constants.CMD, Constants.UPDATE, false);
		portletURL.setParameter("redirect", redirect, false);
		portletURL.setParameter("courseId", String.valueOf(course.getCourseId()), false);
		portletURL.setParameter("navigation", navigation, false);
		portletURL.setWindowState(actionRequest.getWindowState());

		return portletURL.toString();
	}
	
	protected Course updateCourse(ActionRequest actionRequest) throws Exception {
		
		String navigation = ParamUtil.getString(actionRequest, "navigation");
		log.debug("navigation: " + navigation);
		
		Course course = null;
		
		if(LMSPortletConstants.EDIT_COURSE_DESCRIPTION.equals(navigation)) {
			course = updateCourseDescription(actionRequest);
		}else if(LMSPortletConstants.EDIT_COURSE_CONFIGURATION.equals(navigation)) {
			course = updateCourseConfiguration(actionRequest);
		}else if(LMSPortletConstants.EDIT_COURSE_ASSET_LINKS.equals(navigation)) {
			course = updateCourseAssetLinks(actionRequest);
		}else if(LMSPortletConstants.EDIT_COURSE_MESSAGES.equals(navigation)) {
			course = updateCourseMessages(actionRequest);
		}
		
		return course;
	}

	protected Course updateCourseDescription(ActionRequest actionRequest) throws Exception {
		long courseId = ParamUtil.getLong(actionRequest, "courseId", 0);
		
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(actionRequest, "titleMapAsXML");
		Map<Locale, String> summaryMap = LocalizationUtil.getLocalizationMap(actionRequest, "summaryMapAsXML");
		Map<Locale, String> descriptionMap = LocalizationUtil.getLocalizationMap(actionRequest, "descriptionMapAsXML");
		String friendlyURL = ParamUtil.getString(actionRequest, "friendlyURL");
		long layoutSetPrototypeId = ParamUtil.getLong(actionRequest, "layoutSetPrototypeId", 0);
		boolean indexer = ParamUtil.getBoolean(actionRequest, "visible", false);	
		long courseImageFileEntryId = ParamUtil.getLong(actionRequest, "courseImageFileEntryId", 0);
		
		long oldSmallImageId = 0;
		
		if (courseId != 0) {
			Course course = courseLocalService.getCourse(courseId);

			oldSmallImageId = course.getSmallImageId();
		}
		
		CourseImageSelectorHelper courseSmallImageSelectorHelper = new CourseImageSelectorHelper(oldSmallImageId, courseImageFileEntryId);
		ImageSelector smallImageImageSelector = courseSmallImageSelectorHelper.getImageSelector();
		
		ServiceContext serviceContext = ServiceContextFactory.getInstance(Course.class.getName(), actionRequest);
		
		long parentCourseId = ParamUtil.getLong(actionRequest, "parentCourseId", CourseConstants.DEFAULT_PARENT_COURSE_ID);
		
		Course course = null;

		if (courseId <= 0) {
			long courseTypeId = ParamUtil.getLong(actionRequest, "courseTypeId", 0);
			// Añadir curso
			course = courseService.addCourse(themeDisplay.getScopeGroupId(), titleMap, descriptionMap, summaryMap, indexer, friendlyURL, layoutSetPrototypeId, parentCourseId, 
					courseTypeId, smallImageImageSelector, serviceContext);
		} else {
			// Actualizamos el curso

			course = courseService.updateCourse(courseId, titleMap, descriptionMap, summaryMap, indexer, friendlyURL, layoutSetPrototypeId, 
					smallImageImageSelector,  serviceContext);
		} 
		
		if (courseSmallImageSelectorHelper.isFileEntryTempFile()) {
			courseLocalService.addOriginalImageFileEntry(themeDisplay.getUserId(), course.getGroupId(), course.getCourseId(), smallImageImageSelector);

			PortletFileRepositoryUtil.deletePortletFileEntry(courseImageFileEntryId);
		}

		return course;
	}
	
	protected Course updateCourseConfiguration(ActionRequest actionRequest) throws Exception {
		
		long courseId = ParamUtil.getLong(actionRequest, "courseId", 0);
		
		long courseEvalId = ParamUtil.getLong(actionRequest, "courseEvalId", 0);
		long calificationTypeId = ParamUtil.getLong(actionRequest, "calificationType", 0);
		long inscriptionTypeId = ParamUtil.getLong(actionRequest, "inscriptionType", 0);
		int registrationStartDateMonth = ParamUtil.getInteger(actionRequest, "registrationStartDateMonth");
		int registrationStartDateDay = ParamUtil.getInteger(actionRequest, "registrationStartDateDay");
		int registrationStartDateYear = ParamUtil.getInteger(actionRequest, "registrationStartDateYear");
		int registrationStartDateHour = ParamUtil.getInteger(actionRequest, "registrationStartDateHour");
		int registrationStartDateMinute = ParamUtil.getInteger(actionRequest, "registrationStartDateMinute");
		int registrationStartDateAmPm = ParamUtil.getInteger(actionRequest, "registrationStartDateAmPm");
		if (registrationStartDateAmPm == Calendar.PM) {
			registrationStartDateHour += 12;
		}
		int registrationEndDateMonth = ParamUtil.getInteger(actionRequest, "registrationEndDateMonth");
		int registrationEndDateDay = ParamUtil.getInteger(actionRequest, "registrationEndDateDay");
		int registrationEndDateYear = ParamUtil.getInteger(actionRequest, "registrationEndDateYear");
		int registrationEndDateHour = ParamUtil.getInteger(actionRequest, "registrationEndDateHour");
		int registrationEndDateMinute = ParamUtil.getInteger(actionRequest, "registrationEndDateMinute");
		int registrationEndDateAmPm = ParamUtil.getInteger(actionRequest, "registrationEndDateAmPm");
		if (registrationEndDateAmPm == Calendar.PM) {
			registrationEndDateHour += 12;
		}
		int executionStartDateMonth = ParamUtil.getInteger(actionRequest, "executionStartDateMonth");
		int executionStartDateDay = ParamUtil.getInteger(actionRequest, "executionStartDateDay");
		int executionStartDateYear = ParamUtil.getInteger(actionRequest, "executionStartDateYear");
		int executionStartDateHour = ParamUtil.getInteger(actionRequest, "executionStartDateHour");
		int executionStartDateMinute = ParamUtil.getInteger(actionRequest, "executionStartDateMinute");
		int executionStartDateAmPm = ParamUtil.getInteger(actionRequest, "executionStartDateAmPm");
		if (executionStartDateAmPm == Calendar.PM) {
			executionStartDateHour += 12;
		}
		int executionEndDateMonth = ParamUtil.getInteger(actionRequest, "executionEndDateMonth");
		int executionEndDateDay = ParamUtil.getInteger(actionRequest, "executionEndDateDay");
		int executionEndDateYear = ParamUtil.getInteger(actionRequest, "executionEndDateYear");
		int executionEndDateHour = ParamUtil.getInteger(actionRequest, "executionEndDateHour");
		int executionEndDateMinute = ParamUtil.getInteger(actionRequest, "executionEndDateMinute");
		int executionEndDateAmPm = ParamUtil.getInteger(actionRequest, "executionEndDateAmPm");
		if (executionEndDateAmPm == Calendar.PM) {
			executionEndDateHour += 12;
		}
		
		int typeSite = ParamUtil.getInteger(actionRequest, "typeSite", GroupConstants.TYPE_SITE_OPEN);
		int maxUsers = ParamUtil.getInteger(actionRequest, "maxUsers", 0);
		log.debug("courseId: " + courseId);
		log.debug("courseEvalId: " + courseEvalId);
		log.debug("calificationType: " + calificationTypeId);
		log.debug("inscriptionType: " + inscriptionTypeId);
		
		ServiceContext serviceContext = ServiceContextFactory.getInstance(Course.class.getName(), actionRequest);
		int workflowAction = ParamUtil.getInteger(actionRequest, "workflowAction", WorkflowConstants.ACTION_SAVE_DRAFT);
		
		Course course = courseService.updateCourse(courseId, registrationStartDateMonth, registrationStartDateDay, registrationStartDateYear, registrationStartDateHour, 
				registrationStartDateMinute, registrationEndDateMonth, registrationEndDateDay, registrationEndDateYear, registrationEndDateHour, registrationEndDateMinute, 
				executionStartDateMonth, executionStartDateDay, executionStartDateYear, executionStartDateHour, executionStartDateMinute, executionEndDateMonth, 
				executionEndDateDay, executionEndDateYear, executionEndDateHour, executionEndDateMinute, typeSite, inscriptionTypeId, courseEvalId, 
				calificationTypeId, maxUsers, workflowAction, serviceContext);
		
		//Actualizamos el extracontent del curso
		//Guardamos los campos personalizados del método de evaluación
		CourseEvalFactory courseEvalFactory = CourseEvalFactoryRegistryUtil.getCourseEvalFactoryByType(courseEvalId);
		CourseEval courseEval = courseEvalFactory.getCourseEval(course);
		courseEval.setExtraContent(actionRequest);
		
		//Guardamos los campos personalizados del tipo de calificación
		CalificationTypeFactory calificationTypeFactory = CalificationTypeFactoryRegistryUtil.getCalificationTypeFactoryByType(calificationTypeId);
		CalificationType calificationType = calificationTypeFactory.getCalificationType(course);
		calificationType.setExtraContent(actionRequest);
		
		//Guardamos los campos personalizados del tipo de inscripción
		InscriptionTypeFactory inscriptionTypeFactory = InscriptionTypeFactoryRegistryUtil.getInscriptionTypeFactoryByType(inscriptionTypeId);
		InscriptionType inscriptionType = inscriptionTypeFactory.getInscriptionType(course, serviceContext);
		inscriptionType.setExtraContent(actionRequest);
		
		courseLocalService.updateCourse(course);
		
		return course;
	}

	protected Course updateCourseMessages(ActionRequest actionRequest) throws Exception {
		long courseId = ParamUtil.getLong(actionRequest, "courseId", 0);
		
		boolean welcome = ParamUtil.getBoolean(actionRequest, "welcome", false);
		Map<Locale, String> welcomeSubjectMap = LocalizationUtil.getLocalizationMap(actionRequest, "welcomeSubjectMapAsXML");
		Map<Locale, String> welcomeMsgMap = LocalizationUtil.getLocalizationMap(actionRequest, "welcomeMsgMapAsXML");
		
		boolean goodbye = ParamUtil.getBoolean(actionRequest, "goodbye", false);
		Map<Locale, String> goodbyeSubjectMap = LocalizationUtil.getLocalizationMap(actionRequest, "goodbyeSubjectMapAsXML");
		Map<Locale, String> goodbyeMsgMap = LocalizationUtil.getLocalizationMap(actionRequest, "goodbyeMsgMapAsXML");
		
		boolean deniedInscription = ParamUtil.getBoolean(actionRequest, "deniedInscription", false);
		Map<Locale, String> deniedInscriptionSubjectMap = LocalizationUtil.getLocalizationMap(actionRequest, "deniedInscriptionSubjectMapAsXML");
		Map<Locale, String> deniedInscriptionMsgMap = LocalizationUtil.getLocalizationMap(actionRequest, "deniedInscriptionMsgMapAsXML");
		
		ServiceContext serviceContext = ServiceContextFactory.getInstance(Course.class.getName(), actionRequest);
		int workflowAction = ParamUtil.getInteger(actionRequest, "workflowAction", WorkflowConstants.ACTION_SAVE_DRAFT);
		
		log.debug("workflowAction: " + workflowAction);
		
		Course course = courseService.updateCourse(courseId, welcome, welcomeSubjectMap, welcomeMsgMap, goodbye, goodbyeSubjectMap, 
				goodbyeMsgMap, deniedInscription, deniedInscriptionSubjectMap, deniedInscriptionMsgMap, workflowAction, serviceContext);
		
		return course;
	}
	
	protected Course updateCourseAssetLinks(ActionRequest actionRequest) throws Exception {
		long courseId = ParamUtil.getLong(actionRequest, "courseId", 0);
		
		ServiceContext serviceContext = ServiceContextFactory.getInstance(Course.class.getName(), actionRequest);
		int workflowAction = ParamUtil.getInteger(actionRequest, "workflowAction", WorkflowConstants.ACTION_SAVE_DRAFT);
		
		return courseService.updateCourse(courseId, workflowAction, serviceContext);
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
	
	private class UpdateCourseCallable implements Callable<Course> {

		@Override
		public Course call() throws Exception {
			return updateCourse(actionRequest);
		}

		private UpdateCourseCallable(ActionRequest actionRequest) {
			this.actionRequest = actionRequest;
		}

		private final ActionRequest actionRequest;

	}

}
