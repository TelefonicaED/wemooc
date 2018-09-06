package com.ted.lms.model;

import java.util.Locale;

import aQute.bnd.annotation.ProviderType;

/**
 * Interfaz para las postcondiciones que se ejecutan cuando un usuario ha finalizado un curso
 * @author Virginia Martín Agudo
 *
 */
@ProviderType
public interface Postcondition {
	
	public String getClassName();

	public String getTitle(Locale locale);
	
	public String getDescription(Locale locale);
	
	public void passedCourseResult(CourseResult courseResult);
	
	public void failedCourseResult(CourseResult courseResult);

}
