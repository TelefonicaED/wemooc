<%@page import="com.ted.lms.model.Course"%>
<%@ include file="init.jsp" %>

<aui:model-context bean="${course }" model="<%= Course.class %>" />

<div class="container">
	<div class="row">
		<c:if test="${configuration.courseWelcomeMessage() }">
			<div class="${configuration.courseGoodbyeMessage() || course.isTypeSiteRestricted() ? 'col-md-6' : 'col-md-12' }">
				<aui:input name="welcome" label="message-welcome" type="toggle-switch" />
				<div id="${renderResponse.getNamespace()}welcome_wrapper" class="${empty course || !course.welcome ? 'hide' : ''}">
					<aui:input label="message-welcome.subject" localized="true" name="welcomeSubjectMapAsXML" type="text"  
							placeholder="write-the-content-here" value="${course.welcomeSubject }"/>
					<div class="wecolme-msg">
						<label for="<portlet:namespace />welcomeMsgMapAsXML"><liferay-ui:message key="message-welcome.msg" /></label>
						<liferay-ui:input-localized
							cssClass="form-control"
							editorName="alloyeditor"
							formName="fm"
							name="welcomeMsgMapAsXML"
							placeholder="write-the-content-here"
							type="editor"
							xml="${not empty course ? course.welcomeMsgMapAsXML : '' }"
						/>
					</div>
				</div>
			</div>
		</c:if>
		<c:if test="${configuration.courseGoodbyeMessage() }">
			<div class="${configuration.courseWelcomeMessage() || course.isTypeSiteRestricted()? 'col-md-6' : 'col-md-12' }">
				<aui:input name="goodbye" label="message-goodbye" type="toggle-switch" />
				<div id="${renderResponse.getNamespace()}goodbye_wrapper" class="${empty course || !course.welcome ? 'hide' : ''}">
					<aui:input label="message-goodbye.subject" localized="true" name="goodbyeSubjectMapAsXML" type="text" 
							placeholder="write-the-content-here" value="${course.goodbyeSubject }"/>
					<div class="goodbye-msg">
						<label for="<portlet:namespace />goodbyeMsgMapAsXML"><liferay-ui:message key="message-goodbye.msg" /></label>
						<liferay-ui:input-localized
							cssClass="form-control"
							editorName="alloyeditor"
							formName="fm"
							name="goodbyeMsgMapAsXML"
							placeholder="write-the-content-here"
							type="editor"
							xml="${not empty course ? course.goodbyeMsgMapAsXML : '' }"
						/>
					</div>
				</div>
			</div>
		</c:if>
		<c:if test="${course.isTypeSiteRestricted() }">
			<div class="${configuration.courseWelcomeMessage() || configuration.courseGoodbyeMessage() ? 'col-md-6' : 'col-md-12' }">
				<aui:input name="deniedInscription" label="message-denied-inscription" type="toggle-switch" />
				<div id="${renderResponse.getNamespace()}denied_inscription_wrapper" class="${empty course || !course.deniedInscription ? 'hide' : ''}">
					<aui:input label="message-denied-inscription.subject" localized="true" name="deniedInscriptionSubjectMapAsXML" type="text"
							placeholder="write-the-content-here" value="${course.deniedInscriptionSubject }"/>
					<div class="denied-inscription-msg">
						<label for="<portlet:namespace />deniedInscriptionMsgMapAsXML"><liferay-ui:message key="message-denied-inscription.msg" /></label>
						<liferay-ui:input-localized
							cssClass="form-control"
							editorName="alloyeditor"
							formName="fm"
							name="deniedInscriptionMsgMapAsXML"
							placeholder="write-the-content-here"
							type="editor"
							xml="${not empty course ? course.deniedInscriptionMsgMapAsXML : '' }"
						/>
					</div>
				</div>
			</div>
		</c:if>
	</div>
</div>

<script>
	$('#<portlet:namespace />welcome').on(
		'change',
		function() {
			$('#<portlet:namespace />welcome_wrapper').toggleClass("hide");
			if($('#<portlet:namespace />welcomeSubjectMapAsXML').prop("required")){
				$('#<portlet:namespace />welcomeSubjectMapAsXML').prop("required", false);
			}else{
				$('#<portlet:namespace />welcomeSubjectMapAsXML').prop("required", true);
			}
		}
	);
	$('#<portlet:namespace />goodbye').on(
		'change',
		function() {
			$('#<portlet:namespace />goodbye_wrapper').toggleClass("hide");
			if($('#<portlet:namespace />goodbyeSubjectMapAsXML').prop("required")){
				$('#<portlet:namespace />goodbyeSubjectMapAsXML').prop("required", false);
			}else{
				$('#<portlet:namespace />goodbyeSubjectMapAsXML').prop("required", true);
			}
		}
	);
	$('#<portlet:namespace />deniedInscription').on(
		'change',
		function() {
			$('#<portlet:namespace />denied_inscription_wrapper').toggleClass("hide");
			if($('#<portlet:namespace />deniedInscriptionSubjectMapAsXML').prop("required")){
				$('#<portlet:namespace />deniedInscriptionSubjectMapAsXML').prop("required", false);
			}else{
				$('#<portlet:namespace />deniedInscriptionSubjectMapAsXML').prop("required", true);
			}
		}
	);
</script>