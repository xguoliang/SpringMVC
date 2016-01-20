package com.common.base.imp;

import java.security.PublicKey;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.ContextLoader;

import redis.clients.jedis.Jedis;

import com.common.base.IBaseController;
import com.common.util.HttpHelper;
import com.common.util.JedisUtils;
import com.common.util.KeyHelper;
import com.common.util.RSAUtils;
import com.common.util.StringUtils;



public class BaseController extends Base implements IBaseController{
	
	private static Log log = LogFactory.getLog(BaseController.class);

	public Map<String,Object> initMessage(){
		
		Map<String,Object> map = new LinkedHashMap<String,Object>();
		map.put("success", true);
		map.put("error", null);
		map.put("errorCode", 100);
		map.put("data", null);
		return map;
	}
	
	protected String deEncrypt(String eid, String data, Map<String, Object> map) throws Exception {
		
		byte[] dest = Base64.decodeBase64(data.getBytes("UTF-8"));
		byte[] pubkeybyte = getPubKey(eid);
		if (pubkeybyte == null) {
			addMessage("非法MID，未在mcloud注册,密钥为空!", 103, eid, map);
			throw new Exception();
		}
		PublicKey pubkey = RSAUtils.restorePublicKey(pubkeybyte);
		byte[] src = null;
		try {
			src = RSAUtils.deEncryptLarger(dest, pubkey);
		} catch (Exception e) {
			addMessage("数据解密出错！", 104, eid, map);
			throw new Exception();
		}
		return new String(src, "UTF-8");
	}
	private byte[] getPubKey(String eid) throws Exception{
		
		if(StringUtils.isEmpty(eid)){
			return new byte[0];
		}
		byte[] key = ("opensys:company:pubkey"+eid).getBytes("UTF-8");
		Jedis jedis = JedisUtils.jedis();
		try{
			if(jedis != null && jedis.exists(key)){
				return jedis.get(key);
			}
		}catch(Exception e){
			log.info("获取缓存异常！"+e.getMessage());
		}finally{
			if(jedis != null){
				JedisUtils.close(jedis);
			}
		}
		
		if(StringUtils.isEmpty(eid)){
			return new byte[0];
		}
		String actionName = "appKey";
		String url = StringUtils.getProperty("mcloud.host")+"/extmcloud/open/"
				+ actionName
				+ ".action";
		String nonce = UUID.randomUUID().toString();
		JSONObject obj = new JSONObject();
		obj.put("eid", eid);
		obj.put("type","pub");//pri,pub
		
		Long timestamp = System.currentTimeMillis() / 1000;
		
		byte[] src = Base64.encodeBase64(RSAUtils.encryptLarger(obj.toString().getBytes("UTF-8"), KeyHelper.getPrivKey("mcloud")));

		String data = new String(src,"UTF-8");
		
		String signaUrl = "url=" + url + "&timestamp=" + timestamp +"&nonce=" + nonce 
				+ "&action=" + actionName + "&data=" + data;
		byte[] bytes = signaUrl.getBytes("utf-8");
		byte[] signBytes = RSAUtils.sign(bytes,KeyHelper.getPrivKey("mcloud"));
		String signature = new String(Base64.encodeBase64(signBytes));
		JSONObject jsonBody = new JSONObject();
		jsonBody.put("action", actionName);
		jsonBody.put("data", data);
		jsonBody.put("nonce", nonce);
		jsonBody.put("signature", signature);
		jsonBody.put("timestamp", timestamp);
		
		Map<String,String> headers = new LinkedHashMap<String, String>();
		headers.put("Content-type", "application/json;charset=utf-8");
		
		//从mcloud获取公钥
		String rtn = HttpHelper.post(headers, jsonBody.toString(), url);
		log.info("从mcloud获取的key："+rtn);
		JSONObject o = JSONObject.fromObject(rtn);
		if(o == null){
			log.info("从mcloud获取不到key："+rtn);
			return null;
		}		
		if(!o.isNullObject() && o.getBoolean("success") && o.get("data") instanceof String){
			
			String rt = o.getString("data");
			byte [] dest = Base64.decodeBase64(rt);
			byte [] pubKey = Base64.decodeBase64(RSAUtils.deEncryptLarger(dest, KeyHelper.getPrivKey("mcloud")));
			
			jedis = JedisUtils.jedis();
			try{
				if(jedis != null){					
					jedis.setnx(key,pubKey);
					jedis.expire(key, 1*24*60*60);
				}
			}catch(Exception e){
				log.info("获取缓存异常！"+e.getMessage());
			}finally{
				if(jedis != null){
					JedisUtils.close(jedis);
				}
			}
			return pubKey;
		}else{
			log.info("从mcloud获取key失败："+rtn);
			return null;
		}
	}	
	
	
	public void processError(Map<String,Object> map,Throwable e){
		
		if(StringUtils.isEmpty((String)map.get("error"))){
			if(e != null && e.getMessage() != null){
				
				map.put("error",e.getMessage());				
			}else if(e != null && e.getCause() != null){
				
				map.put("error",e.getCause().getMessage());
			}else if(e != null){
				map.put("error",e.toString());
			}
			map.put("errorCode", 1);
		}
		map.put("success", false);
		logError("出现错误了^^:",e);
	}
	
	public String ERROR(Map<String,Object> map){		

		return JSONObject.fromObject(map).toString();
	}
	
	public String SUCCESS(Map<String,Object> map){

		return JSONObject.fromObject(map).toString();
	}

	public Object getService(String name){
		
		return ContextLoader.getCurrentWebApplicationContext().getBean(name);
	}	
}