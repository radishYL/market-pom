package com.market.common.mq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * 鍙戦�佹秷鎭殑瀹㈡埛绔�
 * @author alex
 */
public class MessageClient {
	
	private static final Logger logger = Logger.getLogger(MessageClient.class);
	
	private MessageClient(){
		
	}
	
	private static class ClientHolder {
		private static final MessageClient client = new MessageClient();
	}
	
	public static MessageClient getInstance(){
		return MessageClient.ClientHolder.client;
	}
	
	/**
	 * 绾﹀畾--娑堟伅鍙戦�佷箣鍓嶆牴鎹甿sgId杩涜鍏ュ簱
	 * 		 鍙戦�佷箣鍚庢洿鏀规秷鎭姸鎬�
	 * @param message
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public MqResponse sendMsg(final MQMessage message){
		MsgSendResponse ret = new MsgSendResponse();
		try {
			JmsTemplate jms = JmsTemplateProvider.getJmsTemplate();
			jms.send(message.getData().getQueueName(),new MessageCreator() {
				@Override
				public Message createMessage(Session session) throws JMSException {
					return session.createObjectMessage(message.getData());
				}
			});
		} catch (Exception e) {
			ret.setCode(MsgSendResponse.Code.MSG_SEND_ERROR.getCode());
			ret.setMessage(MsgSendResponse.Code.MSG_SEND_ERROR.getMsg());
			logger.error("----", e);
		}
		return ret;
	}
	
	
}
