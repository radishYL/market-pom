package com.market.common.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * 安装channel
 * @author alex
 *
 */
public class WebsocketChannelInitializer extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline cp = ch.pipeline();
		cp.addLast(new HttpServerCodec());
		cp.addLast(new HttpObjectAggregator(1024*1024));
		cp.addLast(new HttpRequestHandler());
//		cp.addLast(new WebSocketServerProtocolHandler("/ws"));
		cp.addLast(new TextWebSocketHandler());
	}

}
