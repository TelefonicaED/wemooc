<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.ted.lms.learning.activity.question.service.QuestionLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.xml.SAXReaderUtil"%>
<%@page import="com.liferay.petra.string.StringUtil"%>
<%@page import="com.ted.lms.learning.activity.question.registry.QuestionTypeFactoryRegistryUtil"%>
<%@page import="com.ted.lms.learning.activity.question.model.QuestionType"%>
<%@page import="com.ted.lms.learning.activity.QuestionsLearningActivityType"%>
<%@page import="com.ted.lms.learning.activity.question.model.QuestionTypeFactory"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.ted.lms.learning.activity.question.service.AnswerLocalServiceUtil"%>
<%@page import="com.ted.lms.learning.activity.question.model.Answer"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.xml.Document"%>
<%@page import="com.ted.lms.learning.activity.question.FillblankQuestionType"%>
<%@page import="com.ted.lms.learning.activity.question.FillblankQuestionTypeFactory"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.ted.lms.learning.activity.question.model.Question"%>
<%@ include file="/init.jsp" %>

<%
boolean canUserDoNewTry = ParamUtil.getBoolean(request,"canUserDoNewTry");
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

FillblankQuestionType fillblankQuestionType = new FillblankQuestionType(question);

//Cogemos las respuestas a los blancos (separadas por coma) de la pregunta a partir del xml de learningactivityresult
String[] userAnswers = fillblankQuestionType.getAnswersSelected(documentTryResultData);

String answersFeedBack="", feedMessage = "";
String cssclass = "";

//Cogemos el TestAnswer de la pregunta para rellenar las respuestas dadas por el alumno
List<Answer> answers= AnswerLocalServiceUtil.getAnswersByQuestionId(question.getQuestionId());
%>

