package com.ted.lms.learning.activity.question.web.internal.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;
import aQute.bnd.annotation.metatype.Meta.OCD;

@ExtendedObjectClassDefinition(
	category = "lms",
	scope = ExtendedObjectClassDefinition.Scope.COMPANY
)
@OCD(
	id = "com.ted.lms.learning.activity.question.web.internal.configuration.QuestionWebConfiguration",
	localization = "content/Language",
	name = "question-web-configuration-name"
)
public @interface QuestionWebConfiguration {
	
	@Meta.AD(
		deflt = "${server-property://com.liferay.portal/editor.wysiwyg.question-web.docroot.html.portlet.questions.edit_question.jsp}",
		name = "html-question-editor", required = false
	)
	public String getHTMLQuestionEditor();
	
	
}
