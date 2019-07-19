package com.ted.postcondition.registry;

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
import com.ted.postcondition.model.PostconditionFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Registry para los tipos de calificación
 * @author Virginia Martín Agudo
 *
 */
@ProviderType
public class PostconditionFactoryRegistryUtil {
	
	private static final Log log = LogFactoryUtil.getLog(
		PostconditionFactoryRegistryUtil.class);

	private static final Map<String, PostconditionFactory>
		_postconditionFactoriesMapByClassName = new ConcurrentHashMap<>();
	private static final ServiceRegistrationMap<PostconditionFactory>
		_serviceRegistrations = new ServiceRegistrationMapImpl<>();
	private static final
		ServiceTracker<PostconditionFactory, PostconditionFactory>
			_serviceTracker;

	public static List<PostconditionFactory> getPostconditionFactories(
		long companyId) {

		return ListUtil.fromMapValues(
			_filterPostconditionFactories(
				companyId, _postconditionFactoriesMapByClassName));
	}

	public static PostconditionFactory getPostconditionFactoryByClassName(
		String className) {

		return _postconditionFactoriesMapByClassName.get(className);
	}

	public static PostconditionFactory getPostconditionFactoryByClassNameId(
		long classNameId) {

		return _postconditionFactoriesMapByClassName.get(
			PortalUtil.getClassName(classNameId));
	}

	public static long[] getClassNameIds(long companyId) {

		Map<String, PostconditionFactory> postconditionFactories =
			_postconditionFactoriesMapByClassName;

		if (companyId > 0) {
			postconditionFactories = _filterPostconditionFactories(
				companyId, _postconditionFactoriesMapByClassName);
		}

		long[] classNameIds = new long[postconditionFactories.size()];

		int i = 0;

		for (PostconditionFactory postconditionFactory :
				postconditionFactories.values()) {

			classNameIds[i] = postconditionFactory.getClassNameId();

			i++;
		}

		return classNameIds;
	}

	public static void register(PostconditionFactory postconditionFactory) {
		Registry registry = RegistryUtil.getRegistry();

		ServiceRegistration<PostconditionFactory> serviceRegistration =
			registry.registerService(
				(Class<PostconditionFactory>)(Class<?>)
					PostconditionFactory.class,
				postconditionFactory);

		_serviceRegistrations.put(postconditionFactory, serviceRegistration);
	}

	public static void register(
		List<PostconditionFactory> postconditionFactories) {

		for (PostconditionFactory postconditionFactory :
				postconditionFactories) {

			register(postconditionFactory);
		}
	}

	public static void unregister(
		PostconditionFactory postconditionFactory) {

		ServiceRegistration<PostconditionFactory> serviceRegistration =
			_serviceRegistrations.remove(postconditionFactory);

		if (serviceRegistration != null) {
			serviceRegistration.unregister();
		}
	}

	public static void unregister(
		List<PostconditionFactory> postconditionFactories) {

		for (PostconditionFactory postconditionFactory :
				postconditionFactories) {

			unregister(postconditionFactory);
		}
	}

	private static Map<String, PostconditionFactory>
		_filterPostconditionFactories(
			long companyId,
			Map<String, PostconditionFactory> postconditionFactories) {

		Map<String, PostconditionFactory> filteredPostconditionFactories =
			new ConcurrentHashMap<>();

		for (Map.Entry<String, PostconditionFactory> entry :
				postconditionFactories.entrySet()) {

			PostconditionFactory postconditionFactory = entry.getValue();

			filteredPostconditionFactories.put(
				entry.getKey(), postconditionFactory);
		}

		return filteredPostconditionFactories;
	}

	private PostconditionFactoryRegistryUtil() {
	}


	private static class PostconditionFactoryServiceTrackerCustomizer
		implements ServiceTrackerCustomizer
			<PostconditionFactory, PostconditionFactory> {

		@Override
		public PostconditionFactory addingService(
			ServiceReference<PostconditionFactory> serviceReference) {

			Registry registry = RegistryUtil.getRegistry();
			
			log.debug("serviceReference: " + serviceReference.toString());
			
			PostconditionFactory postconditionFactory = null;
			
			try {

				postconditionFactory = registry.getService(
					serviceReference);
	 
				String className = postconditionFactory.getClassName();
	
				PostconditionFactory classNamePostconditionFactory =
					_postconditionFactoriesMapByClassName.put(
						className, postconditionFactory);
	
				if (log.isWarnEnabled() &&
					(classNamePostconditionFactory != null)) {
	
					log.warn(
						StringBundler.concat(
							"Replacing ",
							String.valueOf(classNamePostconditionFactory),
							" for class name ", className, " with ",
							String.valueOf(postconditionFactory)));
				}
			} catch (ClassCastException e) {
				if(log.isDebugEnabled()) log.debug(e.getMessage());
			}

			return postconditionFactory;
		}

		@Override
		public void modifiedService(
			ServiceReference<PostconditionFactory> serviceReference,
			PostconditionFactory postconditionFactory) {
		}

		@Override
		public void removedService(
			ServiceReference<PostconditionFactory> serviceReference,
			PostconditionFactory postconditionFactory) {

			Registry registry = RegistryUtil.getRegistry();

			registry.ungetService(serviceReference);

			_postconditionFactoriesMapByClassName.remove(
				postconditionFactory.getClassName());
		}

	}

	static {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices(
			(Class<PostconditionFactory>)(Class<?>)
				PostconditionFactory.class,
			new PostconditionFactoryServiceTrackerCustomizer());

		_serviceTracker.open();
	}
}