<c:if test="<%=answers!=null && answers.size()>0 %>">
	<%
	//Comprobamos si todos los blancos son acertados para ver si la pregunta resulta correcta o no
	Answer solution = answers.get(0);
	List<String> solutions = fillblankQuestionType.getQuestionSols(solution.getAnswer());

	if (feedback){
		feedMessage = LanguageUtil.get(themeDisplay.getLocale(),"learning-activity.question.answer-in-blank") ;
		if (showCorrectAnswerOnlyOnFinalTry && canUserDoNewTry) {
			showCorrectAnswer = false;
		}else if(showCorrectAnswerOnlyOnFinalTry){
			showCorrectAnswer = true;
		}
		int correctAnswers=0,i=0;
		
		for(String sol:solutions){
			String ans= (userAnswers.length>i)?userAnswers[i]:"";
			//String ans= answer;
			if(fillblankQuestionType.isCorrect(sol, ans)){
				correctAnswers++;
			}
			i++;
		}
		if(correctAnswers==solutions.size()){
			feedMessage=solution.getFeedbackCorrect();
			cssclass=" correct";
		}else {
			feedMessage=solution.getFeedbackIncorrect();
			cssclass=" incorrect";
		}
	}%>
	<div class='<%="question"+ cssclass + " questiontype_" + fillblankQuestionType.getType()%> '>
	
		<input type="hidden" name='<%=themeDisplay.getPortletDisplay().getNamespace()+"question"%>' value='<%=question.getQuestionId()%>'/>
		<div class='questiontext'><%=question.getText()%></div>
		
		<div class='answer-fillblank'>
		
			<%//Obtain feedback
			String solok="";
			answersFeedBack = solution.getAnswer().replace("\n", "<br/>").replace("\r", "<br/>");
			
			int i=0;
			for(String sol:solutions){
				//String ans = (userAnswers.length>i)?userAnswers[i]:"";
				String ans= StringUtil.merge(answers, ",");
				String auxans = "";
				List<String> blankSols = fillblankQuestionType.getBlankSols(sol, true);
				
				if(sol.contains(":SHORTANSWER") || sol.contains(":SA") || sol.contains(":MW")
						|| sol.contains(":NUMERICAL:") || sol.contains(":NM:") || sol.contains("{{")) {
					
					ans= (userAnswers.length>i)?userAnswers[i]:"";
					String readonly = "";
					if (feedback) {
						readonly = "readonly";
					}
					auxans= "<label for=\""+themeDisplay.getPortletDisplay().getNamespace()+"question_"+question.getQuestionId()+"_"+i+"\" > <input id=\""+themeDisplay.getPortletDisplay().getNamespace()+"question_" + question.getQuestionId()+"_"+i+"\" name=\""+themeDisplay.getPortletDisplay().getNamespace()+"question_" + question.getQuestionId()+"_"+i + "\" "+readonly+" type=\"text\" value=\""+ans+"\" /></label>";//input
					
					if(showCorrectAnswer) {
						for(String blankSol:blankSols){
							if(solok != "") solok += " | ";
							solok += blankSol;
						}
						auxans += "<div class=\" correct\"> (" + solok + ") </div>";
					}
				}else if(sol.contains(":MULTICHOICE_") || sol.contains(":MCV") || sol.contains(":MCH")){
					String aux = "";
					auxans = "<br/><div class=\"multichoice\">";
					List<String> totalBlankSols = fillblankQuestionType.getBlankSols(sol, false);
					for(String blankSol:totalBlankSols){
						String checked = "", disabled = "", correct = "";
						if(blankSol.equals(ans)) checked="checked='checked'";
						if(feedback) disabled = "disabled='disabled'";
						if(showCorrectAnswer && blankSols.contains(blankSol)) correct = "correct";
						aux = "<div class=\"answer " + correct + "\"> <label for=\""+themeDisplay.getPortletDisplay().getNamespace()+"question_"+question.getQuestionId()+"_"+i+"\" > <input id=\""+themeDisplay.getPortletDisplay().getNamespace()+"question_" + question.getQuestionId()+"_"+i + "\" name=\""+themeDisplay.getPortletDisplay().getNamespace()+"question_" + question.getQuestionId()+"_"+i + "\" type=\"radio\"" + checked + "value=\"" + blankSol + "\" "+disabled+" /></label>" + blankSol + "</div>";//radiobuttons
						auxans += aux;
					}
					auxans += "</div>";
				}else if(sol.contains(":MULTICHOICE:") || sol.contains(":MC:")){
					String disabled = "";
					if(feedback) disabled = "disabled='disabled'";
					auxans+="<select "+disabled+" name=\""+themeDisplay.getPortletDisplay().getNamespace()+"question_" + question.getQuestionId()+"_"+i + "\" >";
					auxans+="<option value=\"\" "+disabled+" label=\"\"/>";//primer valor vac�o
					List<String> totalBlankSols = fillblankQuestionType.getBlankSols(sol, false);
					for(String blankSol:totalBlankSols){
						String selected = "";
						if(ans.equals(blankSol)) selected ="selected";							
						auxans+="<option value=\""+ blankSol +"\" "+disabled+" label=\""+blankSol +"\" "+ selected +"/>";//dropdown
					}
					auxans+="</select>";
					if(showCorrectAnswer) {
						for(String blankSol:blankSols){
							if(solok != "") solok += " | ";
							solok += blankSol;
						}
						auxans += "<div class=\" correct\"> (" + solok + ") </div>";
					}
				}else{
					auxans+=sol;
				}
				answersFeedBack = answersFeedBack.substring(0,answersFeedBack.indexOf(sol))+auxans+answersFeedBack.substring(answersFeedBack.indexOf(sol)+sol.length()); 
				i++;solok="";
			}
			
			if(feedback) {
				answersFeedBack = "<div class=\"content_answer\">" + answersFeedBack + "</div>";
				if (!"".equals(feedMessage)) {
					answersFeedBack += "<div class=\"questionFeedback\">" + feedMessage + "</div>";
				}
			}%>
			<%=answersFeedBack%>
		</div>
	</div>

</c:if>

<script>
	function <portlet:namespace />validateQuestion<%=questionId%>(){
		return $('input[name^="<portlet:namespace />question_<%=questionId%>_"][type="text"]').filter(function(){return this.value==""}).length == 0
			&& $('select[name^="<portlet:namespace />question_<%=questionId%>_"] > option[value=""]:selected').length == 0
			&& $("input[name^='<portlet:namespace />question_<%=questionId%>_'][type='radio']:checked").val() != undefined;
	}
</script>