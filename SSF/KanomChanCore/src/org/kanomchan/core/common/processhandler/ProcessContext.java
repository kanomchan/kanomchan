package org.kanomchan.core.common.processhandler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.kanomchan.core.common.bean.LocationBean;
import org.kanomchan.core.common.bean.Message;
import org.kanomchan.core.common.bean.MessageDefault;
import org.kanomchan.core.common.bean.UserBean;
import org.kanomchan.core.common.bean.UserBeanDefault;
import org.kanomchan.core.common.constant.MessageCode;
import org.springframework.transaction.TransactionStatus;

public class ProcessContext {
	
	protected String transId;

	protected TransactionStatus txnStatus;

	protected UserBean userBean;
	
	protected List<Message> messageList;
	
	protected String lang;
	protected Locale nativeLocale;
	protected Locale nativeLocaleText;
	
	protected boolean startProcess = false;
	protected String sessionId;

	protected String status;

	protected Locale locale;
	
	protected Map<String, String> contextMapString = new HashMap<String, String>();
	
	protected Long zone;
	protected Long country;
	protected Long region;
	protected Long province;
	protected Long city;
	protected Long county;
	protected Long postal;
	protected Long language;
	protected Long station;
	protected Long currency;
	
	protected ProcessContext() {
		// TODO Auto-generated constructor stub
	}
//	protected ProcessContext(Long zone,Long country,Long region,Long province,Long city,Long county,Long postal,Long station,Long currency) {
//		
//		this.zone = zone;
//		this.country = country;
//		this.region = region;
//		this.province = province;
//		this.city = city;
//		this.county = county;
//		this.postal = postal;
//		this.station = station;
//		this.currency = currency;
//	}
	protected void setLocationBean(LocationBean locationBean) {
		this.lang = locationBean.getLang();
		this.zone = locationBean.getZone();
		this.country = locationBean.getCountry();
		this.region = locationBean.getRegion();
		this.province = locationBean.getProvince();
		this.city = locationBean.getCity();
		this.county = locationBean.getCounty();
		this.postal = locationBean.getPostal();
		this.station = locationBean.getStation();
		this.language = locationBean.getLanguage();
		this.currency = locationBean.getCurrency();
	}
	protected void setLocation(String lang,Long zone,Long country,Long region,Long province,Long city,Long county,Long postal,Long station,Long currency) {
		this.lang = lang;
		this.zone = zone;
		this.country = country;
		this.region = region;
		this.province = province;
		this.city = city;
		this.county = county;
		this.postal = postal;
		this.station = station;
		this.currency = currency;
	}
	
	
	
	public UserBean getUserBean() {
		if(userBean==null){
//			userMenuService = ApplicationContextUtil.getBean("userMenuService");
			userBean= new UserBeanDefault();
			userBean.setRole("GUEST");
			HashSet<String> pri = new HashSet<String>();
			pri.add("COM000000");
			userBean.setPrivileges(pri);
//			ServiceResult<List<MenuBean>> serviceResultMenu = userMenuService.generateMenuList(serviceResult.getResult());
		}
		return userBean;
	}
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	public TransactionStatus getTxnStatus() {
		return txnStatus;
	}

	public void setTxnStatus(TransactionStatus txnStatus) {
		this.txnStatus = txnStatus;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public void addMessage(MessageCode messageCode){
		if(messageList == null){
			messageList = new LinkedList<Message>();
		}
		MessageDefault message  = new MessageDefault();
		message.setMessageCode(messageCode);
		messageList.add(message );
	}
	
	public void addMessage(String messageCode,String...param){
		if(messageList == null){
			messageList = new LinkedList<Message>();
		}
		MessageDefault message  = new MessageDefault();
		message.setMessageCode(messageCode);
		message.setPara(param);
		messageList.add(message );
	}
	public void addMessage(MessageCode messageCode,String...param){
		if(messageList == null){
			messageList = new LinkedList<Message>();
		}
		MessageDefault message  = new MessageDefault();
		message.setMessageCode(messageCode);
		message.setPara(param);
		messageList.add(message );
	}
	List<Message> getMessageList() {
		return messageList;
	}
	public void setLocale(Locale locale) {
		this.locale =locale;
	}
	public Locale getLocale() {
		return locale;
	}
	
	
	public String getSessionId() {
		return sessionId;
	}
	
//	public Locale getNativeLang(){
//		return (contextMapString.get("SESSION_NATIVE_LANG_KEY");
//	}
	
	
	
	public String getLang() {
		return lang;
	}
	public Locale getNativeLocale() {
		return nativeLocale;
	}
	public void setNativeLocale(Locale nativeLocale) {
		this.nativeLocale = nativeLocale;
	}
	
	public Locale getNativeLocaleText() {
		return nativeLocaleText==null?locale:nativeLocaleText;
	}
	public void setNativeLocaleText(Locale nativeLocale) {
		this.nativeLocaleText = nativeLocale;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	public String getUserName(){
		if(userBean==null||userBean.getUserName()==null||"".equals(userBean.getUserName()))
			return "GUEST";
		return userBean.getUserName();
	}
	
	public Long getZone() {
		return zone;
	}
	public Long getCountry() {
		return country;
	}
	public Long getRegion() {
		return region;
	}
	public Long getProvince() {
		return province;
	}
	public Long getCity() {
		return city;
	}
	public Long getCounty() {
		return county;
	}
	public Long getPostal() {
		return postal;
	}
	public Long getStation() {
		return station;
	}
	public Long getCurrency() {
		return currency;
	}
	public Long getUserId() {

		Long l = null ;
		
		if(userBean ==null)
			return null;
		if(userBean.getUserId() ==null ||"".equals(userBean.getUserId()))
			return null;
		try{
			l = Long.parseLong(userBean.getUserId());
		}catch(Exception e){
			
		}
		return l;
	}
	public String getString(String key) {
		return contextMapString.get(key);
	}
	public void setString(String key,String value) {
		contextMapString.put(key, value);
	}
	
	void clearStage(){
		startProcess = false;
		status=null;
		messageList = null;
	}
	public String getNativeLocaleStr() {
		return getNativeLocale().getISO3Language().toUpperCase();
	}
	
}
