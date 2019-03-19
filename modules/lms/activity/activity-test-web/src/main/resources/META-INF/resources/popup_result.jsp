<%@ include file="init.jsp" %>

<c:choose>
	<c:when test="${userPassed }">
		<div id="${renderResponse.namespace }activityResult" class="hide">
			<h2><liferay-ui:message key="test-superado" /></h2>
			<div id="actfeedback">${activity.feedbackNoCorrect}</div>
			<div id="score">
				<b><liferay-ui:message key="shared-you-guess" /> ${calificationType.translate(themeDisplay.locale, score) } <liferay-ui:message key="shared-in-tarea" /></b>
			</div>
		</div>
		
		<script type="text/javascript">
			window.<portlet:namespace />resultPopup = Liferay.Util.Window.getWindow(
					{
						dialog: {
							modal: true,
							resizable: false,
							width: "500px",
							heigth: "280px",
							centered: true,
							bodyContent: <portlet:namespace />}activityResult
						}
					}
				).render();
		</script>
	</c:when>
	<c:otherwise>
		<div id="${renderResponse.namespace }activityResult" class="hide">
			<h2><liferay-ui:message key="test-superado" /></h2>
			
			<c:choose>
				<c:when test="${hasFreeQuestion }">
					<b><liferay-ui:message key="freetext.result.body" /></b>
				</c:when>
				<c:otherwise>
						
					<div id="actfeedback">${activity.feedbackNoCorrect}</div>
					<div id="score">
						<b><liferay-ui:message key="shared-you-guess" /> ${calificationType.translate(themeDisplay.locale, scoreTry) } <liferay-ui:message key="shared-in-tarea" /></b>
					</div>
				
				</c:otherwise>
			</c:choose>
		</div>
		<script type="text/javascript">
			window.<portlet:namespace />resultPopup = Liferay.Util.Window.getWindow(
				{
					dialog: {
						modal: true,
						resizable: false,
						width: "500px",
						heigth: "280px",
						centered: true,
						bodyContent: <portlet:namespace />}activityResult
					}
				}
			).render();
		</script>
 	</c:otherwise>
 </c:choose>