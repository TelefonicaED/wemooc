package com.ted.lms.web.internal;

import com.liferay.item.selector.ItemSelector;
import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.criteria.FileEntryItemSelectorReturnType;
import com.liferay.item.selector.criteria.image.criterion.ImageItemSelectorCriterion;
import com.liferay.item.selector.criteria.upload.criterion.UploadItemSelectorCriterion;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.item.selector.criterion.CourseItemSelectorCriterion;
import com.ted.lms.util.LMSPrefsPropsValues;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

@Component(service = ModulesItemSelectorHelper.class)
public class ModulesItemSelectorHelper {
	public String getItemSelectorURL(RequestBackedPortletURLFactory requestBackedPortletURLFactory, ThemeDisplay themeDisplay, String itemSelectedEventName) {

		if (this.itemSelector == null) {
			return null;
		}

		List<ItemSelectorReturnType> desiredItemSelectorReturnTypes = new ArrayList<>();

		desiredItemSelectorReturnTypes.add(new FileEntryItemSelectorReturnType());

		CourseItemSelectorCriterion modulesItemSelectorCriterion = new CourseItemSelectorCriterion();

		modulesItemSelectorCriterion.setDesiredItemSelectorReturnTypes(desiredItemSelectorReturnTypes);

		ImageItemSelectorCriterion imageItemSelectorCriterion = new ImageItemSelectorCriterion();

		imageItemSelectorCriterion.setDesiredItemSelectorReturnTypes(desiredItemSelectorReturnTypes);

		List<ItemSelectorReturnType> uploadCriterionDesiredItemSelectorReturnTypes = new ArrayList<>();

		uploadCriterionDesiredItemSelectorReturnTypes.add(new FileEntryItemSelectorReturnType());

		PortletURL uploadURL = requestBackedPortletURLFactory.createActionURL(LMSPortletKeys.MODULE);

		uploadURL.setParameter(ActionRequest.ACTION_NAME, "/course/upload_image");

		String[] extensions = LMSPrefsPropsValues.getModuleImageExtensions(themeDisplay.getCompanyId());
		long maxSize = LMSPrefsPropsValues.getModuleImageMaxSize(themeDisplay.getCompanyId());

		UploadItemSelectorCriterion uploadItemSelectorCriterion = new UploadItemSelectorCriterion(
				LMSPortletKeys.MODULE, uploadURL.toString(),
				LanguageUtil.get(themeDisplay.getLocale(), "course-images"),
				maxSize, extensions);

		uploadItemSelectorCriterion.setDesiredItemSelectorReturnTypes(uploadCriterionDesiredItemSelectorReturnTypes);
		
		PortletURL itemSelectorURL = this.itemSelector.getItemSelectorURL(
			requestBackedPortletURLFactory, itemSelectedEventName,
			modulesItemSelectorCriterion, imageItemSelectorCriterion,
			uploadItemSelectorCriterion);

		return itemSelectorURL.toString();
	}

	@Reference(
		cardinality = ReferenceCardinality.OPTIONAL,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	private volatile ItemSelector itemSelector;
}
