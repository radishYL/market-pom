package com.market.web.sse;

import org.apache.log4j.Logger;

import com.market.common.spring.utils.ContextUtils;

import redis.clients.jedis.JedisPubSub;

public class JedisListenner extends JedisPubSub {

	private static Logger log = Logger.getLogger(JedisListenner.class);
	
	@Override
	public void onMessage(String channel, String message) {
		try {
			SseTaskPool taskPool = ContextUtils.getContext().getBean(SseTaskPool.class);
			taskPool.send();
		} catch (Exception e) {
			log.error("--sse send message error--", e);
		}
	}
	
}
