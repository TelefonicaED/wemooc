<%@page import="com.ted.lms.learning.activity.p2p.constants.P2PConstants"%>
<%@page import="com.ted.lms.registry.LearningActivityTypeFactoryRegistryUtil"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.liferay.portal.kernel.util.FastDateFormatFactoryUtil"%>
<%@page import="java.text.Format"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portal.kernel.repository.model.FileVersion"%>
<%@page import="com.liferay.portal.kernel.repository.model.FileEntry"%>
<%@page import="com.liferay.document.library.kernel.util.DLUtil"%>
<%@page import="com.liferay.document.library.kernel.service.DLAppLocalServiceUtil"%>
<%@page import="java.util.Date"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.liferay.portal.kernel.json.JSONObject"%>
<%@page import="com.liferay.portal.kernel.json.JSONFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.json.JSONArray"%>
<%@page import="com.ted.lms.learning.activity.p2p.web.activity.P2PActivityType"%>
<%@page import="com.ted.lms.learning.activity.p2p.web.activity.P2PActivityTypeFactory"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.ted.lms.learning.activity.p2p.service.P2PActivityCorrectionsLocalServiceUtil"%>
<%@page import="com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil"%>
<%@page import="com.liferay.document.library.kernel.model.DLFileEntry"%>
<%@page import="com.ted.lms.service.LearningActivityResultLocalServiceUtil"%>
<%@page import="com.ted.lms.model.LearningActivityResult"%>
<%@page import="com.ted.lms.learning.activity.p2p.service.P2PActivityLocalServiceUtil"%>
<%@page import="com.ted.lms.learning.activity.p2p.model.P2PActivity"%>
<%@page import="com.liferay.portal.kernel.service.UserLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.model.User"%>
<%@page import="com.ted.lms.model.LearningActivityTry"%>
<%@page import="java.util.List"%>
<%@page import="com.ted.lms.service.LearningActivityTryLocalServiceUtil"%>
<%@page import="com.ted.lms.service.LearningActivityLocalServiceUtil"%>
<%@page import="com.ted.lms.model.LearningActivity"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ include file="init.jsp" %>


<%
Boolean isLinkTabletP2PAct = ParamUtil.getBoolean(request, "isTablet", false);
String cssLinkTabletClassP2PAct="";
if(isLinkTabletP2PAct){
	cssLinkTabletClassP2PAct="tablet-link";
}

Format dateFormatDate = FastDateFormatFactoryUtil.getDateTime(DateFormat.SHORT, DateFormat.SHORT, locale, timeZone);

long actId=ParamUtil.getLong(request,"actId",0);
long userId=ParamUtil.getLong(request,"userId",0);

LearningActivity activity = LearningActivityLocalServiceUtil.getLearningActivity(actId);

P2PActivityTypeFactory p2pActivityTypeFactory = (P2PActivityTypeFactory)LearningActivityTypeFactoryRegistryUtil.getLearningActivityTypeFactoryByType(P2PConstants.TYPE);
P2PActivityType p2pActivityType = p2pActivityTypeFactory.getP2PActivityType(activity);

