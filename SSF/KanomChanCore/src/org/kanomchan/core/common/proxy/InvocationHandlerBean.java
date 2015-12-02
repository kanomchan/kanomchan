package org.kanomchan.core.common.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class InvocationHandlerBean implements InvocationHandler {
//	private ProxyEntity proxyBean;
//	
//	
//	
//	public InvocationHandlerBean(ProxyEntity proxyBean) {
//		this.proxyBean = proxyBean;
//	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)throws Throwable {
		return method.invoke(proxy, args);
	}

}
