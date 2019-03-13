<%@page import="java.util.ArrayList"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="com.liferay.portal.kernel.json.JSONObject"%>
<%@page import="com.liferay.portal.kernel.json.JSONFactoryUtil"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.liferay.portal.kernel.service.UserLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.model.User"%>
<%@page import="com.ted.lms.learning.activity.p2p.service.P2PActivityCorrectionsLocalServiceUtil"%>
<%@page import="com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.liferay.portal.kernel.json.JSONArray"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>

<liferay-ui:error key="p2ptask-no-empty-answer" message="learning-activity.p2p.steps.correct-activities.error.empty-answer" />
<liferay-ui:error key="error-p2ptask-correction" message="learning-activity.p2p.steps.correct-activities.error" />
<liferay-ui:error key="p2ptaskactivity-error-file-type" message="learning-activity.p2p.error.file.type" />
<liferay-ui:error key="p2ptaskactivity-error-file-name" message="learning-activity.p2p.error.file.name" />
<liferay-ui:error key="p2ptaskactivity-error-file" message="learning-activity.p2p.error.file" />
<liferay-ui:success key="p2p-activity-assign-correct" message="learning-activity.p2p.success.p2p-activity-assign-correct" />
<liferay-ui:error key="no-p2p-activity-assign" message="learning-activity.p2p.error.no-p2p-activity-assign" />

<%
String textoCorrecion = "";
boolean correctionsCompleted = ParamUtil.getBoolean(request, "correctionsCompleted", false);
boolean correctionsSaved = ParamUtil.getBoolean(request, "correctionSaved", false);

long resultuser=ParamUtil.getLong(request,"resultuser",0);

int numCorrecciones = p2pActivityType.getNumValidations();
boolean configAnonimous = p2pActivityType.getAnonimous();
boolean result = p2pActivityType.getResult();

JSONArray evaluationCriteria = p2pActivityType.getEvaluationCriteria();

System.out.println("are: " + P2PActivityCorrectionsLocalServiceUtil.areAllCorrectionsDoneByUserInP2PActivity(201, 20139, 1));

%>

<!-- Start PopUp confirmation -->

<div id="<portlet:namespace />p2pconfrmCorrec" class="hide">
	<div class="contDesc description">
		<%
			for(int i=0;i<evaluationCriteria.length();i++){ 
				String des = evaluationCriteria.getString(i);
				if(i==0 || (des != null && des.length() > 0)){
					if(Validator.isNull(des)){
						des = LanguageUtil.get(themeDisplay.getLocale(), "feedback");
					}
		%>
				
				<p><label for="${renderResponse.getNamespace() }contentDescriptionCorrec_<%=i%>"><%=des %>: </label></p>
				<p><span id="${renderResponse.getNamespace() }contentDescriptionCorrec_<%=i%>"></span></p>
		<%		}else{
					break;			
				}
			}
		%>
	</div>
	<div class="contDesc">
		<p><label for="${renderResponse.getNamespace() }contentFileCorrec"><liferay-ui:message key="learning-activity.p2p.steps.upload-activity.file-name" />: </label> </p>
		<p><span id="${renderResponse.getNamespace() }contentFileCorrec"></span></p>
	</div>
	
	<c:if test="<%=result %>">
		<div class="contDesc">
			<p><label for="${renderResponse.getNamespace() }contentResult"><liferay-ui:message key="learning-activity.p2p.result" />: </span></p> 
			<p><span id="${renderResponse.getNamespace() }contentResult"></span></p>
		</div>
	</c:if>
	<p class="message"><liferay-ui:message key="learning-activity.p2p.steps.correct-activities.confirmation.message" /></p>
	
	<aui:button-row>
		<aui:button type="button" onclick="${renderResponse.getNamespace()}closeConfirmationCorrection()" value="cancel" />
		<aui:button type="button" cssClass="btn-primary" name="submitCorrec" value="learning-activity.p2p.steps.correct-activities.send-correction" />
	</aui:button-row>
</div>
<!-- End PopUp confrimation -->

<div id="<portlet:namespace />p2pSaved" class="hide">
	<liferay-ui:message key="learning-activity.p2p.steps.correct-activities.save-correction.message" />
</div>

<div id="<portlet:namespace />p2pCorrectionCompleted" class="hide">
	<liferay-ui:message key="learning-activity.p2p.steps.correct-activities.finish-corrections.message" />
</div>



