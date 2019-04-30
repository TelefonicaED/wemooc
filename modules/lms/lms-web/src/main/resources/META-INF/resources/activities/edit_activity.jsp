<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="com.liferay.portal.kernel.workflow.WorkflowConstants"%>
<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@page import="com.ted.lms.model.LearningActivity"%>
<%@ include file="../init.jsp" %>

<portlet:actionURL name="/activities/edit_activity" var="editActivityURL" />

<aui:form action="${editActivityURL}" enctype="multipart/form-data" method="post" name="fm" >

	<aui:model-context bean="${activity }" model="<%= LearningActivity.class %>" />

	<aui:input name="<%= Constants.CMD %>" value="${cmd }" type="hidden" />
	<aui:input name="moduleId" type="hidden" value="${moduleId}" />
	<aui:input name="actId" type="hidden" value="${actId}" />
	<aui:input name="type" type="hidden" value="${learningActivityTypeFactory.type}" />
	<aui:input name="workflowAction" type="hidden" value="<%= WorkflowConstants.ACTION_PUBLISH %>" />

	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<aui:input autoFocus="true" label="title" localized="true" name="titleMapAsXML" type="text" wrapperCssClass="activity-title" 
					value="${activity.title }">
					<aui:validator name="required" />
				</aui:input>
			</div>
		</div>
		<div class="row">	
			<div class="col-md-12">
				<div class="module-description">
					<label for="<portlet:namespace />descriptionMapAsXML"><liferay-ui:message key="description" /></label>
					<liferay-ui:input-localized
						cssClass="form-control"
						editorName="alloyeditor"
						formName="fm"
						name="descriptionMapAsXML"
						placeholder="description"
						type="editor"
						xml="${not empty activity ? activity.descriptionMapAsXML : '' }"
					/>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4">
				<aui:input name="required" label="activity-required" type="toggle-switch" value="${activity.required }"/>
			</div>
			<c:if test="${learningActivityTypeFactory.isTriesConfigurable() }">
					<div class="col-md-4">
						<div class="row">
							<div class="col-md-8">
								<aui:input name="triesToggle" label="num-max-tries" type="toggle-switch" value="${activity.tries > 0 }"/>
							</div>
							<div class="col-md-4">
								<aui:input name="tries" label="" type="text" disabled="${empty activity || activity.tries == 0 }" 
									value="${not empty activity && activity.tries > 0 ? activity.tries : '' }">
									<aui:validator name="required">
										function(){
											return AUI.$('#<portlet:namespace />triesToggle').prop("checked");
										}
									</aui:validator>
									<aui:validator name="number"/>
								</aui:input>
							</div>
						</div>
						<script>
							$('#<portlet:namespace />triesToggle').on(
								'change',
								function() {
									if(!$('#<portlet:namespace />triesToggle').prop('checked')){
										$('#<portlet:namespace />tries').val('');
									}else{
										$('#<portlet:namespace />tries').val('${learningActivityTypeFactory.defaultTries}');
									}
									$('#<portlet:namespace />tries').prop("disabled", !$('#<portlet:namespace />tries').prop("disabled"));
								}
							);
						</script>
					</div>
				</c:if>
				<c:if test="${learningActivityTypeFactory.isScoreConfigurable() }">
					<div class="col-md-4">
						<div class="row">
							<div class="col-md-6">
								<aui:input name="passPuntuationToggle" label="${learningActivityTypeFactory.scoreConfigurableProperty }" 
									type="toggle-switch" value="${activity.passPuntuation > 0 }" />
							</div>
							<div class="col-md-6">
								<aui:input name="passPuntuation" label="" type="text" suffix="%" disabled="${empty activity || activity.passPuntuation == 0 }"
									value="${not empty activity ? activity.passPuntuation : learningActivityTypeFactory.defaultScore }"/>
							</div>
						</div>
						<script>
							$('#<portlet:namespace />passPuntuationToggle').on(
								'change',
								function() {
									$('#<portlet:namespace />passPuntuation').prop("disabled", !$('#<portlet:namespace />passPuntuation').prop("disabled"));
								}
							);
						</script>
					</div>
				</c:if>
				<c:if test="${not empty learningActivityTypeFactory.getURLSpecificContent() }">
					<liferay-util:include page="${learningActivityTypeFactory.getURLSpecificContent()}" portletId="${learningActivityTypeFactory.getPortletId()}" >
						<liferay-util:param name="actId" value="${activity.actId }" />
						<liferay-util:param name="canBeEdited" value="${canBeEdited }" />
						<liferay-util:param name="groupId" value="${themeDisplay.scopeGroupId }" />
					</liferay-util:include>
				</c:if>	
		</div>
		<c:if test="${learningActivityTypeFactory.isFeedbackCorrectConfigurable() || learningActivityTypeFactory.isFeedbackNoCorrectConfigurable() }">
			<div class="row">
				<c:if test="${learningActivityTypeFactory.isFeedbackCorrectConfigurable() }">
					<div class="col-md-6">
						<aui:input name="feedbackCorrectToggle" label="learning-activity.feedback-correct" 
									type="toggle-switch" value="${not empty activity.feedbackCorrect}" />
						<div id="${renderResponse.getNamespace()}feedback_correct_wrapper" class="${empty activity || empty activity.feedbackCorrect ? 'hide' : ''}">
							<aui:input label="" localized="true" name="feedbackCorrectMapAsXML" type="textarea" value="${activity.feedbackCorrect }"
								required="${not empty activity.feedbackCorrect }"/>
						</div>
					</div>
					<script>
						$('#<portlet:namespace />feedbackCorrectToggle').on(
							'change',
							function() {
								$('#<portlet:namespace />feedback_correct_wrapper').toggleClass("hide");
								$('#<portlet:namespace />feedbackCorrectMapAsXML').prop("required", !$('#<portlet:namespace />feedbackCorrectMapAsXML').prop("required"));
							}
						);
					</script>
				</c:if>
				<c:if test="${learningActivityTypeFactory.isFeedbackNoCorrectConfigurable() }">
					<div class="col-md-6">
						<aui:input name="feedbackNoCorrectToggle" label="learning-activity.feedback-no-correct" 
									type="toggle-switch" value="${not empty activity.feedbackNoCorrect}" />
						<div id="${renderResponse.getNamespace()}feedback_no_correct_wrapper" class="${empty activity || empty activity.feedbackNoCorrect ? 'hide' : ''}">
							<aui:input label="" localized="true" name="feedbackNoCorrectMapAsXML" type="textarea" value="${activity.feedbackNoCorrect }"
								required="${not empty activity.feedbackNoCorrect }"/>
						</div>
					</div>
					<script>
						$('#<portlet:namespace />feedbackNoCorrectToggle').on(
							'change',
							function() {
								$('#<portlet:namespace />feedback_no_correct_wrapper').toggleClass("hide");
								$('#<portlet:namespace />feedbackNoCorrectMapAsXML').prop("required", !$('#<portlet:namespace />feedbackNoCorrectMapAsXML').prop("required"));
							}
						);
					</script>
				</c:if>
			</div>
		</c:if>
		<div class="sheet-section">
			<h3 class="sheet-subtitle"><liferay-ui:message key="restrictions" /></h3>
			<div class="row">
				<div class="date-course col-md-6" >
					<c:choose>
						<c:when test="${not empty module.startDate }">
							<liferay-ui:message key="start-date-module" />: ${module.getStartDateFormat(themeDisplay.locale, themeDisplay.timeZone) }
							<aui:input dateTogglerCheckboxLabel="use-start-date-module" 
								disabled="${empty activity || empty activity.startDate }" formName="fm" name="startDate" 
								value="${empty activity || empty activity.startDate ? module.startDateCalendar : activity.startDateCalendar }"/>
						</c:when>
						<c:otherwise>
							<liferay-ui:message key="start-date-course" />: ${course.getExecutionStartDateFormat(themeDisplay.locale, themeDisplay.timeZone) }
							<aui:input dateTogglerCheckboxLabel="use-start-execution-date-course" 
								disabled="${empty activity || empty activity.startDate }" formName="fm" name="startDate" 
								value="${empty activity || empty activity.startDate ? course.executionStartDateCalendar : activity.startDateCalendar }"/>
						</c:otherwise>
					</c:choose>
				</div>
	
				<div class="date-course col-md-6">
					<c:choose>
						<c:when test="${not empty module.endDate }">
							<liferay-ui:message key="end-date-module" />: ${module.getEndDateFormat(themeDisplay.locale, themeDisplay.timeZone) }
							<aui:input dateTogglerCheckboxLabel="use-end-date-module" 
									disabled="${empty activity || empty activity.endDate }" formName="fm" name="endDate" 
									value="${empty activity || empty activity.endDate ? module.endDateCalendar : activity.endDateCalendar }"/>
						</c:when>
						<c:otherwise>
							<liferay-ui:message key="end-date-course" />: ${course.getExecutionEndDateFormat(themeDisplay.locale, themeDisplay.timeZone) }
							<aui:input dateTogglerCheckboxLabel="use-end-execution-date-course" 
									disabled="${empty activity || empty activity.endDate }" formName="fm" name="endDate" 
									value="${empty activity || empty activity.endDate ? course.executionEndDateCalendar : activity.endDateCalendar }"/>
						</c:otherwise>
					</c:choose>
				</div>
				<c:forEach items="${listPrerequisiteFactory }" var="prerequisiteFactory">
					<div class="col-md-6">
						<liferay-util:include page="${prerequisiteFactory.getURLSpecificContent()}" portletId="${prerequisiteFactory.getPortletId()}" >
							<liferay-util:param name="classNameId" value="<%=String.valueOf(PortalUtil.getClassNameId(LearningActivity.class)) %>" />
							<liferay-util:param name="classPK" value="${actId }" />
							<liferay-util:param name="groupId" value="${themeDisplay.scopeGroupId }" />
						</liferay-util:include>
					</div>
				</c:forEach>
			</div>
		</div>
		<div class="sheet-section">
			<h3 class="sheet-subtitle"><liferay-ui:message key="categorization" /></h3>
			<div class="row">
				<div class="col-md-6">
					<aui:field-wrapper label="tags">
						<liferay-ui:asset-tags-selector className="<%=LearningActivity.class.getName() %>" classPK="${activity.actId }" />
					</aui:field-wrapper>
				</div>
				<div class="col-md-6">
					<liferay-ui:asset-categories-selector className="<%=LearningActivity.class.getName() %>" classPK="${activity.actId }" />
				</div>
			</div>
		</div>
	</div>
	<aui:button-row>
		<aui:button name="saveButton" type="submit" value="save" />
		<script>
			$('#<portlet:namespace />saveButton').on(
				'click',
				function() {
					$('#<portlet:namespace />workflowAction').val('<%= WorkflowConstants.ACTION_SAVE_DRAFT %>');
				}
			);
		</script>
		<aui:button name="saveButtonAndView" type="submit-and-view" value="save" />
		<script>
			$('#<portlet:namespace />saveButtonAndView').on(
				'click',
				function() {
					$('#<portlet:namespace />workflowAction').val('<%= WorkflowConstants.ACTION_PUBLISH %>');
				}
			);
		</script>
	</aui:button-row>
</aui:form>