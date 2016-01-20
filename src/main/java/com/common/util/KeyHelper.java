package com.common.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Xinyonda
 * Date: 13-10-11
 * Time: 上午10:53
 * To change this template use File | Settings | File Templates.
 */
public class KeyHelper {

    private Logger logger = Logger.getLogger(KeyHelper.class);

    private Map<String,byte[]> keyMap = new HashMap<String, byte[]>();

    private static KeyHelper helper = new KeyHelper();
    
    @SuppressWarnings("rawtypes")
	private KeyHelper(){
        try {
            Properties properties = new Properties();
            InputStream is = new FileInputStream(this.getClass().getResource("/key.properties").getFile());
            properties.load(is);
            Iterator it = properties.keySet().iterator();
            while(it.hasNext()){
                String appkey =  (String)it.next();
                String path = (String)properties.get(appkey);
                @SuppressWarnings("resource")
				FileInputStream fis = new FileInputStream(this.getClass().getResource(path).getFile());
                byte[]src = new byte[fis.available()];
                fis.read(src);
                keyMap.put(appkey,src);
            }

        } catch(FileNotFoundException e) {
            logger.info(e.getMessage()+e.getCause());
        } catch (IOException e) {
            logger.info(e.getMessage()+e.getCause());
        } catch (Exception e) {
            logger.info(e.getMessage()+e.getCause());
        }
    }

    public static PublicKey getPubKey(String appkey) throws Exception{
    	
    	if(helper.keyMap.containsKey(appkey)){
			 return RSAUtils.restorePublicKey(helper.keyMap.get(appkey)); 
		 }else{
			 return null;
		 }
    }

    public static PrivateKey getPrivKey(String appkey) throws Exception{

    	if(helper.keyMap.containsKey(appkey)){
			 return RSAUtils.restorePrivateKey(helper.keyMap.get(appkey)); 
		 }else{
			 return null;
		 }
    }
}