<aui:script>
	Liferay.provide(
	        window,
	        '<portlet:namespace />checkDataformC',
	        function (thisForm, thisEditor) {
				var A = AUI();
				<%
					for(int i=0;i<evaluationCriteria.length();i++){ 
						String des = (String)evaluationCriteria.get(i);
						if(i==0 || Validator.isNotNull(des)){
							if(Validator.isNull(des)){
								des = LanguageUtil.get(themeDisplay.getLocale(), "feedback");
							}%>
							
							var editor = thisEditor+"_<%=i%>";
							console.log("editor: " + editor);
							var descriptionVal = $('#' + editor).val();
							
							if (descriptionVal == "" || descriptionVal == "<%= StringEscapeUtils.unescapeHtml(textoCorrecion) %>") {
								alert(Liferay.Language.get("p2ptask-no-empty-answer"));
								return;
							}
					<%	}else{
							break;			
						}
					}
					%>
					<portlet:namespace />openPopUpCorrection(thisForm, thisEditor);
	        },
	        ['node']
	);
	
	Liferay.provide(
	        window,
	        '<portlet:namespace />openPopUpCorrection',
	        function (formName, thisEditor) {
	        	console.log("formName: " + formName);
	        	console.log("thisEditor: " + thisEditor);
				var A = AUI();
				var selector = 'form[name="'+formName+'"]';
				var fileName = A.one(selector).one('input[name="<portlet:namespace />fileName"]').val();

				var textResult = ''; 
				
				<%for(int i=0;i<evaluationCriteria.length();i++){
					String des = evaluationCriteria.getString(i);
					if(i==0 || (des != null && des.length() > 0)){
						if(Validator.isNull(des)){
							des = LanguageUtil.get(themeDisplay.getLocale(), "feedback");
						}%>

						var editor = thisEditor+"_<%=i%>";
						var textDesc = $("#" + editor).val();

						A.one("#<portlet:namespace />contentDescriptionCorrec_<%=i%>").html(textDesc);
					<%}else{
						break;
					}
				}%>
				
				if(	A.one(selector).one('select[name="<portlet:namespace />resultuser"]') != null){
					textResult = A.one(selector).one('select[name="<portlet:namespace />resultuser"]').val();
				}

				if (fileName != "") {
					var pos = fileName.lastIndexOf("\\");
					if (pos > 0) {
						fileName = fileName.substring(pos + 1);
					}
				} else {
					
					fileName = Liferay.Language.get("learning-activity.p2p.error.file.empty");
				}
				
				var <portlet:namespace />p2pconfrmCorrecBody = $('#<portlet:namespace />p2pconfrmCorrec').html();
				
				if(window.<portlet:namespace />p2pconfrmCorrecpopup){
					window.<portlet:namespace />p2pconfrmCorrecpopup.show();
				}else{
					window.<portlet:namespace />p2pconfrmCorrecpopup = Liferay.Util.Window.getWindow(
							{
								dialog: {
									modal: true,
									resizable: false,
									width: "auto",
									heigth: "auto",
									centered: true,
									bodyContent: <portlet:namespace />p2pconfrmCorrecBody
								},
								id: '<portlet:namespace />showp2pconfrmCorrec',
								title: Liferay.Language.get('learning-activity.p2p.steps.correct-activities.confirmation.title'),
								
							}
						).render();
				}
		        
				A.one("#<portlet:namespace />contentFileCorrec").html(fileName);

				if(textResult != ''){
					A.one("#<portlet:namespace />contentResult").html(textResult);
				}
				A.one("#<portlet:namespace />submitCorrec").on('click', function(){<portlet:namespace />commitFormCorrection(formName);});
				
				window.<portlet:namespace />p2pconfrmCorrec.show();
	        },
	        ['node','aui-dialog']
	);
	
	Liferay.provide(
	        window,
	        '<portlet:namespace />commitFormCorrection',
	        function (formName) {
				var selector = 'form[name="'+formName+'"]';
				$("#<portlet:namespace />submitCorrec").attr("disabled", "disabled");
				$(selector).submit();
	        },
	        ['node']
	);
	
	Liferay.provide(
	        window,
	        '<portlet:namespace />openSaved',
	        function () {
	        	
	        	var <portlet:namespace />p2pSavedBody = $('#<portlet:namespace />p2pSaved').html();

				if(window.<portlet:namespace />p2pSavedpopup){
					window.<portlet:namespace />p2pSavedpopup.show();
				}else{
					window.<portlet:namespace />p2pSavedpopup = Liferay.Util.Window.getWindow(
							{
								dialog: {
									modal: true,
									resizable: false,
									width: "auto",
									heigth: "auto",
									centered: true,
									bodyContent: <portlet:namespace />p2pSavedBody
								},
								id: '<portlet:namespace />p2pSaved',
								title: Liferay.Language.get('learning-activity.p2p.steps.correct-activities.save-correction.title'),
								
							}
						).render();
				}

	        },
	        ['node','aui-dialog']
	);
	
	Liferay.provide(
	        window,
	        '<portlet:namespace />openCompleted',
	        function () {
	        	
	        	var <portlet:namespace />p2pCorrectionCompletedBody = $('#<portlet:namespace />p2pCorrectionCompleted').html();

				if(window.<portlet:namespace />p2pCorrectionCompletedBody){
					window.<portlet:namespace />p2pCorrectionCompletedBody.show();
				}else{
					window.<portlet:namespace />p2pCorrectionCompletedBody = Liferay.Util.Window.getWindow(
							{
								dialog: {
									modal: true,
									resizable: false,
									width: "auto",
									heigth: "auto",
									centered: true,
									bodyContent: <portlet:namespace />p2pCorrectionCompletedBody
								},
								id: '<portlet:namespace />p2pCorrectionCompleted',
								title: Liferay.Language.get('learning-activity.p2p.steps.correct-activities.finish-corrections.title'),
								
							}
						).render();
				}

	        },
	        ['node','aui-dialog']
	);
	
	Liferay.provide(
	        window,
	        '<portlet:namespace />closeConfirmationCorrection',
	        function() {
				window.<portlet:namespace />p2pconfrmCorrecpopup.hide();
	        }
	    );
	
	</aui:script>

