package com.ted.lms.web.internal.display.context;

import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetTagServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.trash.TrashHelper;
import com.ted.lms.constants.CourseConstants;
import com.ted.lms.model.Course;
import com.ted.lms.service.CourseLocalServiceUtil;
import com.ted.lms.web.constants.LMSPortletConstants;
import com.ted.lms.web.internal.configuration.CourseAdminPortletInstanceConfiguration;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletRequest;

public class CourseDisplayContext {
	
	
	public CourseDisplayContext(HttpServletRequest request, PortletRequest portletRequest,PortletResponse portletResponse,
			PortletPreferences portletPreferences, CourseAdminPortletInstanceConfiguration courseAdminConfiguration, TrashHelper trashHelper) {
		this.portletRequest = portletRequest;
		this.portletResponse = portletResponse;
		this.trashHelper = trashHelper;
		this.request = request;
		this.portletPreferences = portletPreferences;
		
		this.courseAdminConfiguration = courseAdminConfiguration;
		this.portalPreferences = PortletPreferencesFactoryUtil.getPortalPreferences(this.request);
		
	}
	
	public CourseDisplayContext(HttpServletRequest request, PortletRequest portletRequest,PortletResponse portletResponse,
		PortletPreferences portletPreferences, TrashHelper trashHelper) {

		this(request, portletRequest, portletResponse, portletPreferences, 
				(CourseAdminPortletInstanceConfiguration)request.getAttribute(CourseAdminPortletInstanceConfiguration.class.getName()), trashHelper);

	}
	
	public long getParentCourseId() {
		if(parentCourseId == null) {
			parentCourseId = ParamUtil.getLong(request, "parentCourseId", 0);
		}
		return parentCourseId;
	}
	
	public long getGroupId() {
		if (groupId == null) {
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
			groupId = ParamUtil.getLong(request, "groupId", themeDisplay.getSiteGroupIdOrLiveGroupId());
		}
		return groupId;
	}
	
	public long[] getTagIds() {
		if(tagIds == null) {
			tagIds = ParamUtil.getLongValues(request, "tagIds", null);
		}
		return tagIds;
	}
	
	public long[] getCategoryIds() {
		if(categoryIds == null) {
			categoryIds = ParamUtil.getLongValues(request, "categoryIds", null);
		}
		return categoryIds;
	}
	
	public int getStatus() {
		if(status == null) {
			status = ParamUtil.getInteger(request, "status", WorkflowConstants.STATUS_APPROVED);
		}
		return status;
	}
	
	public boolean containsTagId(long tagId) {
		return ArrayUtil.contains(getTagIds(), tagId);
	}
	
	public boolean containsCategoryId(long categoryId) {
		return ArrayUtil.contains(getCategoryIds(), categoryId);
	}
	
	public boolean isStatusApproved() {
		return getStatus() == WorkflowConstants.STATUS_APPROVED;
	}
	
	public boolean isStatusInactive() {
		return getStatus() == WorkflowConstants.STATUS_INACTIVE;
	}
	
	public boolean isStatusAny() {
		return getStatus() == WorkflowConstants.STATUS_ANY;
	}
	
	public Integer getExecutionStartDateDay() {
		Integer day = null;
		try {
			Date executionStartDate = getExecutionStartDate();
			if(executionStartDate != null) {
				Calendar startDate = Calendar.getInstance();
				startDate.setTime(executionStartDate);
				day = startDate.get(Calendar.DAY_OF_MONTH);
			}
		} catch (PortalException e) {
			e.printStackTrace();
		}
		return day;
	}
	
	public Integer getExecutionStartDateMonth() {
		Integer month = null;
		try {
			Date executionStartDate = getExecutionStartDate();
			if(executionStartDate != null) {
				Calendar startDate = Calendar.getInstance();
				startDate.setTime(executionStartDate);
				month = startDate.get(Calendar.MONTH);
			}
		} catch (PortalException e) {
			e.printStackTrace();
		}
		return month;
	}
	
	public Integer getExecutionStartDateYear() {
		Integer year = null;
		try {
			Date executionStartDate = getExecutionStartDate();
			if(executionStartDate != null) {
				Calendar startDate = Calendar.getInstance();
				startDate.setTime(executionStartDate);
				year = startDate.get(Calendar.YEAR);
			}
		} catch (PortalException e) {
			e.printStackTrace();
		}
		return year;
	}
	
	public Integer getExecutionEndDateDay() {
		Integer day = null;
		try {
			Date executionEndDate = getExecutionEndDate();
			if(executionEndDate != null) {
				Calendar endDate = Calendar.getInstance();
				endDate.setTime(executionEndDate);
				day = endDate.get(Calendar.DAY_OF_MONTH);
			}
		} catch (PortalException e) {
			e.printStackTrace();
		}
		return day;
	}
	
	public Integer getExecutionEndDateMonth() {
		Integer month = null;
		try {
			Date executionEndDate = getExecutionEndDate();
			if(executionEndDate != null) {
				Calendar endDate = Calendar.getInstance();
				endDate.setTime(executionEndDate);
				month = endDate.get(Calendar.MONTH);
			}
		} catch (PortalException e) {
			e.printStackTrace();
		}
		return month;
	}
	
	public Integer getExecutionEndDateYear() {
		Integer year = null;
		try {
			Date executionEndDate = getExecutionEndDate();
			if(executionEndDate != null) {
				Calendar endDate = Calendar.getInstance();
				endDate.setTime(executionEndDate);
				year = endDate.get(Calendar.YEAR);
			}
		} catch (PortalException e) {
			e.printStackTrace();
		}
		return year;
	}
	
