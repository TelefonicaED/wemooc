package com.ted.lms.internal.exportimport.data.handler;

import com.liferay.exportimport.kernel.lar.BasePortletDataHandler;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataHandler;
import com.liferay.exportimport.kernel.lar.PortletDataHandlerBoolean;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.exportimport.portlet.data.handler.helper.PortletDataHandlerHelper;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.xml.Element;
import com.ted.lms.constants.LMSConstants;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.Course;
import com.ted.lms.model.Module;
import com.ted.lms.service.CourseLocalService;
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
	
	public static final String NAMESPACE = "courses";
	
	@Activate
	protected void activate() {
		setDeletionSystemEventStagedModelTypes(
			new StagedModelType(Module.class));
		setExportControls(
				new PortletDataHandlerBoolean(
					NAMESPACE, "course", true, false, null,
					Course.class.getName()));
		setStagingControls(getExportControls());
	}
	
	@Override
	protected String doExportData(
	        final PortletDataContext portletDataContext, String portletId,
	        PortletPreferences portletPreferences)
	    throws Exception {

	    Element rootElement = addExportDataRootElement(portletDataContext);

	    if (!portletDataContext.getBooleanParameter(NAMESPACE, "course")) {
	        return getExportDataRootElementString(rootElement);
	    }

	    portletDataContext.addPortletPermissions(
	        LMSConstants.RESOURCE_NAME);

	    rootElement.addAttribute(
	        "group-id", String.valueOf(portletDataContext.getScopeGroupId()));

	    ExportActionableDynamicQuery courseActionableDynamicQuery =
	        courseLocalService.
	            getExportActionableDynamicQuery(portletDataContext);

	    courseActionableDynamicQuery.performActions();

	    return getExportDataRootElementString(rootElement);
	}

	@Override
	protected PortletPreferences doImportData(
	        PortletDataContext portletDataContext, String portletId,
	        PortletPreferences portletPreferences, String data)
	    throws Exception {

	    if (!portletDataContext.getBooleanParameter(NAMESPACE, "courses")) {
	        return null;
	    }

	    portletDataContext.importPortletPermissions(
	        LMSConstants.RESOURCE_NAME);

	    Element coursesElement = portletDataContext.getImportDataGroupElement(
	        Course.class);

	    List<Element> courseElements = coursesElement.elements();

	    for (Element courseElement : courseElements) {
	        StagedModelDataHandlerUtil.importStagedModel(
	            portletDataContext, courseElement);
	    }

	    return null;
	}
	
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
	
	@Override
	protected void doPrepareManifestSummary(
	        PortletDataContext portletDataContext,
	        PortletPreferences portletPreferences)
	    throws Exception {

	    ActionableDynamicQuery courseExportActionableDynamicQuery =
	        courseLocalService.
	            getExportActionableDynamicQuery(portletDataContext);

	    courseExportActionableDynamicQuery.performCount();
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

	private CourseLocalService courseLocalService;
	
	@Reference
	private PortletDataHandlerHelper portletDataHandlerHelper;
}
