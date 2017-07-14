package org.kanomchan.core.common.cookie.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.Cookie;

import org.kanomchan.core.common.bean.UserBean;
import org.kanomchan.core.common.constant.CommonMessageCode;
import org.kanomchan.core.common.context.CurrentThread;
import org.kanomchan.core.common.cookie.bean.CookieBean;
import org.kanomchan.core.common.cookie.bean.CookieOrm;
import org.kanomchan.core.common.dao.JdbcCommonDao;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.NonRollBackProcessException;
import org.kanomchan.core.common.exception.RollBackException;
import org.kanomchan.core.common.io.LoginIO;
import org.kanomchan.core.common.processhandler.CookieFilter;
import org.kanomchan.core.common.processhandler.ProcessContext;
import org.kanomchan.core.common.processhandler.ServiceResult;
import org.kanomchan.core.common.service.ConfigService;
import org.kanomchan.core.security.authen.service.LoginService;
//import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("cookieService")
public class CookieServiceImpl implements CookieService {

	private CookieService self;
	
	@Autowired(required=false)
	@Qualifier("cookieService")
	public void setCookieService(CookieService self) {
		this.self = self;
	}
	@Autowired
	private JdbcCommonDao jdbcCommonDao;
	@Autowired
	private LoginService loginService;
	@Autowired
	private ConfigService configService;
	@Override
	public ServiceResult<CookieBean> checkCookie(CookieOrm cookieOrmOld) throws RollBackException,NonRollBackException {
		CookieBean cookieBean = new CookieBean();
		CookieOrm cookieOrmOut=null;
		if(cookieOrmOld==null||cookieOrmOld.getMachineId()==null){
//			case cookie null
			cookieOrmOut = new CookieOrm();
			UUID uuid = UUID.randomUUID();
			UUID uuidToken = UUID.randomUUID();
			try {
				cookieOrmOut.setMachineId(stringToHex(uuid.toString()));
				cookieOrmOut.setTokenId(stringToHex(uuidToken.toString()));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			cookieOrmOut.setActive(new Date());
			cookieOrmOut = jdbcCommonDao.save(cookieOrmOut);
//			cookieOrmOut.setTokenId(uuidToken.toString());
		}else{
			CookieOrm cookieOrmDB = jdbcCommonDao.get(cookieOrmOld.getMachineId(), CookieOrm.class);
			if(cookieOrmDB!=null&&cookieOrmOld.getTokenId()!=null &&cookieOrmOld.getTokenId().equals(cookieOrmDB.getTokenId())){
				ProcessContext processContext = CurrentThread.getProcessContext();
				if(processContext.getUserId()!=null){
					if(cookieOrmOld.getUserId()!=null&&cookieOrmOld.getKeyId()!=null){
						if(cookieOrmOld.getUserId().equals(processContext.getUserId()+"")){
							if(cookieOrmOld.getKeyId().equals(cookieOrmDB.getKeyId())){
//								cookieOrmOut = cookieOrmDB;
								cookieOrmDB.setActive(new Date());
								jdbcCommonDao.update(cookieOrmDB);
							}else{
//								keyId fail
								throw new NonRollBackProcessException(CommonMessageCode.KEY3999);
							}
						}else{
//							fail userId not equal
							throw new NonRollBackProcessException(CommonMessageCode.KEY3998);
						}
					}else{
//						fail Not Save cookie
						throw new NonRollBackProcessException(CommonMessageCode.KEY3997);
					}
				}else{
//					case not Login
					if(cookieOrmOld.getUserId()!=null&&cookieOrmOld.getKeyId()!=null){
						if(cookieOrmOld.getUserId().equals(cookieOrmDB.getUserId())&&cookieOrmOld.getKeyId().equals(cookieOrmDB.getKeyId())){
//								autoLogin
							
							ServiceResult<LoginIO> s = loginService.performLoginWithOutPasswordAndPutDataSession(Long.parseLong(cookieOrmOld.getUserId()), null);
							cookieBean.setLoginIO(s.getResult());
							cookieOrmOut = cookieOrmDB;
							cookieOrmDB.setActive(new Date());
							jdbcCommonDao.update(cookieOrmDB);
						}else{
//								keyId fail
							throw new NonRollBackProcessException(CommonMessageCode.KEY3996);
						}

					}else{
						cookieOrmDB.setUserId(null);
						cookieOrmDB.setKeyId(null);
						cookieOrmOut = cookieOrmDB;
						cookieOrmDB.setActive(new Date());
						jdbcCommonDao.update(cookieOrmDB);
					}
					
				}
			}else{
//				case TokenId Fail
//				gen New Key
				cookieOrmOut = new CookieOrm();
				UUID uuid = UUID.randomUUID();
				UUID uuidToken = UUID.randomUUID();
				try {
					cookieOrmOut.setMachineId(stringToHex(uuid.toString()));
					cookieOrmOut.setTokenId(stringToHex(uuidToken.toString()));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				cookieOrmOut.setActive(new Date());
				cookieOrmOut = jdbcCommonDao.save(cookieOrmOut);
//				cookieOrmOut.setTokenId(uuidToken.toString());
			}
		}
		cookieBean.setCookieOrm(cookieOrmOut);
		if(cookieOrmOut!=null){
			cookieBean.setCookies(self.genCookieBeanToCookie(cookieOrmOut).getResult());
		}
		return new ServiceResult<CookieBean>(cookieBean);
	}

	private String stringToHex(String uuid) throws UnsupportedEncodingException {
		byte[] bytes = uuid.getBytes("UTF-8");

	    StringBuilder hex = new StringBuilder(bytes.length* 2);
	    Formatter fmt = new Formatter(hex);

	    for (byte b : bytes)
	        fmt.format("%x", b);
		return hex.reverse().toString();
	}

	@Override
	public ServiceResult<CookieOrm> genCookieByUserBean(UserBean userBean,CookieOrm cookieOrmOld)throws RollBackException, NonRollBackException {
//		CookieBean cookieBean = new CookieBean();
		CookieOrm cookieOrmOut=null;
		if(cookieOrmOld==null||cookieOrmOld.getMachineId()==null){
//			case cookie null
			cookieOrmOut = new CookieOrm();
			UUID uuid = UUID.randomUUID();
			UUID uuidToken = UUID.randomUUID();
			UUID uuidKey = UUID.randomUUID();
			try {
				cookieOrmOut.setMachineId(stringToHex(uuid.toString()));
				cookieOrmOut.setTokenId(stringToHex(uuidToken.toString()));
				cookieOrmOut.setUserId(userBean.getUserId());
				cookieOrmOut.setKeyId(stringToHex(uuidKey.toString()));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			cookieOrmOut.setActive(new Date());
			cookieOrmOut = jdbcCommonDao.save(cookieOrmOut);
			cookieOrmOut.setTokenId(uuidToken.toString());
		}else{
			CookieOrm cookieOrmDB = jdbcCommonDao.get(cookieOrmOld.getMachineId(), CookieOrm.class);
			if(cookieOrmDB!=null&&cookieOrmOld.getTokenId()!=null &&cookieOrmOld.getTokenId().equals(cookieOrmDB.getTokenId())){
				try {
					UUID uuidKey = UUID.randomUUID();
					cookieOrmDB.setUserId(userBean.getUserId());
					cookieOrmDB.setKeyId(stringToHex(uuidKey.toString()));
					cookieOrmDB.setActive(new Date());
					jdbcCommonDao.update(cookieOrmDB);
					cookieOrmOut = cookieOrmDB;
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}else{
//				throw new NonRollBackProcessException(CommonMessageCode.KEY3988);
//				case TokenId Fail
//				gen New Key
				cookieOrmOut = new CookieOrm();
				UUID uuid = UUID.randomUUID();
				UUID uuidToken = UUID.randomUUID();
				try {
					cookieOrmOut.setMachineId(stringToHex(uuid.toString()));
					cookieOrmOut.setTokenId(stringToHex(uuidToken.toString()));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				cookieOrmOut.setActive(new Date());
				cookieOrmOut = jdbcCommonDao.save(cookieOrmOut);
				cookieOrmOut.setTokenId(uuidToken.toString());
			}
		}
		return new ServiceResult<CookieOrm>(cookieOrmOut);
	}
	
	@Override
	public ServiceResult<List<Cookie>> genCookieBeanToCookie(CookieOrm cookieOrmOld)throws RollBackException, NonRollBackException {
		List<Cookie> cookies = new ArrayList<Cookie>();
		String path = configService.get("SERVER_PATH");
		cookies.add(genCookie(CookieFilter.KEY_MID,cookieOrmOld.getMachineId(),path,CookieFilter.LIFE));
		cookies.add(genCookie(CookieFilter.KEY_TID,cookieOrmOld.getTokenId(),path,CookieFilter.LIFE));
		cookies.add(genCookie(CookieFilter.KEY_UID,cookieOrmOld.getUserId(),path,CookieFilter.LIFE));
		cookies.add(genCookie(CookieFilter.KEY_KID,cookieOrmOld.getKeyId(),path,CookieFilter.LIFE));
		return new ServiceResult<List<Cookie>>(cookies);
	}
	
	private Cookie genCookie(String key,String value,String path,int maxAge){
		if(value!=null){
			if("null".equalsIgnoreCase(value)){
				Cookie cookie=new Cookie(key,"");
				cookie.setMaxAge(0);
				cookie.setPath(path);
				return cookie;
			}else{
				Cookie cookie=new Cookie(key,value);
				cookie.setMaxAge(maxAge);
				cookie.setPath(path);
				return cookie;
			}
		}else{
			return null;
		}
		
	}

}
