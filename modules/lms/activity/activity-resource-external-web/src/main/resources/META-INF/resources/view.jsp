<%@page import="com.liferay.portal.kernel.util.TextFormatter"%>
<%@page import="com.liferay.portal.kernel.util.HtmlUtil"%>
<%@page import="com.liferay.portal.kernel.workflow.WorkflowConstants"%>
<%@page import="com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil"%>
<%@page import="com.liferay.portal.kernel.repository.model.FileEntry"%>
<%@page import="java.util.List"%>
<%@ include file="/init.jsp" %>

<div class="description">${activity.getDescription(themeDisplay.locale)}</div>

<c:if test="${isTeacher }">
	<aui:button type="button" value="learning-activity.test.correct" href="${goToCorrectionURL }"/>
</c:if>

<%List<FileEntry> documents = (List<FileEntry>)request.getAttribute("documents"); %>

<c:choose>
	<c:when test="${not empty mimeType }">
		<%@ include file="/mediaelement.jsp" %>
	</c:when>
	<c:otherwise>
		<div class="video">
			${video}
		</div>
		<script>
			var unloadEvent = function (e) {
				console.log("unloadEvent otherwise");
				<portlet:namespace/>finishTry(100,0,0);												
			  };
			  window.addEventListener("beforeunload", unloadEvent);	
		</script>
	</c:otherwise>
</c:choose>
<c:if test="<%=documents != null && documents.size() > 0%>">
	<div class="container_files">
		<%for(FileEntry document: documents){ %>
			<div class="row_file">
				<%
				String rowURL = PortletFileRepositoryUtil.getDownloadPortletFileEntryURL(themeDisplay, document, "status=" + WorkflowConstants.STATUS_APPROVED);
				%>

				<liferay-ui:icon
					iconCssClass="icon-paper-clip"
					label="<%= true %>"
					message='<%= HtmlUtil.escape(document.getTitle()) + " (" + TextFormatter.formatStorageSize(document.getSize(), themeDisplay.getLocale()) + ")" %>'
					method="get"
					url="<%= rowURL %>"
				/>
			</div>
		<%} %>
	</div>
</c:if>

<div class="container-activity isFeedback" id="${renderResponse.getNamespace()}videoQuestionFeedback">
</div>

<div class="container-activity isFeedback" id="${renderResponse.getNamespace()}videoQuestionFeedbackFinal">
</div>

<aui:script>
	function <portlet:namespace/>finishTry(score,position,plays){
		
		var A = AUI();
		
		var data = Liferay.Util.ns(
				'<portlet:namespace />',
				{
					score: score,
					position: position,
					plays: plays
				}
			);

		$.ajax({
				dataType: 'json',
				url: '${finishTryURL}',
			    cache:false,
				data: data,
				success: function(data){
					
					if(data.questionCorrection){
						if(data.finalFeedback){
							var videoQuestionFeedback = A.one('#<portlet:namespace />videoQuestionFeedbackFinal');
							var videoQuestionFeedbackQuery = $('#<portlet:namespace />videoQuestionFeedbackFinal');
							videoQuestionFeedback.plug(A.LoadingMask);
							videoQuestionFeedback.loadingmask.show();
							
							<liferay-portlet:resourceURL copyCurrentRenderParameters="false" id="/activities/resource_external/feedback_final" var="videoFeedbackFinalURL">
								<portlet:param name="actId" value="${activity.getActId()}" />
							</liferay-portlet:resourceURL>
							
							var uri = '${videoFeedbackFinalURL}';
							
							if (videoQuestionFeedback.io) {
								videoQuestionFeedback.io.start();
							} else {
								videoQuestionFeedbackQuery.load(uri);
							}
							
							videoQuestionFeedback.unplug(A.LoadingMask);
						}
					}
					
				},
				error: function(){
					console.log("ERROR");
				}
			});
	}
</aui:script>