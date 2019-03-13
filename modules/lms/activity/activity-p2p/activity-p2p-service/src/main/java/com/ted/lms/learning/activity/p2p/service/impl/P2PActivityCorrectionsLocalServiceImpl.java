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
import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.Validator;
import com.ted.audit.api.AuditFactory;
import com.ted.lms.constants.LMSAuditConstants;
import com.ted.lms.learning.activity.p2p.model.P2PActivity;
import com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections;
import com.ted.lms.learning.activity.p2p.service.base.P2PActivityCorrectionsLocalServiceBaseImpl;
import com.ted.lms.learning.activity.p2p.util.P2PPrefsPropsValues;
import com.ted.lms.model.Course;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.Module;
import com.ted.lms.service.CourseLocalServiceUtil;
import com.ted.lms.service.LearningActivityLocalServiceUtil;
import com.ted.lms.service.ModuleLocalServiceUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.mail.internet.InternetAddress;

/**
 * The implementation of the p2p activity corrections local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ted.lms.learning.activity.p2p.service.P2PActivityCorrectionsLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see P2PActivityCorrectionsLocalServiceBaseImpl
 * @see com.ted.lms.learning.activity.p2p.service.P2PActivityCorrectionsLocalServiceUtil
 */
public class P2PActivityCorrectionsLocalServiceImpl
	extends P2PActivityCorrectionsLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.ted.lms.learning.activity.p2p.service.P2PActivityCorrectionsLocalServiceUtil} to access the p2p activity corrections local service.
	 */
	
	@Override
	public List<P2PActivityCorrections> getCorrectionsDoneByUser(long actId, long userId) {
		return p2pActivityCorrectionsFinder.findCorrectionsFinished(actId, userId);
	}
	
	@Override
	public List<P2PActivityCorrections> getCorrectionsDoneByP2PActivityId(long p2pActivityId){
		return p2pActivityCorrectionsFinder.findCorrectionsDoneByP2PActivityId(p2pActivityId);
	}
	
	@Override
	public List<P2PActivityCorrections> getP2PActivityCorrectionsByP2PActivityId(long p2pActivityId){
		return p2pActivityCorrectionsPersistence.findByP2PActivityId(p2pActivityId);
	}
	
	@Override 
	public List<P2PActivityCorrections> getCorrections(long actId, long userId){
		return p2pActivityCorrectionsPersistence.findByActIdAndUserId(actId, userId);
	}
	
	@Override 
	public int getCorrectionsCount(long actId, long userId){
		return p2pActivityCorrectionsPersistence.countByActIdAndUserId(actId, userId);
	}
	
	@Override
	public P2PActivityCorrections getCorrectionsByP2PActivityIdUserId(long p2pActivityId, long userId) {
		return p2pActivityCorrectionsPersistence.fetchByP2PActivityIdAndUserId(p2pActivityId, userId);
	}
	
	public boolean areAllCorrectionsDoneByUserInP2PActivity(long actId, long userId, int numValidations) {
	
		List<P2PActivityCorrections> corrections = p2pActivityCorrectionsLocalService.getCorrectionsDoneByUser(actId, userId);
		log.debug("actId: " + actId);
		log.debug("userId: " + userId);
		log.debug("numValidations: " + numValidations);
		log.debug("corrections: " + corrections.size());
		
		return corrections.size() >= numValidations;
	}
	
	/**
	 * Para saber si al usuario le han realizado todas las correcciones que se indica en el extracontent.
	 */
	public boolean hasAllCorrectionsDoneAboutUserInP2PActivity(long actId, long p2pActivityId, int numValidations) {
		
		boolean res = false;
		
		long num = p2pActivityCorrectionsFinder.findCorrectionsDoneByP2PActivityId(p2pActivityId).size();
		res = num >= numValidations;

		return res;
	}
	
	public P2PActivityCorrections updateP2PActivityCorrections(P2PActivityCorrections p2pActivityCorrections, String description, String fileName, 
			File file, String mimeType, long result, ServiceContext serviceContext) throws PortalException, IOException {
		
		//Auditamos
		Date now = new Date();
		User user = userLocalService.getUser(serviceContext.getUserId());
		p2pActivityCorrections.setModifiedDate(now);
		p2pActivityCorrections.setUserCreateId(user.getUserId());
		p2pActivityCorrections.setUserCreateName(user.getFullName());
		
		p2pActivityCorrections.setDescription(description);
		p2pActivityCorrections.setDate(now);
		p2pActivityCorrections.setResult(result);
		
		//Subimos el fichero
		if(Validator.isNotNull(fileName)) {
			try {
				Folder folder = p2pActivityLocalService.addP2PFolder(serviceContext.getUserId(), serviceContext.getScopeGroupId());
				DLFileEntry p2pFileEntry = p2pActivityLocalService.addP2PFileEntry(fileName, file, mimeType, folder.getFolderId(), serviceContext.getScopeGroupId(),
						serviceContext.getCompanyId(), serviceContext.getUserId());
				//Asociamos con el fichero subido.
				p2pActivityCorrections.setFileEntryId(p2pFileEntry.getFileEntryId());
			} catch (FileNotFoundException e) { 
				e.printStackTrace();
			}
		}
		
		return p2pActivityCorrectionsPersistence.update(p2pActivityCorrections);
	}
	
	/**
	 * A partir del id de una actividad, obtenemos la media de resultados que ha obtenido en ellas.
	 * En las correcciones que se han realizado y tiene fecha de realizaci�n.
	 * @param p2pActivityId
	 * @return la media de results
	 */
	public long getAVGCorrectionsResults(long p2pActivityId){
		long res = 0 ;
		
		List<P2PActivityCorrections> corrections = getP2PActivityCorrectionsByP2PActivityId(p2pActivityId);
		
		long numCorrected = 0;
		long totalResult = 0;
		for(P2PActivityCorrections correct: corrections){
			if(correct.getDate() != null){
				numCorrected++;
				totalResult = totalResult + correct.getResult();
			}
		}
		
		//Obtenemos la media del resultado.
		if(numCorrected > 0){
			res = totalResult / numCorrected;
		}
		
		return res;
	}
	
	public P2PActivityCorrections addP2PActivityCorrections(long actId, long userId, long p2pActivityId, long groupId, long companyId, long userCreatedId) {
		P2PActivityCorrections p2pActivityCorrections = p2pActivityCorrectionsPersistence.create(counterLocalService.increment(P2PActivityCorrections.class.getName()));
		
		p2pActivityCorrections.setGroupId(groupId);
		
		User user = userLocalService.fetchUser(userCreatedId);
		Date now = new Date();
		p2pActivityCorrections.setCompanyId(companyId);
		p2pActivityCorrections.setUserCreateId(userCreatedId);
		p2pActivityCorrections.setUserCreateName(user != null ? user.getFullName(): "");
		p2pActivityCorrections.setCreateDate(now);
		p2pActivityCorrections.setModifiedDate(now);
		
		p2pActivityCorrections.setP2pActivityId(p2pActivityId);
		p2pActivityCorrections.setUserId(userId);
		p2pActivityCorrections.setActId(actId);
		p2pActivityCorrections.setDescription(null);
		p2pActivityCorrections.setDate(null);
		
		
		p2pActivityCorrections = p2pActivityCorrectionsPersistence.update(p2pActivityCorrections);
		
		AuditFactory.audit(companyId, groupId, LMSAuditConstants.ADD, P2PActivityCorrections.class.getName(), p2pActivityCorrections.getP2pActivityCorrectionsId(), userCreatedId, 
				user != null ? user.getFullName() : "", null);
		
		return p2pActivityCorrections;
	}
	
	public void asignCorrectionsToP2PActivities(long actId, long p2pActivityId,int numValidaciones, List<P2PActivity> activityList, long userId) throws PortalException {
		
		//Precondicion: Solo asignamos las tareas cuando tengamos todas las que necesitamos.	
		if(activityList == null || activityList.isEmpty() || activityList.size() < numValidaciones){
			log.info("Solo asignamos las tareas cuando tengamos todas las que necesitamos. ");
			return;
		}
		
		LearningActivity activity = LearningActivityLocalServiceUtil.getLearningActivity(actId);
	
		//auditing
		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();
		
		for(P2PActivity p2pActivity : activityList){	
			
			addP2PActivityCorrections(actId, userId, p2pActivity.getP2pActivityId(), p2pActivity.getGroupId(), p2pActivity.getCompanyId(), serviceContext.getUserId());
			
			log.debug("Incrementamos el contador de correcciones que se ha asignado para corregir.");
			p2pActivity.setCountCorrections(p2pActivity.getCountCorrections()+1);
			p2pActivityLocalService.updateP2PActivity(p2pActivity);
			
		}
		log.debug("Ponemos que ya estan realizadas las asignaciones para no tener que calcular de nuevo las asignaciones.");
		//Ponemos que ya estan realizadas las asignaciones para no tener que calcular de nuevo las asignaciones.
		try {
			P2PActivity p2pActivity = p2pActivityLocalService.getP2PActivity(p2pActivityId);
			p2pActivity.setAsignationsCompleted(true);
			p2pActivityLocalService.updateP2PActivity(p2pActivity);
			
			log.debug("Mandar email al usuario avisando de que ya puede corregir sus actividades.");
			User user = userLocalService.getUser(p2pActivity.getUserId());
			PermissionChecker permissionChecker = PermissionCheckerFactoryUtil.create(user);

			if(!activity.isLocked(user, permissionChecker)){
				sendMailP2PAssignation(user, activity, serviceContext);
			} 			
			
			
		} catch (PortalException e) {
			// TODO Auto-generated catch block
		} catch (Exception e) {
			// TODO Auto-generated catch block
		} 
	}
	
	private void sendMailP2PAssignation(User user, LearningActivity activity, ServiceContext serviceContext) throws PortalException{
		try {
			
			Course course= CourseLocalServiceUtil.getCourseByGroupCreatedId(activity.getGroupId());
			
			Module module = ModuleLocalServiceUtil.getModule(activity.getModuleId());
			String courseFriendlyUrl = "";
			String courseTitle = "";
			String activityTitle = activity.getTitle(user.getLocale());
			String moduleTitle =  module.getTitle(user.getLocale());
			
			long modulesOfCourse = 0;	
			if(course != null){
				courseTitle = course.getTitle(user.getLocale());
				StringBundler sb = new StringBundler(11);

				sb.append(serviceContext.getPortalURL());
				sb.append(serviceContext.getPathMain());
				sb.append("/activities/find_activity");
				sb.append("?p_l_id=");
				sb.append(serviceContext.getPlid());
				sb.append(StringPool.AMPERSAND);
				sb.append("actId");
				sb.append(StringPool.EQUAL);
				sb.append(String.valueOf(activity.getActId()));
				sb.append(StringPool.AMPERSAND);
				sb.append(Constants.CMD);
				sb.append(StringPool.EQUAL);
				sb.append(Constants.VIEW);

				courseFriendlyUrl += sb.toString();
				
				log.debug("URL "+ courseFriendlyUrl);
				modulesOfCourse = ModuleLocalServiceUtil.getModulesCount(course.getGroupCreatedId());
			}
			
			String[] params={activityTitle, moduleTitle, courseTitle, courseFriendlyUrl};
			String bodyMessage = LanguageUtil.format(user.getLocale(), "p2ptaskactivity.mail.body.message", params);
			if(modulesOfCourse<=1){
				bodyMessage =LanguageUtil.format(user.getLocale(), "p2ptaskactivity.mail.body.message-simple", new String[]{activityTitle, courseTitle, courseFriendlyUrl});
			}
			
			
			log.debug("Enviar los emails.");
			
			String portal = LanguageUtil.get(user.getLocale(), "p2ptaskactivity.mail.portal");
			String subject= LanguageUtil.get(user.getLocale(), "p2ptaskactivity.mail.subject");
			
			String bodyTitle   = LanguageUtil.format(user.getLocale(), "p2ptaskactivity.mail.body.title", user.getFullName());
			String bodyEnd     = LanguageUtil.get(user.getLocale(), "p2ptaskactivity.mail.body.end");
			
			String body = bodyTitle +"\n\n"+ bodyMessage +"\n\n"+ bodyEnd +"\n\n"+ portal+"\n";
			log.debug("MESSAGE: "+bodyMessage);
			String fromName = P2PPrefsPropsValues.getP2PEmailFromName(course.getCompanyId());
			String fromAddress = P2PPrefsPropsValues.getP2PEmailFromAddress(course.getCompanyId());
						
			InternetAddress from = new InternetAddress(fromAddress, fromName);
			InternetAddress to = new InternetAddress(user.getEmailAddress(), user.getFullName());
			
			MailMessage mailm = new MailMessage(from, to, subject, body, true);
			MailServiceUtil.sendEmail(mailm);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static Log log = LogFactoryUtil.getLog(P2PActivityCorrectionsLocalServiceImpl.class);

}