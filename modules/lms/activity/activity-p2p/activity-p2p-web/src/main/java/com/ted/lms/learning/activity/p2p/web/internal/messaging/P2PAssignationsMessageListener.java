package com.ted.lms.learning.activity.p2p.web.internal.messaging;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.Validator;
import com.ted.lms.learning.activity.p2p.model.P2PActivity;
import com.ted.lms.learning.activity.p2p.service.P2PActivityLocalService;
import com.ted.lms.learning.activity.p2p.web.activity.P2PActivityType;
import com.ted.lms.learning.activity.p2p.web.activity.P2PActivityTypeFactory;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityType;
import com.ted.lms.service.LearningActivityLocalService;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true, service = P2PAssignationsMessageListener.class
)
public class P2PAssignationsMessageListener extends BaseMessageListener{

	@Override
	protected void doReceive(Message message) throws Exception {
		if(log.isDebugEnabled())log.debug("Check P2P asign corrections");

		//Obtener la lista de actividades p2p de todos los usuarios.
		List<P2PActivity> activities = null;
		
		activities = p2pActivityLocalService.getP2PActivitiesByAssignationsCompleted(false);
		if(Validator.isNotNull(activities)){
			//Recorrer todas las activities
			LearningActivity activity = null;
			P2PActivityTypeFactory activityTypeFactory = new P2PActivityTypeFactory();
			P2PActivityType p2pActivityType = null;
			
			for(P2PActivity p2pActivity:activities){
				activity = learningActivityLocalService.getLearningActivity(p2pActivity.getActId());
				p2pActivityType = activityTypeFactory.getP2PActivityType(activity);
				p2pActivityLocalService.asignCorrectionP2PActivity(p2pActivity, p2pActivityType.getNumValidations(), p2pActivityType.getAssignationType());
				
			}
		}
	}
	
	@Reference(unbind = "-")
	protected void setP2PActivityLocalService(P2PActivityLocalService p2pActivityLocalService) {

		this.p2pActivityLocalService = p2pActivityLocalService;
	}
	
	private P2PActivityLocalService p2pActivityLocalService;
	
	@Reference(unbind = "-")
	protected void setLearningActivityLocalService(LearningActivityLocalService learningActivityLocalService) {

		this.learningActivityLocalService = learningActivityLocalService;
	}
	
	private LearningActivityLocalService learningActivityLocalService;
	
	private static Log log = LogFactoryUtil.getLog(P2PAssignationsMessageListener.class);

}
