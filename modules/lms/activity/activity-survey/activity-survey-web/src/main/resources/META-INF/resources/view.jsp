<%@page import="com.ted.lms.learning.activity.question.model.QuestionType"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.ted.lms.learning.activity.question.model.QuestionTypeFactory"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.ted.lms.learning.activity.question.model.Question"%>
<%@ include file="init.jsp" %>

<c:if test="${isTeacher }">
	<liferay-ui:icon image="view" message="learning-activity.survey.stadistics" label="true" url="${statisticsURL }" />
</c:if>

<c:choose>
	<c:when test="${userPassed }">
		<p><liferay-ui:message key="learning-activity.survey.done" /></p>
	</c:when>
	<c:otherwise>
		<script type="text/javascript">
				<!--		
				
				Liferay.provide(
						window,
			        '<portlet:namespace />popConfirm',
			        function(content, boton) {
						var A = AUI();
					
						var dialog = Liferay.Util.Window.getWindow(
						    {
						    	dialog: {
						    		bodyContent: content,
							        width: 'auto',
							        height: 'auto',
							        resizable: false,
							        destroyOnClose: true,
							        modal: true,
							        toolbars: {
										footer: [
											{
												cssClass: 'btn-secondary',
												label: '<liferay-ui:message key="cancel" />',
												on: {
													click: function() {
														dialog.hide();
													}
												}
											},
											{
												cssClass: 'btn-primary',
												label: '<liferay-ui:message key="ok" />',
												on: {
													click: function() {
														A.one('#<portlet:namespace/>formulario').detach('submit');
								                		document.getElementById('<portlet:namespace/>formulario').submit();

														dialog.hide();
													}
												},
												primary: true
											}
										]
									}
						    	},
						        title: Liferay.Language.get("confirm")
						    }
						).render();
						
			        },
			        ['aui-alert', 'liferay-util-window']
				);
				
				Liferay.provide(
				        window,
				        '<portlet:namespace/>formValidation',
				function(e) {
					var returnValue = true;
					
					 var questions = $('div.question')
				    	.children('input[name="<portlet:namespace/>question"]')
				    	.each(function(){
				    		validQuestion = window["<portlet:namespace />validateQuestion" + this.value]();
				    		console.log(validQuestion);
				    		if (!validQuestion) {
					    		returnValue = false;
					    	}
				    	});
					
				    if (!returnValue) {
				    	if (e.target) {
				    		targ = e.target.blur();
				    	} else if (e.srcElement) {
				    		targ = e.srcElement.blur();
				    	}
				    	<portlet:namespace/>popConfirm(Liferay.Language.get("learning-activity.survey.questions-without-response"), e.srcElement);
		    		}
				    
					if (!returnValue && e.preventDefault) {
						e.preventDefault();
					}
					return returnValue;
				},
				['node', 'aui-dialog', 'event', 'node-event-simulate']
			);
				//-->
		</script>
				
		<aui:form name="formulario" action="${surveyURL}" method="POST" role="form">
			
			<%List<Question> questions = (List<Question>)request.getAttribute("questions"); 
			Map<Long, QuestionTypeFactory> questionTypeFactories = new HashMap<Long, QuestionTypeFactory>();
			QuestionType questionType = null;
			QuestionTypeFactory questionTypeFactory = null;
			
			for(Question question:questions){
				
				questionType = question.getQuestionType();
				questionTypeFactory = questionType.getQuestionTypeFactory();
				if(!questionTypeFactories.containsKey(questionTypeFactory.getType())){
					questionTypeFactories.put(questionTypeFactory.getType(), questionTypeFactory);
				}
				%>
				<liferay-util:include page="<%=questionType.getURLQuestion() %>" portletId="<%=questionTypeFactory.getPortletId() %>" >
					<liferay-util:param name="questionId" value="<%=String.valueOf(question.getQuestionId()) %>"/>
				</liferay-util:include>		
			<%}%>
			
			<c:if test="${not empty questions }">
				<aui:button type="submit"  onClick='<%= "return  "+renderResponse.getNamespace() + "formValidation(event);" %>' ></aui:button>
			</c:if>
				
				
			<%//Importamos el javascript necesario para las preguntas
			for (Map.Entry<Long, QuestionTypeFactory> questionEntry : questionTypeFactories.entrySet()) { 
				String[] importJavascript = questionEntry.getValue().getJavascriptImport(themeDisplay.getCDNHost());
				if(importJavascript != null){
					for(String javascript: importJavascript){%>
						<script src="<%=javascript %>" type="text/javascript"></script>
			<%		}
				}
			} %>
		</aui:form>
			
	</c:otherwise>
</c:choose>