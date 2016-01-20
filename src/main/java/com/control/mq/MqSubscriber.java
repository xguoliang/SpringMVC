package com.control.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;

/**
 * @author leon_yan
 *
 */
/*
 * 1.初始化方法init（）之前，需要给rabbitConnectionFactory，exchangeName，consumer这3个对象进行赋值
 */
public class MqSubscriber {
	private static Logger logger = LoggerFactory.getLogger(MqSubscriber.class);
	private ConnectionFactory rabbitConnectionFactory;

	private String exchangeName;
	private IMqConsumer consumer;

	public void init() {
		Connection connection = getRabbitConnectionFactory().createConnection();
		Channel channel = connection.createChannel(false);
		try {
			channel.exchangeDeclare(getExchangeName(), "fanout");
			String queueName = channel.queueDeclare().getQueue();
			channel.queueBind(queueName, getExchangeName(), "");

			System.out
					.println(" [*] Waiting for messages. To exit press CTRL+C");

			final QueueingConsumer consumer = new QueueingConsumer(channel);
			channel.basicConsume(queueName, true, consumer);
			new Thread(new Runnable(){
				public void run(){
					while (true) {
						try{
							QueueingConsumer.Delivery delivery = consumer.nextDelivery();
							String message = new String(delivery.getBody(),"UTF-8");
							getConsumer().comsume(message);
							System.out.println(" MqSubscriber Received message:'" + message + "'");
							}catch(Throwable t){
								logger.error("",t);
							}
					}
				}
				
			}).start();
			
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	public String getExchangeName() {
		return exchangeName;
	}

	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}

	public IMqConsumer getConsumer() {
		return consumer;
	}

	public void setConsumer(IMqConsumer consumer) {
		this.consumer = consumer;
	}

	public ConnectionFactory getRabbitConnectionFactory() {
		return rabbitConnectionFactory;
	}

	public void setRabbitConnectionFactory(ConnectionFactory rabbitConnectionFactory) {
		this.rabbitConnectionFactory = rabbitConnectionFactory;
	}
}
