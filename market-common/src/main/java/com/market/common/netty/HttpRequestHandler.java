package com.market.common.netty;

import org.apache.log4j.Logger;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * 处理http请求的handler
 * @author alex
 *
 */
public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest>{
	
	private static Logger log = Logger.getLogger(HttpRequestHandler.class);
	private static final String WS_URL = "ws";
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
		if(request.getUri().startsWith(WS_URL)) {
			/*
			 *  是websocket请求,将请求转到下一个handler
			 *  retain是必须的  因为当前handler在read消息之后会释放request资源
			 */
			ctx.fireChannelRead(request.retain());
		} else {
			/*
			 * 是http请求
			 */
			
			
		}
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		Channel incoming = ctx.channel();
		log.error("--client:"+incoming.remoteAddress()+" 连接异常--", cause);
		// 关闭
		ctx.close();
	}
	
}
