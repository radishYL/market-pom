package com.market.web.socket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class MsgPushChannelInitial extends ChannelInitializer<Channel>{

	@Override
	protected void initChannel(Channel ch) throws Exception {
		// 解码器 最大单行长度1024 分割标识符为换行符
		ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, Delimiters.lineDelimiter()));
		ch.pipeline().addLast(new StringDecoder());
		ch.pipeline().addLast(new StringEncoder());
		ch.pipeline().addLast(new MsgPushHandler());
	}

}
