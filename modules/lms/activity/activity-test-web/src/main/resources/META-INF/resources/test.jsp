<%@page import="com.liferay.portal.kernel.language.UnicodeLanguageUtil"%>
<%@page import="com.ted.lms.learning.activity.question.model.QuestionType"%>
<%@page import="java.util.Map"%>
<%@page import="com.ted.lms.learning.activity.question.model.QuestionTypeFactory"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.ted.lms.learning.activity.test.web.constants.TestConstants"%>
<%@page import="com.ted.lms.learning.activity.question.constants.QuestionsWebPortletKeys"%>
<%@page import="com.ted.lms.learning.activity.question.model.Question"%>
<%@page import="java.util.List"%>
<%@page import="com.ted.lms.learning.activity.QuestionsLearningActivityType"%>
<%@page import="com.ted.lms.learning.activity.test.web.activity.TestActivityType"%>
<%@ include file="init.jsp" %>

<div class="description">${activity.getDescription(themeDisplay.locale)}</div>

<c:if test="${activity.passPuntuation > 0 }">
	<p class="msg_pass"><liferay-ui:message key="learning-activity.test.pass-puntuation" arguments="${activity.passPuntuation }" /></p>
</c:if>

<c:if test="${activityTimestamp > 0}">				  									
	<script type="text/javascript">
		AUI().ready('liferay-notice', 'collection', function(A) {

			var TestActivity = function(options) {
				var instance = this;
				instance.timeout=options.timeout||false;
				instance.warningText=options.warningText||'Timeout Warning: <span class="countdown-timer"/>';
				instance.expiredText=options.expiredText||'Text timeout';
				instance.onClose=options.onClose;
	
				instance.banner=null;
				if(instance.timeout) {
					instance.banner=new Liferay.Notice({content:instance.warningText,closeText:false,toggleText:false});
					instance.countdowntext=instance.banner.one('.countdown-timer');
					if(instance.countdowntext){
						instance.countdowntext.text(instance._formatTime(instance.timeout));
					}
	
					var interval=1000;
					var dateTime = new Date().getTime();
					instance.finishtime = dateTime + parseInt(instance.timeout);
	
					instance._countdownTimer = setInterval(
						function() {
	
							var currentTimeout = (instance.finishtime)-new Date().getTime();
	
							if (currentTimeout > 0) {
								instance.countdowntext.text(instance._formatTime(currentTimeout));
							}
							else {
								instance.banner.html(instance.expiredText);
								instance.banner.toggleClass('popup-alert-notice').toggleClass('popup-alert-warning');
								
								if (instance._countdownTimer) {
									clearInterval(instance._countdownTimer);
								}
	
								if (instance.onClose) {
									window.onbeforeunload=function(){}
									instance.onClose();
								}
							}
						},
						interval
					);
				}
			};
	
			TestActivity.prototype = {
				_formatNumber: function(num) {
					var instance = this;
			
					if (num <= 9) {
						num = '0' + num;
					}
	
					return num;
				},
				_formatTime: function(time) {
					var instance = this;
	
					time = Math.floor(time/1000);
	
					var hours = Math.floor(time/3600);
					time = time%3600;
	
					var minutes = Math.floor(time/60);
					time = time%60;
	
					var seconds = Math.floor(time);
					
					return A.Array.map([hours,minutes,seconds], instance._formatNumber).join(':');
					
				}
	
			};
	
			new TestActivity({timeout:'${timestamp}',
			  warningText: Liferay.Language.get("learning-activity.test.timeout.warning"),
			  expiredText: Liferay.Language.get("learning-activity.test.timeout"),
			  onClose:function(){
					document.getElementById('<portlet:namespace/>formulario').submit();
			}});

		});

	</script>
</c:if>

