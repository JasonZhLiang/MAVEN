package com.linguaclassica.access;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// The class contains all the utilities customised specially for Adjuveris
// We need this customise routines, instead of relying on wicket or java script validators, 
// in the scenario whereby the inputs are conditionally optional depending on the scenarios
public final class CommonUtilities {
	// change the regex pattern if the input requirement rule changes.
	private static Pattern SINGLE_NAME_FILTER = Pattern.compile("^[A-Za-z]$");
	private static Pattern MULTI_NAME_FILTER = Pattern.compile("^[A-Za-z][A-Za-z _'-]*[A-Za-z]$");
	private static int MaxNameLength = 35;   // BSEN 7372:1993
	
    // The pattern matches any alphanumeric pattern between 6 to 20 times
	// s is the input String object
	// change the regex pattern if the input requirement rule changes.
	private static Pattern VALID_PASSWORD_INPUT = Pattern.compile("[A-Za-z0-9]{6,20}");

	//private static Pattern EMAIL_FILTER = Pattern.compile("^[_A-Za-z0-9-]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9-]+)*((.[A-Za-z]{2,}){1}$)");
	private static Pattern EMAIL_NAME_FILTER = Pattern.compile("[_A-Za-z0-9-]+(.[_A-Za-z0-9-]+)*");
	private static Pattern EMAIL_SITE_FILTER = Pattern.compile("[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*");
	private static Pattern EMAIL_TLD_FILTER = Pattern.compile("[A-Za-z]{2,}");
	
	// The pattern for group, institution and other names
	private static Pattern NUM_NAME_FILTER = Pattern.compile("^[A-Za-z0-9][A-Za-z0-9 _'-]*[A-Za-z0-9]$");
	
	// The pattern for titles
	private static Pattern SINGLE_TITLE_FILTER = Pattern.compile("^[A-Za-z0-9]$");

	/**
	 * Check whether the parameter represents a valid user's name
	 * 
	 * @param String s
	 * @return true when valid
	 */
	public static boolean checkValidUserNameInput(String s)
	{
		if (s == null)
		{
			return false;
		}
		else if (s.length() < 1)
		{
			return false;
		}
		else if (s.length() > MaxNameLength)
		{
			return false;
		}
		else
		{
			Matcher singlem = SINGLE_NAME_FILTER.matcher(s);
			if (!singlem.matches())
			{
				Matcher multim = MULTI_NAME_FILTER.matcher(s);
				if (!multim.matches())
				{
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Check whether the parameter represents a valid password
	 * 
	 * @param String s
	 * @return true when valid
	 */
	public static boolean checkPasswordInput(String s) {
		if (s == null) {
			return false;
		} else {
			Matcher m = VALID_PASSWORD_INPUT.matcher(s);
			return m.matches();
		}
	}
	
	/**
	 * Check whether the parameter represents a valid email address
	 * 
	 * @param String s
	 * @return true when valid
	 */
	public static boolean checkEmailAddress(String s)
	{
		if (s != null)
		{
			String strim = s.trim();
			int atIndex = strim.indexOf('@');
			if (atIndex > 0)
			{
				String sname = strim.substring(0, atIndex);
				String sdomain = strim.substring(atIndex + 1);
				
				// Other forms are valid but assuming the form something.tld
				// tld = top level domain
				int dotIndex = sdomain.lastIndexOf('.');
				if (dotIndex > 1)
				{
					String ssite = sdomain.substring(0, dotIndex);
					String stld = sdomain.substring(dotIndex + 1);
					if ((ssite.length() > 0) && (stld.length() > 0))
					{
						Matcher mname = EMAIL_NAME_FILTER.matcher(sname);
						boolean bname = mname.matches();
						Matcher msite = EMAIL_SITE_FILTER.matcher(ssite);
						boolean bsite = msite.matches();
						Matcher mtld = EMAIL_TLD_FILTER.matcher(stld);
						boolean btld = mtld.matches();
						return bname && bsite && btld;
					}
				}
			}
		}
		return false;
	}
	
	
	/**
	 * Check whether the parameter represents a valid name for a group, institution or other item.
	 * Allow letters, numbers and limited punctuation.
	 * 
	 * @param String s
	 * @return true when valid
	 */
	public static boolean checkAlphanumeric(String s, int minlen)
	{
		if (s != null)
		{
			String strim = s.trim();
			if (strim.length() >= minlen)
			{
				if (strim.length() >= 2)
				{
					Matcher m = NUM_NAME_FILTER.matcher(s);
					return m.matches();
				}
				else
				{
					Matcher m = SINGLE_TITLE_FILTER.matcher(s);
					return m.matches();
				}
			}
		}
		return false;
	}
}
