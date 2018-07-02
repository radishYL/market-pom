package com.market.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.market.common.proxy.cglib.ProxyTool;

public class ProxyCglibTest {

	public static void main(String[] args) {
		
		List<String> methods = new ArrayList<>();
		methods.add("say");
		methods.add("reuse");
		
		ProxyTool<CglibTarget> p = new ProxyTool<>();
		
		CglibTarget proxy = p.getProxy(CglibTarget.class, methods);
		
		proxy.eat("水果");
		proxy.say("黄梅戏");
		
	}
	
	@Test
	public void test() {
		
		String s = "{name:\"miss\",sex:\"女\"}";
		
		User user = JSON.parseObject(s, User.class);
		
		System.err.println(user);
	}
	
}
