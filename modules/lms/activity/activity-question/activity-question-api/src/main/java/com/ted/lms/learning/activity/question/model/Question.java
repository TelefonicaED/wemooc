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

package com.ted.lms.learning.activity.question.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the Question service. Represents a row in the &quot;qu_Question&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see QuestionModel
 * @see com.ted.lms.learning.activity.question.model.impl.QuestionImpl
 * @see com.ted.lms.learning.activity.question.model.impl.QuestionModelImpl
 * @generated
 */
@ImplementationClassName("com.ted.lms.learning.activity.question.model.impl.QuestionImpl")
@ProviderType
public interface Question extends QuestionModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.ted.lms.learning.activity.question.model.impl.QuestionImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Question, Long> QUESTION_ID_ACCESSOR = new Accessor<Question, Long>() {
			@Override
			public Long get(Question question) {
				return question.getQuestionId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Question> getTypeClass() {
				return Question.class;
			}
		};

	public String getTextMapAsXML();

	public com.liferay.portal.kernel.json.JSONObject getExtraContentJSON();

	public void setExtraContentJSON(
		com.liferay.portal.kernel.json.JSONObject extraContent);
}