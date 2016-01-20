package com.common.util;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
//需要commons-pool2-2.0.jar和jedis.jar
public class JedisUtils {

	private static JedisPool pool = null;
	
	private static Log log = LogFactory.getLog(JedisUtils.class); 

	private static Lock lock = new ReentrantLock();
	
	public static Jedis jedis() {
		
		JedisPool pool = getJedisPool();
		Jedis jedis = pool.getResource();
		return jedis;
	}
	
	
	public static JedisPool getJedisPool() {

		if (pool == null) {
        	lock.lock();//线程锁
        	try{
        		if(pool == null){
            		JedisPoolConfig config = new JedisPoolConfig();
                    String url = StringUtils.getProperty("redis.url");
            		String password = StringUtils.getProperty("redis.password");
            		int port = Integer.valueOf(StringUtils.getProperty("redis.port"));
            		if(StringUtils.isEmpty(password)){
            			pool = new JedisPool(config, url, port);
            			
            		}else{
            			pool = new JedisPool(config, url, port,2*1000,password);
            		}
            	}
        	}finally{
        		lock.unlock();
        	}
        }
        return pool;
    }
	
	/**
     * 返还到连接池
     * 
     * @param pool 
     * @param redis
     */
    public static void close(Jedis redis) {
        
    	try{
    		if (redis != null) {
                getJedisPool().returnResource(redis);
            }
    	}catch(Exception e){
    		log.info(e.getMessage(), e);
    	}
    }
    
//    private static void cachePerson(PersonDao dao) throws Exception{
//    	
//    	int begin = 0;
//    	int length = 500;
//    	String eid = "10109";
//    	List<Map<String,Object>> list = dao.queryAllPersons(eid, begin, length);
//    	Jedis jedis =jedis();
//    	Map<String,String> jobs = null;
//		Map<String,String> orgNames = null;
//		String companyName = dao.queryEnterpriseNameByEid(eid);
//		List<String> idsNull = new LinkedList<String>();
//		
//		while(list.size() > 0){
//			
//			for(Map<String,Object> map:list){
//				
//				if(map.get("id")!=null){
//					idsNull.add((String)map.get("id"));
//				}
//				CommonUtils.mergePhone(map);
//			}
//			
//	  		for(Map<String,Object> map:list){
//				
//	  			jobs = dao.queryPersonJobTitle(idsNull);
//				orgNames = dao.queryPersonDept(idsNull);
//				idsNull.clear();
//	  			if(jobs != null){
//					
//	  				map.put("jobTitle", jobs.get(map.get("id")));
//				}
//				if(orgNames != null){
//					map.put("department", jobs.get(map.get("id")));
//				}
//				
//				JSONObject value = JSONObject.fromObject(map);
//				value.accumulate("companyName", companyName);
//				String updateTime = value.getString("lastUpdateTime");
//				int weights = value.getInt("weights");
//				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				long score = df.parse(updateTime).getTime();
//				jedis.zadd("opensys:openorg:person:updateTime"+value.getString("id"), score,value.toString());
//				jedis.zadd("opensys:openorg:person:weights"+value.getString("id"), weights,value.toString());
//			}
//		}
//		begin += length;
//		list = dao.queryAllPersons(eid, begin, length);		
//    }
//    
//    private static void cacheOrg(OrgDao dao) throws Exception{    	
//    	
//    }
    
    public static void main(String []arg) throws Exception{
    	
//    	@SuppressWarnings("resource")
//		ApplicationContext ctx = new ClassPathXmlApplicationContext("config/spring/spring-base.xml","config/spring/spring-dao.xml");
//		PersonDao personDao = (PersonDao)ctx.getBean("personDao");		
//		cachePerson(personDao);
//		
//		OrgDao orgDao = (OrgDao)ctx.getBean("orgDao");
//		cacheOrg(orgDao); 
    	Jedis j = jedis();
    	if(j.exists("opensys:openorg:getPersonsByIds:49fa03b4-9fca-4249-890b-7fe8355a1fff")){
    		j.del("opensys:openorg:getPersonsByIds:49fa03b4-9fca-4249-890b-7fe8355a1fff");
    	}
    	j.close();
    }
}