package com.market.web.netty;

import com.market.common.netty.TextWebSocketHandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * 安装channel
 * @author alex
 *
 */
public class WebsocketChannelInitializer extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline cp = ch.pipeline();
//		cp.addLast(new HttpServerCodec());
//		cp.addLast(new HttpObjectAggregator(1024*1024));
//		cp.addLast(new HttpRequestHandler());
		cp.addLast(new DelimiterBasedFrameDecoder(1024, Delimiters.lineDelimiter()));
		cp.addLast(new StringDecoder());
		cp.addLast(new StringEncoder());
//		cp.addLast(new WebSocketServerProtocolHandler("/ws"));
		cp.addLast(new TextWebSocketHandler());
	}

}