<%

long userId = themeDisplay.getUserId();
int cont = 0;
List<Long> listIdActivY = new ArrayList<Long>(); 

boolean allCorrected = true;

//Obtenemos todas las correcciones que tiene asignado el usuario.
List<P2PActivityCorrections> p2pActList = P2PActivityCorrectionsLocalServiceUtil.getCorrections(actId, userId);
System.out.println("correcciones asignadas: " + p2pActList.size());

int contaValidations = 0;

List<P2PActivityCorrections> listDone = P2PActivityCorrectionsLocalServiceUtil.getCorrectionsDoneByUser(actId, userId);

%>
<div class="numbervalidations">
	<c:if test="<%=p2pActivityType.getNumValidations()-listDone.size() > 0 %>">
		<%String []arg = {String.valueOf(p2pActivityType.getNumValidations()-listDone.size()) , String.valueOf(p2pActivityType.getNumValidations())}; %>
		<liferay-ui:message key="learning-activity.p2p.steps.correct-activities.number-validations" arguments="<%=arg %>" /> 
	</c:if>
	<c:if test="<%=p2pActivityType.getNumValidations()-listDone.size() == 0 %>">
		<liferay-ui:message key="learning-activity.p2p.steps.correct-activities.validations-completed" /> 
	</c:if>
</div>