	public Date getExecutionStartDate() throws PortalException {
		if(executionStartDate == null) {

			int executionStartDateMonth = ParamUtil.getInteger(request, "executionStartDateMonth", 0);
			int executionStartDateDay = ParamUtil.getInteger(request, "executionStartDateDay", 0);
			int executionStartDateYear = ParamUtil.getInteger(request, "executionStartDateYear", 0);
			
			if(executionStartDateYear > 0) {
				ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
			
				executionStartDate = PortalUtil.getDate(executionStartDateMonth, executionStartDateDay, executionStartDateYear,0, 0, themeDisplay.getTimeZone(), PortalException.class);
			}
		}
		
		return executionStartDate;
	}
	
	public Date getExecutionEndDate() throws PortalException {
		if(executionEndDate == null) {
			int executionEndDateMonth = ParamUtil.getInteger(request, "executionEndDateMonth", 0);
			int executionEndDateDay = ParamUtil.getInteger(request, "executionEndDateDay", 0);
			int executionEndDateYear = ParamUtil.getInteger(request, "executionEndDateYear", 0);
			
			if(executionEndDateYear > 0) {
				ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
			
				executionEndDate = PortalUtil.getDate(executionEndDateMonth, executionEndDateDay, executionEndDateYear,0, 0, themeDisplay.getTimeZone(), PortalException.class);
			}
		}
		
		return executionEndDate;
	}
	
	public CourseAdminPortletInstanceConfiguration getCourseAdminConfiguration() {
		return courseAdminConfiguration;
	}
	
	public List<Group> getSearchListGroups(){
		if(groups == null) {
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
			groups = CourseLocalServiceUtil.getDistinctCourseGroups(themeDisplay.getCompanyId());
		}
		return groups;
	}
	
	public List<AssetTag> getSearchListAssetTags(){
		if(listAssetTags == null) {
			long[] groupIds  = {getGroupId()};
			listAssetTags = AssetTagServiceUtil.getGroupsTags(groupIds);
		}
		
		return listAssetTags;
	}
	
	public List<AssetVocabulary> getSearchListAssetVocabularies(){
		if(listAssetVocabularies == null) {
			long[] groupIds = {getGroupId()};
			listAssetVocabularies = AssetVocabularyLocalServiceUtil.getGroupsVocabularies(groupIds, Course.class.getName());
		}
		
		return listAssetVocabularies;
	}
	
	public String getFriendlyURLBase() {
		StringBundler sb = new StringBundler(4);

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		sb.append(themeDisplay.getPortalURL());

		Group group = themeDisplay.getScopeGroup();

		sb.append(group.getPathFriendlyURL(false, themeDisplay));

		sb.append(StringPool.FORWARD_SLASH);

		return sb.toString();
	}
	
	public String getNavigation() {
		if(Validator.isNull(navigation)){
			navigation = ParamUtil.getString(portletRequest, "navigation", LMSPortletConstants.EDIT_COURSE_DEFAULT_NAVIGATION);
		}
		return navigation;
	}
	
	public Course getCourse() {
		if(course == null) {
			course = (Course)portletRequest.getAttribute("course");
		}
		return course;
	}
	
	public boolean showButtonSaveAsDraft() {
		Course course = getCourse();
		return course == null || course.isDraft();
	}
	
	public boolean showButtonSave() {
		Course course = getCourse();
		return course != null && course.isApproved();
	}
	
	public boolean showButtonPublish() {
		Course course = getCourse();
		String navigation = getNavigation();
		return course != null && course.isDraft() && (LMSPortletConstants.EDIT_COURSE_MESSAGES.equals(navigation)
				|| (navigation.equals(LMSPortletConstants.EDIT_COURSE_CONFIGURATION) && courseAdminConfiguration != null && !courseAdminConfiguration.courseAssetLinks() 
					&& !courseAdminConfiguration.courseWelcomeMessage() && !courseAdminConfiguration.courseGoodbyeMessage()))
				|| (navigation.equals(LMSPortletConstants.EDIT_COURSE_ASSET_LINKS) && courseAdminConfiguration != null && !courseAdminConfiguration.courseWelcomeMessage() 
					&& !courseAdminConfiguration.courseGoodbyeMessage())
				;
	}
	
	public boolean showButtonSaveAndContinue() {
		String navigation = getNavigation();
		return navigation.equals(LMSPortletConstants.EDIT_COURSE_DESCRIPTION) || 
				(navigation.equals(LMSPortletConstants.EDIT_COURSE_CONFIGURATION) && courseAdminConfiguration != null && 
						(courseAdminConfiguration.courseWelcomeMessage() || courseAdminConfiguration.courseGoodbyeMessage() || courseAdminConfiguration.courseAssetLinks()))
				|| (navigation.equals(LMSPortletConstants.EDIT_COURSE_ASSET_LINKS) && courseAdminConfiguration != null && (courseAdminConfiguration.courseWelcomeMessage() || courseAdminConfiguration.courseGoodbyeMessage()));
	}
	
	//private String[] _addMenuFavItems;
	private Course course;
	private String displayStyle;
	private String[] displayViews;
	private String keywords;
	private final PortletRequest portletRequest;
	private final PortletResponse portletResponse;
	private String navigation;
	private String orderByCol;
	private String orderByType;
	private final PortalPreferences portalPreferences;
	private final PortletPreferences portletPreferences;
	private Integer status;
	private final TrashHelper trashHelper;
	private final HttpServletRequest request;
	private Long parentCourseId;
	private Long groupId;
	private long[] tagIds;
	private long[] categoryIds;
	private Date executionStartDate;
	private Date executionEndDate;
	private List<Group> groups;
	private List<AssetTag> listAssetTags;
	private List<AssetVocabulary> listAssetVocabularies;
	private final CourseAdminPortletInstanceConfiguration courseAdminConfiguration;
}
