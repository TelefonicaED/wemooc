package com.ted.lms.service.persistence.impl;

import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.ted.lms.model.LearningActivityResult;
import com.ted.lms.model.impl.LearningActivityResultImpl;
import com.ted.lms.service.persistence.LearningActivityResultFinder;

 
import java.util.List;

public class LearningActivityResultFinderImpl extends LearningActivityResultFinderBaseImpl implements LearningActivityResultFinder{
	
	public static final String FIND_REQUIRED_LEARNING_ACTIVITY_RESULTS = LearningActivityResultFinder.class.getName() + ".findRequiredLearningActivityResults";
	
	@Override
	public List<LearningActivityResult> findRequiredLearningActivityResults(long groupId, long userId) {

		Session session = null;
		
		List<LearningActivityResult> listLearningActivityResults = null;

		try {
			session = openSession();

			String sql = customSQL.get(getClass(), FIND_REQUIRED_LEARNING_ACTIVITY_RESULTS);

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
	
	@ServiceReference(type=CustomSQL.class)
	private CustomSQL customSQL;
}
