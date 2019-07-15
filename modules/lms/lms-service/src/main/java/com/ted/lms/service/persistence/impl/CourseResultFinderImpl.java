package com.ted.lms.service.persistence.impl;

import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.ted.lms.model.Course;
import com.ted.lms.model.CourseResult;
import com.ted.lms.model.impl.CourseResultImpl;
import com.ted.lms.service.persistence.CourseResultFinder;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

public class CourseResultFinderImpl extends CourseResultFinderBaseImpl implements CourseResultFinder{
	
	public static final String HAS_USER_TRIES =
			 CourseResultFinder.class.getName() +
				".hasUserTries";
	public static final String NOT_FINISHED =
			 CourseResultFinder.class.getName() +
				".notFinished";
	public static final String FIND_BY_U_G = 
			CourseResultFinder.class.getName() +
				".findBy_U_G";
	public static final String COUNT_BY_U_G = 
			CourseResultFinder.class.getName() +
			".countBy_U_G";
	public static final String WHERE_MY_COURSES_PASSED_DATE_NULL = 
			CourseResultFinder.class.getName() +
				".whereMyCoursesPassedDateNull";
	public static final String WHERE_MY_COURSES_PASSED_DATE_NOT_NULL = 
			CourseResultFinder.class.getName() +
				".whereMyCoursesPassedDateNotNull";
	public static final String WHERE_MY_COURSES_END_DATE_AFTER = 
			CourseResultFinder.class.getName() +
				".whereMyCoursesEndDateAfter";
	public static final String WHERE_MY_COURSES_END_DATE_BEFORE = 
			CourseResultFinder.class.getName() +
				".whereMyCoursesEndDateBefore";
	
	private static final Log log = LogFactoryUtil.getLog(CourseResultFinderImpl.class);
	
	public List<CourseResult> filterByU_G(long userId, boolean inProgress, boolean completed, boolean expired, long groupId, int start, int end, OrderByComparator obc, boolean inlineSQLHelper){
		Session session = null;
		List<CourseResult> courseResults = null;
		
		try {
		
			session = openSession();
			
			String sql = customSQL.get(getClass(), FIND_BY_U_G);
			
			if (inlineSQLHelper) {
				long[] groupIds = null;
				if (groupId > 0) {
					groupIds = new long[]{groupId};
				}else {
					groupIds = new long[0];
				}
				
				sql = InlineSQLHelperUtil.replacePermissionCheck(
						sql, Course.class.getName(), "Lms_Course.courseId",
						groupIds);
			}
			
			if(inProgress && !completed && !expired) {
				sql += " AND " + customSQL.get(getClass(), WHERE_MY_COURSES_PASSED_DATE_NULL);
			}
			if((inProgress || completed) && !expired) {
				sql += " AND " + customSQL.get(getClass(), WHERE_MY_COURSES_END_DATE_AFTER);
			}
			if(completed && !inProgress && !expired) {
				sql += " AND " + customSQL.get(getClass(), WHERE_MY_COURSES_PASSED_DATE_NOT_NULL);
			}
			if(expired && !inProgress && !completed) {
				sql += " AND " + customSQL.get(getClass(), WHERE_MY_COURSES_END_DATE_BEFORE);
			}
			if(!inProgress && completed && expired) {
				sql += " AND (" + customSQL.get(getClass(), WHERE_MY_COURSES_PASSED_DATE_NOT_NULL) + " OR " + customSQL.get(getClass(), WHERE_MY_COURSES_END_DATE_BEFORE) + ")";
			}
			
			
			log.debug("sql:" + sql);
			
			SQLQuery q = session.createSQLQuery(sql);
	
			q.addEntity("Lms_CourseResult", CourseResultImpl.class);
	
			QueryPos qPos = QueryPos.getInstance(q);
			
			qPos.add(userId);
			qPos.add(WorkflowConstants.STATUS_APPROVED);
			qPos.add(WorkflowConstants.STATUS_INACTIVE);
			qPos.add(groupId);
			qPos.add(groupId);
			
			courseResults = (List<CourseResult>) QueryUtil.list(
					q, getDialect(), start, end);
		
		} catch (Exception e) {
	       e.printStackTrace();
	    } finally {
	        closeSession(session);
	    }
		
		return courseResults;
	}
	
