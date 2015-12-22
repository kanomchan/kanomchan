package org.kanomchan.core.common.web.struts.conversion;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.XWorkException;
import com.opensymphony.xwork2.conversion.impl.DateConverter;
import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;
import com.opensymphony.xwork2.ognl.OgnlUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import ognl.OgnlContext;

import org.kanomchan.core.common.context.CurrentThread;
import org.kanomchan.core.common.processhandler.ProcessContext;

public class DateThaiConverter extends DateConverter {

    @Override
    public Object convertValue(Map<String, Object> context, Object target, Member member, String propertyName, Object value, Class toType) {
        Date result = null;
        
        if (value instanceof String && value != null && ((String) value).length() > 0) {
        	ProcessContext processContext = CurrentThread.getProcessContext();
        	Locale nativeLocale =null;
        	Locale localeStruts = getLocale(context);
        	try{
        		nativeLocale = (Locale) ActionContext.getContext().getValueStack().findValue("nativeLocale",Locale.class);
        		
        	}catch(Exception e){}
        	Locale locale = (nativeLocale != null ? nativeLocale : localeStruts!=null?localeStruts:Locale.ENGLISH);
            String sa = (String) value;
//            Locale locale = getLocale(context);
            DateFormat df = null;
            if (java.sql.Time.class == toType) {
                df = DateFormat.getTimeInstance(DateFormat.MEDIUM, locale);
            } else if (java.sql.Timestamp.class == toType) {
                Date check = null;
                SimpleDateFormat dtfmt = (SimpleDateFormat) DateFormat.getDateTimeInstance(DateFormat.SHORT,
                        DateFormat.MEDIUM,
                        locale);
                SimpleDateFormat fullfmt = new SimpleDateFormat(dtfmt.toPattern() + MILLISECOND_FORMAT,
                        locale);

                SimpleDateFormat dfmt = (SimpleDateFormat) DateFormat.getDateInstance(DateFormat.SHORT,
                        locale);

                SimpleDateFormat[] fmts = {fullfmt, dtfmt, dfmt};
                for (SimpleDateFormat fmt : fmts) {
                    try {
                        check = fmt.parse(sa);
                        df = fmt;
                        if (check != null) {
                            break;
                        }
                    } catch (ParseException ignore) {
                    }
                }
            } else if (java.util.Date.class == toType) {
                Date check;
                DateFormat[] dfs = getDateFormats(locale);
                for (DateFormat df1 : dfs) {
                    try {
                        check = df1.parse(sa);
                        df = df1;
                        if (check != null) {
                            break;
                        }
                    } catch (ParseException ignore) {
                    }
                }
            }
            //final fallback for dates without time
            if (df == null) {
                df = DateFormat.getDateInstance(DateFormat.SHORT, locale);
            }
            try {
                df.setLenient(false); // let's use strict parsing (XW-341)
                result = df.parse(sa);
                if (!(Date.class == toType)) {
                    try {
                        Constructor constructor = toType.getConstructor(new Class[]{long.class});
                        return constructor.newInstance(new Object[]{Long.valueOf(result.getTime())});
                    } catch (Exception e) {
                        throw new XWorkException("Couldn't create class " + toType + " using default (long) constructor", e);
                    }
                }
            } catch (ParseException e) {
                throw new XWorkException("Could not parse date", e);
            }
        } else if (Date.class.isAssignableFrom(value.getClass())) {
            result = (Date) value;
        }
        
        if (result == null) {
            if (value instanceof Object[]) {
                Object[] array = (Object[]) value;

                if (array.length >= 1) {
                    value = array[0];
                } else {
                    value = null;
                }

                // let's try to convert the first element only
                result = (Date) convertValue(context, target, member, propertyName, value, toType);
            } else if (!"".equals(value)) { // we've already tried the types we know
                result = (Date) convertValue(context, value, toType);
            }else if(java.util.Date.class == toType){
            	if("".equals(value)||(value!=null&&"".equals(((String)value).trim()))){
                	result = new Date(0);
                }
            }
            

            if (result == null && value != null && !"".equals(value)) {
                throw new XWorkException("Cannot create type " + toType + " from value " + value);
            }
        }
        
        return result;
    }

    private DateFormat[] getDateFormats(Locale locale) {
        DateFormat dt1 = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.LONG, locale);
        DateFormat dt2 = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM, locale);
        DateFormat dt3 = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, locale);
        DateFormat time = new SimpleDateFormat("HH:mm", locale);
        DateFormat d1 = DateFormat.getDateInstance(DateFormat.SHORT, locale);
        DateFormat d2 = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
        DateFormat d3 = DateFormat.getDateInstance(DateFormat.LONG, locale);
        DateFormat d4 = new SimpleDateFormat("dd/MMM/yyyy", locale);
        DateFormat rfc3399 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return new DateFormat[]{dt1, dt2, dt3, time, rfc3399, d1, d2, d3, d4};
    }

}
