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
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.xml.Element;
import com.ted.lms.learning.activity.question.model.Answer;
import com.ted.lms.learning.activity.question.model.Question;
import com.ted.lms.learning.activity.question.model.impl.AnswerImpl;
import com.ted.lms.learning.activity.question.service.AnswerLocalService;
import com.ted.lms.learning.activity.question.service.QuestionLocalService;

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
public class AnswerStagedModelDataHandler extends BaseStagedModelDataHandler<Answer> {
	
	@Activate
	protected void activate() {
		XStreamAliasRegistryUtil.register(AnswerImpl.class, "Answer");
	}
	
	private static final Log log = LogFactoryUtil.getLog(AnswerStagedModelDataHandler.class);

	public static final String[] CLASS_NAMES = {Answer.class.getName()};

	@Override
	public String[] getClassNames() {
	    return CLASS_NAMES;
	}
	
	@Override
	public void deleteStagedModel(Answer answer)
		throws PortalException {

		answerLocalService.deleteAnswer(answer);
	}

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException {

		Answer answer = fetchStagedModelByUuidAndGroupId(
			uuid, groupId);

		if (answer != null) {
			deleteStagedModel(answer);
		}
	}

	@Override
	public Answer fetchStagedModelByUuidAndGroupId(
		String uuid, long groupId) {

		return answerLocalService.fetchAnswerByUuidAndGroupId(
			uuid, groupId);
	}

	@Override
	public List<Answer> fetchStagedModelsByUuidAndCompanyId(
		String uuid, long companyId) {

		return answerLocalService.getAnswersByUuidAndCompanyId(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new StagedModelModifiedDateComparator<Answer>());
	}
	
	/**
	 * Añadimos el objeto al portletDataContext
	 */
	@Override
	protected void doExportStagedModel(
	        PortletDataContext portletDataContext, Answer answer)
	    throws Exception {
		
		Element answerElement = portletDataContext.getExportDataElement(answer);

		String answerPath = ExportImportPathUtil.getModelPath(answer);
		
		log.debug("answerPath: " + answerPath);
		
		answerElement.addAttribute("path", answerPath);
		
		Question question = questionLocalService.getQuestion(answer.getQuestionId());
		
		portletDataContext.addReferenceElement(
				answer, answerElement, question,
				PortletDataContext.REFERENCE_TYPE_DEPENDENCY, false);
		
		String answerText = answerExportImportContentProcessor.replaceExportContentReferences(portletDataContext, answer, answer.getAnswer(),
				true,false);

		answer.setAnswer(answerText);

		portletDataContext.addZipEntry(answerPath, answer);
	}
	
	@Override
	protected void doImportStagedModel(PortletDataContext portletDataContext, Answer answer) throws Exception {
		
		long userId = portletDataContext.getUserId(answer.getUserUuid());
		
		answer.setAnswer(answerExportImportContentProcessor.replaceImportContentReferences(portletDataContext, answer, answer.getAnswer()));
		
		ServiceContext serviceContext = portletDataContext.createServiceContext(answer);
		
   	 	serviceContext.setUserId(userId);
   	 	serviceContext.setScopeGroupId(portletDataContext.getScopeGroupId());
   	 
   	 	Map<Long, Long> questionIds = (Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(Question.class);
   	 	long newQuestionId = MapUtil.getLong(questionIds, answer.getQuestionId(), answer.getQuestionId());

	    Answer importedAnswer = null;

	    if (portletDataContext.isDataStrategyMirror()) {
	    	
	    	serviceContext.setUuid(answer.getUuid());

	        Answer existingAnswer =
	            answerLocalService. fetchAnswerByUuidAndGroupId(
	                answer.getUuid(), portletDataContext.getScopeGroupId());

	        if (existingAnswer == null) {

	            serviceContext.setUuid(answer.getUuid());
	            serviceContext.setUserId(userId);
	            serviceContext.setScopeGroupId(portletDataContext.getScopeGroupId());
	            
	            importedAnswer = answerLocalService.addAnswer(userId, portletDataContext.getScopeGroupId(), newQuestionId, answer.getActId(), 
	            		answer.getAnswer(), answer.getFeedbackCorrect(), answer.getFeedbackIncorrect(), answer.isCorrect(), serviceContext);
	        }
	        else {
	            importedAnswer = answerLocalService.updateAnswer(userId, answer.getAnswerId(), answer.getAnswer(), answer.getFeedbackCorrect(), 
	            		answer.getFeedbackIncorrect(), answer.isCorrect());
	        }
	    }
	    else {     
	        importedAnswer = answerLocalService.addAnswer(userId, portletDataContext.getScopeGroupId(), newQuestionId, answer.getActId(), 
            		answer.getAnswer(), answer.getFeedbackCorrect(), answer.getFeedbackIncorrect(), answer.isCorrect(), serviceContext);
	    }
	    
	}
	
	@Override
	public String getDisplayName(Answer answer) {
	    return answer.getAnswer();
	}
	
	protected AnswerLocalService getAnswerLocalService() {
	    return answerLocalService;
	}

	@Reference(unbind = "-")
	protected void setAnswerLocalService(AnswerLocalService answerLocalService) {

	    this.answerLocalService = answerLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setQuestionLocalService(QuestionLocalService questionLocalService) {

	    this.questionLocalService = questionLocalService;
	}
	
	@Reference(
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY,
		target = "(model.class.name=com.ted.lms.learning.activity.answer.model.Answer)"
	)
	private volatile ExportImportContentProcessor<String> answerExportImportContentProcessor;

	private AnswerLocalService answerLocalService;
	private QuestionLocalService questionLocalService;
}
