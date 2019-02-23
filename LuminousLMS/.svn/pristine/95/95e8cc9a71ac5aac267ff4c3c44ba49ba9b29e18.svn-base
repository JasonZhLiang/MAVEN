package com.linguaclassica.access;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
 * References:
 * https://stackoverflow.com/questions/47601421/how-to-secure-all-pages-in-folder-with-spring-security
 * https://github.com/apache/wicket/tree/51d9e533dc3a45183210a1f4852851b485f02ea3/wicket-examples/src/main/java/org/apache/wicket/examples/authorization
 * https://github.com/apache/wicket/blob/51d9e533dc3a45183210a1f4852851b485f02ea3/wicket-examples/src/main/java/org/apache/wicket/examples/authorization/UserRolesAuthorizer.java
 */

import org.apache.wicket.authroles.authorization.strategies.role.IRoleCheckingStrategy;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;

public class UserRolesAuthorizer implements IRoleCheckingStrategy
{
	private Logger logger = LogManager.getLogger(getClass());
	
	@Override
	public boolean hasAnyRole(Roles roles)
	{
		String currentPermission = LMSSession.get().getCurrentPermissionStr().toUpperCase();
		boolean permitted = roles.toString().indexOf(currentPermission) >= 0;
		if (!permitted)
		{
			logger.error("Required role {} from roles {}", currentPermission, roles);
		}
		return permitted;
	}

}
