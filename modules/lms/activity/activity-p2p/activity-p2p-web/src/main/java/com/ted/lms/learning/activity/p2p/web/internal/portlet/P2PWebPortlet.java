package com.ted.lms.learning.activity.p2p.web.internal.portlet;

import com.ted.lms.learning.activity.p2p.model.P2PActivity;
import com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections;
import com.ted.lms.learning.activity.p2p.service.P2PActivityCorrectionsLocalService;
import com.ted.lms.learning.activity.p2p.service.P2PActivityCorrectionsLocalServiceUtil;
import com.ted.lms.learning.activity.p2p.service.P2PActivityLocalService;
import com.ted.lms.learning.activity.p2p.service.P2PActivityLocalServiceUtil;
import com.ted.lms.learning.activity.p2p.web.activity.P2PActivityType;
import com.ted.lms.learning.activity.p2p.web.activity.P2PActivityTypeFactory;
import com.ted.lms.learning.activity.p2p.web.constants.P2PWebPortletKeys;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.service.LearningActivityLocalService;
import com.ted.lms.service.LearningActivityLocalServiceUtil;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.ProcessAction;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Virginia Martín Agudo
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=hidden",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.mvc-command-names-default-views=/activities/p2p/view_activity",
		"javax.portlet.name=" + P2PWebPortletKeys.P2P,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=user",
		"javax.portlet.supported-public-render-parameter=actId"
	},
	service = Portlet.class
)
public class P2PWebPortlet extends MVCPortlet {
	
