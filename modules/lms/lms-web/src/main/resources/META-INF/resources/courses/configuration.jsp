<%@page import="com.liferay.portal.kernel.model.GroupConstants"%>
<%@page import="com.ted.lms.model.Course"%>
<%@ include file="init.jsp" %>

<liferay-asset:asset-categories-error />

<liferay-asset:asset-tags-error />

<aui:model-context bean="${course }" model="<%= Course.class %>" />

<div class="container">
	<div class="row">
		<c:choose>
			<c:when test="${not empty listCourseEvalFactory && listCourseEvalFactory.size() > 1}">
				<div class="col-md-6">
					<aui:select name="courseEvalId" label="course-eval" helpMessage="course-eval.help-message" required="true" >
						<c:if test="${course.courseEvalId == -1}">
							<aui:option label="select-option" value="-1" disabled="true" selected="true"/>
						</c:if>
						<c:forEach items="${listCourseEvalFactory }" var="courseEvalFactory">
							<aui:option label="${courseEvalFactory.getTitle(themeDisplay.locale) }" value="${courseEvalFactory.type }"/>
						</c:forEach>
					</aui:select>
					<c:forEach items="${listCourseEvalFactory}" var="courseEvalFactory">
						<div class="${courseEvalFactory.type!= course.courseEvalId ? 'hide' : '' }" id="${renderResponse.getNamespace()}course_eval_${courseEvalFactory.type}">
							<c:if test="${not empty courseEvalFactory.getURLSpecificContent()}">
								<liferay-util:include page="${courseEvalFactory.getURLSpecificContent()}" portletId="${courseEvalFactory.getPortletId()}" >
									<liferay-util:param name="courseId" value="${courseId }" />
									<liferay-util:param name="groupId" value="${themeDisplay.scopeGroupId }" />
								</liferay-util:include>
							</c:if>
						</div>
					</c:forEach>
				</div>
				<script>
					$('#<portlet:namespace />courseEvalId').on(
						'change',
						function() {
							$('*[id^="<portlet:namespace />course_eval_"]').addClass("hide");
							$('#<portlet:namespace />course_eval_' + this.value).removeClass("hide");
						}
					);
				</script>
			</c:when>
			<c:when test="${not empty listCourseEvalFactory }">
				<aui:input type="hidden" name="courseEvalId" value="${listCourseEvalFactory.get(0).type }" />
				<c:if test="${not empty listCourseEvalFactory.get(0).getURLSpecificContent()}">
					<liferay-util:include page="${listCourseEvalFactory.get(0).getURLSpecificContent()}" portletId="${listCourseEvalFactory.get(0).getPortletId()}" >
						<liferay-util:param name="courseId" value="${courseId }" />
						<liferay-util:param name="groupId" value="${themeDisplay.scopeGroupId }" />
					</liferay-util:include>
				</c:if>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${not empty listCalificationTypeFactory && listCalificationTypeFactory.size() > 1}">
				<div class="col-md-6">
					<aui:select name="calificationType" label="calification-type" helpMessage="calification-type.help-message" required="true">
						<c:if test="${course.calificationType == -1 }">
							<aui:option label="select-option" value="-1" disabled="true" selected="true"/>
						</c:if>
						<c:forEach items="${listCalificationTypeFactory }" var="calificationTypeFactory">
							<aui:option label="${calificationTypeFactory.getTitle(themeDisplay.locale) }" value="${calificationTypeFactory.type }"/>
						</c:forEach>
					</aui:select>
					<c:forEach items="${listCalificationTypeFactory}" var="calificationTypeFactory">
						<div class="${calificationTypeFactory.type!= course.calificationType ? 'hide' : '' }" id="${renderResponse.getNamespace()}calification_type_${calificationTypeFactory.type}">
							<c:if test="${not empty calificationTypeFactory.getURLSpecificContent()}">
								<liferay-util:include page="${calificationTypeFactory.getURLSpecificContent()}" portletId="${calificationTypeFactory.getPortletId()}" >
									<liferay-util:param name="courseId" value="${courseId }" />
									<liferay-util:param name="groupId" value="${themeDisplay.scopeGroupId }" />
								</liferay-util:include>
							</c:if>
						</div>
					</c:forEach>
				</div>
				<script>
					$('#<portlet:namespace />calificationType').on(
						'change',
						function() {
							$('*[id^="<portlet:namespace />calification_type_"]').addClass("hide");
							$('#<portlet:namespace />calification_type_' + this.value).removeClass("hide");
						}
					);
				</script>
			</c:when>
			<c:when test="${not empty listCalificationTypeFactory }">
				<aui:input type="hidden" name="calificationType" value="${listCalificationTypeFactory.get(0).type }" />
				<c:if test="${not empty listCalificationTypeFactory.get(0).getURLSpecificContent()}">
					<liferay-util:include page="${listCalificationTypeFactory.get(0).getURLSpecificContent()}" portletId="${listCalificationTypeFactory.get(0).getPortletId()}" >
						<liferay-util:param name="courseId" value="${courseId }" />
						<liferay-util:param name="groupId" value="${themeDisplay.scopeGroupId }" />
					</liferay-util:include>
				</c:if>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${not empty listInscriptionTypeFactory && listInscriptionTypeFactory.size() > 1}">
				<div class="col-md-6">
					<aui:select name="inscriptionType" label="inscription-type" helpMessage="inscription-type.help-message" required="true">
						<c:if test="${course.inscriptionType == -1 }">
							<aui:option label="select-option" value="-1" disabled="true" selected="true"/>
						</c:if>
						<c:forEach items="${listInscriptionTypeFactory }" var="inscriptionTypeFactory">
							<aui:option label="${inscriptionTypeFactory.getTitle(themeDisplay.locale) }" value="${inscriptionTypeFactory.type }"/>
						</c:forEach>
					</aui:select>
					<c:forEach items="${listInscriptionTypeFactory}" var="inscriptionTypeFactory">
						<div class="${inscriptionTypeFactory.type!= course.inscriptionType ? 'hide' : '' }" id="${renderResponse.getNamespace()}inscription_type_${inscriptionTypeFactory.type}">
							<c:if test="${not empty inscriptionTypeFactory.getURLSpecificContent()}">
								<liferay-util:include page="${inscriptionTypeFactory.getURLSpecificContent()}" portletId="${inscriptionTypeFactory.getPortletId()}" >
									<liferay-util:param name="courseId" value="${courseId }" />
									<liferay-util:param name="groupId" value="${themeDisplay.scopeGroupId }" />
								</liferay-util:include>
							</c:if>
						</div>
					</c:forEach>
					<script>
						$('#<portlet:namespace />inscriptionType').on(
							'change',
							function() {
								$('*[id^="<portlet:namespace />inscription_type_"]').addClass("hide");
								$('#<portlet:namespace />inscription_type_' + this.value).removeClass("hide");
							}
						);
					</script>
				</div>
			</c:when>
			<c:when test="${not empty listInscriptionTypeFactory }">
				<aui:input type="hidden" name="inscriptionType" value="${listInscriptionTypeFactory.get(0).type }" />
				<c:if test="${not empty listInscriptionTypeFactory.get(0).getURLSpecificContent()}">
					<liferay-util:include page="${listInscriptionTypeFactory.get(0).getURLSpecificContent()}" portletId="${listInscriptionTypeFactory.get(0).getPortletId()}" >
						<liferay-util:param name="courseId" value="${courseId }" />
						<liferay-util:param name="groupId" value="${themeDisplay.scopeGroupId }" />
					</liferay-util:include>
				</c:if>
			</c:when>
		</c:choose>
		<div class="col-md-6">
			<aui:field-wrapper label="registration-date" >
				<div class="row">
					<div class="col-md-6">
						<aui:input formName="fm" name="registrationStartDate" label="start"/>
					</div>
					<div class="col-md-6">
						<aui:input formName="fm" label="end" name="registrationEndDate" />
					</div>
				</div>
			</aui:field-wrapper>
		</div>
		<c:if test="${configuration.courseTypeRegistration() }">
			<div class="col-md-6">
				<aui:select name="typeSite" label="group-type" helpMessage="group-type.help-message" required="true" >
					<c:if test="${empty course || empty course.group }">
						<aui:option label="select-option" value="" disabled="true" selected="true"/>
					</c:if>
					<aui:option label="<%=GroupConstants.TYPE_SITE_OPEN_LABEL %>" value="<%=GroupConstants.TYPE_SITE_OPEN %>" />
					<aui:option label="<%=GroupConstants.TYPE_SITE_RESTRICTED_LABEL %>" value="<%=GroupConstants.TYPE_SITE_RESTRICTED %>" />
					<aui:option label="<%=GroupConstants.TYPE_SITE_PRIVATE_LABEL %>" value="<%=GroupConstants.TYPE_SITE_PRIVATE %>" />
				</aui:select>
			</div>
		</c:if>
		<c:if test="${configuration.courseNumUsers() }">
			<div class="col-md-6">
				<aui:input name="maxUsers" label="max-users" helpMessage="max-users.help-message" required="true" />
			</div>
		</c:if>
		<div class="col-md-6">
			<aui:field-wrapper label="execution-date">
				<div class="row">
					<div class="col-md-6">
						<aui:input formName="fm" label="start" name="executionStartDate" />
					</div>
					<div class="col-md-6">
						<aui:input formName="fm" label="end" name="executionEndDate"/>
					</div>
				</div>
			</aui:field-wrapper>
		</div>
		<div class="col-md-6">
			<liferay-ui:asset-categories-selector className="<%=Course.class.getName() %>" classPK="${course.courseId }" />
		</div>
		<div class="col-md-6">
			<aui:field-wrapper label="tags">
				<liferay-ui:asset-tags-selector className="<%=Course.class.getName() %>" classPK="${course.courseId }" />
			</aui:field-wrapper>
		</div>	
		<div class="col-md-6">	
			<liferay-expando:custom-attributes-available
				className="<%= Course.class.getName() %>"
			>
				<liferay-expando:custom-attribute-list
					className="<%= Course.class.getName() %>"
					classPK="${course.courseId }"
					editable="true"
					label="true"
				/>
			</liferay-expando:custom-attributes-available>
		</div>
	</div>
</div>