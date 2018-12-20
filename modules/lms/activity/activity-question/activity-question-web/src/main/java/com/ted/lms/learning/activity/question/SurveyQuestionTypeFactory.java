package com.ted.lms.learning.activity.question;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferay.portal.kernel.util.WebKeys;
import com.ted.lms.learning.activity.question.constants.QuestionsWebPortletKeys;
import com.ted.lms.learning.activity.question.model.QuestionTypeFactory;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.portlet.PortletRequest;
import javax.portlet.WindowStateException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    immediate = true,
    property = {},
    service = QuestionTypeFactory.class
)
public class SurveyQuestionTypeFactory extends OptionsQuestionTypeFactory{
	
	public static final long TYPE = 6;

	@Override
	public String getClassName() {
		return SurveyQuestionTypeFactory.class.getName();
	}

	@Override
	public long getType() {
		return TYPE;
	}
	
	@Override
	public String getTitle(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		return LanguageUtil.get(resourceBundle, "question.survey.title");
	}
	
	@Override
	public String getDescription(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		return LanguageUtil.get(resourceBundle, "question.survey.description");
	}
	
	@Override
	public String getURLEditAnswer() {
		return "/question/survey/edit.jsp";
	}
	
	@Override
	public String getURLAddAnswer(LiferayPortletResponse liferayPortletResponse) {
		LiferayPortletURL liferayPortletURL = liferayPortletResponse.createLiferayPortletURL(QuestionsWebPortletKeys.EDIT_QUESTIONS, 
				PortletRequest.RENDER_PHASE);
		try {
	        liferayPortletURL.setWindowState(LiferayWindowState.EXCLUSIVE);
	    }
	    catch (WindowStateException wse) {
	    	wse.printStackTrace();
	    }
		liferayPortletURL.setParameter(WebKeys.PORTLET_CONFIGURATOR_VISIBILITY, StringPool.TRUE);
		liferayPortletURL.setParameter("mvcPath", "/question/survey/add.jsp");
	    return liferayPortletURL.toString();
	}
	
	@Override
	public String getPortletId() {
		return QuestionsWebPortletKeys.EDIT_QUESTIONS;
	}
	
	@Reference(
		unbind = "-"
	)
	public void setResourceBundleLoader(ResourceBundleLoader resourceBundleLoader) {

		this.resourceBundleLoader = resourceBundleLoader;
	}

	protected ResourceBundleLoader resourceBundleLoader;

}