	@ProcessAction(name = "addP2PActivity")
	public void addP2PActivity(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception{
		log.debug("addActivity");
		try{
			UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
			
			ServiceContext serviceContext = null;
			try {
				serviceContext = ServiceContextFactory.getInstance(actionRequest);
			} catch (PortalException e1) {
				e1.printStackTrace();
			} 	
			
			Map<String, String[]> parameters = uploadRequest.getParameterMap();
			parameters.forEach((k,v)->System.out.println("name : " + k + " value : " + v));
			
			String description = ParamUtil.getString(uploadRequest, "description");
			long actId = ParamUtil.getLong(actionRequest, "actId");
			long p2pActivityId = ParamUtil.getLong(actionRequest, "p2pActivityId");
			
			String fileName = uploadRequest.getFileName("fileName");
			
			File file = uploadRequest.getFile("fileName");
			String mimeType = uploadRequest.getContentType("fileName");
			
			if(log.isDebugEnabled()){
				log.debug(description);
				log.debug(actId);
				log.debug(p2pActivityId);
				log.debug(fileName);
				log.debug(mimeType);
			}
						
			//Controlamos que no se duplica la P2pActivity subida por el usuario.
			//Solo puede subir una P2pActivity por Activity.
			P2PActivity p2pActivity = P2PActivityLocalServiceUtil.getP2PActivity(actId, themeDisplay.getUserId());
			
			LearningActivity activity = LearningActivityLocalServiceUtil.getLearningActivity(actId);

			P2PActivityTypeFactory p2pActivityTypeFactory = new P2PActivityTypeFactory();
			P2PActivityType p2pActivityType = p2pActivityTypeFactory.getP2PActivityType(activity);
			
			//Obtener si es obligatorio el fichero en las p2p.
			boolean fileoptional = p2pActivityType.getFileOptional();
			
			if(Validator.isNull(p2pActivity)){

				//Cuando el fichero es obligatorio y no esta seleccionado.
				if(!fileoptional && Validator.isNull(fileName)){
					SessionErrors.add(actionRequest, "campos-necesarios-vacios");
				} else if(Validator.isNotNull(description)){
					
					p2pActivity = P2PActivityLocalServiceUtil.addP2PActivity(themeDisplay.getUserId(), actId, 
							description, fileName, file, mimeType, serviceContext, themeDisplay);
					
					actionResponse.setRenderParameter("uploadCorrect", "true");
					
				}else {
					SessionErrors.add(actionRequest, "learning-activity.p2p.steps.upload-activity.empty-fields");
				}
			}
			
			actionResponse.setRenderParameter("mvcRenderCommandName","/activities/p2p/view_activity");
			actionResponse.setRenderParameter("actId", String.valueOf(actId));
		}
		catch(Exception e){
			if (log.isErrorEnabled()) {
				log.error("Error P2pActivityPortlet.addP2PActivity");
				log.error(e.getMessage());
			}
			e.printStackTrace();
			SessionErrors.add(actionRequest, "error-subir-p2p");
		}
	}
		
	/**
	 * Método para cuando se corrige una p2p
	 * Se debe tener en cuenta a la hora de actualizar el LearningActivityResult los siguientes casos:
	 * - Si el alumno no ha corregido todas las tareas p2p que debe corregir no se pone fecha fin, ya que no ha terminado las correcciones
	 * - Si el alumno ha corregido todas las tareas p2p pero la actividad va con nota, no se le pone fecha fin hasta que:
	 *    - Le hayan valorado todos los alumnos su p2p
	 *    - Haya obtenido la nota necesaria para aprobar la actividad
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@ProcessAction(name = "saveCorrection")
	public void saveCorrection(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException {
		
		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);		
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);		
		User user = themeDisplay.getUser();
				
		long p2pActivityId = ParamUtil.getLong(actionRequest, "p2pActivityId");
		
		//Ya debe existir una correcion en la bd para este usuario y para esta P2pActivity.
		P2PActivityCorrections p2pActivityCorrections = P2PActivityCorrectionsLocalServiceUtil.getCorrectionsByP2PActivityIdUserId(p2pActivityId, user.getUserId());
		
		long actId = ParamUtil.getLong(uploadRequest, "actId");
		
		if(p2pActivityCorrections!=null){
			
			int numCorrection = ParamUtil.getInteger(actionRequest, "cont");
			
			LearningActivity activity=LearningActivityLocalServiceUtil.getLearningActivity(actId);	

			P2PActivityTypeFactory p2pActivityTypeFactory = new P2PActivityTypeFactory();
			P2PActivityType p2pActivityType = p2pActivityTypeFactory.getP2PActivityType(activity);
						
			String description = null;
			
			boolean fill = false;
			JSONArray jArray = JSONFactoryUtil.createJSONArray();
			for(int i = 0; i < p2pActivityType.getEvaluationCriteria().length(); i++) {
				String desc = ParamUtil.getString(actionRequest, "description_" + numCorrection + "_" + i);
				System.out.println("desc: " + desc);
				if(Validator.isNotNull(desc)){
					fill=true;
					JSONObject jo = JSONFactoryUtil.createJSONObject();
					jo.put("text"+i, desc);
					jArray.put(jo);
				}
			}

			if(fill){
				log.debug(jArray.toString());
				description = jArray.toString();
			}
			 		
			if(Validator.isNotNull(description)){ 		
				String fileName = uploadRequest.getFileName("fileName");
				File file = uploadRequest.getFile("fileName");
				String mimeType = uploadRequest.getContentType("fileName");
				long resultuser = ParamUtil.getLong(uploadRequest, "resultUser", 0);
				
				ServiceContext serviceContext = null;
				try {
					serviceContext = ServiceContextFactory.getInstance(actionRequest);
				} catch (PortalException e1) {
					e1.printStackTrace();
				}
				try {
					
					p2pActivityCorrections = P2PActivityCorrectionsLocalServiceUtil.updateP2PActivityCorrections(p2pActivityCorrections, description, fileName, file, mimeType, resultuser, serviceContext);
					
				
					log.debug("p2pActivityCorrections: " + p2pActivityCorrections.getDate());
					
					log.debug("count" + P2PActivityCorrectionsLocalServiceUtil.getCorrectionsDoneByUser(actId, user.getUserId()));
					log.debug("are: " + P2PActivityCorrectionsLocalServiceUtil.areAllCorrectionsDoneByUserInP2PActivity(actId, p2pActivityCorrections.getUserId(), p2pActivityType.getNumValidations()));
					P2PActivityCorrections p2pAux = P2PActivityCorrectionsLocalServiceUtil.getP2PActivityCorrections(p2pActivityCorrections.getP2pActivityCorrectionsId());
					log.debug("date: " + p2pAux.getDate());
					
					P2PActivity p2pActivity = P2PActivityLocalServiceUtil.getP2PActivity(p2pActivityId);
					log.debug("count" + P2PActivityCorrectionsLocalServiceUtil.getCorrectionsDoneByUser(actId, user.getUserId()));
					//Actualizar los resultados de las correcciones de las P2P. 
					P2PActivityLocalServiceUtil.updateResultP2PActivity(p2pActivity, p2pActivityCorrections, user.getUserId(), p2pActivityType.getNumValidations(), p2pActivityType.getResult(),
							p2pActivityType.getAnonimous(), p2pActivityType.getEmailAnonimous(), p2pActivityType.getEvaluationCriteria(), themeDisplay, 
							serviceContext);
					log.debug("count" + P2PActivityCorrectionsLocalServiceUtil.getCorrectionsDoneByUser(actId, user.getUserId()).size());
					actionRequest.setAttribute("actId", actId);
		
					//Para mostrar el mensaje de que ya ha corregido todas las actividades
					List<P2PActivityCorrections> corrections = P2PActivityCorrectionsLocalServiceUtil.getCorrectionsDoneByUser(actId, user.getUserId());
					log.debug("corrections: " + corrections.size());
					log.debug("validations: " + p2pActivityType.getNumValidations());
					
					if(corrections.size() >= p2pActivityType.getNumValidations()){
						actionResponse.setRenderParameter("correctionsCompleted", "true");
					}
					
					actionResponse.setRenderParameter("correctionSaved", "true");
					actionResponse.setRenderParameter("mvcPath","/p2p/activity/view.jsp");
				} catch(Exception e){
					if (log.isErrorEnabled()) {
						log.error("Error getting P2pActivityPortlet.saveCorrection");
						log.error(e);
					}
					SessionErrors.add(actionRequest, "error-p2ptask-correction");
					actionRequest.setAttribute("actId", actId);
				}	
				
			}else{
				SessionErrors.add(actionRequest, "p2ptask-no-empty-answer");
			}
			
		}
	}
	
	public void askForP2PActivities(ActionRequest request, ActionResponse response) throws PortalException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);	
		long actId = ParamUtil.getLong(request, "actId");
		P2PActivity p2pActivity = P2PActivityLocalServiceUtil.getP2PActivity(actId, themeDisplay.getUserId());
		int activityAsignations =P2PActivityCorrectionsLocalServiceUtil.getCorrectionsCount(p2pActivity.getActId(),p2pActivity.getUserId());
    
		LearningActivity activity = LearningActivityLocalServiceUtil.getLearningActivity(actId);

		P2PActivityTypeFactory p2pActivityTypeFactory = new P2PActivityTypeFactory();
		P2PActivityType p2pActivityType = p2pActivityTypeFactory.getP2PActivityType(activity);
		
		System.out.println("p2pActivity: " + p2pActivity.getP2pActivityId());
		System.out.println("numValidations: " + p2pActivityType.getNumValidations());
		System.out.println("assignationType: " + p2pActivityType.getAssignationType());
		
		P2PActivityLocalServiceUtil.asignCorrectionP2PActivity(p2pActivity, p2pActivityType.getNumValidations(), p2pActivityType.getAssignationType());
		if(p2pActivity.getAsignationsCompleted()){
			SessionMessages.add(request, "p2p-activity-assign-correct");
		}else{
			int activityAsignationsAfter =P2PActivityCorrectionsLocalServiceUtil.getCorrectionsCount(p2pActivity.getActId(),p2pActivity.getUserId());
			if(activityAsignations < activityAsignationsAfter){
				SessionMessages.add(request, "p2p-activity-assign-correct");
			}else{
				SessionErrors.add(request, "no-p2p-activity-assign");
			}
        }
		
		request.setAttribute("actId", actId);
		response.setRenderParameter("jspPage","/p2p/activity/view.jsp");
	}
	
	private static Log log = LogFactoryUtil.getLog(P2PWebPortlet.class);
}