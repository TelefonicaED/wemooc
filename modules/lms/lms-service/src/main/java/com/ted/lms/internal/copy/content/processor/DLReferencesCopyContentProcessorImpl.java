package com.ted.lms.internal.copy.content.processor;

import com.liferay.document.library.kernel.exception.DuplicateFileEntryException;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.document.library.kernel.service.DLFolderLocalService;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RepositoryLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.ted.lms.constants.LMSConstants;
import com.ted.lms.copy.content.processor.DLReferencesCopyContentProcessor;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	service = DLReferencesCopyContentProcessor.class
)
public class DLReferencesCopyContentProcessorImpl implements DLReferencesCopyContentProcessor{
	
	private long folderId;
	
	public String replaceExportDLReferences(String content, long oldGroupId, long newGroupId, long userId)
		throws Exception {
		
		log.debug("vamos a reemplazar el contenido");

		Group oldGroup = _groupLocalService.getGroup(oldGroupId);

		StringBuilder sb = new StringBuilder(content);

		String contextPath = _portal.getPathContext();

		String[] patterns = {
			contextPath.concat("/c/document_library/get_file?"),
			contextPath.concat("/documents/"),
			contextPath.concat("/image/image_gallery?")
		};

		int beginPos = -1;
		int endPos = content.length();
		
		FileEntry oldFileEntry = null;
		FileEntry newFileEntry = null;


		while (true) {
			beginPos = StringUtil.lastIndexOfAny(content, patterns, endPos);
			
			log.debug("beginPos: " + beginPos);

			if (beginPos == -1) {
				break;
			}

			Map<String, String[]> dlReferenceParameters = getDLReferenceParameters(oldGroup.getGroupId(), content,beginPos + contextPath.length(), endPos);
			
			long groupId = MapUtil.getLong(dlReferenceParameters, "groupId");
			
			if(groupId == oldGroupId) {
	
				oldFileEntry = getFileEntry(dlReferenceParameters);
	
				if (oldFileEntry == null) {
					endPos = beginPos - 1;
	
					continue;
				}
	
				endPos = MapUtil.getInteger(dlReferenceParameters, "endPos");
				
				try {
					log.debug("*****Creamos archivo con: " + newGroupId + " - " + oldFileEntry.getFileName());
					newFileEntry = PortletFileRepositoryUtil.addPortletFileEntry(
							newGroupId, userId, null, 0, LMSConstants.SERVICE_NAME, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, oldFileEntry.getContentStream(),
							oldFileEntry.getFileName(), oldFileEntry.getMimeType(), true);
					log.debug("creamos el recurso" + oldFileEntry.getFileName() + " - " + newFileEntry.getFolderId() + " - " + newFileEntry.getGroupId());
				} catch (DuplicateFileEntryException e) {
					String fileName = Time.getShortTimestamp() + oldFileEntry.getFileName();
					try {
						log.debug("*****Creamos archivo con: " + newGroupId + " - " + Time.getShortTimestamp() + oldFileEntry.getFileName());
						newFileEntry = PortletFileRepositoryUtil.addPortletFileEntry(
								newGroupId, userId, null, 0, LMSConstants.SERVICE_NAME, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, oldFileEntry.getContentStream(),
								fileName, oldFileEntry.getMimeType(), true);
						log.debug("creamos el recurso duplicado" + oldFileEntry.getFileName() + " - " + newFileEntry.getFolderId() + " - " + newFileEntry.getGroupId());
						folderId = newFileEntry.getFolderId();
					}catch(DuplicateFileEntryException d) {
						newFileEntry = PortletFileRepositoryUtil.getPortletFileEntry(newGroupId, folderId, fileName);
					}
				}
	
				String url = DLUtil.getPreviewURL(newFileEntry, newFileEntry.getFileVersion(), null,StringPool.BLANK, false, false);
	
				sb.replace(beginPos, endPos, url);
				
				log.debug("endPos: " + endPos);
			}
			
			endPos = beginPos - 1;
		}

		return sb.toString();
	}
	
