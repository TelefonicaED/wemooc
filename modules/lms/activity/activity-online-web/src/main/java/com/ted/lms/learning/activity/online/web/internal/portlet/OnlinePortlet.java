package com.ted.lms.learning.activity.online.web.internal.portlet;

import com.ted.lms.learning.activity.online.OnlineActivityType;
import com.ted.lms.learning.activity.online.OnlineActivityTypeFactory;
import com.ted.lms.learning.activity.online.web.constants.OnlineConstants;
import com.ted.lms.learning.activity.online.web.constants.OnlinePortletKeys;
import com.ted.lms.learning.activity.online.web.internal.exception.OnlineFileEntryExtensionException;
import com.ted.lms.learning.activity.online.web.internal.exception.OnlineFileEntrySizeException;
import com.ted.lms.learning.activity.online.web.internal.util.OnlinePrefsPropsValues;
import com.ted.lms.model.CalificationType;
import com.ted.lms.model.CalificationTypeFactory;
import com.ted.lms.model.Course;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityResult;
import com.ted.lms.model.LearningActivityTry;
import com.ted.lms.registry.CalificationTypeFactoryRegistryUtil;
import com.ted.lms.registry.LearningActivityTypeFactoryRegistryUtil;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.service.LearningActivityLocalService;
import com.ted.lms.service.LearningActivityResultLocalService;
import com.ted.lms.service.LearningActivityTryLocalService;
import com.ted.lms.service.ModuleLocalService;
import com.ted.lms.util.LMSUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.ProcessAction;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author JE10436
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=wemooc",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.init-param.template-path=/META-INF/resources/",
		"javax.portlet.init-param.mvc-command-names-default-views=/activities/online/view_activity",
		"javax.portlet.name=" + OnlinePortletKeys.ONLINE,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=user",
		"javax.portlet.supported-public-render-parameter=actId"
	},
	service = Portlet.class
)
public class OnlinePortlet extends MVCPortlet {
	
	private static final Log log = LogFactoryUtil.getLog(OnlinePortlet.class);
	
