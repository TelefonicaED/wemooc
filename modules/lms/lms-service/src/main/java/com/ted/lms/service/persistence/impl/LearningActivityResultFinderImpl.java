package com.ted.lms.service.persistence.impl;

import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.ted.lms.constants.LMSRoleConstants;
import com.ted.lms.model.LearningActivityResult;
import com.ted.lms.model.impl.LearningActivityResultImpl;
import com.ted.lms.service.persistence.LearningActivityResultFinder;

import java.util.Iterator;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = LearningActivityResultFinder.class)
public class LearningActivityResultFinderImpl extends LearningActivityResultFinderBaseImpl implements LearningActivityResultFinder{
	
	@Reference
	private CustomSQL customSQL;
	
	public static final String FIND_REQUIRED_LEARNING_ACTIVITY_RESULTS = 
			LearningActivityResultFinder.class.getName() + ".findRequiredLearningActivityResults";
	public static final String COUNT_REQUIRED_LEARNING_ACTIVITY_RESULTS_BY_MODULE = 
			LearningActivityResultFinder.class.getName() + ".countRequiredLearningActivityResultsByModule";
	public static final String COUNT_REQUIRED_LEARNING_ACTIVITY_RESULTS = 
			LearningActivityResultFinder.class.getName() + ".countRequiredLearningActivityResults";
	public static final String COUNT_STUDENTS_FINISHED = 
			LearningActivityResultFinder.class.getName() + ".countStudentsFinished";
	
	private static final Log log = LogFactoryUtil.getLog(LearningActivityResultFinderImpl.class);
	
	@Override
	public List<LearningActivityResult> findRequiredLearningActivityResults(long groupId, long userId) {

		Session session = null;
		
		List<LearningActivityResult> listLearningActivityResults = null;

		try {
			session = openSession();

			String sql = customSQL.get(getClass(), FIND_REQUIRED_LEARNING_ACTIVITY_RESULTS);
			log.debug("********sql: " + sql);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("lms_learningactivityresult", LearningActivityResultImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);
			qPos.add(userId);

			listLearningActivityResults = (List<LearningActivityResult>) q.list();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
		
		return listLearningActivityResults;
	}
	
	@Override
	public int countRequiredLearningActivityResults(long groupId, long userId) {

		Session session = null;
		
		int countLearningActivities = 0;

		try {
			session = openSession();

			String sql = customSQL.get(getClass(), COUNT_REQUIRED_LEARNING_ACTIVITY_RESULTS);
			log.debug("********sql: " + sql);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);
			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);
			qPos.add(userId);

			Iterator<Integer> itr = q.iterate();

			if (itr.hasNext()) {
				Integer count = itr.next();

				if (count != null) {
					countLearningActivities = count.intValue();
				}
			}
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
		
		return countLearningActivities;
	}
	
	@Override
	public int countRequiredLearningActivityResultsByModule(long moduleId, long userId) {

		Session session = null;
		
		int countLearningActivities = 0;

		try {
			session = openSession();

			String sql = customSQL.get(getClass(), COUNT_REQUIRED_LEARNING_ACTIVITY_RESULTS_BY_MODULE);
			log.debug("********sql: " + sql);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(moduleId);
			qPos.add(userId);

			Iterator<Integer> itr = q.iterate();

			if (itr.hasNext()) {
				Integer count = itr.next();

				if (count != null) {
					countLearningActivities = count.intValue();
				}
			}
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
		
		return countLearningActivities;
	}
	
	public long countStudentsFinished(long actId, long courseGroupCreatedId, long companyId) {
		long countStartedOnlyStudents = 0;
		Session session = null;
		try{
			
			session = openSession();
			String sql = customSQL.get(getClass(), COUNT_STUDENTS_FINISHED);
		
			if(log.isDebugEnabled()){
				log.debug("sql: " + sql);
			}
			
			SQLQuery q = session.createSQLQuery(sql);
			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);
			
			QueryPos qPos = QueryPos.getInstance(q);	
			qPos.add(RoleLocalServiceUtil.getRole(companyId, LMSRoleConstants.STUDENT).getRoleId());
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
}
