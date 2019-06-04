<%@page import="com.ted.lms.learning.activity.resource.external.web.constants.ResourceExternalConstants"%>
<%@page import="com.ted.lms.registry.LearningActivityTypeFactoryRegistryUtil"%>
<%@page import="com.ted.lms.learning.activity.resource.external.ResourceExternalActivityTypeFactory"%>
<%@page import="com.ted.lms.learning.activity.resource.external.ResourceExternalActivityType"%>
<%@page import="com.ted.lms.learning.activity.QuestionsLearningActivityType"%>
<%@page import="com.liferay.portal.kernel.xml.Element"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.liferay.portal.kernel.xml.SAXReaderUtil"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ted.lms.learning.activity.question.service.QuestionLocalServiceUtil"%>
<%@page import="com.ted.lms.learning.activity.question.model.Question"%>
<%@page import="com.ted.lms.model.CalificationType"%>
<%@page import="com.ted.lms.model.Course"%>
<%@page import="com.ted.lms.registry.CalificationTypeFactoryRegistryUtil"%>
<%@page import="com.ted.lms.model.CalificationTypeFactory"%>
<%@page import="com.ted.lms.service.CourseLocalServiceUtil"%>
<%@page import="com.ted.lms.service.LearningActivityLocalServiceUtil"%>
<%@page import="com.ted.lms.model.LearningActivity"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.ted.lms.service.LearningActivityResultLocalServiceUtil"%>
<%@page import="com.ted.lms.model.LearningActivityResult"%>
<%@page import="com.ted.lms.service.LearningActivityTryLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.model.User"%>
<%@page import="com.ted.lms.model.LearningActivityTry"%>
<%@page import="com.liferay.portal.kernel.service.UserLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="java.util.List"%>
<%@ include file="init.jsp" %>

<%long actId = ParamUtil.getLong(request, "actId", 0);
long studentId = ParamUtil.getLong(request, "studentId", 0);
User student = UserLocalServiceUtil.getUser(studentId);
long courseId = ParamUtil.getLong(request, "courseId", 0);
Course course = CourseLocalServiceUtil.getCourse(courseId);
CalificationTypeFactory calificationTypeFactory = CalificationTypeFactoryRegistryUtil.getCalificationTypeFactoryByType(course.getCalificationType());
CalificationType calificationType = calificationTypeFactory.getCalificationType(course);
LearningActivityResult result = LearningActivityResultLocalServiceUtil.getLearningActivityResult(actId, studentId);%>

<p><span><liferay-ui:message key="learning-activity.corrections.done-by" /></span> <%=student.getFullName()%></p>
<p><span><liferay-ui:message key="learning-activity.corrections.result"/></span> <%=calificationType.translate(themeDisplay.getLocale(), result.getResult()) + calificationType.getSuffix() %></p>
<p><span><liferay-ui:message key="learning-activity.corrections.status"/></span> <%=result.isPassed() ? LanguageUtil.get(request, "status.passed") : LanguageUtil.get(request, "status.failed") %></p>


<liferay-ui:panel-container >

	<%List<LearningActivityTry> triesList = LearningActivityTryLocalServiceUtil.getLearningActivityTries(actId, studentId);
	LearningActivity activity = LearningActivityLocalServiceUtil.getLearningActivity(actId);
	ResourceExternalActivityTypeFactory resourceExternalActivityTypeFactory = (ResourceExternalActivityTypeFactory)LearningActivityTypeFactoryRegistryUtil.getLearningActivityTypeFactoryByType(ResourceExternalConstants.TYPE);
	ResourceExternalActivityType resourceExternalActivityType = resourceExternalActivityTypeFactory.getResourceExternalActivityType(activity);
	
	SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy HH:mm");
	sdf.setTimeZone(timeZone);
	String fecha ="-";
	long tries = activity.getTries();
	long userTries = triesList.size();
	boolean userPassed = false;
	List<Question> questions = null;
	
	for(LearningActivityTry larntry:triesList){
		fecha = "";
		
		if(larntry.getEndDate()!=null){
			fecha = " (" + sdf.format(larntry.getEndDate()) + ")";
		}
		String title = LanguageUtil.get(request, "learning-activity.corrections.answers") + fecha;
		userPassed = larntry.getResult()>= activity.getPassPuntuation();
		%>
	
		<liferay-ui:panel id="<%=Long.toString(larntry.getLatId()) %>" title="<%=title %>" collapsible="true" extended="true" defaultState="collapsed">
		
			<p><span><liferay-ui:message key="learning-activity.corrections.result"/></span> <%=calificationType.translate(themeDisplay.getLocale(), larntry.getResult()) + calificationType.getSuffix() %></p>
			<p><span><liferay-ui:message key="learning-activity.corrections.status"/></span> <%=userPassed ? LanguageUtil.get(request, "status.passed") : LanguageUtil.get(request, "status.failed") %></p>
		
			<%
			questions= new ArrayList<Question>();
			
			if(larntry != null && Validator.isNotNull(larntry.getTryResultData())){
				try{
					Iterator<Element> nodeItr = SAXReaderUtil.read(larntry.getTryResultData()).getRootElement().elementIterator();
					while(nodeItr.hasNext()) {
						Element element = nodeItr.next();
				         if("question".equals(element.getName())) {
				        	 questions.add(QuestionLocalServiceUtil.getQuestion(Long.valueOf(element.attributeValue("id"))));
				         }
				    }
				}catch(Exception e){
					e.printStackTrace();
				}
			}	
			
			for(Question question: questions){
				request.setAttribute("question", question);%>
				
				<liferay-util:include page="<%=question.getQuestionType().getURLQuestion() %>" portletId="<%=question.getQuestionType().getQuestionTypeFactory().getPortletId() %>" >
					<liferay-util:param name="questionId" value="<%=String.valueOf(question.getQuestionId()) %>"/>
					<liferay-util:param name="tryResultData" value="<%=larntry.getTryResultData() %>" />
					<liferay-util:param name="showCorrectAnswer" value="true"/>
					<liferay-util:param name="feedback" value="true" />
				</liferay-util:include>
			<%}%>
		</liferay-ui:panel>
	<%} %>

</liferay-ui:panel-container>