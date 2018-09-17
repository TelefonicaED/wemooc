package com.ted.lms.registry;

import aQute.bnd.annotation.ProviderType;

import com.ted.lms.model.CourseEvalFactory;
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
 * Registry para los métodos de evaluación de cursos
 * @author Virginia Martín Agudo
 *
 */
@ProviderType
public class CourseEvalFactoryRegistryUtil {

	public static List<CourseEvalFactory> getCourseEvalFactories(
		long companyId) {

		return ListUtil.fromMapValues(
			_filterCalificationFactories(
				companyId, _courseEvalFactoriesMapByClassName));
	}

	public static <T> CourseEvalFactory getCourseEvalFactoryByClass(
		Class<T> clazz) {

		return (CourseEvalFactory)_courseEvalFactoriesMapByClassName.get(
			clazz.getName());
	}

	public static CourseEvalFactory getCourseEvalFactoryByClassName(
		String className) {

		return _courseEvalFactoriesMapByClassName.get(className);
	}

	public static CourseEvalFactory getCourseEvalFactoryByClassNameId(
		long classNameId) {

		return _courseEvalFactoriesMapByClassName.get(
			PortalUtil.getClassName(classNameId));
	}

	public static CourseEvalFactory getCourseEvalFactoryByType(
		long type) {

		return _courseEvalFactoriesMapByClassType.get(type);
	}

	public static long[] getClassNameIds(long companyId) {

		Map<String, CourseEvalFactory> courseEvalFactories =
			_courseEvalFactoriesMapByClassName;

		if (companyId > 0) {
			courseEvalFactories = _filterCalificationFactories(
				companyId, _courseEvalFactoriesMapByClassName);
		}

		long[] classNameIds = new long[courseEvalFactories.size()];

		int i = 0;

		for (CourseEvalFactory courseEvalFactory :
				courseEvalFactories.values()) {

			classNameIds[i] = courseEvalFactory.getClassNameId();

			i++;
		}

		return classNameIds;
	}

	public static void register(CourseEvalFactory courseEvalFactory) {
		Registry registry = RegistryUtil.getRegistry();
		
		_log.debug("register: " + courseEvalFactory.getClassName());
		ServiceRegistration<CourseEvalFactory> serviceRegistration =
			registry.registerService(
				(Class<CourseEvalFactory>)(Class<?>)
					CourseEvalFactory.class,
				courseEvalFactory);

		_serviceRegistrations.put(courseEvalFactory, serviceRegistration);
	}

	public static void register(
		List<CourseEvalFactory> courseEvalFactories) {

		for (CourseEvalFactory courseEvalFactory :
				courseEvalFactories) {
			_log.debug("register: " + courseEvalFactory.getClassName());
			register(courseEvalFactory);
		}
	}

	public static void unregister(
		CourseEvalFactory courseEvalFactory) {
		_log.debug("unregister: " + courseEvalFactory.getClassName());
		ServiceRegistration<CourseEvalFactory> serviceRegistration =
			_serviceRegistrations.remove(courseEvalFactory);

		if (serviceRegistration != null) {
			serviceRegistration.unregister();
		}
	}

	public static void unregister(
		List<CourseEvalFactory> courseEvalFactories) {

		for (CourseEvalFactory courseEvalFactory :
				courseEvalFactories) {
			_log.debug("unregister: " + courseEvalFactory.getClassName());
			unregister(courseEvalFactory);
		}
	}

	private static Map<String, CourseEvalFactory>
		_filterCalificationFactories(
			long companyId,
			Map<String, CourseEvalFactory> courseEvalFactories) {

		Map<String, CourseEvalFactory> filteredCalificationFactories =
			new ConcurrentHashMap<>();

		for (Map.Entry<String, CourseEvalFactory> entry :
				courseEvalFactories.entrySet()) {

			CourseEvalFactory courseEvalFactory = entry.getValue();

			filteredCalificationFactories.put(
				entry.getKey(), courseEvalFactory);
		}

		return filteredCalificationFactories;
	}

	private CourseEvalFactoryRegistryUtil() {
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CourseEvalFactoryRegistryUtil.class);

	private static final Map<String, CourseEvalFactory>
		_courseEvalFactoriesMapByClassName = new ConcurrentHashMap<>();
	private static final Map<Long, CourseEvalFactory>
		_courseEvalFactoriesMapByClassType = new ConcurrentHashMap<>();
	private static final ServiceRegistrationMap<CourseEvalFactory>
		_serviceRegistrations = new ServiceRegistrationMapImpl<>();
	private static final
		ServiceTracker<CourseEvalFactory, CourseEvalFactory>
			_serviceTracker;

	private static class CourseEvalFactoryServiceTrackerCustomizer
		implements ServiceTrackerCustomizer
			<CourseEvalFactory, CourseEvalFactory> {

		@Override
		public CourseEvalFactory addingService(
			ServiceReference<CourseEvalFactory> serviceReference) {
			_log.debug("addService: " + serviceReference.getClass().getName());
			Registry registry = RegistryUtil.getRegistry();
			
			CourseEvalFactory courseEvalFactory = null;

			try {
			
				courseEvalFactory = registry.getService(
					serviceReference);
	
				String className = courseEvalFactory.getClassName();
				_log.debug("className: " + className);
				CourseEvalFactory classNameCourseEvalFactory =
					_courseEvalFactoriesMapByClassName.put(
						className, courseEvalFactory);
	
				if (_log.isWarnEnabled() &&
					(classNameCourseEvalFactory != null)) {
	
					_log.warn(
						StringBundler.concat(
							"Replacing ",
							String.valueOf(classNameCourseEvalFactory),
							" for class name ", className, " with ",
							String.valueOf(courseEvalFactory)));
				}
	
				long type = courseEvalFactory.getType();
	
				CourseEvalFactory typeCourseEvalFactory =
					_courseEvalFactoriesMapByClassType.put(
						type, courseEvalFactory);
	
				if (_log.isWarnEnabled() && (typeCourseEvalFactory != null)) {
					_log.warn(
						StringBundler.concat(
							"Replacing ", String.valueOf(typeCourseEvalFactory),
							" for type ", String.valueOf(type), " with ",
							String.valueOf(courseEvalFactory)));
				}
			} catch (ClassCastException e) {
				if(_log.isDebugEnabled()) _log.debug(e.getMessage());
			}
			
			return courseEvalFactory;
		}

		@Override
		public void modifiedService(
			ServiceReference<CourseEvalFactory> serviceReference,
			CourseEvalFactory courseEvalFactory) {
		}

		@Override
		public void removedService(
			ServiceReference<CourseEvalFactory> serviceReference,
			CourseEvalFactory courseEvalFactory) {
			_log.debug("removedService: " + courseEvalFactory.getClassName());
			Registry registry = RegistryUtil.getRegistry();

			registry.ungetService(serviceReference);

			_courseEvalFactoriesMapByClassName.remove(
				courseEvalFactory.getClassName());
			_courseEvalFactoriesMapByClassType.remove(
				courseEvalFactory.getType());
		}

	}

	static {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices(
			(Class<CourseEvalFactory>)(Class<?>)
				CourseEvalFactory.class,
			new CourseEvalFactoryServiceTrackerCustomizer());

		_serviceTracker.open();
	}
}
