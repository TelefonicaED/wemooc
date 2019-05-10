<%@page import="com.ted.lms.learning.activity.question.OptionsQuestionType"%>
<%@page import="com.ted.lms.learning.activity.question.constants.OptionConstants"%>
<%@page import="com.ted.lms.learning.activity.question.service.QuestionLocalServiceUtil"%>
<%@page import="com.ted.lms.learning.activity.question.model.Question"%>
<%@page import="com.ted.lms.learning.activity.question.OptionsQuestionTypeFactory"%>
<%@page import="javax.portlet.RenderResponse"%>
<%@page import="com.ted.lms.learning.activity.question.constants.QuestionsWebPortletKeys"%>
<%@page import="com.ted.lms.learning.activity.question.service.AnswerLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.ted.lms.learning.activity.question.model.Answer"%>
<%@page import="java.util.List"%>
<%@ include file="../../init.jsp" %>


<% 
int iteratorQuestion = ParamUtil.getInteger(request, "iteratorQuestion", 0);
long questionId = ParamUtil.getLong(request, "questionId");

int formatType = OptionConstants.DEFAULT_FORMAT_TYPE;

System.out.println("questionId: " + questionId);

if(questionId > 0){
	Question question = QuestionLocalServiceUtil.getQuestion(questionId);
	System.out.println("questionId: " + question.getQuestionTypeId());
	OptionsQuestionType optionsQuestionType = new OptionsQuestionType(question);
	System.out.println("question: " + question.getExtraContent());
	formatType = optionsQuestionType.getFormatType();
	System.out.println("optionsQuestionType: " + optionsQuestionType.getFormatType());
}

String namespace = ParamUtil.getString(request, "namespace", themeDisplay.getPortletDisplay().getNamespace());
%>

<aui:select name="<%=namespace + iteratorQuestion + "_formatType"%>" useNamespace="false" label="question.options.format-type">
	<aui:option label="question.options.format-type.vertical" value="<%=OptionConstants.OPTION_FORMAT_TYPE_VERTICAL %>" selected="<%=formatType == OptionConstants.OPTION_FORMAT_TYPE_VERTICAL %>" />
	<aui:option label="question.options.format-type.horizontal" value="<%=OptionConstants.OPTION_FORMAT_TYPE_HORIZONTAL %>" selected="<%=formatType == OptionConstants.OPTION_FORMAT_TYPE_HORIZONTAL %>" />
	<aui:option label="question.options.format-type.combo" value="<%=OptionConstants.OPTION_FORMAT_TYPE_COMBO %>" selected="<%=formatType == OptionConstants.OPTION_FORMAT_TYPE_COMBO %>" />
</aui:select>