<%@page import="com.ted.lms.calification.zero.to.n.constants.ZeroToNConstants"%>
<%@page import="com.ted.lms.registry.CalificationTypeFactoryRegistryUtil"%>
<%@page import="com.ted.lms.calification.zero.to.n.ZeroToNCalificationTypeFactory"%>
<%@page import="com.ted.lms.calification.zero.to.n.ZeroToNCalificationType"%>
<%@page import="com.ted.lms.model.Course"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.ted.lms.service.CourseLocalServiceUtil"%>
<%@ include file="../../init.jsp" %>

<%
double maxValue = 0;
long courseId = ParamUtil.getLong(request, "courseId", 0);

if(courseId > 0){
	Course course = CourseLocalServiceUtil.fetchCourse(courseId);
	if(course != null){
		ZeroToNCalificationTypeFactory calificationTypeFactory = (ZeroToNCalificationTypeFactory)CalificationTypeFactoryRegistryUtil.getCalificationTypeFactoryByType(ZeroToNCalificationTypeFactory.TYPE);
		ZeroToNCalificationType zeroToNCalificationType = calificationTypeFactory.getZeroToNCalificationType(course);
		maxValue = zeroToNCalificationType.getMaxValue();
	}
}%>

<aui:input type="text" name="calificationMaxValue" label="calification.zero-to-n.max-value" value="<%=maxValue%>" >
	<aui:validator name="required">
		function(){
			return AUI.$('#<portlet:namespace />calificationType').val() == '<%=ZeroToNCalificationTypeFactory.TYPE %>';
		}
	</aui:validator>
	<aui:validator name="number">
		function(){
			return AUI.$('#<portlet:namespace />calificationType').val() == '<%=ZeroToNCalificationTypeFactory.TYPE %>';
		}
	</aui:validator>
	<aui:validator name="min">
		'0'
	</aui:validator>
</aui:input>
