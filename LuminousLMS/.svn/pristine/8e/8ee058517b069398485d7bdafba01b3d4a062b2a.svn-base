package com.linguaclassica.access;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.Session;
import org.apache.wicket.authroles.authorization.strategies.role.RoleAuthorizationStrategy;
import org.apache.wicket.authroles.authorization.strategies.role.metadata.MetaDataRoleAuthorizationStrategy;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.mapper.parameter.UrlPathPageParametersEncoder;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import com.linguaclassica.administrator.ViewOrganizationsPage;
import com.linguaclassica.client.AssignmentsPage;
import com.linguaclassica.client.ClientVideoPage;
import com.linguaclassica.consultant.ClientAssignmentsPage;
import com.linguaclassica.consultant.ConsViewMsgsPage;
import com.linguaclassica.consultant.ConsultantClientsPage;
import com.linguaclassica.consultant.ConsultantVideoPage;
import com.linguaclassica.officeadmin.CreateNotificationPage;
import com.linguaclassica.officeadmin.OfficeAdminClientsPage;
import com.linguaclassica.officeadmin.OfficeAdminConsultantsPage;
import com.linguaclassica.officeadmin.OfficeAdminNotificationsPage;
import com.linguaclassica.officeadmin.OfficeAdminOverviewPage;
import com.linguaclassica.officeadmin.UploadFilePage;
import com.linguaclassica.officeadmin.UserCreateConfirmPage;
import com.linguaclassica.officeadmin.UserCreatePage;
import com.linguaclassica.shared.CommonOverviewPage;
import com.linguaclassica.shared.CreateMessagePage;
import com.linguaclassica.shared.MessagePage;
import com.linguaclassica.shared.MessagingPage;
import com.linguaclassica.shared.OtherResourcePage;
import com.linguaclassica.shared.TrainingMaterialsPage;
import com.linguaclassica.shared.TranscriptPage;
import com.linguaclassica.shared.UserAccountPage;
import com.linguaclassica.shared.ViewMessagesPage;


