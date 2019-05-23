<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.ted.lms.learning.activity.question.model.QuestionTypeFactory"%>
<%@page import="com.ted.lms.learning.activity.question.model.QuestionType"%>
<%@page import="com.ted.lms.learning.activity.question.model.Question"%>
<%@page import="java.util.List"%>
<div class="contentQuestionVideo">
	<div class="video">
		<video width="600" height="338" id="playervideo" ${controls }
			preload="none" src="${video}" type="${mimeType }"></video>
	</div>
	
	<c:if test="${not empty listQuestions }">
	
		<%List<Question> questions = (List<Question>)request.getAttribute("listQuestions"); 
		
		QuestionType questionType = null;
		QuestionTypeFactory questionTypeFactory = null;
		
		Map<Long, QuestionTypeFactory> questionTypeFactories = new HashMap<Long, QuestionTypeFactory>();
		
		for(Question question: questions){
		
			questionType = question.getQuestionType();
			questionTypeFactory = questionType.getQuestionTypeFactory();
			
			if(!questionTypeFactories.containsKey(questionTypeFactory.getType())){
				questionTypeFactories.put(questionTypeFactory.getType(), questionTypeFactory);
			}%>
			
			<div class="hide questionVideo"
				id='<%=renderResponse.getNamespace() + "question_" + question.getQuestionId() %>'>
				
				<aui:form name='<%="questionform_" + question.getQuestionId() %>' role="form">
				
					<aui:input name="questionId" value="<%=question.getQuestionId() %>" type="hidden" />
					<aui:input name="latId" value="${latId}" type="hidden" />
					
					<liferay-util:include page="<%=questionType.getURLQuestion() %>" portletId="<%=questionTypeFactory.getPortletId() %>" >
						<liferay-util:param name="questionId" value="<%=String.valueOf(question.getQuestionId()) %>"/>
						<liferay-util:param name="tryResultData" value="${tryResultData }" />
						<liferay-util:param name="canUserDoNewTry" value="false" />
						<liferay-util:param name="showCorrectAnswer" value="false"/>
						<liferay-util:param name="showCorrectAnswerOnlyOnFinalTry" value="false"/>
					</liferay-util:include>
					
					<aui:button value="save" onClick='<%="javascript:" + renderResponse.getNamespace() + "answerQuestion(" + question.getQuestionId() + ")"%>' />
				</aui:form>
			</div>
			<div class="hide questionVideo"
				id='<%=renderResponse.getNamespace() + "feedback_" + question.getQuestionId() %>'>
				<div id='<%=renderResponse.getNamespace() + "feedback_content_" + question.getQuestionId() %>'></div>
				<aui:button value="continue"
					onClick='<%="javascript:" + renderResponse.getNamespace() + "continueQuestion(" + question.getQuestionId() + ")"%>' />
			</div>
		<%} %>
	</c:if>
</div>

<!-- JS -->
<script
	src="https://cdn.jsdelivr.net/npm/mediaelement@4.2.7/build/mediaelement-and-player.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/mediaelement@4.2.7/build/renderers/dailymotion.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/mediaelement@4.2.7/build/renderers/facebook.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/mediaelement@4.2.7/build/renderers/soundcloud.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/mediaelement@4.2.7/build/renderers/twitch.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/mediaelement@4.2.7/build/renderers/vimeo.min.js"></script>

