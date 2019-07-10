package com.ted.lms.service.util;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.portal.kernel.model.StagedModel;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.xml.Element;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MissingReferencesExportImportContentProcessor {

	public static void setOtherGroupIdsMissingReferences(PortletDataContext portletDataContext, StagedModel stagedModel) {
		//Exportamos lo de otros groupIds
		
		Element missingReferences = portletDataContext.getMissingReferencesElement();
		Iterator<Element> elementsMissingReferences = missingReferences.elementIterator();
		Set<Long> groupIds = new HashSet<>();
		Element element = null;
		long groupId = 0;
		String uuid = null;
		String className = null;
		FileEntry fileEntry = null;
		long scopeGroupId = portletDataContext.getScopeGroupId();
		while(elementsMissingReferences.hasNext()) {
			element = elementsMissingReferences.next();
			groupId = 0;
			uuid = null;
			try {
				groupId = Long.parseLong(element.attribute("group-id").getText());
				if(groupId > 0) {
					if(!groupIds.contains(groupId)) {
						groupIds.add(groupId);
					}
					uuid = element.attributeValue("uuid");
					className = element.attributeValue("class-name");
					if(DLFileEntry.class.getName().equals(className)) {
						fileEntry = DLAppLocalServiceUtil.getFileEntryByUuidAndGroupId(uuid, groupId);
						portletDataContext.setGroupId(groupId);
						portletDataContext.setScopeGroupId(groupId);
						StagedModelDataHandlerUtil.exportReferenceStagedModel(
								portletDataContext, stagedModel, fileEntry,
								PortletDataContext.REFERENCE_TYPE_EMBEDDED);
						missingReferences.remove(element);
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		portletDataContext.setGroupId(scopeGroupId);
		portletDataContext.setScopeGroupId(scopeGroupId);
	}
}
