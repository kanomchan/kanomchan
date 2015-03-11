package org.kanomchan.core.common.util;

import java.beans.IntrospectionException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.kanomchan.core.common.bean.EntityBean;

public class CalculatePercentUtil {

	public static Long cal(Object object){
		
		BigDecimal max = new BigDecimal(object.getClass().getDeclaredFields().length);
		BigDecimal count = new BigDecimal(0L);
		BigDecimal countSkip = new BigDecimal(0L);
		for (Field f : object.getClass().getDeclaredFields()) {
			try {
				if(object instanceof EntityBean){
					if(skipName.contains(f.getName())){
						countSkip = countSkip.add(new BigDecimal(1));
						continue;
					}
				}
				Method m = ClassUtil.findGetter(object.getClass(), f.getName());
				Object conobj = m.invoke(object);
				if(conobj!=null){
					count = count.add(new BigDecimal(1));
				}
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (IntrospectionException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		if(object instanceof EntityBean){
//			countSkip = countSkip.multiply(new BigDecimal(-1));
			max = max.subtract(countSkip);
		}
		
		
		BigDecimal percent = count.divide(max).multiply(new BigDecimal(100));
		
		return percent.longValue();
	}
	
	private static Set<String> skipName = new HashSet<String>(Arrays.asList("status","createDate","createUser","updateDate","updateUser")); 
}
