<%@page import="com.ted.lms.web.internal.configuration.CourseAdminPortletInstanceConfiguration"%>
<%@page import="com.ted.lms.web.constants.LMSPortletConstants"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.frontend.taglib.clay.servlet.taglib.util.JSPNavigationItemList"%>
<%@ include file="init.jsp" %>

<%String navigation = ParamUtil.getString(renderRequest, "navigation", LMSPortletConstants.EDIT_COURSE_DEFAULT_NAVIGATION);
long courseId = ParamUtil.getLong(renderRequest, "courseId", 0);
CourseAdminPortletInstanceConfiguration configuration = (CourseAdminPortletInstanceConfiguration)renderRequest.getAttribute("configuration");
JSPNavigationItemList navigationItemList = new JSPNavigationItemList(pageContext) {
	{
		add(
		navigationItem -> {
			navigationItem.setActive(navigation.equals(LMSPortletConstants.EDIT_COURSE_DESCRIPTION));
			navigationItem.setLabel("1. " + LanguageUtil.get(request, LMSPortletConstants.EDIT_COURSE_DESCRIPTION));
			if(courseId > 0){
				navigationItem.setHref("javascript:" + renderResponse.getNamespace() + "saveChangesNavigationItem('" + LMSPortletConstants.EDIT_COURSE_DESCRIPTION + "')");
			}
		});

		add(
		navigationItem -> {
			navigationItem.setActive(navigation.equals(LMSPortletConstants.EDIT_COURSE_CONFIGURATION));
			navigationItem.setLabel("2. " + LanguageUtil.get(request, LMSPortletConstants.EDIT_COURSE_CONFIGURATION));
			if(courseId > 0){
				navigationItem.setHref("javascript:" + renderResponse.getNamespace() + "saveChangesNavigationItem('" + LMSPortletConstants.EDIT_COURSE_CONFIGURATION + "')");
			}
		});
		
		if(configuration != null && configuration.courseAssetLinks()){
			add(
			navigationItem -> {
				navigationItem.setActive(navigation.equals(LMSPortletConstants.EDIT_COURSE_ASSET_LINKS));
				navigationItem.setLabel("3. " + LanguageUtil.get(request, LMSPortletConstants.EDIT_COURSE_ASSET_LINKS));
				if(courseId > 0){
					navigationItem.setHref("javascript:" + renderResponse.getNamespace() + "saveChangesNavigationItem('" + LMSPortletConstants.EDIT_COURSE_ASSET_LINKS + "')");
				}
			});
		}
		if(configuration != null && (configuration.courseWelcomeMessage() || configuration.courseGoodbyeMessage())){
			add(
			navigationItem -> {
				navigationItem.setActive(navigation.equals(LMSPortletConstants.EDIT_COURSE_MESSAGES));
				navigationItem.setLabel((configuration.courseAssetLinks() ? "4" : "3") + ". " + LanguageUtil.get(request, LMSPortletConstants.EDIT_COURSE_MESSAGES));
				if(courseId > 0){
					navigationItem.setHref("javascript:" + renderResponse.getNamespace() + "saveChangesNavigationItem('" + LMSPortletConstants.EDIT_COURSE_MESSAGES + "')");
				}
			});
		}
	}
};

%>

<clay:navigation-bar
	navigationItems="<%=navigationItemList  %>"
/>

<script>
	function <portlet:namespace />saveChangesNavigationItem(navigationItem){
		$('#<portlet:namespace />navigationItem').val(navigationItem);
		$('#<portlet:namespace />nextStep').val(true);
		$('#<portlet:namespace />fm').submit();
	}
</script>