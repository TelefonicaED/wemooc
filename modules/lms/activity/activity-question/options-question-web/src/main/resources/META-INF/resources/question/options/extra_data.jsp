<%@page import="com.ted.lms.question.options.constants.OptionsConstants"%>
<%@page import="com.ted.lms.learning.activity.question.util.QuestionPrefsPropsValues"%>
<%@page import="com.ted.lms.question.options.OptionsQuestionType"%>
<%@page import="com.ted.lms.learning.activity.question.service.QuestionLocalServiceUtil"%>
<%@page import="com.ted.lms.learning.activity.question.model.Question"%>
<%@page import="com.ted.lms.question.options.OptionsQuestionTypeFactory"%>
<%@page import="javax.portlet.RenderResponse"%>
<%@page import="com.ted.lms.learning.activity.question.service.AnswerLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.ted.lms.learning.activity.question.model.Answer"%>
<%@page import="java.util.List"%>
<%@ include file="../../init.jsp" %>


<% 
int iteratorQuestion = ParamUtil.getInteger(request, "iteratorQuestion", 0);
long questionId = ParamUtil.getLong(request, "questionId");

boolean enableOrder = QuestionPrefsPropsValues.getOptionsEditFormat(themeDisplay.getCompanyId());

if(enableOrder){

	int formatType = OptionsConstants.DEFAULT_FORMAT_TYPE;
	
	if(questionId > 0){
		Question question = QuestionLocalServiceUtil.getQuestion(questionId);
		OptionsQuestionTypeFactory optionsQuestionTypeFactory = new OptionsQuestionTypeFactory();
		OptionsQuestionType optionsQuestionType = optionsQuestionTypeFactory.getOptionsQuestionType(question);
		formatType = optionsQuestionType.getFormatType();
	}
	
	String namespace = ParamUtil.getString(request, "namespace", themeDisplay.getPortletDisplay().getNamespace());%>
	
	<aui:select name="<%=namespace + iteratorQuestion + "_formatType"%>" useNamespace="false" label="question.options.format-type">
		<aui:option label="question.options.format-type.vertical" value="<%=OptionsConstants.OPTION_FORMAT_TYPE_VERTICAL %>" selected="<%=formatType == OptionsConstants.OPTION_FORMAT_TYPE_VERTICAL %>" />
		<aui:option label="question.options.format-type.horizontal" value="<%=OptionsConstants.OPTION_FORMAT_TYPE_HORIZONTAL %>" selected="<%=formatType == OptionsConstants.OPTION_FORMAT_TYPE_HORIZONTAL %>" />
		<aui:option label="question.options.format-type.combo" value="<%=OptionsConstants.OPTION_FORMAT_TYPE_COMBO %>" selected="<%=formatType == OptionsConstants.OPTION_FORMAT_TYPE_COMBO %>" />
	</aui:select>

<%}%>