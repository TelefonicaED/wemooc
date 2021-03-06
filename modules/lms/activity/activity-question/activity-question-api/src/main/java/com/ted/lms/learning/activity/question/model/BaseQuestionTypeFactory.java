package com.ted.lms.learning.activity.question.model;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.xml.Element;
import com.ted.lms.learning.activity.question.constants.QuestionsWebPortletKeys;

import java.util.Locale;

import javax.portlet.PortletRequest;

public abstract class BaseQuestionTypeFactory implements QuestionTypeFactory{
	
	@Override
	public long getClassNameId() {
		return PortalUtil.getClassNameId(getClassName());
	}
	
	@Override
	public String getTitle(Locale locale) {
		String modelResourceNamePrefix =
			ResourceActionsUtil.getModelResourceNamePrefix();

		String key = modelResourceNamePrefix.concat(getClassName());

		String value = LanguageUtil.get(locale, key, null);

		if (value == null) {
			value = getClassName();
		}

		return value;
	}
	
	@Override
	public String getDescription(Locale locale) {
		return "";
	}
	
	@Override
	public boolean getPenalize() {
		return true;
	}
	
	@Override
	public boolean isPartialCorrectAvailable() {
		return false;
	}
	
	@Override
	public boolean isManualCorrection() {
		return false;
	}
	
	@Override
	public int getMinNumAnswers() {
		return 0;
	}
	
	@Override
	public int getMinNumCorrectAnswers() {
		return 0;
	}
	
	@Override
	public String getURLQuestionExtraData() {
		return null;
	}
	
	@Override
	public String getURLAddAnswer(LiferayPortletResponse liferayPortletResponse) {
		String urlAddAnswer = null;
		if(Validator.isNotNull(getURLEditAnswer())) {
			LiferayPortletURL liferayPortletURL = liferayPortletResponse.createLiferayPortletURL(getPortletId(), 
					PortletRequest.RESOURCE_PHASE);
			liferayPortletURL.setParameter(WebKeys.PORTLET_CONFIGURATOR_VISIBILITY, StringPool.TRUE);
			liferayPortletURL.setParameter("mvcPath", getURLEditAnswer());
			liferayPortletURL.setParameter("namespace", liferayPortletResponse.getNamespace());
			urlAddAnswer = liferayPortletURL.toString();
		}
		return urlAddAnswer;
	}

	@Override
	public final String getURLAddQuestion(LiferayPortletResponse liferayPortletResponse) {
		LiferayPortletURL liferayPortletURL = liferayPortletResponse.createLiferayPortletURL(QuestionsWebPortletKeys.EDIT_QUESTIONS, 
				PortletRequest.RESOURCE_PHASE);
		liferayPortletURL.setParameter(WebKeys.PORTLET_CONFIGURATOR_VISIBILITY, StringPool.TRUE);
		liferayPortletURL.setParameter("mvcPath", "/questions_question.jsp");
		liferayPortletURL.setParameter("questionType", String.valueOf(getType()));
		liferayPortletURL.setParameter("namespace", liferayPortletResponse.getNamespace());
		liferayPortletURL.setCopyCurrentRenderParameters(false);
		
	    return liferayPortletURL.toString();
	}
	
	@Override
	public String getURLEditAnswer() {
		return null;
	}
	
	@Override
	public String[] getJavascriptImport(String cdnHost) {
		return null;
	}
	
	@Override
	public String getName() {
		return "";
	}
	
	@Override
	public String[] getXMLType() {
		return new String[] {};
	}
	
	@Override
	public Question importQuestionXML(long userId, long groupId, long actId, Element questionElement, ServiceContext serviceContext) throws PortalException {
		return null;
	}
}
