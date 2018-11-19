<%@page import="com.ted.lms.model.Course"%>
<%@ include file="init.jsp" %>

<aui:model-context bean="${course }" model="<%= Course.class %>" />

<div class="container">
	<div class="row">
		<div class="col-md-12">
			<aui:field-wrapper label="related-assets">
				<liferay-asset:input-asset-links
							className="<%= Course.class.getName() %>"
							classPK="${course.courseId }"
						/>
			</aui:field-wrapper>
		</div>
	</div>
</div>