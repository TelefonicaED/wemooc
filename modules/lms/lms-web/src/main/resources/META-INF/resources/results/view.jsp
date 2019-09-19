<%@ include file="../init.jsp" %>

<c:if test="${not empty redirect }">
	<liferay-ui:header title="" backLabel="back" backURL="${redirect }" />
</c:if>

<c:if test="${not empty student}">
	<h3>${student }</h3>
</c:if>

<div class="course-result">
	<h4><liferay-ui:message key="results.result-course"/></h4>
	<c:choose>
		<c:when test="${courseResult.finished }">
			${calificationType.translate(themeDisplay.locale, courseResult.result).concat(calificationType.suffix) }
		</c:when>
		<c:otherwise>
			<liferay-ui:message key="results.result-course.pending"/>
		</c:otherwise>
	</c:choose>
</div>
<div class="course-status">
	<h4><liferay-ui:message key="results.status"/></h4>
	${courseResult.getCourseStatus(themeDisplay.locale) }
</div>
<liferay-ui:panel-container id="modules">
	<c:forEach items="${modules }" var="module">
		<c:set var="moduleResult" value="${moduleResultLocalService.getModuleResult(module.moduleId, studentId)}" />

		<div class="panel panel-default  lfr-panel lfr-panel-extended" >
			<c:choose>
				<c:when test="${!module.isLocked(themeDisplay.userId, themeDisplay.permissionChecker) }">
					<div class="panel-heading toggler-header toggler-header-expanded" data-toggle="collapse" data-target="#${renderResponse.namespace }module_${module.moduleId}Content"
						id="${renderResponse.namespace }module_${module.moduleId}">
						<div class="panel-toggle" >
							<p>${module.getTitle(themeDisplay.locale) }</p>
							<p>
								<liferay-ui:message key="results.module-result"/>
								<c:choose>
									<c:when test="${moduleResult.finished }">
										${calificationType.translate(themeDisplay.locale, moduleResult.result).concat(calificationType.suffix) }
									</c:when>
									<c:otherwise>
										<liferay-ui:message key="results.result-course.pending" />
									</c:otherwise>
								</c:choose>
							</p>
							<p>
								<liferay-ui:message key="results.status"/>
								<c:choose>
									<c:when test="${not empty moduleResult }">
										${moduleResult.getModuleStatus(themeDisplay.locale) }
									</c:when>
									<c:otherwise>
										<liferay-ui:message key="results.status.not-started" />
									</c:otherwise>
								</c:choose>
							</p>
						</div>
						<span class="glyphicon glyphicon-triangle-bottom" id="${renderResponse.namespace }span_module_${module.moduleId}"></span>
					</div>
					<div class="toggler-content-wrapper panel-collapse collapse" id="${renderResponse.namespace }module_${module.moduleId}Content">
						<div class="toggler-content toggler-content-expanded" >
							<div class="panel-body">
								<c:set var="activities" value="${learningActivityService.getActivities(module.moduleId) }" />
								<div class="activity-result">
									<div class="header row">
										<div class="col-md-7"><liferay-ui:message key="activity" /></div>
										<div class="col-md-2"><liferay-ui:message key="result" /></div>
										<div class="col-md-2"><liferay-ui:message key="status" /></div>
										<div class="col-md-1"></div>
									</div>
									<c:forEach items="${activities}" var="activity">
										<c:set var="activityResult" value="${learningActivityResultLocalService.getLearningActivityResult(activity.actId, studentId) }" />
										<div class="row">
											<div class="col-md-7">
												${activity.getTitle(themeDisplay.locale) }
											</div>
											<div class="col-md-2">
												<c:choose>
													<c:when test="${not empty activityResult && activityResult.finished }">
														${calificationType.translate(themeDisplay.locale, activityResult.result).concat(calificationType.suffix) }
													</c:when>
													<c:otherwise>
														<liferay-ui:message key="results.result-course.pending" />
													</c:otherwise>
												</c:choose>
											</div>
											<div class="col-md-2">
												<c:choose>
													<c:when test="${not empty activityResult }">
														${activityResult.getActivityStatus(themeDisplay.locale) }
													</c:when>
													<c:otherwise>
														<liferay-ui:message key="results.status.not-started" />
													</c:otherwise>
												</c:choose>
											</div>
											<div class="col-md-1">
												<c:if test="${not empty activityResult.comments}">
													<span class="glyphicon glyphicon-comment" data-toggle="tooltip" data-html="true" title="${activityResult.comments }">
													</span>
													
												</c:if>
											</div>
										</div>
									</c:forEach>
								</div>
							</div>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="panel-heading" >
						<div class="panel-toggle" >
							<p>${module.getTitle(themeDisplay.locale) }</p>
							<p>
								<liferay-ui:message key="results.module-result"/>
								<c:choose>
									<c:when test="${moduleResult.finished }">
										${calificationType.translate(themeDisplay.locale, moduleResult.result).concat(calificationType.suffix) }
									</c:when>
									<c:otherwise>
										<liferay-ui:message key="results.result-course.pending" />
									</c:otherwise>
								</c:choose>
							</p>
							<p>
								<liferay-ui:message key="results.status"/>
								<c:choose>
									<c:when test="${not empty moduleResult }">
										${moduleResult.getModuleStatus(themeDisplay.locale) }
									</c:when>
									<c:otherwise>
										<liferay-ui:message key="results.status.not-started" />
									</c:otherwise>
								</c:choose>
							</p>
						</div>
						<span class="glyphicon glyphicon-lock"></span>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
		<script>
			$( document ).ready(function() {
			  	$('#<portlet:namespace />module_${module.moduleId}').on('click', function() {
			    	$('#<portlet:namespace />span_module_${module.moduleId}').toggleClass('glyphicon-triangle-top glyphicon-triangle-bottom');
			  });
			});
		</script>
	</c:forEach>
</liferay-ui:panel-container>


