package com.ted.lms.internal.model.listener;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ModelListener;

import org.osgi.service.component.annotations.Component;

/**
 * Proporciona la funcionalidad para que al asociar/desasociar usuarios a sitios web se pueda
 * comprobar si son cursos, en cuyo caso se realizarán las acciones necesarias 
 * cuando se asocia/desasocia un usuario a un curso
 * @author Virginia Martín Agudo
 */
@Component(immediate = true, service = ModelListener.class)
public class GroupModelListener extends BaseModelListener<Group> {

	@Override
	public void onAfterAddAssociation(Object classPK, String associationClassName, Object associationClassPK) throws ModelListenerException {
		
	}
	
	@Override
	public void onAfterRemoveAssociation(Object classPK, String associationClassName, Object associationClassPK) throws ModelListenerException {
		
	}
}
