<%@page import="com.liferay.portal.kernel.util.ArrayUtil"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.ted.lms.learning.activity.question.exception.MinNumCorrectAnswerException"%>
<%@page import="com.ted.lms.learning.activity.question.exception.MinNumAnswerException"%>
<%@page import="com.ted.lms.learning.activity.question.constants.QuestionsWebPortletKeys"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="javax.portlet.PortletRequest"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayPortletURL"%>
<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@page import="com.ted.lms.learning.activity.question.registry.QuestionTypeFactoryRegistryUtil"%>
<%@page import="com.ted.lms.learning.activity.question.model.QuestionTypeFactory"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.util.StringUtil"%>
<%@page import="com.ted.lms.learning.activity.question.model.impl.QuestionImpl"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ted.lms.learning.activity.question.service.QuestionLocalServiceUtil"%>
<%@page import="com.ted.lms.learning.activity.question.model.Question"%>
<%@page import="java.util.List"%>
<%@ include file="init.jsp" %>

<%
long actId = ParamUtil.getLong(request, "actId", 0);
List<Question> questions = null;
if(actId > 0){
	questions = QuestionLocalServiceUtil.getQuestions(actId);
}
String questionIdsAllowedString = ParamUtil.getString(request, "questionIdsAllowed", "");
long[] questionIdsAllowed = Validator.isNotNull(questionIdsAllowedString) ? StringUtil.split(questionIdsAllowedString,",", 0L) : null;
List<QuestionTypeFactory> questionTypeFactories = QuestionTypeFactoryRegistryUtil.getQuestionFactories(themeDisplay.getCompanyId(), questionIdsAllowed);
%>

<liferay-ui:error exception="<%= MinNumAnswerException.class %>" message="questions.error.min-num-answers" />
<liferay-ui:error exception="<%= MinNumCorrectAnswerException.class %>" message="questions.error.min-num-correct-answers" />

<aui:fieldset cssClass="questions" label="learning-activity.test.questions-and-answers">
	<div id="${themeDisplay.getPortletDisplay().getNamespace()}questions">
		<%if(questions != null && questions.size() > 0){
			int numQuestion = 0;
			for(Question question: questions){
				numQuestion++; %>
				<div id='<%=renderResponse.getNamespace() + "div_question_" + numQuestion %>' >
				
					<liferay-util:include page="/questions_question.jsp" portletId="<%=QuestionsWebPortletKeys.EDIT_QUESTIONS%>" >
						<liferay-util:param name="questionId" value="<%=String.valueOf(question.getQuestionId()) %>" />
						<liferay-util:param name="iteratorQuestion" value="<%=String.valueOf(numQuestion) %>" />
						<liferay-util:param name="questionType" value="<%=String.valueOf(question.getQuestionTypeId()) %>" />
						<liferay-util:param name="questionIdsAllowed" value="<%=questionIdsAllowedString %>" />
					</liferay-util:include>
				</div> 
			<%}
		}%>
	</div>
	<div class="row">
		<div class="col-md-6">
			<aui:button value="new-question" name="newQuestion"/>
		</div>
		<div class="col-md-6">
			<liferay-ui:icon-menu
				direction="down"
				message="question.import-export"
				showWhenSingleIcon="true"
				showArrow="true"
				
			>
				<liferay-ui:icon label="true" message="question.import-questions.xml" url=''/>
				
				<liferay-ui:icon label="true" message="question.import-questions.csv" url='' />

				<liferay-ui:icon label="true" message="question.export-questions.csv" url="" />
				
				<liferay-ui:icon label="true" message="question.export-questions.xml" url="" />
				
				<liferay-ui:icon label="true" message="question.export-questions.excel" url="" />
			</liferay-ui:icon-menu>
			
		</div>
	</div>
	<div class="row" id="${themeDisplay.getPortletDisplay().getNamespace() }new_question_factory" style="display:none">
		<%for(QuestionTypeFactory questionTypeFactory: questionTypeFactories){ %>
			<div class="col-md-4">
				<aui:button value="<%=questionTypeFactory.getTitle(themeDisplay.getLocale()) %>" name="questionType" 
					onClick='<%=renderResponse.getNamespace() + "addQuestion('" + questionTypeFactory.getURLAddQuestion(liferayPortletResponse) + "');" %>'/>
			</div>
		<%} %>
	</div>
</aui:fieldset>

