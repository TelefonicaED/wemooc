<%@ include file="../init.jsp" %>

<h2>${activity.getTitle(themeDisplay.locale) }</h2>
 
<liferay-portlet:runtime portletName="${activityPortletName }" defaultPreferences="${defaultPreferences }" queryString="${queryString }"/>