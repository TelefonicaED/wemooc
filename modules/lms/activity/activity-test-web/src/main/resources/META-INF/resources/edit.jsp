<%@page import="com.ted.lms.registry.LearningActivityTypeFactoryRegistryUtil"%>
<%@page import="com.ted.lms.learning.activity.question.constants.QuestionConstants"%>
<%@page import="com.ted.lms.learning.activity.test.web.activity.TestActivityType"%>
<%@page import="com.ted.lms.learning.activity.test.web.activity.TestActivityTypeFactory"%>
<%@page import="com.ted.lms.learning.activity.question.constants.QuestionsWebPortletKeys"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.ted.lms.learning.activity.test.web.util.TestPrefsPropsValues"%>
<%@page import="com.ted.lms.learning.activity.test.web.constants.TestConstants"%>
<%@page import="com.ted.lms.model.LearningActivity"%>
<%@page import="com.ted.lms.service.LearningActivityLocalServiceUtil"%>
<%@page import="com.ted.lms.model.LearningActivityType"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ include file="init.jsp" %>

<%long actId = ParamUtil.getLong(request, "actId", 0);
boolean canBeEdited = ParamUtil.getBoolean(request, "canBeEdited", true);

TestActivityType testActivityType = null;
LearningActivity learningActivity = null;

if(actId > 0){
	TestActivityTypeFactory testActivityTypeFactory = (TestActivityTypeFactory)LearningActivityTypeFactoryRegistryUtil.getLearningActivityTypeFactoryByType(TestConstants.TYPE);
	learningActivity = LearningActivityLocalServiceUtil.getLearningActivity(actId);
	testActivityType = testActivityTypeFactory.getTestActivityType(learningActivity);
}
NumberFormat timeNumberFormat = NumberFormat.getInstance(themeDisplay.getLocale());
timeNumberFormat.setMinimumIntegerDigits(2);
%>

<div class="col-md-6">
	<div class="row">
		<div class="col-md-6">
			<aui:input name="randomToggle" label="learning-activity.test.random" 
				type="toggle-switch" value="<%=testActivityType != null && testActivityType.getRandom() > 0 %>" 
				ignoreRequestValue="true" helpMessage="learning-activity.test.random.help-message"/>
			
		</div>
		<div class="col-md-6">
			<aui:input name="random" label="" type="text" disabled="<%=testActivityType == null || testActivityType.getRandom() == 0 %>"
				value='<%=testActivityType != null && testActivityType.getRandom() > 0 ? testActivityType.getRandom() : "" %>' size="3" >
				<aui:validator name="required">
					function(){
						return AUI.$('#<portlet:namespace />randomToggle').prop("checked");
					}
				</aui:validator>
				<aui:validator name="number"/>
				<aui:validator errorMessage="<%=LanguageUtil.format(request, "please-enter-a-value-between-x-and-x", new Object[]{1,100}) %>" name="custom">
					function(value) {
						if(AUI.$('#<portlet:namespace />randomToggle').prop('checked') && value != ''){
							return (value >= 1) && (value <= 100);
						}
						return true;
					}
				</aui:validator>
			</aui:input>
		</div>
	</div>
</div>
<script>
	$('#<portlet:namespace />randomToggle').on(
		'change',
		function() {
			if(!$('#<portlet:namespace />randomToggle').prop('checked')){
				$('#<portlet:namespace />random').val('');
			}else{
				$('#<portlet:namespace />random').val('0');
			}
			$('#<portlet:namespace />random').prop("disabled", !$('#<portlet:namespace />random').prop("disabled"));
		}
	);
</script>
<div class="col-md-6">
	<div class="row">
		<div class="col-md-6">
			<aui:input name="questionsPerPageToggle" label="learning-activity.test.questions-per-page" 
				type="toggle-switch" value="<%=testActivityType != null && testActivityType.getQuestionsPerPage() > 0 %>" 
				ignoreRequestValue="true" helpMessage="learning-activity.test.questions-per-page.helpMessage"/>
		</div>
		<div class="col-md-6">
			<aui:input name="questionsPerPage" label="" type="text" disabled="<%=testActivityType == null || testActivityType.getQuestionsPerPage() == 0 %>" size="3" 
				value='<%=testActivityType != null && testActivityType.getQuestionsPerPage() > 0 ? testActivityType.getQuestionsPerPage() : ""%>'>
				<aui:validator name="required">
					function(){
						return AUI.$('#<portlet:namespace />questionsPerPageToggle').prop("checked");
					}
				</aui:validator>
				<aui:validator name="number"/>
				<aui:validator name="range">
					[1,100]
				</aui:validator>
			</aui:input>
		</div>
	</div>
</div>
<script>
	$('#<portlet:namespace />questionsPerPageToggle').on(
		'change',
		function() {
			if(!$('#<portlet:namespace />questionsPerPageToggle').prop('checked')){
				$('#<portlet:namespace />questionsPerPage').val('');
			}else{
				$('#<portlet:namespace />questionsPerPage').val('<%=TestPrefsPropsValues.getQuestionPerPage(themeDisplay.getCompanyId())%>');
			}
			$('#<portlet:namespace />questionsPerPage').prop("disabled", !$('#<portlet:namespace />questionsPerPage').prop("disabled"));
		}
	);
