package com.ted.lms.service.util;

import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil {
	public static Format getSimpleDateFormatPattern(Locale locale, TimeZone timeZone) {
		DateFormat shortDateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, locale);
		SimpleDateFormat shortDateFormatSimpleDateFormat = (SimpleDateFormat)shortDateFormat;
		
		String simpleDateFormatPattern = shortDateFormatSimpleDateFormat.toPattern();

		simpleDateFormatPattern = simpleDateFormatPattern.replaceAll("yyyy", "yy");
		simpleDateFormatPattern = simpleDateFormatPattern.replaceAll("MM", "M");
		simpleDateFormatPattern = simpleDateFormatPattern.replaceAll("dd", "d");
		simpleDateFormatPattern = simpleDateFormatPattern.replaceAll("HH", "H");
		simpleDateFormatPattern = simpleDateFormatPattern.replaceAll("hh", "h");

		simpleDateFormatPattern = simpleDateFormatPattern.replaceAll("yy", "yyyy");
		simpleDateFormatPattern = simpleDateFormatPattern.replaceAll("M", "MM");
		simpleDateFormatPattern = simpleDateFormatPattern.replaceAll("d", "dd");
		simpleDateFormatPattern = simpleDateFormatPattern.replaceAll("H", "HH");
		simpleDateFormatPattern = simpleDateFormatPattern.replaceAll("h", "hh");
		
		return FastDateFormatFactoryUtil.getSimpleDateFormat(simpleDateFormatPattern, locale, timeZone);
		
	}
}
