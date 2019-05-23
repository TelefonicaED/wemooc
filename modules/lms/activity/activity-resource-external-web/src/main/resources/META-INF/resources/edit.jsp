<%@page import="com.ted.lms.learning.activity.question.model.QuestionTypeFactory"%>
<%@page import="com.ted.lms.learning.activity.question.model.QuestionType"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.json.JSONObject"%>
<%@page import="com.ted.lms.learning.activity.question.model.Question"%>
<%@page import="java.util.Hashtable"%>
<%@page import="java.util.regex.Pattern"%>
<%@page import="java.util.regex.Matcher"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="java.text.DecimalFormatSymbols"%>
<%@page import="com.ted.lms.learning.activity.resource.external.web.constants.ResourceExternalConstants"%>
<%@page import="com.ted.lms.learning.activity.resource.external.web.util.ResourceExternalPrefsPropsValues"%>
<%@page import="com.ted.lms.service.LearningActivityLocalServiceUtil"%>
<%@page import="com.ted.lms.learning.activity.resource.external.ResourceExternalActivityTypeFactory"%>
<%@page import="com.ted.lms.model.LearningActivity"%>
<%@page import="com.ted.lms.learning.activity.resource.external.ResourceExternalActivityType"%>
<%@page import="com.ted.lms.learning.activity.question.constants.QuestionsWebPortletKeys"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ include file="init.jsp" %>

<%long actId = ParamUtil.getLong(request, "actId", 0);
boolean canBeEdited = ParamUtil.getBoolean(request, "canBeEdited", true);
ResourceExternalActivityType resourceExternalActivityType = null;
LearningActivity learningActivity = null;
ResourceExternalActivityTypeFactory resourceExternalActivityTypeFactory = null;

if(actId > 0){
	resourceExternalActivityTypeFactory = new ResourceExternalActivityTypeFactory();
	learningActivity = LearningActivityLocalServiceUtil.getLearningActivity(actId);
	resourceExternalActivityType = resourceExternalActivityTypeFactory.getResourceExternalActivityType(learningActivity);
}
%>

<div class="col-md-12">
	<div class="row">
		<div class="col-md-6">
			<aui:input name="video" label="learning-activity.resource-external.video-url" onChange="javascript:${renderResponse.namespace}changeVideo()"
				value='<%=resourceExternalActivityType != null ? resourceExternalActivityType.getVideo() : "" %>'/>
		</div>
		<div class="col-md-6">
			<aui:input name="videoControl" label="learning-activity.resource-external.video-control" type="toggle-switch" 
				value="<%=resourceExternalActivityType != null ? resourceExternalActivityType.getShowControls() : ResourceExternalConstants.DEFAULT_SHOW_CONTROLS%>" ignoreRequestValue="true" />
		</div>
	</div>
</div>
<div class="col-md-6">
	<div class="row">
		<div class="col-md-8">
			<aui:input type="radio" name="correctMode" value="<%=ResourceExternalConstants.CORRECT_VIDEO %>" label="learning-activity.resource-external.correct-video" 
				checked="<%=(resourceExternalActivityType != null && ResourceExternalConstants.CORRECT_VIDEO == resourceExternalActivityType.getCorrectMode()) || (resourceExternalActivityType == null && ResourceExternalConstants.CORRECT_VIDEO == ResourceExternalConstants.DEFAULT_CORRECT_MODE) %>"
				onChange="javascript:${renderResponse.namespace}changeCorrectMode('correctVideo');"/>
		</div>
		<div class="col-md-4">
			<aui:input id="correctVideo"  name="passPuntuation" label="" type="text" suffix="%" value="<%=learningActivity != null ? learningActivity.getPassPuntuation() : resourceExternalActivityTypeFactory.getDefaultScore()%>"
				disabled="<%=(resourceExternalActivityType != null && ResourceExternalConstants.CORRECT_VIDEO != resourceExternalActivityType.getCorrectMode()) || (resourceExternalActivityType == null && ResourceExternalConstants.CORRECT_VIDEO != ResourceExternalConstants.DEFAULT_CORRECT_MODE) %>"/>
		</div>
	</div>
	<div class="row">
		<div class="col-md-8">
			<aui:input type="radio" name="correctMode" value="<%=ResourceExternalConstants.CORRECT_QUESTIONS %>" label="learning-activity.resource-external.correct-questions" 
				checked="<%=(resourceExternalActivityType != null && ResourceExternalConstants.CORRECT_QUESTIONS == resourceExternalActivityType.getCorrectMode()) || (resourceExternalActivityType == null && ResourceExternalConstants.CORRECT_QUESTIONS == ResourceExternalConstants.DEFAULT_CORRECT_MODE) %>"
				onChange="javascript:${renderResponse.namespace}changeCorrectMode('correctQuestions');"/>
				
		</div>
		<div class="col-md-4">
			<aui:input id="correctQuestions" name="passPuntuation" label="" type="text" value="<%=learningActivity != null ? learningActivity.getPassPuntuation() : resourceExternalActivityTypeFactory.getDefaultScore()%>"
				disabled="<%=(resourceExternalActivityType != null && ResourceExternalConstants.CORRECT_QUESTIONS != resourceExternalActivityType.getCorrectMode()) || (resourceExternalActivityType == null && ResourceExternalConstants.CORRECT_QUESTIONS != ResourceExternalConstants.DEFAULT_CORRECT_MODE) %>"/>
		</div>
	</div>
