<%@page import="com.ted.lms.model.Course"%>
<%@ include file="../init.jsp" %>

<aui:model-context bean="${course }" model="<%= Course.class %>" />

<portlet:actionURL name="/courses/copy_course" var="copyCourseURL" />

<liferay-ui:error message="course-admin.copy.error.registration-dates" key="registration-dates"/>
<liferay-ui:error message="course-admin.copy.error.execution-dates" key="execution-dates"/>

<aui:form name="fm" enctype="multipart/form-data" method="post" action="${copyCourseURL}" >

	<aui:input name="redirect" type="hidden" value="${redirect}" />
	<aui:input name="courseId" type="hidden" value="${course.courseId }" />

	<div class="row">
		<div class="col-md-8">
			<aui:input autoFocus="true" label="title" localized="true" name="titleMapAsXML" type="text" wrapperCssClass="course-title" value="${newTitle }" >
				<aui:validator name="required" />
			</aui:input>
		</div>
		<div class="col-md-4">
			<c:choose>
				<c:when test="${not empty listLayoutSetPrototype && listLayoutSetPrototype.size() > 0 }">
					<aui:select name="layoutSetPrototypeId" label="template" helpMessage="template.help-message" required="true" >
						<c:if test="${empty course || empty course.group }">
							<aui:option label="select-option" value="" disabled="true" selected="true"/>
						</c:if>
						<c:forEach items="${listLayoutSetPrototype }" var="layoutSetPrototype">
							<aui:option label="${layoutSetPrototype.getName(themeDisplay.locale) }" value="${layoutSetPrototype.layoutSetPrototypeId }" />
						</c:forEach>
					</aui:select>
				</c:when>
			</c:choose>
		</div>
	</div>
	<div class="row">
		<div class="col-md-3">
			<aui:input name="copyForum" label="course-admin.copy.copy-forum" helpMessage="course-admin.copy.copy-forum.help-message" type="checkbox"/>
		</div>
		<div class="col-md-3">
			<aui:input name="copyDocuments" label="course-admin.copy.copy-documents" helpMessage="course-admin.copy.copy-documents.help-message" type="checkbox"/>
		</div>
	</div>
	
	<div class="row">
		<div class="col-md-5">
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
		<div class="col-md-5">
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
	</div>
	<aui:button-row>
		<aui:button value="cancel" onClick="${redirect}"/>
		<aui:button type="submit" value="course-admin.copy.copy-course" />
	</aui:button-row>

</aui:form>