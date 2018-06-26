package com.test.market;

import java.io.File;

import org.junit.Test;

import com.market.web.netty.WebsocketServer;

public class NettyTest {

	public static void main(String[] args) throws InterruptedException {
		WebsocketServer server = new WebsocketServer();
		server.setPort(8899);
		server.start();
	}
	
	
	
	@Test
	public void test1() {
		
		System.err.println(System.getProperty("user.dir"));
		System.err.println(File.separator);
		
		File f = new File("/Users/alex/Public/market-pom/market-manager/market-manager-web/pom.xml");
		
		System.err.println(f.getName());
		
	}
}
