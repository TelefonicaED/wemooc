package com.ted.lms.calification.zero.to.n;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ted.lms.calification.zero.to.n.constants.ZeroToNConstants;
import com.ted.lms.constants.CourseConstants;
import com.ted.lms.model.BaseCalificationType;
import com.ted.lms.model.Course;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import javax.portlet.ActionRequest;

/**
 * Implementa un producto del tipo de calificación de 0 a n
 * @author Virginia Martín Agudo
 *
 */
public class ZeroToNCalificationType extends BaseCalificationType{
	
	private double maxValue;
	private Course course;
	
	public ZeroToNCalificationType(Course course) {
		JSONObject extraData = course.getCourseExtraDataJSON();
		
		JSONObject calification = extraData.getJSONObject(CourseConstants.JSON_CALIFICATION);
		
		if(calification != null) {
			this.maxValue = calification.getDouble(ZeroToNConstants.JSON_ZERO_TO_N, 0);
		}else {
			this.maxValue = 0;
		}
		
		this.course = course;
	}

	@Override
	public String getClassName() {
		return ZeroToNCalificationType.class.getName();
	}
	
	@Override
	public String translate(Locale locale, double result) {
		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(locale);
		otherSymbols.setDecimalSeparator('.');
		DecimalFormat df = new DecimalFormat("##.#",otherSymbols);	
		
		String translated = "";
		
		if(this.maxValue > 0) {
			translated = df.format(result*this.maxValue/100);
		}else {
			translated = df.format(result);
		}
		
		return translated;
	}
	
	@Override
	public double toBase100(String result) {
		double base100 = Double.parseDouble(result);
		if(this.maxValue > 0) {
			base100 = base100 * 100 / this.maxValue;
		}
		
		return base100;
	}
	
	@Override
	public double getMaxValue() {
		return this.maxValue;
	}
	
	@Override
	public String getSuffix() {
		String suffix = "";
		if(this.maxValue > 0) {
			suffix = "/" + (int)this.maxValue;
		}
		return suffix;
	}
	
	@Override
	public void setExtraContent(ActionRequest actionRequest) throws PortalException{
		this.maxValue = ParamUtil.getDouble(actionRequest, "calificationMaxValue", 0);
		
		JSONObject extraData = course.getCourseExtraDataJSON();
		JSONObject calification = extraData.getJSONObject(CourseConstants.JSON_CALIFICATION);
		
		if(calification == null) {
			calification = JSONFactoryUtil.createJSONObject();
		}
		
		calification.put(ZeroToNConstants.JSON_ZERO_TO_N, this.maxValue);
		
		extraData.put(CourseConstants.JSON_CALIFICATION, calification);
		
		course.setCourseExtraData(extraData.toJSONString());
	}
	
	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}
	
	public JSONObject createJSONCalification() {
		JSONObject calification = JSONFactoryUtil.createJSONObject();
		calification.put(ZeroToNConstants.JSON_ZERO_TO_N, this.maxValue);
		
		return calification;
	}

}
