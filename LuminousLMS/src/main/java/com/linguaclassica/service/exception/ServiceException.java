package com.linguaclassica.service.exception;

import org.springframework.dao.UncategorizedDataAccessException;

/**
 * This class represents a generic service exception where the root cause is unknown or does
 * not easily map to an error where the cause is meaningful to the client code.
 * 
 * Preference should be given to using the other service exceptions which allow for more
 * meaningful error recovery.
 * 
 * This class extends UncategorizedDataAccessException so that it can be used by the 
 * GenericExceptionHandler.
 * 
 * @author Paul
 *
 */
public class ServiceException extends UncategorizedDataAccessException {

	private static final long serialVersionUID = -7381756743212857186L;

	public ServiceException() {
		super("Unspecified service exception", null);
	}

	public ServiceException(String message) {
		super(message, null);
	}

	public ServiceException(Throwable arg0) {
		super("Unspecified service exception", arg0);
	}

	public ServiceException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}
