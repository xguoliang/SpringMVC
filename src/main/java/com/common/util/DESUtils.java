package com.common.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class DESUtils {
	public static byte[] encrypt(byte[] src,String key) throws Exception{
		byte[] keybts=new byte[8];
		byte[] temp=key.getBytes("UTF-8");
		for(int i=0;i<temp.length;i++){
			if(i>=8){
				break;
			}
			keybts[i]=temp[i];
		}
		if(temp.length<8){
			for(int i=temp.length;i<8;i++){
				keybts[i]='\0';
			}
		}
		SecretKey deskey = new SecretKeySpec(keybts, "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec(keybts);
        cipher.init(Cipher.ENCRYPT_MODE, deskey,iv);
        byte[] encryptBts= cipher.doFinal(src);

        return encryptBts;
	}
	public static byte[] decrypt(byte[] dest,String key) throws Exception{
		byte[] keybts=new byte[8];
		byte[] temp=key.getBytes("UTF-8");
		for(int i=0;i<temp.length;i++){
			if(i>=8){
				break;
			}
			keybts[i]=temp[i];
		}
		if(temp.length<8){
			for(int i=temp.length;i<8;i++){
				keybts[i]='\0';
			}
		}
		SecretKey deskey = new SecretKeySpec(keybts, "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");   
        IvParameterSpec iv = new IvParameterSpec(keybts);
        cipher.init(Cipher.DECRYPT_MODE, deskey,iv);   
        return cipher.doFinal(dest);
	}

}
