package com.ted.lms.learning.activity.question.internal.exportimport.data.handler;

import com.liferay.exportimport.content.processor.ExportImportContentProcessor;
import com.liferay.exportimport.data.handler.base.BaseStagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.ExportImportPathUtil;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelModifiedDateComparator;
import com.liferay.exportimport.kernel.xstream.XStreamAliasRegistryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.trash.TrashHandler;
import com.liferay.portal.kernel.trash.TrashHandlerRegistryUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.xml.Element;
import com.ted.lms.learning.activity.question.model.Answer;
import com.ted.lms.learning.activity.question.model.Question;
import com.ted.lms.learning.activity.question.model.impl.QuestionImpl;
import com.ted.lms.learning.activity.question.service.QuestionLocalService;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.Module;
import com.ted.lms.service.LearningActivityLocalService;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

@Component(
	immediate = true, 
	service = StagedModelDataHandler.class
)
public class QuestionStagedModelDataHandler extends BaseStagedModelDataHandler<Question> {
	
	@Activate
	protected void activate() {
		XStreamAliasRegistryUtil.register(QuestionImpl.class, "Question");
	}
	
	private static final Log log = LogFactoryUtil.getLog(QuestionStagedModelDataHandler.class);

	public static final String[] CLASS_NAMES = {Question.class.getName()};

	@Override
	public String[] getClassNames() {
	    return CLASS_NAMES;
	}
	
	@Override
	public void deleteStagedModel(Question question)
		throws PortalException {

		questionLocalService.deleteQuestion(question);
	}

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException {

		Question question = fetchStagedModelByUuidAndGroupId(
			uuid, groupId);

		if (question != null) {
			deleteStagedModel(question);
		}
	}

	@Override
	public Question fetchStagedModelByUuidAndGroupId(
		String uuid, long groupId) {

		return questionLocalService.fetchQuestionByUuidAndGroupId(
			uuid, groupId);
	}

	@Override
	public List<Question> fetchStagedModelsByUuidAndCompanyId(
		String uuid, long companyId) {

		return questionLocalService.getQuestionsByUuidAndCompanyId(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new StagedModelModifiedDateComparator<Question>());
	}
	
	/**
	 * Añadimos el objeto al portletDataContext
	 */
	@Override
	protected void doExportStagedModel(
	        PortletDataContext portletDataContext, Question question)
	    throws Exception {
		
		Element questionElement = portletDataContext.getExportDataElement(question);

		String questionPath = ExportImportPathUtil.getModelPath(question);
		
		log.debug("questionPath: " + questionPath);
		
		questionElement.addAttribute("path", questionPath);
		
		LearningActivity activity = learningActivityLocalService.getLearningActivity(question.getActId());
		StagedModelDataHandlerUtil.exportReferenceStagedModel(portletDataContext, question, activity, PortletDataContext.REFERENCE_TYPE_PARENT);
		
		String text = questionExportImportContentProcessor.replaceExportContentReferences(portletDataContext, question, question.getText(),
				true,false);

		question.setText(text);

		portletDataContext.addZipEntry(questionPath, question);
	}
	
	@Override
	protected void doImportStagedModel(PortletDataContext portletDataContext, Question question) throws Exception {
		
		long userId = portletDataContext.getUserId(question.getUserUuid());
		
		question.setText(questionExportImportContentProcessor.replaceImportContentReferences(portletDataContext, question, question.getText()));
		
		ServiceContext serviceContext = portletDataContext.createServiceContext(question);
		
   	 	serviceContext.setUserId(userId);
   	 	serviceContext.setScopeGroupId(portletDataContext.getScopeGroupId());
   	 	
   	 	Map<Long, Long> activityIds = (Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(LearningActivity.class);
   	 	long activityId = MapUtil.getLong(activityIds, question.getActId(), question.getActId());

	    Question importedQuestion = null;

	    if (portletDataContext.isDataStrategyMirror()) {
	    	
	    	serviceContext.setUuid(question.getUuid());
	    	
	    	log.debug("comprobamos la pregunta: " + question.getUuid() + " - " + portletDataContext.getScopeGroupId());

	        Question existingQuestion =
	            questionLocalService. fetchQuestionByUuidAndGroupId(
	                question.getUuid(), portletDataContext.getScopeGroupId());

	        if (existingQuestion == null) {

	            serviceContext.setUuid(question.getUuid());
	            serviceContext.setUserId(userId);
	            serviceContext.setScopeGroupId(portletDataContext.getScopeGroupId());
	            
	            importedQuestion = questionLocalService.addQuestion(userId, portletDataContext.getScopeGroupId(), activityId, question.getText(), 
	            		question.getQuestionTypeId(), question.isPenalize(), serviceContext);
	        }
	        else {
	            importedQuestion = questionLocalService.updateQuestion(userId, question.getQuestionId(), question.getText(), question.isPenalize());
	        }
	    }
	    else {     
	        importedQuestion = questionLocalService.addQuestion(userId, portletDataContext.getScopeGroupId(), activityId, question.getText(), 
            		question.getQuestionTypeId(), question.isPenalize(), serviceContext);
	    }
	    
	    Map<Long, Long> questionIds = (Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(Question.class);

	    questionIds.put(question.getPrimaryKey(), importedQuestion.getPrimaryKey());
	    
	    importQuestionAnswers(portletDataContext, question, importedQuestion);
	}
	
	protected void importQuestionAnswers(PortletDataContext portletDataContext, 
			Question question,
			Question importedQuestion)
		throws Exception {
		
		List<Element> answerElements = portletDataContext.getReferenceDataElements(question, Answer.class);
		
		 for (Element answerElement : answerElements) {
	        StagedModelDataHandlerUtil.importStagedModel(portletDataContext, answerElement);
	    }
	}
	
	@Override
	public String getDisplayName(Question question) {
	    return question.getText();
	}
	
	protected QuestionLocalService getQuestionLocalService() {
	    return questionLocalService;
	}

	@Reference(unbind = "-")
	protected void setQuestionLocalService(QuestionLocalService questionLocalService) {

	    this.questionLocalService = questionLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setLearningActivityLocalService(LearningActivityLocalService learningActivityLocalService) {

	    this.learningActivityLocalService = learningActivityLocalService;
	}
	
	@Reference(
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY,
		target = "(model.class.name=com.ted.lms.learning.activity.question.model.Question)"
	)
	private volatile ExportImportContentProcessor<String> questionExportImportContentProcessor;

	private QuestionLocalService questionLocalService;
	private LearningActivityLocalService learningActivityLocalService;
}
