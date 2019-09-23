package com.ted.lms.web.internal.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.LayoutSetPrototype;
import com.liferay.portal.kernel.service.LayoutSetPrototypeLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;
import com.ted.lms.configuration.CourseServiceConfiguration;
import com.ted.lms.model.CalificationTypeFactory;
import com.ted.lms.model.Course;
import com.ted.lms.model.CourseEvalFactory;
import com.ted.lms.model.InscriptionTypeFactory;
import com.ted.lms.model.LearningActivityTypeFactory;
import com.ted.lms.registry.CalificationTypeFactoryRegistryUtil;
import com.ted.lms.registry.CourseEvalFactoryRegistryUtil;
import com.ted.lms.registry.InscriptionTypeFactoryRegistryUtil;
import com.ted.lms.registry.LearningActivityTypeFactoryRegistryUtil;
import com.ted.lms.web.internal.configuration.CourseAdminPortletInstanceConfiguration;
import com.ted.lms.web.internal.portlet.action.ImportMembersMVCActionCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Esta clase contiene métodos útiles para trabajar con los cursos
 * @author Virginia Martín Agudo
 *
 */
public class CourseUtil {
	
	private static final Log log = LogFactoryUtil.getLog(CourseUtil.class);
	
	public static String getDisplayTitle(ResourceBundle resourceBundle, Course course) {

		if (Validator.isNull(course.getTitle())) {
			return LanguageUtil.get(resourceBundle, "untitled-entry");
		}

		return course.getTitle(resourceBundle.getLocale());
	}
	
	public static String getDisplayDescription(ResourceBundle resourceBundle, Course course) {

		if (Validator.isNull(course.getDescription())) {
			return LanguageUtil.get(resourceBundle, "undescripted-entry");
		}

		return course.getDescription(resourceBundle.getLocale());
	}
	
	public static List<LearningActivityTypeFactory> getLearningActivityTypeFactories(CourseServiceConfiguration configuration, long companyId){
		List<LearningActivityTypeFactory> learningActivityTypeFactories =null;
		String[] activityClassNames = configuration.activities();
		if(activityClassNames != null && activityClassNames.length > 0 && Validator.isNotNull(activityClassNames[0])) {
			learningActivityTypeFactories = new ArrayList<LearningActivityTypeFactory>();
			LearningActivityTypeFactory activityTypeFactory = null;
			for(String activityClassName: activityClassNames) {
				activityTypeFactory = LearningActivityTypeFactoryRegistryUtil.getLearningActivityTypeFactoryByClassName(activityClassName);
				if(activityTypeFactory != null) {
					learningActivityTypeFactories.add(activityTypeFactory);
				}
			}
		}else {
			learningActivityTypeFactories = 
				LearningActivityTypeFactoryRegistryUtil.getLearningActivityFactories(companyId);
		}
		
		return learningActivityTypeFactories;
	}
	
	public static List<CourseEvalFactory> getCourseEvalFactories(CourseServiceConfiguration configuration, long companyId){
		List<CourseEvalFactory> courseEvalFactories =null;
		String[] courseEvalClassNames = configuration.courseEvaluations();
		if(courseEvalClassNames != null && courseEvalClassNames.length > 0 && Validator.isNotNull(courseEvalClassNames[0])) {
			courseEvalFactories = new ArrayList<CourseEvalFactory>();
			CourseEvalFactory courseEvalFactory = null;
			for(String courseEvalClassName: courseEvalClassNames) {
				courseEvalFactory = CourseEvalFactoryRegistryUtil.getCourseEvalFactoryByClassName(courseEvalClassName);
				if(courseEvalFactory != null) {
					courseEvalFactories.add(courseEvalFactory);
				}
			}
		}else {
			courseEvalFactories = 
					CourseEvalFactoryRegistryUtil.getCourseEvalFactories(companyId);
		}
		
		return courseEvalFactories;
	}
	
