package com.ted.lms.internal.exportimport.creation.strategy;

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.ted.lms.model.LearningActivity;

/**
 * 
 * @author Virginia Martín Agudo
 *
 */
public interface LearningActivityCreationStrategy {
	/**
	 * Constante devuelta por getTransformedDescription para indicar que el texto no ha sido cambiado
	 */
	public static final String LEARNING_ACTIVITY_DESCRIPTION_UNCHANGED = null;

	/**
	 * Constante que devuelve getAuthorUserId() que indica el id de usuario por defecto que importa el portlet
	 */
	public static final long USE_DEFAULT_USER_ID_STRATEGY = 0;

	/**
	 * Devuelve <code>true</code> si se deben añadir permisos a los miembros al crear el actividad
	 *
	 * @param  context el contexto del portlet
	 * @param  activityObj el objeto del actividad
	 * @return <code>true</code> si se deben añadir permisos a los miembros al crear el actividad
	 * @throws Exception si ha ocurrido una excepción
	 */
	public boolean addGroupPermissions(
			PortletDataContext context, Object journalObj)
		throws Exception;

	/**
	 * Devuelve <code>true</code> si se deben añadir permisos al guest al crear el actividad
	 *
	 * @param  context el contexto del portlet
	 * @param  activityObj el objeto del actividad
	 * @return <code>true</code> si se deben añadir permisos al guest al crear el actividad
	 * @throws Exception si ha ocurrido una excepción
	 */
	public boolean addGuestPermissions(
			PortletDataContext context, Object journalObj)
		throws Exception;

	/**
	 * Devuelve el identificador del usuario que se asignará como autor al crear el nuevo actividad. Si se 
	 * devuelve cero, se devolverá el valor de la constante USE_DEFAULT_USER_ID_STRATEGY
	 *
	 * @param  context el contexto del portlet
	 * @param  activityObj el objeto del actividad
	 * @return el identificador del autor o USE_DEFAULT_USER_ID_STRATEGY
	 * @throws Exception si ha ocurrido una excepción
	 */
	public long getAuthorUserId(PortletDataContext context, Object activityObj)
		throws Exception;

	/**
	 * Permite transformar el contenido antes de que el nuevo actividad se guarde en base de datos. 
	 * Los posibles casos de uso incluyen el uso de Velocity para combiar valroes específicos del grupo en el texto.
	 * Devuelve la nueva descripción del actividad. Se se devuelve <code>null</code>, la 
	 * descripción del actividad se añadirá sin cambios.
	 *
	 * @param  context el contexto del portlet
	 * @param  activityObj el objeto del actividad
	 * @return la descripción modificaba para guardar en base de datos o
	 *         LEARNING_ACTIVITY_DESCRIPTION_UNCHANGED si el contenido no ha sido modificado
	 * @throws Exception si ha ocurrido una excepción
	 */
	public String getTransformedDescription(
			PortletDataContext context, LearningActivity newLearningActivity)
		throws Exception;
}