<%
if(!p2pActList.isEmpty()){
	String fullName ="";
	User propietary;
	boolean anonimous;
	P2PActivity p2pActivity = null;%>
	
	<liferay-ui:panel-container extended="false"  persistState="false">
	
	<%for (P2PActivityCorrections myP2PActiCor : p2pActList){
		if(contaValidations >= p2pActivityType.getNumValidations()){
			break;
		}
		
		p2pActivity = P2PActivityLocalServiceUtil.getP2PActivity(myP2PActiCor.getP2pActivityId());
		propietary = UserLocalServiceUtil.fetchUser(p2pActivity.getUserId());
		anonimous  = configAnonimous;
		if(propietary!=null){
			fullName = propietary.getFullName();
		}else{
			anonimous = true;
		}
		cont++;
		//Si no estamos en el usuario actual.
		if(p2pActivity.getUserId()!=themeDisplay.getUserId()){
			System.out.println("fecha de corrección: " + myP2PActiCor.getDate());
			String urlFile = "";
			int sizeKb = 0;
			int size = 0;
			String title = "";
			String descriptionFile = "";
			
			if(p2pActivity.getFileEntryId() != 0){
				
				try{
					FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(p2pActivity.getFileEntryId());
					FileVersion fileVersion = fileEntry.getFileVersion();
					urlFile = DLUtil.getDownloadURL(fileEntry, fileVersion, themeDisplay, "");
					size = Integer.parseInt(String.valueOf(fileEntry.getSize()));
					sizeKb = size/1024; //Lo paso a Kilobytes
					title = fileEntry.getTitle();
					descriptionFile = fileEntry.getDescription();
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
							<a href="<%=urlFile%>" class="<%=cssLinkTabletClassP2PCorrect %>" target="_blank"><liferay-ui:message key="learning-activity.p2p.steps.correct-activities.download-file" /></a>
						</div>
					</c:if>
				
					<%//Si la fecha de corrección es null, es que no se ha corregido la tarea.
					if(myP2PActiCor.getDate() == null){
						allCorrected = false;
						%>
							<portlet:actionURL name="saveCorrection" var="saveCorrectionURL"/>
							<aui:form enctype="multipart/form-data" method="post" action="${saveCorrectionURL }" name='<%="f1_" + cont%>'>
								<aui:input type="hidden" name="actId" value="<%=actId%>"  />
								<aui:input type="hidden" name="p2pActivityCorrectionId" value="0"  />
								<aui:input type="hidden" name="p2pActivityId" value="<%=p2pActivity.getP2pActivityId()%>"  />
								<aui:input type="hidden" name="userId" value="<%=userId%>"  />
								<aui:input type="hidden" name="cont" value="<%=cont%>" />

									<%for(int i=0;i<evaluationCriteria.length();i++){ 
										String des = evaluationCriteria.getString(i);
										
										if(i==0 || (des != null && des.length() > 0)){
											if(Validator.isNull(des)){
												des = LanguageUtil.get(themeDisplay.getLocale(), "feedback");
											}%>
												<label for="<%=renderResponse.getNamespace() +"descriptionContent_"+cont+"_"+i%>"><%=des %></label>
												<liferay-editor:editor
													contents='' 
													editorName='alloyeditor'
													name='<%="descriptionContent_"+cont+"_"+i%>'
													required="<%= true %>" 
													onChangeMethod='<%="OnChangeEditor" + cont+"_"+i %>'
												/>
												<aui:input name='<%="description_"+cont+"_"+i%>' type="hidden" value="" />
												<aui:script>
													function <portlet:namespace />OnChangeEditor<%=cont + "_" + i%>(html) {
														$('#<portlet:namespace />description_<%=cont + "_" + i%>').val(html);
													}
												</aui:script>
			
										<%} 	
									}%>

									
								<liferay-ui:error key="p2ptaskactivity-error-file-size" message="learning-activity.p2p.error.file.size" />
								<div class="container-file">
									<aui:input name="fileName" type="file" value="" />
								</div>
								<c:if test="<%=result %>">
									<aui:select name="resultUser"  label="learning-activity.p2p.steps.correct-activities.add-result">
								    	<%for(int i=100; i>=0; i=i-10){%>
								        	<option value="<%=i%>"><%=i %></option>
								        <%}%>
								    </aui:select>
								</c:if>
								<div>
									<aui:button type="button" value="learning-activity.p2p.steps.correct-activities.send-correction" 
										onclick="<%=renderResponse.getNamespace() + \"checkDataformC('\" + renderResponse.getNamespace() + \"f1_\" + cont + \"','\" + renderResponse.getNamespace() + \"description_\" + cont + \"')\" %>" />
								</div>
							</aui:form>
					<%
					} //Si ya ha corregido la tarea, solo debemos mostrar lo que se ha introducido.
					else {
						listIdActivY.add(p2pActivity.getPrimaryKey());
						
						String description = myP2PActiCor.getDescription();
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
									<a href="<%=urlFileCorrection%>" class="verMas <%=cssLinkTabletClassP2PCorrect %>" target="_blank"><liferay-ui:message key="learning-activity.p2p.steps.correct-activities.download-file" /></a>
								</div>
							<%	}
							}%>
						</div>
						<c:if test="<%=result %>">
							<div class="color_tercero font_13"><liferay-ui:message key="learning-activity.p2p.result" />: <%=myP2PActiCor.getResult() %></div>
						</c:if>
						<%
					}%>
				</div>
			</liferay-ui:panel>
		<%}
		//Mostrar sólo el numero de correcciones que debe corregir.
		contaValidations++;
	}%>
	</liferay-ui:panel-container>
<%} else {%>
	
	<div class="alert alert-info">
		<liferay-ui:message key="learning-activity.p2p.steps.correct-activities.no-assign-activities" />
	</div>
	<c:if test='<%=p2pActivityType.getAskForP2PActivities()%>'>
		<liferay-portlet:actionURL name="askForP2PActivities" var="askForP2PActivitiesURL">
			<liferay-portlet:param name="actId" value="<%=String.valueOf(actId) %>" />
		</liferay-portlet:actionURL>
		<aui:button onClick="${askForP2PActivitiesURL }" value="learning-activity.p2p.steps.correct-activities.ask-for-p2p-corrections"/>
	</c:if>
<%}%>

<%
if(correctionsCompleted){
	request.setAttribute("correctionSaved", false);
	request.setAttribute("correctionsCompleted", false);
	renderResponse.setProperty("clear-request-parameters", Boolean.TRUE.toString());%>
	<script>
		$( document ).ready(function() {
			<portlet:namespace />openCompleted();
		});
	</script>
<%}else if(correctionsSaved){
	request.setAttribute("correctionSaved", false);
	renderResponse.setProperty("clear-request-parameters", Boolean.TRUE.toString());%>
	<script>
		$( document ).ready(function() {
			<portlet:namespace />openSaved();
		});
	</script>
<%}%>