package com.mongo.morphia.complex.common.text;

import com.mongo.morphia.complex.common.Base64Encoder;





/**
 * 加密/解密转换
 * 
 * @since 2010-3-19
 * @author xichu_yu
 */
public abstract class Cipher {
    public static final Cipher DES = new Des();

    /** 加密字符串 */
    public String encrypt(String s, String key) {
        byte[] tmp = Encoding.stringToUtf8(s);
        return Base64Encoder.byteArrayToBase64(encrypt(tmp, key));
    }

    /** 解密字符串 */
    public String decrypt(String c, String key) {
        byte[] tmp = Base64Encoder.base64ToByteArray(c);
        return Encoding.utf8ToString(decrypt(tmp, key));
    }

    /** 对数据编码 */
    public abstract byte[] encrypt(byte[] data, String key);

    /** 对数据解码 */
    public abstract byte[] decrypt(byte[] data, String key);

    /** 测试方法 */
    public static void main(String[] args) throws Exception {
        Cipher ci = DES;
        String msg = "ABC";
        String key = "X";
        String c = ci.encrypt(msg, key);
        String d = ci.decrypt(c, key);
        System.out.println(msg);
        System.out.println(c);
        System.out.println(d);

    }
}