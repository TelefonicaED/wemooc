package com.ted.lms.learning.activity.resource.internal.web.internal.portlet.action;

import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.util.AudioProcessorUtil;
import com.liferay.document.library.kernel.util.DLProcessorRegistryUtil;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.document.library.kernel.util.ImageProcessorUtil;
import com.liferay.document.library.kernel.util.PDFProcessorUtil;
import com.liferay.document.library.kernel.util.VideoProcessorUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.ted.lms.learning.activity.resource.internal.web.activity.ResourceInternalActivityType;
import com.ted.lms.learning.activity.resource.internal.web.activity.ResourceInternalActivityTypeFactory;
import com.ted.lms.learning.activity.resource.internal.web.constants.ResourceInternalPortletKeys;
import com.ted.lms.learning.activity.resource.internal.web.util.EPUBProcessorUtil;
import com.ted.lms.model.Course;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityTry;
import com.ted.lms.service.CourseLocalServiceUtil;
import com.ted.lms.service.LearningActivityLocalServiceUtil;
import com.ted.lms.service.LearningActivityResultLocalServiceUtil;
import com.ted.lms.service.LearningActivityTryLocalServiceUtil;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true, 
	property = { "javax.portlet.name=" + ResourceInternalPortletKeys.RESOURCE_INTERNAL,
			"mvc.command.name=/",
			"mvc.command.name=/activities/resource_internal/view_activity" }, 
	service = MVCRenderCommand.class
)
public class ResourceInternalActivityViewMVCRenderCommand implements MVCRenderCommand {
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

		long actId = ParamUtil.getLong(renderRequest, "actId", 0);

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		if (actId == 0) {
			renderRequest.setAttribute(WebKeys.PORTLET_CONFIGURATOR_VISIBILITY, Boolean.FALSE);
		}

		LearningActivity activity = LearningActivityLocalServiceUtil.fetchLearningActivity(actId);
		renderRequest.setAttribute("activity", activity);

		renderRequest.setAttribute("actId", actId);

		// Creamos el try si corresponde
		Course course = CourseLocalServiceUtil.getCourseByGroupCreatedId(themeDisplay.getScopeGroupId());
		try {
			if (!LearningActivityResultLocalServiceUtil.hasUserPassed(actId, themeDisplay.getUserId())
					&& !course.hasPermissionAccessCourseFinished(themeDisplay.getUserId())) {
				ServiceContext serviceContext = ServiceContextFactory.getInstance(LearningActivityTry.class.getName(),renderRequest);
				LearningActivityTry learningActivityTry = LearningActivityTryLocalServiceUtil
						.addLearningActivityTry(actId, themeDisplay.getUserId(), serviceContext);
				learningActivityTry = LearningActivityTryLocalServiceUtil.finishLearningActivityTry(learningActivityTry,100, serviceContext);
			}
		} catch (PortalException e) {
			e.printStackTrace();
		}

		if (Validator.isNull(activity.getExtraContent())) {
			renderRequest.setAttribute(WebKeys.PORTLET_CONFIGURATOR_VISIBILITY, Boolean.FALSE);
		}

		ResourceInternalActivityTypeFactory resourceInternalActivityTypeFactory = new ResourceInternalActivityTypeFactory();
		ResourceInternalActivityType resourceInternalActivityType = resourceInternalActivityTypeFactory.getResourceInternalType(activity);

