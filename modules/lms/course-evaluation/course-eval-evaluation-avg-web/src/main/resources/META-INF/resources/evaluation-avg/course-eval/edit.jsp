<%@page import="com.ted.lms.course.eval.evaluation.avg.constants.EvaluationAvgPropsKeys"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portal.kernel.servlet.SessionErrors"%>
<%@page import="com.liferay.portal.kernel.util.PropsUtil"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>

<%@ include file="../../init.jsp" %>

<c:if test='<%=Validator.isNull(ParamUtil.getLong(renderRequest, "courseId")) %>'>
<% 
	long defaultEvaluations =  GetterUtil.getLong(PropsUtil.get(EvaluationAvgPropsKeys.DEFAULT_EVALUATIONS), 0);
%>
	<aui:input name="numOfEvaluations" label="course-eval.evaluation-avg.num-of-evaluations" type="text" 
			value="<%=defaultEvaluations %>" helpMessage="course-eval.evaluation-avg.num-of-evaluations.help" >
			
		<aui:validator name="required" ></aui:validator>
		<aui:validator name="number" ></aui:validator>
	</aui:input>
	
</c:if>
