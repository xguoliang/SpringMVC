/**
 * Copyright 1993-2014 Kingdee Software (China), Co., Ltd. , Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */

package com.control.mq.Alllisten;



import java.util.Collection;
import java.util.HashMap;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

import com.control.mq.interf.SyncConsumeService;


/**
 *
 * @since 2014-04-15 用户同步消息类实现
 * @author lian_lin
 */
@Component
public class SyncToxtConsumerBuilder implements ApplicationContextAware, SmartLifecycle{


    private HashMap<String, SyncConsumeService> factories = new HashMap<String, SyncConsumeService>();
    private volatile ApplicationContext applicationContext;
    private volatile boolean running;

    /**
     * @param type
     * @return
     */
    public SyncConsumeService getFactory(String type) {
        return factories.get(type);
    }

    /* 
     * 当一个类实现了这个接口（ApplicationContextAware）之后，这个类就可以方便获得ApplicationContext中的所有bean。换句话说
     */
    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;        
    }

    /* (non-Javadoc)
     * @see org.springframework.context.Lifecycle#start()
     */
    @Override
    public void start() {
        synchronized (this) {
            if (this.running) {
                return;
            }
            Collection<SyncConsumeService> factoryList= this.applicationContext.getBeansOfType(SyncConsumeService.class).values();
            for (SyncConsumeService factory : factoryList) {
                factories.put(factory.getType(), factory);
            }
            this.running = true;
        }        
    }

    /* (non-Javadoc)
     * @see org.springframework.context.Lifecycle#stop()
     */
    @Override
    public void stop() {
    }

    /* (non-Javadoc)
     * @see org.springframework.context.Lifecycle#isRunning()
     */
    @Override
    public boolean isRunning() {
        return running;
    }

    /* (non-Javadoc)
     * @see org.springframework.context.Phased#getPhase()
     */
    @Override
    public int getPhase() {
        return Integer.MIN_VALUE;
    }

    /* (non-Javadoc)
     * @see org.springframework.context.SmartLifecycle#isAutoStartup()
     */
    @Override
    public boolean isAutoStartup() {
        return true;
    }

    /* (non-Javadoc)
     * @see org.springframework.context.SmartLifecycle#stop(java.lang.Runnable)
     */
    @Override
    public void stop(Runnable callback) {
        
    }

    public HashMap<String, SyncConsumeService> getFactories() {
        return factories;
    }
}
