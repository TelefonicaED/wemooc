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

package com.ted.lms.learning.activity.survey.service.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.SystemProperties;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.zip.ZipWriter;
import com.liferay.portal.kernel.zip.ZipWriterFactoryUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.ted.lms.learning.activity.question.model.Question;
import com.ted.lms.learning.activity.question.service.QuestionLocalService;
import com.ted.lms.learning.activity.survey.model.SurveyResult;
import com.ted.lms.learning.activity.survey.service.base.SurveyResultLocalServiceBaseImpl;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.service.LearningActivityLocalService;
import com.ted.lms.service.ModuleLocalService;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Locale;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

/**
 * The implementation of the survey result local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ted.lms.learning.activity.survey.service.SurveyResultLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SurveyResultLocalServiceBaseImpl
 * @see com.ted.lms.learning.activity.survey.service.SurveyResultLocalServiceUtil
 */
public class SurveyResultLocalServiceImpl
	extends SurveyResultLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.ted.lms.learning.activity.survey.service.SurveyResultLocalServiceUtil} to access the survey result local service.
	 */
	
	public SurveyResult addSurveyResult(long actId, long latId, long userId, long questionId, long answerId, String textAnswer) {
		
		SurveyResult surveyResult = surveyResultPersistence.create(counterLocalService.increment(SurveyResult.class.getName()));
		
		surveyResult.setActId(actId);
		surveyResult.setLatId(latId);
		surveyResult.setUserId(userId);
		surveyResult.setQuestionId(questionId);
		surveyResult.setAnswerId(answerId);
		surveyResult.setFreeAnswer(textAnswer);
		
		return surveyResultPersistence.update(surveyResult);
	}
	
	public long countAnswerByQuestionId(long questionId, long companyId, long courseGroupCreatedId){
		return surveyResultFinder.countStudentsByQuestionId(questionId, companyId, courseGroupCreatedId);
	}
	
	public long countAnswerByQuestionIdAnswerId(long questionId, long answerId, long companyId, long courseGroupCreatedId) {
		return surveyResultFinder.countStudentsByQuestionIdAndAnswerId(questionId, answerId, companyId, courseGroupCreatedId);
	}
	
	public File exportStatisticsAsFile(String applicationKey, long actId, Locale locale) throws Exception{

		File file = getFileWriter(applicationKey);
		
		try(FileOutputStream bw = new FileOutputStream(file)){

			//Creamos fichero excel
			HSSFWorkbook workbook = new HSSFWorkbook();
		    HSSFSheet sheet = workbook.createSheet(LanguageUtil.get(locale, "learning-activity.survey.statistics.report"));
		  
		    //Creamos formatos
		    HSSFFont font = workbook.createFont();
		    font.setFontName(HSSFFont.FONT_ARIAL);
		    HSSFCellStyle cabecera = workbook.createCellStyle();
		    cabecera.setFont(font); 
		    HSSFCellStyle contenido = workbook.createCellStyle();
		    HSSFFont fontContenido = workbook.createFont();
		    fontContenido.setFontName(HSSFFont.FONT_ARIAL);
		     contenido.setFont(fontContenido); 
		    
			
			//Creamos la cabecera con las preguntas.
			List<Question> questionsTitle = questionLocalService.getQuestionsOrder(actId);
			
			//Anadimos x columnas para mostrar id, curso...
			int numExtraCols = 4;
			String[] cabeceras = new String[questionsTitle.size()+numExtraCols];
	
			//En las columnas extra ponemos la cabecera
			cabeceras[0]=LanguageUtil.get(locale, "id");
			cabeceras[1]=LanguageUtil.get(locale, "course");
			cabeceras[2]=LanguageUtil.get(locale, "module");
			cabeceras[3]=LanguageUtil.get(locale, "activity");
			
			// - Guardamos el orden en que obtenemos las preguntas de la base
			//   de datos para poner las respuestas en el mismo orden.
			// - Generamos cabecera completa.
			Long []questionOrder = new Long[questionsTitle.size()];
	
			for(int i=numExtraCols; i<questionsTitle.size()+numExtraCols; i++){
				cabeceras[i] = HtmlUtil.extractText(questionsTitle.get(i-numExtraCols).getText());
				questionOrder[i-numExtraCols] = questionsTitle.get(i-numExtraCols).getQuestionId();
			}
			
			//Consultamos los nombres de curso, módulo y actividad
			LearningActivity activity = learningActivityLocalService.getLearningActivity(actId);
			
			String curso = courseLocalService.getCourseByGroupCreatedId(activity.getGroupId()).getTitle();
			String modulo = moduleLocalService.fetchModule(activity.getModuleId()).getTitle();
			String actividad = activity.getTitle();
			
			int rowNumber = 0;
			int columnNumber = 0;
			HSSFRow row = sheet.createRow(rowNumber++);
			//Pintamos cabecera
			for (String valor:cabeceras){
				addLabel(sheet, cabecera, columnNumber++, row, valor);
			}
			
			//Reiniciamos columnas
			columnNumber = 0;
			//Por cada pregunta, traemos sus respuestas
			List<SurveyResult> listaRespuestas;
			
			for(Long questionId: questionOrder)
			{									
				//Empezamos por la fila 1
				rowNumber=1;
				listaRespuestas = surveyResultPersistence.findByQuestionIdActId(questionId, actId);
				if(listaRespuestas!=null && listaRespuestas.size()!=0)
				{
					for(SurveyResult answer:listaRespuestas)
					{
						
						row = sheet.getRow(rowNumber);
						if(row==null){
							row = sheet.createRow(rowNumber);
						}
						// La primera vez pintamos los valores 
						// "Id", "Curso", "M�dulo" y "Actividad"
						if(columnNumber == 0){
							addLabel(sheet, contenido, 0, row, String.valueOf(rowNumber));
							addLabel(sheet, contenido, 1, row, HtmlUtil.extractText(curso));
							addLabel(sheet, contenido, 2, row, HtmlUtil.extractText(modulo));
							addLabel(sheet, contenido, 3, row, HtmlUtil.extractText(actividad));
							addLabel(sheet, contenido, 4, row, HtmlUtil.extractText(answer.getFreeAnswer()));
						}
						else {
							addLabel(sheet, contenido, columnNumber+numExtraCols, row, HtmlUtil.extractText(answer.getFreeAnswer()));
						}
						rowNumber++;
					}
					columnNumber++;//Columna nueva
				}
			}
			
			workbook.write(bw);		
		}
	
		return file;
	}
	
	private File getFileWriter(String applicationKey) {

		StringBundler sb = new StringBundler(8);

		sb.append(applicationKey);
		sb.append(StringPool.UNDERLINE);
		sb.append(Time.getShortTimestamp());
		sb.append(".xls");

		String fileName = sb.toString();

		return 
			new File(
				SystemProperties.get(SystemProperties.TMP_DIR) +
					StringPool.SLASH + fileName);
	}
	
	private void addLabel(HSSFSheet sheet, HSSFCellStyle style,
			 int column, HSSFRow row, String value) {
		HSSFCell cell = row.createCell(column, CellType.STRING);
		cell.setCellStyle(style);
		cell.setCellValue(value);
	}
	
	@ServiceReference(type = CourseLocalService.class)
	protected CourseLocalService courseLocalService;
	
	@ServiceReference(type = ModuleLocalService.class)
	protected ModuleLocalService moduleLocalService;
	
	@ServiceReference(type = QuestionLocalService.class)
	protected QuestionLocalService questionLocalService;
	
	@ServiceReference(type = LearningActivityLocalService.class)
	protected LearningActivityLocalService learningActivityLocalService;
}