package com.market.web.netty;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * websocket服务端
 * @author alex
 */
@Component
public class WebsocketServer {

	private int port;

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	@PostConstruct
	public void start() throws InterruptedException {
		// 接受连接的线程池
		EventLoopGroup boss = new NioEventLoopGroup();
		// 处理连接的线程池
		EventLoopGroup worker = new NioEventLoopGroup();
		
		try {
			ServerBootstrap sb = new ServerBootstrap();
			sb.group(boss, worker)
			.channel(NioServerSocketChannel.class)
			.childHandler(new WebsocketChannelInitializer())
			.option(ChannelOption.SO_BACKLOG, 128)
			.option(ChannelOption.SO_KEEPALIVE, true);
			
			sb.bind(8899).sync();
//			f.channel().closeFuture().sync();
		} finally {
			// 优雅关闭服务端
		}
		
	}
	
}
