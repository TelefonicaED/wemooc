<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.liferay.portal.kernel.json.JSONObject"%>
<%@page import="com.liferay.portal.kernel.json.JSONFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.json.JSONArray"%>
<%@page import="com.liferay.portal.kernel.service.UserLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.model.User"%>
<%@page import="com.ted.lms.learning.activity.p2p.service.P2PActivityCorrectionsLocalServiceUtil"%>
<%@page import="com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections"%>
<%@page import="java.util.List"%>

<%List<P2PActivityCorrections> p2pActCorList = P2PActivityCorrectionsLocalServiceUtil.getP2PActivityCorrectionsByP2PActivityId(myP2PActivity.getP2pActivityId());
boolean correctionsDone=false;%>

<c:if test="<%=p2pActivityType.getResult() %>">
	<% if(learnResult.getResult() >= 0 && p2pActivityType.getResult()) { %>
			<div><liferay-ui:message key="learning-activity.p2p.steps.my-corrections.result-total" />: <%= learnResult.getResult() %></div>
	<% } else { %>
				<div><liferay-ui:message key="learning-activity.p2p.steps.my-corrections.result-not-completed" /></div>
	<% } %>
</c:if>

<%
if(!p2pActCorList.isEmpty()){
	FileEntry dlfile = null;
	FileVersion fileVersion = null;
	String urlFile = "";
	String fullName = "";
	boolean anonimous;
	String correctionDate = "";
	cont=0;%>
	
	<liferay-ui:panel-container extended="false"  persistState="false">
	
		<%for (P2PActivityCorrections myP2PActCor : p2pActCorList){
			
			cont++;
			// Lo reseteamos en cada iteracción.
			User propietary = UserLocalServiceUtil.fetchUser(myP2PActCor.getUserId());
			anonimous = p2pActivityType.getAnonimous();
			if(propietary != null){
				fullName = propietary.getFullName();
			}else{
				anonimous = true;
			}
			
			String correctionText = myP2PActCor.getDescription();
			
			if(myP2PActCor.getDate()!=null){
				correctionDate = dateFormatDate.format(myP2PActCor.getDate());
			}else{
				correctionDate = "";
			}
			
			String titlePanel = "";
			if(anonimous){
				titlePanel = LanguageUtil.format(themeDisplay.getLocale(), "learning-activity.p2p.steps.my-corrections.correction.anonimous", cont);
			}else{
				titlePanel = LanguageUtil.format(themeDisplay.getLocale(), "learning-activity.p2p.steps.my-corrections.correction.full-name", fullName);
			}
			
			%>
			<c:if test="<%=myP2PActCor.getDate() != null %>">
				<%correctionsDone=true; %>
				<liferay-ui:panel title="<%=titlePanel%>" collapsible="true" defaultState="closed" >
					<div class="p2p_correction">
					 	<c:if test="<%=myP2PActCor.getDate() != null %>">
					 		<div class="date"><liferay-ui:message key="learning-activity.p2p.steps.my-corrections.correction-date" /> <%=correctionDate %></div>
					 	</c:if>
			
						<div class="degradade">
							<c:if test="<%=myP2PActCor.getDate() != null %>">
								<c:if test="<%=p2pActivityType.getResult() %>">
									<div class="container-result">
										<div class="color_tercero font_13"><liferay-ui:message key="learning-activity.p2p.result" />: <%=myP2PActCor.getResult() %></div>
									</div>
								</c:if>
								<label class="aui-field-label" > <liferay-ui:message key="learning-activity.p2p.steps.my-corrections.feedback" /> </label>
								<div class="container-textarea">
									<%JSONArray jArrayDes = null;
									try{
										jArrayDes = JSONFactoryUtil.createJSONArray(correctionText);
									}catch(Exception e){
									}
								
									if(jArrayDes!=null&&jArrayDes.length()>0){
										%><div class="p2pResponse">
											<div class="p2pAnswer"><%=myP2PActivity.getDescription()%></div>
										<ul><%
										for(int i=0;i<jArrayDes.length();i++){
											JSONObject jsonObjectDes = jArrayDes.getJSONObject(i);
											String valoration = null;
											if(jsonObjectDes!=null)
												valoration = jsonObjectDes.getString("text"+i);

												String question = p2pActivityType.getEvaluationCriteria().getString(i);
												
											%>
												<li>
													<div class="p2pQuestion"><%=question!=null?question:StringPool.BLANK %></div>
													<div class="p2pCorrect"><%=valoration!=null?valoration:StringPool.BLANK %></div>
												</li>
											<%
										}%></ul></div><%
										
									}else{
										String description = myP2PActCor.getDescription();
										%><%=description %><%
									}%>
								</div>
								<%
								dlfile = null;
								if(myP2PActCor.getFileEntryId()!=0) {
									dlfile = DLAppLocalServiceUtil.getFileEntry(myP2PActCor.getFileEntryId());
									fileVersion = dlfile.getFileVersion();
									urlFile = DLUtil.getDownloadURL(dlfile, fileVersion, themeDisplay, "");
									
									int size = Integer.parseInt(String.valueOf(dlfile.getSize()));
									int sizeKb = size/1024; //Lo paso a Kilobytes
									%>
									<div class="doc_descarga">
										<span><%=dlfile.getTitle()%>&nbsp;(<%= sizeKb%> Kb)&nbsp;</span>
										<a href="<%=urlFile%>" class="verMas <%=cssLinkTabletClassP2PCorrect%>" target="_blank"><liferay-ui:message key="download" /></a>
									</div>
								
								<%}%>
							</c:if>
						</div>
							
					</div>
				</liferay-ui:panel>
			</c:if>
			<%
		}%>
	</liferay-ui:panel-container>
<%}

if(!correctionsDone){
	%>
	<div class="alert alert-info">
		<liferay-ui:message key="learning-activity.p2p.steps.my-corrections.no-activites-corretion" />
	</div>
	<%
}%>