<script>
function <portlet:namespace />addQuestion(questionUrl){
	<%if(questionTypeFactories.size() > 1){%>
		$('#<portlet:namespace />new_question_factory').toggle("hide");
	<%}%>
	AUI().use('aui-node', 'aui-base', 'aui-io-deprecated',
		function(A) {
			var parent = A.one('#<portlet:namespace />questions');
			var list = A.all('#<portlet:namespace />questions > div').filter('[id^=<portlet:namespace />div_question_]'),lastNode=null;
			var iter = 1;
			
			if (list.size()) {
				lastNode = list.item(list.size() - 1);
				iter = A.all('#'+lastNode.get('id') +' input[name="<portlet:namespace />iteratorQuestion"]').val();
				iter = parseInt(iter) +1;
			}
			
			
			var newQuestion = A.Node.create('<div id="<portlet:namespace />div_question_'+iter+'" ></div>');
			
			questionUrl += '&_<%=QuestionsWebPortletKeys.EDIT_QUESTIONS%>_iteratorQuestion=' + iter;
			questionUrl += '&_<%=QuestionsWebPortletKeys.EDIT_QUESTIONS%>_questionIdsAllowed=<%=questionIdsAllowedString%>';
			questionUrl += '&_<%=QuestionsWebPortletKeys.EDIT_QUESTIONS%>_namespace=<portlet:namespace />';
			
			parent.append(newQuestion);
			console.log("iter: "+iter);
			
			if (!newQuestion.io) {
				newQuestion.plug(
					A.Plugin.IO,
					{
						autoLoad: false,
						method: 'GET'
					}
				);
			}
			

			newQuestion.io.set('uri', questionUrl);
			newQuestion.io.start();
		}
	);
}

$('#<portlet:namespace />newQuestion').on(
	'click',
	function() {
		console.log("new question factory: " + $('#<portlet:namespace />new_question_factory'));
		<%if(questionTypeFactories.size() > 1){%>
		$('#<portlet:namespace />new_question_factory').toggle("hide");
		<%}else if(questionTypeFactories.size() > 0){%>
			<portlet:namespace />addQuestion('<%=questionTypeFactories.get(0).getURLAddQuestion(liferayPortletResponse)%>');
		<%}%>
	}
);

function <portlet:namespace />editQuestion(iteratorQuestion){
	$('#<portlet:namespace />questionEdit' + iteratorQuestion).removeClass("hide");
}

function <portlet:namespace />showAnswers(iteratorQuestion){
	$('#<portlet:namespace />answers' + iteratorQuestion).toggle("hide");
}

function <portlet:namespace />addAnswer(iteratorQuestion, urlAnswer){
	AUI().use('aui-node', 'aui-base', 'aui-io-deprecated',
		function(A) {
			var parent = A.one('#<portlet:namespace />div_answers_' + iteratorQuestion);
			var list = A.all('#<portlet:namespace />div_answers_' + iteratorQuestion + ' > div'),lastNode=null;
			var iter = 1;
			
			if (list.size()) {
				lastNode = list.item(list.size() - 1);
				iter = A.all('#'+lastNode.get('id') +' input[name="<portlet:namespace />' + iteratorQuestion + '_iterator"]').val();
				iter = parseInt(iter) +1;
			}
			
			urlAnswer += '&_<%=QuestionsWebPortletKeys.EDIT_QUESTIONS%>_iterator=' + iter;
			urlAnswer += '&_<%=QuestionsWebPortletKeys.EDIT_QUESTIONS%>_iteratorQuestion=' + iteratorQuestion;
			urlAnswer += '&_<%=QuestionsWebPortletKeys.EDIT_QUESTIONS%>_namespace=<portlet:namespace />';
			
			var newQuestion = A.Node.create('<div class="row" id="<portlet:namespace />' + iteratorQuestion + '_div_answer_'+iter+'" ></div>');
			
			parent.append(newQuestion);
			
			if (!newQuestion.io) {
				newQuestion.plug(
					A.Plugin.IO,
					{
						autoLoad: false,
						method: 'POST'
					}
				);
			}
			
			console.log("urlAnswer: " + urlAnswer);

			newQuestion.io.set('uri', urlAnswer);
			newQuestion.io.start();
		}
	);
}

function <portlet:namespace />deleteQuestion(iteratorQuestion){
	$('#<portlet:namespace />div_question_' + iteratorQuestion).remove();
}

function <portlet:namespace />deleteAnswer(iteratorQuestion,iteratorAnswer){
	$('#<portlet:namespace />' + iteratorQuestion + "_div_answer_" + iteratorAnswer).remove();
}



</script>


