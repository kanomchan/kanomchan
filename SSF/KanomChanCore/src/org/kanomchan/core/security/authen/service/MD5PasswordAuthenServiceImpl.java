package org.kanomchan.core.security.authen.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5PasswordAuthenServiceImpl implements PasswordAuthenService {

	@Override
	public String encrytPassword(String password) {
		if(password == null){
			return null;
		}
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
	        md.update(password.getBytes());
	 
	        byte byteData[] = md.digest();
	 
	        //convert the byte to hex format method 1
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < byteData.length; i++) {
	         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }
	 
	 
	        //convert the byte to hex format method 2
	        StringBuffer hexString = new StringBuffer();
	    	for (int i=0;i<byteData.length;i++) {
	    		String hex=Integer.toHexString(0xff & byteData[i]);
	   	     	if(hex.length()==1) hexString.append('0');
	   	     	hexString.append(hex);
	    	}
		return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
		}
		return null;
	}

}
