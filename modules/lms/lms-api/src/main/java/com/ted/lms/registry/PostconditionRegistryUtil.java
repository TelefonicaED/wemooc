package com.ted.lms.registry;

import aQute.bnd.annotation.ProviderType;

import com.ted.lms.model.Postcondition;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ListUtil;
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
 * Registry para las postcondiciones de finalización de curso
 * @author Virginia Martín Agudo
 *
 */
@ProviderType
public class PostconditionRegistryUtil {
	
	private static final Log log = LogFactoryUtil.getLog(
		PostconditionRegistryUtil.class);

	private static final Map<String, Postcondition>
		_postconditions = new ConcurrentHashMap<>();
	private static final ServiceRegistrationMap<Postcondition>
		_serviceRegistrations = new ServiceRegistrationMapImpl<>();
	private static final
		ServiceTracker<Postcondition, Postcondition>
			_serviceTracker;

	public static List<Postcondition> getPostconditions(long companyId) {

		return ListUtil.fromMapValues(
			_filterPostconditions(
				companyId, _postconditions));
	}

	public static void register(Postcondition postcondition) {
		Registry registry = RegistryUtil.getRegistry();

		ServiceRegistration<Postcondition> serviceRegistration =
			registry.registerService(
				(Class<Postcondition>)(Class<?>)
					Postcondition.class,
				postcondition);

		_serviceRegistrations.put(postcondition, serviceRegistration);
	}

	public static void register(
		List<Postcondition> postconditions) {

		for (Postcondition postconditionFactory :
				postconditions) {

			register(postconditionFactory);
		}
	}

	public static void unregister(
		Postcondition postconditionFactory) {

		ServiceRegistration<Postcondition> serviceRegistration =
			_serviceRegistrations.remove(postconditionFactory);

		if (serviceRegistration != null) {
			serviceRegistration.unregister();
		}
	}

	public static void unregister(
		List<Postcondition> postconditionFactories) {

		for (Postcondition postconditionFactory :
				postconditionFactories) {

			unregister(postconditionFactory);
		}
	}

	private static Map<String, Postcondition>
		_filterPostconditions(
			long companyId,
			Map<String, Postcondition> postconditions) {

		Map<String, Postcondition> filteredPostconditions =
			new ConcurrentHashMap<>();

		for (Map.Entry<String, Postcondition> entry :
				postconditions.entrySet()) {

			Postcondition postcondition = entry.getValue();

			filteredPostconditions.put(
				entry.getKey(), postcondition);
		}

		return filteredPostconditions;
	}

	private PostconditionRegistryUtil() {
	}


	private static class PostconditionServiceTrackerCustomizer
		implements ServiceTrackerCustomizer
			<Postcondition, Postcondition> {

		@Override
		public Postcondition addingService(
			ServiceReference<Postcondition> serviceReference) {

			Registry registry = RegistryUtil.getRegistry();
			
			log.debug("serviceReference: " + serviceReference.toString());
			
			Postcondition postcondition = null;
			
			try {

				postcondition = registry.getService(
					serviceReference);
	 
				String className = postcondition.getClassName();
	
				Postcondition classNamePostcondition =
					_postconditions.put(
						className, postcondition);
	
				if (log.isWarnEnabled() &&
					(classNamePostcondition != null)) {
	
					log.warn(
						StringBundler.concat(
							"Replacing ",
							String.valueOf(classNamePostcondition),
							" for class name ", className, " with ",
							String.valueOf(postcondition)));
				}
			} catch (ClassCastException e) {
				if(log.isDebugEnabled()) log.debug(e.getMessage());
			}

			return postcondition;
		}

		@Override
		public void modifiedService(
			ServiceReference<Postcondition> serviceReference,
			Postcondition postconditionFactory) {
		}

		@Override
		public void removedService(
			ServiceReference<Postcondition> serviceReference,
			Postcondition postcondition) {

			Registry registry = RegistryUtil.getRegistry();

			registry.ungetService(serviceReference);

			_postconditions.remove(
				postcondition.getClassName());
		}

	}

	static {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices(
			(Class<Postcondition>)(Class<?>)
				Postcondition.class,
			new PostconditionServiceTrackerCustomizer());

		_serviceTracker.open();
	}
}
