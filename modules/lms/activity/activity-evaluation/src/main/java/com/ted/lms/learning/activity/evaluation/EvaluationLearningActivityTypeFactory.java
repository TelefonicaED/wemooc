package com.ted.lms.learning.activity.evaluation;

import com.ted.lms.model.BaseLearningActivityTypeFactory;
import com.ted.lms.model.LearningActivityTypeFactory;

import org.osgi.service.component.annotations.Component;


@Component(
    immediate = true,
    property = {},
    service = LearningActivityTypeFactory.class
)
public class EvaluationLearningActivityTypeFactory extends BaseLearningActivityTypeFactory{
	
	public static final long TYPE = 8;

	@Override
	public String getClassName() {
		return EvaluationLearningActivityTypeFactory.class.getName();
	}

	@Override
	public String getPortletId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getType() {
		// TODO Auto-generated method stub
		return TYPE;
	}

	@Override
	public boolean isCategorizable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setClassName(String className) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPortletId(String portletId) {
		// TODO Auto-generated method stub
		
	}

}
