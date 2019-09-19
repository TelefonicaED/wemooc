package com.ted.lms.learning.activity.p2p.service.persistence.impl;

import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections;
import com.ted.lms.learning.activity.p2p.model.impl.P2PActivityCorrectionsImpl;
import com.ted.lms.learning.activity.p2p.service.persistence.P2PActivityCorrectionsFinder;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = P2PActivityCorrectionsFinder.class)
public class P2PActivityCorrectionsFinderImpl extends P2PActivityCorrectionsFinderBaseImpl implements P2PActivityCorrectionsFinder{
	
	@Reference
	private CustomSQL customSQL;
	
	public static final String FIND_CORRECTIONS_FINISHED =
			P2PActivityCorrectionsFinder.class.getName() +
		        ".findCorrectionsFinished";
	
	public static final String FIND_CORRECTIONS_DONE_BY_P2P_ACTIVITY_ID =
			P2PActivityCorrectionsFinder.class.getName() +
		        ".findCorrectionsDoneByP2PActivityId";
	
	private static final Log log = LogFactoryUtil.getLog(P2PActivityCorrectionsFinderImpl.class);
	
	/**
	 * Gest all user´s p2pActivities of the course. Used for the assignation. 
	 * 
	 * @param actId Id of Learning activity.
	 * @param userId id del usuario
	 * @return List of P2PActivityCorrections
	 */
	public List<P2PActivityCorrections> findCorrectionsFinished(long actId, long userId){
		List<P2PActivityCorrections> p2pActivities = null;
		Session session = null;
		try{
			
			session = openSession();
			String sql = customSQL.get(getClass(), FIND_CORRECTIONS_FINISHED);
		
			if(log.isDebugEnabled()){
				log.debug("sql: " + sql);
				//log.debug("userId: " + userId);
			}
			
			SQLQuery q = session.createSQLQuery(sql);
			q.addEntity("ptp_p2pactivity", P2PActivityCorrectionsImpl.class);
			QueryPos qPos = QueryPos.getInstance(q);			
			qPos.add(actId);
			qPos.add(userId);
							
			p2pActivities = (List<P2PActivityCorrections>) q.list();
			
		} catch (Exception e) {
	       e.printStackTrace();
	       p2pActivities = new ArrayList<P2PActivityCorrections>();
	    } finally {
	        closeSession(session);
	    }
	
		return p2pActivities;
	}
	
	/**
	 * @param p2pActivityId id del usuario
	 * @return 
	 */
	public List<P2PActivityCorrections> findCorrectionsDoneByP2PActivityId(long p2pActivityId){
		List<P2PActivityCorrections> p2pActivities = null;
		Session session = null;
		try{
			
			session = openSession();
			String sql = customSQL.get(getClass(), FIND_CORRECTIONS_DONE_BY_P2P_ACTIVITY_ID);
		
			if(log.isDebugEnabled()){
				log.debug("sql: " + sql);
			}
			
			SQLQuery q = session.createSQLQuery(sql);
			q.addEntity("ptp_p2pactivity", P2PActivityCorrectionsImpl.class);
			QueryPos qPos = QueryPos.getInstance(q);			
			qPos.add(p2pActivityId);
							
			p2pActivities = (List<P2PActivityCorrections>) q.list();
			
		} catch (Exception e) {
	       e.printStackTrace();
	       p2pActivities = new ArrayList<P2PActivityCorrections>();
	    } finally {
	        closeSession(session);
	    }
	
		return p2pActivities;
	}
}
