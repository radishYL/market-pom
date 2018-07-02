package com.market.common.proxy.cglib;

import java.lang.reflect.Method;
import java.util.List;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import net.sf.cglib.proxy.NoOp;

public class ProxyTool<T extends ProxyFactory> {

	protected static final Object T = null;

	@SuppressWarnings({ "unchecked", "hiding" })
	public <T> T getProxy(Class<T> clazz,List<String> ignoreMethods) {
		Enhancer en = new Enhancer();
		
		en.setSuperclass(clazz);
		
		/**
		 * 方法代理
		 */
		en.setCallbacks(new Callback[]{new MethodInterceptor() {
			
			@Override
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				System.err.println("class:" + obj.getClass().getName() + ",method:" + proxy.getSuperName() + " begin");
				Object ret = null;
				try {
					ret = proxy.invokeSuper(obj, args);
					System.err.println("class:" + obj.getClass().getName() + ",method:" + proxy.getSuperName() + " end");
				} finally {
					ProxyFactory t = (ProxyFactory)obj;
					t.reuse();
				}
				return ret;
			}
		},NoOp.INSTANCE});
		
		/**
		 * 方法过滤
		 */
		en.setCallbackFilter(new CallbackFilter() {
			
			@Override
			public int accept(Method method) {
				if(ignoreMethods != null && ignoreMethods.contains(method.getName())) {
					return 1; // callback 0 to proxy
				}
				return 0; // callback 1 to proxy
			}
		});

		return (T)en.create();
	}
	
}
