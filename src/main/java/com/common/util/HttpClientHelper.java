package com.common.util;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

public final class HttpClientHelper {

    private static HttpClientHelper instance = null;
    private static Lock lock = new ReentrantLock();
    private CloseableHttpClient httpClient;

    private HttpClientHelper() {
        instance = this;
    }

    public static HttpClientHelper getHttpClient() {
    	
        if (instance == null) {
            lock.lock();
            try{
            	if (instance == null) {
                    instance = new HttpClientHelper();
                    instance.init();
                }
            }finally{
            	 lock.unlock();
            }
        }
        return instance;
    }

    private void init() {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(500);
        cm.setDefaultMaxPerRoute(50);
        httpClient = HttpClientBuilder.create()
                .setConnectionManager(cm)
                .build();
    }

    public byte[] executeAndReturnByte(HttpRequestBase request) {
        HttpEntity entity = null;
        CloseableHttpResponse resp = null;
        byte[] rtn = new byte[0];
        if (request == null) 
        	return rtn;
        try {
        	if(httpClient == null){
        		
        		init();        		
        	}
        	if(httpClient == null){
        		
        		LogHelper.error("\n{"+request.getURI().toString()+"}\nreturn error {httpClient获取异常！}");
        		return rtn;        		
        	}
        	resp = httpClient.execute(request);
            entity = resp.getEntity();
            if (resp.getStatusLine().getStatusCode() == 200) {
                String encoding = ("" + resp.getFirstHeader("Content-Encoding")).toLowerCase();
                if (encoding.indexOf("gzip") > 0) {
                    entity = new GzipDecompressingEntity(entity);
                }
                rtn = EntityUtils.toByteArray(entity);
            } else {
            	LogHelper.error("\n{"+request.getURI().toString()+"}\nreturn error {"+resp.getStatusLine().getStatusCode()+"}");
            }
        } catch (Exception e) {
        	LogHelper.error(e.getMessage(), e);
        } finally {
            EntityUtils.consumeQuietly(entity);
            if (resp != null) {
                try {
                    resp.close();
                } catch (Exception ignore) {
                }
            }
        }
        return rtn;
    }

    public String execute(HttpRequestBase request) throws UnsupportedEncodingException {
        byte[] bytes = executeAndReturnByte(request);
        if (bytes == null || bytes.length == 0) 
        	return null;
        return new String(bytes,"UTF-8");
    }
}
