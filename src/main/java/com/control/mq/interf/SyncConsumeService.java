package com.control.mq.interf;

/**
* @since 2014-04-15 异步消息消费端业务逻辑接口
* @author lian_lin
*/
public interface SyncConsumeService {
	public String getType();
	public void process(String message);
}
