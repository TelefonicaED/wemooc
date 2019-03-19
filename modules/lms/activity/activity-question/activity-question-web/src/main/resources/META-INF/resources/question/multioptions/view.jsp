<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="java.util.Date"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.ted.lms.learning.activity.question.service.AnswerLocalServiceUtil"%>
<%@page import="com.ted.lms.learning.activity.question.constants.OptionConstants"%>
<%@page import="com.ted.lms.learning.activity.question.MultioptionsQuestionType"%>
<%@page import="java.util.List"%>
<%@page import="com.ted.lms.learning.activity.question.util.QuestionPrefsPropsValues"%>
<%@page import="com.ted.lms.learning.activity.question.model.Answer"%>
<%@page import="com.ted.lms.learning.activity.question.service.QuestionLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.xml.SAXReaderUtil"%>
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
if(feedback){
	documentTryResultData= SAXReaderUtil.read(tryResultData);
}

String html = "", answersFeedBack="", feedMessage = "", cssclass="", selected="";
String namespace = themeDisplay.getPortletDisplay().getNamespace();

MultioptionsQuestionType multioptionsQuestionType = new MultioptionsQuestionType(question);

String timestamp="";
boolean isCombo = false;
String onclick = "";

boolean enableOrder = QuestionPrefsPropsValues.getOptionsEditFormat(themeDisplay.getCompanyId());

if(enableOrder){
	if(multioptionsQuestionType.getFormatType() == OptionConstants.OPTION_FORMAT_TYPE_HORIZONTAL){
		cssclass="in-line ";
	}else if(multioptionsQuestionType.getFormatType() == OptionConstants.OPTION_FORMAT_TYPE_COMBO){
		isCombo = true;
	}
}

List<Answer> answersSelected=multioptionsQuestionType.getAnswersSelected(documentTryResultData, questionId);
List<Answer> testAnswers= AnswerLocalServiceUtil.getAnswersByQuestionId(question.getQuestionId());
int correctAnswers=0, correctAnswered=0, incorrectAnswered=0;
if(feedback) feedMessage = "";
boolean notAnswers = true;
int numAnswer=0;
String disabled = "";
if (isCombo && !feedback){
	answersFeedBack+="<option class=\"selected\" value=\"\">"+LanguageUtil.get(themeDisplay.getLocale(),"select")+"</option>";
}
for(Answer answer:testAnswers){
	
	int maxNumberOfCheck = QuestionPrefsPropsValues.getMultioptionsEditFormat(themeDisplay.getCompanyId());
	
	onclick = "onclick=\""+namespace+"checkMaxNumberOfChecks('"+question.getQuestionId()+"','"+numAnswer+"')\"";

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
	if(multioptionsQuestionType.isCorrect(answer)){
		correctAnswers++;
		if("true".equals(showCorrectAnswer)) correct="font_14 color_cuarto negrita";
		if(answersSelected.contains(answer)){
			correctAnswered++;
			checked="checked='checked'";
			notAnswers = false;
			if(Validator.isNotNull(answer.getFeedbackCorrect())){
				feedMessage=(!LanguageUtil.get(themeDisplay.getLocale(),"answer-in-blank").equals(feedMessage))?feedMessage+"<br/>"+answer.getFeedbackCorrect():answer.getFeedbackCorrect();
			}
		}
	}else if(answersSelected.contains(answer)){
		incorrectAnswered++;
		checked="checked='checked'";
		selected="selected";
		notAnswers = false;
		if(Validator.isNotNull(answer.getFeedbackIncorrect())){
			feedMessage=(!LanguageUtil.get(themeDisplay.getLocale(),"answer-in-blank").equals(feedMessage))?feedMessage+"<br/>"+answer.getFeedbackIncorrect():answer.getFeedbackIncorrect();
		}
	}

	if (isCombo && !feedback){
		answersFeedBack += 	"<option " + selected + " value= \"" + answer.getAnswerId() + "\" >" +
								answer.getAnswer() +			
							"</option>";
	}else{
		answersFeedBack += "<div class=\"answer " + cssclass + correct + "\">" +
								"<label for=\""+namespace+"question_"+question.getQuestionId()+"_"+numAnswer+"\" />"+
								"<input "+onclick+" id=\""+namespace+"question_"+question.getQuestionId()+"_"+numAnswer+"\" type=\"checkbox\" name=\""+namespace+"question_" + question.getQuestionId() +timestamp+ "\" " + checked + " value=\"" 
										+ answer.getAnswerId() +"\" " + disabled + ">" +
								"<div class=\"answer-options\">" + answer.getAnswer() + 
								"</div>" + 
							"</div>";
	}
	
	numAnswer++;
}

if(feedback){
	
	if(notAnswers){
		feedMessage = LanguageUtil.get(themeDisplay.getLocale(),"answer-in-blank");
	}
	
	
	if(multioptionsQuestionType.isQuestionCorrect(correctAnswers, correctAnswered, incorrectAnswered))	cssclass="correct ";
	else cssclass="incorrect ";
	
	answersFeedBack = "<div class=\"content_answer\">" + answersFeedBack + "</div>";
	if (!"".equals(feedMessage)) {
		answersFeedBack += "<div class=\"questionFeedback\">" + feedMessage + "</div>";
	}
}

if (isCombo && !feedback){
	html += "<div class=\"question " + cssclass + "_select questiontype_" + multioptionsQuestionType.getType() + "\">" +
				"<input type=\"hidden\" name=\""+namespace+"question\" value=\"" + question.getQuestionId() + "\"/>"+
				"<div class=\"questiontext select\">" + question.getText() + "</div>" +
				"<div class=\"answer select\">" +
					"<select "+ disabled + "class=\"answer select\" id=\""+namespace+"question_"+question.getQuestionId()+"_"+numAnswer+"\" name=\""+namespace+"question_"+question.getQuestionId()+"\" />"+
						answersFeedBack +
					"</select>" +
				"</div>" +
			"</div>";
}else{
	html += "<div class=\"question " + cssclass + " questiontype_" + multioptionsQuestionType.getType() + "\">" +
			"<input type=\"hidden\" name=\""+namespace+"question\" value=\"" + question.getQuestionId() + "\"/>"+
			"<div class=\"questiontext " + cssclass + "\">" + question.getText() + "</div>" +
				answersFeedBack +
			"</div>";	
}
	
%>
<%=html%>


<script type="text/javascript"> 
	var numberOfChecks = 0; "
	function <portlet:namespace/>checkMaxNumberOfChecks(idQ,idA){
		var A = AUI();
		if(A.one('#" + namespace + "question_'+idQ+'_'+idA+':checked')){
			numberOfChecks++;
			if(numberOfChecks==" + maxNumberOfCheck + "){ 
				A.all('div.answer input[type=\"checkbox\"]').setAttribute('disabled','disabled');
				var inputs = A.all('div.answer input[type=\"checkbox\"]:checked');
				inputs.each(function(input){
					input.removeAttribute('disabled');
				});
			}
	 	}else{
			if(numberOfChecks==" + maxNumberOfCheck + "){
				A.all('div.answer input[type=\"checkbox\"]').removeAttribute('disabled');
			}
			numberOfChecks--;
		}
	}
</script>