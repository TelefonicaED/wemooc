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

package com.ted.lms.model.impl;

import com.liferay.portal.kernel.language.LanguageUtil;

import java.util.Locale;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model implementation for the LearningActivityResult service. Represents a row in the &quot;LMS_LearningActivityResult&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.ted.lms.model.LearningActivityResult<code> interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class LearningActivityResultImpl extends LearningActivityResultBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a learning activity result model instance should use the {@link com.ted.lms.model.LearningActivityResult} interface instead.
	 */
	public LearningActivityResultImpl() {
	}
	
	public String getStatusProperties() {
		String status = "status.not-attempted";
		if(getStartDate() != null && getEndDate() == null) {
			status = "status.incomplete";
		}else if(getEndDate() != null && isPassed()) {
			status = "status.passed";
		}else if(getEndDate() != null && !isPassed()) {
			status = "status.failed";
		}
		
		return status;
	}
	
	public boolean isFinished() {
		return getEndDate() != null;
	}
	
	public String getActivityStatus(Locale locale) {
		String status = "";
		if(getStartDate() == null) {
			status = LanguageUtil.get(locale, "results.status.not-started");
		}else if(!isFinished()) {
			status = LanguageUtil.get(locale, "results.status.started");
		}else if(getPassed()) {
			status = LanguageUtil.get(locale, "results.status.passed");
		}else {
			status = LanguageUtil.get(locale, "results.status.failed");
		}
		
		return status;
	}

}