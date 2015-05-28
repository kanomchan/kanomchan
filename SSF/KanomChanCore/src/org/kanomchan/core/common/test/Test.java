package org.kanomchan.core.common.test;

import java.beans.IntrospectionException;

import org.kanomchan.core.common.bean.UserBeanDefault;
import org.kanomchan.core.common.util.ClassUtil;

//import org.kanomchan.core.common.proxy.ProxyEntity;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Test {

	public static void main(String[] args) {
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
 
		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("jshowz@gmail.com","ymh3_#//");
				}
			});
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("jshowz@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("jarupong.wiboonchart@gmail.com"));
			message.setSubject("Testing Subject");
			message.setText("Dear Mail Crawler," +
					"\n\n No spam to my email, please!");
 
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
//		// TODO Auto-generated method stub
////		Set<String> key = new HashSet<String>();
////		
////		key.add("222");
////		key.add("223");
////		key.add("444");
////		key.add("555");
////		Set<String> codes = new HashSet<String>();
////		
////		codes.add("555");
////		System.out.println(key.containsAll(codes));
//		Calendar curTime = Calendar.getInstance();
//		
//		
//		curTime = Calendar.getInstance();
//		System.out.println(curTime.getTimeInMillis());
//		curTime = Calendar.getInstance();
//		System.out.println(curTime.getTimeInMillis());
//		curTime = Calendar.getInstance();
//		System.out.println(curTime.getTimeInMillis());
//		curTime = Calendar.getInstance();
//		System.out.println(curTime.getTimeInMillis());
//		curTime = Calendar.getInstance();
//		System.out.println(curTime.getTimeInMillis());
//		curTime = Calendar.getInstance();
//		System.out.println(curTime.getTimeInMillis());
//		
//		curTime = Calendar.getInstance();
//		System.out.println(curTime.getTime().getTime());
//		curTime = Calendar.getInstance();
//		System.out.println(curTime.getTime().getTime());
//		curTime = Calendar.getInstance();
//		System.out.println(curTime.getTime().getTime());
//		curTime = Calendar.getInstance();
//		System.out.println(curTime.getTime().getTime());
//		curTime = Calendar.getInstance();
//		System.out.println(curTime.getTime().getTime());
//		curTime = Calendar.getInstance();
//		System.out.println(curTime.getTime().getTime());
//		curTime = Calendar.getInstance();
//		System.out.println(curTime.getTime().getTime());
//
//		try {
//			LookupService lookupService = new LookupService("/GeoLiteCity.dat",LookupService.GEOIP_MEMORY_CACHE );
//			Location location = lookupService.getLocation("127.0.0.1");
//			 System.out.println(" countryCode: " + location.countryCode +
//                   "\n countryName: " + location.countryName +
//                   "\n region: " + location.region +
//                   "\n regionName: " + regionName.regionNameByCode(location.countryCode, location.region) +
//                   "\n city: " + location.city +
//                   "\n postalCode: " + location.postalCode +
//                   "\n latitude: " + location.latitude +
//                   "\n longitude: " + location.longitude +
////                   "\n distance: " + location.distance(l1) +
////                   "\n distance: " + l1.distance(location) +
//	       "\n metro code: " + location.metro_code +
//	       "\n area code: " + location.area_code +
//                   "\n timezone: " + timeZone.timeZoneByCountryAndRegion(location.countryCode, location.region));
//			Gson gson = new Gson();
//			Type typeOfSrc = new TypeToken<List<String>>(){}.getType();
//			List<String> list = new ArrayList<String>();
//			
//			list.add("ww");
//			list.add("ww");
//			String s = gson.toJson(list, typeOfSrc);
//			System.out.println(s);
		
//		System.out.println(ORDER_MODE.ASC.equals(ORDER_MODE.ASC));
//			List<String> extraParams = gson.fromJson(fieldValidatorBean.getParameter(), typeOfSrc);
			 
//			 
//			 System.out.println(gson.toJson(location));
//			 lookupService.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
//		ProxyEntity proxyBean = new ProxyEntity(TestBean.class);
//		Map<String, String[]> para = new HashMap<String, String[]>();
//		para.put("test", new String[]{"5555"});
//		proxyBean.setParamter(para);
//		System.out.println(proxyBean);
//		  check
//		ProxyEntity
		
//		User.class
		
//		try {
//			System.out.println(ClassUtil.findSetterName(UserBeanDefault.class, "getuserName".substring(3)));
//		} catch (NoSuchFieldException | NoSuchMethodException | IntrospectionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		;
		
		
//		String pattern = "(\\w)(\\s+)([\\.,])";
//		String test = "wefwefwef+-wefwefwef+wefwefwefwef wefwefwef+wefwefwef wefwef";
//		for (String string : test.split("-")) {
//			System.out.println(string); 
//		} 
		
		
//		List<String> listColumnName = new LinkedList<String>();
//		
//		
//		listColumnName.add(" 22222 ");
//		listColumnName.add(" 22222 ");
//		listColumnName.add(" 22222 ");
//		listColumnName.add(" 22222 ");
//		listColumnName.add(" 22222 ");
//		listColumnName.add(" 22222 ");
//		listColumnName.add(" 22222 ");
//		listColumnName.add(" 22222 ");
//		listColumnName.add(" 22222 ");
//		listColumnName.add(" 22222 ");
//		System.out.println(Joiner.on(" , ").skipNulls().join(listColumnName));
//		;
	}

}
