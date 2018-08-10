package com.ted.lms.definition.course.calification;

import com.liferay.portal.kernel.upload.UploadRequest;
import com.ted.lms.model.Course;

import java.util.Locale;

import javax.portlet.PortletResponse;
 
/**
  * Interfaz para el tipo de calificación de curso
  * @author Virginia Martín Agudo
  *
  */
public interface CalificationType {
	
	public long getTypeId();
	public String getName(Locale locale);
	public String getDescription(Locale locale);
	public String getSuffix(Course course);
	public String translate(Locale locale, Course course, double result);
	public long toBase100(Course course,String result);
	public double getMinValue(Course course);
	public long getMaxValue(long groupId);
	public String getExpecificContentPage();
	public String setExtraContent(UploadRequest uploadRequest,PortletResponse portletResponse,Course course);
}
