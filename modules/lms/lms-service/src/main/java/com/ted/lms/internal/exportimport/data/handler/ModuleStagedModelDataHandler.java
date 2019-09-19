package com.ted.lms.internal.exportimport.data.handler;

import com.liferay.document.library.kernel.exception.NoSuchFileException;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.exportimport.content.processor.ExportImportContentProcessor;
import com.liferay.exportimport.data.handler.base.BaseStagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.ExportImportPathUtil;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataException;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelModifiedDateComparator;
import com.liferay.exportimport.kernel.xstream.XStreamAliasRegistryUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Image;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ImageLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.trash.TrashHandler;
import com.liferay.portal.kernel.trash.TrashHandlerRegistryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portlet.documentlibrary.lar.FileEntryUtil;
import com.ted.lms.internal.exportimport.creation.strategy.ModuleCreationStrategy;
import com.ted.lms.model.Module;
import com.ted.lms.model.impl.ModuleImpl;
import com.ted.lms.service.ModuleLocalService;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

@Component(
	immediate = true, 
	service = StagedModelDataHandler.class
)
public class ModuleStagedModelDataHandler extends BaseStagedModelDataHandler<Module> {
	
	private static final Log log = LogFactoryUtil.getLog(ModuleStagedModelDataHandler.class);

	public static final String[] CLASS_NAMES = {Module.class.getName()};

	@Activate
	protected void activate() {
		XStreamAliasRegistryUtil.register(ModuleImpl.class, "Module");
	}
	
	@Override
	public String[] getClassNames() {
	    return CLASS_NAMES;
	}
	
	@Override
	public void deleteStagedModel(Module module) throws PortalException {
		log.debug("deleteStageModel: " + module.getModuleId());
		moduleLocalService.deleteModule(module);
	}

	@Override
	public void deleteStagedModel(String uuid, long groupId, String className, String extraData) throws PortalException {
		Module module = fetchStagedModelByUuidAndGroupId(uuid, groupId);

		if (module != null) {
			deleteStagedModel(module);
		}
	}
	
	@Override
	public void restoreStagedModel(
			PortletDataContext portletDataContext, Module stagedModel)
		throws PortletDataException {

		log.debug("restoreStagedModel: " + stagedModel.getModuleId());
		
		try {
			doRestoreStagedModel(portletDataContext, stagedModel);
		}
		catch (PortletDataException pde) {
			throw pde;
		}
		catch (Exception e) {
			throw new PortletDataException(e);
		}
	}
	
	@Override
	protected void doRestoreStagedModel(
			PortletDataContext portletDataContext, Module module) throws Exception {
		
		log.debug("doRestoreStagedModel: " + module.getModuleId());

		Module existingModule = fetchStagedModelByUuidAndGroupId(
			module.getUuid(), portletDataContext.getScopeGroupId());

		if ((existingModule == null) || !existingModule.isInTrash()) {
			return;
		}

		TrashHandler trashHandler = TrashHandlerRegistryUtil.getTrashHandler(Module.class.getName());

		if (trashHandler.isRestorable(existingModule.getModuleId())) {
			long userId = portletDataContext.getUserId(module.getUserUuid());

			trashHandler.restoreTrashEntry(userId, existingModule.getModuleId());
		}
	}
	
