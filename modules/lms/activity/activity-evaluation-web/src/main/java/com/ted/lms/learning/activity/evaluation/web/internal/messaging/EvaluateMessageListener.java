package com.ted.lms.learning.activity.evaluation.web.internal.messaging;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.SchedulerEntry;
import com.liferay.portal.kernel.scheduler.SchedulerEntryImpl;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.ted.lms.learning.activity.evaluation.EvaluationUsersUtil;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true, service = EvaluateMessageListener.class
)
public class EvaluateMessageListener extends BaseMessageListener {
	
	private static final Log log = LogFactoryUtil.getLog(EvaluateMessageListener.class);
	
	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		
		log.debug("active evaluatemessage");

		Class<?> clazz = getClass();

		String className = clazz.getName();

		Trigger trigger = _triggerFactory.createTrigger(className, className, null, null,1, TimeUnit.DAY);

		SchedulerEntry schedulerEntry = new SchedulerEntryImpl(className, trigger);

		_schedulerEngineHelper.register(this, schedulerEntry, DestinationNames.SCHEDULER_DISPATCH);
	}

	@Deactivate
	protected void deactivate() {
		log.debug("active evaluatemessage");
		_schedulerEngineHelper.unregister(this);
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		log.debug("ejecutando cron de evaluation");
		evaluationUsersUtil.evaluate();
	}

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}
	
	@Reference(unbind = "-")
	protected void setEvaluationUsersUtil(EvaluationUsersUtil evaluationUsersUtil) {

		this.evaluationUsersUtil = evaluationUsersUtil;
	}

	@Reference(unbind = "-")
	protected void setSchedulerEngineHelper(
		SchedulerEngineHelper schedulerEngineHelper) {

		_schedulerEngineHelper = schedulerEngineHelper;
	}
	
	private EvaluationUsersUtil evaluationUsersUtil;

	private SchedulerEngineHelper _schedulerEngineHelper;

	@Reference
	private TriggerFactory _triggerFactory;
}
