package com.ted.lms.module.eval.complete.activities;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
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
	
	public CompleteActivitiesModuleEval(Module module, ServiceContext serviceContext, CourseResultLocalService courseResultLocalService,
			LearningActivityResultLocalService learningActivityResultLocalService, LearningActivityLocalService learningActivityLocalService, 
			ModuleResultLocalService moduleResultLocalService) {
		super(module, serviceContext, moduleResultLocalService);
		
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
		
		return updateModuleResult(moduleResult, learningActivities);
	}
	
	@Override
	public ModuleResult recalculateModule(ModuleResult moduleResult) throws SystemException {
		
		List<LearningActivity> learningActivities = learningActivityLocalService.getRequiredLearningActivitiesOfModule(module.getModuleId());
		
		if(moduleResult != null && moduleResult.getStartDate() != null){
			moduleResult = updateModuleResult(moduleResult, learningActivities);
		}
		return moduleResult;	
	}
	
	private ModuleResult updateModuleResult(ModuleResult moduleResult, List<LearningActivity> learningActivities) {
		
		//Si el módulo no tiene actividades no lo podemos aprobar, da igual que sean obligatorias o no
		boolean passedModule = learningActivityLocalService.getLearningActivitiesOfModuleCount(moduleResult.getModuleId()) > 0;
		long activitiesPassed = 0;
        Date passedDate = new Date(0);
        LearningActivityResult learningActivityResult = null;
        int i = 0;
        
        while(passedModule && i < learningActivities.size()) {	
        	learningActivityResult = learningActivityResultLocalService.getLearningActivityResult(learningActivities.get(i).getActId(), moduleResult.getUserId());

			if(learningActivityResult != null && learningActivityResult.isPassed()) {
				activitiesPassed++;
				if(learningActivityResult.getEndDate()!=null && passedDate.before(learningActivityResult.getEndDate())) {
					passedDate=learningActivityResult.getEndDate();
				} 
			} else {
				passedModule = false;
			}
			i++;
		}

		//Indicamos la media y el resultado del m�dulo.
		long result = learningActivities.size() > 0 ? activitiesPassed * 100 / learningActivities.size() : 0;
		
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
