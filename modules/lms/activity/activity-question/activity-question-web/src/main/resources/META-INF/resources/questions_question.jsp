<%@page import="com.liferay.taglib.aui.AUIUtil"%>
<%@page import="javax.portlet.PortletResponse"%>
<%@page import="com.liferay.portal.kernel.util.JavaConstants"%>
<%@page import="javax.portlet.PortletRequest"%>
<%@page import="com.liferay.portal.kernel.module.configuration.ConfigurationException"%>
<%@page import="com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil"%>
<%@page import="com.ted.lms.learning.activity.question.web.internal.configuration.QuestionWebConfiguration"%>
<%@page import="com.liferay.portal.kernel.util.StringUtil"%>
<%@page import="com.liferay.portal.kernel.util.ArrayUtil"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.liferay.portal.kernel.util.HtmlUtil"%>
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

long questionType = ParamUtil.getLong(request, "questionType");
long questionId = ParamUtil.getLong(request, "questionId");
int iteratorQuestion = ParamUtil.getInteger(request, "iteratorQuestion");
String questionIdsAllowedString = ParamUtil.getString(request, "questionIdsAllowed");
String editorName = ParamUtil.getString(request, "editorName");
long[] questionIdsAllowed = Validator.isNotNull(questionIdsAllowedString) ? StringUtil.split(questionIdsAllowedString,",", 0L) : null;
List<QuestionTypeFactory> questionTypeFactories = QuestionTypeFactoryRegistryUtil.getQuestionFactories(themeDisplay.getCompanyId(), questionIdsAllowed);
String namespace = ParamUtil.getString(request, "namespaceDest", themeDisplay.getPortletDisplay().getNamespace());

Question question = null;
if(questionId > 0){
	question = QuestionLocalServiceUtil.getQuestion(questionId);
	questionType = question.getQuestionTypeId();
}
QuestionTypeFactory questionTypeFactory = QuestionTypeFactoryRegistryUtil.getQuestionTypeFactoryByType(questionType);
System.out.println("namespace questions_question: " + namespace);

PortletRequest portletRequest = (PortletRequest)request.getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);

PortletResponse portletResponse = (PortletResponse)request.getAttribute(JavaConstants.JAVAX_PORTLET_RESPONSE);

String namespaceEditor = AUIUtil.getNamespace(portletRequest, portletResponse);

if (Validator.isNull(namespaceEditor)) {
	namespaceEditor = AUIUtil.getNamespace(request);
}
System.out.println("namespace  namespaceEditor: " + namespaceEditor);
%>

<aui:input  type="hidden" name='<%=namespace + "questionId_" + iteratorQuestion %>' id='<%=namespace + "questionId_" + iteratorQuestion%>' value="<%=questionId%>" useNamespace="false" />
<aui:input  type="hidden" name='<%=namespace + "iteratorQuestion" %>' id='<%=namespace + "iteratorQuestion" %>' value="<%=iteratorQuestion %>" useNamespace="false" />
<aui:input  type="hidden" name='<%=namespace + "questionType_" + iteratorQuestion %>' id='<%=namespace + "questionType_" + iteratorQuestion  %>' 
	value="<%=questionType %>" useNamespace="false" />
<c:if test="<%=questionTypeFactory.getPenalize()%>">
    <aui:input name='<%=namespace + "penalize_" + iteratorQuestion %>' id='<%=namespace + "penalize_" + iteratorQuestion %>' type="hidden" 
    	value="<%=question!=null?question.isPenalize():false%>" useNamespace="false" />
</c:if>

<aui:model-context bean="<%= question %>" model="<%= Question.class %>" />

<label><liferay-ui:message key="num-question" arguments="<%=iteratorQuestion %>"/></label>

