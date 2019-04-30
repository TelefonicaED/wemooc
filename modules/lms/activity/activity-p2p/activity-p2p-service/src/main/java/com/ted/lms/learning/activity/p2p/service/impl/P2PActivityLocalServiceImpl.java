/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.ted.lms.learning.activity.p2p.service.impl;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Team;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.TeamLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.Validator;
import com.ted.audit.api.AuditFactory;
import com.ted.lms.constants.LMSAuditConstants;
import com.ted.lms.learning.activity.p2p.exception.P2PFileEntryExtensionException;
import com.ted.lms.learning.activity.p2p.exception.P2PFileEntrySizeException;
import com.ted.lms.learning.activity.p2p.model.P2PActivity;
import com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections;
import com.ted.lms.learning.activity.p2p.service.base.P2PActivityLocalServiceBaseImpl;
import com.ted.lms.learning.activity.p2p.util.P2PPrefsPropsValues;
import com.ted.lms.model.Course;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityTry;
import com.ted.lms.model.Module;
import com.ted.lms.service.CourseLocalServiceUtil;
import com.ted.lms.service.LearningActivityLocalServiceUtil;
import com.ted.lms.service.LearningActivityTryLocalServiceUtil;
import com.ted.lms.service.ModuleLocalServiceUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.internet.InternetAddress;

/**
 * The implementation of the p2p activity local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ted.lms.learning.activity.p2p.service.P2PActivityLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see P2PActivityLocalServiceBaseImpl
 * @see com.ted.lms.learning.activity.p2p.service.P2PActivityLocalServiceUtil
 */
