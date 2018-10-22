package com.ted.lms.learning.activity.p2p;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.ted.lms.learning.activity.p2p.exception.P2PActivityInProgressException;
import com.ted.lms.learning.activity.p2p.model.P2PActivity;
import com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections;
import com.ted.lms.learning.activity.p2p.service.P2PActivityCorrectionsLocalService;
import com.ted.lms.learning.activity.p2p.service.P2PActivityLocalService;
import com.ted.lms.learning.activity.p2p.web.constants.P2PConstants;
import com.ted.lms.learning.activity.p2p.web.util.P2PPrefsPropsValues;
import com.ted.lms.model.BaseLearningActivityType;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityTry;
import com.ted.lms.service.LearningActivityResultLocalService;

import java.util.Date;
import java.util.List;

import javax.portlet.ActionRequest;

public class P2PActivityType extends BaseLearningActivityType {
	
	private static final Log log = LogFactoryUtil.getLog(P2PActivityType.class);
	private P2PActivityLocalService p2pActivityLocalService;
	private P2PActivityCorrectionsLocalService p2pActivityCorrectionsLocalService;

	public P2PActivityType(LearningActivity activity, LearningActivityResultLocalService learningActivityResultLocalService,
			P2PActivityLocalService p2pActivityLocalService, P2PActivityCorrectionsLocalService p2pActivityCorrectionsLocalService) {
		super(activity, learningActivityResultLocalService);
		this.p2pActivityLocalService = p2pActivityLocalService;
		this.p2pActivityCorrectionsLocalService = p2pActivityCorrectionsLocalService;
	}

	@Override
	public String getClassName() {
		return P2PActivityType.class.getName();
	}
	
	@Override
	public void setExtraContent(ActionRequest actionRequest) throws PortalException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		JSONObject extraContent = activity.getExtraContentJSON();
		
		JSONObject p2pContent = extraContent.getJSONObject(P2PConstants.JSON_P2P);
		
		if(p2pActivityLocalService.hasP2PActivity(activity.getActId())){
			
			if(Validator.isNotNull(p2pContent)) {
				p2pContent = JSONFactoryUtil.createJSONObject();
				extraContent.put(P2PConstants.JSON_P2P, p2pContent);
			}
		

			p2pContent.put(P2PConstants.JSON_ASSIGNATION_TYPE, ParamUtil.getString(actionRequest, "assignationType", "course"));
			p2pContent.put(P2PConstants.JSON_ANONIMOUS, ParamUtil.getBoolean(actionRequest, "anonimous", false));
			p2pContent.put(P2PConstants.JSON_EMAIL_ANONIMOUS, ParamUtil.getBoolean(actionRequest, "email_anonimous", false));
			p2pContent.put(P2PConstants.JSON_ASK_FOR_P2P_ACTIVITIES, ParamUtil.getBoolean(actionRequest, "askforp2pactivities", false));
			p2pContent.put(P2PConstants.JSON_NUM_VALIDATIONS, ParamUtil.getInteger(actionRequest, "numValidations", P2PConstants.DEFAULT_VALIDATION_NUMBER));
			p2pContent.put(P2PConstants.JSON_RESULT, ParamUtil.getBoolean(actionRequest, "result", false));
			p2pContent.put(P2PConstants.JSON_FILE_OPTIONAL, ParamUtil.getBoolean(actionRequest, "fileoptional", false));
			
			int uploadMonth = ParamUtil.getInteger(actionRequest, "uploadMon");
			int uploadYear = ParamUtil.getInteger(actionRequest, "uploadYear");
			int uploadDay = ParamUtil.getInteger(actionRequest, "uploadDay");
			int uploadHour = ParamUtil.getInteger(actionRequest, "uploadHour");
			int uploadMinute = ParamUtil.getInteger(actionRequest, "uploadMin");
			int uploadAMPM = ParamUtil.getInteger(actionRequest, "uploadAMPM");
			
			if (uploadAMPM > 0) {
				uploadHour += 12;
			}
			
			Date uploadDate = PortalUtil.getDate(uploadMonth, uploadDay, uploadYear, uploadHour, uploadMinute, themeDisplay.getTimeZone(), (Class<? extends PortalException>)null);

			p2pContent.put(P2PConstants.JSON_DATE_UPLOAD, uploadDate);
			
			int numQuestion = P2PPrefsPropsValues.getNumEvaluationCriteria(themeDisplay.getCompanyId());
			
			//Vaciamos los criterios de evaluación anteriores
			p2pContent.remove(P2PConstants.JSON_EVALUATION_CRITERIA);
			JSONArray evaluationCriteria = JSONFactoryUtil.createJSONArray();
			p2pContent.put(P2PConstants.JSON_EVALUATION_CRITERIA, evaluationCriteria);
			
			for(int i=0;i<numQuestion;i++){
				String textAdd = ParamUtil.getString(actionRequest,"text"+i,null);
				
				if(Validator.isNotNull(textAdd)){
					evaluationCriteria.put(textAdd);
				}
			}
			
		}else{
			log.debug("***NO SE ACTUALIZA EL EXTRA CONTENT PORQUE YA HAY P2PACTIVITY ASOCIADOS A LA ACTIVIDAD***");
			throw new P2PActivityInProgressException();
		}
	
		activity.setExtraContent(extraContent.toJSONString());
	}
	
	
	@Override
	public double calculateResult(LearningActivityTry learningActivityTry) {
		double score = 0;
		
		JSONObject extraContent = activity.getExtraContentJSON();
		JSONObject p2pContent = extraContent.getJSONObject(P2PConstants.JSON_P2P);
		
		P2PActivity p2pActivity = p2pActivityLocalService.getP2PActivity(activity.getActId(), learningActivityTry.getUserId());
		
		if(Validator.isNotNull(p2pActivity)) {
			
			int numValidations = p2pContent.getInt(P2PConstants.JSON_NUM_VALIDATIONS);
			List<P2PActivityCorrections> listP2PActivityCorrections = p2pActivityCorrectionsLocalService.getCorrectionsDoneByUser(activity.getActId(), learningActivityTry.getUserId());
			
			if (p2pContent.getBoolean(P2PConstants.JSON_RESULT, false)) {
				
				//PARA EL USUARIO QUE CORRIGE			
				if(numValidations <= listP2PActivityCorrections.size()) {
					
					List<P2PActivityCorrections> listP2PActivityCorrectionsAboutMe = p2pActivityCorrectionsLocalService.getCorrectionsDoneByP2PActivityId(p2pActivity.getP2pActivityId());
					
					//Si ya ha corregido todas la tareas que debe correguir, le ponemos el 50% mas la media que ha recibido de sus correctores.
					if(numValidations <= listP2PActivityCorrectionsAboutMe.size()){
						
						double avg = 0;
						
						long numCorrected = 0;
						long totalResult = 0;
						for(P2PActivityCorrections correct: listP2PActivityCorrectionsAboutMe){
							totalResult = totalResult + correct.getResult();
						}
						
						//Obtenemos la media del resultado.
						avg = totalResult / numCorrected;
						
						score = 50 + (avg / 2);
					}
				}
			} 
			//Si en las p2p NO tenemos nota en la correccion.
			else {
				// Sólo se aprueba la actividad (sin nota) si estan todas las valoraciones asignadas corregidas. 
	
				//Si las que ha corregido son las que tiene que corregir.
				if(numValidations <= listP2PActivityCorrections.size()){
					score = 100;
				}
			}
		}
		
		return score;
	}
}
