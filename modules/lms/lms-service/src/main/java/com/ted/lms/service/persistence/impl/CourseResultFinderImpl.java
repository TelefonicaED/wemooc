package com.ted.lms.service.persistence.impl;

import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.ted.lms.service.persistence.CourseResultFinder;

import java.math.BigInteger;
import java.util.Iterator;

public class CourseResultFinderImpl extends CourseResultFinderBaseImpl implements CourseResultFinder{
	
	public static final String HAS_USER_TRIES =
			 CourseResultFinder.class.getName() +
				".hasUserTries";
	
	private static final Log log = LogFactoryUtil.getLog(CourseResultFinderImpl.class);
	
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
	
	@ServiceReference(type=CustomSQL.class)
	private CustomSQL customSQL;
}
