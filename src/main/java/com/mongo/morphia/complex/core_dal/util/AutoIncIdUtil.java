package com.mongo.morphia.complex.core_dal.util;

/**
 *
 * 
 * 修订历史记录：
 * 
 * Revision	1.0	 2011-9-13  guolei_mao  创建。
 * 
 */



import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 类说明:
 * 
 * @author <a href="mailto:guolei_mao@kingdee.com">guolei_mao</a>
 * @version 1.0, 2011-9-13
 */
@Component
public class AutoIncIdUtil {
    private static Logger logger = LoggerFactory.getLogger(AutoIncIdUtil.class);
    
    private static final String AUTO_REDIS_KEY = "kdweibo:autoincID:Redis:Map";
    private static final int STEP = 50;
    
    private static Map<String, Long> MAX = new ConcurrentHashMap<String, Long>();
    private static Map<String, Long> CURRENT = new ConcurrentHashMap<String, Long>();
    
    @Autowired(required=false)
    private JedisPool jedisPool;
    
    private static AutoIncIdUtil INSTANCE;
    
    private AutoIncIdUtil() {
    	INSTANCE = this;
    }

//    private static DBCollection getCollection() {
//        PersistenceInfo pi = DataSourceConfigManager.getByContext(null);
//        PersistenceUnitInfo pui = pi.getByName("oss");
//        DataSource ds = pui.getDataSource();
//        DB db = ds.getDB();
//        return db.getCollection("AutoIncId");
//    }

    /**
     * 启动两个 JVM，每个 3 个 Thead 获取 100000 并发没有出现重复数字，但是丢失了一个  STEP 的数据
     * 
     * power by -10
     * 
     * @param key
     * @return
     */
    public synchronized static long getNextId(String key) {
    	
    	long start = System.nanoTime();
    	
		if (!MAX.containsKey(key)) { //初始化
			long initMax = getRedisVal(key);
			long initCurrnt = initMax - STEP;
			CURRENT.put(key, initCurrnt);
			MAX.put(key, initMax);
		}
		long next = CURRENT.get(key) + 1;
		long max = MAX.get(key);
		if (next >= max) { //更新值
			max = getRedisVal(key);

			MAX.put(key, max);
			CURRENT.put(key, max-STEP);
		} else {
			CURRENT.put(key, next);
		}
		
		long end = System.nanoTime();
		long elapsed = end - start;
		if (elapsed/1000 > 100 ){
			logger.warn("getNextId get auto increase id : " + key + " elapsed(in millisecond): " + elapsed);
		}
		
		return next;
		
    }

	private static long getRedisVal(String key) {
		long start = System.nanoTime();
		
		Jedis jedis = INSTANCE.jedisPool.getResource();
		try {
		    
		    /***
		     * 
		     */
			return jedis.hincrBy(AUTO_REDIS_KEY, key, STEP);
		    
		} catch (Exception e) {
		    logger.error(e.getMessage(), e);
		    INSTANCE.jedisPool.returnBrokenResource(jedis);
		    throw new RuntimeException(e.getMessage(), e);
		} finally {
			INSTANCE.jedisPool.returnResource(jedis);
			
			long end = System.nanoTime();
			long elapsed = end - start;
			if (elapsed/1000 > 100 ){
				logger.warn("getRedisVal get auto increase id : " + key + " elapsed(in millisecond): " + elapsed);
			}
		}
	}

    public static long getNextId(Class<?> clazz) {
        return getNextId(clazz.getName());
    }
    
    public static String getNextIdBase62(String key) {
        return getNextIdBase62(key, true);
    }

    public static String getNextIdBase62(String key, boolean excludePureNumberAndLowerCase) {
        long id = getNextId(key);
        String idBase62 = toStringBase62(id);
        if (excludePureNumberAndLowerCase) {
            while (isPureNumber(idBase62) || isPureLowerCase(idBase62)) {
                id = getNextId(key);
                idBase62 = toStringBase62(id);
            }
        }
        return idBase62;
    }

    private static boolean isPureNumber(String idBase62) {
        for (int i = 0; i < idBase62.length(); i++) {
            char c = idBase62.charAt(i);
            if (c > digits[9])
                return false;
        }
        return true;
    }

    private static boolean isPureLowerCase(String idBase62) {
        for (int i = 0; i < idBase62.length(); i++) {
            char c = idBase62.charAt(i);
            if (c < digits[36])
                return false;
        }
        return true;
    }

    public static String getNextIdBase62(Class<?> clazz, boolean excludePureNumberAndLowerCase) {
        return getNextIdBase62(clazz.getName(), excludePureNumberAndLowerCase);
    }

    public static String getNextIdBase62(Class<?> clazz) {
        return getNextIdBase62(clazz, true);
    }

    private static String toStringBase62(long i) {
        StringBuffer sb = new StringBuffer();
        while (i > 0) {
            char c = digits[(int) i % 62];
            sb.insert(0, c);
            i /= 62;
        }
        return sb.toString();
    }

    final static char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
            'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b',
            'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
            'x', 'y', 'z' };

    /**
     * @param key
     * @param bits
     *            最少位数
     * @return
     */
    public static String getNextId(String key, int bits) {
        long id = getNextId(key);
        String s = Long.toString(id);
        while (s.length() < bits) {
            s = "0" + s;
        }
        return s;
    }

    public static String getNextId(Class<?> clazz, int bits) {
        return getNextId(clazz.getName(), bits);
    }
}
