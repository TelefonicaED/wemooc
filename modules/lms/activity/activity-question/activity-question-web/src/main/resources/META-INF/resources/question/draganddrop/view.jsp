<%@page import="com.ted.lms.learning.activity.question.DragAndDropQuestionTypeFactory"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ted.lms.learning.activity.question.DragAndDropQuestionType"%>
<%@page import="java.util.Date"%>
<%@page import="com.ted.lms.service.LearningActivityTryLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.ted.lms.learning.activity.question.service.AnswerLocalServiceUtil"%>
<%@page import="com.ted.lms.learning.activity.question.model.Answer"%>
<%@page import="java.util.List"%>
<%@page import="com.ted.lms.learning.activity.question.constants.OptionConstants"%>
<%@page import="com.ted.lms.learning.activity.question.OptionsQuestionType"%>
<%@page import="com.liferay.portal.kernel.json.JSONObject"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.ted.lms.learning.activity.question.util.QuestionPrefsPropsValues"%>
<%@page import="com.ted.lms.learning.activity.question.service.QuestionLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.xml.SAXReaderUtil"%>
<%@page import="com.liferay.portal.kernel.xml.Document"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.ted.lms.learning.activity.question.model.Question"%>
<%@page import="com.ted.lms.learning.activity.question.registry.QuestionTypeFactoryRegistryUtil" %>
<%@ include file="/init.jsp" %>

<%boolean canUserDoNewTry = ParamUtil.getBoolean(request,"canUserDoNewTry");
long questionId = ParamUtil.getLong(request, "questionId");
boolean feedback = ParamUtil.getBoolean(request, "feedback", false);
String tryResultData = ParamUtil.getString(request, "tryResultData");
boolean showCorrectAnswer = ParamUtil.getBoolean(request, "showCorrectAnswer", false);
boolean showCorrectAnswerOnlyOnFinalTry = ParamUtil.getBoolean(request, "showCorrectAnswerOnlyOnFinalTry", false);

Question question = QuestionLocalServiceUtil.getQuestion(questionId);
Document documentTryResultData = null;
if(Validator.isNotNull(tryResultData)){
	documentTryResultData= SAXReaderUtil.read(tryResultData);
}

DragAndDropQuestionTypeFactory dragAndDropQuestionTypeFactory = (DragAndDropQuestionTypeFactory) QuestionTypeFactoryRegistryUtil.getQuestionTypeFactoryByType(DragAndDropQuestionTypeFactory.TYPE);
DragAndDropQuestionType dragAndDropQuestionType = dragAndDropQuestionTypeFactory.getDragAndDropQuestionType(question);

String html = "", leftCol="", rightCol = "", feedMessage="", feedsol="";

List<Answer> answersSelected=dragAndDropQuestionType.getAnswersSelected(documentTryResultData);
List<Answer> tA= AnswerLocalServiceUtil.getAnswersByQuestionId(question.getQuestionId());
List<Long>answersSelectedIds = new ArrayList<Long>();
List<Answer> sols = new ArrayList<Answer>();

//array con todas las respuestas posibles desordenadas
ArrayList<Answer> testAnswers = new ArrayList<Answer>();
testAnswers.addAll(tA);
Collections.shuffle(testAnswers);

//la lista tA la reutilizo como lista con la solucion
for(Answer an:tA){
	if(an.isCorrect()) sols.add(an);
}

//Si el alumno ha pasado por la pregunta alguna vez, eliminamos de testAnswers las que el alumno puso en answersSelected
if(answersSelected != null && answersSelected.size() == sols.size()){ 
	for(int k=0; k<answersSelected.size(); k++){
		if(answersSelected.get(k) != null){
			testAnswers.remove(answersSelected.get(k));
			answersSelectedIds.add(answersSelected.get(k).getAnswerId());
		}else
			answersSelectedIds.add(new Long(-1));
	}
	//sino, creamos el array de respuestas con el tamano que tiene q tener para pintar las cajas grises vacias.
}else{
	for(int k=0; k<sols.size(); k++) answersSelectedIds.add(new Long(-1));
}
String correctionClass = "";
if(feedback){
	feedMessage = LanguageUtil.get(themeDisplay.getLocale(),"learning-activity.question.answer-in-blank");
	if (showCorrectAnswerOnlyOnFinalTry) {
		if(canUserDoNewTry){
			showCorrectAnswer = false;
		}else{
			showCorrectAnswer = true;
		}
	}
	if(dragAndDropQuestionType.isCorrect(answersSelectedIds, sols)) correctionClass = " correct";
	else correctionClass = " incorrect";
}

