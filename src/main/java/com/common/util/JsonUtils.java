package  com.common.util;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jettison.json.JSONObject;
/**
 * @since 2014-04-16數據同步要用到的JSON操作相關的工具類
 * @author lian_lin
 *
 */
public class JsonUtils {
	protected static final ObjectMapper mapper = new ObjectMapper();

//	static {
//		mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
////		mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//	}
//
//	public static String toJSONString(Object object) throws Exception {
//		try {
//			return net.sf.json.JSONObject.fromObject(object)
//			return mapper.writeValueAsString(object);
//		} catch (Throwable e) {
//			throw new Exception("Mapping Object to json failed!", e);
//		}
//	}
//
//	public static Object fromJSONString(String json, Class<?> clazz)
//			throws Exception {
//		try {
//			return mapper.readValue(json, clazz);
//		} catch (Throwable e) {
//			throw new Exception("Mapping from json failed!", e);
//		}
//	}
	
	
	public static JSONObject stringToJson(String content) throws Exception{
		try {
			return new JSONObject(content);
		} catch (Exception e) {
			throw new Exception("Mapping from json failed!", e);
		}
	}
	
	
	public static void main(String[] args){
		String content = "{ '_id' : '53795bb11a067220bd7d7bc1', 'userId' : '4e97fbeecce7b2ca3259658f', 'networkId' : '383cee68-cea3-4818-87ae-24fb46e081b1', 'userSeq' : '10003941', 'accountName' : 'yuanfei@kingdee.com', 'createTime' : ISODate('2014-05-19T01:17:37.433Z') }";
		
		content = "{'_id' : '53795bb11a067220bd7d7bc1',userId:'1111',accountName:'32222','createTime' : ISODate('2014-05-19T01:17:37.433Z')}";
		
		content= "{ '_id' : '53795bb11a067220bd7d7bc1', 'userId' : '4e97fbeecce7b2ca3259658f', 'networkId' : '383cee68-cea3-4818-87ae-24fb46e081b1', 'userSeq' : '10003941', 'accountName' : 'yuanfei@kingdee.com'}";
		
		
		try{
		    JSONObject json = JsonUtils.stringToJson(content);
		    System.out.println(json.getString("userId"));
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
}