	public int doCountByU_G(long userId, boolean inProgress, boolean completed, boolean expired, long groupId, boolean inlineSQLHelper){
		Session session = null;
		
		int countValue = 0;
		
		try{
			
			session = openSession();
			
			String sql = customSQL.get(getClass(), COUNT_BY_U_G);
			
			if (inlineSQLHelper) {
				long[] groupIds = null;
				if (groupId > 0) {
					groupIds = new long[]{groupId};
				}else {
					groupIds = new long[0];
				}
				
				sql = InlineSQLHelperUtil.replacePermissionCheck(
						sql, Course.class.getName(), "Lms_Course.courseId",
						groupIds);
			}
			
			if(inProgress && !completed && !expired) {
				sql += " AND " + customSQL.get(getClass(), WHERE_MY_COURSES_PASSED_DATE_NULL);
			}
			if((inProgress || completed) && !expired) {
				sql += " AND " + customSQL.get(getClass(), WHERE_MY_COURSES_END_DATE_AFTER);
			}
			if(completed && !inProgress && !expired) {
				sql += " AND " + customSQL.get(getClass(), WHERE_MY_COURSES_PASSED_DATE_NOT_NULL);
			}
			if(expired && !inProgress && !completed) {
				sql += " AND " + customSQL.get(getClass(), WHERE_MY_COURSES_END_DATE_BEFORE);
			}
			if(!inProgress && completed && expired) {
				sql += " AND (" + customSQL.get(getClass(), WHERE_MY_COURSES_PASSED_DATE_NOT_NULL) + " OR " + customSQL.get(getClass(), WHERE_MY_COURSES_END_DATE_BEFORE) + ")";
			}
			
			log.debug("sql:" + sql);
			
			SQLQuery q = session.createSQLQuery(sql);
	
			QueryPos qPos = QueryPos.getInstance(q);
			
			qPos.add(userId);
			qPos.add(WorkflowConstants.STATUS_APPROVED);
			qPos.add(WorkflowConstants.STATUS_INACTIVE);
			qPos.add(groupId);
			qPos.add(groupId);
			
			Iterator<Object> itr = q.iterate();

			if (itr.hasNext()) {
				Object count = itr.next();
				
				if (count != null) {
					if(count instanceof Long){
						countValue = ((Long)count).intValue();
					}else if(count instanceof BigInteger){
						countValue = ((BigInteger)count).intValue();
					}else if(count instanceof Integer){
						countValue = (Integer)count;
					}
					
				}
			}
		
		} catch (Exception e) {
	       e.printStackTrace();
	    } finally {
	        closeSession(session);
	    }
		
		return countValue;
	}
	
	public boolean hasUserTries(long courseId, long userId){
		Session session = null;
		
		try{
			
			session = openSession();
			
			String sql = customSQL.get(getClass(), HAS_USER_TRIES);
		
			if(log.isDebugEnabled()){
				log.debug("sql: " + sql);
				log.debug("courseId: " + courseId);
				log.debug("userId: " + userId);
			}
			
			SQLQuery q = session.createSQLQuery(sql);
			
			QueryPos qPos = QueryPos.getInstance(q);
			qPos.add(userId);
			qPos.add(courseId);
			
			Iterator<BigInteger> itr =  q.iterate();
								
			if (itr.hasNext()) {
				BigInteger hasUserTries = itr.next();

				return hasUserTries.longValue() > 0;
			}
			
			
			
		} catch (Exception e) {
	       e.printStackTrace();
	    } finally {
	        closeSession(session);
	    }
	
		return false;
	}
	
	public List<CourseResult> getCourseResultNotFinished(long courseId){
		List<CourseResult> courseResults = null;
		
		Session session = null;
		
		try{
			
			session = openSession();
			
			String sql = customSQL.get(getClass(), NOT_FINISHED);
		
			if(log.isDebugEnabled()){
				log.debug("sql: " + sql);
				log.debug("courseId: " + courseId);
			}
			
			SQLQuery q = session.createSQLQuery(sql);
			
			QueryPos qPos = QueryPos.getInstance(q);
			qPos.add(courseId);
			q.addEntity("Lms_courseresult", CourseResultImpl.class);
								
			courseResults = q.list();
		} catch (Exception e) {
	       e.printStackTrace();
	    } finally {
	        closeSession(session);
	    }
	
		return courseResults;
	}
	
	@ServiceReference(type=CustomSQL.class)
	private CustomSQL customSQL;
}
