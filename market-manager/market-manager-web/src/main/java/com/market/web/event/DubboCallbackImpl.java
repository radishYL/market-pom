package com.market.web.event;

import org.springframework.stereotype.Service;

import com.market.common.entity.UserBasicBean;
import com.market.service.callback.DubboCallback;

@Service("dubboCallback")
public class DubboCallbackImpl implements DubboCallback {
	
	private static final long serialVersionUID = 1L;

	@Override
	public void onReturn(UserBasicBean user) {
		System.out.println(user.getId()+" 查询完毕");
		try {
			Thread.sleep(50000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onThrow(long userId, Throwable t) {
		System.err.println(userId+" 查询失败:"+t);
	}

}
