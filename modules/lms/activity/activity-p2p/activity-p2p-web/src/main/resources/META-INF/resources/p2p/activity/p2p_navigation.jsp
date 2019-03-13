<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.frontend.taglib.clay.servlet.taglib.util.JSPNavigationItemList"%>
<%@page import="com.ted.lms.learning.activity.p2p.web.constants.P2PPortletConstants" %>
<%@page import="com.ted.lms.learning.activity.p2p.model.P2PActivity"%>
<%@ include file="init.jsp" %>

<%String navigation = ParamUtil.getString(request, "navigation", P2PPortletConstants.P2P_DEFAULT_NAVIGATION);

boolean p2pActivity = ParamUtil.getBoolean(request, "p2pActivity", false);

JSPNavigationItemList navigationItemList = new JSPNavigationItemList(pageContext) {
	{
		add(
		navigationItem -> {
			navigationItem.setActive(navigation.equals(P2PPortletConstants.UPLOAD_ACTIVITY) || !p2pActivity);
			navigationItem.setLabel(LanguageUtil.get(request, "learning-activity.p2p.steps.upload-activity"));
			if(p2pActivity){
				navigationItem.setHref("javascript:" + renderResponse.getNamespace() + "saveChangesNavigationItem('0')");
			}
		});

		add(
		navigationItem -> {
			navigationItem.setActive(navigation.equals(P2PPortletConstants.CORRECT_ACTIVITIES));
			navigationItem.setLabel(LanguageUtil.get(request, "learning-activity.p2p.steps.correct-activities"));
			if(p2pActivity){
				navigationItem.setHref("javascript:" + renderResponse.getNamespace() + "saveChangesNavigationItem('1')");
			}
		});
		add(
		navigationItem -> {
			navigationItem.setActive(navigation.equals(P2PPortletConstants.MY_CORRECTIONS));
			navigationItem.setLabel(LanguageUtil.get(request, "learning-activity.p2p.steps.my-corrections"));
			if(p2pActivity){
				navigationItem.setHref("javascript:" + renderResponse.getNamespace() + "saveChangesNavigationItem('2')");
			}
		});
	}
};
%>

<clay:navigation-bar
	navigationItems="<%=navigationItemList %>" componentId="${renderResponse.namespace }p2pNavigation"
/>

<script>
	function <portlet:namespace />saveChangesNavigationItem(id){
		
		$('#<portlet:namespace />p2pNavigation').find("a").removeClass("active");
		$('#<portlet:namespace />p2pNavigation').find("li[data-nav-item-index=" + id + "]").children("a").addClass("active");

		if(id=="0"){				
			$("#<portlet:namespace />capa1").removeClass('hide');
			$("#<portlet:namespace />capa2").addClass('hide');
			$("#<portlet:namespace />capa3").addClass('hide');
		}else if(id=="1"){
			$("#<portlet:namespace />capa1").addClass('hide');
			$("#<portlet:namespace />capa2").removeClass('hide');
			$("#<portlet:namespace />capa3").addClass('hide');
		}
		else if(id=="2"){
			$("#<portlet:namespace />capa1").addClass('hide');
			$("#<portlet:namespace />capa2").addClass('hide');
			$("#<portlet:namespace />capa3").removeClass('hide');
		}			
	}
</script>