package com.ted.lms.calification.percent;

import com.ted.lms.model.BaseCalificationType;

import java.text.DecimalFormat;
import java.util.Locale;

/**
 * Implementa un producto del tipo de calificación por porcentaje
 * @author Virginia Martín Agudo
 *
 */
public class PercentCalificationType extends BaseCalificationType{

	@Override
	public String getClassName() {
		return PercentCalificationType.class.getName();
	}
	
	@Override
	public String translate(Locale locale, double result) {
		DecimalFormat df = new DecimalFormat("##");
		return df.format(result);
	}
	
	@Override
	public double getMaxValue() {
		return 100;
	}
	
	@Override
	public String getSuffix() {
		return "/100";
	}

}
