package com.ted.lms.course.eval.complete.modules;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;
import com.ted.lms.model.BaseCourseEval;
import com.ted.lms.model.Course;
import com.ted.lms.model.CourseResult;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityResult;
import com.ted.lms.model.Module;
import com.ted.lms.service.CourseResultLocalService;
import com.ted.lms.service.LearningActivityLocalService;
import com.ted.lms.service.LearningActivityResultLocalService;
import com.ted.lms.service.LearningActivityTryLocalService;
import com.ted.lms.service.ModuleLocalService;
import com.ted.lms.service.ModuleResultLocalService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class CompleteModulesCourseEval extends BaseCourseEval{

	private static final Log log = LogFactoryUtil.getLog(CompleteModulesCourseEval.class);
	
	public CompleteModulesCourseEval(Course course, ServiceContext serviceContext, CourseResultLocalService courseResultLocalService,
			LearningActivityResultLocalService learningActivityResultLocalService, LearningActivityTryLocalService learningActivityTryLocalService,
			LearningActivityLocalService learningActivityLocalService, ModuleResultLocalService moduleResultLocalService,
			ModuleLocalService moduleLocalService) {
		super(course, serviceContext);
		this.courseResultLocalService = courseResultLocalService;
		this.learningActivityResultLocalService = learningActivityResultLocalService;
		this.learningActivityTryLocalService = learningActivityTryLocalService;
		this.learningActivityLocalService = learningActivityLocalService;
		this.moduleResultLocalService = moduleResultLocalService;
		this.moduleLocalService = moduleLocalService;
	}

	@Override
	public CourseResult updateCourseResult(CourseResult courseResult) throws SystemException {
		// Se obtiene el courseresult del usuario en dicho course.
		long courseId = course.getCourseId();
		
		List<LearningActivityResult> lresult = learningActivityResultLocalService.getRequiredLearningActivityResults(course.getGroupCreatedId(), courseResult.getUserId());
		
		return updateCourseResult(courseResult, lresult);
	}

	@Override
	public double getPassPuntuation() {
		throw new RuntimeException();
	}

	@Override
	public String getClassName() {
		return CompleteModulesCourseEval.class.getName();
	}

	@Override
	public CourseResult recalculateCourseResult(CourseResult courseResult) throws SystemException {
		// Se obtiene el courseresult del usuario en dicho course.
		List<LearningActivityResult> lresult = learningActivityResultLocalService.getRequiredLearningActivityResults(course.getGroupCreatedId(), courseResult.getUserId());

		if(courseResult.getStartDate() != null || (courseResult.getStartDate() != null &&  lresult.size() > 0)){
			courseResult = updateCourseResult(courseResult, lresult);
			courseResult = courseResultLocalService.updateCourseResult(courseResult);
		}
		
		return courseResult;	
	}
	
	public CourseResult updateCourseResult(CourseResult courseResult, List<LearningActivityResult> lresult) {
		
		long groupCreatedId = course.getGroupCreatedId();
		
		if(courseResult.getStartDate() == null) {
			courseResult.setStartDate(new Date());
		}
	
		// Se obtienen todos los módulos del curso.
		List<Module> modules = moduleLocalService.findAllInGroup(groupCreatedId);
		long numModules = modules.size();
		
		log.debug("--- Numero de modulos "+numModules);
		boolean passed = true;
		long result = 0;
		// Se obtienen los módulos aprobados por el usuario.
		long modulesPassedByUser = moduleResultLocalService.countModulesUserPassed(groupCreatedId, courseResult.getUserId());
		log.debug("--- Numero de modulos pasados "+modulesPassedByUser);
		
		// Se calcula el resultado del usuario.
		result = 100 * modulesPassedByUser / numModules;
		log.debug("--- Result "+result);
		boolean isFailed = false;
		
		if(numModules > modulesPassedByUser){
			passed = false;
		}
			
		// Se obtienen las actividades que son obligatorias en el curso.
		List<LearningActivity> learningActivities = learningActivityLocalService.getRequiredLearningActivitiesOfGroup(groupCreatedId);
		
		//Guardo los resultados de las actividades del usuario en el curso en un hashmap para no tener que acceder a bbdd por cada uno de ellos
		HashMap<Long, LearningActivityResult> results = new HashMap<Long, LearningActivityResult>();
		for(LearningActivityResult ar:lresult){
			results.put(ar.getActId(), ar);
		}
		
		long currentActId = 0, numTriesDone = 0, numTriesCurrentAct;
		LearningActivityResult lar = null;
		
		// Se iteran por las actividades obligatorias para comprobar si se tienen resultados de las mismas y se tienen aprobadas.
		for(LearningActivity activity:learningActivities) {   
			currentActId = activity.getActId();
			log.debug("--- Actividad actual "+currentActId);
			if(results.containsKey(currentActId)){
				lar = results.get(currentActId);
			}else{
				lar = null;
			}
			log.debug("--- LAR "+lar);
			// Si el usuario no tiene resultado en la actividad.
			if(lar != null) {
				log.debug("--- LAR "+lar.isPassed());
				if (!lar.isPassed() && Validator.isNotNull(lar.getEndDate())) {
					numTriesCurrentAct = activity.getTries();
					// Si la actividad no tiene un número ilimitado de intentos (numTriesCurrentAct = 0) y el usuario ya ha hecho todos los intentos disponibles se marca el curso como "Suspenso" (isFailed). 
					if(numTriesCurrentAct != 0) {						
						numTriesDone = learningActivityTryLocalService.getLearningActivityTriesCount(lar.getActId(), courseResult.getUserId());
						log.debug("-- NUM TRIES CURRENT ACT "+numTriesCurrentAct);
						log.debug("-- NUM TRIES DONE "+numTriesDone);
						if (numTriesCurrentAct <= numTriesDone) {
							isFailed = true;
						}
					}
					
					passed = false;
				} else if(!lar.isPassed()){
					passed = false;
				}
			}else {
				passed = false;
			}
			
			log.debug("--- Passed "+passed);
			log.debug("--- IsFailed "+isFailed);
		}
		
		log.debug("---Course Passed "+(passed && !isFailed));
		// Si el usuario se ha marcado como isFailed es porque lo tiene suspenso. Se le asigna un passed a false y se marca la fecha de finalización del curso (passedDate).
		courseResult.setPassed(passed && !isFailed);
		log.debug("---Course Result "+result);
		// Se almacena el result del resultado del usuario en el curso.
		courseResult.setResult(result);
		if((passed || isFailed) && courseResult.getPassedDate() == null) {
			courseResult.setPassedDate(new Date());
		}
		log.debug("---Course Passed Date "+courseResult.getPassedDate());
		
		return courseResult;
	}
	
	@Override
	public void onOpenCourse() {
		List<CourseResult> listCourseResults = courseResultLocalService.getCourseResults(course.getCourseId(), false);
		for(CourseResult courseResult: listCourseResults){
			courseResult.setPassedDate(null);
			courseResultLocalService.updateCourseResult(courseResult);
		}
	}
	
	@Override
	public void onCloseCourse() {
		List<CourseResult> listCourseResults = courseResultLocalService.getFailedCourseResults(course.getCourseId());
		for(CourseResult courseResult: listCourseResults) {
			courseResult.setPassedDate(course.getModifiedDate());
			courseResultLocalService.updateCourseResult(courseResult);
		}
	}

	private CourseResultLocalService courseResultLocalService;
	private LearningActivityResultLocalService learningActivityResultLocalService;
	private LearningActivityTryLocalService learningActivityTryLocalService;
	private LearningActivityLocalService learningActivityLocalService;
	private ModuleResultLocalService moduleResultLocalService;
	private ModuleLocalService moduleLocalService;
}
