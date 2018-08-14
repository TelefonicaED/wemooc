package com.ted.lms.registry;

import aQute.bnd.annotation.ProviderType;

import com.ted.lms.model.CalificationTypeFactory;
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
public class CalificationTypeFactoryRegistryUtil {

	public static List<CalificationTypeFactory<?>> getCalificationFactories(
		long companyId) {

		return ListUtil.fromMapValues(
			_filterCalificationFactories(
				companyId, _calificationFactoriesMapByClassName));
	}

	public static <T> CalificationTypeFactory<T> getCalificationTypeFactoryByClass(
		Class<T> clazz) {

		return (CalificationTypeFactory<T>)_calificationFactoriesMapByClassName.get(
			clazz.getName());
	}

	public static CalificationTypeFactory<?> getCalificationTypeFactoryByClassName(
		String className) {

		return _calificationFactoriesMapByClassName.get(className);
	}

	public static CalificationTypeFactory<?> getCalificationTypeFactoryByClassNameId(
		long classNameId) {

		return _calificationFactoriesMapByClassName.get(
			PortalUtil.getClassName(classNameId));
	}

	public static CalificationTypeFactory<?> getCalificationTypeFactoryByType(
		long type) {

		return _calificationFactoriesMapByClassType.get(type);
	}

	public static long[] getClassNameIds(long companyId) {

		Map<String, CalificationTypeFactory<?>> calificationFactories =
			_calificationFactoriesMapByClassName;

		if (companyId > 0) {
			calificationFactories = _filterCalificationFactories(
				companyId, _calificationFactoriesMapByClassName);
		}

		long[] classNameIds = new long[calificationFactories.size()];

		int i = 0;

		for (CalificationTypeFactory<?> calificationFactory :
				calificationFactories.values()) {

			classNameIds[i] = calificationFactory.getClassNameId();

			i++;
		}

		return classNameIds;
	}

	public static void register(CalificationTypeFactory<?> calificationFactory) {
		Registry registry = RegistryUtil.getRegistry();

		ServiceRegistration<CalificationTypeFactory<?>> serviceRegistration =
			registry.registerService(
				(Class<CalificationTypeFactory<?>>)(Class<?>)
					CalificationTypeFactory.class,
				calificationFactory);

		_serviceRegistrations.put(calificationFactory, serviceRegistration);
	}

	public static void register(
		List<CalificationTypeFactory<?>> calificationFactories) {

		for (CalificationTypeFactory<?> calificationFactory :
				calificationFactories) {

			register(calificationFactory);
		}
	}

	public static void unregister(
		CalificationTypeFactory<?> calificationFactory) {

		ServiceRegistration<CalificationTypeFactory<?>> serviceRegistration =
			_serviceRegistrations.remove(calificationFactory);

		if (serviceRegistration != null) {
			serviceRegistration.unregister();
		}
	}

	public static void unregister(
		List<CalificationTypeFactory<?>> calificationFactories) {

		for (CalificationTypeFactory<?> calificationFactory :
				calificationFactories) {

			unregister(calificationFactory);
		}
	}

	private static Map<String, CalificationTypeFactory<?>>
		_filterCalificationFactories(
			long companyId,
			Map<String, CalificationTypeFactory<?>> calificationFactories) {

		Map<String, CalificationTypeFactory<?>> filteredCalificationFactories =
			new ConcurrentHashMap<>();

		for (Map.Entry<String, CalificationTypeFactory<?>> entry :
				calificationFactories.entrySet()) {

			CalificationTypeFactory<?> calificationFactory = entry.getValue();

			filteredCalificationFactories.put(
				entry.getKey(), calificationFactory);
		}

		return filteredCalificationFactories;
	}

	private CalificationTypeFactoryRegistryUtil() {
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CalificationTypeFactoryRegistryUtil.class);

	private static final Map<String, CalificationTypeFactory<?>>
		_calificationFactoriesMapByClassName = new ConcurrentHashMap<>();
	private static final Map<Integer, CalificationTypeFactory<?>>
		_calificationFactoriesMapByClassType = new ConcurrentHashMap<>();
	private static final ServiceRegistrationMap<CalificationTypeFactory<?>>
		_serviceRegistrations = new ServiceRegistrationMapImpl<>();
	private static final
		ServiceTracker<CalificationTypeFactory<?>, CalificationTypeFactory<?>>
			_serviceTracker;

	private static class CalificationTypeFactoryServiceTrackerCustomizer
		implements ServiceTrackerCustomizer
			<CalificationTypeFactory<?>, CalificationTypeFactory<?>> {

		@Override
		public CalificationTypeFactory<?> addingService(
			ServiceReference<CalificationTypeFactory<?>> serviceReference) {

			Registry registry = RegistryUtil.getRegistry();

			CalificationTypeFactory<?> calificationFactory = registry.getService(
				serviceReference);

			String className = calificationFactory.getClassName();

			CalificationTypeFactory<?> classNameCalificationTypeFactory =
				_calificationFactoriesMapByClassName.put(
					className, calificationFactory);

			if (_log.isWarnEnabled() &&
				(classNameCalificationTypeFactory != null)) {

				_log.warn(
					StringBundler.concat(
						"Replacing ",
						String.valueOf(classNameCalificationTypeFactory),
						" for class name ", className, " with ",
						String.valueOf(calificationFactory)));
			}

			int type = calificationFactory.getType();

			CalificationTypeFactory<?> typeCalificationTypeFactory =
				_calificationFactoriesMapByClassType.put(
					type, calificationFactory);

			if (_log.isWarnEnabled() && (typeCalificationTypeFactory != null)) {
				_log.warn(
					StringBundler.concat(
						"Replacing ", String.valueOf(typeCalificationTypeFactory),
						" for type ", String.valueOf(type), " with ",
						String.valueOf(calificationFactory)));
			}

			return calificationFactory;
		}

		@Override
		public void modifiedService(
			ServiceReference<CalificationTypeFactory<?>> serviceReference,
			CalificationTypeFactory<?> calificationFactory) {
		}

		@Override
		public void removedService(
			ServiceReference<CalificationTypeFactory<?>> serviceReference,
			CalificationTypeFactory<?> calificationFactory) {

			Registry registry = RegistryUtil.getRegistry();

			registry.ungetService(serviceReference);

			_calificationFactoriesMapByClassName.remove(
				calificationFactory.getClassName());
			_calificationFactoriesMapByClassType.remove(
				calificationFactory.getType());
		}

	}

	static {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices(
			(Class<CalificationTypeFactory<?>>)(Class<?>)
				CalificationTypeFactory.class,
			new CalificationTypeFactoryServiceTrackerCustomizer());

		_serviceTracker.open();
	}
}