	public static List<InscriptionTypeFactory> getInscriptionTypeFactories(CourseServiceConfiguration configuration, long companyId){
		List<InscriptionTypeFactory> inscriptionTypeFactories =null;
		String[] inscriptionTypeClassNames = configuration.inscriptions();
		if(inscriptionTypeClassNames != null && inscriptionTypeClassNames.length > 0 && Validator.isNotNull(inscriptionTypeClassNames[0])) {
			inscriptionTypeFactories = new ArrayList<InscriptionTypeFactory>();
			InscriptionTypeFactory inscriptionTypeFactory = null;
			for(String inscriptionTypeClassName: inscriptionTypeClassNames) {
				inscriptionTypeFactory = InscriptionTypeFactoryRegistryUtil.getInscriptionTypeFactoryByClassName(inscriptionTypeClassName);
				if(inscriptionTypeFactory != null) {
					inscriptionTypeFactories.add(inscriptionTypeFactory);
				}
			}
		}else {
			inscriptionTypeFactories = 
					InscriptionTypeFactoryRegistryUtil.getInscriptionFactories(companyId);
		}
		
		return inscriptionTypeFactories;
	}
	
	public static List<CalificationTypeFactory> getCalificationTypeFactories(CourseServiceConfiguration configuration, long companyId){
		List<CalificationTypeFactory> calificationTypeFactories =null;
		String[] calificationTypeClassNames = configuration.califications();
		if(calificationTypeClassNames != null && calificationTypeClassNames.length > 0 && Validator.isNotNull(calificationTypeClassNames[0])) {
			calificationTypeFactories = new ArrayList<CalificationTypeFactory>();
			CalificationTypeFactory calificationTypeFactory = null;
			for(String calificationTypeClassName: calificationTypeClassNames) {
				calificationTypeFactory = CalificationTypeFactoryRegistryUtil.getCalificationTypeFactoryByClassName(calificationTypeClassName);
				if(calificationTypeFactory != null) {
					calificationTypeFactories.add(calificationTypeFactory);
				}
			}
		}else {
			calificationTypeFactories = 
					CalificationTypeFactoryRegistryUtil.getCalificationFactories(companyId);
		}
		
		return calificationTypeFactories;
	}
	
	public static List<LayoutSetPrototype> getTemplates(CourseServiceConfiguration configuration, long companyId){
		List<LayoutSetPrototype> listLayoutSetPrototype = null;
		String[] courseTemplatesStr = configuration.courseTemplates();
		long[] courseTemplates = courseTemplatesStr != null ? Arrays.stream(configuration.courseTemplates()).mapToLong(item -> Long.parseLong(item)).toArray(): null;

		if(courseTemplates != null && courseTemplates.length > 0 && courseTemplates[0] > 0){
			
			listLayoutSetPrototype = new ArrayList<LayoutSetPrototype>();
			
			for(long courseTemplate: courseTemplates) {
				log.debug("courseTemplate: " + courseTemplate);
				try {
					listLayoutSetPrototype.add(LayoutSetPrototypeLocalServiceUtil.getLayoutSetPrototype(courseTemplate));
				} catch (PortalException e) {
					e.printStackTrace();
				}
			}
		}else {
			listLayoutSetPrototype = LayoutSetPrototypeLocalServiceUtil.getLayoutSetPrototypes(companyId);
		}
		
		return listLayoutSetPrototype;
	}
	
	public static List<LayoutSetPrototype> getTemplates(CourseAdminPortletInstanceConfiguration portletInstanceConfiguration, 
			CourseServiceConfiguration configuration, long companyId){
		List<LayoutSetPrototype> layoutSetPrototypes = null;
		if(portletInstanceConfiguration != null && portletInstanceConfiguration.courseTemplates() != null && portletInstanceConfiguration.courseTemplates().length > 0) {
			layoutSetPrototypes = new ArrayList<LayoutSetPrototype>();
			
			String[] courseTemplates = portletInstanceConfiguration.courseTemplates();
			
			for(String courseTemplate: courseTemplates) {
				log.debug("courseTemplate: " + courseTemplate);
				try {
					layoutSetPrototypes.add(LayoutSetPrototypeLocalServiceUtil.getLayoutSetPrototype(Long.parseLong(courseTemplate)));
				} catch (NumberFormatException | PortalException e) {
					e.printStackTrace();
				}
			}
		}else {
			layoutSetPrototypes = getTemplates(configuration, companyId);
		}
		
		return layoutSetPrototypes;
	}
	
}
