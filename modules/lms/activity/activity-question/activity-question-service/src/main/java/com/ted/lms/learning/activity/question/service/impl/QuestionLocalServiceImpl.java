/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.ted.lms.learning.activity.question.service.impl;

import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.upload.AttachmentContentUpdater;
import com.ted.lms.copy.content.processor.DLReferencesCopyContentProcessor;
import com.ted.lms.learning.activity.question.constants.QuestionConstants;
import com.ted.lms.learning.activity.question.model.Answer;
import com.ted.lms.learning.activity.question.model.Question;
import com.ted.lms.learning.activity.question.model.QuestionType;
import com.ted.lms.learning.activity.question.model.QuestionTypeFactory;
import com.ted.lms.learning.activity.question.registry.QuestionTypeFactoryRegistryUtil;
import com.ted.lms.learning.activity.question.service.AnswerLocalService;
import com.ted.lms.learning.activity.question.service.base.QuestionLocalServiceBaseImpl;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityTypeFactory;
import com.ted.lms.registry.LearningActivityTypeFactoryRegistryUtil;
import com.ted.lms.service.LearningActivityLocalService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.portlet.ActionRequest;
import javax.portlet.PortletException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * The implementation of the question local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.ted.lms.learning.activity.question.service.QuestionLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see QuestionLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.ted.lms.learning.activity.question.model.Question",
	service = AopService.class
)
public class QuestionLocalServiceImpl extends QuestionLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>com.ted.lms.learning.activity.question.service.QuestionLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.ted.lms.learning.activity.question.service.QuestionLocalServiceUtil</code>.
	 */
	private static final Log log = LogFactoryUtil.getLog(QuestionLocalServiceImpl.class);
	
	public List<Question> getQuestions(long actId){
		return questionPersistence.findByActId(actId);
	}
	
	public int getQuestionsCount(long actId){
		return questionPersistence.countByActId(actId);
	}
	
	public List<Question> getQuestionsOrder(long actId){
		OrderByComparator<Question> orderByComparator = OrderByComparatorFactoryUtil.create("qu_question", "weight", true);
		return questionPersistence.findByActId(actId, -1, -1, orderByComparator);
	}
	
	@Override
	public Question addQuestion(long userId, long groupId, long actId, String questionText, long questionType, boolean penalize, 
			ServiceContext serviceContext) throws PortalException {
		
		Question question = questionPersistence.create(counterLocalService.increment(Question.class.getName()));
		
		//Auditoria
		question.setUuid(serviceContext.getUuid());
		question.setGroupId(groupId);
		question.setCompanyId(serviceContext.getCompanyId());
		question.setUserId(userId);
		User user = userLocalService.getUser(userId);
		question.setUserName(user.getFullName());
		question.setCompanyId(user.getCompanyId());

		question.setText(questionText);
		
		question.setPenalize(penalize);
		question.setQuestionTypeId(questionType);
		question.setActId(actId);
		question.setWeight(question.getQuestionId());
		
		return questionPersistence.update(question);
		
	}
	
	@Override
	public Question updateQuestion(long userId, long questionId, String questionText, boolean penalize) throws PortalException {
		Question question = questionPersistence.fetchByPrimaryKey(questionId);

		User user = userLocalService.getUser(userId);
		
		question.setText(questionText);
		question.setPenalize(penalize);
		question.setUserId(userId);
		question.setUserName(user.getFullName());
		
		return questionPersistence.update(question);
	}
	
	public Question copyQuestion(long userId, long groupId, long actId, Question oldQuestion, ServiceContext serviceContext) throws Exception {
		
		serviceContext.setUuid(oldQuestion.getUuid());
		
		Question newQuestion = addQuestion(userId, groupId, actId, oldQuestion.getText(), oldQuestion.getQuestionTypeId(), 
				oldQuestion.isPenalize(), serviceContext);
		List<Answer> oldAnswers = answerLocalService.getAnswersByQuestionId(oldQuestion.getQuestionId());
		for(Answer oldAnswer: oldAnswers) {
			serviceContext.setUuid(oldAnswer.getUuid());
			answerLocalService.copyAnswer(userId, groupId, newQuestion.getQuestionId(), actId, oldAnswer, serviceContext);
		}
		copyQuestionImages(oldQuestion, newQuestion);
		
		newQuestion = questionPersistence.update(newQuestion);
		
		return newQuestion;
	}
	
	public void copyQuestionImages(Question oldQuestion, Question newQuestion) throws Exception {
		newQuestion.setText(dlReferencesCopyContentProcessor.replaceExportDLReferences(newQuestion.getText(), oldQuestion.getGroupId(), newQuestion.getGroupId(), newQuestion.getUserId()));
	}

	
	public void saveQuestions(ActionRequest actionRequest, long actId) throws PortalException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		//Pedimos las preguntas que ya existían por si alguna ha sido borrada eliminarla
		List<Question> existingQuestions = questionPersistence.findByActId(actId);
		List<Long> existingQuestionIds = new ArrayList<Long>();
		
		for(Question question: existingQuestions){
			existingQuestionIds.add(question.getQuestionId());
		}
		
		//Recorro todas las respuestas y las actualizo o las creo en funcion de si son nuevas o modificaciones y si son modificaciones guardo sus ids en un array para despues borrar las que no existan.
		String[] iteratorQuestionIds = ParamUtil.getParameterValues(actionRequest, "iteratorQuestion", null);
		List<Long> editingQuestionIds = new ArrayList<Long>();
		
		long questionId = 0;
		long questionTypeId = 0;
		String questionText = null;
		Question question = null;
		
		boolean penalize = false;
		QuestionTypeFactory questionTypeFactory  = null;
		QuestionType questionType = null;
		
		ServiceContext serviceContext = ServiceContextFactory.getInstance(Question.class.getName(), actionRequest);
		
		if(Validator.isNotNull(iteratorQuestionIds)) {
		
			for(String iteratorQuestion: iteratorQuestionIds) {
				
				questionId = ParamUtil.getLong(actionRequest, "questionId_" + iteratorQuestion, 0);
				questionTypeId = ParamUtil.getLong(actionRequest, "questionType_" + iteratorQuestion);
				penalize = ParamUtil.getBoolean(actionRequest, "penalize_" + iteratorQuestion, false);
				
				questionTypeFactory = QuestionTypeFactoryRegistryUtil.getQuestionTypeFactoryByType(questionTypeId);
				
				questionText = ParamUtil.getString(actionRequest, "question_title_" + iteratorQuestion);
				
				log.debug("***penalize:"+penalize);			
				log.debug("***questionId:"+questionId);
				log.debug("***questionType: " + questionType);
				log.debug("***questionText: " + questionText);
				
				if(questionId == 0){
					question = addQuestion(themeDisplay.getUserId(), themeDisplay.getScopeGroupId(), actId, questionText, questionTypeId, penalize, serviceContext);
				}else{
					question = updateQuestion(serviceContext.getUserId(), questionId, questionText, penalize);
				}
				
				questionType = questionTypeFactory.getQuestionType(question);
				questionType.setExtraContent(actionRequest, iteratorQuestion);
	
				questionPersistence.update(question);
				editingQuestionIds.add(question.getQuestionId());
				
				log.debug("question.getQuestionType() " + question.getQuestionTypeId());
				
				//Cada tipo de pregunta guarda sus respuestas
				questionType.saveAnswers(actionRequest, iteratorQuestion);
			}
		}
		
		//Eliminamos las preguntas que no estén	
		for(Long existingQuestionId:existingQuestionIds){
			if(editingQuestionIds != null && editingQuestionIds.size()>0){
				if(!editingQuestionIds.contains(existingQuestionId)){
					questionPersistence.remove(existingQuestionId);
				}
			}else {
				questionPersistence.remove(existingQuestionId);
			}
		}
		
		log.debug("questionType: " + questionType);
	}
	
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		final PortletDataContext portletDataContext, final long actId) {
		final ExportActionableDynamicQuery exportActionableDynamicQuery = new ExportActionableDynamicQuery() {
				@Override
				public long performCount() throws PortalException {
					ManifestSummary manifestSummary = portletDataContext.getManifestSummary();

					StagedModelType stagedModelType = getStagedModelType();

					long modelAdditionCount = super.performCount();

					manifestSummary.addModelAdditionCount(stagedModelType,
						modelAdditionCount);

					long modelDeletionCount = ExportImportHelperUtil.getModelDeletionCount(portletDataContext,
							stagedModelType);

					manifestSummary.addModelDeletionCount(stagedModelType,
						modelDeletionCount);

					return modelAdditionCount;
				}
			};

		initActionableDynamicQuery(exportActionableDynamicQuery);

		exportActionableDynamicQuery.setAddCriteriaMethod(new ActionableDynamicQuery.AddCriteriaMethod() {
				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					portletDataContext.addDateRangeCriteria(dynamicQuery,
						"modifiedDate");
				}
			});
		
		exportActionableDynamicQuery.setAddCriteriaMethod(new ActionableDynamicQuery.AddCriteriaMethod() {
				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					Property actIdProperty = PropertyFactoryUtil.forName(
							"actId");

					Criterion actIdCriterion = actIdProperty.eq(actId);

					dynamicQuery.add(actIdCriterion);
				}
			});

		exportActionableDynamicQuery.setCompanyId(portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setGroupId(portletDataContext.getScopeGroupId());

		exportActionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<Question>() {
				@Override
				public void performAction(Question question)
					throws PortalException {
					StagedModelDataHandlerUtil.exportStagedModel(portletDataContext,
						question);
				}
			});
		exportActionableDynamicQuery.setStagedModelType(new StagedModelType(
				PortalUtil.getClassNameId(Question.class.getName())));

		return exportActionableDynamicQuery;
	}
	
	@Override
	public Question deleteQuestion(Question question) {
		question = super.deleteQuestion(question);
		List<Answer> answers = answerLocalService.getAnswersByQuestionId(question.getQuestionId());
		for(Answer answer: answers) {
			answerLocalService.deleteAnswer(answer);
		}
		
		return question;
	}
	
	public JSONObject importQuestionsExcel(long userId, long actId, FileEntry fileEntry, long[] questionIdsAllowed, Locale locale) throws PortalException, IOException {
		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		
		Workbook workbook = null;
		
		try(InputStream excelFile = fileEntry.getContentStream()){ 
			
			try{
				workbook = new HSSFWorkbook(excelFile);
			}catch(Exception e){//Excel 2007
				workbook = new XSSFWorkbook(excelFile);
			}			
	
			//Cogemos la primera hoja
			Sheet worksheet = workbook.getSheetAt(0);
			int fila = 0;
			String questionTitle, feedbackCorrect, feedbackIncorrect;
			int questionType;
			boolean questionPenalize;
			String answerTitle;
			boolean answerIsCorrect;
			boolean firstLine = true;
			Row row = worksheet.getRow(fila);
			Question question =null;
			ServiceContext serviceContext=null;
			ServiceContext serviceContextAnswer = null;
			JSONArray questionAddIds = JSONFactoryUtil.createJSONArray(); 
			JSONArray errors = JSONFactoryUtil.createJSONArray();
			LearningActivity activity = learningActivityLocalService.getLearningActivity(actId);
			
			while(row != null){
				//La primera linea es la cabecera
				try{
					if(!firstLine){
						try{	
							questionTitle = row.getCell(QuestionConstants.COLUMN_INDEX_QUESTION_TITLE).getStringCellValue();
							answerTitle = row.getCell(QuestionConstants.COLUMN_INDEX_ANSWER_TITLE).getStringCellValue();
							answerIsCorrect = Boolean.parseBoolean(row.getCell(QuestionConstants.COLUMN_INDEX_ANSWER_IS_CORRECT).getStringCellValue());
							feedbackCorrect =  row.getCell(QuestionConstants.COLUMN_INDEX_ANSWER_FEEDBACK_CORRECT).getStringCellValue();
							feedbackIncorrect = row.getCell(QuestionConstants.COLUMN_INDEX_ANSWER_FEEDBACK_INCORRECT).getStringCellValue();
							
							serviceContext = new ServiceContext();
							serviceContext.setCompanyId(activity.getCompanyId());
							
							if(questionTitle!=null && Validator.isNotNull(questionTitle.trim())){
								questionType = Integer.valueOf(row.getCell(QuestionConstants.COLUMN_INDEX_QUESTION_TYPE).getStringCellValue());
								questionPenalize = Boolean.parseBoolean(row.getCell(QuestionConstants.COLUMN_INDEX_QUESTION_PENALIZE).getStringCellValue());
								//Es pregunta
								if (log.isDebugEnabled()) log.debug("Line: " + fila + " ***********Es pregunta************");
								if (log.isDebugEnabled()) log.debug("Line: " + fila + " Titulo pregunta: " + questionTitle);					
								if (log.isDebugEnabled()) log.debug("Line: " + fila + " Tipo: " + questionType);
								if (log.isDebugEnabled()) log.debug("Line: " + fila + " Penalize: " + questionPenalize);
	
								//Creamos la pregunta
								question = questionLocalService.addQuestion(userId, activity.getGroupId(),
										actId, questionTitle, questionType, questionPenalize, serviceContext);
								questionAddIds.put(question.getQuestionId());
								if(answerTitle!=null && Validator.isNotNull(answerTitle.trim())){
									//Si tiene respuestas, creamos la respuesta 
									if (log.isDebugEnabled()) log.debug("Line: " + fila + " ***********Tiene respuesta************");
									if (log.isDebugEnabled()) log.debug("Line: " + fila + " Titulo respuesta: " + answerTitle);
									if (log.isDebugEnabled()) log.debug("Line: " + fila + " Es correcta: " + answerIsCorrect);
									serviceContextAnswer = new ServiceContext();
									serviceContextAnswer.setCompanyId(activity.getCompanyId());
									answerLocalService.addAnswer(userId, activity.getGroupId(), 
											question.getQuestionId(), actId, answerTitle, feedbackCorrect, feedbackIncorrect, answerIsCorrect, serviceContextAnswer);
								}
							}else{	//Es solo respuesta
								if (log.isDebugEnabled()) log.debug("Line: " + fila + " ***********Es solo respuesta************");
								if (log.isDebugEnabled()) log.debug("Line: " + fila + " Titulo respuesta: " + answerTitle);
								if (log.isDebugEnabled()) log.debug("Line: " + fila + " Es correcta: " + answerIsCorrect);
								if(feedbackCorrect!=null && feedbackCorrect.length()>1000){
									feedbackCorrect = feedbackCorrect.substring(0, 999);
								}
	
								if(feedbackIncorrect!=null && feedbackIncorrect.length()>1000){
									feedbackIncorrect = feedbackIncorrect.substring(0, 999);
								}
								if(question!=null){
									serviceContextAnswer = new ServiceContext();
									serviceContextAnswer.setCompanyId(activity.getCompanyId());
									answerLocalService.addAnswer(userId, activity.getGroupId(), 
											question.getQuestionId(), actId, answerTitle, feedbackCorrect, feedbackIncorrect, answerIsCorrect, serviceContextAnswer);
								}
							}
							
						}catch(Exception e){
							log.error(e.getMessage());
							log.debug(e);
							log.error("FILA "+fila);
							result.put(QuestionConstants.IMPORT_JSON_RESULT, QuestionConstants.IMPORT_JSON_RESULT_ERROR);
							JSONObject error = JSONFactoryUtil.createJSONObject();
							error.put(String.valueOf(fila), e.getLocalizedMessage());
							errors.put(error);
						}
					}else{
						firstLine = false;
					}
					fila++;
					row = worksheet.getRow(fila);
				}catch(Exception e){
					e.printStackTrace();
					result.put(QuestionConstants.IMPORT_JSON_RESULT, QuestionConstants.IMPORT_JSON_RESULT_ERROR);
					JSONObject error = JSONFactoryUtil.createJSONObject();
					error.put(String.valueOf(fila), e.getLocalizedMessage());
					errors.put(error);
				}
			}
			
			if(result.getInt(QuestionConstants.IMPORT_JSON_RESULT) != QuestionConstants.IMPORT_JSON_RESULT_ERROR) {
				result.put(QuestionConstants.IMPORT_JSON_RESULT, QuestionConstants.IMPORT_JSON_RESULT_SUCCESS);
				result.put(QuestionConstants.IMPORT_JSON_QUESTION_IDS, questionAddIds);
			}else {
				result.put(QuestionConstants.IMPORT_JSON_ERRORS, errors);
			}
		}finally {
			if(workbook != null) {
				workbook.close();
			}
		}
		
		return result;

	}
	
	public JSONObject importQuestionsXML(long userId, long actId, FileEntry fileEntry, long[] questionIdsAllowed, Locale locale) throws PortalException {

		LearningActivity activity = learningActivityLocalService.getLearningActivity(actId);
		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		
		try {
			System.out.println("fileEntry: " + fileEntry.getFileEntryId());
			Document document = SAXReaderUtil.read(fileEntry.getContentStream());
			if (isTypeAllowed(activity.getCompanyId(), questionIdsAllowed, document)){
				JSONArray questionAddIds = JSONFactoryUtil.createJSONArray(); 
				
				Element rootElement = document.getRootElement();
				long questionTypeId = -1;
				QuestionTypeFactory questionTypeFactory = null;
				Question question = null;
				ServiceContext serviceContext = new ServiceContext();
				serviceContext.setCompanyId(activity.getCompanyId());
				for(Element questionElement:rootElement.elements("question")){
					questionTypeId = getQuestionType(activity.getCompanyId(), questionIdsAllowed, questionElement);
					if(questionTypeId != -1){
						questionTypeFactory = QuestionTypeFactoryRegistryUtil.getQuestionTypeFactoryByType(questionTypeId);
						question = questionTypeFactory.importQuestionXML(userId, activity.getGroupId(), actId, questionElement, serviceContext);
						if(question != null) {
							questionAddIds.put(question.getQuestionId());
						}
					}
				}
				result.put(QuestionConstants.IMPORT_JSON_RESULT, QuestionConstants.IMPORT_JSON_RESULT_SUCCESS);
				result.put(QuestionConstants.IMPORT_JSON_QUESTION_IDS, questionAddIds);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
			Matcher matcher = DOCUMENT_EXCEPTION_MATCHER.matcher(e.getMessage());
			
			result.put(QuestionConstants.IMPORT_JSON_RESULT, QuestionConstants.IMPORT_JSON_RESULT_ERROR);

			if(matcher.matches()) {
				result.put(QuestionConstants.IMPORT_JSON_ERRORS, LanguageUtil.format(locale, "questions.import.error.xml.parse-xml-line", matcher.group(1)));
			} else{
				result.put(QuestionConstants.IMPORT_JSON_ERRORS, LanguageUtil.get(locale, "questions.import.error.xml.parse-xml"));		
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put(QuestionConstants.IMPORT_JSON_RESULT, QuestionConstants.IMPORT_JSON_RESULT_ERROR);
			result.put(QuestionConstants.IMPORT_JSON_ERRORS, e.getLocalizedMessage());		
		}
		
		return result;
	}
	
	private long getQuestionType(long companyId, long[] questionIdsAllowed, Element question) {
		long type = -1;		
		
		List<QuestionTypeFactory> questionTypeFactories = QuestionTypeFactoryRegistryUtil.getQuestionFactories(companyId, questionIdsAllowed);
		
		boolean finded = false;
		int i = 0;
		String questionType = question.attributeValue("type");
		String[] questionTypes = null;
		while(!finded && i < questionTypeFactories.size()) {
			System.out.println("questionTypeFactory: " + questionTypeFactories.get(i).getClassName());
			System.out.println("question: " + questionType + " - " + questionTypeFactories.get(i).getXMLType());
			questionTypes = questionTypeFactories.get(i).getXMLType();
			for(String xml: questionTypes) {
				System.out.println("xml: " + xml);
			}
			
			finded = ArrayUtil.contains(questionTypeFactories.get(i).getXMLType(), questionType, true);
			if(!finded) {
				i++;
			}else {
				type = questionTypeFactories.get(i).getType();
			}
		}
		System.out.println("return type: " + type);
		return type;
	}
	
	public JSONObject validateFileXML(FileEntry fileEntry) throws Exception {
		
		log.debug("validamos el fichero");
		
		JSONObject jsonObject = null;
		
		try (InputStream inputStream = dlFileEntryLocalService.getFileAsStream(
				fileEntry.getFileEntryId(), fileEntry.getVersion(), false);) {
			

			//Comprobamos las cabeceras
			
			//log.debug("headers: " + headers.length);
			
			jsonObject = JSONFactoryUtil.createJSONObject();
			
		/*	jsonObject.put(
					"infoMessages",
					reader.getLinesRead());*/
			
		}
		return jsonObject;
	}
	
	private boolean isTypeAllowed(long companyId, long[] questionIdsAllowed, Document document){

		Element rootElement = document.getRootElement();
		List<Element> elements = rootElement.elements("question");
		System.out.println("elements: " + elements.size());
		for(Element question:elements){
			System.out.println("question: " + question.toString());
			long type = getQuestionType(companyId, questionIdsAllowed, question);
			System.out.println("type: " + type);
			if (type < 0)
				return false;
		}
		return true;
	}
	
	public PrintWriter exportXMLQuestions(OutputStream outputStream, long actId) throws PortalException, IOException {
		try (PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream,StringPool.UTF8))){
			
			Element quizXML=SAXReaderUtil.createElement("quiz");
			Document quizXMLDoc=SAXReaderUtil.createDocument(quizXML);
	
			List<Question> questions = questionLocalService.getQuestions(actId);
	
			if(questions!=null &&questions.size()>0){
				QuestionTypeFactory questionTypeFactory = null;
				QuestionType questionType = null;
				for(Question question:questions){
					questionTypeFactory = QuestionTypeFactoryRegistryUtil.getQuestionTypeFactoryByType(question.getQuestionTypeId());
					questionType = questionTypeFactory.getQuestionType(question);
					
					System.out.println("quesitonType: " + questionType.getClassName());
					
					quizXML.add(questionType.exportXML());
				}
			}
	
			printWriter.write(quizXMLDoc.formattedString());
			printWriter.flush();
			printWriter.close();
			
			return printWriter;
		}
	}
	
	public File exportExcelQuestions(Locale locale, long actId) throws PortletException, IOException {

		log.debug("::ARRANCAMOS HILO USERS EXPORT EXCEL:::"); 
		int rowNumber = 1;
		// Presenta en pantalla informacion sobre este hilo en particular
		File file = FileUtil.createTempFile("xls");
		try {

			FileOutputStream bw = null;
			try {
				bw = new FileOutputStream(file);
			} 
			catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			String [] headers = {"title","type","penalize","answer","correct","feedbackCorrect","feedbackNoCorrect"} ;
			String [] headersTitle = new String [headers.length];
			int i = 0;
			for(String header : headers) {
				headersTitle[i++] = LanguageUtil.get(locale, header, header);
				log.debug("hearder: " + header);
			}

			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet(LanguageUtil.get(locale, "questions", "questions"));

			HSSFFont font = workbook.createFont();
			font.setFontName(QuestionConstants.TIMES_NEW_ROMAN);
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			HSSFCellStyle style = workbook.createCellStyle();
			style.setFont(font);

			exportExcelLine(headersTitle, sheet.createRow(0), style);

			font = workbook.createFont();
			font.setFontName(QuestionConstants.TIMES_NEW_ROMAN);
			style = workbook.createCellStyle();
			style.setFont(font);
			String[] questionLine = new String[headers.length];
			List<Answer> answers = null;

			for(Question question: questionLocalService.getQuestions(actId)){
				questionLine[QuestionConstants.COLUMN_INDEX_QUESTION_TITLE]=question.getText();
				questionLine[QuestionConstants.COLUMN_INDEX_QUESTION_TYPE]=String.valueOf(question.getQuestionType());
				questionLine[QuestionConstants.COLUMN_INDEX_QUESTION_PENALIZE]= String.valueOf(question.getPenalize());
				i=0;
				answers = answerLocalService.getAnswersByQuestionId(question.getQuestionId());
				
				if(answers != null && answers.size() > 0){
					for(Answer answer: answers){
						questionLine[QuestionConstants.COLUMN_INDEX_ANSWER_TITLE]=answer.getAnswer();
						questionLine[QuestionConstants.COLUMN_INDEX_ANSWER_IS_CORRECT]=String.valueOf(answer.isCorrect());
						questionLine[QuestionConstants.COLUMN_INDEX_ANSWER_FEEDBACK_CORRECT]=answer.getFeedbackCorrect();
						questionLine[QuestionConstants.COLUMN_INDEX_ANSWER_FEEDBACK_INCORRECT]=answer.getFeedbackIncorrect();
	
						exportExcelLine(questionLine, sheet.createRow(rowNumber++), style);
	
						if(questionLine[QuestionConstants.COLUMN_INDEX_QUESTION_TITLE]!="" || questionLine[QuestionConstants.COLUMN_INDEX_QUESTION_TYPE]!=""|| questionLine[QuestionConstants.COLUMN_INDEX_QUESTION_PENALIZE]!= ""){
							questionLine[QuestionConstants.COLUMN_INDEX_QUESTION_TITLE]="";
							questionLine[QuestionConstants.COLUMN_INDEX_QUESTION_TYPE]="";
							questionLine[QuestionConstants.COLUMN_INDEX_QUESTION_PENALIZE]= "";
						}
					}
				}else{
					questionLine[QuestionConstants.COLUMN_INDEX_ANSWER_TITLE]="";
					questionLine[QuestionConstants.COLUMN_INDEX_ANSWER_IS_CORRECT]="";
					questionLine[QuestionConstants.COLUMN_INDEX_ANSWER_FEEDBACK_CORRECT]="";
					questionLine[QuestionConstants.COLUMN_INDEX_ANSWER_FEEDBACK_INCORRECT]="";
					exportExcelLine(questionLine, sheet.createRow(rowNumber++), style);
				}

			}
			workbook.write(bw);

			try {
				bw.close();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}		
			
			workbook.close();

		}catch (Exception e) {
			e.printStackTrace();
		} 

		return file;

	}
	
	private void exportExcelLine(String[] line,HSSFRow row,HSSFCellStyle style){
		int columnNumer = 0;
		for (String column : line) {
			HSSFCell cell = row.createCell(columnNumer++, HSSFCell.CELL_TYPE_STRING);
			cell.setCellStyle(style);
			cell.setCellValue(column);
		}
	}
	
	@Reference
	private AttachmentContentUpdater attachmentContentUpdater;
	
	@Reference
	protected DLReferencesCopyContentProcessor dlReferencesCopyContentProcessor;
	
	@Reference
	private AnswerLocalService answerLocalService;
	
	@Reference
	private LearningActivityLocalService learningActivityLocalService;
	
	public static final Pattern DOCUMENT_EXCEPTION_MATCHER = Pattern.compile("Error on line (\\d+) of document ([^ ]+) : (.*)");
}