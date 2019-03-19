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
	<!--	AUI().ready('liferay-notice', 'collection', function(A) {

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
				instance.finishtime = new Date().getTime()+instance.timeout;

				instance._countdownTimer = setInterval(
					function() {

						var currentTimeout = instance.finishtime-new Date().getTime();

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
						  warningText: Liferay.Language.get("execActivity.timeout.warning"),
						  expiredText: Liferay.Language.get("execActivity.timeout"),
						  onClose:function(){
							document.getElementById('<portlet:namespace/>formulario').submit();
						}});

		});-->

	</script>
</c:if>

<script>
		Liferay.provide(
		        window,
		        '<portlet:namespace />questionValidation',
		        function(question) {
					var A = AUI();
					var questionValidators = {
						questiontype_options : function(question) {
							return (question.all('div.answer input[type="radio"]:checked').size() > 0);
						},
						questiontype_options_select : function(question) {
							var selectAnswers = question.all('select.answer');
							var validSelects = true;
							selectAnswers.each(function (selectAnswer){
								if (selectAnswer.val()==""||selectAnswer.val()==0){
									validSelects = false;
								}
							});
							return validSelects;
						},
						questiontype_multioptions : function(question) {
							return (question.all('div.answer input[type="checkbox"]:checked').size() > 0);
						},
						questiontype_freetext : function(question) {
							return (A.Lang.trim(question.one('div.answer textarea').val()) != '');
						},
						questiontype_fillblank : function(question) {
							var texts = question.all(':text');
							var validTexts = true;
							texts.each(function(node) {
								validTexts = validTexts && (A.Lang.trim(node.val()) != '');
							});
							
							var selecteds = question.all(':selected');
							var validSelecteds = true;
							selecteds.each(function(node) {
								validSelecteds = validSelecteds && (node.val() != '');
							});
							
							var validCheckeds = (question.all(':radio:checked').size() == question.all('.multichoice').size());
							
							return validTexts && validSelecteds && validCheckeds;
						},
						questiontype_sortable : function(question) {
							return true;
						},
						questiontype_draganddrop : function(question) {
							return (question.all('div.drop > input[type="hidden"][value="-1"]').size() == 0);
						},
						
					};
					
					var clases = question.getAttribute('class').split(" ");
					var questiontypevalidator = '';
					for ( var i = 0; i < clases.length; i++) {
						var clase = clases[i];
						if (clase.indexOf('questiontype_') == 0) {
							questiontypevalidator = clase;
							break;
						}
					}
					if (questionValidators[questiontypevalidator] != null) {
						var resultado = questionValidators[questiontypevalidator](question);
						return resultado;
					} else {
						return true;
					}
					
		        },
		        ['node', 'aui-dialog', 'event', 'node-event-simulate']
		);

		Liferay.provide(
			      window,
			        '<portlet:namespace />popContinue',
			        function(content, boton) {
						var A = AUI();
					
						window.<portlet:namespace />confirmDialog = new A.Dialog(
						    {
						        title: Liferay.Language.get("execactivity.confirm.title"),
						        bodyContent: content,
						        buttons: [
						                  {
						                	  label: Liferay.Language.get("continue"),
						                	  handler: function() {
						                		  A.one('#<portlet:namespace/>formulario').detach('submit');
						                		  document.getElementById('<portlet:namespace/>formulario').submit();
						                		  <portlet:namespace />confirmDialog.close();
						                	  }
						                  },
						                  {
						                	  label: Liferay.Language.get("lms.dialog.cancel"),
						                	  handler: function() {
						                		  <portlet:namespace />confirmDialog.close();
						                	  }
						                  }
						                  ],
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
						).render();
						
			        },
			        ['node', 'aui-dialog', 'event', 'node-event-simulate']
			);
			
			Liferay.provide(
					window,
		        '<portlet:namespace />popConfirm',
		        function(content, boton) {
					var A = AUI();
				
					window.<portlet:namespace />confirmDialog = new A.Dialog(
					    {
					        title: Liferay.Language.get("execactivity.confirm.title"),
					        bodyContent: content,
					        buttons: [
					                  {
					                	  label: Liferay.Language.get("lms.dialog.ok"),
					                	  handler: function() {
					                		  A.one('#<portlet:namespace/>formulario').detach('submit');
					                		  document.getElementById('<portlet:namespace/>formulario').submit();
					                		  <portlet:namespace />confirmDialog.close();
					                	  }
					                  },
					                  {
					                	  label: Liferay.Language.get("lms.dialog.cancel"),
					                	  handler: function() {
					                		  <portlet:namespace />confirmDialog.close();
					                	  }
					                  }
					                  ],
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
					).render();
					
		        },
		        ['node', 'aui-dialog', 'event', 'node-event-simulate']
		);

		Liferay.provide(
		        window,
		        '<portlet:namespace />popContinue',
		        function(content, boton) {
					var A = AUI();
				
					window.<portlet:namespace />confirmDialog = new A.Dialog(
					    {
					        title: Liferay.Language.get("execactivity.confirm.title"),
					        bodyContent: content,
					        buttons: [
					                  {
					                	  label: Liferay.Language.get("continue"),
					                	  handler: function() {
					                		  A.one('#<portlet:namespace/>formulario').detach('submit');
					                		  document.getElementById('<portlet:namespace/>formulario').submit();
					                		  <portlet:namespace />confirmDialog.close();
					                	  }
					                  },
					                  {
					                	  label: Liferay.Language.get("lms.dialog.cancel"),
					                	  handler: function() {
					                		  <portlet:namespace />confirmDialog.close();
					                	  }
					                  }
					                  ],
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
					).render();
					
		        },
		        ['node', 'aui-dialog', 'event', 'node-event-simulate']
		);
		
		Liferay.provide(
        	window,
        	'<portlet:namespace/>submitForm',
			function(e, navigate) {
        		
        		window.onbeforeunload=function(){}
        		
				var returnValue = true;
				
				var A = AUI();
			    var questions = A.all('div.question-page-current div.question');

			    if (navigate != 'backward') {
				    for (var i = 0; i < questions.size(); i++) {
				    	var question = questions.item(i);
				    	var validQuestion = <portlet:namespace />questionValidation(question);
				    	if (typeof validQuestion == 'undefined') {
				    		validQuestion = <portlet:namespace />questionValidation(question);
				    	}
				    	if (!validQuestion) {
				    		returnValue = false;
				    		break;
				    	}
					}
					
				    if (!returnValue) {
				    	if (e.target) {
				    		targ = e.target.blur();
				    	} else if (e.srcElement) {
				    		targ = e.srcElement.blur();
				    	}
				    	
				    	if(testActivityType.getQuestionsPerPage() > 0){
				    		<portlet:namespace />popContinue(Liferay.Language.get("execativity.test.questions.without.responsepagination"), e.srcElement);
				    	}else{
				    		<portlet:namespace />popConfirm(Liferay.Language.get("execativity.test.questions.without.response"), e.srcElement);
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
					submitForm('#<portlet:namespace/>formulario');
				}
			},
			['node', 'aui-dialog', 'event', 'node-event-simulate']
		);				
	
		function finishTry(){
			document.<portlet:namespace />formulario.action='${correctURL}';
			document.<portlet:namespace />formulario.submit();
		}
	</script>	

<c:choose>
	<c:when test="${not empty questions }">		
	
		<aui:form name="formulario" action="${!hasPermissionAccessCourseFinished ? correctURL : correctAccessFinishedURL}" method="post" role="form" onSubmit="javascript:return false;">
		
			<script>
			$(document).ready(function(){
				window.onbeforeunload = function() { 
					return Liferay.Language.get("execativity.test.try.confirm.close");
				}
			})
			</script>
			
			<c:set var="showPrevious" value="false" />
			<c:set var="showNext" value="false" />
			<c:set var="currentPage" value="0" />
			
			<%List<Question> questions = (List<Question>)request.getAttribute("questions"); 
			long random = (Long)request.getAttribute("random");
			long limitChunk = (Long)request.getAttribute("limitChunk");	
			long currentQuestionId = (Long)request.getAttribute("currentQuestionId");
			TestActivityType testActivityType = (TestActivityType)request.getAttribute("testActivityType");
							
			System.out.println("random: " + random);
			System.out.println("limitChunk: " + limitChunk);
			
			for(int index = 0; index < random; index += limitChunk) {
				boolean questionPageCurrent = false;%>
				<div class='question-page' id="question-page-<%= testActivityType.getQuestionsPerPage() == 0 ? 0 : index / testActivityType.getQuestionsPerPage() %>">
					
					<%Question question = null;
					for(int jndex = 0; jndex < Math.min(limitChunk, random - index); jndex++) {
						System.out.println("index: " + (jndex + index));
						question = questions.get(jndex + index);
						if (question.getQuestionId() == currentQuestionId) {
							questionPageCurrent = true;
						}
						System.out.println("question: " + question.getQuestionId());
						System.out.println("type: " + question.getQuestionTypeId());
						System.out.println("URL: " + question.getQuestionType().getURLQuestion());
						System.out.println("portletId: " + question.getQuestionType().getQuestionTypeFactory().getPortletId());
						System.out.println("classname: " + question.getQuestionType().getClassName());
						request.setAttribute("question", question);
						%>
						<liferay-util:include page="<%=question.getQuestionType().getURLQuestion() %>" portletId="<%=question.getQuestionType().getQuestionTypeFactory().getPortletId() %>" >
							<liferay-util:param name="questionId" value="<%=String.valueOf(question.getQuestionId()) %>"/>
							<liferay-util:param name="tryResultData" value="${tryResultData }" />
							<liferay-util:param name="canUserDoNewTry" value="${canUserDoNewTry }" />
							<liferay-util:param name="showCorrectAnswer" value="<%=String.valueOf(testActivityType.getShowCorrectAnswer()) %>"/>
							<liferay-util:param name="showCorrectAnswerOnlyOnFinalTry" value="<%=String.valueOf(testActivityType.getShowCorrectAnswerOnlyOnFinalTry()) %>"/>
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
						<c:set var="currentPage" value="<%=(testActivityType.getQuestionsPerPage() == 0 ? 0 : index / testActivityType.getQuestionsPerPage()) + 1%>" />
					<%}
					%>
				</div>
				<%
			}%>
			<c:choose>
				<c:when test="${testActivityType.questionsPerPage == 0 }">
					<aui:button type="button" value="send" onClick='${renderResponse.namespace}submitForm(event, null);' ></aui:button>
				</c:when>
				<c:otherwise>
					
					<div id="testactivity-navigator" class="taglib-page-iterator">
						<c:if test="${showPrevious }">
							<div id="testactivity-navigator-previous">
								<aui:button type="button" value="execactivity.editActivity.questionsPerPage.previous" onClick="${renderResponse.namespace}submitForm(event, 'backward');" ></aui:button>
							</div>
						</c:if>
						<c:if test="${(showPrevious || showNext) && currentPage >= 1 }">
							<div id="testactivity-navigator-pages">
								<p>${currentPage} / ${totalPages}</p>
								<div id="testactivity-navigator-progress">
									<c:forEach var="i" begin="1" end="${totalPages }">
										<c:set var="browsed" value="${i <=currentPage }" />
										<div id="testactivity-navigator-progress-frame-${i}" class='testactivity-navigator-progress-frame ${browsed ? "testactivity-navigator-progress-frame-browsed" : "testactivity-navigator-progress-frame-not-browsed"}' 
											style='width: ${(width_frame / 100) + "." + (width_frame % 100) }%'></div>
									</c:forEach>
								</div>
							</div>
						</c:if>
						<div id="testactivity-navigator-next">
							<c:choose>
								<c:when test="${showNext }">
									<aui:button type="button" value="execactivity.editActivity.questionsPerPage.next" 
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
		</aui:form>

	</c:when>
	<c:otherwise>
		<div class="alert alert-info"><liferay-ui:message key="learning-activity.test.no-questions" /></div>
	</c:otherwise>
</c:choose>