/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see com.linguaclassica.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{
	private Logger logger = LogManager.getLogger(WicketApplication.class);
	
	@Override
    public RuntimeConfigurationType getConfigurationType()
	{
        return RuntimeConfigurationType.DEPLOYMENT;
    }

	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return HomePage.class;
	}
	
	@Override
	public Session newSession(Request request, Response response)
	{
		return new LMSSession(request);
	}
	
	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();

		logger.debug("WicketApplication.init starts");
		
		getApplicationSettings().setPageExpiredErrorPage(SessionExpiredPage.class);
		getApplicationSettings().setAccessDeniedPage(SessionExpiredPage.class);
		getPageSettings().setRecreateBookmarkablePagesAfterExpiry(false);
		getSecuritySettings().setAuthorizationStrategy(new RoleAuthorizationStrategy(new UserRolesAuthorizer()));

		// Add configuration for each page
		// 1) security authorization: restrict to valid user roles
		// 2) URL mapper: use shorter name
		
		// Public pages do not need security authorization
		mount(new org.apache.wicket.core.request.mapper.MountedMapper("homepage", HomePage.class,
				new UrlPathPageParametersEncoder()));
		
		mount(new org.apache.wicket.core.request.mapper.MountedMapper("forgotpasswordpage", ForgotPasswordPage.class,
				new UrlPathPageParametersEncoder()));
		
		// Administrator pages
		MetaDataRoleAuthorizationStrategy.authorize(ViewOrganizationsPage.class, "ADMINISTRATOR");
		mount(new org.apache.wicket.core.request.mapper.MountedMapper("vieworganizationspage", ViewOrganizationsPage.class,
				new UrlPathPageParametersEncoder()));
		
		// Office Administrator pages
		MetaDataRoleAuthorizationStrategy.authorize(OfficeAdminOverviewPage.class, "OFFICE_ADMIN");
		mount(new org.apache.wicket.core.request.mapper.MountedMapper("officeadminoverviewpage", OfficeAdminOverviewPage.class,
				new UrlPathPageParametersEncoder()));
		
		MetaDataRoleAuthorizationStrategy.authorize(CreateNotificationPage.class, "CLIENT,CONSULTANT,OFFICE_ADMIN");
		mount(new org.apache.wicket.core.request.mapper.MountedMapper("oacreatenotificationpage", CreateNotificationPage.class,
				new UrlPathPageParametersEncoder()));
		
		MetaDataRoleAuthorizationStrategy.authorize(OfficeAdminNotificationsPage.class, "OFFICE_ADMIN");
		mount(new org.apache.wicket.core.request.mapper.MountedMapper("oaviewnotificationspage", OfficeAdminNotificationsPage.class,
				new UrlPathPageParametersEncoder()));
		
		MetaDataRoleAuthorizationStrategy.authorize(OfficeAdminConsultantsPage.class, "OFFICE_ADMIN");
		mount(new org.apache.wicket.core.request.mapper.MountedMapper("oaconsultantspage", OfficeAdminConsultantsPage.class,
				new UrlPathPageParametersEncoder()));
		
		MetaDataRoleAuthorizationStrategy.authorize(OfficeAdminClientsPage.class, "OFFICE_ADMIN");
		mount(new org.apache.wicket.core.request.mapper.MountedMapper("oaclientspage", OfficeAdminClientsPage.class,
				new UrlPathPageParametersEncoder()));

		MetaDataRoleAuthorizationStrategy.authorize(UserCreatePage.class, "OFFICE_ADMIN");
		mount(new org.apache.wicket.core.request.mapper.MountedMapper("createuserpage", UserCreatePage.class,
				new UrlPathPageParametersEncoder()));

		MetaDataRoleAuthorizationStrategy.authorize(UserCreatePage.class, "OFFICE_ADMIN");
		mount(new org.apache.wicket.core.request.mapper.MountedMapper("confirmcreateuserpage", UserCreateConfirmPage.class,
				new UrlPathPageParametersEncoder()));

		MetaDataRoleAuthorizationStrategy.authorize(UserCreatePage.class, "OFFICE_ADMIN");
		mount(new org.apache.wicket.core.request.mapper.MountedMapper("uploadfilepage", UploadFilePage.class,
				new UrlPathPageParametersEncoder()));

		// Consultant pages
		MetaDataRoleAuthorizationStrategy.authorize(ConsultantClientsPage.class, "CONSULTANT");
		mount(new org.apache.wicket.core.request.mapper.MountedMapper("coclientspage", ConsultantClientsPage.class,
				new UrlPathPageParametersEncoder()));
		
		MetaDataRoleAuthorizationStrategy.authorize(ClientAssignmentsPage.class, "CONSULTANT");
		mount(new org.apache.wicket.core.request.mapper.MountedMapper("coclientassignmentspage", ClientAssignmentsPage.class,
				new UrlPathPageParametersEncoder()));
		
		MetaDataRoleAuthorizationStrategy.authorize(ConsultantVideoPage.class, "OFFICE_ADMIN,CONSULTANT");
		mount(new org.apache.wicket.core.request.mapper.MountedMapper("consultantvideopage", ConsultantVideoPage.class,
				new UrlPathPageParametersEncoder()));

		// Client pages
		MetaDataRoleAuthorizationStrategy.authorize(ClientVideoPage.class, "CLIENT");
		mount(new org.apache.wicket.core.request.mapper.MountedMapper("clientvideopage", ClientVideoPage.class,
				new UrlPathPageParametersEncoder()));

		MetaDataRoleAuthorizationStrategy.authorize(AssignmentsPage.class, "CLIENT");
		mount(new org.apache.wicket.core.request.mapper.MountedMapper("assignmentspage", AssignmentsPage.class,
				new UrlPathPageParametersEncoder()));

		// Shared pages
		MetaDataRoleAuthorizationStrategy.authorize(CommonOverviewPage.class, "CLIENT,CONSULTANT");
		mount(new org.apache.wicket.core.request.mapper.MountedMapper("overviewpage", CommonOverviewPage.class,
				new UrlPathPageParametersEncoder()));
		
		MetaDataRoleAuthorizationStrategy.authorize(TrainingMaterialsPage.class, "OFFICE_ADMIN,CONSULTANT");
		mount(new org.apache.wicket.core.request.mapper.MountedMapper("trainingmaterialspage", TrainingMaterialsPage.class,
				new UrlPathPageParametersEncoder()));

		MetaDataRoleAuthorizationStrategy.authorize(CreateMessagePage.class, "CLIENT,CONSULTANT");
		mount(new org.apache.wicket.core.request.mapper.MountedMapper("createmessagingpage", CreateMessagePage.class,
				new UrlPathPageParametersEncoder()));

		MetaDataRoleAuthorizationStrategy.authorize(ViewMessagesPage.class, "CLIENT");
		mount(new org.apache.wicket.core.request.mapper.MountedMapper("viewmessagingpage", ViewMessagesPage.class,
				new UrlPathPageParametersEncoder()));

		MetaDataRoleAuthorizationStrategy.authorize(ConsViewMsgsPage.class, "CONSULTANT");
		mount(new org.apache.wicket.core.request.mapper.MountedMapper("consviewmsgspage", ConsViewMsgsPage.class,
				new UrlPathPageParametersEncoder()));

		MetaDataRoleAuthorizationStrategy.authorize(MessagingPage.class, "CLIENT,CONSULTANT");
		mount(new org.apache.wicket.core.request.mapper.MountedMapper("onemessagepage", MessagingPage.class,
				new UrlPathPageParametersEncoder()));

		MetaDataRoleAuthorizationStrategy.authorize(OtherResourcePage.class, "CLIENT,CONSULTANT,OFFICE_ADMIN");
		mount(new org.apache.wicket.core.request.mapper.MountedMapper("viewresourcespage", OtherResourcePage.class,
				new UrlPathPageParametersEncoder()));

		MetaDataRoleAuthorizationStrategy.authorize(MessagingPage.class, "CLIENT,CONSULTANT");
		mount(new org.apache.wicket.core.request.mapper.MountedMapper("transcriptpage", TranscriptPage.class,
				new UrlPathPageParametersEncoder()));

		mount(new org.apache.wicket.core.request.mapper.MountedMapper("myaccountpage", UserAccountPage.class,
				new UrlPathPageParametersEncoder()));

		mount(new org.apache.wicket.core.request.mapper.MountedMapper("messagepage", MessagePage.class,
				new UrlPathPageParametersEncoder()));

		getComponentInstantiationListeners().add(new SpringComponentInjector(this));

		logger.debug("WicketApplication.init ends");
     }
}
