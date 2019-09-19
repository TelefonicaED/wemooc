package com.ted.lms.internal.exportimport.data.handler;

import com.liferay.changeset.model.ChangesetCollection;
import com.liferay.changeset.service.ChangesetCollectionLocalService;
import com.liferay.changeset.service.ChangesetEntryLocalService;
import com.liferay.exportimport.kernel.lar.BasePortletDataHandler;
import com.liferay.exportimport.kernel.lar.ExportImportDateUtil;
import com.liferay.exportimport.kernel.lar.ExportImportHelper;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataHandler;
import com.liferay.exportimport.kernel.lar.PortletDataHandlerBoolean;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.exportimport.kernel.staging.Staging;
import com.liferay.exportimport.kernel.staging.StagingConstants;
import com.liferay.exportimport.portlet.data.handler.helper.PortletDataHandlerHelper;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.xml.Element;
import com.ted.lms.constants.LMSConstants;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.Course;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.Module;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.service.CourseTypeLocalService;
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
	property = {"javax.portlet.name=" + LMSPortletKeys.COURSE},
	service = PortletDataHandler.class
)
public class CoursePortletDataHandler extends BasePortletDataHandler {
	
	public static final String[] CLASS_NAMES = {
			Course.class.getName(), Module.class.getName(),
			LearningActivity.class.getName()
		};
	
	public static final String NAMESPACE = "courses";
	
	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Activate
	protected void activate() {
		setDeletionSystemEventStagedModelTypes(
			new StagedModelType(Course.class),
			new StagedModelType(Module.class),
			new StagedModelType(LearningActivity.class));
		
		setExportControls(
				new PortletDataHandlerBoolean(
					NAMESPACE, "courses", true, false, 
					new PortletDataHandlerBoolean[] {
						new PortletDataHandlerBoolean(
							NAMESPACE, "modules", true, false, 
							new PortletDataHandlerBoolean[] {
								new PortletDataHandlerBoolean(
									NAMESPACE, "activities", true, false, 
									new PortletDataHandlerBoolean[] {
											new PortletDataHandlerBoolean(
												NAMESPACE, "referenced-content")
									},
									LearningActivity.class.getName()),
								new PortletDataHandlerBoolean(
										NAMESPACE, "referenced-content")
							},
							Module.class.getName()),
						new PortletDataHandlerBoolean(
								NAMESPACE, "referenced-content")
					},
					Course.class.getName()));
		setStagingControls(getExportControls());
	}
	
	/**
	 * Definimos los datos que vamos a exportar
	 */
	@Override
	protected String doExportData(
	        final PortletDataContext portletDataContext, String portletId,
	        PortletPreferences portletPreferences)
	    throws Exception {
		
		portletDataContext.addPortletPermissions(LMSConstants.RESOURCE_NAME);

	    Element rootElement = addExportDataRootElement(portletDataContext);
	    
	    rootElement.addAttribute(
    	        "group-id", String.valueOf(portletDataContext.getScopeGroupId()));

	    if (portletDataContext.getBooleanParameter(NAMESPACE, "courses")) {
    	    ExportActionableDynamicQuery courseActionableDynamicQuery =
    	        courseLocalService.
    	            getExportActionableDynamicQuery(portletDataContext);

    	    courseActionableDynamicQuery.performActions();
	    }
	    
	    if(portletDataContext.getBooleanParameter(NAMESPACE, "modules")) {
	    	ExportActionableDynamicQuery moduleActionableDynamicQuery =
	    	        moduleLocalService.
	    	            getExportActionableDynamicQuery(portletDataContext);

	    	moduleActionableDynamicQuery.performActions();
	    }
	    
	    if(portletDataContext.getBooleanParameter(NAMESPACE, "activities")) {
	    	ExportActionableDynamicQuery activityActionableDynamicQuery =
	    	        learningActivityLocalService.
	    	            getExportActionableDynamicQuery(portletDataContext);

	    	activityActionableDynamicQuery.performActions();
	    }
	    

	    return getExportDataRootElementString(rootElement);
	}

	/**
	 * Definimos los datos que vamos a importar
	 */
	@Override
	protected PortletPreferences doImportData(
	        PortletDataContext portletDataContext, String portletId,
	        PortletPreferences portletPreferences, String data)
	    throws Exception {
		
		 portletDataContext.importPortletPermissions(LMSConstants.RESOURCE_NAME);

	    if (portletDataContext.getBooleanParameter(NAMESPACE, "courses")) {
	    	 Element coursesElement = portletDataContext.getImportDataGroupElement(Course.class);

		    List<Element> courseElements = coursesElement.elements();

		    for (Element courseElement : courseElements) {
		        StagedModelDataHandlerUtil.importStagedModel(
		            portletDataContext, courseElement);
		    }
	    }

	   if(portletDataContext.getBooleanParameter(NAMESPACE, "modules")) {
	    	 Element modulesElement = portletDataContext.getImportDataGroupElement(Module.class);

		    List<Element> moduleElements = modulesElement.elements();

		    for (Element moduleElement : moduleElements) {
		        StagedModelDataHandlerUtil.importStagedModel(
		            portletDataContext, moduleElement);
		    }
	    }

	   if(portletDataContext.getBooleanParameter(NAMESPACE, "activities")) {
	    	 Element activitiesElement = portletDataContext.getImportDataGroupElement(LearningActivity.class);

		    List<Element> activityElements = activitiesElement.elements();

		    for (Element activityElement : activityElements) {
		        StagedModelDataHandlerUtil.importStagedModel(
		            portletDataContext, activityElement);
		    }
	    }

	    return null;
	}
	
