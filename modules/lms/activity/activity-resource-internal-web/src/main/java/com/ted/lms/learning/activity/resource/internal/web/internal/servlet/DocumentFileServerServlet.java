package com.ted.lms.learning.activity.resource.internal.web.internal.servlet;

import com.liferay.document.library.kernel.document.conversion.DocumentConversionUtil;
import com.liferay.document.library.kernel.exception.NoSuchFileEntryException;
import com.liferay.document.library.kernel.exception.NoSuchFileException;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.util.AudioProcessorUtil;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.document.library.kernel.util.ImageProcessorUtil;
import com.liferay.document.library.kernel.util.PDFProcessor;
import com.liferay.document.library.kernel.util.PDFProcessorUtil;
import com.liferay.document.library.kernel.util.VideoProcessor;
import com.liferay.document.library.kernel.util.VideoProcessorUtil;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.flash.FlashMagicBytesUtil;
import com.liferay.portal.kernel.image.ImageBag;
import com.liferay.portal.kernel.image.ImageToolUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Image;
import com.liferay.portal.kernel.model.ImageConstants;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileShortcut;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = {
		"osgi.http.whiteboard.context.path=/",
		"osgi.http.whiteboard.servlet.pattern=/resource_internal/*"
	},
	service = Servlet.class
)
public class DocumentFileServerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Set<String> _acceptRangesMimeTypes = SetUtil.fromArray(PropsUtil.getArray(PropsKeys.WEB_SERVER_SERVLET_ACCEPT_RANGES_MIME_TYPES));
	
	private static Log log = LogFactoryUtil.getLog(DocumentFileServerServlet.class);
	
	/**
	 * Procesa los metodos HTTP GET y POST.<br>
	 * Busca en la ruta que se le ha pedido el comienzo del directorio
	 * "contenidos" y sirve el fichero.
	 */
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
			IOException {
		
		log.debug("******************************SERVLET**************************");

		try {

			String path = HttpUtil.fixPath(request.getPathInfo());
			log.debug("path http: " + path);
			String[] pathArray = StringUtil.split(path, CharPool.SLASH);
			
			sendFile(request, response, pathArray);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Error en el processRequest() de ServidorArchivos: "
							+ e.getMessage());
		}

	}
	
	protected void sendFile(HttpServletRequest request, HttpServletResponse response, String[] pathArray) throws Exception {

		// Retrieve file details

		FileEntry fileEntry = getFileEntry(pathArray);

		if (fileEntry == null) {
			throw new NoSuchFileEntryException();
		}

		String version = ParamUtil.getString(request, "version");

		if (Validator.isNull(version)) {
			if (Validator.isNotNull(fileEntry.getVersion())) {
				version = fileEntry.getVersion();
			}
		}

		String tempFileId = DLUtil.getTempFileId(fileEntry.getFileEntryId(), version);

		FileVersion fileVersion = fileEntry.getFileVersion(version);

		if ((ParamUtil.getInteger(request, "height") > 0) || (ParamUtil.getInteger(request, "width") > 0)) {

			InputStream inputStream = fileVersion.getContentStream(true);

			Image image = ImageToolUtil.getImage(inputStream);

			writeImage(image, request, response);

			return;
		}

		String fileName = fileVersion.getFileName();

		// Handle requested conversion

		boolean converted = false;

		String targetExtension = ParamUtil.getString(request, "targetExtension");
		int imageThumbnail = ParamUtil.getInteger(request, "imageThumbnail");
		int documentThumbnail = ParamUtil.getInteger(request, "documentThumbnail");
		int previewFileIndex = ParamUtil.getInteger(request, "previewFileIndex");
		boolean audioPreview = ParamUtil.getBoolean(request, "audioPreview");
		boolean imagePreview = ParamUtil.getBoolean(request, "imagePreview");
		boolean videoPreview = ParamUtil.getBoolean(request, "videoPreview");
		int videoThumbnail = ParamUtil.getInteger(request, "videoThumbnail");

		InputStream inputStream = null;
		long contentLength = 0;

		if ((imageThumbnail > 0) && (imageThumbnail <= 3)) {
			fileName = FileUtil.stripExtension(fileName).concat(StringPool.PERIOD).concat(ImageProcessorUtil.getThumbnailType(fileVersion));

			int thumbnailIndex = imageThumbnail - 1;

			inputStream = ImageProcessorUtil.getThumbnailAsStream(fileVersion, thumbnailIndex);
			contentLength = ImageProcessorUtil.getThumbnailFileSize(fileVersion, thumbnailIndex);

			converted = true;
		}
		else if ((documentThumbnail > 0) && (documentThumbnail <= 3)) {
			fileName = FileUtil.stripExtension(fileName).concat(StringPool.PERIOD).concat(PDFProcessor.THUMBNAIL_TYPE);

			int thumbnailIndex = documentThumbnail - 1;

			inputStream = PDFProcessorUtil.getThumbnailAsStream(fileVersion, thumbnailIndex);
			contentLength = PDFProcessorUtil.getThumbnailFileSize(fileVersion, thumbnailIndex);

			converted = true;
		}
		else if (previewFileIndex > 0) {
			fileName = FileUtil.stripExtension(fileName).concat(StringPool.PERIOD).concat(PDFProcessor.PREVIEW_TYPE);
			inputStream = PDFProcessorUtil.getPreviewAsStream(fileVersion, previewFileIndex);
			contentLength = PDFProcessorUtil.getPreviewFileSize(fileVersion, previewFileIndex);

			converted = true;
		}
		else if (audioPreview || videoPreview) {
			String type = ParamUtil.getString(request, "type");

			fileName = FileUtil.stripExtension(fileName).concat(
				StringPool.PERIOD).concat(type);

			if (audioPreview) {
				inputStream = AudioProcessorUtil.getPreviewAsStream(fileVersion, type);
				contentLength = AudioProcessorUtil.getPreviewFileSize(fileVersion, type);
			}
			else {
				inputStream = VideoProcessorUtil.getPreviewAsStream(fileVersion, type);
				contentLength = VideoProcessorUtil.getPreviewFileSize(fileVersion, type);
			}

			converted = true;
		}
		else if (imagePreview) {
			String type = ImageProcessorUtil.getPreviewType(fileVersion);

			fileName = FileUtil.stripExtension(fileName).concat(StringPool.PERIOD).concat(type);

			inputStream = ImageProcessorUtil.getPreviewAsStream(fileVersion);

			contentLength = ImageProcessorUtil.getPreviewFileSize(fileVersion);

			converted = true;
		}
		else if ((videoThumbnail > 0) && (videoThumbnail <= 3)) {
			fileName = FileUtil.stripExtension(fileName).concat(StringPool.PERIOD).concat(VideoProcessor.THUMBNAIL_TYPE);

			int thumbnailIndex = videoThumbnail - 1;

			inputStream = VideoProcessorUtil.getThumbnailAsStream(fileVersion, thumbnailIndex);
			contentLength = VideoProcessorUtil.getThumbnailFileSize(fileVersion, thumbnailIndex);

			converted = true;
		}
		else {
			inputStream = fileVersion.getContentStream(true);
			contentLength = fileVersion.getSize();

			if (Validator.isNotNull(targetExtension)) {
				File convertedFile = DocumentConversionUtil.convert(tempFileId, inputStream, fileVersion.getExtension(),targetExtension);

				if (convertedFile != null) {
					fileName = FileUtil.stripExtension(fileName).concat(StringPool.PERIOD).concat(targetExtension);
					inputStream = new FileInputStream(convertedFile);
					contentLength = convertedFile.length();

					converted = true;
				}
			}
		}

		FlashMagicBytesUtil.Result flashMagicBytesUtilResult = FlashMagicBytesUtil.check(inputStream);

		if (flashMagicBytesUtilResult.isFlash()) {
			fileName = FileUtil.stripExtension(fileName) + ".swf";
		}

		inputStream = flashMagicBytesUtilResult.getInputStream();

		// Determine proper content type

		String contentType = null;

		if (converted) {
			contentType = MimeTypesUtil.getContentType(fileName);
		} else {
			contentType = fileVersion.getMimeType();
		}

		if (log.isDebugEnabled()) {
			log.debug("Content type set to " + contentType);
		}

		// Send file

		if (isSupportsRangeHeader(contentType)) {
			ServletResponseUtil.sendFileWithRangeHeader(request, response, fileName, inputStream, contentLength,contentType);
		} else {
			boolean download = ParamUtil.getBoolean(request, "download");

			if (download) {
				ServletResponseUtil.sendFile(request, response, fileName, inputStream, contentLength,contentType, HttpHeaders.CONTENT_DISPOSITION_ATTACHMENT);
			}
			else {
				ServletResponseUtil.sendFile(request, response, fileName, inputStream, contentLength, contentType);
			}
		}
	}

	/**
	 * Procesa el metodo HTTP GET.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Procesa el metodo HTTP POST.
	 */
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected FileEntry getFileEntry(String[] pathArray) throws Exception {
		log.debug("*****pathArray*********");
		for(String path: pathArray) {
			log.debug("path: " + path);
		}
		if (pathArray.length == 1) {
			long fileShortcutId = GetterUtil.getLong(pathArray[0]);

			FileShortcut dlFileShortcut = DLAppLocalServiceUtil.getFileShortcut(fileShortcutId);

			return DLAppLocalServiceUtil.getFileEntry(dlFileShortcut.getToFileEntryId());
		} else if (pathArray.length == 2) {
			long groupId = GetterUtil.getLong(pathArray[0]);

			return DLAppLocalServiceUtil.getFileEntryByUuidAndGroupId(pathArray[1], groupId);
		} else if (pathArray.length == 3) {
			long groupId = GetterUtil.getLong(pathArray[0]);
			long folderId = GetterUtil.getLong(pathArray[1]);

			String fileName = HttpUtil.decodeURL(pathArray[2]);

			if (fileName.contains(StringPool.QUESTION)) {
				fileName = fileName.substring(0, fileName.indexOf(StringPool.QUESTION));
			}

			return DLAppLocalServiceUtil.getFileEntry(groupId, folderId, fileName);
		}
		else {
			long groupId = GetterUtil.getLong(pathArray[0]);

			String uuid = pathArray[3];

			return DLAppLocalServiceUtil.getFileEntryByUuidAndGroupId(uuid, groupId);
		}
	}	
	
	protected void writeImage(Image image, HttpServletRequest request, HttpServletResponse response) throws PortalException {

		if (image == null) {
			return;
		}

		String contentType = null;

		String type = image.getType();

		if (!type.equals(ImageConstants.TYPE_NOT_AVAILABLE)) {
			contentType = MimeTypesUtil.getExtensionContentType(type);

			response.setContentType(contentType);
		}

		String fileName = ParamUtil.getString(request, "fileName");

		byte[] bytes = getImageBytes(request, image);

		try {
			if (Validator.isNotNull(fileName)) {
				ServletResponseUtil.sendFile(request, response, fileName, bytes, contentType);
			}else {
				ServletResponseUtil.write(response, bytes);
			}
		} catch (Exception e) {
			if (log.isWarnEnabled()) {
				log.warn(e, e);
			}
		}
	}
	
	protected byte[] getImageBytes(HttpServletRequest request, Image image) throws PortalException {

			byte[] textObj = image.getTextObj();

			if ((textObj == null) || (textObj.length == 0)) {
				throw new NoSuchFileException();
			}

			try {
				if (!GetterUtil.getBoolean(PropsUtil.get(PropsKeys.IMAGE_AUTO_SCALE))) {
					return textObj;
				}

				ImageBag imageBag = null;

				if (image.getImageId() == 0) {
					imageBag = ImageToolUtil.read(textObj);

					RenderedImage renderedImage = imageBag.getRenderedImage();

					image.setHeight(renderedImage.getHeight());
					image.setWidth(renderedImage.getWidth());
				}

				int height = ParamUtil.getInteger(request, "height", image.getHeight());
				int width = ParamUtil.getInteger(request, "width", image.getWidth());

				if ((height >= image.getHeight()) && (width >= image.getWidth())) {
					return textObj;
				}

				if (image.getImageId() != 0) {
					imageBag = ImageToolUtil.read(textObj);
				}

				RenderedImage renderedImage = ImageToolUtil.scale(imageBag.getRenderedImage(), height, width);

				return ImageToolUtil.getBytes(renderedImage, imageBag.getType());
			}
			catch (Exception e) {
				if (log.isWarnEnabled()) {
					log.warn("Error scaling image " + image.getImageId(), e);
				}
			}

			return textObj;
		}

	protected boolean isSupportsRangeHeader(String contentType) {
		return _acceptRangesMimeTypes.contains(contentType);
	}
}
