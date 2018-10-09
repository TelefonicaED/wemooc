package com.ted.lms.web.internal.upload;

import com.liferay.item.selector.ItemSelectorUploadResponseHandler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.servlet.ServletResponseConstants;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.upload.UploadResponseHandler;
import com.ted.lms.exception.CourseImageNameException;
import com.ted.lms.exception.CourseImageSizeException;
import com.ted.lms.util.LMSPrefsPropsValues;

import javax.portlet.PortletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = ImageCourseUploadResponseHandler.class)
public class ImageCourseUploadResponseHandler implements UploadResponseHandler {
	
	@Override
	public JSONObject onFailure(PortletRequest portletRequest, PortalException pe) throws PortalException {

		JSONObject jsonObject = this.itemSelectorUploadResponseHandler.onFailure(portletRequest, pe);

		if (pe instanceof CourseImageNameException || pe instanceof CourseImageSizeException) {

			String errorMessage = StringPool.BLANK;
			int errorType = 0;

			if (pe instanceof CourseImageNameException) {
				errorType = ServletResponseConstants.SC_FILE_EXTENSION_EXCEPTION;

				ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(WebKeys.THEME_DISPLAY);
				
				String[] imageExtensions = LMSPrefsPropsValues.getCourseImageExtensions(themeDisplay.getCompanyId());

				errorMessage = StringUtil.merge(imageExtensions);
			} else if (pe instanceof CourseImageSizeException) {
				errorType = ServletResponseConstants.SC_FILE_SIZE_EXCEPTION;
			}

			JSONObject errorJSONObject = JSONFactoryUtil.createJSONObject();

			errorJSONObject.put("errorType", errorType);
			errorJSONObject.put("message", errorMessage);

			jsonObject.put("error", errorJSONObject);
		}

		return jsonObject;
	}

	@Override
	public JSONObject onSuccess(UploadPortletRequest uploadPortletRequest, FileEntry fileEntry) throws PortalException {

		return this.itemSelectorUploadResponseHandler.onSuccess(uploadPortletRequest, fileEntry);
	}

	@Reference
	private ItemSelectorUploadResponseHandler itemSelectorUploadResponseHandler;
}
