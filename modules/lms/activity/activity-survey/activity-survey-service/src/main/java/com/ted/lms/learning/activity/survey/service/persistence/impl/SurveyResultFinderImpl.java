package com.ted.lms.learning.activity.survey.service.persistence.impl;

import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.ted.lms.learning.activity.survey.service.persistence.SurveyResultFinder;
import com.ted.lms.util.LMSUtil;

import java.util.Iterator;

public class SurveyResultFinderImpl extends SurveyResultFinderBaseImpl implements SurveyResultFinder {
	
	public static final String COUNT_STARTED_ONLY_STUDENTS =
			SurveyResultFinder.class.getName() +
		        ".countStartedOnlyStudents";
	
	public static final String COUNT_STUDENTS_BY_QUESTION_ID_ANSWER_ID =
			SurveyResultFinder.class.getName() +
		        ".countStudentsByQuestionIdAnswerId";
	
	public static final String COUNT_STUDENTS_BY_QUESTION_ID =
			SurveyResultFinder.class.getName() +
		        ".countStudentsByQuestionId";
	
	private static final Log log = LogFactoryUtil.getLog(SurveyResultFinderImpl.class);
	
	/**
	 * Alumnos que han empezado la encuesta
	 * 
	 * @param actId Id de la actividad
	 * @param companyId id de la instancia
	 * @param courseGroupCreatedId id del sitio web del curso
	 * @return número de estudiantes
	 */
	public long countStartedOnlyStudents(long actId, long companyId, long courseGroupCreatedId){
		long countStartedOnlyStudents = 0;
		Session session = null;
		try{
			
			session = openSession();
			String sql = customSQL.get(getClass(), COUNT_STARTED_ONLY_STUDENTS);
		
			if(log.isDebugEnabled()){
				log.debug("sql: " + sql);
			}
			
			SQLQuery q = session.createSQLQuery(sql);
			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);
			
			QueryPos qPos = QueryPos.getInstance(q);			
			qPos.add(courseGroupCreatedId);
			qPos.add(LMSUtil.getTeacherRoleId(companyId));
			qPos.add(LMSUtil.getEditorRoleId(companyId));
			qPos.add(actId);
							
			Iterator<Long> itr = q.iterate();

			if (itr.hasNext()) {
				Long count = itr.next();

				if (count != null) {
					countStartedOnlyStudents = count.intValue();
				}
			}
			
		} catch (Exception e) {
	       e.printStackTrace();
	    } finally {
	        closeSession(session);
	    }
	
		return countStartedOnlyStudents;
	}
	
	/**
	 * Alumnos que respondido una repuesta concreta a una pregunta
	 * 
	 * @param questionId Id de la pregunta
	 * @param answerId Id de la respuesta
	 * @param companyId id de la instancia
	 * @param courseGroupCreatedId id del sitio web del curso
	 * @return número de estudiantes
	 */
	public long countStudentsByQuestionIdAndAnswerId(long questionId, long answerId, long companyId, long courseGroupCreatedId){
		long countStudents = 0;
		Session session = null;
		try{
			
			session = openSession();
			String sql = customSQL.get(getClass(), COUNT_STUDENTS_BY_QUESTION_ID_ANSWER_ID);
		
			if(log.isDebugEnabled()){
				log.debug("sql: " + sql);
			}
			
			SQLQuery q = session.createSQLQuery(sql);
			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);
			
			QueryPos qPos = QueryPos.getInstance(q);			
			qPos.add(courseGroupCreatedId);
			qPos.add(LMSUtil.getTeacherRoleId(companyId));
			qPos.add(LMSUtil.getEditorRoleId(companyId));
			qPos.add(answerId);
			qPos.add(questionId);
								
			Iterator<Long> itr = q.iterate();

			if (itr.hasNext()) {
				Long count = itr.next();

				if (count != null) {
					countStudents = count.intValue();
				}
			}
			
		} catch (Exception e) {
	       e.printStackTrace();
	    } finally {
	        closeSession(session);
	    }
	
		return countStudents;
	}
	
	/**
	 * Alumnos que respondido a una pregunta
	 * 
	 * @param questionId Id de la pregunta
	 * @param companyId id de la instancia
	 * @param courseGroupCreatedId id del sitio web del curso
	 * @return número de estudiantes
	 */
	public long countStudentsByQuestionId(long questionId, long companyId, long courseGroupCreatedId){
		long countStudents = 0;
		Session session = null;
		try{
			
			session = openSession();
			String sql = customSQL.get(getClass(), COUNT_STUDENTS_BY_QUESTION_ID);
		
			if(log.isDebugEnabled()){
				log.debug("sql: " + sql);
			}
			
			SQLQuery q = session.createSQLQuery(sql);
			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);
			
			QueryPos qPos = QueryPos.getInstance(q);			
			qPos.add(courseGroupCreatedId);
			qPos.add(LMSUtil.getTeacherRoleId(companyId));
			qPos.add(LMSUtil.getEditorRoleId(companyId));
			qPos.add(questionId);
							
			Iterator<Long> itr = q.iterate();

			if (itr.hasNext()) {
				Long count = itr.next();

				if (count != null) {
					countStudents = count.intValue();
				}
			}
			
		} catch (Exception e) {
	       e.printStackTrace();
	    } finally {
	        closeSession(session);
	    }
	
		return countStudents;
	}

	
	@ServiceReference(type=CustomSQL.class)
	private CustomSQL customSQL;
}
