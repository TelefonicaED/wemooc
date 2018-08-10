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
import com.ted.lms.model.LearningActivity;
import com.ted.lms.service.LearningActivityLocalService;
import java.util.Dictionary;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Esta clase registra los permisos sobre la entidad de actividad
 * @author Virginia Martín Agudo
 *
 */
@Component(immediate = true)
public class LearningActivityModelResourcePermissionRegistrar { 
	
	private static final Log log = LogFactoryUtil.getLog(LearningActivityModelResourcePermissionRegistrar.class);
	
	@Activate
    public void activate(BundleContext bundleContext) {
		
		log.debug("LearningActivityModelResourcePermissionRegistrar::activate");
		
        Dictionary<String, Object> properties = new HashMapDictionary<>();

        properties.put("model.class.name", LMSConstants.LEARNING_ACTIVITY_MODEL_CLASS_NAME);

        serviceRegistration = bundleContext.registerService(
            ModelResourcePermission.class,
            ModelResourcePermissionFactory.create(
            	LearningActivity.class, LearningActivity::getActId,
            	learningActivityLocalService::getLearningActivity, portletResourcePermission,
                (modelResourcePermission, consumer) -> {
                	consumer.accept(	
                			new WorkflowedModelPermissionLogic<>(
                				workflowPermission, modelResourcePermission,
                                groupLocalService, LearningActivity::getActId));
                }),
            properties);
    }

    @Deactivate
    public void deactivate() {
    	log.debug("LearningActivityModelResourcePermissionRegistrar::deactivate");
        serviceRegistration.unregister();
    }

    @Reference
    private LearningActivityLocalService learningActivityLocalService;
    
    @Reference
    private WorkflowPermission workflowPermission;

    @Reference(target = "(resource.name=" + LMSConstants.RESOURCE_NAME + ")")
    private PortletResourcePermission portletResourcePermission;

    private ServiceRegistration<ModelResourcePermission> serviceRegistration;
    
    @Reference
   	private GroupLocalService groupLocalService;

}
