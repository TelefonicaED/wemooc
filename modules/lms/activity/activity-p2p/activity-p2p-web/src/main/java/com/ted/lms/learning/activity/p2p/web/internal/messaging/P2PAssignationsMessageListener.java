package com.ted.lms.learning.activity.p2p.web.internal.messaging;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.SchedulerEntryImpl;
import com.liferay.portal.kernel.scheduler.SchedulerException;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.StorageTypeAware;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.ted.lms.learning.activity.p2p.model.P2PActivity;
import com.ted.lms.learning.activity.p2p.service.P2PActivityLocalService;
import com.ted.lms.learning.activity.p2p.web.activity.P2PActivityType;
import com.ted.lms.learning.activity.p2p.web.activity.P2PActivityTypeFactory;
import com.ted.lms.learning.activity.p2p.web.internal.scheduler.StorageTypeAwareSchedulerEntryImpl;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.service.LearningActivityLocalService;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true, 
	property = {"cron.expression=0 0 3 * * ?"},
	service = P2PAssignationsMessageListener.class
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
	
	/**
	   * activate: Called whenever the properties for the component change (ala Config Admin)
	   * or OSGi is activating the component.
	   * @param properties The properties map from Config Admin.
	   * @throws SchedulerException in case of error.
	   */
	  @Activate
	  @Modified
	  protected void activate(Map<String,Object> properties) throws SchedulerException {
		  log.debug("activate: " + _initialized);

	    // extract the cron expression from the properties
	    String cronExpression = GetterUtil.getString(properties.get("cron.expression"), _DEFAULT_CRON_EXPRESSION);
	    log.debug("cronExpression: " + cronExpression);

	    // create a new trigger definition for the job.
	    String listenerClass = getClass().getName();
	    Trigger jobTrigger = _triggerFactory.createTrigger(listenerClass, listenerClass, new Date(), null, cronExpression);

	    // wrap the current scheduler entry in our new wrapper.
	    // use the persisted storaget type and set the wrapper back to the class field.
	    _schedulerEntryImpl = new SchedulerEntryImpl(getClass().getName(), jobTrigger);
	    _schedulerEntryImpl = new StorageTypeAwareSchedulerEntryImpl(_schedulerEntryImpl, StorageType.PERSISTED);

	    // update the trigger for the scheduled job.
	    _schedulerEntryImpl.setTrigger(jobTrigger);

	    // if we were initialized (i.e. if this is called due to CA modification)
	    if (_initialized) {
	      // first deactivate the current job before we schedule.
	      deactivate();
	    }

	    // register the scheduled task
	    _schedulerEngineHelper.register(this, _schedulerEntryImpl, DestinationNames.SCHEDULER_DISPATCH);

	    // set the initialized flag.
	    _initialized = true;
	  }

	  /**
	   * deactivate: Called when OSGi is deactivating the component.
	   */
	  @Deactivate
	  protected void deactivate() {
		  log.debug("deactivate");
	    // if we previously were initialized
	    if (_initialized) {
	      // unschedule the job so it is cleaned up
	      try {
	        _schedulerEngineHelper.unschedule(_schedulerEntryImpl, getStorageType());
	      } catch (SchedulerException se) {
	        if (log.isWarnEnabled()) {
	          log.warn("Unable to unschedule trigger", se);
	        }
	      }

	      // unregister this listener
	      _schedulerEngineHelper.unregister(this);
	    }
	    
	    // clear the initialized flag
	    _initialized = false;
	  }

	  /**
	   * getStorageType: Utility method to get the storage type from the scheduler entry wrapper.
	   * @return StorageType The storage type to use.
	   */
	  protected StorageType getStorageType() {
	    if (_schedulerEntryImpl instanceof StorageTypeAware) {
	      return ((StorageTypeAware) _schedulerEntryImpl).getStorageType();
	    }
	    
	    return StorageType.MEMORY_CLUSTERED;
	  }
	  
	  /**
	   * setModuleServiceLifecycle: So this requires some explanation...
	   * 
	   * OSGi will start a component once all of it's dependencies are satisfied.  However, there
	   * are times where you want to hold off until the portal is completely ready to go.
	   * 
	   * This reference declaration is waiting for the ModuleServiceLifecycle's PORTAL_INITIALIZED
	   * component which will not be available until, surprise surprise, the portal has finished
	   * initializing.
	   * 
	   * With this reference, this component activation waits until portal initialization has completed.
	   * @param moduleServiceLifecycle
	   */
	  @Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	  protected void setModuleServiceLifecycle(ModuleServiceLifecycle moduleServiceLifecycle) {
	  }

	  @Reference(unbind = "-")
	  protected void setTriggerFactory(TriggerFactory triggerFactory) {
	    _triggerFactory = triggerFactory;
	  }

	  @Reference(unbind = "-")
	  protected void setSchedulerEngineHelper(SchedulerEngineHelper schedulerEngineHelper) {
	    _schedulerEngineHelper = schedulerEngineHelper;
	  }

	  // the default cron expression is to run daily at midnight
	  private static final String _DEFAULT_CRON_EXPRESSION = "0 0 0 * * ?";

	  private volatile boolean _initialized;
	  private TriggerFactory _triggerFactory;
	  private SchedulerEngineHelper _schedulerEngineHelper;
	  private SchedulerEntryImpl _schedulerEntryImpl = null;
	
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
