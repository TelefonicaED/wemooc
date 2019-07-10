package com.ted.lms.internal.exportimport.content.processor;

import com.liferay.document.library.kernel.exception.NoSuchFileEntryException;
import com.liferay.exportimport.content.processor.ExportImportContentProcessor;
import com.liferay.exportimport.kernel.exception.ExportImportContentValidationException;
import com.liferay.exportimport.kernel.lar.ExportImportThreadLocal;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.exception.NoSuchLayoutException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.StagedModel;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.util.StringBundler;
import com.ted.lms.service.ModuleLocalService;
import com.ted.lms.service.util.MissingReferencesExportImportContentProcessor;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = "model.class.name=com.ted.lms.model.LearningActivity",
	service = {
		ExportImportContentProcessor.class,
		LearningActivityExportImportContentProcessor.class
	}
)
public class LearningActivityExportImportContentProcessor implements ExportImportContentProcessor<String> {

	@Override
	public String replaceExportContentReferences(
			PortletDataContext portletDataContext, StagedModel stagedModel,
			String content, boolean exportReferencedContent,
			boolean escapeContent)
		throws Exception {

		content =
				dlReferencesExportImportContentProcessor.
					replaceExportContentReferences(
						portletDataContext, stagedModel, content,
						exportReferencedContent, escapeContent);

		content =
			defaultTextExportImportContentProcessor.
				replaceExportContentReferences(
					portletDataContext, stagedModel, content,
					exportReferencedContent, escapeContent);
		
		
		MissingReferencesExportImportContentProcessor.setOtherGroupIdsMissingReferences(portletDataContext, stagedModel);

		return content;
	}

	@Override
	public String replaceImportContentReferences(
			PortletDataContext portletDataContext, StagedModel stagedModel,
			String content)
		throws Exception {

		content =
				dlReferencesExportImportContentProcessor.
					replaceImportContentReferences(
						portletDataContext, stagedModel, content);

		content =
			defaultTextExportImportContentProcessor.
				replaceImportContentReferences(
					portletDataContext, stagedModel, content);

		return content;
	}

	@Override
	public void validateContentReferences(long groupId, String content)
		throws PortalException {

		dlReferencesExportImportContentProcessor.validateContentReferences(
				groupId, content);

		try {
			defaultTextExportImportContentProcessor.validateContentReferences(
				groupId, content);
		}
		catch (ExportImportContentValidationException |
			   NoSuchFileEntryException | NoSuchLayoutException e) {

			if (ExportImportThreadLocal.isImportInProcess()) {
				if (_log.isDebugEnabled()) {
					StringBundler sb = new StringBundler(8);

					sb.append("An invalid ");

					String type = "page";

					if (e instanceof NoSuchFileEntryException ||
						e.getCause() instanceof NoSuchFileEntryException) {

						type = "file entry";
					}

					sb.append(type);

					sb.append(" was detected during import when validating ");
					sb.append("the content below. This is not an error; it ");
					sb.append("typically means the ");
					sb.append(type);
					sb.append(" was deleted.\n");
					sb.append(content);

					_log.debug(sb.toString());
				}

				return;
			}

			throw e;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LearningActivityExportImportContentProcessor.class);

	@Reference(target = "(model.class.name=java.lang.String)")
	private ExportImportContentProcessor<String>
		defaultTextExportImportContentProcessor;

	@Reference(target = "(content.processor.type=DLReferences)")
	private ExportImportContentProcessor<String>
		dlReferencesExportImportContentProcessor;
	
	@Reference
	private GroupLocalService groupLocalService;

	@Reference
	private ModuleLocalService moduleLocalService;
}
