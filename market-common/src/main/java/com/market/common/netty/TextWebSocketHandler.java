package com.market.common.netty;

import org.apache.log4j.Logger;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * 处理text消息类型的websocket处理器
 * @author alex
 *
 */
public class TextWebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame>{

	private static Logger log = Logger.getLogger(TextWebSocketHandler.class);
	
	private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
		Channel incoming = ctx.channel();
		log.info("--client:"+incoming.remoteAddress()+" 发送消息到服务端 msg:"+msg.text()+"--");
		channels.add(incoming);
		this.send();
	}
	
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		Channel incoming = ctx.channel();
		log.info("--client:"+incoming.remoteAddress()+" 连接服务端--");
		channels.add(incoming);
		this.send();
	}
	
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		Channel incoming = ctx.channel();
		log.info("--client:"+incoming.remoteAddress()+" 断开服务端--");
		channels.remove(incoming);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		Channel incoming = ctx.channel();
		log.error("--client:"+incoming.remoteAddress()+" 连接异常--",cause);
		ctx.close();
		channels.remove(incoming);
	}
	
	private void send() {
		for (Channel channel : channels) {
			log.info("--server send to:"+channel.remoteAddress()+" 消息--");
			channel.writeAndFlush("server response:welcome to netty server !");
			log.info("--server send to:"+channel.remoteAddress()+" 消息成功--");
		}
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		log.info("--client:"+ctx.channel().remoteAddress()+" 连接服务端成功--");
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		log.info("--client:"+ctx.channel().remoteAddress()+" 离开--");
	}
}
