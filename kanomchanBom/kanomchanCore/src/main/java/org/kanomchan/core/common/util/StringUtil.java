package org.kanomchan.core.common.util;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	public static boolean isNull(String input) {
		return input==null||"".equals(input);
	}
	private static int seq =0;
	public static String genName(String name){
//		Calendar.getInstance().getTimeInMillis()
		return Calendar.getInstance().getTimeInMillis()+(++seq+"")+name;
	}
    private static final String REGEX = "\\?v[0-9]+v$";
	
	public static String genStringVersion(String imagePathOriginal){

		Pattern p = Pattern.compile(REGEX);
		String imagePathReal = null;
		int count = 0;
		Matcher m = p.matcher(imagePathOriginal); // get a matcher object
		if(m.find()){
			imagePathReal = imagePathOriginal.substring(0, m.start());
			String strcount = imagePathOriginal.substring(m.start()+2,m.end()-1);
			try{
				count = Integer.parseInt(strcount);
			}catch (Exception ex){
				count = 0;
			}
		}else{
			imagePathReal = imagePathOriginal;
		}
		count++;
		StringBuilder sb = new StringBuilder();
		sb.append(imagePathReal);
		sb.append("?v");
		sb.append(count);
		sb.append("v");
		return sb.toString(); 
	}
	
	public static String removeStringVersion(String imagePathOriginal){
		if (imagePathOriginal==null) {
			return null;
		}
		Pattern p = Pattern.compile(REGEX);
		String imagePathReal = null;
		Matcher m = p.matcher(imagePathOriginal); // get a matcher object
		if(m.find()){
			imagePathReal = imagePathOriginal.substring(0, m.start());
		}else{
			imagePathReal = imagePathOriginal;
		}
		return imagePathReal; 
	}
	
	public static String removeStringPath(String imagePathOriginal){
		if (imagePathOriginal==null) {
			return null;
		}
		String imagePathReal = null;
		if(imagePathOriginal!=null&&imagePathOriginal.lastIndexOf("/")>0){
			imagePathReal = imagePathOriginal.substring(imagePathOriginal.lastIndexOf("/"), imagePathOriginal.length());
		}else{
			imagePathReal = imagePathOriginal;
		}
		return imagePathReal; 
	}
}
