package org.kanomchan.core.common.util;

import java.beans.IntrospectionException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.kanomchan.core.common.bean.EntityBean;

public class CalculatePercentUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5641774522934759653L;

	public static Long calList(Object object){
		List<Object> objects = new ArrayList<Object>();
		if(object != null)
			objects = (ArrayList)object;
		
		if(objects.size() == 0)
			return 0L;
		BigDecimal percent = null;
		BigDecimal countAll = new BigDecimal(0L);
		BigDecimal count = new BigDecimal(0L);
		BigDecimal countSkip = new BigDecimal(0L);
		BigDecimal max = new BigDecimal(0L);
		BigDecimal maxAll = new BigDecimal(0L);
		for (Object obj : objects) {
			if(obj == null)
				return 0L;
			max = new BigDecimal(obj.getClass().getDeclaredFields().length);
			count = new BigDecimal(0L);
			countSkip = new BigDecimal(0L);
			for (Field f : obj.getClass().getDeclaredFields()) {
				try {
					if(obj instanceof EntityBean){
						if(skipName.contains(f.getName())){
							countSkip = countSkip.add(new BigDecimal(1));
							continue;
						}
					}
					Method m = ClassUtil.findGetter(obj.getClass(), f.getName());
					Object conobj = m.invoke(obj);
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
//				countSkip = countSkip.multiply(new BigDecimal(-1));
				max = max.subtract(countSkip);
			}
			
			countAll = countAll.add(count);
			maxAll = maxAll.add(max);
		}
		percent = countAll.multiply(new BigDecimal(100)).divide(maxAll,2,RoundingMode.DOWN);
		return percent.longValue();
	}
	
	public static Long cal(Object object){
		if(object instanceof List)
			return calList(object);
		if(object == null)
			return 0L;
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
		BigDecimal percent = count.multiply(new BigDecimal(100)).divide(max,2,RoundingMode.DOWN);
		return percent.longValue();
	}
	
	public static Long calMultiPercent(long... percent){
		long len = percent.length;
		long max = 0l;
		for (long lg : percent) {
			max += lg;
		}
		return max / len;
	}
	
	private static Set<String> skipName = new HashSet<String>(Arrays.asList("status","createDate","createUser","updateDate","updateUser")); 
}