		try {

			AssetEntry assetEntry = AssetEntryLocalServiceUtil.getAssetEntry(resourceInternalActivityType.getAssetEntryId());
			
			if (assetEntry.getClassName().equals(DLFileEntry.class.getName())) {
				HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
				String randomNamespace = PortalUtil.generateRandomKey(request, "portlet_resource_internal_view_file_entry_preview") + StringPool.UNDERLINE;

				FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(assetEntry.getClassPK());
				FileVersion fileVersion = fileEntry.getFileVersion();
				
				boolean emptyPreview = false;
				int previewFileCount = 0;
				String previewFileURL = null;
				String[] previewFileURLs = null;
				String videoThumbnailURL = null;

				boolean hasAudio = AudioProcessorUtil.hasAudio(fileVersion);
				boolean hasImages = ImageProcessorUtil.hasImages(fileVersion);
				boolean hasPDFImages = PDFProcessorUtil.hasImages(fileVersion);
				boolean hasVideo = VideoProcessorUtil.hasVideo(fileVersion);
				boolean isEPUB = EPUBProcessorUtil.isEPUB(fileVersion);

				boolean showImageContainer = true;

				if (hasAudio || hasImages || hasPDFImages || hasVideo || isEPUB) {
					String previewQueryString = null;

					if (hasAudio) {
						previewQueryString = "&audioPreview=1";
					} else if (hasImages) {
						previewQueryString = "&imagePreview=1";
					} else if (hasPDFImages) {
						previewFileCount = PDFProcessorUtil.getPreviewFileCount(fileVersion);

						previewQueryString = "&previewFileIndex=";

						previewFileURL = DLUtil.getPreviewURL(fileEntry, fileVersion, themeDisplay, previewQueryString);
					} else if (hasVideo) {
						previewQueryString = "&videoPreview=1";

						videoThumbnailURL = DLUtil.getPreviewURL(fileEntry, fileVersion, themeDisplay,"&videoThumbnail=1");
					}

					if (hasAudio) {
						emptyPreview = true;
						
						String[] dlFileEntryPreviewAudioContainers = PropsUtil.getArray(PropsKeys.DL_FILE_ENTRY_PREVIEW_AUDIO_CONTAINERS);
						previewFileURLs = new String[dlFileEntryPreviewAudioContainers.length];

						for (int i = 0; i < dlFileEntryPreviewAudioContainers.length; i++) {
							try {
								if (AudioProcessorUtil.getPreviewFileSize(fileVersion,dlFileEntryPreviewAudioContainers[i]) > 0) {
									emptyPreview = false;
									previewFileURLs[i] = DLUtil.getPreviewURL(fileEntry, fileVersion, themeDisplay,
											previewQueryString + "&type=" + dlFileEntryPreviewAudioContainers[i]);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					} else if (hasVideo) {
						emptyPreview = true;
						String[] dlFileEntryPreviewVideoContainers = PropsUtil.getArray(PropsKeys.DL_FILE_ENTRY_PREVIEW_VIDEO_CONTAINERS);
						
						if (dlFileEntryPreviewVideoContainers.length > 0) {
							previewFileURLs = new String[dlFileEntryPreviewVideoContainers.length];

							for (int i = 0; i < dlFileEntryPreviewVideoContainers.length; i++) {
								try {
									if (VideoProcessorUtil.getPreviewFileSize(fileVersion, dlFileEntryPreviewVideoContainers[i]) > 0) {
										emptyPreview = false;
										previewFileURLs[i] = DLUtil.getPreviewURL(fileEntry, fileVersion, themeDisplay,
												previewQueryString + "&type=" + dlFileEntryPreviewVideoContainers[i]);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						} else {
							emptyPreview = false;

							previewFileURLs = new String[1];

							previewFileURLs[0] = videoThumbnailURL;
						}
					} else if(isEPUB) {
						String urlEPUB = DLUtil.getPreviewURL(fileEntry, fileVersion, themeDisplay, StringPool.BLANK);
						
						previewFileURLs = new String[1];
						previewFileURLs[0] = urlEPUB;
					} else if(hasPDFImages) {
						previewFileURL = "/o/activity-resource-internal-web/js/pdfjs-dist/web/viewer.html?file=" + previewFileURL;
						previewFileURLs = new String[1];
						previewFileURLs[0] = previewFileURL;
					} else {
						previewFileURLs = new String[1];

						previewFileURLs[0] = DLUtil.getPreviewURL(fileEntry, fileVersion, themeDisplay, previewQueryString);
					}

					previewFileURL = previewFileURLs[0];

					if (!hasPDFImages) {
						previewFileCount = 1;
					}
				}
				
				if(previewFileCount == 0) {
					boolean tooLargeForPreview = !DLProcessorRegistryUtil.isPreviewableSize(fileVersion) 
							&& (AudioProcessorUtil.isAudioSupported(fileVersion.getMimeType()) 
									|| ImageProcessorUtil.isImageSupported(fileVersion.getMimeType()) 
									|| PDFProcessorUtil.isDocumentSupported(fileVersion.getMimeType()) 
									|| VideoProcessorUtil.isVideoSupported(fileVersion.getMimeType()));
					renderRequest.setAttribute("tooLargeForPreview", tooLargeForPreview);
					if(!tooLargeForPreview) {
						renderRequest.setAttribute("generatingPreview", AudioProcessorUtil.isAudioSupported(fileVersion) 
								|| ImageProcessorUtil.isImageSupported(fileVersion) || PDFProcessorUtil.isDocumentSupported(fileVersion) 
								|| VideoProcessorUtil.isVideoSupported(fileVersion));
					}
				}
				
				if(Validator.isNotNull(previewFileURL)) {
					previewFileURL = previewFileURL.replace("/documents/", "/o/resource_internal/");
				}
				if(Validator.isNotNull(previewFileURLs)) {
					for(int i = 0; i < previewFileURLs.length; i++) {
						previewFileURLs[i] = previewFileURLs[i].replace("/documents/", "/o/resource_internal/");
					}
				}
				if(Validator.isNotNull(videoThumbnailURL)) {
					videoThumbnailURL = videoThumbnailURL.replace("/documents/", "/o/resource_internal/");
				}
				
				String messageDownload = LanguageUtil.get(themeDisplay.getLocale(), "download") + " (" + TextFormatter.formatStorageSize(fileVersion.getSize(), themeDisplay.getLocale()) + "k)";

				String downloadFileURL = DLUtil.getDownloadURL(fileEntry, fileVersion, themeDisplay, StringPool.BLANK);
				if(Validator.isNotNull(downloadFileURL)) {
					downloadFileURL = downloadFileURL.replace("/documents/", "/o/resource_internal/");
				}
				
				renderRequest.setAttribute("emptyPreview", emptyPreview);

				renderRequest.setAttribute("fileEntry", fileEntry);
				renderRequest.setAttribute("previewFileCount", previewFileCount);

				renderRequest.setAttribute("hasAudio", hasAudio);
				renderRequest.setAttribute("randomNamespace", randomNamespace);
				renderRequest.setAttribute("hasImages", hasImages);
				renderRequest.setAttribute("hasPDFImages", hasPDFImages);
				renderRequest.setAttribute("isEPUB", isEPUB);
				renderRequest.setAttribute("showImageContainer", showImageContainer);
				renderRequest.setAttribute("previewFileURL", previewFileURL);
				renderRequest.setAttribute("hasVideo", hasVideo);
				renderRequest.setAttribute("previewFileURLs", previewFileURLs);
				renderRequest.setAttribute("videoThumbnailURL", videoThumbnailURL);
				renderRequest.setAttribute("messageDownload", messageDownload);
				renderRequest.setAttribute("downloadFileURL", downloadFileURL);
				renderRequest.setAttribute("fileVersion", fileVersion);

			} else {
				AssetRendererFactory<?> assetRendererFactory = AssetRendererFactoryRegistryUtil
						.getAssetRendererFactoryByClassName(assetEntry.getClassName());
				AssetRenderer<?> assetRenderer = assetRendererFactory.getAssetRenderer(assetEntry.getClassPK());
				renderRequest.setAttribute("assetRendererFactory", assetRendererFactory);
				renderRequest.setAttribute("assetRenderer", assetRenderer);
			}

			renderRequest.setAttribute("assetEntry", assetEntry);

		} catch (PortalException e) {
			e.printStackTrace();
		}

		return "/view.jsp";
	}
}
