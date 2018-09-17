package com.ted.audit.api.registry;

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
import com.ted.audit.api.Audit;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AuditRegistryUtil {
	private static final Log log = LogFactoryUtil.getLog(AuditRegistryUtil.class);

		private static final Map<Long, Audit>
			_audits = new ConcurrentHashMap<>();
		private static final ServiceRegistrationMap<Audit>
			_serviceRegistrations = new ServiceRegistrationMapImpl<>();
		private static final
			ServiceTracker<Audit, Audit>
				_serviceTracker;

		public static List<Audit> getAudits(long companyId) {

			return ListUtil.fromMapValues(_audits);
		}

		public static void register(Audit audit) {
			Registry registry = RegistryUtil.getRegistry();

			ServiceRegistration<Audit> serviceRegistration =
				registry.registerService(
					(Class<Audit>)(Class<?>)
						Audit.class,
					audit);

			_serviceRegistrations.put(audit, serviceRegistration);
		}

		public static void register(
			List<Audit> audits) {

			for (Audit auditFactory :
					audits) {

				register(auditFactory);
			}
		}

		public static void unregister(
			Audit auditFactory) {

			ServiceRegistration<Audit> serviceRegistration =
				_serviceRegistrations.remove(auditFactory);

			if (serviceRegistration != null) {
				serviceRegistration.unregister();
			}
		}

		public static void unregister(
			List<Audit> auditFactories) {

			for (Audit auditFactory :
					auditFactories) {

				unregister(auditFactory);
			}
		}

		private AuditRegistryUtil() {
		}


		private static class AuditServiceTrackerCustomizer
			implements ServiceTrackerCustomizer	<Audit, Audit> {

			@Override
			public Audit addingService(
				ServiceReference<Audit> serviceReference) {

				Registry registry = RegistryUtil.getRegistry();
				
				log.debug("serviceReference: " + serviceReference.toString());
				
				Audit audit = null;
				
				try {

					audit = registry.getService(
						serviceReference);
		 
					long classNameId = audit.getClassNameId();
		
					Audit classNameAudit =
						_audits.put(
							classNameId, audit);
		
					if (log.isWarnEnabled() &&
						(classNameAudit != null)) {
		
						log.warn(
							StringBundler.concat(
								"Replacing ",
								String.valueOf(classNameAudit),
								" for class name id ", String.valueOf(classNameId), " with ",
								String.valueOf(audit)));
					}
				} catch (ClassCastException e) {
					if(log.isDebugEnabled()) log.debug(e.getMessage());
				}

				return audit;
			}

			@Override
			public void modifiedService(
				ServiceReference<Audit> serviceReference,
				Audit auditFactory) {
			}

			@Override
			public void removedService(
				ServiceReference<Audit> serviceReference,
				Audit audit) {

				Registry registry = RegistryUtil.getRegistry();

				registry.ungetService(serviceReference);

				_audits.remove(
					audit.getClassNameId());
			}

		}

		static {
			Registry registry = RegistryUtil.getRegistry();

			_serviceTracker = registry.trackServices(
				(Class<Audit>)(Class<?>)
					Audit.class,
				new AuditServiceTrackerCustomizer());

			_serviceTracker.open();
		}
}
