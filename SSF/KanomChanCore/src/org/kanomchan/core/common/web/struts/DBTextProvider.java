package org.kanomchan.core.common.web.struts;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.kanomchan.core.common.bean.Message;
import org.kanomchan.core.common.context.ApplicationContextUtil;
import org.kanomchan.core.common.service.ConfigService;
import org.kanomchan.core.common.service.LabelService;
import org.kanomchan.core.common.service.MessageService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.TextProvider;
import com.opensymphony.xwork2.util.LocalizedTextUtil;
import com.opensymphony.xwork2.util.ValueStack;

public class DBTextProvider implements TextProvider {
	
    private static final Map<MessageFormatKey, MessageFormat> messageFormats = new ConcurrentHashMap<MessageFormatKey, MessageFormat>();
//    private static final Map<String, Map<String,String>> lableCache  = new ConcurrentHashMap<String, Map<String,String>>();
//    private static final Map<String, Map<String,Message>> messageCache  = new ConcurrentHashMap<String, Map<String,Message>>();

    private static final Object[] EMPTY_ARGS = new Object[0];

    public boolean hasKey(String key) {
        return getText(key) != null;
    }

    public String getText(String key) {
        return findDBText(key, ActionContext.getContext().getLocale());
    }

    public String getText(String key, String defaultValue) {
        String text = getText(key);
        if (text == null) {
            return defaultValue;
        }
        return text;
    }

    public String getText(String key, List<?> args) {
        Object[] params;
        if (args != null) {
            params = args.toArray();
        } else {
            params = EMPTY_ARGS;
        }

        return findDBText(key, ActionContext.getContext().getLocale(), params);
    }

    public String getText(String key, String[] args) {
        Object[] params;
        if (args != null) {
            params = args;
        } else {
            params = EMPTY_ARGS;
        }

        return findDBText(key, ActionContext.getContext().getLocale(), params);
    }

    public String getText(String key, String defaultValue, List<?> args) {
        String text = getText(key, args);
        if(text == null && defaultValue == null) {
            defaultValue = key;
        }
        if (text == null && defaultValue != null) {

            MessageFormat format = new MessageFormat(defaultValue);
            format.setLocale(ActionContext.getContext().getLocale());
            format.applyPattern(defaultValue);

            Object[] params;
            if (args != null) {
                params = args.toArray();
            } else {
                params = EMPTY_ARGS;
            }

            return format.format(params);
        }
        return text;        
    }

    public String getText(String key, String defaultValue, String[] args) {
        String text = getText(key, args);
        if (text == null) {
            MessageFormat format = new MessageFormat(defaultValue);
            format.setLocale(ActionContext.getContext().getLocale());
            format.applyPattern(defaultValue);

            if (args == null) {
                return format.format(EMPTY_ARGS);
            }

            return format.format(args);
        }
        return text;
    }


    public String getText(String key, String defaultValue, String obj) {
        List<Object> args = new ArrayList<Object>(1);
        args.add(obj);
        return getText(key, defaultValue, args);
    }

    public String getText(String key, String defaultValue, List<?> args, ValueStack stack) {
        //we're not using the value stack here
        return getText(key, defaultValue, args);
    }

    public String getText(String key, String defaultValue, String[] args, ValueStack stack) {
        //we're not using the value stack here
        List<Object> values = new ArrayList<Object>(Arrays.asList(args));
        return getText(key, defaultValue, values);
    }

    public ResourceBundle getTexts(String bundleName) {
        return LocalizedTextUtil.findResourceBundle(bundleName, ActionContext.getContext().getLocale());
    }