<script>
 	
 	var player;
 
     document.addEventListener('DOMContentLoaded', function() {
    	 
    	 
    	var answerQuestion = true;
  	 	var plays = 0;
  	 	var finished = false;

	 	player = new MediaElement("playervideo", {
	    	pluginPath: 'https://cdn.jsdelivr.net/npm/mediaelement@4.2.7/build/',
	        shimScriptAccess: 'always',
	        success: function (media, node) {
				
	        }
	    });
	
	    var currentTime = parseInt('${currentTime}');
		if(currentTime > 0){
			player.setCurrentTime(currentTime);
		}
		
		player.addEventListener('play', function () {
			if(!$('#<portlet:namespace/>videoQuestionFeedback').hasClass("hide")){
				$('#<portlet:namespace/>videoQuestionFeedback').addClass("hide")
			}	
			$('#<portlet:namespace/>videoQuestionFeedback').html("");
			finished = false;
			if(plays > 0){
				if($('[id^=<portlet:namespace/>question_]')!=null){
					$('[id^=<portlet:namespace/>question_]').addClass("hide");	
				}
			}
			plays++;
			
			
			
		});	
			
		player.addEventListener('ended',function() {
			
			var duration = player.getDuration();
			
			<portlet:namespace/>finishTry(100,duration,plays);	

			// Process Success - A LearningActivityResult returned
			finished = true;	
			Liferay.Portlet.refresh('#p_p_id_activityNavigator_WAR_liferaylmsportlet_');
			Liferay.Portlet.refresh('#p_p_id_lmsactivitieslist_WAR_liferaylmsportlet_');
			player.setControls(true);
			if('${isVimeoIframe}' == 'true'){
				var src = 	document.getElementById("playervideo_vimeo_iframe").src;
				var index = src.indexOf("background");
				if(index > 0){
					src = src.substring(0,index-1);
					document.getElementById("playervideo_vimeo_iframe").src = src;
				}
			}
			
			
		});
		
		//Creamos el array para las preguntas
		var questions = [];
		<c:forEach items="${timeQuestions }" var="question">
			var question = ["${question.key}","${question.value}"];
			if(parseInt("${question.value}") >= currentTime){
				questions.push(question);
			}
			
		</c:forEach>
		
		questions.sort(function(a, b){return a[1]-b[1]});
		
		if(questions.length > 0){
			var indexQuestion = 0;
			var maxQuestions = questions.length;
			var nextQuestion = questions[indexQuestion];
			
			player.addEventListener('timeupdate', function() {
				if(indexQuestion < maxQuestions && nextQuestion[1] < player.currentTime && (nextQuestion[1] > (player.currentTime - 2))){
					player.pause();
					$('#<portlet:namespace/>question_' + nextQuestion[0]).removeClass("hide");
					indexQuestion++;
					if(indexQuestion < maxQuestions){
						nextQuestion = questions[indexQuestion];
					}
					answerQuestion = false;
				}else if(indexQuestion < maxQuestions && nextQuestion[1] < player.currentTime){
					indexQuestion++;
				}
			});
		}
		
		if('${!hasPermissionAccessCourseFinished}' == 'true'){
			
		var unloadEvent = function (e) {
			//console.log("unload event vimeo");  
			if(!finished){
				var duration = player.getDuration();
				currentTime = player.getCurrentTime();
					
				var isDefaultScore = '${isDefaultScore}' == 'true';
				var positionToSave = parseFloat('${videoPosition}');
				var oldScore = parseInt('${oldScore}');
				if (currentTime > positionToSave)
					positionToSave = currentTime;
				var score = 100;														
				if (!isDefaultScore) score = Math.round((currentTime/duration)*100);
				//debugger;
				<portlet:namespace/>finishTry(score, positionToSave,plays);													
			  
			}
		};
		window.addEventListener("beforeunload", unloadEvent);
		
		}
		
	});
     
    function <portlet:namespace/>continueQuestion(questionId){
  		//Cogemos la respuesta
  		$('#<portlet:namespace />feedback_'+questionId).remove();
 		player.play();
  	}
     

</script>
<aui:script>
 	function <portlet:namespace/>answerQuestion(questionId){
 		//Cogemos la respuesta
 		//console.log("guardamos respuesta");
 		var validationCorrect = window["<portlet:namespace />validateQuestion" + questionId]();
 		var A = AUI();
 		
 		if(validationCorrect){
 			answerQuestion = true;
 			$.ajax({
 				dataType: 'json',
 				url: '${saveQuestionURL}',
 			    cache:false,
 				data: $("#<portlet:namespace />questionform_" + questionId).serialize(),
 				success: function(data){			
 					if(data){
 						if(data.correct){
 							$('#<portlet:namespace />question_'+questionId).remove();
 							if(data.questionFeedback){
 								
 								var videoQuestionFeedback = A.one('#<portlet:namespace />feedback_content_'+ questionId);
 								var videoQuestionFeedbackQuery = $('#<portlet:namespace />feedback_content_'+ questionId);
 								videoQuestionFeedback.plug(A.LoadingMask);
 								videoQuestionFeedback.loadingmask.show();
 								
 								<liferay-portlet:resourceURL copyCurrentRenderParameters="false" id="/activities/resource_external/feedback_question" var="videoFeedbackURL">
 									<portlet:param name="actId" value="${activity.getActId()}" />
 								</liferay-portlet:resourceURL>
 								
 								var uri = '${videoFeedbackURL}';
 								uri = Liferay.Util.addParams('<portlet:namespace />questionId=' + questionId, uri) || uri;
 								
 								if (videoQuestionFeedback.io) {
 									videoQuestionFeedback.io.start();
 								} else {
 									videoQuestionFeedbackQuery.load(uri);
 								}
 								
 								videoQuestionFeedback.unplug(A.LoadingMask);
 								
 								$('#<portlet:namespace />feedback_'+questionId).removeClass("hide");
	
 							}else{
 								$('#<portlet:namespace />feedback_'+questionId).remove();
 								player.play();
 							}
 							
							
 						}	
 					}
 				},
 				error: function(){
 					
 				}
 			});
 		}else{
 			//Mostramos los mensajes que sean
 		}
 	}
</aui:script>	
