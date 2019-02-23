package com.linguaclassica.access;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginHelper
{	
	public static String encodePassword (String password) throws NoSuchAlgorithmException, NullPointerException
	{
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		md.update(password.getBytes());

		byte byteData[] = md.digest();

		//convert the byte to hex format
		StringBuffer hexString = new StringBuffer();
		for (int i=0;i<byteData.length;i++) {
			String hex=Integer.toHexString(0xff & byteData[i]);
			if(hex.length()==1) hexString.append('0');
			hexString.append(hex);
		}
		System.out.println("Hex format : " + hexString.toString());
		
		return hexString.toString();
	}
}
