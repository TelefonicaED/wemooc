package com.ted.lms.learning.activity.question.registry;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceReference;
import com.liferay.registry.ServiceRegistration;
import com.liferay.registry.ServiceTracker;
import com.liferay.registry.ServiceTrackerCustomizer;
import com.liferay.registry.collections.ServiceRegistrationMap;
import com.liferay.registry.collections.ServiceRegistrationMapImpl;
import com.ted.lms.learning.activity.question.model.QuestionTypeFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import aQute.bnd.annotation.ProviderType;

@ProviderType
public class QuestionTypeFactoryRegistryUtil {
	private static final Log log = LogFactoryUtil.getLog(QuestionTypeFactoryRegistryUtil.class);

	private static final Map<String, QuestionTypeFactory> _questionFactoriesMapByClassName = new ConcurrentHashMap<>();
	private static final Map<Long, QuestionTypeFactory> _questionFactoriesMapByClassType = new ConcurrentHashMap<>();
	private static final ServiceRegistrationMap<QuestionTypeFactory> _serviceRegistrations = new ServiceRegistrationMapImpl<>();
	private static final ServiceTracker<QuestionTypeFactory, QuestionTypeFactory> _serviceTracker;

	public static List<QuestionTypeFactory> getQuestionFactories(long companyId) {
		return ListUtil.fromMapValues(_filterQuestionFactories(companyId, _questionFactoriesMapByClassName, null));
	}
	
	public static List<QuestionTypeFactory> getQuestionFactories(long companyId, long[] questionIdsAllowed) {
		return ListUtil.fromMapValues(_filterQuestionFactories(companyId, _questionFactoriesMapByClassName, questionIdsAllowed));
	}

	public static QuestionTypeFactory getQuestionTypeFactoryByClassName(String className) {
		return _questionFactoriesMapByClassName.get(className);
	}

	public static QuestionTypeFactory getQuestionTypeFactoryByClassNameId(long classNameId) {
		return _questionFactoriesMapByClassName.get(PortalUtil.getClassName(classNameId));
	}

	public static QuestionTypeFactory getQuestionTypeFactoryByType(long type) {
		return _questionFactoriesMapByClassType.get(type);
	}

	public static long[] getClassNameIds(long companyId) {

		Map<String, QuestionTypeFactory> questionFactories = _questionFactoriesMapByClassName;

		if (companyId > 0) {
			questionFactories = _filterQuestionFactories(companyId, _questionFactoriesMapByClassName, null);
		}

		long[] classNameIds = new long[questionFactories.size()];

		int i = 0;

		for (QuestionTypeFactory questionFactory :questionFactories.values()) {

			classNameIds[i] = questionFactory.getClassNameId();
			i++;
		}

		return classNameIds;
	}

	public static void register(QuestionTypeFactory questionFactory) {
		Registry registry = RegistryUtil.getRegistry();

		ServiceRegistration<QuestionTypeFactory> serviceRegistration = registry.registerService((Class<QuestionTypeFactory>)(Class<?>)QuestionTypeFactory.class,questionFactory);

		_serviceRegistrations.put(questionFactory, serviceRegistration);
	}

	public static void register(List<QuestionTypeFactory> questionFactories) {

		for (QuestionTypeFactory questionFactory : questionFactories) {
			register(questionFactory);
		}
	}

	public static void unregister(QuestionTypeFactory questionFactory) {

		ServiceRegistration<QuestionTypeFactory> serviceRegistration = _serviceRegistrations.remove(questionFactory);

		if (serviceRegistration != null) {
			serviceRegistration.unregister();
		}
	}

	public static void unregister(List<QuestionTypeFactory> questionFactories) {

		for (QuestionTypeFactory questionFactory : questionFactories) {
			unregister(questionFactory);
		}
	}

	private static Map<String, QuestionTypeFactory> _filterQuestionFactories(long companyId, Map<String, QuestionTypeFactory> questionFactories, long[] questionIdsAllowed) {

		Map<String, QuestionTypeFactory> filteredQuestionFactories = new ConcurrentHashMap<>();

		for (Map.Entry<String, QuestionTypeFactory> entry: questionFactories.entrySet()) {
			if(questionIdsAllowed == null || ArrayUtil.contains(questionIdsAllowed, entry.getValue().getType())) {
				QuestionTypeFactory questionFactory = entry.getValue();
				filteredQuestionFactories.put(entry.getKey(), questionFactory);
			}
		}

		return filteredQuestionFactories;
	}

	private QuestionTypeFactoryRegistryUtil() {
	}

	private static class QuestionTypeFactoryServiceTrackerCustomizer implements ServiceTrackerCustomizer<QuestionTypeFactory, QuestionTypeFactory> {

	@Override
	public QuestionTypeFactory addingService(ServiceReference<QuestionTypeFactory> serviceReference) {

		Registry registry = RegistryUtil.getRegistry();
		
		log.debug("serviceReference: " + serviceReference.toString());
		
		QuestionTypeFactory questionFactory = null;
		
		try {

			questionFactory = registry.getService(serviceReference);
 
			String className = questionFactory.getClassName();

			QuestionTypeFactory classNameQuestionTypeFactory = _questionFactoriesMapByClassName.put(className, questionFactory);

			if (log.isWarnEnabled() && (classNameQuestionTypeFactory != null)) {
				log.warn( StringBundler.concat("Replacing ", String.valueOf(classNameQuestionTypeFactory),
						" for class name ", className, " with ",String.valueOf(questionFactory)));
			}

			long type = questionFactory.getType();

			QuestionTypeFactory typeQuestionTypeFactory = _questionFactoriesMapByClassType.put(type, questionFactory);

			if (log.isWarnEnabled() && (typeQuestionTypeFactory != null)) {
				log.warn(StringBundler.concat("Replacing ", String.valueOf(typeQuestionTypeFactory),
						" for type ", String.valueOf(type), " with ", String.valueOf(questionFactory)));
				}
			} catch (ClassCastException e) {
				if(log.isDebugEnabled()) log.debug(e.getMessage());
			}

			return questionFactory;
		}

		@Override
		public void modifiedService(ServiceReference<QuestionTypeFactory> serviceReference,QuestionTypeFactory questionFactory) {
		}

		@Override
		public void removedService(ServiceReference<QuestionTypeFactory> serviceReference,QuestionTypeFactory questionFactory) {
			Registry registry = RegistryUtil.getRegistry();
			registry.ungetService(serviceReference);

			_questionFactoriesMapByClassName.remove(questionFactory.getClassName());
			_questionFactoriesMapByClassType.remove(questionFactory.getType());
		}

	}

	static {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices((Class<QuestionTypeFactory>)(Class<?>)QuestionTypeFactory.class,
			new QuestionTypeFactoryServiceTrackerCustomizer());

		_serviceTracker.open();
	}
}
