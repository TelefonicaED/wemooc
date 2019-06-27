<%@ include file="../init.jsp" %>

<c:choose>
	<c:when test="${not empty inscriptionPortletName }">
		<liferay-portlet:runtime portletName="${inscriptionPortletName }" />
	</c:when>
	<c:otherwise>
		<liferay-ui:success message="inscription.enroll.success" key="inscription-ok" />
		<liferay-ui:success message="inscription.unsusbscribe.success" key="unsusbscribe" />
		<liferay-ui:error message="inscription.enroll.error.already-enrolled" key="inscription-error-already-enrolled" />
		<liferay-ui:error message="inscription.enroll.error.error-max-users" key="inscription-error-max-users" />
		<liferay-ui:error message="inscription.unsusbscribe.error.already-unsusbscribe" key="unsusbscribe" />
		
		<div class="container">
			<c:choose>
				<c:when test="${themeDisplay.isSignedIn() && registredUser}">
					<div class="msg-info">
						<liferay-ui:message key="inscription.enrolled" />
					</div>
					<c:if test="${not empty listChildCourses }">	
						<c:forEach items="${listChildCourses }" var="childCourse">
							<div class="row">
								<span class="edition-name">${childCourse.getTitle(locale)}</span>
								<aui:button type="button" value="inscription.go-to-course" href="${childCourse.friendlyURL}"/>
								<c:if test="${childCourse.canUnsubscribe(themeDisplay.userId, themeDisplay.permissionChecker) }">
									<div class="button ">
										<a href="#" onclick="javascript:<portlet:namespace/>unsubscribe(${childCourse.courseId })"><liferay-ui:message key="inscription.unsusbscribe" /></a>
									</div>
								</c:if>
							</div>
						</c:forEach>
					</c:if>
					<c:if test="${not empty course}">
						<c:if test="${course.canUnsubscribe(themeDisplay.userId, themeDisplay.permissionChecker)}">
							<div class="button ">
								<a href="#" onclick="javascript:<portlet:namespace />unsubscribe(${course.courseId });"><liferay-ui:message key="inscription.unsusbscribe" /></a>
							</div>
						</c:if>
					</c:if>
					<aui:form action="${unsubscribeURL }" name="unsubscribeForm">
						<aui:input name="courseId" type="hidden" value=""/>
					</aui:form>
					<script>
						function <portlet:namespace />unsubscribe(courseId) {
							if(confirm(Liferay.Language.get('inscription.unsusbscribe.confirm'))){
								document.<portlet:namespace />unsubscribeForm.<portlet:namespace />courseId.value=courseId;
								document.<portlet:namespace />unsubscribeForm.submit();
							}
						}
					</script>
				</c:when>
				<c:when test="${themeDisplay.isSignedIn() && inscriptionPending }">
					<div><liferay-ui:message key="inscription.pending" /></div>
				</c:when>
				<c:when test="${themeDisplay.isSignedIn() && inscriptionDenied }">
					<div><liferay-ui:message key="inscription.denied" /></div>
				</c:when>
				<c:when test="${themeDisplay.isSignedIn() && !registredUser}">
					<c:set var="prerequisitePassed" value="${true }"/>
					<c:if test="${not empty listPrerequisites}">
						<div><liferay-ui:message key="prerequisites.necessary" />:</div>
						<ul>
							<c:forEach items="${listPrerequisites}" var="prerequisite">
								<c:set var="prerequisitePassed" value="${prerequisitePassed && prerequisite.isPassed(themeDisplay.userId) }" />
								<liferay-util:include page="${prerequisite.prerequisiteFactory.getURLSpecificContent()}" portletId="${rerequisite.prerequisiteFactory.getPortletId()}"/>
							</c:forEach>
						</ul>
					</c:if>
					<!-- Si tiene pasadas las competencias, entonces se continua con la inscripción -->
					<c:choose>
						<c:when test="${prerequisitePassed}">
							<!-- Se comprueba si tiene competencias por curso hijo -->
							<c:choose>
								<c:when test="${not empty listChildCourses}">
								<!-- Si tiene convocatorias, se buclea por los cursos hijos pero no se tiene en cuenta los equipos -->
									<h2><liferay-ui:message key="inscription.editions-available"/></h2>
									<p><liferay-ui:message key="inscription.editions-available.select-edition"/></p>
									
									<c:set var="childAvailable" value="false" />
									<aui:form name="enrollEditionForm" action="${enrollURL}">
										<c:forEach items="${listChildCourses}" var="childCourse">
											<c:if test="${childCourse.isRegistrationOnDate()}">
												<c:catch var ="inscriptionException">
													<c:if test="${childCourse.canEnroll(themeDisplay.userId, themeDisplay.locale, themeDisplay.permissionChecker)}">
														<c:set var="childAvailable" value="true" />
													</c:if>
												</c:catch>
												
												<div class="row inscription-edition">
													<aui:input label="${childCourse.getTitle(themeDisplay.getLocale())}" type="radio" name="courseId" value="${childCourse.courseId}"
														disabled="${not empty inscriptionException }" showRequiredLabel="false">
														<aui:validator name="required" errorMessage="inscription.enroll.select-edition"/>
													</aui:input>
													<div class="edition-dates">
														<span><liferay-ui:message key="registration-date"/>:</span> <span><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${childCourse.registrationStartDate }" /> - <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${childCourse.registrationEndDate }" /></span>
													</div>
													<div class="edition-dates">
														<span><liferay-ui:message key="execution-date"/>:</span> <span><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${childCourse.executionStartDate }" /> - <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${childCourse.executionEndDate }" /></span>
													</div>
													<c:if test="${not empty inscriptionException }">
														<div>${fn:substringAfter(inscriptionException.message, ':')}</div>
													</c:if>
												</div>
											</c:if>
										</c:forEach>
										<c:choose>	
											<c:when test="${childAvailable }">
												<aui:button type="submit" value="inscription.enroll"/>						
											</c:when>
											<c:otherwise>
												<liferay-ui:message key="inscription.enroll.no-schedule-open"/>
											</c:otherwise>
										</c:choose>
									</aui:form>
								</c:when>
								<c:otherwise>
									<!-- Si no tiene convocatorias, se hace igual que hasta ahora, teniendo en cuenta los equipos -->
									<c:catch var ="inscriptionException">
										<c:if test="${course.canEnroll(themeDisplay.userId, themeDisplay.locale, themeDisplay.permissionChecker)}">
											<c:if test="${course.group.type == TYPE_SITE_OPEN}">
										 		<div><liferay-ui:message key="inscription.not-registred" /></div>
												<div class="button ">
													<a href="javascript:${renderResponse.getNamespace()}enroll('${course.courseId }');"><liferay-ui:message key="inscription.enroll-you" /></a>
												</div>		
											</c:if>
											<c:if test="${course.group.type == TYPE_SITE_RESTRICTED}">
												<div><liferay-ui:message key="inscription.surveillance" /></div>
												<div class="button">
													<a href="javascript:${renderResponse.getNamespace()}enroll('${course.courseId }');"><liferay-ui:message key="inscription.request" /></a>
												</div>	
											</c:if>
										</c:if>
										<c:if test="${not empty inscriptionException }">
											<div>${inscriptionException.message }</div>
										</c:if>
									</c:catch>
								</c:otherwise>
							</c:choose>
													
							<aui:form name="enrollForm" action="${enrollURL}">
								<aui:input name="courseId" value="" type="hidden"/>
							</aui:form>
							<script>
								function <portlet:namespace />enroll(courseId){
									document.<portlet:namespace />enrollForm.<portlet:namespace />courseId.value=courseId;
									document.<portlet:namespace />enrollForm.submit();
								}
							</script>
						</c:when>
						<c:otherwise>
							<div class="boton_inscibirse ">
								<liferay-ui:message key="prerequisites.block" />
							</div>	
						</c:otherwise>
					</c:choose> 
				</c:when>
				<c:otherwise>
					<div><liferay-ui:message key="inscription.not-logged" /></div>
					<div class="boton_inscibirse ">
						<a href="/c/portal/login?p_l_id=${themeDisplay.layout.plid }"><liferay-ui:message key="inscription.sign-up" /></a>
					</div>		
				</c:otherwise>
			</c:choose>
		</div>
	</c:otherwise>
</c:choose>