	@ProcessAction(name = "saveActivity")
	public void saveActivity(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException {
		
		log.debug("saveActivity action");
		
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		long actId = ParamUtil.getLong(actionRequest, "actId");
		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);
		
		String text = ParamUtil.getString(uploadRequest, "description");
		try {
		
			LearningActivity activity = learningActivityLocalService.getLearningActivity(actId);
			
			OnlineActivityTypeFactory onlineActivityTypeFactory = (OnlineActivityTypeFactory)LearningActivityTypeFactoryRegistryUtil.getLearningActivityTypeFactoryByType(OnlineConstants.TYPE);
			OnlineActivityType onlineActivityType = onlineActivityTypeFactory.getOnlineActivityType(activity);
			
			boolean isSetTextoEnr =  onlineActivityType.getRichText();
			boolean isSetFichero =  onlineActivityType.getIncludeFile();
					
			if(log.isDebugEnabled()){
				log.debug("::setActivity:: actId :: " + actId);
				log.debug("::setActivity:: text :: " + text);
				log.debug("::setActivity:: isSetTextoEnr :: " + isSetTextoEnr);
				log.debug("::setActivity:: isSetFichero :: " + isSetFichero);
			}
	
			LearningActivityResult result = learningActivityResultLocalService.getLearningActivityResult(actId, themeDisplay.getUserId());
			
			//Si el result no tiene end date es que aún no está corregida
			if(Validator.isNotNull(result) && Validator.isNotNull(result.getEndDate())) {
				SessionErrors.add(actionRequest, "onlineActivity.max-tries");	
				if(log.isDebugEnabled())
					log.debug("::setActivity:: MAX TRIES :: ");
			} else {
				Element resultadosXML = SAXReaderUtil.createElement("results");
				Document resultadosXMLDoc=SAXReaderUtil.createDocument(resultadosXML);
				String fileName = null;
	
				if(isSetFichero) {
					fileName = uploadRequest.getFileName("fileName");
					if(log.isDebugEnabled())
						log.debug("::setActivity:: fileName :: " + fileName);
					File file = uploadRequest.getFile("fileName");
					String mimeType = uploadRequest.getContentType("fileName");
					//Si no se ha subido archivo y no hay un intento previo con archivo subido
					if (Validator.isNull(fileName) && Validator.isNull(result)) {
						if(log.isDebugEnabled())
							log.debug("::setActivity:: MANDATORYFILE :: ");
						SessionErrors.add(actionRequest, "onlineActivity.mandatory.file");
						actionRequest.setAttribute("actId", actId);
						actionResponse.setRenderParameter("text", text);
						return;
					}
						
					//Subimos el fichero
					try {
						Folder folder = addOnlineFolder(themeDisplay.getUserId(), themeDisplay.getScopeGroupId());
						DLFileEntry onlineFileEntry = addOnlineFileEntry(fileName, file, mimeType, folder.getFolderId(), themeDisplay.getScopeGroupId(),
								themeDisplay.getCompanyId(), themeDisplay.getUserId());
						//Asociamos con el fichero subido.
						Element fileXML=SAXReaderUtil.createElement(OnlineConstants.TRY_FILE_XML);
						fileXML.addAttribute("id", Long.toString(onlineFileEntry.getFileEntryId()));
						resultadosXML.add(fileXML);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
	
				if(isSetTextoEnr){
					Element richTextXML=SAXReaderUtil.createElement(OnlineConstants.TRY_RICH_TEXT_XML);
					richTextXML.setText(text);
					resultadosXML.add(richTextXML);				
				} else {
					Element textXML=SAXReaderUtil.createElement(OnlineConstants.TRY_TEXT_XML);
					textXML.setText(text);
					resultadosXML.add(textXML);				
				}
				
				LearningActivityTry learningActivityTry = null;
				if(Validator.isNotNull(result) && Validator.isNull(result.getEndDate())){
					//Si hay resultado sin corregir se actualiza el último try
					learningActivityTry = learningActivityTryLocalService.getLastLearningActivityTry(actId, themeDisplay.getUserId());
					//Si no se ha subido un archivo nuevo vuelvo a guardar el archivo que estaba guardado previamente
					if(isSetFichero && Validator.isNull(fileName)){
						Iterator<Node> nodeItr = SAXReaderUtil.read(learningActivityTry.getTryResultData()).getRootElement().nodeIterator();	
						while(nodeItr.hasNext()) {
							Node element = nodeItr.next();
							if(OnlineConstants.TRY_FILE_XML.equals(element.getName())) {
								Element fileXML=SAXReaderUtil.createElement(OnlineConstants.TRY_FILE_XML);
								fileXML.addAttribute("id", ((Element)element).attributeValue("id"));
								resultadosXML.add(fileXML);
							}
						}
					}
				}
				
				ServiceContext serviceContext = ServiceContextFactory.getInstance(actionRequest);
				if(Validator.isNull(learningActivityTry)) {
					//Si no se encuentra ningún intento previo se crea
					learningActivityTry =  learningActivityTryLocalService.addLearningActivityTry(actId, themeDisplay.getUserId(), serviceContext);
				}
				
				learningActivityTry.setTryResultData(resultadosXMLDoc.formattedString());	
				learningActivityTryLocalService.updateLearningActivityTry(learningActivityTry, 0, serviceContext);
				SessionMessages.add(actionRequest, "onlinetaskactivity.updating");
			}
			
			actionResponse.setRenderParameter("actId", String.valueOf(actId));
		
		} catch (IOException | DocumentException e) {
			e.printStackTrace();
		}
	}
	
	@ProcessAction(name = "updateResult")
	public void updateResult(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException {
		
		log.debug("updateResult action");
		
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		long actId = ParamUtil.getLong(actionRequest, "actId");
		
		long studentId = ParamUtil.getLong(actionRequest,"studentId");	
		String comments = ParamUtil.getString(actionRequest,"comments");
		
		log.debug("actId: " + actId);
		log.debug("studentId: " + studentId);
		log.debug("comments: " + comments);
		
		//Obtenemos el método de calificación para obtener el valor
		Course course = courseLocalService.getCourseByGroupCreatedId(themeDisplay.getScopeGroupId());
		CalificationTypeFactory calificationTypeFactory = CalificationTypeFactoryRegistryUtil.getCalificationTypeFactoryByType(course.getCalificationType());
		CalificationType calificationType = calificationTypeFactory.getCalificationType(course);
		
		try {
		
			double result = calificationType.getResultBase100(actionRequest);
			
			log.debug("result base 100: " + result);
			
			LearningActivityTry  learningActivityTry =  learningActivityTryLocalService.getLastLearningActivityTry(actId, studentId);
			ServiceContext serviceContext = ServiceContextFactory.getInstance(LearningActivityTry.class.getName(), actionRequest);
			if(learningActivityTry==null){
				learningActivityTry = learningActivityTryLocalService.addLearningActivityTry(actId, studentId, serviceContext);
				log.debug("creamos learningActivityTryId: " + learningActivityTry.getLatId());
			}else {
				log.debug("learningActivityTryId: " + learningActivityTry.getLatId());
			}
			
			learningActivityTry.setComments(comments);
			learningActivityTryLocalService.finishLearningActivityTry(learningActivityTry, result, serviceContext);
			
			LearningActivityResult learningActivityResult = learningActivityResultLocalService.getLearningActivityResult(actId, studentId);
			String status="status.not-attempted";
			if(learningActivityResult != null){
				status="status.incomplete";
				
				if(learningActivityResult.getEndDate()!=null){
					status="status.failed"	;
				}
				if(learningActivityResult.isPassed()){
					status="status.passed"	;
				}
			}	
			
			actionResponse.setRenderParameter("status", status);
			SessionMessages.add(actionRequest, "resultEdited");
		}catch(PortalException e) {
			e.printStackTrace();
			SessionErrors.add(actionRequest, "grades.bad-updating");
		}
		
		actionResponse.setRenderParameter("studentId", String.valueOf(studentId));
		actionResponse.setRenderParameter("actId", String.valueOf(actId));
		actionResponse.setRenderParameter("mvcRenderCommandName", "/activity/online/view_result");
		
	}
	
	protected Folder addOnlineFolder(long userId, long groupId) throws PortalException {

		return doAddFolder(userId, groupId, getOnlineFolderName(userId));
	}
	
	protected String getOnlineFolderName(long userId) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    	Date date = new Date();
    	return dateFormat.format(date) + StringPool.UNDERLINE + userId;
	}
	
	protected Folder doAddFolder(long userId, long groupId, String folderName) throws PortalException {
		
		Folder onlineFolder = null;

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);

		long repositoryId = DLFolderConstants.getDataRepositoryId(groupId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID);
		Folder moduleFolder = moduleLocalService.addModuleFolder(userId, repositoryId);

		if(Validator.isNotNull(moduleFolder)) {
			onlineFolder = dlAppLocalService.addFolder(userId, repositoryId, moduleFolder.getFolderId(), folderName, folderName, serviceContext);
		}
		
		return onlineFolder;
	}
	
	public DLFileEntry addOnlineFileEntry(String fileName, File file, String mimeType, long folderId, long groupId, long companyId, long userCreatedId) throws PortalException, IOException {

		log.debug("fileName: " + fileName);
		log.debug("mimeType: " + mimeType);
		log.debug("folderId: " + folderId);
		log.debug("groupId: " + groupId);
		log.debug("companyId: " + companyId);
		log.debug("userCreatedId: " + userCreatedId);
		
		validateFile(fileName, file.length(), companyId);
		
		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(false);
		serviceContext.setAddGuestPermissions(false);
		
		try (InputStream inputStream = new FileInputStream(file)) {
			DLFileEntry fileEntry = dlFileEntryLocalService.addFileEntry(userCreatedId, groupId, 
					groupId,folderId,fileName, mimeType, fileName, null, "Importation", 0, null, null , inputStream, file.length(), serviceContext);

			//Añadimos permisos a los usuarios que tienen el permiso de ver resultados
			try{
				resourcePermissionLocalService.setResourcePermissions(companyId, DLFileEntry.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(fileEntry.getFileEntryId()) , LMSUtil.getTeacherRoleId(companyId), new String[]{ActionKeys.VIEW});
				resourcePermissionLocalService.setResourcePermissions(companyId, DLFileEntry.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(fileEntry.getFileEntryId()) , LMSUtil.getEditorRoleId(companyId), new String[]{ActionKeys.VIEW});
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return fileEntry;
		}
		
	}
	
	private void validateFile(String fileName, long size, long companyId) throws PortalException {

		long onlineFileMaxSize = OnlinePrefsPropsValues.getOnlineFileMaxSize(companyId);
		
		if (onlineFileMaxSize > 0 && size > onlineFileMaxSize) {
			throw new OnlineFileEntrySizeException();
		}

		String extension = FileUtil.getExtension(fileName);

		String[] imageExtensions = OnlinePrefsPropsValues.getOnlineFileExtensions(companyId);

		for (String imageExtension : imageExtensions) {
			if (!StringPool.STAR.equals(imageExtension) &&
				imageExtension.equals(StringPool.PERIOD + extension)) {
				
				throw new OnlineFileEntryExtensionException("Invalid extension for file name " + fileName);
			}
		}

	}
	
	@Reference(unbind = "-")
	protected void setLearningActivityResultLocalService(LearningActivityResultLocalService learningActivityResultLocalService) {
		this.learningActivityResultLocalService = learningActivityResultLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setLearningActivityLocalService(LearningActivityLocalService learningActivityLocalService) {
		this.learningActivityLocalService = learningActivityLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setLearningActivityTryLocalService(LearningActivityTryLocalService learningActivityTryLocalService) {
		this.learningActivityTryLocalService = learningActivityTryLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setDLAppLocalService(DLAppLocalService dlAppLocalService) {
		this.dlAppLocalService = dlAppLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setDLFileEntryLocalService(DLFileEntryLocalService dlFileEntryLocalService) {
		this.dlFileEntryLocalService = dlFileEntryLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setResourcePermissionLocalService(ResourcePermissionLocalService resourcePermissionLocalService) {
		this.resourcePermissionLocalService = resourcePermissionLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setCourseLocalService(CourseLocalService courseLocalService) {
		this.courseLocalService = courseLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setModuleLocalService(ModuleLocalService moduleLocalService) {
		this.moduleLocalService = moduleLocalService;
	}
	
	private LearningActivityResultLocalService learningActivityResultLocalService;
	private LearningActivityLocalService learningActivityLocalService;
	private LearningActivityTryLocalService learningActivityTryLocalService;
	private DLAppLocalService dlAppLocalService;
	private DLFileEntryLocalService dlFileEntryLocalService;
	private ResourcePermissionLocalService resourcePermissionLocalService;
	private CourseLocalService courseLocalService;
	private ModuleLocalService moduleLocalService;
}