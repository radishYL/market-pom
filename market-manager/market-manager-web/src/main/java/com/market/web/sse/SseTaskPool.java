package com.market.web.sse;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.market.common.jedis.client.JedisClient;


@Component("sseTaskPool")
public class SseTaskPool implements InitializingBean {

	public static List<SseEmitter> pool = new CopyOnWriteArrayList<>();
	
	@Override
	public void afterPropertiesSet() throws Exception {
		JedisClient.getJedis(1).subscribe(new JedisListenner(), "push-msg");
	}
	
	
	public void send() throws Exception {
		for (SseEmitter sseEmitter : pool) {
			// 此处为演示 真实应该是取数据
			String message = "send message!";
			try {
				sseEmitter.send(message);
				sseEmitter.complete();
			} catch (Exception e) {
				pool.remove(sseEmitter);
			}
			
		}
		
	}

}
