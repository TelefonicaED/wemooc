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
List<QuestionTypeFactory> questionTypeFactories = QuestionTypeFactoryRegistryUtil.getQuestionFactories(themeDisplay.getCompanyId());
%>

<liferay-ui:error exception="<%= MinNumAnswerException.class %>" message="the-url-title-is-already-in-use-please-enter-a-unique-url-title" />
<liferay-ui:error exception="<%= MinNumCorrectAnswerException.class %>" message="the-url-title-is-already-in-use-please-enter-a-unique-url-title" />

<aui:fieldset cssClass="questions" label="learning-activity.test.questions-and-answers">
	<div id="${renderResponse.namespace }questions">
		<%if(questions != null && questions.size() > 0){
			int numQuestion = 0;
			for(Question question: questions){
				numQuestion++; %>
				<div class="row" id='<%=renderResponse.getNamespace() + "div_question_" + numQuestion %>' >
				
					<liferay-util:include page="/questions_question.jsp" portletId="<%=QuestionsWebPortletKeys.EDIT_QUESTIONS%>" >
						<liferay-util:param name="questionId" value="<%=String.valueOf(question.getQuestionId()) %>" />
						<liferay-util:param name="iteratorQuestion" value="<%=String.valueOf(numQuestion) %>" />
						<liferay-util:param name="questionType" value="<%=String.valueOf(question.getQuestionTypeId()) %>" />
						<liferay-util:param name="namespace" value="<%=renderResponse.getNamespace() %>" />
					</liferay-util:include>
				</div> 
			<%}
		}%>
	</div>
	<aui:button value="new-question" name="newQuestion"/>
	<div class="row" id="${renderResponse.namespace }new_question_factory" style="display:none">
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
	$('#<portlet:namespace />new_question_factory').toggle("hide");
	AUI().use('aui-node', 'aui-io', 'aui-io-deprecated',
		function(A) {
			var parent = A.one('#<portlet:namespace />questions');
			var list = A.all('#<portlet:namespace />questions > div').filter('[id^=<portlet:namespace />div_question_]'),lastNode=null;
			var iter = 1;
			
			if (list.size()) {
				lastNode = list.item(list.size() - 1);
				iter = A.all('#'+lastNode.get('id') +' input[name="<portlet:namespace />iteratorQuestion"]').val();
				iter = parseInt(iter) +1;
			}
			
			questionUrl += '&_<%=QuestionsWebPortletKeys.EDIT_QUESTIONS%>_iteratorQuestion=' + iter;
			console.log("questionUrl: " + questionUrl);

			if(parent!=null) parent.append(A.Node.create('<div id="<portlet:namespace />div_question_'+iter+'" class="row"></div>').plug(A.Plugin.IO,{
				uri:questionUrl,
				parseContent:true,
				data:{iteratorQuestion:iter}
			}));
		}
	);
}

$('#<portlet:namespace />newQuestion').on(
	'click',
	function() {
		console.log("new question factory: " + $('#<portlet:namespace />new_question_factory'));
		$('#<portlet:namespace />new_question_factory').toggle("hide");
	}
);
</script>