public class P2PActivityLocalServiceImpl extends P2PActivityLocalServiceBaseImpl {
	
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.ted.lms.learning.activity.p2p.service.P2PActivityLocalServiceUtil} to access the p2p activity local service.
	 */
	
	@Override
	public boolean hasP2PActivity(long actId) {
		log.debug("actId: " + actId);
		log.debug("count: " + p2pActivityPersistence.countByActId(actId));
		return p2pActivityPersistence.countByActId(actId) > 0;
	}
	
	@Override
	public P2PActivity getP2PActivity(long actId, long userId) {
		return p2pActivityPersistence.fetchByActIdUserId(actId, userId);
	}
	
	@Override
	public List<P2PActivity> getP2PActivitiesByAssignationsCompleted(boolean assignationsCompleted){
		return p2pActivityPersistence.findByAsignationsCompleted(assignationsCompleted);
	}
	
	@Override
	public boolean hasP2PActivity(long actId, long userId) {
		return p2pActivityPersistence.countByActIdUserId(actId, userId) > 0;
	}
	
	@Override
	public P2PActivity addP2PActivity(long userId, long actId, String description,
			ServiceContext serviceContext, ThemeDisplay themeDisplay) throws PortalException {
		try {
			return addP2PActivity(userId, actId, description, null, null, null, serviceContext, themeDisplay);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public P2PActivity addP2PActivity(long userId, long actId, String description,
			String fileName, File file, String mimeType, ServiceContext serviceContext, ThemeDisplay themeDisplay) 
					throws PortalException, IOException {
		
		User user = userLocalService.getUser(serviceContext.getUserId());
		
		P2PActivity p2pActivity = p2pActivityPersistence.create(counterLocalService.increment(P2PActivity.class.getName()));
		
		p2pActivity.setUuid(serviceContext.getUuid());
		p2pActivity.setGroupId(serviceContext.getScopeGroupId());
		p2pActivity.setCompanyId(user.getCompanyId());
		p2pActivity.setUserCreateId(user.getUserId());
		p2pActivity.setUserCreateName(user.getFullName());
		Date now = new Date();
		p2pActivity.setCreateDate(serviceContext.getModifiedDate(now));
		p2pActivity.setModifiedDate(serviceContext.getModifiedDate(now));
		
		p2pActivity.setActId(actId);
		p2pActivity.setUserId(userId);
		p2pActivity.setCountCorrections(0);
		p2pActivity.setDescription(description);
		p2pActivity.setDate(now);
		p2pActivity.setAsignationsCompleted(false);
		
		//Subimos el fichero
		if(Validator.isNotNull(fileName)) {
			try {
				Folder folder = addP2PFolder(userId, serviceContext.getScopeGroupId());
				DLFileEntry p2pFileEntry = addP2PFileEntry(fileName, file, mimeType, folder.getFolderId(), serviceContext.getScopeGroupId(),
						serviceContext.getCompanyId(), serviceContext.getUserId());
				//Asociamos con el fichero subido.
				p2pActivity.setFileEntryId(p2pFileEntry.getFileEntryId());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
			
		p2pActivity = p2pActivityPersistence.update(p2pActivity);

		
		//Creamos el LearningActivityTry
		LearningActivityTryLocalServiceUtil.addLearningActivityTry(actId, user.getUserId(), serviceContext);

		//Enviar por email que se ha entregado una tarea p2p.
		sendMailP2PDone(user, actId, themeDisplay);
		
		return p2pActivity;
	}
	
	/*
	 * Email para el caso 1, avisando que ha entregado su tarea.
	 * Asunto: 1. Confirmacion de entrega de tarea p2p
	 * 
	 * */
	private void sendMailP2PDone(User user, long actId, ThemeDisplay themeDisplay) throws PortalException, UnsupportedEncodingException{
			LearningActivity activity = LearningActivityLocalServiceUtil.getLearningActivity(actId);
			Course course= CourseLocalServiceUtil.getCourseByGroupCreatedId(activity.getGroupId());
			Module module = ModuleLocalServiceUtil.getModule(activity.getModuleId());
			
			String courseFriendlyUrl = "";
			String courseTitle = "";
			String activityTitle = activity.getTitle(user.getLocale());
			String moduleTitle =  module.getTitle(user.getLocale());
			
			long modulesOfCourse = ModuleLocalServiceUtil.getModulesCount(course.getGroupCreatedId());
			
			courseTitle = course.getTitle(user.getLocale());
			courseFriendlyUrl = activity.getURLView(themeDisplay);
			log.debug("URL "+ courseFriendlyUrl);

			String messageArgs[]= {activityTitle, moduleTitle, courseTitle, courseFriendlyUrl};
			
			String titleArgs[]= {String.valueOf(user.getFullName())};
			
			//Nuevos campos del email
			//Subject
			
			String subject = LanguageUtil.get(user.getLocale(), "p2ptaskactivity.mail.sendactivity.mail.subject"); 
			String title = LanguageUtil.format(user.getLocale(), "p2ptaskactivity.mail.sendactivity.mail.title", titleArgs);
			String body = title +"<br /><br />"+ LanguageUtil.format(user.getLocale(), "p2ptaskactivity.mail.sendactivity.mail.message", messageArgs);
			if(modulesOfCourse<=1){
				body = title +"<br /><br />"+ LanguageUtil.format(user.getLocale(), "p2ptaskactivity.mail.sendactivity.mail.message-simple", new String[]{activityTitle, courseTitle, courseFriendlyUrl});
			}
			
			String firmaPortal  = P2PPrefsPropsValues.getP2PEmailSignature(course.getCompanyId());

			if(log.isDebugEnabled()){log.debug("P2PActivityPortlet::sendMailNoCorrection::subject:"+subject);}
			if(log.isDebugEnabled()){log.debug("P2PActivityPortlet::sendMailNoCorrection::body:"+body);}
			
			String fromName = P2PPrefsPropsValues.getP2PEmailFromName(course.getCompanyId());
			String fromAddress = P2PPrefsPropsValues.getP2PEmailFromAddress(course.getCompanyId());

			InternetAddress from = new InternetAddress(fromAddress, fromName);
			InternetAddress to = new InternetAddress(user.getEmailAddress(), user.getFullName());
			MailMessage mailMessage = new MailMessage(from, to, subject, body, true);
			
			MailServiceUtil.sendEmail(mailMessage);		
			if(log.isDebugEnabled())log.debug("P2PActivityPortlet::sendMailNoCorrection::Mail Enviado");
	}
	
	/**
	 * Se actualizan los tries y los results de los usuarios implicados
	 * @param p2pActivityId
	 * @param userId
	 * @throws PortalException 
	 * @throws UnsupportedEncodingException 
	 * @throws Exception
	 */
	public void updateResultP2PActivity(P2PActivity p2pActivityCorrected, P2PActivityCorrections p2pActivityCorrection, long userId, int numValidations, boolean result,
			boolean anonimous, boolean emailAnonimous, JSONArray evaluationCriteria, ThemeDisplay themeDisplay, 
			ServiceContext serviceContext) throws PortalException, UnsupportedEncodingException {
		
		//El valor que añadiremos al LearningActivityResult
		long newValueResult = 0;
		boolean correctionCompleted = false;
		boolean correctionCompletedAboutMe = false;
		
		//La p2p del que realiza la corrección.
		P2PActivity p2pActivityFromCorrectorUser = p2pActivityLocalService.getP2PActivity(p2pActivityCorrected.getActId(), userId);
		
		//La actividad será la misma para las dos actividades.
		long actId = p2pActivityCorrected.getActId();
		
		LearningActivity activity = LearningActivityLocalServiceUtil.getLearningActivity(actId);
		
		//Log para ver la traza de ejecución.
		if(log.isDebugEnabled()){
			log.debug("----------------------");
			log.debug(" PARA EL USUARIO QUE CORRIGE");
			log.debug(" p2pActivityId: "+p2pActivityFromCorrectorUser.getP2pActivityId()+", actId: "+p2pActivityFromCorrectorUser.getActId()+", userId: "+p2pActivityFromCorrectorUser.getUserId());
			log.debug(" correctionCompleted: "+p2pActivityCorrectionsLocalService.areAllCorrectionsDoneByUserInP2PActivity(actId, p2pActivityFromCorrectorUser.getUserId(), numValidations)+", avg: "+p2pActivityCorrectionsLocalService.getAVGCorrectionsResults(p2pActivityFromCorrectorUser.getP2pActivityId()));
			log.debug(" PARA EL USUARIO QUE ES CORREGIDO");
			log.debug(" p2pActivityId: "+p2pActivityCorrected.getP2pActivityId()+", actId: "+p2pActivityCorrected.getActId()+", userId: "+p2pActivityCorrected.getUserId());
			log.debug(" actId: " + actId);
			log.debug(" userId: " + p2pActivityCorrected.getUserId());
			log.debug(" numValidations: " + numValidations);
			log.debug(" correctionCompleted: "+p2pActivityCorrectionsLocalService.areAllCorrectionsDoneByUserInP2PActivity(actId, p2pActivityCorrected.getUserId(), numValidations)+", avg: "+p2pActivityCorrectionsLocalService.getAVGCorrectionsResults(p2pActivityCorrected.getP2pActivityId()));
			log.debug("----------------------");
		}
		
		//Si en las p2p tenemos nota en la correccion.
		if (result) {

			//PARA EL USUARIO QUE CORRIGE
			correctionCompleted = p2pActivityCorrectionsLocalService.areAllCorrectionsDoneByUserInP2PActivity(actId, p2pActivityFromCorrectorUser.getUserId(), numValidations);
			correctionCompletedAboutMe = p2pActivityCorrectionsLocalService.hasAllCorrectionsDoneAboutUserInP2PActivity(actId, p2pActivityFromCorrectorUser.getP2pActivityId(), numValidations);
			
			log.debug("correctionCompleted: " + correctionCompleted);
			log.debug("correctionCompletedAboutMe: " + correctionCompletedAboutMe);
			
			//Si ya ha corregido todas la tareas que debe correguir, le ponemos el 50% mas la media que ha recibido de sus correctores.
			if(correctionCompleted){
				long avg = p2pActivityCorrectionsLocalService.getAVGCorrectionsResults(p2pActivityFromCorrectorUser.getP2pActivityId());
				log.debug("avg: " + avg);
				newValueResult = 50 + (avg / 2);
			}
			//Si no ha corregido todas, su nota sera 0.
			else{
				newValueResult = 0;
			}
			log.debug("newValueResult: " + newValueResult);
			//Guardamos el resultado
			saveLearningActivityResult(actId, p2pActivityFromCorrectorUser.getUserId(), newValueResult, correctionCompleted, result, activity, 
					correctionCompletedAboutMe, serviceContext);
			
			//PARA EL USUARIO QUE ES CORREGIDO
			correctionCompleted = p2pActivityCorrectionsLocalService.areAllCorrectionsDoneByUserInP2PActivity(actId, p2pActivityCorrected.getUserId(), numValidations);
			correctionCompletedAboutMe = p2pActivityCorrectionsLocalService.hasAllCorrectionsDoneAboutUserInP2PActivity(actId, p2pActivityCorrected.getP2pActivityId(), numValidations);
			//Si ya ha corregido todas la tareas que debe correguir, le ponemos el 50% mas la media que ha recibido de sus correctores.
			if(correctionCompleted){
				long avg = p2pActivityCorrectionsLocalService.getAVGCorrectionsResults(p2pActivityCorrected.getP2pActivityId());
				newValueResult = 50 + (avg / 2);
			}
			//Si no ha corregido todas, su nota sera 0.
			else{
				newValueResult = 0;
			}
			//Guardamos el resultado
			saveLearningActivityResult(actId, p2pActivityCorrected.getUserId(), newValueResult, correctionCompleted, result, activity, 
					correctionCompletedAboutMe, serviceContext);
			
		} 
		//Si en las p2p NO tenemos nota en la correccion.
		else {
			// Sólo se aprueba la actividad (sin nota) si estan todas las valoraciones asignadas corregidas. 
			correctionCompleted = p2pActivityCorrectionsLocalService.areAllCorrectionsDoneByUserInP2PActivity(actId, p2pActivityFromCorrectorUser.getUserId(), numValidations);
			
			//Si las que ha corregido son las que tiene que corregir.
			if(correctionCompleted){
				newValueResult = 100;
			}
			//Si no ha corregido todas, tiene valor 0.
			else{
				newValueResult = 0;
			}
			//Guardamos el resultado
			correctionCompletedAboutMe = p2pActivityCorrectionsLocalService.hasAllCorrectionsDoneAboutUserInP2PActivity(actId, p2pActivityCorrected.getP2pActivityId(), numValidations);
			saveLearningActivityResult(actId, p2pActivityFromCorrectorUser.getUserId(), newValueResult, correctionCompleted, result, activity, 
					correctionCompletedAboutMe, serviceContext);
		}
		
		User userPropietaryP2pAct = UserLocalServiceUtil.getUser(p2pActivityCorrected.getUserId());

		sendMailCorrection(userPropietaryP2pAct, actId, p2pActivityCorrection, anonimous, emailAnonimous, result, evaluationCriteria, themeDisplay);
	}
	
	private static void sendMailCorrection(User user, long actId, P2PActivityCorrections p2pActiCor, boolean anonimous, boolean emailAnonimous,
			boolean result, JSONArray evaluationCriteria, ThemeDisplay themeDisplay) throws PortalException, UnsupportedEncodingException{

		LearningActivity activity = LearningActivityLocalServiceUtil.getLearningActivity(actId);
					
		Course course= CourseLocalServiceUtil.getCourseByGroupCreatedId(activity.getGroupId());
		
		Module module = ModuleLocalServiceUtil.getModule(activity.getModuleId());
		String courseFriendlyUrl = "";
		String courseTitle = "";
		String activityTitle = activity.getTitle(user.getLocale());
		String moduleTitle =  module.getTitle(user.getLocale());
		
		long modulesOfCourse = 0;				
		if(course != null){
			courseTitle = course.getTitle(user.getLocale());
			courseFriendlyUrl = activity.getURLView(themeDisplay);
			log.debug("URL "+ courseFriendlyUrl);
			modulesOfCourse = ModuleLocalServiceUtil.getModulesCount(course.getGroupCreatedId());
		}
						
		String messageArgs[]= {activityTitle, moduleTitle, courseTitle, courseFriendlyUrl};
		String resultArgs[]= {String.valueOf(p2pActiCor.getResult())};
		String titleArgs[]= {String.valueOf(user.getFullName())};
		User u = UserLocalServiceUtil.getUserById(p2pActiCor.getUserId());
		String userArgs[]= {String.valueOf(u.getFullName())};
		
		//Nuevos campos del email
		//Subject
		String subject = LanguageUtil.get(user.getLocale(), "p2ptaskactivity.mail.valoration.recieved.subject"); 
		
		//Body
		String title  			 = LanguageUtil.format(user.getLocale(), "p2ptaskactivity.mail.valoration.recieved.body.title",   titleArgs); 
		String message  		 = LanguageUtil.format(user.getLocale(), "p2ptaskactivity.mail.valoration.recieved.body.message", messageArgs);
		if(modulesOfCourse<=1){
			message  		 = LanguageUtil.format(user.getLocale(), "p2ptaskactivity.mail.valoration.recieved.body.message-simple", new String[]{activityTitle, courseTitle, courseFriendlyUrl});
		}
		String usercorrection    = LanguageUtil.format(user.getLocale(), "p2ptaskactivity.mail.valoration.recieved.body.usercorrection", userArgs); 
		String resultcorrection  = LanguageUtil.format(user.getLocale(), "p2ptaskactivity.mail.valoration.recieved.body.result",  resultArgs); 			
		String end  			 = LanguageUtil.get(user.getLocale(), 	"p2ptaskactivity.mail.valoration.recieved.body.end"); 
		
		//Componer el body seg�n la actividad.
		String body = title;
		
		if(message!=null){
			body += "<br /><br />" + message;	
		}
		
		if(!emailAnonimous){
			
			log.debug("*********Email no anonimo********");
				
			if(!anonimous && usercorrection!=null){
				body += "<br /><br />" + usercorrection;
			}
			
			//Comentarios realizados por el usuario que ha corregido la actividad.
			if(p2pActiCor!= null && p2pActiCor.getDescription()!=null){
				JSONObject object=null;
				try {
					JSONArray jArray = JSONFactoryUtil.createJSONArray(p2pActiCor.getDescription());
					for(int i=0;i<evaluationCriteria.length();i++){
						String des = evaluationCriteria.getString(i);
						if(Validator.isNotNull(des)){
							body+= "<br />" +des;
							object=jArray.getJSONObject(i);
							body+= "<br />" +object.getString("text"+i, "");
						}
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			if(result && resultcorrection!=null){
				body += "<br /><br />" + resultcorrection;	
			}
		
		}else{
			log.debug("*********Email Anonimo. No se muestran las correcciones ********");
		}
		
		String fileId = String.valueOf(p2pActiCor.getFileEntryId());
		if(fileId.length() == 1 && fileId.equals("0")){
			body += "<br /><br />" + LanguageUtil.get(user.getLocale(), 	"p2ptaskactivity.mail.valoration.recieved.body.file.no"); 
		} else {
			body += "<br /><br />" + LanguageUtil.get(user.getLocale(), 	"p2ptaskactivity.mail.valoration.recieved.body.file.yes");
		}
		
		body += "<br /><br />" + end;	
		
		
		if(log.isDebugEnabled()){log.debug("P2PActivityPortlet::sendMailCorrection::subject:"+subject);}
		if(log.isDebugEnabled()){log.debug("P2PActivityPortlet::sendMailCorrection::body:"+body);}
		
		String fromName = P2PPrefsPropsValues.getP2PEmailFromName(course.getCompanyId());
		String fromAddress = P2PPrefsPropsValues.getP2PEmailFromAddress(course.getCompanyId());
		
		InternetAddress from = new InternetAddress(fromAddress, fromName);
		InternetAddress to = new InternetAddress(user.getEmailAddress(), user.getFullName());
		MailMessage mailMessage = new MailMessage(from, to, subject, body, true);
		MailServiceUtil.sendEmail(mailMessage);	
		
		if(log.isDebugEnabled()){log.debug("P2PActivityPortlet::sendMailCorrection::Mail Enviado");}
	}
	
	public void asignCorrectionP2PActivity(P2PActivity p2pActivity, int numValidations, String assignationType) {
		
		long actId = p2pActivity.getActId();
		int activityAsignations = 0;	
		
		//Obtenemos las asignaciones que ya están realidas.
		try {
			activityAsignations = p2pActivityCorrectionsLocalService.getCorrectionsCount(p2pActivity.getActId(),p2pActivity.getUserId());
			log.debug("asignaciones ya asginadas: " + activityAsignations);
		
			//Si la actividad no tiene asignadas todas las tareas que tiene que corregir.
			if( activityAsignations < numValidations && !p2pActivity.isAsignationsCompleted()){
				
				List<P2PActivity> activitiesToAsign = getP2PActivitiesToCorrect(actId, 
						p2pActivity, numValidations - activityAsignations, assignationType);

				if(log.isDebugEnabled()){
					log.debug("P2P assign corrections to activity::"+p2pActivity.getActId()+"::"+p2pActivity.getUserId());
					for(P2PActivity p2pactivity : activitiesToAsign){
						log.debug("assign::"+p2pactivity.getUserId());
					}
				}
				
				//Asignar al usuario que entrega sus correctores.
				p2pActivityCorrectionsLocalService.asignCorrectionsToP2PActivities(actId, p2pActivity.getP2pActivityId(), 
						numValidations - activityAsignations, activitiesToAsign, p2pActivity.getUserId());

			}else{
				p2pActivity.setAsignationsCompleted(true);
				p2pActivityLocalService.updateP2PActivity(p2pActivity);
			}
		} catch (PortalException e) {
			e.printStackTrace();
		}
	}
	
	public List<P2PActivity> getP2PActivitiesToCorrect(long actId, P2PActivity p2pActivity, int numValidaciones, String assignationType) throws PortalException{
		List<P2PActivity> res = new ArrayList<P2PActivity>();
		
		LearningActivity la = LearningActivityLocalServiceUtil.getLearningActivity(actId);
		
		List<P2PActivity> activities = null;
		if(p2pActivity!=null && la!=null){
			if("team".equals(assignationType)){
				List<Team> groupTeams = TeamLocalServiceUtil.getGroupTeams(la.getGroupId());
				
				//Si hay equipos, realizamos las asignaciones por equipos. Si no hay equipos, por grupo.
				if(groupTeams!=null && groupTeams.size()>0){
					List<Team> userTeams = TeamLocalServiceUtil.getUserTeams(p2pActivity.getUserId(), la.getGroupId());
					if(userTeams!=null && userTeams.size()>0){
						//Caso de asignacion por equipos.
						log.debug("******* USER ID "+p2pActivity.getUserId()  + " -->  ASIGNACIÓN POR EQUIPOS ");
						activities =p2pActivityFinder.findByTeam(actId, p2pActivity.getP2pActivityId(), userTeams, 0, numValidaciones);
						
					}else{
						//Caso de asignación suelta entre miembros sueltos del curso (sin equipos)
						log.debug("******* USER ID "+p2pActivity.getUserId()  + " -->  ASIGNACIÓN POR USUARIOS SUELTOS ");
						activities = p2pActivityFinder.findByUserWithoutTeamActivities(actId, p2pActivity.getP2pActivityId(), la.getGroupId(), 0, numValidaciones);
					}
				}else{
					log.debug("******* USER ID "+p2pActivity.getUserId()  + " -->  ASIGNACIÓN POR GRUPO (CURSO SIN EQUIPOS)");
					activities = p2pActivityFinder.findByGroup(actId, p2pActivity.getP2pActivityId(), 0, numValidaciones);
				}
			}else{
				log.debug("******* USER ID "+p2pActivity.getUserId()  + " -->  ASIGNACIÓN POR GRUPO ");
				activities = p2pActivityFinder.findByGroup(actId, p2pActivity.getP2pActivityId(), 0, numValidaciones);
			}
		}
			
		
		//Si no es null ni esta vacia, la asignamos para devolver, sino devolveremos vacia.
		if(activities!=null && !activities.isEmpty()){
			//auditing
			ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();
			if(serviceContext!=null){
				User user = userLocalService.fetchUser(serviceContext.getUserId());
				AuditFactory.audit(serviceContext.getCompanyId(), serviceContext.getScopeGroupId(), LMSAuditConstants.GET, 
						P2PActivity.class.getName(), p2pActivity.getP2pActivityId(), serviceContext.getUserId(), 
						user != null ? user.getFullName() : "", null);
			}else if(la!=null){
				User user = userLocalService.fetchUser(la.getUserId());
				AuditFactory.audit(la.getCompanyId(), la.getGroupId(), LMSAuditConstants.GET, 
						P2PActivity.class.getName(), p2pActivity.getP2pActivityId(), la.getUserId(), 
						user != null ? user.getFullName() : "", null);
			}
			
			res = activities;
		}
				
		return res;
	}
	
	private static void saveLearningActivityResult(long actId, long userId, long value, boolean correctionCompleted, boolean result, 
			LearningActivity activity, boolean correctionCompletedAboutMe, ServiceContext serviceContext) throws PortalException{
		
		log.info("*****actId: " + actId);
		log.info("***userId: " + userId);
		log.info("value: " + value);
		log.info("correctionCompleted: " + correctionCompleted);
		log.info("result: " + result);
		log.info("correctionCompletedAboutMe: " + correctionCompletedAboutMe);
	
		//Actualizamos el try, que a su vez actualiza el result
		LearningActivityTry  learningActivityTry =  LearningActivityTryLocalServiceUtil.getLastLearningActivityTry(actId, userId);
		log.debug("*****learningActivityTry: " + (learningActivityTry != null ? learningActivityTry.getLatId() : "null"));
		
		if(learningActivityTry != null && correctionCompleted && ((!result) || (value >= activity.getPassPuntuation()) || correctionCompletedAboutMe)){
			log.info("Modifico la fecha de fin del try");
			
			learningActivityTry = LearningActivityTryLocalServiceUtil.finishLearningActivityTry(learningActivityTry, value, serviceContext);
		}else {
			//Actualizamos sólo la nota
			learningActivityTry = LearningActivityTryLocalServiceUtil.updateLearningActivityTry(learningActivityTry, value, serviceContext);
		}
	}
	
	public Folder addP2PFolder(long userId, long groupId) throws PortalException {

		return doAddFolder(userId, groupId, getP2PFolderName(userId));
	}
	
	protected String getP2PFolderName(long userId) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    	Date date = new Date();
    	return dateFormat.format(date) + StringPool.UNDERLINE + userId;
	}
	
	protected Folder doAddFolder(long userId, long groupId, String folderName) throws PortalException {
		
		Folder p2pFolder = null;

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(false);

		long repositoryId = DLFolderConstants.getDataRepositoryId(groupId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID);
		Folder moduleFolder = ModuleLocalServiceUtil.addModuleFolder(userId, repositoryId);

		if(Validator.isNotNull(moduleFolder)) {
			p2pFolder = dlAppLocalService.addFolder(userId, repositoryId, moduleFolder.getFolderId(), folderName, folderName, serviceContext);
		}
		
		return p2pFolder;
	}
	
	public DLFileEntry addP2PFileEntry(String fileName, File file, String mimeType, long folderId, long groupId, long companyId, long userCreatedId) throws PortalException, IOException {

		log.debug("fileName: " + fileName);
		log.debug("mimeType: " + mimeType);
		log.debug("folderId: " + folderId);
		log.debug("groupId: " + groupId);
		log.debug("companyId: " + companyId);
		log.debug("userCreatedId: " + userCreatedId);
		
		validateFile(fileName, file.length(), companyId);
		
		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(false);
		
		try (InputStream inputStream = new FileInputStream(file)) {
			return dlFileEntryLocalService.addFileEntry(userCreatedId, groupId, 
					groupId,folderId,fileName, mimeType, fileName, null, "Importation", 0, null, null , inputStream, file.length(), serviceContext);
		}
		
	}
	
	private void validateFile(String fileName, long size, long companyId) throws PortalException {

		long p2pFileMaxSize = P2PPrefsPropsValues.getP2PFileMaxSize(companyId);
		
		if (p2pFileMaxSize > 0 && size > p2pFileMaxSize) {
			throw new P2PFileEntrySizeException();
		}

		String extension = FileUtil.getExtension(fileName);

		String[] imageExtensions = P2PPrefsPropsValues.getP2PFileExtensions(companyId);

		for (String imageExtension : imageExtensions) {
			if (!StringPool.STAR.equals(imageExtension) &&
				imageExtension.equals(StringPool.PERIOD + extension)) {
				
				throw new P2PFileEntryExtensionException("Invalid extension for file name " + fileName);
			}
		}

	}
	
	private static Log log = LogFactoryUtil.getLog(P2PActivityLocalServiceImpl.class);
}