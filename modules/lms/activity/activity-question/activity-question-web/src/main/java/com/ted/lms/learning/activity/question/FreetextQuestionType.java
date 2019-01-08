package com.ted.lms.learning.activity.question;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Element;
import com.ted.lms.learning.activity.question.model.Answer;
import com.ted.lms.learning.activity.question.model.BaseQuestionType;
import com.ted.lms.learning.activity.question.model.Question;
import com.ted.lms.learning.activity.question.service.AnswerLocalService;

import java.text.Collator;
import java.util.List;

public class FreetextQuestionType extends BaseQuestionType{
	
	private static final Log log = LogFactoryUtil.getLog(FreetextQuestionType.class);

	public FreetextQuestionType(Question question, AnswerLocalService answerLocalService) {
		super(question, answerLocalService);
	}
	
	@Override
	public String getClassName() {
		return FreetextQuestionType.class.getName();
	}

	@Override
	public long getType() {
		return FreetextQuestionTypeFactory.TYPE;
	}
	
	@Override
	public long correct(Element element) throws PortalException {
		String answer= element.element("answer").getText();
		List<Answer> answers = answerLocalService.getAnswersByQuestionId(question.getQuestionId());

		long result = INCORRECT;
		
		if(Validator.isNotNull(answers) && answers.size() > 0){
			Answer solution = answers.get(0);
			if (isCorrect(solution, answer)){
				result = CORRECT;
			}else{
				result = INCORRECT;
			}
		}
		return result;
	}
	
	protected boolean isCorrect(Answer solution, String answer){
		Collator c = Collator.getInstance();
		c.setStrength(Collator.PRIMARY);
		return c.compare(solution.getAnswer(), answer) == 0;
	}
}
