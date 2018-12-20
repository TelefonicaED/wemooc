package com.ted.lms.internal.exportimport.data.handler;

import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.exportimport.kernel.lar.BasePortletDataHandler;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataHandler;
import com.liferay.exportimport.kernel.lar.PortletDataHandlerBoolean;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.exportimport.kernel.staging.Staging;
import com.liferay.exportimport.kernel.xstream.XStreamAliasRegistryUtil;
import com.liferay.exportimport.portlet.data.handler.helper.PortletDataHandlerHelper;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.xml.Element;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.Module;
import com.ted.lms.model.impl.LearningActivityImpl;
import com.ted.lms.model.impl.ModuleImpl;
import com.ted.lms.service.LearningActivityLocalService;
import com.ted.lms.service.ModuleLocalService;

import java.util.List;

import javax.portlet.PortletPreferences;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Proporciona la funcionalidad de exportar e importar un curso desde el portlet de administraci�n de cursos, 
 * que consiste en importar o exportar todos los m�dulos y actividades asociadas a ese curso. 
 * Tras la importaci�n, nuevos m�dulos y actividades se crear�n de acuerdo con la estrategia DATA_MIRROW
 * El autor de los objetos reci�n creados est� determinado por la clase
 * CourseCreationStrategy definida en el <i>portal.properties</i>. 
 * Esta estrategia tambi�n permite que los m�dulos y actividades se revisen y modifiquen antes
 * de la importaci�n.
 *
 * @author Virginia Martín Agudo
 * @see    com.ted.lms.internal.exportimport.creation.strategy.ModuleCreationStrategy
 * @see    PortletDataHandler
 */
@Component(
	immediate = true,
	property = {"javax.portlet.name=" + LMSPortletKeys.MODULES_ADMIN},
	service = PortletDataHandler.class
)
public class ModulePortletDataHandler extends BasePortletDataHandler {
	
	public static final String NAMESPACE = "admin_modules";
	
	private static final Log log = LogFactoryUtil.getLog(ModulePortletDataHandler.class);
	
	/**
	 * Inicializamos los valores que tendrá la importación del portlet. Las entidades que añadamos en
	 * setExportControls serán las que aparezcan en contenido.
	 */
	@Activate
	protected void activate() {
		setDeletionSystemEventStagedModelTypes(
			new StagedModelType(Module.class),
			new StagedModelType(LearningActivity.class));
		setExportControls(
			new PortletDataHandlerBoolean(
				NAMESPACE, "activities", true, false, null,
				LearningActivity.class.getName()),
			new PortletDataHandlerBoolean(
				NAMESPACE, "modules", true, false, null,
				Module.class.getName()));
		setStagingControls(getExportControls());
		
		XStreamAliasRegistryUtil.register(ModuleImpl.class, "Module");
		XStreamAliasRegistryUtil.register(
			LearningActivityImpl.class, "LearningActivity");
	}
	
	@Override
	protected String doExportData(final PortletDataContext portletDataContext, String portletId,
	        PortletPreferences portletPreferences) throws Exception {
		
	    Element rootElement = addExportDataRootElement(portletDataContext);

	    rootElement.addAttribute("group-id", String.valueOf(portletDataContext.getScopeGroupId()));
	    
	    if(portletDataContext.getBooleanParameter(NAMESPACE, "activities")) {
	    	ActionableDynamicQuery activityActionableDynamicQuery =
			        learningActivityLocalService.getExportActionableDynamicQuery(portletDataContext);
			    
	    	activityActionableDynamicQuery.performActions();
	    }

	    if(portletDataContext.getBooleanParameter(NAMESPACE, "modules")) {
	    	ActionableDynamicQuery moduleActionableDynamicQuery =
		        moduleLocalService.getExportActionableDynamicQuery(portletDataContext);
		    
		    moduleActionableDynamicQuery.performActions();
	    }
	    return getExportDataRootElementString(rootElement);
	}

	@Override
	protected PortletPreferences doImportData(
	        PortletDataContext portletDataContext, String portletId,
	        PortletPreferences portletPreferences, String data)
	    throws Exception {

		if (portletDataContext.getBooleanParameter(NAMESPACE, "activities")) {
			Element activitiesElement = portletDataContext.getImportDataGroupElement(LearningActivity.class);

			List<Element> activityElements = activitiesElement.elements();

			for (Element activityElement : activityElements) {
			
				StagedModelDataHandlerUtil.importStagedModel(portletDataContext, activityElement);
			}
		}
	    
		if (portletDataContext.getBooleanParameter(NAMESPACE, "modules")) {
		    Element modulesElement = portletDataContext.getImportDataGroupElement(Module.class);
	
		    List<Element> moduleElements = modulesElement.elements();
	
		    for (Element moduleElement : moduleElements) {
		        StagedModelDataHandlerUtil.importStagedModel(portletDataContext, moduleElement);
		    }
		}

	    return null;
	}
	
	/**
	 * Cuando indicamos que se borre el contenido completo antes de importar, se llama a esta función. Eliminamos los módulos ya existentes
	 * y la carpeta del repositorio correspondiente.
	 */
	@Override
	protected PortletPreferences doDeleteData(
	        PortletDataContext portletDataContext, String portletId,
	        PortletPreferences portletPreferences)
	    throws Exception {

	    if (portletDataContext.addPrimaryKey(
	            ModulePortletDataHandler.class, "deleteData")) {

	        return portletPreferences;
	    }

	    moduleLocalService.deleteModules(portletDataContext.getScopeGroupId());
	    
	    long repositoryId = DLFolderConstants.getDataRepositoryId(portletDataContext.getScopeGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID);

	    dLAppLocalService.deleteAll(repositoryId);

	    return portletPreferences;
	}
	
	@Override
	protected void doPrepareManifestSummary(PortletDataContext portletDataContext, PortletPreferences portletPreferences) throws Exception {
		
		ActionableDynamicQuery moduleExportActionableDynamicQuery = moduleLocalService.getExportActionableDynamicQuery(portletDataContext);

		moduleExportActionableDynamicQuery.performCount();
		
		ActionableDynamicQuery activityExportActionableDynamicQuery = learningActivityLocalService.
		            getExportActionableDynamicQuery(portletDataContext);

		activityExportActionableDynamicQuery.performCount();
	}
	
	public static final String SCHEMA_VERSION = "1.0.0";

	@Override
	public String getSchemaVersion() {
	    return SCHEMA_VERSION;
	}

	@Override
	public boolean validateSchemaVersion(String schemaVersion) {
	    return portletDataHandlerHelper.validateSchemaVersion(
	        schemaVersion, getSchemaVersion());
	}
	
	@Reference(unbind = "-")
	protected void setModuleLocalService(
	    ModuleLocalService moduleLocalService) {

	    this.moduleLocalService = moduleLocalService;
	}

	@Reference(unbind = "-")
	protected void setLearningActivityLocalService(
	    LearningActivityLocalService learningActivityLocalService) {

	    this.learningActivityLocalService = learningActivityLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setDLAppLocalService(
			DLAppLocalService dLAppLocalService) {

	    this.dLAppLocalService = dLAppLocalService;
	}
	
	@Reference
	private PortletDataHandlerHelper portletDataHandlerHelper;

	private ModuleLocalService moduleLocalService;
	private LearningActivityLocalService learningActivityLocalService;
	private DLAppLocalService dLAppLocalService;
	
	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}
	
	@Reference
	private Staging staging;
}
