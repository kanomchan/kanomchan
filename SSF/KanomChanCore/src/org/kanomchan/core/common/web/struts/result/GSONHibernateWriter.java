package org.kanomchan.core.common.web.struts.result;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opensymphony.xwork2.inject.Inject;

import org.apache.struts2.StrutsConstants;;

public class GSONHibernateWriter implements DynamicWriter {
	private String defaultEncoding = "ISO-8859-1";
	@Inject(StrutsConstants.STRUTS_I18N_ENCODING)
	public void setDefaultEncoding(String val) {
		this.defaultEncoding = val;
	}
		
//	@Override
//	public String writer(Object object, Collection<Pattern> excludeProperties, Collection<Pattern> includeProperties) {
//		GsonBuilder gsonBuilder = new GsonBuilder();
////		gsonBuilder.
////		object.getClass()
////		Type typeOfSrc = new TypeToken<Map<String, Object>>(){}.getType();
//		Gson gson = gsonBuilder.create();
//		return gson.toJson(object);
//	}

	@Override
	public void writeToResponse(HttpServletResponse response,Object object, Collection<Pattern> excludeProperties, Collection<Pattern> includeProperties) {
		// TODO Auto-generated method stub
		GsonBuilder gsonBuilder = new GsonBuilder();
//	gsonBuilder.
//	object.getClass()
//	Type typeOfSrc = new TypeToken<Map<String, Object>>(){}.getType();
	Gson gson = gsonBuilder.create();
	String json = gson.toJson(object);
	
	 try {
		response.setContentLength(json.getBytes(defaultEncoding).length);
		PrintWriter out = response.getWriter();
		out.print(json);
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	}catch (IOException e) {
		e.printStackTrace();
	}

	}

}
