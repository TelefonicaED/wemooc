package com.ted.lms.learningactivity;

import aQute.bnd.annotation.ProviderType;

import com.ted.lms.model.LearningActivityTypeFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
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

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Virginia Martín Agudo
 */
@ProviderType
public class LearningActivityTypeFactoryRegistryUtil {

	public static List<LearningActivityTypeFactory<?>> getLearningActivityFactories(
		long companyId) {

		return ListUtil.fromMapValues(
			_filterLearningActivityFactories(
				companyId, _learningActivityFactoriesMapByClassName, false));
	}

	public static List<LearningActivityTypeFactory<?>> getLearningActivityFactories(
		long companyId, boolean filterSelectable) {

		return ListUtil.fromMapValues(
			_filterLearningActivityFactories(
				companyId, _learningActivityFactoriesMapByClassName,
				filterSelectable));
	}

	public static <T> LearningActivityTypeFactory<T> getLearningActivityTypeFactoryByClass(
		Class<T> clazz) {

		return (LearningActivityTypeFactory<T>)_learningActivityFactoriesMapByClassName.get(
			clazz.getName());
	}

	public static LearningActivityTypeFactory<?> getLearningActivityTypeFactoryByClassName(
		String className) {

		return _learningActivityFactoriesMapByClassName.get(className);
	}

	public static LearningActivityTypeFactory<?> getLearningActivityTypeFactoryByClassNameId(
		long classNameId) {

		return _learningActivityFactoriesMapByClassName.get(
			PortalUtil.getClassName(classNameId));
	}

	public static LearningActivityTypeFactory<?> getLearningActivityTypeFactoryByType(
		String type) {

		return _learningActivityFactoriesMapByClassType.get(type);
	}

	public static long[] getClassNameIds(long companyId) {
		return getClassNameIds(companyId, false);
	}

	public static long[] getClassNameIds(
		long companyId, boolean filterSelectable) {

		Map<String, LearningActivityTypeFactory<?>> learningActivityFactories =
			_learningActivityFactoriesMapByClassName;

		if (companyId > 0) {
			learningActivityFactories = _filterLearningActivityFactories(
				companyId, _learningActivityFactoriesMapByClassName,
				filterSelectable);
		}

		long[] classNameIds = new long[learningActivityFactories.size()];

		int i = 0;

		for (LearningActivityTypeFactory<?> learningActivityFactory :
				learningActivityFactories.values()) {

			classNameIds[i] = learningActivityFactory.getClassNameId();

			i++;
		}

		return classNameIds;
	}

	public static void register(LearningActivityTypeFactory<?> learningActivityFactory) {
		Registry registry = RegistryUtil.getRegistry();

		ServiceRegistration<LearningActivityTypeFactory<?>> serviceRegistration =
			registry.registerService(
				(Class<LearningActivityTypeFactory<?>>)(Class<?>)
					LearningActivityTypeFactory.class,
				learningActivityFactory);

		_serviceRegistrations.put(learningActivityFactory, serviceRegistration);
	}

	public static void register(
		List<LearningActivityTypeFactory<?>> learningActivityFactories) {

		for (LearningActivityTypeFactory<?> learningActivityFactory :
				learningActivityFactories) {

			register(learningActivityFactory);
		}
	}

	public static void unregister(
		LearningActivityTypeFactory<?> learningActivityFactory) {

		ServiceRegistration<LearningActivityTypeFactory<?>> serviceRegistration =
			_serviceRegistrations.remove(learningActivityFactory);

		if (serviceRegistration != null) {
			serviceRegistration.unregister();
		}
	}

	public static void unregister(
		List<LearningActivityTypeFactory<?>> learningActivityFactories) {

		for (LearningActivityTypeFactory<?> learningActivityFactory :
				learningActivityFactories) {

			unregister(learningActivityFactory);
		}
	}

