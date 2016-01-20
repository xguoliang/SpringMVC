package com.mongo.morphia.complex.core_dal.impl;

/**
 *
 * 
 * 修订历史记录：
 * 
 * Revision	1.0	 2012-5-7  guolei_mao  创建。
 * 
 */



import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongo.morphia.complex.core_dal.RedisBaseDAO;

import redis.clients.jedis.JedisPool;



/**
 * 类说明:
 * 
 * @author <a href="mailto:guolei_mao@kingdee.com">guolei_mao</a>
 * @version 1.0, 2012-5-7
 */
@SuppressWarnings("unchecked")
public abstract class RedisBaseDAOImpl<T extends Serializable> implements RedisBaseDAO<T> {
    @Autowired
    protected JedisPool jedisPool;

    protected Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.SSSz").create();

    public String toJson(T t) {
        return gson.toJson(t);
    }

    public T fromJson(String s) {
        Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
        return gson.fromJson(s, entityClass);
    }

//    public byte[] toByte(T t) throws IOException {
//        byte[] bytes = null;
//
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ObjectOutputStream oos = new ObjectOutputStream(baos);
//        oos.writeObject(t);
//
//        bytes = baos.toByteArray();
//
//        baos.close();
//        oos.close();
//
//        return (bytes);
//    }
//
//    public T fromByte(byte[] bytes) throws IOException, ClassNotFoundException {
//        T t = null;
//        ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
//        ObjectInputStream oi = new ObjectInputStream(bi);
//
//        t = (T) oi.readObject();
//
//        bi.close();
//        oi.close();
//        return t;
//    }
}
