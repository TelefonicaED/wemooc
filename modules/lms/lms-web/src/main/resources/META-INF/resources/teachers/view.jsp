<%@ include file="../init.jsp" %>

<div class="teachers row">
	<c:forEach items="${teachers }" var="teacher">	
		<div class="teacher col-md-4">
			<liferay-ui:user-display displayStyle="1" userId="${teacher.userId}"/>
		</div>
	</c:forEach>
</div>