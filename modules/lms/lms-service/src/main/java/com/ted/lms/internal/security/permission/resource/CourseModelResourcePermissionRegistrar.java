package com.ted.lms.internal.security.permission.resource;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.WorkflowedModelPermissionLogic;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.kernel.workflow.permission.WorkflowPermission;
import com.ted.lms.constants.LMSConstants;
import com.ted.lms.model.Course;
import com.ted.lms.service.CourseLocalService;

import java.util.Dictionary;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true)
public class CourseModelResourcePermissionRegistrar { 
	
	private static final Log log = LogFactoryUtil.getLog(CourseModelResourcePermissionRegistrar.class);
	
	@Activate
    public void activate(BundleContext bundleContext) {
		
		log.debug("CourseModelResourcePermissionRegistrar::activate");
		
        Dictionary<String, Object> properties = new HashMapDictionary<>();

        properties.put("model.class.name", LMSConstants.COURSE_MODEL_CLASS_NAME);

        serviceRegistration = bundleContext.registerService(
            ModelResourcePermission.class,
            ModelResourcePermissionFactory.create(
                Course.class, Course::getCourseId,
                courseLocalService::getCourse, portletResourcePermission,
                (modelResourcePermission, consumer) -> {
                    consumer.accept(	
                        new WorkflowedModelPermissionLogic<>(
                            workflowPermission, modelResourcePermission,
                            groupLocalService, Course::getCourseId));
                }),
            properties);
    }

    @Deactivate
    public void deactivate() {
    	log.debug("CourseModelResourcePermissionRegistrar::deactivate");
        serviceRegistration.unregister();
    }

    @Reference
    private CourseLocalService courseLocalService;

    @Reference(target = "(resource.name=" + LMSConstants.RESOURCE_NAME + ")")
    private PortletResourcePermission portletResourcePermission;

    private ServiceRegistration<ModelResourcePermission> serviceRegistration;

    @Reference
    private WorkflowPermission workflowPermission;
    
    @Reference
	private GroupLocalService groupLocalService;
}