if(actId!=0){%>

	<portlet:renderURL var="backURL">
		<portlet:param name="mvcRenderCommandName" value="/activities/p2p/revisions" />
		<portlet:param name="actId" value="<%=String.valueOf(actId) %>" />
	</portlet:renderURL>

	<liferay-ui:header title="learning-activity.p2p.corrections" backURL="<%=backURL %>" localizeTitle="true"/>
	
	<%JSONArray evaluationCriteria = p2pActivityType.getEvaluationCriteria();
	
	User owner = UserLocalServiceUtil.getUser(userId);
	%>
	<h4><liferay-ui:message key="learning-activity.p2p.corrections.task-done-by" arguments="<%=owner.getFullName()%>"/></h4>
	<%
	P2PActivity myP2PActivity = P2PActivityLocalServiceUtil.getP2PActivity(actId, userId);
	
	request.setAttribute("actId", actId);
	
	if(myP2PActivity!=null){
		FileEntry dlfile = null;
		FileVersion fileVersion = null;
		String urlFile = "";
		int size = 0;
		int sizeKb = 0;
		String passed="";
		long showRevision = ParamUtil.getLong(request, "showRevision",0);
		%>
		
		<liferay-util:include page="/p2p/activity/p2p_navigation.jsp" servletContext="<%= application %>" >
			<liferay-util:param name="p2pActivity" value="true"/>
		</liferay-util:include>
		
		<div id="${renderResponse.namespace}capa1" >
			<label class="aui-field-label" for="${renderResponse.getNamespace()}textCorrection"><liferay-ui:message key="learning-activity.p2p.steps.upload-activity.description" /> </label>
			<div class="container-textarea" id="${renderResponse.getNamespace()}textCorrection">
				<%=myP2PActivity.getDescription() %>
			</div>
			<%if(myP2PActivity.getFileEntryId() != 0){
				
				try{
					dlfile = DLAppLocalServiceUtil.getFileEntry(myP2PActivity.getFileEntryId());
					fileVersion = dlfile.getFileVersion();
					urlFile = DLUtil.getDownloadURL(dlfile, fileVersion, themeDisplay, "");
					size = Integer.parseInt(String.valueOf(dlfile.getSize()));
					sizeKb = size/1024; //Lo paso a Kilobytes%>
					
					<div class="doc_descarga">
						<span><%=dlfile.getTitle()%>&nbsp;(<%= sizeKb%> Kb)&nbsp;</span>
						<a href="<%=urlFile%>" class="<%=cssLinkTabletClassP2PAct %>" target="_blank"><liferay-ui:message key="download" /></a>
					</div>
				<%}catch(Exception e){
					e.printStackTrace();
				}
			}%>
		</div>
		<div class='hide' id="${renderResponse.namespace}capa2" >
		<%
			List<P2PActivityCorrections> p2pActList = P2PActivityCorrectionsLocalServiceUtil.getCorrections(actId, userId);
			
			String fullName ="";
			User propietary;
			boolean anonimous = p2pActivityType.getAnonimous();
			P2PActivity p2pActivity = null;
			
			if(!p2pActList.isEmpty()){
				int cont = 0;
				
				for (P2PActivityCorrections myP2PActiCor : p2pActList){
					
					p2pActivity = P2PActivityLocalServiceUtil.getP2PActivity(myP2PActiCor.getP2pActivityId());
					propietary = UserLocalServiceUtil.fetchUser(p2pActivity.getUserId());
					if(propietary!=null){
						fullName = propietary.getFullName();
					}else{
						anonimous = true;
					}
					cont++;
					
					if(p2pActivity.getUserId()!=userId){
						
						urlFile = "";
						sizeKb = 0;
						size = 0;
						String title = "";
						String descriptionFile = "";
						
						if(p2pActivity.getFileEntryId() != 0){
							
							try{
								dlfile = DLAppLocalServiceUtil.getFileEntry(p2pActivity.getFileEntryId());
								fileVersion = dlfile.getFileVersion();
								urlFile = DLUtil.getDownloadURL(dlfile, fileVersion, themeDisplay, "");
								size = Integer.parseInt(String.valueOf(dlfile.getSize()));
								sizeKb = size/1024; //Lo paso a Kilobytes
								title = dlfile.getTitle();
								descriptionFile = dlfile.getDescription();
							}catch(Exception e){
								e.printStackTrace();
							}
						}
						
	
						if(Validator.isNull(descriptionFile) && Validator.isNotNull(p2pActivity.getDescription())){
							descriptionFile = p2pActivity.getDescription();
						}
						String titlePanel = "";
						if(anonimous){
							titlePanel = LanguageUtil.format(themeDisplay.getLocale(), "learning-activity.p2p.steps.correct-activities.correction.anonimous", cont);
						}else{
							titlePanel = LanguageUtil.format(themeDisplay.getLocale(), "learning-activity.p2p.steps.correct-activities.correction.full-name", fullName);
						}
						
						%>
						
						<liferay-ui:panel title="<%=titlePanel%>" collapsible="true" defaultState="closed" >
							<div class="p2p_correction">
								
								<div class="correctAnswer"><%=descriptionFile  %></div>
					
								<c:if test="<%=p2pActivity.getFileEntryId() != 0 %>">
									<div class="doc_descarga">
										<span><%=title%>&nbsp;(<%= sizeKb%> Kb)&nbsp;</span>
										<a href="<%=urlFile%>" class="<%=cssLinkTabletClassP2PAct %>" target="_blank"><liferay-ui:message key="learning-activity.p2p.steps.correct-activities.download-file" /></a>
									</div>
								</c:if>
								<%String description = myP2PActiCor.getDescription();
								String textButton = "p2ptask-correction";
								%>
								<div class="degradade">
									<div class="subtitle"><liferay-ui:message key="learning-activity.p2p.steps.correct-activities.your-valoration" /> :</div>
			
									<div class="container-textarea">
										<label for="${renderResponse.getNamespace() }readonlydesc" />
										
										<%JSONArray jArrayDes = null;
										try{
											jArrayDes = JSONFactoryUtil.createJSONArray(description);
										}catch(Exception e){}
									
										if(jArrayDes!=null&&jArrayDes.length()>0){%>
											<div class="p2pResponse">
												<ul>
													<%for(int i=0;i<jArrayDes.length();i++){
														JSONObject jsonObjectDes = null;
														try{
														jsonObjectDes = jArrayDes.getJSONObject(i);
														}catch(Exception e){}
					
														String valoration = null;
														if(jsonObjectDes!=null)
															valoration = jsonObjectDes.getString("text"+i);%>
															<li>
																<% String value = evaluationCriteria.getString(i);
																if(i==0 || (value!=null&&!value.equals(StringPool.BLANK))){
																	if(Validator.isNull(value)){
																		value = LanguageUtil.get(themeDisplay.getLocale(), "feedback");
																	}%>
																	<div class="p2pQuestion"><%=value %></div>
																	<div class="p2pCorrect"><%=valoration!=null?valoration:StringPool.BLANK %></div>
																<%}%>
															</li>
														<%
													}%>
												</ul>
											</div><%
											
										}else{
											%><%=description %><%
										}%>
									</div>
									<%if(myP2PActiCor.getFileEntryId()!=0){
										
										FileEntry fileEntryCorrection = DLAppLocalServiceUtil.getFileEntry(myP2PActiCor.getFileEntryId());
										
										if(fileEntryCorrection != null){
											FileVersion fileVersionCorrection = fileEntryCorrection.getFileVersion();
											String urlFileCorrection = DLUtil.getDownloadURL(fileEntryCorrection, fileVersionCorrection, themeDisplay, "");
											size = Integer.parseInt(String.valueOf(fileEntryCorrection.getSize()));
											sizeKb = size/1024; //Lo paso a Kilobytes
										%>
										<div class="doc_descarga">
											<span><%=fileEntryCorrection.getTitle()%>&nbsp;(<%= sizeKb%> Kb)&nbsp;</span>
											<a href="<%=urlFileCorrection%>" class="verMas <%=cssLinkTabletClassP2PAct %>" target="_blank"><liferay-ui:message key="learning-activity.p2p.steps.correct-activities.download-file" /></a>
										</div>
									<%	}
									}%>
								</div>
								<c:if test="<%=p2pActivityType.getResult() %>">
									<div><liferay-ui:message key="learning-activity.p2p.result" />: <%=myP2PActiCor.getResult() %></div>
								</c:if>
							</div>
						</liferay-ui:panel>
					<%
					}
				}
			}
			else{
			%>
			<div class="alert alert-info"><liferay-ui:message key="learning-activity.p2p.revisions.correct-activities.no-activities-uploaded-resume" /></div>
			<%
			}
			%>
			
		</div>
		
		<div class='hide' id="${renderResponse.namespace}capa3" >
		
			<c:if test="<%=p2pActivityType.getResult() %>">
				<% LearningActivityResult learnResult = LearningActivityResultLocalServiceUtil.getLearningActivityResult(actId, userId);
				if(learnResult != null && learnResult.getResult() >= 0 && p2pActivityType.getResult()) { %>
					<div><liferay-ui:message key="learning-activity.p2p.steps.my-corrections.result-total" />: <%= learnResult.getResult() %></div>
				<% } else { %>
					<div><liferay-ui:message key="learning-activity.p2p.revisions.my-corrections.result-not-completed" /></div>
				<% } %>
			</c:if>
			<%
			List<P2PActivityCorrections> p2pActCorList = P2PActivityCorrectionsLocalServiceUtil.getP2PActivityCorrectionsByP2PActivityId(myP2PActivity.getP2pActivityId());
	
	
			if(!p2pActCorList.isEmpty()){
				dlfile = null;
				fileVersion = null;
				urlFile = "";
				fullName = "";
				String correctionDate = "";
				int cont=0;%>
				
				<liferay-ui:panel-container extended="false"  persistState="false">
				
					<%for (P2PActivityCorrections myP2PActCor : p2pActCorList){
							
						cont++;
						// Lo reseteamos en cada iteracción.
						propietary = UserLocalServiceUtil.fetchUser(myP2PActCor.getUserId());
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
												
												size = Integer.parseInt(String.valueOf(dlfile.getSize()));
												sizeKb = size/1024; //Lo paso a Kilobytes
												%>
												<div class="doc_descarga">
													<span><%=dlfile.getTitle()%>&nbsp;(<%= sizeKb%> Kb)&nbsp;</span>
													<a href="<%=urlFile%>" class="verMas <%=cssLinkTabletClassP2PAct%>" target="_blank"><liferay-ui:message key="download" /></a>
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
			<%}else{
				%>
				<div class="alert alert-info">
					<liferay-ui:message key="learning-activity.p2p.revisions.my-corrections.no-activities-corretion-resume" />
				</div>
				<%
			}%>
		</div>
	<%
	}
}
%>