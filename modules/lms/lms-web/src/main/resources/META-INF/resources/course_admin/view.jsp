<%@page import="com.liferay.petra.string.StringPool"%>
<%@ include file="init.jsp" %>

<c:if test="${course.courseId > 0 }">
	<liferay-ui:header title='${course.getTitle(themeDisplay.locale) }' backURL="${redirect}" />

	<aui:nav-bar cssClass="navbar-collapse-absolute" markupView="lexicon">
		<h2><liferay-ui:message key="editions"/></h2>
		<liferay-ui:icon-menu
			direction="left-side"
			icon="<%= StringPool.BLANK %>"
			markupView="lexicon"
			message="<%= StringPool.BLANK %>"
			showWhenSingleIcon="true"
		>
		
			<liferay-portlet:resourceURL id="/courses/export_editions" var="exportURL"  copyCurrentRenderParameters="false"> 
				<liferay-portlet:param name="courseId" value="${course.courseId }" />
			</liferay-portlet:resourceURL>
			
			<liferay-util:buffer
				var="onClickFn"
			>
				submitForm(document.<portlet:namespace />searchAdvancedFm, '<%= exportURL + "&compress=0&etag=0&strip=0" %>');
			</liferay-util:buffer>
			
			<liferay-ui:icon
				message="export"
				method="get"
				onClick="<%= onClickFn %>"
				url="javascript:;"
			/>
			<liferay-ui:icon
				message="import"
				onClick="${renderResponse.namespace }importEditions();"
				url="javascript:;"
			/>
		</liferay-ui:icon-menu>
	</aui:nav-bar>
	<script>
		function <portlet:namespace />importEditions() {
			
			AUI().use('liferay-portlet-url,liferay-util-window', function(A){
				
				var uri = '${importEditionsURL}';
				
				window.<portlet:namespace />popupImport = Liferay.Util.Window.getWindow(
						{
							dialog: {
								modal: true,
								resizable: false,
								width: "auto",
								heigth: "auto",
								centered: true,
								destroyOnHide: true
							},
							title: Liferay.Language.get('course-admin.editions.import'),		
							uri: uri
						}
						).render();
				window.<portlet:namespace />popupImport.show();
		  
			});
		};
		
	</script>
</c:if>

<liferay-util:include page="/course_admin/nav.jsp" servletContext="<%= application %>" />

<liferay-ui:search-container
	id="courses"
	searchContainer="${searchContainer}"
	var="courseSearchContainer"
>
	<liferay-ui:search-container-row
		className="com.ted.lms.model.Course"
		modelVar="course"
	>

		<liferay-ui:search-container-column-text
			cssClass="important table-cell-content"
			href="${course.isApproved() ? course.friendlyURL : '' }"
			name="name"
			value="${course.getTitle(themeDisplay.locale) }"
		/>
		
		<liferay-ui:search-container-column-jsp
			path="/course_admin/course_action.jsp"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator
		displayStyle="${displayStyle}"
		markupView="lexicon"
	/>
</liferay-ui:search-container>