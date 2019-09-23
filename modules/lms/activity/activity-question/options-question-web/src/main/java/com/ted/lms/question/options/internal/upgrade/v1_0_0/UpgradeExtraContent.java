package com.ted.lms.question.options.internal.upgrade.v1_0_0;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.service.ReleaseLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.ted.lms.learning.activity.question.model.Question;
import com.ted.lms.learning.activity.question.service.QuestionLocalService;
import com.ted.lms.question.options.OptionsQuestionTypeFactory;
import com.ted.lms.question.options.constants.OptionsConstants;

import java.util.List;

public class UpgradeExtraContent extends UpgradeProcess {
	
	public UpgradeExtraContent(QuestionLocalService questionLocalService, ReleaseLocalService releaseLocalService){
		this.questionLocalService = questionLocalService;
		this.releaseLocalService = releaseLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		//Comprobamos si se está migrando o no
		Release release = releaseLocalService.fetchRelease("liferaylms-portlet");
		if(release != null && release.getBuildNumber() > 0) {
			
			List<Question> questions = questionLocalService.getQuestions(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			JSONObject questionExtraContent = null;
			Document document = null;
			Element rootElement = null;
			JSONObject questionTypeContent = null;
			Element formatTypeElement = null;
			Element partialCorrectionElement = null;
			long questionType = 0;
			
			for(Question question: questions) {
				if(Validator.isNotNull(question.getExtraContent())) {
					try {
						document=SAXReaderUtil.read(question.getExtraContent());
						rootElement =document.getRootElement();
						questionExtraContent = JSONFactoryUtil.createJSONObject();
						questionType = question.getQuestionTypeId();
						
						if(questionType == OptionsQuestionTypeFactory.TYPE || questionType == 1
								|| questionType == 6 || questionType == 7) {
							
							questionTypeContent = JSONFactoryUtil.createJSONObject();
							questionExtraContent.put(OptionsConstants.JSON_OPTIONS, questionTypeContent);
							
							formatTypeElement = rootElement.element("formattype");
							if(formatTypeElement != null) {
								questionTypeContent.put(OptionsConstants.JSON_FORMAT_TYPE, formatTypeElement.getText());
							}
							
							//Si existe el campo que guarda partialcorrection lo guardamos a nivel de pregunta, ya que es 
							//común a todas
							partialCorrectionElement = rootElement.element("partialcorrection");
							if(partialCorrectionElement != null) {
								questionTypeContent.put(OptionsConstants.JSON_PARTIAL_CORRECTION, Boolean.parseBoolean(partialCorrectionElement.getText()));
							}
							
							//Comprobamos ahora si este campo está guardado con el nombre antiguo
							partialCorrectionElement = rootElement.element("correctiontype");
							if(partialCorrectionElement != null) {
								questionTypeContent.put(OptionsConstants.JSON_PARTIAL_CORRECTION, Boolean.parseBoolean(partialCorrectionElement.getText()));
							}
						}
						
						question.setExtraContent(questionExtraContent.toJSONString());
						question.setExtraContentJSON(questionExtraContent);
						questionLocalService.updateQuestion(question);
					} catch(DocumentException e) {
						e.printStackTrace();
					}
				}
			}
		}	
		
	}
	
	private final QuestionLocalService questionLocalService;
	private final ReleaseLocalService releaseLocalService;
}
