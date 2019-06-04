<%@page import="com.ted.lms.learning.activity.question.SortableQuestionTypeFactory"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.ted.lms.service.LearningActivityLocalServiceUtil"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ted.lms.learning.activity.question.service.AnswerLocalServiceUtil"%>
<%@page import="com.ted.lms.learning.activity.question.model.Answer"%>
<%@page import="com.ted.lms.learning.activity.question.SortableQuestionType"%>
<%@page import="java.util.List"%>
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

SortableQuestionTypeFactory sortableQuestionTypeFactory = (SortableQuestionTypeFactory)QuestionTypeFactoryRegistryUtil.getQuestionTypeFactoryByType(SortableQuestionTypeFactory.TYPE);
SortableQuestionType sortableQuestionType = sortableQuestionTypeFactory.getSortableQuestionType(question);

String html = "";

List<Answer> answersSelected=sortableQuestionType.getAnswerSelected(documentTryResultData, questionId);
List<Answer> tA= AnswerLocalServiceUtil.getAnswersByQuestionId(question.getQuestionId());
List<Long>answersSelectedIds = new ArrayList<Long>();
for(Answer ans:answersSelected)
	answersSelectedIds.add(ans.getAnswerId());

ArrayList<Answer> testAnswers = new ArrayList<Answer>();
testAnswers.addAll(tA);
Collections.shuffle(testAnswers);

String correctionClass = "";
if(feedback){
	if (showCorrectAnswerOnlyOnFinalTry) {
		if(canUserDoNewTry){
			showCorrectAnswer = false;
		}else{
			showCorrectAnswer = true;
		}
	}
	if(sortableQuestionType.isCorrect(answersSelectedIds, tA)) correctionClass = " correct";
	else correctionClass = " incorrect";
}%>

<div id='<%="id"+questionId%>' class='<%="question sortable" + correctionClass + " questiontype_" + sortableQuestionType.getType()%>'>
	<input type="hidden" name='<%=themeDisplay.getPortletDisplay().getNamespace()+"question"%>' value='<%=question.getQuestionId()%>'/>
	<div class="questiontext">
		<%=question.getText()%>
	</div>
	<div class="content_answer">
		<ul class="sortable" id='<%="question_"+question.getQuestionId()%>' >
			<%List<Answer> answers = testAnswers;
			if(answersSelected != null && answersSelected.size()>0) answers = answersSelected;
			for(int i=0;i<answers.size();i++){%>
				<li class="ui-sortable-default" id='<%=answers.get(i).getAnswerId()%>'>
					<input type="hidden" name='<%=themeDisplay.getPortletDisplay().getNamespace()+"question_" + question.getQuestionId()+"_ans"%>'  value='<%=answers.get(i).getAnswerId()%>'/>
					<div class="answer ui-corner-all">
						<%=answers.get(i).getAnswer()%>
					</div>
				</li>
				<c:if test="<%=showCorrectAnswer %>">
					<div class="correct">
						<%=tA.get(i).getAnswer()%>
					</div>
				</c:if>
			<%}%>
		</ul>
	</div>
</div>


<script>
	function <portlet:namespace />validateQuestion<%=questionId%>(){
		return true;
	}
</script>
