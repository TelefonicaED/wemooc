<%@ include file="../init.jsp" %>

<c:choose>
	<c:when test="${not empty inscriptionPortletName }">
		<liferay-portlet:runtime portletName="${inscriptionPortletName }" />
	</c:when>
	<c:otherwise>
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
					<c:if test="${not empty listChildCourses }">	
						<c:forEach items="${listChildCourses }" var="childCourse">
							<div class="mensaje_marcado">
								<span class="edition-name">${childCourse.getTitle(locale)}</span>
								<aui:button type="button" value="inscription.go-to-course" href="${childCourse.friendlyURL}"/>
								<c:if test="${childCourse.canUnsubscribe(themeDisplay.userId, themeDisplay.permissionChecker) }">
									<div class="boton_inscibirse ">
										<a href="#" onclick="javascript:<portlet:namespace/>unsubscribe(${childCourse.courseId })"><liferay-ui:message key="inscripcion.desinscribete" /></a>
									</div>
								</c:if>
							</div>
						</c:forEach>
					</c:if>
					<c:if test="${not empty course}">
						<c:if test="${course.canUnsubscribe(themeDisplay.userId, themeDisplay.permissionChecker)}">
							<div class="boton_inscibirse ">
								<a href="#" onclick="javascript:<portlet:namespace />unsubscribe(${course.courseId });"><liferay-ui:message key="inscripcion.desinscribete" /></a>
							</div>
						</c:if>
					</c:if>
					<aui:form action="${unsubscribeURL }" name="unsubscribeForm">
						<aui:input name="courseId" type="hidden" value=""/>
					</aui:form>
					<script>
						function <portlet:namespace />unsubscribe(courseId) {
							if(confirm(Liferay.Language.get('inscripcion.desinscribete.seguro'))){
								document.<portlet:namespace />unsubscribeForm.<portlet:namespace />courseId.value=courseId;
								document.<portlet:namespace />unsubscribeForm.submit();
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
							<c:choose>
								<c:when test="${not empty listChildCourses}">
								<!-- Si tiene convocatorias, se buclea por los cursos hijos pero no se tiene en cuenta los equipos -->
									<aui:fieldset>
										<h2>${course.getTitle(themeDisplay.getLocale())}</h2>
									</aui:fieldset>
									<div class="col-md-12 header">
										<div class="col-md-3">
											<liferay-ui:message key="courseadmin.edition"/>
										</div>
										<div class="col-md-3">
											<liferay-ui:message key="lms-inscription-configuration"/>
										</div>
										<div class="col-md-3">
											<liferay-ui:message key="lms-execution-configuration"/>											
										</div>
										<div class="col-md-3"></div>
									</div>
									<c:forEach items="${listChildCourses}" var="childCourse">
										<div class="col-md-12 body">
											<div class="col-md-3">
												<span>${childCourse.getTitle(themeDisplay.getLocale())}</span>
											</div>
											<div class="col-md-3">
												<span>${childCourse.registrationStartDate}</span> <span>-</span> <span>${childCourse.registrationEndDate}</span>
											</div>
											<div class="col-md-3">
												<span>${childCourse.executionStartDate}</span> <span>-</span> <span>${childCourse.executionEndDate}</span>
											</div>
											<div class="col-md-3">
												<c:catch var ="inscriptionException">
													<c:if test="${childCourse.canEnroll(themeDisplay.userId, themeDisplay.locale, themeDisplay.permissionChecker)}">
														<c:if test="${childCourse.group.type == TYPE_SITE_OPEN}">
															<div class="boton_inscibirse ">
																<a href="javascript:${renderResponse.getNamespace()}enroll('${childCourse.courseId}');"><liferay-ui:message key="inscripcion.inscribete" /></a>
															</div>							
														</c:if>
														<c:if test="${childCourse.group.type == TYPE_SITE_RESTRICTED}">
															<c:choose>
																<c:when test="${not empty membershipRequestLocalService.getMembershipRequests(themeDisplay.userId, childCourse.groupCreatedId, STATUS_PENDING)}">
																	<div class="mensaje_marcado"><liferay-ui:message key="course.pending" /></div>
																</c:when>
																<c:when test="${not empty membershipRequestLocalService.getMembershipRequests(themeDisplay.userId, childCourse.groupCreatedId, STATUS_DENIED)}">
																	<div class="mensaje_marcado"><liferay-ui:message key="course.denied" /></div>
																</c:when>
																<c:otherwise>
																	<div class="mensaje_marcado"><liferay-ui:message key="inscripcion.surveillance" /></div>
																	<div class="boton_inscibirse ">
																		<a href="javascript:${renderResponse.getNamespace()}enroll('${childCourse.courseId}');"><liferay-ui:message key="inscripcion.request" /></a>
																	</div>
																</c:otherwise>
															</c:choose>		
														</c:if>
													</c:if>
													<c:if test="${not empty inscriptionException }">
														<div class="mensaje_marcado">${inscriptionException.message }</div>
													</c:if>
												</c:catch>
											</div>
										</div>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<!-- Si no tiene convocatorias, se hace igual que hasta ahora, teniendo en cuenta los equipos -->
									<c:catch var ="inscriptionException">
										<c:if test="${course.canEnroll(themeDisplay.userId, themeDisplay.locale, themeDisplay.permissionChecker)}">
											<c:if test="${course.group.type == TYPE_SITE_OPEN}">
										 		<div class="mensaje_marcado"><liferay-ui:message key="inscripcion.noinscrito" /></div>
												<div class="boton_inscibirse ">
													<a href="javascript:${renderResponse.getNamespace()}enroll('${course.courseId }');"><liferay-ui:message key="inscripcion.inscribete" /></a>
												</div>		
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
														<div class="mensaje_marcado"><liferay-ui:message key="inscripcion.surveillance" /></div>
														<div class="boton_inscibirse ">
															<a href="javascript:${renderResponse.getNamespace()}enroll('${course.courseId }');"><liferay-ui:message key="inscripcion.request" /></a>
														</div>
													</c:otherwise>
												</c:choose>		
											</c:if>
										</c:if>
										<c:if test="${not empty inscriptionException }">
											<div class="mensaje_marcado">${inscriptionException.message }</div>
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
					<div class="mensaje_marcado"><liferay-ui:message key="inscripcion.nologado" /></div>
					<div class="boton_inscibirse ">
						<a href="/c/portal/login?p_l_id=${themeDisplay.layout.plid }"><liferay-ui:message key="inscripcion.registrate" /></a>
					</div>		
				</c:otherwise>
			</c:choose>
		</div>
	</c:otherwise>
</c:choose>
