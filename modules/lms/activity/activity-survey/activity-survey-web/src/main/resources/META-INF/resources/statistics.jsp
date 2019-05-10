<%@page import="java.text.DecimalFormat"%>
<%@page import="com.liferay.portal.kernel.util.HtmlUtil"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.ted.lms.learning.activity.question.service.AnswerLocalServiceUtil"%>
<%@page import="com.ted.lms.learning.activity.question.model.Answer"%>
<%@page import="com.ted.lms.learning.activity.survey.service.SurveyResultLocalServiceUtil"%>
<%@page import="com.ted.lms.learning.activity.question.model.Question"%>
<%@page import="java.util.List"%>
<%@ include file="init.jsp" %>

<liferay-ui:header
	backURL="${backURL }"
	title="learning-activity.survey.stadistics"
/>

<%List<Question> questions = (List<Question>)request.getAttribute("questions"); 
DecimalFormat df = (DecimalFormat)request.getAttribute("df");%>

<div id="${renderResponse.getNamespace()}generate_report" class="rightButton">
	<liferay-ui:icon image="export" label="<%= true %>" message="download-content" method="get" url="javascript:${renderResponse.getNamespace()}exportReport();" />
</div>
<div id="${renderResponse.getNamespace()}generating_report" class="hide">
	<div class="message_generating_report"><liferay-ui:message key="generatingreport"/></div>
</div>
<div id="${renderResponse.getNamespace()}download_report" class="hide"></div>

<span><liferay-ui:message key="learning-activity.survey.statistics.participants" />${participants }</span>	
<span><liferay-ui:message key="learning-activity.survey.statistics.participation-rate" />${percent}%</span>
	
<%long totalQuestion = 0;
List<Answer> answers = null;
String answerText = StringPool.BLANK;
long totalAnswer = 0;
String percent = StringPool.BLANK;

for(Question question: questions){ 
	totalQuestion = SurveyResultLocalServiceUtil.countAnswerByQuestionId(question.getQuestionId(), themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());%>	
	<div class="question">
		<div  class="questiontext">
			<p><%=question.getText() %></p>
		</div>
		<span><liferay-ui:message key="learning-activity.survey.statistics.total" />: <%= totalQuestion %></span>		
		<%answers = AnswerLocalServiceUtil.getAnswersByQuestionId(question.getQuestionId()); %>
		
		<c:choose>
			<c:when test="<%=answers != null && answers.size() > 0 %>">
				<%for(Answer answer: answers){
					answerText = HtmlUtil.extractText(answer.getAnswer());
					answerText = answerText.length() > 50 ? answerText.substring(0, 50) + "..." : answerText;
					totalAnswer = SurveyResultLocalServiceUtil.countAnswerByQuestionIdAnswerId(question.getQuestionId(), answer.getAnswerId(), themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
					percent = totalQuestion > 0 ? df.format(100*(double)totalAnswer/(double)totalQuestion) : "0";%>
					
					<div class="answer">
						<%=answerText %>
						<span><liferay-ui:message key="learning-activity.survey.statistics.answers" /> <%=totalAnswer %> (<%=percent %>%)</span>
					</div>
					
				<%} %>
			</c:when>
			<c:otherwise>
				<div class="answer">
						<p><liferay-ui:message key="learning-activity.survey.statistics.no-answers" /></p>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
<%}%>

<script>
	

function <portlet:namespace/>exportReport(){

	$('#<portlet:namespace />generating_report').removeClass("hide");
	$('#<portlet:namespace />generate_report').addClass("hide");
	var exportResourceURL = "${renderURL}";
	var action ="stadisticsReport";

	$.ajax({
		dataType: 'json',
		url:'${statisticsReportURL}',
	    cache:false,
		success: function(data){
			if(data && data.backgroundTaskId > 0){
				console.log("backgroundTaskId: " + data.backgroundTaskId);
				$('#<portlet:namespace />download_report').addClass("hide");
				$('#<portlet:namespace />generate_report').addClass("hide");
				$('#<portlet:namespace />generating_report').removeClass("hide");
				<portlet:namespace />readThreadState(data.backgroundTaskId);
			}else{
				alert("Error generando el archivo");
				$('#<portlet:namespace />generating_report').addClass("hide");
				$('#<portlet:namespace />generate_report').removeClass("hide");
			}
		},
		error: function(){
			alert("Error al generar el archivo");
			$('#<portlet:namespace />generating_report').addClass("hide");
			$('#<portlet:namespace />generate_report').removeClass("hide");
		}
	});
}

function <portlet:namespace />readThreadState(backgroundTaskId){
	console.log("backgroundTaskId thread: " + backgroundTaskId);
	if(backgroundTaskId > 0){
		
		var data = Liferay.Util.ns(
			'<portlet:namespace />',
			{
				backgroundTaskId: backgroundTaskId
			}
		);
		
		$.ajax({
			dataType: 'json',
			url: '${statisticsReportURL}',
		    cache:false,
			data: data,
			success: function(data){
				if(data){						
			    	if(!data.finished){		
			    		$('#<portlet:namespace />generating_report').removeClass("hide");
			    		$('#<portlet:namespace />download_report').addClass("hide");
			    		setTimeout(<portlet:namespace />readThreadState,2000, backgroundTaskId);
			    		
			    	}else{	
			    		console.log("hemos terminado, el fichero es: " + data.fileURL);
			    		$('#<portlet:namespace />download_report').empty();
						$('#<portlet:namespace />generating_report').addClass("hide");
						var downloadReport = Liferay.Language.get('download-report');
						$('#<portlet:namespace />download_report').append('<a href="' + data.fileURL + '" >'+downloadReport+'</a>');
						$('#<portlet:namespace />download_report').removeClass("hide");
			    	}
				}else{
					alert("Error en el readThreadState");
				}
			},
			error: function(){		
				$('#<portlet:namespace />generating_report').addClass("hide");
	    		$('#<portlet:namespace />generate_report').removeClass("hide");
			}
		});		
	}
}	

</script>