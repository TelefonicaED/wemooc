package com.ted.lms.internal.security.permission.resource;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.ted.lms.constants.LMSConstants;
import com.ted.lms.model.Module;
import com.ted.lms.service.ModuleLocalService;

import java.util.Dictionary;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true)
public class ModuleModelResourcePermissionRegistrar { 
	
	private static final Log log = LogFactoryUtil.getLog(ModuleModelResourcePermissionRegistrar.class);
	
	@Activate
    public void activate(BundleContext bundleContext) {
		
		log.debug("ModuleModelResourcePermissionRegistrar::activate");
		
        Dictionary<String, Object> properties = new HashMapDictionary<>();

        properties.put("model.class.name", LMSConstants.MODULE_MODEL_CLASS_NAME);

        serviceRegistration = bundleContext.registerService(
            ModelResourcePermission.class,
            ModelResourcePermissionFactory.create(
                Module.class, Module::getModuleId,
                moduleLocalService::getModule, portletResourcePermission,
                (modelResourcePermission, consumer) -> {
                	
                }),
            properties);
    }

    @Deactivate
    public void deactivate() {
    	log.debug("ModuleModelResourcePermissionRegistrar::deactivate");
        serviceRegistration.unregister();
    }

    @Reference
    private ModuleLocalService moduleLocalService;

    @Reference(target = "(resource.name=" + LMSConstants.RESOURCE_NAME + ")")
    private PortletResourcePermission portletResourcePermission;

    private ServiceRegistration<ModelResourcePermission> serviceRegistration;

}
