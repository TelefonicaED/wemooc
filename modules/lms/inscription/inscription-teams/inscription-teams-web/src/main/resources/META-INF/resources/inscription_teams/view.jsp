<%@ include file="../init.jsp" %>

<liferay-ui:success message="inscription-ok" key="inscription-ok" />
<liferay-ui:success message="lms.inscription.unsusbscribe.success" key="unsusbscribe" />
<liferay-ui:error key="error-enroll-user" />
<liferay-ui:error message="lms.inscription.unsusbscribe.error" key="unsusbscribe" />

<div id="caja_inscripcion">
	<c:choose>
		<c:when test="${themeDisplay.isSignedIn() && registredUser}">
			<div class="mensaje_marcado">
				<liferay-ui:message key="inscripcion.inscrito" />
			</div>
			<c:if test="${not empty course}">
				<c:if test="${course.canUnsubscribe(themeDisplay.userId, themeDisplay.permissionChecker)}">
					<div class="boton_inscibirse ">
						<a href="#" onclick="javascript:<portlet:namespace />unsubscribe();"><liferay-ui:message key="inscripcion.desinscribete" /></a>
					</div>
				</c:if>
			</c:if>
			<script>
				function <portlet:namespace />unsubscribe() {
					if(confirm(Liferay.Language.get('inscripcion.desinscribete.seguro'))){
						window.location.href = "${unsubscribeURL }";
					}
				}
			</script>
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
					<!-- Si no tiene convocatorias, se hace igual que hasta ahora, teniendo en cuenta los equipos -->
					<c:catch var ="inscriptionException">
						<c:if test="${course.canEnroll(themeDisplay.userId, themeDisplay.locale, themeDisplay.permissionChecker)}">
							<c:if test="${course.group.type == TYPE_SITE_OPEN}">
								<c:forEach items="${schedules}" var="schedule">
								 	<div class="mensaje_marcado"><liferay-ui:message key="inscripcion.noinscrito" /></div>
									<div class="col-md-12">
										<div class="col-md-3">${schedule.team.title}</div>
										<div class="col-md-3">${schedule.team.startDate}</div>
										<div class="col-md-3">${schedule.team.endDate}</div>
										<div class="col-md-3 boton_inscibirse">
											<a href="javascript:${renderResponse.getNamespace()}enroll('${schedule.teamId}');"><liferay-ui:message key="inscripcion.inscribete" /></a>
										</div>
									</div>
								 </c:forEach>
								 <c:choose>
								 	<c:when test="${!hasTeams}">
								 		<div class="mensaje_marcado"><liferay-ui:message key="inscripcion.noinscrito" /></div>
										<div class="boton_inscibirse ">
											<a href="javascript:${renderResponse.getNamespace()}enroll('0');"><liferay-ui:message key="inscripcion.inscribete" /></a>
										</div>
								 	</c:when>
								 	<c:otherwise>
								 		<c:if test="${empty schedules}">
								 			<div class="mensaje_marcado">
												<liferay-ui:message key="inscripcion.no-schedule-open"/>
											</div>
								 		</c:if>
								 	</c:otherwise>
								 </c:choose>			
							</c:if>
							<c:if test="${course.group.type == TYPE_SITE_RESTRICTED}">
								<c:choose>
									<c:when test="${not empty membershipRequestLocalService.getMembershipRequests(themeDisplay.userId, course.groupCreatedId, STATUS_PENDING)}">
										<div class="mensaje_marcado"><liferay-ui:message key="course.pending" /></div>
									</c:when>
									<c:when test="${not empty membershipRequestLocalService.getMembershipRequests(themeDisplay.userId, course.groupCreatedId, STATUS_DENIED)}">
										<div class="mensaje_marcado"><liferay-ui:message key="course.denied" /></div>
									</c:when>
									<c:otherwise>
										<c:forEach items="${schedules}" var="schedule">
										 	<div class="mensaje_marcado"><liferay-ui:message key="inscripcion.noinscrito" /></div>
											<div class="col-md-12">
												<div class="col-md-3">${schedule.team.title}</div>
												<div class="col-md-3">${schedule.team.startDate}</div>
												<div class="col-md-3">${schedule.team.endDate}</div>
												<div class="col-md-3 boton_inscibirse">
													<a href="javascript:${renderResponse.getNamespace()}enroll('${schedule.teamId}');"><liferay-ui:message key="inscripcion.request" /></a>
												</div>
											</div>
										 </c:forEach>
										 <c:choose>
										 	<c:when test="${!hasTeams}">
										 		<div class="mensaje_marcado"><liferay-ui:message key="inscripcion.noinscrito" /></div>
												<div class="boton_inscibirse ">
													<a href="javascript:${renderResponse.getNamespace()}enroll('0');"><liferay-ui:message key="inscripcion.request" /></a>
												</div>
										 	</c:when>
										 	<c:otherwise>
										 		<c:if test="${empty schedules}">
										 			<div class="mensaje_marcado">
														<liferay-ui:message key="inscripcion.no-schedule-open"/>
													</div>
										 		</c:if>
										 	</c:otherwise>
										 </c:choose>
									</c:otherwise>
								</c:choose>		
							</c:if>
						</c:if>
						<c:if test="${not empty inscriptionException }">
							<div class="mensaje_marcado">${inscriptionException.message }</div>
						</c:if>
					</c:catch>
											
					<aui:form name="enrollForm" action="${enrollURL}">
						<aui:input name="teamId" value="" type="hidden"/>
					</aui:form>
					<script>
						function <portlet:namespace />enroll(teamId){
							document.<portlet:namespace />enrollForm.<portlet:namespace />teamId.value=teamId;
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
			<div class="mensaje_marcado"><liferay-ui:message key="inscripcion.nologado" /></div>
			<div class="boton_inscibirse ">
				<a href="/c/portal/login?p_l_id=${themeDisplay.layout.plid }"><liferay-ui:message key="inscripcion.registrate" /></a>
			</div>		
		</c:otherwise>
	</c:choose>
</div>
