<%@page import="com.liferay.portal.kernel.module.configuration.ConfigurationException"%>
<%@page import="com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil"%>
<%@page import="com.ted.lms.learning.activity.question.web.internal.configuration.QuestionWebConfiguration"%>
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

QuestionWebConfiguration questionWebConfiguration = null;
try {
	questionWebConfiguration = ConfigurationProviderUtil.getCompanyConfiguration(QuestionWebConfiguration.class, themeDisplay.getCompanyId());
} catch (ConfigurationException e) {
	e.printStackTrace();
}

String editorName = questionWebConfiguration != null ? questionWebConfiguration.getHTMLQuestionEditor() : "alloyeditor";
String namespace = ParamUtil.getString(request, "namespace", themeDisplay.getPortletDisplay().getNamespace());
%>

<liferay-ui:error exception="<%= MinNumAnswerException.class %>" message="questions.error.min-num-answers" />
<liferay-ui:error exception="<%= MinNumCorrectAnswerException.class %>" message="questions.error.min-num-correct-answers" />

<aui:fieldset cssClass="questions" label="learning-activity.test.questions-and-answers">
	<liferay-ui:icon-menu
		direction="down"
		showWhenSingleIcon="true"
		markupView="lexicon"
		message="<%= StringPool.BLANK %>"
	>
	
		<liferay-portlet:renderURL var="importQuestionsXMLURL" windowState="<%=LiferayWindowState.POP_UP.toString() %>" portletName="<%=QuestionsWebPortletKeys.EDIT_QUESTIONS %>">
			<portlet:param name="mvcRenderCommandName" value="/questions/import_questions" />
			<portlet:param name="<%=Constants.ACTION %>" value="XML" />
			<portlet:param name="actId" value="<%= String.valueOf(actId) %>" />
			<portlet:param name="questionIdsAllowed" value="<%= questionIdsAllowedString %>" />
		</liferay-portlet:renderURL>
		
		<liferay-ui:icon label="true" message="question.import-questions.xml" url='${importQuestionsXMLURL }' useDialog="true"/>
		
		<liferay-portlet:renderURL var="importQuestionsCSVURL" windowState="<%=LiferayWindowState.POP_UP.toString() %>" portletName="<%=QuestionsWebPortletKeys.EDIT_QUESTIONS %>">
			<portlet:param name="mvcRenderCommandName" value="/questions/import_questions" />
			<portlet:param name="<%=Constants.ACTION %>" value="CSV" />
			<portlet:param name="actId" value="<%= String.valueOf(actId) %>" />
			<portlet:param name="questionIdsAllowed" value="<%= questionIdsAllowedString %>" />
		</liferay-portlet:renderURL>
		
		<liferay-ui:icon label="true" message="question.import-questions.csv" url='${importQuestionsCSVURL }' useDialog="true"/>
		
		<liferay-portlet:resourceURL copyCurrentRenderParameters="false" id="/questions/export_questions" var="exportQuestionsXMLURL" >
			<portlet:param name="<%=Constants.ACTION %>" value="XML" />
			<portlet:param name="actId" value="<%= String.valueOf(actId) %>" />
		</liferay-portlet:resourceURL>

		<liferay-ui:icon label="true" message="question.export-questions.xml" url="${exportQuestionsXMLURL }" />
		
		<liferay-portlet:resourceURL copyCurrentRenderParameters="false" id="/questions/export_questions" var="exportQuestionsCSVURL" >
			<portlet:param name="<%=Constants.ACTION %>" value="CSV" />
			<portlet:param name="actId" value="<%= String.valueOf(actId) %>" />
		</liferay-portlet:resourceURL>

		<liferay-ui:icon label="true" message="question.export-questions.excel" url="${exportQuestionsCSVURL }" />
		
	</liferay-ui:icon-menu>
		
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
						<liferay-util:param name="editorName" value="<%=editorName %>" />
						<liferay-util:param name="namespaceDest" value="<%=themeDisplay.getPortletDisplay().getNamespace() %>" />
					</liferay-util:include>
				</div> 
			<%}
		}%>
	</div>
	<div class="row">
		<div class="col-md-6">
			<aui:button value="new-question" name="newQuestion"/>
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

<liferay-portlet:resourceURL var="addQuestionImportURL" portletName="<%=QuestionsWebPortletKeys.EDIT_QUESTIONS %>" copyCurrentRenderParameters="false">
</liferay-portlet:resourceURL>

