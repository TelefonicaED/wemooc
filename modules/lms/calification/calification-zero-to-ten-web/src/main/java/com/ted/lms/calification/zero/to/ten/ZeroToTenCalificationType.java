package com.ted.lms.calification.zero.to.ten;

import com.ted.lms.model.BaseCalificationType;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Implementa el producto del método de calificación de 0 a 10
 * @author Virginia Martín Agudo
 *
 */
public class ZeroToTenCalificationType extends BaseCalificationType{

	@Override
	public String getClassName() {
		return ZeroToTenCalificationType.class.getName();
	}
	
	@Override
	public String translate(Locale locale, double result) {
		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(locale);
		otherSymbols.setDecimalSeparator('.');
		DecimalFormat df = new DecimalFormat("##.#",otherSymbols);				
		return df.format(result/10);
	}
	
	@Override
	public double toBase100(String result) {
		return Double.parseDouble(result)*10;
	}
	
	@Override
	public double getMaxValue() {
		return 10;
	}
	
	@Override
	public String getSuffix() {
		return "/10";
	}

}
