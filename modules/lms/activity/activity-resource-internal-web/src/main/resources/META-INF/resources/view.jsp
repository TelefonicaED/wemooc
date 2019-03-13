<%@page import="com.liferay.portal.kernel.util.HttpUtil"%>
<%@page import="com.liferay.document.library.constants.DLPortletKeys"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@ include file="/init.jsp" %>

<%
request.setAttribute("view_file_entry.jsp-randomNamespace", request.getAttribute("randomNamespace"));

request.setAttribute("view_file_entry.jsp-supportedAudio", String.valueOf(request.getAttribute("hasAudio")));
request.setAttribute("view_file_entry.jsp-supportedVideo", String.valueOf(request.getAttribute("hasVideo")));

request.setAttribute("view_file_entry.jsp-previewFileURLs", request.getAttribute("previewFileURLs"));
request.setAttribute("view_file_entry.jsp-videoThumbnailURL", request.getAttribute("videoThumbnailURL"));
%>

<div class="description">${activity.getDescription(themeDisplay.locale) }</div>

<div>
	<c:choose>
		<c:when test="${not empty assetRendererFactory }">
			<liferay-asset:asset-display
				assetEntry="${assetEntry}"
				assetRenderer="${assetRenderer}"
				assetRendererFactory="${assetRendererFactory}"
				showExtraInfo="false"
				
			/>
		</c:when>
		<c:when test="${emptyPreview}">
			<div class="alert alert-info">
				<liferay-ui:message arguments="${fileEntry.title}" key="cannot-generate-preview-for-x" />
			</div>
		</c:when>
		<c:when test="${previewFileCount == 0}">
			<c:choose>
				<c:when test="${tooLargeForPreview }">
					<div class="alert alert-info">
						<liferay-ui:message key="file-is-too-large-for-preview-or-thumbnail-generation" />
					</div>
				</c:when>
				<c:when test="${tooLargeForPreview }">
					<div class="alert alert-info">
						<liferay-ui:message key="generating-preview-will-take-a-few-minutes" />
					</div>
				</c:when>
			</c:choose>
		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="${hasAudio }">
				hasAudiooooo
					<div class="lfr-preview-audio" id="<portlet:namespace />${randomNamespace }previewFile">
						<div class="lfr-preview-audio-content" id="<portlet:namespace />${randomNamespace }previewFileContent"></div>
					</div>

					<liferay-util:include page="/document_library/player.jsp" servletContext="<%= application %>" portletId="<%=DLPortletKeys.DOCUMENT_LIBRARY %>"/>
				</c:when>
				<c:when test="${hasImages }">
					<c:choose>
						<c:when test="${showImageContainer }">
							<div class="lfr-preview-file lfr-preview-image" id="<portlet:namespace />${randomNamespace }previewFile">
								<div class="lfr-preview-file-content lfr-preview-image-content" id="<portlet:namespace />${randomNamespace }previewFileContent">
									<div class="lfr-preview-file-image-current-column">
										<div class="lfr-preview-file-image-container">
											<img alt="<liferay-ui:message escapeAttribute="<%= true %>" key="preview" />" class="lfr-preview-file-image-current" src="${previewFileURL}" />
										</div>
									</div>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<img alt="<liferay-ui:message escapeAttribute="<%= true %>" key="preview" />" class="lfr-preview-file-image-current" src="${previewFileURL}" />
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:when test="${hasVideo}">
					<div class="lfr-preview-file lfr-preview-video" id="<portlet:namespace />${randomNamespace }previewFile">
						<div class="lfr-preview-file-content lfr-preview-video-content">
							<div class="lfr-preview-file-video-current-column">
								<div id="<portlet:namespace />${randomNamespace }previewFileContent"></div>
							</div>
						</div>
					</div>

					<liferay-util:include page="/document_library/player.jsp" servletContext="<%= application %>" portletId="<%=DLPortletKeys.DOCUMENT_LIBRARY %>"/>
				</c:when>
				<c:when test="${isEPUB }">
					<script src="<%= HttpUtil.getProtocol(request) %>://cdn.jsdelivr.net/npm/epubjs/dist/epub.min.js"></script>
					<script src="<%= HttpUtil.getProtocol(request) %>://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.5/jszip.min.js"></script>
					
					<div class="lfr-preview-file lfr-preview-epub" id="<portlet:namespace />${randomNamespace }previewFile">
						<div class="lfr-preview-epub-content">
							<div class="lfr-preview-file-epub-current-column">
								<div id="<portlet:namespace />${randomNamespace }previewFileContent"></div>
								<a id="prev" href="#prev" class="arrow">&lt;</a>
								<a id="next" href="#next" class="arrow">&gt;</a>
							</div>
						</div>
					</div>
					<script>
						var book = ePub("${previewFileURL }", {openAs : "epub"});
						var rendition = book.renderTo("<portlet:namespace />${randomNamespace }previewFileContent", { width: "100%", height: 600 });
						rendition.display();
						
						var keyListener = function(e){
							// Left Key
						    if ((e.keyCode || e.which) == 37) {
						    	rendition.prev();
						   	}
						   	// Right Key
						    if ((e.keyCode || e.which) == 39) {
						    	rendition.next();
						    }
						};
						
						rendition.on("keyup", keyListener);
						
						document.getElementById("next").addEventListener("click", function(e){
							rendition.next();
						    e.preventDefault();
						}, false);
						document.getElementById("prev").addEventListener("click", function(e){
							rendition.prev();
						    e.preventDefault();
						}, false);
						AUI().ready('event',function(A) {
							if ((navigator.userAgent.indexOf('iPad') !== -1) || (navigator.userAgent.indexOf('iPhone') !== -1) || (navigator.userAgent.indexOf('Macintosh')!== -1)) {
								if(confirm(Liferay.Language.get('open-in-ibooks'))){
									$('#<portlet:namespace/>download').attr("target", "");
									document.getElementById('<portlet:namespace/>download').click();
									$('#<portlet:namespace/>download').attr("target", "_blank");
								}
							}
						 });	
					</script>		
				</c:when>
				<c:when test="${hasPDFImages }">
					<div class="lfr-preview-file lfr-preview-pdf" id="<portlet:namespace />${randomNamespace }previewFile">
						<div class="lfr-preview-pdf-content">
							<div class="lfr-preview-file-pdf-current-column">
								<iframe width="100%" height="1000" src='${previewFileURL }' allowfullscreen="true"></iframe>
							</div>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="lfr-preview-file" id="<portlet:namespace />${randomNamespace }previewFile">
						<div class="lfr-preview-file-content" id="<portlet:namespace />${randomNamespace }previewFileContent">
							<div class="lfr-preview-file-image-current-column">
								<div class="lfr-preview-file-image-container">
									<img alt="<liferay-ui:message escapeAttribute="<%= true %>" key="preview" />" class="lfr-preview-file-image-current" id="<portlet:namespace />${randomNamespace }previewFileImage" src='${previewFileURL + "1"}' />
								</div>

								<span class="hide lfr-preview-file-actions" id="<portlet:namespace />${randomNamespace }previewFileActions">
									<span class="lfr-preview-file-toolbar" id="<portlet:namespace />${randomNamespace }previewToolbar"></span>

									<span class="lfr-preview-file-info">
										<span class="lfr-preview-file-index" id="<portlet:namespace />${randomNamespace }previewFileIndex">1</span> <liferay-ui:message key="of" /> <span class="lfr-preview-file-count">${previewFileCount}</span>
									</span>
								</span>
							</div>

							<div class="lfr-preview-file-images" id="<portlet:namespace />${randomNamespace }previewImagesContent">
								<div class="lfr-preview-file-images-content"></div>
							</div>
						</div>
					</div>

					<aui:script use="liferay-preview">
						new Liferay.Preview(
							{
								actionContent: '#<portlet:namespace />${randomNamespace }previewFileActions',
								baseImageURL: '${previewFileURL}',
								boundingBox: '#<portlet:namespace />${randomNamespace }previewFile',
								contentBox: '#<portlet:namespace />${randomNamespace }previewFileContent',
								currentPreviewImage: '#<portlet:namespace />${randomNamespace }previewFileImage',
								imageListContent: '#<portlet:namespace />${randomNamespace }previewImagesContent',
								maxIndex: ${previewFileCount},
								previewFileIndexNode: '#<portlet:namespace />${randomNamespace }previewFileIndex',
								toolbar: '#<portlet:namespace />${randomNamespace }previewToolbar'
							}
						).render();
					</aui:script>
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>
	<span class="download-document">
		<liferay-ui:icon
				id="download"
				image="download"
				label="<%= true %>"
				message='${messageDownload }'
				url="${downloadFileURL }"
				target="_blank"
		/>
	</span>	
	<p class="asset-description">${fileVersion.description}</p>
</div>

<aui:script use="aui-base">
	var currentImage = A.one('.lfr-preview-file-image-current');

	if (currentImage && (currentImage.get('complete') || currentImage.get('naturalWidth'))) {
		currentImage.setStyle('background-image', 'none');
	}
</aui:script>