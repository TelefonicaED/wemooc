package com.ted.lms.internal.exportimport.data.handler;

import com.liferay.exportimport.content.processor.ExportImportContentProcessor;
import com.liferay.exportimport.data.handler.base.BaseStagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.ExportImportPathUtil;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataException;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandler;
import com.liferay.exportimport.staged.model.repository.StagedModelRepository;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Image;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ImageLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Element;
import com.ted.lms.model.Course;
import com.ted.lms.service.CourseLocalService;

import java.io.File;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

@Component(
	immediate = true, 
	service = StagedModelDataHandler.class
)
public class CourseStagedModelDataHandler extends BaseStagedModelDataHandler<Course> {
	
	private static final Log log = LogFactoryUtil.getLog(CourseStagedModelDataHandler.class);

	public static final String[] CLASS_NAMES = {Course.class.getName()};

	@Override
	public String[] getClassNames() {
	    return CLASS_NAMES;
	}
	
	/**
	 * Añadimos el objeto al portletDataContext
	 */
	@Override
	protected void doExportStagedModel(
	        PortletDataContext portletDataContext, Course course)
	    throws Exception {
		
		Element courseElement = portletDataContext.getExportDataElement(course);

		courseElement.addAttribute(
				"course-uuid", course.getUuid());

		if (Validator.isNotNull(course.getSmallImageId())) {
			Image smallImage = imageLocalService.fetchImage(course.getSmallImageId());

			if ((smallImage != null) && (smallImage.getTextObj() != null)) {
				String smallImagePath = ExportImportPathUtil.getModelPath(course,smallImage.getImageId() + StringPool.PERIOD +smallImage.getType());

				courseElement.addAttribute("small-image-path", smallImagePath);

				portletDataContext.addZipEntry(
					smallImagePath, smallImage.getTextObj());
			} else {
				if (log.isWarnEnabled()) {
					StringBundler sb = new StringBundler(4);

					sb.append("Unable to export small image ");
					sb.append(course.getSmallImageId());
					sb.append(" to course ");
					sb.append(course.getCourseId());

					log.warn(sb.toString());
				}

				course.setSmallImageId(0);
			}
	    }

	    Element entryElement = portletDataContext.getExportDataElement(course);

	    portletDataContext.addClassedModel(
	        entryElement, ExportImportPathUtil.getModelPath(course), course);
	}
	
	
	@Override
	protected void doImportStagedModel(
	        PortletDataContext portletDataContext, Course course)
	    throws Exception {
		
		long userId = portletDataContext.getUserId(course.getUserUuid());

		User user = userLocalService.getUser(userId);
		
		String description = course.getDescription();

		course.setDescription(description);
		
		
	    File smallFile = null;
	    
	    try {
	    	
	    	 Element courseElement = portletDataContext.getImportDataStagedModelElement(course);
		
			
			ServiceContext serviceContext =
			        portletDataContext.createServiceContext(course);

		    Course importedCourse = null;
		    
		    String courseUuid = courseElement.attributeValue("course-uuid");

		    if (portletDataContext.isDataStrategyMirror()) {
		    	
		    	serviceContext.setUuid(course.getUuid());
				serviceContext.setAttribute("courseUuid", courseUuid);

		        Course existingCourse =
		            courseLocalService. fetchCourseByUuidAndGroupId(
		                course.getUuid(), portletDataContext.getScopeGroupId());

		        if (existingCourse == null) {

		            serviceContext.setUuid(course.getUuid());
		            importedCourse = null;//courseLocalService.addCourse(course.getTitleMap(), course.getDescriptionMap(), null, null, course.getParentCourseId(), course.getSmallImageId(), course.getRegistrationStartDate(), course.getRegistrationEndDate(), course.getExecutionStartDate(), course.getExecutionEndDate(), 0, typeSite, inscriptionType, courseEvalId, calificationType, maxUsers, welcome, welcomeSubject, welcomeMsg, goodbye, goodbyeSubject, goodbyeMsg, status, serviceContext)
		        }
		        else {
		            importedCourse = existingCourse; //courseLocalService.updateCourse(course.getCourseId(), userId, portletDataContext.getScopeGroupId(), course.getTitleMap(),course.getDescriptionMap(), smallFile, course.getOrder(), 
		            		//course.getStartDate(), course.getEndDate(), course.getAllowedTime(), course.getCourseEvalId(), course.getCourseExtraData(), serviceContext);
		        }
		    }
		    else {
		        importedCourse = null;//courseLocalService.addCourse(             
	            		//userId, portletDataContext.getScopeGroupId(), course.getTitleMap(),course.getDescriptionMap(), smallFile, course.getOrder(), 
	            		//course.getStartDate(), course.getEndDate(), course.getAllowedTime(), course.getCourseEvalId(), course.getCourseExtraData(), serviceContext);
		    }
		    
		    portletDataContext.importClassedModel(course, importedCourse);
		    
		    Map<Long, Long> coursePrimaryKeys = (Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(Course.class + ".primaryKey");

			coursePrimaryKeys.put(course.getPrimaryKey(), importedCourse.getPrimaryKey());
	    }finally {
			if (smallFile != null) {
				smallFile.delete();
			}
		}  
	}
	
	@Override
	protected void doImportMissingReference(PortletDataContext portletDataContext, String uuid, long groupId,
			long courseId)
		throws PortletDataException {

		Course existingCourse = fetchMissingReference(uuid, groupId);

		if (existingCourse == null) {
			return;
		}

		Map<Long, Long> courseIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				Course.class);

		courseIds.put(courseId, existingCourse.getCourseId());
	}
	
	@Override
	public String getDisplayName(Course course) {
	    return course.getTitleCurrentValue();
	}
	
	protected CourseLocalService getCourseLocalService() {
	    return this.courseLocalService;
	}

	@Reference(unbind = "-")
	protected void setCourseLocalService(
			CourseLocalService courseLocalService) {

	    this.courseLocalService = courseLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setImageLocalService(ImageLocalService imageLocalService) {
		this.imageLocalService = imageLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}
	
	private CourseLocalService courseLocalService;
	private ImageLocalService imageLocalService;
	private UserLocalService userLocalService;
	
	//private CourseCreationStrategy courseCreationStrategy;
}
