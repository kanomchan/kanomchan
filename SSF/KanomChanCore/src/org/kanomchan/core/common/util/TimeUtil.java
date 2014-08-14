package org.kanomchan.core.common.util;

import java.util.Date;

import org.kanomchan.core.common.context.ApplicationContextUtil;
import org.kanomchan.core.common.service.ConfigService;

public class TimeUtil {

	public static Date getTimeOut(int min){
		ConfigService configService = (ConfigService) ApplicationContextUtil.getBean("configService");
		Date curTime = new Date();
		final long ONE_MINUTE_IN_MILLIS = 60000;
		String timeOut = configService.get("TIME_OUT");
		Long timeout = Long.parseLong(timeOut);
		Long t = curTime.getTime();
		Date afterAddingTenMins = new Date(t + (timeout * ONE_MINUTE_IN_MILLIS));
		return afterAddingTenMins;
	}
}
