<%@page import="com.liferay.portal.kernel.util.HtmlUtil"%>
<%@page import="com.ted.lms.service.LearningActivityResultLocalServiceUtil"%>
<%@page import="com.ted.lms.model.LearningActivityResult"%>
<%@page import="com.ted.lms.service.LearningActivityLocalServiceUtil"%>
<%@page import="com.ted.lms.model.LearningActivity"%>
<%@page import="com.ted.lms.model.Course"%>
<%@page import="com.ted.lms.registry.CalificationTypeFactoryRegistryUtil"%>
<%@page import="com.ted.lms.model.CalificationTypeFactory"%>
<%@page import="com.ted.lms.service.CourseLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ include file="../init.jsp" %>

<%long actId = ParamUtil.getLong(request, "actId", 0);
long studentId = ParamUtil.getLong(request, "studentId", 0);
long courseId = ParamUtil.getLong(request, "courseId");
Course course = CourseLocalServiceUtil.getCourse(courseId);
CalificationTypeFactory calificationTypeFactory = CalificationTypeFactoryRegistryUtil.getCalificationTypeFactoryByType(course.getCalificationType());
LearningActivity activity = LearningActivityLocalServiceUtil.getLearningActivity(actId);
LearningActivityResult result = LearningActivityResultLocalServiceUtil.getLearningActivityResult(actId, studentId);
%>
<aui:input name="actId" value="<%=actId %>" type="hidden"/>
<aui:input name="studentId" value="<%=studentId %>" type="hidden"/>
<aui:input name="courseId" value="<%=courseId %>" type="hidden"/>

<liferay-util:include page="<%=calificationTypeFactory.getURLEditResult() %>" portletId="<%=calificationTypeFactory.getPortletIdEditResult() %>" >
	<liferay-util:param name="actId" value="<%=String.valueOf(actId) %>" />
	<liferay-util:param name="studentId" value="<%=String.valueOf(studentId) %>" />
	<liferay-util:param name="courseId" value="<%=String.valueOf(courseId) %>" />
</liferay-util:include>


<div class="comments">
	<label for="<portlet:namespace />commentsEditor"><liferay-ui:message key="comments" /></label>
	<liferay-editor:editor contents='<%=result != null ? HtmlUtil.escape(result.getComments()) : "" %>' editorName="alloyeditor" name="commentsEditor" 
		placeholder="write-here-your-comments" onChangeMethod='changeComments'/>
</div>
	
<aui:input name="comments" type="hidden" value='<%=result != null ? HtmlUtil.escape(result.getComments()) : "" %>'/>

<script>
	function <portlet:namespace />changeComments(val){
		$('#<portlet:namespace />comments').val(val);
	}
</script>
