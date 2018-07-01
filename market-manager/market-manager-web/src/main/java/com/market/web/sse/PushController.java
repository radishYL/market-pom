package com.market.web.sse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * 消息推送controller
 * @author Alex
 *
 */
@Controller
public class PushController {

	@RequestMapping("pushNews")
	@ResponseBody
	public SseEmitter pushNews() {
		SseEmitter sse = new SseEmitter();
		SseTaskPool.pool.add(sse);
		return sse;
	}
	
	
}
