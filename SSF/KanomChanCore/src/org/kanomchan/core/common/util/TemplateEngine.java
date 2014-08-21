package org.kanomchan.core.common.util;

import java.io.StringWriter;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.context.Context;
import org.kanomchan.core.common.constant.CommonConstant;



public class TemplateEngine {
	
	public TemplateEngine(Properties config){
		Properties p = new Properties();		
	    //p.setProperty("file.resource.loader.path", "/home/chayakon/Temp/mail");
	    p.setProperty("input.encoding", CommonConstant.TEMPLATE.INPUT_ENCODING);
	    p.setProperty("output.encoding", CommonConstant.TEMPLATE.OUTPUT_ENCODING);
	    p.putAll(config);	    
	    Velocity.init(p);
	}
	
	private Context convertToContext(Map<String,Object> values){
		Context context = new VelocityContext();
		for(Entry<String,Object> entry : values.entrySet()){
			context.put(entry.getKey() , entry.getValue());
		}
		return context;
	}
	
	public String render(String templateName , Map<String,Object> values){
		
		Context context = convertToContext(values);
		Template template = Velocity.getTemplate(templateName);
		StringWriter sw = new StringWriter();
        template.merge( context, sw );
		return sw.toString();
	}
	
	public String evaluate(String inString , Map<String,Object> values){
		Context context = convertToContext(values);
		StringWriter sw = new StringWriter();
		Velocity.evaluate(context, sw, "LOG" , inString);
		return sw.toString();
	}
}
