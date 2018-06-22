package com.market.web.socket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * 向客户端发消息的handler
 * @author alex
 *
 */
public class MsgPushHandler extends SimpleChannelInboundHandler<String>{

	private static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		// 收到String类型的消息
	}

	@Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		group.add(ctx.channel());
		this.send();
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        group.remove(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    		group.remove(ctx.channel());
    }
	
	private void send() {
		for (Channel channel : group) {
			channel.writeAndFlush("hello  welcome");
		}
	}
}
