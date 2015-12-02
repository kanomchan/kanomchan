package org.kanomchan.core.common.util;

import java.util.Calendar;

public class StringUtil {

	public static boolean isNull(String input) {
		return input==null||"".equals(input);
	}
	private static int seq =0;
	public static String genName(String name){
//		Calendar.getInstance().getTimeInMillis()
		return Calendar.getInstance().getTimeInMillis()+(++seq+"")+name;
	}
}