html += "<div id=\"id"+questionId+"\" class=\"question draganddrop"+correctionClass + " questiontype_" + dragAndDropQuestionType.getType() +"\">"+
			"<input type=\"hidden\" name=\""+themeDisplay.getPortletDisplay().getNamespace()+"question\" value=\"" + question.getQuestionId() + "\"/>"+
			"<div class=\"questiontext\">" + question.getText() + "</div>";

//en la columna de la izq el contenido de testAnswers, con las que el estudiante dejo sin arrastrar
leftCol +=	"<div class=\"row\"><div class=\"items col-md-6\" style=\"border:1px solid #fff; min-height:"+20*testAnswers.size()+"px; min-width=300px;\">";
for(Answer answer:testAnswers){
	leftCol += "<div id=\""+answer.getAnswerId()+"\" class=\"ui-corner-all\">"+answer.getAnswer()+"</div>";
}
leftCol +=	"</div>";

//en la columna de la derecha el contenido de answersSelected, con las respuestas que dio el estudiante
rightCol +=	"<div class=\"drop col-md-6\">";
for(int i=0;i<answersSelectedIds.size();i++){
	int aux = i+1;
	long value = -1;
	String text = LanguageUtil.format(themeDisplay.getLocale(), "drop", aux);
	if(answersSelectedIds.get(i)!= -1 && answersSelected.get(i) != null){
		value = answersSelected.get(i).getAnswerId();
		text = answersSelected.get(i).getAnswer();
	}
	
	if(feedback){
		if(answersSelectedIds.get(i) == sols.get(i).getAnswerId()) {
			feedMessage = "<div class=\"questionFeedback\">"+sols.get(i).getFeedbackCorrect()+"</div>";
		}
		else {
			String feedAux = new String();
			Iterator<Answer> it = tA.iterator();
			Answer answerAux = null;
			while (it.hasNext()){ 
				answerAux = it.next();
				if(answersSelectedIds.get(i) == answerAux.getAnswerId()){
					feedAux = answerAux.getFeedbackIncorrect();
					break;
				}
			}
			feedMessage = "<div class=\"questionFeedback\">"+feedAux+"</div>";
		}
		if("true".equals(showCorrectAnswer)) {
			feedsol = "<div class=\" font_14 color_cuarto negrita\">" + sols.get(i).getAnswer() + "</div>";
		}
		if (!"<div class=\"questionFeedback\"></div>".equals(feedMessage)) {
			feedsol += feedMessage;
		}
	}
	
	rightCol +=	"<input type=\"hidden\" name=\""+themeDisplay.getPortletDisplay().getNamespace()+"question_" + question.getQuestionId() + "_" + i +"hidden\"  value=\""+value+"\"/>" +
			"<div name=\""+themeDisplay.getPortletDisplay().getNamespace()+"question_" + question.getQuestionId() + "_" + i +"\" id=\"Drop"+aux +"\" class=\"drop-containers ui-corner-all background "+(value == -1 ? "base" : "occupied")+"\">"+
			(value == -1 ? "" : "<div id=\""+value+"\" class=\"ui-corner-all ui-draggable\">") +
			text +
			(value == -1 ? "" : "</div>") +
			"</div>"
			+ feedsol;
}
rightCol += "</div>";

if(feedback) {
	html += "<div class=\"content_answer\">" + leftCol + rightCol + "</div></div>";
}
else html += leftCol + rightCol;
html+=	"</div>";

%>

<%=html%>

<script>
	function <portlet:namespace />validateQuestion<%=questionId%>(){
		return $('input[name^="<portlet:namespace />question_<%=questionId%>_"][type="hidden"][value="-1"]').length == 0;
	}
</script>