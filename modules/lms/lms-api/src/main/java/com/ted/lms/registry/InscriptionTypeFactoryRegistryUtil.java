package com.ted.lms.registry;

import com.ted.lms.model.InscriptionTypeFactory;
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

import org.osgi.annotation.versioning.ProviderType;

/**
 * Registry para los tipos de inscripción a los cursos
 * @author Virginia Martín Agudo
 *
 */
@ProviderType
public class InscriptionTypeFactoryRegistryUtil {

	public static List<InscriptionTypeFactory> getInscriptionFactories(
		long companyId) {

		return ListUtil.fromMapValues(
			_filterInscriptionFactories(
				companyId, _inscriptionFactoriesMapByClassName));
	}

	public static <T> InscriptionTypeFactory getInscriptionTypeFactoryByClass(
		Class<T> clazz) {

		return (InscriptionTypeFactory)_inscriptionFactoriesMapByClassName.get(
			clazz.getName());
	}

	public static InscriptionTypeFactory getInscriptionTypeFactoryByClassName(
		String className) {

		return _inscriptionFactoriesMapByClassName.get(className);
	}

	public static InscriptionTypeFactory getInscriptionTypeFactoryByClassNameId(
		long classNameId) {

		return _inscriptionFactoriesMapByClassName.get(
			PortalUtil.getClassName(classNameId));
	}

	public static InscriptionTypeFactory getInscriptionTypeFactoryByType(
		long type) {

		return _inscriptionFactoriesMapByClassType.get(type);
	}

	public static long[] getClassNameIds(long companyId) {

		Map<String, InscriptionTypeFactory> inscriptionFactories =
			_inscriptionFactoriesMapByClassName;

		if (companyId > 0) {
			inscriptionFactories = _filterInscriptionFactories(
				companyId, _inscriptionFactoriesMapByClassName);
		}

		long[] classNameIds = new long[inscriptionFactories.size()];

		int i = 0;

		for (InscriptionTypeFactory inscriptionFactory :
				inscriptionFactories.values()) {

			classNameIds[i] = inscriptionFactory.getClassNameId();

			i++;
		}

		return classNameIds;
	}

	public static void register(InscriptionTypeFactory inscriptionFactory) {
		Registry registry = RegistryUtil.getRegistry();

		ServiceRegistration<InscriptionTypeFactory> serviceRegistration =
			registry.registerService(
				(Class<InscriptionTypeFactory>)(Class<?>)
					InscriptionTypeFactory.class,
				inscriptionFactory);

		_serviceRegistrations.put(inscriptionFactory, serviceRegistration);
	}

	public static void register(
		List<InscriptionTypeFactory> inscriptionFactories) {

		for (InscriptionTypeFactory inscriptionFactory :
				inscriptionFactories) {

			register(inscriptionFactory);
		}
	}

	public static void unregister(
		InscriptionTypeFactory inscriptionFactory) {

		ServiceRegistration<InscriptionTypeFactory> serviceRegistration =
			_serviceRegistrations.remove(inscriptionFactory);

		if (serviceRegistration != null) {
			serviceRegistration.unregister();
		}
	}

	public static void unregister(
		List<InscriptionTypeFactory> inscriptionFactories) {

		for (InscriptionTypeFactory inscriptionFactory :
				inscriptionFactories) {

			unregister(inscriptionFactory);
		}
	}

	private static Map<String, InscriptionTypeFactory>
		_filterInscriptionFactories(
			long companyId,
			Map<String, InscriptionTypeFactory> inscriptionFactories) {

		Map<String, InscriptionTypeFactory> filteredInscriptionFactories =
			new ConcurrentHashMap<>();

		for (Map.Entry<String, InscriptionTypeFactory> entry :
				inscriptionFactories.entrySet()) {

			InscriptionTypeFactory inscriptionFactory = entry.getValue();

			filteredInscriptionFactories.put(
				entry.getKey(), inscriptionFactory);
		}

		return filteredInscriptionFactories;
	}

	private InscriptionTypeFactoryRegistryUtil() {
	}

	private static final Log _log = LogFactoryUtil.getLog(
		InscriptionTypeFactoryRegistryUtil.class);

	private static final Map<String, InscriptionTypeFactory>
		_inscriptionFactoriesMapByClassName = new ConcurrentHashMap<>();
	private static final Map<Long, InscriptionTypeFactory>
		_inscriptionFactoriesMapByClassType = new ConcurrentHashMap<>();
	private static final ServiceRegistrationMap<InscriptionTypeFactory>
		_serviceRegistrations = new ServiceRegistrationMapImpl<>();
	private static final
		ServiceTracker<InscriptionTypeFactory, InscriptionTypeFactory>
			_serviceTracker;

	private static class InscriptionTypeFactoryServiceTrackerCustomizer
		implements ServiceTrackerCustomizer
			<InscriptionTypeFactory, InscriptionTypeFactory> {

		@Override
		public InscriptionTypeFactory addingService(
			ServiceReference<InscriptionTypeFactory> serviceReference) {

			Registry registry = RegistryUtil.getRegistry();
			
			InscriptionTypeFactory inscriptionFactory = null;

			try {
				inscriptionFactory = registry.getService(
					serviceReference);
	
				String className = inscriptionFactory.getClassName();
	
				InscriptionTypeFactory classNameInscriptionTypeFactory =
					_inscriptionFactoriesMapByClassName.put(
						className, inscriptionFactory);
	
				if (_log.isWarnEnabled() &&
					(classNameInscriptionTypeFactory != null)) {
	
					_log.warn(
						StringBundler.concat(
							"Replacing ",
							String.valueOf(classNameInscriptionTypeFactory),
							" for class name ", className, " with ",
							String.valueOf(inscriptionFactory)));
				}
	
				long type = inscriptionFactory.getType();
	
				InscriptionTypeFactory typeInscriptionTypeFactory =
					_inscriptionFactoriesMapByClassType.put(
						type, inscriptionFactory);
	
				if (_log.isWarnEnabled() && (typeInscriptionTypeFactory != null)) {
					_log.warn(
						StringBundler.concat(
							"Replacing ", String.valueOf(typeInscriptionTypeFactory),
							" for type ", String.valueOf(type), " with ",
							String.valueOf(inscriptionFactory)));
				}
				
			} catch (ClassCastException e) {
				if(_log.isDebugEnabled()) _log.debug(e.getMessage());
			}

			return inscriptionFactory;
		}

		@Override
		public void modifiedService(
			ServiceReference<InscriptionTypeFactory> serviceReference,
			InscriptionTypeFactory inscriptionFactory) {
		}

		@Override
		public void removedService(
			ServiceReference<InscriptionTypeFactory> serviceReference,
			InscriptionTypeFactory inscriptionFactory) {

			Registry registry = RegistryUtil.getRegistry();

			registry.ungetService(serviceReference);

			_inscriptionFactoriesMapByClassName.remove(
				inscriptionFactory.getClassName());
			_inscriptionFactoriesMapByClassType.remove(
				inscriptionFactory.getType());
		}

	}

	static {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices(
			(Class<InscriptionTypeFactory>)(Class<?>)
				InscriptionTypeFactory.class,
			new InscriptionTypeFactoryServiceTrackerCustomizer());

		_serviceTracker.open();
	}
}
