package com.ted.lms.learning.activity.p2p.service.persistence.impl;

import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Team;
import com.ted.lms.learning.activity.p2p.model.P2PActivity;
import com.ted.lms.learning.activity.p2p.model.impl.P2PActivityImpl;
import com.ted.lms.learning.activity.p2p.service.persistence.P2PActivityCorrectionsFinder;
import com.ted.lms.learning.activity.p2p.service.persistence.P2PActivityFinder;
import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = P2PActivityFinder.class)
public class P2PActivityFinderImpl extends P2PActivityFinderBaseImpl implements P2PActivityFinder{
	
	@Reference
	private CustomSQL customSQL;
	
	public static final String FIND_BY_TEAM =
			P2PActivityFinder.class.getName() +
		        ".findByTeam";
	
	public static final String FIND_BY_GROUP =
			P2PActivityFinder.class.getName() +
		        ".findByGroup";
	
	public static final String FIND_BY_USER_WITHOUT_TEAM_ACTIVITIES =
			P2PActivityFinder.class.getName() +
		        ".findByUserWithoutTeamActivities";
	
	private static final Log log = LogFactoryUtil.getLog(P2PActivityFinderImpl.class);
	
	/**
	 * Gest all user´s p2pActivities of the course. Used for the assignation. 
	 * 
	 * @param actId Id of Learning activity.
	 * @param p2pActId Id of current p2pActivity
	 * @param start Limit start
	 * @param end Limit end
	 * @return List of p2pActivities of the group
	 */
	public List<P2PActivity> findByGroup(long actId, long p2pActId, int start, int end){
		List<P2PActivity> p2pActivities = new ArrayList<P2PActivity>();
		Session session = null;
		try{
			
			session = openSession();
			String sql = customSQL.get(getClass(), FIND_BY_GROUP);
		
			if(log.isDebugEnabled()){
				log.debug("sql: " + sql);
				//log.debug("userId: " + userId);
			}
			
			SQLQuery q = session.createSQLQuery(sql);
			q.addEntity("ptp_p2pactivity", P2PActivityImpl.class);
			QueryPos qPos = QueryPos.getInstance(q);			
			qPos.add(actId);
			qPos.add(p2pActId);
							
			p2pActivities = (List<P2PActivity>) QueryUtil.list(q, getDialect(), start, end);
			
		} catch (Exception e) {
	       e.printStackTrace();
	    } finally {
	        closeSession(session);
	    }
	
		return p2pActivities;
	}
	
	/**
	 * Gest all user´s p2pActivities of the team. Used for the assignation. 
	 * 
	 * @param actId Id of Learning activity.
	 * @param p2pActId Id of current p2pActivity
	 * @param start Limit start
	 * @param end Limit end
	 * @return List of p2pActivities of the group
	 */
	public List<P2PActivity> findByTeam(long actId, long p2pActId, List<Team> userTeams, int start, int end){
		List<P2PActivity> p2pActivities = new ArrayList<P2PActivity>();
		Session session = null;
		try{
			
			session = openSession();

			String sql = customSQL.get(getClass(), FIND_BY_TEAM);

			String teams = "(";
			for (int i = 0; i < userTeams.size(); i++) {
				teams += userTeams.get(i).getTeamId();
				if (i < (userTeams.size() - 1)) {
					teams += ", ";
				}
			}
			teams += ")";
			
			if (log.isDebugEnabled()) {
				log.debug("TEAMS "+userTeams);
				log.debug("sql: " + sql);
			}
			sql = sql.replace("[$TEAMS$]",teams);
			
			SQLQuery q = session.createSQLQuery(sql);
			q.addEntity("ptp_P2PActivity", P2PActivityImpl.class);
			QueryPos qPos = QueryPos.getInstance(q);
			qPos.add(actId);
			qPos.add(p2pActId);
			p2pActivities = (List<P2PActivity>) QueryUtil.list(q, getDialect(), start, end);
			
			
		} catch (Exception e) {
	       e.printStackTrace();
	    } finally {
	        closeSession(session);
	    }
	
		return p2pActivities;
		
	}
	
	public List<P2PActivity> findByUserWithoutTeamActivities(long actId, long p2pActId, long groupId, int start, int end){
		List<P2PActivity> p2pActivities = new ArrayList<P2PActivity>();
		Session session = null;
		try{
			
			session = openSession();
			
			String sql = customSQL.get(getClass(), FIND_BY_USER_WITHOUT_TEAM_ACTIVITIES);
		
			if(log.isDebugEnabled()){
				log.debug("sql: " + sql);
			}
			
			SQLQuery q = session.createSQLQuery(sql);
			q.addEntity("ptp_P2pActivity", P2PActivityImpl.class);
			QueryPos qPos = QueryPos.getInstance(q);			
			qPos.add(groupId);
			qPos.add(groupId);
			qPos.add(actId);
			qPos.add(p2pActId);
							
			p2pActivities = (List<P2PActivity>) QueryUtil.list(q, getDialect(), start, end);
				
		} catch (Exception e) {
	       e.printStackTrace();
	    } finally {
	        closeSession(session);
	    }
	
		return p2pActivities;
		
	}
}
