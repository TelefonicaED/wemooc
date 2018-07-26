package com.ted.lms.internal.exportimport.data.handler;

import com.liferay.exportimport.kernel.lar.BasePortletDataHandler;
import com.liferay.exportimport.kernel.lar.PortletDataHandler;
import com.ted.lms.constants.LMSPortletKeys;

import org.osgi.service.component.annotations.Component;

/**
 * Proporciona la funcionalidad de exportar e importar un curso desde el portlet de administración de cursos, 
 * que consiste en importar o exportar todos los módulos y actividades asociadas a ese curso. 
 * Tras la importación, nuevos módulos y actividades se crearán de acuerdo con la estrategia DATA_MIRROW
 * El autor de los objetos recién creados está determinado por la clase
 * CourseCreationStrategy definida en el <i>portal.properties</i>. 
 * Esta estrategia también permite que los módulos y actividades se revisen y modifiquen antes
 * de la importación.
 *
 * @author Virginia Martín Agudo
 * @see    com.ted.lms.internal.exportimport.creation.strategy.CourseCreationStrategy
 * @see    PortletDataHandler
 */
@Component(
		property = "javax.portlet.name=" + LMSPortletKeys.COURSE,
		service = PortletDataHandler.class
	)
public class CoursePortletDataHandler extends BasePortletDataHandler {

}
