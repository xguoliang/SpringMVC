/**
 * Copyright 1993-2013 Kingdee Software (China), Co., Ltd. , Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */

package com.control.mq.Alllisten;

import java.io.UnsupportedEncodingException;

import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.control.mq.interf.SyncConsumeService;
/**
 * MQ小心的侦听类，启动之后自动侦听mq消息，并处理
 */

/**
 * @since 2014-04-15 同步程序MQ接收基础类
 * @author lian_lin
 */
public class SyncToxtMessageListener  implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(SyncToxtMessageListener.class);
    
    @Autowired
    private SyncToxtConsumerBuilder syncToxtConsumerBuilder;
    /*
     * (non-Javadoc)
     * 
     * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
     */
    @Override
    public void onMessage(final Message message) {
        try {
            String body = new String(message.getBody(), "UTF-8");
            try {
            	 logger.info("Process SyncToxtMessageListener message:"+body);
//                 JSONObject json = JsonUtils.stringToJson(body);
            	 net.sf.json.JSONObject json= net.sf.json.JSONObject.fromObject(body);
                 String consumeType = json.getString("syncType");
                 //根据侦听到的json.getString("syncType")来调用实现接口 SyncConsumeService的类的process处理方法
                 SyncConsumeService syncConsumeService = this.syncToxtConsumerBuilder.getFactory(consumeType);
                 if(syncConsumeService==null){
                	 logger.error("Process SyncToxtMessageListener error syncConsumeService is null consumeType:"+consumeType);
                 }
                 syncConsumeService.process(body);
            } catch (Throwable t) {
                logger.error("Process SyncToxtMessageListener[" + body + "] failed, cause by " + t.getMessage(), t);
            }
        } catch (UnsupportedEncodingException e1) {
            logger.error(e1.getMessage(), e1);
        }
    }
}