	private static Map<String, LearningActivityTypeFactory<?>>
		_filterLearningActivityFactories(
			long companyId,
			Map<String, LearningActivityTypeFactory<?>> learningActivityFactories,
			boolean filterSelectable) {

		Map<String, LearningActivityTypeFactory<?>> filteredLearningActivityFactories =
			new ConcurrentHashMap<>();

		for (Map.Entry<String, LearningActivityTypeFactory<?>> entry :
				learningActivityFactories.entrySet()) {

			LearningActivityTypeFactory<?> learningActivityFactory = entry.getValue();

			if (learningActivityFactory.isActive(companyId) &&
				(!filterSelectable || learningActivityFactory.isSelectable())) {

				filteredLearningActivityFactories.put(
					entry.getKey(), learningActivityFactory);
			}
		}

		return filteredLearningActivityFactories;
	}

	private LearningActivityTypeFactoryRegistryUtil() {
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LearningActivityTypeFactoryRegistryUtil.class);

	private static final Map<String, LearningActivityTypeFactory<?>>
		_learningActivityFactoriesMapByClassName = new ConcurrentHashMap<>();
	private static final Map<String, LearningActivityTypeFactory<?>>
		_learningActivityFactoriesMapByClassType = new ConcurrentHashMap<>();
	private static final ServiceRegistrationMap<LearningActivityTypeFactory<?>>
		_serviceRegistrations = new ServiceRegistrationMapImpl<>();
	private static final
		ServiceTracker<LearningActivityTypeFactory<?>, LearningActivityTypeFactory<?>>
			_serviceTracker;

	private static class LearningActivityTypeFactoryServiceTrackerCustomizer
		implements ServiceTrackerCustomizer
			<LearningActivityTypeFactory<?>, LearningActivityTypeFactory<?>> {

		@Override
		public LearningActivityTypeFactory<?> addingService(
			ServiceReference<LearningActivityTypeFactory<?>> serviceReference) {

			Registry registry = RegistryUtil.getRegistry();

			LearningActivityTypeFactory<?> learningActivityFactory = registry.getService(
				serviceReference);

			String className = learningActivityFactory.getClassName();

			LearningActivityTypeFactory<?> classNameLearningActivityTypeFactory =
				_learningActivityFactoriesMapByClassName.put(
					className, learningActivityFactory);

			if (_log.isWarnEnabled() &&
				(classNameLearningActivityTypeFactory != null)) {

				_log.warn(
					StringBundler.concat(
						"Replacing ",
						String.valueOf(classNameLearningActivityTypeFactory),
						" for class name ", className, " with ",
						String.valueOf(learningActivityFactory)));
			}

			String type = learningActivityFactory.getType();

			LearningActivityTypeFactory<?> typeLearningActivityTypeFactory =
				_learningActivityFactoriesMapByClassType.put(
					type, learningActivityFactory);

			if (_log.isWarnEnabled() && (typeLearningActivityTypeFactory != null)) {
				_log.warn(
					StringBundler.concat(
						"Replacing ", String.valueOf(typeLearningActivityTypeFactory),
						" for type ", type, " with ",
						String.valueOf(learningActivityFactory)));
			}

			return learningActivityFactory;
		}

		@Override
		public void modifiedService(
			ServiceReference<LearningActivityTypeFactory<?>> serviceReference,
			LearningActivityTypeFactory<?> learningActivityFactory) {
		}

		@Override
		public void removedService(
			ServiceReference<LearningActivityTypeFactory<?>> serviceReference,
			LearningActivityTypeFactory<?> learningActivityFactory) {

			Registry registry = RegistryUtil.getRegistry();

			registry.ungetService(serviceReference);

			_learningActivityFactoriesMapByClassName.remove(
				learningActivityFactory.getClassName());
			_learningActivityFactoriesMapByClassType.remove(
				learningActivityFactory.getType());
		}

	}

	static {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices(
			(Class<LearningActivityTypeFactory<?>>)(Class<?>)
				LearningActivityTypeFactory.class,
			new LearningActivityTypeFactoryServiceTrackerCustomizer());

		_serviceTracker.open();
	}
}
