package com.market.test;

import com.market.common.proxy.cglib.ProxyFactory;

public class CglibTarget implements ProxyFactory {

	public void say(String something) {
		System.out.println("say:" + something);
	}
	
	public void eat(String something) {
		System.out.println("eat:" + something);
	}
	
	@Override
	public void reuse() {
		System.err.println("释放资源");
	}
	
}
