package com.market.common.netty;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
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
			.option(ChannelOption.SO_BACKLOG, 128)
			.option(ChannelOption.SO_KEEPALIVE, true)
			.childHandler(new WebsocketChannelInitializer());
			
			ChannelFuture future = sb.bind(8899).sync();
			
			// 异步等待服务器关闭 此处不会关闭
			future.channel().closeFuture().sync();
			
		} finally {
			// 优雅关闭服务端
			boss.shutdownGracefully();
			worker.shutdownGracefully();
		}
		
	}
	
}
