<%@page import="java.util.List"%>
<%@page import="com.ted.lms.learning.activity.question.service.AnswerLocalServiceUtil"%>
<%@page import="com.ted.lms.learning.activity.question.constants.OptionConstants"%>
<%@page import="com.ted.lms.learning.activity.question.util.QuestionPrefsPropsValues"%>
<%@page import="com.ted.lms.learning.activity.question.model.Answer"%>
<%@page import="java.util.Date"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.ted.lms.learning.activity.question.SurveyQuestionType"%>
<%@page import="com.liferay.portal.kernel.xml.SAXReaderUtil"%>
<%@page import="com.ted.lms.learning.activity.question.service.QuestionLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.xml.Document"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.ted.lms.learning.activity.question.model.Question"%>
<%@ include file="/init.jsp" %>

<%boolean canUserDoNewTry = ParamUtil.getBoolean(request,"canUserDoNewTry");
long questionId = ParamUtil.getLong(request, "questionId");
boolean feedback = ParamUtil.getBoolean(request, "feedback", false);
String tryResultData = ParamUtil.getString(request, "tryResultData");
boolean showCorrectAnswer = ParamUtil.getBoolean(request, "showCorrectAnswer");
boolean showCorrectAnswerOnlyOnFinalTry = ParamUtil.getBoolean(request, "showCorrectAnswerOnlyOnFinalTry");

Question question = QuestionLocalServiceUtil.getQuestion(questionId);
Document documentTryResultData = null;
if(Validator.isNotNull(tryResultData)){
	documentTryResultData= SAXReaderUtil.read(tryResultData);
}

SurveyQuestionType surveyQuestionType = new SurveyQuestionType(question);

String html = "", answersFeedBack="", feedMessage = "", cssclass="", selected="";
String timestamp="";
boolean isCombo = false;

boolean enableOrder = QuestionPrefsPropsValues.getOptionsEditFormat(themeDisplay.getCompanyId());

if(enableOrder){
	if(surveyQuestionType.getFormatType() == OptionConstants.OPTION_FORMAT_TYPE_HORIZONTAL){
		cssclass="in-line ";
	}else if(surveyQuestionType.getFormatType() == OptionConstants.OPTION_FORMAT_TYPE_COMBO){
		isCombo = true;
	}
}
List<Answer> answersSelected=surveyQuestionType.getAnswersSelected(documentTryResultData, questionId);
List<Answer> testAnswers= AnswerLocalServiceUtil.getAnswersByQuestionId(question.getQuestionId());
boolean correctAnswered = false;
if(feedback) feedMessage = "";
boolean notAnswers = true;
int numAnswer=0;
String disabled = "";
if (isCombo && !feedback){
	answersFeedBack+="<option class=\"selected\" value=\"\">"+LanguageUtil.get(themeDisplay.getLocale(),"select")+"</option>";
}
for(Answer answer:testAnswers){
	String correct="", checked="";
	disabled = "";
	if(feedback) {
		if (showCorrectAnswerOnlyOnFinalTry) {
			if(canUserDoNewTry){
				showCorrectAnswer = false;
			}else{
				showCorrectAnswer = true;
			}
		}
		disabled = "disabled='disabled'";
		Date now = new Date();
		timestamp = String.valueOf(now.getTime());
	}
	if(surveyQuestionType.isCorrect(answer)){
		if(showCorrectAnswer) correct="correct";
		if(answersSelected.contains(answer)){
			correctAnswered = true;
			checked="checked='checked'";
			notAnswers = false;
			if(Validator.isNotNull(answer.getFeedbackCorrect())){
				feedMessage=(!LanguageUtil.get(themeDisplay.getLocale(),"learning-activity.question.answer-in-blank").equals(feedMessage))?feedMessage+"<br/>"+answer.getFeedbackCorrect():answer.getFeedbackCorrect();
			}
		}
	}else if(answersSelected.contains(answer)){
		checked="checked='checked'";
		selected="selected";
		notAnswers = false;
		if(Validator.isNotNull(answer.getFeedbackIncorrect())){
			feedMessage=(!LanguageUtil.get(themeDisplay.getLocale(),"learning-activity.question.answer-in-blank").equals(feedMessage))?feedMessage+"<br/>"+answer.getFeedbackIncorrect():answer.getFeedbackIncorrect();
		}
	}

	if (isCombo && !feedback){
		answersFeedBack += 	"<option " + selected + " value= \"" + answer.getAnswerId() + "\" >" +
								answer.getAnswer() +			
							"</option>";
	}else{
		answersFeedBack += "<div class=\"answer " + cssclass + correct + "\">" +
								"<label for=\""+themeDisplay.getPortletDisplay().getNamespace()+"question_"+question.getQuestionId()+"_"+numAnswer+"\" />"+
								"<input id=\""+themeDisplay.getPortletDisplay().getNamespace()+"question_"+question.getQuestionId()+"_"+numAnswer+"\" type=\"radio\" name=\""+themeDisplay.getPortletDisplay().getNamespace()+"question_" + question.getQuestionId() +timestamp+ "\" " + checked + " value=\"" 
										+ answer.getAnswerId() +"\" " + disabled + ">" +
								"<div class=\"answer-options\">" + answer.getAnswer() + 
								"</div>" + 
							"</div>";
	}
	
	numAnswer++;
}

if(feedback){
	
	if(notAnswers){
		feedMessage = LanguageUtil.get(themeDisplay.getLocale(),"learning-activity.question.answer-in-blank");
	}
	
	
	if(correctAnswered)	cssclass="correct ";
	else cssclass="incorrect ";
	
	answersFeedBack = "<div class=\"content_answer\">" + answersFeedBack + "</div>";
	if (!"".equals(feedMessage)) {
		answersFeedBack += "<div class=\"questionFeedback\">" + feedMessage + "</div>";
	}
}%>

<c:choose>
	<c:when test="<%=isCombo && !feedback %>">
		<div class='<%="question " + cssclass + "_select questiontype_" + surveyQuestionType.getType()%>'>
			<input type="hidden" name='<%=themeDisplay.getPortletDisplay().getNamespace()+"question"%>' value='<%=question.getQuestionId()%>'/>
			<div class="questiontext select"><%=question.getText()%></div>
			<div class="answer select">
				<select <%=disabled %> class="answer select" id='<%=themeDisplay.getPortletDisplay().getNamespace()+"question_"+question.getQuestionId()+"_"+numAnswer %>' 
					name='<%=themeDisplay.getPortletDisplay().getNamespace()+"question_"+question.getQuestionId()%>' />"+
					<%=answersFeedBack %>
				</select>
			</div>
		</div>	
	</c:when>
	<c:otherwise>
		<div class='<%="question " + cssclass + " questiontype_" + surveyQuestionType.getType()%>'>
			<input type="hidden" name='<%=themeDisplay.getPortletDisplay().getNamespace()+"question"%>' value='<%=question.getQuestionId()%>'/>
			<div class='<%="questiontext " + cssclass%>'>
				<%=question.getText()%>
			</div>
			<%=answersFeedBack%>
		</div>		
	</c:otherwise>	
</c:choose>


<script>
	function <portlet:namespace />validateQuestion<%=questionId%>(){
		if('<%=isCombo%>' == 'true'){
			return $('select[name="<portlet:namespace />question_<%=questionId%>"] > option[value=""]:selected').length == 0;
		}else{
			return $("input[name='<portlet:namespace />question_<%=questionId%>']:checked").length > 0;
		}
	}
</script>