<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.ted.lms.learning.activity.question.service.AnswerLocalServiceUtil"%>
<%@page import="com.ted.lms.learning.activity.question.model.Answer"%>
<%@page import="java.util.List"%>
<%@page import="com.ted.lms.question.freetext.FreetextQuestionType"%>
<%@page import="com.ted.lms.question.freetext.FreetextQuestionTypeFactory"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portal.kernel.xml.SAXReaderUtil"%>
<%@page import="com.ted.lms.learning.activity.question.service.QuestionLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.xml.Document"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.ted.lms.learning.activity.question.model.Question"%>
<%@page import="com.ted.lms.learning.activity.question.registry.QuestionTypeFactoryRegistryUtil" %>
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

FreetextQuestionTypeFactory freetextQuestionTypeFactory = (FreetextQuestionTypeFactory)QuestionTypeFactoryRegistryUtil.getQuestionTypeFactoryByType(FreetextQuestionTypeFactory.TYPE);
FreetextQuestionType freetextQuestionType = freetextQuestionTypeFactory.getFreetextQuestionType(question);

String answersFeedBack= "", cssclass = "";
String feedMessage = LanguageUtil.get(themeDisplay.getLocale(),"learning-activity.question.answer-in-blank") ;
String answer=freetextQuestionType.getAnswersSelected(documentTryResultData);

List<Answer> testAnswers= AnswerLocalServiceUtil.getAnswersByQuestionId(question.getQuestionId());
if(testAnswers!=null && testAnswers.size()>0){//el profesor puso alguna soluci�n para correcci�n autom�tica
	Answer solution = testAnswers.get(0);
	if(feedback){
		if (showCorrectAnswerOnlyOnFinalTry) {
			if(canUserDoNewTry){
				showCorrectAnswer = false;
			}else{
				showCorrectAnswer = true;
			}
		}
		if(freetextQuestionType.isCorrect(solution, answer)){
			feedMessage=solution.getFeedbackCorrect();
			cssclass=" correct";
		}else {
			feedMessage=solution.getFeedbackIncorrect();
			cssclass=" incorrect";
		}
	}

	answersFeedBack = answer;
	if(showCorrectAnswer) answersFeedBack += "<br/>" +"<div class=\"answer correct\">" +
																			solution.getAnswer() +
																	"</div>";
}else{//el profesor lo corregira manualmente
	answersFeedBack = answer;
	if(feedback) feedMessage = LanguageUtil.get(themeDisplay.getLocale(), "learning-activity.question.free-text.manually-correction");
}

if(feedback) { 
	answersFeedBack = "<div class=\"content_answer\">" + answersFeedBack + "</div>";
	if (!"".equals(feedMessage)) {
		answersFeedBack += "<div class=\"questionFeedback\">" + feedMessage + "</div>";
	}
}
%>
<div class='<%="question" + cssclass + " questiontype_" + freetextQuestionType.getType()%>'>
	<input type="hidden" name='<%=themeDisplay.getPortletDisplay().getNamespace()+"question"%>' value='<%=question.getQuestionId()%>'/>
	
	<div class="questiontext"><%=question.getText()%></div>
	
	<c:if test="<%=!feedback %>">
		<div class="answer">
			<label for='<%=themeDisplay.getPortletDisplay().getNamespace()+"question_" + question.getQuestionId()%>' />
			<textarea rows="4" cols="60" maxlength="1000" id='<%=themeDisplay.getPortletDisplay().getNamespace()+"question_" + question.getQuestionId()%>' name='<%=themeDisplay.getPortletDisplay().getNamespace()+"question_" + question.getQuestionId()%>'>
				<%=answer%>
			</textarea>
		</div>
	</c:if>
	<%=answersFeedBack%>
</div>	


<script>
	function <portlet:namespace />validateQuestion<%=questionId%>(){
		return $("textarea[name='<portlet:namespace />question_<%=questionId%>']").val().trim() != "";
	}
</script>