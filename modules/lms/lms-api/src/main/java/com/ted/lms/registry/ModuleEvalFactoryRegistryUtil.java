package com.ted.lms.registry;

import aQute.bnd.annotation.ProviderType;

import com.ted.lms.model.ModuleEvalFactory;
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
 * Registry para los métodos de evaluación de los módulos
 * @author Virginia Martín Agudo
 *
 */
@ProviderType
public class ModuleEvalFactoryRegistryUtil {

	public static List<ModuleEvalFactory> getCalificationFactories(
		long companyId) {

		return ListUtil.fromMapValues(
			_filterCalificationFactories(
				companyId, _moduleEvalFactoriesMapByClassName));
	}

	public static <T> ModuleEvalFactory getModuleEvalFactoryByClass(
		Class<T> clazz) {

		return (ModuleEvalFactory)_moduleEvalFactoriesMapByClassName.get(
			clazz.getName());
	}

	public static ModuleEvalFactory getModuleEvalFactoryByClassName(
		String className) {

		return _moduleEvalFactoriesMapByClassName.get(className);
	}

	public static ModuleEvalFactory getModuleEvalFactoryByClassNameId(
		long classNameId) {

		return _moduleEvalFactoriesMapByClassName.get(
			PortalUtil.getClassName(classNameId));
	}

	public static ModuleEvalFactory getModuleEvalFactoryByType(
		int type) {

		return _moduleEvalFactoriesMapByClassType.get(type);
	}

	public static long[] getClassNameIds(long companyId) {

		Map<String, ModuleEvalFactory> moduleEvalFactories =
			_moduleEvalFactoriesMapByClassName;

		if (companyId > 0) {
			moduleEvalFactories = _filterCalificationFactories(
				companyId, _moduleEvalFactoriesMapByClassName);
		}

		long[] classNameIds = new long[moduleEvalFactories.size()];

		int i = 0;

		for (ModuleEvalFactory moduleEvalFactory :
				moduleEvalFactories.values()) {

			classNameIds[i] = moduleEvalFactory.getClassNameId();

			i++;
		}

		return classNameIds;
	}

	public static void register(ModuleEvalFactory moduleEvalFactory) {
		Registry registry = RegistryUtil.getRegistry();

		ServiceRegistration<ModuleEvalFactory> serviceRegistration =
			registry.registerService(
				(Class<ModuleEvalFactory>)(Class<?>)
					ModuleEvalFactory.class,
				moduleEvalFactory);

		_serviceRegistrations.put(moduleEvalFactory, serviceRegistration);
	}

	public static void register(
		List<ModuleEvalFactory> moduleEvalFactories) {

		for (ModuleEvalFactory moduleEvalFactory :
				moduleEvalFactories) {

			register(moduleEvalFactory);
		}
	}

	public static void unregister(
		ModuleEvalFactory moduleEvalFactory) {

		ServiceRegistration<ModuleEvalFactory> serviceRegistration =
			_serviceRegistrations.remove(moduleEvalFactory);

		if (serviceRegistration != null) {
			serviceRegistration.unregister();
		}
	}

	public static void unregister(
		List<ModuleEvalFactory> moduleEvalFactories) {

		for (ModuleEvalFactory moduleEvalFactory :
				moduleEvalFactories) {

			unregister(moduleEvalFactory);
		}
	}

	private static Map<String, ModuleEvalFactory>
		_filterCalificationFactories(
			long companyId,
			Map<String, ModuleEvalFactory> moduleEvalFactories) {

		Map<String, ModuleEvalFactory> filteredCalificationFactories =
			new ConcurrentHashMap<>();

		for (Map.Entry<String, ModuleEvalFactory> entry :
				moduleEvalFactories.entrySet()) {

			ModuleEvalFactory moduleEvalFactory = entry.getValue();

			filteredCalificationFactories.put(
				entry.getKey(), moduleEvalFactory);
		}

		return filteredCalificationFactories;
	}

	private ModuleEvalFactoryRegistryUtil() {
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ModuleEvalFactoryRegistryUtil.class);

	private static final Map<String, ModuleEvalFactory>
		_moduleEvalFactoriesMapByClassName = new ConcurrentHashMap<>();
	private static final Map<Integer, ModuleEvalFactory>
		_moduleEvalFactoriesMapByClassType = new ConcurrentHashMap<>();
	private static final ServiceRegistrationMap<ModuleEvalFactory>
		_serviceRegistrations = new ServiceRegistrationMapImpl<>();
	private static final
		ServiceTracker<ModuleEvalFactory, ModuleEvalFactory>
			_serviceTracker;

	private static class ModuleEvalFactoryServiceTrackerCustomizer
		implements ServiceTrackerCustomizer
			<ModuleEvalFactory, ModuleEvalFactory> {

		@Override
		public ModuleEvalFactory addingService(
			ServiceReference<ModuleEvalFactory> serviceReference) {

			Registry registry = RegistryUtil.getRegistry();
			
			ModuleEvalFactory moduleEvalFactory = null;
			
			try {

				moduleEvalFactory = registry.getService(
					serviceReference);
	
				String className = moduleEvalFactory.getClassName();
	
				ModuleEvalFactory classNameModuleEvalFactory =
					_moduleEvalFactoriesMapByClassName.put(
						className, moduleEvalFactory);
	
				if (_log.isWarnEnabled() &&
					(classNameModuleEvalFactory != null)) {
	
					_log.warn(
						StringBundler.concat(
							"Replacing ",
							String.valueOf(classNameModuleEvalFactory),
							" for class name ", className, " with ",
							String.valueOf(moduleEvalFactory)));
				}
	
				int type = moduleEvalFactory.getType();
	
				ModuleEvalFactory typeModuleEvalFactory =
					_moduleEvalFactoriesMapByClassType.put(
						type, moduleEvalFactory);
	
				if (_log.isWarnEnabled() && (typeModuleEvalFactory != null)) {
					_log.warn(
						StringBundler.concat(
							"Replacing ", String.valueOf(typeModuleEvalFactory),
							" for type ", String.valueOf(type), " with ",
							String.valueOf(moduleEvalFactory)));
				}
				
			} catch (ClassCastException e) {
				if(_log.isDebugEnabled()) _log.debug(e.getMessage());
			}

			return moduleEvalFactory;
		}

		@Override
		public void modifiedService(
			ServiceReference<ModuleEvalFactory> serviceReference,
			ModuleEvalFactory moduleEvalFactory) {
		}

		@Override
		public void removedService(
			ServiceReference<ModuleEvalFactory> serviceReference,
			ModuleEvalFactory moduleEvalFactory) {

			Registry registry = RegistryUtil.getRegistry();

			registry.ungetService(serviceReference);

			_moduleEvalFactoriesMapByClassName.remove(
				moduleEvalFactory.getClassName());
			_moduleEvalFactoriesMapByClassType.remove(
				moduleEvalFactory.getType());
		}

	}

	static {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices(
			(Class<ModuleEvalFactory>)(Class<?>)
				ModuleEvalFactory.class,
			new ModuleEvalFactoryServiceTrackerCustomizer());

		_serviceTracker.open();
	}
}
