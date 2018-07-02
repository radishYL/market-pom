package com.market.service.test;

import java.text.MessageFormat;

import javax.jms.Message;
import javax.jms.ObjectMessage;

import org.junit.Test;
import org.springframework.jms.core.JmsTemplate;

import com.market.common.mq.JmsTemplateProvider;
import com.market.common.mq.MQMessage;
import com.market.common.mq.MessageBody;
import com.market.common.mq.QueueContainner;
import com.market.service.impl.message.MessageServiceImpl;

public class MyTest {

	public static void main(String[] args) throws Exception {
		
		MessageServiceImpl service = new MessageServiceImpl();
		
//		service.init();
		
		MessageBody data = new MessageBody(001L, QueueContainner.USER_REGISTER_QUEUE);
		
		MQMessage<MessageBody> msg = new MQMessage<MessageBody>(data);
		
		service.send(msg);
		
		JmsTemplate jmsTemplate = JmsTemplateProvider.getJmsTemplate();
		Message receive = jmsTemplate.receive(QueueContainner.USER_REGISTER_QUEUE);
		ObjectMessage omsg = (ObjectMessage)receive;
		MessageBody body = (MessageBody)(omsg.getObject());
		System.err.println(body.getQueueName());
		
		Thread.sleep(10000);
		
	}
	
	@Test
	public void test02() {
		String parttern = "update from '%s','%s'";
		
		System.err.println(String.format(parttern, "twu1293qorw","4651872"));
		
		
		String parttern2 = "update from {0},{1}";
		
		String s1 = "yl",s2 = "ly";
		
		System.err.println(MessageFormat.format(parttern2,"'" + s1 + "'",s2));
		
	}
}
