package com.ted.lms.learning.activity;

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.xml.Element;
import com.ted.lms.learning.activity.question.model.Answer;
import com.ted.lms.learning.activity.question.model.Question;
import com.ted.lms.learning.activity.question.service.AnswerLocalService;
import com.ted.lms.learning.activity.question.service.QuestionLocalService;
import com.ted.lms.model.BaseLearningActivityTypeFactory;
import java.util.List;

/**
 * Base para la factoría de los tipos de actividad
 * @author Virginia Martín Agudo
 *
 */
public abstract class QuestionsLearningActivityTypeFactory extends BaseLearningActivityTypeFactory {
	
	protected QuestionLocalService questionLocalService;
	protected AnswerLocalService answerLocalService;
	
	@Override
	public void doExportStagedModel(PortletDataContext portletDataContext) throws PortalException{
		super.doExportStagedModel(portletDataContext);
		ExportActionableDynamicQuery questionActionableDynamicQuery =
    	        questionLocalService.
    	            getExportActionableDynamicQuery(portletDataContext);

		questionActionableDynamicQuery.performActions();
		
		ExportActionableDynamicQuery answerActionableDynamicQuery =
    	        answerLocalService.
    	            getExportActionableDynamicQuery(portletDataContext);

		answerActionableDynamicQuery.performActions();
	}
	
	@Override
	public void doImportStagedModel(PortletDataContext portletDataContext) throws PortalException {
		super.doImportStagedModel(portletDataContext);
		
		Element questionsElement = portletDataContext.getImportDataGroupElement(Question.class);

	    List<Element> questionElements = questionsElement.elements();

	    for (Element questionElement : questionElements) {
	    	try {
		        StagedModelDataHandlerUtil.importStagedModel(
		            portletDataContext, questionElement);
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    }
	    
	    Element answersElement = portletDataContext.getImportDataGroupElement(Answer.class);

	    List<Element> answerElements = answersElement.elements();

	    for (Element answerElement : answerElements) {
	    	try {
		        StagedModelDataHandlerUtil.importStagedModel(
		            portletDataContext, answerElement);
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    }
	}
}
