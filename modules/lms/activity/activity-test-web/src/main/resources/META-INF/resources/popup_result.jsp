<div id="${renderResponse.namespace }activityResult" class="hide">
	<c:choose>
		<c:when test="${!userPassed && hasFreeQuestion }">
			<b><liferay-ui:message key="learning-activity.question.free-text.correct-teacher" /></b>
		</c:when>
		<c:otherwise>
		
			<c:if test="${userPassed }">
				<div id="actfeedback">${activity.feedbackCorrect}</div>
			</c:if>
			<c:if test="${!userPassed }">
				<div id="actfeedback">${activity.feedbackNoCorrect}</div>
			</c:if>
			<div id="score">
				<b><liferay-ui:message key="learning-activity.test.shared" arguments="${calificationType.translate(themeDisplay.locale, scoreTry) }"/></b>
			</div>
		
		</c:otherwise>
	</c:choose>
</div>
<aui:script use="liferay-util-window">
	$(document).ready(function() {
		var dialog = Liferay.Util.Window.getWindow(
			{
				dialog: {
					modal: true,
					resizable: false,
					width: "500px",
					heigth: "280px",
					centered: true,
					bodyContent: $('#<portlet:namespace />activityResult').html()
				},
				title: Liferay.Language.get('learning-activity.test.passed')
			}
		).render();
	});
</aui:script>