<%@page import="com.liferay.portal.kernel.util.StringUtil"%>
<%@page import="com.ted.lms.util.LMSPrefsPropsValues"%>
<%@page import="com.liferay.portal.kernel.util.LocaleUtil"%>
<%@ include file="init.jsp" %>

<aui:input name="courseTypeId" type="hidden" value="${courseTypeId }"/>

<div class="container">
	<div class="row">
		<div class="col-md-8">
			<aui:input autoFocus="true" label="title" localized="true" name="titleMapAsXML" type="text" wrapperCssClass="course-title" 
					value="${course.title }" placeholder="write-title-here">
				<aui:validator name="required" />
			</aui:input>
			<aui:input label="summary" localized="true" name="summaryMapAsXML" type="text" wrapperCssClass="course-summary" value="${assetEntry.summary }"
				placeholder="write-description-here">
				<aui:validator name="required" />
			</aui:input>
			<div class="course-description">
				<label for="<portlet:namespace />descriptionMapAsXML"><liferay-ui:message key="free-text" /></label>
				<liferay-ui:input-localized
					cssClass="form-control"
					editorName="${lmsWebConfiguration.getHTMLCourseEditor() }"
					formName="fm"
					name="descriptionMapAsXML"
					placeholder="write-content-here"
					type="editor"
					xml="${not empty course ? course.descriptionMapAsXML : '' }"
				/>
			</div>
			<aui:select name="layoutSetPrototypeId" label="template" helpMessage="template.help-message" required="true" >
				<c:if test="${(empty course || empty course.group) &&  listLayoutSetPrototype.size() > 1 }">
					<aui:option label="select-option" value="" disabled="true" selected="true"/>
				</c:if>
				<c:forEach items="${listLayoutSetPrototype }" var="layoutSetPrototype">
					<aui:option label="${layoutSetPrototype.getName(themeDisplay.locale) }" value="${layoutSetPrototype.layoutSetPrototypeId }" 
							selected="${layoutSetPrototype.layoutSetPrototypeId == course.layoutSetPrototypeId }"
							disabled="${not empty course }"/>
				</c:forEach>
			</aui:select>
		</div>
		<div class="col-md-4">
			<c:if test="${configuration.courseIndexer() }">
				<aui:input name="visible" label="searchable" type="toggle-switch" value="${not empty assetEntry ? assetEntry.visible : true }"/>
			</c:if>
			<portlet:actionURL name="/course/upload_image" var="uploadCourseImageURL" />
			<label for="<portlet:namespace />courseImageFileEntryId"><liferay-ui:message key="course-admin.user-small-image" /><liferay-ui:icon-help message='course-admin.user-small-image.help-message' /></label>
			
			<div class="lfr-course-image-selector form-group">
				<liferay-item-selector:image-selector
					draggableImage="vertical"
					fileEntryId="${course.smallImageId }"
					itemSelectorEventName="${smallImageSelectedItemEventName}"
					itemSelectorURL="${itemSelectorURL}"
					maxFileSize="<%=LMSPrefsPropsValues.getCourseImageMaxSize(themeDisplay.getCompanyId())%>"
					paramName="courseImageFileEntry"
					uploadURL="${uploadCourseImageURL}"
					validExtensions='<%=StringUtil.merge(LMSPrefsPropsValues.getCourseImageExtensions(themeDisplay.getCompanyId()), ", ") %>'
				/>
			</div>
			
			<div class="form-group">
				<label for="<portlet:namespace />friendlyURL"><liferay-ui:message key="url" /></label>
				<div class="form-text">
					${courseDisplayContext.friendlyURLBase }
				</div>
				<aui:input name="friendlyURL" label="" value="${course.groupFriendlyURL }"/>
			</div>
		</div>
	</div>
</div>