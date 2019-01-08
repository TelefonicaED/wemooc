package com.ted.lms.learning.activity.question;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Element;
import com.ted.lms.learning.activity.question.model.Answer;
import com.ted.lms.learning.activity.question.model.BaseQuestionType;
import com.ted.lms.learning.activity.question.model.Question;
import com.ted.lms.learning.activity.question.service.AnswerLocalService;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.portlet.ActionRequest;

public class SortableQuestionType extends BaseQuestionType{
	
	private static final Log log = LogFactoryUtil.getLog(SortableQuestionType.class);

	public SortableQuestionType(Question question, AnswerLocalService answerLocalService) {
		super(question, answerLocalService);
	}
	
	@Override
	public String getClassName() {
		return SortableQuestionType.class.getName();
	}

	@Override
	public long getType() {
		return SortableQuestionTypeFactory.TYPE;
	}
	
	@Override
	public long correct(Element element) throws PortalException {
		log.debug("element: " + element.asXML());
		
		Iterator<Element> iteratorAnswers = element.elementIterator("answer");
		List<Long> answersId = new ArrayList<Long>();
		
		Element elementAnswer = null;
		
		while(iteratorAnswers.hasNext()) {
			elementAnswer = iteratorAnswers.next();
			log.debug("elementAnswer: " + elementAnswer.asXML());
			answersId.add(Long.parseLong(elementAnswer.attributeValue("id")));
		}
		
		List<Answer> answers = answerLocalService.getAnswersByQuestionId(question.getQuestionId());
		
		if(!isCorrect(answersId, answers)){
			return INCORRECT;
		}else{
			return CORRECT;
		}
	}
	
	
	protected boolean isCorrect(List<Long> answersId, List<Answer> answers){
		boolean correct = true;
		for(int i=0;i<answers.size();i++){ 
			if(answersId.get(i) == -1 || answersId.get(i) != answers.get(i).getAnswerId())	
				correct = false;
		}
		return correct;
	}
}
