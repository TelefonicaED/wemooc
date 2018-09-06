package com.ted.prerequisite.registry;

import aQute.bnd.annotation.ProviderType;

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
import com.ted.prerequisite.model.PrerequisiteFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Registry para los tipos de calificación
 * @author Virginia Martín Agudo
 *
 */
@ProviderType
public class PrerequisiteFactoryRegistryUtil {
	
	private static final Log log = LogFactoryUtil.getLog(
		PrerequisiteFactoryRegistryUtil.class);

	private static final Map<String, PrerequisiteFactory>
		_prerequisiteFactoriesMapByClassName = new ConcurrentHashMap<>();
	private static final ServiceRegistrationMap<PrerequisiteFactory>
		_serviceRegistrations = new ServiceRegistrationMapImpl<>();
	private static final
		ServiceTracker<PrerequisiteFactory, PrerequisiteFactory>
			_serviceTracker;

	public static List<PrerequisiteFactory> getPrerequisiteFactories(
		long companyId) {

		return ListUtil.fromMapValues(
			_filterPrerequisiteFactories(
				companyId, _prerequisiteFactoriesMapByClassName));
	}

	public static PrerequisiteFactory getPrerequisiteFactoryByClassName(
		String className) {

		return _prerequisiteFactoriesMapByClassName.get(className);
	}

	public static PrerequisiteFactory getPrerequisiteFactoryByClassNameId(
		long classNameId) {

		return _prerequisiteFactoriesMapByClassName.get(
			PortalUtil.getClassName(classNameId));
	}

	public static long[] getClassNameIds(long companyId) {

		Map<String, PrerequisiteFactory> prerequisiteFactories =
			_prerequisiteFactoriesMapByClassName;

		if (companyId > 0) {
			prerequisiteFactories = _filterPrerequisiteFactories(
				companyId, _prerequisiteFactoriesMapByClassName);
		}

		long[] classNameIds = new long[prerequisiteFactories.size()];

		int i = 0;

		for (PrerequisiteFactory prerequisiteFactory :
				prerequisiteFactories.values()) {

			classNameIds[i] = prerequisiteFactory.getClassNameId();

			i++;
		}

		return classNameIds;
	}

	public static void register(PrerequisiteFactory prerequisiteFactory) {
		Registry registry = RegistryUtil.getRegistry();

		ServiceRegistration<PrerequisiteFactory> serviceRegistration =
			registry.registerService(
				(Class<PrerequisiteFactory>)(Class<?>)
					PrerequisiteFactory.class,
				prerequisiteFactory);

		_serviceRegistrations.put(prerequisiteFactory, serviceRegistration);
	}

	public static void register(
		List<PrerequisiteFactory> prerequisiteFactories) {

		for (PrerequisiteFactory prerequisiteFactory :
				prerequisiteFactories) {

			register(prerequisiteFactory);
		}
	}

	public static void unregister(
		PrerequisiteFactory prerequisiteFactory) {

		ServiceRegistration<PrerequisiteFactory> serviceRegistration =
			_serviceRegistrations.remove(prerequisiteFactory);

		if (serviceRegistration != null) {
			serviceRegistration.unregister();
		}
	}

	public static void unregister(
		List<PrerequisiteFactory> prerequisiteFactories) {

		for (PrerequisiteFactory prerequisiteFactory :
				prerequisiteFactories) {

			unregister(prerequisiteFactory);
		}
	}

	private static Map<String, PrerequisiteFactory>
		_filterPrerequisiteFactories(
			long companyId,
			Map<String, PrerequisiteFactory> prerequisiteFactories) {

		Map<String, PrerequisiteFactory> filteredPrerequisiteFactories =
			new ConcurrentHashMap<>();

		for (Map.Entry<String, PrerequisiteFactory> entry :
				prerequisiteFactories.entrySet()) {

			PrerequisiteFactory prerequisiteFactory = entry.getValue();

			filteredPrerequisiteFactories.put(
				entry.getKey(), prerequisiteFactory);
		}

		return filteredPrerequisiteFactories;
	}

	private PrerequisiteFactoryRegistryUtil() {
	}


	private static class PrerequisiteFactoryServiceTrackerCustomizer
		implements ServiceTrackerCustomizer
			<PrerequisiteFactory, PrerequisiteFactory> {

		@Override
		public PrerequisiteFactory addingService(
			ServiceReference<PrerequisiteFactory> serviceReference) {

			Registry registry = RegistryUtil.getRegistry();
			
			log.debug("serviceReference: " + serviceReference.toString());
			
			PrerequisiteFactory prerequisiteFactory = null;
			
			try {

				prerequisiteFactory = registry.getService(
					serviceReference);
	 
				String className = prerequisiteFactory.getClassName();
	
				PrerequisiteFactory classNamePrerequisiteFactory =
					_prerequisiteFactoriesMapByClassName.put(
						className, prerequisiteFactory);
	
				if (log.isWarnEnabled() &&
					(classNamePrerequisiteFactory != null)) {
	
					log.warn(
						StringBundler.concat(
							"Replacing ",
							String.valueOf(classNamePrerequisiteFactory),
							" for class name ", className, " with ",
							String.valueOf(prerequisiteFactory)));
				}
			} catch (ClassCastException e) {
				if(log.isDebugEnabled()) log.debug(e.getMessage());
			}

			return prerequisiteFactory;
		}

		@Override
		public void modifiedService(
			ServiceReference<PrerequisiteFactory> serviceReference,
			PrerequisiteFactory prerequisiteFactory) {
		}

		@Override
		public void removedService(
			ServiceReference<PrerequisiteFactory> serviceReference,
			PrerequisiteFactory prerequisiteFactory) {

			Registry registry = RegistryUtil.getRegistry();

			registry.ungetService(serviceReference);

			_prerequisiteFactoriesMapByClassName.remove(
				prerequisiteFactory.getClassName());
		}

	}

	static {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices(
			(Class<PrerequisiteFactory>)(Class<?>)
				PrerequisiteFactory.class,
			new PrerequisiteFactoryServiceTrackerCustomizer());

		_serviceTracker.open();
	}
}