</script>
<div class="col-md-6">
	<div class="row">
		<div class="col-md-6">
			<aui:input name="passwordToggle" label="learning-activity.test.password" 
				type="toggle-switch" value="<%=testActivityType != null && Validator.isNotNull(testActivityType.getPassword()) %>" 
				ignoreRequestValue="true" helpMessage="learning-activity.test.password.help-message"/>
		</div>
		<div class="col-md-4">
			<aui:input name="password" label="" type="text" disabled="<%=testActivityType == null || Validator.isNull(testActivityType.getPassword())%>"
				value="<%=testActivityType != null ? testActivityType.getPassword() : TestConstants.DEFAULT_PASSWORD%>">
				<aui:validator name="required" >
					function(){
						return AUI.$('#<portlet:namespace />passwordToggle').prop("checked");
					}
				</aui:validator>
			</aui:input>
		</div>
	</div>
</div>
<script>
	$('#<portlet:namespace />passwordToggle').on(
		'change',
		function() {
			if(!$('#<portlet:namespace />passwordToggle').prop('checked')){
				$('#<portlet:namespace />password').val('');
			}
			$('#<portlet:namespace />password').prop("disabled", !$('#<portlet:namespace />password').prop("disabled"));
		}
	);
</script>
<div class="col-md-6">
	<div class="row">
		<div class="col-md-5">
			<aui:input name="timeStampToggle" label="learning-activity.test.time-stamp" 
				type="toggle-switch" value="<%=testActivityType != null && testActivityType.getTimeStamp() > 0 %>" 
				ignoreRequestValue="true" helpMessage="learning-activity.test.time-stamp.help-message"/>
		</div>
		<div class="col-md-7 time-stamp">
			<div class="row">
				<aui:select name="testHourDuration" label="" disabled="<%=testActivityType == null || testActivityType.getTimeStamp() == 0%>">
					<c:if test="<%=testActivityType == null || testActivityType.getTimeStamp() == 0%>">
						<aui:option label="hh" value="" disabled="true"/>
					</c:if>
					<%for(int i = 0; i < 24; i++){ %>
						<aui:option label="<%=timeNumberFormat.format(i) %>" value="<%=i %>" selected="<%=testActivityType != null && testActivityType.getHourDuration() == i %>" />
					<%} %>
				</aui:select>
				<aui:select name="testMinuteDuration" label="" disabled="<%=testActivityType == null || testActivityType.getTimeStamp() == 0%>">
					<c:if test="<%=testActivityType == null || testActivityType.getTimeStamp() == 0%>">
						<aui:option label="mm" value="" disabled="true"/>
					</c:if>
					<%for(int i = 0; i < 60; i++){ %>
						<aui:option label="<%=timeNumberFormat.format(i) %>" value="<%=i %>" selected="<%=testActivityType != null && testActivityType.getMinuteDuration() == i %>" />
					<%} %>
				</aui:select>
				<aui:select name="testSecondDuration" label="" disabled="<%=testActivityType == null || testActivityType.getTimeStamp() == 0%>">
					<c:if test="<%=testActivityType == null || testActivityType.getTimeStamp() == 0%>">
						<aui:option label="ss" value="" disabled="true"/>
					</c:if>
					<%for(int i = 0; i < 60; i++){ %>
						<aui:option label="<%=timeNumberFormat.format(i) %>" value="<%=i %>" selected="<%=testActivityType != null && testActivityType.getSecondDuration() == i %>" />
					<%} %>
				</aui:select>
			</div>
		</div>
	</div>
</div>
<script>
	$('#<portlet:namespace />timeStampToggle').on(
		'change',
		function() {
			$('#<portlet:namespace />testHourDuration').prop("disabled", !$('#<portlet:namespace />testHourDuration').prop("disabled"));
			$('#<portlet:namespace />testMinuteDuration').prop("disabled", !$('#<portlet:namespace />testMinuteDuration').prop("disabled"));
			$('#<portlet:namespace />testSecondDuration').prop("disabled", !$('#<portlet:namespace />testSecondDuration').prop("disabled"));
		}
	);
</script>
<div class="col-md-4">
	<aui:input name="showFeedback" label="learning-activity.test.show-feedback" 
				type="toggle-switch" value="<%=(testActivityType != null && testActivityType.getShowFeedback()) || QuestionConstants.DEFAULT_SHOW_FEEDBACK%>" 
				ignoreRequestValue="true" helpMessage="learning-activity.test.show-feedback.help-message"/>