<script>

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
				        title: '<%= UnicodeLanguageUtil.get(request, "confirm") %>'
				    }
				).render();
				
	        },
	        ['aui-alert', 'liferay-util-window']
		);

		Liferay.provide(
		        window,
		        '<portlet:namespace />popContinue',
		        function(content, boton) {
					var A = AUI();
					
					window.<portlet:namespace />confirmDialog = Liferay.Util.Window.getWindow(
					    {
					        title: '<%= UnicodeLanguageUtil.get(request, "access-from-desktop") %>',
					        dialog: {
						        bodyContent: content,
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
											label: '<liferay-ui:message key="continue" />',
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
								},
						        width: 'auto',
						        height: 'auto',
						        resizable: false,
						        draggable: false,
						        close: true,
								cssClass: 'dialog-principal',
						        destroyOnClose: true,
						        centered: true,
						        modal: true
					        }
					    }
					).render();
					
		        },
		        ['aui-alert', 'liferay-util-window']
		);
		
		Liferay.provide(
        	window,
        	'<portlet:namespace/>submitForm',
			function(e, navigate) {
        		
        		window.onbeforeunload=function(){}
        		
				var returnValue = true;
				
				var A = AUI();
				

			    if (navigate != 'backward') {

				    var questions = $('div.question-page-current div.question')
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
				    	
				    	if(parseInt('${testActivityType.getQuestionsPerPage()}') > 0){
				    		<portlet:namespace />popContinue(Liferay.Language.get("learning-activity.test.questions-without-response.pagination"), e.srcElement);
				    	}else{
				    		<portlet:namespace />popConfirm(Liferay.Language.get("learning-activity.test.questions-without-response"), e.srcElement);
				    	}
   					}
			    }
				
				if (navigate == 'backward' || navigate == 'forward') {
					A.one('#<portlet:namespace/>navigate').val(navigate);

					var page = A.one('.question-page-current');
					var n = page.attr('id').split("-");
					if (navigate == 'backward') {
						n[n.length - 1]--;
					} else {
						n[n.length - 1]++;
					}
					var valor = A.one('#'+n.join("-")).one('.question > input').val();
					A.one('#<portlet:namespace/>currentQuestionId').val(valor);
				} else {
					A.one('#<portlet:namespace/>navigate').val("");
					A.one('#<portlet:namespace/>currentQuestionId').val("0");
				}
				if (returnValue) {
					$('#<portlet:namespace/>formulario').submit();
				}
			},
			['node', 'aui-dialog', 'event', 'node-event-simulate']
		);				
	</script>	
