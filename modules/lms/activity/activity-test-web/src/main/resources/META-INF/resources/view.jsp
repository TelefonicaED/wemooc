<%@ include file="/init.jsp"%>

<div class="execactivity preview">

	<c:if test="${isTeacher }">
		<aui:button type="button" value="learning-activity.test.correct" href="${goToCorrectionURL }"/>
	</c:if>
	
	<p>
		<liferay-ui:message key="execativity.test.try.notification" />
	</p>
	
	<c:if test="${activity.tries > 0 }">
		<p class="negrita">
			<liferay-ui:message key="execativity.test.try.count" arguments="${argumentsTries }" />
		</p>
	</c:if>
	<c:if test="${activity.passPuntuation > 0 }">
		<p>
			<liferay-ui:message key="execativity.test.try.pass.puntuation"arguments="${argumentsPassPuntuation }" />
		</p>
	</c:if>
	<c:choose>
		<c:when test="${hasQuestions && hasTries }">
			<p class="color_tercero textcenter negrita">
				<liferay-ui:message key="execativity.test.try.confirmation" />
			</p>
			<aui:button value="execativity.test.try.start" onClick="${correctURL}"/>
		</c:when>
		<c:when test="${!hasQuestions }">
			<p class="negrita">
				<liferay-ui:message key="execativity.test.no.question" />
			</p>
		</c:when>
	</c:choose>
</div>