<script>

	var namespace = '${themeDisplay.getPortletDisplay().getNamespace()}';

	function refreshQuestions(questions){
		for(i in questions){
			var A = AUI();
			var parent = A.one('#<portlet:namespace />questions');
			var list = A.all('#<portlet:namespace />questions > div').filter('[id^=<portlet:namespace />div_question_]'),lastNode=null;
			var iter = 1;
			
			if (list.size()) {
				lastNode = list.item(list.size() - 1);
				iter = A.all('#'+lastNode.get('id') +' input[name="<portlet:namespace />iteratorQuestion"]').val();
				iter = parseInt(iter) +1;
			}
			
			var id = '<portlet:namespace />div_question_'+iter;
			
			var newQuestion = A.Node.create('<div id="' + id + '" ></div>');
			
			parent.append(newQuestion);
			
			var questionUrl = "";
			
			var getQuestionURL = '${addQuestionImportURL}';
			getQuestionURL += '&_<%=QuestionsWebPortletKeys.EDIT_QUESTIONS%>_questionId=' + questions[i];
			getQuestionURL += '&_<%=QuestionsWebPortletKeys.EDIT_QUESTIONS%>_<%=Constants.ACTION %>=<%=Constants.ADD %>';
			
			$.ajax({
				url: getQuestionURL,
				dataType:"json",
				cache:false,
				success:function(data){
					console.log("data: " + data);
					console.log("data.urlQuestion: " + data.urlQuestion);
					questionUrl =data.urlQuestion;
					console.log("questionUrl: " + questionUrl);
					questionUrl += '&_<%=QuestionsWebPortletKeys.EDIT_QUESTIONS%>_iteratorQuestion=' + iter;
					questionUrl += '&_<%=QuestionsWebPortletKeys.EDIT_QUESTIONS%>_questionIdsAllowed=<%=questionIdsAllowedString%>';
					questionUrl += '&_<%=QuestionsWebPortletKeys.EDIT_QUESTIONS%>_namespaceDest=' + namespace;
					questionUrl += '&_<%=QuestionsWebPortletKeys.EDIT_QUESTIONS%>_editorName=<%=editorName%>';
					questionUrl += '&_<%=QuestionsWebPortletKeys.EDIT_QUESTIONS%>_questionId=' + questions[i];
					console.log("questionUrl .3..: " + questionUrl);
					
					$.ajax({
						type: "POST",
						url: questionUrl,
						cache:false,
						dataType: "html",
						success:function(data){
							newQuestion.html(data);
							console.log("newQuestion: " + newQuestion);
							$('#' + id).find("script").each(function(){
								console.log("script: " + $(this).text());
						     	eval($(this).text());
						   });
						}
						
					});
				},
				error: function(){
					console.log("ERROR");
				}
			});
			
			
		}
	}

	function <portlet:namespace />addQuestion(questionUrl){
		<%if(questionTypeFactories.size() > 1){%>
			$('#<portlet:namespace />new_question_factory').toggle("hide");
		<%}%>
		AUI().use('aui-node', 'aui-base',
			function(A) {
				var parent = A.one('#<portlet:namespace />questions');
				var list = A.all('#<portlet:namespace />questions > div').filter('[id^=<portlet:namespace />div_question_]'),lastNode=null;
				var iter = 1;
				
				if (list.size()) {
					lastNode = list.item(list.size() - 1);
					iter = A.all('#'+lastNode.get('id') +' input[name="<portlet:namespace />iteratorQuestion"]').val();
					iter = parseInt(iter) +1;
				}
				
				var id = '<portlet:namespace />div_question_'+iter;
				
				var newQuestion = A.Node.create('<div id="' + id + '" ></div>');
				
				questionUrl += '&_<%=QuestionsWebPortletKeys.EDIT_QUESTIONS%>_iteratorQuestion=' + iter;
				questionUrl += '&_<%=QuestionsWebPortletKeys.EDIT_QUESTIONS%>_questionIdsAllowed=<%=questionIdsAllowedString%>';
				questionUrl += '&_<%=QuestionsWebPortletKeys.EDIT_QUESTIONS%>_namespaceDest=<portlet:namespace />';
				questionUrl += '&_<%=QuestionsWebPortletKeys.EDIT_QUESTIONS%>_editorName=<%=editorName%>';
				
				parent.append(newQuestion);
				console.log("iter: "+iter);
				
				$.ajax({
					type: "POST",
					url: questionUrl,
					cache:false,
					dataType: "html",
					success:function(data){
						console.log("data: " + data);
						newQuestion.html(data);
						console.log("newQuestion: " + newQuestion);
						$('#' + id).find("script").each(function(){
							console.log("script: " + $(this).text());
					     	eval($(this).text());
					   });
					}
					
				});
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

function <portlet:namespace />addAnswer(iteratorQuestion, urlAnswer, portletId){
	AUI().use('aui-node', 'aui-base',
		function(A) {
			var parent = A.one('#<portlet:namespace />div_answers_' + iteratorQuestion);
			var list = A.all('#<portlet:namespace />div_answers_' + iteratorQuestion + ' > div'),lastNode=null;
			var iter = 1;
			
			if (list.size() > 0) {
				lastNode = list.item(list.size() - 1);
				iter = A.all('#'+lastNode.get('id') +' input[name="<portlet:namespace />' + iteratorQuestion + '_iterator"]').val();
				iter = parseInt(iter) +1;
			}
			
			urlAnswer += '&_' + portletId + '_editorName=<%=editorName%>';
			urlAnswer += '&_' + portletId + '_iterator=' + iter;
			urlAnswer += '&_' + portletId + '_iteratorQuestion=' + iteratorQuestion;
			urlAnswer += '&_' + portletId + '_namespaceDest=<portlet:namespace />';
			
			var id = '<%=themeDisplay.getPortletDisplay().getNamespace()%>' + iteratorQuestion + '_div_answer_'+iter;
			
			var newQuestion = A.Node.create('<div class="row" id="' + id + '" ></div>');
			
			parent.append(newQuestion);
			
			$.ajax({
				type: "POST",
				url: urlAnswer,
				cache:false,
				dataType: "html",
				success:function(data){
					newQuestion.html(data);
					console.log("newQuestion: " + newQuestion);
					$('#' + id).find("script").each(function(){
						console.log("script: " + $(this).text());
				     	eval($(this).text());
				   });
				}
			});
			
			console.log("urlAnswer: " + urlAnswer);
		}
	);
}

function <portlet:namespace />deleteQuestion(iteratorQuestion){
	$('#<portlet:namespace />div_question_' + iteratorQuestion).remove();
}

function <portlet:namespace />deleteAnswer(iteratorQuestion,iteratorAnswer){
	$('#<%=themeDisplay.getPortletDisplay().getNamespace()%>' + iteratorQuestion + "_div_answer_" + iteratorAnswer).remove();
}

</script>