<c:choose>
	<c:when test="${not empty questions }">		
	
		<aui:form name="formulario" action="${!hasPermissionAccessCourseFinished ? correctURL : correctAccessFinishedURL}" method="post" role="form" onSubmit="javascript:return false;">
		
			<aui:input type="hidden" name="currentQuestionId" value="${currentQuestionId}"/>
			<aui:input type="hidden" name="navigate" value=""/>
			
			<c:set var="showPrevious" value="false" />
			<c:set var="showNext" value="false" />
			<c:set var="currentPage" value="0" />
			
			<%List<Question> questions = (List<Question>)request.getAttribute("questions"); 
			long random = (Long)request.getAttribute("random");
			long limitChunk = (Long)request.getAttribute("limitChunk");	
			long currentQuestionId = (Long)request.getAttribute("currentQuestionId");
			TestActivityType testActivityType = (TestActivityType)request.getAttribute("testActivityType");
			
			Map<Long, QuestionTypeFactory> questionTypeFactories = new HashMap<Long, QuestionTypeFactory>();
			
			for(int index = 0; index < random; index += limitChunk) {
				boolean questionPageCurrent = false;
				String questionPageId = renderResponse.getNamespace() + "question-page-" + (testActivityType.getQuestionsPerPage() == 0 ? 0 : index / testActivityType.getQuestionsPerPage()); %>
				<div class='question-page' id="<%=questionPageId %>">
					
					<%Question question = null;
					QuestionType questionType = null;
					QuestionTypeFactory questionTypeFactory = null;
					for(int jndex = 0; jndex < Math.min(limitChunk, random - index); jndex++) {
						question = questions.get(jndex + index);
						if (question.getQuestionId() == currentQuestionId) {
							questionPageCurrent = true;
						}
						questionType = question.getQuestionType();
						questionTypeFactory = questionType.getQuestionTypeFactory();
						if(!questionTypeFactories.containsKey(questionTypeFactory.getType())){
							questionTypeFactories.put(questionTypeFactory.getType(), questionTypeFactory);
						}
						%>
						<liferay-util:include page="<%=questionType.getURLQuestion() %>" portletId="<%=questionTypeFactory.getPortletId() %>" >
							<liferay-util:param name="questionId" value="<%=String.valueOf(question.getQuestionId()) %>"/>
							<liferay-util:param name="tryResultData" value="${tryResultData }" />
							<liferay-util:param name="canUserDoNewTry" value="${canUserDoNewTry }" />
							<liferay-util:param name="showCorrectAnswer" value="false"/>
							<liferay-util:param name="showCorrectAnswerOnlyOnFinalTry" value="false"/>
						</liferay-util:include>
					<%} 
					boolean markAsCurrentPage = questionPageCurrent || (currentQuestionId == 0 && index == 0);
					if (markAsCurrentPage && index != 0) {%>
						<c:set var="showPrevious" value="true" />
					<%}
					if (markAsCurrentPage && (index + limitChunk < random)) {%>
						<c:set var="showNext" value="true" />
					<%}
					if (markAsCurrentPage) {%>
						<script>$('#<%=questionPageId%>').addClass("question-page-current");</script>
						<c:set var="currentPage" value="<%=(testActivityType.getQuestionsPerPage() == 0 ? 0 : index / testActivityType.getQuestionsPerPage()) + 1%>" />
					<%}else{%>
						<script>$('#<%=questionPageId%>').addClass("hide");</script>
					<%}
					%>
				</div>
				<%
			}%>
			<c:choose>
				<c:when test="${testActivityType.questionsPerPage == 0 }">
					<aui:button value="send" onClick='${renderResponse.namespace}submitForm(event, null);' />
				</c:when>
				<c:otherwise>
					
					<div id="testactivity-navigator" class="taglib-page-iterator">
						<c:if test="${showPrevious }">
							<div id="testactivity-navigator-previous">
								<aui:button type="button" value="learning-activity.test.previous" onClick="${renderResponse.namespace}submitForm(event, 'backward');" ></aui:button>
							</div>
						</c:if>
						<c:if test="${(showPrevious || showNext) && currentPage >= 1 }">
							<div id="testactivity-navigator-pages">
								<p>${currentPage} / ${totalPages}</p>
								<div id="testactivity-navigator-progress">
									<c:forEach var="i" begin="1" end="${totalPages }">
										<c:set var="browsed" value="${i <=currentPage }" />
										<div id="testactivity-navigator-progress-frame-${i}" class='testactivity-navigator-progress-frame ${browsed ? "testactivity-navigator-progress-frame-browsed" : "testactivity-navigator-progress-frame-not-browsed"}' 
											style='width: ${(width_frame / 100)}.${(width_frame % 100)}%'></div>
									</c:forEach>
								</div>
							</div>
						</c:if>
						<div id="testactivity-navigator-next">
							<c:choose>
								<c:when test="${showNext }">
									<aui:button type="button" value="learning-activity.test.next" 
										onClick="${renderResponse.namespace}submitForm(event, 'forward');" />
								</c:when>
								<c:otherwise>
									<aui:button type="button" value="send" onClick="${renderResponse.namespace}submitForm(event, null);" />
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</c:otherwise>
			</c:choose>
	
			<aui:input type="hidden" name="navigate" value=""/>
			
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
		
		<script>
		$(document).ready(function(){
			window.onbeforeunload = function() { 
				return Liferay.Language.get("learning-activity.test.try-confirm.close");
			}
		})
		</script>

	</c:when>
	<c:otherwise>
		<div class="alert alert-info"><liferay-ui:message key="learning-activity.test.no-questions" /></div>
	</c:otherwise>
</c:choose>