package com.ted.lms.web.internal.portlet.action;

import com.liferay.document.library.kernel.exception.FileSizeException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.LayoutSetPrototype;
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
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.exception.NoSuchCourseTypeException;
import com.ted.lms.exception.SmallImageNameException;
import com.ted.lms.exception.SmallImageScaleException;
import com.ted.lms.model.CalificationTypeFactory;
import com.ted.lms.model.CourseEvalFactory;
import com.ted.lms.model.CourseType;
import com.ted.lms.model.InscriptionTypeFactory;
import com.ted.lms.model.LearningActivityTypeFactory;
import com.ted.lms.service.CourseTypeLocalService;
import com.ted.lms.service.CourseTypeRelationLocalService;
import com.ted.lms.web.internal.util.CourseTypeImageSelectorHelper;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + LMSPortletKeys.COURSE_TYPE,
		"mvc.command.name=/course_type/edit_course_type"
	},
	service = MVCActionCommand.class
)
public class EditCourseTypeMVCActionCommand extends BaseMVCActionCommand {
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		log.debug("cmd: " + cmd);
		
		try {
			CourseType courseType = null;

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
				Callable<CourseType> updateCourseTypeCallable = new UpdateCourseTypeCallable(actionRequest);

				courseType = TransactionInvokerUtil.invoke(_transactionConfig, updateCourseTypeCallable);
			} else if (cmd.equals(Constants.DELETE)) {
				deleteCourseType(actionRequest, false);
			}


