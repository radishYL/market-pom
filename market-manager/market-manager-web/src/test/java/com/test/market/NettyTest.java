package com.test.market;

import com.market.web.netty.WebsocketServer;

public class NettyTest {

	public static void main(String[] args) throws InterruptedException {
		WebsocketServer server = new WebsocketServer();
		server.setPort(8899);
		server.start();
	}
	
}
