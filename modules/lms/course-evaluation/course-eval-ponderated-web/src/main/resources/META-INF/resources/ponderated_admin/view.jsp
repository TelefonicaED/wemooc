<%@ include file="../init.jsp" %>

<portlet:actionURL var="savePonderationURL" name="savePonderation" />

<aui:form name="fm" action="${savePonderationURL}"  method="post">
	<aui:input size="5" name="passPuntuation" label="course-eval.pass-puntuation" type="text" value="${passPuntuation }" >
		<aui:validator name="required"></aui:validator>
		<aui:validator name="number"></aui:validator>
	</aui:input>
	
	<liferay-ui:panel-container >
		<c:forEach items="${modules }" var="module">
			<liferay-ui:panel id="${moduleId }" title="${module.getTitle(themeDisplay.locale)}" collapsible="true" extended="true" defaultState="collapsed">
				<liferay-ui:search-container  emptyResultsMessage="there-are-no-results" delta="500" deltaConfigurable="false">
					<liferay-ui:search-container-results results="${module.requiredLearningActivities }" >
					</liferay-ui:search-container-results>
					<liferay-ui:search-container-row className="com.ted.lms.model.LearningActivity" keyProperty="actId" modelVar="learningActivity">

						<liferay-ui:search-container-column-text cssClass="number-column" name = "activity">
							${learningActivity.getTitle(themeDisplay.getLocale())}
						</liferay-ui:search-container-column-text>
						<liferay-ui:search-container-column-text cssClass="number-column" name = "course_eval.ponderated.weight">
							<aui:input size="5"  name="weight_${learningActivity.actId}" value="${weights.containsKey(learningActivity.actId) ? weights.get(learningActivity.actId) : ''} " label="">
								<aui:validator name="digits"></aui:validator>
							</aui:input>
						</liferay-ui:search-container-column-text>
						<liferay-ui:search-container-column-text cssClass="number-column" name = "course_eval.ponderated.required">
							<aui:input type="checkbox" name="required_${learningActivity.actId }" label="" value="${required.contains(learningActivity.actId) }" />
						</liferay-ui:search-container-column-text>
					</liferay-ui:search-container-row>
					<liferay-ui:search-iterator></liferay-ui:search-iterator>
				</liferay-ui:search-container>
			
			</liferay-ui:panel>
		</c:forEach>
	</liferay-ui:panel-container>
	<aui:button-row>
		<aui:button type="submit"></aui:button>							
	</aui:button-row>
</aui:form>