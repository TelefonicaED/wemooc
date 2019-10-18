package com.ted.lms.module.eval.complete.activities;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.ted.lms.model.BaseModuleEval;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityResult;
import com.ted.lms.model.Module;
import com.ted.lms.model.ModuleResult;
import com.ted.lms.service.CourseResultLocalService;
import com.ted.lms.service.LearningActivityLocalService;
import com.ted.lms.service.LearningActivityResultLocalService;
import com.ted.lms.service.ModuleResultLocalService;

import java.util.Date;
import java.util.List;

public class CompleteActivitiesModuleEval extends BaseModuleEval{

	private static final Log log = LogFactoryUtil.getLog(CompleteActivitiesModuleEval.class);
	
	public CompleteActivitiesModuleEval(Module module, CourseResultLocalService courseResultLocalService,
			LearningActivityResultLocalService learningActivityResultLocalService, LearningActivityLocalService learningActivityLocalService, 
			ModuleResultLocalService moduleResultLocalService) {
		super(module, moduleResultLocalService);
		
		this.courseResultLocalService = courseResultLocalService;
		this.learningActivityResultLocalService = learningActivityResultLocalService;
		this.learningActivityLocalService = learningActivityLocalService;
	}
	
	@Override
	public String getClassName() {
		return CompleteActivitiesModuleEval.class.getName();
	}
	
	@Override
	public ModuleResult updateModuleResult(ModuleResult moduleResult) throws SystemException {
		// Se obtiene el courseresult del usuario en dicho course.
		
		List<LearningActivity> learningActivities = learningActivityLocalService.getRequiredLearningActivitiesOfModule(moduleResult.getModuleId());
		int numActivities = learningActivityLocalService.getLearningActivitiesOfModuleCount(module.getModuleId());
		
		return updateModuleResult(moduleResult, learningActivities, numActivities);
	}
	
	@Override
	public ModuleResult recalculateModule(ModuleResult moduleResult) throws SystemException {
		
		if(moduleResult != null && moduleResult.getStartDate() != null){
			List<LearningActivity> learningActivities = learningActivityLocalService.getRequiredLearningActivitiesOfModule(module.getModuleId());
			int numActivities = learningActivityLocalService.getLearningActivitiesOfModuleCount(module.getModuleId());
			moduleResult = updateModuleResult(moduleResult, learningActivities, numActivities);
		}
		return moduleResult;	
	}
	
	private ModuleResult updateModuleResult(ModuleResult moduleResult, List<LearningActivity> learningActivitiesRequired, int countNumActivites) {
		
		//Si el módulo no tiene actividades no lo podemos aprobar, da igual que sean obligatorias o no
		boolean passedModule = countNumActivites > 0;
		long activitiesPassed = 0;
        Date passedDate = new Date(0);
        LearningActivityResult learningActivityResult = null;
        int i = 0;
        log.debug("passedModule: " + passedModule);
        log.debug("learningActivitiesRequired size: " + learningActivitiesRequired.size());
        
        while(i < learningActivitiesRequired.size()) {	
        	learningActivityResult = learningActivityResultLocalService.getLearningActivityResult(learningActivitiesRequired.get(i).getActId(), moduleResult.getUserId());
        	log.debug("learningActivityResult: " + (learningActivityResult != null ? learningActivityResult.isPassed(): "null"));

			if(learningActivityResult != null && learningActivityResult.isPassed()) {
				activitiesPassed++;
				if(learningActivityResult.getEndDate()!=null && passedDate.before(learningActivityResult.getEndDate())) {
					passedDate=learningActivityResult.getEndDate();
				} 
			} else {
				passedModule = false;
			}
			i++;
			log.debug("activitiesPassed: " + activitiesPassed);
		}

		//Indicamos la media y el resultado del m�dulo.
		long result = learningActivitiesRequired.size() > 0 ? activitiesPassed * 100 / learningActivitiesRequired.size() : 0;
		
		log.debug("Vamos a ver si actualizamos...");
		log.debug("Module result "+moduleResult.getResult());
		log.debug("Result "+result);
		log.debug("PassedModule "+passedModule);
		log.debug("Module Result passed "+moduleResult.getPassed());
		
		if(moduleResult.getResult() <= result || (passedModule&&!moduleResult.getPassed())) {	
			
			log.debug("Actualizamos curso");
			moduleResult.setResult(result);
			if(!moduleResult.getPassed()){
				moduleResult.setPassed(passedModule);
				if(passedModule){
					moduleResult.setPassedDate(passedDate);
				}
			}
		}
		
		return moduleResult;
	}
	
	private CourseResultLocalService courseResultLocalService;
	private LearningActivityResultLocalService learningActivityResultLocalService;
	private LearningActivityLocalService learningActivityLocalService;
}