	protected Map<String, String[]> getDLReferenceParameters(
		long groupId, String content, int beginPos, int endPos) {

		boolean legacyURL = true;
		String[] stopStrings = _DL_REFERENCE_LEGACY_STOP_STRINGS;

		if (content.startsWith("/documents/", beginPos)) {
			legacyURL = false;
			stopStrings = _DL_REFERENCE_STOP_STRINGS;
		}

		endPos = StringUtil.indexOfAny(content, stopStrings, beginPos, endPos);

		if (endPos == -1) {
			return null;
		}

		Map<String, String[]> map = new HashMap<>();

		String dlReference = content.substring(beginPos, endPos);

		while (dlReference.contains(StringPool.AMPERSAND_ENCODED)) {
			dlReference = dlReference.replace(
				StringPool.AMPERSAND_ENCODED, StringPool.AMPERSAND);
		}

		if (!legacyURL) {
			String[] pathArray = dlReference.split(StringPool.SLASH);

			if (pathArray.length < 3) {
				return map;
			}

			if ("portlet_file_entry".equals(pathArray[2])) {
				map.put("groupId", new String[] {pathArray[3]});
				map.put("title", new String[] {_http.decodeURL(pathArray[4])});
			}
			else {
				map.put("groupId", new String[] {pathArray[2]});

				if (pathArray.length == 5) {
					map.put("folderId", new String[] {pathArray[3]});
					map.put(
						"title", new String[] {_http.decodeURL(pathArray[4])});
				}
			}

			String uuid = _getUuid(dlReference);

			if (Validator.isNotNull(uuid)) {
				map.put("uuid", new String[] {uuid});
			}
		}
		else {
			dlReference = dlReference.substring(
				dlReference.indexOf(CharPool.QUESTION) + 1);

			map = _http.parameterMapFromString(dlReference);

			String[] imageIds = null;

			if (map.containsKey("img_id")) {
				imageIds = map.get("img_id");
			}
			else if (map.containsKey("i_id")) {
				imageIds = map.get("i_id");
			}

			imageIds = ArrayUtil.filter(imageIds, Validator::isNotNull);

			if (ArrayUtil.isNotEmpty(imageIds)) {
				map.put("image_id", imageIds);
			}
		}

		map.put("endPos", new String[] {String.valueOf(endPos)});

		String groupIdString = MapUtil.getString(map, "groupId");

		if (groupIdString.equals("@group_id@")) {
			groupIdString = String.valueOf(groupId);

			map.put("groupId", new String[] {groupIdString});
		}

		return map;
	}

	protected FileEntry getFileEntry(Map<String, String[]> map) {
		if (MapUtil.isEmpty(map)) {
			return null;
		}

		FileEntry fileEntry = null;

		try {
			String uuid = MapUtil.getString(map, "uuid");
			long groupId = MapUtil.getLong(map, "groupId");

			if (Validator.isNotNull(uuid)) {
				fileEntry = _dlAppLocalService.getFileEntryByUuidAndGroupId(
					uuid, groupId);
			}
			else {
				if (map.containsKey("folderId")) {
					long folderId = MapUtil.getLong(map, "folderId");
					String name = MapUtil.getString(map, "name");
					String title = MapUtil.getString(map, "title");

					if (Validator.isNotNull(title)) {
						fileEntry = _dlAppLocalService.getFileEntry(
							groupId, folderId, title);
					}
					else {
						DLFileEntry dlFileEntry =
							_dlFileEntryLocalService.fetchFileEntryByName(
								groupId, folderId, name);

						if (dlFileEntry != null) {
							fileEntry = _dlAppLocalService.getFileEntry(
								dlFileEntry.getFileEntryId());
						}
					}
				}
				else if (map.containsKey("image_id")) {
					DLFileEntry dlFileEntry =
						_dlFileEntryLocalService.fetchFileEntryByAnyImageId(
							MapUtil.getLong(map, "image_id"));

					if (dlFileEntry != null) {
						fileEntry = _dlAppLocalService.getFileEntry(
							dlFileEntry.getFileEntryId());
					}
				}
			}
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
			else if (_log.isWarnEnabled()) {
				_log.warn(e.getMessage());
			}
		}

		return fileEntry;
	}

	private String _getUuid(String s) {
		Matcher matcher = _uuidPattern.matcher(s);

		if (matcher.find()) {
			return matcher.group(0);
		}

		return StringPool.BLANK;
	}

	private static final String[] _DL_REFERENCE_LEGACY_STOP_STRINGS = {
		StringPool.APOSTROPHE, StringPool.APOSTROPHE_ENCODED,
		StringPool.CLOSE_BRACKET, StringPool.CLOSE_CURLY_BRACE,
		StringPool.CLOSE_PARENTHESIS, StringPool.GREATER_THAN,
		StringPool.LESS_THAN, StringPool.PIPE, StringPool.QUOTE,
		StringPool.QUOTE_ENCODED, StringPool.SPACE
	};

	private static final String[] _DL_REFERENCE_STOP_STRINGS = {
		StringPool.APOSTROPHE, StringPool.APOSTROPHE_ENCODED,
		StringPool.CLOSE_BRACKET, StringPool.CLOSE_CURLY_BRACE,
		StringPool.CLOSE_PARENTHESIS, StringPool.GREATER_THAN,
		StringPool.LESS_THAN, StringPool.PIPE, StringPool.QUESTION,
		StringPool.QUOTE, StringPool.QUOTE_ENCODED, StringPool.SPACE
	};

	private static final Log _log = LogFactoryUtil.getLog(DLReferencesCopyContentProcessorImpl.class);

	private static final Pattern _uuidPattern = Pattern.compile(
		"[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-" +
			"[a-fA-F0-9]{12}");

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference
	private DLAppLocalService _dlAppLocalService;

	@Reference
	private DLFileEntryLocalService _dlFileEntryLocalService;
	
	@Reference
	private DLFolderLocalService dlFolderLocalService;

	@Reference
	private GroupLocalService _groupLocalService;
	
	@Reference
	private RepositoryLocalService repositoryLocalService;

	@Reference
	private Http _http;

	@Reference
	private Portal _portal;
}
