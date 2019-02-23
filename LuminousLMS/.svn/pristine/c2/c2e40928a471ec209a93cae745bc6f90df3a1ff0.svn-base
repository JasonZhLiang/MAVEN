package com.linguaclassica.access;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

import com.linguaclassica.model.UserModel;

/**
 * A Wicket WebSession object that creates the session for the Wicket 
 * access management classes only
 * @author Eugene Price
 * @Copyright("2017 Lingua Classica")
 */

public class LMSSession extends WebSession
{
	private static final long serialVersionUID = 1L;

	private Logger logger = LogManager.getLogger(LMSSession.class);
	
	private boolean multiplePermissions = false;
	private Integer currentOUP = 0;
	private Integer currentOrganizationId = 0;
	private Integer currentUserId = 0;	// local use only
	private Integer currentPermissionId = 0;
	private Integer otherResId = 0;
	private String currentPermissionStr = "";
	private String language = "English";
	private String logInfo = "";
	

	public static LMSSession get() {
		return (LMSSession) Session.get();
	}
	
	public LMSSession(Request request) {
		super(request);
	}

	/**
	 * Retrieve the active user from the session
	 * @param request
	 * @return An instance of the user model or NULL if no active user is defined
	 */
	public UserModel getUser(Request request)
	{
		logger.debug("LMSSession.getUser");

		// Access to the underlying HTTP container is only available through the request
		HttpServletRequest servletRequest = (HttpServletRequest)request.getContainerRequest();
		HttpSession httpSession = servletRequest.getSession();
		
		if(httpSession != null){
			logger.debug("session object not null");
		}
		
		// Get the user model from the container session
		UserModel userModel = (UserModel) httpSession.getAttribute(UserModel.LCUSER);
		if(userModel != null){
			logger.debug("userModel NOT null");
		}
		return userModel;		
	}


	/**
	 * Set the current user for this session
	 * @param request - The current request being processed
	 * @param user - User to set as active in session
	 */
	public void setUser(Request request, UserModel user)
	{
		// Access to the underlying HTTP container is only available through the request
		HttpServletRequest servletRequest = (HttpServletRequest)request.getContainerRequest();
		HttpSession httpSession = servletRequest.getSession();

		// Set the user in the session. We use the underlying container session rather than
		// the wicket session as the user is also needed by the parser and this way we only
		// have one copy of the data.
		httpSession.setAttribute(UserModel.LCUSER, user);
		currentUserId = user.getId();
		updateLogInfo();
	}
	
	
	/*
	 * The below, commented out for now, shows how we might use spring to call an .xml configuration file.
	 * We do not have a use for this at present, but here it is if we need it in the future.
	 */
	//ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
	
	
	public boolean areMultiplePermissions()
	{
		return multiplePermissions;
	}
	
	public void setMultiplePermissions(boolean b)
	{
		multiplePermissions = b;
	}
	
	public Integer getCurrentOUP()
	{
		return currentOUP;
	}
	
	public void setCurrentOUP(Integer oupId)
	{
		currentOUP = oupId;
	}
	
	public Integer getCurrentOrganization()
	{
		return currentOrganizationId;
	}
	
	public void setCurrentOrganization(Integer iid)
	{
		currentOrganizationId = iid;
		updateLogInfo();
	}
	
	public Integer getCurrentPermission()
	{
		return currentPermissionId;
	}
	
	public String getCurrentPermissionStr()
	{
		return currentPermissionStr;
	}
	
	public void setCurrentPermission(Integer pid, String permStr)
	{
		currentPermissionId = pid;
		currentPermissionStr = permStr;
		updateLogInfo();
	}
	
	public String getLanguage()
	{
		return language;
	}

	public void setLanguage(String language)
	{
		this.language = language;
	}

	
	private void updateLogInfo()
	{
		logInfo = String.format("o:%d u:%d p:%d ", currentOrganizationId, currentUserId, currentPermissionId);
	}
	
	/**
	 * Get data string for the logged-in user
	 * @return
	 */
	public String getLogInfo()
	{
		return logInfo;
	}
	
	public Integer getOtherResId() {
		return otherResId;
	}

	public void setOtherResId(Integer otherResId) {
		this.otherResId = otherResId;
	}
	
}