<c:if test="<%=question != null %>">
	<div class="question-edit">
	
		<div class="row question-title-edit <%=questionTypeFactory.getIconCssClass() %>">
		
			<div class="col-md-11 question-title-collapsible" onclick="<%="javascript:" + namespace + "showAnswers(" + iteratorQuestion + ")" %>">
				<span><%=HtmlUtil.extractText(question.getText()) %></span>
			</div>
			<div class="col-md-1 question-title-action">
				<liferay-ui:icon-menu
						cssClass='entry-options inline'
						direction="left-side"
						icon="<%= StringPool.BLANK %>"
						markupView="lexicon"
						message="<%= StringPool.BLANK %>"
						showWhenSingleIcon="true"
					>
						<liferay-ui:icon
							message="edit-question"
							url='<%="javascript:" + namespace + "editQuestion(" + iteratorQuestion + ")" %>'
						/>
						<liferay-ui:icon-delete
							label="true"
							url='<%="javascript:" + namespace + "deleteQuestion(" + iteratorQuestion + ")" %>'
							confirmation="question.delete-question.confirm"
						/>
					</liferay-ui:icon-menu>
			</div>
		</div>
		
		<%List<Answer> answers = AnswerLocalServiceUtil.getAnswersByQuestionId(question.getQuestionId());%>
		
		<c:if test="<%=answers != null && answers.size() > 0 %>">
			<ul class="answers-edit" id='<%=namespace + "answers" + iteratorQuestion%>' style="display:none">
				<%for(Answer answer: answers){ %>
					<li class='answer-edit <%=answer.isCorrect() ? "correct" : ""%>'>
						<span><%=HtmlUtil.extractText(answer.getAnswer()) %></span>
					</li>
				<%} %>
			</ul>
		</c:if>
	</div>
</c:if>

<div class='question-editing <%=(question != null) ? "hide":"" %>' id='<%=namespace + "questionEdit" + iteratorQuestion %>'>
	<div class="row">
		<%for(QuestionTypeFactory questionTypeFactoryIterator: questionTypeFactories){ %>
			<div class="col-md-4">
				<aui:input label="<%=questionTypeFactoryIterator.getTitle(themeDisplay.getLocale()) %>" type="radio" value="<%=questionTypeFactoryIterator.getType() %>"
					checked="<%=questionType == questionTypeFactoryIterator.getType() %>" name='<%="question_type_read_only_" + iteratorQuestion %>' disabled="true"/>
			</div>
		<%} %>
	</div>
	
	<div class="col-md-12">
		<c:if test="<%=Validator.isNotNull(questionTypeFactory.getURLQuestionExtraData()) %>">
			<div class="row">
				<liferay-util:include page="<%=questionTypeFactory.getURLQuestionExtraData()%>" portletId="<%=questionTypeFactory.getPortletId()%>" servletContext="<%=this.getServletContext() %>">
					<liferay-util:param name="questionId" value="<%=String.valueOf(questionId) %>" />
					<liferay-util:param name="iteratorQuestion" value="<%=String.valueOf(iteratorQuestion) %>" />
				</liferay-util:include>
			</div>
		</c:if>
		<div class="row">
			<div class="question-title col-md-11">
				<label for="<portlet:namespace />questionTitle<%=iteratorQuestion %>"><liferay-ui:message key="question" /></label>
				
				<liferay-editor:editor
					contents='<%= (question != null) ? question.getText() : StringPool.BLANK %>' 
					name='<%="questionTitle" + iteratorQuestion%>'
					placeholder="description"
					required="<%= true %>"
					editorName='<%=editorName %>'
					onChangeMethod='<%="changeQuestion" + iteratorQuestion%>' 
				/>
				<aui:input type="hidden" name='<%=namespace + "question_title_" + iteratorQuestion %>' useNamespace="false" value="<%=(question != null) ? question.getText() : StringPool.BLANK %>"/>
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
					url='<%="javascript:" + namespace + "addAnswer(" + iteratorQuestion + ",'" + questionTypeFactory.getURLAddAnswer(liferayPortletResponse) + "','" + questionTypeFactory.getPortletId() + "')" %>'
				/>
			</liferay-ui:icon-menu>
			</div>
		</div>
	</div>
	
	<div id='<%=namespace + "div_answers_" + iteratorQuestion%>' >
		<liferay-util:include page="<%=questionTypeFactory.getURLEditAnswers()%>" portletId="<%=questionTypeFactory.getPortletId()%>" >
			<liferay-util:param name="questionId" value="<%=String.valueOf(questionId) %>" />
			<liferay-util:param name="iteratorQuestion" value="<%=String.valueOf(iteratorQuestion) %>" />
			<liferay-util:param name="editorName" value="<%=editorName %>"/>
			<liferay-util:param name="namespaceAnswers" value="<%=namespace %>"/>
		</liferay-util:include>
	</div>
</div>

<c:if test="<%=!namespaceEditor.equals(namespace) %>">
	<aui:script>
		document.getElementById("<%=namespaceEditor %>questionTitle<%=iteratorQuestion%>").addEventListener("mouseout",
			function(){
				$('#<%=namespace%>question_title_<%=iteratorQuestion%>').val(window.<%=namespaceEditor %>questionTitle<%=iteratorQuestion%>.getHTML());
			});
	</aui:script>
</c:if>