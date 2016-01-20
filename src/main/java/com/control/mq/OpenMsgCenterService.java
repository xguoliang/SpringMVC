package com.control.mq;

/**
 * 单独的rabbitMQ的服务，通过开启一个线程来侦听mq消息
 */

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by rui-zhan 20141101
 */

@Component
public class OpenMsgCenterService {
	private static final Logger logger = LoggerFactory
			.getLogger(OpenMsgCenterService.class);
	private static final String EXCHANGE_NAME = "SENDTO_OPEN_ADMIN_EXCHANGE_NAME";
	private static final String ROUTING_KEY = "SENDTO_OPEN_ADMIN_ROUTING_KEY";

	@Value(value = "${rabbitmq.ip}")
	private String rabbitmqHost = null;
	@Value(value = "${rabbitmq.username}")
	private String rabbitmqUsername = null;
	@Value(value = "${rabbitmq.password}")
	private String rabbitmqPassword = null;

	@Autowired
	 AssistantPubService assistantPubService;

	private OpenMsgCenterServiceListener listener;
	
//	@PostConstruct是Java EE 5引入的注解，Spring允许开发者在受管Bean中使用它。当DI容器实例化当前受管Bean时，@PostConstruct注解的方法会被自动触发，从而完成一些初始化工作
	@PostConstruct
	private void init() throws Exception {
		if (StringUtils.isEmpty(rabbitmqHost))			return;
		logger.info("OpenMsgCenterService {}:{}@{}", rabbitmqUsername,
				rabbitmqPassword, rabbitmqHost);
		listener = new OpenMsgCenterServiceListener(rabbitmqHost,
				rabbitmqUsername, rabbitmqPassword,assistantPubService);
		Thread thread = new Thread(listener, "OpenMsgCenterService");
		thread.start();
	}

	@PreDestroy
	private void stop() {
		if (listener == null)
			return;
		listener.destroy();
		logger.info("OpenMsgCenterService stopped");
	}

	private static class OpenMsgCenterServiceListener implements Runnable {
		private String rabbitmqHost;
		private String rabbitmqUsername;
		private String rabbitmqPassword;
		AssistantPubService assPubService;

		private AtomicBoolean isStop = new AtomicBoolean(false);

		private OpenMsgCenterServiceListener(String rabbitmqHost,
				String rabbitmqUsername, String rabbitmqPassword,AssistantPubService pubService) {
			this.rabbitmqHost = rabbitmqHost;
			this.rabbitmqUsername = rabbitmqUsername;
			this.rabbitmqPassword = rabbitmqPassword;
			this.assPubService=pubService;
		}

		@Override
		public void run() {
			com.rabbitmq.client.ConnectionFactory factory = new com.rabbitmq.client.ConnectionFactory();
			factory.setHost(rabbitmqHost);
			factory.setUsername(rabbitmqUsername);
			factory.setPassword(rabbitmqPassword);
			factory.setConnectionTimeout(10000);
			while (!isStop.get()) {
				com.rabbitmq.client.Connection connection = getConnection(factory);
				try {
					if (connection == null)
						continue;
					com.rabbitmq.client.QueueingConsumer openConsumer = createOpenMsgConsumer(connection);
					logger.info("创建MQ对象openConsumer成功！");
					while (!isStop.get()) {
						handleOpenMsg(openConsumer.nextDelivery(2000));
					}
				} catch (Throwable ignore) {
					logger.error("", ignore);
				} finally {
					try {
						if (connection.isOpen())
							connection.close();
					} catch (Throwable ignore) {
					}
				}
			}
		}

		private com.rabbitmq.client.QueueingConsumer createOpenMsgConsumer(
				com.rabbitmq.client.Connection connection) throws Exception {
			com.rabbitmq.client.Channel channel = connection.createChannel();
			channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
			String queueName = channel.queueDeclare().getQueue();
			channel.queueBind(queueName, EXCHANGE_NAME, ROUTING_KEY);
			com.rabbitmq.client.QueueingConsumer consumer = new com.rabbitmq.client.QueueingConsumer(
					channel);
			channel.basicConsume(queueName, true, consumer);
			return consumer;
		}

		private void handleOpenMsg(
				com.rabbitmq.client.QueueingConsumer.Delivery delivery) {
			if (delivery == null)
				return;

			try {
				String message = new String(delivery.getBody());
				logger.info("{}", message);
				JSONObject jsonObject = JSONObject.fromObject(message);
				
				String type = jsonObject.getString("type");

				logger.info("{}", type);
				if (StringUtils.equals("ADMIN_CHANGE_NOTICE", type)) {
					String value = jsonObject.getString("value");
					String eid = jsonObject.getString("eid");
					String oid = jsonObject.getString("oid");
					logger.info("获取参数值：" + "eid:" + eid + ",oid：" + oid
							+ ",value:" + value);
					if (StringUtils.equals("COMMISSION", value)) {// 设置是否是管理员
						assPubService.excute();
					} else if (StringUtils.equals("DIMISSION", value)) {
						logger.info("取消管理员。" + "eid:" + eid + ",oid：" + oid);
					}
				}
			} catch (Throwable ignore) {
				logger.error("", ignore);
			}
		}

		public void destroy() {
			isStop.set(true);
		}

		private com.rabbitmq.client.Connection getConnection(
				com.rabbitmq.client.ConnectionFactory factory) {
			com.rabbitmq.client.Connection connection = null;
			while (connection == null && !isStop.get()) {
				try {
					connection = factory.newConnection();
				} catch (Throwable ignore) {
					connection = null;
				}
				if (connection == null) {
					try {
						Thread.sleep(500);
					} catch (Throwable ignore) {
					}
				}
			}
			return connection;
		}
	}
}