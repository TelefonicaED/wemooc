package com.ted.lms.service.persistence.impl;

import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.ted.lms.service.persistence.ModuleResultFinder;

 
import java.util.Iterator;

public class ModuleResultFinderImpl extends ModuleResultFinderBaseImpl implements ModuleResultFinder{
	
	public static final String COUNT_MODULES_USER_PASSED = ModuleResultFinder.class.getName() + ".countModulesUserPassed";
	
	@Override
	public int countModulesUserPassed(long groupId, long userId) {

		Session session = null;

		try {
			session = openSession();

			String sql = customSQL.get(getClass(),COUNT_MODULES_USER_PASSED);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.INTEGER);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);
			qPos.add(userId);

			Iterator<Integer> itr = q.iterate();

			if (itr.hasNext()) {
				Integer count = itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}
	
	@ServiceReference(type=CustomSQL.class)
	private CustomSQL customSQL;
}
