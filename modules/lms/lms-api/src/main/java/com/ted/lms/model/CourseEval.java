package com.ted.lms.model;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.upload.UploadRequest;
import javax.portlet.PortletResponse;

import aQute.bnd.annotation.ProviderType;

/**
 * Interfaz para los métodos de evaluación de los cursos
 * @author Virginia Martín Agudo
 *
 */
@ProviderType
public interface CourseEval {
	
	/**
	 * Este método se llamará cuando se produzcan cambios en alguno de los módulos o actividades del usuario para que se calcule la nota
	 * del curso
	 * @param userId
	 * @return
	 * @throws SystemException
	 */
	public CourseResult updateCourse(long userId) throws SystemException;
	
	public boolean updateCourse() throws SystemException;
	
	public CourseResult recalculateCourse(long userId) throws SystemException;
	
	public boolean recalculateCourse() throws SystemException;

	public double getPassPuntuation();

	public void setExtraContent(UploadRequest uploadRequest, PortletResponse portletResponse);
	
	public void onOpenCourse();
	public void onCloseCourse();
	
	public CourseEvalFactory getCourseEvalFactory();
	
	public String getClassName();

}