package com.test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.crypto.Data;

import com.common.httpHelp.apacheHttpClient;
import com.google.gson.Gson;
import com.google.gson.JsonArray;





import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

//nonce:string, //校验重复请求,格式为16位以内随机字符串
//eid:string, //注册号,格式为字符串
//data:string //业务数据,格式为BASE64编码的字符串,编码前的内容须加密处理

//{
//
//	eid:string //必填,注册号
//
//	persons:[{ //必填,人员列表
//
//	openId:string //必填,人员的openId
//
//	type:string, //必填,详见type类型值说明表
//
//	},…]}
public class OpenIntergerTest {
//	1 人员离职 1:正常 0:注销 有效，接口已支持此操作
//	2 人员恢复 0:注销 1:正常 无效，接口已支持此操作
//	3 人员禁用 1:正常 2:禁用 无效，接口已支持此操作
//	4 人员启用 2:禁用 1:正常 无效，接口已支持此操作

	public static void main(String arg[]){
			
//			String keyPath ="H:/download/105.key";
			String URL ="http://xttest.msbu.kingdee.com/openaccess/input/person/updateStatus";
//			String eid ="105";		
//			String openid ="93585b1d-67f4-43a5-9ec1-a8d67f7ea946";		
			
			
			String keyPath ="H:/download/103.key";
			String openid ="6484f142-2481-11e5-a74e-005056ac6b20";
			String eid ="103";		
//			String URL ="http://iworldhk2.nwd.com.hk/openaccess/input/person/updateStatus";
			String dataS = "{\"eid\":\""+eid + "\",\"persons\":[{\"openId\":\""+ openid +"\",\"type\":\"3\"}]}";
			String nonce="112132132";			
	        getReponse(keyPath, eid, URL, dataS, nonce);
			
		}

	/**
	 * @param keyPath
	 * @param eid
	 * @param URL
	 * @param dataS
	 * @param nonce
	 */
	private static void getReponse(String keyPath, String eid, String URL,
			String dataS, String nonce) {
		File file = new File(keyPath);
		if (!file.exists()) {
		    return;
		}
		FileInputStream input;
		Key key =null;
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream(); 
		try {
			input = new FileInputStream(file);
			
			byte[] byteArray = new byte[1024];
			int len=0;
			while((len=input.read(byteArray))>0){
				swapStream.write(byteArray);
			}
			byte[] byteData = swapStream.toByteArray();
			 key = OpenIntergerTest.restorePrivateKey(byteData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		      
		byte[] data = dataS.getBytes();
		try {
			byte[] dataJson = OpenIntergerTest.encryptLarger(data, key);
			byte[] datab =  Base64.encodeBase64(dataJson);
			String datas= new String(datab,"UTF-8");
			apacheHttpClient http = new apacheHttpClient();
			Map headerMap  = new HashMap<String,String>();
			headerMap.put("Content-Type", "application/x-www-form-urlencoded");
			Map paramsMap  = new HashMap<String,String>();
			paramsMap.put("eid", eid);
			paramsMap.put("data", datas);
			paramsMap.put("nonce", nonce);
			String rsp =http.sendHttpPostRequest(URL, headerMap, paramsMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	@Test
	public void addperson(){
		String keyPath ="H:/download/103.key";
		String eid ="103";	
		String nonce="112132132";	
		String URL ="http://xttest.msbu.kingdee.com/openaccess/input/person/add";
		
		Map<String,Object> personMap = new HashMap<String,Object>();
		personMap.put("name", "cai12");
		personMap.put("photoUrl", "");
		personMap.put("phone", "13611531499");
		personMap.put("phones", "13611531499");
//		personMap.put("isHidePhone", 0);
		personMap.put("email", "");
		personMap.put("department", "研发部\\PC研发");
		personMap.put("stats", "1");
		List<Object> personlist = new LinkedList<Object>();
		personlist.add(personMap);
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("eid", eid);
		dataMap.put("persons", personlist);
		
		String dataS=ObjToJson(dataMap);
		
		
		Map<String,Object> dataMap1  = new HashMap<String,Object>();
		Gson gson = new Gson();
		dataMap1=gson.fromJson(dataS, Map.class);
		
		
		getReponse(keyPath, eid, URL, dataS, nonce);
	}
	
	@Test
	public void updateperson(){
		String keyPath ="H:/download/103.key";
		String eid ="103";	
		String nonce="112132132";	
		String URL ="http://xttest.msbu.kingdee.com/openaccess/input/person/updateInfo";
		Map<String,Object> personMap = new HashMap<String,Object>();
		personMap.put("openId", "fee3cd57-2470-11e5-a74e-005056ac6b20");
		personMap.put("name", "LeSeT");
		personMap.put("officePhone1", "135226");
		List<Object> personlist = new LinkedList<Object>();
		personlist.add(personMap);
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("eid", eid);
		dataMap.put("persons", personlist);
		
		String dataS=ObjToJson(dataMap);
		
		
		Map<String,Object> dataMap1  = new HashMap<String,Object>();
		Gson gson = new Gson();
		dataMap1=gson.fromJson(dataS, Map.class);
		
		
		getReponse(keyPath, eid, URL, dataS, nonce);
	}	
	
	
	
	public static String ObjToJson(Object obj){
		Gson gson = new Gson();
		return gson.toJson(obj);
	}	
	
	public static byte[] encryptLarger(byte[] data, Key key) throws Exception {

		javax.crypto.Cipher rsa = javax.crypto.Cipher.getInstance("RSA");

		rsa.init(javax.crypto.Cipher.ENCRYPT_MODE, key);

		SecureRandom random = new SecureRandom();

		final byte[] secretKey = new byte[16];

		random.nextBytes(secretKey);

		final javax.crypto.Cipher aes = javax.crypto.Cipher.getInstance("AES");

		SecretKeySpec k = new SecretKeySpec(secretKey, "AES");

		aes.init(javax.crypto.Cipher.ENCRYPT_MODE, k);

		final byte[] ciphedKey = rsa.doFinal(secretKey);

		final byte[] ciphedData = aes.doFinal(data);

		byte[] result = new byte[128 + ciphedData.length];

		System.arraycopy(ciphedKey, 0, result, 0, 128);

		System.arraycopy(ciphedData, 0, result, 128, ciphedData.length);

		return result;

	}
	
	public static PrivateKey restorePrivateKey(byte[] bytes) throws Exception {
		PKCS8EncodedKeySpec pkcs = new PKCS8EncodedKeySpec(bytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePrivate(pkcs);
	}

}
