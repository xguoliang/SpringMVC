package com.control.mq.singleListen;

import java.io.UnsupportedEncodingException;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * 用于向RabbitMQ发送消息的类
 * @author wenxiang_xu
 *
 */

public class SendMsgToRabbitMQ {
	public static final String DIMISSION_TYPE = "DIMISSION";
	public static final String CHANGE_TYPE = "CHANGE";
	public static final String SENDTO_CLIENT_MSG_ROUTING_KEY="SENDTO_CLIENT_MSG_ROUTING_KEY";
	public static final String SENDTO_CLIENT_MSG_EXCHANGE_NAME="SENDTO_CLIENT_MSG_EXCHANGE_NAME";
	public static final String SENDTO_OPEN_ADMIN_ROUTING_KEY="SENDTO_OPEN_ADMIN_ROUTING_KEY";
	public static final String SENDTO_OPEN_ADMIN_EXCHANGE_NAME="SENDTO_OPEN_ADMIN_EXCHANGE_NAME";
	public static final String SENDTO_XT_FIRST_LOGIN_KEY = "SENDTO_XT_FIRST_LOGIN_ROUTING_KEY";
	public static final String SENDTO_XT_FIRST_LOGIN_EXCHANGE_NAME = "SENDTO_XT_FIRST_LOGIN_EXCHANGE_NAME";
	public static final String SENDTO_PUBNUM_ROUTING_KEY="SENDTO_PUBNUM_ROUTING_KEY";
	public static final String SENDTO_PUBNUM_EXCHANGE_NAME="SENDTO_PUBNUM_EXCHANGE_NAME";
	
	public static final String SENDTO_MANAGE_SHARE_ROUTING_KEY = "SENDTO_MANAGE_SHARE_ROUTING_KEY" ;
	public static final String SENDTO_MANAGE_SHARE_EXCHANGE_NAME = "SENDTO_MANAGE_SHARE_EXCHANGE_NAME" ;
	
	
	private static Logger logger=LoggerFactory.getLogger(SendMsgToRabbitMQ.class);

    private ConnectionFactory rabbitConnectionFactory;
    /**
     *  向RabbitMQ发送一条消息
     * @param msg 待发送消息
     * @param exchangeName 消息交换“箱子”名称
     * @param routingKey 路由钥匙（消息交换名称和路由钥匙自己定义，需要与客户端一致）
     * @throws Exception 若干异常
     */
    public void send(JSONObject msg , String exchangeName, String routingKey) throws Exception{
    	long time0=System.currentTimeMillis();
    	logger.info("开始向RabbitMQ发送消息, 消息详情:\n\t exchangeName:"+exchangeName+"\n\t routingKey:"+routingKey+"\n\t msg:"+msg.toString());
    	RabbitTemplate template=  new RabbitTemplate(getRabbitConnectionFactory());
    	Message smsg=new Message(msg.toString().getBytes("utf-8"),new MessageProperties());
    	template.send(exchangeName,routingKey,smsg);
    	logger.info("向RabbitMQ发送消息成功,耗费时间:["+(System.currentTimeMillis()-time0)+" ms]");
    }
    /**
     * @author leon_yan
     * 向RabbitMQ发送一条队列消息，队列消息只能被一个消费者接受
     * @param msg:消息
     * @param queueName：队列名称
     * @throws Exception 
     */
    public void sendToQueue(JSONObject msg , String queueName) throws Exception{
    	logger.info("开始向RabbitMQ发送消息, 消息详情:\n\t queueName:"+queueName+" msg:"+msg.toString());
    	RabbitTemplate template=  new RabbitTemplate(getRabbitConnectionFactory());
    	template.setRoutingKey(queueName);
    	template.convertAndSend(msg.toString());
    }
	public ConnectionFactory getRabbitConnectionFactory() {
		return rabbitConnectionFactory;
	}
	public void setRabbitConnectionFactory(ConnectionFactory rabbitConnectionFactory) {
		this.rabbitConnectionFactory = rabbitConnectionFactory;
	}
}