	@Override
	public Module fetchStagedModelByUuidAndGroupId(String uuid, long groupId) {
		return moduleLocalService.fetchModuleByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public List<Module> fetchStagedModelsByUuidAndCompanyId(String uuid, long companyId) {

		return moduleLocalService.getModulesByUuidAndCompanyId(uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,new StagedModelModifiedDateComparator<Module>());
	}
	
	@Override
	public String getDisplayName(Module module) {
	    return module.getTitleCurrentValue();
	}
	
	@Override
	public boolean validateReference(PortletDataContext portletDataContext, Element referenceElement) {

		log.debug("validateReference: " + referenceElement);
		
		validateMissingGroupReference(portletDataContext, referenceElement);

		String uuid = referenceElement.attributeValue("uuid");

		Map<Long, Long> groupIds =(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(Group.class);

		long groupId = GetterUtil.getLong(referenceElement.attributeValue("group-id"));

		groupId = MapUtil.getLong(groupIds, groupId);

		String displayName = referenceElement.attributeValue("display-name");
		
		log.debug("validamos las referencias que no están: " + uuid + " - " + groupId + " - " + displayName);

		return validateMissingReference(uuid, groupId, displayName);
	}
	
	/**
	 * Añadimos el objeto al portletDataContext
	 */
	@Override
	protected void doExportStagedModel(PortletDataContext portletDataContext, Module module) throws Exception {
		
		Element moduleElement = portletDataContext.getExportDataElement(module);
		log.debug("moduleElement: " + moduleElement.asXML());

		if (Validator.isNotNull(module.getSmallImageId())) {
			Image smallImage = imageLocalService.fetchImage(module.getSmallImageId());

			if ((smallImage != null) && (smallImage.getTextObj() != null)) {
				String smallImagePath = ExportImportPathUtil.getModelPath(module,smallImage.getImageId() + StringPool.PERIOD +smallImage.getType());

				moduleElement.addAttribute("small-image-path", smallImagePath);

				module.setSmallImageType(smallImage.getType());

				portletDataContext.addZipEntry(
					smallImagePath, smallImage.getTextObj());
			} else {
				if (log.isWarnEnabled()) {
					StringBundler sb = new StringBundler(4);

					sb.append("Unable to export small image");
					sb.append(module.getSmallImageId());
					sb.append(" to module ");
					sb.append(module.getModuleId());

					log.warn(sb.toString()); 
				}

				module.setSmallImageId(0);
			}
	    }
		
		String modulePath = ExportImportPathUtil.getModelPath(module);
		moduleElement.addAttribute("path", modulePath);
		
		log.debug("modulePath: " + modulePath);
		
		for (FileEntry fileEntry : module.getImagesFileEntries()) {
			log.debug("fileEntry: " + fileEntry.getFileName());
			StagedModelDataHandlerUtil.exportReferenceStagedModel(portletDataContext, module, fileEntry,PortletDataContext.REFERENCE_TYPE_WEAK);
		}
		
		String description = moduleExportImportContentProcessor.replaceExportContentReferences(portletDataContext, module, module.getDescription(),
					true,false);

		module.setDescription(description);
		
		portletDataContext.addZipEntry(modulePath, module);
	}
	
	
	@Override
	protected void doImportStagedModel(PortletDataContext portletDataContext, Module module) throws Exception {
		
		long userId = portletDataContext.getUserId(module.getUserUuid());

		long authorId = moduleCreationStrategy.getAuthorUserId(portletDataContext, module);

		if (authorId != ModuleCreationStrategy.USE_DEFAULT_USER_ID_STRATEGY) {
			userId = authorId;
		}
		
		long groupId = portletDataContext.getScopeGroupId();
		
		log.debug("doImportStagedModel: " + userId + " - " + authorId + " - " + groupId);
		
		log.debug("antigua descripción: " + module.getDescription());

		module.setDescription(moduleExportImportContentProcessor.replaceImportContentReferences(portletDataContext, module, module.getDescription()));

		String description = module.getDescription();

		description = moduleExportImportContentProcessor.replaceImportContentReferences(portletDataContext, module, description);

		module.setDescription(description);

		String newDescription = moduleCreationStrategy.getTransformedDescription(portletDataContext, module);

		if (newDescription != ModuleCreationStrategy.MODULE_DESCRIPTION_UNCHANGED) {
			module.setDescription(newDescription);
		}
		
		log.debug("new descripción: " + module.getDescription());
		
	    try {
	    	
	    	Element moduleElement = portletDataContext.getImportDataStagedModelElement(module);
			
			List<Element> attachmentElements = portletDataContext.getReferenceDataElements(module, DLFileEntry.class, PortletDataContext.REFERENCE_TYPE_WEAK);

			log.debug("cargamos los attachment");
			
			for (Element attachmentElement : attachmentElements) {
				String path = attachmentElement.attributeValue("path");
				
				log.debug("attachment: " + path);

				FileEntry fileEntry = (FileEntry)portletDataContext.getZipEntryAsObject(path);

				InputStream inputStream = null;

				try {
					String binPath = attachmentElement.attributeValue("bin-path");

					if (Validator.isNull(binPath) && portletDataContext.isPerformDirectBinaryImport()) {

						try {
							inputStream = FileEntryUtil.getContentStream(fileEntry);
						} catch (NoSuchFileException nsfe) {
							if (log.isDebugEnabled()) {
								log.debug(
									"Unable to import attachment for " +
										"file entry " + fileEntry.getFileEntryId(), nsfe);
							}
						}
					} else {
						inputStream = portletDataContext.getZipEntryAsInputStream(binPath);
					}

					if (inputStream == null) {
						if (log.isWarnEnabled()) {
							log.warn(
								"Unable to import attachment for file " +
									"entry " + fileEntry.getFileEntryId());
						}
					}
				}finally {
					StreamUtil.cleanUp(true, inputStream);
				}
			}
			
			boolean addGroupPermissions = moduleCreationStrategy.addGroupPermissions(portletDataContext, module);
			
			boolean addGuestPermissions = moduleCreationStrategy.addGuestPermissions(portletDataContext, module);
			
			ServiceContext serviceContext = portletDataContext.createServiceContext(module);
			
			serviceContext.setAddGroupPermissions(addGroupPermissions);
			serviceContext.setAddGuestPermissions(addGuestPermissions);	

		    Module importedModule = null;
		    
		    String moduleUuid = moduleElement.attributeValue("module-uuid");

		    if (portletDataContext.isDataStrategyMirror()) {
		    	log.debug("data strategry mirror");
		    	
		    	serviceContext.setUuid(module.getUuid());
				serviceContext.setAttribute("moduleUuid", moduleUuid);

		        Module existingModule = moduleLocalService.fetchModuleByUuidAndGroupId(module.getUuid(), portletDataContext.getScopeGroupId());

		        if (existingModule == null) {
		        	log.debug("no existía el módulo, lo creamos");
		            serviceContext.setUuid(module.getUuid());
		            importedModule = moduleLocalService.addModule(             
		            		userId, groupId, module.getTitleMap(),module.getDescriptionMap(), module.getStartDate(), module.getEndDate(), 
		            		module.getAllowedTime(), null, module.getModuleEvalId(), module.getModuleExtraData(), serviceContext);
		            importedModule = moduleLocalService.updateOrder(userId, importedModule, module.getPriority());
		        } else {
		        	log.debug("el módulo ya existe: " + module.getModuleId());
		            importedModule = moduleLocalService.updateModule(userId, module.getModuleId(), module.getTitleMap(),module.getDescriptionMap(), 
		            		module.getStartDate(), module.getEndDate(), module.getAllowedTime(), null, module.getModuleEvalId(),  module.getModuleExtraData(),
		            		serviceContext);
		            importedModule = moduleLocalService.updateOrder(userId, importedModule, module.getPriority());
		        }
		        
			    // Small image
		        long existingSmallImageId = 0;
		        
		        if (existingModule != null) {
		        	existingSmallImageId = existingModule.getSmallImageId();
		        }
			    
			    Map<Long, Long> fileEntryIds = (Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(FileEntry.class);

				if (module.getSmallImageId() > 0) {
					long smallImageId = MapUtil.getLong(fileEntryIds, module.getSmallImageId(), 0);
		
					importedModule.setSmallImageId(smallImageId);
		
					importedModule = moduleLocalService.updateModule(importedModule);
		
					if ((existingSmallImageId != 0) && (module.getSmallImageId() == 0)) {
		
						PortletFileRepositoryUtil.deletePortletFileEntry(existingSmallImageId);
					}
				}
				
		    } else {
		    	log.debug("lo creamos porque no es espejo");
		        importedModule = moduleLocalService.addModule(             
	            		userId, groupId, module.getTitleMap(),module.getDescriptionMap(), module.getStartDate(), module.getEndDate(), module.getAllowedTime(), 
	            		null, module.getModuleEvalId(), module.getModuleExtraData(), serviceContext);
		        importedModule = moduleLocalService.updateOrder(userId, importedModule, module.getPriority());
		    }
		    
		    portletDataContext.importClassedModel(module, importedModule);
		    
		    Map<Long, Long> moduleIds = (Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(Module.class);

		    log.debug("******añadimos relación: " + module.getPrimaryKey() + " - " + importedModule.getPrimaryKey());
		    
		    moduleIds.put(module.getPrimaryKey(), importedModule.getPrimaryKey());
	    }finally {
	    	ServiceContextThreadLocal.popServiceContext();
		}  
	}
	
	@Override
	protected void doImportMissingReference(PortletDataContext portletDataContext, String uuid, long groupId, long moduleId) throws PortletDataException {

		Module existingModule = fetchMissingReference(uuid, groupId);

		if (existingModule == null) {
			return;
		}

		Map<Long, Long> moduleIds = (Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(Module.class);

		moduleIds.put(moduleId, existingModule.getModuleId());
	}
	
	protected boolean validateMissingReference(String uuid, long groupId, String name) {

			Module existingStagedModel = fetchMissingReference(uuid, groupId);

			if (existingStagedModel == null) {
				return false;
			}

			return true;
		}
	
	protected ModuleLocalService getModuleLocalService() {
	    return this.moduleLocalService;
	}

	@Reference(unbind = "-")
	protected void setModuleLocalService(ModuleLocalService moduleLocalService) {
	    this.moduleLocalService = moduleLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setImageLocalService(ImageLocalService imageLocalService) {
		this.imageLocalService = imageLocalService;
	}
	
	@Reference(
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY,
		target = "(model.class.name=com.ted.lms.model.Module)"
	)
	private volatile ExportImportContentProcessor<String> moduleExportImportContentProcessor;

	private ModuleLocalService moduleLocalService;
	private ImageLocalService imageLocalService;
	
	@Reference(unbind = "-")
	protected void setModuleCreationStrategy(ModuleCreationStrategy moduleCreationStrategy) {
		this.moduleCreationStrategy = moduleCreationStrategy;
	}
	
	private ModuleCreationStrategy moduleCreationStrategy;
	
	@Reference
	private Portal portal;
}
