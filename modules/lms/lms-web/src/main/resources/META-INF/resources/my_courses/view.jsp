<%@page import="com.liferay.portal.kernel.dao.search.SearchContainer"%>
<%@page import="java.util.Set"%>
<%@page import="com.ted.lms.model.CourseResult"%>
<%@page import="java.util.List"%>
<%@ include file="../init.jsp" %>

<c:choose>
	<c:when test="${not empty coursesShowed }">
		<liferay-ui:tabs
			names="${tabsNames }"
			param="tabSelected"
			refresh="<%= false %>"
			type="tabs nav-tabs-default"
		>
			<c:forEach var="courseShowed" items="${coursesShowed}">
				<liferay-ui:section>
					<c:set var="searchContainer" value="${courseShowed.value }"/>
					<%@ include file="/my_courses/view_entries.jspf" %>
				</liferay-ui:section>
			</c:forEach>
		</liferay-ui:tabs>
	</c:when>
	<c:otherwise>
		<%@ include file="/my_courses/view_entries.jspf" %>
	</c:otherwise>
</c:choose>

