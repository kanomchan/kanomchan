
package org.kanomchan.core.common.web.struts.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class RemoveListInterceptor extends AbstractInterceptor {
    private static final long serialVersionUID = 1L;


    public String intercept(ActionInvocation actionInvocation) throws Exception {
        Map<String, Object> parameters = actionInvocation.getInvocationContext().getParameters();
        Map<String, Object> newParams = new HashMap<String, Object>();
        Set<String> keys = parameters.keySet();

        for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
            String key = iterator.next();

             if (key.startsWith("__pushdataonremove_")) {
                String name = key.substring("__pushdataonremove_".length());

                Object value = parameters.get(key);
                iterator.remove();

                // is this multi-select box submitted?
                if (!parameters.containsKey(name)) {

                    // if not, let's be sure to default the value to an empty string array
                    newParams.put(name, value);
                }
             }else if(key.startsWith("__pushnulllongonremove_")){
             	String name = key.substring("__pushnulllongonremove_".length());

                 iterator.remove();

                 // is this multi-select box submitted?
                 if (!parameters.containsKey(name)) {

                     // if not, let's be sure to default the value to an empty string array
                     newParams.put(name, new Long[0]);
                 }
            }else if(key.startsWith("__pushnullonremove_")){
            	String name = key.substring("__pushnullonremove_".length());

                iterator.remove();

                // is this multi-select box submitted?
                if (!parameters.containsKey(name)) {

                    // if not, let's be sure to default the value to an empty string array
                    newParams.put(name, new String[0]);
                }
            }
            else if(key.startsWith("__pushotheronremove_")){
            	String name = key.substring("__pushotheronremove_".length());
            	Object value = parameters.get(key);
                iterator.remove();
                if (parameters.containsKey(name)) {
                	if(parameters.get(name).getClass().isArray()){
                    	Object[] values = (Object[]) parameters.get(name);
                    	if("-2".equals(values[0])){
                        	newParams.put(name, value);
                        }
                    }else{
                    	if("-2".equals(parameters.get(name))){
                        	newParams.put(name, value);
                        }
                    }
                }
                
                
//                parameters.get(name);
                // is this multi-select box submitted?
//                if (!parameters.containsKey(name)) {
//
//                    // if not, let's be sure to default the value to an empty string array
//                    newParams.put(name, new String[0]);
//                }
            }
        }

        parameters.putAll(newParams);

        return actionInvocation.invoke();
    }

}