			if(courseType != null) {
				actionResponse.setRenderParameter("courseTypeId", String.valueOf(courseType.getCourseTypeId()));
				actionResponse.setRenderParameter("mvcRenderCommandName", "/course_type/edit_course_type");
			}
			
				
		} catch (SmallImageNameException | SmallImageScaleException | 
			   FileSizeException | LiferayFileItemException | SanitizerException | UploadRequestSizeException e) {
			e.printStackTrace();
			SessionErrors.add(actionRequest, e.getClass());

			actionResponse.setRenderParameter("mvcRenderCommandName", "/modules/edit_module");

			hideDefaultSuccessMessage(actionRequest);
		} catch (NoSuchCourseTypeException | PrincipalException e) {
			SessionErrors.add(actionRequest, e.getClass());

			actionResponse.setRenderParameter("mvcPath", "/course_type/error.jsp");

			hideDefaultSuccessMessage(actionRequest);
		} catch (Throwable t) {
			log.error(t, t);

			actionResponse.setRenderParameter("mvcPath", "/course_type/error.jsp");

			hideDefaultSuccessMessage(actionRequest);
		}
	}
	
	protected void deleteCourseType(ActionRequest actionRequest, boolean moveToTrash) throws Exception {

		long courseTypeId = ParamUtil.getLong(actionRequest, "courseTypeId");

		courseTypeLocalService.deleteCourseType(courseTypeId);

	}
	
	protected CourseType updateCourseType(ActionRequest actionRequest) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		long courseTypeId = ParamUtil.getLong(actionRequest, "courseTypeId");

		Map<Locale, String> nameMap = LocalizationUtil.getLocalizationMap(actionRequest, "nameMapAsXML");
		Map<Locale, String> descriptionMap = LocalizationUtil.getLocalizationMap(actionRequest, "descriptionMapAsXML");
		log.debug("nameMap: " + nameMap);
		log.debug("descriptionMap: " + descriptionMap);

		long oldIconId = 0;
		
		log.debug("courseTypeId: " + courseTypeId);
		
		CourseType courseType = null;
		
		if (courseTypeId != 0) {
			courseType = courseTypeLocalService.getCourseType(courseTypeId);

			oldIconId = courseType.getIconId();
		}
		
		long iconFileEntryId = ParamUtil.getLong(actionRequest, "iconFileEntryId");

		CourseTypeImageSelectorHelper courseTypeIconSelectorHelper = new CourseTypeImageSelectorHelper(oldIconId, iconFileEntryId);

		ImageSelector iconSelector = courseTypeIconSelectorHelper.getImageSelector();

		ServiceContext serviceContext = ServiceContextFactory.getInstance(CourseType.class.getName(), actionRequest);

		if (courseTypeId <= 0) {

			// Añadir módulo
			courseType = courseTypeLocalService.addCourseType(nameMap, descriptionMap, iconSelector, serviceContext);
		} else {
			// Actualizamos el módulo
			courseType = courseTypeLocalService.updateCourseType(courseTypeId, nameMap, descriptionMap, iconSelector, serviceContext);
		} 
		
		if (courseTypeIconSelectorHelper.isFileEntryTempFile()) {
			courseTypeLocalService.addOriginalIconFileEntry(themeDisplay.getUserId(), courseType.getGroupId(), courseType.getCourseTypeId(), iconSelector);

			PortletFileRepositoryUtil.deletePortletFileEntry(iconFileEntryId);
		}
		
		//Recogemos las relaciones
		long[] templateIds = ParamUtil.getLongValues(actionRequest, "templateIds", new long[] {});
		long[] courseEvalTypeIds = ParamUtil.getLongValues(actionRequest, "courseEvalIds", new long[] {});
		long[] learningActivityTypeIds = ParamUtil.getLongValues(actionRequest, "learningActivityTypeIds", new long[] {});
		long[] inscriptionTypeIds = ParamUtil.getLongValues(actionRequest, "inscriptionTypeIds", new long[] {});
		long[] calificationTypeIds = ParamUtil.getLongValues(actionRequest, "calificationTypeIds", new long[] {});
		
		courseTypeRelationLocalService.addCourseTypeRelation(courseType.getCourseTypeId(), PortalUtil.getClassNameId(LayoutSetPrototype.class), templateIds);
		courseTypeRelationLocalService.addCourseTypeRelation(courseType.getCourseTypeId(), PortalUtil.getClassNameId(CourseEvalFactory.class), courseEvalTypeIds);
		courseTypeRelationLocalService.addCourseTypeRelation(courseType.getCourseTypeId(), PortalUtil.getClassNameId(LearningActivityTypeFactory.class), learningActivityTypeIds);
		courseTypeRelationLocalService.addCourseTypeRelation(courseType.getCourseTypeId(), PortalUtil.getClassNameId(InscriptionTypeFactory.class), inscriptionTypeIds);
		courseTypeRelationLocalService.addCourseTypeRelation(courseType.getCourseTypeId(), PortalUtil.getClassNameId(CalificationTypeFactory.class), calificationTypeIds);
		
		return courseType;
	}
	
	private static final Log log = LogFactoryUtil.getLog(EditCourseTypeMVCActionCommand.class);
	
	private static final TransactionConfig _transactionConfig =
			TransactionConfig.Factory.create(
				Propagation.REQUIRED, new Class<?>[] {Exception.class});
	
	@Reference
	private Portal _portal;
	
	@Reference
	private Http _http;
	
	@Reference(unbind = "-")
	protected void setCourseTypeLocalService(CourseTypeLocalService courseTypeLocalService) {
		this.courseTypeLocalService = courseTypeLocalService;
	}
	
	private CourseTypeLocalService courseTypeLocalService;
	
	@Reference(unbind = "-")
	protected void setCourseRelationTypeLocalService(CourseTypeRelationLocalService courseTypeRelationLocalService) {
		this.courseTypeRelationLocalService = courseTypeRelationLocalService;
	}
	
	private CourseTypeRelationLocalService courseTypeRelationLocalService;

	
	private class UpdateCourseTypeCallable implements Callable<CourseType> {

		@Override
		public CourseType call() throws Exception {
			return updateCourseType(actionRequest);
		}

		private UpdateCourseTypeCallable(ActionRequest actionRequest) {
			this.actionRequest = actionRequest;
		}

		private final ActionRequest actionRequest;

	}
}
