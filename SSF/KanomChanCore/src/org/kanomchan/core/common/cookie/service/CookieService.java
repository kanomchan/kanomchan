package org.kanomchan.core.common.cookie.service;

import java.util.List;

import javax.servlet.http.Cookie;

import org.kanomchan.core.common.bean.UserBean;
import org.kanomchan.core.common.cookie.bean.CookieBean;
import org.kanomchan.core.common.cookie.bean.CookieOrm;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;
import org.kanomchan.core.common.processhandler.ServiceResult;

public interface CookieService {

	public ServiceResult<CookieBean> checkCookie(CookieOrm cookieBean)throws RollBackException,NonRollBackException;

	public ServiceResult<CookieOrm> genCookieByUserBean(UserBean userBean,CookieOrm cookieOrmOld)throws RollBackException,NonRollBackException;

	public ServiceResult<List<Cookie>> genCookieBeanToCookie(CookieOrm cookieOrmOld)throws RollBackException, NonRollBackException;
}