</div>
<div class="col-md-12">
	<aui:input type="toggle-switch"  label="learning-activity.resource-external.use-question-video" name="questionVideo" 
		value="<%= resourceExternalActivityType.getQuestions() != null && resourceExternalActivityType.getQuestions().size() > 0%>" 
		onChange="javascript:${renderResponse.namespace}showQuestionsVideo();"/>
</div>
<div id="${renderResponse.namespace }questionsVideo" class='<%=resourceExternalActivityType.getQuestions() != null && resourceExternalActivityType.getQuestions().size() > 0 ? "" : "hide" %>'>
	<div class="col-md-12 activity-test-questions" >
		<liferay-util:include page="<%=QuestionsWebPortletKeys.EDIT_QUESTIONS_JSP %>" portletId="<%=QuestionsWebPortletKeys.EDIT_QUESTIONS%>" >
			<liferay-util:param name="actId" value="<%=String.valueOf(actId)%>" />
			<liferay-util:param name="canBeEdited" value="<%=String.valueOf(canBeEdited)%>" />
			<liferay-util:param name="questionIdsAllowed" value="<%=ResourceExternalPrefsPropsValues.getQuestionsAllowed(themeDisplay.getCompanyId())%>" />
		</liferay-util:include>
	</div>
	
	<%
	if(resourceExternalActivityType != null && Validator.isNotNull(resourceExternalActivityType.getVideo()) && resourceExternalActivityType.getQuestions().size() > 0){
		boolean isVimeoIframe = false;
		boolean isDLFileEntry = false;
		String videoIframeCode = resourceExternalActivityType.getVideo();
		
		isVimeoIframe = ((videoIframeCode.indexOf("iframe")>-1) &&  (videoIframeCode.indexOf("vimeo")>-1));
		
		boolean videoControlDisabled = resourceExternalActivityType.getShowControls();
		
		String videoCode= resourceExternalActivityType.getVideo();
		
		if(videoCode.indexOf("src=") > 0){
			try{
				Matcher matcher = Pattern.compile("src=\"([^\"]+)\"").matcher(videoCode);
				matcher.find();
				videoCode = matcher.group(1);
			}catch (IllegalStateException e){
				Matcher matcher = Pattern.compile("src=\'([^\']+)\'").matcher(videoCode);
				matcher.find();
				videoCode = matcher.group(1);
			}
		}
		
		if(isVimeoIframe && videoCode.indexOf("?") >= 0){
			videoCode = videoCode.substring(0, videoCode.indexOf("?"));
			
		}
		
		String mimeType = "video/";
		if(videoCode.contains("vimeo.com")){
			videoCode += "?background=1&loop=0&mute=0";	
			mimeType += "vimeo";
		}else if(videoCode.contains("youtu")){
			mimeType += "youtube";
		}else if(videoCode.contains(".mp4")){
			mimeType += "mp4";
		}else if(videoCode.contains(".wmv")){
			mimeType += "wmv";
		}else if(videoCode.contains(".ogv")){
			mimeType += "ogg";
		}else if(videoCode.contains(".webm")){
			mimeType += "webm";
		}else if(videoCode.contains(".flv")){
			mimeType += "flv";
		}else if(videoCode.contains(".mp4")){
			mimeType += "mp4";
		}
		
		
		//Ahora pasamos los tiempos de las preguntas
		Hashtable<Long, Integer> timeQuestions = new Hashtable<Long, Integer>();
		int numSecond = 0;
		JSONObject questionPositions = resourceExternalActivityType.getQuestionPositions();
		List<Question> listQuestions = resourceExternalActivityType.getQuestions();
		for(Question question: listQuestions){
			if(questionPositions.has(ResourceExternalConstants.JSON_SECOND + question.getQuestionId())){
				timeQuestions.put(question.getQuestionId(), questionPositions.getInt(ResourceExternalConstants.JSON_SECOND + question.getQuestionId()));
			}
		}
		%>
		
		<c:set var="controls" value="controls"/>
		<c:set var="currentTime" value="0"/>
		<c:set var="mimeType" value="<%=mimeType %>"/>
		<c:set var="video" value="<%=videoCode %>"/>
		<c:set var="timeQuestions" value="<%=timeQuestions %>"/>
			
		<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/mediaelement@4.2.7/build/mediaelementplayer.min.css">
	
		<div class="contentQuestionVideo col-md-12">
			<div class="video">
				<video  id="${renderResponse.namespace }playervideo" width="600" height="338" autoplay="autoplay" type="">
					<source src="${video}" type="${mimeType }" id="${renderResponse.namespace }srcVideo">
				</video>
			</div>
			<%QuestionType questionType = null;
			QuestionTypeFactory questionTypeFactory = null;
			for(Question question: listQuestions){
				questionType = question.getQuestionType();
				questionTypeFactory = questionType.getQuestionTypeFactory();%>
				<div class="hide questionVideo" id='<%=renderResponse.getNamespace() + "question_video_" + question.getQuestionId()%>'>	
					
					<liferay-util:include page="<%=questionType.getURLQuestion() %>" portletId="<%=questionTypeFactory.getPortletId() %>" >
						<liferay-util:param name="questionId" value="<%=String.valueOf(question.getQuestionId()) %>" />
					</liferay-util:include>
					
					<aui:button value="continue" onClick='<%="javascript:" + renderResponse.getNamespace() + "continueQuestion(" + question.getQuestionId() + ")"%>' />
				</div>
			<%} %>
		</div>
	
		 <!-- JS -->
		 <script src="https://cdn.jsdelivr.net/npm/mediaelement@4.2.7/build/mediaelement-and-player.min.js"></script>
		 <script src="https://cdn.jsdelivr.net/npm/mediaelement@4.2.7/build/renderers/dailymotion.min.js"></script>
		 <script src="https://cdn.jsdelivr.net/npm/mediaelement@4.2.7/build/renderers/facebook.min.js"></script>
		 <script src="https://cdn.jsdelivr.net/npm/mediaelement@4.2.7/build/renderers/soundcloud.min.js"></script>
		 <script src="https://cdn.jsdelivr.net/npm/mediaelement@4.2.7/build/renderers/twitch.min.js"></script>
		 <script src="https://cdn.jsdelivr.net/npm/mediaelement@4.2.7/build/renderers/vimeo.min.js"></script>
		 
		 <script src="/o/activity-resource-external-web/js/media-element-marker.js"></script>
		 
		<script>
		 	
		 	var player;
		 	
		 	document.addEventListener('DOMContentLoaded', function() {   
		
				//Creamos el array para las preguntas
				var questions = [];
				<c:forEach items="${timeQuestions }" var="question">
					var question = ["${question.key}","${question.value}"];
					questions.push(question);
				</c:forEach>
				
				questions.sort(function(a, b){return a[1]-b[1]});
		 		
		 		$('#<portlet:namespace/>playervideo').mediaelementplayer({
		     	    features: ['playpause','current','progress','duration','markers','volume'], //Adding the feature 'markers' enables this plugin
		     		pluginPath: 'https://cdn.jsdelivr.net/npm/mediaelement@4.2.7/build/',
		     		markerColor: '#FCD730', // Optional : Specify the color of the marker
		     		markers:questions, // Specify marker times in seconds 
		     		markerCallback:function(media,time,currentMarker){ // Callback function invoked when a marker position is reache
		     		  	media.pause();
						$('#<portlet:namespace/>question_video_' + currentMarker).removeClass("hide");
		     		},
		     	    success: function (media) {
		     	    	player = media;
		     	    }		
		     	}); 
		 		
		 		player.pause();
		   	
		     	$(".mejs__playpause-button").remove();
		     }); 
		     
		   
		     
		 	function <portlet:namespace/>continueQuestion(questionId){
		 		//Cogemos la respuesta
		 		//console.log("continuamos respuesta");
		 		var A = AUI();
		 		var divQuestionId = $('.question',$('#<portlet:namespace />question_video_'+questionId)).attr("id");
		 		var divQuestion = A.one('#' + divQuestionId);
		 		$('#<portlet:namespace />question_video_'+questionId).remove();
				player.play();
		 		
		 	}
		    
		 	function <portlet:namespace/>changeTime(questionId,second){
		 		if(second>player.duration || second<0){
		 			alert(Liferay.Language.get("resourceexternalactivity.error.seconds"));
		 		}else{
		 			//Borramos el marcador
		 	 		$('#marker-'+questionId).remove();
		 	 		//Creamos marcador nuevo
		 	 		 $('.mejs__time-total').append('<span class="mejs-time-marker" id="marker-'+questionId+'"></span>');
		 	 		//Maquetamos el marcador
		 	 		if (Math.floor(second) <= player.getDuration() && Math.floor(second) >= 0) {
		 	                    left = 100 * Math.floor(second) / player.getDuration();
		 	                    $('#marker-'+questionId).css({
		 	                        "width": "2px",
		 	                        "height": "10px",
		 	                        "left": left+"%",
		 	                        "position": "absolute",
		 	                        "background": "#FCD730"
		 	                    });
		 	                }
		 	 		
		 	 		for (i = 0; i < player.options.markers.length; ++i) {
		 	          if(player.options.markers[i][0]==questionId){
		 	        	  player.options.markers[i][1]=second;
		 	          }
		 	        }
		 	 		
		 	 		//Pausamos el video
		 	 		player.pause();
		 	 		//Posicionamos el video en el segundo.
		 	 		player.setCurrentTime(second);
		 	 		//console.log("finish");
		 	 		
		 	 			
		 		}
		 		
		 		
		 	}
		</script>				
		<div class="col-md-12">
			<div class="row header">
				<div class="col-md-8">
					<liferay-ui:message key="question" />
				</div>
				<div class="col-md-4">
					<liferay-ui:message key="second" />
				</div>
			</div>
			<%int second = 0;
			for(Question question: listQuestions){
				second = questionPositions.getInt(ResourceExternalConstants.JSON_SECOND + question.getQuestionId(), 0);
				%>
				<div class="row results-row">
					<div class="col-md-8"><span id="${renderResponse.getNamespace()}question_<%=question.getQuestionId()%>"><%=question.getText() %></div>
					<div class="col-md-4">
						<c:set var="questionId" value="<%=question.getQuestionId()%>" />
						<aui:input name="second_${questionId }" label="" value='<%=second %>'  onChange="${renderResponse.getNamespace()}changeTime('${questionId}',this.value);">
							<aui:validator name="number"/>
							<aui:validator name="min">"0"</aui:validator>
						</aui:input>
					</div>
				</div>
			<%} %>
		</div>
		<div class="col-md-6">
			<aui:input type="toggle-switch"  label="learning-activity.resource-external.question-feedback" name="questionFeedback" value="<%= resourceExternalActivityType != null ? resourceExternalActivityType.getQuestionFeedback() : ResourceExternalConstants.DEFAULT_QUESTION_FEEDBACK%>" />
		</div>
		<div class="col-md-6">
			<aui:input type="toggle-switch"  label="learning-activity.resource-external.final-feedback" name="finalFeedback" value="<%= resourceExternalActivityType != null ? resourceExternalActivityType.getFinalFeedback() : ResourceExternalConstants.DEFAULT_FINAL_FEEDBACK%>" />
		</div>
	<%}%>
</div>

<script>
	function <portlet:namespace/>changeCorrectMode(correctId){
		$('[name="<portlet:namespace/>passPuntuation"]').prop("disabled",true);
		$('#<portlet:namespace/>' + correctId).prop("disabled", false);
	}
	
	function <portlet:namespace/>showQuestionsVideo(){
		$('#<portlet:namespace/>questionsVideo').toggle("hide");
	}
	
	var videoContainer = document.getElementById("<portlet:namespace/>playervideo");
	var videoSource = document.getElementById("<portlet:namespace/>srcVideo");
	
	function <portlet:namespace/>changeVideo(){
		var video = $('#<portlet:namespace/>video').val();
		player.setSrc(video);
		player.pause();
	}
</script>