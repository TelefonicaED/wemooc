<%@page import="com.liferay.portal.kernel.util.LocaleUtil"%>
<%@ include file="init.jsp" %>

<div class="container">
	<div class="row">
		<div class="col-md-8">
			<aui:input autoFocus="true" label="title" localized="true" name="titleMapAsXML" type="text" wrapperCssClass="course-title" value="${course.title }">
				<aui:validator name="required" />
			</aui:input>
			<aui:input autoFocus="true" label="summary" localized="true" name="summaryMapAsXML" type="text" wrapperCssClass="course-summary" value="${assetEntry.summary }">
				<aui:validator name="required" />
			</aui:input>
			<div class="course-description">
				<label for="<portlet:namespace />descriptionMapAsXML"><liferay-ui:message key="description" /></label>
				<liferay-ui:input-localized
					cssClass="form-control"
					editorName="alloyeditor"
					formName="fm"
					name="descriptionMapAsXML"
					placeholder="description"
					type="editor"
					xml="${not empty course ? course.descriptionMapAsXML : '' }"
				/>
			</div>
			<c:if test="${not empty listTemplates && listTemplates.size() > 1}">
				<aui:select name="courseTemplatelId" label="template" helpMessage="template-help-message">
					<c:forEach items="${listTemplates}" var="template">
						<aui:option selected="${template.getLayoutSetPrototypeId() == templateSelectedId}" label="${template.getName(themeDisplay.locale)}" 
							value="${template.getLayoutSetPrototypeId()}"/>
					</c:forEach>
				</aui:select>
			</c:if>
		</div>
		<div class="col-md-4">
			<aui:input name="visible" label="searchable" type="toggle-switch" value="${not empty assetEntry ? assetEntry.visible : true }"/>
			
			<portlet:actionURL name="/course/upload_image" var="uploadCourseImageURL" />
			<label for="<portlet:namespace />courseImageFileEntryId"><liferay-ui:message key="course-admin.user-small-image" /><liferay-ui:icon-help message='course-admin.user-small-image.help-message' /></label>
			
			<div class="lfr-course-image-selector form-group">
				<liferay-item-selector:image-selector
					draggableImage="vertical"
					fileEntryId="${course.smallImageId }"
					itemSelectorEventName="${courseImageSelectedItemEventName}"
					itemSelectorURL="${itemSelectorURL}"
					maxFileSize="${maxFileSize }"
					paramName="courseImageFileEntry"
					uploadURL="${uploadCourseImageURL}"
					validExtensions='${validExtensions }'
				/>
			</div>
			
			<div class="form-group">
				<label for="<portlet:namespace />friendlyURL"><liferay-ui:message key="url" /></label>
			
				<liferay-ui:input-localized
					defaultLanguageId="<%= LocaleUtil.toLanguageId(themeDisplay.getSiteDefaultLocale()) %>"
					inputAddon="${courseDisplayContext.friendlyURLBase }"
					maxLength='${friendlyURLMaxLength }'
					name="friendlyURL"
					xml="${course.friendlyURLsXML }"
				/>
			</div>

		</div>
	</div>
</div>