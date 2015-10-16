package org.kanomchan.core.common.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static Date getDiffDate(Date startDate, Date endDate){
		if(endDate.compareTo(startDate) != -1){
			long secs = (endDate.getTime() - startDate.getTime()) / 1000;
	        long mins = secs / 60;
	        long hours = mins / 60;
	        int days = (int) hours / 24;
	        int day = days % 365;
	        int month = days / 30;
	        if(month > 0)
	        	day = days % 30;
	        int years = days / 365;
	        if(years > 0)
	        	month = month % 12;
	        
	        Calendar calendar = Calendar.getInstance();
	        calendar.set(years, month, day);
			return calendar.getTime();
		}else{
			return null;
		}
	}
}