</div>
<div class="col-md-4">
	<aui:input name="showCorrectAnswer" label="learning-activity.test.show-correct-answer" 
				type="toggle-switch" value="<%=testActivityType != null && testActivityType.getShowCorrectAnswer() %>" 
				ignoreRequestValue="true" helpMessage="learning-activity.test.show-correct-answer.help-message"
				disabled="<%=(testActivityType == null && !QuestionConstants.DEFAULT_SHOW_FEEDBACK) || (testActivityType != null && !testActivityType.getShowFeedback()) %>"/>
</div>
<div class="col-md-4">
	<aui:input name="showCorrectAnswerOnlyOnFinalTry" label="learning-activity.test.show-correct-answer-only-on-final-try" 
				type="toggle-switch" value="<%=testActivityType != null && testActivityType.getShowCorrectAnswerOnlyOnFinalTry() %>" 
				ignoreRequestValue="true" helpMessage="learning-activity.test.show-correct-answer-only-on-final-try.help-message"
				disabled="<%=learningActivity == null || learningActivity.getTries() == 0 || testActivityType == null || !testActivityType.getShowCorrectAnswer() %>"/>
</div>
<div class="col-md-4">
	<aui:input name="improve" label="learning-activity.test.improve" 
				type="toggle-switch" value="<%=testActivityType != null && testActivityType.getImprove() %>" 
				ignoreRequestValue="true" helpMessage="learning-activity.test.improve.help-message"/>
</div>
<div class="col-md-4">
	<aui:input name="preview" label="learning-activity.test.preview" 
				type="toggle-switch" value="<%=testActivityType != null && testActivityType.getPreview() %>" 
				ignoreRequestValue="true" helpMessage="learning-activity.test.preview.help-message"/>
</div>
<script>
	$('#<portlet:namespace />triesToggle').on(
		'change',
		function() {
			alert("tries: " + $('#<portlet:namespace />triesToggle').prop("checked") + " - " + $('#<portlet:namespace />showFeedback').prop("checked") + " - " + $('#<portlet:namespace />showCorrectAnswer').prop("checked"));
			if(!$('#<portlet:namespace />triesToggle').prop("checked")){
				$('#<portlet:namespace />showCorrectAnswerOnlyOnFinalTry').prop("disabled",true);
				$('#<portlet:namespace />showCorrectAnswerOnlyOnFinalTry').prop("checked",false);
			}else if($('#<portlet:namespace />showFeedback').prop("checked") && $('#<portlet:namespace />showCorrectAnswer').prop("checked")){
				$('#<portlet:namespace />showCorrectAnswerOnlyOnFinalTry').prop("disabled",false);
				$('#<portlet:namespace />showCorrectAnswerOnlyOnFinalTry').removeClass("disabled");
;			}
		}
	);
	$('#<portlet:namespace />showFeedback').on(
		'change',
		function() {
			if(!$('#<portlet:namespace />showFeedback').prop("checked")){
				$('#<portlet:namespace />showCorrectAnswer').prop("disabled",true);
				$('#<portlet:namespace />showCorrectAnswer').prop("checked",false);
				$('#<portlet:namespace />showCorrectAnswerOnlyOnFinalTry').prop("disabled",true);
				$('#<portlet:namespace />showCorrectAnswerOnlyOnFinalTry').prop("checked",false);
			}else{
				$('#<portlet:namespace />showCorrectAnswer').prop("disabled",true);
				$('#<portlet:namespace />showCorrectAnswer').removeClass("disabled");
				if($('#<portlet:namespace />showCorrectAnswer').prop("checked")){
					$('#<portlet:namespace />showCorrectAnswerOnlyOnFinalTry').prop("disabled",false);
					$('#<portlet:namespace />showCorrectAnswerOnlyOnFinalTry').removeClass("disabled");
				}
			}
		}
	);
	$('#<portlet:namespace />showCorrectAnswer').on(
		'change',
		function() {
			if($('#<portlet:namespace />showCorrectAnswer').prop("checked") && $('#<portlet:namespace />triesToggle').prop("checked")){
				$('#<portlet:namespace />showCorrectAnswerOnlyOnFinalTry').prop("disabled",false);
				$('#<portlet:namespace />showCorrectAnswerOnlyOnFinalTry').removeClass("disabled");
			}else{
				$('#<portlet:namespace />showCorrectAnswerOnlyOnFinalTry').prop("disabled",true);
				$('#<portlet:namespace />showCorrectAnswerOnlyOnFinalTry').prop("checked",false);
			}
		}
	);
</script>
<div class="col-md-12 activity-test-questions" >
	<liferay-util:include page="<%=QuestionsWebPortletKeys.EDIT_QUESTIONS_JSP %>" portletId="<%=QuestionsWebPortletKeys.EDIT_QUESTIONS%>" >
		<liferay-util:param name="actId" value="<%=String.valueOf(actId)%>" />
		<liferay-util:param name="canBeEdited" value="<%=String.valueOf(canBeEdited)%>" />
		<liferay-util:param name="questionIdsAllowed" value="<%=TestPrefsPropsValues.getQuestionsAllowed(themeDisplay.getCompanyId())%>" />
	</liferay-util:include>
</div>