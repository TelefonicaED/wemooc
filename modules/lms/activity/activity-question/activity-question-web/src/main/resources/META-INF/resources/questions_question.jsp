<%@page import="com.ted.lms.learning.activity.question.constants.QuestionsWebPortletKeys"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.ted.lms.learning.activity.question.service.QuestionLocalServiceUtil"%>
<%@page import="com.ted.lms.learning.activity.question.model.Question"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.ted.lms.learning.activity.question.service.AnswerLocalServiceUtil"%>
<%@page import="com.ted.lms.learning.activity.question.model.Answer"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.ted.lms.learning.activity.question.model.QuestionTypeFactory"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="com.ted.lms.learning.activity.question.registry.QuestionTypeFactoryRegistryUtil"%>
<%@ include file="init.jsp" %>
<%

List<QuestionTypeFactory> questionTypeFactories = QuestionTypeFactoryRegistryUtil.getQuestionFactories(themeDisplay.getCompanyId());
long questionType = ParamUtil.getLong(request, "questionType");
long questionId = ParamUtil.getLong(request, "questionId");
int iteratorQuestion = ParamUtil.getInteger(request, "iteratorQuestion");
String namespace = ParamUtil.getString(request, "namespace");

System.out.println("questions_question.jsp::iteratorQuestion: " + iteratorQuestion);
System.out.println("questions_question.jsp::questionType: " + questionType);
System.out.println("questions_question.jsp::questionId: " + questionId);
System.out.println("questions_question.jsp::namespace: " + namespace);

Question question = null;
if(questionId > 0){
	question = QuestionLocalServiceUtil.getQuestion(questionId);
	questionType = question.getQuestionType();
}
QuestionTypeFactory questionTypeFactory = QuestionTypeFactoryRegistryUtil.getQuestionTypeFactoryByType(questionType);
%>

<aui:input  type="hidden" name='<%=namespace + "questionId_" + iteratorQuestion %>' id='<%=namespace + "questionId_" + iteratorQuestion%>' value="<%=questionId%>" useNamespace="false" />
<aui:input  type="hidden" name='<%=namespace + "iteratorQuestion" %>' id='<%=namespace + "iteratorQuestion" %>' value="<%=iteratorQuestion %>" useNamespace="false" />
<aui:input  type="hidden" name='<%=namespace + "questionType_" + iteratorQuestion %>' id='<%=namespace + "questionType_" + iteratorQuestion  %>' 
	value="<%=questionType %>" useNamespace="false" />
<c:if test="<%=questionTypeFactory.getPenalize()%>">
    <aui:input name='<%=namespace + "penalize_" + iteratorQuestion %>' id='<%=namespace + "penalize_" + iteratorQuestion %>' type="hidden" 
    	value="<%=question!=null?question.isPenalize():false%>" useNamespace="false" />
</c:if>

<liferay-frontend:fieldset
		collapsible="true"
		id="<%=renderResponse.getNamespace() + iteratorQuestion%>"
		label='<%= LanguageUtil.format(themeDisplay.getLocale(), "num-question", iteratorQuestion)%>'
	>
	
	<div class="col-md-12">
		<%for(QuestionTypeFactory questionTypeFactoryIterator: questionTypeFactories){ %>
			<div class="col-md-4">
				<aui:input label="<%=questionTypeFactoryIterator.getTitle(themeDisplay.getLocale()) %>" type="radio" value="<%=questionTypeFactoryIterator.getType() %>"
					checked="<%=questionType == questionTypeFactoryIterator.getType() %>" name='<%="question_type_read_only_" + iteratorQuestion %>' disabled="true"/>
			</div>
		<%} %>
	</div>
	
	<div class="col-md-12">
		<div class="row">
			<div class="question-title col-md-11">
				<label for="<portlet:namespace />questionTitleMapAsXML<%=iteratorQuestion %>"><liferay-ui:message key="question" /></label>
				
				<liferay-ui:input-localized
					cssClass="form-control"
					editorName="alloyeditor"
					formName="fm"
					ignoreRequestValue="true"
					name="<%="questionTitleMapAsXML" + iteratorQuestion%>"
					placeholder="description"
					type="editor"
					xml="<%= (question != null) ? question.getTextMapAsXML() : StringPool.BLANK %>"
				/>
			</div>
			<div class="col-md-1">
				<liferay-ui:icon-menu
					cssClass='entry-options inline'
					direction="left-side"
					icon="<%= StringPool.BLANK %>"
					markupView="lexicon"
					message="<%= StringPool.BLANK %>"
					showWhenSingleIcon="true"
				>
					<liferay-ui:icon
						message="add-answer"
						url='<%="javascript:" + renderResponse.getNamespace() + "addAnswer(" + iteratorQuestion + ",'" + questionTypeFactory.getURLAddAnswer(liferayPortletResponse) + "')" %>'
					/>
					<liferay-ui:icon-delete
						label="true"
						url="javascript:${renderResponse.namespace }deleteQuestion(<%=iteratorQuestion %>)"
					/>
				</liferay-ui:icon-menu>
			</div>
		</div>
	</div>
	
	<div id="${renderResponse.namespace }div_answers_<%=iteratorQuestion%>" >
		
		<liferay-util:include page="<%=questionTypeFactory.getURLEditAnswers()%>" portletId="<%=questionTypeFactory.getPortletId()%>" servletContext="<%=this.getServletContext() %>">
			<liferay-util:param name="questionId" value="<%=String.valueOf(questionId) %>" />
			<liferay-util:param name="iteratorQuestion" value="<%=String.valueOf(iteratorQuestion) %>" />
			<liferay-util:param name="namespace" value="<%=namespace %>" />
		</liferay-util:include>
	</div>

</liferay-frontend:fieldset>

<script>
function <portlet:namespace />addAnswer(iteratorQuestion, urlAnswer){
	AUI().use('aui-node', 'aui-io',
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

			if(parent!=null) parent.append(A.Node.create('<div class="row" id="<portlet:namespace />' + iteratorQuestion + '_answer_'+iter+'" ></div>').plug(A.Plugin.IO,{
				uri:urlAnswer,
				parseContent:true,
				data:{iterator:iter}
			}));
		}
	);
}
</script>