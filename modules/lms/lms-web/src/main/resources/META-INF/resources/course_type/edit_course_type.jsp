<%@page import="com.ted.lms.util.LMSPrefsPropsValues"%>
<%@page import="com.liferay.portal.kernel.util.StringUtil"%>
<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@ include file="../init.jsp" %>

<liferay-ui:header title='${not empty courseType ? courseType.nameCurrentValue : "new-course-type" }' backURL="${backURL}" localizeTitle="${empty courseType }"/>

<aui:form name="fm" enctype="multipart/form-data" method="post" action="${editCourseTypeURL}" >

	<aui:input name="<%= Constants.CMD %>" value="${cmd}" type="hidden" />
	
	<portlet:actionURL name="/course/upload_image" var="uploadIconURL" />
			
	<div class="course-type-icon-selector">

		<liferay-item-selector:image-selector
			draggableImage="vertical"
			fileEntryId="${courseType.iconId }"
			itemSelectorEventName="${iconSelectedItemEventName }"
			itemSelectorURL="${itemSelectorURL }"
			paramName="iconFileEntry"
			uploadURL="${uploadIconURL}"
			validExtensions='<%=StringUtil.merge(LMSPrefsPropsValues.getCourseTypeIconExtensions(themeDisplay.getCompanyId()), ", ") %>'
			maxFileSize='<%=LMSPrefsPropsValues.getCourseTypeIconMaxSize(themeDisplay.getCompanyId()) %>'
		/>
	</div>	
	
	<aui:input autoFocus="true" label="name" localized="true" name="nameMapAsXML" type="text" 
			value="${courseType.name }" placeholder="write-name-here">
		<aui:validator name="required" />
	</aui:input>
	
	<aui:input autoFocus="true" label="description" localized="true" name="descriptionMapAsXML" type="text" 
			value="${courseType.description }" placeholder="write-description-here">
		<aui:validator name="required" />
	</aui:input>
	
	 <aui:select required="true" label="template" name="templateIds" multiple="true" helpMessage="${not empty courseTemplateIds ? 'course-type.templates.only-add' : ''}" ignoreRequestValue="true">
		<c:forEach items="${listLayoutSetPrototype }" var="template">
			<c:set var="selectedTemplate" value="${not empty courseTemplateIds and courseTemplateIds.contains(template.layoutSetPrototypeId ) }"/>
			<aui:option 
				value="${template.layoutSetPrototypeId }" 
				selected="${selectedTemplate}"
				disabled="${selectedTemplate}" >
					${template.getName(themeDisplay.locale) }
			</aui:option>
		</c:forEach>
	</aui:select>
	
	<aui:select required="true" label="course-eval" name="courseEvalIds" multiple="true"  helpMessage="${not empty courseEvalTypeIds ? 'coursetype.course-eval.only-add' : ''}" ignoreRequestValue="true">
		<c:forEach items="${courseEvalFactories }" var="courseEval">
			<c:set var="selectedCourseEval" value="${not empty courseEvalTypeIds and courseEvalTypeIds.contains(courseEval.type ) }"/>
			<aui:option
				value="${courseEval.type }"
				selected="${selectedCourseEval}"
				disabled="${selectedCourseEval}">
					<liferay-ui:message key="${courseEval.getTitle(themeDisplay.locale) }" />
			</aui:option>
		</c:forEach>
	</aui:select>

	<aui:select required="true" label="activities" name="learningActivityTypeIds" multiple="true"  helpMessage="${not empty activityIds ? 'course-type.learning-activity.only-add' : ''}" ignoreRequestValue="true">
		<c:forEach items="${learningActivityTypeFactories }" var="activityType">
			<c:set var="selectedActivity" value="${not empty activityIds and activityIds.contains(activityType.type ) }"/>
			<aui:option
				value="${activityType.type }"
				selected="${selectedActivity}"
				disabled="${selectedActivity}">
					<liferay-ui:message key="${activityType.getName(themeDisplay.locale) }" />
			</aui:option>
		</c:forEach>
	</aui:select>

	<aui:select required="true" label="inscription-type" name="inscriptionTypeIds" multiple="true"  helpMessage="${not empty inscriptionTypeIds ? 'course-type.inscription-type.only-add' : ''}" ignoreRequestValue="true">
		<c:forEach items="${inscriptionTypeFactories }" var="inscriptionType">
			<c:set var="selectedInscription" value="${not empty inscriptionTypeIds and inscriptionTypeIds.contains(inscriptionType.type ) }"/>
			<aui:option
				value="${inscriptionType.type }"
				selected="${selectedInscription}"
				disabled="${selectedInscription}">
					${inscriptionType.getTitle(themeDisplay.locale) }
			</aui:option>
		</c:forEach>
	</aui:select>
	
	<aui:select required="true" label="calification-type" name="calificationTypeIds" multiple="true"  helpMessage="${not empty calificationTypeIds ? 'course-type.calification-type.only-add' : ''}" ignoreRequestValue="true">
		<c:forEach items="${calificationTypeFactories }" var="calificationType">
			<c:set var="selectedCalification" value="${not empty calificationTypeIds and calificationTypeIds.contains(calificationType.type ) }"/>
			<aui:option
				value="${calificationType.type }"
				selected="${selectedCalification}"
				disabled="${selectedCalification}">
					<liferay-ui:message key="${calificationType.getTitle(themeDisplay.locale) }"/>
			</aui:option>
		</c:forEach>
	</aui:select>

	
	<aui:button-row>
		<aui:button value="cancel" onClick="${backURL}"/>
		<aui:button type="submit" value="save" />
	</aui:button-row>
	
</aui:form>