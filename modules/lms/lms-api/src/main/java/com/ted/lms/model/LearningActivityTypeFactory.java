package com.ted.lms.model;

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.xml.Element;

import java.util.Locale;

import javax.portlet.ActionRequest;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Interfaz para la factoría de actividades del LMS
 * @author Virginia Martín Agudo
 *
 */
@ProviderType
public interface LearningActivityTypeFactory {

	public LearningActivity getLearningActivity(long actId) throws PortalException;
	public LearningActivityType getLearningActivityType(long actId) throws PortalException;
	public LearningActivityType getLearningActivityType(LearningActivity activity) throws PortalException;

	public String getClassName();
	public long getClassNameId();
	public String getPortletId();
	public long getType();

	public String getName(Locale locale);
	public String getDescription(Locale locale);
	
	public String getURLSpecificContent();
	
	public String getIconCssClass();

	//public PortletURL getURLView(LiferayPortletResponse liferayPortletResponse,WindowState windowState) throws PortalException;
	public boolean hasAddPermission(PermissionChecker permissionChecker, long groupId, long classTypeId) throws Exception;
	public boolean hasPermission(PermissionChecker permissionChecker, long actId, String actionId) throws Exception;
	
	public void doImportStagedModel(PortletDataContext portletDataContext) throws PortalException;
	public void doExportStagedModel(PortletDataContext portletDataContext) throws PortalException;
	
	public boolean isActive(long companyId, long groupId);
	
	//public boolean isCategorizable();
	public boolean isScoreConfigurable();
	public double getDefaultScore();
	public String getScoreConfigurableProperty();
	public String getScoreConfigurableHelpMessageProperty();
	public boolean isTriesConfigurable();
	public int getDefaultTries();
	
	public boolean isFeedbackCorrectConfigurable();
	public String getDefaultFeedbackCorrect();
	public boolean isFeedbackNoCorrectConfigurable();
	public String getDefaultFeedbackNoCorrect();
	
	public boolean isManualCalificationAllowed();
	public boolean canDeleteTries();
	
	public boolean isAutoCorrect();
	
	public String getSpecificResultsPage();
	public void specificValidations(ActionRequest actionRequest) throws PortalException;
	
	public boolean canAccessFinished();
	
	public boolean hasDocuments();
}