    public ResourceBundle getTexts() {
        return null;
    }
    private static String findDBText(  String aTextName ,Locale locale, Object[] args) {
    	
    	String defaultText = findDBText(aTextName, locale);
        if (defaultText != null) {
            MessageFormat mf = buildMessageFormat(defaultText, locale);
            return formatWithNullDetection(mf, args);
        }
        return null;
    }
    private static String findDBText(  String aTextName ,Locale locale) {
    	
//    	if(messageCache.containsKey(locale.getISO3Language().toUpperCase())){
//    		
//    	}else{
//    		MessageService messageService= ApplicationContextUtil.getBean(MessageService.class);
//    		
//    		messageCache.put(locale.getISO3Language().toUpperCase(), messageService.getMessageMap(locale.getISO3Language().toUpperCase()));
//    	}
    	
    	
    	ConfigService configService= ApplicationContextUtil.getBean(ConfigService.class);
    	MessageService messageService= ApplicationContextUtil.getBean(MessageService.class);
    	Map<String, Message> message = messageService.getMessageMap(locale.getISO3Language().toUpperCase());//  messageCache.get(locale.getISO3Language().toUpperCase());
        if (message == null) {
            return null;
        }
        String out = null;
        if(message.get(aTextName)!=null)
        out = message.get(aTextName).getDisplayText();
        if(out!=null){
        	StringBuilder sb = new StringBuilder();
            if("true".equals(configService.get("TRANSLATE_LABEL"))){
            	sb.append("<span>");
            	sb.append(out);
            	sb.append("</span>");
            	sb.append("<translateLabel value=\"");
            	sb.append(aTextName);
            	sb.append("\"></translateLabel>");
            }else{
            	sb.append(out);
            }
        	return sb.toString();
        }
        
    	
//    	if(lableCache.containsKey(locale.getISO3Language().toUpperCase())){
//    		
//    	}else{
//    		LabelService labelService= ApplicationContextUtil.getBean(LabelService.class);
//    		lableCache.put(locale.getISO3Language().toUpperCase(), labelService.getLabel(locale.getISO3Language().toUpperCase()));
//    	}
    	LabelService labelService= ApplicationContextUtil.getBean(LabelService.class);
    	Map<String, String> lable = labelService.getLabel(locale.getISO3Language().toUpperCase());// lableCache.get(locale.getISO3Language().toUpperCase());
        if (lable == null) {
            return null;
        }
        
        StringBuilder sb = new StringBuilder();
        if("true".equals(configService.get("TRANSLATE_LABEL"))){
        	sb.append("<span>");
        	sb.append(lable.get(aTextName));
        	sb.append("</span>");
        	sb.append("<translateLabel value=\"");
        	sb.append(aTextName);
        	sb.append("\"></translateLabel>");
        }else{
        	sb.append(lable.get(aTextName));
        }
    	return sb.toString();
    }
    
    private static String formatWithNullDetection(MessageFormat mf, Object[] args) {
    	LinkedList<Object> argList = new LinkedList<Object>(Arrays.asList(args));
//    	argList.addFirst("");
        String message = mf.format(argList.toArray());
        if ("null".equals(message)) {
            return null;
        } else {
            return message;
        }
    }

    private static MessageFormat buildMessageFormat(String pattern, Locale locale) {
        MessageFormatKey key = new MessageFormatKey(pattern, locale);
        MessageFormat format = messageFormats.get(key);
        if (format == null) {
            format = new MessageFormat(pattern);
            format.setLocale(locale);
            format.applyPattern(pattern);
            messageFormats.put(key, format);
        }

        return format;
    }
    
    static class MessageFormatKey {
        String pattern;
        Locale locale;

        MessageFormatKey(String pattern, Locale locale) {
            this.pattern = pattern;
            this.locale = locale;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof MessageFormatKey)) return false;

            final MessageFormatKey messageFormatKey = (MessageFormatKey) o;

            if (locale != null ? !locale.equals(messageFormatKey.locale) : messageFormatKey.locale != null)
                return false;
            if (pattern != null ? !pattern.equals(messageFormatKey.pattern) : messageFormatKey.pattern != null)
                return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result;
            result = (pattern != null ? pattern.hashCode() : 0);
            result = 29 * result + (locale != null ? locale.hashCode() : 0);
            return result;
        }
    }
}