	/**
	 * Los cursos que borraremos si se marca la opción al importar de eliminar el contenido actual
	 */
	@Override
	protected PortletPreferences doDeleteData(
	        PortletDataContext portletDataContext, String portletId,
	        PortletPreferences portletPreferences)
	    throws Exception {

	    if (portletDataContext.addPrimaryKey(
	            CoursePortletDataHandler.class, "deleteData")) {

	        return portletPreferences;
	    }

	    
	    
	    courseLocalService.deleteCourses(
	        portletDataContext.getScopeGroupId());

	    return portletPreferences;
	}
	
	/**
	 * Número de cursos a exportar
	 */
	@Override
	protected void doPrepareManifestSummary(
	        PortletDataContext portletDataContext,
	        PortletPreferences portletPreferences)
	    throws Exception {
		
		if (ExportImportDateUtil.isRangeFromLastPublishDate(
				portletDataContext)) {

			_staging.populateLastPublishDateCounts(
				portletDataContext,
				new StagedModelType[] {
					new StagedModelType(
						Course.class.getName()),
					new StagedModelType(
						Module.class.getName()),
					new StagedModelType(LearningActivity.class.getName())
				});

			populateCourseLastPublishDateCounts(portletDataContext);

			return;
		}

	    ActionableDynamicQuery courseExportActionableDynamicQuery =
	        courseLocalService.
	            getExportActionableDynamicQuery(portletDataContext);

	    courseExportActionableDynamicQuery.performCount();
	    
	    ActionableDynamicQuery moduleExportActionableDynamicQuery =
		        moduleLocalService.getExportActionableDynamicQuery(portletDataContext);

	    moduleExportActionableDynamicQuery.performCount();
		    
	    ActionableDynamicQuery activityExportActionableDynamicQuery =
		        learningActivityLocalService.getExportActionableDynamicQuery(portletDataContext);

	    activityExportActionableDynamicQuery.performCount();
	}
	
	private void populateCourseLastPublishDateCounts(
			PortletDataContext portletDataContext)
		throws PortalException {

		ManifestSummary manifestSummary =
			portletDataContext.getManifestSummary();

		StagedModelType courseStagedModelType = new StagedModelType(
			Course.class);

		long modelAdditionCount = manifestSummary.getModelAdditionCount(
			courseStagedModelType);

		if (modelAdditionCount > -1) {
			return;
		}

		ChangesetCollection changesetCollection =
			changesetCollectionLocalService.fetchChangesetCollection(
				portletDataContext.getScopeGroupId(),
				StagingConstants.RANGE_FROM_LAST_PUBLISH_DATE_CHANGESET_NAME);

		if (changesetCollection != null) {
			modelAdditionCount =
				changesetEntryLocalService.getChangesetEntriesCount(
					changesetCollection.getChangesetCollectionId(),
					_portal.getClassNameId(Course.class));

			manifestSummary.addModelAdditionCount(
				courseStagedModelType, modelAdditionCount);
		}

		long modelDeletionCount = _exportImportHelper.getModelDeletionCount(
			portletDataContext, courseStagedModelType);

		manifestSummary.addModelDeletionCount(
				courseStagedModelType, modelDeletionCount);
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
	protected void setCourseLocalService(
			CourseLocalService courseLocalService) {

	    this.courseLocalService = courseLocalService;
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
	protected void setCourseTypeLocalService(CourseTypeLocalService courseTypeLocalService) {

	    this.courseTypeLocalService = courseTypeLocalService;
	}

	private CourseLocalService courseLocalService;
	private CourseTypeLocalService courseTypeLocalService;
	private ModuleLocalService moduleLocalService;
	private LearningActivityLocalService learningActivityLocalService;
	
	@Reference
	private PortletDataHandlerHelper portletDataHandlerHelper;
	
	@Reference
	private Staging _staging;
	
	@Reference
	private Portal _portal;
	
	@Reference
	private ChangesetCollectionLocalService changesetCollectionLocalService;
	
	@Reference
	private ChangesetEntryLocalService changesetEntryLocalService;
	
	@Reference
	private ExportImportHelper _exportImportHelper;
}
