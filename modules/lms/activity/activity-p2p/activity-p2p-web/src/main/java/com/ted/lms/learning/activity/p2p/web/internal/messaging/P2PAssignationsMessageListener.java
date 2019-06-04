package com.ted.lms.learning.activity.p2p.web.internal.messaging;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.SchedulerEntry;
import com.liferay.portal.kernel.scheduler.SchedulerEntryImpl;
import com.liferay.portal.kernel.scheduler.SchedulerException;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.util.Validator;
import com.ted.lms.learning.activity.p2p.constants.P2PConstants;
import com.ted.lms.learning.activity.p2p.model.P2PActivity;
import com.ted.lms.learning.activity.p2p.service.P2PActivityLocalService;
import com.ted.lms.learning.activity.p2p.web.activity.P2PActivityType;
import com.ted.lms.learning.activity.p2p.web.activity.P2PActivityTypeFactory;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.registry.LearningActivityTypeFactoryRegistryUtil;
import com.ted.lms.service.LearningActivityLocalService;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true, 
	service = MessageListener.class
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
			P2PActivityTypeFactory activityTypeFactory = (P2PActivityTypeFactory)LearningActivityTypeFactoryRegistryUtil.getLearningActivityTypeFactoryByType(P2PConstants.TYPE);
			P2PActivityType p2pActivityType = null;
			
			for(P2PActivity p2pActivity:activities){
				activity = learningActivityLocalService.getLearningActivity(p2pActivity.getActId());
				p2pActivityType = activityTypeFactory.getP2PActivityType(activity);
				p2pActivityLocalService.asignCorrectionP2PActivity(p2pActivity, p2pActivityType.getNumValidations(), p2pActivityType.getAssignationType());
			}
		}
	}
	

	@Activate
	@Modified
	protected void activate(Map<String,Object> properties) throws SchedulerException {
	
		Class<?> clazz = getClass();
		
		String className = clazz.getName();
	
		Trigger trigger = _triggerFactory.createTrigger(className, className, null, null,20, TimeUnit.MINUTE);
	
		SchedulerEntry schedulerEntry = new SchedulerEntryImpl(className, trigger);
	
		_schedulerEngineHelper.register(this, schedulerEntry, DestinationNames.SCHEDULER_DISPATCH);
	}
	
	@Deactivate
	protected void deactivate() {
		_schedulerEngineHelper.unregister(this);
	}
	  
	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(ModuleServiceLifecycle moduleServiceLifecycle) {
	}
	
	@Reference(unbind = "-")
	protected void setSchedulerEngineHelper(SchedulerEngineHelper schedulerEngineHelper) {
		_schedulerEngineHelper = schedulerEngineHelper;
	}
	
	private SchedulerEngineHelper _schedulerEngineHelper;
	
	@Reference
	private TriggerFactory _triggerFactory;
	
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
