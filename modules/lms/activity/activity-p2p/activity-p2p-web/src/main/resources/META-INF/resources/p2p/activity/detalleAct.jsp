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
<%@ include file="../../init.jsp" %>
<%
Boolean isLinkTabletP2PAct = ParamUtil.getBoolean(request, "isTablet", false);
String cssLinkTabletClassP2PAct="";
if(isLinkTabletP2PAct){
	cssLinkTabletClassP2PAct="tablet-link";
}
%>

<%
long actId=ParamUtil.getLong(request,"actId",0);
long userId=ParamUtil.getLong(request,"userId",0);

LearningActivity activity = LearningActivityLocalServiceUtil.getLearningActivity(actId);

P2PActivityTypeFactory p2pActivityTypeFactory = new P2PActivityTypeFactory();
P2PActivityType p2pActivityType = p2pActivityTypeFactory.getP2PActivityType(activity);

if(actId!=0){
	JSONArray evaluationCriteria = p2pActivityType.getEvaluationCriteria();
	
	long latId = ParamUtil.getLong(request,"latId",0);
	if(latId==0){
		if(LearningActivityTryLocalServiceUtil.getLearningActivityTriesCount(actId, userId)>0){
			List<LearningActivityTry> latList = LearningActivityTryLocalServiceUtil.getLearningActivityTries(actId, userId);
			if(!latList.isEmpty())
			{
				for(LearningActivityTry lat :latList){
					latId = lat.getLatId();
				}
			}
		}
	}
	User owner = UserLocalServiceUtil.getUser(userId);
	%>
	<h2 class="description-title"><%=activity.getTitle(themeDisplay.getLocale()) %></h2>
	<%--<h3 class="description-h3"><liferay-ui:message key="description" /></h3> --%>
	<div class="description"><%=activity.getDescription(themeDisplay.getLocale()) %></div>
	<p class="sub-title"><liferay-ui:message key="p2ptask-done-by" /> <%=owner.getFullName()%></p>
	<%
	P2PActivity myp2pActivity = P2PActivityLocalServiceUtil.getP2PActivity(actId, userId);
	
	request.setAttribute("actId", actId);
	request.setAttribute("latId", latId);
	
	LearningActivityResult learnResult = LearningActivityResultLocalServiceUtil.getLearningActivityResult(actId,userId);
	if(myp2pActivity!=null){
		String classCSS2="";
		String classCSS3="";
		String passed="";
		String javascript="";
		Long showRevision = ParamUtil.getLong(request, "showRevision",0);
		if(!learnResult.getPassed()){
			classCSS2="selected";
			if(showRevision==1)
				javascript=renderResponse.getNamespace()+"changeDiv(3);";
			else
				javascript=renderResponse.getNamespace()+"changeDiv(2);";
		}
		else{
			classCSS3="selected";
			javascript=renderResponse.getNamespace()+"changeDiv(3);";
			passed="done";
		}
		
	%>
	<div class="steps">
		<span id="span1" onclick="<portlet:namespace />changeDiv(1)" class="clicable done"><liferay-ui:message key="p2ptask-step1-resume" />&nbsp;>&nbsp;</span>
		<span id="span2" class="<%=classCSS2 %> clicable <%=passed%>" onclick="<portlet:namespace />changeDiv(2)"><liferay-ui:message key="p2ptask-step2-resume" />&nbsp;>&nbsp;</span>
		<span id="span3" class="<%=classCSS3 %> clicable" onclick="<portlet:namespace />changeDiv(3)"><liferay-ui:message key="p2ptask-step3-resume" /></span>
	</div>
	<div class="preg_content" id="capa1" style="display:none">
		<%
		int size=0, sizeKb=0; 
		String title = "";
		DLFileEntry dlfile = null;
		String urlFile = null;
		

		
		try{
			
			dlfile = DLFileEntryLocalServiceUtil.getDLFileEntry(myp2pActivity.getFileEntryId());
			
			title = dlfile.getTitle();
			
			size = Integer.parseInt(String.valueOf(dlfile.getSize()));
			sizeKb = size/1024; //Lo paso a Kilobytes
			urlFile = themeDisplay.getPortalURL()+"/documents/"+dlfile.getGroupId()+"/"+dlfile.getUuid();	
		}catch(Exception e){}
		
		
		
		%>
		<div class="container-textarea">
			<%=myp2pActivity.getDescription() %>
		</div>
		<c:if test="<%=Validator.isNotNull(urlFile) %>">
			<div class="doc_descarga">
				<span><%=title%>&nbsp;(<%= sizeKb%> Kb)&nbsp;</span>
				<a href="<%=urlFile%>" class="verMas <%=cssLinkTabletClassP2PAct %>" target="_blank"><liferay-ui:message key="p2ptask-donwload" /></a>
			</div>
		</c:if>
	</div>
	<div class="preg_content" id="capa2" style="display:none">
	<%
		List<P2PActivityCorrections> p2pActList = P2PActivityCorrectionsLocalServiceUtil.getCorrections(actId, userId);
		LearningActivityTry larEntry=LearningActivityTryLocalServiceUtil.getLearningActivityTry(latId);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		dateFormat.setTimeZone(timeZone);
		int cont=0;
		if(!p2pActList.isEmpty()){
			
			for (P2PActivityCorrections myP2PActiCor : p2pActList){
				P2PActivity myP2PActivity = P2PActivityLocalServiceUtil.getP2PActivity(myP2PActiCor.getP2pActivityId());
				
				if(myP2PActivity.getUserId()!=userId){
					
					cont++;
					User propietary = UserLocalServiceUtil.getUser(myP2PActivity.getUserId());
					
					String description = myP2PActiCor.getDescription();
					String textButton = "p2ptask-correction";
					String correctionDate="";
					if(myP2PActivity.getDate()!=null){
						correctionDate = dateFormat.format(myP2PActivity.getDate());
					}
					
					size=0; sizeKb=0; title = ""; dlfile = null;
					if( myp2pActivity.getFileEntryId() != 0){
						try{
						
							
							dlfile = DLFileEntryLocalServiceUtil.getDLFileEntry(myp2pActivity.getFileEntryId());
							
							title = dlfile.getTitle();
							
							size = Integer.parseInt(String.valueOf(dlfile.getSize()));
							sizeKb = size/1024; //Lo paso a Kilobytes
						
						
						
						
						}catch(Exception e){}

						urlFile = themeDisplay.getPortalURL()+"/documents/"+dlfile.getGroupId()+"/"+dlfile.getUuid(); 
					}
					
					boolean anonimous = p2pActivityType.getAnonimous();
					
					%>
					<div class="option-more">
						<span class="label-col">
							<liferay-ui:message key="p2ptask-exercise" /> 
							<span class="name">
								<liferay-ui:message key="of" /> 
								<%=propietary.getFullName() %>
							</span>
							<c:if test="<%=anonimous %>">
								<span class="number">
									<liferay-ui:message key="p2ptaskactivity.number" /> 
									<%=cont%>
								</span>
							</c:if>
						</span>
						<div class="collapsable">
							<%
							String descriptionFile = "";
							if(myP2PActivity.getDescription()!=null){
								descriptionFile = myP2PActivity.getDescription();
							}
							%>
							<div class="description"><%=descriptionFile %></div>
							<%if(dlfile != null){ %>
								<div class="doc_descarga">
									<span><%=title%>&nbsp;(<%= sizeKb%> Kb)&nbsp;</span>
									<a href="<%=urlFile%>" class="verMas <%=cssLinkTabletClassP2PAct %>" target="_blank"><liferay-ui:message key="p2ptask-donwload" /></a>
								</div>
							<%} %>
							<div class="degradade">
								<div class="subtitle"><liferay-ui:message key="p2ptask-valoration" /> :</div>
								<div class="container-textarea">
									<%
							
								if(myP2PActivity!=null){
									JSONArray jArrayDes = null;
									try{
										jArrayDes = JSONFactoryUtil.createJSONArray(description);
									}catch(Exception e){}
								
									if(jArrayDes!=null&&jArrayDes.length()>0){
										%><div class="p2pResponse">
										<ul><%
										for(int i=0;i<jArrayDes.length();i++){
											JSONObject jsonObjectDes = jArrayDes.getJSONObject(i);
											String valoration = null;
											if(jsonObjectDes!=null)
												valoration = jsonObjectDes.getString("text"+i);
											
												String question = evaluationCriteria.getString(i);
												
											%>
												<li>
													<div class="p2pQuestion"><%=question!=null?question:StringPool.BLANK %></div>
													<div class="p2pCorrect"><%=valoration!=null?valoration:StringPool.BLANK %></div>
												</li>
											<%
										}%></ul></div><%
										
									}else{
										%><%=description %><%
									}
								}
							%>
									
									
									
									
								</div>
								<%
								if(myP2PActiCor.getFileEntryId()!=0){
									DLFileEntry dlfileCor = DLFileEntryLocalServiceUtil.getDLFileEntry(myP2PActiCor.getFileEntryId());
									String urlFileCor = themeDisplay.getPortalURL()+"/documents/"+dlfileCor.getGroupId()+"/"+dlfileCor.getUuid();
									size = Integer.parseInt(String.valueOf(dlfileCor.getSize()));
									sizeKb = size/1024; //Lo paso a Kilobytes
								%>
								<div class="doc_descarga">
									<span><%=dlfileCor.getTitle()%>&nbsp;(<%= sizeKb%> Kb)&nbsp;</span>
									<a href="<%=urlFileCor%>" class="verMas <%=cssLinkTabletClassP2PAct %>" target="_blank"><liferay-ui:message key="p2ptask-donwload" /></a>
								</div>
								<%} %>
							</div>
						</div>
					</div>
					<%
				}
			}
		}
		else{
		%>
		<div class="no-p2pActivites-uploaded-resume"><liferay-ui:message key="no-p2pActivites-uploaded-resume" /></div>
		<%
		}
		%>
	</div>
	<div class="preg_content" id="capa3" style="display:none">
		<%
		List<P2PActivityCorrections> p2pActCorList = P2PActivityCorrectionsLocalServiceUtil.getP2PActivityCorrectionsByP2PActivityId(myp2pActivity.getP2pActivityId());

		dlfile = null;
		urlFile = "";

		dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		dateFormat.setTimeZone(timeZone);
		String correctionDate = "";
		cont=0;
		if(!p2pActCorList.isEmpty()){
			for (P2PActivityCorrections myP2PActCor : p2pActCorList){
					
				cont++;
				dlfile = null;
				User propietary = UserLocalServiceUtil.getUser(myP2PActCor.getUserId());
				String correctionText = myP2PActCor.getDescription();
				if(myP2PActCor.getFileEntryId()!=0)
				{

					size=0; sizeKb=0; title = ""; dlfile = null;
					try{
						
						dlfile = DLFileEntryLocalServiceUtil.getDLFileEntry(myP2PActCor.getFileEntryId());
						
						title = dlfile.getTitle();
						
						size = Integer.parseInt(String.valueOf(dlfile.getSize()));
						sizeKb = size/1024; //Lo paso a Kilobytes
						
					}catch(Exception e){}
					
					urlFile = themeDisplay.getPortalURL()+"/documents/"+dlfile.getGroupId()+"/"+dlfile.getUuid();
					Date date = myP2PActCor.getDate();
					correctionDate = dateFormat.format(date);
				}
				
				boolean anonimous = p2pActivityType.getAnonimous();
				
				%>
				<div class="option-more">
				<span class="label-col">
					<liferay-ui:message key="p2ptask-correction-title" /> 
					<c:if test="<%=!anonimous %>">
						<span class="name">
							<liferay-ui:message key="by" /> 
							<%=propietary.getFullName() %>
						</span>
					</c:if>
					<c:if test="<%=anonimous %>">
						<span class="number">
							<liferay-ui:message key="p2ptaskactivity.number" /> 
							<%=cont%>
						</span>
					</c:if>
					<%=correctionDate %>
				</span>
					<div class="collapsable" style="padding-left:10px">
						<div class="container-textarea">
							<%  if(myp2pActivity!=null){
									JSONArray jArrayDes = null;
									try{
										jArrayDes = JSONFactoryUtil.createJSONArray(correctionText);
									}catch(Exception e){}
								
									if(jArrayDes!=null&&jArrayDes.length()>0){
										%><div class="p2pResponse">
											<div class="p2pAnswer"><%=myp2pActivity.getDescription()%></div>
										<ul><%
										for(int i=0;i<jArrayDes.length();i++){
											JSONObject jsonObjectDes = jArrayDes.getJSONObject(i);
											String valoration = null;
											if(jsonObjectDes!=null)
												valoration = jsonObjectDes.getString("text"+i);
											
												String question = evaluationCriteria.getString(i);
												
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
									}
								}
							%>
						</div>
						<%if(dlfile!=null){%>
						<div class="doc_descarga">
							<span><%=title%>&nbsp;(<%= sizeKb%> Kb)&nbsp;</span>
							<a href="<%=urlFile%>" class="verMas <%=cssLinkTabletClassP2PAct %>" target="_blank"><liferay-ui:message key="p2ptask-donwload" /></a>
						</div>
						<%
						}
						%>
					</div>
				</div>
				<%
			}
		}else{
			%>
			<div class="no-p2pActivites-corretion-resume">
				<liferay-ui:message key="no-p2pActivites-corretion-resume" />
			</div>
			<%
		}%>
	</div>
	<script type="text/javascript">
	<%=javascript%>
	</script>
	<%
	}
}
%>
<portlet:renderURL var="back">
	<portlet:param name="jspPage" value="/html/p2ptaskactivity/revisions.jsp" />
	<portlet:param name="actId" value="<%=String.valueOf(actId) %>" />
</portlet:renderURL>
<%
String urlback = "self.location = '"+back+"';";
%>
<aui:button-row>
	<aui:button cssClass="floatl" value="back" type="button" onClick="<%=urlback %>" style="margin-top:10px" />
</aui:button-row>
	